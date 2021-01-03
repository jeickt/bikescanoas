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
import com.jeisonruckert.bikescanoas.domain.Categoria;
import com.jeisonruckert.bikescanoas.domain.CessaoDeBicicleta;
import com.jeisonruckert.bikescanoas.domain.Terminal;
import com.jeisonruckert.bikescanoas.domain.Usuario;
import com.jeisonruckert.bikescanoas.dto.CessaoDeBicicletaDTO;
import com.jeisonruckert.bikescanoas.dto.CessaoDeBicicletaNewDTO;
import com.jeisonruckert.bikescanoas.repositories.BicicletaRepository;
import com.jeisonruckert.bikescanoas.repositories.CategoriaRepository;
import com.jeisonruckert.bikescanoas.repositories.CessaoDeBicicletaRepository;
import com.jeisonruckert.bikescanoas.repositories.TerminalRepository;
import com.jeisonruckert.bikescanoas.repositories.UsuarioRepository;
import com.jeisonruckert.bikescanoas.security.UserSS;
import com.jeisonruckert.bikescanoas.services.exceptions.AuthorizationException;
import com.jeisonruckert.bikescanoas.services.exceptions.ObjectNotFoundException;

@Service
public class CessaoDeBicicletaService {
	
	@Autowired
	private BicicletaRepository bicicletaRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private CessaoDeBicicletaRepository repo;
	
	@Autowired
	private TerminalRepository terminalRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

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
		bicicletaRepository.save(obj.getBicicleta());
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
	
	public CessaoDeBicicleta fromDTO(CessaoDeBicicletaNewDTO objDto) {
		Categoria cat = categoriaRepository.findById(objDto.getCategoriaId()).get();
		Terminal ter = terminalRepository.findById(objDto.getTerminalId()).get();
		Bicicleta bic = new Bicicleta(null, objDto.getFabricante(), objDto.getModelo(), 
				objDto.getTamQuadro(), objDto.getTamAro(), cat, ter);
		Usuario usu = usuarioRepository.findById(objDto.getUsuarioId()).get();
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
		UserSS user = UserService.authenticated();
		if (user == null) {
			throw new AuthorizationException("Acesso negado");
		}
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Usuario usu = usuarioRepository.findById(user.getId()).get();
		return repo.findByUsuario(usu, pageRequest);
	}
	
 }
