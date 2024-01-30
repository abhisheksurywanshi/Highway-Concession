package toll.tcm.APIs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
	public static void main(String args[]) throws SQLException
	{
		GetBlacklistTag g= new GetBlacklistTag();
	}
}
