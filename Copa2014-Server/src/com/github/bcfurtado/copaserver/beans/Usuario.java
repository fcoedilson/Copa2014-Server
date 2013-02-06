package com.github.bcfurtado.copaserver.beans;

import java.util.ArrayList;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="usuario")

public class Usuario {
	
	@Id
	private Long id_facebook;
	@ManyToOne
	@JoinColumn(name="time_id")
	private Time time;
	@Column(unique=true)
	private String email;
	private boolean ativo;
	private Integer vitoriasConsecutivas;
	private Integer derrotasConsecutivas;
	
	@ManyToMany (fetch = FetchType.LAZY)
	@JoinTable(name="badge_usuario", 
	joinColumns = @JoinColumn(name="usuario_id_facebook"),
	inverseJoinColumns = @JoinColumn(name="badge_id"))
	private List<Badge> badges = new ArrayList<Badge>();
		
	public List<Badge> getBadges() {
		return badges;
	}

	public void setBadges(List<Badge> badges) {
		this.badges = badges;
	}

	
	
	public Integer getVitoriasConsecutivas() {
		return vitoriasConsecutivas;
	}

	public void setVitoriasConsecutivas(Integer vitoriasConsecutivas) {
		this.vitoriasConsecutivas = vitoriasConsecutivas;
	}

	public Integer getDerrotasConsecutivas() {
		return derrotasConsecutivas;
	}

	public void setDerrotasConsecutivas(Integer derrotasConsecutivas) {
		this.derrotasConsecutivas = derrotasConsecutivas;
	}

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
	
	public void adicionarBadge(Badge badge){
		badges.add(badge);
	}
	
}
