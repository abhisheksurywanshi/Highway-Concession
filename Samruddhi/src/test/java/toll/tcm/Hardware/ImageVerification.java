package toll.tcm.Hardware;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import toll.tcm.testCases.*;

public class ImageVerification extends BaseClass{
	static Logger logger=LogManager.getLogger(ImageVerification.class);

	public static void main(String[] args) {
		Calendar calendar = Calendar.getInstance();
		 String dayOfMonth = new SimpleDateFormat("dd").format(calendar.getTime());
	        System.out.println(dayOfMonth);
		
	}
	public ImageVerification() throws InterruptedException, IOException {
		Calendar calendar = Calendar.getInstance();
        String year =String.valueOf(calendar.get(Calendar.YEAR));
        logger.info("Year: "+year);
        
        String month = new SimpleDateFormat("MMM").format(calendar.getTime());
        logger.info("month: "+month);
        
        String dayOfMonth = new SimpleDateFormat("dd").format(calendar.getTime());
        logger.info("day of month: "+dayOfMonth);
        try
        {
             LastTransaction=driver.findElementByAccessibilityId("ListViewSubItem-0").getText().toString();

        }
        catch(org.openqa.selenium.NoSuchElementException v)
        {
        	Thread.sleep(2000);
        	
        }
        logger.info("current transaction: "+LastTransaction);
		
        String filePathForFrontImage="D:\\Toll Systems\\Images\\Toll_Images\\"+year+"\\"+month+year+"\\"+dayOfMonth+month+year+"\\"+LastTransaction+"_F.jpg";
        
		String filePathForBackImage="D:\\Toll Systems\\Images\\Toll_Images\\"+year+"\\"+month+year+"\\"+dayOfMonth+month+year+"\\"+LastTransaction+"_B.jpg";
		
		String filePathForFrontVideo="D:\\Toll Systems\\Images\\Toll_Images\\"+year+"\\"+month+year+"\\"+dayOfMonth+month+year+"\\"+LastTransaction+"_FVID.mp4";

		String filePathForBackVideo="D:\\Toll Systems\\Images\\Toll_Images\\"+year+"\\"+month+year+"\\"+dayOfMonth+month+year+"\\"+LastTransaction+".mp4";

		File file_FI = new File(filePathForFrontImage);
		File file_BI = new File(filePathForBackImage);
		File file_FV = new File(filePathForFrontVideo);
		File file_BV= new File(filePathForBackVideo);
		
		
//		videoDuration(filePathForFrontVideo);
//		videoDuration(filePathForBackVideo);
		long file_FVSizeInBytes = file_FV.length();
    	double file_FVSizeInKB = (double) file_FVSizeInBytes / 1024;
    	long file_BVSizeInBytes = file_BV.length();
    	double file_BVSizeInKB = (double) file_BVSizeInBytes / 1024;
        if (file_FI.exists()&&file_BI.exists()&&file_FV.exists()&&file_BV.exists()) {
            
            logger.info("Image and video is Captured");
            if(file_FVSizeInKB<50)
            {
            	logger.warn("Video size  for Front Image is less than 50 kb:"+file_FVSizeInKB);
            	Thread.sleep(1000);
            	logger.warn("checking second time size Video size  for Front Image is less than 50 kb:"+file_FVSizeInKB);
            }
            else
            {
            	logger.info("Video size for Front Image is is in kb:"+file_FVSizeInKB);
            }
            if(file_BVSizeInKB<50)
            {
            	logger.warn("Video size  for Back Image is less than 50 kb:"+file_BVSizeInKB);
            	Thread.sleep(1000);
            	logger.warn("checking second time size Video size  for Back Image is less than 50 kb:"+file_BVSizeInKB);
            }
            else
            {
            	logger.info("Video size for Back Image  is in kb:"+file_BVSizeInKB);
            }
            
        } else {
        	
        		Thread.sleep(2000);
        		if (file_FI.exists()&&file_BI.exists()&&file_FV.exists()&&file_BV.exists()) {
        			 logger.info("Image and video is Captured");
        			 if(file_FVSizeInKB<30)
        	            {
        	            	logger.warn("Video size  for Front Image is less than 50 kb:"+file_FVSizeInKB);
        	            	Thread.sleep(1000);
        	            	logger.warn("checking second time size Video size  for Front Image is less than 50 kb:"+file_FVSizeInKB);
        	            }
        	            else
        	            {
        	            	logger.info("Video size for Front Image is is in kb:"+file_FVSizeInKB);
        	            }
        	            if(file_BVSizeInKB<30)
        	            {
        	            	logger.warn("Video size  for Back Image is less than 50 kb:"+file_BVSizeInKB);
        	            	Thread.sleep(1000);
        	            	logger.warn("checking second time size Video size  for Back Image is less than 50 kb:"+file_BVSizeInKB);
        	            }
        	            else
        	            {
        	            	logger.info("Video size for Back Image  is in kb:"+file_BVSizeInKB);
        	            }
                }
        		else
        		{
        			logger.warn("Image is  not Captured");
        			logger.warn("Video size is in kb:"+file_FVSizeInKB);
        		}
           
        }
	}
	public static void videoDuration(String videoFilePath) throws IOException, InterruptedException
	{
		 Boolean videoFound=false;
		 int k=0;
		do
		{
			
			try 
			{
				 String[] command = {"ffmpeg", "-i", videoFilePath};

		         // Start the process
		         ProcessBuilder processBuilder = new ProcessBuilder(command);
		         Process process = processBuilder.start();

		         // Read the process output
		         BufferedReader reader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
		         String line;
		         StringBuilder output = new StringBuilder();

		         while ((line = reader.readLine()) != null) {
		             output.append(line).append("\n");
		         }

		         // Parse the output to find the duration information
		         String durationPattern = "Duration: (\\d+:\\d+:\\d+\\.\\d+)";
		         java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(durationPattern);
		         java.util.regex.Matcher matcher = pattern.matcher(output.toString());

		         if (matcher.find()) {
		             String duration = matcher.group(1);
		             if(videoFilePath.contains("_FVID"))
		             {
		            	 logger.info("Video Duration of Front Video: " + duration);
		             }
		             else
		             {
		            	 logger.info("Video Duration of Back Video: " + duration);
		             }
		         } else {
		        	 logger.info("Failed to retrieve video duration."); 
		         } 

		         // Wait for the process to finish
		         process.waitFor();
		         videoFound=true;
		         
			}catch(java.io.IOException e)
			{
				logger.info("Video Duration checking loop count :"+k);
				k++;
				continue;
			}
			
		}while(videoFound);
	}
}

