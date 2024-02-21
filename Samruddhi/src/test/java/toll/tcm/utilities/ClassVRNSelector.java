package toll.tcm.utilities;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import io.appium.java_client.windows.WindowsElement;
import toll.tcm.Database.KeyMapping;
import toll.tcm.Hardware.GetAVCData;
import toll.tcm.Hardware.ImageVerification;
import toll.tcm.MOPs.*;
import toll.tcm.testCases.*;
import toll.tcm.testData.SequentialExcelTransaction;
public class ClassVRNSelector extends BaseClass {
	
	
	static Logger logger=LogManager.getLogger(ClassVRNSelector.class);

	
	public ClassVRNSelector(String Class,String VRN) throws InterruptedException, IOException, AWTException, SQLException {
		getdata = new GetAVCData();
		randomVRN = BaseClass.randomGenerator(1, 9999);
		DecimalFormat fourdigits = new DecimalFormat("0000");
		System.out.println(fourdigits.format(randomVRN));
		FourDigitVRN = fourdigits.format(randomVRN);
		
		Robot robot = new Robot();
//		String[] keysArrayVehicleCode = KeyMapping.getVehicleCodeWithClass().keySet().toArray(new String[0]);
		for (int i = 0; i < keysArrayVehicleCode.length; i++) {
            String key = keysArrayVehicleCode[i];
            String value = KeyMapping.getVehicleCodeWithASCII().get(key);
            ClassCodes.put(key, value);
            System.out.println("Class Alias: " +value  + ", Code: " +key );
            System.out.println("KEY----"+key);
            System.out.println("Class----"+Class);
            if (Class.contains(key))
            {
            	
            	int IntKey=Integer.parseInt(value);
            	System.out.println("KEY----"+IntKey);
        		robot.keyPress(IntKey);
                robot.keyRelease(IntKey);
            }
            
        }
//		if (Class.contains("04")) {
//			driver.getKeyboard().sendKeys(Keys.F1);
//		} else if (Class.contains("05")) {
//			driver.getKeyboard().sendKeys(Keys.F2);
//		} else if (Class.contains("07")) {
//			driver.getKeyboard().sendKeys(Keys.F3);
//		} else if (Class.contains("10")) {
//			driver.getKeyboard().sendKeys(Keys.F4);
//		} else if (Class.contains("11")) {
//			driver.getKeyboard().sendKeys(Keys.F5);
//		} else if (Class.contains("12")) {
//			driver.getKeyboard().sendKeys(Keys.F6);
//		} else if (Class.contains("13")) {
//			driver.getKeyboard().sendKeys(Keys.F7);
//		} else if (Class.contains("14")) {
//			driver.getKeyboard().sendKeys(Keys.F7);
//		} else {
//			driver.getKeyboard().sendKeys(Keys.F9);
//		}
		
	}

	public static void paymentSelection(String VRN) throws IOException, Exception
	{
		String Vclass = driver.findElementByAccessibilityId("lblClass").getText().toString();
		logger.info(Vclass.toString() + " Key is selected...");
		if (Vclass.equals("Vehicle Class")) {

			driver.findElementByAccessibilityId("txtVehicleNo").clear();
			logger.info("Vehicle number cleared..");
		} else {
			driver.findElementByAccessibilityId("txtVehicleNo").sendKeys(VRN);
			logger.info("Random VRN is Generated :" + FourDigitVRN);
			driver.getKeyboard().sendKeys(Keys.ENTER);
			random = new Random();
			int randomNumber = random.nextInt(PaymentTypes);

			int randomNumber1 = random.nextInt(3);

			switch (randomNumber) {
			case 0: // cash-cash
				CashTransaction.arrowDownOneTime();
				driver.getKeyboard().sendKeys(Keys.ENTER);
				logger.info("Cash transaction is selected....");
				ExplicitWait(By.name("Vehicle pending in queue - 1"));
				outputStreamForAVC.write(getdata.getAVCData(Vclass).getBytes());
				logger.info(Vclass + "AVC is send");

				ExplicitWait(By.name("Vehicle pending in queue - 0"));
				i = new ImageVerification();
				break;
			case 1: // Card
//	        	CashTransaction.arrowDownZeroTime();
				driver.getKeyboard().sendKeys(Keys.ENTER);
				logger.info("Card transaction is selected....");
				ExplicitWait(By.name("Vehicle pending in queue - 1"));
				outputStreamForAVC.write(getdata.getAVCData(Vclass).getBytes());
				logger.info(Vclass + "AVC is send");
				ExplicitWait(By.name("Vehicle pending in queue - 0"));
				i = new ImageVerification();
				break;

			case 2: // wallet
//	        	
				CashTransaction.arrowDownTwoTime();
				driver.getKeyboard().sendKeys(Keys.ARROW_RIGHT);
				switch (randomNumber1) {
				case 0:
					CashTransaction.arrowDownOneTime();
					driver.getKeyboard().sendKeys(Keys.ENTER);
					logger.info("Paytm Wallet transaction is selected....");
					driver.findElementByName("Enter Refernce Number").sendKeys(String.valueOf(randomVRN));
					logger.info("Reference number is entered");
					driver.getKeyboard().sendKeys(Keys.ENTER);
					System.out.println("wallet Paytm");
					ExplicitWait(By.name("Vehicle pending in queue - 1"));
					outputStreamForAVC.write(getdata.getAVCData(Vclass).getBytes());
					logger.info(Vclass + "AVC is send");

					ExplicitWait(By.name("Vehicle pending in queue - 0"));
					i = new ImageVerification();
					break;
				case 1:
					CashTransaction.arrowDownTwoTime();
					driver.getKeyboard().sendKeys(Keys.ENTER);
					logger.info("Paytm Wallet transaction is selected....");
					driver.findElementByName("Enter Refernce Number").sendKeys(String.valueOf(randomVRN));
					logger.info("Reference number is entered");
					driver.getKeyboard().sendKeys(Keys.ENTER);
					System.out.println("Wallet");
					ExplicitWait(By.name("Vehicle pending in queue - 1"));
					outputStreamForAVC.write(getdata.getAVCData(Vclass).getBytes());
					logger.info(Vclass + "AVC is send");

					ExplicitWait(By.name("Vehicle pending in queue - 0"));
					i = new ImageVerification();
					break;
				case 2:
					CashTransaction.arrowDownZeroTime();
					driver.getKeyboard().sendKeys(Keys.ENTER);
					logger.info("Paytm Wallet transaction is selected....");
					driver.findElementByName("Enter Refernce Number").sendKeys(String.valueOf(randomVRN));
					logger.info("Reference number is entered");
					driver.getKeyboard().sendKeys(Keys.ENTER);
					System.out.println("Wallet");
					ExplicitWait(By.name("Vehicle pending in queue - 1"));
					outputStreamForAVC.write(getdata.getAVCData(Vclass).getBytes());
					logger.info(Vclass + "AVC is send");
					ExplicitWait(By.name("Vehicle pending in queue - 0"));
					i = new ImageVerification();
					break;
				default:
					System.out.println("Invalid random number generated. inside");

				}
				break;
			default:
				System.out.println("Invalid random number generated. outside");

			}

		}
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
