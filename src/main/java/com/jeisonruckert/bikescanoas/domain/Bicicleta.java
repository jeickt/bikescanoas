package com.jeisonruckert.bikescanoas.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Bicicleta implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String fabricante;
	private String modelo;
	private String tamQuadro;
	private String tamAro;
	private Double KmManutencao = 0.0;
	private Double KmTerminal = 0.0;
	private Boolean manutencao = false;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="categoria_id")
	private Categoria categoria;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="terminal_id")
	private Terminal terminal;
	
	public Bicicleta() {
	}

	public Bicicleta(Integer id, String fabricante, String modelo, String tamQuadro, String tamAro, Categoria categoria, Terminal terminal) {
		super();
		this.id = id;
		this.fabricante = fabricante;
		this.modelo = modelo;
		this.tamQuadro = tamQuadro;
		this.tamAro = tamAro;
		this.categoria = categoria;
		this.terminal = terminal;
	}
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getTamQuadro() {
		return tamQuadro;
	}

	public void setTamQuadro(String tamQuadro) {
		this.tamQuadro = tamQuadro;
	}

	public String getTamAro() {
		return tamAro;
	}

	public void setTamAro(String tamAro) {
		this.tamAro = tamAro;
	}

	public Double getKmManutencao() {
		return KmManutencao;
	}

	public void setKmManutencao(Double kmManutencao) {
		KmManutencao = kmManutencao;
	}

	public Double getKmTerminal() {
		return KmTerminal;
	}

	public void setKmTerminal(Double kmTerminal) {
		KmTerminal = kmTerminal;
	}

	public Boolean getManutencao() {
		return manutencao;
	}

	public void setManutencao(Boolean manutencao) {
		this.manutencao = manutencao;
	}
	
	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	public Terminal getTerminal() {
		return terminal;
	}

	public void setTerminal(Terminal terminal) {
		this.terminal = terminal;
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
		Bicicleta other = (Bicicleta) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
