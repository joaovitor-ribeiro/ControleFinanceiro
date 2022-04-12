package controlefinanceiroservicos.repository;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import controlefinanceiroservicos.model.Despesa;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Repository
public class DespesaCustomRepository {
	
	private final EntityManager em;
	
	public Page<Despesa> listar(String descricao, List<Integer> categorias, Date dataInicial, Date dataFinal, Pageable paginacao) {
		
		String query = "select d from Despesa d";
		String condicao = " where ";
		
		if (!(descricao == null || descricao.isEmpty())) {
			query += condicao + "upper(d.descricao) LIKE :descricao";
			condicao = " and ";
		}
		
		if (!(categorias == null || categorias.isEmpty())) {
			query += condicao + "d.categoria.id IN :categorias";
			condicao = " and ";
		}
		
		if (!(dataInicial == null)) {
			query += condicao + "d.data >= :dataInicial";
			condicao = " and ";
		}
		
		if (!(dataFinal == null)) {
			query += condicao + "d.data <= :dataFinal";
			condicao = " and ";
		}
		
		query += " order by data desc ";
		
		TypedQuery<Despesa> q = em.createQuery(query, Despesa.class);
		
		if (!(descricao == null || descricao.isEmpty())) {
			q.setParameter("descricao", "%" + descricao.toUpperCase() + "%");
		}
		
		if (!(categorias == null || categorias.isEmpty())) {
			q.setParameter("categorias",  categorias);
		}
		
		if (!(dataInicial == null)) {
			q.setParameter("dataInicial", dataInicial);
		}
		
		if (!(dataFinal == null)) {
			q.setParameter("dataFinal", dataFinal);
		}
		
		List<Despesa> despesas = q.getResultList();
		
		int size = despesas.size();
		
		int toIndex = paginacao.getPageNumber() + paginacao.getPageSize();
		
		if (toIndex > despesas.size()) {
			toIndex = despesas.size();
		}
		
		despesas = despesas.subList(paginacao.getPageNumber(), toIndex);
		
		Page<Despesa> pageDespesa = new PageImpl<Despesa>( despesas, paginacao, size);
		
		return pageDespesa;
	}

}
