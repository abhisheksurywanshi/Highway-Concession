package toll.tcm.MOPs;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import com.aventstack.extentreports.ExtentTest;

import toll.tcm.Database.GetExemptType;
import toll.tcm.Database.Mapping;
import toll.tcm.Hardware.*;
import toll.tcm.testCases.*;
import toll.tcm.utilities.*;

public class ExemptTransaction extends BaseClass {
	static Logger logger=LogManager.getLogger(ExemptTransaction.class);

	public static void randomExempt() throws IOException, InterruptedException, Exception {
		ExtentTest ExemptTransaction = extent.createTest("ExemptTransaction");
		ExetentReportPass test;
		test= new ExetentReportPass(ExemptTransaction,"Class Selection Time","Class selection is null","This stage class selection is null");
		Robot robot = new Robot(); // lblClass
		GetAVCData getdata = new GetAVCData();
		String VRN = RandomVehicleNumberGenerator.generateRandomVehicleNumber();

		String parent = driver.getWindowHandle();
		RandomClassSelector.getSchedualVehicleKey();
		if (IsWimAvailable.contains("Y")) {
			SelectRandomSubClass.getRandomSelection();
		}
//	    driver.getKeyboard().sendKeys(Keys.F3);
		logger.info("Class selection time");
//		ExemptTransaction.info("Class selection time");
		String Vclass=driver.findElementByAccessibilityId("lblClass").getText().toString();
		String MOP=driver.findElementByAccessibilityId("lblMOP").getText().toString();
		test= new ExetentReportPass(ExemptTransaction,"Class is selected ","Class selection is "+Vclass,"This stage class selection and MOP  is "+MOP);
	    parent=driver.getWindowHandle();   
		mainWindowToCurrentWindow(driver, parent);
		
		logger.info(Vclass.toString() + " Key is selected... for exempt");
//		ExemptTransaction.info(Vclass.toString() + " Key is selected... for exempt");
//	    if(Class==Keys.F6||Class==Keys.F7) //tasawade
//	    {
//	    	RandomClassSelector.subClassSelector(Class);
//	    	driver.getKeyboard().sendKeys(Keys.ENTER);
//	    }
		if(IsWimAvailable.contains("Y"))
		{
			SelectRandomSubClass.getRandomSelection();
		}
		robot.keyPress(Integer.valueOf(ExemptKey));
		robot.keyRelease(Integer.valueOf(ExemptKey));

		
		parent = driver.getWindowHandle();
		mainWindowToCurrentWindow(driver, parent);
//		System.out.println(driver.findElementByAccessibilityId("txtExemptSequeneceNo").isDisplayed());
		try {
			ExplicitWait(By.name("Select Exempt Type"));
			logger.info("Exempt key is selected");
			test= new ExetentReportPass(ExemptTransaction,"Exempt Key selection","Exempt key is"+ASCIIToKeyboardValue.aSCIIToKeyboardValue(Integer.valueOf(ExemptKey)),"This stage is for exempt type selection");
			driver.findElement(By.name("Select Exempt Type")).click();
			logger.info("TRY BLOCK FOR EXEMPT TYPE SELECTION IS EXECUTE");
		} catch (Exception e) {
//			driver.getKeyboard().sendKeys(Keys.chord(Keys.ALT,Keys.TAB));
			robot.keyPress(18);
			robot.keyPress(9);
			robot.keyRelease(9);
			robot.keyRelease(18);
			ExplicitWait(By.name("Select Exempt Type")); // Window[@Name='Exempt Types
			logger.info("Exempt key is selected");
			test= new ExetentReportPass(ExemptTransaction,"Exempt Key selection","Exempt key is "+ASCIIToKeyboardValue.aSCIIToKeyboardValue(Integer.valueOf(ExemptKey)),"This stage is for exempt type selection");												// List'][@AutomationId='frmExemptType']//Edit[@AutomationId='txtExemptSequeneceNo']
			driver.findElement(By.name("Select Exempt Type")).click();
			logger.info("CATCH BLOCK FOR EXEMPT TYPE SELECTION IS EXECUTE");

		}
//		int RandomExemptType = BaseClass.randomGenerator(1, 1);
//		if (RandomExemptType == 1) {
			RandomClassSelector.randomExemptTypeSelector(Vclass,ExemptTransaction);
//		} else {
//			driver.findElementByXPath("//Edit[@AutomationId='txtExemptSequeneceNo']").sendKeys("" + 13 + "");
//		}

		logger.info("Exempt Type is selected");
//		ExemptTransaction.info("Exempt Type is selected");
		driver.getKeyboard().sendKeys(Keys.ENTER);
//		driver.findElementByAccessibilityId("txtExemptSequeneceNo").sendKeys();
		parent = driver.getWindowHandle();
		mainWindowToCurrentWindow(driver, parent);
		driver.findElementByAccessibilityId("txtVehicleNo").sendKeys(VRN);
		logger.info("Random VRN is Generated :" + VRN);
		
		String Exempt_Type = driver.findElementByAccessibilityId("lblExemptType").getText().toString();
		test= new ExetentReportPass(ExemptTransaction,"VRN insertion time","VRN is "+VRN+ " with Exempt type "+Exempt_Type,"This stage VRN is inserted ");
		driver.getKeyboard().sendKeys(Keys.ENTER);
		parent = driver.getWindowHandle();
		mainWindowToCurrentWindow(driver, parent);
		logger.info("EXEMPT TYPE is:" + Exempt_Type);
//		ExemptTransaction.info("EXEMPT TYPE is:" + Exempt_Type);
		if(Exempt_Camera_Exist_Flg.contains("Y"))
		{
			if (allVehicleCaptureFlags.get(ExemptCodeToClass(Vclass)).get(Exempt_Type).contains("Y")) {
				logger.info("Capture flag is:Y");
				test= new ExetentReportPass(ExemptTransaction,"Capture Image check","Class is "+Vclass+ " with Exempt type "+Exempt_Type,"This stage is for EDV image capture ");


				ExplicitWait(By.name("Capture"));
				driver.findElement(By.name("Capture")).click();
			}
		}
		
		parent = driver.getWindowHandle();
		mainWindowToCurrentWindow(driver, parent);
//		logger.info("Remark flag is:"+allVehicleIsExemptRemarkFlags.get(ExemptCodeToClass(Vclass)).get(Exempt_Type));
//		if(Is_Exempt_Remark.contains("Y"))
//		{
//			if(allVehicleIsExemptRemarkFlags.get(ExemptCodeToClass(Vclass)).get(Exempt_Type).contains("Y"))
//			{
//				driver.findElementByName("* Press Enter To Continue. ").sendKeys(Exempt_Type + VRN.substring(6));
//				driver.getKeyboard().sendKeys(Keys.ENTER);
//			}
//			
//		}
		
		String[] Remark=IsExemptRemarkKent.keySet().toArray(new String[0]);
		
		for (int i = 0; i < Remark.length; i++) {
	           String key = Remark[i];
	           String value = IsExemptRemarkKent.get(key);
	           try
	           {
	        	   if(key.equals(Exempt_Type)&&value.contains("Y"))
	 	          {
	 	        	  driver.findElementByName("* Press Enter To Continue. ").sendKeys(Exempt_Type + VRN.substring(6));
		 	        	 test= new ExetentReportPass(ExemptTransaction,"Exempt remark insertion","Class is "+Vclass+ " with Exempt remark is  "+Exempt_Type + VRN.substring(6),"This stage EDV image is captured ");
		 	        	driver.findElementByName("* Press Enter To Continue. ").click();

	 					driver.getKeyboard().sendKeys(Keys.ENTER);
	 	        	  logger.info("Exempt Type: " + key + ", Is Remark: " + value);
	 	          }
	        	   else
	        	   {
//	        		   logger.info("Exempt Type: " + key + ", Is Remark: " + value);
	        	   }
	           }catch(java.lang.NullPointerException e)
	           {
	        	   logger.info("catch block for exempt remark check count:" +i);
	        	   continue;
	           }
	         
	           
	          
	           
	       }
		parent = driver.getWindowHandle();
		mainWindowToCurrentWindow(driver, parent);
		ExplicitWait(By.name("Vehicle pending in queue - 1"));
//		Fluentwait(driver.findElementByName("Vehicle pending in queue - 1"), 30, 6);
		logger.info("Barrier UP Time");
		 test= new ExetentReportPass(ExemptTransaction,"Barrier UP Time","Vehicle pending in queue - 1"," in This stage for barrier up ");
		String TagClass1 = driver.findElementByAccessibilityId("ListViewSubItem-4").getText().toString();

    	PC_Send p=new PC_Send();
    	Thread.sleep(500);
    	logger.info("PC Send Time");
    	test= new ExetentReportPass(ExemptTransaction,"PC Send Time","Vehicle pending in queue - 1 ","in This stage for barrier down");
//    	outputStreamForAVC.write(getdata.getAVCData(TagClass1).getBytes());
//    	COM_Setup.IPAVCSetup99();
		COM_Setup.COMAVCSetup99();
//		outputStreamForAVC.write(getdata.getAVCData(Vclass).getBytes());
//		logger.info(getdata.getAVCData(Vclass) + " :outputstream ");
		logger.info(TagClass1 + " AVC is send");
//		ExemptTransaction.info(TagClass1 + " AVC is send");
		logger.info(TagClass1 + " Barrier Down Time");
		try {
			ExplicitWait(By.name("Vehicle pending in queue - 0"));
			test= new ExetentReportPass(ExemptTransaction,"Barrier Down Time","Vehicle pending in queue - 0 ","in This stage for barrier down");

//    		Fluentwait(driver.findElementByName("Vehicle pending in queue - 0"), 30, 6);
			SetProfilerImage s = new SetProfilerImage(ExemptTransaction,TagClass1);
			i = new ImageVerification(ExemptTransaction);
			ExemptImageVerification i1=new ExemptImageVerification();	

		} catch (Exception t) {
			try {
//    			outputStreamForAVC.write(getdata.getAVCData(TagClass1).getBytes());
				ExplicitWait(By.name("Vehicle pending in queue - 0"));
//        		Fluentwait(driver.findElementByName("Vehicle pending in queue - 0"),10, 1);
				test= new ExetentReportPass(ExemptTransaction,"Barrier Down Time","Vehicle pending in queue - 0 ","in This stage for barrier down");

				SetProfilerImage s = new SetProfilerImage(ExemptTransaction,TagClass1);
				ImageVerification i1 = new ImageVerification(ExemptTransaction);

			} catch (Exception e) {
//    			outputStreamForAVC.write(getdata.getAVCData(TagClass1).getBytes());
				logger.info(TagClass1 + " AVC is send second attempt");
				logger.info(TagClass1 + " Barrier Down Time");
//    	    	outputStreamForAVC.write(getdata.getAVCData(TagClass1).getBytes());
				ExplicitWait(By.name("Vehicle pending in queue - 0"));
				test= new ExetentReportPass(ExemptTransaction,"Barrier Down Time","Vehicle pending in queue - 0 ","in This stage for barrier down");

//        		Fluentwait(driver.findElementByName("Vehicle pending in queue - 0"),10, 1);
				SetProfilerImage s = new SetProfilerImage(ExemptTransaction,TagClass1);
				ImageVerification i1 = new ImageVerification(ExemptTransaction);
				ExemptImageVerification i2=new ExemptImageVerification();
			}

		}

	}

	public void ExemptTypeSelector(String VRN, String Exempt_Type)
			throws IOException, InterruptedException, SQLException, AWTException {
		Robot robot = new Robot();
		robot.keyPress(Integer.valueOf(ExemptKey));
		robot.keyRelease(Integer.valueOf(ExemptKey));
//		driver.switchTo().activeElement();
//		RandomClassSelector.randomExemptTypeSelector();
		for (int i = 0; i < ExemptTypes; i++) {

			logger.info(Exempt_Type);
			logger.info(ExemptType[i] + "===");
			if (ExemptType[i].contains(GetExemptType.getExemptTypeFromExcel(Exempt_Type).toString())) {

				driver.findElementByAccessibilityId("txtExemptSequeneceNo").sendKeys(String.valueOf(i + 1));
			}

		}

//			try
//			{
////				String exempt =driver.findElementByName("SRNO Row "+i).getText().toString();  
//				ExplicitQuickWait(By.xpath("//Window[@AutomationId='frmExemptType']//Table//Custom"));
//				String exempt =driver.findElementByXPath("(//Window[@AutomationId='frmExemptType']//Table//Custom)"+"["+i+"]").getText().toString();
//				System.out.println(exempt);
//				System.out.println(ExemptType[i]);
//				if(ExemptType[i].contains(exempt))
//				{
//					driver.getKeyboard().sendKeys(String.valueOf(i));
//				}
//			}
//			catch(org.openqa.selenium.NoSuchElementException n)
//			{
//				String exempt =driver.findElementByName("SRNO Row "+i).getText().toString();  
//				String exempt =driver.findElementByXPath("(//Window[@AutomationId='frmExemptType']//Table//Custom)"+"["+i+"]").getText().toString();
//				System.out.println(ExemptType[i]);
//				if(ExemptType[i].contains(exempt))
//				{
//					driver.getKeyboard().sendKeys(String.valueOf(i));
//				}
//			}

//		System.out.println(driver.findElementByLinkText(Exempt_Type).getAttribute("Name")+"This is");
//		System.out.println(driver.findElementByLinkText(Exempt_Type).getAttribute("name")+"Thisss");
//		driver.getKeyboard().sendKeys(Keys.ENTER);

//		driver.findElementByName("Vehicle Number/Barcode").sendKeys(VRN);	
//		driver.getKeyboard().sendKeys(Keys.ENTER);
//		driver.getKeyboard().sendKeys(Keys.ENTER);
//		ExplicitWait(By.name("Vehicle pending in queue - 1"));
//		String TagClass1=driver.findElementByAccessibilityId("ListViewSubItem-4").getText().toString();
//    	outputStreamForAVC.write(getdata.getAVCData(TagClass1).getBytes());

//    	ExplicitWait(By.name("Vehicle pending in queue - 0"));

	}

	public static int ExemptTypeFinder(String VClass) {
		logger.info("CLASS IS :" + VClass);
		
//		for (int i = 0; i < keysArrayForCDAndClass.length; i++) 
//		{
//			String CD = keysArrayForCDAndClass[i];
//	           String Vehicle = CDANDCLASS.get(CD);
//		if(Vehicle.equals(VClass))
//		{
//			logger.info(Vehicle+" and "+VClass);
//		}
//		}
		
		
		
		
		if (VClass.contains("CAR")) {
			return AvailableExemptCar.size();
		} 
		else if (VClass.equals("MAV")) {
			return AvailableExemptMAV.size();
		}
		else if (VClass.contains("LCV")) {
			return AvailableExemptLCV.size();
		} else if (VClass.contains("S/TR")) {
			return AvailableExemptBus.size();
		}
		else if (VClass.contains("K/B")) {
			return AvailableExemptTruck.size();
		}
		else if (VClass.equals("TRUCK")) {
			return AvailableExemptTruck.size();
		}

		else if (VClass.equals("BUS")) {
			return AvailableExemptBus.size();
		} else if (VClass.contains("MAV 3")||VClass.contains("3 Axle Com")) {
			return AvailableExemptMAV3.size();
		} else if (VClass.contains("MAV 4")) {
			return AvailableExemptMAV4.size();
		} else if (VClass.contains("MAV 5")) {
			return AvailableExemptMAV5.size();
		} else if (VClass.contains("MAV 6")) {
			return AvailableExemptMAV6.size();
		} else if (VClass.contains("OSV")) {
			return AvailableExemptOSV.size();
		} 

		else {
			return 0;
		}

	}

	public static String ExemptCodeToClass(String VClass) {
		logger.info("CLASS IS :" + VClass);
		Object[] keysArray = SchedualClassTypes.keySet().toArray();
		for (int i = 0; i < keysArray.length; i++) {
            String key = (String) keysArray[i];
            String Class = SchedualClassTypes.get(key);
            String CD=VehicleCDWiseExemptType.get(i);
           
            if(VClass.contains(Class))
            {
            	 logger.info("Actual selected class is :"+VClass+ " Schedual class is:"+Class+" CD is "+CD);
            
            	 return CD;
            }
            
		}
		return null;
//		if (VClass.contains("CAR")) {
//			return "04";
//		} else if (VClass.contains("LCV")) {
//			return "05";
//		} else if (VClass.contains("S/TR")) {
//			return "07";
//		} else if (VClass.contains("TRUCK")) {
//			return "10";
//		}
//
//		else if (VClass.contains("BUS")) {
//			return "07";
//		} else if (VClass.contains("MAV 3")) {
//			return "11";
//		} else if (VClass.contains("MAV 4")) {
//			return "12";
//		} else if (VClass.contains("MAV 5")) {
//			return "13";
//		} else if (VClass.contains("MAV 6")) {
//			return "14";
//		} else if (VClass.contains("OSV")) {
//			return "17";
//		} else if (VClass.contains("MAV")) {
//			return "17";
//		}
//
//		else {
//			return "0";
//		}
//
	}
}
