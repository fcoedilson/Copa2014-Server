package com.github.bcfurtado.copaserver.util;

import org.hibernate.classic.Session;

import com.github.bcfurtado.copaserver.beans.Time;
import com.github.bcfurtado.copaserver.beans.Usuario;
import com.github.bcfurtado.copaserver.util.GeraTabelas;

public class InserirDados {
	public static void povoar(){
		
		Session session = GeraTabelas.preparaSessao();
		
		session.beginTransaction();
		
		Time time = new Time();
		time.setNome("Brasil");
		
		Usuario usuario = new Usuario();
		usuario.setId((long) 1231231421);
		usuario.setEmail("rhonanc@gmail.com");
		usuario.setTime(time);
		
		session.save(time);
		session.save(usuario);
		session.getTransaction().commit();
		
		
		session.close();
	}
}
