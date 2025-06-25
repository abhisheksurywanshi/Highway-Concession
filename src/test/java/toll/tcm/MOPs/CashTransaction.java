package toll.tcm.MOPs;




import java.util.Map;
import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import com.aventstack.extentreports.ExtentTest;


import toll.tcm.Hardware.*;
import toll.tcm.testCases.*;
import toll.tcm.utilities.*;
import toll.tcm.utilities.ExetentReportPass;
import java.awt.AWTException;

import java.io.IOException;

import java.sql.SQLException;

public class CashTransaction extends BaseClass {
	static Logger logger=LogManager.getLogger(CashTransaction.class);

	public static void randomCashTransaction() throws IOException, InterruptedException, SQLException, AWTException {

		ExtentTest CashTransaction = extent.createTest("CashTransaction");
		ExetentReportPass test;
		test= new ExetentReportPass(CashTransaction,"Class Selection Time","Class selection is null","This stage class selection is null");
		GetAVCData getdata = new GetAVCData();


		RandomClassSelector.getSchedualVehicleKey();
		RandomOtherCashKeySelector c = new RandomOtherCashKeySelector();
		String Vclass=driver.findElementByAccessibilityId("lblClass").getText().toString();
		String MOP=driver.findElementByAccessibilityId("lblMOP").getText().toString();
		logger.info("Class selection TIme");
		test= new ExetentReportPass(CashTransaction,"Class is selected ","Class selection is "+Vclass+ " and MOP is "+MOP,"This stage is for class selection");

		String parent = driver.getWindowHandle();
		driver.switchTo().window(parent);

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
			PaymentModeSelector paymentmode= new PaymentModeSelector(CashTransaction);
			
			parent = driver.getWindowHandle();
			mainWindowToCurrentWindow(driver, parent);
			ExplicitWait(By.name("Vehicle pending in queue - 1"));
			
			logger.info("Barrier up time");
			test= new ExetentReportPass(CashTransaction,"Barrier UP Time","Vehicle pending in queue - 1"," in This stage for barrier up ");

					        	PC_Send obj=new PC_Send();
					        	logger.info("PC Send Time");
					        	test= new ExetentReportPass(CashTransaction,"PC Send Time","Vehicle pending in queue - 1 ","in This stage for barrier down");

					        	COM_Setup.COMAVCSetup99();
//					        	COM_Setup.IPAVCSetup99();
			
			logger.info(Vclass + " AVC is send");
//			CashTransaction.info(Vclass + " AVC is send");
			logger.info(Vclass + " Barrier is Down");

			ExplicitWait(By.name("Vehicle pending in queue - 0"));
			logger.info("Vehicle pending in queue - 0");
			test= new ExetentReportPass(CashTransaction,"Barrier Down Time","Vehicle pending in queue - 0 ","in This stage for barrier down");

			
			SetProfilerImage s = new SetProfilerImage(CashTransaction,Vclass);
			i = new ImageVerification(CashTransaction);
			BaseClass.mainWindowToCurrentWindow(driver, driver.getWindowHandle());


		}


	}

	

}
