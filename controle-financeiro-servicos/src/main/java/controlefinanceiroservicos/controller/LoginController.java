package controlefinanceiroservicos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import controlefinanceiroservicos.config.security.TokenService;
import controlefinanceiroservicos.model.Usuario;
import controlefinanceiroservicos.model.bean.LoginBean;
import controlefinanceiroservicos.model.bean.TokenBean;
import controlefinanceiroservicos.repository.UsuarioRepository;

@RestController
@RequestMapping("/autenticacao")
@CrossOrigin
public class LoginController {
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UsuarioRepository repository;

	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<TokenBean> autenticar(@RequestBody LoginBean form) {
		UsernamePasswordAuthenticationToken dadosLogin = form.converter();
		try {
			Authentication authentication = authManager.authenticate(dadosLogin);
			String token = tokenService.gerarToken(authentication);
			Usuario usuario = repository.findByEmail(form.getEmail()).get();
			return ResponseEntity.ok(new TokenBean(token, "Bearer", usuario.getId(), usuario.getNome().trim(), usuario.getFoto()));
		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}
	}

}
