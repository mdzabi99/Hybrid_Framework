package TestCase;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.HomePage;
import PageObjects.LoginPage;
import PageObjects.MyAccountPage;
import TestBase.BaseClass;
import Utilities.*;
public class TC003_LoginTest extends BaseClass
{

	@Test(dataProvider="LoginData" , dataProviderClass =DataProvides.class,groups = {"smoke"})
	public void verify_login(String email,String Password,String Exp)
	{
	
	try
	{
	logger.info("******** started Execution of HomePage **********");
	
	HomePage hp=new HomePage(driver);
	hp.click_MyAccount();
	
	logger.info("******** Suucessfully Entered into Home Page **********");
	hp.click_Login();
	
	
	logger.info("******** started Execution of Login Page **********");
	
	
	LoginPage Lp=new LoginPage(driver);
	
	
	Lp.setEmail(email );
	logger.info("******** Suucessfully Navigated to Login Page **********");
	

	Lp.setPassword(Password );
	Lp.Click_loginButton();
	logger.info("******** started Execution of MyAccountPage Page **********");
	
	
	MyAccountPage My=new MyAccountPage(driver);
	boolean text_MyAccnt=My.MyAccountPageExists();
	
	
    
   if(Exp.equalsIgnoreCase("Vaild_Data"))
   {
	   if(text_MyAccnt==true)
	   {
		   
		   My.LogoutMethod();
		   Assert.assertTrue(true);
		
	   }
	   else
	   {
		   Assert.assertTrue(false);  
	   }
   }
	
   if(Exp.equalsIgnoreCase("Invalid_Data"))
   {
	   if(text_MyAccnt==true)
	   {
		   My.LogoutMethod();
		   Assert.assertTrue(false);
		  
	   }
	   else
	   {
		   Assert.assertTrue(true);  
	   }
   }
	
	
	}
	catch(Exception ee)
	{
    	logger.error("Login Test Got Failed");
		logger.debug("Debug Logs..");
		System.out.println("Printing Exception Test Case Got Failed : "+ ee);
		logger.info("******** Finished TC002 LoginPage successfully **********");
	    Assert.fail();
	
	}



}
	
	
}
