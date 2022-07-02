package controlefinanceiroservicos.acceptance.steps;

import static org.junit.jupiter.api.Assertions.assertEquals;

import controlefinanceiroservicos.acceptance.page.DespesaPage;
import io.cucumber.java.After;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;

public class DespesaSteps {
	
	DespesaPage despesaPage = new DespesaPage();
	
	@After
	public void depois() {
		despesaPage.sair();
	}
	
	@Dado("que estou na página de cadastro de despesa")
	public void que_estou_na_página_de_cadastro_de_despesa() {
		despesaPage.iniciarDriver();
	    despesaPage.navegaParaDespesaListar();
	    despesaPage.clicoBotaoNovaDespesa();
	}

	@Quando("preencho os campos cartao {string} descricao {string} categoria {string} valor {string} data {string}")
	public void preencho_os_campos_cartao_descricao_categorias_valor_data(String cartao, String descricao, String categoria, String valor, String data) {
		despesaPage.preencherFormulario(cartao, descricao, categoria, valor, data);
	}
	
	@Quando("aciono o botão salvar do cadastro de despesa")
	public void aciono_o_botão_salvar_do_cadastro_de_despesa() {
		despesaPage.acionoBotaoSalvar();
	}
	
	@Entao("o usuário será redirecionado para a grid de despesa")
	public void o_usuário_será_redirecionado_para_a_grid_de_despesa() {
		despesaPage.esperaAlert();
		assertEquals("http://localhost:4200/despesa/listar", despesaPage.paginaAtual());
	}
	
	@Entao("será exibida a mensagem {string} de erro no cadastro de despesa")
	public void será_exibida_a_mensagem_de_erro_no_cadastro_de_despesa(String mensagem) {
		assertEquals(mensagem, despesaPage.mensagemDeErro());
	}

}
