package com.github.bcfurtado.copaserver.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.github.bcfurtado.copaserver.beans.Noticia;
import com.github.bcfurtado.copaserver.controladores.ControladorNoticias;

public class ListarNoticias extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ControladorNoticias controladorNoticias;

	
    public ListarNoticias() {
        super();
        controladorNoticias = new ControladorNoticias();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Noticia> noticias = controladorNoticias.pegarTodasNoticias();
		
		JSONArray json = JSONArray.fromObject(noticias);
		response.setContentType("application/json");
        
		PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
	}

}
