package controlefinanceiroservicos.controller;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import controlefinanceiroservicos.model.Ganho;
import controlefinanceiroservicos.service.GanhoService;

@RestController
@RequestMapping("ganho")
@CrossOrigin
public class GanhoController {
	
	@Autowired
	private GanhoService ganhoService;
	
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(method = RequestMethod.POST, path = "/inserir")
	public void inserir(@RequestBody Ganho ganho) throws Exception {
		ganhoService.inserir(ganho);
	}	
		
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.GET, path= "/listar")
	public Page<Ganho> listar(
			@RequestParam(required = false) String descricao, 
			@RequestParam(required = false) List<Integer> categorias,
			@RequestParam(required = false) Date dataInicial, 
			@RequestParam(required = false) Date dataFinal,
			@PageableDefault(sort = "data", direction = Direction.DESC, page = 0, size = 5) Pageable paginacao) {
		return ganhoService.listar(descricao, categorias, dataInicial, dataFinal, paginacao);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.PUT, path= "/editar/{id}")
	@Transactional
	@CrossOrigin
	public void editar(@PathVariable Integer id, @RequestBody Ganho ganhoNovo) throws Exception {
		ganhoService.editar(id, ganhoNovo);
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)	
	@RequestMapping(method = RequestMethod.DELETE, path =  "/excluir/{id}")
	public void excluir(@PathVariable Integer id) {
		ganhoService.excluir(id);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.GET, path = "/{id}")
	public Ganho retornarGanhoId(@PathVariable Integer id) {
		return ganhoService.retornarGanhoId(id);
	} 
}
