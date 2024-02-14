package toll.tcm.MOPs;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import toll.tcm.Hardware.*;
import toll.tcm.testCases.*;
import toll.tcm.utilities.*;
public class Violation extends BaseClass 
{
    
	public static  void randomViolation() throws IOException, InterruptedException, SQLException
	{
//		int port=Integer.valueOf(5001);
//		Socket socket = new Socket(tagReaderIPaddress, port);

//		  OutputStream outputStreamForAVC = socket.getOutputStream();
		LastTransaction= DefaultTransactionNumber.getDefaultTransationNumber();
		 InputStream inputStreamForAVC = socket.getInputStream();
		 
		 
		boolean Non_Default=false;
		 // Replace with the actual server IP or hostname
//		ExplicitWait(By.name("Vehicle pending in queue - 0"));
		System.out.println("default transaction:"+ LastTransaction);
		try 
		{
			LastTransaction=driver.findElementByAccessibilityId("ListViewSubItem-0").getText().toString();
			 Non_Default =true;
			System.out.println("NON-default transaction:"+ LastTransaction);
		}
		catch(Exception e)
		{
			LastTransaction=DefaultTransactionNumber.getDefaultTransationNumber();
		}
		GetAVCData getdata=new GetAVCData();
		String LastTransaction=driver.findElementByAccessibilityId("ListViewSubItem-0").getText().toString();
		PC_Send P =new PC_Send();
//		if(Toll_Name.contains("Pal"))
//		{
			ExplicitWait(By.name("Vehicle pending in queue - 1"));
//		}
        logger.info("PC Send Time");
//        ExplicitWait(By.name("Vehicle pending in queue - 1"));
		String timeStamp=new SimpleDateFormat("yy.MM.dd.HH.mm.ss").format(new Date());
		String [] VClass=new String[14];
		
		VClass[0]="Vehicle Class";
		VClass[1]="CAR/JEEP";
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
//		VClass[14]="";
//		VClass[15]="AVCX";
		int randomClass=randomGenerator(1, VClass.length-1);
		
		DecimalFormat twodigits = new DecimalFormat("00");
	    String t="";
		for(int i=1;i<13;i++)
		{
			System.out.println("returnVehicleClass :" + VClass[randomClass] );
			System.out.println("VClass: "+VClass[i]);
			
//			System.out.println(VehicleClassList.get(i));
//			System.out.println(twodigits.format(i));
		    String b=twodigits.format(i);
//		    this.returnVehicleClass=returnVehicleClass;
			if(VClass[randomClass].contains(VClass[i]))
			{
				System.out.println("Match Found");
				t=b;
				break;
			}
			
		}
		System.out.println("[IMG01_"+timeStamp.replace(".", "")+"_"+t+"]"+" inside method");
		String toReturnAVCClass="[IMG01_"+timeStamp.replace(".", "")+"_"+t+"]";
//		Thread.sleep(500);
		
		hexData = "02";         
        dataBytes = hexStringToByteArray(hexData);
        outputStreamForExitAVC.write(dataBytes);
        
        
   	   
//        COM_Setup.COMAVCSetup99();
        outputStreamForAVC.write(toReturnAVCClass.getBytes());
    	logger.info(getdata.getAVCData(toReturnAVCClass)+" :outputstream ");
//		outputStreamForAVC.write(toReturnAVCClass.getBytes());
		hexData = "03";         
        dataBytes = hexStringToByteArray(hexData);
        logger.info("AVC start time"); 
        outputStreamForExitAVC.write(dataBytes);
        logger.info("AVC end time");
//        if(Non_Default)
//        {
//        	Long Latesttransaction=(Long.valueOf(LastTransaction))+1;
//       	    String latest=String.valueOf(Latesttransaction);
//       	     System.out.println(latest);
//        	 ExplicitWait(By.name(latest));
//        }
//        else
//        {
//        	Long Latesttransaction=(Long.valueOf(LastTransaction))+1;
//        	 String latest=String.valueOf(Latesttransaction);
//       	     System.out.println(latest);
//       	     try
//       	     {
//       	    	ExplicitWait(By.name(latest));
//       	     }
//       	     catch(Exception e)
//       	     {
//       	    	driver.getKeyboard().sendKeys(Keys.ESCAPE);
////       	    	outputStreamForAVC.write(toReturnAVCClass.getBytes());
//       	    	driver.findElementByAccessibilityId("txtVehicleNo").click();
//       	    	driver.getKeyboard().sendKeys(Keys.chord(Keys.CONTROL,Keys.END));
//				driver.getKeyboard().sendKeys(Keys.chord(Keys.CONTROL,Keys.END));
//				ExplicitWait(By.name("Vehicle pending in queue - 0"));
//       	     }
//        	
//        }
//        ExplicitWait(By.name("Vehicle pending in queue - 1"));
		ImageVerification i =new ImageVerification();
		String parent=driver.getWindowHandle();
		mainWindowToCurrentWindow(driver, parent);
		try 
		{
			String LatestTransaction=driver.findElementByAccessibilityId("ListViewSubItem-0").getText().toString();
			System.out.println("first transactio endswith: "+LastTransaction.substring(LastTransaction.length()-1));
			System.out.println("last transaction ends with: "+LatestTransaction.substring(LatestTransaction.length()-1));
			System.out.println(LatestTransaction.endsWith(LastTransaction.substring(LastTransaction.length()-1)));
			if(LatestTransaction.endsWith(LastTransaction.substring(LastTransaction.length()-1)))
			{	
				System.out.println("equal");
//				LastTransaction=driver.findElementByAccessibilityId("ListViewSubItem-0").getText().toString();
//				ExplicitWait(By.name(LastTransaction));
				
			}
			
				
				
				FirstFreeConvoy=LastTransaction;
		}
		catch(org.openqa.selenium.NoSuchElementException v)
		{
//			Thread.sleep(5000);
			String LatestTransaction=driver.findElementByAccessibilityId("ListViewSubItem-0").getText().toString();
			System.out.println("first transactio endswith: "+LastTransaction.substring(LastTransaction.length()-1));
			System.out.println("last transaction ends with: "+LatestTransaction.substring(LatestTransaction.length()-1));
			System.out.println(LatestTransaction.endsWith(LastTransaction.substring(LastTransaction.length()-1)));
			if(LatestTransaction.endsWith(LastTransaction.substring(LastTransaction.length()-1)))
			{	
				System.out.println("equal");
//				LastTransaction=driver.findElementByAccessibilityId("ListViewSubItem-0").getText().toString();
//				ExplicitWait(By.name(LastTransaction));
				
			}
			
				
				
				FirstFreeConvoy=LastTransaction;
		}
		
		SetProfilerImage s= new SetProfilerImage(VClass[randomClass]);
		
	}
	public static void excelViolation(String Class) throws Exception
	{
		LastTransaction= DefaultTransactionNumber.getDefaultTransationNumber();
		boolean Non_Default=false;
		 // Replace with the actual server IP or hostname
		System.out.println("default transaction:"+ LastTransaction);
		try 
		{
			LastTransaction=driver.findElementByAccessibilityId("ListViewSubItem-0").getText().toString();
			 Non_Default =true;
			System.out.println("NON-default transaction:"+ LastTransaction);
		}
		catch(Exception e)
		{
			LastTransaction=DefaultTransactionNumber.getDefaultTransationNumber();
		}
		GetAVCData getdata=new GetAVCData();
		String LastTransaction=driver.findElementByAccessibilityId("ListViewSubItem-0").getText().toString();
		hexData = "02";         
        dataBytes = hexStringToByteArray(hexData);
        outputStreamForExitAVC.write(dataBytes);
//        Thread.sleep(200);
		outputStreamForExitAVC.write("P".getBytes());
//		Thread.sleep(200);
		hexData = "03";         
        dataBytes = hexStringToByteArray(hexData);
        outputStreamForExitAVC.write(dataBytes);
//        Thread.sleep(200);
        hexData = "02";         
        dataBytes = hexStringToByteArray(hexData);
        outputStreamForExitAVC.write(dataBytes);
//        Thread.sleep(200);
		outputStreamForExitAVC.write("C".getBytes());
//		Thread.sleep(200);
		hexData = "03";         
        dataBytes = hexStringToByteArray(hexData);
        outputStreamForExitAVC.write(dataBytes);
//        ExplicitWait(By.name("Vehicle pending in queue - 1"));
		String timeStamp=new SimpleDateFormat("yy.MM.dd.HH.mm.ss").format(new Date());
		String [] VClass=new String[14];
		
//		VClass[0]="Vehicle Class";
		VClass[1]="CAR/JEEP";
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
//		VClass[14]="NOT MAPP";
//		VClass[15]="AVC";
		
		
		DecimalFormat twodigits = new DecimalFormat("00");
	    String t="";
		for(int i=1;i<VClass.length;i++)
		{
			System.out.println("returnVehicleClass :" + VClass[seriralprofilertoVClass(Class)] );
			System.out.println("VClass: "+VClass[i]);
//			System.out.println(VehicleClassList.get(i));
			int h=seriralprofilertoVClass(Class);
			System.out.println("h:"+h);
//			System.out.println(twodigits.format(i));
		    String b=twodigits.format(i);
//		    this.returnVehicleClass=returnVehicleClass;
			if(VClass[seriralprofilertoVClass(Class)].contains(VClass[i]))
			{
				System.out.println("Match Found");
				t=b;
				if(h==99)
				{
					t="99";
				}
				break;
			}
			else
			{
				t="99";
			}
			
		}
		System.out.println("[IMG01_"+timeStamp.replace(".", "")+"_"+t+"]"+" inside method");
		String toReturnAVCClass="[IMG01_"+timeStamp.replace(".", "")+"_"+t+"]";
//		Thread.sleep(500);
		
		hexData = "02";         
        dataBytes = hexStringToByteArray(hexData);
        outputStreamForExitAVC.write(dataBytes);
        
        
        
		outputStreamForAVC.write(toReturnAVCClass.getBytes());
		hexData = "03";         
        dataBytes = hexStringToByteArray(hexData);
        outputStreamForExitAVC.write(dataBytes);
       if(Non_Default)
        {
        	Long Latesttransaction=(Long.valueOf(LastTransaction))+1;
       	    String latest=String.valueOf(Latesttransaction);
       	     System.out.println(latest);
        	 ExplicitWait(By.name(latest));
        }
        else
        {
        	ExplicitWait(By.name(LastTransaction));
        }
//        ExplicitWait(By.name("Vehicle pending in queue - 1"));
		ImageVerification i =new ImageVerification();
		
	}
	public static int seriralprofilertoVClass(String Class)
	{
		if (Class.contains("04")) {
			return 1;
		} else if (Class.contains("05")||Class.contains("09")) {
			return 2;
		}
		else if (Class.contains("06")||Class.contains("08")){
			return 5;
		}else if (Class.contains("07")) {
			return 4;
			
		} 
		else if (Class.contains("10")) {
			return 3;
		} else if (Class.contains("11")) {
			return 5;
		} else if (Class.contains("12")) {
			return 6;
		} else if (Class.contains("13")) {
			return 7;
		} else if (Class.contains("14")) {
			return 8;
		} else if (Class.contains("15")){
			return 9;
		}		
		
		else
		{
			return 99;
		}
	}
}

