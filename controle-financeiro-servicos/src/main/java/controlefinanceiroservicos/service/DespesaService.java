package controlefinanceiroservicos.service;

import java.util.Arrays;
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
import controlefinanceiroservicos.utils.ValidUtils;

@Service
public class DespesaService {
	
	@Autowired
	private DespesaRepository despesaRepository; 
	
	@Autowired
	private DespesaCustomRepository despesaCustomRepository; 
	
	private ValidUtils valid = new ValidUtils(); 
	
	public void inserir(Despesa despesa) throws Exception {
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

	public void editar(Integer id, Despesa despesaNova) throws Exception {
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
	
	private void validaDespesa(Despesa despesa) throws Exception {
		valid.validaObject(despesa, Arrays.asList("descrição", "valor", "data"), 3, 20);
		
		if (despesa.getCartao() == null || despesa.getCartao().getId() == null || despesa.getCartao().getId() <= 0) {
			throw new RuntimeException("O campo cartão é de preenchimento obrigatório!");
		}
		
		if (despesa.getCategoria() == null || despesa.getCategoria().getId() == null || despesa.getCategoria().getId() <= 0) {
			throw new RuntimeException("O campo categoria é de preenchimento obrigatório!");
		}
		
	}
	
}
