package TestCase;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.HomePage;
import PageObjects.LoginPage;
import PageObjects.MyAccountPage;
import TestBase.BaseClass;
import Utilities.*;

public class TC004_LoginTest extends BaseClass
{
    @Test(dataProvider="LoginData" , dataProviderClass = DataProvides.class, groups = {"smoke"})
    public void verify_login(String email,String Password,String Exp) throws Exception
    {
        // Path to your Excel file (same as used in DataProvider)
        String path = "C:\\Users\\mdjab\\OneDrive\\Documents\\Telegram Desktop\\selenium\\hybrid\\Hybrid_Framework\\Test_Data\\Data_Provider.xlsx";
        ExcelUtility EU = new ExcelUtility(path);

        try {
            logger.info("******** started Execution of HomePage **********");

            HomePage hp = new HomePage(driver);
            hp.click_MyAccount();

            logger.info("******** Suucessfully Entered into Home Page **********");
            hp.click_Login();

            logger.info("******** started Execution of Login Page **********");

            LoginPage Lp = new LoginPage(driver);
            Lp.setEmail(email);
            Lp.setPassword(Password);
            Lp.Click_loginButton();

            logger.info("******** started Execution of MyAccountPage **********");

            MyAccountPage My = new MyAccountPage(driver);
            boolean text_MyAccnt = My.MyAccountPageExists();

            // Row index from DataProvider (skip header row)
            int rowIndex = getRowIndex(path, email, Password);

            if(Exp.equalsIgnoreCase("Vaild_Data")) {
                if(text_MyAccnt) {
                    My.LogoutMethod();
                    EU.setCellData("Sheet1", rowIndex, 3, "Pass");
                    EU.fillGreenColor("Sheet1", rowIndex, 3);
                    Assert.assertTrue(true);
                } else {
                    EU.setCellData("Sheet1", rowIndex, 3, "Fail");
                    EU.fillRedColor("Sheet1", rowIndex, 3);
                    Assert.assertTrue(false);
                }
            }

            if(Exp.equalsIgnoreCase("Invalid_Data")) {
                if(text_MyAccnt) {
                    My.LogoutMethod();
                    EU.setCellData("Sheet1", rowIndex, 3, "Fail");
                    EU.fillRedColor("Sheet1", rowIndex, 3);
                    Assert.assertTrue(false);
                } else {
                    EU.setCellData("Sheet1", rowIndex, 3, "Pass");
                    EU.fillGreenColor("Sheet1", rowIndex, 3);
                    Assert.assertTrue(true);
                }
            }

        } catch(Exception ee) {
            logger.error("Login Test Got Failed");
            logger.debug("Debug Logs..");
            System.out.println("Printing Exception Test Case Got Failed : "+ ee);
            logger.info("******** Finished TC003 LoginPage with Exception **********");
            Assert.fail();
        }
    }

    // Utility method to find the row index for a given email+password
    private int getRowIndex(String path, String email, String password) throws Exception {
        ExcelUtility EU = new ExcelUtility(path);
        int rows = EU.getRowCount("Sheet1");
        for (int i = 1; i <= rows; i++) {
            String e = EU.getCellData("Sheet1", i, 0);
            String p = EU.getCellData("Sheet1", i, 1);
            if(e.equalsIgnoreCase(email) && p.equalsIgnoreCase(password)) {
                return i; // exact matching row
            }
        }
        return -1; // not found
    }
}
