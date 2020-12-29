package com.jeisonruckert.bikescanoas.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Uso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	private Date partida;
	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	private Date chegada;
	private Boolean precisaConserto = false;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="usuario_id")
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name="bicicleta_id")
	private Bicicleta bicicleta;
	
	public Uso() {
	}

	public Uso(Integer id, Date partida, Date chegada, Usuario usuario, Bicicleta bicicleta) {
		super();
		this.id = id;
		this.partida = partida;
		this.chegada = chegada;
		this.usuario = usuario;
		this.bicicleta = bicicleta;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getPartida() {
		return partida;
	}

	public void setPartida(Date partida) {
		this.partida = partida;
	}

	public Date getChegada() {
		return chegada;
	}

	public void setChegada(Date chegada) {
		this.chegada = chegada;
	}

	public Boolean getPrecisaConserto() {
		return precisaConserto;
	}

	public void setPrecisaConserto(Boolean precisaConserto) {
		this.precisaConserto = precisaConserto;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Bicicleta getBicicleta() {
		return bicicleta;
	}

	public void setBicicleta(Bicicleta bicicleta) {
		this.bicicleta = bicicleta;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Uso other = (Uso) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
