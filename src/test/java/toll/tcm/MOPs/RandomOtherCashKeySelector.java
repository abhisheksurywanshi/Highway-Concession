package toll.tcm.MOPs;

import java.awt.AWTException;
import java.awt.Robot;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import toll.tcm.testCases.BaseClass;


public class RandomOtherCashKeySelector extends BaseClass
{
	static Logger logger=LogManager.getLogger(RandomOtherCashKeySelector.class);

	public  static int RandomOtherCashKeySelector() throws AWTException
	{
		String[] keysArray = OtherCashKeys.keySet().toArray(new String[0]);
		Robot robot = new Robot();
		System.out.println(OtherCashKey);
		int RandomSV=0;
		if(OtherCashKey==0)
		{
			return RandomSV;
		}
		
		    RandomSV=randomGenerator(0, OtherCashKey-1);
		
		
		
	
		
		
			String key = keysArray[RandomSV];
			
			int RandomSVIntgerForm=Integer.parseInt(key);
			logger.info(RandomSVIntgerForm+" ASCII Value for Other Cash Key");
			if(RandomSVIntgerForm==187)
			{
				RandomSVIntgerForm=61;
			}
			if(RandomSVIntgerForm==121)
			{
				
			}
//			RandomSVIntgerForm=122;
			robot.keyPress(RandomSVIntgerForm);
	        robot.keyRelease(RandomSVIntgerForm);
	        return RandomSVIntgerForm;
		
		
		
	}
}
