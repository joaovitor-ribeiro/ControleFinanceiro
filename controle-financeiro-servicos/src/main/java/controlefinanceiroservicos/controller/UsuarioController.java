package controlefinanceiroservicos.controller;

import java.io.IOException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import controlefinanceiroservicos.model.Usuario;
import controlefinanceiroservicos.service.UsuarioService;

@RestController
@RequestMapping("usuario")
@CrossOrigin
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(method = RequestMethod.POST, path = "/inserir")
	public Integer inserir(@RequestBody Usuario usuario) {
		return usuarioService.inserir(usuario);		
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(method = RequestMethod.POST, path = "/inserir/{id}")
	@Transactional
	public void inserirFoto(@RequestParam("file") MultipartFile foto, @PathVariable Integer id) throws IOException {
		usuarioService.inserirFoto(foto, id);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.GET, path = "/{id}")
	public Usuario retornarUsuarioId(@PathVariable Integer id) {
		return usuarioService.retornarUsuarioId(id);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.PUT, path = "/editar/{id}")
	@Transactional
	public void editar(@PathVariable Integer id, @RequestBody Usuario usuarioNovo) {
		usuarioService.editar(id, usuarioNovo);		
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(method = RequestMethod.DELETE, path = "/excluir/{id}")
	@Transactional
	public void excluir(@PathVariable Integer id) {
		usuarioService.excluir(id);
	}
	
}
