package com.jeisonruckert.bikescanoas.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.jeisonruckert.bikescanoas.domain.Usuario;

public class UsuarioDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=5, max=120, message="Entre 5 e 120 caracteres")
	private String nome;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Email(message="Email inválido")
	private String email;
	private Double saldo;
	private Double kmTotal;
	
	public UsuarioDTO() {
	}
	
	public UsuarioDTO(Usuario obj) {
		id = obj.getId();
		nome = obj.getNome();
		email = obj.getEmail();
		saldo = obj.getSaldo();
		kmTotal = obj.getKmTotal();
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public Double getKmTotal() {
		return kmTotal;
	}

	public void setKmTotal(Double kmTotal) {
		this.kmTotal = kmTotal;
	}

	
	
}
