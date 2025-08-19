package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage 
{

	public LoginPage(WebDriver driver)
	{
		super(driver);
	}
	
	
	
	By email_box=By.cssSelector("input#input-email");
	WebElement Email=driver.findElement(email_box);
	
	
	By Password_box=By.cssSelector("input#input-password");
	WebElement Password=driver.findElement(Password_box);
	
	@FindBy(xpath="//button[text()='Login']") WebElement Login_button;
	
	
	public void setEmail(String UserEmail)
	{
		Email.sendKeys(UserEmail);
	}
	
	public void setPassword(String UserPassword)
	{
		Password.sendKeys(UserPassword);
	}
	
	public void Click_loginButton()
	{
		Login_button.click();
	}
}
