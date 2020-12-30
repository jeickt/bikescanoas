package com.jeisonruckert.bikescanoas.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import com.jeisonruckert.bikescanoas.domain.Bicicleta;

public class BicicletaDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String fabricante;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String modelo;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String tamQuadro;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String tamAro;
	private Double KmManutencao;
	private Double KmTerminal;
	private Boolean manutencao;
	
	private Integer categoriaId;
	
	private Integer terminalId;
	
	public BicicletaDTO() {
	}
	
	public BicicletaDTO(Bicicleta obj) {
		id = obj.getId();
		fabricante = obj.getFabricante();
		modelo = obj.getModelo();
		tamQuadro = obj.getTamQuadro();
		tamAro = obj.getTamAro();
		KmManutencao = obj.getKmManutencao();
		KmTerminal = obj.getKmTerminal();
		manutencao = obj.getManutencao();
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
	
}
