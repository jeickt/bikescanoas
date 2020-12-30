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
import com.jeisonruckert.bikescanoas.domain.Compra;
import com.jeisonruckert.bikescanoas.domain.Terminal;
import com.jeisonruckert.bikescanoas.domain.Usuario;
import com.jeisonruckert.bikescanoas.dto.CompraDTO;
import com.jeisonruckert.bikescanoas.dto.CompraNewDTO;
import com.jeisonruckert.bikescanoas.repositories.CategoriaRepository;
import com.jeisonruckert.bikescanoas.repositories.CompraRepository;
import com.jeisonruckert.bikescanoas.repositories.TerminalRepository;
import com.jeisonruckert.bikescanoas.repositories.UsuarioRepository;
import com.jeisonruckert.bikescanoas.services.exceptions.DataIntegrityException;
import com.jeisonruckert.bikescanoas.services.exceptions.ObjectNotFoundException;

@Service
public class CompraService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private CompraRepository repo;
	
	@Autowired
	private TerminalRepository terminalRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	public Compra find(Integer id) {
		Optional<Compra> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Compra não encontrada! Id: " + id + ", Tipo: " + Compra.class.getName()));
	}
	
	public List<Compra> findAll() {
		return repo.findAll();
	}
	
	public Compra insert(Compra obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Compra update(Compra obj) {
		Compra objBD = find(obj.getId());
		updateData(objBD, obj);
		return repo.save(objBD);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui bicicletas");
		}
	}
	
	public Page<Compra> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Compra fromDTO(CompraDTO objDto) {
		return new Compra(objDto.getId(), objDto.getData(), objDto.getPreco(), objDto.getLoja());
	}
	
	public Compra fromDTO(CompraNewDTO objDto) {
		Categoria cat = categoriaRepository.findById(objDto.getCategoriaId()).get();
		Terminal ter = terminalRepository.findById(objDto.getTerminalId()).get();
		Bicicleta bic = new Bicicleta(null, objDto.getFabricante(), objDto.getModelo(), 
				objDto.getTamQuadro(), objDto.getTamAro(), cat, ter);
		Usuario usu = usuarioRepository.findById(objDto.getUsuarioId()).get();
		Compra com = new Compra(null, objDto.getData(), objDto.getPreco(), objDto.getLoja());
		com.setBicicleta(bic);
		com.setUsuario(usu);
		return com;
	}
	
	private void updateData(Compra objBD, Compra obj) {
		objBD.setData(obj.getData());
		objBD.setPreco(obj.getPreco());
		objBD.setLoja(obj.getLoja());
	}
	
 }
