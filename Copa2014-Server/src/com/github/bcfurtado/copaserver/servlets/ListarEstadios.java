package com.github.bcfurtado.copaserver.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.github.bcfurtado.copaserver.beans.Estadio;
import com.github.bcfurtado.copaserver.controladores.ControladorEstadios;

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
		JSONArray json = JSONArray.fromObject(estadios);
		response.setContentType("application/json");
		
		PrintWriter out = response.getWriter();
		out.print(json);
		out.flush();
	}

}
