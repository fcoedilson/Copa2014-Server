package com.github.bcfurtado.copaserver.controladores;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;

import com.github.bcfurtado.copaserver.beans.Noticia;
import com.github.bcfurtado.copaserver.repositorios.TodasAsNoticias;
import com.github.bcfurtado.copaserver.util.PreparaSessao;

public class ControladorNoticias {

	private Session session;
	private TodasAsNoticias todasAsNoticias;

	public ControladorNoticias() {
		session = PreparaSessao.pegarSessao();
		todasAsNoticias = new TodasAsNoticias();
	}

	public void cadastrarNoticias(String titulo, String corpo) {
		Noticia noticia = new Noticia();
		noticia.setTitulo(titulo);
		noticia.setCorpo(corpo);
		noticia.ativar();

		session = PreparaSessao.pegarSessao();
		session.beginTransaction();
		session.save(noticia);
		session.getTransaction().commit();
		session.close();
	}

	public void atualizarNoticia(Noticia noticia) {
		session = PreparaSessao.pegarSessao();
		session.beginTransaction();
		session.update(noticia);
		session.getTransaction().commit();
		session.close();
	}

	public void removerNoticia(Long id) {
		session = PreparaSessao.pegarSessao();
		Criteria criteria = session.createCriteria(Noticia.class)
				.add(Restrictions.eq("id", id));

		Noticia noticia = (Noticia)criteria.uniqueResult();

		session.beginTransaction();
		try {
			session.delete(noticia);
			session.getTransaction().commit();
		} catch (Exception e) {
			noticia.desativar();
			session.update(noticia);
			session.getTransaction().commit();
		} finally {
			session.close();
		}

	}

	public Noticia pegarNoticiaPeloId(Long id) {

		session = PreparaSessao.pegarSessao();
		Criteria criteria = session.createCriteria(Noticia.class)
				.add(Restrictions.eq("id", id));

		Noticia noticia = (Noticia) criteria.uniqueResult();
		session.close();
		return noticia;
	}

	public List<Noticia> pegarTodasNoticias(){
		return todasAsNoticias.pegarTodosAsNoticiasAtivadas();
	}



}