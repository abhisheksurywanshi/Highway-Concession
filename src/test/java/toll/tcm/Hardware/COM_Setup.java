package toll.tcm.Hardware;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.aventstack.extentreports.ExtentTest;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import toll.tcm.testCases.BaseClass;
import toll.tcm.utilities.CDToProfilerClass;

public class COM_Setup extends BaseClass {
	static Logger logger=LogManager.getLogger(COM_Setup.class);
	public  COM_Setup() throws Exception {
		

//			ExtentTest COM_Setup=extent.createTest("COM_Setup");

//		     System.out.println(d.getAVC_COM()+"LL");
			if(Is_Profile_IP.contains("N")&&ProfilerPortNo==null)
			{
			    portIdentifierForAVC = CommPortIdentifier.getPortIdentifier(COMPortNameForAVC);
		        serialPortForAVC = (SerialPort) portIdentifierForAVC.open("SerialCommunication", 2000);       
		        serialPortForAVC.setSerialPortParams(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
		        outputStreamForAVC = serialPortForAVC.getOutputStream();
		        inputStreamForAVC=serialPortForAVC.getInputStream();
		        logger.info("AVC_COM Connected....");
			}
			

	        //Exit avc com
//		  System.out.println(d.getExit_Avc_Com_No()+"------");
	        portIdentifierForExitAVC = CommPortIdentifier.getPortIdentifier(COMPortNameForExitAVC);
	        serialPortForExitAVC = (SerialPort) portIdentifierForExitAVC.open("SerialCommunication", 2000);       
	        serialPortForExitAVC.setSerialPortParams(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
	        outputStreamForExitAVC = serialPortForExitAVC.getOutputStream();	
	        logger.info("EXIT_AVC_COM Connected....");
	        //COM_Setup.info("EXIT_AVC_COM Connected....");
	        //Weight COM
	        
	        portIdentifierForWeight = CommPortIdentifier.getPortIdentifier(COMPortNameForWeight);
	        serialPortForWeight = (SerialPort) portIdentifierForWeight.open("SerialCommunication", 2000);
	        logger.info("WIM_COM Connected....");
	        //COM_Setup.info("WIM_COM Connected....");
	}
	
	public static void IPAVCSetup(String VClass) throws IOException
	{
		int port=Integer.valueOf(5000);
		Socket socket = new Socket(tagReaderIPaddress, port);
//		ExtentTest COM_Setup=extent.createTest("COM_Setup");
		  OutputStream outputStreamForAVC = socket.getOutputStream(); 	

//		  ExtentTest IPAVCSetup=extent.createTest("IPAVCSetup");

		 // Prepare the data to be sent

		 
		 



		 // Convert the data to bytes and send it through the output stream
		 InputStream inputStreamForAVC = socket.getInputStream();
		 String data=GetAVCData.getAVCData(VClass);
		 logger.info("ETC Tag AVC Start");
		 
//		 IPAVCSetup.info("ETC Tag AVC Start");
		 outputStreamForAVC.write(data.getBytes());
//		 byte[] responseBytes = new byte[1024];
//         int bytesRead = inputStreamForAVC.read(responseBytes);
         
         // Convert the received bytes to a string
//         String response = new String(responseBytes, 0, bytesRead);
	}
	public static void COMAVCSetup99() throws IOException
	{
		if(Is_Profile_IP.contains("N"))//||Toll_Name.contains("Talapady")
		{
		String timeStamp=new SimpleDateFormat("yy.MM.dd.HH.mm.ss").format(new Date());
    	String AVC_Recieve_String="[IMG01_"+timeStamp.replace(".", "")+"_"+"99"+"]";
    	logger.info(AVC_Recieve_String+" is send");
    	outputStreamForAVC.write(AVC_Recieve_String.getBytes());
		}
		else if(Toll_Name.contains("Hulikunte")||Toll_Name.contains("Pollachi"))
		{
			IPAVCSetup99();
		}
	}
	public static void IPAVCSetup99() throws IOException
	{
		
		int port=Integer.valueOf(5000);
		Socket socket=null;
		try
		{
			socket = new Socket(tagReaderIPaddress, port);

			
			  
		}catch(ConnectException e)
		{
			
		}
		

		OutputStream outputStreamForAVC = socket.getOutputStream(); 
		  String timeStamp=new SimpleDateFormat("yy.MM.dd.HH.mm.ss").format(new Date());


			 InputStream inputStreamForAVC = socket.getInputStream();
			 String data="[IMG01_"+timeStamp.replace(".", "")+"_99]";
			 logger.info(" AVC Start");
			 

			 outputStreamForAVC.write(data.getBytes());
		 
		 

		  

	}
	 public static void DirectAVCCode(ExtentTest Extent,String Class) throws IOException, SQLException
	    {
	       	 Calendar calendar = Calendar.getInstance();
	            String year =String.valueOf(calendar.get(Calendar.YEAR));
	            String month = new SimpleDateFormat("MMM").format(calendar.getTime());
	            String dayOfMonth = new SimpleDateFormat("dd").format(calendar.getTime());
	            String code="";
	            for (int i = 0; i < keysArrayForCDAndClass.length; i++) {
	                String key = keysArrayForCDAndClass[i];
	                String value = CDANDCLASS.get(key);
	                
	                logger.info(value+" "+Class);
	              if(value.toString().contains(Class))
	              {
	            	  
	            	  code=CDToProfilerClass.getProfilerCode(key);
	              }
	               
	                
	            }
	            

	            logger.info(code+" :code");
	           
	            	timeStamp=new SimpleDateFormat("yy.MM.dd.HH.mm.ss").format(new Date());
	            
	            timeStamp=timeStamp.replace(".", "");
	            
	            
	            
	           
	        	String AVC_Recieve_String="[IMG01_"+timeStamp.replace(".", "")+"_"+code+"]";
	        	logger.info(AVC_Recieve_String+" is send");
	        	outputStreamForAVC.write(AVC_Recieve_String.getBytes());
	       	 
		 }
}
