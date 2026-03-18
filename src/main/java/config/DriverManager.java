package config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverManager {

    private static WebDriver driver;
    private static final String BASE_URL = "https://blog.agibank.com.br/";

    /**
     * Retorna a instância única do WebDriver.
     */
    public static WebDriver getDriver() {
        if (driver == null) {
            ChromeOptions options = new ChromeOptions();

            // Headless opcional via -Dheadless=true
            if (Boolean.parseBoolean(System.getProperty("headless", "false"))) {
                options.addArguments("--headless=new"); // modo headless moderno
                options.addArguments("--disable-gpu");
                options.addArguments("--no-sandbox");
                options.addArguments("--window-size=1920,1080");
            }

            // Evita erro "Invalid Status code=403" / problemas de origem remota
            options.addArguments("--remote-allow-origins=*");

            // Baixa automaticamente o ChromeDriver correto
            WebDriverManager.chromedriver().setup();

            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
        }
        return driver;
    }

    /**
     * Navega para a URL base.
     */
    public static void navigateToBaseUrl() {
        if (driver == null) {
            throw new IllegalStateException("Driver não inicializado. Chame getDriver() antes.");
        }
        driver.get(BASE_URL);
    }

    /**
     * Encerra a instância do driver.
     */
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
