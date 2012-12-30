package com.github.bcfurtado.copaserver.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.bcfurtado.copaserver.beans.Noticia;
import com.github.bcfurtado.copaserver.controladores.ControladorNoticias;
import com.google.gson.Gson;

public class PegarNoticia extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private ControladorNoticias controladorNoticias;
	
    public PegarNoticia() {
        controladorNoticias = new ControladorNoticias();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long id_noticia = Long.parseLong(request.getParameter("id_noticia"));
		Noticia noticia = controladorNoticias.pegarNoticiaPeloId(id_noticia);
		
		if ( id_noticia != null && noticia != null) {
			
			Gson gson = new Gson();
			String json = gson.toJson(noticia);
			
			response.setContentType("application/json");	        
			PrintWriter out = response.getWriter();
	        out.print(json);
	        out.flush();
		}
	}

}
