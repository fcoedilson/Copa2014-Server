package com.github.bcfurtado.copaserver.repositorios;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;

import com.github.bcfurtado.copaserver.beans.Time;
import com.github.bcfurtado.copaserver.util.PreparaSessao;

public class TodosOsTimes {

	private Session session;

	public TodosOsTimes() {
		session = PreparaSessao.pegarSessao();
	}
	
	public List<Time> pegarTodosOsTimesAtivados() {
		Criteria criteria = session.createCriteria(Time.class)
				.add(Restrictions.eq("ativo", true));
		List<Time> times = criteria.list();
		
		return times;
	}
	
	public List<Time> pegarTodosOsTimesDesativados() {
		Criteria criteria = session.createCriteria(Time.class)
				.add(Restrictions.eq("ativo", false));
		List<Time> times = criteria.list();
		
		return times;
	}
	
}
