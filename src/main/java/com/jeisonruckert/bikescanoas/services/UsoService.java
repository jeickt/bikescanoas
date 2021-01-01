package com.jeisonruckert.bikescanoas.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.jeisonruckert.bikescanoas.domain.Bicicleta;
import com.jeisonruckert.bikescanoas.domain.Uso;
import com.jeisonruckert.bikescanoas.domain.Usuario;
import com.jeisonruckert.bikescanoas.dto.UsoDTO;
import com.jeisonruckert.bikescanoas.repositories.BicicletaRepository;
import com.jeisonruckert.bikescanoas.repositories.UsoRepository;
import com.jeisonruckert.bikescanoas.repositories.UsuarioRepository;
import com.jeisonruckert.bikescanoas.services.exceptions.ObjectNotFoundException;

@Service
public class UsoService {
	
	@Autowired
	private BicicletaRepository bicicletaRepository;
	
	@Autowired
	private UsoRepository repo;

	@Autowired
	private UsuarioRepository usuarioRepository;

	public Uso find(Integer id) {
		Optional<Uso> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Uso não encontrado! Id: " + id + ", Tipo: " + Uso.class.getName()));
	}
	
	public List<Uso> findAll() {
		return repo.findAll();
	}
	
	@Transactional
	public Uso insert(Uso obj) {
		obj.setId(null);
		obj.setPartida(new Date());
		obj = repo.save(obj);
		return obj;
	}
	
	public Uso update(Uso obj) {
		Uso objBD = find(obj.getId());
		updateData(objBD, obj);
		objBD.setDistancia(objBD.getBicicleta().getKmTerminal());
		
		objBD.getUsuario().setKmTotal(objBD.getUsuario().getKmTotal() + objBD.getBicicleta().getKmTerminal());
		objBD.getUsuario().getUsos().add(objBD);
		
		objBD.getBicicleta().setKmManutencao(objBD.getBicicleta().getKmManutencao() + objBD.getBicicleta().getKmTerminal());
		objBD.getBicicleta().setKmTerminal(0.0);
		return repo.save(objBD);
	}
	
	public void delete(Integer id) {
		find(id);
		repo.deleteById(id);
	}
	
	public Page<Uso> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Uso fromDTOUp(UsoDTO objDto) {
		Uso obj = new Uso(objDto.getId(), objDto.getPartida(), objDto.getChegada());
		obj.setChegada(new Date());
		obj.setPrecisaConserto(objDto.getPrecisaConserto());
		return obj;
	}
	
	public Uso fromDTO(UsoDTO objDto) {
		Bicicleta bic = bicicletaRepository.findById(objDto.getBicicletaId()).get();
		Usuario usu = usuarioRepository.findById(objDto.getUsuarioId()).get();
		Uso uso = new Uso(null, null, null);
		uso.setBicicleta(bic);
		uso.setUsuario(usu);
		return uso;
	}
	
	private void updateData(Uso objBD, Uso obj) {
		objBD.setChegada(obj.getChegada());
		objBD.setPrecisaConserto(obj.getPrecisaConserto());
	}
	
 }
