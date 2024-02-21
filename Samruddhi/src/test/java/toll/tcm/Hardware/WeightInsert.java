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
      
       String a="S;#1, w1210, s1.5, d0;#2, w1670, s3.7, d26740;649;OK;SF;GVW="+Weight+";E"; // Send the data
       logger.info("Wim Insert Time");
       outputStreamForWeight.write(a.getBytes());
//       String AWeight =driver.findElementByName(Weight).getText().toString();
       ExplicitWait(By.name(Weight));
       System.out.println(Weight);
       System.out.println("------------------------------");
	}
}
