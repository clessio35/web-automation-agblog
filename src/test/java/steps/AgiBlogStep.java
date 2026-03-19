package steps;

import org.openqa.selenium.WebDriver;
import config.DriverManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.AgiBlogPage;

public class AgiBlogStep {

    private WebDriver driver;
    private AgiBlogPage agi;
    private String url = "https://blog.agibank.com.br/";

    @Given("que o usuario acessa o blog do Agibank")
    public void acessaBlog() {
        driver = DriverManager.getDriver(); 
        driver.get(url); 
        agi = new AgiBlogPage(driver);   
        agi.validateUrlAccess();
        agi.validateHomePage();
    }

    @When("ele insere {string} na busca")
    public void buscaPor(String termo) {
        agi.scanAndClickLupa(termo);
    }

    @Then("os resultados devem conter conteudos relacionados a {string}")
    public void validaConteudoRelacionado(String categoria) {
        agi.validateResultsByCategory(categoria);
    }

}