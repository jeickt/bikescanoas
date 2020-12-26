package com.jeisonruckert.bikescanoas.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Bicicleta implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String fabricante;
	private String modelo;
	private String tamQuadro;
	private String tamAro;
	private Double KmManutencao;
	private Double KmTerminal;
	private Boolean manutencao;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="categoria_id")
	private Categoria categoria;
	
	public Bicicleta() {
	}

	public Bicicleta(Integer id, String fabricante, String modelo, String tamQuadro, String tamAro, Double kmManutencao,
			Double kmTerminal, Boolean manutencao, Categoria categoria) {
		super();
		this.id = id;
		this.fabricante = fabricante;
		this.modelo = modelo;
		this.tamQuadro = tamQuadro;
		this.tamAro = tamAro;
		KmManutencao = kmManutencao;
		KmTerminal = kmTerminal;
		this.manutencao = manutencao;
		this.categoria = categoria;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
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
