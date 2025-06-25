package toll.tcm.MOPs;

import java.awt.AWTException;
import java.awt.Robot;
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
import org.openqa.selenium.NoSuchElementException;

import com.aventstack.extentreports.ExtentTest;

import toll.tcm.Database.DatabaseConnectivity;
import toll.tcm.Hardware.*;
import toll.tcm.testCases.*;
import toll.tcm.utilities.*;

public class Tow extends BaseClass{
	static Logger logger=LogManager.getLogger(Tow.class);
	public static void tow(int trNo) throws IOException, SQLException, AWTException, InterruptedException {
		ExtentTest Tow = extent.createTest("Tow");
		ExetentReportPass test;
//		GetAVCData getdata = new GetAVCData();
		driver.getKeyboard().sendKeys(Keys.PAGE_DOWN);
		test= new ExetentReportPass(Tow,"After Pressing Tow Button","Pop up is display for Tow Mode Start ","Operator Selection for Tow Mode Start Yes or No");

		driver.findElementByName("Yes").click(); // Enter number of paid Convoy transaction.
		 String parent = driver.getWindowHandle();
	        mainWindowToCurrentWindow(driver, parent);
		driver.findElementByName("Enter number of tow transaction.").sendKeys(String.valueOf(trNo));
		test= new ExetentReportPass(Tow,"After Pressing Tow Button","Pop up is display Enter number of tow transaction. "+trNo,"");
		driver.findElementByName("Enter number of tow transaction.").click();
		driver.getKeyboard().sendKeys(Keys.ENTER);
		parent = driver.getWindowHandle();
        mainWindowToCurrentWindow(driver, parent);
		try 
		{
			ExplicitWait(By.name("Tow mode started."));
			test= new ExetentReportPass(Tow,"After Starting Tow Mode","system message is display for Tow Mode Start ","Tow Mode is started");

		}
		catch(org.openqa.selenium.TimeoutException e)
		
		{
			ExplicitWait(By.name("Tow mode started."));
			test= new ExetentReportPass(Tow,"After Strarting Tow Mode","system message is display for Tow Mode Start ","Tow Mode is started");

		}
		List<String> avcAdd = new ArrayList<String>();
		List<Long> TLC_No = new ArrayList<Long>();
		Long LastTransaction=Long.valueOf(DatabaseConnectivity.getLatestTransaction());
		
		for (int i = 1; i <= trNo; i++) {
		
		
//		test = new ExetentReportPass(Tow, "Class Selection Time", "Class selection is null",
//				"This stage class selection is null");
		

//		driver.getKeyboard().sendKeys(Keys.F3);
		int ClassSelectionKey=RandomClassSelector.getSchedualVehicleKey();
		
		if (IsWimAvailable.contains("Y")) {
			SelectRandomSubClass.getRandomSelection();
		}
//		RandomOtherCashKeySelector c = new RandomOtherCashKeySelector();
		int otherclass=RandomOtherCashKeySelector.RandomOtherCashKeySelector();
		String Vclass = driver.findElementByAccessibilityId("lblClass").getText().toString();
		avcAdd.add(Vclass);
		String MOP = driver.findElementByAccessibilityId("lblMOP").getText().toString(); 
		
//		Robot robot = new Robot();
//		robot.keyPress(61);
//        robot.keyRelease(61);
        parent = driver.getWindowHandle();
        mainWindowToCurrentWindow(driver, parent);
        logger.info("ClassSelectionKey:"+otherclass);
        
        if(otherclass==61||otherclass==121)
        {
        	
        		Robot robot = new Robot();
        		robot.keyPress(61);
                robot.keyRelease(61);
        	try 
        	{
        		driver.findElementByName("Entry-Toll Sr. No :").click();
        	}
        	 catch(NoSuchElementException e)
        	{
        		 parent = driver.getWindowHandle();
        	        mainWindowToCurrentWindow(driver, parent);
        	        driver.findElementByName("Entry-Toll Sr. No :").click();
        	}
             System.out.println(KentTollMaster.size());
             int rand =randomGenerator(1, KentTollMaster.size());
             driver.findElementByXPath("//Edit[@AutomationId='txtExemptSequeneceNo']").sendKeys(""+rand);

      		test= new ExetentReportPass(Tow,"SubToll Selection ","Class selection is "+Vclass+"with subtoll key  ("+ASCIIToKeyboardValue.aSCIIToKeyboardValue(otherclass)+") and MOP is "+MOP,"This stage is for class selection");
     		driver.findElementByXPath("//Edit[@AutomationId='txtExemptSequeneceNo']").click();
             driver.getKeyboard().sendKeys(Keys.ENTER);
        }
       
// 		test= new ExetentReportPass(Tow,"SubToll Selection ","Class selection is "+Vclass+"with subtoll key  ("+ASCIIToKeyboardValue.aSCIIToKeyboardValue(otherclass)+") and MOP is "+MOP,"This stage is for class selection");

//		test= new ExetentReportPass(Tow,"Class is selected ","Class selection is "+Vclass+" ("+ASCIIToKeyboardValue.aSCIIToKeyboardValue(ClassSelectionKey)+") and MOP is "+MOP,"This stage is for class selection");



		
//		driver.switchTo().window(parent);

		

//		String Vclass = driver.findElementByAccessibilityId("lblClass").getText().toString();
		logger.info(Vclass.toString() + " Key is selected...");
//		Tow.info(Vclass.toString() + " Key is selected...");
		if (Vclass.equals("Vehicle Class")) {

			driver.findElementByAccessibilityId("txtVehicleNo").clear();
			logger.warn("Vehicle number cleared.. because class selection is null");
			Tow.warning("Vehicle number cleared.. because class selection is null");

			Thread.sleep(2000);
		} else {
			parent = driver.getWindowHandle();
	        mainWindowToCurrentWindow(driver, parent);
	        driver.findElementByAccessibilityId("lblToSubToll").click();
	        String FromSubToll= driver.findElementByAccessibilityId("lblToSubToll").getText().toString();
			test= new ExetentReportPass(Tow,"Class is selected ","Class selection is "+Vclass+" ("+ASCIIToKeyboardValue.aSCIIToKeyboardValue(ClassSelectionKey)+") and MOP is "+MOP+" with to sub toll "+FromSubToll,"This stage is for class selection");

			PaymentModeSelector paymentmode = new PaymentModeSelector(Tow);

			parent = driver.getWindowHandle();
			mainWindowToCurrentWindow(driver, parent);
			driver.findElementByAccessibilityId("txtVehicleNo").clear();
			
			TLC_No.add(LastTransaction+1);
			LastTransaction=LastTransaction+1;
			}
		}
		 test= new ExetentReportPass(Tow,"Barrier UP Time","Vehicle pending in queue - "+trNo," in This stage for barrier up ");

		int AfterPendingAVC;
		int BerforePendingAVC=trNo;
		for(int i=0,j=trNo-1;j>=0;j--)
		{
			
			ExplicitWait(By.name("Vehicle pending in queue - "+BerforePendingAVC));
//			test = new ExetentReportPass(Tow, "PC Send Time", "Vehicle pending in queue - "+BerforePendingAVC,
//					" in This stage  barrier is Down ");
//			PC_Send obj = new PC_Send();
//			logger.info("PC Send Time");
//			COM_Setup.COMAVCSetup99();
			COM_Setup.DirectAVCCode(Tow,avcAdd.get(i));
			logger.info(avcAdd.get(i) + " AVC is send");
			
//			ImageVerification i4 = new ImageVerification(Tow);
			ImageVerification.imageVerificationTow(Tow, TLC_No.get(i));
			logger.info(avcAdd.get(i));
			SetProfilerImage s = new SetProfilerImage(Tow,avcAdd.get(i));
			i++;
			AfterPendingAVC=BerforePendingAVC-1;
			ExplicitWait(By.name("Vehicle pending in queue - "+AfterPendingAVC));
			if(j==0)
			test = new ExetentReportPass(Tow, "AVC Code send Time", "Vehicle pending in queue - "+AfterPendingAVC,
					"in This stage  barrier is down");
			else
				test = new ExetentReportPass(Tow, "AVC Code send Time", "Vehicle pending in queue - "+AfterPendingAVC,
						"in This stage  barrier is up");
			BerforePendingAVC=AfterPendingAVC;
		}
	}
	public static void excelTow(String Class,int trNo,ArrayList VRN) throws IOException, InterruptedException, SQLException, AWTException
	{
		GetAVCData getdata = new GetAVCData();
		driver.getKeyboard().sendKeys(Keys.PAGE_DOWN);
		driver.findElementByName("Yes").click(); // Enter number of paid Convoy transaction.
		driver.findElementByName("Enter number of tow transaction.").sendKeys(String.valueOf(trNo));
		driver.getKeyboard().sendKeys(Keys.ENTER);
		List<String> avcAdd = new ArrayList<String>();
		for (int i = 1; i <= trNo; i++) {ExtentTest Tow = extent.createTest("Tow");
		ExetentReportPass test;
		test = new ExetentReportPass(Tow, "Class Selection Time", "Class selection is null",
				"This stage class selection is null");
	

//		driver.getKeyboard().sendKeys(Keys.F3);
		int ClassSelectionKey=RandomClassSelector.getSchedualVehicleKey();
//		RandomOtherCashKeySelector c = new RandomOtherCashKeySelector();
		int otherclass=RandomOtherCashKeySelector.RandomOtherCashKeySelector();
		String Vclass = driver.findElementByAccessibilityId("lblClass").getText().toString();
		String MOP = driver.findElementByAccessibilityId("lblMOP").getText().toString(); 
		
//		Robot robot = new Robot();
//		robot.keyPress(61);
//        robot.keyRelease(61);
        String parent = driver.getWindowHandle();
        mainWindowToCurrentWindow(driver, parent);
        logger.info("ClassSelectionKey:"+otherclass);
        
        if(otherclass==61||otherclass==121)
        {
        	
        		Robot robot = new Robot();
        		robot.keyPress(61);
                robot.keyRelease(61);
        	try 
        	{
        		driver.findElementByName("Entry-Toll Sr. No :").click();
        	}
        	 catch(NoSuchElementException e)
        	{
        		 parent = driver.getWindowHandle();
        	        mainWindowToCurrentWindow(driver, parent);
        	        driver.findElementByName("Entry-Toll Sr. No :").click();
        	}
             System.out.println(KentTollMaster.size());
             int rand =randomGenerator(1, KentTollMaster.size());
             driver.findElementByXPath("//Edit[@AutomationId='txtExemptSequeneceNo']").sendKeys(""+rand);
             
      		test= new ExetentReportPass(Tow,"SubToll Selection ","Class selection is "+Vclass+"with subtoll key  ("+ASCIIToKeyboardValue.aSCIIToKeyboardValue(otherclass)+") and MOP is "+MOP,"This stage is for class selection");
     		driver.findElementByXPath("//Edit[@AutomationId='txtExemptSequeneceNo']").click();
             driver.getKeyboard().sendKeys(Keys.ENTER);
        }
       
// 		test= new ExetentReportPass(Tow,"SubToll Selection ","Class selection is "+Vclass+"with subtoll key  ("+ASCIIToKeyboardValue.aSCIIToKeyboardValue(otherclass)+") and MOP is "+MOP,"This stage is for class selection");

//		test= new ExetentReportPass(Tow,"Class is selected ","Class selection is "+Vclass+" ("+ASCIIToKeyboardValue.aSCIIToKeyboardValue(ClassSelectionKey)+") and MOP is "+MOP,"This stage is for class selection");



		
//		driver.switchTo().window(parent);

		if (IsWimAvailable.contains("Y")) {
			SelectRandomSubClass.getRandomSelection();
		}

//		String Vclass = driver.findElementByAccessibilityId("lblClass").getText().toString();
		logger.info(Vclass.toString() + " Key is selected...");
//		Tow.info(Vclass.toString() + " Key is selected...");
		if (Vclass.equals("Vehicle Class")) {

			driver.findElementByAccessibilityId("txtVehicleNo").clear();
			logger.warn("Vehicle number cleared.. because class selection is null");
			Tow.warning("Vehicle number cleared.. because class selection is null");

			Thread.sleep(2000);
		} else {
			parent = driver.getWindowHandle();
	        mainWindowToCurrentWindow(driver, parent);
	        driver.findElementByAccessibilityId("lblToSubToll").click();
	        String FromSubToll= driver.findElementByAccessibilityId("lblToSubToll").getText().toString();
			test= new ExetentReportPass(Tow,"Class is selected ","Class selection is "+Vclass+" ("+ASCIIToKeyboardValue.aSCIIToKeyboardValue(ClassSelectionKey)+") and MOP is "+MOP+" with to sub toll "+FromSubToll,"This stage is for class selection");

			PaymentModeSelector paymentmode = new PaymentModeSelector(Tow);

			parent = driver.getWindowHandle();
			mainWindowToCurrentWindow(driver, parent);
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
}
