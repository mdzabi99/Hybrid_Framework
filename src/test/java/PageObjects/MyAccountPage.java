package PageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyAccountPage extends BasePage 
{
	
	
	public MyAccountPage(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(xpath="//span[normalize-space()='My Account']") WebElement my_acct;
	
	
	@FindBy(xpath="//h1[text()='My Account']") WebElement MyAccountPage_TxtMsg;
	
	@FindBy(xpath="//ul//a[text()='Logout']") WebElement LogoutLink;
	
	JavascriptExecutor js=(JavascriptExecutor)driver;
	
	public boolean MyAccountPageExists()
	{
		try
		{
		boolean b=MyAccountPage_TxtMsg.isDisplayed();
		return b;
		}
		catch(Exception ee)
		{
			return false;
		}
		
	}
    
	public void LogoutMethod() throws InterruptedException
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		my_acct.click();
		Thread.sleep(2000);
		
		LogoutLink.click();
		
	}
	
	
	
	
	
}
