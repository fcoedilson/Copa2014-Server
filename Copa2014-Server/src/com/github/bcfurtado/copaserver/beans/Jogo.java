package com.github.bcfurtado.copaserver.beans;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity

public class Jogo {
	
	@Id
	@GeneratedValue
	private Long id;
	private Time time_a;
	private Time time_b;
	private Estadio local;
	private Date horario;
	
	public Long getId(){
		return id;
	}
	
	public void setTime_a(Time time_a){
		this.time_a = time_a;
	}
	
	public void setTime_b(Time time_b){
		this.time_b = time_b;
	}
	
	public Time getTime_a(){
		return time_a;
	}
	
	public Time getTime_b(){
		return time_b;
	}
	
	public void setLocal(Estadio local){
		this.local = local;
	}
	
	public Estadio getLocal(){
		return local;
	}
	
	public void setHorario(Date horario){
		this.horario = horario;
	}
	
	public Date getHorario(){
		return horario;
	}
	
}
