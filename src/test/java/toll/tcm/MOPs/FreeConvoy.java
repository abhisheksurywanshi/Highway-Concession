package toll.tcm.MOPs;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;

import com.aventstack.extentreports.ExtentTest;

import toll.tcm.Hardware.*;
import toll.tcm.testCases.*;
import toll.tcm.utilities.*;
public class FreeConvoy extends BaseClass
{
	static Logger logger=LogManager.getLogger(FreeConvoy.class);

	public static void freeConvoy(int trNo) throws Exception
	{
		ExtentTest FreeConvoy = extent.createTest("FreeConvoy");
		ExetentReportPass test;
//		int port=Integer.valueOf(5001);
//		Socket socket = new Socket(tagReaderIPaddress, port);

//		  OutputStream outputStreamForAVC = socket.getOutputStream();
		String LastClass="";
		VClassForFrCnvy=new String[14];
		if(trNo==1)
		{
			GetAVCData getdata=new GetAVCData();
			driver.getKeyboard().sendKeys(Keys.chord(Keys.SHIFT,Keys.DELETE));
			test= new ExetentReportPass(FreeConvoy,"After Pressing Free Convoy Button","Pop up is display for Convoy Mode Start ","Operator Selection for Convoy Mode Start Yes or No");
			driver.findElementByName("Yes").click();
			try 
			{
				ExplicitWait(By.name("convoy mode started."));
				test= new ExetentReportPass(FreeConvoy,"After Strarting Free Convoy Mode","system message is display for Convoy Mode Start ","Convoy Mode is started");

			}
			catch(org.openqa.selenium.TimeoutException e)
			
			{
				ExplicitWait(By.name("convoy mode started."));
				test= new ExetentReportPass(FreeConvoy,"After Strarting Free Convoy Mode","system message is display for Convoy Mode Start ","Convoy Mode is started");

			}
			
//			System.out.println("this block is perform tr==1");
			
				PC_Send p= new PC_Send();
		        logger.info("PC send time");
		        test= new ExetentReportPass(FreeConvoy,"PC Send Time","Vehicle pending in queue - 1 ","in This stage for barrier up");
//		        Thread.sleep(200);
				timeStamp=new SimpleDateFormat("yy.MM.dd.HH.mm.ss").format(new Date());
				
				
				 int Rand=randomGenerator(0, keysArrayForCDAndClass.length-1);
				   String key = keysArrayForCDAndClass[Rand];
		           String value = CDANDCLASS.get(key);
		           LastClass=value;
		           System.out.println("last regular:"+LastClass);
//				VClassForFrCnvy[1]="CAR/JEEP";
//				VClassForFrCnvy[2]="LCV";
//				VClassForFrCnvy[3]="TRUCK";
//				VClassForFrCnvy[4]="BUS";
//				VClassForFrCnvy[5]="MAV 3";
//				VClassForFrCnvy[6]="MAV 4";
//				VClassForFrCnvy[7]="MAV 5";
//				VClassForFrCnvy[8]="MAV 6";
//				VClassForFrCnvy[9]="OSV";
//				VClassForFrCnvy[10]="LCV";
//				VClassForFrCnvy[11]="TRACTOR";
//				VClassForFrCnvy[12]="Auto";
//				VClassForFrCnvy[13]="BIKE";
//				
//				randomFreeConvoy=randomGenerator(1, VClassForFrCnvy.length-1);
//			logger.info(VClassForFrCnvy[randomFreeConvoy]+" Class for ");
//				twodigits = new DecimalFormat("00");
//			    AVCCLASS="";
//				for(int j=1;j<VClassForFrCnvy.length;j++)
//				{
//
//				    AVCRandom=twodigits.format(j);
//
//					if(VClassForFrCnvy[randomFreeConvoy].contains(VClassForFrCnvy[j]))
//					{
//
//						AVCCLASS=AVCRandom;
//						LastClass=VClassForFrCnvy[randomFreeConvoy];
//						break;
//					}
//					
//				}
//
//				String toReturnAVCClass="[IMG01_"+timeStamp.replace(".", "")+"_"+AVCCLASS+"]";
//
//				logger.info(toReturnAVCClass);
				
		           
				

				String parent =driver.getWindowHandle();
			    driver.switchTo().window(parent); 
				try 
				{
					 logger.info("Exmpicit wait for Vehicle pending in queue - 1");
					Fluentwait((driver.findElementByName("Vehicle pending in queue - 1")), 5, 300)  ; //(By.name("Vehicle pending in queue - 1"));
					logger.info("Exmpicit wait for Vehicle pending in queue - 1 done");
					COM_Setup.COMAVCSetup99();
//					COM_Setup.IPAVCSetup99();
			        test= new ExetentReportPass(FreeConvoy,"Vehicle is pass through barrigate","Vehicle pending in queue - 0 ","in This stage for barrier is up");
				}
				catch(Exception e)
				{
					
				}
				
					 logger.info("Exmpicit wait for Vehicle pending in queue - 0");
					 Fluentwait((driver.findElementByName("Vehicle pending in queue - 0")), 5, 300) ;
	    	    	 logger.info("Exmpicit wait for Vehicle pending in queue - 1 done");
//	    	    	 COM_Setup.COMAVCSetup99();
//				       test= new ExetentReportPass(FreeConvoy,"Vehicle is pass through barrigate","Vehicle pending in queue - 0 ","in This stage for barrier is up");
	    	    	 SetProfilerImage d= new SetProfilerImage(FreeConvoy,LastClass);
	    	    	 logger.info("Set profiler image done");
	    	     
	    	     
		        
		        logger.info("Last Clss is:"+LastClass);
//				SetProfilerImage s = new SetProfilerImage(LastClass);
//				i= new ImageVerification();
//		        driver.getKeyboard().sendKeys(Keys.chord(Keys.CONTROL,Keys.END));
		        driver.getKeyboard().sendKeys(Keys.chord(Keys.SHIFT,Keys.DELETE));
				test= new ExetentReportPass(FreeConvoy,"After Pressing Free Convoy Button","Pop up is display for Convoy Mode Stop ","Operator Selection for Convoy Mode Stop Yes or No");

				driver.findElementByName("Yes").click();
				test= new ExetentReportPass(FreeConvoy,"After Stoping Free Convoy Mode ","System message is display for Convoy Mode Stop ","In this stage selection is null");

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
		        ImageVerification n1= new ImageVerification(FreeConvoy);
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
							LastClass=VClassForFrCnvy[j];
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
//					COM_Setup.IPAVCSetup99();
//					hexData = "03";         
//			        dataBytes = hexStringToByteArray(hexData);
//					outputStreamForAVC.write(dataBytes);
			        ExplicitWait(By.name("Vehicle pending in queue - 0"));
	    	    	 SetProfilerImage d= new SetProfilerImage(FreeConvoy,VClassForFrCnvy[randomFreeConvoy]);

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
				i = new ImageVerification(FreeConvoy);
				logger.info("Last Clss is:"+LastClass);
				SetProfilerImage s = new SetProfilerImage(FreeConvoy,LastClass);
				driver.getKeyboard().sendKeys(Keys.chord(Keys.SHIFT,Keys.DELETE));
				driver.findElementByName("Yes").click();
		}
	
	}
	
	
	 
}
