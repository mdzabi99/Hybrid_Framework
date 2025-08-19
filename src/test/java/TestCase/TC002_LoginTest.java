package TestCase;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.HomePage;
import PageObjects.LoginPage;
import PageObjects.MyAccountPage;
import TestBase.BaseClass;

public class TC002_LoginTest  extends BaseClass {

	
	@Test(groups = {"regression"})
	public void verify_login() throws Exception
	{
		
		String path="C:\\Users\\live tech\\eclipse-workspace\\Hybrid\\src\\test\\resources\\config.properties";
		FileInputStream file=new FileInputStream(path);
		Properties	p = new Properties();
		p.load(file);
		try
		{
		logger.info("******** started Execution of HomePage **********");
		
		HomePage hp=new HomePage(driver);
		hp.click_MyAccount();
		
		logger.info("******** Suucessfully Entered into Home Page **********");
		hp.click_Login();
		
		
		logger.info("******** started Execution of Login Page **********");
		
		
		LoginPage Lp=new LoginPage(driver);
		
		
		Lp.setEmail(p.getProperty("email") );
		logger.info("******** Suucessfully Navigated to Login Page **********");
		
	
		Lp.setPassword(p.getProperty("password") );
		Lp.Click_loginButton();
		logger.info("******** started Execution of MyAccountPage Page **********");
		
		
		MyAccountPage My=new MyAccountPage(driver);
		boolean text_MyAccnt=My.MyAccountPageExists();
		logger.info("******** Finished TC002 LoginPage successfully **********");
		Assert.assertTrue(text_MyAccnt);
	    }
	    catch(Exception ee)
		{
	    	logger.error("Login Test Got Failed");
			logger.debug("Debug Logs..");;
			System.out.println("Printing Exception Test Case Got Failed : "+ ee);
			
		    Assert.fail();
		
		}
	
	
	
	}
	
	
}
