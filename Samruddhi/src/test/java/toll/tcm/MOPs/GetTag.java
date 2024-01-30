package toll.tcm.MOPs;

import java.awt.Robot;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import toll.tcm.Hardware.*;
import toll.tcm.testCases.*;
import toll.tcm.utilities.*;
public class GetTag 	extends BaseClass
{
	
	public static void main(String[] args) {
		
//		    DecimalFormat twodigits = new DecimalFormat("00");		
//		    Random random = new Random();
//		    int TagSeriesStart = 67;
//	        int TagSeriesEnd = 70;
//	        int TagClassStart=4;
//	        int TagClassEnd=15;
//	        int randomClass = random.nextInt(TagClassEnd - TagClassStart + 1) + TagClassStart;
//	        int randomTagSeries = random.nextInt(TagSeriesEnd - TagSeriesStart + 1) + TagSeriesStart;
//	        String tagClass=twodigits.format(randomClass);
//			System.out.println(tagClass);
//			int randomNumber = random.nextInt(100);
//			String b=twodigits.format(randomNumber);
//			System.out.println((char)randomTagSeries+b);
			String timeStamp=new SimpleDateFormat("yy.MM.dd.HH.mm.ss").format(new Date());
			System.out.println(timeStamp.replace(".", ""));
		 
	}
	public  void getTagData() throws Exception
	{
		LastTransaction= DefaultTransactionNumber.getDefaultTransationNumber();
		boolean Non_Default=false;
		 // Replace with the actual server IP or hostname
		System.out.println("default transaction:"+ LastTransaction);
		try 
		{
			String parent1=driver.getWindowHandle();
			mainWindowToCurrentWindow(driver, parent1);
//			LastTransaction=driver.findElementByAccessibilityId("ListViewSubItem-0").getText().toString(); [2A.1108A6.4.0.0] ListViewSubItem-0
			LastTransaction=driver.findElement(By.xpath("//List[@Name='Last. Trans.'][@AutomationId='lstLastTrans']//ListItem[starts-with(@AutomationId,'ListViewItem-')]")).getText().toString();
			 Non_Default =true;
			System.out.println("NON-default transaction:"+ LastTransaction);
		}
		catch(Exception e)
		{
//			
			LastTransaction=DefaultTransactionNumber.getDefaultTransationNumber();
		}
		
		GetAVCData getdata=new GetAVCData();
		// Replace with the actual server port
		 DecimalFormat twodigits = new DecimalFormat("00");		
		    Random random = new Random();
		    int TagSeriesStart = 67;
	        int TagSeriesEnd = 70;
//	        int TagClassStart=4;
//	        int TagClassEnd=15;
//	        int randomClass=randomGenerator(TagClassStart, TagClassEnd);
	        int randomTagSeries=randomGenerator(TagSeriesStart, TagSeriesEnd);
	        	
	        int randomClass= Integer.valueOf(getRandomETC(FastagVehicleMasterETCClass));
	        String TagClass=twodigits.format(randomClass);
			int randomNumber = random.nextInt(100);
			String zeroto99=twodigits.format(randomNumber);
			System.out.println((char)randomTagSeries+zeroto99);
		
		 
			String randomString = generateRandomString(5);
	        System.out.println(randomString);

		 
			

//		int port=Integer.valueOf(Port);
//		Socket socket = new Socket(tagReaderIPaddress, port);

//		  outputStreamForTag = socket.getOutputStream();
//		  34161FA8203288AC0836CF33,58585858585858585858585805,E2003412012A1B00025EF59E060B00

//			 String data = "34161FA8203288AC"+randomString+randomTagSeries+zeroto99+",5858585858585858585858581"+TagClass+",E2003412012A1B00025EF59E060B00";
	        int rand =BaseClass.randomGenerator(1, 1);
	        String data;
	        if(rand==1)
	        {
	   		 data = "585858585858585858585858"+TagClass+",34161FA8203288AC"+randomString+randomTagSeries+zeroto99+",E2003412012A1B00025EF59E060B00";

	        }
	        else
	        {
	        	System.out.println("blacklist!!!");
	        	int RandomBlacklistedTag=BaseClass.randomGenerator(0, BlackListedTags.size());
	   		  data = "585858585858585858585858"+TagClass+","+BlackListedTags.get(RandomBlacklistedTag)+",E2003412012A1B00025EF59E060B00";

	        }
		 // Prepare the data to be sent

//			 String data = "58585858585858585858585811"+TagClass+",34161FA8203288AC816,E2003412012A1B00025EF59E060B00";

		 System.out.println("data======== "+data);

		 // Convert the data to bytes and send it through the output stream
//		 inputStreamForTag = socket.getInputStream();
		 logger.info("ETC Tag Read Start");
		 outputStreamForTag.write(data.getBytes());
		 byte[] responseBytes = new byte[1024];
         int bytesRead = inputStreamForTag.read(responseBytes);
         String parent=driver.getWindowHandle();
         // Convert the received bytes to a string
         String response = new String(responseBytes, 0, bytesRead);
         logger.info("Responce is:"+response);
//         ExplicitWaitForTag(By.className("WindowsForms10.BUTTON.app.0.13965fa_r6_ad1")); //
         try 
         {
        	 if(Is_ETC_Popup.contains("Y"))
             {
//            	 wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("WindowsForms10.STATIC.app.0.13965fa_r6_ad1")));
             }
             else
             {
            	 try 
            	 {
            		 
//            		 String Parent=driver.getWindowHandle();
//            		 mainWindowToCurrentWindow(driver, Parent);
//            		 logger.info("Waiting for Application Valid message..."); 
//            		 ExplicitWaitForTag(By.id("picBarrier_O"));
//            		 logger.info("Valid message displayed..."); 
            	 }
            	 catch(Exception e)
            	 {
//            		 ExplicitWaitForTag(By.className("WindowsForms10.STATIC.app.0.13965fa_r8_ad1"));
            	 }
             }
         }
         catch(java.lang.NullPointerException v)
         {
        	 
         }
         logger.info("ETC Tag Read End");
         if(response.contains("INVALID"))
         {
        	
        	 System.out.println("Response from server is invalid: " + response);
//        	 String TagClass1=driver.findElementByAccessibilityId("lblTagId").getText().toString();
//        	 System.out.println(TagClass1+" yyyyy");
        	 try 
        	 {
        		 ExplicitWaitForTag(By.className("WindowsForms10.STATIC.app.0.13965fa_r6_ad1"));
        	 }
        	 catch(Exception e)
        	 {
        		 ExplicitWaitForTag(By.className("WindowsForms10.STATIC.app.0.13965fa_r8_ad1"));
        	 }
        	 captureScreen(driver, "ETC");
        	 Robot robot= new Robot();
        	 robot.keyPress(61);
        	 robot.keyRelease(61);
//     		 Assert.assertTrue(false);
//        	 Thread.sleep(200);
//        	 getTagData();
        	 
         }
         else
         {
        	 
        	 logger.info("Response from server: " + response); 
        	 mainWindowToCurrentWindow(driver, parent);
//        	 ExplicitWait(By.xpath("//Window[@Name='frmCardDetails'][@AutomationId='frmTagConfermation']//Button[@AutomationId='btnAccept']"));
//        	 ExplicitWait(By.name("Tag Transaction Confirmation"));
//        	 driver.findElementByName("Tag Transaction Confirmation").click();
//        	 logger.info("Confirmation popup found time");
//        	 driver.getKeyboard().sendKeys(Keys.ENTER);
//        	 parent=driver.getWindowHandle();
//        	 mainWindowToCurrentWindow(driver, parent);
//    		 String TagClass1=driver.findElementByAccessibilityId("btnTag1").getText().toString();  //TASAWADE
//        	 Long Latesttransaction=(Long.valueOf(LastTransaction))+1;
//        	 String latest=String.valueOf(Latesttransaction);
//        	 System.out.println(latest);
//        	 ExplicitWaitForTag(By.name(latest));
        	 logger.info("is weight insert:"+Is_ManualInsertWeight );
        	 try 
        	 {
        		 if(Is_ManualInsertWeight.contains("Y"))
            	 {
            		 ExplicitWaitForTag(By.name("Wim Weight"));
                	 driver.getKeyboard().sendKeys(Keys.ENTER);
            	 }
            	 
            	if(Is_ETC_Popup.contains("Y")|Is_ETC_Popup=="")       		
            	{
            		 ExplicitWait(By.xpath("//Window[@Name='frmCardDetails'][@AutomationId='frmTagConfermation']//Button[@AutomationId='btnAccept']"));
//                	 ExplicitWait(By.name("Tag Transaction Confirmation"));
            		 parent=driver.getWindowHandle();
                	 mainWindowToCurrentWindow(driver, parent);
                	 driver.findElementByName("Tag Transaction Confirmation").click();
                	 logger.info("Confirmation popup found time");
                	 driver.findElement(By.xpath("//Window[@Name='frmCardDetails'][@AutomationId='frmTagConfermation']//Button[@AutomationId='btnAccept']")).click();
            		 logger.info("enter is press");
            	}
        	 }
        	 catch(java.lang.NullPointerException v)
             {
            	 
             }
        	 try 
        	 {
        		 String parent1=driver.getWindowHandle();
        		 mainWindowToCurrentWindow(driver, parent1);
        		 logger.info("waiting for try block :Vehicle pending in queue - 1");
        		 ExplicitWaitForTag(By.name("Vehicle pending in queue - 1"));
        		 
        	 }
    	     catch(org.openqa.selenium.TimeoutException e)
    	     {
    	    	 logger.info("waiting for catch block :Vehicle pending in queue - 1");
    	    	 ExplicitWaitForTag(By.name("Vehicle pending in queue - 1"));
    	     }
//    	     captureScreen(driver, "main");
    	       //SNC
    	     if(Non_Default)
    	        {
    	        	Long Latesttransaction=(Long.valueOf(LastTransaction))+1;
    	       	    String latest=String.valueOf(Latesttransaction);
    	       	     System.out.println(latest);
    	       	    
    	       	  logger.info("Non-Default Transaction is found.");
    	       	    	ExplicitWaitForTag(By.name(latest));
    	       	     
    	        }
    	        else
    	        { 	
    	        	
    	        	Long Latesttransaction=(Long.valueOf(LastTransaction))+1;
    	        	try 
    	        	{
    	        		logger.info("Default Transaction is found.");
    	        		ExplicitWaitForTag(By.name(String.valueOf(Latesttransaction)));
    	        	}
    	        	catch(org.openqa.selenium.TimeoutException r )
    	        	{
    	        		
    	        	}
    	        } 
    	     logger.info("tag class searching..");
    	     String TagClass1=driver.findElementByAccessibilityId("ListViewSubItem-4").getText().toString();   //SNC
    	     logger.info("class found now"+TagClass1+" Tag getting AVC..");
    	     PC_Send P=new PC_Send();
//    		 COM_Setup.IPAVCSetup99(TagClass1);
//    	     COM_Setup.COMAVCSetup99();
    	     outputStreamForAVC.write(getdata.getAVCData(TagClass1).getBytes());
		    	logger.info(getdata.getAVCData(TagClass1)+" :outputstream ");
//    	     outputStreamForAVC.write(getdata.getAVCData(TagClass1).getBytes()); 
    	     logger.info("Tag End AVC");
//    	     Thread.sleep(5000);
//    	     byte[] responseBytesForAVC = new byte[1024];
//             int bytesReadForAVC = inputStreamForAVC.read(responseBytesForAVC);
             
             // Convert the received bytes to a string
//             String responseForAVC = new String(responseBytesForAVC, 0, bytesReadForAVC);
//             System.out.println("Responce for AVC:"+responseForAVC);
//           outputStream.write(getdata.getAVCData(response).getBytes());   //TASWADE
    	     try 
    	     {
    	    	 logger.info("waiting for try block :Vehicle pending in queue - 0");
    	    	 ExplicitWaitForTag(By.name("Vehicle pending in queue - 0"));
    	     }
    	     catch(TimeoutException e)
    	     {
    	    	 logger.info("waiting for catch block :Vehicle pending in queue - 0");
    	    	 ExplicitWaitForTag(By.name("Vehicle pending in queue - 1"));
    	    	 
    	    	 if(Non_Default)
    	         {
    	         	Long Latesttransaction=(Long.valueOf(LastTransaction))+1;
    	        	    String latest=String.valueOf(Latesttransaction);
    	        	     System.out.println(latest);
    	         	 ExplicitWaitForTag(By.name(latest));
    	         }
    	         else
    	         {
    	         	ExplicitWaitForTag(By.xpath("//List[@Name='Last. Trans.'][@AutomationId='lstLastTrans']//ListItem[starts-with(@AutomationId,'ListViewItem-')]"));
    	         }
    	    	 logger.info("tag class searching..");
        	     TagClass1=driver.findElementByAccessibilityId("ListViewSubItem-4").getText().toString();   //SNC
        	     logger.info("class found now"+TagClass1+" Tag getting AVC..");
//    	    	 responseBytesForAVC = new byte[1024];
//                 bytesReadForAVC = inputStreamForTag.read(responseBytesForAVC);
                 
                 // Convert the received bytes to a string
//                 responseForAVC = new String(responseBytesForAVC, 0, bytesReadForAVC);
//                 System.out.println("Responce for AVC:"+responseForAVC);
        		 logger.info("waiting for :Vehicle pending in queue - 0");
    	    	 ExplicitWaitForTag(By.name("Vehicle pending in queue - 0"));
    	     }
          	 SetProfilerImage s = new SetProfilerImage(TagClass1);
          	ImageVerification i=new ImageVerification();
         }
         String parent1=driver.getWindowHandle();
		 // Close the output stream and socket
         mainWindowToCurrentWindow(driver, parent1);
//		 outputStream.close();

//		 socket.close();
		
		
	}
	public  void getExceltag(String Class) throws Exception
	{
		LastTransaction= DefaultTransactionNumber.getDefaultTransationNumber();
		boolean Non_Default=false;
		
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
		// Replace with the actual server port
		 DecimalFormat twodigits = new DecimalFormat("00");		
		    Random random = new Random();
		    int TagSeriesStart = 67;
	        int TagSeriesEnd = 70;
//	        int randomClass = random.nextInt(TagClassEnd - TagClassStart + 1) + TagClassStart;
	        int randomTagSeries=randomGenerator(TagSeriesStart, TagSeriesEnd);
//	        int randomTagSeries = random.nextInt(TagSeriesEnd - TagSeriesStart + 1) + TagSeriesStart;
	        
	        String TagClass=SjClassToETCClass(Class);
//			System.out.println(TagClass);
			int randomNumber = random.nextInt(100);
			String zeroto99=twodigits.format(randomNumber);
			System.out.println((char)randomTagSeries+zeroto99);
		
		 
			String randomString = generateRandomString(5);
	        System.out.println(randomString);

		 
			

		int port=Integer.valueOf(Port);
		Socket socket = new Socket(tagReaderIPaddress, port);

		  outputStreamForTag = socket.getOutputStream();



		 // Prepare the data to be sent

		 String data = "5858585858585858585858581"+TagClass+",34161FA8203288AC"+randomString+randomTagSeries+zeroto99+",E2003412012A1B00025EF59E060B00";


		 // Convert the data to bytes and send it through the output stream
		 inputStreamForTag = socket.getInputStream();
		 outputStreamForTag.write(data.getBytes());
		 byte[] responseBytes = new byte[1024];
         int bytesRead = inputStreamForTag.read(responseBytes);

         // Convert the received bytes to a string
         String response = new String(responseBytes, 0, bytesRead);
//         ExplicitWaitForTag(By.className("WindowsForms10.BUTTON.app.0.13965fa_r6_ad1"));
         if(response.contains("INVALID"))
         {
        	
        	 System.out.println("Response from server is invalid: " + response);
//        	 String TagClass1=driver.findElementByAccessibilityId("lblTagId").getText().toString();
//        	 System.out.println(TagClass1+" yyyyy");
//        	  ExplicitWaitForTag(By.className("WindowsForms10.BUTTON.app.0.13965fa_r6_ad1"));
        	 captureScreen(driver, "ETC");
     		 Assert.assertTrue(false);
        	 Thread.sleep(200);
//        	 getTagData();
        	 
         }
         else
         {
        	 
        	 System.out.println("Response from server: " + response); 
//    		 String TagClass1=driver.findElementByAccessibilityId("btnTag1").getText().toString();  //TASAWADE
//        	 Long Latesttransaction=(Long.valueOf(LastTransaction))+1;
//        	 String latest=String.valueOf(Latesttransaction);
//        	 System.out.println(latest);
//        	 ExplicitWaitForTag(By.name(latest));
        	 if(Is_ETC_Popup.contains("Y"))       		
         	{
         		ExplicitWaitForTag(By.name("Tag Transaction Confirmation"));
         		driver.getKeyboard().sendKeys(Keys.ENTER);
         	}
    	     ExplicitWaitForTag(By.name("Vehicle pending in queue - 1"));
//    	     captureScreen(driver, "main");
    	       //SNC
    	     if(Non_Default)
    	        {
    	        	Long Latesttransaction=(Long.valueOf(LastTransaction))+1;
    	       	    String latest=String.valueOf(Latesttransaction);
    	       	     System.out.println(latest);
    	        	 ExplicitWaitForTag(By.name(latest));
    	        }
    	        else
    	        {
    	        	ExplicitWaitForTag(By.name(LastTransaction));
    	        }
    	     String TagClass1=driver.findElementByAccessibilityId("ListViewSubItem-4").getText().toString();   //SNC
    	     System.out.println("Tag getting AVC");
    		 System.out.println(TagClass1);   		 
    	     outputStreamForAVC.write(getdata.getAVCData(TagClass1).getBytes());  
//    	     Thread.sleep(5000);
//    	     byte[] responseBytesForAVC = new byte[1024];
//             int bytesReadForAVC = inputStreamForAVC.read(responseBytesForAVC);
             
             // Convert the received bytes to a string
//             String responseForAVC = new String(responseBytesForAVC, 0, bytesReadForAVC);
//             System.out.println("Responce for AVC:"+responseForAVC);
//           outputStream.write(getdata.getAVCData(response).getBytes());   //TASWADE
    	     try 
    	     {
    	    	 ExplicitWaitForTag(By.name("Vehicle pending in queue - 0"));
    	     }
    	     catch(TimeoutException e)
    	     {
    	    	 ExplicitWaitForTag(By.name("Vehicle pending in queue - 1"));
    	    	 
    	    	 if(Non_Default)
    	         {
    	         	Long Latesttransaction=(Long.valueOf(LastTransaction))+1;
    	        	    String latest=String.valueOf(Latesttransaction);
    	        	     System.out.println(latest);
    	         	 ExplicitWaitForTag(By.name(latest));
    	         }
    	         else
    	         {
    	         	ExplicitWaitForTag(By.name(LastTransaction));
    	         }
    	    	 TagClass1=driver.findElementByAccessibilityId("ListViewSubItem-4").getText().toString();   //SNC
    	    	 System.out.println("Tag getting AVC");
        		 System.out.println(TagClass1);       		 
        		 outputStreamForAVC.write(getdata.getAVCData(TagClass1).getBytes());
//    	    	 responseBytesForAVC = new byte[1024];
//                 bytesReadForAVC = inputStreamForTag.read(responseBytesForAVC);
                 
                 // Convert the received bytes to a string
//                 responseForAVC = new String(responseBytesForAVC, 0, bytesReadForAVC);
//                 System.out.println("Responce for AVC:"+responseForAVC);
    	    	 ExplicitWaitForTag(By.name("Vehicle pending in queue - 0"));
    	     }
    	     SetProfilerImage s = new SetProfilerImage(TagClass1);
          	ImageVerification i=new ImageVerification();
         }
	}
	public String SjClassToETCClass(String VClass)
	{
		if(VClass.contains("04"))
		{
			return "04";
		}
		else if(VClass.contains("05"))
		{
			return "05";
		}
		else if(VClass.contains("07"))
		{
			return "05";
		}
		else if(VClass.contains("10"))
		{
			return "10";
		}
		else if(VClass.contains("11"))
		{
			return "11";
		}
		else if(VClass.contains("12"))
		{
			return "12";
		}
		else if(VClass.contains("13"))
		{
			return "13";
		}
		else if(VClass.contains("14"))
		{
			return "14";
		}
		else
		{
			return "15";
		}
		
	}
	private static <T> T getRandomETC(ArrayList<T> arrayList) {
		Random random = new Random();
        int randomIndex = random.nextInt(arrayList.size());
        return arrayList.get(randomIndex);
    }
}
