package utils;

import com.aventstack.extentreports.ExtentTest;
import io.cucumber.java.Scenario;

public class TestContext {
	private static ThreadLocal<Scenario> scenario = new ThreadLocal<>();
	private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

	public static void setScenario(Scenario s) {
		scenario.set(s);
	}

	public static Scenario getScenario() {
		return scenario.get();
	}

	public static void setExtentTest(ExtentTest test) {
		extentTest.set(test);
	}

	public static ExtentTest getExtentTest() {
		return extentTest.get();
	}
}
