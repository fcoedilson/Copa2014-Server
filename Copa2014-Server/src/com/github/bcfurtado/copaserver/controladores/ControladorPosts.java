package com.github.bcfurtado.copaserver.controladores;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.github.bcfurtado.copaserver.beans.Jogo;
import com.github.bcfurtado.copaserver.beans.Post;
import com.github.bcfurtado.copaserver.beans.Time;
import com.github.bcfurtado.copaserver.beans.Usuario;
import com.github.bcfurtado.copaserver.repositorios.TodosOsPosts;
import com.github.bcfurtado.copaserver.util.PreparaSessao;

public class ControladorPosts {

	private Session session;
	private TodosOsPosts todosOsPosts;
	
	public ControladorPosts() {
		todosOsPosts = new TodosOsPosts();
	}
	
	public boolean cadastrarPost( String idPostFacebook, Time time, Jogo jogo, Usuario usuario ){
		
		Post post = new Post();
		post.setIdPostFacebook(idPostFacebook);
		post.setTime(time);
		post.setJogo(jogo);
		post.setUsuario(usuario);
		post.setAtivar(true);
		
		boolean resposta = false;
		
		session = PreparaSessao.pegarSessao();
		try {
			session.beginTransaction();;
			session.save(post);
			session.getTransaction().commit();
			resposta = true;
		} catch (Exception e) {
			resposta = false;
		} finally {
			session.close();
		}
		return resposta;
	}

	public List<Post> pegarTodosOsPostsDoJogo( Jogo jogo ) {
		session = PreparaSessao.pegarSessao();
		
		Criteria criteria = session.createCriteria(Post.class)
				.add(Restrictions.eq("jogo", jogo))
				.addOrder(Order.desc("id"));
		
		List<Post> posts = criteria.list();
		session.close();
		
		return posts;
	}
}
