package com.github.bcfurtado.copaserver.repositorios;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;

import com.github.bcfurtado.copaserver.beans.Jogo;
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



}