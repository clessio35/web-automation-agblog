package steps;

import org.openqa.selenium.WebDriver;

import config.DriverManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.fillPage;

public class fillTravelsStep {
    private WebDriver driver;
    private fillPage web;

    public fillTravelsStep() {
        this.driver = DriverManager.getDriver();
        this.web = new fillPage(driver);
    }

    @Given("que o usuario acessa o site")
    public void que_o_usuario_acessa_o_site() {
        web.validateUrlAccess();
    }

    @Then("a pagina inicial deve ser carregada corretamente")
    public void a_pagina_inicial_deve_ser_carregada_corretamente() {
        web.validateHomePage();
    }

    @Given("que o usuario esta na pagina de Signup")
    public void que_o_usuario_esta_na_pagina_de_signup() {
        web.accessSignup();
    }

    @When("ele preenche os dados obrigatorios com valores do banco")
    public void ele_preenche_os_dados_obrigatorios_com_valores_do_banco() {
        web.fillRegistrationForm();
    }

    @When("confirma o cadastro")
    public void confirma_o_cadastro() {
        web.clickBtnCreateAccount();
    }

    @Then("o sistema deve exibir a mensagem {string}")
    public void o_sistema_deve_exibir_a_mensagem(String msg) {
        web.validateAccountCreated(msg);
    }

    @Given("que o usuario esta na pagina de login")
    public void que_o_usuario_esta_na_pagina_de_login() {
        web.accessSignupAndLogin();
    }

    @When("ele realiza login com os dados do banco")
    public void ele_realiza_login_com_os_dados_do_banco() {
        web.realizeLogin();
    }

    @Then("o sistema deve exibir a mensagem de login com sucesso")
    public void o_sistema_deve_exibir_a_mensagem_de_login_com_sucesso() {
        web.validateLoginSuccess();
    }

    @Given("que o usuario esta logado")
    public void que_o_usuario_esta_logado() {
        web.accessSignupAndLogin();
        web.realizeLogin();
    }

    @When("ele adiciona um produto ao carrinho pela pagina de produtos")
    public void ele_adiciona_um_produto_ao_carrinho_pela_pagina_de_produtos() {
        web.accessProducts();
        web.addProduct();
    }

    @Then("o carrinho deve conter o produto adicionado com o nome correto")
    public void o_carrinho_deve_conter_o_produto_adicionado_com_o_nome_correto() {
        web.accessCartByProduct();
    }

    @Given("que o usuario possui um produto no carrinho")
    public void que_o_usuario_possui_um_produto_no_carrinho() {
        web.accessSignupAndLogin();
        web.realizeLogin();
        web.accessProducts();
        web.addProduct();
        web.accessCartByProduct();
    }

    @When("ele remove o produto do carrinho")
    public void ele_remove_o_produto_do_carrinho() {
        web.removeProduct();
    }

    @Then("o sistema deve exibir a mensagem de exclusao {string}")
    public void o_sistema_deve_exibir_a_mensagem_de_exclusao(String msg) {
        web.validateRemoveProductMsg(msg);
    }

    @Given("que o usuario esta logado na aplicacao")
    public void que_o_usuario_esta_logado_na_aplicacao() {
        web.accessSignupAndLogin();
        web.realizeLogin();
    }

    @When("ele realiza o logout")
    public void ele_realiza_o_logout() {
        web.realizeLogout();
    }

    @Then("o sistema deve redirecionar para a pagina de login ou home publica")
    public void o_sistema_deve_redirecionar_para_a_pagina_de_login_ou_home_publica() {
        web.validateInicialPageAfterLogout();
    }
}
