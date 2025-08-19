package TestCase;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import PageObjects.Account_Registration;
import PageObjects.HomePage;
import TestBase.BaseClass;
import ch.qos.logback.classic.Logger;
public class TC001_AccRegTest extends BaseClass
{


	
	@Test(groups = { "sanity"})
	public void verify_Acc_Registration() throws InterruptedException
	{
		try
		{
		logger.info("******** starting Execution of HomePage **********");
		HomePage  obj=new HomePage(driver);
		obj.click_MyAccount();
		obj.click_Register();
		logger.info("******** starting Execution of Account_Registration Page **********");
		Account_Registration Ar=new Account_Registration(driver);
		/*Ar.setFirstName("MOHAMMAD");
		Ar.setLastName("JABIVULLA");
		Ar.setEmail("mdjabi99@gmail.com");
		Ar.setPassword("Boi@123$*");
		Ar.subscribe_button();
		Ar.agree();
		Ar.ClickSubmit();  
		
		*/
		logger.info("******** Entering UserName And Password **********");
		Ar.setFirstName(BaseClass.RanString().toUpperCase());
		Ar.setLastName(BaseClass.RanString().toUpperCase());
		
		logger.info("******** Entering Email ID **********");
		Ar.setEmail(BaseClass.Ranemail());
		Ar.setPassword(BaseClass.RanString().toUpperCase()+BaseClass.Ranemail());
		logger.info("******** Accepting Submit Buttons **********");
		Ar.subscribe_button();
		Ar.agree();
		Ar.ClickSubmit();
		
		logger.info("******** Validating Confirmation Message  **********");
		String Actual_Text="YYour1 Account Has Been Created!";
		String confirm_txt=Ar.getConfirmMsg();
		
		if (Actual_Text.equals(confirm_txt))
		{
			Assert.assertTrue(true);
			logger.info("******** Registration Test Case got success  **********");
		}
		else
		{
			System.out.println("Validation Got Failed");		
			Assert.assertTrue(false);
		}
			
		
		}
		catch(Exception ee)
		{
			logger.error("Test Got Failed");
			logger.debug("Debug Logs..");;
			System.out.println("Printing Exception Test Case Got Failed : "+ ee);
			
		}
	}
	
	
	
	
	
	
	
}
