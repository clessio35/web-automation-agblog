package report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentReport {
    private static ExtentReports extent;

    public synchronized static ExtentReports getExtentReports() {
        if (extent == null) {
            ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("target/extent-report.html");
            extent = new ExtentReports();
            extent.attachReporter(htmlReporter);
        }
        return extent;
    }
}