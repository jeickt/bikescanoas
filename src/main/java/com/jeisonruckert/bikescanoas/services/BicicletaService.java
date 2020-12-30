package com.jeisonruckert.bikescanoas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.jeisonruckert.bikescanoas.domain.Bicicleta;
import com.jeisonruckert.bikescanoas.domain.Categoria;
import com.jeisonruckert.bikescanoas.domain.Terminal;
import com.jeisonruckert.bikescanoas.dto.BicicletaDTO;
import com.jeisonruckert.bikescanoas.repositories.BicicletaRepository;
import com.jeisonruckert.bikescanoas.repositories.CategoriaRepository;
import com.jeisonruckert.bikescanoas.repositories.TerminalRepository;
import com.jeisonruckert.bikescanoas.services.exceptions.DataIntegrityException;
import com.jeisonruckert.bikescanoas.services.exceptions.ObjectNotFoundException;

@Service
public class BicicletaService {
	
	@Autowired
	private BicicletaRepository repo;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private TerminalRepository terminalRepository;

	public Bicicleta find(Integer id) {
		Optional<Bicicleta> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Bicicleta não encontrada! Id: " + id + ", Tipo: " + Bicicleta.class.getName()));
	}
	
	public List<Bicicleta> findAll() {
		return repo.findAll();
	}
	
	public Bicicleta insert(Bicicleta obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Bicicleta update(Bicicleta obj) {
		Bicicleta objBD = find(obj.getId());
		updateData(objBD, obj);
		return repo.save(objBD);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma bicicleta que possui compra associada");
		}
	}
	
	public Page<Bicicleta> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Bicicleta fromDTO(BicicletaDTO objDto) {
		Categoria cat = categoriaRepository.findById(objDto.getCategoriaId()).get();
		Terminal ter = terminalRepository.findById(objDto.getTerminalId()).get();
		return new Bicicleta(objDto.getId(), objDto.getFabricante(), objDto.getModelo(), 
				objDto.getTamQuadro(), objDto.getTamAro(), cat, ter);
	}
	
	private void updateData(Bicicleta objBD, Bicicleta obj) {
		objBD.setFabricante(obj.getFabricante());
		objBD.setModelo(obj.getModelo());
		objBD.setTamQuadro(obj.getTamQuadro());
		objBD.setTamAro(obj.getTamAro());
		objBD.setCategoria(obj.getCategoria());
		objBD.setTerminal(obj.getTerminal());
	}
	
 }
