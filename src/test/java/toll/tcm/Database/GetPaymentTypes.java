package toll.tcm.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import toll.tcm.testCases.BaseClass;

public class GetPaymentTypes extends BaseClass  {

	
	public static void main(String[] args) throws SQLException {
		
		 connection= DriverManager.getConnection("jdbc:oracle:thin:@10.10.1.87:1521:XE","SNC_HC","CaPitAL_36_iTS") ; 
		DatabaseConnectivity d= new DatabaseConnectivity();
		
		 paymentTypes=getPaymentTypes();
		 IsReference();
		 Cash=getPaymentSubTypes("CASH");
		  Wallet=getPaymentSubTypes("WALLET");
		  Card=getPaymentSubTypes("CARD");
		  System.out.println(PaymentTypeSize);
	       int count=0,i=0;
	       String []StringArrayOfMstrPOM=new String[1];
	       List <String>StringListOfMstrPOM=new ArrayList<String>();
	       Map<String, Integer> payementsubtypecount = new HashMap<String, Integer>();
	       for (String key : allReferenceFlags.keySet()) {
	    	
	    	   if(count==0)
	    	   {
	    		   
	    	   }
	    	   else
	    	   {
	    		   ++PaymentTypeSize;
	    		   
	    	   }
	    	    int sizeOfInnerMap = allReferenceFlags.get(key).size();
	    	    System.out.println("Size of the inner map associated with key '" + key + "': " + sizeOfInnerMap);
	    	    payementsubtypecount.put(key, sizeOfInnerMap);
	    	    StringListOfMstrPOM.add(key);
	    	    count++;i++;
	    	}
	       
	       System.out.println();
	       int rand =randomGenerator(0, PaymentTypeSize);
	       String string=StringListOfMstrPOM.get(rand);
	      System.out.println("String:"+string);
	      
	       random = new Random();
	    		   
	       for( i=0;i<PaymentTypeSize;i++)
	       {
	    	   StringArrayOfMstrPOM=new String[PaymentTypeSize];
	    	   StringArrayOfMstrPOM[i]=StringListOfMstrPOM.get(i);
	    	   int randomIndex = random.nextInt(PaymentTypeSize);
	    	   String randomlySelectedOption = StringArrayOfMstrPOM[randomIndex];
	    	   System.out.println("Randomly selected option: " + randomlySelectedOption);
	           
	       }
	       System.out.println(allReferenceFlags.toString());
	       String referenceflagstring=allReferenceFlags.toString();
	       String type="CASH";
	       referenceflagstring=referenceflagstring.substring(1, referenceflagstring.length()-1);
	       System.out.println(referenceflagstring);
	       if(referenceflagstring.contains(type))
	       {
	    	   int start=referenceflagstring.indexOf(type);
	    	   String substring=referenceflagstring.substring(start);
	    	   System.out.println(substring);
	    	   int end=substring.indexOf("},");
	    	   System.out.println(start+" "+end);
	    	   String final0=substring.substring(0, end+1);
	    	   System.out.println(final0);
	    	   String type2="CASH";
	    	   if(final0.contains(type2))
	    	   {
	    		   int start1 =final0.indexOf(type2);
	    		   String substring1=final0.substring(start1);
	    		   int end1=substring.indexOf("=");
	    		   String final1=substring1.substring(0,end1);
	    		   System.out.println(final1);
	    		   if(final1.endsWith("Y"))
	    		   {
	    			   System.out.println("yes");
	    		   }
	    		   else
	    		   {
	    			   System.out.println("no");
	    		   }
	    	   }
	       }
	       
	        // Iterate over the original paymentTypes and populate the MasterPaymentType
	        
	        
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
   	    	 


	       
	
 	    	Mapping example = new Mapping();


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
				 CashIsReferenceFlags=getPaymentSubTypes(paymentype);
				 allReferenceFlags.put(paymentype, getPaymentSubTypes(paymentype));
			 }
			 else if(paymentype.contains("CARD"))
			 {
				 CardIsReferenceFlags=getPaymentSubTypes(paymentype);
				 allReferenceFlags.put(paymentype, getPaymentSubTypes(paymentype));
			 }
			 else if(paymentype.contains("WALLET"))
			 {
				 WalletIsReferenceFlags=getPaymentSubTypes(paymentype);
				 allReferenceFlags.put(paymentype, getPaymentSubTypes(paymentype));
			 }
	        }
	}
	
}
