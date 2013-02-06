package com.github.bcfurtado.copaserver.controladores;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.github.bcfurtado.copaserver.beans.Badge;
import com.github.bcfurtado.copaserver.beans.Jogo;
import com.github.bcfurtado.copaserver.beans.Post;
import com.github.bcfurtado.copaserver.beans.Time;
import com.github.bcfurtado.copaserver.beans.Usuario;
import com.github.bcfurtado.copaserver.util.PreparaSessao;

public class ControladorBadges {

	private Session session;
	Jogo jogo = new Jogo();
	List<Post> postsJogo = new ArrayList<Post>();
	List<Usuario> usuarios = new ArrayList<Usuario>();
	List<Long> listaDeIds = new ArrayList<Long>();
	Time vencedor = new Time();
	Time derrotado = new Time();

	public ControladorBadges(Jogo jogo) {
		this.jogo = jogo;
		postsJogo = new ControladorPosts().pegarTodosOsPostsDoJogo(jogo);
		vencedor = jogo.getVencedor();
		if(vencedor.equals(jogo.getTime_a())){
			derrotado = jogo.getTime_b();
		}
		else{
			derrotado = jogo.getTime_a();
		}
	}

	public void obterUsuariosOnlineNoJogo(){
		for (Post p : postsJogo){
			Usuario u = p.getUsuario();
			for(Long id : listaDeIds){
				if ((u.getId()!=id)){
					listaDeIds.add(u.getId());
					usuarios.add(u);
				}
			}
		}
	}

	public void distribuirBadges() {
		for (Usuario u : usuarios) {
			distribuirBadgesResultado(u);
		}
	}

	public void distribuirBadgesResultado(Usuario u) {
		Badge badge = new Badge();
		badge.setTipo("Resultado");
		badge.setData(jogo.getHorario());
		if(u.getTime().equals(vencedor)){
			if(u.getVitoriasConsecutivas()==5){
				badge.setDescricao("Pé Ultra Quente!");
			}
			else if(u.getVitoriasConsecutivas()==3){
				badge.setDescricao("Pé Quente!");
			}
			u.adicionarBadge(badge);
			badge.setDescricao("Eu vi a vitória do " + vencedor.getNome() + " contra " + derrotado.getNome());
			u.adicionarBadge(badge);
		}
		else{
			if(u.getDerrotasConsecutivas()==5){
				badge.setDescricao("Pé Gelado!");
			}
			else if(u.getDerrotasConsecutivas()==3){
				badge.setDescricao("Pé Frio!");
			}
			u.adicionarBadge(badge);
			badge.setDescricao("Eu vi a derrota do " + derrotado.getNome() + " contra " + vencedor.getNome());
			u.adicionarBadge(badge);
		}

		
		session = PreparaSessao.pegarSessao();
		session.beginTransaction();
		session.update(u);
		session.getTransaction().commit();
		session.close();
	}
	
	public void distribuirBadgeGols(Usuario u) {
		Badge badge = new Badge();
		badge.setTipo("Gol");
		badge.setData(jogo.getHorario());

		for(String m : jogo.getGoleadores_time_a()){
			badge.setDescricao("Eu vi o gol do " + m + " no jogo " + vencedor.getNome() + " contra " + derrotado.getNome());
			u.adicionarBadge(badge);
		}
		
		for(String m : jogo.getGoleadores_time_b()){
			badge.setDescricao("Eu vi o gol do " + m + " no jogo " + vencedor.getNome() + " contra " + derrotado.getNome());
			u.adicionarBadge(badge);
		}
		
		session = PreparaSessao.pegarSessao();
		session.beginTransaction();
		session.update(u);
		session.getTransaction().commit();
		session.close();
	}

}
