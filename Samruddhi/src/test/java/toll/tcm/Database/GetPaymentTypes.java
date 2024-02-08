package toll.tcm.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import toll.tcm.testCases.BaseClass;

public class GetPaymentTypes extends BaseClass  {
//	protected static String DataBaseurl = "jdbc:oracle:thin:@10.10.1.87:1521:XE"; // Replace with your database URL
//	protected static String DatBaseusername = "HC_1_DB"; // Replace with your username1
//	protected static String DatBasepassword = "inCReDiBLe_9CitY"; 
//	static Connection connection;
	
	public static void main(String[] args) throws SQLException {
		DatabaseConnectivity d= new DatabaseConnectivity();
		connection = DriverManager.getConnection(DataBaseurl, DatBaseusername, DatBasepassword);
		 paymentTypes=getPaymentTypes();
		for(int i=0;i<paymentTypes.size();i++)
		{
			System.out.println(paymentTypes.get(i));
		}
		 Map<String, String> Cash=getPaymentSubTypes("CASH");
		 System.out.println(Cash);
//		for (Map.Entry<String, String> entry : PaymentSubType.entries()) {
//            String key = entry.getKey();
//            if(key.contains("CASH"))
//            {
//            	
//            	if(PaymentSubType.get(key).size()==1)
//            	{
//            		System.out.println("enter");
//            	}
//            }
//            else
//            {
//            	String value = entry.getValue();
//                logger.info("Key: " + key + ", Value: " + value);
//            }
//            
//        }
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
	public static  Map<String, String> getPaymentSubTypes(String type) throws SQLException
	{
		
		Map<String, String> MapperKeys = new HashMap<String, String>();


//   	 String sql1 = "SELECT M.VEHICLE_CLASS_ALIAS FROM MVW_TOLL_VEHICLE_MASTER M WHERE M.PAID_UNPAID='P' AND M.IS_OVERWEIGHT_APPLICABLE='Y'  group by m.vehicle_class_alias ORDER BY M.VEHICLE_CLASS_ALIAS";
   
	        
//	        ResultSet resultSet = statement.executeQuery(sql1);
	        
	    	
   		        
//	            String KEY = (String) paymentTypes.get(i); // Replace with the actual column name
	       	 String sql="select t.* from mvw_payment_method_details t, mvw_payment_method_master m where t.is_applicable_tcm = 'Y'  and m.is_applicable_tcm='Y' and t.payment_type_id = m.payment_type_id and m.payment_type='"+type+"'" ;		       		        
	       	 Statement statement = connection.createStatement();
	                Statement preparedStatement = connection.createStatement();
	            	ResultSet resultSet = preparedStatement.executeQuery(sql);

	            while(resultSet.next())
	            {

	            	String Payment_Sub_Type= resultSet.getString("Payment_Sub_Type");
	            	String IsReference=resultSet.getString("Is_Reference_No");
	            	MapperKeys.put(Payment_Sub_Type,IsReference);
	            }
	           resultSet.close();
 	    	 preparedStatement.close();
	            
	            
	            
	        
//   	    	 resultSet.close();
//   	    	 statement.close();
   	    	 


	       
	
 	    	KeyMapping example = new KeyMapping();


            example.setItemList(MapperKeys);

          
            Map<String, String>retrievedList = example.getItemList();

         
            return retrievedList;
	}
	public static void IsReference() throws SQLException
	{
		 Iterator<String> iterator = paymentTypes.iterator();
		 for(int i=0;i<paymentTypes.size();i++)
	        {
			 String paymentype=paymentTypes.get(i).toString();
			 if(paymentype.contains("CASH"))
			 {
				 CashIsReferenceFLags=getPaymentSubTypes(paymentype);
				 allReferenceFlags.put(paymentype, getPaymentSubTypes(paymentype));
			 }
			 else if(paymentype.contains("CARD"))
			 {
				 CardIsReferenceFLags=getPaymentSubTypes(paymentype);
				 allReferenceFlags.put(paymentype, getPaymentSubTypes(paymentype));
			 }
			 else if(paymentype.contains("WALLET"))
			 {
				 WalletIsReferenceFLags=getPaymentSubTypes(paymentype);
				 allReferenceFlags.put(paymentype, getPaymentSubTypes(paymentype));
			 }
	        }
	}
	
}
