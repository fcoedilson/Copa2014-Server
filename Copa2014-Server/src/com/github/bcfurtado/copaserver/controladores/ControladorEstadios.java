package com.github.bcfurtado.copaserver.controladores;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;


import com.github.bcfurtado.copaserver.beans.Estadio;
import com.github.bcfurtado.copaserver.repositorios.TodosOsEstadios;
import com.github.bcfurtado.copaserver.util.PreparaSessao;

public class ControladorEstadios {

	private Session session;
	private TodosOsEstadios todosOsEstadios;

	public ControladorEstadios() {
		todosOsEstadios = new TodosOsEstadios();
	}

	public void cadastrarEstadio(String nome, Double latitude, Double longitude) {
		Estadio estadio = new Estadio();
		estadio.setNome(nome);
		estadio.setLatitude(latitude);
		estadio.setLongitude(longitude);
		estadio.ativar();
		
		session = PreparaSessao.pegarSessao();
		session.beginTransaction();
		session.save(estadio);
		session.getTransaction().commit();
		session.close();
	}

	public void atualizarEstadio(Estadio estadio) {
		session = PreparaSessao.pegarSessao();
		session.beginTransaction();
		session.update(estadio);
		session.getTransaction().commit();
		session.close();
	}

	public void removerEstadio(Long id) {
		Criteria criteria = session.createCriteria(Estadio.class)
				.add(Restrictions.eq("id", id));

		Estadio estadio = (Estadio)criteria.uniqueResult();

		session = PreparaSessao.pegarSessao();
		session.beginTransaction();
		try {
			session.delete(estadio);
			session.getTransaction().commit();
		} catch (Exception e) {
			estadio.desativar();
			session.update(estadio);
			session.getTransaction().commit();
		} finally {
			session.close();
		}

	}

	public Estadio pegarEstadioPeloId(Long id) {
		session = PreparaSessao.pegarSessao();
		Criteria criteria = session.createCriteria(Estadio.class)
				.add(Restrictions.eq("id", id));

		Estadio estadio = (Estadio) criteria.uniqueResult();
		session.close();
		
		return estadio;
	}
	
	public Estadio pegarEstadioPeloNome(String nome) {
		session = PreparaSessao.pegarSessao();
		Criteria criteria = session.createCriteria(Estadio.class)
				.add(Restrictions.eq("nome", nome));

		Estadio estadio = (Estadio) criteria.uniqueResult();
		session.close();
		return estadio;
	}

	public List<Estadio> pegarTodosOsEstadios(){
		return todosOsEstadios.pegarTodosOsEstadiosAtivados();
	}


}