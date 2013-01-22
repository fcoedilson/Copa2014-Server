package com.github.bcfurtado.copaserver.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.bcfurtado.copaserver.beans.Jogo;
import com.github.bcfurtado.copaserver.beans.Post;
import com.github.bcfurtado.copaserver.controladores.ControladorJogos;
import com.github.bcfurtado.copaserver.controladores.ControladorPosts;
import com.google.gson.Gson;


public class ListarTodosOsPostsDoJogo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ControladorPosts controladorPosts;
	private ControladorJogos controladorJogos;
	
    public ListarTodosOsPostsDoJogo() {
    	controladorPosts = new ControladorPosts();
    	controladorJogos = new ControladorJogos();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id_jogo = request.getParameter("id_jogo");
		
		if ( id_jogo != null && !id_jogo.isEmpty() ) {
			
			Jogo jogo = controladorJogos.pegarJogoPeloId(Long.parseLong(id_jogo));
			
			List<Post> posts = controladorPosts.pegarTodosOsPostsDoJogo(jogo);
			
			response.setContentType("application/json");
	        
			Gson gson = new Gson();
			String json = gson.toJson(posts);
			
			PrintWriter out = response.getWriter();
	        out.print(json);
	        out.flush();
		}
	}

}
