package com.github.bcfurtado.copaserver.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.github.bcfurtado.copaserver.beans.Noticia;
import com.github.bcfurtado.copaserver.controladores.ControladorNoticias;

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
			JSONObject json = JSONObject.fromObject(noticia);
			
			response.setContentType("application/json");	        
			PrintWriter out = response.getWriter();
	        out.print(json);
	        out.flush();
		}
	}

}
