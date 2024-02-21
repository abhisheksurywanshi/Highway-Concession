package toll.tcm.Database;

import java.awt.AWTException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import toll.tcm.testCases.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.reporters.jq.Main;

import com.aventstack.extentreports.ExtentTest;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class KeyMapping extends BaseClass
	
{	
	static Logger logger=LogManager.getLogger(KeyMapping.class);

	public Map<String, String> itemList = new HashMap<String, String>();
	public Multimap<String, String> itemMultiMap =  ArrayListMultimap.create();
    public Map<String, String> getItemList() {
        return itemList;
    }
    public Multimap<String, String> getItemMultiMap() {
        return itemMultiMap;
    }
    public void setItemList(Map<String, String> itemList) {
        this.itemList = itemList;
    }
    public void setItemMultiMap(Multimap<String, String> itemList) {
        this.itemMultiMap = itemList;
    }
    
	public  void KeyMappingg() throws SQLException, AWTException, IOException, InterruptedException
	{
		 ExtentTest KeyMappingg=extent.createTest("KeyMappingg");
		
		ArrayList <String>ExemptTypesOfVehicleClass=new ArrayList<String>();	
		keysArrayForSchedualVehicle = KeyMapping.getSchedualVehicleKeys().keySet().toArray(new String[0]);
		 keysArrayVehicleCode = KeyMapping.getVehicleCodeWithASCII().keySet().toArray(new String[0]);
		 // The index value from which the loop will start
		 logger.info("Schedual Vehicle KeyMapping:");
		 KeyMappingg.info("Schedual Vehicle KeyMapping:");
		for (int i = 0; i < keysArrayVehicleCode.length; i++) {
            String key = keysArrayVehicleCode[i];
            String value = KeyMapping.getVehicleCodeWithASCII().get(key);
            ClassCodes.put(key, value);
            logger.info("Vehicle Code: " + key + ", ASCII Key:" + value); 
            KeyMappingg.info("Vehicle Code: " + key + ", ASCII Key:" + value);
        }
        for (int i = 0; i < keysArrayForSchedualVehicle.length; i++) {
            String key = keysArrayForSchedualVehicle[i];
            String value = KeyMapping.getSchedualVehicleKeys().get(key);
            SchedualClassTypes.put(key, value);
            logger.info("ASCII Key: " + key + ", Vehicle Alias: " + value);
            KeyMappingg.info("ASCII Key: " + key + ", Vehicle Alias: " + value);
            
        }
        
         keysArrayForNonSchedualVehicle = KeyMapping.getNonSchedualVehicleKeys().keySet().toArray(new String[0]);
		 // The index value from which the loop will start
         logger.info("Non Schedual Vehicle KeyMapping:");
         KeyMappingg.info("Non Schedual Vehicle KeyMapping:");
       for (int i = 0; i < keysArrayForNonSchedualVehicle.length; i++) {
           String key = keysArrayForNonSchedualVehicle[i];
           String value = KeyMapping.getNonSchedualVehicleKeys().get(key);
           NonSchedualClassTypes.put(key, value);
           logger.info("ASCII Key: " + key + ", Value: " + value);
           KeyMappingg.info("ASCII Key: " + key + ", Value: " + value);
           
       }
//		for(String key :KeyMapping.getSchedualVehicleKeys().keySet())
//		{
////			System.out.println(KeyMapping.getSchedualVehicleKeys().get(i));
//			
//			SchedualClassTypes.put(KeyMapping.getSchedualVehicleKeys().keySet());
//			logger.info( "Key: " + key + ", Value: " + KeyMapping.getSchedualVehicleKeys().get(key));
//			
//		}
//		for(String key :KeyMapping.getNonSchedualVehicleKeys().keySet())
//		{
////			System.out.println(KeyMapping.getSchedualVehicleKeys().get(i));
//			NonSchedualClassTypes.keySet(KeyMapping.getNonSchedualVehicleKeys().keySet());
//			logger.info( "Key: " + key + ", Value: " + KeyMapping.getNonSchedualVehicleKeys().get(key));
//			
//		}
		SchedualClassType =SchedualClassTypes.size();
		NonSchedualClassType =NonSchedualClassTypes.size();
		VehicleCDWiseExemptType=GetExemptType.getVehicleCDWiseExemptType();
		GetExemptType.getVehicleCDWiseExempt();
		logger.info("Available Exempt Class Code:"+VehicleCDWiseExemptType);
		KeyMappingg.info("Available Exempt Class Code:"+VehicleCDWiseExemptType);
//		System.out.println("|||");
		ExemptKey=getExemptKey();
		logger.info("Exempt Key ASCII Value:"+ExemptKey);
		KeyMappingg.info("Exempt Key ASCII Value:"+ExemptKey);
		OtherCashKey=getOtherCashKeys().size();
		keysArrayForOtherCashKeys=KeyMapping.getOtherCashKeys().keySet().toArray(new String[0]);
		  logger.info("Other Cash MOP KeyMapping:");
		  KeyMappingg.info("Other Cash MOP KeyMapping:");
	       for (int i = 0; i < keysArrayForOtherCashKeys.length; i++) {
	           String key = keysArrayForOtherCashKeys[i];
	           String value = KeyMapping.getOtherCashKeys().get(key);
	           OtherCashKeys.put(key, value);
	           logger.info("ASCII Key: " + key + ", Value: " + value);
	           KeyMappingg.info("ASCII Key: " + key + ", Value: " + value);
	           
	       }
//	       logger.info(GetWimSubClass.toll_wim_class_details());
//	       KeyMappingg.info(GetWimSubClass.toll_wim_class_details().toString());
	       TollWimClassDetails=GetWimSubClass.toll_wim_class_details();
//	       ClassArrayForTollWimClassDetail=GetWimSubClass.toll_wim_class_detailsClasses();
//	       keysArrayForTolWimClassDetail=GetWimSubClass.toll_wim_class_details().keySet().toArray(new String[0]);
//	       for(int i=0;i<ClassArrayForTollWimClassDetail.size();i++)
//	       {
//	    	   String Class=ClassArrayForTollWimClassDetail.get(i);
//	    	  
//	    	  
//	    	   
//	    	   logger.info("class:"+Class);
//	    	   KeyMappingg.info("class:"+Class);
//	       }
	       Collection<String> values = TollWimClassDetails.values();
	       for (String value : values) {
	    	   ToWeight.add(Integer.valueOf(value));
	           
	        }
	       Integer[] ToWeightarray = ToWeight.toArray(new Integer[0]);
	       IsOverWeightApplicable=Is_OverWeight_Applicable.isOverWeightApplicable();
	       keysArrayForIsOverWeightApplicable=Is_OverWeight_Applicable.isOverWeightApplicable().keySet().toArray(new String[0]);
//	       System.out.println("Done=======");
	       GetExemptType.ClassesIsCaptureFlags();
//	       GetExemptType.ClassesIsExemptRemarkFlags();
	       paymentTypes=GetPaymentTypes.getPaymentTypes();
	       
	       GetPaymentTypes.IsReference();
//	       System.out.println("{");
//	       for (Map.Entry<String, Map<String, String>> outerEntry : allVehicleCaptureFlags.entrySet()) {
//	    	    String outerKey = outerEntry.getKey();
//	    	    Map<String, String> innerMap = outerEntry.getValue();
//	    	    
//	    	    System.out.println(" "+outerEntry.getKey()+"=");
//	    	    System.out.print("  ");
//	    	    // Now you can iterate over the entries of the inner map
//	    	    for (Map.Entry<String, String> innerEntry : innerMap.entrySet()) {
//	    	        String innerKey = innerEntry.getKey();
//	    	        String innerValue = innerEntry.getValue();
//	    	       System.out.print(("{"+innerKey+"="+innerValue+"}"));
//	    	        // Do something with the inner key and value
//	    	    }
//	    	   System.out.print("}");
//	    	   System.out.println();
//	    	}
//	       System.out.println("}");
//	       
//	       System.out.println();
	       String flags=allVehicleCaptureFlags.toString();
	       String output = flags.replaceAll("},", "},\n");
	       	logger.info("Is Capture Flags");
	        // Print the formatted string
	        logger.info("\n"+output);
	        KeyMappingg.info("\n"+output);
	        
//	        String flags1=allVehicleIsExemptRemarkFlags.toString();
//		       String output1 = flags1.replaceAll("},", "},\n");
//		       	logger.info("Is ExemptRemark Flags");
//
//		        // Print the formatted string
//		        logger.info("\n"+output1);
//		        KeyMappingg.info("\n"+output1);
		        
	        IsExemptRemarkKent=GetExemptType.isExemptRemarkFlagKent();
	        keysArrayForIsExemptRemarkKent=GetExemptType.isExemptRemarkFlagKent().keySet().toArray(new String[0]);
			  logger.info("IsExemtRemarkKent Mapping:");
			  KeyMappingg.info("IsExemptRemarkKent KeyMapping:");
		       for (int i = 0; i < keysArrayForIsExemptRemarkKent.length; i++) {
		           String key = keysArrayForIsExemptRemarkKent[i];
		           String value = IsExemptRemarkKent.get(key);
		           
		           logger.info("Exempt Type :" + key + ", Flag: " + value);
		           KeyMappingg.info("Exempt Type :" + key + ", Flag: " + value);
		           
		       }
		        String flags2=allReferenceFlags.toString();
			       String output2 = flags2.replaceAll("},", "},\n");
			       	logger.info("Is Reference Flags");

			        // Print the formatted string
			        logger.info("\n"+output2);
			        KeyMappingg.info("\n"+output2);
//	       ToWeight=Arrays.sort(ToWeightarray);
//	       for(int i=0;i<ToWeight.size();i++)
//	       {
//	    	   ToWeight.get
//	       }
//		NSV.getNonSchedualVehicleKey();
//		for(int i=0;i<getSchedualVehicleKeys().size();i++)
//		{
//			logger.info(SchedualClassTypes.get(0)+"---");
			
//		}
//		for(int i=0;i<KeyMapping.getNonSchedualVehicleKeys().size();i++)
//		{
////			System.out.println(KeyMapping.getNonSchedualVehicleKeys().get(i));
//			NonSchedualClassTypes.add(KeyMapping.getNonSchedualVehicleKeys().get(i));
//		}
	}
		public static void main(String[] args) throws SQLException {
			System.out.println(getSchedualVehicleKeys().size());
			System.out.println(getNonSchedualVehicleKeys());
		}
		public static ArrayList<String> AllKeysAsciiValue() throws SQLException
		{
			ArrayList <String>Keys=new  ArrayList<String>();
			String sql="select * from mvw_keyboard_keymapper v where v.vehicle_class is not null";
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			 while (resultSet.next()) {
 		        
	 	            String KEY = resultSet.getString("KEY"); 
	 	            Keys.add(KEY);
	 	           
			 }
			 return Keys;
		}
	 public static Map<String, String> getSchedualVehicleKeys() throws SQLException
     {
//    	 ArrayList <String>KEYS=new ArrayList<String>();
    	 Map<String, String> MapperKeys = new HashMap<String, String>();
//    	 Connection connection = DriverManager.getConnection(DataBaseurl, DatBaseusername, DatBasepassword);
//    	 Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@"+LaneIPAddress+":1521:XE", "TASAWADE_DB", "CaPitAL_36_iTS");
    
    	 String sql = "select * from mvw_keyboard_keymapper v where v.vehicle_class is not null AND (V.METHOD_OF_PAYMENT>35 or V.METHOD_OF_PAYMENT=00)";
	        
	       
	        
	        Statement statement = connection.createStatement();
	        
	        ResultSet resultSet = statement.executeQuery(sql);
//    	 try {
//    		     connection = DriverManager.getConnection(DataBaseurl, DatBaseusername, DatBasepassword);
//    			 Statement statement = connection.createStatement();
//    			 ResultSet resultSet = statement.executeQuery("select * from mvw_keyboard_keymapper v where v.vehicle_class is not null AND (V.METHOD_OF_PAYMENT=38 or V.METHOD_OF_PAYMENT=00)"); 
    	    	 while (resultSet.next()) {
    		        
 	            String KEY = resultSet.getString("KEY"); // Replace with the actual column name
 	            String KEYS_ALIAS= resultSet.getString("KAY_ALIAS");
 	   
 	            MapperKeys.put(KEY,KEYS_ALIAS);
 	            
 	            
 	            
 	        }
    	    	 resultSet.close();
    	            statement.close();
//    	            for (String key : MapperKeys.keySet()) {
//    	                System.out.println("Key: " + key + ", Value: " + MapperKeys.get(key));
//    	            }
//    	            System.out.println("end------------");
    		    // Process the result set here

//    		} catch (SQLException e) {
    		    // Handle the exception
//    		}

	       
	
        
    	 KeyMapping example = new KeyMapping();


         example.setItemList(MapperKeys);

       
         Map<String, String>retrievedList = example.getItemList();

      
         return retrievedList;
    	    	 
    	    	 
     }
	 public static Map<String, String> getOtherCashKeys() throws SQLException
     {
//    	 ArrayList <String>KEYS=new ArrayList<String>();
    	 Map<String, String> MapperKeys = new HashMap<String, String>();
//    	 Connection connection = DriverManager.getConnection(DataBaseurl, DatBaseusername, DatBasepassword);
//    	 Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@"+LaneIPAddress+":1521:XE", "TASAWADE_DB", "CaPitAL_36_iTS");

    	 String sql = "select * from MVW_keyboard_keymapper t where t.vehicle_class is null and (t.method_of_payment is not null and t.method_of_payment <> 'X') and t.kay_alias <> 'EX'";
	        
	       
	        
	        Statement statement = connection.createStatement();
	        
	        ResultSet resultSet = statement.executeQuery(sql);
//    	 try {
//    		     connection = DriverManager.getConnection(DataBaseurl, DatBaseusername, DatBasepassword);
//    			 Statement statement = connection.createStatement();
//    			 ResultSet resultSet = statement.executeQuery("select * from mvw_keyboard_keymapper v where v.vehicle_class is not null AND (V.METHOD_OF_PAYMENT=38 or V.METHOD_OF_PAYMENT=00)"); 
    	    	 while (resultSet.next()) {
    		        
 	            String KEY = resultSet.getString("KEY"); // Replace with the actual column name
 	            String KEYS_ALIAS= resultSet.getString("KAY_ALIAS");
 	   
 	            MapperKeys.put(KEY,KEYS_ALIAS);
 	            
 	            
 	            
 	        }
    	    	 resultSet.close();
    	            statement.close();
//    	            for (String key : MapperKeys.keySet()) {
//    	                System.out.println("Key: " + key + ", Value: " + MapperKeys.get(key));
//    	            }
//    	            System.out.println("end------------");
    		    // Process the result set here

//    		} catch (SQLException e) {
    		    // Handle the exception
//    		}

	       
	
        
    	 KeyMapping example = new KeyMapping();


         example.setItemList(MapperKeys);

       
         Map<String, String>retrievedList = example.getItemList();

      
         return retrievedList;
    	    	 
    	    	 
     }
	 public static Map<String, String> getVehicleCodeWithASCII() throws SQLException
     {
//    	 ArrayList <String>KEYS=new ArrayList<String>();
    	 Map<String, String> MapperKeys = new HashMap<String, String>();
//    	 Connection connection = DriverManager.getConnection(DataBaseurl, DatBaseusername, DatBasepassword);
//    	 Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@"+LaneIPAddress+":1521:XE", "TASAWADE_DB", "CaPitAL_36_iTS");

    	 String sql = "select * from mvw_keyboard_keymapper v where v.vehicle_class is not null AND (V.METHOD_OF_PAYMENT='X')";
	        
	       
	        
	        Statement statement = connection.createStatement();
	        
	        ResultSet resultSet = statement.executeQuery(sql);
//    	 try {
//    		     connection = DriverManager.getConnection(DataBaseurl, DatBaseusername, DatBasepassword);
//    			 Statement statement = connection.createStatement();
//    			 ResultSet resultSet = statement.executeQuery("select * from mvw_keyboard_keymapper v where v.vehicle_class is not null AND (V.METHOD_OF_PAYMENT=38 or V.METHOD_OF_PAYMENT=00)"); 
    	    	 while (resultSet.next()) {
    		        
 	            String KEY = resultSet.getString("Vehicle_class"); // Replace with the actual column name
 	            String KEYS_ALIAS= resultSet.getString("Key");
 	   
 	            MapperKeys.put(KEY,KEYS_ALIAS);
 	            
 	            
 	        }
    	    	 resultSet.close();
    	            statement.close();
//    	            for (String key : MapperKeys.keySet()) {
//    	                System.out.println("Key: " + key + ", Value: " + MapperKeys.get(key));
//    	            }
//    	            System.out.println("end------------");
    		    // Process the result set here

//    		} catch (SQLException e) {
    		    // Handle the exception
//    		}

	       
	
        
    	 KeyMapping example = new KeyMapping();


         example.setItemList(MapperKeys);

       
         Map<String, String>retrievedList = example.getItemList();

      
         return retrievedList;
    	    	 
    	    	 
     }
	 public static Map<String, String>getNonSchedualVehicleKeys() throws SQLException
     {
    	 ArrayList <String>KEYS=new ArrayList<String>();
    	 Map<String, String> MapperKeys = new HashMap<String, String>();
//    	 Connection connection = DriverManager.getConnection(DataBaseurl, DatBaseusername, DatBasepassword);
//    	 Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@"+LaneIPAddress+":1521:XE", "TASAWADE_DB", "CaPitAL_36_iTS");

    	 String sql = "select * from mvw_keyboard_keymapper v where v.vehicle_class is not null AND V.METHOD_OF_PAYMENT='35'";
	        
	       
	        
	        Statement statement = connection.createStatement();
	        ResultSet resultSet = statement.executeQuery(sql);

	        while (resultSet.next()) {
	        
	        	String KEY = resultSet.getString("KEY"); // Replace with the actual column name
	            String KEYS_ALIAS= resultSet.getString("KAY_ALIAS");
	            if(KEY.contains("188"))
	            {
	            	 MapperKeys.put("44",KEYS_ALIAS);
	            	KEYS.add("44");
	            }
	            else if(KEY.contains("190"))
	            {
	            	MapperKeys.put("46",KEYS_ALIAS);
	            	KEYS.add("46");
	            }
	            else
	            {
	            	MapperKeys.put("47",KEYS_ALIAS);
	            	KEYS.add("47");
	            }
//	            MapperKeys.put(KEY,KEYS_ALIAS);
	             // Replace with the actual column name
	            
	            
	        }
	
        
	        KeyMapping example = new KeyMapping();


	        example.setItemList(MapperKeys);

       
         Map<String, String> retrievedList = example.getItemList();

      
         return retrievedList;
     }
	 public static String getExemptKey() throws SQLException
	 {
		 String sql = "select * from mvw_keyboard_keymapper t where t.kay_alias='EX' and t.extra2 is null";
		 Statement statement = connection.createStatement();
		 String KEY="";
	        ResultSet resultSet = statement.executeQuery(sql);
	        while (resultSet.next()) {
		        
	        	KEY = resultSet.getString("KEY"); // Repla
	        }
		 return KEY;
	 }
}
