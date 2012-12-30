package com.github.bcfurtado.copaserver.repositorios;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.github.bcfurtado.copaserver.beans.Estadio;
import com.github.bcfurtado.copaserver.beans.Jogo;
import com.github.bcfurtado.copaserver.controladores.ControladorEstadios;
import com.github.bcfurtado.copaserver.util.PreparaSessao;

public class TodosOsJogos {

	private Session session;

	public TodosOsJogos() {
	}

	public List<Jogo> pegarTodosOsJogosAtivados() {
		session = PreparaSessao.pegarSessao();
		Criteria criteria = session.createCriteria(Jogo.class)
				.add(Restrictions.eq("ativo", true));
		List<Jogo> jogos = criteria.list();
		session.close();

		return jogos;
	}

	public List<Jogo> pegarTodosOsJogosDesativados() {
		session = PreparaSessao.pegarSessao();
		Criteria criteria = session.createCriteria(Jogo.class)
				.add(Restrictions.eq("ativo", false));
		List<Jogo> jogos = criteria.list();
		session.close();
		
		return jogos;
	}

	public List<Jogo> pegarTodosOsJogosDeHoje() {
		GregorianCalendar inicio = new GregorianCalendar();
		inicio.setTimeInMillis(System.currentTimeMillis());
		inicio.set(GregorianCalendar.HOUR_OF_DAY, 0);
		inicio.set(GregorianCalendar.MINUTE, 0);
		inicio.set(GregorianCalendar.SECOND, 1);
		
		GregorianCalendar fim = new GregorianCalendar();
		fim.setTimeInMillis(System.currentTimeMillis());
		fim.set(GregorianCalendar.HOUR_OF_DAY, 23);
		fim.set(GregorianCalendar.MINUTE, 59);
		fim.set(GregorianCalendar.SECOND, 59);
		
		session = PreparaSessao.pegarSessao();
		Criteria criteria = session.createCriteria(Jogo.class)
				.add(Restrictions.between("horario", inicio, fim) );

		List<Jogo> jogos = criteria.list();
		session.close();
		
		return jogos;
	}
	
	public List<Jogo> pegarTodosOsProximosJogos(){
		GregorianCalendar inicio = new GregorianCalendar();
		inicio.setTimeInMillis(System.currentTimeMillis());
		inicio.set(GregorianCalendar.HOUR_OF_DAY, 0);
		inicio.set(GregorianCalendar.MINUTE, 0);
		inicio.set(GregorianCalendar.SECOND, 1);
		
		ControladorEstadios controladorEstadios = new ControladorEstadios();
		
		List<Jogo> jogos = new ArrayList<Jogo>();
		
		for (Estadio estadio : controladorEstadios.pegarTodosOsEstadios()) {
			session = PreparaSessao.pegarSessao();

			Criteria criteria = session.createCriteria(Jogo.class)
					.add(Restrictions.ge("horario", inicio))
					.add(Restrictions.eq("local", estadio))
					.addOrder(Order.asc("horario"));
			
			List<Jogo> proximosJogoDoEstadioAtual = criteria.list();
			if ( proximosJogoDoEstadioAtual != null && !proximosJogoDoEstadioAtual.isEmpty() ){
				jogos.add(proximosJogoDoEstadioAtual.get(0));
			}
		}
		session.close();
		
		return jogos;
		
	}

}