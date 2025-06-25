package toll.tcm.testData;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.File;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;

import toll.tcm.utilities.XLUtils;

public class ManualTransactionDataProvider
{
	public static void main(String[] args) {
		Object a[] []=getExcelData(System.getProperty("user.dir")+"\\src\\test\\java\\toll\\tcm\\testData\\ManualTagPunchData.xlsx","Sheet1");
		for(int i=0;i<a.length;i++)
		{
//			for (int j = 0; j < a[i].length; j++) {
				
                System.out.print(a[i][0] + " ");
//            }
            System.out.println();
		}
	}
	@DataProvider(name="ManualTransaction")
	public Object[][] manualTagPunchData()
	{
		return getExcelData(System.getProperty("user.dir")+"\\src\\test\\java\\toll\\tcm\\testData\\ManualTagPunchData.xlsx","Sheet1");
//		XLUtils.getCellData(System.getProperty("user.dir")+"src\\test\\java\\toll\\tcm\\testData\\ManualTagPunchData.xlsx", "Sheet1", 0, 0)
	}
	
	 public static Object[][] getExcelData(String filePath, String sheetName) {
	        Object[][] data = null;
	        try {
	            FileInputStream file = new FileInputStream(new File(filePath));
	            Workbook workbook = WorkbookFactory.create(file);
	            Sheet sheet = workbook.getSheet(sheetName);
	            int rowCount = sheet.getPhysicalNumberOfRows();
	            int colCount = sheet.getRow(0).getPhysicalNumberOfCells();

	            data = new Object[rowCount - 1][colCount];

	            for (int i = 1; i < rowCount; i++) {
	                Row row = sheet.getRow(i);
	                for (int j = 0; j < colCount; j++) {
	                    data[i - 1][j] = row.getCell(j).toString();
//	                    System.out.println(row.getCell(j).toString());
	                }
	            }
	            workbook.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
//	        System.out.println(data.toString());
	        return data;
	    }
}
