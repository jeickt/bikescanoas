package com.jeisonruckert.bikescanoas;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jeisonruckert.bikescanoas.domain.Bicicleta;
import com.jeisonruckert.bikescanoas.domain.Categoria;
import com.jeisonruckert.bikescanoas.repositories.BicicletaRepository;
import com.jeisonruckert.bikescanoas.repositories.CategoriaRepository;

@SpringBootApplication
public class BikescanoasApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private BicicletaRepository bicicletaRepository;

	public static void main(String[] args) {
		SpringApplication.run(BikescanoasApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "MTB");
		Categoria cat2 = new Categoria(null, "Speed");
		Categoria cat3 = new Categoria(null, "Urban");
		Categoria cat4 = new Categoria(null, "Infantil");
		
		
		Bicicleta b1 = new Bicicleta(null, "Caloi", "Atacama", "19", "29", 0.0, 0.0, false, cat1);
		Bicicleta b2 = new Bicicleta(null, "Soul", "Claris", "XS", "29", 0.0, 0.0, false, cat3);
		Bicicleta b3 = new Bicicleta(null, "Oggi", "Velloce", "56", "700", 0.0, 0.0, false, cat2);
		Bicicleta b4 = new Bicicleta(null, "Houston", "TN161Q", "único", "20", 0.0, 0.0, false, cat4);
		Bicicleta b5 = new Bicicleta(null, "Caloi", "10", "55", "700", 0.0, 0.0, false, cat2);
		
		cat1.getBicicletas().addAll(Arrays.asList(b1));
		cat2.getBicicletas().addAll(Arrays.asList(b3, b5));
		cat3.getBicicletas().addAll(Arrays.asList(b2));
		cat4.getBicicletas().addAll(Arrays.asList(b4));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4));
		bicicletaRepository.saveAll(Arrays.asList(b1, b2, b3, b4, b5));
		
	}

}
