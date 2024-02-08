package toll.tcm.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import toll.tcm.testCases.BaseClass;

public class ETCAutoLoginCheck extends BaseClass
{
	public  ETCAutoLoginCheck() throws SQLException {
//		DataBaseurl = "jdbc:oracle:thin:@"+LaneIPAddress+":1521:XE"; // Replace with your database URL
//		DatBaseusername = XEUsername; // Replace with your username1
//		DatBasepassword = XEPassword;
//		Connection connection = DriverManager.getConnection(DataBaseurl, DatBaseusername, DatBasepassword);
//		
		
			if(Is_ETCAutoLogin.contains("Y"))
			{
				for(int i=0;i<GetOperatorList.size();i++)
				{
					String AutoOperator="ETC"+GetOperatorList.get(i).substring(3);
					System.out.println(AutoOperator);
					if(AutoOperator.equals("ETC"+Lane_CD))
					{
						IsAutoLane=true;
						
						System.out.println("IT is autolane");
					}
				}
			}
		
		
		
		

		
        
        
}
	}
