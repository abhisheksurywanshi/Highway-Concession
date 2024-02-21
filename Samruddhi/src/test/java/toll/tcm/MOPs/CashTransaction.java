package toll.tcm.MOPs;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import com.aventstack.extentreports.ExtentTest;

import io.appium.java_client.windows.WindowsElement;
import toll.tcm.Database.DatabaseConnectivity;
import toll.tcm.Hardware.*;
import toll.tcm.testCases.*;
import toll.tcm.utilities.*;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;

public class CashTransaction extends BaseClass {
	static Logger logger=LogManager.getLogger(CashTransaction.class);

	public static void randomCashTransaction() throws IOException, InterruptedException, SQLException, AWTException {
//			try
//			{	
		ExtentTest CashTransaction = extent.createTest("CashTransaction");
		GetAVCData getdata = new GetAVCData();
//				random = new Random();
//				int VRNStart = 1;
//		        int VRNEnd = 9999;
//		        int randomVRN = random.nextInt(VRNEnd - VRNStart + 1) + VRNStart;
//		        int randomVRN = BaseClass.randomGenerator(1, 9999);
//					    DecimalFormat fourdigits = new DecimalFormat("0000");
//					    System.out.println(fourdigits.format(randomVRN));
//					    String b=fourdigits.format(randomVRN);
//					    Keys Class=RandomClassSelector.getRandomKeys();
//					    driver.getKeyboard().sendKeys(Class);

		RandomClassSelector.getSchedualVehicleKey();
		RandomOtherCashKeySelector c = new RandomOtherCashKeySelector();
//				driver.getKeyboard().sendKeys(Keys.F2);
		logger.info("Class selection TIme");
		CashTransaction.info("Class selection TIme");
//					    if(Class==Keys.F6||Class==Keys.F7) //tasawade
//					    {
//					    	RandomClassSelector.subClassSelector(Class);
//					    	driver.getKeyboard().sendKeys(Keys.ENTER);
//					    }
//					    try	
//					    {
//					    	if(driver.findElementByName("Select sub Vehicle Class For WIM").isDisplayed())
//					    	{ 
		String parent = driver.getWindowHandle();
		driver.switchTo().window(parent);
//					    		Thread.sleep(500);
		if (IsWimAvailable.contains("Y")) {
			SelectRandomSubClass.getRandomSelection();
		}

//					    	}
//					    }
//					    catch(org.openqa.selenium.NoSuchElementException r)
//					    {

//					    }

		String Vclass = driver.findElementByAccessibilityId("lblClass").getText().toString();
		logger.info(Vclass.toString() + " Key is selected...");
		CashTransaction.info(Vclass.toString() + " Key is selected...");
		if (Vclass.equals("Vehicle Class")) {

			driver.findElementByAccessibilityId("txtVehicleNo").clear();
			logger.warn("Vehicle number cleared.. because class selection is null");
			CashTransaction.warning("Vehicle number cleared.. because class selection is null");

			Thread.sleep(2000);
		} else {
			String VRN = RandomVehicleNumberGenerator.generateRandomVehicleNumber();
			driver.findElementByAccessibilityId("txtVehicleNo").sendKeys(VRN);
			logger.info("Random VRN is Generated :" + VRN);
			CashTransaction.info("Random VRN is Generated :" + VRN);
			driver.getKeyboard().sendKeys(Keys.ENTER);
			parent = driver.getWindowHandle();
			mainWindowToCurrentWindow(driver, parent);
			try {
				ExplicitWait(By.name("Select Payment Mode"));
				logger.info("payment mode selection time");
				CashTransaction.info("payment mode selection time");
				driver.findElementByName("Select Payment Mode").click();
			} catch (org.openqa.selenium.TimeoutException e) {
				ExplicitWait(By.name("Select Payment Mode"));
				logger.info("payment mode selection time");
				CashTransaction.info("payment mode selection time");
				driver.findElementByName("Select Payment Mode").click();
			}

//					    	if(In_Out.contains("I")&&!Toll_Name.contains("Sasthan"))
//					    	{
//					    		ExplicitWait(By.name("Vehicle pending in queue - 1"));
//					        	logger.info("Barrier up time");
//					        	System.out.println(getdata.getAVCData(Vclass));
//					        	PC_Send obj1=new PC_Send();
//					        	logger.info("PC Send Time");
//					        	COM_Setup.IPAVCSetup(Vclass);
//						    	
//					        	logger.info(Vclass+" AVC is send");
//					        	logger.info(Vclass+" Barrier Down Time");
//					        	ExplicitWait(By.name("Vehicle pending in queue - 0"));
//					        	i= new ImageVerification();
//					    	}
//					    	else
//					    	{
			random = new Random();
			int randomNumber = random.nextInt(PaymentTypes);

			int randomNumber1 = random.nextInt(3);
			SetProfilerImage s;
			switch (randomNumber) {
			case 0: // cash-cash

				driver.getKeyboard().sendKeys(Keys.ENTER);
				logger.info("Cash payement type  selected....");
				CashTransaction.info("Cash payement type  selected....");

				for (Map.Entry<String, String> entry : PaymentSubType.entries()) {
					String key = entry.getKey();
					if (key.contains("CASH")) {

						if (PaymentSubType.get(key).size() == 1) {
							driver.getKeyboard().sendKeys(Keys.ENTER);
							logger.info("cash subtype is selected");
							CashTransaction.info("cash subtype is selected");
//						                		break;
//						                		driver.getKeyboard().sendKeys(Keys.ENTER);
						}
					} else {
						String value = entry.getValue();
						System.out.println("Key: " + key + ", Value: " + value);
					}

				}

				break;
			case 1: // Card
//						        	CashTransaction.arrowDownZeroTime();
				arrowDownOneTime();
				driver.getKeyboard().sendKeys(Keys.ENTER);
				logger.info("Card payment type is selected....");
				CashTransaction.info("Card payment type is selected....");
				for (Map.Entry<String, String> entry : PaymentSubType.entries()) {
					String key = entry.getKey();
					if (key.contains(PaymentType.get(1))) {

						if (PaymentSubType.get(key).size() == 1) {
							driver.getKeyboard().sendKeys(Keys.ENTER);
//						                		driver.getKeyboard().sendKeys(Keys.ENTER);
						}
					} else {
						String value = entry.getValue();
						logger.info("Key: " + key + ", Value: " + value);
					}

				}
				for (int i = 0; i < keysArraygetPaymentTypeIsReference.length; i++) {
					String key = keysArraygetPaymentTypeIsReference[i];
					if (key.contains(PaymentType.get(1))) {
//						   				parent=driver.getWindowHandle();
//								    	mainWindowToCurrentWindow(driver, parent);
						ExplicitWait(By.xpath("//Text[@Name='Enter Refernce Number'][@AutomationId='lblRef']"));
						driver.getKeyboard().sendKeys(VRN.substring(6));
						driver.getKeyboard().sendKeys(Keys.ENTER);
						if (Is_Card_Ref.contains("Y")) {
							driver.getKeyboard().sendKeys(VRN.substring(6));
							driver.getKeyboard().sendKeys(Keys.ENTER);
						}

					}
				}
				parent = driver.getWindowHandle();

				break;

			case 2: // wallet
//						        	
				arrowDownTwoTime();
				driver.getKeyboard().sendKeys(Keys.ENTER);
				switch (randomNumber1) {
				case 0:
					arrowDownOneTime();
					driver.getKeyboard().sendKeys(Keys.ENTER);
					logger.info("Paytm Wallet payment type is selected....");
					CashTransaction.info("Paytm Wallet payment type is selected....");
//						        			                          Enter Refernce Number
					driver.findElementByName("Enter Refernce Number").sendKeys(VRN.substring(6));
					logger.info("Reference number is entered");
					CashTransaction.info("Paytm Wallet payment type is selected....");
					driver.getKeyboard().sendKeys(Keys.ENTER);
					System.out.println("wallet Paytm");

					break;
				case 1:
					arrowDownTwoTime();
					driver.getKeyboard().sendKeys(Keys.ENTER);
					logger.info("Paytm Wallet payment type is selected....");
					CashTransaction.info("Paytm Wallet payment type is selected....");
					driver.findElementByName("Enter Refernce Number").sendKeys(VRN.substring(6));
					logger.info("Reference number is entered");
					CashTransaction.info("Reference number is entered");
					driver.getKeyboard().sendKeys(Keys.ENTER);
//									        System.out.println("Wallet");

					break;
				case 2:
					arrowDownZeroTime();
					driver.getKeyboard().sendKeys(Keys.ENTER);
					logger.info("Paytm Wallet payment type is selected....");
					CashTransaction.info("Paytm Wallet payment type is selected....");
					driver.findElementByName("Enter Refernce Number").sendKeys(VRN.substring(6));
					logger.info("Reference number is entered");
					CashTransaction.info("Reference number is entered");
					driver.getKeyboard().sendKeys(Keys.ENTER);
//									    	System.out.println("Wallet");

					break;
				default:
					System.out.println("Invalid random number generated. inside");

				}
				break;
			default:
//						             System.out.println("Invalid random number generated. outside");

			}
			parent = driver.getWindowHandle();
			mainWindowToCurrentWindow(driver, parent);
			ExplicitWait(By.name("Vehicle pending in queue - 1"));
			logger.info("Barrier up time");
			CashTransaction.info("Barrier up time");
//					        	System.out.println(getdata.getAVCData(Vclass));
					        	PC_Send obj=new PC_Send();
					        	logger.info("PC Send Time");
					        	CashTransaction.info("PC Send Time");
					        	COM_Setup.COMAVCSetup99();
//			logger.info(getdata.getAVCData(Vclass) + " :outputstream ");
//						    	COM_Setup.COMAVCSetup(Vclass);
//						    	Thread.sleep(3000);

			
			logger.info(Vclass + " AVC is send");
			CashTransaction.info(Vclass + " AVC is send");
			logger.info(Vclass + " Barrier is Down");
			CashTransaction.info(Vclass + " Barrier Down");
			ExplicitWait(By.name("Vehicle pending in queue - 0"));
			logger.info("Vehicle pending in queue - 0");
			CashTransaction.info("Vehicle pending in queue - 0");
			i = new ImageVerification();
			s = new SetProfilerImage(Vclass);
			BaseClass.mainWindowToCurrentWindow(driver, driver.getWindowHandle());
//					    	}

		}
//			}
//			catch(org.openqa.selenium.TimeoutException c)
//			{
//				
//				logger.warn("AVC is unable to send or somthing went wrong...");
//				driver.getKeyboard().sendKeys(Keys.ESCAPE);
//				driver.getKeyboard().sendKeys(Keys.ESCAPE);
//				driver.getKeyboard().sendKeys(Keys.chord(Keys.CONTROL,Keys.END));
//				driver.getKeyboard().sendKeys(Keys.chord(Keys.CONTROL,Keys.END));
//			}

	}

	public static void arrowDownOneTime() {
		driver.getKeyboard().sendKeys(Keys.ARROW_DOWN);
	}

	public static void arrowDownTwoTime() {
		driver.getKeyboard().sendKeys(Keys.ARROW_DOWN);
		driver.getKeyboard().sendKeys(Keys.ARROW_DOWN);
	}

	public static void arrowDownZeroTime() {
		driver.getKeyboard().sendKeys(Keys.ARROW_UP);
	}

}
