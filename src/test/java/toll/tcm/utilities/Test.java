package toll.tcm.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

import toll.tcm.Database.Mapping;
import toll.tcm.testCases.BaseClass;
import toll.tcm.testCases.StaticVariables;

public class Test extends StaticVariables{

	public static void main(String[] args) throws SQLException {
		Connection connection= DriverManager.getConnection("jdbc:oracle:thin:@10.10.1.87:1521:XE","SNC_HC","CaPitAL_36_iTS") ; 
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
 	            System.out.println(Avc_Vehicle_Cd+" "+TLC_Vehicle_Class);
 	            
 	            
 	        }
    	    	 resultSet.close();
    	            statement.close();
    	            Mapping example = new Mapping();


    	            example.setItemList(MapperKeys);

    	          
    	            Map<String, String>retrievedList = example.getItemList();
    	            System.out.println(retrievedList);
    	            keysArrayForProfilerVehicleCode= retrievedList.keySet().toArray(new String[0]);
    	            
    	            for (int i = 0; i < retrievedList.size(); i++) {
    	                String keyfirst = keysArrayForProfilerVehicleCode[i];
    	                String value1 = retrievedList.get(keyfirst);
//    	                System.out.println(value1);
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
    	                		System.out.print("value1: "+value1+" value2: "+value2+" keyfirst: "+keyfirst+" keysecond: "+keysecond);
    	                	}
    	                	
    	                }
    	                if(count>1)
	                	{
	                		System.out.println(" round "+i+" count is more key is :"+temp+" value 1: "+value1);
	                	}
	                	else
	                	{
	                		System.out.println(" round "+i+" count is 1 key is :"+keyfirst+" value:1 "+value1);
	                		AVC_Profiler_CDs.put( value1,keyfirst);
	                	}
    	               
    	                
    	             
//    	                System.out.println("ASCII Key: " + key + ", Value: " + value);
    	                
    	            }
    	            System.out.println(AVC_Profiler_CDs);
    	            
    	           
    	            
    	            // Iterate over the entries of the Multimap
    	            for (Entry<String, Collection<String>> entry : AVC_Profiler_CDs.asMap().entrySet()) {
    	                String key = entry.getKey();
    	                Collection<String> values = entry.getValue();
    	                
    	                // Create a Set to store unique child records
    	                Set<String> uniqueValues = new HashSet<>(values);
    	                
    	                // Update the Multimap entry with unique child records
    	                AVC_Profiler_CDs.replaceValues(key, uniqueValues);
    	            }
    	            System.out.println(AVC_Profiler_CDs);
    	        
    	            for(int i=0;i<AVC_Profiler_CDs.size();i++)
    	            {
    	            	String keyfirst = keysArrayForProfilerVehicleCode[i];
    	            	String CD="04";
    	            	List<String> valuesForKey = List.copyOf(AVC_Profiler_CDs.get(CD));
    	            	 int length = valuesForKey.size();
    	            	 if (length == 1) {
    	                     System.out.println("Value associated with key '" + CD + "': " + valuesForKey.get(0));
    	                     break;
    	                 }
    	            	 else
    	            	{
    	            		if("12".equals(keyfirst))
    	            		{
    	            			 
    	            			 Random random = new Random();
    	            		        int randomIndex = random.nextInt(valuesForKey.size());
    	            		        
    	            		        // Get the random element from the list
    	            		        String randomValue = valuesForKey.get(randomIndex);
    	            		        
    	            		        // Print the random value associated with the specific key
    	            		        System.out.println("Random value associated with key '" + keyfirst + "': " + randomValue);

    	            		}
    	            		
    	            			
    	            			
    	            		
    	            	}
    	            	
    	            }
//    	            System.out.println("--");
//    	            System.out.println(list);
//    	            System.out.println(list.get(Randoms)+" last");
	}

}
