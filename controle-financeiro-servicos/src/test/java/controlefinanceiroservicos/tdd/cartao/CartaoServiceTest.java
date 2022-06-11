package controlefinanceiroservicos.tdd.cartao;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import controlefinanceiroservicos.service.CartaoService;

public class CartaoServiceTest {
	
	private CartaoService cartaoService = new CartaoService(); 
	
	@Test
	public void validaBandeiraMastercard() {
		List<String> cartoesValidosMastercard = Arrays.asList("51", "52", "53", "54", "55");
		
		cartoesValidosMastercard.forEach(cartaoValidoParaMastercard -> {
		boolean cartaoValido = cartaoService.validarNumeroCorrespondenteABandeira(cartaoValidoParaMastercard, "Mastercard");
			assertTrue("Cartão inválido " + cartaoValidoParaMastercard, cartaoValido);
		});
	}
	
	@Test
	public void validaBandeiraInvalidaMastercard() {
		List<String> cartoesInvalidosMastercard = Arrays.asList("99", "88", "70", "00", "aa", "");
		
		cartoesInvalidosMastercard.forEach(cartaoinValidoParaMastercard -> {
			boolean cartaoValido = cartaoService.validarNumeroCorrespondenteABandeira(cartaoinValidoParaMastercard, "Mastercard");
			assertFalse("Cartão válido " + cartaoinValidoParaMastercard, cartaoValido);
		});
	}
	
	@Test
	public void validaBandeiraDinersClub() {
		List<String> cartoesValidosDinersClub = Arrays.asList("300", "301", "302", "303", "304", "305", "36", "38");
		
		cartoesValidosDinersClub.forEach(cartaoValidoDinersClub -> {
			boolean cartaoValido = cartaoService.validarNumeroCorrespondenteABandeira(cartaoValidoDinersClub, "Diners Club");
			assertTrue("Cartão válido " + cartaoValidoDinersClub, cartaoValido);
		});
	}
	
	@Test
	public void validaBandeiraInvalidosDinersClub() {
		List<String> cartoesInvalidosDinersClub = Arrays.asList("99", "88", "70", "00", "aa", "", "307");
		
		cartoesInvalidosDinersClub.forEach(cartaoInvalidoDinersClub -> {
			boolean cartaoInvalido = cartaoService.validarNumeroCorrespondenteABandeira(cartaoInvalidoDinersClub, "Diners Club");
			assertFalse(cartaoInvalido);
		});
	}
	
}
