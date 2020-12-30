package com.jeisonruckert.bikescanoas.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.jeisonruckert.bikescanoas.domain.Compra;

public class CompraDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;

	private Date data;
	private Double preco;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=5, max=80, message="Entre 5 e 80 caracteres")
	private String loja;
	
	public CompraDTO() {
	}
	
	public CompraDTO(Compra obj) {
		id = obj.getId();
		data = obj.getData();
		preco = obj.getPreco();
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
	
}
