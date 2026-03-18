package runners;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import report.ExtentReport;
import utils.ReportUtils;

@SuppressWarnings("deprecation")
@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features",
		glue = { "steps", "hooks" },
		tags = "@web", 
		plugin = {
			"pretty", "html:target/cucumber-reports.html", 
			"json:target/cucumber-report.json"
		},
		monochrome = true)

public class TestRunner {
	
	@BeforeClass
	public static void beforeAll() {
		ReportUtils.initReport(); 
	}

	@AfterClass
	public static void afterAll() {
		ExtentReport.getExtentReports().flush();
	}
}