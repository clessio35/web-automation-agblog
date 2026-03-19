package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Duration;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.MediaEntityBuilder;

import io.cucumber.java.Scenario;

public class MetodoUtils {
	private WebDriver driver;

	public MetodoUtils(WebDriver driver) {
		this.driver = driver;
	}

	private static final String FILE_PATH = "src/main/resources/dadosUsuarios.xlsx";

	private static int screenshotCounter = 1;

	// Método para salvar dados Excel (igual seu código)
	public static void saveData(String firstName, String lastName, String email, String password, String address) {
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = (Sheet) workbook.createSheet("Usuarios");

		Row header = ((org.apache.poi.ss.usermodel.Sheet) sheet).createRow(0);
		header.createCell(0).setCellValue("FirstName");
		header.createCell(1).setCellValue("LastName");
		header.createCell(2).setCellValue("Email");
		header.createCell(3).setCellValue("Password");
		header.createCell(4).setCellValue("Address");

		Row row = sheet.createRow(1);
		row.createCell(0).setCellValue(firstName);
		row.createCell(1).setCellValue(lastName);
		row.createCell(2).setCellValue(email);
		row.createCell(3).setCellValue(password);
		row.createCell(4).setCellValue(address);

		try (FileOutputStream fileOut = new FileOutputStream(FILE_PATH)) {
			workbook.write(fileOut);
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Ler célula específica (igual seu código)
	@SuppressWarnings("deprecation")
	public static String readCell(int rowIndex, String columnName) {
		try (FileInputStream fis = new FileInputStream(FILE_PATH); Workbook workbook = new XSSFWorkbook(fis)) {
			Sheet sheet = workbook.getSheet("Usuarios");
			Row headerRow = sheet.getRow(0);
			Row dataRow = sheet.getRow(rowIndex);

			if (headerRow == null || dataRow == null)
				return null;

			for (int i = 0; i < headerRow.getPhysicalNumberOfCells(); i++) {
				String header = headerRow.getCell(i).getStringCellValue();
				if (header.equalsIgnoreCase(columnName)) {
					Cell cell = dataRow.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
					cell.setCellType(CellType.STRING);
					return cell.getStringCellValue();
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void resetScreenshotCounter() {
		screenshotCounter = 1;
	}

	/**
	 * Salva screenshot no disco e anexa no ExtentReport
	 */
	public static void takeStepScreenshot(WebDriver driver, String stepName) {
		Scenario scenario = TestContext.getScenario();
		if (scenario == null)
			return;

		String scenarioName = scenario.getName().replaceAll("[^a-zA-Z0-9\\-_]", "_");
		String safeStepName = stepName.replaceAll("[^a-zA-Z0-9\\-_]", "_");

		Path screenshotDir = Paths.get("evidencias", scenarioName);
		try {
			Files.createDirectories(screenshotDir);
		} catch (IOException e) {
			System.err.println("Erro ao criar diretório: " + e.getMessage());
			return;
		}

		String filename = String.format("%02d_%s.png", screenshotCounter++, safeStepName);
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		Path destination = screenshotDir.resolve(filename);

		try {
			Files.copy(screenshot.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);
			System.out.println("Screenshot saved! ---->  " + destination.toAbsolutePath());

			if (TestContext.getExtentTest() != null) {
				TestContext.getExtentTest().info(stepName,
						MediaEntityBuilder.createScreenCaptureFromPath(destination.toString()).build());
			}

		} catch (IOException e) {
			System.err.println("Erro ao salvar screenshot: " + e.getMessage());
		}
	}

	public static void saveTextEvidence(String content, String fileName) {
		Scenario scenario = TestContext.getScenario();
		if (scenario == null)
			return;

		String scenarioName = scenario.getName().replaceAll("[^a-zA-Z0-9\\-_]", "_");
		Path evidenceDir = Paths.get("evidencias", scenarioName);

		try {
			Files.createDirectories(evidenceDir);
		} catch (IOException e) {
			System.err.println("Erro ao criar diretório: " + e.getMessage());
			return;
		}

		Path filePath = evidenceDir.resolve(fileName + ".txt");

		try {
			Files.writeString(filePath, content);
			System.out.println("📝 Evidência de texto salva: " + filePath.toAbsolutePath());

			if (TestContext.getExtentTest() != null) {
				TestContext.getExtentTest().info("Evidência: " + fileName + "<br><pre>" + content + "</pre>");
			}

		} catch (IOException e) {
			System.err.println("Erro ao salvar evidência de texto: " + e.getMessage());
		}
	}

	public void clickElementByXpath(String xpath) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		By locator = By.xpath(xpath);
		WebElement elemento = wait.until(ExpectedConditions.elementToBeClickable(locator));
		System.out.println("aguardando elemento");
		try {
			elemento.click();
		} catch (ElementClickInterceptedException e) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", elemento);
		}
	}

	public void clickElementById(String id) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		By locator = By.id(id);
		WebElement elemento = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		wait.until(ExpectedConditions.elementToBeClickable(locator));
		elemento.click();
	}
	
	public void clickElementBycss(String css) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		By locator = By.cssSelector(css);
		WebElement elemento = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		wait.until(ExpectedConditions.elementToBeClickable(locator));
		elemento.click();
	}

	public void typeElement(String locatorString, String text) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		By locator = getByFromString(locatorString);
		WebElement elemento = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		elemento.clear();
		elemento.sendKeys(text);
	}

	private By getByFromString(String locatorString) {
		if (locatorString.startsWith("id=")) {
			return By.id(locatorString.substring(3));
		} else if (locatorString.startsWith("xpath=")) {
			return By.xpath(locatorString.substring(6));
		} else {
			return By.id(locatorString);
		}
	}

	public void validateText(String expectedText, String element) {
		WebElement report = new WebDriverWait(driver, Duration.ofSeconds(20))
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(element)));
		String text = report.getText().trim();
		Assert.assertEquals(expectedText, text);
	}

	public static boolean isElementVisible(WebDriver driver, By locator) {
		List<WebElement> elements = driver.findElements(locator);
		return elements.size() > 0 && elements.get(0).isDisplayed();
	}

}
