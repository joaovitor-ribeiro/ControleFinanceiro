package controlefinanceiroservicos.acceptance.page;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageObject {
	
//	public WebDriver  browser;
	
// 	A diferença do de cima para o de baixo, é que o de cima usamos o public para indicar que é
//  publico e todos consegue acessar, no de baixo não usamos pq o public vem por padrão quando
//  não colocamos nada.
	
	WebDriver browser;
	
	WebDriverWait wait;
	
	public void iniciarDriver() {
		// Avisa o sistema onde está o nosso chromedriver, 
		// é necessário pq o driver não está na raiz do projeto
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		// Instancia o o browser idicando que o browser a ser usado será o Chrome
		browser = new ChromeDriver();
		//faz com que o browser espere por 40 seguntos o elemento ser achado
		//se o elemento for achado antes a execução continuará 
		browser.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		//maximiza a tela
		browser.manage().window().maximize();
		// Instancia nosso wait (espera) falando que quando formos utilizar essa variavel para esperar
		// ela irá esperar por 40 segundos no máximo
		wait = new WebDriverWait(browser, 40);
	}
	
	public void acionoBotaoSalvar() {
		browser.findElement(By.cssSelector("button[type=submit]")).click();
	}
	
	public String paginaAtual() {
		return browser.getCurrentUrl();
	}
	
	public void esperaAlert() {
		wait.until(ExpectedConditions.visibilityOf(browser.findElement(By.cssSelector("div[role=alert]"))));
	}
	
	public boolean alertaDeSucesso(String mensagem) {
		// verifica se na página inteira contem a mensagem 
		return browser.getPageSource().contains(mensagem);
	}
	
	public String mensagemDeErro() {
		return wait.until(ExpectedConditions.visibilityOf(browser.findElement(By.className("mat-error")))).getText();
	}
	
	public void realizarLogin(String email, String senha) {
		esperar();
		browser.get("http://localhost:4200/entrar");
		browser.findElement(By.cssSelector("input[formcontrolname=email]")).sendKeys(email);
		browser.findElement(By.cssSelector("input[formcontrolname=senha]")).sendKeys(senha);
		browser.findElement(By.id("botaoEntrar")).click();
		wait.until(ExpectedConditions.visibilityOf(browser.findElement(By.cssSelector("div.divData > mat-form-field"))));
		esperar();
	}
	
	public void sair() {
		if (browser != null) {
			browser.quit();
		}
	}
	
	public void esperar() {
		try {
			Thread.sleep(250);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
