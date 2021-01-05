package com.jeisonruckert.bikescanoas.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.jeisonruckert.bikescanoas.domain.Cidade;
import com.jeisonruckert.bikescanoas.domain.Endereco;
import com.jeisonruckert.bikescanoas.domain.Usuario;
import com.jeisonruckert.bikescanoas.domain.enums.Perfil;
import com.jeisonruckert.bikescanoas.dto.UsuarioDTO;
import com.jeisonruckert.bikescanoas.dto.UsuarioNewDTO;
import com.jeisonruckert.bikescanoas.repositories.EnderecoRepository;
import com.jeisonruckert.bikescanoas.repositories.UsuarioRepository;
import com.jeisonruckert.bikescanoas.security.UserSS;
import com.jeisonruckert.bikescanoas.services.exceptions.AuthorizationException;
import com.jeisonruckert.bikescanoas.services.exceptions.DataIntegrityException;
import com.jeisonruckert.bikescanoas.services.exceptions.ObjectNotFoundException;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repo;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private EnderecoRepository enderecoRepository;

	public Usuario find(Integer id) {
		UserSS user = UserService.authenticated();
		if (user == null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso negado");
		}
		Optional<Usuario> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Usuário não encontrado! Id: " + id + ", Tipo: " + Usuario.class.getName()));
		}
	
	public List<Usuario> findAll() {
		return repo.findAll();
	}
	
	@Transactional
	public Usuario insert(Usuario obj) {
		obj.setId(null);
		obj = repo.save(obj);
		enderecoRepository.save(obj.getEndereco());
		return obj;
	}
	
	public Usuario update(Usuario obj) {
		Usuario objBD = find(obj.getId());
		updateData(objBD, obj);
		return repo.save(objBD);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir um usuário com registros associados");
		}
	}
	
	public Page<Usuario> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Usuario fromDTO(UsuarioDTO objDto) {
		return new Usuario(objDto.getId(), null, objDto.getNome(), null, objDto.getEmail());
	}
	
	public Usuario fromDTO(UsuarioNewDTO objDto) {
		Usuario usu = new Usuario(null, objDto.getCpf(), objDto.getNome(), pe.encode(objDto.getSenha()), objDto.getEmail());
		Cidade cid = new Cidade(objDto.getCidadeId(), null, null);
		Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), 
				objDto.getBairro(), objDto.getCep(), usu, cid);
		usu.setEndereco(end);
		usu.getTelefones().add(objDto.getTelefone1());
		if (objDto.getTelefone2() != null)
			usu.getTelefones().add(objDto.getTelefone2());
		if (objDto.getTelefone3() != null)
			usu.getTelefones().add(objDto.getTelefone3());
		return usu;
	}
	
	private void updateData(Usuario objBD, Usuario obj) {
		objBD.setNome(obj.getNome());
		objBD.setEmail(obj.getEmail());
		objBD.setEndereco(obj.getEndereco());
	}
	
 }
