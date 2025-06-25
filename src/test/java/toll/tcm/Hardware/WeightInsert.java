package toll.tcm.Hardware;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.SerialPort;
import toll.tcm.testCases.*;

public class WeightInsert extends BaseClass
{
	static Logger logger=LogManager.getLogger(WeightInsert.class);

	public static void insertWeight() throws Exception
	{
		

		 // Specify the port on which the server will listen
		
       Weight="5000";
       
       // Configure the serial port
       serialPortForWeight.setSerialPortParams(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
       // Obtain output stream
       outputStreamForWeight = serialPortForWeight.getOutputStream();	
      
//       String a="S;#1, w1210, s1.5, d0;#2, w1670, s3.7, d26740;649;OK;SF;GVW="+Weight+";E"; // Send the data
       String a="S,0,01/04/2024,17:01,22109,IA,82CBB386FA1E6BEFCB7241D792F4D489,ES,0,01/04/2024,17:01,22109,3,1550,P27,OK,1350,P27,OK,600,P27,FAST,"+Weight+",OK,EA,45.0,E0DD94BD0F808033830FE092EC32C072,E";
       logger.info("Wim Insert Time");
       outputStreamForWeight.write(a.getBytes());
//       String AWeight =driver.findElementByName(Weight).getText().toString();
       ExplicitWait(By.name(Weight));
       Thread.sleep(1000);
       System.out.println(Weight);
       System.out.println("------------------------------");
	}
}
