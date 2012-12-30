package com.github.bcfurtado.copaserver.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.bcfurtado.copaserver.beans.Estadio;
import com.github.bcfurtado.copaserver.controladores.ControladorEstadios;
import com.google.gson.Gson;

public class ListarEstadios extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ControladorEstadios controladorEstadios;
	
	public ListarEstadios() {
		controladorEstadios = new ControladorEstadios();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Estadio> estadios = controladorEstadios.pegarTodosOsEstadios();
		response.setContentType("application/json");
		
		Gson gson = new Gson();
		String json = gson.toJson(estadios);
		
		PrintWriter out = response.getWriter();
		out.print(json);
		out.flush();
	}

}
