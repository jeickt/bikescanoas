package com.jeisonruckert.bikescanoas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.jeisonruckert.bikescanoas.domain.Usuario;
import com.jeisonruckert.bikescanoas.dto.UsuarioDTO;
import com.jeisonruckert.bikescanoas.repositories.UsuarioRepository;
import com.jeisonruckert.bikescanoas.services.exceptions.DataIntegrityException;
import com.jeisonruckert.bikescanoas.services.exceptions.ObjectNotFoundException;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repo;

	public Usuario find(Integer id) {
		Optional<Usuario> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Objeto não encontrado! Id: " + id + ", Tipo: " + Usuario.class.getName()));
		}
	
	public List<Usuario> findAll() {
		return repo.findAll();
	}
	
	public Usuario insert(Usuario obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Usuario update(Usuario obj) {
		Usuario objBD = find(obj.getId());
		updateData(objBD, obj);
		return repo.save(objBD);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir porque há entidades relacionadas");
		}
	}
	
	public Page<Usuario> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Usuario fromDTO(UsuarioDTO objDto) {
		return new Usuario(objDto.getId(), null, objDto.getNome(), objDto.getEmail());
	}
	
	private void updateData(Usuario objBD, Usuario obj) {
		objBD.setNome(obj.getNome());
		objBD.setEmail(obj.getEmail());
	}
	
 }
