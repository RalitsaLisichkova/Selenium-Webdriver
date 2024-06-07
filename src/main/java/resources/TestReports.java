package resources;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class TestReports {


    public static ExtentReports getReporterObject() {

        String file = "/Users/ralitsalisichkova/Desktop/pragmatic/SeleniumFrameworkDesign//reports//index.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(file);
        reporter.config().setReportName("Web Automation Results");
        reporter.config().setDocumentTitle("Test Results");
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester", "Ralitsa Lisichkova");
        return extent;
    }


}
