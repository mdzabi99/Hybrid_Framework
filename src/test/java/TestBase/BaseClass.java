package TestBase;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import org.apache.commons.lang3.RandomStringUtils;

public class BaseClass {

    public static WebDriver driver;
    public static final Logger logger = LogManager.getLogger(BaseClass.class);
    public Properties prop;

    @BeforeClass(groups = {"sanity"})
    @Parameters({"browser", "os"})
    public void setUp(String browser, String os) throws Exception {

       

        String path = "C:\\Users\\mdjab\\git\\Hybrid_Framework\\src\\test\\resources\\config.properties";
        FileInputStream file = new FileInputStream(path);
        prop = new Properties();
        prop.load(file);
       
        String executionEnv = prop.getProperty("execution_env");
        if (executionEnv.equalsIgnoreCase("remote"))
        {
            
        	 MutableCapabilities options;
            // Create browser options first
            switch (browser.toLowerCase())
            {
                case "chrome":
                    options = new ChromeOptions();
                    break;

                case "firefox":
                    options = new FirefoxOptions();
                    break;

                default:
                    System.out.println("Browser not supported");
                    return;
            }

         
            options.setCapability("platformName", "LINUX");

            driver = new RemoteWebDriver(new java.net.URL("http://localhost:4444/"), options);

        } 
        else if (executionEnv.equalsIgnoreCase("local")) 
        {
            switch (browser.toLowerCase())
            {
                case "chrome":
                    System.setProperty("webdriver.chrome.driver", "C:\\Users\\mdjab\\git\\Hybrid_Framework\\config\\chromedriver.exe");
                    ChromeOptions chromeOpt = new ChromeOptions();
                    chromeOpt.setAcceptInsecureCerts(true);
                    driver = new ChromeDriver(chromeOpt);
                    break;
                case "edge":
                    System.setProperty("webdriver.edge.driver", "C:\\Users\\mdjab\\git\\Hybrid_Framework\\config\\msedgedriver.exe");
                    EdgeOptions edgeOpt = new EdgeOptions();
                    edgeOpt.setAcceptInsecureCerts(true);
                    driver = new EdgeDriver(edgeOpt);
                    break;
                case "firefox":
                    System.setProperty("webdriver.gecko.driver", "C:\\Users\\mdjab\\git\\Hybrid_Framework\\config\\geckodriver.exe");
                    driver = new FirefoxDriver();
                    break;
                default:
                    System.out.println("No matching browser for local execution");
                    return;
            }
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().deleteAllCookies();
        driver.get(prop.getProperty("url"));
    }

    public static String RanString() {
        return RandomStringUtils.randomAlphabetic(5);
    }

    public static String RanNum() {
        return RandomStringUtils.randomNumeric(10);
    }

    public static String Ranemail() {
        String subpass1 = RandomStringUtils.randomAlphabetic(5);
        String subpass2 = RandomStringUtils.randomNumeric(3);
        return subpass1 + "$456*" + subpass2 + "@gmail.com";
    }

    public String CapturesScreen(String tname) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        File src = ts.getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir") + "\\screenShorts\\" + tname + "_" + timestamp + ".png";
        File target = new File(path);
        src.renameTo(target);
        return path;
    }

    @AfterClass(groups = {"sanity"})
    public void tearDown() {
       
            driver.quit();
        
    }
}
