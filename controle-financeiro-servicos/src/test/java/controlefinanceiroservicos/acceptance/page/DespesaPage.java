package controlefinanceiroservicos.acceptance.page;

import org.openqa.selenium.By;

public class DespesaPage extends PageObject {
	
	public void navegaParaDespesaListar() {
		browser.get("http://localhost:4200/despesa/listar");
	}
	
	public void clicoBotaoNovaDespesa() {
		browser.findElement(By.cssSelector("mat-grid-tile:nth-child(2) > div > button")).click();//obs: cuidado ao remover o final do css selector
	}
	
	public void preencherFormulario(String cartao, String descricao, String categoria, String valor, String data ) {
		esperar();
		browser.findElement(By.id("cartao")).sendKeys(cartao);
		browser.findElement(By.cssSelector("input[formcontrolname=descricao]")).sendKeys(descricao);
		browser.findElement(By.cssSelector("mat-select[formcontrolname=categoria]")).sendKeys(categoria);
		browser.findElement(By.cssSelector("input[formcontrolname=valor]")).sendKeys(valor);
		browser.findElement(By.cssSelector("input[formcontrolname=data]")).sendKeys(data);
		esperar();
	}

}
