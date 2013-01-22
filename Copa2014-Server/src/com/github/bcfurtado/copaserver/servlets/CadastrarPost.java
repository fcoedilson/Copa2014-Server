package com.github.bcfurtado.copaserver.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.github.bcfurtado.copaserver.beans.Jogo;
import com.github.bcfurtado.copaserver.beans.Time;
import com.github.bcfurtado.copaserver.beans.Usuario;
import com.github.bcfurtado.copaserver.controladores.ControladorJogos;
import com.github.bcfurtado.copaserver.controladores.ControladorPosts;
import com.github.bcfurtado.copaserver.controladores.ControladorTimes;
import com.github.bcfurtado.copaserver.controladores.ControladorUsuarios;

public class CadastrarPost extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ControladorJogos controladorJogos;
	private ControladorUsuarios controladorUsuarios;
	private ControladorPosts controladorPosts;
	private ControladorTimes controladorTimes;
	
	public CadastrarPost() {
		controladorJogos = new ControladorJogos();
		controladorUsuarios = new ControladorUsuarios();
		controladorPosts = new ControladorPosts();
		controladorTimes = new ControladorTimes();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id_post_facebook = request.getParameter("id_post_facebook");
		String id_time = request.getParameter("id_time");
		String id_jogo = request.getParameter("id_jogo");
		String id_usuario = request.getParameter("id_usuario");

		boolean cadastrar = true;
		if (id_post_facebook == null || id_jogo == null || id_usuario == null || id_time == null
				|| id_post_facebook.isEmpty() || id_jogo.isEmpty() || id_usuario.isEmpty() || id_time.isEmpty()) {
			cadastrar = false;
		}

		boolean sucesso = false;
		if (cadastrar) {
			String idPostFacebook = id_post_facebook;
			Long idTime = Long.parseLong(id_time);
			Long idJogo = Long.parseLong(id_jogo);
			Long idUsuario = Long.parseLong(id_usuario);

			Time time = controladorTimes.pegarTimePeloId(idTime);
			Jogo jogo = controladorJogos.pegarJogoPeloId(idJogo);
			Usuario usuario = controladorUsuarios.pegarUsuarioPeloId(idUsuario);
			if ( time != null && jogo != null && usuario != null ) {
				sucesso = controladorPosts.cadastrarPost(idPostFacebook, time, jogo, usuario);
			} else {
				sucesso = false;
			}
		}
		
		HashMap<String,Object> hm = new HashMap<String,Object>();
		if (sucesso){
			hm.put("mensagem","Post cadastrado com sucesso.");
			hm.put("status", true);
		} else {
			hm.put("mensagem","Não foi possivel cadastar o post.");
			hm.put("status", false);
		}
		
		PrintWriter out = response.getWriter();
		JSONObject json = JSONObject.fromObject(hm);
		response.setContentType("application/json");
        out.print(json);
        out.flush();
	}

}
