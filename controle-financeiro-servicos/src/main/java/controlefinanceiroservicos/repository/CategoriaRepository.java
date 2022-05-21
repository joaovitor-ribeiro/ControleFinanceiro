package controlefinanceiroservicos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import controlefinanceiroservicos.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
	List<Categoria> findByNome(String nome);
	
	List<Categoria> findByTipo(String tipo);
	
	@Query(value = "select * from categoria where nome ilike %?1% and tipo = ?2", nativeQuery = true)
	List<Categoria> findNomeAndTipo(String nome, String tipo);

}
