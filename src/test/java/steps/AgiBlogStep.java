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
//        agi.validateResultsByCategory(categoria);
    }

    // ================================
    // CENÁRIO: VALIDA RELEVÂNCIA DOS RESULTADOS
    // ================================
    @Then("os primeiros resultados devem ser relevantes para {string}")
    public void validaRelevancia(String termo) {
//        agi.validateTopResultsRelevance(termo);
    }

    @Then("nao deve haver conteudos irrelevantes nas primeiras posicoes")
    public void validaIrrelevancia() {
//        agi.validateNoIrrelevantTopResults();
    }

    // ================================
    // CENÁRIO: VALIDA ORDENAÇÃO DOS RESULTADOS
    // ================================
    @Then("os resultados devem estar ordenados por {string}")
    public void validaOrdenacao(String criterio) {
//        agi.validateResultsOrder(criterio);
    }

    // ================================
    // CENÁRIO: VALIDA PAGINAÇÃO DOS RESULTADOS
    // ================================
    @When("acessa a proxima pagina de resultados")
    public void acessaProximaPagina() {
//        agi.goToNextPage();
    }

    @Then("novos resultados devem ser exibidos")
    public void validaNovosResultados() {
//        agi.validateNewResults();
    }

    @Then("nao deve repetir os resultados anteriores")
    public void validaNaoRepeticao() {
//        agi.validateNoDuplicateResults();
    }

    // ================================
    // CENÁRIO: VALIDA ABERTURA DE ARTIGO
    // ================================
    @When("clica em um dos resultados")
    public void clicaEmResultado() {
//        agi.clickOnResult();
    }

    @Then("o artigo deve ser carregado corretamente")
    public void validaArtigo() {
//        agi.validateArticleLoaded();
    }

    @Then("o titulo deve corresponder ao resultado clicado")
    public void validaTitulo() {
//        agi.validateArticleTitle();
    }

    // ================================
    // CENÁRIO: VALIDA BUSCA POR INTENÇÃO
    // ================================
    @Then("os resultados devem ser relevantes para {string}")
    public void validaRelevanciaGeral(String termo) {
//        agi.validateResultsByIntent(termo);
    }

    // ================================
    // CENÁRIO: BUSCA COM CARACTERES ESPECIAIS
    // ================================
    @Then("o sistema nao deve apresentar erro")
    public void validaSemErro() {
//        agi.validateNoSystemError();
    }

    @Then("os resultados devem ser exibidos corretamente")
    public void validaResultadosOK() {
//        agi.validateResultsDisplayedCorrectly();
    }

    // ================================
    // CENÁRIO: ENTRADAS INVÁLIDAS
    // ================================
    @Then("o sistema deve tratar a busca corretamente")
    public void validaTratamentoBusca() {
//        agi.validateSearchHandledCorrectly();
    }

    @Then("nao deve apresentar falha visual")
    public void validaLayout() {
//        agi.validateNoVisualErrors();
    }
}