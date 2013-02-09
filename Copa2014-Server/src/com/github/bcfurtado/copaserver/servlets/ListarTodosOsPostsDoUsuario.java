package com.github.bcfurtado.copaserver.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.bcfurtado.copaserver.beans.Jogo;
import com.github.bcfurtado.copaserver.beans.Post;
import com.github.bcfurtado.copaserver.beans.Usuario;
import com.github.bcfurtado.copaserver.controladores.ControladorJogos;
import com.github.bcfurtado.copaserver.controladores.ControladorPosts;
import com.github.bcfurtado.copaserver.controladores.ControladorUsuarios;
import com.google.gson.Gson;

public class ListarTodosOsPostsDoUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private ControladorJogos controladorJogos;
	private ControladorPosts controladorPosts;
	private ControladorUsuarios controladorUsuarios;
    
    public ListarTodosOsPostsDoUsuario() {
    	controladorJogos = new ControladorJogos();
    	controladorPosts = new ControladorPosts();
    	controladorUsuarios = new ControladorUsuarios();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id_jogo = request.getParameter("id_jogo");
		String id_usuario_fb = request.getParameter("id_usuario_fb");
		
		if ( id_jogo != null && id_usuario_fb != null && !id_jogo.isEmpty() && !id_usuario_fb.isEmpty() ) {
			
			Usuario usuario = controladorUsuarios.pegarUsuarioPeloId(Long.parseLong(id_usuario_fb));
			
			List<Post> posts = new ArrayList<Post>();
			if ( usuario != null ) {
				Jogo jogo = controladorJogos.pegarJogoPeloId(Long.parseLong(id_jogo));
				posts = controladorPosts.pegarTodosOsPostsDoUsuario(jogo, usuario);
			}
			
			response.setContentType("application/json");
	        
			Gson gson = new Gson();
			String json = gson.toJson(posts);
			
			PrintWriter out = response.getWriter();
	        out.print(json);
	        out.flush();
		}
	}

}
