package pages;

import static config.DriverManager.getDriver;
import static utils.MetodoUtils.isElementVisible;

import java.time.Duration;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.github.javafaker.Faker;

import config.DriverManager;
import utils.MetodoUtils;
import utils.TestContext;

public class fillPage {
    private WebDriver driver;
    private MetodoUtils metodo;

    private Faker faker = new Faker();
    private String firstName = faker.name().firstName();
    private String lastName = faker.name().lastName();
    private String company = faker.company().name();
    private String address = faker.address().streetAddress();
    private String state = faker.address().state();
    private String city = faker.address().cityName();
    private String zipCode = faker.address().zipCode();
    private String phone = faker.phoneNumber().cellPhone();
    private String email = faker.internet().emailAddress();
    private String password = faker.internet().password(8, 12);
    private String description = "";

    public fillPage(WebDriver driver) {
        this.driver = driver;
        this.metodo = new MetodoUtils(driver);
    }

    // Pega o nome do cenário atual
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
        String url = "https://automationexercise.com/";
        String getUrl = DriverManager.getDriver().getCurrentUrl();
        Assert.assertTrue("URL incorreta ao acessar o site", getUrl.contains(url));
    }

    public void validateHomePage() {
        System.out.println("validate home page");
        isElementVisible(driver, By.xpath("//a[normalize-space()='Home']"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[normalize-space()='Home']")));
        Assert.assertEquals("Home", el.getText().trim());
        MetodoUtils.takeStepScreenshot(getDriver(), getScenarioName());
    }

    public void accessSignup() {
        System.out.println("access signup");
        isElementVisible(driver, By.xpath("//a[normalize-space()='Signup / Login']"));
        metodo.clickElementByXpath("//a[normalize-space()='Signup / Login']");
        WebElement inputNome = driver.findElement(By.xpath("//form[@action='/signup']//input[@name='name']"));
        WebElement inputEmail = driver.findElement(By.xpath("//form[@action='/signup']//input[@name='email']"));
        WebElement btnSignup = driver.findElement(By.xpath("//form[@action='/signup']//button[@type='submit']"));
        inputNome.sendKeys(firstName);
        inputEmail.sendKeys(email);
        MetodoUtils.takeStepScreenshot(getDriver(), getScenarioName());
        btnSignup.click();
    }

    private void birth() {
        Select selectDays = new Select(driver.findElement(By.id("days")));
        selectDays.selectByVisibleText("1");
        Select selectMonth = new Select(driver.findElement(By.id("months")));
        selectMonth.selectByVisibleText("January");
        Select selectYear = new Select(driver.findElement(By.id("years")));
        selectYear.selectByVisibleText("1997");
    }

    public void fillRegistrationForm() {
        System.out.println("registration");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//b[normalize-space()='Enter Account Information']")));
        Assert.assertEquals("enter account information", el.getText().trim().toLowerCase());
        metodo.clickElementById("uniform-id_gender1");
        metodo.typeElement("password", password);
        birth();
        metodo.typeElement("first_name", firstName);
        metodo.typeElement("last_name", lastName);
        metodo.typeElement("company", company);
        metodo.typeElement("address1", address);
        Select selectCountry = new Select(driver.findElement(By.id("country")));
        selectCountry.selectByVisibleText("Australia");
        metodo.typeElement("state", state);
        metodo.typeElement("city", city);
        metodo.typeElement("zipcode", zipCode);
        metodo.typeElement("mobile_number", phone);
        MetodoUtils.saveData(firstName, lastName, email, password, address);
        MetodoUtils.takeStepScreenshot(getDriver(), getScenarioName());
    }

    public void clickBtnCreateAccount() {
        System.out.println("create account");
        isElementVisible(driver, By.xpath("//button[normalize-space()='create-account']"));
        metodo.clickElementByXpath("//button[normalize-space()='create-account']");
        MetodoUtils.takeStepScreenshot(getDriver(), getScenarioName());
    }

    public void validateAccountCreated(String msg) {
        System.out.println("validate account created");
        isElementVisible(driver, By.xpath("//b[normalize-space()='Account Created!']"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//b[normalize-space()='Account Created!']")));
        Assert.assertEquals(msg.toUpperCase(), el.getText().trim().toUpperCase());
        MetodoUtils.takeStepScreenshot(getDriver(), getScenarioName());
    }

    public void accessSignupAndLogin() {
        System.out.println("login");
        isElementVisible(driver, By.xpath("//a[normalize-space()='Signup / Login']"));
        metodo.clickElementByXpath("//a[normalize-space()='Signup / Login']");
        MetodoUtils.takeStepScreenshot(getDriver(), getScenarioName());
    }

    public void realizeLogin() {
        System.out.println("realize login");
        WebElement inputEmail = driver.findElement(By.xpath("//form[@action='/login']//input[@name='email']"));
        WebElement inputPass = driver.findElement(By.xpath("//form[@action='/login']//input[@type='password']"));
        WebElement btnSignup = driver.findElement(By.xpath("//form[@action='/login']//button[@type='submit']"));

        String email = MetodoUtils.readCell(1, "Email");
        String password = MetodoUtils.readCell(1, "Password");
        System.out.println("email: " + email + " password: " + password);

        inputEmail.sendKeys(email);
        inputPass.sendKeys(password);
        MetodoUtils.takeStepScreenshot(getDriver(), getScenarioName());
        btnSignup.click();
        MetodoUtils.takeStepScreenshot(getDriver(), getScenarioName());
    }

    public void validateLoginSuccess() {
        System.out.println("validate login");
        isElementVisible(driver, By.xpath("//i[@class='fa fa-user']"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[@class='fa fa-user']")));
        Assert.assertTrue("Element not found", el.isDisplayed());
        MetodoUtils.takeStepScreenshot(getDriver(), getScenarioName());
    }

    public void accessProducts() {
        System.out.println("products");
        isElementVisible(driver, By.xpath("//a[@href='/products']"));
        metodo.clickElementByXpath("//a[@href='/products']");
        MetodoUtils.takeStepScreenshot(getDriver(), getScenarioName());
    }

    public void addProduct() {
        System.out.println("add products");
        isElementVisible(driver, By.xpath("(//img[@alt='ecommerce website products'])[1]"));
        metodo.clickElementByXpath("(//a[contains(text(),'View Product')])[1]");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class='product-information'] h2")));
        description = el.getText().trim();
        metodo.clickElementByXpath("//button[normalize-space()='Add to cart']");
        WebElement cart = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[normalize-space()='Added!']")));
        Assert.assertEquals("Added!", cart.getText().trim());
        MetodoUtils.takeStepScreenshot(getDriver(), getScenarioName());
    }

    public void accessCartByProduct() {
        System.out.println("access cart by product added");
        isElementVisible(driver, By.xpath("//u[normalize-space()='View Cart']"));
        metodo.clickElementByXpath("//u[normalize-space()='View Cart']");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@class='cart_description']//h4")));
        Assert.assertEquals(description, el.getText().trim());
        MetodoUtils.takeStepScreenshot(getDriver(), getScenarioName());
    }

    public void removeProduct() {
        System.out.println("remove");
        isElementVisible(driver, By.xpath("//a[@class='cart_quantity_delete']"));
        metodo.clickElementByXpath("//a[@class='cart_quantity_delete']");
        MetodoUtils.takeStepScreenshot(getDriver(), getScenarioName());
    }

    public void validateRemoveProductMsg(String msg) {
        System.out.println("validate remove product");
        isElementVisible(driver, By.xpath("//b[normalize-space()='Cart is empty!']"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//b[normalize-space()='Cart is empty!']")));
        Assert.assertEquals(msg, el.getText().trim());
        MetodoUtils.takeStepScreenshot(getDriver(), getScenarioName());
    }

    public void realizeLogout() {
        System.out.println("logout");
        isElementVisible(driver, By.xpath("//a[normalize-space()='Logout']"));
        metodo.clickElementByXpath("//a[normalize-space()='Logout']");
        MetodoUtils.takeStepScreenshot(getDriver(), getScenarioName());
    }

    public void validateInicialPageAfterLogout() {
        System.out.println("validate inicial page after logout");
        validateHomePage();
        isElementVisible(driver, By.xpath("//h2[normalize-space()='Login to your account']"));
        isElementVisible(driver, By.xpath("//h2[normalize-space()='New User Signup!']"));
        MetodoUtils.takeStepScreenshot(getDriver(), getScenarioName());
    }
}
