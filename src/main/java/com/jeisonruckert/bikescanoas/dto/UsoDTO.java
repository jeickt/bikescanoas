package com.jeisonruckert.bikescanoas.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jeisonruckert.bikescanoas.domain.Uso;

public class UsoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;

	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	private Date partida;
	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	private Date chegada;
	private Double distancia;
	private Boolean precisaConserto = false;

	private Integer bicicletaId;
	
	private Integer usuarioId;
	
	public UsoDTO() {
	}
	
	public UsoDTO(Uso obj) {
		id = obj.getId();
		partida = obj.getPartida();
		chegada = obj.getChegada();
		distancia = obj.getDistancia();
		precisaConserto = obj.getPrecisaConserto();
		bicicletaId = obj.getBicicleta().getId();
		usuarioId = obj.getUsuario().getId();
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

	public Double getDistancia() {
		return distancia;
	}

	public void setDistancia(Double distancia) {
		this.distancia = distancia;
	}

	public Boolean getPrecisaConserto() {
		return precisaConserto;
	}

	public void setPrecisaConserto(Boolean precisaConserto) {
		this.precisaConserto = precisaConserto;
	}

	public Integer getBicicletaId() {
		return bicicletaId;
	}

	public void setBicicletaId(Integer bicicletaId) {
		this.bicicletaId = bicicletaId;
	}

	public Integer getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
	}
	
}
