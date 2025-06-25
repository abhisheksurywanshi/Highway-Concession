package toll.tcm.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import toll.tcm.Database.Mapping;
import toll.tcm.testCases.BaseClass;
import toll.tcm.testCases.StaticVariables;

public class CDToProfilerClass extends StaticVariables {
	static Connection connection;
	public static void main(String[] args) throws SQLException {
		Map<String, String> CDToProfiler = new HashMap<String, String>();
		Map<String, String> ActualCDToProfiler = new HashMap<String, String>();
		
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

//	       for(int i=0;i<standardCD().length;i++)
//	       System.out.println(standardCD()[i]);
	
        
    	 Mapping example = new Mapping();


         example.setItemList(MapperKeys);

       
         Map<String, String>retrievedList = example.getItemList();
         System.out.println(retrievedList);
         Map<String, String> sortedMap = new TreeMap<>(retrievedList);
         System.out.println(sortedMap);
         String []keysArrayForCDAndClass=sortedMap.keySet().toArray(new String[0]);
         int AllCDLenght=keysArrayForCDAndClass.length;
         //System.out.println("AllCDLenght:"+AllCDLenght);
         //System.out.println("standardCD: "+standardCD().length);
         for ( int i = 0; i<AllCDLenght;i++ ) { 
         String CD = keysArrayForCDAndClass[i];
         String Vehicle = sortedMap.get(CD);
         System.out.println(Vehicle);
         String n="";
         int count=0;
        boolean found =false;
         for(int j=0;j<standardCD().length;j++)
         {
        	 
        	 if(standardCD()[j].equals(CD))
        	 {
        		 count=1;
        		 
        		 
//        		 System.out.println(standardCD()[j]+" found break");
        		 found=true;
//        		 System.out.println(" CD: "+CD+" n:"+standardCD()[j]+ " i:"+i+" j:"+j+" continue");
        		 break;
        		 
        	 }
        	 else if(!CD.equals(standardCD()[j]))
        	 {
//        		 System.out.println(standardCD()[j]+" not found");
//        		 System.out.println(" CD: "+CD+" n:"+standardCD()[j]+ " i:"+i+" j:"+j);
        		count=0;
        		found=false;
        		 
        	 }
        	 
         }
         if(found==false)
         {
//        	 System.out.println(" not found for :"+CD+" i:"+i);
        	 
         }
         else
         {
        	 
//        	 System.out.println( "found "+count+"  "+CD+"  "+Vehicle);
        	 ActualCDToProfiler.put(CDToProfilerConverter(CD), Vehicle);
        	 count=0;
         }
//         //System.out.println("CD: " + CD + ", Class: " + Vehicle);
         
        
         }
//         //System.out.println(standardCD().length);
//         //System.out.println();
//         //System.out.println(keysArrayForCDAndClass.length);
//         for(int i=0;i<standardCD().length;i++)
//        	 //System.out.println(standardCD()[i]);
         	//System.out.println(CDToProfilerConverter("04"));
//         //System.out.println();
//         for(int i=0;i<keysArrayForCDAndClass.length;i++)
//         //System.out.println(keysArrayForCDAndClass[i]);
        System.out.println("--");
         System.out.println(ActualCDToProfiler);
        
         boolean CDfound=false;
         String []keysArrayForEmptyprofilerclass=ActualCDToProfiler.keySet().toArray(new String[0]);
         Arrays.sort(keysArrayForEmptyprofilerclass);
//         for(int i=0;i<keysArrayForEmptyprofilerclass.length;i++)
//         {
//        	 System.out.println(keysArrayForEmptyprofilerclass[i]);
//         }
        	 
         List<String> CDNotFoundArray=new ArrayList<String>();
         List<String>RandomCD=new  ArrayList<String>();
         String temp="";
         for(int i=1;i<standardProfilerCD().length;i++)
         {
        	 CDfound=false;
        	 for(int j=0;j<AllCDLenght;j++)
        	 {
        		 temp=keysArrayForEmptyprofilerclass[j];
        		 if(standardProfilerCD()[i].equals(keysArrayForEmptyprofilerclass[j]))
        		 {
        			 CDfound=true;
//        			 System.out.println("This CD is found:"+standardProfilerCD()[i]+" "+temp);
        			 break;
        			 
        		 }
        	 }
        	 if(!CDfound)
        	 {
        		 String FinalCD=standardProfilerCD()[i];
        		 System.out.println("This CD is not found:"+FinalCD+" "+temp);
        		 CDNotFoundArray.add(FinalCD);
        	 }
        	 
         }
         String code="";
         DecimalFormat twodigits = new DecimalFormat("00");

         for (int i = 0; i < keysArrayForEmptyprofilerclass.length; i++) {
	           String key = keysArrayForEmptyprofilerclass[i];
	           String value = ActualCDToProfiler.get(key);
//	           sortedMap.put(key, value);
	           boolean limitfound=false;
	           System.out.println("CD: " + key + ", Class: " + value);
	           if(value.equals("TRUCK/BUS"))
	           {
	        	   code=key;
	        	  
	        	   int IntegerValueCode=Integer.valueOf(code);
	        	   int l=1;
	        	   for(int j=i;j< keysArrayForEmptyprofilerclass.length;j++)
	        	   {
	        		   String StringValueCode=twodigits.format(IntegerValueCode+l);
	        		   System.out.println("String value code"+StringValueCode);
	        		   
	        		   for(int k=0;k<CDNotFoundArray.size();k++)
	        		   {
	        			   
	        			   int NotFoundCDIntValue=Integer.valueOf(CDNotFoundArray.get(k));
	        			   String NotFoundCDStrValue=twodigits.format(NotFoundCDIntValue);
	        			   System.out.println("+1code:"+StringValueCode+" compairnotfoundCD:"+CDNotFoundArray.get(k));
	        			   if(CDNotFoundArray.get(k).equals(StringValueCode))
	        			   {
	        				   System.out.println("--"+StringValueCode);
	        				   RandomCD.add(StringValueCode);
	        				   l++;
	        				   break;
	        			   }
	        			   
	        			   else
	        			   {
	        				   
	        			   }
	        		   }
	        			   if(l==1)
	        			   {
	        				   break;
	        			   }
	        			   
	        			   
	        	   }
	        	    
	        	   
	        	   
	           }
	           
	       }
         RandomCD.add(code);
        for(int i=0;i<RandomCD.size();i++) 
         System.out.println(RandomCD.get(i));
        System.out.println("---");
        int RandomFinal= BaseClass.randomGenerator(0, RandomCD.size()-1);
        System.out.println(RandomFinal);
        code=RandomCD.get(RandomFinal);
        System.out.println(code);
       
	}
	public static String[] standardCD()
	{
		String [] CD=new String[12];
		CD[0] ="11";
		CD[1] ="12";
		CD[2] ="01";
		CD[3] ="13";
		CD[4] ="02";
		CD[5] ="14";
		CD[6] ="04";
		CD[7] ="05";
		CD[8] ="17";
		CD[9] ="07";
		CD[10] ="18";
		CD[11] ="10";
		
		return CD;
	}
	public static String[] standardProfilerCD()
	{
		String [] CD=new String[14];
//		CD[0] ="01";
		CD[1] ="01";
		CD[2] ="02";
		CD[3] ="03";
		CD[4] ="04";
		CD[5] ="05";
		CD[6] ="06";
		CD[7] ="07";
		CD[8] ="08";
		CD[9] ="09";
		CD[10] ="10";
		CD[11] ="11";
		CD[12] ="12";
		CD[13] ="13";
		return CD;
	}
	public static String CDToProfilerConverter(String CD)
	{
		if(CD.contains("04"))
		{
			return "01";
		}
		else if(CD.contains("05"))
		{
			return "02";
		}
		else if(CD.contains("07"))
		{
			return "04";
		}
		else if(CD.contains("10"))
		{
			return "03";
		}
		else if(CD.contains("11"))
		{
			return "05";
		}
		else if(CD.contains("12"))
		{
			return "06";
		}
		else if(CD.contains("13"))
		{
			return "07";
		}
		else if(CD.contains("14"))
		{
			return "08";
		}
		else if(CD.contains("17"))
		{
			return "09";
		}
		else if(CD.contains("05"))
		{
			return "10";
		}
		else if(CD.contains("18"))
		{
			return "11";
		}
		else if(CD.contains("02"))
		{
			return "12";
		}
		else if(CD.contains("01"))
		{
			return "13";
		}
		
		else if(CD.contains("99"))
		{
			return "99";
		}
		return null;
	}
	public static String getProfilerCode(String CD)
	{
		String randomValue="";
		 for(int i=0;i<AVC_Profiler_CDs.size();i++)
         {
         	String keyfirst = keysArrayForProfilerVehicleCode[i];
         	
         	List<String> valuesForKey = List.copyOf(AVC_Profiler_CDs.get(CD));
         	 int length = valuesForKey.size();
         	 if (length == 1) {
                  System.out.println("Value associated with key '" + CD + "': " + valuesForKey.get(0));
                  randomValue=valuesForKey.get(0);
                  return randomValue;
                 
              }
         	 else
         	{
         		if(CD.equals(keyfirst))
         		{
         			 
         			 Random random = new Random();
         		        int randomIndex = random.nextInt(valuesForKey.size());
         		        
         		        // Get the random element from the list
         		       randomValue = valuesForKey.get(randomIndex);
         		        
         		        // Print the random value associated with the specific key
         		        System.out.println("Random value associated with key '" + keyfirst + "': " + randomValue);
         		        
         		}
         		
         		
         			
         		
         	}
         	
         }
		 return randomValue;
	}
}
