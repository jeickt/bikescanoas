package com.jeisonruckert.bikescanoas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jeisonruckert.bikescanoas.domain.Bicicleta;

@Repository
public interface BicicletaRepository extends JpaRepository<Bicicleta, Integer> {

}
