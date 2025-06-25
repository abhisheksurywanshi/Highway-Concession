package toll.tcm.Hardware;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

import toll.tcm.testCases.*;
import toll.tcm.utilities.ExetentReportPass;
import toll.tcm.utilities.ImageToBase64;

public class ImageVerification extends BaseClass{
	static Logger logger=LogManager.getLogger(ImageVerification.class);
	public static void main(String[] args) {
		Calendar calendar = Calendar.getInstance();
		 String dayOfMonth = new SimpleDateFormat("dd").format(calendar.getTime());
	        System.out.println(dayOfMonth);
		
	}
	public ImageVerification(ExtentTest Extent) throws InterruptedException, IOException {
		ExetentReportPass test;
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
//            ExtentTest childTest = Extent.createNode("Front Camera");
//            byte[] byteArray = convertImageToByteArray(filePathForFrontImage);
//            String screenshotBase64 = Base64.getEncoder().encodeToString(byteArray);
//            childTest.info("Front Camera", MediaEntityBuilder.createScreenCaptureFromBase64String(screenshotBase64).build());
//      		 childTest.pass("pass");
//      		 ExtentTest childTest1 = Extent.createNode("Back Camera");
//             byte[] byteArray1= convertImageToByteArray(filePathForBackImage);
//             String screenshotBase641 = Base64.getEncoder().encodeToString(byteArray1);
//             childTest1.info("Back Camera", MediaEntityBuilder.createScreenCaptureFromBase64String(screenshotBase641).build());
//       		 childTest1.pass("pass");
       		 
            ExtentReportImage(Extent,"Front Camera",filePathForFrontImage);
            ExtentReportImage(Extent,"Back Camera",filePathForBackImage);
       		
//            test= new ExetentReportPass(Extent,"Image Verification F","","");
//	    	ExtentTest childTest = Extent.createNode("Image Verification front");
//	    	childTest.info("Image Verificaation", MediaEntityBuilder.createScreenCaptureFromBase64String(ImageToBase64.encodeImageToBase64(filePathForFrontImage)).build());
            logger.info("Image is Captured");
        } else {
        	
        		Thread.sleep(2000);
        		if (file_F.exists()&&file_B.exists()) {
        			logger.info("Image is Captured");
//        			 ExtentTest childTest = Extent.createNode("Front Camera");
//        	            byte[] byteArray = convertImageToByteArray(filePathForFrontImage);
//        	            String screenshotBase64 = Base64.getEncoder().encodeToString(byteArray);
//        	            childTest.info("Front Camera", MediaEntityBuilder.createScreenCaptureFromBase64String(screenshotBase64).build());
//        	      		 childTest.pass("pass");
        			ExtentReportImage(Extent,"Front Camera",filePathForFrontImage);
//        	      		 ExtentTest childTest1 = Extent.createNode("Back Camera");
//        	             byte[] byteArray1= convertImageToByteArray(filePathForBackImage);
//        	             String screenshotBase641 = Base64.getEncoder().encodeToString(byteArray1);
//        	             childTest1.info("Back Camera", MediaEntityBuilder.createScreenCaptureFromBase64String(screenshotBase641).build());
//        	       		 childTest1.pass("pass");
        			ExtentReportImage(Extent,"Back Camera",filePathForBackImage);
        			
                }
        		else
        		{
        			logger.warn("Image is  not Captured");
        			try 
        			{
        				try 
        				{
//        					ExtentTest childTest = Extent.createNode("Front Camera");
//            	            byte[] byteArray = convertImageToByteArray(filePathForFrontImage);
//            	            String screenshotBase64 = Base64.getEncoder().encodeToString(byteArray);
//            	            childTest.info("Front Camera", MediaEntityBuilder.createScreenCaptureFromBase64String(screenshotBase64).build());
//            	      		 childTest.pass("pass");
        					ExtentReportImage(Extent,"Front Camera",filePathForFrontImage);
        				}
        				catch(NoSuchFileException n)
        				{
//        					ExtentTest childTest = Extent.createNode("Front Camera");
//        					childTest.fail("File not found");
        				}
            	      try {      
//            	      		 ExtentTest childTest1 = Extent.createNode(" Back Camera");
//            	             byte[] byteArray1= convertImageToByteArray(filePathForBackImage);
//            	             String screenshotBase641 = Base64.getEncoder().encodeToString(byteArray1);
//            	             childTest1.info("Back Camera", MediaEntityBuilder.createScreenCaptureFromBase64String(screenshotBase641).build());
//            	       		 childTest1.pass("pass");
            	    	  ExtentReportImage(Extent,"Back Camera",filePathForBackImage);
            	    	  
            	      }
            	      catch(NoSuchFileException n)
            	      {
            	    	  ExtentTest childTest = Extent.createNode("Back Camera");
      					childTest.fail("File not found");
            	      }
        				
        				
        			}catch(Exception e)
        			{
        				e.printStackTrace();
        			}
        		}
           
        }
	}
	public static byte[] convertImageToByteArray(String imagePath) throws IOException {
        // Read the image file into a byte array
        File file = new File(imagePath);
        try 
        {
        	return Files.readAllBytes(file.toPath());
        }
        catch(NoSuchFileException e)
        {
        	e.printStackTrace();
        	
        	return null;
        }
    }
	
	public static void ExtentReportImage(ExtentTest Extent,String Camera,String filePath) throws IOException, InterruptedException
	{
		ExtentTest childTest = Extent.createNode(Camera);
		try
		{
			File file = new File(filePath);
			 byte[] byteArray=Files.readAllBytes(file.toPath());
			
	         String screenshotBase64 = Base64.getEncoder().encodeToString(byteArray);
	         childTest.info(Camera, MediaEntityBuilder.createScreenCaptureFromBase64String(screenshotBase64).build());
	   		 childTest.pass("pass");
		}catch(NoSuchFileException n)
		{
			Thread.sleep(2000);
			try
			{
				File file = new File(filePath);
				 byte[] byteArray=Files.readAllBytes(file.toPath());
				
		         String screenshotBase64 = Base64.getEncoder().encodeToString(byteArray);
		         childTest.info(Camera, MediaEntityBuilder.createScreenCaptureFromBase64String(screenshotBase64).build());
		   		 childTest.pass("pass");
			}catch(NoSuchFileException t)
			{
				childTest.fail("File not Found");
				
			}
			
		}
	}
	public static void imageVerificationTow(ExtentTest Extent,Long LastTransaction) throws IOException, InterruptedException
	{

		ExetentReportPass test;
		Calendar calendar = Calendar.getInstance();
        String year =String.valueOf(calendar.get(Calendar.YEAR));

        
        String month = new SimpleDateFormat("MMM").format(calendar.getTime());

        
        String dayOfMonth = new SimpleDateFormat("dd").format(calendar.getTime());

        
        logger.info(LastTransaction);
		
        String filePathForFrontImage="D:\\Toll Systems\\Images\\Toll_Images\\"+year+"\\"+month+year+"\\"+dayOfMonth+month+year+"\\"+LastTransaction+"_F.jpg";
        
		String filePathForBackImage="D:\\Toll Systems\\Images\\Toll_Images\\"+year+"\\"+month+year+"\\"+dayOfMonth+month+year+"\\"+LastTransaction+"_B.jpg";
		
		File file_F = new File(filePathForFrontImage);
		File file_B = new File(filePathForBackImage);
        if (file_F.exists()&&file_B.exists()) {
            logger.info("File exists.");
//            ExtentTest childTest = Extent.createNode("Front Camera");
//            byte[] byteArray = convertImageToByteArray(filePathForFrontImage);
//            String screenshotBase64 = Base64.getEncoder().encodeToString(byteArray);
//            childTest.info("Front Camera", MediaEntityBuilder.createScreenCaptureFromBase64String(screenshotBase64).build());
//      		 childTest.pass("pass");
//      		 ExtentTest childTest1 = Extent.createNode("Back Camera");
//             byte[] byteArray1= convertImageToByteArray(filePathForBackImage);
//             String screenshotBase641 = Base64.getEncoder().encodeToString(byteArray1);
//             childTest1.info("Back Camera", MediaEntityBuilder.createScreenCaptureFromBase64String(screenshotBase641).build());
//       		 childTest1.pass("pass");
       		 
            ExtentReportImage(Extent,"Front Camera",filePathForFrontImage);
            ExtentReportImage(Extent,"Back Camera",filePathForBackImage);
       		
//            test= new ExetentReportPass(Extent,"Image Verification F","","");
//	    	ExtentTest childTest = Extent.createNode("Image Verification front");
//	    	childTest.info("Image Verificaation", MediaEntityBuilder.createScreenCaptureFromBase64String(ImageToBase64.encodeImageToBase64(filePathForFrontImage)).build());
            logger.info("Image is Captured");
        } else {
        	
        		Thread.sleep(2000);
        		if (file_F.exists()&&file_B.exists()) {
        			logger.info("Image is Captured");
//        			 ExtentTest childTest = Extent.createNode("Front Camera");
//        	            byte[] byteArray = convertImageToByteArray(filePathForFrontImage);
//        	            String screenshotBase64 = Base64.getEncoder().encodeToString(byteArray);
//        	            childTest.info("Front Camera", MediaEntityBuilder.createScreenCaptureFromBase64String(screenshotBase64).build());
//        	      		 childTest.pass("pass");
        			ExtentReportImage(Extent,"Front Camera",filePathForFrontImage);
//        	      		 ExtentTest childTest1 = Extent.createNode("Back Camera");
//        	             byte[] byteArray1= convertImageToByteArray(filePathForBackImage);
//        	             String screenshotBase641 = Base64.getEncoder().encodeToString(byteArray1);
//        	             childTest1.info("Back Camera", MediaEntityBuilder.createScreenCaptureFromBase64String(screenshotBase641).build());
//        	       		 childTest1.pass("pass");
        			ExtentReportImage(Extent,"Back Camera",filePathForBackImage);
        			
                }
        		else
        		{
        			logger.warn("Image is  not Captured");
        			try 
        			{
        				try 
        				{
//        					ExtentTest childTest = Extent.createNode("Front Camera");
//            	            byte[] byteArray = convertImageToByteArray(filePathForFrontImage);
//            	            String screenshotBase64 = Base64.getEncoder().encodeToString(byteArray);
//            	            childTest.info("Front Camera", MediaEntityBuilder.createScreenCaptureFromBase64String(screenshotBase64).build());
//            	      		 childTest.pass("pass");
        					ExtentReportImage(Extent,"Front Camera",filePathForFrontImage);
        				}
        				catch(NoSuchFileException n)
        				{
//        					ExtentTest childTest = Extent.createNode("Front Camera");
//        					childTest.fail("File not found");
        				}
            	      try {      
//            	      		 ExtentTest childTest1 = Extent.createNode(" Back Camera");
//            	             byte[] byteArray1= convertImageToByteArray(filePathForBackImage);
//            	             String screenshotBase641 = Base64.getEncoder().encodeToString(byteArray1);
//            	             childTest1.info("Back Camera", MediaEntityBuilder.createScreenCaptureFromBase64String(screenshotBase641).build());
//            	       		 childTest1.pass("pass");
            	    	  ExtentReportImage(Extent,"Back Camera",filePathForBackImage);
            	    	  
            	      }
            	      catch(NoSuchFileException n)
            	      {
            	    	  ExtentTest childTest = Extent.createNode("Back Camera");
      					childTest.fail("File not found");
            	      }
        				
        				
        			}catch(Exception e)
        			{
        				e.printStackTrace();
        			}
        		}
           
        }
	
	}
}












//class ExemptImageVerification extends ImageVerification
//{
//	public  ExemptImageVerification() throws InterruptedException
//	{
//		
//		String LastTransaction=driver.findElementByAccessibilityId("ListViewSubItem-0").getText().toString();
//		Calendar calendar = Calendar.getInstance();
//		String year =String.valueOf(calendar.get(Calendar.YEAR));
//		 String month=new SimpleDateFormat("MMM").format(calendar.getTime());
//		 String dayOfMonth=new SimpleDateFormat("dd").format(calendar.getTime());
//		String filePathForExemptImage="D:\\Toll Systems\\Images\\Toll_Images\\"+year+"\\"+month+year+"\\"+dayOfMonth+month+year+"\\"+LastTransaction+"_E.jpg";
//		
//		File file_E = new File(filePathForExemptImage);
//		
//        if (file_E.exists()) {
//            System.out.println("File exists.");
//            logger.info("Image is Captured");
//        } else {
//        	
//        		Thread.sleep(2000);
//        		if (file_E.exists()) {
//        			logger.info("Image is Captured");
//                }
//        		else
//        		{
//        			logger.warn("Image is  not Captured");
//        		}
//        
//	}
//	
//	}
//}
