package toll.tcm.testCases;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;

public class Demo extends BaseClass {
	static Connection connection;
	public static void main(String[] args) {
		
		try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

           // 

            CallableStatement callableStatement = connection.prepareCall("{call prc_toll_transactions_iavc_upd(p_status => :p_status)}");

            // Set input parameter
//            callableStatement.setInt(1, ""); // Replace with the actual value for p_status

            // Execute the stored procedure
            callableStatement.execute();
            String outputValue = callableStatement.getString(0);

            // Print the result
            System.out.println("Output from stored procedure: " + outputValue);

            // Close resources
            callableStatement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
	    		
		 }
	

}
