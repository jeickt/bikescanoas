package com.jeisonruckert.bikescanoas.dto;

import com.jeisonruckert.bikescanoas.domain.Categoria;

public class CategoriaDTO {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String tipo;
	
	public CategoriaDTO() {
	}
	
	public CategoriaDTO(Categoria obj) {
		id = obj.getId();
		tipo = obj.getTipo();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
}
