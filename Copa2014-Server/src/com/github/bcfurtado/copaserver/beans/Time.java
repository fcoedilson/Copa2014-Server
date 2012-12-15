package com.github.bcfurtado.copaserver.beans;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="time")
public class Time {
	@Id
	@GeneratedValue
	private Long id;
	private String nome;
	private boolean ativo;
	@OneToMany(mappedBy="time")
	private Set<Usuario> usuarios = new HashSet<Usuario>();
	
	
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
