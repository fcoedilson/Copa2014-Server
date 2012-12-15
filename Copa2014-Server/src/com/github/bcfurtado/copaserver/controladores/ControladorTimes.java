package com.github.bcfurtado.copaserver.controladores;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;

import com.github.bcfurtado.copaserver.beans.Time;
import com.github.bcfurtado.copaserver.repositorios.TodosOsTimes;
import com.github.bcfurtado.copaserver.util.PreparaSessao;

public class ControladorTimes {

	private Session session;
	private TodosOsTimes todosOsTimes;
	
	public ControladorTimes() {
		session = PreparaSessao.pegarSessao();
		todosOsTimes = new TodosOsTimes();
	}
	
	public void cadastrarTime(String nome) {
		Time time = new Time();
		time.setNome(nome);
		time.ativar();
		
		session.beginTransaction();
		session.save(time);
		session.getTransaction().commit();
	}

	public void atualizarTime(Time time) {
		session.beginTransaction();
		session.update(time);
		session.getTransaction().commit();
	}

	public void removerTime(Long id) {
		Criteria criteria = session.createCriteria(Time.class)
				.add(Restrictions.eq("id", id));
		
		Time time = (Time)criteria.uniqueResult();
		
		session.beginTransaction();
		try {
			session.delete(time);
			session.getTransaction().commit();
		} catch (Exception e) {
			time.desativar();
			session.update(time);
			session.getTransaction().commit();
		}

	}

	public Time pegarTimePeloId(Long id) {

		Criteria criteria = session.createCriteria(Time.class)
				.add(Restrictions.eq("id", id));
		
		Time time = (Time) criteria.uniqueResult();
		return time;
	}
	
	public List<Time> pegarTodosOsTimes(){
		return todosOsTimes.pegarTodosOsTimesAtivados();
	}
}
