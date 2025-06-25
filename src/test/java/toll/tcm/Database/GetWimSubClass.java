package toll.tcm.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.aventstack.extentreports.ExtentTest;

import toll.tcm.testCases.*;

public class GetWimSubClass extends BaseClass {
	static Logger logger=LogManager.getLogger(GetWimSubClass.class);

    private ArrayList<String> itemList;

    public GetWimSubClass() {
        itemList = new ArrayList<String>();
    }

    public ArrayList<String> getItemList() {
        return itemList;
    }

    public void setItemList(ArrayList<String> itemList) {
        this.itemList = itemList;
    }

    public static void main(String[] args) throws SQLException {
        System.out.println(getWimSubClassTruck());
        System.out.println(getWimSubClassMAV3());
        System.out.println(getWimSubClassMAV4());
        System.out.println(getWimSubClassMAV5());
        System.out.println(getWimSubClassMAV6());
    }
     public static ArrayList getWimSubClassTruck() throws SQLException
     {
    	 ArrayList <String>wIMSubClassTruck=new ArrayList<String>();
    	 System.out.println(DataBaseurl+" "+ DatBaseusername+" "+ DatBasepassword);
    	 Connection connection = DriverManager.getConnection(DataBaseurl, DatBaseusername, DatBasepassword);
	        String sql = "select * FROM (SELECT T.WIM_CLASS_NAME,T.VERSION_NO ,rank() over(order by t.version_no desc) as available_SUBWIM from mvw_toll_wim_class_details t  where t.wim_class_id like '%_0010%') mvw_toll_wim_class_details WHERE available_SUBWIM=1";
	        
	       
	        
	        Statement statement = connection.createStatement();
	        ResultSet resultSet = statement.executeQuery(sql);

	        while (resultSet.next()) {
	        
	            String WIM_CLASS = resultSet.getString("WIM_CLASS_NAME"); // Replace with the actual column name

	            wIMSubClassTruck.add(WIM_CLASS);
	            
	            
	        }
	
        
    	 GetWimSubClass example = new GetWimSubClass();


         example.setItemList(wIMSubClassTruck);

       
         ArrayList<String> retrievedList = example.getItemList();

      
         return retrievedList;
     }
     public static ArrayList getWimSubClassMAV3() throws SQLException
     {
    	 ArrayList <String>wIMSubClassTruck=new ArrayList<String>();
    	 Connection connection = DriverManager.getConnection(DataBaseurl, DatBaseusername, DatBasepassword);
	        String sql = "select * FROM (SELECT T.WIM_CLASS_NAME,T.VERSION_NO ,rank() over(order by t.version_no desc) as available_SUBWIM from mvw_toll_wim_class_details t  where t.wim_class_id like '%_0011%') mvw_toll_wim_class_details WHERE available_SUBWIM=1";
	        
	       
	        
	        Statement statement = connection.createStatement();
	        ResultSet resultSet = statement.executeQuery(sql);

	        while (resultSet.next()) {
	        
	            String WIM_CLASS = resultSet.getString("WIM_CLASS_NAME"); // Replace with the actual column name

	            wIMSubClassTruck.add(WIM_CLASS);
	            
	            
	        }
	
        
    	 GetWimSubClass example = new GetWimSubClass();


         example.setItemList(wIMSubClassTruck);

       
         ArrayList<String> retrievedList = example.getItemList();

      
         return retrievedList;
     }
     public static ArrayList getWimSubClassMAV4() throws SQLException
     {
    	 ArrayList <String>wIMSubClassTruck=new ArrayList<String>();
    	 Connection connection = DriverManager.getConnection(DataBaseurl, DatBaseusername, DatBasepassword);
	        String sql = "select * FROM (SELECT T.WIM_CLASS_NAME,T.VERSION_NO ,rank() over(order by t.version_no desc) as available_SUBWIM from mvw_toll_wim_class_details t  where t.wim_class_id like '%_0012%') mvw_toll_wim_class_details WHERE available_SUBWIM=1";
	        
	       
	        
	        Statement statement = connection.createStatement();
	        ResultSet resultSet = statement.executeQuery(sql);

	        while (resultSet.next()) {
	        
	            String WIM_CLASS = resultSet.getString("WIM_CLASS_NAME"); // Replace with the actual column name

	            wIMSubClassTruck.add(WIM_CLASS);
	            
	            
	        }
	
        
    	 GetWimSubClass example = new GetWimSubClass();


         example.setItemList(wIMSubClassTruck);

       
         ArrayList<String> retrievedList = example.getItemList();

      
         return retrievedList;
     }
     public static ArrayList getWimSubClassMAV5() throws SQLException
     {
    	 ArrayList <String>wIMSubClassTruck=new ArrayList<String>();
    	 Connection connection = DriverManager.getConnection(DataBaseurl, DatBaseusername, DatBasepassword);
    	 String sql = "select * FROM (SELECT T.WIM_CLASS_NAME,T.VERSION_NO ,rank() over(order by t.version_no desc) as available_SUBWIM from mvw_toll_wim_class_details t  where t.wim_class_id like '%_0013%') mvw_toll_wim_class_details WHERE available_SUBWIM=1";
	        
	       
	        
	        Statement statement = connection.createStatement();
	        ResultSet resultSet = statement.executeQuery(sql);

	        while (resultSet.next()) {
	        
	            String WIM_CLASS = resultSet.getString("WIM_CLASS_NAME"); // Replace with the actual column name

	            wIMSubClassTruck.add(WIM_CLASS);
	            
	            
	        }
	
        
    	 GetWimSubClass example = new GetWimSubClass();


         example.setItemList(wIMSubClassTruck);

       
         ArrayList<String> retrievedList = example.getItemList();

      
         return retrievedList;
     }
     public static ArrayList getWimSubClassMAV6() throws SQLException
     {
    	 ArrayList <String>wIMSubClassTruck=new ArrayList<String>();
    	 Connection connection = DriverManager.getConnection(DataBaseurl, DatBaseusername, DatBasepassword);
    	 String sql = "select * FROM (SELECT T.WIM_CLASS_NAME,T.VERSION_NO ,rank() over(order by t.version_no desc) as available_SUBWIM from mvw_toll_wim_class_details t  where t.wim_class_id like '%_0014%') mvw_toll_wim_class_details WHERE available_SUBWIM=1";
	        
	       
	        
	        Statement statement = connection.createStatement();
	        ResultSet resultSet = statement.executeQuery(sql);

	        while (resultSet.next()) {
	        
	            String WIM_CLASS = resultSet.getString("WIM_CLASS_NAME"); // Replace with the actual column name

	            wIMSubClassTruck.add(WIM_CLASS);
	            
	            
	        }
	
        
    	 GetWimSubClass example = new GetWimSubClass();


         example.setItemList(wIMSubClassTruck);

       
         ArrayList<String> retrievedList = example.getItemList();

      
         return retrievedList;
     }
     public static ArrayList getWimSubClassMAV() throws SQLException
     {
    	 ArrayList <String>wIMSubClassTruck=new ArrayList<String>();
    	 Connection connection = DriverManager.getConnection(DataBaseurl, DatBaseusername, DatBasepassword);
    	 String sql = "select * FROM (SELECT T.WIM_CLASS_NAME,T.VERSION_NO ,rank() over(order by t.version_no desc) as available_SUBWIM from mvw_toll_wim_class_details t  where t.wim_class_id like '%_0015%') mvw_toll_wim_class_details WHERE available_SUBWIM=1";
	        
	       
	        
	        Statement statement = connection.createStatement();
	        ResultSet resultSet = statement.executeQuery(sql);

	        while (resultSet.next()) {
	        
	            String WIM_CLASS = resultSet.getString("WIM_CLASS_NAME"); // Replace with the actual column name

	            wIMSubClassTruck.add(WIM_CLASS);
	            
	            
	        }
	
        
    	 GetWimSubClass example = new GetWimSubClass();


         example.setItemList(wIMSubClassTruck);

       
         ArrayList<String> retrievedList = example.getItemList();

      
         return retrievedList;
     }
     public static String isWimAvailable() throws SQLException
     {
    	 ArrayList <String>wIMSubClassTruck=new ArrayList<String>();
    	 String sql="SELECT c.ip_address , h.lane_id,h.wim_exist_flg FROM MVW_TOLL_LANE_HARDWARE_INFO h,CURRENT_LANE_INFORMATION c,mvw_toll_lane_master t where c.ip_address=t.ip_address and t.lane_id=h.lane_id group by c.ip_address ,h.lane_id,h.wim_exist_flg ";
    	 Statement statement = connection.createStatement();
	        ResultSet resultSet = statement.executeQuery(sql);

	        while (resultSet.next()) {
	        
	            String wim_exist_flg = resultSet.getString("wim_exist_flg"); // Replace with the actual column name

	            wIMSubClassTruck.add(wim_exist_flg);
	            
	            
	        }
    	 return wIMSubClassTruck.get(0);
     }
     private static int getResultSetSize(ResultSet resultSet) throws SQLException {
         int count = 0;
         while (resultSet.next()) {
             count++;
         }
         return count;
     }
     public static Map<String, String> toll_wim_class_details() throws SQLException
	  {
    	 Map<String, String> AllowWeight = new HashMap<String, String>();
//    	 ExtentTest TollWimClassDetails=extent.createTest("TollWimClassDetails");
		  String sql="select t.wim_class_name,t.from_weight,t.to_weight+t.buffer_weight as final_weight from mvw_toll_wim_class_details t order by t.from_weight";
		  Statement statement = connection.createStatement();
         ResultSet resultSet = statement.executeQuery(sql);
         int i=0;
        
         
        
//         logger.info("Class--from_weight---to_weight+buffer_weight");
//         //TollWimClassDetails.info("Class+------from_weight  ------to_weight+buffer_weight");
         ClassArrayForTollWimClassDetail=GetWimSubClass.toll_wim_class_detailsClasses();
         
         while (resultSet.next()) {
        	 
        	    String From_Weight = resultSet.getString("from_weight"); // Replace with the actual column name
	            String Final_Weight= resultSet.getString("final_weight");
	            FromWeightArrayList.add(Integer.valueOf(From_Weight));
	        	 ToWeightArrayList.add(Integer.valueOf(Final_Weight));
	        	String Class=ClassArrayForTollWimClassDetail.get(i);
	        	 logger.info(Class+"--having weight from-- "+ FromWeightArrayList.get(i)+" to "+ToWeightArrayList.get(i));
	        	 
	        	 //TollWimClassDetails.info(Class+"--having weight from-- "+ FromWeightArrayList.get(i)+" to "+ToWeightArrayList.get(i));
	        i++;
         }
        
         
        
         
         Mapping example = new Mapping();


         example.setItemList(AllowWeight);

       
         Map<String, String>retrievedList = example.getItemList();

      
         return retrievedList;
	  }
     public static ArrayList toll_wim_class_detailsClasses() throws SQLException
	  {
    	 ArrayList <String>AllowWeightClassesList=new ArrayList<String>();
    	 Connection connection = DriverManager.getConnection(DataBaseurl, DatBaseusername, DatBasepassword);
    	 String sql = "select t.wim_class_name,t.from_weight,t.to_weight+t.buffer_weight as final_weight from mvw_toll_wim_class_details t order by t.from_weight";
	        
	       
	        
	        Statement statement = connection.createStatement();
	        ResultSet resultSet = statement.executeQuery(sql);

	        while (resultSet.next()) {
	        
	            String WIM_CLASS = resultSet.getString("WIM_CLASS_NAME"); // Replace with the actual column name

	            AllowWeightClassesList.add(WIM_CLASS);
//	            System.out.println(WIM_CLASS);
	            
	        }
	
        
    	 GetWimSubClass example = new GetWimSubClass();


         example.setItemList(AllowWeightClassesList);

       
         ArrayList<String> retrievedList = example.getItemList();

      
         return retrievedList;
	  }
}
