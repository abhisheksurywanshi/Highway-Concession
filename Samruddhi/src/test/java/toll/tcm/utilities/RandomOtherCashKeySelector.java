package toll.tcm.utilities;

import java.awt.AWTException;
import java.awt.Robot;

import toll.tcm.testCases.BaseClass;

public class RandomOtherCashKeySelector extends BaseClass
{
	public  RandomOtherCashKeySelector() throws AWTException
	{
		String[] keysArray = OtherCashKeys.keySet().toArray(new String[0]);
		Robot robot = new Robot();
		System.out.println(OtherCashKey);
		int RandomSV=randomGenerator(0, OtherCashKey);
		
	
		
		try 
		{
			String key = keysArray[RandomSV];
			
			int RandomSVIntgerForm=Integer.parseInt(key);
			logger.info(RandomSVIntgerForm+" ASCII Value for Other Cash Key");
			robot.keyPress(RandomSVIntgerForm);
	        robot.keyRelease(RandomSVIntgerForm);
		}
		catch(java.lang.ArrayIndexOutOfBoundsException w)
		{
			
		}
		
		
	}
}
