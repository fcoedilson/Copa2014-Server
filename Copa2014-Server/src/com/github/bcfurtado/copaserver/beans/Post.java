package com.github.bcfurtado.copaserver.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="post")
public class Post {

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable=false)
	private String idPostFacebook;

	@ManyToOne
	private Jogo jogo;

	@ManyToOne
	private Usuario usuario;

	private boolean ativar;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIdPostFacebook() {
		return idPostFacebook;
	}

	public void setIdPostFacebook(String idPostFacebook) {
		this.idPostFacebook = idPostFacebook;
	}

	public Jogo getJogo() {
		return jogo;
	}

	public void setJogo(Jogo jogo) {
		this.jogo = jogo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public boolean isAtivar() {
		return ativar;
	}

	public void setAtivar(boolean ativar) {
		this.ativar = ativar;
	}

}
