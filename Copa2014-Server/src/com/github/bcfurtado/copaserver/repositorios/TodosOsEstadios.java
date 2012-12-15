package com.github.bcfurtado.copaserver.repositorios;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;

import com.github.bcfurtado.copaserver.beans.Estadio;

import com.github.bcfurtado.copaserver.util.PreparaSessao;

public class TodosOsEstadios {
	
	private Session session;

	public TodosOsEstadios() {
		session = PreparaSessao.pegarSessao();
	}
	
	public List<Estadio> pegarTodosOsEstadiosAtivados() {
		Criteria criteria = session.createCriteria(Estadio.class)
				.add(Restrictions.eq("ativo", true));
		List<Estadio> estadios = criteria.list();
		
		return estadios;
	}
	
	public List<Estadio> pegarTodosOsEstadiosDesativados() {
		Criteria criteria = session.createCriteria(Estadio.class)
				.add(Restrictions.eq("ativo", false));
		List<Estadio> estadios = criteria.list();
		
		return estadios;
	}


}
