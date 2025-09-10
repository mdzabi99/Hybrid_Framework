package Utilities;

import org.testng.annotations.DataProvider;

public class DataProvides 
{

   @DataProvider(name="LoginData")
	public String[][] getdata() throws Exception
	{
		String path="C:\\Users\\mdjab\\OneDrive\\Documents\\TelegramDesktop\\selenium\\hybrid\\Hybrid_Framework\\Test_Data\\Data_Provider.xlsx";
		
		ExcelUtility EU=new ExcelUtility(path);
		int rows=EU.getRowCount("Sheet1");
		System.out.println(rows+"excel rows count");
		int cols=EU.getCellCount("Sheet1", rows);
		System.out.println(cols+"excel cols count");
		String LoginData[][]=new String[rows][cols];
		
		 for (int i = 1; i <= rows; i++)   // start from 1 (skip header row)
	      {
	         for (int j = 0; j < cols; j++)  // strictly < cols
	         {
	        	 LoginData[i-1][j] = EU.getCellData("Sheet1", i, j); 
	            System.out.print(LoginData[i-1][j] + "  ");
	         }
	         System.out.println();
	      }

	      return LoginData;
	   }
	
	
  
	
}
