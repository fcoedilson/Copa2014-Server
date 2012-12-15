package com.github.bcfurtado.copaserver.beans;

import javax.persistence.*;

@Entity

public class Usuario {
	
	@Id
	private Long id_facebook;
	@OneToMany
	private Time time;
	private String email;
	private boolean ativo;
	
	public Long getId(){
		return id_facebook;
	}
	
	public void setId(Long id_facebook){
		this.id_facebook = id_facebook;
	}
	
	public Time getTime(){
		return time;
	}
	
	public String getEmail(){
		return email;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
	
	public void setTime(Time time){
		this.time = time;
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
