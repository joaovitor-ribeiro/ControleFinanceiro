package controlefinanceiroservicos.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import controlefinanceiroservicos.model.Cartao;
import controlefinanceiroservicos.model.Despesa;
import controlefinanceiroservicos.repository.CartaoRepository;
import controlefinanceiroservicos.repository.DespesaRepository;
import controlefinanceiroservicos.utils.ValidUtils;

@Service
public class CartaoService {	
	
	@Autowired
	private CartaoRepository cartaoRepository; //Instanciando o Repository
	
	@Autowired
	private DespesaRepository despesaRepository; 
	
	private ValidUtils valid = new ValidUtils(); 
	
	public void inserir (Cartao cartao) throws Exception{		
		validacoes(cartao);	
		cartao.setLimite(Double.parseDouble(cartao.getLimite().toString().replace("/[^0-9]/g", "")));		
		cartaoRepository.save(cartao);		
	}
	
	public List<Cartao> listar(String nome, List<String> bandeiras) {
		if(!(nome == null || nome.isEmpty()) && !(bandeiras == null || bandeiras.isEmpty())) {
			return cartaoRepository.findNomeAndBandeiras(nome, bandeiras);
		} else if(!(nome == null || nome.isEmpty())) {
			return cartaoRepository.findByNome(nome);
		} else if (!(bandeiras == null || bandeiras.isEmpty())) {
			return cartaoRepository.findByBandeiras(bandeiras);
		}
		return cartaoRepository.findAll();
	}

	public Cartao retornarCartaoId(Integer id){	
		Optional<Cartao> cartao = cartaoRepository.findById(id);
		
		if (cartao.isPresent()) {
			return cartao.get();
		}
		
		throw new RuntimeException("Cartão não encontrado!");	
	}

	public void editar(Integer id, Cartao cartaoNovo) throws Exception{
		Optional<Cartao> optionalCartaoAntigo = cartaoRepository.findById(id);
		
		if (optionalCartaoAntigo.isPresent()) {			
			Cartao cartaoAntigo = optionalCartaoAntigo.get();
			validacoes(cartaoNovo);
			cartaoAntigo.setNome(cartaoNovo.getNome());
			cartaoAntigo.setBandeira(cartaoNovo.getBandeira());
			cartaoAntigo.setLimite(cartaoNovo.getLimite());
			cartaoAntigo.setNumero(cartaoNovo.getNumero());
		} else {
			throw new RuntimeException("Cartão não foi encontrado para a edição!");
		}		
	}

	public void excluir(Integer id){
		Optional<Cartao> optionalCartao = cartaoRepository.findById(id);
		
		if (optionalCartao.isPresent()) {	
			Optional<Despesa> optionalDespesa = despesaRepository.findDespesaByIdCartao(id);
			if (optionalDespesa.isPresent()) {
				Despesa despesa = optionalDespesa.get();
				throw new RuntimeException("Esse cartão não pode ser excluído pois está vinculado a despesa: " + despesa.getDescricao() + "!");
			}
			cartaoRepository.deleteById(id);
		} else {
			throw new RuntimeException("Cartão não foi encontrado para a exclusão!");
		}
	}
	
	private void validacoes(Cartao cartao) throws Exception{
		valid.validaObject(cartao, Arrays.asList("nome", "bandeira", "limite"), 3, 20);

		if (valid.validStringEmpty(cartao.getNumero())) {
			throw new RuntimeException("O campo número é de preenchimento obrigatório!");
		} else if (cartao.getNumero().length() < 13 || cartao.getNumero().length() > 16 || !validarNumeroCartao(cartao.getNumero())) {			
			throw new RuntimeException("Número de cartão inválido!");
		} else if (!validarNumeroCorrespondenteABandeira(cartao.getNumero(), cartao.getBandeira())) {
			throw new RuntimeException("O número de cartão informado não corresponde com a bandeira!");
		}
		
	}
	
	private boolean validarNumeroCartao(String numero) {
		int total = 0;
		boolean deveDobrar = false;
		numero = numero.replace(" ", "");
		if ( !numero.matches("[0-9]*") ) {
			return false;
		}
		for (int i = numero.length() - 1; i >= 0; i--) {
			int digito = Integer.parseInt(numero.substring(i, (i + 1)));
			if (deveDobrar) {
				digito *= 2;
				if (digito > 9) digito -= 9;
			}
			total += digito;
			deveDobrar = !deveDobrar;
		}
		if (total <= 0) {
			return false;
		}
		return total % 10 == 0;
	}
	
	private boolean validarNumeroCorrespondenteABandeira(String numero, String bandeira) {
		switch (bandeira) {
		case "Mastercard":
			return numero.startsWith("51") || numero.startsWith("52") || numero.startsWith("53") || 
				   numero.startsWith("54") || numero.startsWith("55");
		case "Visa":
			return numero.startsWith("4");
		case "JCB":
			return numero.startsWith("35");
		case "American Express":
			return numero.startsWith("34") || numero.startsWith("37");
		case "Diners Club":
			return numero.startsWith("300") || numero.startsWith("301") || numero.startsWith("302") || 
				   numero.startsWith("303") || numero.startsWith("304") || numero.startsWith("305") || 
				   numero.startsWith("36")  || numero.startsWith("38");
		case "Aura":
			return numero.startsWith("50");
		case "Hipercard":
			return numero.startsWith("606282");
		default:
			return false;
		}
		
	}

}
