package com.jeisonruckert.bikescanoas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jeisonruckert.bikescanoas.domain.Oficina;

@Repository
public interface OficinaRepository extends JpaRepository<Oficina, Integer> {

}
