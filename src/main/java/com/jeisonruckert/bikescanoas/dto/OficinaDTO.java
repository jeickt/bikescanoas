package com.jeisonruckert.bikescanoas.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.jeisonruckert.bikescanoas.domain.Oficina;

public class OficinaDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=5, max=80, message="Entre 5 e 120 caracteres")
	private String nome;
	
	public OficinaDTO() {
	}
	
	public OficinaDTO(Oficina obj) {
		id = obj.getId();
		nome = obj.getNome();
	}

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
