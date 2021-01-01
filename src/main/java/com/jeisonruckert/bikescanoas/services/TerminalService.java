package com.jeisonruckert.bikescanoas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.jeisonruckert.bikescanoas.domain.Terminal;
import com.jeisonruckert.bikescanoas.dto.TerminalDTO;
import com.jeisonruckert.bikescanoas.repositories.TerminalRepository;
import com.jeisonruckert.bikescanoas.services.exceptions.DataIntegrityException;
import com.jeisonruckert.bikescanoas.services.exceptions.ObjectNotFoundException;

@Service
public class TerminalService {
	
	@Autowired
	private TerminalRepository repo;

	public Terminal find(Integer id) {
		Optional<Terminal> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Terminal não encontrado! Id: " + id + ", Tipo: " + Terminal.class.getName()));
		}
	
	public List<Terminal> findAll() {
		return repo.findAll();
	}
	
	public Terminal insert(Terminal obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Terminal update(Terminal obj) {
		Terminal objBD = find(obj.getId());
		updateData(objBD, obj);
		return repo.save(objBD);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir um terminal que possui bicicletas associadas");
		}
	}
	
	public Page<Terminal> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Terminal fromDTO(TerminalDTO objDto) {
		return new Terminal (objDto.getId(), objDto.getNome(), objDto.getVagas());
	}
	
	private void updateData(Terminal objBD, Terminal obj) {
		objBD.setNome(obj.getNome());
		objBD.setVagas(obj.getVagas());
	}
	
 }
