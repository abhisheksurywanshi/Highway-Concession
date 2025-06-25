package toll.tcm.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import toll.tcm.testCases.BaseClass;

public class GetEmployeeMaster extends BaseClass{
	public static String getMaintainanceId() throws SQLException
	{
		String sql="SELECT *  FROM MVW_TOLL_EMPLOYEE_MASTER T, MVW_TOLL_EMPLOYEE_DETAIL D,MVW_KENT_DESIGNATION_MASTER K\r\n"
				+ "where (T.STATUS = 'A'\r\n"
				+ "   AND T.EMPLOYEE_ID = D.EMPLOYEE_ID\r\n"
				+ "   AND D.TOLL_STATUS = 'A'\r\n"
				+ "    AND D.TOLL_ID = (select toll_id from mvw_kent_present_toll)\r\n"
				+ "    AND (K.DESIGNATION_NAME='MAINTENANCE ENGINEER' OR K.DESIGNATION_NAME='MAINTAINANCE OPERATOR')\r\n"
				+ "    AND K.DESIGNATION_ID=T.DESIGNATION_ID\r\n"
				+ "    AND T.PASSWORD='93279e3308bdbbeed946fc965017f67a'\r\n"
				+ "    AND T.EMPLOYEE_NAME<>'NOLOGINOPTR'\r\n"
				+ "   AND D.VALID_TILL_DATE > SYSDATE )";
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
//   	 try {
//   		     connection = DriverManager.getConnection(DataBaseurl, DatBaseusername, DatBasepassword);
//   			 Statement statement = connection.createStatement();
//   			 ResultSet resultSet = statement.executeQuery("select * from mvw_keyboard_keymapper v where v.vehicle_class is not null AND (V.METHOD_OF_PAYMENT=38 or V.METHOD_OF_PAYMENT=00)"); 
   	    	 while (resultSet.next()) {
   		        
	            String KEY = resultSet.getString("Login_id"); // Replace with the actual column name
//	            String KEYS_ALIAS= resultSet.getString("KAY_ALIAS");
	   
	            Maintanance_ID=KEY;
	            
	            
	            
	        }
   	    	 resultSet.close();
   	            statement.close();
   	            return Maintanance_ID;
	}
}
