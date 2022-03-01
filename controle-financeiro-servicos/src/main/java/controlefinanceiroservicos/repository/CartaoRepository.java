package controlefinanceiroservicos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import controlefinanceiroservicos.model.Cartao;

public interface CartaoRepository extends JpaRepository<Cartao, Integer>{
	//Só será utilizado se for ter algum select especial, por exemplo, um where com duas condições
	List<Cartao> findByNome(String nome);
	@Query(value = "select * from cartao where bandeira in (:bandeiras)", nativeQuery = true)
	List<Cartao> findByBandeiras(List<String> bandeiras);
	@Query(value = "select * from cartao where nome ilike %?1% and bandeira in (?2)", nativeQuery = true)
	List<Cartao> findNomeAndBandeiras(String nome, List<String> bandeiras);
}
