package toll.tcm.utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;

import com.aventstack.extentreports.ExtentTest;

import toll.tcm.Hardware.*;
import toll.tcm.testCases.*;
import toll.tcm.utilities.*;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.IOException;

import java.sql.SQLException;

public class CashTransactionSubToll extends BaseClass {
	static Logger logger = LogManager.getLogger(CashTransactionSubToll.class);

	public static void randomCashTransaction() throws IOException, InterruptedException, SQLException, AWTException {

		ExtentTest CashTransaction = extent.createTest("CashTransaction");
		ExetentReportPass test;
		test = new ExetentReportPass(CashTransaction, "Class Selection Time", "Class selection is null",
				"This stage class selection is null");
		GetAVCData getdata = new GetAVCData();

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
			test= new ExetentReportPass(CashTransaction,"Class is selected ","Class selection is "+Vclass+" ("+ASCIIToKeyboardValue.aSCIIToKeyboardValue(Integer.valueOf(otherclass))+") and MOP is "+MOP,"This stage is for class selection");
     	
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
             
      		test= new ExetentReportPass(CashTransaction,"SubToll Selection ","Class selection is "+Vclass+" with subtoll key  ("+ASCIIToKeyboardValue.aSCIIToKeyboardValue(61)+") and MOP is "+MOP,"This stage is for class selection");
     		driver.findElementByXPath("//Edit[@AutomationId='txtExemptSequeneceNo']").click();
             driver.getKeyboard().sendKeys(Keys.ENTER);
        }
       
// 		test= new ExetentReportPass(CashTransaction,"SubToll Selection ","Class selection is "+Vclass+"with subtoll key  ("+ASCIIToKeyboardValue.aSCIIToKeyboardValue(otherclass)+") and MOP is "+MOP,"This stage is for class selection");

//		test= new ExetentReportPass(CashTransaction,"Class is selected ","Class selection is "+Vclass+" ("+ASCIIToKeyboardValue.aSCIIToKeyboardValue(ClassSelectionKey)+") and MOP is "+MOP,"This stage is for class selection");



		
//		driver.switchTo().window(parent);

		if (IsWimAvailable.contains("Y")) {
			SelectRandomSubClass.getRandomSelection();
		}

//		String Vclass = driver.findElementByAccessibilityId("lblClass").getText().toString();
		logger.info(Vclass.toString() + " Key is selected...");
//		CashTransaction.info(Vclass.toString() + " Key is selected...");
		if (Vclass.equals("Vehicle Class")) {

			driver.findElementByAccessibilityId("txtVehicleNo").clear();
			logger.warn("Vehicle number cleared.. because class selection is null");
			CashTransaction.warning("Vehicle number cleared.. because class selection is null");

			Thread.sleep(2000);
		} else {
			parent = driver.getWindowHandle();
	        mainWindowToCurrentWindow(driver, parent);
	        driver.findElementByAccessibilityId("lblToSubToll").click();
	        String FromSubToll= driver.findElementByAccessibilityId("lblToSubToll").getText().toString();
			test= new ExetentReportPass(CashTransaction,"Class is selected ","Class selection is "+Vclass+" ("+ASCIIToKeyboardValue.aSCIIToKeyboardValue(ClassSelectionKey)+") and MOP is "+MOP+" with to sub toll "+FromSubToll,"This stage is for class selection");

			PaymentModeSelector paymentmode = new PaymentModeSelector(CashTransaction);

			parent = driver.getWindowHandle();
			mainWindowToCurrentWindow(driver, parent);
			ExplicitWait(By.name("Vehicle pending in queue - 1"));

			logger.info("Barrier up time");
			test = new ExetentReportPass(CashTransaction, "Barrier UP Time", "Vehicle pending in queue - 1",
					" in This stage for barrier up ");

//			PC_Send obj = new PC_Send();
//			logger.info("PC Send Time");
//			test = new ExetentReportPass(CashTransaction, "PC Send Time", "Vehicle pending in queue - 1 ",
//					"in This stage barrier is down");

			COM_Setup.COMAVCSetup99();

			logger.info(Vclass + " AVC is send");
//			CashTransaction.info(Vclass + " AVC is send");
			logger.info(Vclass + " Barrier is Down");

			ExplicitWait(By.name("Vehicle pending in queue - 0"));
			logger.info("Vehicle pending in queue - 0");
			test = new ExetentReportPass(CashTransaction, "Barrier Down Time", "Vehicle pending in queue - 0 ",
					"in This stage  barrier is down");

			i = new ImageVerification(CashTransaction);
			SetProfilerImage s = new SetProfilerImage(CashTransaction,Vclass);
			BaseClass.mainWindowToCurrentWindow(driver, driver.getWindowHandle());
//			 modifyReportHtml(ExtendReportPath, "base64 img", "Click here for Screenshot");
//			List<ExtentTest> testList = new ArrayList<>();
//				 modifyTestCaseName(MOP,CashTransaction);
//				 ExtentTest newTest = extent.createTest(MOP);
//				 copyLogs(CashTransaction, newTest);
//				 testList.remove(CashTransaction);
				 
			 
				
		}

	}
	private static void modifyTestCaseName(String newName,ExtentTest currentTest) {
        // Create a new test instance with the new name
        ExtentTest newTest = extent.createTest(newName);

        // Copy logs from the current test to the new test
        copyLogs(currentTest, newTest);

        // Set the new test as the current test
        currentTest = newTest;
    }

    private static void copyLogs(ExtentTest fromTest, ExtentTest toTest) {
        fromTest.getModel().getLogs().forEach(log -> toTest.log(log.getStatus(), log.getDetails()));
    }
}
