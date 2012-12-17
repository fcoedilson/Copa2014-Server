package com.github.bcfurtado.copaserver.controladores;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;

import com.github.bcfurtado.copaserver.beans.Time;
import com.github.bcfurtado.copaserver.beans.Usuario;
import com.github.bcfurtado.copaserver.repositorios.TodosOsUsuarios;
import com.github.bcfurtado.copaserver.util.PreparaSessao;

public class ControladorUsuarios {

	private Session session;
	private TodosOsUsuarios todosOsUsuarios;
	
	public ControladorUsuarios() {
		session = PreparaSessao.pegarSessao();
		todosOsUsuarios = new TodosOsUsuarios();
	}

	public boolean cadastrarUsuario(Long id_facebook, Time time, String email) {

		Usuario usuario = new Usuario();
		usuario.setId(id_facebook);
		usuario.setTime(time);
		usuario.setEmail(email);
		usuario.ativar();
		
		try {
			session.beginTransaction();
			session.save(usuario);
			session.getTransaction().commit();
			return true;
		} catch (HibernateException e) {
			return false;
		} catch (Exception e) {
			return false;
		}
		
		//return session.getTransaction().wasCommitted();
		
	}

	public void atualizarUsuario(Usuario usuario) {
		session.beginTransaction();
		session.update(usuario);
		session.getTransaction().commit();
	}

	public void removerUsuario( Long id_facebook ){
		Criteria criteria = session.createCriteria(Usuario.class)
				.add(Restrictions.eq("id_facebook", id_facebook));
		
		Usuario usuario = (Usuario) criteria.uniqueResult();
		
		session.beginTransaction();	
		try {
			session.delete(usuario);
			session.getTransaction().commit();
		} catch (Exception e) {
			usuario.desativar();
			session.update(usuario);
			session.getTransaction().commit();
		}

	}

	public Usuario pegarUsuarioPeloId(Long id_facebook) {
		Criteria criteria = session.createCriteria(Usuario.class)
				.add(Restrictions.eq("id_facebook", id_facebook));
		
		Usuario usuario = (Usuario) criteria.uniqueResult();
		return usuario;
	}

	public List<Usuario> pegarTodosOsUsuarios() {
		return todosOsUsuarios.pegarTodosOsUsuarios();
	}
	
	public Usuario autorizarUsuario(Time time, String email) {
		Criteria criteria = session.createCriteria(Usuario.class)
				.add(Restrictions.eq("time", time))
				.add(Restrictions.eq("email", email));

		Usuario usuario = (Usuario) criteria.uniqueResult();
		
		if ( usuario != null) {
			if (usuario.isAtivo()) {
				return usuario;
			} else {
				return null;
			}
		}else {
			return null;
		}
	}

}