package com.github.bcfurtado.copaserver.repositorios;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.classic.Session;

import com.github.bcfurtado.copaserver.beans.Usuario;
import com.github.bcfurtado.copaserver.util.PreparaSessao;

public class TodosOsUsuarios {

	private Session session;
	
	public TodosOsUsuarios(){
	}

	public List<Usuario> pegarTodosOsUsuarios() {
		session = PreparaSessao.pegarSessao();
		Criteria criteria = session.createCriteria(Usuario.class);
		List<Usuario> usuarios = criteria.list();
		session.close();
		
		return usuarios;

	}

}