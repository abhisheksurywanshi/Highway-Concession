package toll.tcm.utilities;

import java.awt.AWTException;
import java.awt.Robot;

import toll.tcm.testCases.BaseClass;

public class RandomCashTransaction extends BaseClass
{
	public  RandomCashTransaction() throws AWTException
	{
		String[] keysArray = OtherCashKeys.keySet().toArray(new String[0]);
		Robot robot = new Robot();
		int RandomSV=randomGenerator(0, OtherCashKey);
		
	
		
		try 
		{
			String key = keysArray[RandomSV];
			
			int RandomSVIntgerForm=Integer.parseInt(key);
			System.out.println(RandomSVIntgerForm);
			robot.keyPress(RandomSVIntgerForm);
	        robot.keyRelease(RandomSVIntgerForm);
		}
		catch(java.lang.ArrayIndexOutOfBoundsException w)
		{
			
		}
		
		
	}
}
