package com.jeisonruckert.bikescanoas;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jeisonruckert.bikescanoas.domain.Bicicleta;
import com.jeisonruckert.bikescanoas.domain.Categoria;
import com.jeisonruckert.bikescanoas.domain.Cidade;
import com.jeisonruckert.bikescanoas.domain.Compra;
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
import com.jeisonruckert.bikescanoas.repositories.BicicletaRepository;
import com.jeisonruckert.bikescanoas.repositories.CategoriaRepository;
import com.jeisonruckert.bikescanoas.repositories.CidadeRepository;
import com.jeisonruckert.bikescanoas.repositories.CompraRepository;
import com.jeisonruckert.bikescanoas.repositories.EnderecoRepository;
import com.jeisonruckert.bikescanoas.repositories.EstadoRepository;
import com.jeisonruckert.bikescanoas.repositories.ManutencaoRepository;
import com.jeisonruckert.bikescanoas.repositories.OficinaRepository;
import com.jeisonruckert.bikescanoas.repositories.PagamentoRepository;
import com.jeisonruckert.bikescanoas.repositories.TerminalRepository;
import com.jeisonruckert.bikescanoas.repositories.UsoRepository;
import com.jeisonruckert.bikescanoas.repositories.UsuarioRepository;

@SpringBootApplication
public class BikescanoasApplication implements CommandLineRunner {
	
	@Autowired
	private BicicletaRepository bicicletaRepository;
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private CompraRepository compraRepository;
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

	

	public static void main(String[] args) {
		SpringApplication.run(BikescanoasApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Estado est1 = new Estado(null, "Rio Grande do Sul");
		
		Cidade cid1 = new Cidade(null, "Canoas", est1);
		Cidade cid2 = new Cidade(null, "Porto Alegre", est1);
		
		est1.getCidades().addAll(Arrays.asList(cid1, cid2));
		
		estadoRepository.saveAll(Arrays.asList(est1));
		cidadeRepository.saveAll(Arrays.asList(cid1, cid2));
		
		Usuario us1 = new Usuario(null, "01234567890", "Jorge Cardoso", "jorge@gmail.com");
		us1.getTelefones().addAll(Arrays.asList("5130313233", "51987654321"));
		
		Endereco end1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Igara", "92412-250", us1, cid1);
		us1.setEndereco(end1);
		
		usuarioRepository.saveAll(Arrays.asList(us1));
		enderecoRepository.saveAll(Arrays.asList(end1));
		
		Categoria cat1 = new Categoria(null, "MTB");
		Categoria cat2 = new Categoria(null, "Speed");
		Categoria cat3 = new Categoria(null, "Urban");
		Categoria cat4 = new Categoria(null, "Infantil");
		
		Bicicleta b1 = new Bicicleta(null, "Caloi", "Atacama", "19", "29", cat1);
		
		cat1.getBicicletas().addAll(Arrays.asList(b1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4));
		bicicletaRepository.saveAll(Arrays.asList(b1));

		Compra com1 = new Compra(null, sdf.parse("05/12/2020 15:18"), 3999.99, "Centauro", us1, b1);
		
		compraRepository.saveAll(Arrays.asList(com1));
		
		Pagamento pag1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, us1, 3);
		Pagamento pag2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, us1, sdf.parse("20/02/2021 23:59"), null);
		us1.getPagamentos().addAll(Arrays.asList(pag1, pag2));
		
		pagamentoRepository.saveAll(Arrays.asList(pag1, pag2));
		
		Terminal ter1 = new Terminal(null, "centro", 550);
		b1.setTerminal(ter1);
		
		ter1.getBicicletas().addAll(Arrays.asList(b1));
		
		terminalRepository.saveAll(Arrays.asList(ter1));
		
		Oficina ofi1 = new Oficina(null, "88888888444422", "BikesConserto");
		
		Manutencao man1 = new Manutencao(null, sdf.parse("15/12/2020 16:55"), 252.34, 86.00, "Anderson Machado", b1, ofi1);
		ofi1.getManutencoes().addAll(Arrays.asList(man1));
		
		oficinaRepository.saveAll(Arrays.asList(ofi1));
		manutencaoRepository.saveAll(Arrays.asList(man1));
		
		Uso uso1 = new Uso(null, sdf.parse("12/12/2020 7:10"), sdf.parse("12/12/2020 7:50"), us1, b1);
		us1.getUsos().addAll(Arrays.asList(uso1));
		
		usuarioRepository.saveAll(Arrays.asList(us1));
		enderecoRepository.saveAll(Arrays.asList(end1));
		usoRepository.saveAll(Arrays.asList(uso1));

	}

}
