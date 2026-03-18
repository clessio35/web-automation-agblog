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

    // CENÁRIO: ABERTURA DA BUSCA
    @Given("que o usuario acessa o blog do Agibank")
    public void acessaBlog() {
        driver = DriverManager.getDriver(); // garante driver inicializado
        driver.get("https://blog.agibank.com.br/"); // navega para a URL base
        agi = new AgiBlogPage(driver);           // instancia AgiBlog com driver correto
        agi.validateUrlAccess();
        agi.validateHomePage();
    }

    @Given("clica na lupa de busca")
    public void clicaNaLupa() {
    }

    @Then("o campo de busca deve ser exibido corretamente")
    public void validaCampoBusca() {
    }

    // Os outros steps seguem o mesmo padrão, mas só declarados
    @When("ele busca por {string}")
    public void buscaPor(String termo) {
    }

    @Then("os resultados devem conter conteudos relacionados a {string}")
    public void validaConteudoRelacionado(String categoria) {
    }

    @Then("pelo menos {int} resultados devem ser exibidos")
    public void validaQuantidadeResultados(Integer quantidade) {
    }

    @Then("os primeiros resultados devem ser relevantes para {string}")
    public void validaRelevancia(String termo) {
    }

    @Then("nao deve haver conteudos irrelevantes nas primeiras posicoes")
    public void validaIrrelevancia() {
    }

    @Then("os resultados devem estar ordenados por {string}")
    public void validaOrdenacao(String criterio) {
    }

    @When("acessa a proxima pagina de resultados")
    public void acessaProximaPagina() {
    }

    @Then("novos resultados devem ser exibidos")
    public void validaNovosResultados() {
    }

    @Then("nao deve repetir os resultados anteriores")
    public void validaNaoRepeticao() {
    }

    @When("clica em um dos resultados")
    public void clicaEmResultado() {
    }

    @Then("o artigo deve ser carregado corretamente")
    public void validaArtigo() {
    }

    @Then("o titulo deve corresponder ao resultado clicado")
    public void validaTitulo() {
    }

    @Then("os resultados devem ser relevantes para {string}")
    public void validaRelevanciaGeral(String termo) {
    }

    @Then("o sistema nao deve apresentar erro")
    public void validaSemErro() {
    }

    @Then("os resultados devem ser exibidos corretamente")
    public void validaResultadosOK() {
    }

    @Then("o sistema deve tratar a busca corretamente")
    public void validaTratamentoBusca() {
    }

    @Then("nao deve apresentar falha visual")
    public void validaLayout() {
    }
}