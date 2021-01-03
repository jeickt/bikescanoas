package com.jeisonruckert.bikescanoas.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jeisonruckert.bikescanoas.domain.CessaoDeBicicleta;
import com.jeisonruckert.bikescanoas.domain.Usuario;

@Repository
public interface CessaoDeBicicletaRepository extends JpaRepository<CessaoDeBicicleta, Integer> {
	
	@Transactional(readOnly=true)
	Page<CessaoDeBicicleta> findByUsuario(Usuario usuario, Pageable pageRquest);

}
