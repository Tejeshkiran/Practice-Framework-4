package Resourses;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReport {

    public static ExtentReports getReport()
    {
        //ExtentReport
        //ExtentSparkReporter
        String path =System.getProperty("User.dir")+"//Reports/index.html";
        ExtentSparkReporter extentSpark = new ExtentSparkReporter(path);
        extentSpark.config().setDocumentTitle("AutomationTestResults");
        extentSpark.config().setDocumentTitle("TestResults");

        ExtentReports extent = new ExtentReports();
        extent.attachReporter(extentSpark);
        extent.setSystemInfo("Tejesh","tests");
        return extent;
    }
}
