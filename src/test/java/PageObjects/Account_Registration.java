package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Account_Registration extends BasePage {
	
	
	public Account_Registration(WebDriver driver)
	{
		super(driver);
	}
	JavascriptExecutor js=(JavascriptExecutor)driver;
	
	@FindBy(xpath = "//input[@id='input-firstname']") WebElement F_Name;
	
	@FindBy(xpath = "//input[@id='input-lastname']") WebElement L_Name;
	
	@FindBy(xpath = "//input[@id='input-email']") WebElement Email;
	

	@FindBy(xpath = "//input[@id='input-password']") WebElement Pass;
	
	WebElement agree=driver.findElement(By.name("agree"));
	
	@FindBy(xpath = "(//button[@type='submit'])[2]") WebElement button;
	@FindBy(xpath="//input[@id='input-newsletter']" ) WebElement subscribe;
	public void setFirstName(String fname)
	{
		F_Name.sendKeys(fname);
	}

	public void setLastName(String Lname)
	{
		L_Name.sendKeys(Lname);
	}
	

	public void setEmail(String email)
	{
		Email.sendKeys(email);
	}
	

	public void setPassword(String password)
	{
		Pass.sendKeys(password);
		
	}
	
	public void subscribe_button() throws InterruptedException
	{ Thread.sleep(2000);
	
js.executeScript("arguments[0].scrollIntoView();", subscribe);
		js.executeScript("arguments[0].click()", subscribe);
		//subscribe.click();
	}

	public void agree() throws InterruptedException
	{
		Thread.sleep(2000);
		js.executeScript("arguments[0].click()", agree);
		
		//agree.click();
	}
	

	
	public void ClickSubmit() throws InterruptedException
	{
		Thread.sleep(2000);
		js.executeScript("arguments[0].click()", button);
		//button.click();
	}
	
	//Your Account Has Been Created!
	@FindBy(xpath = "//div/h1[text()='Your Account Has Been Created!']")
	WebElement confirm_text;
	
	public String getConfirmMsg()
	{
		try {
			System.out.println(confirm_text.getText().toString());
			return (confirm_text.getText().toString());
			
		}catch(Exception e)
		{
			return(e.getMessage());
		}
	}
	
	
}
