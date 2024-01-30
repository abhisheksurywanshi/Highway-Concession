package toll.tcm.testCases;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;

public class Demo extends BaseClass {
	public static void main(String[] args) {
		
		try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@10.10.1.87:1521:XE", "HC_1_DB", "inCReDiBLe_9CitY");

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
