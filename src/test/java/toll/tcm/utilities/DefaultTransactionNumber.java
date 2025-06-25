package toll.tcm.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import toll.tcm.Database.DatabaseConnectivity;
import toll.tcm.MOPs.Violation;
import toll.tcm.testCases.*;
public class DefaultTransactionNumber extends BaseClass{
	static Logger logger=LogManager.getLogger(DefaultTransactionNumber.class);
	public static void main(String[] args) {
		
//		try {
//			Connection connection = DriverManager.getConnection(DataBaseurl, DatBaseusername, DatBasepassword);
//            String sql = "select * from mvw_toll_shift_master m order by m.applicable_from desc";
//
//           
//            
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery(sql);
//
//            while (resultSet.next()) {
//                // Retrieve data from each row for the specific column
//                String Shift = resultSet.getString("ip_address"); // Replace with the actual column name
//               
//                // Process the retrieved data
//                System.out.println("Shif:"+Shift);
//                
//                
//            }
//
//            resultSet.close();
//            statement.close();
//        } catch (SQLException e) {
//        	logger.warn("COM Port is not Properly set...");
//            e.printStackTrace();
//        }

	}
	public static String getDefaultTransationNumber() throws SQLException
	{
		String timeStamp=new SimpleDateFormat("yy.MM.dd").format(new Date());
		String Transaction=timeStamp.replace(".", "");
		
//		try
//		{
//			d.getTollInfo();
//		}
//		catch(Exception e)
//		{
//			
//		}
		logger.info(Lane_CD);
		String lane_no=Lane_CD.substring(Lane_CD.length()-2);
		return Transaction+"0"+toGetCurrentShift()+lane_no+"00000";
	}
	public static String toGetCurrentShift() throws SQLException
	{
		DatabaseConnectivity d= new DatabaseConnectivity();
		String timeStamp=new SimpleDateFormat("HH:mm:ss").format(new Date());
		String sql="select * from mvw_toll_shift_master t where (t.start_time<'"+timeStamp+"' and t.end_time>'"+timeStamp+"') ORDER BY T.VERSION_NO DESC";
		 Statement statement = connection.createStatement();
       ResultSet resultSet = statement.executeQuery(sql);

       while (resultSet.next()) {
           // Retrieve data from each row for the specific column
           currentShiftAlias = resultSet.getString("shift"); // Replace with the actual column name
           
           // Process the retrieved data
           logger.info("Shift:"+currentShiftAlias);
           break;
           
       }

       resultSet.close();
       statement.close();
		return currentShiftAlias;
	}
}
