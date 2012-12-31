package com.github.bcfurtado.copaserver.util;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.classic.Session;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import com.github.bcfurtado.copaserver.beans.Estadio;
import com.github.bcfurtado.copaserver.beans.Jogo;
import com.github.bcfurtado.copaserver.beans.Noticia;
import com.github.bcfurtado.copaserver.beans.Post;
import com.github.bcfurtado.copaserver.beans.Time;
import com.github.bcfurtado.copaserver.beans.Usuario;


public class GeraTabelas {

	public static void exportarEsquema(List<Class<? extends Object>> classes) {
		AnnotationConfiguration annotConfig = adicionaClassesConfiguracao(classes);

		SchemaExport se = new SchemaExport(annotConfig);
		se.create(true, true);
	}

	private static AnnotationConfiguration adicionaClassesConfiguracao(
			List<Class<? extends Object>> classes) {
		AnnotationConfiguration annotConfig = new AnnotationConfiguration(); // dom4j.jar,
																				// slf4j
																				// api
																				// e
																				// log4j

		for (Class classe : classes) {
			annotConfig.addAnnotatedClass(classe);
		}
		return annotConfig;
	}

	static Session preparaSessao() {
		AnnotationConfiguration annotConfig = adicionaClassesConfiguracao(initialize());
		SessionFactory sf = annotConfig.buildSessionFactory();
		Session session = sf.openSession();
		return session;
	}

	static void reiniciaEsquemaBD() {
		exportarEsquema(initialize());
	}

	private static List<Class<? extends Object>> initialize() {
		List<Class<? extends Object>> classes = new ArrayList<Class<? extends Object>>();

		// classes.add(SuaClasseAqui.class);
		classes.add(Usuario.class);
		classes.add(Time.class);
		classes.add(Estadio.class);
		classes.add(Jogo.class);
		classes.add(Noticia.class);
		classes.add(Post.class);

		return classes;
	}

}
