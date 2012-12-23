package com.github.bcfurtado.copaserver.controladores;

import java.util.GregorianCalendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;

import com.github.bcfurtado.copaserver.beans.Estadio;
import com.github.bcfurtado.copaserver.beans.Jogo;
import com.github.bcfurtado.copaserver.beans.Time;
import com.github.bcfurtado.copaserver.repositorios.TodosOsJogos;
import com.github.bcfurtado.copaserver.util.PreparaSessao;

public class ControladorJogos {

	private Session session;
	private TodosOsJogos todosOsJogos;

	public ControladorJogos() {
		todosOsJogos = new TodosOsJogos();
	}

	public void cadastrarJogo(Time time_a, Time time_b, Estadio local, GregorianCalendar horario) {
		Jogo jogo = new Jogo();
		jogo.setTime_a(time_a);
		jogo.setTime_b(time_b);
		jogo.setLocal(local);
		jogo.setHorario(horario);
		jogo.ativar();
		
		session = PreparaSessao.pegarSessao();
		session.beginTransaction();
		session.save(jogo);
		session.getTransaction().commit();
		session.close();
		
	}

	public void atualizarJogo(Jogo jogo) {
		session = PreparaSessao.pegarSessao();
		session.beginTransaction();
		session.update(jogo);
		session.getTransaction().commit();
		session.close();
	}

	public void removerJogo(Long id) {
		Criteria criteria = session.createCriteria(Jogo.class)
				.add(Restrictions.eq("id", id));

		Jogo jogo = (Jogo)criteria.uniqueResult();

		session = PreparaSessao.pegarSessao();
		session.beginTransaction();
		try {
			session.delete(jogo);
			session.getTransaction().commit();
		} catch (Exception e) {
			jogo.desativar();
			session.update(jogo);
			session.getTransaction().commit();
		} finally {
			session.close();
		}

	}

	public Jogo pegarJogoPeloId(Long id) {
		session = PreparaSessao.pegarSessao();
		Criteria criteria = session.createCriteria(Jogo.class)
				.add(Restrictions.eq("id", id));

		Jogo jogo = (Jogo) criteria.uniqueResult();
		session.close();
		return jogo;
	}

	public List<Jogo> pegarTodosOsJogos(){
		return todosOsJogos.pegarTodosOsJogosAtivados();
	}



}