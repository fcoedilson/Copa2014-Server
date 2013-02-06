package com.github.bcfurtado.copaserver.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.github.bcfurtado.copaserver.beans.Time;
import com.github.bcfurtado.copaserver.beans.Usuario;

import com.github.bcfurtado.copaserver.controladores.ControladorTimes;
import com.github.bcfurtado.copaserver.controladores.ControladorUsuarios;

public class CadastrarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ControladorUsuarios controladorUsuarios;
	private ControladorTimes controladorTimes;
       
    public CadastrarUsuario() {
    	controladorUsuarios = new ControladorUsuarios();
    	controladorTimes = new ControladorTimes();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id_facebook = request.getParameter("id_facebook");
		String email = request.getParameter("email");
		String id_time = request.getParameter("id_time");
		
		
		boolean cadastrar = true;
		if (id_facebook == null || email == null || email.isEmpty() || id_time == null) {
			cadastrar = false;
		}
		
		JSONObject json = new JSONObject();
		
		if (cadastrar) {
			Long id_facebook_long = Long.parseLong(id_facebook);
			Long id_time_long = Long.parseLong(id_time);
			Time time = controladorTimes.pegarTimePeloId(id_time_long);

			Usuario usuario = controladorUsuarios.pegarUsuarioPeloId(id_facebook_long);
			
			if (usuario != null){
				json.put("mensagem","Usuario já cadastrado.");
				json.put("status", "2");	//ja cadastrado
			} else {
				boolean sucesso = controladorUsuarios.cadastrarUsuario(id_facebook_long, time,email);

				if ( sucesso ){
					json.put("mensagem","Usuario cadastrado com Sucesso.");
					json.put("status", "1");	//true
				} else {
					json.put("mensagem","Não foi possivel cadastar o usuário.");
					json.put("status", "0");	//false
				}
			}
			
			PrintWriter out = response.getWriter();
			response.setContentType("application/json");
            out.print(json);
            out.flush();
            
		} else {
			PrintWriter out = response.getWriter();
			json.put("mensagem", "Campos de cadastro invalidos.");
			json.put("status", "0");	//false
            out.print("Não foi possivel realizar o cadastro.");
            out.flush();
		}
	}

}
