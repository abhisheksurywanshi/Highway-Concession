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

import com.aventstack.extentreports.ExtentTest;

import net.bytebuddy.implementation.bind.annotation.Super;
import toll.tcm.Hardware.*;
import toll.tcm.MOPs.RandomOtherCashKeySelector;
import toll.tcm.testCases.*;
import toll.tcm.utilities.*;


public class PaidConvoy extends BaseClass {
	static Logger logger=LogManager.getLogger(PaidConvoy.class);
	public static void main(String[] args) {
		String a="Enter number of tow transaction.";
	}

	public static void paidConvoy(int trNo) throws IOException, InterruptedException, SQLException, AWTException {
		ExtentTest PaidConvoy = extent.createTest("PaidConvoy");
		ExetentReportPass test;
		GetAVCData getdata = new GetAVCData();
		driver.getKeyboard().sendKeys(Keys.PAGE_UP);
		test= new ExetentReportPass(PaidConvoy,"After Pressing Tow Button","Pop up is display for Paid Convoy Mode Start ","Operator Selection for Convoy Mode Start Yes or No");

		driver.findElementByName("Yes").click(); // Enter number of paid Convoy transaction.
		String parent = driver.getWindowHandle();
        mainWindowToCurrentWindow(driver, parent);
		driver.findElementByName("Enter number of paid Convoy transaction.").sendKeys(String.valueOf(trNo));
		driver.getKeyboard().sendKeys(Keys.ENTER);
		parent = driver.getWindowHandle();
        mainWindowToCurrentWindow(driver, parent);
		List<String> avcAdd = new ArrayList<String>();
		for (int i = 1; i <= trNo; i++) {
			

	      
			
			test= new ExetentReportPass(PaidConvoy,"Class Selection Time","Class selection is null","This stage class selection is null");
			


			RandomClassSelector.getSchedualVehicleKey();
			RandomOtherCashKeySelector c = new RandomOtherCashKeySelector();
			String Vclass=driver.findElementByAccessibilityId("lblClass").getText().toString();
			String MOP=driver.findElementByAccessibilityId("lblMOP").getText().toString();
			logger.info("Class selection TIme");
			test= new ExetentReportPass(PaidConvoy,"Class is selected ","Class selection is "+Vclass+ " and MOP is "+MOP,"This stage is for class selection");

			 parent = driver.getWindowHandle();
			driver.switchTo().window(parent);

			if (IsWimAvailable.contains("Y")) {
				SelectRandomSubClass.getRandomSelection();
			}


//			String Vclass = driver.findElementByAccessibilityId("lblClass").getText().toString();
			logger.info(Vclass.toString() + " Key is selected...");
			
			avcAdd.add(Vclass);
//			CashTransaction.info(Vclass.toString() + " Key is selected...");
			if (Vclass.equals("Vehicle Class")) {

				driver.findElementByAccessibilityId("txtVehicleNo").clear();
				logger.warn("Vehicle number cleared.. because class selection is null");
				PaidConvoy.warning("Vehicle number cleared.. because class selection is null");

				Thread.sleep(2000);
			} else {
				PaymentModeSelector paymentmode= new PaymentModeSelector(PaidConvoy);
				
				parent = driver.getWindowHandle();
				mainWindowToCurrentWindow(driver, parent);
				driver.findElementByAccessibilityId("txtVehicleNo").clear();
				}
		}
		int AfterPendingAVC;
		int BerforePendingAVC=trNo;
		System.out.println(avcAdd);
		for(int l=0,j=trNo-1;j>=0;j--)
		{
			
			ExplicitWait(By.name("Vehicle pending in queue - "+BerforePendingAVC));
			PC_Send obj=new PC_Send();
			COM_Setup.IPAVCSetup99();
			outputStreamForAVC.write(getdata.getAVCData(avcAdd.get(l)).getBytes());
			l++;
			AfterPendingAVC=BerforePendingAVC-1;
			ImageVerification i1= new ImageVerification(PaidConvoy);
			ExplicitWait(By.name("Vehicle pending in queue - "+AfterPendingAVC));
			
			BerforePendingAVC=AfterPendingAVC;
		}
	}
	}
	
