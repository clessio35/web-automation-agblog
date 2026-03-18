package pages;

import static config.DriverManager.getDriver;
import static utils.MetodoUtils.isElementVisible;

import java.time.Duration;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import config.DriverManager;
import utils.MetodoUtils;
import utils.TestContext;

public class AgiBlog {

	private WebDriver driver;
	private MetodoUtils metodo;

	public AgiBlog(WebDriver driver) {
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

}
