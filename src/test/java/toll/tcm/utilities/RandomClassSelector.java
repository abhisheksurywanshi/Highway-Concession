package toll.tcm.utilities;

import java.awt.AWTException;
import java.awt.Robot;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentTest;

import toll.tcm.MOPs.ExemptTransaction;
import toll.tcm.testCases.*;
public class RandomClassSelector extends BaseClass{
	
	static Logger logger=LogManager.getLogger(RandomClassSelector.class);

	public static void main(String[] args) throws SQLException, AWTException {
		
		
		getSchedualVehicleKey();
		
	}
	public static int getSchedualVehicleKey() throws SQLException, AWTException
	{
	
		String[] keysArray = SchedualClassTypes.keySet().toArray(new String[0]);
		
		Robot robot = new Robot();
		int RandomSV=randomGenerator(0, SchedualClassType-1);
		String key = keysArray[RandomSV];
	
		int RandomSVIntgerForm=Integer.parseInt(key);
		robot.keyPress(RandomSVIntgerForm);
        robot.keyRelease(RandomSVIntgerForm);
		return RandomSVIntgerForm;
	}
	public static Keys getRandomKeys()
	{
		List<Keys> Key=new ArrayList<Keys>();
		
//		Key.add(Keys.F1);
//		Key.add(Keys.F2);
//		Key.add(Keys.F3);
//		Key.add(Keys.F4);
//		Key.add(Keys.F5);
		Key.add(Keys.F6);
		Key.add(Keys.F7);
//		Key.add(Keys.F8);
//		Key.add(Keys.F9);

		
           // generating the index using Math.random()
            int index = (int)(Math.random() * Key.size());                      
             return Key.get(index);
        
		
	}
	public static void subClassSelector(Keys Vclass)
	{
		if(Vclass==Keys.F6 )
		{
//			random = new Random();
//			subClassStart = 1;
//	        subClassEnd = 2;
//	        randomsubClass = random.nextInt(subClassEnd - subClassStart + 1) + subClassStart;
	        int randomsubClass = BaseClass.randomGenerator(1, 2);
	        if(randomsubClass==1)
	        {
	        	driver.getKeyboard().sendKeys(Keys.NUMPAD1);
	        	
	        }
	        else
	        {
	        	driver.getKeyboard().sendKeys(Keys.NUMPAD2);
	        	
	        }
		}
		else 
		{
//			random = new Random();
//			subClassStart = 1;
//	        subClassEnd = 3;
//	        randomsubClass = random.nextInt(subClassEnd - subClassStart + 1) + subClassStart;
	        int randomsubClass = BaseClass.randomGenerator(1, 3);
	        if(randomsubClass==1)
	        {
	        	driver.getKeyboard().sendKeys(Keys.NUMPAD1);
	        }
	        else if(randomsubClass==2)
	        {
	        	driver.getKeyboard().sendKeys(Keys.NUMPAD2);
	        }
	        else
	        {
	        	driver.getKeyboard().sendKeys(Keys.NUMPAD3);
	        }
		}
		
		
	}
	public static void randomExemptTypeSelector(String VClass,ExtentTest Extent)
	{
		ExetentReportPass test;
		int ExemptmaxSelection =ExemptTransaction.ExemptTypeFinder(VClass);
		logger.info("Max Selection allow:"+ExemptmaxSelection);
		if(ExemptmaxSelection==0)
		{
			logger.warn("Class is may be null please check selected class:"+VClass);
			ExemptmaxSelection=1;
		}
//		BaseClass.ExplicitWait(By.xpath("//Edit[@AutomationId='txtExemptSequeneceNo']"));  
		int RandomExemptType = BaseClass.randomGenerator(1, ExemptmaxSelection);
		logger.info("Random Exempt type: "+RandomExemptType);
		
		for(int i=1; i<=ExemptmaxSelection;i++)
		{
			if(i==RandomExemptType)
			{
				driver.findElementByXPath("//Edit[@AutomationId='txtExemptSequeneceNo']").sendKeys(""+i+"");
	        	logger.info(i+" key is selected ");
			}
		}
		test= new ExetentReportPass(Extent,"Exempt key number  selection","Exempt Type number  is "+RandomExemptType,"This stage is for exempt type number selection");
		driver.findElement(By.name("Select Exempt Type")).click();
//		 if(randomsubClass==1)
//	        {
//			 
//	        	driver.findElementByXPath("//Edit[@AutomationId='txtExemptSequeneceNo']").sendKeys("1");
//	        	logger.info("1 key is selected ");
//	        }
//	        else if(randomsubClass==2)
//	        {
//	        	driver.findElementByXPath("//Edit[@AutomationId='txtExemptSequeneceNo']").sendKeys("2");
//	        	logger.info("2 key is selected ");
//	        }
//	        else if(randomsubClass==3)
//	        {
//	        	driver.findElementByXPath("//Edit[@AutomationId='txtExemptSequeneceNo']").sendKeys("3");
//	        	logger.info("3 key is selected ");
//	        }
//	        else if(randomsubClass==4)
//	        {
//	        	driver.findElementByXPath("//Edit[@AutomationId='txtExemptSequeneceNo']").sendKeys("4");
//	        	logger.info("4 key is selected ");
//	        }
//	        else if(randomsubClass==5)
//	        {
//	        	driver.findElementByXPath("//Edit[@AutomationId='txtExemptSequeneceNo']").sendKeys("5");
//	        	logger.info("5 key is selected ");
//	        }
//	        else if(randomsubClass==6)
//	        {
//	        	driver.findElementByXPath("//Edit[@AutomationId='txtExemptSequeneceNo']").sendKeys("6");
//	        	logger.info("6 key is selected ");
//	        }
//	        else if(randomsubClass==7)
//	        {
//	        	driver.findElementByXPath("//Edit[@AutomationId='txtExemptSequeneceNo']").sendKeys("7");
//	        	logger.info("7 key is selected ");
//	        }
//	        else if(randomsubClass==8)
//	        {
//	        	driver.findElementByXPath("//Edit[@AutomationId='txtExemptSequeneceNo']").sendKeys("8");
//	        	logger.info("8 key is selected ");
//	        }
//	        else if(randomsubClass==9)
//	        {
//	        	driver.findElementByXPath("//Edit[@AutomationId='txtExemptSequeneceNo']").sendKeys("9");
//	        	logger.info("9 key is selected ");
//	        }
//	        else if(randomsubClass==10)
//	        {
//	        	driver.findElementByXPath("//Edit[@AutomationId='txtExemptSequeneceNo']").sendKeys("10");
//	        	logger.info("10 key is selected");
////	        	driver.findElementByXPath("//Edit[@AutomationId='txtExemptSequeneceNo']").sendKeys(Keys.NUMPAD0);
//	        }
//	        else if(randomsubClass==11)
//	        {
//	        	driver.findElementByXPath("//Edit[@AutomationId='txtExemptSequeneceNo']").sendKeys("11");
//	        	logger.info("11 key is selected");
////	        	driver.findElementByXPath("//Edit[@AutomationId='txtExemptSequeneceNo']").sendKeys(Keys.NUMPAD0);
//	        }
//	        else if(randomsubClass==12)
//	        {
//	        	driver.findElementByXPath("//Edit[@AutomationId='txtExemptSequeneceNo']").sendKeys("12");
//	        	logger.info("12 key is selected");
////	        	driver.findElementByXPath("//Edit[@AutomationId='txtExemptSequeneceNo']").sendKeys(Keys.NUMPAD0);
//	        }
//	        else if(randomsubClass==13)
//	        {
//	        	driver.findElementByXPath("//Edit[@AutomationId='txtExemptSequeneceNo']").sendKeys("13");
//	        	logger.info("13 key is selected");
////	        	driver.findElementByXPath("//Edit[@AutomationId='txtExemptSequeneceNo']").sendKeys(Keys.NUMPAD0);
//	        }
//	        else
//	        {
//	        	driver.findElementByXPath("//Edit[@AutomationId='txtExemptSequeneceNo']").sendKeys("11");
//	        	logger.warn("1 key is selected somthing went wrong");
//	        	driver.findElementByXPath("//Edit[@AutomationId='txtExemptSequeneceNo']").sendKeys("1");
//	        }
		 
	}
	
}
