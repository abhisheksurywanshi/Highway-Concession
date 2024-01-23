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
import java.util.Calendar;
import java.util.Date;

import org.openqa.selenium.By;

import toll.tcm.testCases.BaseClass;
public class SetProfilerImage extends BaseClass{

	public SetProfilerImage(String Class) throws InterruptedException, SQLException {
//		String timeStamp=new SimpleDateFormat("yyMMddHHmmss").format(new Date());
		String [] VClass=new String[14];
		
		Calendar calendar = Calendar.getInstance();
        String year =String.valueOf(calendar.get(Calendar.YEAR));
        LastTransaction=driver.findElement(By.xpath("//List[@Name='Last. Trans.'][@AutomationId='lstLastTrans']//ListItem[starts-with(@AutomationId,'ListViewItem-')]")).getText().toString();
        
        String month = new SimpleDateFormat("MMM").format(calendar.getTime());
        
        
        String dayOfMonth = new SimpleDateFormat("dd").format(calendar.getTime());
        
		
		VClass[0]="Vehicle Class";
		VClass[1]="CAR";
		VClass[2]="LCV";
		VClass[3]="TRUCK";
		VClass[4]="BUS";
		VClass[5]="MAV 3";
		VClass[6]="MAV 4";
		VClass[7]="MAV 5";
		VClass[8]="MAV 6";
		VClass[9]="OSV";
		VClass[10]="LCV";
		VClass[11]="TRACTOR";
		VClass[12]="AUTO";
		VClass[13]="BIKE";
		
		DecimalFormat twodigits = new DecimalFormat("00");
	    String code="";
		for(int i=1;i<VClass.length;i++)
		{
//			System.out.println("returnVehicleClass :" + returnVehicleClass );
//			System.out.println("VClass: "+VClass[i]);
//			System.out.println(VehicleClassList.get(i));
//			System.out.println(twodigits.format(i));
			
		    String b=twodigits.format(i); 
//		    this.returnVehicleClass=returnVehicleClass;
			if(Class.contains(VClass[i]))
			{
//				System.out.println("Match Found");
				
				code=b;
				break;
			}
			
		}
		Path sourceFilePath = Paths.get(System.getProperty("user.dir")+"/VEHICLE_CLASS/CLASS"+code+"/"+code+".png");

        // Specify the destination folder
        Path destinationFolder = Paths.get("D:\\Share_Files\\Images");
        
       
        
        // Specify the new name for the image IMG01_"+timeStamp+"_"+Class+".png
        System.out.println("----"+LastTransaction);
        String sql="select a.extra8 from lane_transaction_avc a,lane_transaction_tlc t where t.transaction_tlc_id='"+LastTransaction+"' and t.transaction_tlc_id=a.transaction_tlc_id";
		Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        
        String timeStamp=null;
        while (resultSet.next()) {
            // Retrieve data from each row for the specific column
            timeStamp = resultSet.getString("extra8"); // Replace with the actual column name
            
        }
        
        if(timeStamp=="")
        {
        	timeStamp=new SimpleDateFormat("yy.MM.dd.HH.mm.ss").format(new Date());
        }
        
        String newFileName = "IMG01_"+timeStamp+"_"+code+".png";
        System.out.println("file name: "+newFileName);
        try {
            // Resolve the destination file path
            Path destinationFilePath = destinationFolder.resolve(newFileName);

            // Move and rename the file
            Files.copy(sourceFilePath, destinationFilePath, StandardCopyOption.REPLACE_EXISTING);

            System.out.println("Image moved and renamed successfully.");
        } catch (Exception e) {
        	System.out.println("profiler image is not generated");
            e.printStackTrace();
        }
        File file_prof = new File("D:\\Share_Files\\Images\\"+newFileName);
        System.out.println(file_prof);
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
            			logger.warn("Image is  not exist: "+attempt);
            		}
        	if(attempt==1)
            {
            	System.out.println(firstAttempt+" 1");
            }
            
            attempt++;
            } 
//        
        }
	

}
