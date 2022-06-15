package controlefinanceiroservicos.acceptance.page;

import org.openqa.selenium.By;

public class CategoriaPage extends PageObject {
	
	public void navegaParaCategoriaListar() {
		browser.get("http://localhost:4200/categoria/listar");
	}
	
	public void clicoBotaoNovaCategoria() {
		browser.findElement(By.id("novaCategoria")).click();
	}
	
	public void preencherFormulario(String nome, String tipo) {
		esperar();
		browser.findElement(By.cssSelector("input[formcontrolname=nome]")).sendKeys(nome);
		browser.findElement(By.cssSelector("mat-select[formcontrolname=tipo]")).sendKeys(tipo);
		esperar();
	}
	
}
