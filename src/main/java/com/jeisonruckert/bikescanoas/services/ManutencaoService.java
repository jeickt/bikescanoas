package com.jeisonruckert.bikescanoas.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.jeisonruckert.bikescanoas.domain.Bicicleta;
import com.jeisonruckert.bikescanoas.domain.Manutencao;
import com.jeisonruckert.bikescanoas.domain.Oficina;
import com.jeisonruckert.bikescanoas.dto.ManutencaoDTO;
import com.jeisonruckert.bikescanoas.repositories.BicicletaRepository;
import com.jeisonruckert.bikescanoas.repositories.ManutencaoRepository;
import com.jeisonruckert.bikescanoas.repositories.OficinaRepository;
import com.jeisonruckert.bikescanoas.services.exceptions.ObjectNotFoundException;

@Service
public class ManutencaoService {
	
	@Autowired
	private BicicletaRepository bicicletaRepository;
	
	@Autowired
	private ManutencaoRepository repo;

	@Autowired
	private OficinaRepository oficinaRepository;

	public Manutencao find(Integer id) {
		Optional<Manutencao> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Manutenção não encontrada! Id: " + id + ", Tipo: " + Manutencao.class.getName()));
	}
	
	public List<Manutencao> findAll() {
		return repo.findAll();
	}
	
	@Transactional
	public Manutencao insert(Manutencao obj) {
		obj.setId(null);
		obj.setData(new Date());
		obj = repo.save(obj);
		return obj;
	}
	
	public Manutencao update(Manutencao obj) {
		Manutencao objBD = find(obj.getId());
		updateData(objBD, obj);
		return repo.save(objBD);
	}
	
	public void delete(Integer id) {
		find(id);
		repo.deleteById(id);
	}
	
	public Page<Manutencao> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Manutencao fromDTOUp(ManutencaoDTO objDto) {
		return new Manutencao(objDto.getId(), objDto.getKm(), objDto.getCusto(), objDto.getMecanico());
	}
	
	public Manutencao fromDTO(ManutencaoDTO objDto) {
		Bicicleta bic = bicicletaRepository.findById(objDto.getBicicletaId()).get();
		bic.setKmManutencao(0.0);
		Oficina ofi = oficinaRepository.findById(objDto.getOficinaId()).get();
		Manutencao man = new Manutencao(null, objDto.getKm(), objDto.getCusto(), objDto.getMecanico());
		man.setPecas(objDto.getPecas());
		man.setServicos(objDto.getServicos());
		man.setBicicleta(bic);
		man.setOficina(ofi);
		return man;
	}
	
	private void updateData(Manutencao objBD, Manutencao obj) {
		objBD.setData(obj.getData());
		objBD.setKm(obj.getKm());
		objBD.setCusto(obj.getCusto());
		objBD.setMecanico(obj.getMecanico());
		objBD.setPecas(obj.getPecas());
		objBD.setServicos(obj.getServicos());
	}
	
 }
