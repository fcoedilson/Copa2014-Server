package com.github.bcfurtado.copaserver.repositorios;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;

import com.github.bcfurtado.copaserver.beans.Noticia;
import com.github.bcfurtado.copaserver.util.PreparaSessao;

public class TodasAsNoticias {

	private Session session;

	public TodasAsNoticias() {
		session = PreparaSessao.pegarSessao();
	}
	
	public List<Noticia> pegarTodosAsNoticiasAtivadas() {
		Criteria criteria = session.createCriteria(Noticia.class)
				.add(Restrictions.eq("ativo", true));
		List<Noticia> noticias = criteria.list();
		
		return noticias;
	}
	
	public List<Noticia> pegarTodasAsNoticiasDesativadas() {
		Criteria criteria = session.createCriteria(Noticia.class)
				.add(Restrictions.eq("ativo", false));
		List<Noticia> noticias = criteria.list();
		
		return noticias;
	}


	
}
