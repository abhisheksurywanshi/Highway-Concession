package toll.tcm.MOPs;

import java.awt.AWTException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import net.bytebuddy.implementation.bind.annotation.Super;
import toll.tcm.Hardware.*;
import toll.tcm.testCases.*;
import toll.tcm.utilities.*;


public class PaidConvoy extends BaseClass {
	static Logger logger=LogManager.getLogger(PaidConvoy.class);

	public static void main(String[] args) {
		String a="Enter number of tow transaction.";
	}

	public static void paidConvoy(int trNo) throws IOException, InterruptedException, SQLException, AWTException {
		
		GetAVCData getdata = new GetAVCData();
		driver.getKeyboard().sendKeys(Keys.PAGE_UP);
		driver.findElementByName("Yes").click(); // Enter number of paid Convoy transaction.
		driver.findElementByName("Enter number of paid Convoy transaction.").sendKeys(String.valueOf(trNo));
		driver.getKeyboard().sendKeys(Keys.ENTER);
		List<String> avcAdd = new ArrayList<String>();
		for (int i = 1; i <= trNo; i++) {

//					random = new Random();
//					int VRNStart = 1;
//			        int VRNEnd = 9999;
//			        int randomVRN = random.nextInt(VRNEnd - VRNStart + 1) + VRNStart;
			int randomVRN = BaseClass.randomGenerator(1, 9999);
			DecimalFormat fourdigits = new DecimalFormat("0000");
			System.out.println(fourdigits.format(randomVRN));
			String FourDigitVRN = fourdigits.format(randomVRN);
			 RandomClassSelector.getSchedualVehicleKey();
			    RandomOtherCashKeySelector c= new RandomOtherCashKeySelector();
			    logger.info("Class selection TIme");
			    Thread.sleep(500);
	    		if(IsWimAvailable.contains("Y"))
	    		{
	    			SelectRandomSubClass.getRandomSelection();
	    		}
			String Vclass = driver.findElementByAccessibilityId("lblClass").getText().toString();
			avcAdd.add(Vclass);
			if (Vclass.equals("Vehicle Class")) {

				driver.findElementByAccessibilityId("txtVehicleNo").clear();

			} else {
				driver.findElementByAccessibilityId("txtVehicleNo").sendKeys("MH01" + "AA" + FourDigitVRN);
				driver.getKeyboard().sendKeys(Keys.ENTER);
				random = new Random();
				int randomNumber = random.nextInt(PaymentTypes);
				System.out.println("random number :" + randomNumber);
				int randomNumber1 = random.nextInt(3);
				System.out.println("random number1 :" + randomNumber1);
				switch (randomNumber) {
				case 0: // cash-cash
					CashTransaction.arrowDownOneTime();
					driver.getKeyboard().sendKeys(Keys.ENTER);
					System.out.println("Cash");

					break;
				case 1: // Card
//						        	CashTransaction.arrowDownZeroTime();
					driver.getKeyboard().sendKeys(Keys.ENTER);
					System.out.println("card");

					break;

				case 2: // wallet
//						        	
					CashTransaction.arrowDownTwoTime();
					driver.getKeyboard().sendKeys(Keys.ARROW_RIGHT);
					switch (randomNumber1) {
					case 0:
						CashTransaction.arrowDownOneTime();
						driver.getKeyboard().sendKeys(Keys.ENTER);
						driver.findElementByName("Enter Refernce Number").sendKeys(String.valueOf(randomVRN));
						driver.getKeyboard().sendKeys(Keys.ENTER);
						System.out.println("wallet");

						break;
					case 1:
						CashTransaction.arrowDownTwoTime();
						driver.getKeyboard().sendKeys(Keys.ENTER);
						driver.findElementByName("Enter Refernce Number").sendKeys(String.valueOf(randomVRN));
						driver.getKeyboard().sendKeys(Keys.ENTER);
						System.out.println("Wallet");

						break;
					case 2:
						CashTransaction.arrowDownZeroTime();
						driver.getKeyboard().sendKeys(Keys.ENTER);
						driver.findElementByName("Enter Refernce Number").sendKeys(String.valueOf(randomVRN));
						driver.getKeyboard().sendKeys(Keys.ENTER);
						System.out.println("Wallet");

						break;
					default:
						System.out.println("Invalid random number generated.");

					}
				default:
					System.out.println("Invalid random number generated.");
					
					
				
				}
				
			}
			
		}
		int AfterPendingAVC;
		int BerforePendingAVC=trNo;
		for(int l=0,j=trNo-1;j>=0;j--)
		{
			
			ExplicitWait(By.name("Vehicle pending in queue - "+BerforePendingAVC));
			outputStreamForAVC.write(getdata.getAVCData(avcAdd.get(l)).getBytes());
			l++;
			AfterPendingAVC=BerforePendingAVC-1;
			i= new ImageVerification();
			ExplicitWait(By.name("Vehicle pending in queue - "+AfterPendingAVC));
			
			BerforePendingAVC=AfterPendingAVC;
		}
	}
}
