package PageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyAccountPage extends BasePage {
	public MyAccountPage(WebDriver driver)
	{
		super(driver);
	}
	
	
	
	@FindBy(xpath="//h1[text()='My Account']") WebElement MyAccountPage_TxtMsg;
	
	@FindBy(xpath="(//a[text()='Logout'])[2]") WebElement LogoutLink;
	
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
		Thread.sleep(2000);
		//js.executeScript("arguments[0].click()", LogoutLink);
		//LogoutLink.click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Login']")));
		loginBtn.click();
	}
	
	
	
	
	
}
