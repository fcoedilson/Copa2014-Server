package com.github.bcfurtado.copaserver.util;

import com.github.bcfurtado.copaserver.util.GeraTabelas;
import com.github.bcfurtado.copaserver.util.InserirDados;

public class PovoaDados {
	
	public static void main (String[] args){
		GeraTabelas.reiniciaEsquemaBD();
		InserirDados.povoar();
	}
	
	public static void instalar(){
		GeraTabelas.reiniciaEsquemaBD();
		InserirDados.povoar();
	}
	
}
