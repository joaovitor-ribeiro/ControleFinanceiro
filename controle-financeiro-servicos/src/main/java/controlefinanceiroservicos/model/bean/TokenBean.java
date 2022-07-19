package controlefinanceiroservicos.model.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter 
@Getter 
public class TokenBean {
	
	private String token;
	private String tipo;
	private Integer  id;
	private String nome;
	private byte[] foto;

}
