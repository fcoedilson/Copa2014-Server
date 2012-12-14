package com.github.bcfurtado.copaserver.util;
import org.hibernate.classic.Session;
import com.github.bcfurtado.copaserver.util.GeraTabelas;

public class PreparaSessao {

	private static Session session = GeraTabelas.preparaSessao();
	
	public static Session pegarSessao(){
		return session;
	}
	
	
}
