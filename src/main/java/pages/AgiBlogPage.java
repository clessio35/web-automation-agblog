package pages;

import static config.DriverManager.getDriver;
import static utils.MetodoUtils.isElementVisible;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import config.DriverManager;
import utils.MetodoUtils;
import utils.TestContext;

public class AgiBlogPage {

	private WebDriver driver;
	private MetodoUtils metodo;

	public AgiBlogPage(WebDriver driver) {
		this.driver = driver;
		this.metodo = new MetodoUtils(driver);
	}

	private String getScenarioName() {
		if (TestContext.getScenario() != null) {
			return TestContext.getScenario().getName().replaceAll("[^a-zA-Z0-9\\-_]", "_");
		}
		return "unknown_scenario";
	}

	public void printScenarioName() {
		if (TestContext.getScenario() != null) {
			System.out.println("Nome do cenário: " + TestContext.getScenario().getName());
		}
	}

	public void validateUrlAccess() {
		System.out.println("validate url");
		String url = "https://blog.agibank.com.br/";
		String getUrl = DriverManager.getDriver().getCurrentUrl();
		Assert.assertTrue("URL incorreta ao acessar o site", getUrl.contains(url));
	}

	public void validateHomePage() {
		System.out.println("validate home page");
		isElementVisible(driver, By.id("ast-desktop-header"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ast-desktop-header")));
		Assert.assertTrue(el.isDisplayed());
		MetodoUtils.takeStepScreenshot(getDriver(), getScenarioName());
	}

	public void scanAndClickLupa(String textoBusca) {
		WebElement container = new WebDriverWait(driver, Duration.ofSeconds(20))
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
						"//div[@class='site-above-header-wrap ast-builder-grid-row-container site-header-focus-item ast-container']")));
		List<WebElement> lupas = container.findElements(
				By.cssSelector(".ast-search-menu-icon.slide-search, .ast-search-icon, a[aria-label='Search button']"));

		if (lupas.isEmpty()) {
			throw new RuntimeException("Nenhum botão de lupa encontrado dentro do header!");
		}
		boolean textoInserido = false;
		int contador = 1;

		for (WebElement lupa : lupas) {
			try {
				System.out.println("loop -->  " + contador + "/" + lupas.size());
				new WebDriverWait(driver, Duration.ofSeconds(10))
						.until(driver1 -> lupa.isDisplayed() && lupa.isEnabled());
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", lupa);

				WebElement campoBusca = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(10))
						.pollingEvery(Duration.ofMillis(200)).ignoring(NoSuchElementException.class).until(d -> {
							WebElement input = d.findElement(
									By.cssSelector(".ast-search-menu .search-field, input[type='search']"));
							return (input.isDisplayed() && input.isEnabled()) ? input : null;
						});
				campoBusca.clear();
				campoBusca.sendKeys(textoBusca);
				Thread.sleep(200);
				campoBusca.sendKeys(Keys.ENTER);
				textoInserido = true;
				break;
			} catch (Exception e) {
				System.out.println("proximo loop.");
			}
			contador++;
		}
		if (!textoInserido) {
			throw new RuntimeException("not found");
		}
	}
}
