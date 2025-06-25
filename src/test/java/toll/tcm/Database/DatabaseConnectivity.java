package toll.tcm.Database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.sql.ResultSet;
import oracle.jdbc.*;
import toll.tcm.Hardware.*;
import toll.tcm.testCases.BaseClass;

public class DatabaseConnectivity extends BaseClass
{
	static Logger logger=LogManager.getLogger(DatabaseConnectivity.class);
	public static ArrayList getAllLanes() throws SQLException
	{
		ArrayList <String>AllLanes=new ArrayList<String>();
   	 System.out.println(DataBaseurl+" "+ DatBaseusername+" "+ DatBasepassword);
   	 Connection connection = DriverManager.getConnection(DataBaseurl, DatBaseusername, DatBasepassword);
	        String sql = "select * from mvw_toll_lane_master t where t.toll_id= (select toll_id from mvw_kent_present_toll)";
	        
	       
	        
	        Statement statement = connection.createStatement();
	        ResultSet resultSet = statement.executeQuery(sql);

	        while (resultSet.next()) {
	        
	            String Lane = resultSet.getString("LANE_CD"); // Replace with the actual column name

	            AllLanes.add(Lane);
	            
	            
	        }
	
       
	        GetWimSubClass example = new GetWimSubClass();


        example.setItemList(AllLanes);

      
        ArrayList<String> retrievedList = example.getItemList();

     
        return retrievedList;
	}

	public static void main(String[] args) throws Exception 
	{
		DatabaseConnectivity d= new DatabaseConnectivity();
		connection = DriverManager.getConnection(DataBaseurl, DatBaseusername, DatBasepassword);
			d.getTollInfo();
//		String timeStamp=new SimpleDateFormat("dd/MMM/yyyy").format(new Date());
//		System.out.println(timeStamp);
//		d.getNumberOfTransaction();
		
	}
//	ExtentTest getTollInfo=extent.createTest("getTollInfo");
	public  void getTollInfo() throws Exception
	{
		try {
			
			
//			logger.info("Database Successfully Connected....");
//			//getTollInfo.info("Database Successfully Connected....");
            String sql = "select c.ip_address, t.lane_id,t.toll_id, t.in_out,m.toll_name ,p.avc_com,h.exit_avc_com_no,t.lane_cd,h.wim_com_no,t.is_login_authorization,p.extra13,p.extra4,p.extra5,p.extra3,h.is_avc_client,p.extra14,p.extra12,h.exempt_camera_exist_flg,h.profilerportno,h.tag_gun_type\r\n"
            		+ "                                  from current_lane_information  c,\r\n"
            		+ "                                       mvw_toll_lane_master      t,\r\n"
            		+ "                                       mvw_lane_config_parameter p,\r\n"
            		+ "                                      mvw_toll_lane_hardware_info h,\r\n"
            		+ "                                      mvw_kent_toll_master m\r\n"
            		+ "                                where c.ip_address = t.ip_address\r\n"
            		+ "                                 and t.lane_id = p.lane_id\r\n"
            		+ "                                  and p.lane_id = h.lane_id\r\n"
            		+ "                                  and t.toll_id=m.toll_id\r\n"
            		+ "                                  and t.toll_id= (select toll_id from mvw_kent_present_toll)\r\n"
            		+ "                                group by c.ip_address, t.lane_id, t.toll_id,t.in_out,p.avc_com,h.exit_avc_com_no,t.lane_cd,h.wim_com_no,m.toll_name,t.is_login_authorization,p.extra13,p.extra4,p.extra5,p.extra3,h.is_avc_client,p.extra14,p.extra12,h.exempt_camera_exist_flg,h.profilerportno,h.tag_gun_type";

           
            
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                // Retrieve data from each row for the specific column
                IP_Address = resultSet.getString("ip_address"); // Replace with the actual column name
                Lane_ID = resultSet.getString("lane_id");
                AVC_COM = resultSet.getString("avc_com");
                Exit_Avc_Com_No= resultSet.getString("exit_avc_com_no");
                Lane_CD = resultSet.getString("lane_cd");
                WIM_COM_NO = resultSet.getString("wim_com_no");
                Toll_Name=resultSet.getString("Toll_Name");
                Is_LSDU=resultSet.getString("is_login_authorization");
                Is_ETC_Popup=resultSet.getString("extra3");
                Is_ManualInsertWeight="Not in Use"; //p.extra13
                Is_0Weight_Insert=resultSet.getString("extra4");
                Is_Weight_Delete=resultSet.getString("extra5");
                In_Out=resultSet.getString("in_out");
                Is_Profile_IP=resultSet.getString("is_avc_client");
                Is_ETCAutoLogin=resultSet.getString("extra14");
                Toll_ID=resultSet.getString("Toll_ID");
                Is_Exempt_Remark=resultSet.getString("Extra12");
                Exempt_Camera_Exist_Flg=resultSet.getString("Exempt_Camera_Exist_Flg");
                ProfilerPortNo=resultSet.getString("profilerportno");
                Tag_Gun_Type=resultSet.getString("Tag_Gun_Type");
                // Process the retrieved data
                logger.info("toll name: "+Toll_Name+"|\n Toll_ID:"+Toll_ID+"| \nlane_ip_address :"+IP_Address+"| \nlane_id :"+Lane_ID+"| \navc_com :"+AVC_COM+"| \nexit_avc_com_no :"+Exit_Avc_Com_No+"| \nlane_alias :"+Lane_CD+"| \nwim_com :"+WIM_COM_NO+"| \nIsLSDU:"+Is_LSDU+"| \nIs_ETC_Popup:"+Is_ETC_Popup+"| \nIs_ManualInsertWeight:"+Is_ManualInsertWeight+"| \nis_0_Weight Insert:"+Is_0Weight_Insert+"| \nis_Weight_Delete:"+Is_Weight_Delete+"| \nIn_Out:"+In_Out+"|\nIs_ETCAutoLogin:"+Is_ETCAutoLogin+"| \nExempt_Camera_Exist_Flg:"+Exempt_Camera_Exist_Flg+"|\nProfiler Port No:"+ProfilerPortNo+"|t \nTag_Gun_Type"+Tag_Gun_Type);
                //getTollInfo.info("toll name: "+Toll_Name+"| \nlane_ip_address :"+IP_Address+"| \nlane_id :"+Lane_ID+"| \navc_com :"+AVC_COM+"| \nexit_avc_com_no :"+Exit_Avc_Com_No+"| \nlane_alias :"+Lane_CD+"| \nwim_com :"+WIM_COM_NO+"| \nIsLSDU:"+Is_LSDU+"| \nIs_ETC_Popup:"+Is_ETC_Popup+"| \nIs_ManualInsertWeight:"+Is_ManualInsertWeight+"| \nis_0_Weight Insert:"+Is_0Weight_Insert+"| \nis_Weight_Delete:"+Is_Weight_Delete+"| \nIn_Out:"+In_Out+"|\nIs_ETCAutoLogin:"+Is_ETCAutoLogin+"| \nExempt_Camera_Exist_Flg:"+Exempt_Camera_Exist_Flg);
                COMPortNameForAVC=comPairing(AVC_COM);
                logger.info("AVC_COM Pairing is: "+COMPortNameForAVC);
                //getTollInfo.info("AVC_COM Pairing is: "+COMPortNameForAVC);
                COMPortNameForExitAVC=comPairing(Exit_Avc_Com_No);
                logger.info("EXIT_AVC_COM Pairing is: "+COMPortNameForExitAVC);
                //getTollInfo.info("EXIT_AVC_COM Pairing is: "+COMPortNameForExitAVC);
                COMPortNameForWeight=comPairing(WIM_COM_NO);
                logger.info("WIM_COM Pairing is: "+COMPortNameForWeight);
                //getTollInfo.info("WIM_COM Pairing is: "+COMPortNameForWeight);
                
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
        	logger.warn("COM Port is not Properly set...");
        	DatabaseConnectivity.warning("COM Port is not Properly set...");
            e.printStackTrace();
        }
		COM_Setup c= new COM_Setup();
		Mapping k=new Mapping();
		k.KeyMappingg();
		
		logger.info("Wim Available  parameter is: "+GetWimSubClass.isWimAvailable());
		//getTollInfo.info("Wim Available  parameter is: "+GetWimSubClass.isWimAvailable());
		IsWimAvailable=GetWimSubClass.isWimAvailable();
		if(IsWimAvailable.contains("Y"))
		{
			getWimCount();
			
		}
		
		getExemptCount();
		PaymentTypes=getPaymentTypes().size();
		PaymentType=getPaymentTypes();
		PaymentSubType=getPaymentSubTypes();
		keysArraygetPaymentTypeIsReference=getPaymentTypeIsReference().keySet().toArray(new String[0]);
		logger.info("PaymentTypeIsReference TextBoxes Mapping:");
		//getTollInfo.info("PaymentTypeIsReference TextBoxes Mapping:");
		
		 for (int i = 0; i < keysArraygetPaymentTypeIsReference.length; i++) {
			 String key = keysArraygetPaymentTypeIsReference[i];
	           String value = getPaymentTypeIsReference().get(key);
	           getPaymentTypeIsReference.put(key, value);
	           logger.info("Payment Type: " + key + ", Is reference: " + value);
	           //getTollInfo.info("Payment Type: " + key + ", Is reference: " + value);
	           	       
		 }
		 logger.info("Is_Card_ref:"+Is_Card_Ref);
		 //getTollInfo.info("Is_Card_ref:"+Is_Card_Ref);
		 FastagVehicleMaster=GetFastagVehicleMaster.getFastagVehicleMaster();
		 GetOperatorList=getOperatorDetails();
//		 System.out.println(GetOperatorList);
		 ETCAutoLoginCheck e=new ETCAutoLoginCheck();
	}
	
	
	
	
	
private static void warning(String string) {
		
		
	}
//	public static String getWIM_COM_NO() {
//		return COMPortNameForWeight;
//	}
//	public static void setWIM_COM_NO(String wIM_COM_NO) {
//		COMPortNameForWeight = wIM_COM_NO;
//	}
//	public static void setIs_LSDU(String IsLSDU) {
//		Is_LSDU = IsLSDU;
//	}
//	public static void setIs_ETC_Popup(String Is_ETCPopup) {
//		Is_ETC_Popup = Is_ETCPopup;
//	}
//	public static String getIP_Address() {
//		return IP_Address;
//	}
//	public static String getIs_LSDU() {
//		return Is_LSDU;
//	}
//	public static String getIs_ETC_Popup() {
//		return Is_ETC_Popup;
//	}
//	public static String getToll_Name() {
//		return Toll_Name;
//	}
//	public static void setIP_Address(String iP_Address) {
//		IP_Address = iP_Address;
//	}
//	public static String getLane_ID() {
//		return Lane_ID;
//	}
//	public static void setLane_ID(String lane_ID) {
//		Lane_ID = lane_ID;
//	}
//	public static String getAVC_COM() {
//		return COMPortNameForAVC;
//	}
//	public static void setAVC_COM(String aVC_COM) {
//		COMPortNameForAVC = aVC_COM;
//	}
//	public static String getExit_Avc_Com_No() {
//		return COMPortNameForExitAVC;
//	}
//	public static void setExit_Avc_Com_No(String exit_Avc_Com_No) {
//		COMPortNameForAVC = exit_Avc_Com_No;
//	}
//	public static String getLane_Alias() {
//		return Lane_Alias;
//	}
//	public static void setLane_Alias(String lane_Alias) {
//		Lane_Alias = lane_Alias;
//	}
	
	
	public static void getWimCount() throws SQLException
	{
		truck=GetWimSubClass.getWimSubClassTruck().size();
		mav3=GetWimSubClass.getWimSubClassMAV3().size();
		mav4=GetWimSubClass.getWimSubClassMAV4().size();
		mav5=GetWimSubClass.getWimSubClassMAV5().size();
		mav6=GetWimSubClass.getWimSubClassMAV6().size();
		mav=GetWimSubClass.getWimSubClassMAV().size();
	    logger.info("Total WimSub classes :"+GetWimSubClass.getWimSubClassTruck()+","+GetWimSubClass.getWimSubClassMAV3()+","+GetWimSubClass.getWimSubClassMAV4()+","+GetWimSubClass.getWimSubClassMAV5()+","+GetWimSubClass.getWimSubClassMAV6());
	}
	public  void getExemptCount() throws SQLException
	{
		
		ExemptTypes=GetExemptType.getExemptType().size();
		logger.info("Total Exempt Types :"+ExemptTypes);
		
	}
	
	public  String comPairing(String com)
	{
		
		int comInteger=Integer.valueOf(com);
		
			if(comInteger%2==0)
			{
				int PairingComInteger=comInteger+1;
				return "COM"+PairingComInteger;
			}
			else if(comInteger%2!=0)
			{
				int PairingComInteger=comInteger-1;
				return "COM"+PairingComInteger;
			}
			
			else
			{
				logger.info("Please Select Proper comport");
				return null;
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


//  	 String sql1 = "SELECT M.VEHICLE_CLASS_ALIAS FROM MVW_TOLL_VEHICLE_MASTER M WHERE M.PAID_UNPAID='P' AND M.IS_OVERWEIGHT_APPLICABLE='Y'  group by m.vehicle_class_alias ORDER BY M.VEHICLE_CLASS_ALIAS";
  
	        
//	        ResultSet resultSet = statement.executeQuery(sql1);
	        
	    	 for(int i=0;i<PaymentType.size();i++) {
  		        
	            String KEY = (String) PaymentType.get(i); // Replace with the actual column name
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
//  	    	 resultSet.close();
//  	    	 statement.close();
  	    	 


	       
	
      
  	 Mapping example = new Mapping();


       example.setItemMultiMap( MapperKeys);

     
       Multimap<String, String>retrievedList = example.getItemMultiMap();

    
       return retrievedList;
	}
	
	public static Map<String, String> getPaymentTypeIsReference() throws SQLException
	{
		
   	 Map<String, String> MapperKeys = new HashMap<String, String>();
//	 Connection connection = DriverManager.getConnection(DataBaseurl, DatBaseusername, DatBasepassword);
//	 Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@"+LaneIPAddress+":1521:XE", "TASAWADE_DB", "CaPitAL_36_iTS");

	 String sql = "select * from mvw_payment_method_master T WHERE T.IS_APPLICABLE_TCM='Y' AND T.IS_REFERENCE_NO='Y'";
	 String sql1="select * from mvw_payment_method_master T WHERE T.IS_APPLICABLE_TCM='Y' AND T.IS_REFERENCE_NO='Y' AND T.PAYMENT_TYPE='CARD'";
	       
        
        Statement statement = connection.createStatement();
        
        ResultSet resultSet = statement.executeQuery(sql);

	    	 while (resultSet.next()) {
		        
	            String Payment_Type = resultSet.getString("PAYMENT_TYPE"); // Replace with the actual column name
	            String Is_Reference_No= resultSet.getString("IS_REFERENCE_NO");
	           
	            MapperKeys.put(Payment_Type,Is_Reference_No);
	            
	            
	            
	        }
	    	 resultSet = statement.executeQuery(sql1);
	    	 while (resultSet.next()) {
	    		 Is_Card_Ref= resultSet.getString("IS_CARD_DATA");
	    	 }
	    	 
	    	 resultSet.close();
	            statement.close();
//	            for (String key : MapperKeys.keySet()) {
//	                System.out.println("Key: " + key + ", Value: " + MapperKeys.get(key));
//	            }
//	            System.out.println("end------------");
		    // Process the result set here

//		} catch (SQLException e) {
		    // Handle the exception
//		}

       

    
	 Mapping example = new Mapping();


     example.setItemList(MapperKeys);

   
     Map<String, String>retrievedList = example.getItemList();

  
     return retrievedList;
        
	}
	public String getNumberOfTransaction() throws SQLException
	{
		String timeStamp=new SimpleDateFormat("dd/MMM/yyyy").format(new Date());
		
			connection = DriverManager.getConnection(url, username, password) ;
            String sql = "select count(*) from lane_transaction_tlc t where t.shift_date='"+timeStamp+"'";

           
            
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                // Retrieve data from each row for the specific column
                String q = resultSet.getString("count(*)"); // Replace with the actual column name
                
                // Process the retrieved data
                logger.info("Number of Transaction: "+q);
                
                return String.valueOf(q);
            }
            
            	return null;
            
        
	}
	 public static ArrayList<String> getOperatorDetails() throws SQLException
     {
		 
		 String sql="SELECT *\r\n"
		 		+ "  FROM MVW_TOLL_EMPLOYEE_MASTER T, MVW_TOLL_EMPLOYEE_DETAIL D ,mvw_kent_designation_master k\r\n"
		 		+ "where (T.STATUS = 'A'\r\n"
		 		+ "   AND T.EMPLOYEE_ID = D.EMPLOYEE_ID\r\n"
		 		+ "   AND D.TOLL_STATUS = 'A'\r\n"
		 		+ "    AND D.TOLL_ID = "+Toll_ID+"\r\n"
		 		+ "    AND k.DESIGNATION_NAME ='OPERATOR'\r\n"
		 		+ "    and t.password='93279e3308bdbbeed946fc965017f67a'\r\n"
		 		+ "    and t.designation_id=k.designation_id\r\n"
		 		+ "   AND D.VALID_TILL_DATE > SYSDATE )";
			Statement statement = connection.createStatement();
	        ResultSet resultSet = statement.executeQuery(sql);
	        ArrayList <String>OperatorList=new  ArrayList<String>();
	        String Login_ID="";
	        while (resultSet.next()) {
	            // Retrieve data from each row for the specific column
	             Login_ID = resultSet.getString("Login_ID"); // Replace with the actual column name
	             OperatorList.add(Login_ID);
	        }
	        GetWimSubClass example = new GetWimSubClass();


	         example.setItemList(OperatorList);

	       
	         ArrayList<String> retrievedList = example.getItemList();

	      
	         return retrievedList;
	        
     }
	public  String getSysdate() throws SQLException
	{
		Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        
            // Define the JDBC connection parameters
         

            // Create a JDBC connection
        	connection = DriverManager.getConnection(DataBaseurl, DatBaseusername, DatBasepassword);

            // Create an SQL query that uses sysdate
        	String sql = "SELECT sysdate FROM dual";

            // Create a prepared statement
            preparedStatement = connection.prepareStatement(sql);

            // Execute the query
            resultSet = preparedStatement.executeQuery();
            String formattedDate="";
            // Process the result
            if (resultSet.next()) {
                // Retrieve the sysdate as a Timestamp
                Timestamp sysdateTimestamp = resultSet.getTimestamp(1);

                // Format the Timestamp as "DD/MMM/YYYY HH:MM:SS"
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy HH:mm:ss");
                formattedDate = dateFormat.format(sysdateTimestamp);

                System.out.println("Formatted Date (sysdate): " + formattedDate.toString());
            }
            return formattedDate.toString();
      
        
	}
	public static String getLatestTransaction() throws SQLException
	{
		
		String sql="select *\r\n"
				+ "  from lane_transaction_tlc     t,\r\n"
				+ "       current_lane_information c,\r\n"
				+ "       mvw_toll_lane_master     m\r\n"
				+ " where c.ip_address = m.ip_address\r\n"
				+ "   and t.lane_id = m.lane_id\r\n"
				+ " order by t.transaction_date desc";
		Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        String LatestTransatcion="";
        while (resultSet.next()) {
            // Retrieve data from each row for the specific column
              LatestTransatcion = resultSet.getString("transaction_tlc_id"); // Replace with the actual column name
            break;
        }
        resultSet.close();
        statement.close();
        return LatestTransatcion;
	}
	
}
