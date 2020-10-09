package pages;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;

public class MetodoStock {

	WebDriver driver;

	/**
	 * @author Marcos Moreira
	 * @param abrir navegador
	 * @throws IOException
	 */
	public void abrirNavegador(String site, String navegador, String descricaoPasso) throws IOException {

		try {

			if (navegador == "CHROME" || navegador == "FIREFOX") {

				if (navegador == "CHROME") {
					System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
					driver = new ChromeDriver();
					driver.get(site);
					driver.manage().window().maximize();
				} else if (navegador == "FIREFOX") {
					System.setProperty("webdriver.gecko.driver", "./Drivers/geckodriver.exe");
					driver = new FirefoxDriver();
					driver.get(site);
					driver.manage().window().maximize();
				} else if (navegador == "EDGE") {
					System.setProperty("webdriver.msedge.driver", "./Drivers/msedgedriver.exe");
					driver = new EdgeDriver();
					driver.get(site);
					driver.manage().window().maximize();

				}
			}

		} catch (Exception e) {
			System.out.println("Erro ao tentar abrir navegador, escolha a opção CHORME ou EDGE ou FIREFOX");
			screnShoot("./Evidencias/erros/" + descricaoPasso + ".png");
		}

	}

	/**
	 * @author Marcos Moreira
	 * @param clicar
	 * @throws IOException
	 */
	public void clicar(By element, String descricaoPasso) throws IOException {
		try {
			driver.findElement(element).click();
		} catch (Exception e) {
			System.out.println("Erro ao tentar clicar no elmeneto " + element + " no passo " + descricaoPasso);
			screnShoot("./Evidencias/erros/" + descricaoPasso + ".png");
		}
	}

	/**
	 * @author Marcos Moreira
	 * @param preencher
	 * @throws IOException
	 */
	public void preencher(By element, String text, String descricaoPasso) throws IOException {

		try {
			driver.findElement(element).sendKeys(text);
		} catch (Exception e) {
			System.out.println("Erro ao tentar preencher no elemento " + element + ", no teste " + descricaoPasso);
			screnShoot("./Evidencias/erros/" + descricaoPasso + ".png");
		}
	}

	/**
	 * @author Marcos Moreira
	 * @param Tirar print
	 */
	public void screnShoot(String printName) throws IOException {
		TakesScreenshot scrShot = ((TakesScreenshot) driver);
		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
		File DestFile = new File(printName);
		FileUtils.copyFile(SrcFile, DestFile);
	}

	/**
	 * @author Marcos Moreira
	 * @param submit
	 * @throws IOException
	 */
	public void submit(By element, String descricaoPasso) throws IOException {
		try {
			driver.findElement(element).submit();
		} catch (Exception e) {
			System.out.println("Erro ao tentar submeter o elemento " + element + ", no teste " + descricaoPasso);
			screnShoot("./Evidencias/erros/" + descricaoPasso + ".png");
		}

	}

	/**
	 * @author Marcos Moreira
	 * @param Clicar por texto
	 * @throws IOException
	 */

	public void clicarText(String text, String descricaoPasso) {

		driver.findElement(By.linkText(text)).click();

	}

	/**
	 * @author Marcos Moreira
	 * @param esperar elemento ser clicavel
	 * @throws IOException
	 */

	public void esperarClicavel(By element, String descricaoPasso) {

		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement elemento = wait.until(ExpectedConditions.elementToBeClickable(element));

	}

	/**
	 * @author Marcos Moreira
	 * @param esperar o elemento
	 * @throws IOException
	 */
	public void esperarElemento(By element, String descricaoPasso) {

		WebElement myDynamicElement = (new WebDriverWait(driver, 10))
				.until(ExpectedConditions.presenceOfElementLocated(element));

	}

	/**
	 * @author Marcos Moreira
	 * @param validar a página
	 * @throws IOException
	 */
	public void validarPagina(String pagina, String descricaoPasso) {
		String pag = driver.getTitle();
		assertEquals(pagina, pag);
	}

	/**
	 * @author Marcos Moreira
	 * @param validar por posição do html
	 * @throws IOException
	 */

	public void validarItemPorPosicao(String posicao, String produto, String descricaoPasso) {
		String prod = driver.findElement(By.cssSelector("#item-list > div.wrapper > div:nth-child(" + posicao
				+ ") > div.item-card__description > a.item-card__description__product-name > span")).getText();

	}

	/**
	 * @author Marcos Moreira
	 * @param pesquisar produto por posição
	 * @throws InterruptedException
	 * @throws IOException
	 */

	public void escolherPorPosicao(String posicao, String descricaoPasso) throws InterruptedException {

		driver.findElement(By.cssSelector(
				"#item-list > div.wrapper > div:nth-child(" + posicao + ") > div.item-card__images > a > img")).click();

	}
	/**
	 * @author Marcos Moreira
	 * @param Validar carrinho
	 */

	public void validarCarrinho(String texto, String descricaoPasso) throws InterruptedException {
		Thread.sleep(5000);

		driver.findElement(By.xpath("//*[@id=\"buy-box\"]/section[2]/div/ul/li[4]/a")).click();
		driver.findElement(By.cssSelector("#buy-button-now > span")).click();
	}

	/**
	 * @author Marcos Moreira
	 * @param esperar em milisegundos
	 */

	public void esperar(int time, String descricaoPasso) throws InterruptedException {

		Thread.sleep(time);
	}

}