package controlefinanceiroservicos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import controlefinanceiroservicos.model.Categoria;
import controlefinanceiroservicos.model.Despesa;
import controlefinanceiroservicos.repository.CategoriaRepository;
import controlefinanceiroservicos.repository.DespesaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private DespesaRepository despesaRepository;
	
	public void inserir(Categoria categoria) {
		validacoes(categoria);
		categoriaRepository.save(categoria);
	}

	private void validacoes(Categoria categoria) {
		if (categoria.getNome().isEmpty()) {
			throw new RuntimeException("O campo nome é de preenchimento obrigatório!");
		} else if(categoria.getNome().length() < 3){
			throw new RuntimeException("O nome não pode ter menos que 3 caracteres!");
		} else if(categoria.getNome().length() > 20){
			throw new RuntimeException("O nome não pode ter mais que 20 caracteres!");
		} 
		if (categoria.getTipo().isEmpty()) {
			throw new RuntimeException("O campo tipo é de preenchimento obrigatório!");
		} else if(categoria.getTipo().length() > 1){
			throw new RuntimeException("O tipo tem que ter um caracter. Informe G para ganho ou D para despesa!");
		} else if(!categoria.getTipo().equals("G") && !categoria.getTipo().equals("D")){
			throw new RuntimeException("Informe G para ganho ou D para despesa!");
		} 
	}

	public List<Categoria> listar(String nome, String tipo) {
		if (!(nome == null || nome.isEmpty()) && !(tipo == null || tipo.isEmpty() || tipo.equals("T"))) {
			return categoriaRepository.findNomeAndTipo(nome, tipo);
		} else if (!(nome == null || nome.isEmpty())) {
			return categoriaRepository.findByNome(nome);
		} else if (!(tipo == null || tipo.isEmpty() || tipo.equals("T"))) {
			return categoriaRepository.findByTipo(tipo);
		}
		return categoriaRepository.findAll();
	}

	public void editar(Categoria categoria, Integer id) {		
		Optional<Categoria> optionCategoria= categoriaRepository.findById(id);
		if (optionCategoria.isPresent())  { 
			validacoes(categoria);
			Categoria categoriaDadosAntigos = optionCategoria.get();
			categoriaDadosAntigos.setNome(categoria.getNome());
			categoriaDadosAntigos.setTipo(categoria.getTipo());
		} else {
			throw new RuntimeException("Categoria não foi encontrada para edição!");
		}
		
	}

	public void excluir(Integer id) {
		Optional<Categoria> optionCategoria = categoriaRepository.findById(id);
		if (optionCategoria.isPresent()) {
			Categoria categoria = optionCategoria.get();
			if (categoria.getTipo().equals("D")) {
				Optional<Despesa> optionalDespesa = despesaRepository.findDespesaByIdCategoria(id);
				if (optionalDespesa.isPresent()) {
					Despesa despesa = optionalDespesa.get();
					throw new RuntimeException("Essa categoria não pode ser excluída pois está vinculada a despesa: " + despesa.getDescricao() + "!");
				}
			} else {
				//TODO Leid faz a boa
			}
			categoriaRepository.deleteById(id);
		} else {
			throw new RuntimeException("Categoria não foi encontrada para exclusão!");
		}
	}

	public Categoria retornarCategoriaId(Integer id) {
		Optional<Categoria> optionCategoria = categoriaRepository.findById(id);
		if (optionCategoria.isPresent()) {
			return optionCategoria.get();
		}
		throw new RuntimeException("Categoria não encontrada!");
	}
	
}
