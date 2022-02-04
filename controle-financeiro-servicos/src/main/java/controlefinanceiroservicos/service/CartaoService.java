package controlefinanceiroservicos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import controlefinanceiroservicos.model.Cartao;
import controlefinanceiroservicos.repository.CartaoRepository;

@Service
public class CartaoService {	
	@Autowired
	private CartaoRepository cartaoRepository; //Instanciando o Repository
	
	public void inserir (Cartao cartao) throws Exception {
		
		if (cartao.getNome().isEmpty()) {
			throw new Exception("O campo nome é de preenchimento obrigatório!");
		} else if (cartao.getNome().length() < 3) {
			throw new Exception("O nome não pode ter menos do que 3 caracteres!");
		} else if (cartao.getNome().length() > 20) {
			throw new Exception("O nome não pode ter mais do que 20 caracteres!");
		}
		
		if (cartao.getBandeira().isEmpty()) {
			throw new Exception("O campo bandeira é de preenchimento obrigatório!");
		} else if (cartao.getBandeira().length() < 3) {
			throw new Exception("A bandeira não pode ter menos do que 3 caracteres!");
		} else if (cartao.getBandeira().length() > 20) {
			throw new Exception("A bandeira não pode ter mais do que 20 caracteres!");
		}

		//TODO - Validar se o número do cartão digitado é válido (ex.: 0000 0000)
		if (cartao.getNumero().isEmpty()) {
			throw new Exception("O campo número é de preenchimento obrigatório!");
		} else if (cartao.getNumero().length() < 13 || cartao.getNumero().length() > 16) {			
			throw new Exception("Número de cartão inválido!");
		} else if (!cartao.getNumero().matches("[0-9]*")) {
			throw new Exception("Número de cartão deve conter somente números!");
		}
		
		if (cartao.getLimite() <= 0) {
			throw new Exception("O limite não pode ser menor ou igual a zero!");		
		}
			
		cartao.setLimite(Double.parseDouble(cartao.getLimite().toString().replace("/[^0-9]/g", "")));		
		cartaoRepository.save(cartao);		
	}
	
	public List<Cartao> listar() {
		return cartaoRepository.findAll();
	}

	public Cartao retornarCartaoId(Integer id) throws Exception {	
		Optional<Cartao> cartao = cartaoRepository.findById(id);
		
		if (cartao.isPresent()) {
			return cartao.get();
		}
		
		throw new Exception("Cartão não encontrado!");	
	}

	public void editar(Integer id, Cartao cartaoNovo) throws Exception {
		Optional<Cartao> optionalCartaoAntigo = cartaoRepository.findById(id);
		
		if (optionalCartaoAntigo.isPresent()) {
			Cartao cartaoAntigo =  optionalCartaoAntigo.get();
			cartaoAntigo.setNome(cartaoNovo.getNome());
			cartaoAntigo.setBandeira(cartaoNovo.getBandeira());
			cartaoAntigo.setLimite(cartaoNovo.getLimite());
			cartaoAntigo.setNumero(cartaoNovo.getNumero());
		} else {
			throw new Exception("Cartão não foi encontrado para a edição!");
		}		
	}	
}
