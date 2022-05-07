package controlefinanceiroservicos.acceptance.steps;

import static org.junit.jupiter.api.Assertions.assertTrue;

import controlefinanceiroservicos.e2e.CartaoPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;

public class CartaoSteps {
	
	private CartaoPage cartaoPage;
	
	@Before
	public void before() {
		this.cartaoPage = new CartaoPage();
	}

	@After
	public void after() {
		this.cartaoPage.fechar();
	}
	
	@Dado("O preenchimento dos dados corretamente")
	public void o_preenchimento_dos_dados_corretamente() {
		this.cartaoPage.novoCartao();
		this.cartaoPage.preencherFormulario("Selenium", "Visa", "4929877172804891", "1200");
	}

	@Quando("acionar o botao salvar")
	public void acionar_o_botao_salvar() throws InterruptedException {
		Thread.sleep(500);
		this.cartaoPage.salvarCartao();
	}

	@Entao("um alerta sera exibido")
	public void um_alerta_sera_exibido() {
		assertTrue(this.cartaoPage.cartaoCadastradoComSucesso());
	}
	
	@E("o usuario sera redirecionado para a grid") 
	public void o_usuario_sera_redirecionado_para_a_grid() {
		assertTrue(this.cartaoPage.isPaginaAtual());
	}

}
