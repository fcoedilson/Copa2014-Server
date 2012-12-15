package com.github.bcfurtado.copaserver.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity

public class Estadio {
	@Id
	@GeneratedValue
	private Long id;
	private String nome;
	private float latitude;
	private float longitude;
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

	public void setLatitude(float latitude){
		this.latitude = latitude;
	}

	public void setLongitude(float longitude){
		this.longitude = longitude;
	}

	public float getLatitude(){
		return latitude;
	}

	public float getLongitude(){
		return longitude;
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