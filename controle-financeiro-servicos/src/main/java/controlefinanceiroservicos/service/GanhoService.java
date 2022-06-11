package controlefinanceiroservicos.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import controlefinanceiroservicos.model.Ganho;
import controlefinanceiroservicos.repository.GanhoCustomRepository;
import controlefinanceiroservicos.repository.GanhoRepository;

@Service
public class GanhoService {

	@Autowired
	private GanhoRepository ganhoRepository;
	@Autowired
	private GanhoCustomRepository ganhoCustomRepository;
	
	public void inserir(Ganho ganho) {			
		validacoes(ganho);
		ganho.setValor(Double.parseDouble(ganho.getValor().toString().replace("/[^0-9]/g", "")));
		ganhoRepository.save(ganho);
	}

	public void editar(Integer id, Ganho ganhoNovo) {
		Optional<Ganho> optionGanhoAntigo = ganhoRepository.findById(id);
		
		if (optionGanhoAntigo.isPresent()) {			
			Ganho ganhoAntigo = optionGanhoAntigo.get();
			validacoes(ganhoNovo);
			ganhoAntigo.setCategoria(ganhoNovo.getCategoria());
			ganhoAntigo.setData(ganhoNovo.getData());
			ganhoAntigo.setDescricao(ganhoNovo.getDescricao());
			ganhoAntigo.setValor(ganhoNovo.getValor());
		}else {
			throw new RuntimeException("Ganho não foi encontrado para edição!");
		}
	}

	public void excluir(Integer id) {
		Optional<Ganho> optionGanho = ganhoRepository.findById(id);
		if (optionGanho.isPresent()) {
			ganhoRepository.deleteById(id);
		} else {
			throw new RuntimeException("Ganho não foi encontrado para exclusão!");
		}
	}

	public Ganho retornarGanhoId(Integer id) {
		Optional<Ganho> optionGanho = ganhoRepository.findById(id);
		if (optionGanho.isPresent()) {
			return optionGanho.get();
		}
		throw new RuntimeException("Ganho não encontrado!");
	}
	
	private void validacoes(Ganho ganho) {
		if (ganho.getDescricao().isEmpty()) {
			throw new RuntimeException("O campo descrição é de preenchimento obrigatório!");
		} else if(ganho.getDescricao().length() < 3){
			throw new RuntimeException("O campo descrição não pode ter menos que 3 caracteres!");
		} else if(ganho.getDescricao().length() > 20){
			throw new RuntimeException("O campo descrição não pode ter mais que 20 caracteres!");
		}
		
		//TODO: Precisa validar se a categoria existe no BD?	
		if (ganho.getCategoria().getId() == null && ganho.getCategoria().getId() <= 0) {
			throw new RuntimeException("O campo categoria é de preenchimento obrigatório!");
		}		
		
		if (ganho.getValor() <= 0) {
			throw new RuntimeException("O valor não pode ser menor ou igual a zero!");		
		}
		
		if (ganho.getData() == null) {
			throw new RuntimeException("A data é de preenchimento obrigatório!");
		}		
	}

	public Page<Ganho> listar(String descricao, List<Integer> categorias, Date dataInicial, Date dataFinal, Pageable paginacao) {
		if ( (descricao == null || descricao.isEmpty()) && (categorias == null || categorias.isEmpty()) && dataInicial == null && dataFinal == null )  {
			return ganhoRepository.findAll(paginacao);
		}
		return ganhoCustomRepository.listar(descricao, categorias, dataInicial, dataFinal, paginacao);				
	}
}
