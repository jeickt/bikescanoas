package com.jeisonruckert.bikescanoas.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@CPF
	private String cpf;
	private String nome;
	
	@JsonIgnore
	private String senha;
	
	@Column(unique=true)
	private String email;
	private Double saldo = 0.0;
	private Double kmTotal = 0.0;
	
	@ElementCollection
	@CollectionTable(name="TELEFONE")
	private Set<String> telefones = new HashSet<>();
	
	@OneToOne(mappedBy="usuario", cascade=CascadeType.ALL)
	private Endereco endereco = null;
	
	@OneToMany(mappedBy="usuario")
	private List<Pagamento> pagamentos = new ArrayList<>();
	
	@OneToMany(mappedBy="usuario")
	private List<Uso> usos = new ArrayList<>();
	
	public Usuario() {
	}

	public Usuario(Integer id, String cpf, String nome, String senha, String email) {
		super();
		this.id = id;
		this.cpf = cpf;
		this.nome = nome;
		this.senha = senha;
		this.email = email;
	}

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
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

	public Set<String> getTelefones() {
		return telefones;
	}

	public void setTelefones(Set<String> telefones) {
		this.telefones = telefones;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	public List<Pagamento> getPagamentos() {
		return pagamentos;
	}

	public void setPagamentos(List<Pagamento> pagamentos) {
		this.pagamentos = pagamentos;
	}

	public List<Uso> getUsos() {
		return usos;
	}

	public void setUsos(List<Uso> usos) {
		this.usos = usos;
	}
	
	public double getDistanciaTotal() {
		double soma = 0.0;
		for (Uso u : usos) {
			soma += u.getDistancia();
		}
		return soma;
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
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
