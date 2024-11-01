package TestComponents;

import Resourses.ExtentReport;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class Listners extends BaseTest implements ITestListener {
    ExtentReports extent = ExtentReport.getReport();
    public ExtentTest test;
    WebDriver driver;
    @Override
    public void onTestStart(ITestResult result) {
        // Code to execute when a test starts
         test  = extent.createTest(result.getMethod().getMethodName());

    }
    @Override
    public void onTestSuccess(ITestResult result) {
        // Code to execute when a test succeeds
        test.log(Status.PASS,result.getMethod().getMethodName()+" test passesd");
    }
    @Override
    public void onTestFailure(ITestResult result) {
        test.log(Status.FAIL, "test got fail");
        // Code to execute when a test fails
        try {
            driver=(WebDriver) result.getTestClass().getRealClass().getField("driver")
                    .get(result.getInstance());
        } catch (Exception e1 ) {
            e1.printStackTrace();
        }
        String path= null;
        try {
            path = getScreenShort(result.getMethod().getMethodName(), driver);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        test.addScreenCaptureFromPath(path,result.getMethod().getMethodName());
    }
    @Override
    public void onTestSkipped(ITestResult result) {
        // Code to execute when a test is skipped
        test.log(Status.SKIP,result.getMethod().getMethodName()+" :skipped");
    }
    @Override
    public void onStart(ITestContext context) {
        // Code to execute before any test method is run
    }
    @Override
    public void onFinish(ITestContext context) {
        // Code to execute after all test methods have run
        extent.flush();
    }

}
