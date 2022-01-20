package com.jeisonruckert.bikescanoas;

import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.jeisonruckert.bikescanoas.domain.Bicicleta;
import com.jeisonruckert.bikescanoas.domain.Categoria;
import com.jeisonruckert.bikescanoas.domain.Terminal;
import com.jeisonruckert.bikescanoas.repositories.BicicletaRepository;
import com.jeisonruckert.bikescanoas.services.BicicletaService;
import com.jeisonruckert.bikescanoas.services.CategoriaService;
import com.jeisonruckert.bikescanoas.services.TerminalService;
import com.jeisonruckert.bikescanoas.services.exceptions.ObjectNotFoundException;

@RunWith(MockitoJUnitRunner.class)
public class BicicletaServiceTests {

	BicicletaService service;
	
	@Mock
	BicicletaRepository repo;
	
	@Mock
	CategoriaService categoriaService;
	
	@Mock
	TerminalService terminalService;
	
	@Before
	public void setup() throws ParseException {
		service = new BicicletaService(repo, categoriaService, terminalService);
	}

	@Test
	public void getAll_returnListOfBycicles_whenSuccessful() {
		// cenário
        Bicicleta bicicleta = new Bicicleta(null, "Caloi", "Atacama", "19", "29", new Categoria(null, "MTB"), new Terminal(null, "centro", 550));
		when(repo.findAll()).thenReturn(List.of(bicicleta, bicicleta));
		
		// ação
		List<Bicicleta> products = service.findAll();

		// verificação
		Assert.assertEquals(2, products.size());
	}
	
	@Test
	public void findById_returnBycicleWithTheIdSearched_whenSuccessful() {
		// cenário
		Optional<Bicicleta> bicicleta = Optional.of(new Bicicleta(null, "Caloi", "Atacama", "19", "29", new Categoria(null, "MTB"), new Terminal(null, "centro", 550)));
		when(repo.findById(3)).thenReturn(bicicleta);
		Integer id = 3;

		// ação
		Bicicleta bic = service.find(id);
		
		//verificação
		Assert.assertEquals("Atacama", bic.getModelo());
	}

	@Test (expected = ObjectNotFoundException.class)
	public void findByIdError_dontReturnTheBycicleWhitTheIdSearched_whenIdNotFoundError() {
		// cenário
		when(repo.findById(7)).thenThrow(ObjectNotFoundException.class);
		Integer id = 7;

		// ação
		service.find(id);
	}

	@Test
	public void insert_createNewBycicle_whenSuccessful() {
		// cenário
		Bicicleta bicicleta = new Bicicleta(null, "Caloi", "Atacama", "19", "29", new Categoria(null, "MTB"), new Terminal(null, "centro", 550));
		when(repo.save(bicicleta)).thenReturn(bicicleta);
		
		// ação
		Bicicleta bic = service.insert(bicicleta);

		// verificação
		Assert.assertEquals("19", bic.getTamQuadro());
	}
	
	@Test
	public void update_updateABycicle_whenSuccessful() {
		// cenário
		Integer id = 1;
		Bicicleta novaBicicleta = new Bicicleta(1, "Caloi", "Atacama", "19", "29", new Categoria(null, "MTB"), new Terminal(null, "centro", 550));
		Optional<Bicicleta> bic = Optional.of(new Bicicleta(1, "Caloi", "Atacama", "17", "29", new Categoria(null, "MTB"), new Terminal(null, "centro", 550)));
		when(repo.findById(id)).thenReturn(bic);
		when(repo.save(novaBicicleta)).thenReturn(novaBicicleta);
		
		// ação
		Bicicleta bicicletaAtualizada = service.update(novaBicicleta);
		
		// verificação
		Assert.assertEquals("19", bicicletaAtualizada.getTamQuadro());
	}
	
	@Test (expected = ObjectNotFoundException.class)
	public void updateError_dontUpdateABycicle_whenIdIsNotValidError() {
		// cenário
		Bicicleta novaBicicleta = new Bicicleta(4, "Caloi", "Atacama", "19", "29", new Categoria(null, "MTB"), new Terminal(null, "centro", 550));
		
		// ação
		service.update(novaBicicleta);
	}
	
	@Test
	public void delete_deleteABycicle_whenSeccessful() {
		// cenário
		Integer id = 1;
		Optional<Bicicleta> bic = Optional.of(new Bicicleta(4, "Caloi", "Atacama", "17", "29", new Categoria(null, "MTB"), new Terminal(null, "centro", 550)));
		when(repo.findById(id)).thenReturn(bic);
		
		// ação
		service.delete(id);		
	}
	
	@Test (expected = ObjectNotFoundException.class)
	public void deleteError_dontDeleteABycicle_whenIdIsNotValidError() {
		// cenário
		Integer id = 8;
		
		// ação
		service.delete(id);		
	}

}
