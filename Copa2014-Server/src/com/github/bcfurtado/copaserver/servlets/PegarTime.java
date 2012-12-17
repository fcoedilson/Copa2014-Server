package com.github.bcfurtado.copaserver.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.github.bcfurtado.copaserver.beans.Time;
import com.github.bcfurtado.copaserver.controladores.ControladorTimes;


public class PegarTime extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private ControladorTimes controladorTimes;
	
    public PegarTime() {    	
        controladorTimes = new ControladorTimes();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Long id_time = Long.parseLong(request.getParameter("id_time"));
		Time time = controladorTimes.pegarTimePeloId(id_time);
		
		if ( id_time != null && time != null) {
			JSONObject json = JSONObject.fromObject(time);
			
			response.setContentType("application/json");	        
			PrintWriter out = response.getWriter();
	        out.print(json);
	        out.flush();
		}
		
	}

}
