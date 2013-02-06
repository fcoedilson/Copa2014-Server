package com.github.bcfurtado.copaserver.beans;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "jogo")
public class Jogo {

	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	private Time time_a;
	
	@ManyToOne
	private Time time_b;
	
	@ManyToOne
	private Estadio local;
	
	private GregorianCalendar horario;

	@ManyToOne
	private Time vencedor;
	
	private ArrayList<String> goleadores_time_a = new ArrayList<String>();
	
	private ArrayList<String> goleadores_time_b = new ArrayList<String>();
	
	private boolean ativo;

	public void setId(Long id) {
		this.id = id;
	}

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

	public void setHorario(GregorianCalendar horario){
		this.horario = horario;
	}

	public GregorianCalendar getHorario(){
		return horario;
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

	public Time getVencedor() {
		return vencedor;
	}

	public void setVencedor(Time vencedor) {
		this.vencedor = vencedor;
	}

	public List<String> getGoleadores_time_b() {
		return goleadores_time_b;
	}

	public void setGoleadores_time_b(ArrayList<String> goleadores_time_b) {
		this.goleadores_time_b = goleadores_time_b;
	}

	public List<String> getGoleadores_time_a() {
		return goleadores_time_a;
	}

	public void setGoleadores_time_a(ArrayList<String> goleadores_time_a) {
		this.goleadores_time_a = goleadores_time_a;
	}
	
}