package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ReportUtils {
	 private static ExtentReports extent;
	    private static ExtentTest test;

	    public static void initReport() {
	        try {
	            Path reportDir = Paths.get("target", "extent-report");
	            Files.createDirectories(reportDir);

	            ExtentSparkReporter spark = new ExtentSparkReporter(reportDir.resolve("report.html").toString());
	            extent = new ExtentReports();
	            extent.attachReporter(spark);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    public static void flushReport() {
	        if (extent != null) {
	            extent.flush();
	        }
	    }

	    public static ExtentReports getExtent() {
	        return extent;
	    }

	    public static ExtentTest getExtentTest() {
	        return test;
	    }

	    public static void setExtentTest(ExtentTest t) {
	        test = t;
	    }
}
