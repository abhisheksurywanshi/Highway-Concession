package toll.tcm.utilities;

import java.util.Base64;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

import toll.tcm.testCases.BaseClass;

public class ExetentReportPass extends BaseClass {

	public  ExetentReportPass(ExtentTest Test ,String Description,String Step,String Status){
		

		ExtentTest childTest = Test.createNode(Description);
		try {
			byte[] screenshotBytes=null;
			String screenshotBase64=null;
            if (driver != null) {
            	if(is_extent_exist.contains("Y")) {
                 screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                 screenshotBase64 = Base64.getEncoder().encodeToString(screenshotBytes);
                 childTest.info(Step, MediaEntityBuilder.createScreenCaptureFromBase64String(screenshotBase64).build());
            	}
            	else
            	{
            		 childTest.info(Step);
            	}
//                Test.addScreenCaptureFromBase64String(screenshotBase64, "Custom Screenshot Title");
            
                // Handle the screenshot bytes as needed (e.g., save to a file or attach to a report)
            
            }
            else {
                System.out.println("Driver is not initialized");
                }
            
       		 
       		 childTest.pass(Status);
		}
         catch (WebDriverException e) {
            System.err.println("An error occurred while taking the screenshot: " + e.getMessage());
            e.printStackTrace();
        }
		 
	
	}

}
