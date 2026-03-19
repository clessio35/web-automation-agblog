package pages;

import static config.DriverManager.getDriver;
import static utils.MetodoUtils.waitFor;

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

import com.aventstack.extentreports.Status;

import config.DriverManager;
import utils.MetodoUtils;
import utils.ReportUtils;
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
		waitFor(driver, By.id("ast-desktop-header"), 30);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ast-desktop-header")));
		Assert.assertTrue(el.isDisplayed());
		MetodoUtils.takeStepScreenshot(getDriver(), getScenarioName());
	}

	public void scanAndClickLupa(String termo) {
		WebElement container = new WebDriverWait(driver, Duration.ofSeconds(30))
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
						"//div[@class='site-above-header-wrap ast-builder-grid-row-container site-header-focus-item ast-container']")));
		List<WebElement> lupas = container.findElements(
				By.cssSelector(".ast-search-menu-icon.slide-search, .ast-search-icon, a[aria-label='Search button']"));

		if (lupas.isEmpty()) {
			throw new RuntimeException("Lupa not found!");
		}
		boolean text = false;
		int cont = 1;

		for (WebElement lupa : lupas) {
			try {
				System.out.println("loop -->  " + cont + "/" + lupas.size());
				new WebDriverWait(driver, Duration.ofSeconds(15)).until(driver1 -> lupa.isDisplayed() && lupa.isEnabled());
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", lupa);
				
				WebElement cmpLupa = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(13))
						.pollingEvery(Duration.ofMillis(140)).ignoring(NoSuchElementException.class).until(d -> {
							WebElement input = d.findElement(
									By.cssSelector(".ast-search-menu .search-field, input[type='search']"));
							return (input.isDisplayed() && input.isEnabled()) ? input : null;
						});
				cmpLupa.clear();
				cmpLupa.sendKeys(termo);
				Thread.sleep(200);
				MetodoUtils.takeStepScreenshot(getDriver(), getScenarioName());
				cmpLupa.sendKeys(Keys.ENTER);
				text = true;
				break;
			} catch (Exception e) {
				System.out.println("proximo loop.");
			}
			cont++;
		}
		if (!text) {
			throw new RuntimeException("not found");
		}
	}

	public void validateResultsByCategory(String categoria) {
	    System.out.println("validate results by termo");

	    ReportUtils.setExtentTest(ReportUtils.getExtent().createTest("Validate article: " + categoria));
	    var test = ReportUtils.getExtentTest();

	    try {
	        test.log(Status.INFO, "init log: " + categoria);
	        waitFor(driver, By.xpath("//span[normalize-space()='" + categoria + "']"), 30);
	        MetodoUtils.takeStepScreenshot(getDriver(), getScenarioName()); 

	        WebElement main = waitFor(driver, By.id("main"), 30);
	        List<WebElement> listDeArtigos = main.findElements(By.tagName("article"));
	        
	        if (listDeArtigos.isEmpty()) {
	            waitFor(driver, By.xpath("//p[contains(text(),'Lamentamos, mas nada foi encontrado para sua pesqu')]"), 30);
	            MetodoUtils.takeStepScreenshot(getDriver(), getScenarioName() + " sem pesquisa");
	            test.log(Status.WARNING, "not founded -> : " + categoria);
	            System.out.println("not found, termo -> : " + categoria);
	            return;
	        }

	        for (WebElement artigo : listDeArtigos) {
	            String title = artigo.findElement(By.tagName("h2")).getText();
	            String link = artigo.findElement(By.tagName("a")).getAttribute("href");
	            if (title.isEmpty() || link.isEmpty()) {
	                test.log(Status.WARNING, "incomplete ");
	                System.out.println("not found");
	                continue;
	            }
	            test.log(Status.PASS, "Título: " + title + " | Link: " + link);
	            System.out.println("title: " + title);
	            System.out.println("link: " + link);
	        }

	        MetodoUtils.takeStepScreenshot(getDriver(), getScenarioName()); 
	        test.log(Status.INFO, "success");

	    } catch (Exception e) {
	        test.log(Status.FAIL, "error: " + e.getMessage());
	        throw e;
	    }
	}
}
