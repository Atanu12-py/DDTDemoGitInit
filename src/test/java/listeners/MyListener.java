package listeners;

import Base.Base;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import util.ExtentReporter;

public class MyListener implements ITestListener {

    ExtentReports extentReports = ExtentReporter.getExtentReport();
    ExtentTest extentTest ;
    @Override
    public void onTestStart(ITestResult result) {
        extentTest = extentReports.createTest(result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        extentTest.log(Status.FAIL, "Test Failed");
        extentTest.fail(result.getThrowable());
//        give me the code for taking screenshot and attaching to the report
        Object currentClass = result.getInstance();
        WebDriver driver = ((Base) currentClass).driver;
        String screenshotPath = ((Base) currentClass).takeScreenshot(result.getName(), driver);
        extentTest.addScreenCaptureFromPath(screenshotPath);

    }

    @Override
    public void onTestSkipped(ITestResult result) {
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
    }

    @Override
    public void onStart(ITestContext context) {
    }

    @Override
    public void onFinish(ITestContext context) {
        extentReports.flush();

    }
}
