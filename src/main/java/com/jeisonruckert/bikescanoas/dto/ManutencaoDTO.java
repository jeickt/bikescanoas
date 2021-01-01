package com.jeisonruckert.bikescanoas.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jeisonruckert.bikescanoas.domain.Manutencao;

public class ManutencaoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;

	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	private Date data;
	private Double km;
	private Double custo;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=5, max=120, message="Entre 5 e 120 caracteres")
	private String mecanico;
	
	private Set<String> pecas = new HashSet<>();
	private Set<String> servicos = new HashSet<>();

	private Integer bicicletaId;
	
	private Integer oficinaId;
	
	public ManutencaoDTO() {
	}
	
	public ManutencaoDTO(Manutencao obj) {
		id = obj.getId();
		data = obj.getData();
		km = obj.getKm();
		custo = obj.getCusto();
		mecanico = obj.getMecanico();
		pecas = obj.getPecas();
		servicos = obj.getServicos();
		bicicletaId = obj.getBicicleta().getId();
		oficinaId = obj.getOficina().getId();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Double getKm() {
		return km;
	}

	public void setKm(Double km) {
		this.km = km;
	}

	public Double getCusto() {
		return custo;
	}

	public void setCusto(Double custo) {
		this.custo = custo;
	}

	public String getMecanico() {
		return mecanico;
	}

	public void setMecanico(String mecanico) {
		this.mecanico = mecanico;
	}

	public Set<String> getPecas() {
		return pecas;
	}

	public void setPecas(Set<String> pecas) {
		this.pecas = pecas;
	}

	public Set<String> getServicos() {
		return servicos;
	}

	public void setServicos(Set<String> servicos) {
		this.servicos = servicos;
	}

	public Integer getBicicletaId() {
		return bicicletaId;
	}

	public void setBicicletaId(Integer bicicletaId) {
		this.bicicletaId = bicicletaId;
	}

	public Integer getOficinaId() {
		return oficinaId;
	}

	public void setOficinaId(Integer oficinaId) {
		this.oficinaId = oficinaId;
	}
	
}
