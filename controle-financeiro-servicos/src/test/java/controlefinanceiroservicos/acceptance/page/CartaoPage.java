package controlefinanceiroservicos.acceptance.page;

import org.openqa.selenium.By;

public class CartaoPage extends PageObject {
	
	public void navegaParaCartaoListar() {
		browser.get("http://localhost:4200/cartao/listar");
	}
	
	public void clicoBotaoNovoCartao() {
		browser.findElement(By.id("novoCartao")).click();
	}
	
	public void preencherFormulario(String nome, String bandeira, String numero, String limite) {
		// Espera o foco ir para o campo nome
		esperar();
		
		browser.findElement(By.cssSelector("input[formcontrolname=nome]")).sendKeys(nome);
		browser.findElement(By.cssSelector("mat-select[formcontrolname=bandeira]")).sendKeys(bandeira);
		browser.findElement(By.cssSelector("input[formcontrolname=numero]")).sendKeys(numero);
		browser.findElement(By.cssSelector("input[formcontrolname=limite]")).sendKeys(limite);
		
		// Espera ser feitas todas as validações exemplo: verificar se o número é válido 
		// verificar se o número corresponde a bandeira
		esperar();
	}
	
}
