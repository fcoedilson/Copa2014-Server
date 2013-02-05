package com.github.bcfurtado.copaserver.controladores;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.github.bcfurtado.copaserver.beans.Jogo;
import com.github.bcfurtado.copaserver.beans.Post;
import com.github.bcfurtado.copaserver.beans.Usuario;

public class ControladorBadges {

	private Session session;
	List<Post> postsJogo = new ArrayList<Post>();
	List<Usuario> usuarios = new ArrayList<Usuario>();

	public ControladorBadges(Jogo jogo) {
		postsJogo = new ControladorPosts().pegarTodosOsPostsDoJogo(jogo);
	}

	public void obterUsuariosOnlineNoJogo() {
		for (Post p : postsJogo) {
			usuarios.add(p.getUsuario());
		}
	}

	public void distribuirBadgesVitoria() {

	}

}
