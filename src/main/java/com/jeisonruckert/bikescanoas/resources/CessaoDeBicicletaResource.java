package com.jeisonruckert.bikescanoas.resources;

import java.net.URI;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jeisonruckert.bikescanoas.domain.CessaoDeBicicleta;
import com.jeisonruckert.bikescanoas.dto.CessaoDeBicicletaDTO;
import com.jeisonruckert.bikescanoas.dto.CessaoDeBicicletaNewDTO;
import com.jeisonruckert.bikescanoas.services.CessaoDeBicicletaService;

@RestController
@RequestMapping(value="/cessoes")
public class CessaoDeBicicletaResource {
	
	@Autowired
	private CessaoDeBicicletaService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<CessaoDeBicicleta> find(@PathVariable Integer id) {
		CessaoDeBicicleta obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<CessaoDeBicicletaDTO>> findAll() {
		List<CessaoDeBicicleta> list = service.findAll();
		List<CessaoDeBicicletaDTO> listDTO = list.stream().map(obj -> 
		new CessaoDeBicicletaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<CessaoDeBicicletaDTO>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="data") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<CessaoDeBicicleta> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<CessaoDeBicicletaDTO> listDTO = list.map(obj -> new CessaoDeBicicletaDTO(obj));
		return ResponseEntity.ok().body(listDTO);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody CessaoDeBicicletaNewDTO objDto) {
		CessaoDeBicicleta obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@PathVariable Integer id, @Valid @RequestBody CessaoDeBicicletaDTO objDto) {
		CessaoDeBicicleta obj = service.fromDTO(objDto);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
