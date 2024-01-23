package toll.tcm.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import toll.tcm.testCases.BaseClass;

public class Is_OverWeight_Applicable extends BaseClass
		{
	public static Multimap<String, String> isOverWeightApplicable() throws SQLException
    {
		Multimap<String, String> MapperKeys = ArrayListMultimap.create();


    	 String sql1 = "SELECT M.VEHICLE_CLASS_ALIAS FROM MVW_TOLL_VEHICLE_MASTER M WHERE M.PAID_UNPAID='P' AND M.IS_OVERWEIGHT_APPLICABLE='Y'  group by m.vehicle_class_alias ORDER BY M.VEHICLE_CLASS_ALIAS";
    	 String sql2="SELECT M.MOP_ALIAS FROM MVW_TOLL_VEHICLE_MASTER M WHERE M.PAID_UNPAID='P' AND M.IS_OVERWEIGHT_APPLICABLE='Y' group by m.Mop_Alias"  ;
	       
	        
    	 Statement statement = connection.createStatement();
	        
	        ResultSet resultSet = statement.executeQuery(sql1);
	        
	    	 while (resultSet.next()) {
    		        
 	            String KEY = resultSet.getString("vehicle_class_alias"); // Replace with the actual column name
 	            Statement preparedStatement2 = connection.createStatement();
	            	ResultSet resultSet2 = preparedStatement2.executeQuery(sql2);
 	            while(resultSet2.next())
 	            {
 	            	
 	            	String KEYS_ALIAS= resultSet2.getString("mop_alias");
 	            	MapperKeys.put(KEY,KEYS_ALIAS);
 	            }
 	           resultSet2.close();
  	    	 preparedStatement2.close();
 	            
 	            
 	            
 	        }
    	    	 resultSet.close();
    	    	 statement.close();
    	    	 


	       
	
        
    	 KeyMapping example = new KeyMapping();


         example.setItemMultiMap( MapperKeys);

       
         Multimap<String, String>retrievedList = example.getItemMultiMap();

      
         return retrievedList;
    }
	
	public static Boolean isOverWeightApplicableCheck(String VClass,String MOP) throws SQLException
	{
		Boolean overWeightApplicable=false;
//         System.out.println("length:"+keysArrayForIsOverWeightApplicable.length);
		int OriginalLength=keysArrayForIsOverWeightApplicable.length;
		for (int i = 0; i < OriginalLength; i++)
		{		
	           String Class=keysArrayForIsOverWeightApplicable[i] ;
	           
	           
	           List<String> MOPs=(List<String>) Is_OverWeight_Applicable.isOverWeightApplicable().get(Class);	           	
	           int TempLength=IsOverWeightApplicable.get(Class).size();
	           OriginalLength=TempLength;
	           Is_OverWeight_Applicable.isOverWeightApplicable().get(Class).size();
//	           Is_OverWeight_Applicable.isOverWeightApplicable().asMap().
	           if(Class.contains(VClass))
	           {
	        	   String mop=MOPs.get(i);
	        	   if(MOP.contains(mop))
	        	   {
	        		   overWeightApplicable=true;
	        		   System.out.println(VClass+"TRUE  "+"  "+MOP+overWeightApplicable);
	        		   System.out.println(Class+"TRUE  "+"  "+mop+overWeightApplicable);
	        		   return overWeightApplicable;
	        	   }
	        	   else
	        	   {
	        		   System.out.println(VClass+"  "+"  "+MOP+overWeightApplicable);
	        		   System.out.println(Class+"  "+"  "+mop+overWeightApplicable);
	        		   continue;
	        	   }
	        	   
	        	   
	           }
	           
	       }
		
		System.out.println("Final"+overWeightApplicable);
		return overWeightApplicable;
//		return null;
	}
		}

