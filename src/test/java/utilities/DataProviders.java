package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	@DataProvider(name ="LoginData")
	public String[][] getData() throws IOException
	{
		String path = ".\\testdata\\OpenCart_loginData.xlsx";
		
		ExcelUtility xlutil = new ExcelUtility(path); //creating object on ExcelUtility class
		
		int totalrows = xlutil.getRowCount("Sheet1");
		int totalcols = xlutil.getCellCount("Sheet1", 1);
		
		// creating 2d array 
		
		String logindata[][] = new String[totalrows][totalcols];
		
		for(int i=1;i<=totalrows;i++) // start from 1 leave hearder row read the data from excel storing 2d array
		{
			
			for(int j=0;j<totalcols;j++) // 0   i is rows j is col
			{
				logindata[i-1][j] =xlutil.getCellData("Sheet1", i, j);
			}
		}
		
		return logindata;
		
		
		
	}
	
	// DataProvider 2
	// DataProvider 3
	// DataProvider 4
	
}
