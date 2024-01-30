package toll.tcm.MOPs;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import com.aventstack.extentreports.ExtentTest;

import toll.tcm.Database.GetExemptType;
import toll.tcm.Hardware.*;
import toll.tcm.testCases.*;
import toll.tcm.utilities.*;
public class ExemptTransaction extends BaseClass
{
	public static void randomExempt() throws IOException, InterruptedException, Exception
	{
		ExtentTest ExemptTransaction=extent.createTest("ExemptTransaction");
		   Robot robot = new Robot();   //lblClass
		GetAVCData getdata= new GetAVCData();
		String VRN=RandomVehicleNumberGenerator.generateRandomVehicleNumber();
		
	    String parent=driver.getWindowHandle();
	    RandomClassSelector.getSchedualVehicleKey();
	    if(IsWimAvailable.contains("Y"))
		{
			SelectRandomSubClass.getRandomSelection();
		}
//	    driver.getKeyboard().sendKeys(Keys.F3);
	    logger.info("Class selection time");
	    ExemptTransaction.info("Class selection time");
	    driver.switchTo().window(Home_Page_Window); 
	    String Vclass=driver.findElementByAccessibilityId("lblClass").getText().toString();
	    logger.info(Vclass.toString()+" Key is selected... for exempt");
	    ExemptTransaction.info(Vclass.toString()+" Key is selected... for exempt");
//	    if(Class==Keys.F6||Class==Keys.F7) //tasawade
//	    {
//	    	RandomClassSelector.subClassSelector(Class);
//	    	driver.getKeyboard().sendKeys(Keys.ENTER);
//	    }
	    
	    
		robot.keyPress(Integer.valueOf(ExemptKey));
        robot.keyRelease(Integer.valueOf(ExemptKey));
      
        logger.info("Exempt key is selected");
        ExemptTransaction.info("Exempt key is selected");
         parent=driver.getWindowHandle();   
		mainWindowToCurrentWindow(driver, parent);
//		System.out.println(driver.findElementByAccessibilityId("txtExemptSequeneceNo").isDisplayed());
		try 
		{
			ExplicitWait(By.name("Select Exempt Type"));
			driver.findElement(By.name("Select Exempt Type")).click();
			System.out.println("TRY BLOCK FOR EXEMPT TYPE SELECTION IS EXECUTE");
		}
		catch(Exception e)
		{
//			driver.getKeyboard().sendKeys(Keys.chord(Keys.ALT,Keys.TAB));
			robot.keyPress(18);
			robot.keyPress(9);
			robot.keyRelease(9);
			robot.keyRelease(18);
			ExplicitWait(By.name("Select Exempt Type"));  //Window[@Name='Exempt Types List'][@AutomationId='frmExemptType']//Edit[@AutomationId='txtExemptSequeneceNo']
			driver.findElement(By.name("Select Exempt Type")).click();
			System.out.println("CATCH BLOCK FOR EXEMPT TYPE SELECTION IS EXECUTE");

		}
		int RandomExemptType = BaseClass.randomGenerator(1, 1);
		if(RandomExemptType==1)
		{
			RandomClassSelector.randomExemptTypeSelector(Vclass);
		}
		else
		{
			driver.findElementByXPath("//Edit[@AutomationId='txtExemptSequeneceNo']").sendKeys(""+13+"");
		}
		
		logger.info("Exempt Type is selected");
        ExemptTransaction.info("Exempt Type is selected");
		driver.getKeyboard().sendKeys(Keys.ENTER);
//		driver.findElementByAccessibilityId("txtExemptSequeneceNo").sendKeys();
		driver.switchTo().window(Home_Page_Window); 
		driver.findElementByAccessibilityId("txtVehicleNo").sendKeys(VRN);	
		logger.info("Random VRN is Generated :"+VRN);
		ExemptTransaction.info("Random VRN is Generated :"+VRN);
		String Exempt_Type=driver.findElementByAccessibilityId("lblExemptType").getText().toString();
		driver.getKeyboard().sendKeys(Keys.ENTER);
		parent=driver.getWindowHandle();
		mainWindowToCurrentWindow(driver, parent);
		logger.info("EXEMPT TYPE is:"+Exempt_Type);
		ExemptTransaction.info("EXEMPT TYPE is:"+Exempt_Type);
		if(allVehicleCaptureFlags.get(ExemptCodeToClass(Vclass)).get(Exempt_Type).contains("Y"))
		{
			logger.info("Capture flag is:"+allVehicleCaptureFlags.get(ExemptCodeToClass(Vclass)).get(Exempt_Type));
			ExemptTransaction.info("Capture flag is:"+allVehicleCaptureFlags.get(ExemptCodeToClass(Vclass)).get(Exempt_Type));

			ExplicitWait(By.name("Capture"));	
			driver.findElement(By.name("Capture")).click();
		}
		parent=driver.getWindowHandle();
		mainWindowToCurrentWindow(driver, parent);
		ExplicitWait(By.name("Vehicle pending in queue - 1"));
//		Fluentwait(driver.findElementByName("Vehicle pending in queue - 1"), 30, 6);
		logger.info("Barrier UP Time");
		ExemptTransaction.info("Barrier UP Time");
		String TagClass1=driver.findElementByAccessibilityId("ListViewSubItem-4").getText().toString();
    
    	PC_Send p=new PC_Send();
    	Thread.sleep(500);
    	logger.info("PC Send Time");
//    	outputStreamForAVC.write(getdata.getAVCData(TagClass1).getBytes());
//    	COM_Setup.IPAVCSetup99(Vclass);
//		COM_Setup.COMAVCSetup99();
    	outputStreamForAVC.write(getdata.getAVCData(Vclass).getBytes());
    	logger.info(getdata.getAVCData(Vclass)+" :outputstream ");
    	logger.info(TagClass1+" AVC is send");
    	ExemptTransaction.info(TagClass1+" AVC is send");
    	logger.info(TagClass1+" Barrier Down Time");
    	ExemptTransaction.info(TagClass1+" Barrier Down Time");
    	try 
    	{
    		ExplicitWait(By.name("Vehicle pending in queue - 0"));
//    		Fluentwait(driver.findElementByName("Vehicle pending in queue - 0"), 30, 6);
    		SetProfilerImage s= new SetProfilerImage(TagClass1);
    		ImageVerification i= new ImageVerification();
    		
    	}
    	catch(Exception t)
    	{
    		try 
    		{
//    			outputStreamForAVC.write(getdata.getAVCData(TagClass1).getBytes());
        		ExplicitWait(By.name("Vehicle pending in queue - 0"));
//        		Fluentwait(driver.findElementByName("Vehicle pending in queue - 0"),10, 1);
        		SetProfilerImage s= new SetProfilerImage(TagClass1);
        		ImageVerification i1= new ImageVerification();
        		
    		}
    		catch(Exception e)
    		{
//    			outputStreamForAVC.write(getdata.getAVCData(TagClass1).getBytes());
    	    	logger.info(TagClass1+" AVC is send second attempt");
    	    	logger.info(TagClass1+" Barrier Down Time");
//    	    	outputStreamForAVC.write(getdata.getAVCData(TagClass1).getBytes());
        		ExplicitWait(By.name("Vehicle pending in queue - 0"));
//        		Fluentwait(driver.findElementByName("Vehicle pending in queue - 0"),10, 1);
        		SetProfilerImage s= new SetProfilerImage(TagClass1);
        		ImageVerification i1= new ImageVerification();
    		}
    		
    	}
    	
		
	}
	public void ExemptTypeSelector(String VRN,String Exempt_Type) throws IOException, InterruptedException, SQLException, AWTException
	{
		Robot robot = new Robot();
		robot.keyPress(Integer.valueOf(ExemptKey));
        robot.keyRelease(Integer.valueOf(ExemptKey));
//		driver.switchTo().activeElement();
//		RandomClassSelector.randomExemptTypeSelector();
		for(int i=0;i<ExemptTypes;i++)
		{
			
			System.out.println(Exempt_Type);
			System.out.println(ExemptType[i]+"===");
			if(ExemptType[i].contains(GetExemptType.getExemptTypeFromExcel(Exempt_Type).toString()))
			{
				
				driver.findElementByAccessibilityId("txtExemptSequeneceNo").sendKeys(String.valueOf(i+1));
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
	public static int ExemptTypeFinder(String VClass)
	{
		System.out.println("CLASS IS :"+VClass );
		if(VClass.contains("CAR"))
		{
			return AvailableExemptCar.size();
		}
		else if(VClass.contains("LCV"))
		{
			return AvailableExemptLCV.size();
		}
		else if(VClass.contains("S/TR"))
		{
			return AvailableExemptBus.size();
		}
		else if(VClass.contains("TRUCK"))
		{
			return AvailableExemptTruck.size();
		}
		
		else if(VClass.contains("BUS"))
		{
			return AvailableExemptBus.size();
		}
		else if(VClass.contains("MAV 3"))
		{
			return AvailableExemptMAV3.size();
		}
		else if(VClass.contains("MAV 4"))
		{
			return AvailableExemptMAV4.size();
		}
		else if(VClass.contains("MAV 5"))
		{
			return AvailableExemptMAV5.size();
		}
		else if(VClass.contains("MAV 6"))
		{
			return AvailableExemptMAV6.size();
		}
		else if(VClass.contains("OSV"))
		{
			return AvailableExemptOSV.size();
		}else if(VClass.contains("MAV"))
		{
			return AvailableExemptMAV.size();
		}
		
		else
		{
			return 0;
		}
		
	}
	public static String ExemptCodeToClass(String VClass)
	{
		System.out.println("CLASS IS :"+VClass );
		if(VClass.contains("CAR"))
		{
			return "04";
		}
		else if(VClass.contains("LCV"))
		{
			return "05";
		}
		else if(VClass.contains("S/TR"))
		{
			return "07";
		}
		else if(VClass.contains("TRUCK"))
		{
			return "10";
		}
		
		else if(VClass.contains("BUS"))
		{
			return "07";
		}
		else if(VClass.contains("MAV 3"))
		{
			return "11";
		}
		else if(VClass.contains("MAV 4"))
		{
			return "12";
		}
		else if(VClass.contains("MAV 5"))
		{
			return "13";
		}
		else if(VClass.contains("MAV 6"))
		{
			return "14";
		}
		else if(VClass.contains("OSV"))
		{
			return "17";
		}else if(VClass.contains("MAV"))
		{
			return "17";
		}
		
		else
		{
			return "0";
		}
		
	}
}
