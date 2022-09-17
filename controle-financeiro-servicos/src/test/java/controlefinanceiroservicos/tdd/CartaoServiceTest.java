package controlefinanceiroservicos.tdd;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import controlefinanceiroservicos.model.Cartao;
import controlefinanceiroservicos.service.CartaoService;

public class CartaoServiceTest {
	
	private CartaoService cartaoService = new CartaoService(); 
	
	@Test
	public void cadastroCartaoSemParametroNome() {
		try {
			Cartao cartaoNome = new Cartao();
			cartaoService.inserir(cartaoNome);
			fail("Não validou a obrigatoriedade no preenchimento do Nome!");
		} catch (Exception e) {
			assertEquals("O campo nome é de preenchimento obrigatório!", e.getMessage());
		}
	}
	
	@Test
	public void cadastroCartaoParametroNomeVazio() {
		try {
			Cartao cartaoNome = new Cartao();
			cartaoNome.setNome("");
			cartaoService.inserir(cartaoNome);
			fail("Não validou a obrigatoriedade no preenchimento do Nome!");
		} catch (Exception e) {
			assertEquals("O campo nome é de preenchimento obrigatório!", e.getMessage());
		}
	}
	
	@Test
	public void cadastroCartaoValidaTamanhoMinimoNome() {
		try {
			Cartao cartaoTamanhoNome = new Cartao();
			cartaoTamanhoNome.setNome("Ma");
			cartaoService.inserir(cartaoTamanhoNome);
			fail("Não validou o tamanho minimo para o preenchimento do Nome!");
		} catch (Exception e) {
			assertEquals("O campo nome não pode ter menos do que 3 caracteres!", e.getMessage());
		}
	}
	
	@Test
	public void cadastroCartaoValidaTamanhoMaximoNome() {
		try {
			Cartao cartaoTamanhoNome = new Cartao();
			cartaoTamanhoNome.setNome("MastercardCieloNubank");
			cartaoService.inserir(cartaoTamanhoNome);
			fail("Não validou o tamanho maximo para o preenchimento do Nome!");
		} catch (Exception e) {
			assertEquals("O campo nome não pode ter mais do que 20 caracteres!", e.getMessage());
		}
	}
	
	@Test
	public void cadastroCartaoSemParametroBandeira() {
		try {
			Cartao cartaoBandeira = new Cartao();
			cartaoBandeira.setNome("Nubank");
			cartaoService.inserir(cartaoBandeira);
			fail("Não validou a obrigatoriedade no preenchimento da Bandeira!");
			
		} catch (Exception e) {
			assertEquals("O campo bandeira é de preenchimento obrigatório!", e.getMessage());
		}
	}
	
	@Test
	public void cadastroCartaoParametroBandeiraVazio() {
		try {
			Cartao cartaoBandeira = new Cartao();
			cartaoBandeira.setNome("Nubank");
			cartaoBandeira.setBandeira("");
			cartaoService.inserir(cartaoBandeira);
			fail("Não validou a obrigatoriedade no preenchimento da Bandeira!");
			
		} catch (Exception e) {
			assertEquals("O campo bandeira é de preenchimento obrigatório!", e.getMessage());
		}
	}
	
	@Test
	public void cadastroCartaoValidaTamanhoMinimoBandeira() {
		try {
			Cartao cartaoTamanho = new Cartao();
			cartaoTamanho.setNome("Nubank");
			cartaoTamanho.setBandeira("Ma");
			cartaoService.inserir(cartaoTamanho);
			fail("Não validou o tamanho minimo para o preenchimento da Bandeira!");
		} catch (Exception e) {
			assertEquals("O campo bandeira não pode ter menos do que 3 caracteres!", e.getMessage());
		}
	}
	
	@Test
	public void cadastroCartaoValidaTamanhoMaximoBandeira() {
		try {
			Cartao cartaoTamanho = new Cartao();
			cartaoTamanho.setNome("Nubank");
			cartaoTamanho.setBandeira("MastercardNubankCielo");
			cartaoService.inserir(cartaoTamanho);
			fail("Não validou o tamanho maximo para o preenchimento da Bandeira!");
		} catch (Exception e) {
			assertEquals("O campo bandeira não pode ter mais do que 20 caracteres!", e.getMessage());
		}
	}
	
	@Test
	public void cadastroCartaoSemParametroNumero() {
		//try - tentando executar as linhas dentro do seu bloco
		try {
			Cartao cartaoNumero = new Cartao();
			//set - definir
			cartaoNumero.setNome("Nubank");
			cartaoNumero.setBandeira("Mastercard");
			cartaoNumero.setLimite(10.00);
			cartaoService.inserir(cartaoNumero);
			//fail - falhou
			fail("Não validou a obrigatoriedade no preenchimento do Número!");
			//catch - pegar
		} catch (Exception e) {
			assertEquals("O campo número é de preenchimento obrigatório!", e.getMessage());
		}
	} 
	
	@Test
	public void cadastroCartaoParametroNumeroVazio() {
		try {
			Cartao cartaoNumero = new Cartao();
			cartaoNumero.setNome("Nubank");
			cartaoNumero.setBandeira("Mastercard"); 
			cartaoNumero.setLimite(10.00); 
			cartaoNumero.setNumero(""); 
			cartaoService.inserir(cartaoNumero);
			fail("Não validou a obrigatoriedade no preenchimento do Número!");
		} catch (Exception e) {
			assertEquals("O campo número é de preenchimento obrigatório!", e.getMessage());
		}
	} 
	
	@Test
	public void cadastroCartaoValidaTamanhoNumeroMenor() {
		try {
			Cartao cartaoTamanhoNumeroMenor = new Cartao();
			cartaoTamanhoNumeroMenor.setNome("Nubank");
			cartaoTamanhoNumeroMenor.setBandeira("Mastercard");
			cartaoTamanhoNumeroMenor.setLimite(10.00);
			cartaoTamanhoNumeroMenor.setNumero("123456789012");
			cartaoService.inserir(cartaoTamanhoNumeroMenor);
			fail("Não validou o tamanho minimo para o preenchimento do Número!");
			
		} catch (Exception e) {
			assertEquals("Número de cartão inválido!",e.getMessage());
		}
	}
	
	@Test
	public void cadastroCartaoValidaTamanhoNumeroMaior() {
		try {
			Cartao cartaoTamanhoNumeroMaior = new Cartao();
			cartaoTamanhoNumeroMaior.setNome("Nubank");
			cartaoTamanhoNumeroMaior.setBandeira("Mastercard");
			cartaoTamanhoNumeroMaior.setLimite(10.00);
			cartaoTamanhoNumeroMaior.setNumero("12345678901234567");
			cartaoService.inserir(cartaoTamanhoNumeroMaior);
			fail("Não validou o tamanho máximo para o preenchimento do Número!");
			
		} catch (Exception e) {
			assertEquals("Número de cartão inválido!", e.getMessage());
		}
	}
	
	@Test
	public void cadastroCartaoValidaNumero() {
		List<String> cartoesInvalidos = Arrays.asList("5547350794900010", "5400215600034433", "370014997122445", "6060006287088777", "3527434000509338", "30250310001001", "ABCDEFGHIJKLMNOP");
		int i = 0;
		while (i<cartoesInvalidos.size()) {
			try {
				Cartao cartaoValidaNumero = new Cartao();
				cartaoValidaNumero.setNome("Nubank");
				cartaoValidaNumero.setBandeira("Mastercard");
				cartaoValidaNumero.setLimite(10.00);
				cartaoValidaNumero.setNumero(cartoesInvalidos.get(i));
				cartaoService.inserir(cartaoValidaNumero);
				fail("Não validou o Número do cartão como inválido!");
				
			} catch (Exception e) {
				assertEquals("Número de cartão inválido!", e.getMessage());
			}
			i++;
		}
	}
	
	@Test
	public void cadastroCartaoValidaNumeroCorrespondenteABandeira() {
		List<String> cartoesValidos = Arrays.asList("5388708838533791", "4532557096669138", "377157978157280", "30105527889423", "5388708838533791", "5037289724580589", "6062827885779171");
		List<String> cartoesBandeirasValidas = Arrays.asList("Hipercard", "Aura", "Diners Club", "American Express", "JCB", "Visa", "Mastercard" );
		int i = 0;
		while (i<cartoesValidos.size()) {
			try {
				Cartao cartaoValidaBandeira = new Cartao();
				cartaoValidaBandeira.setNome("Nubank");
				cartaoValidaBandeira.setBandeira(cartoesBandeirasValidas.get(i));
				cartaoValidaBandeira.setLimite(10.00);
				cartaoValidaBandeira.setNumero(cartoesValidos.get(i));
				cartaoService.inserir(cartaoValidaBandeira);
				fail("Não validou o Número do cartão de acordo com a Bandeira!");
			} catch (Exception e) {
				assertEquals("O número de cartão informado não corresponde com a bandeira!", e.getMessage());
			}
			i++;
		}
	}
	
	@Test
	public void cadastroCartaoValidaLimiteMaiorQueZero(){
		try {
			Cartao cartaoValidaLimite = new Cartao();
			cartaoValidaLimite.setNome("Nubank");
			cartaoValidaLimite.setBandeira("Mastercard");
			cartaoValidaLimite.setNumero("5388708838533791");
			cartaoValidaLimite.setLimite(0.00);
			cartaoService.inserir(cartaoValidaLimite);
			fail("Não validou o Limite do cartão!");
		} catch (Exception e) {
			assertEquals("O campo limite não pode ser menor ou igual a zero!", e.getMessage());
		}
	}
	
	@Test
	public void cadastroCartaoSemParametroLimite(){
		try {
			Cartao cartaoValidaLimite = new Cartao();
			cartaoValidaLimite.setNome("Nubank");
			cartaoValidaLimite.setBandeira("Mastercard");
			cartaoValidaLimite.setNumero("5388708838533791");
			cartaoService.inserir(cartaoValidaLimite);
			fail("Não validou o Limite do cartão!");
		} catch (Exception e) {
			assertEquals("O campo limite é de preenchimento obrigatório!", e.getMessage());
		}
	}
	
}
