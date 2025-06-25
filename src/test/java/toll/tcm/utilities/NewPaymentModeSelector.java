package toll.tcm.utilities;

import java.util.Map;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import com.aventstack.extentreports.ExtentTest;

import toll.tcm.Database.Mapping;
import toll.tcm.Hardware.SetProfilerImage;
import toll.tcm.testCases.BaseClass;

public class NewPaymentModeSelector extends BaseClass {
	static Logger logger=LogManager.getLogger(NewPaymentModeSelector.class);
	public NewPaymentModeSelector(ExtentTest test) throws InterruptedException {
		ExetentReportPass childtest;
		String VRN = RandomVehicleNumberGenerator.generateRandomVehicleNumber();
		driver.findElementByAccessibilityId("txtVehicleNo").sendKeys(VRN);
		logger.info("Random VRN is Generated :" + VRN);
		childtest= new ExetentReportPass(test,"VRN insertion time","VRN is "+VRN,"This stage VRN is inserted ");
		driver.getKeyboard().sendKeys(Keys.ENTER);
		String parent = driver.getWindowHandle();
		mainWindowToCurrentWindow(driver, parent);
		try {
			ExplicitWait(By.name("Select Payment Mode"));
			logger.info("payment mode selection time");
			driver.findElementByName("Select Payment Mode").click();
			childtest= new ExetentReportPass(test,"payment mode selection time","payment window is displayed","payment windows displayed successfully");
			driver.findElementByName("Select Payment Mode").click();
		} catch (org.openqa.selenium.TimeoutException e) {
			ExplicitWait(By.name("Select Payment Mode"));
			logger.info("payment mode selection time");
			driver.findElementByName("Select Payment Mode").click();
			childtest= new ExetentReportPass(test,"payment mode selection time","payment window is displayed","payment windows displayed successfully");
			driver.findElementByName("Select Payment Mode").click();
		}
		int PaymentType = random.nextInt(PaymentTypes);
		
		int PaymentSubTypesPayment1=Cash.size();
		int PaymentSubTypesPayment2=Card.size();
		int PaymentSubTypesPayment3=Wallet.size();
		
		String []keysArraysForCash=Cash.keySet().toArray(new String[0]);
		String []keysArraysForCard=Card.keySet().toArray(new String[0]);
		String []keysArraysForWallet=Wallet.keySet().toArray(new String[0]);
		
		
		random = new Random();
		int RandomSubTypePayment1 = random.nextInt(Cash.size());
		int RandomSubTypePayment2=random.nextInt(Card.size());
		int RandomSubTypePayment3=random.nextInt(Wallet.size());
		
		
		switch(PaymentType)
		{
		  case 0:
			  driver.getKeyboard().sendKeys(Keys.ENTER);
			  paymentTypes.get(PaymentType);
			  for (int i = 0; i < keysArraysForCash.length; i++) {
		           String key = keysArraysForCash[i];
		           String value = Cash.get(key);
		           logger.info(key+" payement type  selected....");
		   			childtest= new ExetentReportPass(test,key+" Payment Type",key+" payment type is selected",key+" payment type is selected successfully");
		   			driver.findElementByName("Select Payment Mode").click();
		   			if(keysArraysForCash.length==1)
		   			{
		   				if(value.equals("N"))
				           {
		   					driver.getKeyboard().sendKeys(Keys.ENTER);
							logger.info("cash subtype is selected");
							childtest= new ExetentReportPass(test,"Cash Subpayment Type","Cash subpayment type is selected","Cash subpayment type is selected successfully");
							driver.findElementByName("Select Payment Mode").click();
				           }
		   			}
		   			else
		   			{
		   				
		   			}
			  }
			  
		}
		
		
		
		SetProfilerImage s;
		
	}

}
