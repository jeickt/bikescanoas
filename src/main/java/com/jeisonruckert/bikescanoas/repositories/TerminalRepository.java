package com.jeisonruckert.bikescanoas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jeisonruckert.bikescanoas.domain.Terminal;

@Repository
public interface TerminalRepository extends JpaRepository<Terminal, Integer> {

}
