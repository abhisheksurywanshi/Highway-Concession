package toll.tcm.testData;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import toll.tcm.testCases.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel extends BaseClass{
	public ReadExcel(String FileName) throws IOException {
		String ExelPath=System.getProperty("user.dir")+"/src/test/java/toll/tcm/testData/"+FileName+".xlsx";
		FileInputStream fls=new FileInputStream(ExelPath);
		xw= new XSSFWorkbook(fls);
		sheet=xw.getSheetAt(0);
		
        
	}
}
