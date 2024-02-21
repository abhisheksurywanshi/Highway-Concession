package toll.tcm.APIs;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelGenerator {
	public static void main(String[] args) {
       for (int i=0,i1=1;i<=23;i++)
       {
    	   String formattedNumberHHi = String.format("%02d", i);
			 
           String formattedNumberHHi1 = String.format("%02d", i1);
           
    	   for(int j=0,j1=1;j<=59;j++)
    	   {
    		   String formattedNumberMMj = String.format("%02d", j);
  			 
               String formattedNumberMMj1 = String.format("%02d", j1);
    		   for (int k=0,k1=1;k<=59;k++)
    		   {
    			   String formattedNumberSSk = String.format("%02d", k);
    				 
    	           String formattedNumberSSk1 = String.format("%02d", k1);
    	           
    	           System.out.println("from HH:"+formattedNumberHHi+"MM:"+formattedNumberMMj+"SS:"+formattedNumberSSk);
    	           
    		   }
    	   }
    	   if(i>=5)
           {
        	   break;
           }
       }
    	   
    }
	public static int SecWiseTraffic(String from_HH,String from_MM,String from_SS ,String to_HH,String to_MM,String toSS)
	{
		
		
		return 00;
	}
}
