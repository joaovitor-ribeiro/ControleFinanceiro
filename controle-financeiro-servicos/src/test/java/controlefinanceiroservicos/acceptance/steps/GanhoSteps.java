package controlefinanceiroservicos.acceptance.steps;

import static org.junit.jupiter.api.Assertions.assertEquals;

import controlefinanceiroservicos.acceptance.page.GanhoPage;
import io.cucumber.java.After;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;

public class GanhoSteps {
	
	GanhoPage ganhoPage = new GanhoPage();
	
	//@After vai executar depois de cada teste.
	@After
	public void depois() {
		ganhoPage.sair();
	}
	
	@Dado("que estou logado na aplicação com o email {string} e a senha {string} para validar o ganho")
	public void estou_logado_na_aplicação_com_o_email_e_a_senha(String email, String senha) {
		ganhoPage.iniciarDriver();
		ganhoPage.realizarLogin(email, senha);
	}
	
	@Dado("que estou na página de cadastro de ganho")
	public void que_estou_na_página_de_cadastro_de_ganho() {
	    ganhoPage.navegaParaGanhoListar();
	    ganhoPage.clicoBotaoNovoGanho();
	}


	@Quando("preencho os campos descricao {string} categoria {string} valor {string} data {string}")
	public void preencho_os_campos_descricao_categorias_valor_data(String descricao, String categoria, String valor, String data) {
	   ganhoPage.preencherFormulario(descricao, categoria, valor, data);
	}
	
	@Quando("aciono o botão salvar do cadastro de ganho")
	public void aciono_o_botão_salvar_do_cadastro_de_ganho() {
		ganhoPage.acionoBotaoSalvar();
	   
	}
	@Entao("o usuário será redirecionado para a grid de ganho")
	public void o_usuário_será_redirecionado_para_a_grid_de_ganho() {
		ganhoPage.esperaAlert();
		assertEquals("http://localhost:4200/ganho/listar", ganhoPage.paginaAtual());
	}
	
	@Entao("será exibida a mensagem {string} de erro no cadastro de ganho")
	public void será_exibida_a_mensagem_de_erro_no_cadastro_de_ganho(String mensagem) {
	   assertEquals(mensagem, ganhoPage.mensagemDeErro());
	}
	
}
