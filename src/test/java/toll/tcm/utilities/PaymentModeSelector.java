package toll.tcm.utilities;

import java.util.Map;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import com.aventstack.extentreports.ExtentTest;

import toll.tcm.Hardware.SetProfilerImage;
import toll.tcm.testCases.BaseClass;

public class PaymentModeSelector extends BaseClass {
	static Logger logger=LogManager.getLogger(PaymentModeSelector.class);
	public PaymentModeSelector(ExtentTest test) throws InterruptedException {
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
		
		random = new Random();
		logger.info("total payment type:"+PaymentTypes);
		int randomNumber = random.nextInt(PaymentTypes);

		int randomNumber1 = random.nextInt(3);
		SetProfilerImage s;
		switch (randomNumber) {
		case 0: // cash-cash

			
			logger.info("Cash payement type  selected....");
			childtest= new ExetentReportPass(test,"Cash Payment Type","Cash payment type is selected","Cash payment type is selected successfully");
			driver.findElementByName("Select Payment Mode").click();
			driver.getKeyboard().sendKeys(Keys.ENTER);
			
		
			
			
			for (Map.Entry<String, String> entry : PaymentSubType.entries()) {
				String key = entry.getKey();
				if (key.contains("CASH")) {

					if (PaymentSubType.get(key).size() == 1) {
						
						logger.info("cash subtype is selected");
						childtest= new ExetentReportPass(test,"Cash Subpayment Type","Cash subpayment type is selected","Cash subpayment type is selected successfully");
						driver.findElementByName("Select Payment Mode").click();
						driver.getKeyboard().sendKeys(Keys.ENTER);
					}
				} else {
					String value = entry.getValue();
					System.out.println("Key: " + key + ", Value: " + value);
				}

			}

			break;
		case 1: // Card

			arrowDownOneTime();
			driver.getKeyboard().sendKeys(Keys.ENTER);
			logger.info("Card payment type is selected....");
			childtest= new ExetentReportPass(test,"Card Payment Type","Card Payment type is selected","Card Payment type is selected successfully");
			driver.findElementByName("Select Payment Mode").click();	
			for (Map.Entry<String, String> entry : PaymentSubType.entries()) {
				String key = entry.getKey();
				if (key.contains(PaymentType.get(1))) {

					if (PaymentSubType.get(key).size() == 1) {
						childtest= new ExetentReportPass(test,"Card Subpayment Type","Card subpayment type is selected","Card subpayment type is selected successfully");
						driver.findElementByName("Select Payment Mode").click();
						driver.getKeyboard().sendKeys(Keys.ENTER);
						

					}
				} else {
					String value = entry.getValue();
					logger.info("Key: " + key + ", Value: " + value);
				}

			}
			for (int i = 0; i < keysArraygetPaymentTypeIsReference.length; i++) {
				String key = keysArraygetPaymentTypeIsReference[i];
				if (key.contains(PaymentType.get(1))) {

					ExplicitWait(By.xpath("//Text[@Name='Enter Refernce Number'][@AutomationId='lblRef']"));
					
					driver.getKeyboard().sendKeys(VRN.substring(6));
					childtest= new ExetentReportPass(test,"Reference Number Popup",VRN.substring(6)+" is entered","reference number insert successfully");
					driver.findElementByXPath("//Text[@Name='Enter Refernce Number'][@AutomationId='lblRef']").click();
					driver.getKeyboard().sendKeys(Keys.ENTER);
//					if (Is_Card_Ref.contains("Y")) {
//						driver.getKeyboard().sendKeys(VRN.substring(6));
//						driver.getKeyboard().sendKeys(Keys.ENTER);
//					}

				}
			}
			parent = driver.getWindowHandle();

			break;

		case 2: // wallet
//					        	
			arrowDownTwoTime();
			driver.getKeyboard().sendKeys(Keys.ENTER);
			logger.info("Wallet payment type is selected....");
			childtest= new ExetentReportPass(test,"Wallet Payment Type","Wallet Payment type is selected","Wallet Payment type is selected successfully");
			driver.findElementByName("Select Payment Mode").click();
			switch (randomNumber1) {
			case 0:
//				arrowDownOneTime();
				driver.getKeyboard().sendKeys(Keys.ENTER);
				logger.info("Paytm Wallet payment type is selected.... case 0");
//				CashTransaction.info("Paytm Wallet payment type is selected....");

				driver.findElementByName("Enter Refernce Number").sendKeys(VRN.substring(6));
				logger.info("Reference number is entered");
//				CashTransaction.info("Paytm Wallet payment type is selected....");
				driver.getKeyboard().sendKeys(Keys.ENTER);
				System.out.println("wallet Paytm");

				break;
			case 1:
				arrowDownOneTime();
				driver.getKeyboard().sendKeys(Keys.ENTER);
				logger.info("Paytm Wallet payment type is selected.... case 1");
//				CashTransaction.info("Paytm Wallet payment type is selected....");
				driver.findElementByName("Enter Refernce Number").sendKeys(VRN.substring(6));
				logger.info("Reference number is entered");
//				CashTransaction.info("Reference number is entered");
				driver.getKeyboard().sendKeys(Keys.ENTER);


				break;
			case 2:
				arrowDownTwoTime();
				driver.getKeyboard().sendKeys(Keys.ENTER);
				logger.info("Paytm Wallet payment type is selected.... case 2");
//				CashTransaction.info("Paytm Wallet payment type is selected....");
				driver.findElementByName("Enter Refernce Number").sendKeys(VRN.substring(6));
				logger.info("Reference number is entered");
//				CashTransaction.info("Reference number is entered");
				driver.getKeyboard().sendKeys(Keys.ENTER);


				break;
			default:
				System.out.println("Invalid random number generated. inside");

			}
			break;
		default:


		}
	}

}
