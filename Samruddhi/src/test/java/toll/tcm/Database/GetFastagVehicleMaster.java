package toll.tcm.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import toll.tcm.testCases.BaseClass;

public class GetFastagVehicleMaster extends BaseClass{
	public Map<String, String> getItemList() {
        return itemList;
    } public void setItemList(Map<String, String> itemList) {
        this.itemList = itemList;
    }
	public static  Map<String,String> getFastagVehicleMaster() throws SQLException {

		
		Map<String, String> Kent_Class_To_ETC_Class = new HashMap<String, String>();

		
//  	 String sql1 = "SELECT M.VEHICLE_CLASS_ALIAS FROM MVW_TOLL_VEHICLE_MASTER M WHERE M.PAID_UNPAID='P' AND M.IS_OVERWEIGHT_APPLICABLE='Y'  group by m.vehicle_class_alias ORDER BY M.VEHICLE_CLASS_ALIAS";
  
	        
//	        ResultSet resultSet = statement.executeQuery(sql1);
	        
	    	 
  		        
	             // Replace with the actual column name
	       	 String sql="SELECT * FROM mvw_fastag_vehicle_master";		       		        
	       	 Statement statement = connection.createStatement();
	                Statement preparedStatement2 = connection.createStatement();
	            	ResultSet resultSet2 = preparedStatement2.executeQuery(sql);
	            while(resultSet2.next())
	            {
	            	
	            	String Kent_Vehicle_Class= resultSet2.getString("Kent_Vehicle_Class");
	            	String ETC_Class= resultSet2.getString("ICICI_Vehicle_Class");
	            	Kent_Class_To_ETC_Class.put(Kent_Vehicle_Class,ETC_Class);
	            	FastagVehicleMasterETCClass.add(ETC_Class);
	            }
	           resultSet2.close();
	    	 preparedStatement2.close();
	            
	            
	            
	        
//  	    	 resultSet.close();
//  	    	 statement.close();
	    	 GetFastagVehicleMaster example = new GetFastagVehicleMaster();


		        example.setItemList(Kent_Class_To_ETC_Class);

	       
	         Map<String, String> retrievedList = example.getItemList();

	      
	         return retrievedList;

	       
	
      
  	 
	
	}

}
