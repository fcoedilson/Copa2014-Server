package com.github.bcfurtado.copaserver.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.github.bcfurtado.copaserver.beans.Time;

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
		Long id_facebook = Long.parseLong(request.getParameter("id_facebook"));
		String email = request.getParameter("email");
		Long id_time = Long.parseLong(request.getParameter("id_time"));
		
		Time time = controladorTimes.pegarTimePeloId(id_time);
		
		boolean cadastrar = true;
		if (id_facebook == null || email == null || email.isEmpty() || id_time == null) {
			cadastrar = false;
		}
		
		if (cadastrar) {
			controladorUsuarios.cadastrarUsuario(id_facebook, time,email);
			HashMap<String,String> hm = new HashMap<String,String>();
			hm.put("message","Usu‡rio cadastrado.");
			
			JSONObject json = JSONObject.fromObject(hm);
			
			response.setContentType("application/json");
            
			PrintWriter out = response.getWriter();
            out.print(json);
            out.flush();
		}
	}

}
