package com.jeisonruckert.bikescanoas.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Manutencao implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	private Date instante;
	private Double km;
	private Double custo;
	private String mecanico;
	
	@ElementCollection
	@CollectionTable(name="PECA")
	private Set<String> pecas = new HashSet<>();
	
	@ElementCollection
	@CollectionTable(name="SERVICO")
	private Set<String> servicos = new HashSet<>();
	
	@ManyToOne
	@JoinColumn(name="bicicleta_id")
	private Bicicleta bicicleta;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="oficina_id")
	private Oficina oficina;
	
	public Manutencao() {
	}

	public Manutencao(Integer id, Date instante, Double km, Double custo, String mecanico, Bicicleta bicicleta,
			Oficina oficina) {
		super();
		this.id = id;
		this.instante = instante;
		this.km = km;
		this.custo = custo;
		this.mecanico = mecanico;
		this.bicicleta = bicicleta;
		this.oficina = oficina;
	}

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getInstante() {
		return instante;
	}

	public void setInstante(Date instante) {
		this.instante = instante;
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

	public Bicicleta getBicicleta() {
		return bicicleta;
	}

	public void setBicicleta(Bicicleta bicicleta) {
		this.bicicleta = bicicleta;
	}

	public Oficina getOficina() {
		return oficina;
	}

	public void setOficina(Oficina oficina) {
		this.oficina = oficina;
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
		Manutencao other = (Manutencao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
