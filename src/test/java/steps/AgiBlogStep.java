package steps;

import org.openqa.selenium.WebDriver;

import config.DriverManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.AgiBlog;

public class AgiBlogStep {

	private WebDriver driver;
	private AgiBlog agi;

	public AgiBlogStep() {
		this.driver = DriverManager.getDriver();
		this.agi = new AgiBlog(driver);
	}
	// ---------- GIVEN ----------

	@Given("que o usuario acessa o blog do Agibank")
	public void acessaBlog() {
		// TODO: abrir navegador e acessar URL
	}

	@Given("clica na lupa de busca")
	public void clicaNaLupa() {
		// TODO: clicar no ícone de busca
	}

	// ---------- WHEN ----------

	@When("ele busca por {string}")
	public void buscaPor(String termo) {
		// TODO: digitar termo e executar busca
	}

	@When("acessa a proxima pagina de resultados")
	public void acessaProximaPagina() {
		// TODO: clicar na paginação
	}

	@When("clica em um dos resultados")
	public void clicaEmResultado() {
		// TODO: clicar em um resultado da lista
	}

	// ---------- THEN ----------

	@Then("o campo de busca deve ser exibido corretamente")
	public void validaCampoBusca() {
		// TODO: validar input visível
	}

	@Then("os resultados devem conter conteudos relacionados a {string}")
	public void validaConteudoRelacionado(String categoria) {
		// TODO: validar conteúdo por categoria
	}

	@Then("pelo menos {int} resultados devem ser exibidos")
	public void validaQuantidadeResultados(Integer quantidade) {
		// TODO: validar quantidade mínima
	}

	@Then("os primeiros resultados devem ser relevantes para {string}")
	public void validaRelevancia(String termo) {
		// TODO: validar relevância dos primeiros resultados
	}

	@Then("nao deve haver conteudos irrelevantes nas primeiras posicoes")
	public void validaIrrelevancia() {
		// TODO: validar ausência de conteúdo irrelevante
	}

	@Then("os resultados devem estar ordenados por {string}")
	public void validaOrdenacao(String criterio) {
		// TODO: validar ordenação
	}

	@Then("novos resultados devem ser exibidos")
	public void validaNovosResultados() {
		// TODO: validar novos resultados
	}

	@Then("nao deve repetir os resultados anteriores")
	public void validaNaoRepeticao() {
		// TODO: validar não repetição
	}

	@Then("o artigo deve ser carregado corretamente")
	public void validaArtigo() {
		// TODO: validar carregamento do artigo
	}

	@Then("o titulo deve corresponder ao resultado clicado")
	public void validaTitulo() {
		// TODO: validar título
	}

	@Then("os resultados devem ser relevantes para {string}")
	public void validaRelevanciaGeral(String termo) {
		// TODO: validação geral
	}

	@Then("o sistema nao deve apresentar erro")
	public void validaSemErro() {
		// TODO: validar ausência de erro
	}

	@Then("os resultados devem ser exibidos corretamente")
	public void validaResultadosOK() {
		// TODO: validar renderização
	}

	@Then("o sistema deve tratar a busca corretamente")
	public void validaTratamentoBusca() {
		// TODO: validar tratamento
	}

	@Then("nao deve apresentar falha visual")
	public void validaLayout() {
		// TODO: validar layout
	}
}