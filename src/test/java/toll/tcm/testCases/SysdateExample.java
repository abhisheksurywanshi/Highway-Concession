package toll.tcm.testCases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.openqa.selenium.By;

public class SysdateExample {
    public static void main(String[] args) {
        // Define the JDBC connection parameters
    	String latest="230914020300005";
    	String currentShiftAlias="3";
    	String shift=""+latest.charAt(7);
    	System.out.println(shift);
  	     if(shift.contains(currentShiftAlias))
  	     {
  	    	System.out.println(latest);
  	     }
  	     else
  	     {
  	    	StringBuilder stringBuilder = new StringBuilder(latest);
  	    	if(7!=Integer.valueOf(currentShiftAlias))
  	    	{
  	    		
  	    		int newShfit=Integer.valueOf(shift)+1;
  	    		char c=(char)newShfit;
  	    		 stringBuilder.setCharAt(7, c);

  	            // Convert the StringBuilder back to a string
  	            String result = stringBuilder.toString();
  	            System.out.println(result);
  	    	}
  	    	System.out.println(latest.substring(7, 8));
  	    	System.out.println("end");
  	     }
  	     
    }
}
