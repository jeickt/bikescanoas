package com.jeisonruckert.bikescanoas.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jeisonruckert.bikescanoas.domain.Bicicleta;
import com.jeisonruckert.bikescanoas.domain.Categoria;
import com.jeisonruckert.bikescanoas.domain.Terminal;

@Repository
public interface BicicletaRepository extends JpaRepository<Bicicleta, Integer> {

	@Transactional(readOnly=true)
	@Query("SELECT obj FROM Bicicleta obj INNER JOIN obj.categoria cat INNER JOIN obj.terminal ter WHERE obj.tamQuadro = :tamQuadro AND cat = :categoria AND ter = :terminal")
	Page<Bicicleta> searchQuadro(String tamQuadro, Categoria categoria, Terminal terminal, Pageable pageRequest);
	
	// @Query("SELECT obj FROM Bicicleta obj INNER JOIN obj.categoria cat INNER JOIN obj.terminal ter WHERE obj.tamAro = :tamAro AND cat = :categoria AND ter = :terminal")
	// Page<Bicicleta> searchAro(String tamAro, Categoria categoria, Terminal terminal, Pageable pageRequest);
	@Transactional(readOnly=true)
	Page<Bicicleta> findByTamAroAndCategoriaAndTerminal(String tamQuadro, Categoria categoria, Terminal terminal, Pageable pageRequest);
}
