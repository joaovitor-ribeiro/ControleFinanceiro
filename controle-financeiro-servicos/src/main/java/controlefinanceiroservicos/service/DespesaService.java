package controlefinanceiroservicos.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import controlefinanceiroservicos.model.Despesa;
import controlefinanceiroservicos.repository.DespesaCustomRepository;
import controlefinanceiroservicos.repository.DespesaRepository;

@Service
public class DespesaService {
	
	@Autowired
	private DespesaRepository despesaRepository; 
	@Autowired
	private DespesaCustomRepository despesaCustomRepository; 
	
	public void inserir(Despesa despesa) {
		validaDespesa(despesa);
		despesaRepository.save(despesa);
	}

	public Page<Despesa> listar(String descricao, List<Integer> categorias, Date dataInicial, Date dataFinal, Pageable paginacao) {
		if ( (descricao == null || descricao.isEmpty()) && (categorias == null || categorias.isEmpty()) && dataInicial == null && dataFinal == null )  {
			return despesaRepository.findAll(paginacao);
		}
		return despesaCustomRepository.listar(descricao, categorias, dataInicial, dataFinal, paginacao);
	}

	public Despesa retornarDespesaId(Integer id) {
		Optional<Despesa> optionalDespesa = despesaRepository.findById(id);
		if (optionalDespesa.isPresent()) {
			return optionalDespesa.get();
		}
		throw new RuntimeException("Despesa não encontrada!");	
	}

	public void editar(Integer id, Despesa despesaNova) {
		Optional<Despesa> optionalDespesaAntiga = despesaRepository.findById(id);
		if (optionalDespesaAntiga.isPresent()) {
			Despesa despesaAntiga = optionalDespesaAntiga.get();
			validaDespesa(despesaNova);
			despesaAntiga.setCartao(despesaNova.getCartao());
			despesaAntiga.setCategoria(despesaNova.getCategoria());
			despesaAntiga.setData(despesaNova.getData());
			despesaAntiga.setDescricao(despesaNova.getDescricao());
			despesaAntiga.setValor(despesaNova.getValor());
		} else {
			throw new RuntimeException("Despesa não encontrada!");	
		}
	}

	public void excluir(Integer id) {
		Optional<Despesa> optionalDespesa = despesaRepository.findById(id);
		if (optionalDespesa.isPresent()) {
			despesaRepository.deleteById(id);
		} else {
			throw new RuntimeException("Despesa não encontrada para a exclusão!");
		}
	}
	
	private void validaDespesa(Despesa despesa) {
		if (despesa.getDescricao() == null || despesa.getDescricao().isEmpty()) {
			throw new RuntimeException("O campo descrição é de preenchimento obrigatório!");
		} else if(despesa.getDescricao().length() < 3){
			throw new RuntimeException("O campo descrição não pode ter menos que 3 caracteres!");		
		} else if (despesa.getDescricao().length() > 20) {
			throw new RuntimeException("A descrição não pode ter mais do que 20 caracteres!");
		}
		if (despesa.getValor() == null) {
			throw new RuntimeException("O campo valor é de preenchimento obrigatório!");
		} else if (despesa.getValor() <= 0) {
			throw new RuntimeException("Valor tem que ser maior que 0!");
		}
		if (despesa.getCartao() == null || despesa.getCartao().getId() == null || despesa.getCartao().getId() <= 0) {
			throw new RuntimeException("O campo cartão é de preenchimento obrigatório!");
		}
		if (despesa.getCategoria() == null || despesa.getCategoria().getId() == null || despesa.getCategoria().getId() <= 0) {
			throw new RuntimeException("O campo categoria é de preenchimento obrigatório!");
		}
		if (despesa.getData() == null) {
			throw new RuntimeException("A data é de preenchimento obrigatório!");
		}			
	}
	
}
