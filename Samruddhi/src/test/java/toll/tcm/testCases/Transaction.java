package toll.tcm.testCases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;



import gnu.io.NoSuchPortException;
import io.appium.java_client.windows.WindowsElement;
import toll.tcm.Database.Is_OverWeight_Applicable;
import toll.tcm.Database.KeyMapping;
import toll.tcm.Hardware.*;
import toll.tcm.MOPs.*;
import toll.tcm.testData.SequentialExcelTransaction;
import toll.tcm.testData.VehicleCDWiseTransaction;
import toll.tcm.utilities.DefaultTransactionNumber;
import toll.tcm.utilities.*;


public class Transaction extends BaseClass
{

	
	@Test//(dataProvider="main")
	public void main() throws InterruptedException, NoSuchPortException, Exception
	{   
		
		
		
//		for(int i=0;i<ManualTagPunch.GetManualTagPunchVRN().size();i++)
//		{
//			System.out.println(ManualTagPunch.GetManualTagPunchVRN().size());
//			ManualTagPunch m=new ManualTagPunch();
//		}
//		System.out.println("length:"+keysArrayForIsOverWeightApplicable.length);
//		for(int i=0;i<keysArrayForIsOverWeightApplicable.length;i++)
//		{
//			   String key = keysArrayForIsOverWeightApplicable[i];
//	           String value =String.valueOf(Is_OverWeight_Applicable.isOverWeightApplicable ().get(key)) ;
//	           OtherCashKeys.put(key, value);
//	           logger.info("Class: " + key + ", MOPs " + value);
//		}
		//		VehicleCDWiseTransaction v =new VehicleCDWiseTransaction();
//			
//		
//		LastTransaction= DefaultTransactionNumber.getDefaultTransationNumber();
//		System.out.println(":::::::::c "+LastTransaction);
		for(int i=1;i<=10;i++)
		{
//
//		ManualWeight.manualWeightTransaction(BaseClass.randomGenerator(0, 61001));
//		SequentialWeightTransaction.SequentialWeightTransaction();
//		ExemptTransaction.randomExempt();
//		driver.getKeyboard().sendKeys(Keys.F5);
//		driver.findElementByAccessibilityId("txtVehicleNo").sendKeys("MH01"+"AA5255");	
//		ManualWeight.manualInsertWeight(50000);
//		 String Vclass=driver.findElementByAccessibilityId("lblClass").getText().toString();
//		 System.out.println("DDDD");
//		ManualWeight.OverWeightLogic(Vclass, 50000);
//		System.out.println("DDDD");
//			WeightInsert.insertWeight();
//			CashTransaction.randomCashTransaction();		
//			ExemptTransaction.randomExempt(); 
//			FreeConvoy.freeConvoy(1);
			
			ExemptTransaction.randomExempt(); 
//			Violation.randomViolation();		
//			Violation.randomViolation();			
//		    GetTag g=new GetTag();
//		    WeightInsert.insertWeight();
//		    g.getTagData();
//		    PaidConvoy.paidConvoy(3);
//		    NSV.getNonSchedualVehicleKey();
//		    Tow.tow(3);
		    
	}
		
		
//		driver.findElementByAccessibilityId("txtVehicleNo").sendKeys("MH01"+"AA");	
//		driver.findElementByAccessibilityId("txtVehicleNo").clear();
		
		
		
		
		
		
//		ManualWeight.manualInsertWeight(50000);
//		ManualWeight.insert0Weight(); 
//		ManualWeight.deleteWeight();
//		ManualWeight.manualWeightTransaction(60000); 
//			SequentialExcelTransaction.inconsequentExcelTransaction();	
//					Boolean v;
			
//			
			
			
			
			
//			logger.info("after class selection");
//			 try	
//			    {
//			    	if(driver.findElementByName("Select sub Vehicle Class For WIM").isDisplayed())
//			    	{
//			    		logger.info("after subclass class selection");
//			    		SelectRandomSubClass.getRandomSelection();
//			    	}
//			    		
//			    	
//			    }
//			    catch( r)
//			    {
//			    	logger.info("wimsub class catch block");
//			    }
//			 driver.findElementByAccessibilityId("txtVehicleNo").sendKeys("MH01"+"AA"+"1542");
//			 driver.getKeyboard().sendKeys(Keys.ENTER);
//			 mainWindowToCurrentWindow(driver, driver.getWindowHandle());
//			if(driver.findElementByName("Select Payment Mode").isDisplayed())
//			{
//				System.out.println("yes");
//			}
//			 
//			 
//			 driver.getKeyboard().sendKeys(Keys.ENTER);
//			 PC_Send obj1=new PC_Send();
//	        	logger.info("PC Send Time");
//		    	outputStreamForAVC.write(getdata.getAVCData("TR").getBytes());
		    	
			
				 
//				 byte[] responseBytes = new byte[1024];
//		         int bytesRead = inputStreamForAVC.read(responseBytes);
		         
		         // Convert the received bytes to a string
//		         String response = new String(responseBytes, 0, bytesRead);
//		         System.out.println(response);
//			 mainWindowToCurrentWindow(driver, driver.getWindowHandle());

			 
		
		
		
//		for(int i=1;i<=10;i++)
//		{
//			VehicleCDWiseTransaction v=new VehicleCDWiseTransaction();	
//			System.out.println("---------------");
	//		VehicleCDWiseTransaction.excelTransactionSelection("09","07");
//			VehicleCDWiseTransaction.excelTransactionSelection("07","10");
//			WeightInsert.insertWeight();
	//		Robot robot = new Robot();
	//		robot.keyPress(NSVSelector(Class))
//			WindowsElement element;    
//			element.send_keys(Keys.CONTROL + Keys.DELETE)
//			WeightInsert.insertWeight();
			
//			CashTransaction.cashTransaction();
//			ExemptTransaction.randomExempt();
//			FreeConvoy.freeConvoy(1);
//			FreeConvoy.freeConvoy(1);
//			FreeConvoy.freeConvoy(1);
//			GetTag t =new GetTag();
			for(int i=1;i<=50;i++)
			{
//				t.getTagData();
//				Violation.randomViolation();
//				FreeConvoy.freeConvoy(1);tin	
//				WeightInsert.insertWeight();ut
//				CashTransaction.cashTransaction();
//				NSV.getNonSchedualVehicleKey();
//				RandomClassSelector.getSchedualVehicleKey();
//				driver.getKeyboard().sendKeys(Keys.ESCAPE);
			}
//			DecimalFormat twodigits = new DecimalFormat("00");
//			for(int i=4;i<=15;i++)
//			
//				String b=twodigits.format(i);
////				VehicleCDWiseTransaction.excelTransactionSelection("09","15");
//				VehicleCDWiseTransaction.excelTransactionSelection("09",b);
////				VehicleCDWiseTransaction.excelTransactionSelection("09","05");
//			}
			
//		System.out.println("----------------------------------------------------------------hyefgfh-------");
			
			
//			NSV.toNSV();
//			PaidConvoy.paidConvoy(4);9991212
//			Tow.tow(4);
//			Violation.randomViolation();
//			Violation.randomViolation();
//			SequentialExcelTransaction s= new SequentialExcelTransaction();
			
		}
		}
//	@Test212
//	public void ETC() throws Exception
//	{
//		GetTag t =new GetTag();
//		t.getTagData();
//		
//	}
//}