package toll.tcm.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import toll.tcm.testCases.BaseClass;

public class GetPaymentTypes extends BaseClass  {
//	protected static String DataBaseurl = "jdbc:oracle:thin:@10.10.1.87:1521:XE"; // Replace with your database URL
//	protected static String DatBaseusername = "HC_1_DB"; // Replace with your username1
//	protected static String DatBasepassword = "inCReDiBLe_9CitY"; 
//	static Connection connection;
	static ArrayList a;
	public static void main(String[] args) throws SQLException {
		DatabaseConnectivity d= new DatabaseConnectivity();
		connection = DriverManager.getConnection(DataBaseurl, DatBaseusername, DatBasepassword);
		 a=getPaymentTypes();
		for(int i=0;i<a.size();i++)
		{
			System.out.println(a.get(i));
		}
		 Multimap<String, String> PaymentSubType=getPaymentSubTypes();
		 System.out.println(PaymentSubType);
		for (Map.Entry<String, String> entry : PaymentSubType.entries()) {
            String key = entry.getKey();
            if(key.contains("CASH"))
            {
            	
            	if(PaymentSubType.get(key).size()==1)
            	{
            		System.out.println("enter");
            	}
            }
            else
            {
            	String value = entry.getValue();
                logger.info("Key: " + key + ", Value: " + value);
            }
            
        }
	}
	
	
	
	public static ArrayList getPaymentTypes() throws SQLException
	{
		
		String sql="select * from mvw_payment_method_master t where t.is_applicable_tcm='Y'";
		Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        ArrayList <String>PaymentTypes=new  ArrayList<String>();
        while (resultSet.next()) {
            // Retrieve data from each row for the specific column
            String Payment_Type = resultSet.getString("Payment_Type"); // Replace with the actual column name
            PaymentTypes.add(Payment_Type);
        }
        return PaymentTypes;
	}
	public static Multimap getPaymentSubTypes() throws SQLException
	{
		
		Multimap<String, String> MapperKeys = ArrayListMultimap.create();


//   	 String sql1 = "SELECT M.VEHICLE_CLASS_ALIAS FROM MVW_TOLL_VEHICLE_MASTER M WHERE M.PAID_UNPAID='P' AND M.IS_OVERWEIGHT_APPLICABLE='Y'  group by m.vehicle_class_alias ORDER BY M.VEHICLE_CLASS_ALIAS";
   
	        
//	        ResultSet resultSet = statement.executeQuery(sql1);
	        
	    	 for(int i=0;i<a.size();i++) {
   		        
	            String KEY = (String) a.get(i); // Replace with the actual column name
	       	 String sql="select t.* from mvw_payment_method_details t, mvw_payment_method_master m where t.is_applicable_tcm = 'Y'  and m.is_applicable_tcm='Y' and t.payment_type_id = m.payment_type_id and m.payment_type='"+KEY+"'" ;		       		        
	       	 Statement statement = connection.createStatement();
	                Statement preparedStatement2 = connection.createStatement();
	            	ResultSet resultSet2 = preparedStatement2.executeQuery(sql);
	            while(resultSet2.next())
	            {
	            	
	            	String KEYS_ALIAS= resultSet2.getString("Payment_Sub_Type");
	            	MapperKeys.put(KEY,KEYS_ALIAS);
	            }
	           resultSet2.close();
 	    	 preparedStatement2.close();
	            
	            
	            
	        }
//   	    	 resultSet.close();
//   	    	 statement.close();
   	    	 


	       
	
       
   	 KeyMapping example = new KeyMapping();


        example.setItemMultiMap( MapperKeys);

      
        Multimap<String, String>retrievedList = example.getItemMultiMap();

     
        return retrievedList;
	}
}
