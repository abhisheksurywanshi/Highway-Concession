package toll.tcm.testCases;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.IOException;
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
import toll.tcm.Database.Mapping;
import toll.tcm.Hardware.*;
import toll.tcm.MOPs.*;
import toll.tcm.testData.ManualTransactionDataProvider;
import toll.tcm.testData.SequentialExcelTransaction;
import toll.tcm.testData.VehicleCDWiseTransaction;
import toll.tcm.utilities.DefaultTransactionNumber;
import toll.tcm.utilities.*;


public class Transaction extends BaseClass 
{

	

	@Test//(dataProvider="main")
	public void main() throws InterruptedException, NoSuchPortException, Exception
	{   
		

		for(int i=1;i<=1;i++)
		{
//
//		ManualWeight.manualWeightTransaction(BaseClass.randomGenerator(0, 61001));
//		SequentialWeightTransaction.SequentialWeightTransaction();
		ExemptTransaction.randomExempt();
//		driver.getKeyboard().sendKeys(Keys.F5);
//		driver.findElementByAccessibilityId("txtVehicleNo").sendKeys("MH01"+"AA5255");	
//		ManualWeight.manualInsertWeight(50000);
//		 String Vclass=driver.findElementByAccessibilityId("lblClass").getText().toString();
//		 System.out.println("DDDD");
//		ManualWeight.OverWeightLogic(ass, 50000);
//		System.out.println("DDDD");
//			WeightInsert.insertWeight();
			CashTransaction.randomCashTransaction();		
//			ExemptTransaction.randomExempt(); 
			FreeConvoy.freeConvoy(1);	
//			
//			ExemptTransaction.randomExempt(); 
			Violation.randomViolation();		
//			Violation.randomViolation();	
//			FreeConvoy.freeConvoy(1);	
		    GetTag g=new GetTag();
//		    WeightInsert.insertWeight();
		    g.getTagData();
//		    PaidConvoy.paidConvoy(3);
		    NSV.getNonSchedualVehicleKey();
		    
//		    Tow.tow(3);
		    
	}
		
		

		
		
		

			
//		System.out.println("----------------------------------------------------------------hyefgfh-------");
			
			
//			NSV.toNSV();
//			PaidConvoy.paidConvoy(4);9991212
//			Tow.tow(4);
//			Violation.randomViolation();
//			Violation.randomViolation();
//			SequentialExcelTransaction s= new SequentialExcelTransaction();
			
//			mainWindowToCurrentWindow(driver, driver.getWindowHandle());
//			thread.start();
		}
//	@Test(dataProvider="ManualTransaction",dataProviderClass=ManualTransactionDataProvider.class)
	public void manualTagPunchTransaction(String Vehicle_No,String Response) throws AWTException, IOException, InterruptedException
	{
		driver.findElementByAccessibilityId("txtVehicleNo").sendKeys(Vehicle_No);
		System.out.println(Vehicle_No+" "+Response);
		Robot robot = new Robot();
		robot.keyPress(92);
        robot.keyRelease(92);
        ExplicitWait(By.name("Vehicle pending in queue - 1"));
        PC_Send p=new PC_Send();
    	Thread.sleep(500);
        COM_Setup.COMAVCSetup99();
        ExplicitWait(By.name("Vehicle pending in queue - 0"));
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