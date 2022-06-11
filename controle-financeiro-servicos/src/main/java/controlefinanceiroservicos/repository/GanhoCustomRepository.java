package controlefinanceiroservicos.repository;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import controlefinanceiroservicos.model.Ganho;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Repository
public class GanhoCustomRepository {

	private final EntityManager em;
	
	public Page<Ganho> listar(String descricao, List<Integer> categorias, Date dataInicial, Date dataFinal, Pageable paginacao) {
		
		String query = "select g from Ganho g";
		String condicao = " where ";
		
		if (!(descricao == null || descricao.isEmpty())) {
			query += condicao + "upper(g.descricao) LIKE :descricao";
			condicao = " and ";
		}
		
		if (!(categorias == null || categorias.isEmpty())) {
			query += condicao + "g.categoria.id IN :categorias";
			condicao = " and ";
		}
		
		if (!(dataInicial == null)) {
			query += condicao + "g.data >= :dataInicial";
			condicao = " and ";
		}
		
		if (!(dataFinal == null)) {
			query += condicao + "g.data <= :dataFinal";
			condicao = " and ";
		}
		
		query += " order by data desc ";
		
		TypedQuery<Ganho> q = em.createQuery(query, Ganho.class);
		
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
		
		List<Ganho> ganhos = q.getResultList();
		
		int size = ganhos.size();
		
		int toIndex = paginacao.getPageNumber() + paginacao.getPageSize();
		
		if (toIndex > ganhos.size()) {
			toIndex = ganhos.size();
		}
		
		ganhos = ganhos.subList(paginacao.getPageNumber(), toIndex);
		
		Page<Ganho> pageGanho = new PageImpl<Ganho>( ganhos, paginacao, size);
		
		return pageGanho;
	}
}
