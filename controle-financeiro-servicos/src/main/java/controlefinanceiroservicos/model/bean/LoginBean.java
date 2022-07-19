package controlefinanceiroservicos.model.bean;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LoginBean {
	
	private String email;
	private String senha;
	
	public UsernamePasswordAuthenticationToken converter() {
		return new UsernamePasswordAuthenticationToken(email, senha);
	}
}
