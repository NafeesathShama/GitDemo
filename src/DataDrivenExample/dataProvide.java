package DataDrivenExample;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class dataProvide 
{
	
	DataFormatter formatter=new DataFormatter();
	
	@Test(dataProvider="driveTest")
	public void testCaseData(String greeting, String communi, String id)
	{
	System.out.println(greeting+communi+id);

	}
	
	@DataProvider(name="driveTest")
	public Object[][] getData() throws IOException
	{
		
		// Object[][] data={{"hello","text","1"},{"Bye","message","3"},{"solo","call","50"}};
		//every row of excel should be stored in one array
		//return data;'
		
		FileInputStream fis= new FileInputStream("C:\\JAVA SELENIUM\\ExcelDataProvider\\Book1.xlsx");
		XSSFWorkbook wb= new XSSFWorkbook(fis);
		XSSFSheet sheet=wb.getSheetAt(0); //first sheet
		int rowcount= sheet.getPhysicalNumberOfRows(); //total row count as 4
		XSSFRow row= sheet.getRow(0); // getting first row
		int colcount=row.getLastCellNum(); // 3
		
		Object[][] data= new Object[rowcount-1][colcount];
		
		for(int i=0;i<rowcount-1;i++)
		{
			row=sheet.getRow(i+1);
			for (int j=0;j<colcount;j++)
			{
				
			XSSFCell cell=row.getCell(j);
			data[i][j]=formatter.formatCellValue(cell);
			}
			
		}
		
		return data;
	
	}
}
