package toll.tcm.utilities;

import java.sql.SQLException;

import org.openqa.selenium.Keys;
import toll.tcm.testCases.*;
public class SelectRandomSubClass extends BaseClass {

	public static void getRandomSelection() throws SQLException
	{
		String Vclass=driver.findElementByAccessibilityId("lblClass").getText().toString();
		logger.info(Vclass+" Class is selected...");
		if(Vclass.contains("Tr"))
		{
			if(truck==1)
			{
				
			}
			else 
			{
				 int randomsubClass = BaseClass.randomGenerator(1, truck);
			        if(randomsubClass==1)
			        {
			        	driver.getKeyboard().sendKeys(Keys.NUMPAD1);
			        	
			        }
			        else if(randomsubClass==2)
			        {
			        	driver.getKeyboard().sendKeys(Keys.NUMPAD2);
			        	
			        }
			        else if(randomsubClass==3)
			        {
			        	driver.getKeyboard().sendKeys(Keys.NUMPAD3);
			        	
			        }
			        else if(randomsubClass==4)
			        {
			        	driver.getKeyboard().sendKeys(Keys.NUMPAD4);
			        	
			        }
			        driver.getKeyboard().sendKeys(Keys.ENTER);
				    String subclass=driver.findElementByAccessibilityId("lblClass").getText().toString();
				    logger.info("Random subClass is: "+subclass);
			        
			}
		}
		else if(Vclass.contains("MAV 3"))
		{
			if(mav3==1)
			{
				
			}
			else 
			{
				 int randomsubClass = BaseClass.randomGenerator(1, mav3);
			        if(randomsubClass==1)
			        {
			        	driver.getKeyboard().sendKeys(Keys.NUMPAD1);
			        
			        }
			        else if(randomsubClass==2)
			        {
			        	driver.getKeyboard().sendKeys(Keys.NUMPAD2);
			        	
			        }
			        else if(randomsubClass==3)
			        {
			        	driver.getKeyboard().sendKeys(Keys.NUMPAD3);
			        	
			        }
			        else if(randomsubClass==4)
			        {
			        	driver.getKeyboard().sendKeys(Keys.NUMPAD4);
			        	
			        }
			        driver.getKeyboard().sendKeys(Keys.ENTER);
			        String subclass=driver.findElementByAccessibilityId("lblClass").getText().toString();
				    logger.info("Random subClass is: "+subclass);
			}
		}
		else if(Vclass.contains("MAV 4"))
		{
			if(mav4==1)
			{
				System.out.println("MAV4 SUB CLASS:"+mav4);
			}
			else 
			{
				 int randomsubClass = BaseClass.randomGenerator(1, mav4);
			        if(randomsubClass==1)
			        {
			        	driver.getKeyboard().sendKeys(Keys.NUMPAD1);
			        	System.out.println("MAV4 SUB CLASS:"+mav4);
			        }
			        else if(randomsubClass==2)
			        {
			        	driver.getKeyboard().sendKeys(Keys.NUMPAD2);
			        	System.out.println("MAV4 SUB CLASS:"+mav4);
			        }
			        else if(randomsubClass==3)
			        {
			        	driver.getKeyboard().sendKeys(Keys.NUMPAD3);
			        	System.out.println("MAV4 SUB CLASS:"+mav4);
			        }
			        else if(randomsubClass==4)
			        {
			        	driver.getKeyboard().sendKeys(Keys.NUMPAD4);
			        	System.out.println("MAV4 SUB CLASS:"+mav4);
			        }
			        driver.getKeyboard().sendKeys(Keys.ENTER);
			        String subclass=driver.findElementByAccessibilityId("lblClass").getText().toString();
				    logger.info("Random subClass is: "+subclass);
			}
		}
		else if(Vclass.contains("MAV 5"))
		{
			if(mav5==1)
			{
				System.out.println("MAV5 SUB CLASS:"+mav5);
			}
			else 
			{
				 int randomsubClass = BaseClass.randomGenerator(1, mav5);
			        if(randomsubClass==1)
			        {
			        	driver.getKeyboard().sendKeys(Keys.NUMPAD1);
			        	System.out.println("MAV5 SUB CLASS:"+mav5);
			        }
			        else if(randomsubClass==2)
			        {
			        	driver.getKeyboard().sendKeys(Keys.NUMPAD2);
			        	System.out.println("MAV5 SUB CLASS:"+mav5);
			        }
			        else if(randomsubClass==3)
			        {
			        	driver.getKeyboard().sendKeys(Keys.NUMPAD3);
			        	System.out.println("MAV5 SUB CLASS:"+mav5);
			        }
			        else if(randomsubClass==4)
			        {
			        	driver.getKeyboard().sendKeys(Keys.NUMPAD4);
			        	System.out.println("MAV5 SUB CLASS:"+mav5);
			        }
			        driver.getKeyboard().sendKeys(Keys.ENTER);
			        String subclass=driver.findElementByAccessibilityId("lblClass").getText().toString();
				    logger.info("Random subClass is: "+subclass);
			}
		}
		else if(Vclass.contains("MAV 6"))
		{
			if(mav6==1)
			{
				
			}
			else 
			{
				 int randomsubClass = BaseClass.randomGenerator(1, mav6);
			        if(randomsubClass==1)
			        {
			        	driver.getKeyboard().sendKeys(Keys.NUMPAD1);
			        	
			        }
			        else if(randomsubClass==2)
			        {
			        	driver.getKeyboard().sendKeys(Keys.NUMPAD2);
			        	
			        }
			        else if(randomsubClass==3)
			        {
			        	driver.getKeyboard().sendKeys(Keys.NUMPAD3);
			        	
			        }
			        else if(randomsubClass==4)
			        {
			        	driver.getKeyboard().sendKeys(Keys.NUMPAD4);
			        	
			        }
			        driver.getKeyboard().sendKeys(Keys.ENTER);
			        String subclass=driver.findElementByAccessibilityId("lblClass").getText().toString();
				    logger.info("Random subClass is: "+subclass);
			}
		}
		else if(Vclass.contains("MAV"))
		{
			if(mav==1)
			{
				
			}
			else if(mav==0)
			{
				
			}
			else 
			{
				 int randomsubClass = BaseClass.randomGenerator(1, mav);
			        if(randomsubClass==1)
			        {
			        	driver.getKeyboard().sendKeys(Keys.NUMPAD1);
			        	
			        }
			        else if(randomsubClass==2)
			        {
			        	driver.getKeyboard().sendKeys(Keys.NUMPAD2);
			        	
			        }
			        else if(randomsubClass==3)
			        {
			        	driver.getKeyboard().sendKeys(Keys.NUMPAD3);
			        	
			        }
			        else if(randomsubClass==4)
			        {
			        	driver.getKeyboard().sendKeys(Keys.NUMPAD4);
			        	
			        }
			        driver.getKeyboard().sendKeys(Keys.ENTER);
			        String subclass=driver.findElementByAccessibilityId("lblClass").getText().toString();
				    logger.info("Random subClass is: "+subclass);
			}
		}
		else
		{
			
		}
	}

}
