package toll.tcm.MOPs;

import java.awt.AWTException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import toll.tcm.Hardware.*;
import toll.tcm.MOPs.RandomOtherCashKeySelector;
import toll.tcm.testCases.*;
import toll.tcm.utilities.*;

public class Tow extends BaseClass{
	static Logger logger=LogManager.getLogger(Tow.class);

	public static void tow(int trNo) throws IOException, SQLException, AWTException, InterruptedException {
		
		GetAVCData getdata = new GetAVCData();
		driver.getKeyboard().sendKeys(Keys.PAGE_DOWN);
		driver.findElementByName("Yes").click(); // Enter number of paid Convoy transaction.
		String parent=driver.getWindowHandle();
    	mainWindowToCurrentWindow(driver, parent);
		driver.findElementByName("Enter number of tow transaction.").click();
		driver.findElementByName("Enter number of tow transaction.").sendKeys(String.valueOf(trNo));
		driver.getKeyboard().sendKeys(Keys.ENTER);
    	mainWindowToCurrentWindow(driver, parent);
		List<String> avcAdd = new ArrayList<String>();
		for (int i = 1; i <= trNo; i++) {

//					random = new Random();
//					int VRNStart = 1;
//			        int VRNEnd = 9999;
//			        int randomVRN = random.nextInt(VRNEnd - VRNStart + 1) + VRNStart;
			 RandomClassSelector.getSchedualVehicleKey();
			  RandomOtherCashKeySelector c= new RandomOtherCashKeySelector();
		    logger.info("Class selection TIme");
		     parent=driver.getWindowHandle();
		     mainWindowToCurrentWindow(driver, parent);
//		    		Thread.sleep(500);
		    		if(IsWimAvailable.contains("Y"))
		    		{
		    			SelectRandomSubClass.getRandomSelection();
		    		}
			String Vclass = driver.findElementByAccessibilityId("lblClass").getText().toString();
			avcAdd.add(Vclass);
			if (Vclass.equals("Vehicle Class")) {

				driver.findElementByAccessibilityId("txtVehicleNo").clear();

			} else {
				String VRN=RandomVehicleNumberGenerator.generateRandomVehicleNumber();
		    	driver.findElementByAccessibilityId("txtVehicleNo").sendKeys(VRN);	
		    	logger.info("Random VRN is Generated :"+VRN);
		    	
		    	driver.getKeyboard().sendKeys(Keys.ENTER);
		    	parent=driver.getWindowHandle();
		    	mainWindowToCurrentWindow(driver, parent);
		    	try 
		    	{
		    		ExplicitWait(By.name("Select Payment Mode"));
		    		logger.info("payment mode selection time");
		    		
				     driver.findElementByName("Select Payment Mode").click();
		    	}catch(org.openqa.selenium.TimeoutException e)
		    	{
		    		ExplicitWait(By.name("Select Payment Mode"));
		    		logger.info("payment mode selection time");
		    		
				       driver.findElementByName("Select Payment Mode").click();
		    	}
		    	random =new Random();
		    	int randomNumber = random.nextInt(PaymentTypes);
		    				    	
		    	int randomNumber1 = random.nextInt(3);
		    	SetProfilerImage s;
				switch (randomNumber) {
		        case 0: //cash-cash
		        	
		        	driver.getKeyboard().sendKeys(Keys.ENTER);
		        	logger.info("Cash payement type  selected....");
		        	;
		        	
		        	for (Map.Entry<String, String> entry : PaymentSubType.entries()) {
		                String key = entry.getKey();
		                if(key.contains("CASH"))
		                {
		                	
		                	if(PaymentSubType.get(key).size()==1)
		                	{
		                		driver.getKeyboard().sendKeys(Keys.ENTER);
		                		logger.info("cash subtype is selected");
		                		
//		                		break;
//		                		driver.getKeyboard().sendKeys(Keys.ENTER);
		                	}
		                }
		                else
		                {
		                	String value = entry.getValue();
		                    System.out.println("Key: " + key + ", Value: " + value);
		                }
		                
		            }
		        	
		        	
		            break;
		        case 1: //Card
//		        	CashTransaction.arrowDownZeroTime();
		        	arrowDownOneTime();
		        	driver.getKeyboard().sendKeys(Keys.ENTER);
		        	logger.info("Card payment type is selected....");
		        	
		        	for (Map.Entry<String, String> entry : PaymentSubType.entries()) {
		                String key = entry.getKey();
		                if(key.contains(PaymentType.get(1)))
		                {
		                	
		                	if(PaymentSubType.get(key).size()==1)
		                	{
		                		driver.getKeyboard().sendKeys(Keys.ENTER);
//		                		driver.getKeyboard().sendKeys(Keys.ENTER);
		                	}
		                }
		                else
		                {
		                	String value = entry.getValue();
		                    System.out.println("Key: " + key + ", Value: " + value);
		                }
		                
		            }
		        	for (int j = 0; j < keysArraygetPaymentTypeIsReference.length; j++)
		        	{
		   			 String key = keysArraygetPaymentTypeIsReference[j];
		   			 if(key.contains(PaymentType.get(1)))
		   			 {
//		   				parent=driver.getWindowHandle();
//				    	mainWindowToCurrentWindow(driver, parent);
		   				ExplicitWait(By.xpath("//Text[@Name='Enter Refernce Number'][@AutomationId='lblRef']"));
			        	driver.getKeyboard().sendKeys(VRN.substring(6));
			        	driver.getKeyboard().sendKeys(Keys.ENTER);
			        	if(Is_Card_Ref.contains("Y"))
			        	{
			        		driver.getKeyboard().sendKeys(VRN.substring(6));
			        		driver.getKeyboard().sendKeys(Keys.ENTER);
			        	}
			        	
			        	
		   			 }
		        	}
		        	parent=driver.getWindowHandle();
			    	
		        	
		            break;
		        	
		        case 2: //wallet
//		        	
		            arrowDownTwoTime();
		        	driver.getKeyboard().sendKeys(Keys.ENTER);
		        		switch(randomNumber1)
		        		{
		        		case 0:
		        			arrowDownOneTime();
		        			driver.getKeyboard().sendKeys(Keys.ENTER);
		        			logger.info("Paytm Wallet payment type is selected....");
		        			
//		        			                          Enter Refernce Number
		        			driver.findElementByName("Enter Refernce Number").sendKeys(VRN.substring(6));
		        			logger.info("Reference number is entered");
		        			
		        			driver.getKeyboard().sendKeys(Keys.ENTER);
					    	System.out.println("wallet Paytm");
				        	
				            break;
		        		case 1:
		        			arrowDownTwoTime();
		        			driver.getKeyboard().sendKeys(Keys.ENTER);
		        			logger.info("Paytm Wallet payment type is selected....");
		        			
		        			driver.findElementByName("Enter Refernce Number").sendKeys(VRN.substring(6));
		        			logger.info("Reference number is entered");
		        			
		        			driver.getKeyboard().sendKeys(Keys.ENTER);
//					        System.out.println("Wallet");
					       
					    	
				            break;
		        		case 2:
		        			arrowDownZeroTime();
		        			driver.getKeyboard().sendKeys(Keys.ENTER);
		        			logger.info("Paytm Wallet payment type is selected....");
		        			
		        			driver.findElementByName("Enter Refernce Number").sendKeys(VRN.substring(6));
		        			logger.info("Reference number is entered");
		        			
		        			driver.getKeyboard().sendKeys(Keys.ENTER);
//					    	System.out.println("Wallet");
				        	
					    	
				            break;
		        		default:
				             System.out.println("Invalid random number generated. inside");
		        			
		        		}
		        		break;
		        default:
//		             System.out.println("Invalid random number generated. outside");
		    	
		    	
		    	
		    	
		    		
		    	
		    	
		    	}
				
			}
			
		}
		int AfterPendingAVC;
		int BerforePendingAVC=trNo;
		for(int i=0,j=trNo-1;j>=0;j--)
		{
			parent=driver.getWindowHandle();
	    	mainWindowToCurrentWindow(driver, parent);
	    	
			ExplicitWait(By.name("Vehicle pending in queue - "+BerforePendingAVC));
			COM_Setup.COMAVCSetup99();
			logger.info(i+" AVC is send");
			i++;
			AfterPendingAVC=BerforePendingAVC-1;
			ExplicitWait(By.name("Vehicle pending in queue - "+AfterPendingAVC));
			SetProfilerImage s=new SetProfilerImage(avcAdd.get(i));
			BerforePendingAVC=AfterPendingAVC;
		}
	}
	public static void excelTow(String Class,int trNo,ArrayList VRN) throws IOException
	{
		GetAVCData getdata = new GetAVCData();
		driver.getKeyboard().sendKeys(Keys.PAGE_DOWN);
		driver.findElementByName("Yes").click(); // Enter number of paid Convoy transaction.
		driver.findElementByName("Enter number of tow transaction.").sendKeys(String.valueOf(trNo));
		driver.getKeyboard().sendKeys(Keys.ENTER);
		List<String> avcAdd = new ArrayList<String>();
		for (int i = 1; i <= trNo; i++) {
			
//					random = new Random();
//					int VRNStart = 1;
//			        int VRNEnd = 9999;
//			        int randomVRN = random.nextInt(VRNEnd - VRNStart + 1) + VRNStart;
//			int randomVRN = BaseClass.randomGenerator(1, 9999);
//			DecimalFormat fourdigits = new DecimalFormat("0000");
//			System.out.println(fourdigits.format(randomVRN));
//			String b = fourdigits.format(randomVRN);
//			Keys Class = RandomClassSelector.getRandomKeys();
//			driver.getKeyboard().sendKeys(Class);
//			if (Class == Keys.F6 || Class == Keys.F7) // tasawade
//			{
//				RandomClassSelector.subClassSelector(Class);
//				driver.getKeyboard().sendKeys(Keys.ENTER);
//			}
			String Vclass = driver.findElementByAccessibilityId("lblClass").getText().toString();
			avcAdd.add(Vclass);
			if (Vclass.equals("Vehicle Class")) {

				driver.findElementByAccessibilityId("txtVehicleNo").clear();

			} else {
				driver.findElementByAccessibilityId("txtVehicleNo").sendKeys((String)VRN.get(i));
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
		for(int i=0,j=trNo-1;j>=0;j--)
		{
			
			ExplicitWait(By.name("Vehicle pending in queue - "+BerforePendingAVC));
			outputStreamForAVC.write(getdata.getAVCData(avcAdd.get(i)).getBytes());
			i++;
			AfterPendingAVC=BerforePendingAVC-1;
			ExplicitWait(By.name("Vehicle pending in queue - "+AfterPendingAVC));
			BerforePendingAVC=AfterPendingAVC;
		}
	}
	public static void arrowDownOneTime()
	{
		driver.getKeyboard().sendKeys(Keys.ARROW_DOWN);
	}
	public static void arrowDownTwoTime()
	{
		driver.getKeyboard().sendKeys(Keys.ARROW_DOWN);
		driver.getKeyboard().sendKeys(Keys.ARROW_DOWN);
	}
	public static void arrowDownZeroTime()
	{
		driver.getKeyboard().sendKeys(Keys.ARROW_UP);			
	}
}
