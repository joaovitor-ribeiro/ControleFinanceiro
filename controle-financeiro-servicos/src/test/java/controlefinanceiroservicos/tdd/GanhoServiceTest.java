package controlefinanceiroservicos.tdd;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import controlefinanceiroservicos.model.Categoria;
import controlefinanceiroservicos.model.Ganho;
import controlefinanceiroservicos.service.GanhoService;

public class GanhoServiceTest {
	
	private GanhoService ganhoService = new GanhoService();
	
	@Test
	public void cadastroGanhoSemParametroDescricao() {
		try {
			Ganho ganho = new Ganho();
			ganhoService.inserir(ganho);
			fail("A Descrição não foi preenchida!");
		} catch (Exception e) {
			assertEquals("O campo descrição é de preenchimento obrigatório!", e.getMessage());
		}
	}
	
	@Test
	public void cadastroGanhoParametroDescricaoVazio() {
		try {
			Ganho ganho = new Ganho();
			ganho.setDescricao("");
			ganhoService.inserir(ganho);
			fail("A Descrição não foi preenchida!");
		} catch (Exception e) {
			assertEquals("O campo descrição é de preenchimento obrigatório!", e.getMessage());
		}
	}
	
	@Test
	public void cadastroGanhoValidaTamanhoMinimoDescricao() {
		try {
			Ganho ganho = new Ganho();
			ganho.setDescricao("Fa");
			ganhoService.inserir(ganho);
			fail("O tamanho da descrição não possui menos de 3 caracteres.");
		} catch (Exception e) {
			assertEquals("O campo descrição não pode ter menos que 3 caracteres!", e.getMessage());
		}
	}
	
	@Test
	public void cadastroGanhoValidaTamanhoMaximoDescricao() {
		try {
			Ganho ganho = new Ganho();
			ganho.setDescricao("Faaaaaaaaaaaaaaaaaaaaa");
			ganhoService.inserir(ganho);
			fail("O tamanho da descrição possui menos de 20 caracteres.");
		} catch (Exception e) {
			assertEquals("O campo descrição não pode ter mais que 20 caracteres!", e.getMessage());
		}
	}
	
	@Test
	public void cadastroGanhoSemParametroCategoria() {
		try {
			Ganho ganho = new Ganho();
			ganho.setDescricao("Faculdade");
			ganhoService.inserir(ganho);
			fail("A categoria foi preenchida!");
		} catch (Exception e) {
			assertEquals("O campo categoria é de preenchimento obrigatório!", e.getMessage());
		}
	}
	
	@Test
	public void cadastroGanhoSemParametroIDCategoria() {
		try {
			Ganho ganho = new Ganho();
			ganho.setDescricao("Faculdade");
			ganho.setCategoria(new Categoria(null, null, null));
			ganhoService.inserir(ganho);
			fail("A categoria foi preenchida!");
		} catch (Exception e) {
			assertEquals("O campo categoria é de preenchimento obrigatório!", e.getMessage());
		}
	}
	
	@Test
	public void cadastroGanhoCategoriaMenorIgualAZero() {
		try {
			Ganho ganho = new Ganho();
			ganho.setDescricao("Faculdade");
			ganho.setCategoria(new Categoria(-1, null, null));
			ganhoService.inserir(ganho);
			fail("A categoria foi preenchida!");
		} catch (Exception e) {
			assertEquals("O campo categoria é de preenchimento obrigatório!", e.getMessage());
		}
	}
	
	@Test
	public void cadastroGanhoSemParametroValor() {
		try {
			Ganho ganho = new Ganho();
			ganho.setDescricao("Faculdade");
			ganho.setCategoria(new Categoria(2, null, null));			
			ganhoService.inserir(ganho);
			fail("O valor é de preenchimento obrigatório!");
		} catch (Exception e) {
			assertEquals("O campo valor é de preenchimento obrigatório!", e.getMessage());
		}
	}
	
	@Test
	public void cadastroGanhoValidaValorMaiorQueZero() {
		try {
			Ganho ganho = new Ganho();
			ganho.setDescricao("Faculdade");
			ganho.setCategoria(new Categoria(2, null, null));
			ganho.setValor(-10.1);
			ganhoService.inserir(ganho);
			fail("O valor informado não foi maior que zero!");
		} catch (Exception e) {
			assertEquals("O valor não pode ser menor ou igual a zero!", e.getMessage());
		}
	}
	
	@Test
	public void cadastroGanhoSemParametroData() {
		try {
			Ganho ganho = new Ganho();
			ganho.setDescricao("Faculdade");
			ganho.setCategoria(new Categoria(2, null, null));
			ganho.setValor(10.1);			
			ganhoService.inserir(ganho);
			fail("A data não foi preenchida!");
		} catch (Exception e) {
			assertEquals("A data é de preenchimento obrigatório!", e.getMessage());
		}
	}
	
}
