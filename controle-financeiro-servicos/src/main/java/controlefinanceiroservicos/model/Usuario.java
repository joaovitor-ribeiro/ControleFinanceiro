package controlefinanceiroservicos.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
	@Id @GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column (length = 50, nullable = false)
	private String nome;
	@Column (length = 11, nullable = false)
	private String cpf;
	@Column (length = 50, nullable = false)
	private String email;
	@Column (length = 100, nullable = false)
	private String senha;	
	private byte[] foto;  

}
