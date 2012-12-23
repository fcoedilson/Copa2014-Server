package com.github.bcfurtado.copaserver.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="estadio")
public class Estadio {
	@Id
	@GeneratedValue
	private Long id;
	private String nome;
	private Double latitude;
	private Double longitude;
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

	public void setLatitude(Double latitude){
		this.latitude = latitude;
	}

	public void setLongitude(Double longitude){
		this.longitude = longitude;
	}

	public Double getLatitude(){
		return latitude;
	}

	public Double getLongitude(){
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