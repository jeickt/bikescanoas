package com.jeisonruckert.bikescanoas.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jeisonruckert.bikescanoas.domain.Uso;
import com.jeisonruckert.bikescanoas.domain.Usuario;

@Repository
public interface UsoRepository extends JpaRepository<Uso, Integer> {
	
	@Transactional(readOnly=true)
	Page<Uso> findByUsuario(Usuario usuario, Pageable pageRquest);

}
