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
import com.github.bcfurtado.copaserver.beans.Time;
import com.github.bcfurtado.copaserver.controladores.ControladorJogos;
import com.github.bcfurtado.copaserver.controladores.ControladorPosts;
import com.github.bcfurtado.copaserver.controladores.ControladorTimes;
import com.google.gson.Gson;

public class ListarTodosOsPostsDaTorcida extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ControladorPosts controladorPosts;
	private ControladorJogos controladorJogos;
	private ControladorTimes controladorTimes;
	
	public ListarTodosOsPostsDaTorcida() {
		controladorPosts = new ControladorPosts();
		controladorJogos = new ControladorJogos();
		controladorTimes = new ControladorTimes();    	
	}

	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id_jogo = request.getParameter("id_jogo");
		String id_time = request.getParameter("id_time");

		if (id_jogo != null && id_time != null && !id_jogo.isEmpty() && !id_time.isEmpty()) {

			Jogo jogo = controladorJogos.pegarJogoPeloId(Long.parseLong(id_jogo));
			Time time = controladorTimes.pegarTimePeloId(Long.parseLong(id_time));

			List<Post> posts = controladorPosts.pegarTodosOsPostsDaTorcida(	jogo, time);
			response.setContentType("application/json");
			Gson gson = new Gson();
			String json = gson.toJson(posts);
			PrintWriter out = response.getWriter();
			out.print(json);
			out.flush();
		}
	}

}
