package controlefinanceiroservicos.integration;

import static org.junit.Assert.fail;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import controlefinanceiroservicos.model.Usuario;
import controlefinanceiroservicos.repository.UsuarioRepository;

@DataJpaTest
public class UsuarioRepositoryTest {
	
	@Autowired
	UsuarioRepository usuarioRepositry;
	
	@Test
	public void deveCarregarUmUsuarioPeloEmail() {
		try {
			Usuario usuarioEsperado  = new Usuario(1, "Usuário", "51027504027", "usuario@gmail.com", null, null, null);
			Usuario usuarioCarregado = usuarioRepositry.findByEmail("usuario@gmail.com").get();
			Assertions.assertEquals(usuarioEsperado, usuarioCarregado);
		} catch (Exception e) {
			fail();
		}
	}

}
