package com.github.bcfurtado.copaserver.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="time")
public class Time {
	@Id
	@GeneratedValue
	private Long id;
	private String nome;
	private boolean ativo;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}
	

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	public boolean isAtivo() {
		return ativo;
	}

	public void ativar() {
		this.ativo = true;
	}

	public void desativar() {
		this.ativo = false;
	}

}
