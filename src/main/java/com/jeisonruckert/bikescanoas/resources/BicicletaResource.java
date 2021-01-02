package com.jeisonruckert.bikescanoas.resources;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jeisonruckert.bikescanoas.domain.Bicicleta;
import com.jeisonruckert.bikescanoas.dto.BicicletaDTO;
import com.jeisonruckert.bikescanoas.resources.utils.URL;
import com.jeisonruckert.bikescanoas.services.BicicletaService;

@RestController
@RequestMapping(value="/bicicletas")
public class BicicletaResource {
	
	@Autowired
	private BicicletaService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Bicicleta> find(@PathVariable Integer id) {
		Bicicleta obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<BicicletaDTO>> findAll() {
		List<Bicicleta> list = service.findAll();
		List<BicicletaDTO> listDTO = list.stream().map(obj -> 
		new BicicletaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<BicicletaDTO>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="tamAro") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<Bicicleta> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<BicicletaDTO> listDTO = list.map(obj -> new BicicletaDTO(obj));
		return ResponseEntity.ok().body(listDTO);
	}
	
	// POST implementado por CessaoDeBicicletaResource
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@PathVariable Integer id, @Valid @RequestBody BicicletaDTO objDto) {
		Bicicleta obj = service.fromDTO(objDto);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/searchQuadro", method=RequestMethod.GET)
	public ResponseEntity<Page<BicicletaDTO>> findPageQuadro(
			@RequestParam(value="tamQuadro", defaultValue="") String tamQuadro,
			@RequestParam(value="categoria", defaultValue="") String categoria,
			@RequestParam(value="terminal", defaultValue="") String terminal,
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="tamAro") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		String consDecoded = URL.decodeParam(tamQuadro);
		Page<Bicicleta> list = service.searchQuadro(consDecoded, Integer.parseInt(categoria), Integer.parseInt(terminal), page, linesPerPage, orderBy, direction);
		Page<BicicletaDTO> listDTO = list.map(obj -> new BicicletaDTO(obj));
		return ResponseEntity.ok().body(listDTO);
	}
	
	@RequestMapping(value="/searchAro", method=RequestMethod.GET)
	public ResponseEntity<Page<BicicletaDTO>> findPageAro(
			@RequestParam(value="tamAro", defaultValue="") String tamAro,
			@RequestParam(value="categoria", defaultValue="") String categoria,
			@RequestParam(value="terminal", defaultValue="") String terminal,
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="tamQuadro") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		String consDecoded = URL.decodeParam(tamAro);
		Page<Bicicleta> list = service.searchAro(consDecoded, Integer.parseInt(categoria), Integer.parseInt(terminal), page, linesPerPage, orderBy, direction);
		Page<BicicletaDTO> listDTO = list.map(obj -> new BicicletaDTO(obj));
		return ResponseEntity.ok().body(listDTO);
	}

}
