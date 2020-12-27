package com.jeisonruckert.bikescanoas.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeisonruckert.bikescanoas.domain.Oficina;
import com.jeisonruckert.bikescanoas.repositories.OficinaRepository;
import com.jeisonruckert.bikescanoas.services.exceptions.ObjectNotFoundException;

@Service
public class OficinaService {
	
	@Autowired
	private OficinaRepository repo;

	public Oficina find(Integer id) {
		Optional<Oficina> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Objeto não encontrado! Id: " + id + ", Tipo: " + Oficina.class.getName()));
		}
	
 }
