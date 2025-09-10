package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
// https://demo.opencart.com/
	
	
	public WebDriver driver;
	public HomePage(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(xpath="//span[normalize-space()='My Account']") WebElement my_acc;
	
	@FindBy(xpath="//a[text()='Register']") WebElement lnk_Reg;
	
	@FindBy(xpath="//a[text()='Login']")  WebElement Login_Link;
	
	public void click_MyAccount()
	{
		my_acc.click();
	}
	public void click_Register()
	{
		lnk_Reg.click();
	}
	
	public void click_Login() throws InterruptedException
	{
		Thread.sleep(2000);
		Login_Link.click();
	}
}
