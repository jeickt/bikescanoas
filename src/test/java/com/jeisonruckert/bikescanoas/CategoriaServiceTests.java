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

import com.jeisonruckert.bikescanoas.domain.Categoria;
import com.jeisonruckert.bikescanoas.repositories.CategoriaRepository;
import com.jeisonruckert.bikescanoas.services.CategoriaService;
import com.jeisonruckert.bikescanoas.services.exceptions.ObjectNotFoundException;

@RunWith(MockitoJUnitRunner.class)
public class CategoriaServiceTests {

	CategoriaService service;
	
	@Mock
	CategoriaRepository repo;
	
	@Before
	public void setup() throws ParseException {
		service = new CategoriaService(repo);
	}

	@Test
	public void getAll_returnListOfCategories_whenSuccessful() {
		// cenário
        Categoria categoria = new Categoria(null, "Urban");
		when(repo.findAll()).thenReturn(List.of(categoria, categoria));
		
		// ação
		List<Categoria> products = service.findAll();

		// verificação
		Assert.assertEquals(2, products.size());
	}
	
	@Test
	public void findById_returnCategoryWithTheIdSearched_whenSuccessful() {
		// cenário
		Optional<Categoria> categoria = Optional.of(new Categoria(null, "Urban"));
		when(repo.findById(3)).thenReturn(categoria);
		Integer id = 3;

		// ação
		Categoria cat = service.find(id);
		
		//verificação
		Assert.assertEquals("Urban", cat.getTipo());
	}

	@Test (expected = ObjectNotFoundException.class)
	public void findByIdError_dontReturnTheCategoryWhitTheIdSearched_whenIdNotFoundError() {
		// cenário
		Integer id = 7;

		// ação
		service.find(id);
	}

	@Test
	public void insert_createNewCategory_whenSuccessful() {
		// cenário
		Categoria categoria = new Categoria(null, "Urban");
		when(repo.save(categoria)).thenReturn(categoria);
		
		// ação
		Categoria cat = service.insert(categoria);

		// verificação
		Assert.assertEquals("Urban", cat.getTipo());
	}
	
	@Test
	public void update_updateACategory_whenSuccessful() {
		// cenário
		Integer id = 1;
		Categoria novaCategoria = new Categoria(1, "MTB");
		Optional<Categoria> cat = Optional.of(new Categoria(1, "Urban"));
		when(repo.findById(id)).thenReturn(cat);
		when(repo.save(novaCategoria)).thenReturn(novaCategoria);
		
		// ação
		Categoria categoriaAtualizada = service.update(novaCategoria);
		
		// verificação
		Assert.assertEquals("MTB", categoriaAtualizada.getTipo());
	}
	
	@Test (expected = ObjectNotFoundException.class)
	public void updateError_dontUpdateACategory_whenIdIsNotValidError() {
		// cenário
		Categoria novaCategoria = new Categoria(3, "MTB");
		
		// ação
		service.update(novaCategoria);
	}
	
	@Test
	public void delete_deleteACategory_whenSeccessful() {
		// cenário
		Integer id = 1;
		Optional<Categoria> cat = Optional.of(new Categoria(3, "MTB"));
		when(repo.findById(id)).thenReturn(cat);
		
		// ação
		service.delete(id);		
	}
	
	@Test (expected = ObjectNotFoundException.class)
	public void deleteError_dontDeleteACategory_whenIdIsNotValidError() {
		// cenário
		Integer id = 8;
		
		// ação
		service.delete(id);		
	}

}
