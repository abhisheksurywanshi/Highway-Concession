package toll.tcm.testCases;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;

import toll.tcm.testData.ReadExcel;

public class Test extends BaseClass{
	static String Transaction_Status;
	public static void main(String[] args) throws IOException {
		ReadExcel r= new ReadExcel("Tow");
		int rowcount=sheet.getLastRowNum();
	    XSSFCell CD;
	    int count=0;
	    
		for(int i=1;i<=rowcount;i++)
		 {
			CD=sheet.getRow(i).getCell(22);
//			
			if(CD.toString().contains("P")) 
			{
				count++;
				Transaction_Status="P";
				continue;
				
			}
			else if(CD.toString().contains("T"))
			{
				count++;
				Transaction_Status="T";
				continue;
				
			}
			else if(CD.toString().contains("C"))
			{
				count++;
				Transaction_Status="C";
				continue;
			}
			else
			{
				if(!(count==0))
				{
					
					CD=sheet.getRow(i-1).getCell(22);
					System.out.println(CD+"==="+i+" STATUS:"+Transaction_Status);
					
					if(Transaction_Status.contains("P"))
					{
						System.out.println("this is paid convoy Transaction"+count+Transaction_Status);
						count=0;
					}
					else if(Transaction_Status.contains("T"))
					{
						System.out.println("this is tow Transaction"+count+Transaction_Status);
						count=0;
					}
					else 
					{
						System.out.println("this is Free Convoy Transaction"+count+Transaction_Status);
						count=0;
					}
					
				}
				else
				{
					Transaction_Status="V";
					System.out.println("this is not Tow Transaction:"+count+"==="+CD+"===="+i+Transaction_Status); 
					count=0;
					
				}
				
			}
//			System.out.println(CD);
		 }
	}

}
