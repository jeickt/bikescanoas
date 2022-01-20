package com.jeisonruckert.bikescanoas.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.jeisonruckert.bikescanoas.domain.Bicicleta;
import com.jeisonruckert.bikescanoas.domain.Categoria;
import com.jeisonruckert.bikescanoas.domain.CessaoDeBicicleta;
import com.jeisonruckert.bikescanoas.domain.Cidade;
import com.jeisonruckert.bikescanoas.domain.Endereco;
import com.jeisonruckert.bikescanoas.domain.Estado;
import com.jeisonruckert.bikescanoas.domain.Manutencao;
import com.jeisonruckert.bikescanoas.domain.Oficina;
import com.jeisonruckert.bikescanoas.domain.Pagamento;
import com.jeisonruckert.bikescanoas.domain.PagamentoComBoleto;
import com.jeisonruckert.bikescanoas.domain.PagamentoComCartao;
import com.jeisonruckert.bikescanoas.domain.Terminal;
import com.jeisonruckert.bikescanoas.domain.Uso;
import com.jeisonruckert.bikescanoas.domain.Usuario;
import com.jeisonruckert.bikescanoas.domain.enums.EstadoPagamento;
import com.jeisonruckert.bikescanoas.domain.enums.Perfil;
import com.jeisonruckert.bikescanoas.repositories.BicicletaRepository;
import com.jeisonruckert.bikescanoas.repositories.CategoriaRepository;
import com.jeisonruckert.bikescanoas.repositories.CessaoDeBicicletaRepository;
import com.jeisonruckert.bikescanoas.repositories.CidadeRepository;
import com.jeisonruckert.bikescanoas.repositories.EnderecoRepository;
import com.jeisonruckert.bikescanoas.repositories.EstadoRepository;
import com.jeisonruckert.bikescanoas.repositories.ManutencaoRepository;
import com.jeisonruckert.bikescanoas.repositories.OficinaRepository;
import com.jeisonruckert.bikescanoas.repositories.PagamentoRepository;
import com.jeisonruckert.bikescanoas.repositories.TerminalRepository;
import com.jeisonruckert.bikescanoas.repositories.UsoRepository;
import com.jeisonruckert.bikescanoas.repositories.UsuarioRepository;

@Service
public class DBService {
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private BicicletaRepository bicicletaRepository;
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private CessaoDeBicicletaRepository cessaoDeBicicletaRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private ManutencaoRepository manutencaoRepository;
	@Autowired
	private OficinaRepository oficinaRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private TerminalRepository terminalRepository;
	@Autowired
	private UsoRepository usoRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;

	public void instantiateTestDatabase() throws ParseException {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Estado est1 = new Estado(null, "Rio Grande do Sul");
		
		Cidade cid1 = new Cidade(null, "Canoas", est1);
		Cidade cid2 = new Cidade(null, "Porto Alegre", est1);
		
		est1.getCidades().addAll(Arrays.asList(cid1, cid2));
		
		estadoRepository.saveAll(Arrays.asList(est1));
		cidadeRepository.saveAll(Arrays.asList(cid1, cid2));
		
		Usuario usu1 = new Usuario(null, "58364361007", "Jorge Cardoso", pe.encode("senha"), "jorgecardoso@gmail.com");
		usu1.getTelefones().addAll(Arrays.asList("5130313233", "51987654321"));
		
		Usuario usu2 = new Usuario(null, "92946090060", "Ana Cardoso", pe.encode("outrasenha"), "anacardoso@gmail.com");
		usu1.getTelefones().addAll(Arrays.asList("5130313233", "51987654320"));
		usu2.addPerfil(Perfil.ADMIN);
		
		Endereco end1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Igara", "92412-250", usu1, cid1);
		usu1.setEndereco(end1);
		usu2.setEndereco(end1);
		
		usuarioRepository.saveAll(Arrays.asList(usu1, usu2));
		enderecoRepository.saveAll(Arrays.asList(end1));
		
		Categoria cat1 = new Categoria(null, "MTB");
		Categoria cat2 = new Categoria(null, "Speed");
		Categoria cat3 = new Categoria(null, "Urban");
		Categoria cat4 = new Categoria(null, "Infantil");
		
		Terminal ter1 = new Terminal(null, "centro", 550);
		
		Bicicleta bic1 = new Bicicleta(null, "Caloi", "Atacama", "19", "29", cat1, ter1);
		ter1.getBicicletas().addAll(Arrays.asList(bic1));
		cat1.getBicicletas().addAll(Arrays.asList(bic1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4));
		terminalRepository.saveAll(Arrays.asList(ter1));
		bicicletaRepository.saveAll(Arrays.asList(bic1));

		CessaoDeBicicleta ces1 = new CessaoDeBicicleta(null, 3999.99, "Centauro");
		ces1.setUsuario(usu1);
		ces1.setBicicleta(bic1);
		
		cessaoDeBicicletaRepository.saveAll(Arrays.asList(ces1));
		
		Pagamento pag1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, usu1, 3);
		Pagamento pag2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, usu1, sdf.parse("20/02/2021 23:59"), null);
		usu1.getPagamentos().addAll(Arrays.asList(pag1, pag2));
		
		pagamentoRepository.saveAll(Arrays.asList(pag1, pag2));
		
		Oficina ofi1 = new Oficina(null, "88888888444422", "BikesConserto");
		
		Manutencao man1 = new Manutencao(null, 252.34, 86.00, "Anderson Machado");
		man1.setBicicleta(bic1);
		man1.setOficina(ofi1);
		ofi1.getManutencoes().addAll(Arrays.asList(man1));
		
		oficinaRepository.saveAll(Arrays.asList(ofi1));
		manutencaoRepository.saveAll(Arrays.asList(man1));
		
		Uso uso1 = new Uso(null, sdf.parse("12/12/2020 7:10"), sdf.parse("12/12/2020 7:50"));
		uso1.setUsuario(usu1);
		uso1.setDistancia(43.56);
		uso1.getUsuario().setKmTotal(uso1.getDistancia());
		uso1.setBicicleta(bic1);
		usu1.getUsos().addAll(Arrays.asList(uso1));
		
		usoRepository.saveAll(Arrays.asList(uso1));
	}
}
