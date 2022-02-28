package controlefinanceiroservicos.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import controlefinanceiroservicos.model.Cartao;
import controlefinanceiroservicos.service.CartaoService;

@RestController
@RequestMapping ("cartao")
@CrossOrigin
public class CartaoController {
	
	@Autowired
	private CartaoService cartaoService;
	
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(method = RequestMethod.POST, path = "/inserir")	
	public void inserir(@RequestBody Cartao cartao){ //@RequestBody: Indica que as informações vão vir no corpo da requisição
		cartaoService.inserir(cartao);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.GET, path = "/listar")
	public List<Cartao> listar() {
		return cartaoService.listar();
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.GET, path = "/{id}")
	public Cartao retornarCartaoId(@PathVariable Integer id){
		return cartaoService.retornarCartaoId(id);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.PUT, path = "/editar/{id}")
	@Transactional
	@CrossOrigin
	public void editar(@PathVariable Integer id, @RequestBody Cartao cartaoNovo){
		cartaoService.editar(id, cartaoNovo);
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(method = RequestMethod.DELETE, path = "/excluir/{id}")
	@Transactional
	public void excluir(@PathVariable Integer id){
		cartaoService.excluir(id);
	}
}
