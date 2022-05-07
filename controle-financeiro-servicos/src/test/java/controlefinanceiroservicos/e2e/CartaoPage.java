package controlefinanceiroservicos.e2e;

import org.openqa.selenium.By;

public class CartaoPage extends PageObject{
	
	private static final String URL_LISTA_CARTAO = "http://localhost:4200/cartao/listar";
	
	public CartaoPage() {
		super(null);
		this.browser.navigate().to(URL_LISTA_CARTAO);
	}
	
	public void novoCartao() {
		this.browser.findElement(By.id("novoCartao")).click();
	}
	
	public void preencherFormulario(String nome, String bandeira, String numero, String limite) {
		this.browser.findElement(By.cssSelector("input[formcontrolname=nome]")).sendKeys(nome);
 		this.browser.findElement(By.cssSelector("mat-select[formcontrolname=bandeira]")).sendKeys(bandeira);
		this.browser.findElement(By.cssSelector("input[formcontrolname=numero]")).sendKeys(numero);
		this.browser.findElement(By.cssSelector("input[formcontrolname=limite]")).sendKeys(limite);
	}
	
	public void salvarCartao() {
		this.browser.findElement(By.cssSelector("button[type=submit]")).click();
	}
	
	public boolean cartaoCadastradoComSucesso() {
		return this.browser.getPageSource().contains("Cartão cadastrado com sucesso");
	}
	
	public boolean isPaginaAtual() {
		return this.browser.getCurrentUrl().contains(URL_LISTA_CARTAO);
	}

}
