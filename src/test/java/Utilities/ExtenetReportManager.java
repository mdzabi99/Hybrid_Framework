package Utilities;

import java.awt.Desktop;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import TestBase.BaseClass;
//@Listeners(Utilities.ExtenetReportManager.class)
public class ExtenetReportManager implements ITestListener {

    public ExtentSparkReporter sparkReporter; // UI of report
    public ExtentReports extent;              // Report engine
    public ExtentTest test;                   // Test logger
    public String repName;

    @Override
    public void onStart(ITestContext context) {
        // ✅ Correct timestamp (valid for Windows filenames)
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        repName = "Test_Report_" + timeStamp + ".html";

        // ✅ Ensure Reports folder exists
        new File(System.getProperty("user.dir") + "\\Reports").mkdirs();

        // ✅ Report file path
        sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "\\Reports\\" + repName);

        sparkReporter.config().setDocumentTitle("OpenCart Automation Report");
        sparkReporter.config().setReportName("OpenCart Functional Testing");
        sparkReporter.config().setTheme(Theme.STANDARD);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        // ✅ System info
        extent.setSystemInfo("Application", "OpenCart");
        extent.setSystemInfo("Module", "ADMIN");
        extent.setSystemInfo("Sub Module", "Customer");
        extent.setSystemInfo("UserName", "Customers");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("Tester Name", "MD_ZABI");

        // ✅ Fetching from testng.xml <parameter>
        String os = context.getCurrentXmlTest().getParameter("os");
        if (os != null) extent.setSystemInfo("Operating System", os);

        String browser = context.getCurrentXmlTest().getParameter("browser");
        if (browser != null) extent.setSystemInfo("Browser", browser);

        // ✅ Fetch groups info
        List<String> includedGroups = context.getCurrentXmlTest().getIncludedGroups();
        if (!includedGroups.isEmpty()) {
            extent.setSystemInfo("Groups", includedGroups.toString());
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName()); // test name
        test.assignCategory(result.getMethod().getGroups());          // assign groups
        test.log(Status.PASS, "Test Case Passed: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.FAIL, "Test Case Failed: " + result.getName());
        test.log(Status.INFO, "Reason: " + result.getThrowable().getMessage());

        try {
            String imgPath = new BaseClass().CapturesScreen(result.getName());
            test.addScreenCaptureFromPath(imgPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.SKIP, "Test Case Skipped: " + result.getName());
        if (result.getThrowable() != null) {
            test.log(Status.INFO, "Reason: " + result.getThrowable().getMessage());
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush(); // ✅ Write all logs to report

        // ✅ Auto open report in browser
        try {
            String pathOfReport = System.getProperty("user.dir") + "\\Reports\\" + repName;
            File extentReport = new File(pathOfReport);
            Desktop.getDesktop().browse(extentReport.toURI());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
