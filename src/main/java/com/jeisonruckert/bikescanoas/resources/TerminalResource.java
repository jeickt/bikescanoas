package com.jeisonruckert.bikescanoas.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jeisonruckert.bikescanoas.domain.Terminal;
import com.jeisonruckert.bikescanoas.services.TerminalService;

@RestController
@RequestMapping(value="/terminais")
public class TerminalResource {
	
	@Autowired
	private TerminalService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Terminal> find(@PathVariable Integer id) {
		Terminal obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}

}
