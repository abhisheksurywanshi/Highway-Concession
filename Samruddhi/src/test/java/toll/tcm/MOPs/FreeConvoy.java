package toll.tcm.MOPs;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import toll.tcm.Hardware.*;
import toll.tcm.testCases.*;
import toll.tcm.utilities.*;
public class FreeConvoy extends BaseClass
{
	
	public static void freeConvoy(int trNo) throws Exception
	{
//		int port=Integer.valueOf(5001);
//		Socket socket = new Socket(tagReaderIPaddress, port);

//		  OutputStream outputStreamForAVC = socket.getOutputStream();
		
		VClassForFrCnvy=new String[14];
		if(trNo==1)
		{
			GetAVCData getdata=new GetAVCData();
			driver.getKeyboard().sendKeys(Keys.chord(Keys.SHIFT,Keys.DELETE));
			driver.findElementByName("Yes").click();
			try 
			{
				ExplicitWait(By.name("convoy mode started."));
			}
			catch(org.openqa.selenium.TimeoutException e)
			
			{
				ExplicitWait(By.name("convoy mode started."));
			}
			
			System.out.println("this block is perform tr==1");
			
				PC_Send p= new PC_Send();
		        
//		        Thread.sleep(200);
				timeStamp=new SimpleDateFormat("yy.MM.dd.HH.mm.ss").format(new Date());
				
				
//				VClassForFrCnvy[0]="Vehicle Class";
				VClassForFrCnvy[1]="CAR/JEEP";
				VClassForFrCnvy[2]="LCV";
				VClassForFrCnvy[3]="TRUCK";
				VClassForFrCnvy[4]="BUS";
				VClassForFrCnvy[5]="MAV 3";
				VClassForFrCnvy[6]="MAV 4";
				VClassForFrCnvy[7]="MAV 5";
				VClassForFrCnvy[8]="MAV 6";
				VClassForFrCnvy[9]="OSV";
				VClassForFrCnvy[10]="LCV";
				VClassForFrCnvy[11]="TRACTOR";
				VClassForFrCnvy[12]="Auto";
				VClassForFrCnvy[13]="BIKE";
				
				randomFreeConvoy=randomGenerator(1, VClassForFrCnvy.length-1);
				System.out.println(VClassForFrCnvy[randomFreeConvoy]+" rrrrrrrr");
				twodigits = new DecimalFormat("00");
			    AVCCLASS="";
				for(int j=1;j<VClassForFrCnvy.length;j++)
				{
//					System.out.println("returnVehicleClass :" + VClassForFrCnvy[randomFreeConvoy] );
//					System.out.println("VClassForFrCnvy: "+VClassForFrCnvy[i]);
//					System.out.println(VehicleClassList.get(i));
//					System.out.println(twodigits.format(i));
				    AVCRandom=twodigits.format(j);
//				    this.returnVehicleClass=returnVehicleClass;
					if(VClassForFrCnvy[randomFreeConvoy].contains(VClassForFrCnvy[j]))
					{
//						System.out.println("Match Found");
						AVCCLASS=AVCRandom;
						break;
					}
					
				}
//				System.out.println("[IMG01_"+timeStamp.replace(".", "")+"_"+t+"]"+" inside method");
				String toReturnAVCClass="[IMG01_"+timeStamp.replace(".", "")+"_"+AVCCLASS+"]";
//				Thread.sleep(500);
				logger.info(toReturnAVCClass);
				
//				hexData = "02";         
//		        dataBytes = hexStringToByteArray(hexData);
//		        outputStreamForAVC.write(dataBytes);
		       
//		        System.out.println(toReturnAVCClass+" ccccccc");
				COM_Setup.COMAVCSetup99();
//				outputStreamForAVC.write(toReturnAVCClass.getBytes());
//				hexData = "03";         
//		        dataBytes = hexStringToByteArray(hexData);
//				outputStreamForAVC.write(dataBytes);
				String parent =driver.getWindowHandle();
			    driver.switchTo().window(parent); 
				try 
				{
					ExplicitWait(By.name("Vehicle pending in queue - 1"));
				}
				catch(Exception e)
				{
					
				}
				try 
	    	     {
					
	    	    	 ExplicitWait(By.name("Vehicle pending in queue - 0"));
	    	    	 SetProfilerImage d= new SetProfilerImage(VClassForFrCnvy[randomFreeConvoy]);
	    	     }
	    	     catch(TimeoutException e)
	    	     {
	    	    	  parent =driver.getWindowHandle();
					    driver.switchTo().window(parent); 
	    	    	 ExplicitWait(By.name("Vehicle pending in queue - 1"));
	    	    	 outputStreamForAVC.write(toReturnAVCClass.getBytes());
	    	    	 ExplicitWait(By.name("Vehicle pending in queue - 0"));
	    	    	 SetProfilerImage d= new SetProfilerImage(VClassForFrCnvy[randomFreeConvoy]);

	    	     }
//		        i= new ImageVerification();
		        driver.getKeyboard().sendKeys(Keys.chord(Keys.SHIFT,Keys.DELETE));
				driver.findElementByName("Yes").click();
		}
		else 
		{
			GetAVCData getdata=new GetAVCData();
			driver.getKeyboard().sendKeys(Keys.DELETE);
			driver.findElementByName("Yes").click();
			
			ExplicitWait(By.name("convoy mode started."));
//				PC_Send p= new PC_Send();
				String parent =driver.getWindowHandle();
			    driver.switchTo().window(parent); 
		        ExplicitWait(By.name("Vehicle pending in queue - 1"));
//		        Thread.sleep(200);
				timeStamp=new SimpleDateFormat("yy.MM.dd.HH.mm.ss").format(new Date());
				
				
//				VClassForFrCnvy[0]="Vehicle Class";
				VClassForFrCnvy[1]="CAR/JEEP";
				VClassForFrCnvy[2]="LCV";
				VClassForFrCnvy[3]="TRUCK";
				VClassForFrCnvy[4]="BUS";
				VClassForFrCnvy[5]="MAV 3";
				VClassForFrCnvy[6]="MAV 4";
				VClassForFrCnvy[7]="MAV 5";
				VClassForFrCnvy[8]="MAV 6";
				VClassForFrCnvy[9]="OSV";
				VClassForFrCnvy[10]="LCV";
				VClassForFrCnvy[11]="TRACTOR";
				VClassForFrCnvy[12]="Auto";
				VClassForFrCnvy[13]="BIKE";
				
				randomFreeConvoy=randomGenerator(1, VClassForFrCnvy.length-1);
				System.out.println(VClassForFrCnvy[randomFreeConvoy]+" rrrrrrrr");
				twodigits = new DecimalFormat("00");
			    AVCCLASS="";
				for(int j=1;j<VClassForFrCnvy.length;j++)
				{
//					System.out.println("returnVehicleClass :" + VClassForFrCnvy[randomFreeConvoy] );
//					System.out.println("VClassForFrCnvy: "+VClassForFrCnvy[i]);
//					System.out.println(VehicleClassList.get(i));
//					System.out.println(twodigits.format(i));
				    AVCRandom=twodigits.format(j);
//				    this.returnVehicleClass=returnVehicleClass;
					if(VClassForFrCnvy[randomFreeConvoy].contains(VClassForFrCnvy[j]))
					{
//						System.out.println("Match Found");
						AVCCLASS=AVCRandom;
						break;
					}
					
				}
//				System.out.println("[IMG01_"+timeStamp.replace(".", "")+"_"+t+"]"+" inside method");
				toReturnAVCClass="[IMG01_"+timeStamp.replace(".", "")+"_"+AVCCLASS+"]";
//				Thread.sleep(500);
//				hexData = "02";         
//		        dataBytes = hexStringToByteArray(hexData);
//		        outputStreamForAVC.write(dataBytes);
		       
//		        
		        
//				outputStreamForAVC.write(toReturnAVCClass.getBytes());
//				hexData = "03";         
//		        dataBytes = hexStringToByteArray(hexData);
//				outputStreamForAVC.write(dataBytes);
//		        ExplicitWait(By.name("lblAVCReceviedData"));  //26/Jul/2023 18:01:05--C
		        ImageVerification n1= new ImageVerification();
				FirstFreeConvoy=driver.findElementByAccessibilityId("ListViewSubItem-0").getText().toString();
				System.out.println(FirstFreeConvoy+" "+ 1);
				
				for(int i=1;i<trNo;i++)
				{
					PC_Send p1=new PC_Send();
					 parent =driver.getWindowHandle();
				    driver.switchTo().window(parent); 
			        ExplicitWait(By.name("Vehicle pending in queue - 1"));
//			        Thread.sleep(200);
					timeStamp=new SimpleDateFormat("yy.MM.dd.HH.mm.ss").format(new Date());
					
					
//					VClassForFrCnvy[0]="Vehicle Class";
					VClassForFrCnvy[1]="CAR/JEEP";
					VClassForFrCnvy[2]="LCV";
					VClassForFrCnvy[3]="TRUCK";
					VClassForFrCnvy[4]="BUS";
					VClassForFrCnvy[5]="MAV 3";
					VClassForFrCnvy[6]="MAV 4";
					VClassForFrCnvy[7]="MAV 5";
					VClassForFrCnvy[8]="MAV 6";
					VClassForFrCnvy[9]="OSV";
					VClassForFrCnvy[10]="LCV";
					VClassForFrCnvy[11]="TRACTOR";
					VClassForFrCnvy[12]="Auto";
					VClassForFrCnvy[13]="BIKE";
					
					randomFreeConvoy=randomGenerator(1, VClassForFrCnvy.length-1);
					System.out.println(VClassForFrCnvy[randomFreeConvoy]+" rrrrrrrr");
					twodigits = new DecimalFormat("00");
				    AVCCLASS="";
					for(int j=1;j<VClassForFrCnvy.length;j++)
					{

					    AVCRandom=twodigits.format(j);

						if(VClassForFrCnvy[randomFreeConvoy].contains(VClassForFrCnvy[j]))
						{

							AVCCLASS=AVCRandom;
							break;
						}
						
					}
//					System.out.println("[IMG01_"+timeStamp.replace(".", "")+"_"+t+"]"+" inside method");
					toReturnAVCClass="[IMG01_"+timeStamp.replace(".", "")+"_"+AVCCLASS+"]";
					Thread.sleep(500);
//					hexData = "02";         
//			        dataBytes = hexStringToByteArray(hexData);
//			        outputStreamForAVC.write(dataBytes);
//			        
//
//					
//					outputStreamForAVC.write(toReturnAVCClass.getBytes());
					COM_Setup.COMAVCSetup99();
//					hexData = "03";         
//			        dataBytes = hexStringToByteArray(hexData);
//					outputStreamForAVC.write(dataBytes);
			        ExplicitWait(By.name("Vehicle pending in queue - 0"));
	    	    	 SetProfilerImage d= new SetProfilerImage(VClassForFrCnvy[randomFreeConvoy]);

//					ImageVerification n= new ImageVerification();
					String LastTransaction=driver.findElementByAccessibilityId("ListViewSubItem-0").getText().toString();
					System.out.println("first transactio endswith: "+FirstFreeConvoy.substring(FirstFreeConvoy.length()-1));
					System.out.println("last transaction ends with: "+LastTransaction.substring(LastTransaction.length()-1));
					System.out.println(LastTransaction.endsWith(FirstFreeConvoy.substring(FirstFreeConvoy.length()-1)));
					if(LastTransaction.endsWith(FirstFreeConvoy.substring(FirstFreeConvoy.length()-1)))
					{	
						System.out.println("equal");
//						LastTransaction=driver.findElementByAccessibilityId("ListViewSubItem-0").getText().toString();
//						ExplicitWait(By.name(LastTransaction));
						i--;
						continue;
					}
					
						
						
						FirstFreeConvoy=LastTransaction;
					
					
				}
			
				driver.getKeyboard().sendKeys(Keys.chord(Keys.SHIFT,Keys.DELETE));
				driver.findElementByName("Yes").click();
		}
	
	}
	
	
	 
}
