package toll.tcm.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import toll.tcm.testCases.BaseClass;

public class ETCAutoLoginCheck extends BaseClass
{
	static Logger logger=LogManager.getLogger(ETCAutoLoginCheck.class);
	public  ETCAutoLoginCheck() throws SQLException {
//		DataBaseurl = "jdbc:oracle:thin:@"+LaneIPAddress+":1521:XE"; // Replace with your database URL
//		DatBaseusername = XEUsername; // Replace with your username1
//		DatBasepassword = XEPassword;
//		Connection connection = DriverManager.getConnection(DataBaseurl, DatBaseusername, DatBasepassword);
//		
		try 
		{
			if(Is_ETCAutoLogin.contains("Y"))
			{
				for(int i=0;i<GetOperatorList.size();i++)
				{
					String AutoOperator="ETC"+GetOperatorList.get(i).substring(3);
//					System.out.println(AutoOperator);
					if(AutoOperator.equals("ETC"+Lane_CD))
					{
						IsAutoLane=true;
						
						logger.info(" is autolane");
					}
				}
			}
		}catch(java.lang.NullPointerException e)
		{
			
				IsAutoLane=false;
				
				logger.info(" is not autolane");
			
		}
			
		
		
		
		

		
        
        
}
	}
