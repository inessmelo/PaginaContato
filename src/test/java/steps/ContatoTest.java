package steps;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import javax.swing.text.Document;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;

public class ContatoTest {

	private WebDriver driver;

	@Before
	public void iniciar() {
		System.setProperty("webdriver.chrome.driver", "target\\driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://automationpractice.com/index.php");
		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
		driver.manage().window().maximize();
	}

	@After
	public void finalizar() {
//		driver.quit();
	}

	// não inserindo nenhum dado nos campos obrigatorios - ERRO
	@Dado("que os campos obrigatorios nao tenham sido preenchido")
	public void queOsCamposObrigatoriosNaoTenhamSidoPreenchido() {
		// acessando a pagina de contato
		driver.findElement(By.linkText("Contact us")).click();
	}

	@Quando("clicar no botao Send")
	public void clicarNoBotaoSend() {
//		driver.findElement(By.id("submitMessage")).click();
	}

	@Entao("aparece a mensagem de erro solicitando email")
	public void apareceAMensagemDeErroSolicitandoEmail() {
		String msg = driver.findElement(By.xpath("//*[@id='center_column']//li")).getText();
		assertEquals("Invalid email address.", msg);
	}

	// inserindo um email no campo Email address - ERRO
	@Dado("que no campo email foi preenchido com {string}")
	public void queNoCampoEmailFoiPreenchidoCom(String email) {
		// acessando a pagina de contato
		driver.findElement(By.linkText("Contact us")).click();
		// preenchedo o campo Email address com um email
		driver.findElement(By.id("email")).sendKeys(email);
	}

	@Entao("aparece a mensagem de erro solicitando mensagem")
	public void apareceAMensagemDeErroSolicitandoMensagem() {
		String msg = driver.findElement(By.xpath("//*[@id='center_column']//li")).getText();
		assertEquals("The message cannot be blank.", msg);
	}

	// inserindo email no campo Email address e mensagem no campo Message - ERRO
	@Dado("o campo mensagem foi preenchido com {string}")
	public void oCampoMensagemFoiPreenchidoCom(String msg) {
		// preenchedo o campo Message com uma mensagem
		driver.findElement(By.id("message")).sendKeys(msg);
	}

	@Entao("aparece a mensagem de erro solicitando assunto")
	public void apareceAMensagemDeErroSolicitandoAssunto() {
		String msg = driver.findElement(By.xpath("//*[@id='center_column']//li")).getText();
		assertEquals("Please select a subject from the list provided.", msg);
	}

	// inserindo campos obrigatorios - SUCESSO
	@Dado("que os campos obrigatorios foram preenchidos")
	public void queOsCamposObrigatoriosForamPreenchidos() {
		
		driver.findElement(By.linkText("Contact us")).click();		
		Select assunto = new Select(driver.findElement(By.id("id_contact")));
		assunto.selectByVisibleText("Customer service");		
		driver.findElement(By.id("email")).sendKeys("testando@teste.com");
		driver.findElement(By.id("id_order")).sendKeys("RF0245N$8");	
		
		String caminho = "D:\\Documentos\\Trabalho\\SaveCash\\PaginaContatoAutomatizado\\target\\file\\ContatoSaveCash.txt";
//		driver.findElement(By.id("uniform-fileUpload")).click();
		JavascriptExecutor js = (JavascriptExecutor) driver;			
		js.executeScript("document.querySelector(\"#uniform-fileUpload\").style.display=\"block\"");
		driver.findElement(By.xpath("uniform-fileUpload")).sendKeys(caminho);
		
//		js.executeScript("document.querySelector(\"#fileUpload\").style=\"block\"");
		
		
		js.executeScript("document.getElementsByClassName('filename').style='block'");
//		WebElement textField = driver.findElement(By.xpath("//*[@id='uniform-fileUpload']/span[2]"));
//		js.executeScript("document.querySelector(\"#textField\").style=\"block\"");
		
//		driver.findElement(By.id("uniform-fileUpload")).click();
//		driver.findElement(By.xpath("//*[@id='uniform-fileUpload']/span[2]")).clear();
		
//		js.executeScript("document.querySelector(\"#uniform-fileUpload\").style.display=\"none\"");
		
		
		
//		js.executeScript("document.getElementoById('uniform-fileUpload').style.display='block'");
//		driver.findElement(By.id("uniform-fileUpload")).click();
//		driver.findElement(By.cssSelector("//*[@id='uniform-fileUpload']/span[1]")).sendKeys(caminho);
		
			
		
		
//		driver.findElement(By.id("message")).sendKeys("Teste automatizado com Selenium WebDriver");
	}

	@Entao("aparece a mensagem de sucesso")
	public void apareceAMensagemDeSucesso() {
//		String msg = driver.findElement(By.xpath("//*[@id='center_column']//p")).getText();
//		assertEquals("Your message has been successfully sent to our team.", msg);
	}

}
