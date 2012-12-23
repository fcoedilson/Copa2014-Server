package com.github.bcfurtado.copaserver.util;

import java.util.GregorianCalendar;

import com.github.bcfurtado.copaserver.controladores.ControladorEstadios;
import com.github.bcfurtado.copaserver.controladores.ControladorJogos;
import com.github.bcfurtado.copaserver.controladores.ControladorNoticias;
import com.github.bcfurtado.copaserver.controladores.ControladorTimes;

public class InserirDados {
	public static void povoar() {

		/* Respeitar ordem de chamada dos metodos */
		cadastrarNoticias();
		cadastrarTimes();
		cadastrarEstadios();
		cadastrarJogos();
	}

	private static void cadastrarTimes() {
		ControladorTimes controlador = new ControladorTimes();

		controlador.cadastrarTime("Brasil");
		controlador.cadastrarTime("Japão");
		controlador.cadastrarTime("México");
		controlador.cadastrarTime("Ítalia");
		controlador.cadastrarTime("Espanha");
		controlador.cadastrarTime("Uruguai");
		controlador.cadastrarTime("Taiti");
		controlador.cadastrarTime("Representante Africano");

	}

	private static void cadastrarNoticias() {
		ControladorNoticias controlador = new ControladorNoticias();

		controlador.cadastrarNoticias("Brasil sera à sede da Copa de 2014",
						"A FIFA decidiu que o Brasil irá sediar a Copa do Mundo de 2014.");
		controlador.cadastrarNoticias("Corinthians Bi-Campeão Mundial!",
						"Domingo (17/12) o Corinthians foi Bi-Campeão Mundial em cima do Chelsea.");
	}

	/* Lista dos Estadios
	 * https://maps.google.com.br/maps/ms?msid=212659362010415557313.0004d17256294632815fb&msa=0&iwloc=A
	 */
	private static void cadastrarEstadios() {
		ControladorEstadios controlador = new ControladorEstadios();

		controlador.cadastrarEstadio("Castelão", -3.807217, -38.522688);
		controlador.cadastrarEstadio("Mané Garrincha", -15.784104, -47.898376);
		controlador.cadastrarEstadio("Maracanã", -22.912468, -43.226757);
		controlador.cadastrarEstadio("Estádio Fonte Nova", -12.978644, -38.504044);
		controlador.cadastrarEstadio("Mineirão", -19.866093, -43.971390);
		/*
		 * Coordenadas da Arena Pernambuco indefinida. Coordenadas de Pernambuco utilizadas.
		 */
		controlador
				.cadastrarEstadio("Arena Pernambuco", -12.382928, -41.396484);

	}

	private static void cadastrarJogos() {
		ControladorJogos controladorJogos = new ControladorJogos();
		ControladorTimes controladorTimes = new ControladorTimes();
		ControladorEstadios controladorEstadios = new ControladorEstadios();

		/* Grupo A */
		controladorJogos.cadastrarJogo(
				controladorTimes.pegarTimePeloNome("Brasil"),
				controladorTimes.pegarTimePeloNome("Japão"),
				controladorEstadios.pegarEstadioPeloNome("Mané Garrincha"),
				new GregorianCalendar(2013, 6, 15, 16, 00));

		controladorJogos.cadastrarJogo(
				controladorTimes.pegarTimePeloNome("México"),
				controladorTimes.pegarTimePeloNome("Ítalia"),
				controladorEstadios.pegarEstadioPeloNome("Maracanã"),
				new GregorianCalendar(2013, 6, 16, 16, 00));

		controladorJogos.cadastrarJogo(
				controladorTimes.pegarTimePeloNome("Brasil"),
				controladorTimes.pegarTimePeloNome("México"),
				controladorEstadios.pegarEstadioPeloNome("Castelão"),
				new GregorianCalendar(2013, 6, 19, 19, 00));

		controladorJogos.cadastrarJogo(
				controladorTimes.pegarTimePeloNome("Ítalia"),
				controladorTimes.pegarTimePeloNome("Japão"),
				controladorEstadios.pegarEstadioPeloNome("Arena Pernambuco"),
				new GregorianCalendar(2013, 6, 19, 19, 00));

		controladorJogos.cadastrarJogo(
				controladorTimes.pegarTimePeloNome("Japão"),
				controladorTimes.pegarTimePeloNome("México"),
				controladorEstadios.pegarEstadioPeloNome("Mineirão"),
				new GregorianCalendar(2013, 6, 22, 16, 00));

		controladorJogos.cadastrarJogo(
				controladorTimes.pegarTimePeloNome("Itália"),
				controladorTimes.pegarTimePeloNome("Brasil"),
				controladorEstadios.pegarEstadioPeloNome("Estádio Fonte Nova"),
				new GregorianCalendar(2013, 6, 22, 16, 00));

		/* Grupo B */
		controladorJogos.cadastrarJogo(
				controladorTimes.pegarTimePeloNome("Espanha"),
				controladorTimes.pegarTimePeloNome("Uruguai"),
				controladorEstadios.pegarEstadioPeloNome("Arena Pernambuco"),
				new GregorianCalendar(2013, 6, 16, 19, 00));
		
		controladorJogos.cadastrarJogo(
				controladorTimes.pegarTimePeloNome("Taiti"),
				controladorTimes.pegarTimePeloNome("Representante Africano"),
				controladorEstadios.pegarEstadioPeloNome("Mineirão"),
				new  GregorianCalendar(2013, 6, 17, 16, 00));

		controladorJogos.cadastrarJogo(
				controladorTimes.pegarTimePeloNome("Espanha"),
				controladorTimes.pegarTimePeloNome("Taiti"),
				controladorEstadios.pegarEstadioPeloNome("Maracanã"),
				new  GregorianCalendar(2013, 6, 20, 16, 00));
		
		controladorJogos.cadastrarJogo(
				controladorTimes.pegarTimePeloNome("Representante Africano"),
				controladorTimes.pegarTimePeloNome("Uruguai"),
				controladorEstadios.pegarEstadioPeloNome("Estádio Fonte Nova"),
				new  GregorianCalendar(2013, 6, 20, 16, 00));
		
		controladorJogos.cadastrarJogo(
				controladorTimes.pegarTimePeloNome("Representante Africano"),
				controladorTimes.pegarTimePeloNome("Espanha"),
				controladorEstadios.pegarEstadioPeloNome("Castelão"),
				new  GregorianCalendar(2013, 6, 20, 16, 00));

		controladorJogos.cadastrarJogo(
				controladorTimes.pegarTimePeloNome("Uruguai"),
				controladorTimes.pegarTimePeloNome("Taiti"),
				controladorEstadios.pegarEstadioPeloNome("Arena Pernambuco"),
				new  GregorianCalendar(2013, 6, 20, 16, 00));
		
		/* Semifinais e Final */
		// ...
	}
}
