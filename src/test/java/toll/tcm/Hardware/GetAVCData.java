package toll.tcm.Hardware;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import toll.tcm.testCases.*;
//CAR/JEEP LCV/Mini Bus BUS TRUCK MAV 3 Axle
public class GetAVCData  extends BaseClass{

	static Logger logger=LogManager.getLogger(GetAVCData.class);
	public static  String getAVCData(String returnVehicleClass)
	{
		
		String timeStamp=new SimpleDateFormat("yy.MM.dd.HH.mm.ss").format(new Date());
//		String [] VClass=new String[14];
//		
//		VClass[0]="Vehicle Class";
//		VClass[1]="CAR";
//		VClass[2]="LCV";
//		VClass[3]="TRUCK";
//		VClass[4]="BUS";
//		VClass[5]="MAV 3";
//		VClass[6]="MAV 4";
//		VClass[7]="MAV 5";
//		VClass[8]="MAV 6";
//		VClass[9]="OSV";
//		VClass[10]="LCV";
//		VClass[11]="TRACTOR";
//		VClass[12]="AUTO";
//		VClass[13]="BIKE";
		
//		VClass[1]="Car / Jeep / Van";
//		VClass[2]="Light Commercial Vehicle 2 - Axle";
//		VClass[3]="Truck 2 - Axle";
//		VClass[4]="Bus 2 - Axle";
//		VClass[5]="Truck 3 - Axle";
//		VClass[6]="Truck 4 - Axle";
//		VClass[7]="Truck 5 - Axle";
//		VClass[8]="Truck 6 - Axle";
//		VClass[9]="Heavy Construction Machinery";
//		VClass[10]="Light Commercial Vehicle 2 - Axle";
//		VClass[11]="Tractor";
//		VClass[12]="Three - Wheeler Passenger";
//		VClass[13]="Two Wheeler";
//		VClass[14]="";
//		VClass[15]="AVCX";
		 
//		DecimalFormat twodigits = new DecimalFormat("00");
	    String code="";
//		for(int i=1;i<VClass.length;i++)
//		{
//
//			
//		    String b=twodigits.format(i); 
//
//			if(returnVehicleClass.contains(VClass[i]))
//			{
//
//				
//				code=b;
//				break;
//			}
//			
//		}
		for (int i = 0; i < keysArrayForCDAndClass.length; i++) {
	           String key = keysArrayForCDAndClass[i];
	           String value = CDANDCLASS.get(key);
	           CDANDCLASS.put(key, value);
	           logger.info("CD: " + key + ", Class: " + value);
	           if(value.equals(returnVehicleClass))
	           {
	        	   code=key;
	           }
	           
	       }
		logger.info("Profiler signal is:[IMG01_"+timeStamp.replace(".", "")+"_"+code+"]");
		
		return "[IMG01_"+timeStamp.replace(".", "")+"_"+code+"]";
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
			return "03";
		}
		else if(CD.contains("10"))
		{
			return "04";
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
		else if(CD.contains("17"))
		{
			return "13";
		}
		else if(CD.contains("99"))
		{
			return "99";
		}
		return null;
	}
}
