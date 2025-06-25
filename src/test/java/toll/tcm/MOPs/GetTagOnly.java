package toll.tcm.MOPs;

import java.awt.Robot;
import java.text.DecimalFormat;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;

import toll.tcm.Hardware.GetAVCData;
import toll.tcm.Hardware.ImageVerification;
import toll.tcm.Hardware.SetProfilerImage;
import toll.tcm.testCases.BaseClass;
import toll.tcm.utilities.DefaultTransactionNumber;

public class GetTagOnly extends BaseClass{
	static Logger logger=LogManager.getLogger(GetTagOnly.class);

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
	        	
	        int randomClass= Integer.valueOf(GetTag.getRandomETC(FastagVehicleMasterETCClass));
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
         driver.getKeyboard().sendKeys(Keys.chord(Keys.CONTROL,Keys.END));
		driver.getKeyboard().sendKeys(Keys.chord(Keys.CONTROL,Keys.END));
	}
}
