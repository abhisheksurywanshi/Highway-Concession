package toll.tcm.Hardware;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import toll.tcm.testCases.BaseClass;

public class ExemptImageVerification extends BaseClass
{
	static Logger logger=LogManager.getLogger(ExemptImageVerification.class);

	public  ExemptImageVerification() throws InterruptedException
	{
		
		String LastTransaction=driver.findElementByAccessibilityId("ListViewSubItem-0").getText().toString();
		Calendar calendar = Calendar.getInstance();
		String year =String.valueOf(calendar.get(Calendar.YEAR));
		 String month=new SimpleDateFormat("MMM").format(calendar.getTime());
		 String dayOfMonth=new SimpleDateFormat("dd").format(calendar.getTime());
		String filePathForExemptImage="D:\\Toll Systems\\Images\\Toll_Images\\"+year+"\\"+month+year+"\\"+dayOfMonth+month+year+"\\"+LastTransaction+"_E.jpg";
		
		File file_E = new File(filePathForExemptImage);
		
        if (file_E.exists()) {
          logger.info("File exists.");
            logger.info("Image is Captured");
        } else {
        	
//        		Thread.sleep(2000);
        		if (file_E.exists()) {
        			logger.info("Image is Captured");
                }
        		else
        		{
        			logger.warn("Image is  not Captured");
        		}
        
	}
	
	}
}