package toll.tcm.Database;

import java.awt.AWTException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import toll.tcm.Hardware.GetAVCData;
import toll.tcm.testCases.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.plexus.personality.plexus.lifecycle.phase.Suspendable;
import org.testng.reporters.jq.Main;

import com.aventstack.extentreports.ExtentTest;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class Mapping extends BaseClass
	
{	
	static Logger logger=LogManager.getLogger(Mapping.class);

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
//		 ExtentTest KeyMappingg=extent.createTest("KeyMappingg");
		
		ArrayList <String>ExemptTypesOfVehicleClass=new ArrayList<String>();	
		keysArrayForSchedualVehicle = Mapping.getSchedualVehicleKeys().keySet().toArray(new String[0]);
		 keysArrayVehicleCode = Mapping.getVehicleCodeWithASCII().keySet().toArray(new String[0]);
		 // The index value from which the loop will start
		 logger.info("Schedual Vehicle KeyMapping:");
		 //KeyMappingg.info("Schedual Vehicle KeyMapping:");
		for (int i = 0; i < keysArrayVehicleCode.length; i++) {
            String key = keysArrayVehicleCode[i];
            String value = Mapping.getVehicleCodeWithASCII().get(key);
            ClassCodes.put(key, value);
            logger.info("Vehicle Code: " + key + ", ASCII Key:" + value); 
            //KeyMappingg.info("Vehicle Code: " + key + ", ASCII Key:" + value);
        }
        for (int i = 0; i < keysArrayForSchedualVehicle.length; i++) {
            String key = keysArrayForSchedualVehicle[i];
            String value = Mapping.getSchedualVehicleKeys().get(key);
            SchedualClassTypes.put(key, value);
            logger.info("ASCII Key: " + key + ", Vehicle Alias: " + value);
            //KeyMappingg.info("ASCII Key: " + key + ", Vehicle Alias: " + value);
            
        }
        
         keysArrayForNonSchedualVehicle = Mapping.getNonSchedualVehicleKeys().keySet().toArray(new String[0]);
		 // The index value from which the loop will start
         logger.info("Non Schedual Vehicle KeyMapping:");
         //KeyMappingg.info("Non Schedual Vehicle KeyMapping:");
       for (int i = 0; i < keysArrayForNonSchedualVehicle.length; i++) {
           String key = keysArrayForNonSchedualVehicle[i];
           String value = Mapping.getNonSchedualVehicleKeys().get(key);
           NonSchedualClassTypes.put(key, value);
           logger.info("ASCII Key: " + key + ", Value: " + value);
           //KeyMappingg.info("ASCII Key: " + key + ", Value: " + value);
           
       }
       logger.info("CD and Class");
       CDANDCLASS=Mapping.getCDAndClass();
       keysArrayForCDAndClass=CDANDCLASS.keySet().toArray(new String[0]);
       for (int i = 0; i < keysArrayForCDAndClass.length; i++) {
           String key = keysArrayForCDAndClass[i];
           String value = CDANDCLASS.get(key);
           CDANDCLASS.put(key, value);
           logger.info("Vehicle CD: " + key + ", Class: " + value);
           //KeyMappingg.info("Vehicle CD: " + key + ", Class: " + value);
//           VehicleCDWiseExemptType.add(key);
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
		System.out.println(VehicleCDWiseExemptType.size());
		GetExemptType.getVehicleCDWiseExempt();
		logger.info("Available Exempt Class Code:"+VehicleCDWiseExemptType);
		//KeyMappingg.info("Available Exempt Class Code:"+VehicleCDWiseExemptType);
//		System.out.println("|||");
		ExemptKey=getExemptKey();
		logger.info("Exempt Key ASCII Value:"+ExemptKey);
		//KeyMappingg.info("Exempt Key ASCII Value:"+ExemptKey);
		OtherCashKey=getOtherCashKeys().size();
		keysArrayForOtherCashKeys=Mapping.getOtherCashKeys().keySet().toArray(new String[0]);
		  logger.info("Other Cash MOP KeyMapping:");
		  //KeyMappingg.info("Other Cash MOP KeyMapping:");
	       for (int i = 0; i < keysArrayForOtherCashKeys.length; i++) {
	           String key = keysArrayForOtherCashKeys[i];
	           String value = Mapping.getOtherCashKeys().get(key);
	           OtherCashKeys.put(key, value);
	           logger.info("ASCII Key: " + key + ", Value: " + value);
	           //KeyMappingg.info("ASCII Key: " + key + ", Value: " + value);
	           
	       }
//	       logger.info(GetWimSubClass.toll_wim_class_details());
//	       //KeyMappingg.info(GetWimSubClass.toll_wim_class_details().toString());
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
//	    	   //KeyMappingg.info("class:"+Class);
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
	       Cash=GetPaymentTypes.getPaymentSubTypes("CASH");
			  Wallet=GetPaymentTypes.getPaymentSubTypes("WALLET");
			  Card=GetPaymentTypes.getPaymentSubTypes("CARD");
	       GetPaymentTypes.IsReference();
	       logger.info(allReferenceFlags);
	       System.out.println(PaymentTypeSize);
	       int count=0;
	       for (String key : allReferenceFlags.keySet()) {
	    	   if(count==0)
	    	   {
	    		   
	    	   }
	    	   else
	    	   {
	    		   ++PaymentTypeSize;
	    	   }
	    	    int sizeOfInnerMap = allReferenceFlags.get(key).size();
	    	    logger.info("Size of the inner map associated with key '" + key + "': " + sizeOfInnerMap);
	    	    count++;
	    	}
	       
	       System.out.println(PaymentTypeSize);
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
	        GetEmployeeMaster.getMaintainanceId();
	        //KeyMappingg.info("\n"+output);
	        
//	        String flags1=allVehicleIsExemptRemarkFlags.toString();
//		       String output1 = flags1.replaceAll("},", "},\n");
//		       	logger.info("Is ExemptRemark Flags");
//
//		        // Print the formatted string
//		        logger.info("\n"+output1);
//		        //KeyMappingg.info("\n"+output1);
		        
	        IsExemptRemarkKent=GetExemptType.isExemptRemarkFlagKent();
	        keysArrayForIsExemptRemarkKent=GetExemptType.isExemptRemarkFlagKent().keySet().toArray(new String[0]);
			  logger.info("IsExemtRemarkKent Mapping:");
			  //KeyMappingg.info("IsExemptRemarkKent KeyMapping:");
		       for (int i = 0; i < keysArrayForIsExemptRemarkKent.length; i++) {
		           String key = keysArrayForIsExemptRemarkKent[i];
		           String value = IsExemptRemarkKent.get(key);
		           
		           logger.info("Exempt Type :" + key + ", Flag: " + value);
		           //KeyMappingg.info("Exempt Type :" + key + ", Flag: " + value);
		           
		       }
		        String flags2=allReferenceFlags.toString();
			       String output2 = flags2.replaceAll("},", "},\n");
			       	logger.info("Is Reference Flags");

			        // Print the formatted string
			        logger.info("\n"+output2);
			        //KeyMappingg.info("\n"+output2);
			        
			        
			        
			        AVC_Profiler_CDs=get_Avc_Class_Mapping();
			        String[] keys = AVC_Profiler_CDs.keySet().toArray(new String[0]);
			        
			        logger.info("TLC to Profiler Codes:");
					  //KeyMappingg.info("TLC to Profiler Codes:");
					  for (int i = 0; i < keys.length; i++) {
				             
				           String key = keys[i];
				           Iterable<String> values1 = AVC_Profiler_CDs.get(key); 
				          
				           logger.info("Vehicle CD:" + key + ", Profiler CD:" + values1);
				           //KeyMappingg.info("Vehicle CD:" + key + ", Profiler CD:" + values1);
				           int j = 0;
				            for (String value : values) {
				                System.out.println("Index: " + j + ", Value: " + value);
				                j++;
				            }
				           
				       }
					  KentTollMaster=getKentTollMaster();
					  FastagVehicleMaster=GetFastagVehicleMaster.getFastagVehicleMaster();
					  logger.info(FastagVehicleMaster);
					  logger.info(FastagVehicleMasterETCClass);
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
	public static ArrayList<String> getKentTollMaster() throws SQLException
	{
		ArrayList <String>Keys=new  ArrayList<String>();
		String sql="select * from mvw_kent_toll_master v where v.toll_name <>'"+Toll_Name+"'";
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		 while (resultSet.next()) {
		        
 	            String KEY = resultSet.getString("toll_name"); 
 	            Keys.add(KEY);
 	           
		 }
		 return Keys;
	}
		public static void main(String[] args) throws SQLException {
			System.out.println(getSchedualVehicleKeys().size());
			System.out.println(getNonSchedualVehicleKeys());
		}
		public static Map<String, String> getCDAndClass() throws SQLException
		{
			Map<String, String> CDToProfiler = new HashMap<String, String>();
			Map<String, String> ActualCDToProfiler = new HashMap<String, String>();
//			
			Map<String, String> MapperKeys = new HashMap<String, String>();
			ArrayList <String>Keys=new  ArrayList<String>();
			String sql="select * from mvw_keyboard_keymapper v where v.vehicle_class is not null";
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			 while (resultSet.next()) {
				 String KEY = resultSet.getString("Vehicle_class"); // Replace with the actual column name
	 	            String KEYS_ALIAS= resultSet.getString("KAY_ALIAS");
	 	   
	 	            MapperKeys.put(KEY,KEYS_ALIAS);
	 	            
	 	            
	 	            
	 	        }
	    	    	 resultSet.close();
	    	            statement.close();

		       
		
	    	            Mapping example = new Mapping();


	    		        example.setItemList(MapperKeys);

	    	       
	    	         Map<String, String> retrievedList = example.getItemList();

	    	      
	    	         return retrievedList;
//	       
//	         Map<String, String>retrievedList = example.getItemList();
//	         
//	         Map<String, String> sortedMap = new TreeMap<>(retrievedList);
//
//	         String []keysArrayForCDAndClass=sortedMap.keySet().toArray(new String[0]);
//	         int AllCDLenght=keysArrayForCDAndClass.length;
//
//	         for ( int i = 0; i<AllCDLenght;i++ ) { 
//	         String CD = keysArrayForCDAndClass[i];
//	         String Vehicle = retrievedList.get(CD);
//	         
//	         String n="";
//	         int count=0;
//	        boolean found =false;
//	         for(int j=0;j<GetAVCData.standardCD().length;j++)
//	         {
//	        	 
//	        	 if(GetAVCData.standardCD()[j].equals(CD))
//	        	 {
//	        		 count=1;
//	        		 
//	        		 
//
//	        		 found=true;
//
//	        		 break;
//	        		 
//	        	 }
//	        	 else if(!CD.equals(GetAVCData.standardCD()[j]))
//	        	 {
//
//	        		count=0;
//	        		found=false;
//	        		 
//	        	 }
//	        	 
//	         }
//	         if(found==false)
//	         {
//	        	 
//	        	 
//	         }
//	         else
//	         {
//	        	 
//	        	
//	        	 ActualCDToProfiler.put(GetAVCData.CDToProfilerConverter(CD), Vehicle);
//	        	 count=0;
//	         }
//
//	         
//	        
//	         }
//	         
//	      
//	         return ActualCDToProfiler;
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
    
    	 String sql = "select * from mvw_keyboard_keymapper v where v.vehicle_class is not null AND (V.METHOD_OF_PAYMENT>'35' or V.METHOD_OF_PAYMENT='00')";
	        
	       
	        
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

	       
	
        
    	 Mapping example = new Mapping();


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

    	 String sql = "select *\r\n"
    	 		+ "  from MVW_keyboard_keymapper t\r\n"
    	 		+ " where t.vehicle_class is null\r\n"
    	 		+ "   and (t.method_of_payment is not null and t.method_of_payment <> 'X')\r\n"
    	 		+ "   and t.kay_alias NOT IN( 'EX','LTP','LT')";
	        
	       
	        
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

	       
	
        
    	 Mapping example = new Mapping();


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

	       
	
        
    	 Mapping example = new Mapping();


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
	
        
	        Mapping example = new Mapping();


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
	 public static Multimap<String, String> get_Avc_Class_Mapping() throws SQLException
	 {
		 Map<String, String> MapperKeys = new HashMap<String, String>();
			String sql="select * from mvw_kent_avc_class_mapping";
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			DecimalFormat twodigits = new DecimalFormat("00");
			 while (resultSet.next()) {
				 String AVC_CD = resultSet.getString("Avc_Vehicle_Cd"); // Replace with the actual column name
	 	            String TLC_CD= resultSet.getString("TLC_Vehicle_Class");
	 	           String Avc_Vehicle_Cd=twodigits.format(Integer.valueOf((AVC_CD)));
	 	          String TLC_Vehicle_Class=twodigits.format(Integer.valueOf((TLC_CD)));
	 	            MapperKeys.put(Avc_Vehicle_Cd,TLC_Vehicle_Class);
//	 	            System.out.println(Avc_Vehicle_Cd+" "+TLC_Vehicle_Class);
	 	            
	 	            
	 	        }
	    	    	 resultSet.close();
	    	            statement.close();
	    	            Mapping example = new Mapping();


	    	            example.setItemList(MapperKeys);

	    	          
	    	            Map<String, String>retrievedList = example.getItemList();
//	    	            System.out.println(retrievedList);
	    	            keysArrayForProfilerVehicleCode= retrievedList.keySet().toArray(new String[0]);
	    	            
	    	            for (int i = 0; i < retrievedList.size(); i++) {
	    	                String keyfirst = keysArrayForProfilerVehicleCode[i];
	    	                String value1 = retrievedList.get(keyfirst);
//	    	                System.out.println(value1);
	    	                int count=1;
	    	                String temp="null";
	    	                for(int j=i+1;j<retrievedList.size();j++)
	    	                {
	    	                	
	    	                	String keysecond=keysArrayForProfilerVehicleCode[j];
	    	                	String value2 = retrievedList.get(keysecond);
	    	                	if(value1.equals(value2))
	    	                	{
	    	                		AVC_Profiler_CDs.put( value1,keyfirst);
	    	                		AVC_Profiler_CDs.put( value1,keysecond);
	    	                		count++;
	    	                		temp=value2;
//	    	                		System.out.print("value1: "+value1+" value2: "+value2+" keyfirst: "+keyfirst+" keysecond: "+keysecond);
	    	                	}
	    	                	
	    	                }
	    	                if(count>1)
		                	{
//		                		System.out.println(" round "+i+" count is more key is :"+temp+" value 1: "+value1);
		                	}
		                	else
		                	{
//		                		System.out.println(" round "+i+" count is 1 key is :"+keyfirst+" value:1 "+value1);
		                		AVC_Profiler_CDs.put( value1,keyfirst);
		                	}
	    	               
	    	                
	    	             
//	    	                System.out.println("ASCII Key: " + key + ", Value: " + value);
	    	                
	    	            }
//	    	            System.out.println(AVC_Profiler_CDs);
	    	            
	    	           
	    	            
	    	            // Iterate over the entries of the Multimap
	    	            for (Entry<String, Collection<String>> entry : AVC_Profiler_CDs.asMap().entrySet()) {
	    	                String key = entry.getKey();
	    	                Collection<String> values = entry.getValue();
	    	                
	    	                // Create a Set to store unique child records
	    	                Set<String> uniqueValues = new HashSet<>(values);
	    	                
	    	                // Update the Multimap entry with unique child records
	    	                AVC_Profiler_CDs.replaceValues(key, uniqueValues);
	    	            }
	    	            return AVC_Profiler_CDs;
	 }
}
