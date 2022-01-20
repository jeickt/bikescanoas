package com.jeisonruckert.bikescanoas.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.jeisonruckert.bikescanoas.domain.Oficina;
import com.jeisonruckert.bikescanoas.dto.OficinaDTO;
import com.jeisonruckert.bikescanoas.repositories.OficinaRepository;
import com.jeisonruckert.bikescanoas.services.exceptions.DataIntegrityException;
import com.jeisonruckert.bikescanoas.services.exceptions.ObjectNotFoundException;

@Service
public class OficinaService {
	
	private OficinaRepository repo;
	
	public OficinaService(OficinaRepository repository) {
		this.repo = repository;
	}

	public Oficina find(Integer id) {
		Optional<Oficina> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Oficina não encontrado! Id: " + id + ", Tipo: " + Oficina.class.getName()));
		}
	
	public List<Oficina> findAll() {
		return repo.findAll();
	}
	
	@Transactional
	public Oficina insert(Oficina obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Oficina update(Oficina obj) {
		Oficina objBD = find(obj.getId());
		updateData(objBD, obj);
		return repo.save(objBD);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma oficina que possui manutenções associadas");
		}
	}
	
	public Page<Oficina> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Oficina fromDTO(OficinaDTO objDto) {
		return new Oficina (objDto.getId(), null, objDto.getNome());
	}
	
	private void updateData(Oficina objBD, Oficina obj) {
		objBD.setNome(obj.getNome());
	}
	
 }
