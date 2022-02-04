package controlefinanceiroservicos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import controlefinanceiroservicos.model.Cartao;

public interface CartaoRepository extends JpaRepository<Cartao, Integer>{
	//Só será utilizado se for ter algum select especial, por exemplo, um where com duas condições
}
