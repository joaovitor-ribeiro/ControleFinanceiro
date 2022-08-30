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
	public void cadastroDespesaSemParametroDescricao() {
		try {
			Despesa despesa = new Despesa();
			despesaService.inserir(despesa);
			fail("A Descrição não foi preenchida!");
		} catch (Exception e) {
			assertEquals("O campo descrição é de preenchimento obrigatório!", e.getMessage());
		}
	}
	
	@Test
	public void cadastroDespesaParametroDescricaoVazio() {
		try {
			Despesa despesa = new Despesa();
			despesa.setDescricao("");
			despesaService.inserir(despesa);
			fail("A Descrição não foi preenchida!");
		} catch (Exception e) {
			assertEquals("O campo descrição é de preenchimento obrigatório!", e.getMessage());
		}
	}
	
	@Test
	public void cadastroDespesaValidaTamanhoMinimoDescricao() {
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
	public void cadastroDespesaValidaTamanhoMaximoDescricao() {
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
	public void cadastroDespesaSemParametroValor() {
		try {
			Despesa despesa = new Despesa();
			despesa.setDescricao("Faculdade");
			despesaService.inserir(despesa);
			fail("O valor é de preenchimento obrigatório!");
		} catch (Exception e) {
			assertEquals("O campo valor é de preenchimento obrigatório!", e.getMessage());
		}
	}
	
	@Test
	public void cadastroDespesaValidaValorMaiorQueZero() {
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
	public void cadastroDespesaSemParametroCartao() {
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
	public void cadastroDespesaSemParametroIDCartao() {
		try {
			Despesa despesa = new Despesa();
			despesa.setDescricao("Faculdade");
			despesa.setValor(523.10);
			despesa.setCartao(new Cartao(null, null, null, null, null));
			despesaService.inserir(despesa);
			fail("O cartão não foi preenchido!");
		} catch (Exception e) {
			assertEquals("O campo cartão é de preenchimento obrigatório!", e.getMessage());
		}
	}
	
	@Test
	public void cadastroDespesaIDCartaoMenorIgualAZero() {
		try {
			Despesa despesa = new Despesa();
			despesa.setDescricao("Faculdade");
			despesa.setValor(523.10);
			despesa.setCartao(new Cartao(-1, null, null, null, null));
			despesaService.inserir(despesa);
			fail("O cartão não foi preenchido!");
		} catch (Exception e) {
			assertEquals("O campo cartão é de preenchimento obrigatório!", e.getMessage());
		}
	}
	
	@Test
	public void cadastroDespesaSemParametroCategoria() {
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
	public void cadastroDespesaSemParametroIDCategoria() {
		try {
			Despesa despesa = new Despesa();
			despesa.setDescricao("Faculdade");
			despesa.setValor(523.10);
			despesa.setCartao(new Cartao(2, null, null, null, null));
			despesa.setCategoria(new Categoria(null, null, null));
			despesaService.inserir(despesa);
			fail("A categoria não foi preenchida!");
		} catch (Exception e) {
			assertEquals("O campo categoria é de preenchimento obrigatório!", e.getMessage());
		}
	}
	
	@Test
	public void cadastroDespesaIDCategoriaMenorIgualAZero() {
		try {
			Despesa despesa = new Despesa();
			despesa.setDescricao("Faculdade");
			despesa.setValor(523.10);
			despesa.setCartao(new Cartao(2, null, null, null, null));
			despesa.setCategoria(new Categoria(-1, null, null));
			despesaService.inserir(despesa);
			fail("A categoria não foi preenchida!");
		} catch (Exception e) {
			assertEquals("O campo categoria é de preenchimento obrigatório!", e.getMessage());
		}
	}
	
	@Test
	public void cadastroDespesaSemParametroData() {
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
