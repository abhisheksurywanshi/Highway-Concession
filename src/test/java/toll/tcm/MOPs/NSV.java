package toll.tcm.MOPs;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import com.aventstack.extentreports.ExtentTest;

import toll.tcm.Database.DatabaseConnectivity;
import toll.tcm.Hardware.*;
import toll.tcm.testCases.*;
import toll.tcm.utilities.*;
public class NSV extends BaseClass
{
	static Logger logger=LogManager.getLogger(NSV.class);

	public static void getNonSchedualVehicleKey() throws SQLException, AWTException, IOException, InterruptedException
	{
		GetAVCData getdata=new GetAVCData();
		ExtentTest NSV = extent.createTest("NSV");
		ExetentReportPass test;
		test= new ExetentReportPass(NSV,"Class Selection Time","Class selection is null","This stage class selection is null");
		String[] keysArray = NonSchedualClassTypes.keySet().toArray(new String[0]);
		Robot robot = new Robot();
		int RandomSV=randomGenerator(0, NonSchedualClassType-1);
//		System.out.println(NonSchedualClassType+"uyu");
		String key = keysArray[RandomSV];
//		System.out.println(key+"====");
		int RandomSVIntgerForm=Integer.parseInt(key);
		robot.keyPress(RandomSVIntgerForm);
        robot.keyRelease(RandomSVIntgerForm);
        String Vclass=driver.findElementByAccessibilityId("lblClass").getText().toString();
		String MOP=driver.findElementByAccessibilityId("lblMOP").getText().toString();
        logger.info("NSV Class Selection time");
		test= new ExetentReportPass(NSV,"Class is selected ","Class selection is "+Vclass+ " and MOP is "+MOP,"This stage is for class selection");

       
        String VRN=RandomVehicleNumberGenerator.generateRandomVehicleNumber();
	    driver.findElementByAccessibilityId("txtVehicleNo").sendKeys(VRN);	
	    test= new ExetentReportPass(NSV,"VRN insertion time","VRN is "+VRN,"This stage VRN is inserted ");
	    driver.getKeyboard().sendKeys(Keys.ENTER);
	    logger.info("Barrier Up Time");
	    ExplicitWait(By.name("Vehicle pending in queue - 1"));
		test= new ExetentReportPass(NSV,"Barrier UP Time","Vehicle pending in queue - 1"," in This stage for barrier up ");

	    logger.info("Vclass is:"+Vclass);
	    PC_Send obj1=new PC_Send();
	    logger.info("PC Send Time");
    	test= new ExetentReportPass(NSV,"PC Send Time","Vehicle pending in queue - 1 ","in This stage for barrier down");

//	    ExplicitWait(By.xpath("//Pane[@Name='Shift Reamaining Time'][@AutomationId='picTL']"));
//	    COM_Setup.IPAVCSetup99(Vclass);
	    COM_Setup.COMAVCSetup99();
//    	COM_Setup.IPAVCSetup99();
//	    outputStreamForAVC.write(getdata.getAVCData(Vclass).getBytes());
    	logger.info(getdata.getAVCData(Vclass)+" :outputstream ");
//    	outputStreamForAVC.write(getdata.getAVCData(Vclass).getBytes());
    	logger.info("Barrier down Time");
    	try {
    		ExplicitWait(By.name("Vehicle pending in queue - 0"));
			test= new ExetentReportPass(NSV,"Barrier Down Time","Vehicle pending in queue - 0 ","in This stage for barrier down");

    	}catch(org.openqa.selenium.TimeoutException e)
    	{
    		
    	}
    	
    	ImageVerification i=new ImageVerification(NSV);
		SetProfilerImage s= new SetProfilerImage(NSV,Vclass);

    	if(RandomSVIntgerForm==47&&(Toll_Name.contains("TASAWADE")||Toll_Name.contains("Nashik")))
    	{
    		robot.keyPress(RandomSVIntgerForm);
            robot.keyRelease(RandomSVIntgerForm);
    	}
    	
	}
	public static void toNSV() throws AWTException, IOException, InterruptedException
	{
		GetAVCData getdata=new GetAVCData();
		
		ExtentTest NSV = extent.createTest("NSV");
		ExetentReportPass test;
		int nsv[]=new int[3];
		nsv[0] =44;
		nsv[1]=46;
		nsv[2]=47;
		Robot robot = new Robot();
		int RandomNSV=randomGenerator(0, 2);
        
			robot.keyPress(nsv[RandomNSV]);
            robot.keyRelease(nsv[RandomNSV]);
            String Vclass=driver.findElementByAccessibilityId("lblClass").getText().toString();
            int randomVRN = BaseClass.randomGenerator(1, 9999);
    	    DecimalFormat fourdigits = new DecimalFormat("0000");
    	    String b=fourdigits.format(randomVRN);
    	    driver.findElementByAccessibilityId("txtVehicleNo").sendKeys("MH01"+"AA"+b);	
    	    driver.getKeyboard().sendKeys(Keys.ENTER);
    	    ExplicitWait(By.name("Vehicle pending in queue - 1"));
        	outputStreamForAVC.write(getdata.getAVCData(Vclass).getBytes());
        	try 
        	{
        		ExplicitWait(By.name("Vehicle pending in queue - 0"));
        	}
        	catch(org.openqa.selenium.TimeoutException e)
        	{
        		ExplicitWait(By.name("Vehicle pending in queue - 0"));
        	}
        	
        	ImageVerification i=new ImageVerification(NSV);
        	if(nsv[RandomNSV]==47&&(Toll_Name.contains("TASAWADE")||Toll_Name.contains("Nashik")))
        	{
        		robot.keyPress(nsv[RandomNSV]);
                robot.keyRelease(nsv[RandomNSV]);
        	}
        
	}
	public static void excelNSV(String Class) throws Exception
	{
		GetAVCData getdata=new GetAVCData();
		ExtentTest NSV = extent.createTest("NSV");
		ExetentReportPass test;
		int nsv[]=new int[3];
		nsv[0] = 44;
		nsv[1]=46;
		nsv[2]=47;
		Robot robot = new Robot();
		
			robot.keyPress(NSVSelector(Class));
            robot.keyRelease(NSVSelector(Class));
            String Vclass=driver.findElementByAccessibilityId("lblClass").getText().toString();
            int randomVRN = BaseClass.randomGenerator(1, 9999);
    	    DecimalFormat fourdigits = new DecimalFormat("0000");
    	    String b=fourdigits.format(randomVRN);
    	    driver.findElementByAccessibilityId("txtVehicleNo").sendKeys("MH01"+"AA"+b);	
    	    driver.getKeyboard().sendKeys(Keys.ENTER);
    	    ExplicitWait(By.name("Vehicle pending in queue - 1"));
        	outputStreamForAVC.write(getdata.getAVCData(Vclass).getBytes());
        	
        	ExplicitWait(By.name("Vehicle pending in queue - 0"));
        	ImageVerification i=new ImageVerification(NSV);
        	if(NSVSelector(Class)==47&&Toll_Name.contains("TASAWADE"))
        	{
        		robot.keyPress(NSVSelector(Class));
                robot.keyRelease(NSVSelector(Class));
        	}
	}
	public static int NSVSelector(String Class)
	{
		if(Class.contains("01"))
		{
			return 44;
		}
		else if(Class.contains("02"))
		{
			return 46;
		}
		else
		{
			return 47;
		}
	}
}
