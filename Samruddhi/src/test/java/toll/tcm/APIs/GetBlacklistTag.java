package toll.tcm.APIs;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import toll.tcm.testCases.BaseClass;

public class GetBlacklistTag extends BaseClass {

	public GetBlacklistTag() throws SQLException 
	{
		Connection BlcklistConnection=DriverManager.getConnection("jdbc:oracle:thin:@"+"10.10.1.7"+":1521:KENTTOLLCDB","ETC_BLACKLIST","Shree1998");
		String sql="SELECT * FROM TBL_BLT_MON T WHERE T.TAG_STATUS='A'";
		Statement statement = BlcklistConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while(resultSet.next())
        {
        	String BlackListedTag=resultSet.getString("tag_id");
        	BlackListedTags.add(BlackListedTag);
//        	System.out.println(a);
        }
	}
	public static void GetHrWise(String from_HH,String to_HH) throws SQLException 
	{
		Connection BlcklistConnection=DriverManager.getConnection("jdbc:oracle:thin:@"+"10.10.1.7"+":1521:KENTTOLLCDB","HC_SASTHAN","inCReDiBLe_9CitY");
		String sql="select count(*) as a from lane_transaction_tlc t where T.TRANSACTION_DATE BETWEEN to_date('12/feb/2024 "+from_HH+":00:01', 'dd/Mon/yyyy HH24:mi:ss') \r\n"
				+ "AND to_date('12/feb/2024 "+to_HH+":00:01', 'dd/Mon/yyyy HH24:mi:ss')";
		Statement statement = BlcklistConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while(resultSet.next())
        {
        	String BlackListedTag=resultSet.getString("a");
        	BlackListedTags.add(BlackListedTag);
        	System.out.println(BlackListedTag);
        }
	}
	public static int GetMinWise(String from_HH,String from_MM,String to_HH,String to_MM) throws SQLException 
	{
		Connection BlcklistConnection=DriverManager.getConnection("jdbc:oracle:thin:@"+"10.10.1.7"+":1521:KENTTOLLCDB","HC_SASTHAN","inCReDiBLe_9CitY");
		String sql="select count(*) as a from lane_transaction_tlc t where t.lane_id=1031016 and T.TRANSACTION_DATE BETWEEN to_date('06/feb/2024 "+from_HH+":"+from_MM+":00', 'dd/Mon/yyyy HH24:mi:ss') \r\n"
				+ "AND to_date('06/feb/2024 "+to_HH+":"+to_MM+":59', 'dd/Mon/yyyy HH24:mi:ss')";
		Statement statement = BlcklistConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while(resultSet.next())
        {
        	String BlackListedTag=resultSet.getString("a");
        	BlackListedTags.add(BlackListedTag);
//        	System.out.println(BlackListedTag);
//        	return BlackListedTag;
        	return Integer.valueOf(BlackListedTag);
        }
       return 10000000;
	}
	public static void main(String args[]) throws SQLException, IOException
	{
//		GetMinWise("17","00","17", "01");
		
		Workbook workbook = new XSSFWorkbook();

        // Create a sheet
        Sheet sheet = workbook.createSheet("Sheet1");

        // Create a row and put some cells in it
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Time");
        headerRow.createCell(1).setCellValue("Count");
        headerRow.createCell(2).setCellValue("Average");
        int j=1;
        Float Total_HH_Count=0.0f;
        Float Total_MM_Count=0.0f;
        Row dataRow=null;
       for(int l=16;l<24;l++)
       {
    	   String formattedNumberHHi=String.format("%02d", l);
    	   
    	   for(int i=0;i<=59;i++)
  		 {
  			 String formattedNumberMMi = String.format("%02d", i);
  			 
              
               
  			  dataRow = sheet.createRow(j);
  			
               
              	 System.out.println(formattedNumberHHi+" :"+formattedNumberMMi+":00 to "+formattedNumberHHi+":"+formattedNumberMMi+":59");
              	 String time=formattedNumberHHi+":"+formattedNumberMMi+":00 to "+formattedNumberHHi+":"+formattedNumberMMi+":59";
//              	 dataRow.createCell(i+1).getRow().setRowNum(Integer.valueOf(GetMinWise("17",formattedNumberMMi,"17", "00"))); 
              	 dataRow.createCell(0).setCellValue(time);
              	int count= GetBlacklistTag.GetMinWise(formattedNumberHHi,formattedNumberMMi,formattedNumberHHi, formattedNumberMMi);
//              	 System.out.println(n);
//              	 dataRow.createCell(i+1).setCellValue(GetMinWise("17",formattedNumberMMi,"17", formattedNumberHHj));
              	 dataRow.createCell(1).setCellValue(count); 
              	 
                Total_MM_Count=Total_MM_Count+count;
              	 j++;
  		 }
    	   Total_HH_Count=Total_HH_Count+Total_MM_Count;
    	   
    	  System.out.println("average for HH :"+l+" is "+(Total_MM_Count/60));
    	  dataRow.createCell(2).setCellValue("average for HH :"+l+" is "+(Total_MM_Count/60)); 
       }
       System.out.println("average for Shift 1 is: "+(Total_HH_Count/24));
       dataRow.createCell(2).setCellValue("average for Shift  is: "+(Total_HH_Count/24)); 
		 FileOutputStream fileOut = new FileOutputStream("workbook.xlsx");
         workbook.write(fileOut);
         fileOut.close();

         System.out.println("Excel file generated successfully!");
		
		//		GetBlacklistTag g= new GetBlacklistTag();
//		for (int i = 0,j=1; i <= 23; i++) {
//            String formattedNumberMMi = String.format("%02d", i);
//            String formattedNumberHHj = String.format("%02d", j);
//            String t=formattedNumberHHj;
//            String formattedNumberMMl="";
//            String formattedNumberMMk="" ;
//            System.out.println("in HH:"+formattedNumberMMi+" to HH"+formattedNumberHHj);
//            
//            if(j==24)
//            {
//            	formattedNumberHHj=String.format("%02d", 23);
//            }
//            GetHrWise(formattedNumberMMi, formattedNumberHHj);
//            for(int k=0,l=1;k<=59;k++)
//            {
//            	
//            	
//            	formattedNumberMMk = String.format("%02d", k);
//            	formattedNumberMMl = String.format("%02d", l);
//                
////                System.out.println("in MM:"+formattedNumberMMk+" to MM"+formattedNumberMMl);
//                if(l==60)
//                {
//                	formattedNumberMMl=String.format("%02d", 59);
//                	formattedNumberMMi=String.format("%02d", i+1);
//                	formattedNumberMMk=String.format("%02d", 0);
//                }
//                if(!formattedNumberMMk.contains("59"))
//                {
//                	
//                	formattedNumberHHj="00";
//                }
//                else
//                {
//                	
//                }
//                System.out.println("HH: "+formattedNumberMMi+" MM:"+formattedNumberMMk+ " to  HH:"+formattedNumberHHj+" MM"+formattedNumberMMl);
//                ;
//                GetMinWise(formattedNumberMMi,formattedNumberHHj,formattedNumberMMk, formattedNumberMMl);
//                l++;
//                formattedNumberHHj=t;
//            }
//            
//            
//            j++;
//        }
//		
//		GetMinWise("00","00","01", "59");
	}
}
