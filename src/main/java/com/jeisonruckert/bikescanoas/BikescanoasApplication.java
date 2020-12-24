package com.jeisonruckert.bikescanoas;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jeisonruckert.bikescanoas.domain.Categoria;
import com.jeisonruckert.bikescanoas.repositories.CategoriaRepository;

@SpringBootApplication
public class BikescanoasApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	public static void main(String[] args) {
		SpringApplication.run(BikescanoasApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Bicicleta");
		Categoria cat2 = new Categoria(null, "Patinete");
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		
	}

}
