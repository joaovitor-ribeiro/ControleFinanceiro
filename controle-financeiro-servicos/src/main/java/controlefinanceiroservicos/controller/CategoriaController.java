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

import controlefinanceiroservicos.model.Categoria;
import controlefinanceiroservicos.service.CategoriaService;

@RestController
@RequestMapping ("categoria")
@CrossOrigin
public class CategoriaController {
	
	@Autowired
	private CategoriaService categoriaService;

	@ResponseStatus (HttpStatus.CREATED)
	@RequestMapping (method = RequestMethod.POST, path = "/inserir") 
	public void inserir(@RequestBody Categoria categoria)  {
		categoriaService.inserir(categoria);
	}
	@ResponseStatus (HttpStatus.OK)
	@RequestMapping (method = RequestMethod.GET, path = "/listar")
	public List<Categoria> listar(){
		return categoriaService.listar();
	}
	@ResponseStatus (HttpStatus.OK)
	@Transactional
	@RequestMapping (method = RequestMethod.PUT, path = "/editar/{id}")
	public void editar(@RequestBody Categoria categoria,@PathVariable Integer id){
		categoriaService.editar(categoria, id);
	}
}
