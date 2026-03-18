package hooks;

import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import config.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import report.ExtentReport;
import utils.MetodoUtils; 
import utils.TestContext;

public class Hooks {
    private static Scenario currentScenario;
    private static ExtentReports extent = ExtentReport.getExtentReports();

    @Before
    public void beforeScenario(Scenario scenario) throws IOException {
        currentScenario = scenario;
        TestContext.setScenario(scenario);
        MetodoUtils.resetScreenshotCounter();

        ExtentTest test = extent.createTest(scenario.getName());
        TestContext.setExtentTest(test);

        boolean isWeb = scenario.getSourceTagNames().stream()
            .map(String::trim)
            .map(String::toLowerCase)
            .anyMatch(tag -> tag.equals("@web"));
        if (isWeb) {
            DriverManager.getDriver();
            DriverManager.navigateToBaseUrl();
        }
    }

    @After
    public void tearDown(Scenario scenario) {
        ExtentTest test = TestContext.getExtentTest();

        if (scenario.isFailed()) {
            test.fail("❌ Scenario failed");
        } else {
            test.pass("✅ Scenario passed");
        }

        if (DriverManager.getDriver() != null) {
            DriverManager.quitDriver();
        }
    }

    public static String getScenarioName() {
        if (currentScenario != null) {
            return currentScenario.getName().replaceAll("[^a-zA-Z0-9\\-_]", "_");
        }
        return "unknown_scenario";
    }
}
