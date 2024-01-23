package toll.tcm.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.aventstack.extentreports.ExtentTest;

import toll.tcm.testCases.*;

public class GetExemptType extends BaseClass{
	
	public static String getExemptTypeFromExcel(String Exempt_Type_Id) throws SQLException {
//		DataBaseurl = "jdbc:oracle:thin:@"+LaneIPAddress+":1521:XE"; // Replace with your database URL
//		DatBaseusername = XEUsername; // Replace with your username1
//		DatBasepassword = XEPassword;
//		Connection connection = DriverManager.getConnection(DataBaseurl, DatBaseusername, DatBasepassword);
//		
		
		String sql="select k.exempt_type from mvw_toll_exempt_type t, mvw_kent_exempt_type_master k where t.toll_exempt_id="+Exempt_Type_Id+" and k.kent_exempt_id=t.kent_exempt_id";
		Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        String Exempt_Type="";
        while (resultSet.next()) {
            // Retrieve data from each row for the specific column
           Exempt_Type = resultSet.getString("Exempt_Type"); // Replace with the actual column name
            
            // Process the retrieved data
           
            
           
        }
        return Exempt_Type;
	}
	   public static ArrayList getExemptType() throws SQLException
	     {
	    	 ArrayList <String>wIMSubClassTruck=new ArrayList<String>();
//	    	 Connection connection = DriverManager.getConnection(DataBaseurl, DatBaseusername, DatBasepassword);
	    	 String sql = "select * from (select  t.version_no,m.exempt_type, rank() over (order by t.version_no desc) as available_exempt from  mvw_toll_exempt_type t,mvw_kent_exempt_type_master m where t.kent_exempt_id=m.kent_exempt_id group by m.exempt_type,t.version_no) h where available_exempt=1";
//		     String sql="select count(*) from (select  t.version_no,m.exempt_type, rank() over (order by t.version_no desc) as available_exempt from  mvw_toll_exempt_type t,mvw_kent_exempt_type_master m where t.kent_exempt_id=m.kent_exempt_id  and t.vehicle_cd like '%05' group by m.exempt_type,t.version_no) h where available_exempt=1";
		       
		        
		        Statement statement = connection.createStatement();
		        ResultSet resultSet = statement.executeQuery(sql);

		        while (resultSet.next()) {
		        
		            String WIM_CLASS = resultSet.getString("EXEMPT_TYPE"); // Replace with the actual column name

		            wIMSubClassTruck.add(WIM_CLASS);
		            
		            
		        }
		
	        
	    	 GetWimSubClass example = new GetWimSubClass();


	         example.setItemList(wIMSubClassTruck);

	       
	         ArrayList<String> retrievedList = example.getItemList();

	      
	         return retrievedList;
	     }
	   public static ArrayList getVehicleCDWiseExemptType() throws SQLException
	     {
	    	 ArrayList <String>wIMSubClassTruck=new ArrayList<String>();
//	    	 Connection connection = DriverManager.getConnection(DataBaseurl, DatBaseusername, DatBasepassword);
	    	 String sql = "select * from mvw_keyboard_keymapper v where v.vehicle_class is not null AND (V.METHOD_OF_PAYMENT='58' or V.METHOD_OF_PAYMENT='X')";
		        
		       
		        
		        Statement statement = connection.createStatement();
		        ResultSet resultSet = statement.executeQuery(sql);

		        while (resultSet.next()) {
		        
		            String VEHICLE_CLASS = resultSet.getString("VEHICLE_CLASS"); // Replace with the actual column name

		            wIMSubClassTruck.add(VEHICLE_CLASS);
		            
		            
		        }
		
	        
	    	 GetWimSubClass example = new GetWimSubClass();


	         example.setItemList(wIMSubClassTruck);

	       
	         ArrayList<String> retrievedList = example.getItemList();

	      
	         return retrievedList;
	     }
	   
	   public static ArrayList getVehicleCDWiseExempt() throws SQLException
	   {
//		   System.out.println(SchedualClassTypes.size());
		   ExtentTest GetExemptType=extent.createTest("GetExemptType");
		   ArrayList <String>ExemptTypesOfVehicleClass=new ArrayList<String>();
//		   System.out.println(VehicleCDWiseExemptType.size()+"-----");
			for (int i = 0; i < VehicleCDWiseExemptType.size(); i++) 
			{
	            String CD = VehicleCDWiseExemptType.get(i);
	            Collection<String> values = SchedualClassTypes.values();
	            List<String> valuesList = new ArrayList<String>(SchedualClassTypes.values());
//	            System.out.println("values:::"+valuesList.get(i));
	            String Vehicle=valuesList.get(i);
			     String sql="select * from (select  t.version_no,m.exempt_type, rank() over (order by t.version_no desc) as available_exempt from  mvw_toll_exempt_type t,mvw_kent_exempt_type_master m where t.kent_exempt_id=m.kent_exempt_id  and t.vehicle_cd like '%"+CD+"' and t.toll_id= (select toll_id from mvw_kent_present_toll) group by m.exempt_type,t.version_no) h where available_exempt=1";
			     Statement statement = connection.createStatement();
			        ResultSet resultSet = statement.executeQuery(sql);
			        while (resultSet.next()) {
			        			String VEHICLE_CLASS = resultSet.getString("Exempt_type");
			        			ExemptTypesOfVehicleClass.add(VEHICLE_CLASS);
			        }
			        if(CD.contains("04")&&Vehicle.contains("CAR"))
			        {
			        	AvailableExemptCar.addAll(ExemptTypesOfVehicleClass);
			        	logger.info("Exempt Type of "+Vehicle+":"+AvailableExemptCar.size());
			        	GetExemptType.info("Exempt Type of "+Vehicle+":"+AvailableExemptCar.size());

			        }
			        else if((CD.contains("15")&&Vehicle.contains("MAV"))||(CD.contains("15")&&Vehicle.contains("OSV")))
			        {
			        	if(Vehicle.contains("MAV"))
			        	{
			        		
			        		AvailableExemptMAV.addAll(ExemptTypesOfVehicleClass);
			        		logger.info("Exempt Type of "+Vehicle+":"+AvailableExemptMAV.size());
			        		GetExemptType.info("Exempt Type of "+Vehicle+":"+AvailableExemptMAV.size());
			        		ExemptTypesOfVehicleClass.clear();
			        	}
			        	else
			        	{
			        		AvailableExemptOSV.addAll(ExemptTypesOfVehicleClass);
			        		logger.info("Exempt Type of "+Vehicle+":"+AvailableExemptOSV.size());
			        		GetExemptType.info("Exempt Type of "+Vehicle+":"+AvailableExemptOSV.size());
			        		ExemptTypesOfVehicleClass.clear();
			        	}			        	
			        }
			        else if(((CD.contains("05")||(CD.contains("09")))&&Vehicle.contains("LCV")))
			        		{
			        			AvailableExemptLCV.addAll(ExemptTypesOfVehicleClass);
			        			logger.info("Exempt Type of "+Vehicle+":"+AvailableExemptLCV.size());
			        			GetExemptType.info("Exempt Type of "+Vehicle+":"+AvailableExemptLCV.size());
			        		}
			        else if((CD.contains("06")&&Vehicle.contains("MAV 3")))
	        		{
	        			AvailableExemptMAV3.addAll(ExemptTypesOfVehicleClass);
	        			logger.info("Exempt Type of "+Vehicle+":"+AvailableExemptMAV3.size());
	        			GetExemptType.info("Exempt Type of "+Vehicle+":"+AvailableExemptMAV3.size());
	        		}
			        else if((CD.contains("07")&&Vehicle.contains("BUS")))
	        		{
	        			AvailableExemptBus.addAll(ExemptTypesOfVehicleClass);
	        			logger.info("Exempt Type of "+Vehicle+":"+AvailableExemptBus.size());
	        			GetExemptType.info("Exempt Type of "+Vehicle+":"+AvailableExemptBus.size());
	        		}
			        else if((CD.contains("08")&&Vehicle.contains("MAV 3")))
	        		{
	        			AvailableExemptMAV3.addAll(ExemptTypesOfVehicleClass);
	        			logger.info("Exempt Type of "+Vehicle+":"+AvailableExemptMAV3.size());
	        			GetExemptType.info("Exempt Type of "+Vehicle+":"+AvailableExemptMAV3.size());
	        		}
			        else if((CD.contains("10")&&Vehicle.contains("TRUCK")))
	        		{
	        			AvailableExemptTruck.addAll(ExemptTypesOfVehicleClass);
	        			logger.info("Exempt Type of "+Vehicle+":"+AvailableExemptTruck.size());
	        			GetExemptType.info("Exempt Type of "+Vehicle+":"+AvailableExemptTruck.size());
	        		}
			        else if((CD.contains("11")&&Vehicle.contains("MAV 3")))
	        		{
	        			AvailableExemptMAV3.addAll(ExemptTypesOfVehicleClass);
	        			logger.info("Exempt Type of "+Vehicle+":"+AvailableExemptMAV3.size());
	        			GetExemptType.info("Exempt Type of "+Vehicle+":"+AvailableExemptMAV3.size());
	        		}
			        else if((CD.contains("12")&&Vehicle.contains("MAV 4")))
	        		{
	        			AvailableExemptMAV4.addAll(ExemptTypesOfVehicleClass);
	        			logger.info("Exempt Type of "+Vehicle+":"+AvailableExemptMAV4.size());
	        			GetExemptType.info("Exempt Type of "+Vehicle+":"+AvailableExemptMAV4.size());
	        		}
			        else if((CD.contains("13")&&Vehicle.contains("MAV 5")))
	        		{
	        			AvailableExemptMAV5.addAll(ExemptTypesOfVehicleClass);
	        			logger.info("Exempt Type of "+Vehicle+":"+AvailableExemptMAV5.size());
	        			GetExemptType.info("Exempt Type of "+Vehicle+":"+AvailableExemptMAV5.size());
	        		}
			        else if((CD.contains("14")&&Vehicle.contains("MAV 6")))
	        		{
	        			AvailableExemptMAV6.addAll(ExemptTypesOfVehicleClass);
	        			logger.info("Exempt Type of "+Vehicle+":"+AvailableExemptMAV6.size());
	        			GetExemptType.info("Exempt Type of "+Vehicle+":"+AvailableExemptMAV6.size());
	        		}
			        else if((CD.contains("15")||(CD.contains("16")||(CD.contains("17")))&&Vehicle.contains("OSV")))
			        {
			        	AvailableExemptOSV.addAll(ExemptTypesOfVehicleClass);
			        	logger.info("Exempt Type of "+Vehicle+":"+AvailableExemptOSV.size());
			        	GetExemptType.info("Exempt Type of "+Vehicle+":"+AvailableExemptOSV.size());
			        }
			    	ExemptTypesOfVehicleClass.clear();
	        }
			GetWimSubClass example = new GetWimSubClass();


	        example.setItemList(ExemptTypesOfVehicleClass);

	      
	        ArrayList<String> retrievedList = example.getItemList();

	     
	        return retrievedList;
	   }
	   public static void ClassesIsCaptureFlags() throws SQLException
	   {
		  
		    
	        Iterator<String> iterator = VehicleCDWiseExemptType.iterator();
	        for(int i=0;i<VehicleCDWiseExemptType.size();i++)
	        {
	        	String VCD=VehicleCDWiseExemptType.get(i).toString();
	        	
	        	if(VCD.contains("04"))
	        	{
	        		CARIsCaptureFlags=isCapture(VehicleCDWiseExemptType.get(i));
	        		allVehicleCaptureFlags.put(VehicleCDWiseExemptType.get(i) , CARIsCaptureFlags);
	        	}
	        	else if(VCD.contains("05"))
	        	{
	        		LCVIsCaptureFlags=isCapture(VehicleCDWiseExemptType.get(i));
	        		allVehicleCaptureFlags.put(VehicleCDWiseExemptType.get(i) , LCVIsCaptureFlags);
	        	}
	        	else if(VCD.contains("07"))
	        	{
	        		BUSIsCaptureFlags=isCapture(VehicleCDWiseExemptType.get(i));
	        		allVehicleCaptureFlags.put(VehicleCDWiseExemptType.get(i) , BUSIsCaptureFlags);
	        	}
	        	else if(VCD.contains("10"))
	        	{
	        		TRUCKIsCaptureFlags=isCapture(VehicleCDWiseExemptType.get(i));
	        		allVehicleCaptureFlags.put(VehicleCDWiseExemptType.get(i) , TRUCKIsCaptureFlags);
	        	}
	        	else if(VCD.contains("11"))
	        	{
	        		MAV3IsCaptureFlags=isCapture(VehicleCDWiseExemptType.get(i));
	        		allVehicleCaptureFlags.put(VehicleCDWiseExemptType.get(i) , MAV3IsCaptureFlags);
	        	}
	        	else if(VCD.contains("12"))
	        	{
	        		MAV4IsCaptureFlags=isCapture(VehicleCDWiseExemptType.get(i));
	        		allVehicleCaptureFlags.put(VehicleCDWiseExemptType.get(i) , MAV4IsCaptureFlags);
	        	}
	        	else if(VCD.contains("13"))
	        	{
	        		MAV5IsCaptureFlags=isCapture(VehicleCDWiseExemptType.get(i));
	        		allVehicleCaptureFlags.put(VehicleCDWiseExemptType.get(i) , MAV5IsCaptureFlags);
	        	}
	        	else if(VCD.contains("14"))
	        	{
	        		MAV6IsCaptureFlags=isCapture(VehicleCDWiseExemptType.get(i));
	        		allVehicleCaptureFlags.put(VehicleCDWiseExemptType.get(i) , MAV6IsCaptureFlags);
	        	}
	        	else if(VCD.contains("15"))
	        	{
	        		OSVIsCaptureFlags=isCapture(VehicleCDWiseExemptType.get(i));
	        		allVehicleCaptureFlags.put(VehicleCDWiseExemptType.get(i) , OSVIsCaptureFlags);
	        	}
	        	else if(VCD.contains("17"))
	        	{
	        		OSVIsCaptureFlags=isCapture(VehicleCDWiseExemptType.get(i));
	        		allVehicleCaptureFlags.put(VehicleCDWiseExemptType.get(i) , OSVIsCaptureFlags);	        		
	        	}	        	        	
	        
	        }
	        
	        }
//	        	
	        
	        
	   
	   public static Map<String, String> isCapture(String CD) throws SQLException
	     {
		   Map<String, String> IsCapture = new HashMap<String, String>();
//	    	 Connection connection = DriverManager.getConnection(DataBaseurl, DatBaseusername, DatBasepassword);
	    	 String sql = "SELECT *\r\n"
		    	 		+ "FROM (\r\n"
		    	 		+ "    SELECT t.version_no,\r\n"
		    	 		+ "           t.imagecapture, -- Add the imagecapture column\r\n"
		    	 		+ "           m.exempt_type,\r\n"
		    	 		+ "           RANK() OVER (ORDER BY t.version_no DESC) AS available_exempt\r\n"
		    	 		+ "    FROM mvw_toll_exempt_type t\r\n"
		    	 		+ "    JOIN mvw_kent_exempt_type_master m ON t.kent_exempt_id = m.kent_exempt_id\r\n"
		    	 		+ "    WHERE t.vehicle_cd LIKE '%"+CD+"'\r\n"
		    	 		+ "      AND t.toll_id = (SELECT toll_id FROM mvw_kent_present_toll)\r\n"
		    	 		+ "    GROUP BY t.imagecapture, m.exempt_type, t.version_no\r\n"
		    	 		+ ") h\r\n"
		    	 		+ "WHERE available_exempt = 1\r\n"
		    	 		+ "";
		        
		       
		        
		        Statement statement = connection.createStatement();
		        ResultSet resultSet = statement.executeQuery(sql);

		        while (resultSet.next()) {
		        
		            String Image_Capture = resultSet.getString("ImageCapture"); // Replace with the actual column name
		            String Exempt_Type = resultSet.getString("Exempt_Type");
		            IsCapture.put(Exempt_Type, Image_Capture);
		            
		            
		        }
		        resultSet.close();
	            statement.close();
	            KeyMapping example = new KeyMapping();


	            example.setItemList(IsCapture);

	          
	            Map<String, String>retrievedList = example.getItemList();

	         
	            return retrievedList;
	     }
}
