package util;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporter {

    public static ExtentReports getExtentReport(){
        String extentReportFilePath = System.getProperty("user.dir")+"\\reports\\extentreport.html";
        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(extentReportFilePath);
        extentSparkReporter.config().setReportName("TutorialNinja Automation Result");
        extentSparkReporter.config().setDocumentTitle("Tutorial Ninja Test Automation Result");

        ExtentReports extentReports = new ExtentReports();
        extentReports.attachReporter(extentSparkReporter);
        extentReports.setSystemInfo("Selenium Version" , "4.38.0");
        extentReports.setSystemInfo("Operating System", "Windows 11");
        extentReports.setSystemInfo("Executred By", "Atanu");

        return extentReports;
    }
}

