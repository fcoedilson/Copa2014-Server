package com.github.bcfurtado.copaserver.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.bcfurtado.copaserver.beans.Badge;
import com.github.bcfurtado.copaserver.controladores.ControladorUsuarios;
import com.google.gson.Gson;

public class PegarBadges extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private ControladorUsuarios controladorUsuarios;
	
    public PegarBadges() {    	
        controladorUsuarios = new ControladorUsuarios();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Long id_usuario = Long.parseLong(request.getParameter("id_usuario"));
		List<Badge> badges = controladorUsuarios.pegarBadges(id_usuario);
		
		if ( id_usuario != null && badges != null) {
			Gson gson = new Gson();
			String json = gson.toJson(badges);
			
			response.setContentType("application/json");	        
			PrintWriter out = response.getWriter();
	        out.print(json);
	        out.flush();
		}
		
	}

}
