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
		Connection BlcklistConnection=DriverManager.getConnection("jdbc:oracle:thin:@"+"10.10.1.181"+":1521:KENTTOLLCDB","ETC_BLACKLIST","Shree1998");
		String sql="SELECT * FROM TBL_BLT_THU T WHERE T.TAG_STATUS='A'";
		Statement statement = BlcklistConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        int a=0;
        while(resultSet.next())
        {
        	String BlackListedTag=resultSet.getString("tag_id");
        	BlackListedTags.add(BlackListedTag);
        	a++;
        	if(a>=100)
        	{
        		break;
        	}
        }
       
        resultSet.close();
        statement.close();
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
		GetBlacklistTag t=new   GetBlacklistTag();
		 System.out.println("tag: "+BlackListedTags);
		 int RandomBlacklistedTag=BaseClass.randomGenerator(0, BlackListedTags.size());
		 System.out.println("tag: "+BlackListedTags.get(RandomBlacklistedTag));
	}
}
