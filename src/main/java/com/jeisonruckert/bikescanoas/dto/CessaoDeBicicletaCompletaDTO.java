package com.jeisonruckert.bikescanoas.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

public class CessaoDeBicicletaCompletaDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Double preco;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=5, max=80, message="Entre 5 e 80 caracteres")
	private String loja;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String fabricante;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String modelo;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String tamQuadro;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String tamAro;
	
	private Integer categoriaId;
	
	private Integer terminalId;
	
	private Integer usuarioId;
	
	public CessaoDeBicicletaCompletaDTO() {
	}
	

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public String getLoja() {
		return loja;
	}

	public void setLoja(String loja) {
		this.loja = loja;
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

	public Integer getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(Integer categoriaId) {
		this.categoriaId = categoriaId;
	}

	public Integer getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(Integer terminalId) {
		this.terminalId = terminalId;
	}

	public Integer getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
	}
	
}
