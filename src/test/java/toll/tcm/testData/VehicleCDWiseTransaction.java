package toll.tcm.testData;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.aventstack.extentreports.ExtentTest;

import toll.tcm.Database.GetExemptType;
import toll.tcm.MOPs.*;

import toll.tcm.testCases.*;
import toll.tcm.utilities.ClassVRNSelector;
import toll.tcm.utilities.ExetentReportPass;
public class VehicleCDWiseTransaction extends BaseClass
{
	
	
	public static void main(String[] args) throws Exception {
		VehicleCDWiseTransaction v=new VehicleCDWiseTransaction();
		
	}
	public VehicleCDWiseTransaction() throws Exception 
	{
//		String Vehicle_CD="43501";
//		String VClass=Vehicle_CD.substring(3,Vehicle_CD.length());
//		String MOP=Vehicle_CD.substring(1, 3);
//		System.out.println(VClass+" "+MOP);
		ExtentTest VehicleCDWiseTransaction = extent.createTest("VehicleCDWiseTransaction");
		ExetentReportPass test;
		ReadExcel r =new ReadExcel("Convoy");
		int rowcount=sheet.getLastRowNum();
        String Vehicle_CD;
        XSSFCell CD;
        String VRN;
        String Exempt_Type_Id;
        int NumberOfTr=0;
        ArrayList<String> NumberOfTraVRN=new ArrayList<String>();
		for(int i=1;i<=rowcount;i++)
		 {
//			
			try 
			{
				CD=sheet.getRow(i).getCell(11);
			}
			catch(java.lang.NullPointerException j)
			{
				CD=null;
			}
			try 
			{
				VRN=sheet.getRow(i).getCell(12).toString();
			}
			catch(java.lang.NullPointerException j)
			{
				VRN=null;
			}
			try 
			{
				Exempt_Type_Id=sheet.getRow(i).getCell(19).toString();
			}
			catch(java.lang.NullPointerException j)
			{
				Exempt_Type_Id=null;
			}
				
				  
				  
//				  
				  if(CD!=null)
				  {					  
					  Vehicle_CD=CD.toString().replace(" ", "").replace(".0", "");
					  String lastFour = Vehicle_CD.substring(Vehicle_CD.length() - 4);
//					  System.out.println(lastFour);
					  String MOP = lastFour.substring(0,2);
					  String Class = lastFour.substring(lastFour.length() - 2);
					  
					  
//					  
					  GetExemptType e=new GetExemptType();
					 String Exempt_Type=e.getExemptTypeFromExcel(Exempt_Type_Id);
					String Transaction_Status=sheet.getRow(i).getCell(22).toString();
					 System.out.println(VRN+" "+lastFour+" "+Exempt_Type+" "+Transaction_Status);
//					 if(Transaction_Status.contains("T")||Transaction_Status.contains("P"))
//						{
//							do
//							{
//								Transaction_Status=sheet.getRow(i).getCell(22).toString();
//								VRN=sheet.getRow(i).getCell(12).toString();
//								NumberOfTraVRN.add(VRN);
//								i++;
//								NumberOfTr++;
//							}while(Transaction_Status.contains("T")||Transaction_Status.contains("P"));
//						}
					 System.out.println("mop:"+MOP);
					 excelTransactionSelection(MOP,Class,VRN,Exempt_Type_Id,Transaction_Status,NumberOfTr,NumberOfTraVRN);
					 System.out.println("dONE");
				  }  
				  else
				  {
					  System.out.print("is Null");
				  }
			
			  
//				 				
//			
//			 
		 }
//		FileReader reader=new FileReader(System.getProperty("user.dir")+"/src/test/java/toll/snc/tcm/testData/Convoy.json");
//		JSONParser jsonparser =new JSONParser();
//		Object obj =jsonparser.parse(reader);
//		JSONObject tags=(JSONObject)obj;
//
//		JSONArray array=(JSONArray)tags.get("Transaction");
//
//		for(int i=0;i<array.size();i++)
//		{
//		JSONObject tag=(JSONObject) array.get(i);
//		String data1 =tag.toString();
//		String VEHICLE_CD=(String) tag.get("VEHICLE_CD");
//		String TRANSACTION_STATUS=(String) tag.get("TRANSACTION_STATUS");
//		System.out.println(VEHICLE_CD);
//		System.out.println(TRANSACTION_STATUS);
//		if(VEHICLE_CD!=null)
//		{
//			String  VEHICLE_CD_toString=VEHICLE_CD.toString();
//			String lastFour = VEHICLE_CD_toString.substring(VEHICLE_CD_toString.length() - 4);
//			String MOP = lastFour.substring(0,2);
//			  String Class = lastFour.substring(lastFour.length() - 2);
//			  System.out.println("MOP:"+MOP+"\n"+"CLASS:"+Class);
//			 System.out.println("block execute");
//			  excelTransactionSelection("10","12");
//		}
//		else
//		{
//			System.out.print("Null ");
//		}
//		
//		}

		
	}
	
	public static void excelTransactionSelection(String MOP,String Class,String VRN, String Exempt_Type,String Transaction_Status,int NumberOfTr,ArrayList NumberOfTraVRN) throws Exception
	{
		ExtentTest VehicleCDWiseTransaction = extent.createTest("VehicleCDWiseTransaction");
		ExetentReportPass test;
		if(MOP.contains("00")||MOP.contains("38"))
		{
			MOPType="Single Journey";
			System.out.println(MOPType);
//			if(!Transaction_Status.contains("V")||!Transaction_Status.contains("S"))
//			{
//				if(Transaction_Status.contains("T"))
//				{
//					Tow.excelTow(Class,NumberOfTr,NumberOfTraVRN);
//				}
//			}
//			else
//			{
				ClassVRNSelector c= new ClassVRNSelector(Class,VRN);
				c.paymentSelection(VRN,VehicleCDWiseTransaction);
//			}
			
		}
		else if(MOP.contains("03"))
		{
			MOPType="Exempted";
			System.out.println(MOPType);
			ClassVRNSelector c= new ClassVRNSelector(Class,VRN);
			ExemptTransaction e= new ExemptTransaction();
			e.ExemptTypeSelector(VRN,Exempt_Type);
		}
		else if(MOP.contains("07"))
		{
			MOPType="ETC";
			System.out.println(MOPType);

			GetTag g= new GetTag();
			g.getExceltag(Class);
		}
		else if(MOP.contains("09"))
		{
			MOPType="Violation";
			System.out.println(MOPType);

			Violation.excelViolation(Class);
			
		}
		else if(MOP.contains("35"))
		{
			MOPType="NSV";
			System.out.println(MOPType);

			NSV.excelNSV(Class);
			
		}
		else
		{
			MOPType="Unknown";
			System.out.println(MOPType);
		}
	}
}
