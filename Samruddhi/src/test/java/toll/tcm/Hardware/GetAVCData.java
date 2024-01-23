package toll.tcm.Hardware;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import toll.tcm.testCases.*;
//CAR/JEEP LCV/Mini Bus BUS TRUCK MAV 3 Axle
public class GetAVCData  extends BaseClass{


	public static  String getAVCData(String returnVehicleClass)
	{
		
		String timeStamp=new SimpleDateFormat("yy.MM.dd.HH.mm.ss").format(new Date());
		String [] VClass=new String[14];
		
		VClass[0]="Vehicle Class";
		VClass[1]="CAR";
		VClass[2]="LCV";
		VClass[3]="TRUCK";
		VClass[4]="BUS";
		VClass[5]="MAV 3";
		VClass[6]="MAV 4";
		VClass[7]="MAV 5";
		VClass[8]="MAV 6";
		VClass[9]="OSV";
		VClass[10]="LCV";
		VClass[11]="TRACTOR";
		VClass[12]="AUTO";
		VClass[13]="BIKE";
		
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
		 
		DecimalFormat twodigits = new DecimalFormat("00");
	    String code="";
		for(int i=1;i<VClass.length;i++)
		{
//			System.out.println("returnVehicleClass :" + returnVehicleClass );
//			System.out.println("VClass: "+VClass[i]);
//			System.out.println(VehicleClassList.get(i));
//			System.out.println(twodigits.format(i));
			
		    String b=twodigits.format(i); 
//		    this.returnVehicleClass=returnVehicleClass;
			if(returnVehicleClass.contains(VClass[i]))
			{
//				System.out.println("Match Found");
				
				code=b;
				break;
			}
			
		}
		logger.info("Profiler signal is:[IMG01_"+timeStamp.replace(".", "")+"_"+code+"]");
		
		return "[IMG01_"+timeStamp.replace(".", "")+"_"+code+"]";
	}
}
