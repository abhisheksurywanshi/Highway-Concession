package toll.tcm.testData;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import toll.tcm.testCases.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Seq extends BaseClass {

	public static void main(String[] args) throws Exception {
		
		 for(int i=0;i<numberoftransaction().length;i++)
		 {
			 System.out.println(numberoftransaction()[i]);
		 }
	}
	public static int[] numberoftransaction() throws IOException
	{
		String ExelPath=System.getProperty("user.dir")+"/src/test/java/toll/tcm/testData/seq.xlsx";
		FileInputStream fls=new FileInputStream(ExelPath);
		xw= new XSSFWorkbook(fls);
		sheet=xw.getSheet("Sheet1");
		 int Tag=Integer.valueOf(sheet.getRow(1).getCell(3).getRawValue());
		 int cash= Integer.valueOf(sheet.getRow(2).getCell(3).getRawValue());
		 int Exempt=Integer.valueOf(sheet.getRow(3).getCell(3).getRawValue());
		 int FreeConvoy=Integer.valueOf(sheet.getRow(4).getCell(3).getRawValue());
		 int Violation=Integer.valueOf(sheet.getRow(5).getCell(3).getRawValue());
		 int NSV=Integer.valueOf(sheet.getRow(6).getCell(3).getRawValue());
//		 System.out.println(Tag+" "+cash+" "+Exempt+" "+FreeConvoy+" "+Violation+" "+NSV);
		 int[] tr=new int[6];
		 tr[0]=Tag;
		 tr[1]=cash;
		 tr[2]=Exempt;
		 tr[3]=FreeConvoy;
		 tr[4]=Violation;
		 tr[5]=NSV;
		 int total=tr[0]+tr[1]+tr[2]+tr[3]+tr[4]+tr[5];
		 if(total==Integer.valueOf(sheet.getRow(8).getCell(0).getRawValue()))
		 {
			 
			 return tr;
		 }
		 else
		 {
			 return null;
		 }
		 
	}
}
