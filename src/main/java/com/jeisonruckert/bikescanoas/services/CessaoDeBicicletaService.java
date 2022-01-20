package com.jeisonruckert.bikescanoas.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.jeisonruckert.bikescanoas.domain.Bicicleta;
import com.jeisonruckert.bikescanoas.domain.Categoria;
import com.jeisonruckert.bikescanoas.domain.CessaoDeBicicleta;
import com.jeisonruckert.bikescanoas.domain.Terminal;
import com.jeisonruckert.bikescanoas.domain.Usuario;
import com.jeisonruckert.bikescanoas.dto.CessaoDeBicicletaCompletaDTO;
import com.jeisonruckert.bikescanoas.dto.CessaoDeBicicletaDTO;
import com.jeisonruckert.bikescanoas.repositories.CessaoDeBicicletaRepository;
import com.jeisonruckert.bikescanoas.security.UserSS;
import com.jeisonruckert.bikescanoas.services.exceptions.AuthorizationException;
import com.jeisonruckert.bikescanoas.services.exceptions.ObjectNotFoundException;

@Service
public class CessaoDeBicicletaService {
	
	private CessaoDeBicicletaRepository repo;
	
	private BicicletaService bicicletaService;
	private CategoriaService categoriaService;
	private TerminalService terminalService;
	private UsuarioService usuarioService;
	
	public CessaoDeBicicletaService(CessaoDeBicicletaRepository repository, BicicletaService bicicletaService,
			CategoriaService categoriaService, TerminalService terminalService, UsuarioService usuarioService) {
		this.repo = repository;
		this.bicicletaService = bicicletaService;
		this.categoriaService = categoriaService;
		this.terminalService = terminalService;
		this.usuarioService = usuarioService;
	}

	public CessaoDeBicicleta find(Integer id) {
		Optional<CessaoDeBicicleta> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Cessão de bicicleta não encontrada! Id: " + id + ", Tipo: " + CessaoDeBicicleta.class.getName()));
	}
	
	public List<CessaoDeBicicleta> findAll() {
		return repo.findAll();
	}
	
	@Transactional
	public CessaoDeBicicleta insert(CessaoDeBicicleta obj) {
		obj.setId(null);
		obj.setData(new Date());
		obj = repo.save(obj);
		bicicletaService.insert(obj.getBicicleta());
		return obj;
	}
	
	public CessaoDeBicicleta update(CessaoDeBicicleta obj) {
		CessaoDeBicicleta objBD = find(obj.getId());
		updateData(objBD, obj);
		return repo.save(objBD);
	}
	
	public void delete(Integer id) {
		find(id);
		repo.deleteById(id);
	}
	
	public Page<CessaoDeBicicleta> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public CessaoDeBicicleta fromDTO(CessaoDeBicicletaDTO objDto) {
		return new CessaoDeBicicleta(objDto.getId(), objDto.getPreco(), objDto.getLoja());
	}
	
	public CessaoDeBicicleta fromDTO(CessaoDeBicicletaCompletaDTO objDto) {
		Categoria cat = categoriaService.find(objDto.getCategoriaId());
		Terminal ter = terminalService.find(objDto.getTerminalId());
		Bicicleta bic = new Bicicleta(null, objDto.getFabricante(), objDto.getModelo(), 
				objDto.getTamQuadro(), objDto.getTamAro(), cat, ter);
		Usuario usu = usuarioService.find(objDto.getUsuarioId());
		CessaoDeBicicleta ces = new CessaoDeBicicleta(null, objDto.getPreco(), objDto.getLoja());
		ces.setBicicleta(bic);
		ces.setUsuario(usu);
		return ces;
	}
	
	private void updateData(CessaoDeBicicleta objBD, CessaoDeBicicleta obj) {
		objBD.setData(obj.getData());
		objBD.setPreco(obj.getPreco());
		objBD.setLoja(obj.getLoja());
		objBD.setUsuario(obj.getUsuario());
	}
	
	public Page<CessaoDeBicicleta> findPageByUser(Integer page, Integer linesPerPage, String orderBy, String direction) {
		UserSS user = UsuarioLoginService.authenticated();
		if (user == null) {
			throw new AuthorizationException("Acesso negado");
		}
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Usuario usu = usuarioService.find(user.getId());
		return repo.findByUsuario(usu, pageRequest);
	}
	
 }
