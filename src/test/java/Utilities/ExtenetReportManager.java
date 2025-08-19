package Utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Listeners;

import java.util.Date;
import java.util.List;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import TestBase.BaseClass;
@Listeners(Utilities.ExtenetReportManager.class)
public class ExtenetReportManager implements ITestListener {

	public ExtentSparkReporter sparkReporter;
	
	public ExtentReports extent;
	
	public ExtentTest test;
    public	ITestContext testContext;
	public String repName;
	
	public void onStart(ITestContext context)
	{
		
		SimpleDateFormat SD=new SimpleDateFormat();
		Date dt=new Date();
		String timeStamp=SD.format(dt);
		
		// approach-2 for creating timestamp
		//	String timestamp=new SimpleDateFormat("yyyy.mm.dd.hh.mm.ss").format(new Date());
		
		repName="Test_Report_"+timeStamp+".html";
		
		sparkReporter =new ExtentSparkReporter(System.getProperty("user.dir")+"\\Reports\\"+repName);
		
		sparkReporter.config().setDocumentTitle("OpenCart Automation");
		sparkReporter.config().setReportName("OpenCart Functional Testing");
		
		sparkReporter.config().setTheme(Theme.STANDARD);
		
		extent =new ExtentReports();
		
		extent.attachReporter(sparkReporter);
		
		extent.setSystemInfo("Application ", "OpenCart");
		extent.setSystemInfo("Module ", "ADMIN");
		extent.setSystemInfo("Sub Module ", "Customer");
		extent.setSystemInfo("UserName ", "Customers");
		extent.setSystemInfo("Enivornment", "QA");
		extent.setSystemInfo("Tester Name", "MD_ZABI");
		String os=testContext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System", os);
		String browser=testContext.getCurrentXmlTest().getParameter("browser");
		
		
		
		extent.setSystemInfo("Browser", browser);
		
		List<String> includedGroups=testContext.getCurrentXmlTest().getIncludedGroups();
          if(!includedGroups.isEmpty())
          {
        	  extent.setSystemInfo("Groups", includedGroups.toString());
          }
		
	
	}
	
	public void onTestSuccess(ITestResult result)
	{
		test= extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		
		test.log(Status.PASS, "Test Case Got Succesfully Executed : "+ result.getName());
		
	}
	
	public void onTestFailure(ITestResult result)
	{
		test= extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL, "Test Case Got Failed : "+ result.getName());
		
		test.log(Status.INFO, "Test Case  FailED Becaus OF : "+ result.getThrowable().getMessage());
		try {
			
			String imgpath=new BaseClass().CapturesScreen(result.getName());
			test.addScreenCaptureFromPath(imgpath);
			
		}catch(Exception ee)
		{
			ee.printStackTrace();
		}
		
		
		
	}
	
	public void onTestSkipped(ITestResult result)
	{
		test= extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, "Test Case is SKIPED : "+ result.getName());
		test.log(Status.INFO, "Test Case  is Skipped Because OF : "+ result.getThrowable().getMessage());
	
	}
	public void onFinish(ITestContext context)
	{
		extent.flush();
	
		String pathofReport=System.getProperty("user.dir")+"\\Reports\\"+repName;
	
		 File extentReport=new File(pathofReport);
	try {
		Desktop.getDesktop().browse(extentReport.toURI());
	}
	catch(Exception oo)
	{
		oo.printStackTrace();
	}
	}
	
	
	
	
	
	
	
	
}
