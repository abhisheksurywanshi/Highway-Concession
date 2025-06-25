package toll.tcm.Hardware;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import com.aventstack.extentreports.ExtentTest;

import toll.tcm.testCases.BaseClass;
import toll.tcm.utilities.CDToProfilerClass;
public class SetProfilerImage extends BaseClass{
	static Logger logger=LogManager.getLogger(SetProfilerImage.class);

	public  SetProfilerImage(ExtentTest Extent,String Class) throws InterruptedException, SQLException, IOException {
//		String timeStamp=new SimpleDateFormat("yyMMddHHmmss").format(new Date());
		String [] VClass=new String[14];
		
		Calendar calendar = Calendar.getInstance();
        String year =String.valueOf(calendar.get(Calendar.YEAR));
//        LastTransaction=driver.findElement(By.xpath("//List[@Name='Last. Trans.'][@AutomationId='lstLastTrans']//ListItem[starts-with(@AutomationId,'ListViewItem-')]")).getText().toString();
        LastTransaction=driver.findElement(By.xpath("//List[@AutomationId='lstLastTrans']//ListItem[starts-with(@AutomationId,'ListViewItem-')]")).getText().toString();

        String month = new SimpleDateFormat("MMM").format(calendar.getTime());
        
        
        String dayOfMonth = new SimpleDateFormat("dd").format(calendar.getTime());
        String code="";
        for (int i = 0; i < keysArrayForCDAndClass.length; i++) {
            String key = keysArrayForCDAndClass[i];
            String value = CDANDCLASS.get(key);
            logger.info(value+" "+Class);
            
          if(value.contains(Class)||Class.contains(value))
          {
        	  
        	  code=CDToProfilerClass.getProfilerCode(key);
          }
           
            
        }
        

        logger.info(code+" :code");
        String []spRand= new String[9];
        spRand[0]="03";
        spRand[1]="04";
        spRand[2]="06";
        spRand[3]="07";
        spRand[4]="08";
        spRand[5]="08";
        spRand[6]="13";
        spRand[7]="12";
        spRand[8]="11";
        int Rand=randomGenerator(0, spRand.length-1);
//        code=spRand[Rand];
//        code="06";
        System.out.println("last code:"+code);
			Path sourceFilePath = Paths.get(System.getProperty("user.dir")+"/VEHICLE_CLASS/CLASS"+code+"/"+code+".png");
		
		

        // Specify the destination folder
        Path destinationFolder = Paths.get("D:\\Share_Files\\Images");
        
       
        
        // Specify the new name for the image IMG01_"+timeStamp+"_"+Class+".png
        logger.info("current transaction----"+LastTransaction);
        String sql="select a.extra8, a.AVC_PROFILER_IMAGE_NAME from lane_transaction_avc a,lane_transaction_tlc t where t.transaction_tlc_id='"+LastTransaction+"' and t.transaction_tlc_id=a.transaction_tlc_id";
		Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        
        String timeStamp=null;
        String newtimestap=null;
        while (resultSet.next()) {
            // Retrieve data from each row for the specific column
            timeStamp = resultSet.getString("extra8"); // Replace with the actual column name
            newtimestap = resultSet.getString("AVC_PROFILER_IMAGE_NAME");
        }
        
        if(timeStamp==null)
        {
        	 
        	 System.out.println(newtimestap);
        	 try 
        	 {
        		 int v= newtimestap.indexOf("IMG01_");
//          		System.out.println(v);
          		String partial=(newtimestap.substring(v+6));
//          		System.out.println(partial);
          		newtimestap=partial.substring(0, 12);
          		timeStamp=newtimestap;
        	 }catch(NullPointerException b)
        	 {
        		 logger.info("null point exception");
        		 timeStamp=new SimpleDateFormat("yy.MM.dd.HH.mm.ss").format(new Date());
        	 }
        	 
     		
        	
        }
        timeStamp=timeStamp.replace(".", "");
        String newFileName = "IMG01_"+timeStamp+"_"+code+".png";
        logger.info("profiler image file name: "+newFileName);
        try {
            // Resolve the destination file path
            Path destinationFilePath = destinationFolder.resolve(newFileName);

            // Move and rename the file
            Files.copy(sourceFilePath, destinationFilePath, StandardCopyOption.REPLACE_EXISTING);

           logger.info("Image moved and renamed successfully.");
        } catch (Exception e) {
        	logger.info("profiler image is not generated");
            e.printStackTrace();
        }
        File file_prof = new File("D:\\Share_Files\\Images\\"+newFileName);
        logger.info(file_prof);
        boolean exist=true;
        boolean firstAttempt=false;
        int attempt=1;
        while(exist)
        {
        	if (file_prof.exists()) {

                firstAttempt=true;

            } 
            		else
            		{
            			exist=false;
            			logger.info("Image is  not exist: "+attempt);
            		}
        	if(attempt==1)
            {
            	logger.info("First Attempt to find image:"+firstAttempt+" 1");
            }
            
            attempt++;
            if(attempt>100000)
            {
            	logger.warn("failed to find image ");
            	break;
            }
            } 
        int index=newFileName.lastIndexOf(".png");
        newFileName=newFileName.substring(0, index) + "P" + newFileName.substring(index);
        System.out.println("P added:"+newFileName);
        String destination="D:\\Toll Systems\\Images\\Toll_Images\\"+year+"\\"+month+year+"\\"+dayOfMonth+month+year+"\\"+newFileName;
        System.out.println(destination);
//        return destination;
        	ImageVerification.ExtentReportImage(Extent, "Profile Image", destination);
        }
	

	}
