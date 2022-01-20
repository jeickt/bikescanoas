package com.jeisonruckert.bikescanoas.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.jeisonruckert.bikescanoas.domain.Pagamento;
import com.jeisonruckert.bikescanoas.domain.PagamentoComBoleto;
import com.jeisonruckert.bikescanoas.domain.enums.EstadoPagamento;
import com.jeisonruckert.bikescanoas.repositories.PagamentoRepository;
import com.jeisonruckert.bikescanoas.services.exceptions.ObjectNotFoundException;

@Service
public class PagamentoService {
	
	private PagamentoRepository repo;
	
	private BoletoService boletoService;
	
	public PagamentoService(PagamentoRepository repository, BoletoService boletoService) {
		this.repo = repository;
		this.boletoService = boletoService;
	}

	public Pagamento find(Integer id) {
		Optional<Pagamento> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Pagamento não encontrado! Id: " + id + ", Tipo: " + Pagamento.class.getName()));
	}
	
	public List<Pagamento> findAll() {
		return repo.findAll();
	}
	
	@Transactional
	public Pagamento insert(Pagamento obj) {
		obj.setId(null);
		obj.setEstado(EstadoPagamento.PENDENTE);
		if (obj instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj;
			boletoService.preencherPagamentoComBoleto(pagto, new Date());
		}
		obj = repo.save(obj);
		obj.getUsuario().getPagamentos().add(obj);
		return obj;
	}
	
	
	
	
	public void delete(Integer id) {
		find(id);
		repo.deleteById(id);
	}
	
 }
