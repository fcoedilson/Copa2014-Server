package com.github.bcfurtado.copaserver.util;

import org.hibernate.classic.Session;

import com.github.bcfurtado.copaserver.beans.Time;
import com.github.bcfurtado.copaserver.beans.Usuario;
import com.github.bcfurtado.copaserver.util.GeraTabelas;

public class InserirDados {
	public static void povoar(){
		
		Session session = GeraTabelas.preparaSessao();
		
		session.beginTransaction();
		
		Time timeBrasil = new Time();
		timeBrasil.setNome("Brasil");
		timeBrasil.ativar();
		
		Usuario usuarioTeste1 = new Usuario();
		usuarioTeste1.setId((long) 1);
		usuarioTeste1.setEmail("teste1@gmail.com");
		usuarioTeste1.setTime(timeBrasil);
		usuarioTeste1.ativar();
		
		session.save(timeBrasil);
		session.save(usuarioTeste1);

		session.getTransaction().commit();	
		session.close();
		
		Session session2 = GeraTabelas.preparaSessao();
		
		session2.beginTransaction();
		
		Time timeArgentina = new Time();
		timeArgentina.setNome("Argentina");
		timeArgentina.ativar();
		
		Usuario usuarioTeste2 = new Usuario();
		usuarioTeste2.setId((long) 2);
		usuarioTeste2.setEmail("teste2@gmail.com");
		usuarioTeste2.setTime(timeArgentina);
		usuarioTeste2.ativar();
		
		session2.save(timeArgentina);
		session2.save(usuarioTeste2);
		
		session2.getTransaction().commit();	
		session2.close();
	}
}
