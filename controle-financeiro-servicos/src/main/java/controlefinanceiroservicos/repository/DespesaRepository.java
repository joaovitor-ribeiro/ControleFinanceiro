package controlefinanceiroservicos.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import controlefinanceiroservicos.model.Despesa;

public interface DespesaRepository extends JpaRepository<Despesa, Integer>{
	@Query(value = ""
			+ "select * from despesa "
			+ "where (:descricao is null or descricao ilike %:descricao%) "
			+ "and "
			+ "(:categorias is null or categoria_id in (:categorias)) "
			+ "and "
			+ ":dataInicial is null or data >= :dataInicial "
			+ "and "
			+ ":dataFinal is null or data <= :dataFinal", nativeQuery = true)
	Page<Despesa> find(String descricao, List<Integer> categorias, Date dataInicial, Date dataFinal, Pageable paginacao);
}
