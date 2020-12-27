package com.jeisonruckert.bikescanoas.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jeisonruckert.bikescanoas.domain.Oficina;
import com.jeisonruckert.bikescanoas.services.OficinaService;

@RestController
@RequestMapping(value="/oficinas")
public class OficinaResource {
	
	@Autowired
	private OficinaService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Oficina obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}

}
