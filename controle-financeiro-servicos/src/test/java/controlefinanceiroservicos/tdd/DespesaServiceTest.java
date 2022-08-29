package controlefinanceiroservicos.tdd;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import controlefinanceiroservicos.model.Cartao;
import controlefinanceiroservicos.model.Categoria;
import controlefinanceiroservicos.model.Despesa;
import controlefinanceiroservicos.service.DespesaService;

public class DespesaServiceTest {
	
	DespesaService despesaService = new DespesaService();
	
	@Test
	public void ValidaPreenchimentoObrigatorioDescricao() {
		try {
			Despesa despesa = new Despesa();
			despesaService.inserir(despesa);
			fail("A Descrição não foi preenchida!");
		} catch (Exception e) {
			assertEquals("O campo descrição é de preenchimento obrigatório!", e.getMessage());
		}
	}
	
	@Test
	public void VerificaTamanhoMinimoDescricao() {
		try {
			Despesa despesa = new Despesa();
			despesa.setDescricao("Fa");
			despesaService.inserir(despesa);
			fail("O tamanho da descrição não possui menos de 3 caracteres.");
		} catch (Exception e) {
			assertEquals("O campo descrição não pode ter menos que 3 caracteres!", e.getMessage());
		}
	}
	
	@Test
	public void VerificaTamanhoMaximoDescricao() {
		try {
			Despesa despesa = new Despesa();
			despesa.setDescricao("Faculdadepagamentomensal");
			despesaService.inserir(despesa);
			fail("O tamanho da descrição possui menos de 20 caracteres");
		} catch (Exception e) {
			assertEquals("A descrição não pode ter mais do que 20 caracteres!", e.getMessage());
		}
	}
	
	@Test
	public void validaPreenchimentoObrigatorioValor() {
		try {
			Despesa despesa = new Despesa();
			despesa.setDescricao("Faculdade");
			despesa.setValor(null);
			despesaService.inserir(despesa);
			fail("O valor é de preenchimento obrigatório!");
		} catch (Exception e) {
			assertEquals("O campo valor é de preenchimento obrigatório!", e.getMessage());
		}
	}
	
	@Test
	public void ValorMaiorZero() {
		try {
			Despesa despesa = new Despesa();
			despesa.setDescricao("Faculdade");
			despesa.setValor(-523.10);
			despesaService.inserir(despesa);
			fail("O valor informado não foi maior que zero!");
		} catch (Exception e) {
			assertEquals("Valor tem que ser maior que 0!", e.getMessage());
		}
	}
	
	@Test
	public void validaPreenchimentoObrigatorioCartao() {
		try {
			Despesa despesa = new Despesa();
			despesa.setDescricao("Faculdade");
			despesa.setValor(523.10);
			despesaService.inserir(despesa);
			fail("O cartão não foi preenchido!");
		} catch (Exception e) {
			assertEquals("O campo cartão é de preenchimento obrigatório!", e.getMessage());
		}
	}
	
	@Test
	public void validaPreenchimentoObrigatorioCategoria() {
		try {
			Despesa despesa = new Despesa();
			despesa.setDescricao("Faculdade");
			despesa.setValor(523.10);
			despesa.setCartao(new Cartao(2, null, null, null, null));
			despesaService.inserir(despesa);
			fail("A categoria não foi preenchida!");
		} catch (Exception e) {
			assertEquals("O campo categoria é de preenchimento obrigatório!", e.getMessage());
		}
	}
	
	@Test
	public void validaPreenchimentoObrigatorioData() {
		try {
			Despesa despesa = new Despesa();
			despesa.setDescricao("Faculdade");
			despesa.setValor(523.10);
			despesa.setCartao(new Cartao(2, null, null, null, null));
			despesa.setCategoria(new Categoria(2, null, null));
			despesaService.inserir(despesa);
			fail("A data não foi preenchida");
		} catch (Exception e) {
			assertEquals("A data é de preenchimento obrigatório!", e.getMessage());
		}
	}

}
