package com.github.bcfurtado.copaserver.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="noticia")
public class Noticia {

	@Id
	@GeneratedValue
	private Long id;
	private String titulo;
	private String corpo;

	private boolean ativo;

	public Long getId(){
		return id;
	}

	public void setTitulo(String titulo){
		this.titulo = titulo;
	}

	public String getTitulo(){
		return titulo;
	}

	public void setCorpo(String corpo){
		this.corpo = corpo;
	}

	public String getCorpo(){
		return corpo;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public void ativar(){
		this.setAtivo(true);
	}

	public void desativar(){
		this.setAtivo(false);
	}
}