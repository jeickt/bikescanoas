package com.jeisonruckert.bikescanoas;

import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jeisonruckert.bikescanoas.repositories.BicicletaRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BicicletaRepositoryTest {
	
	@Autowired
	private BicicletaRepository repo;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();

}