package controlefinanceiroservicos.acceptance.page;

import org.openqa.selenium.By;

public class GanhoPage extends PageObject {
	
	public void navegaParaGanhoListar() {
		browser.get("http://localhost:4200/ganho/listar");
	}
	
	public void clicoBotaoNovoGanho() {
		browser.findElement(By.id("novoGanho")).click();
	}
	
	public void preencherFormulario(String descricao, String categoria, String valor, String data ) {
		esperar();
		browser.findElement(By.id("descricao")).sendKeys(descricao);
		browser.findElement(By.cssSelector("mat-select[formcontrolname=categoria]")).sendKeys(categoria);
		browser.findElement(By.cssSelector("input[formcontrolname=valor]")).sendKeys(valor);
		browser.findElement(By.cssSelector("input[formcontrolname=data]")).sendKeys(data);
		esperar();
	}
	
}
