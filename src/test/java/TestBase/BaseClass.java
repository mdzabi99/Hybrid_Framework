package TestBase;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import javax.imageio.stream.FileImageInputStream;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.beust.jcommander.Parameter;

import net.bytebuddy.asm.Advice.Argument;

import org.apache.commons.lang3.*;
public class BaseClass 
{

	public static WebDriver driver;
	public Logger logger;
	
	@BeforeClass(groups= {"sanity","regression","smoke"})
	@Parameters({"browser"})
	public void setUp(String br) throws InterruptedException, Exception
	{
		
		logger=LogManager.getLogger(this.getClass());
		
		switch (br.toLowerCase())  // safer, so "Chrome" or "edge" works
		{
		    case "chrome":
		    	System.setProperty("webdriver.edge.driver", "C:\\Users\\mdjab\\OneDrive\\Documents\\TelegramDesktop\\selenium\\hybrid\\Hybrid_Framework\\config\\chromedriver.exe");
		        ChromeOptions chromeOpt = new ChromeOptions();
		        chromeOpt.setAcceptInsecureCerts(true);
		        driver = new ChromeDriver(chromeOpt);
		        break;

		    case "edge":
		    	System.setProperty("webdriver.edge.driver", "C:\\Users\\mdjab\\OneDrive\\Documents\\TelegramDesktop\\selenium\\hybrid\\Hybrid_Framework\\config\\msedgedriver.exe");
		    	EdgeOptions opt=new EdgeOptions();
		    	opt.setAcceptInsecureCerts(true);
		        driver = new EdgeDriver(opt);  // Edge browser driver
		        break;

		    default:
		    	
		        //throw new IllegalArgumentException("Invalid browser: " + br);
		        return;
		}

		
	
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().deleteAllCookies();
		
		String path="C:\\Users\\mdjab\\OneDrive\\Documents\\TelegramDesktop\\selenium\\hybrid\\Hybrid_Framework\\src\\test\\resources\\config.properties";
		FileInputStream file=new FileInputStream(path);
		 Properties prop = new Properties();
		prop.load(file);

		
		
		driver.get(prop.getProperty("url"));
	
	
	}
	
	
	public static String RanString()
	{
		String GenString = RandomStringUtils.randomAlphabetic(5);
	   return GenString;
		
	}
	
	
	public static String RanNum()
	{
		 String Phn=RandomStringUtils.randomNumeric(10);
		return Phn;
	}
	
	public static String Ranemail()
	{
		  String subpass1=RandomStringUtils.randomAlphabetic(5);
		    String subpass2=RandomStringUtils.randomNumeric(3);
		    
		return (subpass1+"$456*"+subpass2+"@gmil.com");
	}
	
	
	
	public String CapturesScreen(String tname)
	{
		TakesScreenshot ts=(TakesScreenshot)driver;
		
		String timestamp=new SimpleDateFormat("yyyy.mm.dd.hh.mm.ss").format(new Date());
		File Src=ts.getScreenshotAs(OutputType.FILE);
		
		String path=System.getProperty("user.dir")+"\\screenShorts\\"+tname+timestamp;
		
		File targetpath =new File(path);
		
		Src.renameTo(targetpath);
		
		return path;
	}
	@AfterClass(groups= {"sanity","regression","smoke"})
		public void tearDown()
		{
			driver.quit();
		}
	
}
