package controlefinanceiroservicos.acceptance.steps;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import controlefinanceiroservicos.acceptance.page.CategoriaPage;
import io.cucumber.java.After;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;

public class CategoriaSteps {
	
	CategoriaPage categoriaPage = new CategoriaPage();
	
	@After
	public void fecharNavegador() {
		categoriaPage.fecharNavegador();
	}
	
	@Dado("que estou na página de cadastro de categoria")
	public void que_estou_na_página_de_cadastro_de_categoria() {
		categoriaPage.navegaParaCategoriaListar();
		categoriaPage.clicoBotaoNovaCategoria();
	}


	@Quando("O preencho os campos nome {string} tipo {string}")
	public void o_preencho_os_campos_nome_tipo(String nome, String tipo) {
		categoriaPage.preencherFormulario(nome, tipo);
	}
	
	@Quando("aciono o botao salvar do cadastro de categoria")
	public void aciono_o_botao_salvar_do_cadastro_de_categoria() {
		categoriaPage.acionoBotaoSalvar();
	}
	
	@Entao("será exibido a mensagem {string}")
	public void será_exibido_a_mensagem(String mensagem) {
		categoriaPage.esperaAlert();
		assertTrue(categoriaPage.alertaDeSucesso(mensagem));
	}
	
	@Entao("o usuário será redirecionado para a grid da categoria")
	public void o_usuário_será_redirecionado_para_a_grid_da_categoria() {
		assertEquals("http://localhost:4200/categoria/listar?tipo=T", categoriaPage.paginaAtual());
	}
}
