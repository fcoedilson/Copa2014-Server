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
		todosOsUsuarios = new TodosOsUsuarios();
	}

	public boolean cadastrarUsuario(Long id_facebook, Time time, String email) {

		Usuario usuario = new Usuario();
		usuario.setId(id_facebook);
		usuario.setTime(time);
		usuario.setEmail(email);
		usuario.ativar();
		
		boolean resposta = false;
		
		session = PreparaSessao.pegarSessao();
		try {
			session.beginTransaction();
			session.save(usuario);
			session.getTransaction().commit();
			resposta = true;
		} catch (HibernateException e) {
			resposta = false;
		} catch (Exception e) {
			resposta = false;
		} finally {
			session.close();
		}
		return resposta;
	}

	public void atualizarUsuario(Usuario usuario) {
		session = PreparaSessao.pegarSessao();
		session.beginTransaction();
		session.update(usuario);
		session.getTransaction().commit();
		session.close();
	}

	public void removerUsuario( Long id_facebook ){
		session = PreparaSessao.pegarSessao();
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
		} finally {
			session.close();
		}

	}

	public Usuario pegarUsuarioPeloId(Long id_facebook) {
		session = PreparaSessao.pegarSessao();
		Criteria criteria = session.createCriteria(Usuario.class)
				.add(Restrictions.eq("id_facebook", id_facebook));
		
		Usuario usuario = (Usuario) criteria.uniqueResult();
		session.close();
		return usuario;
	}

	public List<Usuario> pegarTodosOsUsuarios() {
		return todosOsUsuarios.pegarTodosOsUsuarios();
	}
	
	public Usuario autorizarUsuario(Time time, String email) {
		session = PreparaSessao.pegarSessao();
		Criteria criteria = session.createCriteria(Usuario.class)
				.add(Restrictions.eq("time", time))
				.add(Restrictions.eq("email", email));

		Usuario usuario = (Usuario) criteria.uniqueResult();
		session.close();
		
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