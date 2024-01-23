package toll.tcm.MOPs;

import java.awt.Robot;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import toll.tcm.Hardware.*;
import toll.tcm.testCases.*;
import toll.tcm.testData.ReadExcel;
import toll.tcm.utilities.*;
import toll.tcm.APIs.*;
public class ManualTagPunch extends BaseClass
{
	public static void main(String[] args) throws IOException {
//		for(String a:GetManualTagPunchVRN()) 
//		{
//			System.out.println(a);
//		}
		
		
	}
	public ManualTagPunch() throws Exception
	{
		int rand=randomGenerator(0, GetManualTagPunchVRN().size());
		System.out.println("VRN :"+GetManualTagPunchVRN().get(rand));
		String Response=GetEXCCODE.getEXCCODE("https://uat.pecl-panipat.com/BankAPICall/BankApiCall/ReqTagByVRNDetails/", GetManualTagPunchVRN().get(rand));
		driver.findElementByAccessibilityId("txtVehicleNo").sendKeys(GetManualTagPunchVRN().get(rand));	
		Robot robot = new Robot();
		
		if(!Toll_Name.contains("Pal"))
			{
			robot.keyPress(92);
	        robot.keyRelease(92);
			}
		else
		{
			robot.keyPress(42);
	        robot.keyRelease(42);
		}
        System.out.println("Code: "+Response);
        if(Response==null)
        {
        	logger.info("response is null");
        	driver.findElementByAccessibilityId("txtVehicleNo").clear();
        }
        else
        {
        	if(Response.contains("01")||Response.contains("05")||Response.contains("06"))
    		{
    			try 
    			{
    				ExplicitWaitForTag(By.name("Hotlist/Negative Balance Tag"));
    				if(driver.findElementByName("Hotlist/Negative Balance Tag").isDisplayed())
    				{
    					logger.info("Hotlist/Negative Balance Tag");
    			    	driver.findElementByAccessibilityId("txtVehicleNo").clear();
    					CashTransaction.randomCashTransaction();
    				}
    			}
    			catch(Exception e)
    			{
    				logger.info("No such Element");
    			}
    			
    			
    		}
    		else if((Response.contains("02")||Response.contains("04")||Response.contains("00"))&&(!Response.contains("01")||!Response.contains("05")||!Response.contains("06")))
    		{
    			ExplicitWait(By.name("Vehicle pending in queue - 1"));
    			String TagClass1=driver.findElementByAccessibilityId("ListViewSubItem-4").getText().toString();   
    	    	 System.out.println("Tag getting AVC");
       		     System.out.println(TagClass1);       		 
       		     outputStreamForAVC.write(getdata.getAVCData(TagClass1).getBytes());
    			
    		}
    		else
    		{
    			System.out.println("Low Balance or null");
    		}
    		GetManualTagPunchVRN().remove(rand);
    		driver.getKeyboard().sendKeys(Keys.chord(Keys.CONTROL,Keys.END));
    		driver.getKeyboard().sendKeys(Keys.chord(Keys.CONTROL,Keys.END));
    		ExplicitWait(By.name("Vehicle pending in queue - 0"));
        }
		
	}
	public static ArrayList<String> GetManualTagPunchVRN() throws IOException
	{
		
		ReadExcel r= new ReadExcel("Manual Tag Punch");
		int rowcount=sheet.getLastRowNum();
		 ArrayList<String> ManualTagPunchVRNs=new ArrayList<String>();
		
		for(int i=1,j=0;i<=rowcount;i++,j++)
		 {
			int colcount=sheet.getRow(i).getLastCellNum();
			XSSFCell ManualVRN=sheet.getRow(i).getCell(0);
			for(int k=0;k<colcount;k++)
			{
				if(ManualVRN!=null)
				  {	
//					System.out.println(i+" not null");
					ManualTagPunchVRNs.add(ManualVRN.toString());
					
//					System.out.println(ManualVRN.toString());
				  }
				else
				  {
//					ManualTagPunchVRNs[j]="null";
					System.out.println(i+" null");
					
				  }
			}
			
		 }
		
		return ManualTagPunchVRNs;
	}
}
