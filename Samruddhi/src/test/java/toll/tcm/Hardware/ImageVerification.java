package toll.tcm.Hardware;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import toll.tcm.testCases.*;

public class ImageVerification extends BaseClass{

	public static void main(String[] args) {
		Calendar calendar = Calendar.getInstance();
		 String dayOfMonth = new SimpleDateFormat("dd").format(calendar.getTime());
	        System.out.println(dayOfMonth);
		
	}
	public ImageVerification() throws InterruptedException {
		Calendar calendar = Calendar.getInstance();
        String year =String.valueOf(calendar.get(Calendar.YEAR));
        System.out.println(year);
        
        String month = new SimpleDateFormat("MMM").format(calendar.getTime());
        System.out.println(month);
        
        String dayOfMonth = new SimpleDateFormat("dd").format(calendar.getTime());
        System.out.println(dayOfMonth);
        try
        {
             LastTransaction=driver.findElementByAccessibilityId("ListViewSubItem-0").getText().toString();

        }
        catch(org.openqa.selenium.NoSuchElementException v)
        {
        	Thread.sleep(2000);
        	
        }
        System.out.println(LastTransaction);
		
        String filePathForFrontImage="D:\\Toll Systems\\Images\\Toll_Images\\"+year+"\\"+month+year+"\\"+dayOfMonth+month+year+"\\"+LastTransaction+"_F.jpg";
        
		String filePathForBackImage="D:\\Toll Systems\\Images\\Toll_Images\\"+year+"\\"+month+year+"\\"+dayOfMonth+month+year+"\\"+LastTransaction+"_B.jpg";
		
		File file_F = new File(filePathForFrontImage);
		File file_B = new File(filePathForBackImage);
        if (file_F.exists()&&file_B.exists()) {
            System.out.println("File exists.");
            logger.info("Image is Captured");
        } else {
        	
        		Thread.sleep(2000);
        		if (file_F.exists()&&file_B.exists()) {
        			logger.info("Image is Captured");
                }
        		else
        		{
        			logger.warn("Image is  not Captured");
        		}
           
        }
	}
}
class ExemptImageVerification extends ImageVerification
{
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
            System.out.println("File exists.");
            logger.info("Image is Captured");
        } else {
        	
        		Thread.sleep(2000);
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
