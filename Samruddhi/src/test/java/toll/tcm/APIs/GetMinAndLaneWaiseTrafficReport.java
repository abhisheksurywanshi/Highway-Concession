package toll.tcm.APIs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GetMinAndLaneWaiseTrafficReport {
	public static int GetMinWise(String from_HH,String from_MM,String to_HH,String to_MM) throws SQLException 
	{
		Connection BlcklistConnection=DriverManager.getConnection("jdbc:oracle:thin:@"+"10.10.1.7"+":1521:KENTTOLLCDB","HC_SASTHAN","inCReDiBLe_9CitY");
		String sql="select count(*) as a from lane_transaction_tlc t where t.lane_id=1031016 and T.TRANSACTION_DATE BETWEEN to_date('12/feb/2024 "+from_HH+":"+from_MM+":00', 'dd/Mon/yyyy HH24:mi:ss') \r\n"
				+ "AND to_date('12/feb/2024 "+to_HH+":"+to_MM+":59', 'dd/Mon/yyyy HH24:mi:ss')";
		Statement statement = BlcklistConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while(resultSet.next())
        {
        	String BlackListedTag=resultSet.getString("a");
//        	BlackListedTags.add(BlackListedTag);
//        	System.out.println(BlackListedTag);
//        	return BlackListedTag;
        	return Integer.valueOf(BlackListedTag);
        }
       return 10000000;
	}

}
