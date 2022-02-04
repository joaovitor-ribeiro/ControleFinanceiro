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
@AllArgsConstructor //Construtor com tudo
@NoArgsConstructor //Construtor vazio
public class Cartao {
	@Id @GeneratedValue (strategy = GenerationType.IDENTITY) //Indicando que é a PK
	private Integer id;
	@Column (length = 20, nullable = false)
	private String nome;
	@Column (length = 20, nullable = false)
	private String bandeira;
	@Column (length = 16, nullable = false)
	private String numero;
	@Column (nullable = false)
	private Double limite;	
}
