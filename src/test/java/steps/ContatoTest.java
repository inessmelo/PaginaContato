package steps;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
		driver.findElement(By.id("submitMessage")).click();
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
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		//acessando a pagina de contato
		driver.findElement(By.linkText("Contact us")).click();
		
		//selecionando o campo Subject Heading com o assunto Customer service
		Select assunto = new Select(driver.findElement(By.id("id_contact")));
		assunto.selectByVisibleText("Customer service");
		
		// preenchendo o campo Email address com um email
		driver.findElement(By.id("email")).sendKeys("testando@teste.com");
		
		// preenchendo o campo Order reference
		driver.findElement(By.id("id_order")).sendKeys("RF0245N$8");
		
		// anexando um arquivo no campo Attach File
//		WebElement botao = driver.findElement(By.id("uniform-fileUpload"));
//		js.executeScript("document.getElementById('uniform-fileUpload').click();");
		
		// preenchendo o campo Message
		driver.findElement(By.id("message")).sendKeys("Teste automatizado com Selenium WebDriver");
	}

	@Entao("aparece a mensagem de sucesso")
	public void apareceAMensagemDeSucesso() {
		String msg = driver.findElement(By.xpath("//*[@id='center_column']//p")).getText();
		assertEquals("Your message has been successfully sent to our team.", msg);
	}

}
