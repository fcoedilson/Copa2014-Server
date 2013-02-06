package com.github.bcfurtado.copaserver.beans;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name = "badge")
public class Badge {

	@Id
	@GeneratedValue
	private Long id;
	private String tipo;
	private GregorianCalendar data;
	private String descricao;
	
	@ManyToMany (cascade={CascadeType.MERGE}, fetch = FetchType.LAZY)
	@JoinTable(name="badge_usuario",
	joinColumns = @JoinColumn(name="badge_id"),
	inverseJoinColumns = @JoinColumn(name="usuario_id_facebook"))
	private List<Usuario> usuarios = new ArrayList<Usuario>(0);

	
	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public GregorianCalendar getData() {
		return data;
	}

	public void setData(GregorianCalendar gregorianCalendar) {
		this.data = gregorianCalendar;
	}

}
