package controlefinanceiroservicos.e2e;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CartaoTest {
	
	private CartaoPage cartaoPage;
	
	@BeforeEach()
	private void beforeEach() {
		this.cartaoPage = new CartaoPage();
	}
	
	@AfterEach()
	private void afterEach() {
		this.cartaoPage.fechar();
	}
	
	@Test
	public void deveriaCadastrarComDadosValidos() throws InterruptedException {
		this.cartaoPage.novoCartao();
		this.cartaoPage.preencherFormulario("Selenium", "Visa", "4929877172804891", "1200");
		assertTrue(this.cartaoPage.cartaoCadastradoComSucesso(), "alert");
		assertTrue(this.cartaoPage.isPaginaAtual(), "troca de URL");
	}
		
}
