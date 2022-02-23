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
public class Categoria {
	@Id @GeneratedValue (strategy = GenerationType.IDENTITY)/*@Id indica para a JPA o identificador da tabela categoria 
	@GeneratedValue gera um valor sequêncial único sem repetição*/
	private Integer id;
	@Column (length = 20, nullable = false)
	private String nome;
	@Column(length = 1, nullable = false)
	private String tipo;
}
