package com.github.bcfurtado.copaserver.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity

public class Time {
	@Id
	@GeneratedValue
	private Long id;
	private String nome;
	private boolean ativo;
	
	
	public Long getId(){
		return id;
	}
	
	public void setNome(String nome){
		this.nome = nome;
	}
	
	public String getNome(){
		return nome;
	}
	
	public boolean isAtivo(){
		return ativo;
	}
	
	public void ativar(){
		this.ativo = true;
	}

	public void desativar(){
		this.ativo = false;
	}
	
}
