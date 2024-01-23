package toll.tcm.MOPs;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;

import toll.tcm.Database.*;
import toll.tcm.Hardware.*;
import toll.tcm.testCases.*;
import toll.tcm.utilities.*;
public class IAVC extends BaseClass
{
	
	public void getIAVC(int trNo) throws IOException, SQLException, InterruptedException
	{ 
		DatabaseConnectivity d= new DatabaseConnectivity();
		for(int i=1;i<=trNo;i++)
		{
			Before_IAVC=Integer.valueOf(d.getNumberOfTransaction());
		   System.out.println("before "+i+" IAVC Transacvtion"+Before_IAVC);	
			hexData = "02";         
	        dataBytes = hexStringToByteArray(hexData);
	        outputStreamForExitAVC.write(dataBytes);
//	        Thread.sleep(200);
			outputStreamForExitAVC.write("P".getBytes());
//			Thread.sleep(200);
			hexData = "03";         
	        dataBytes = hexStringToByteArray(hexData);
	        outputStreamForExitAVC.write(dataBytes);
//	        Thread.sleep(200);
	        hexData = "02";         
	        dataBytes = hexStringToByteArray(hexData);
	        outputStreamForExitAVC.write(dataBytes);
//	        Thread.sleep(200);
			outputStreamForExitAVC.write("C".getBytes());
//			Thread.sleep(200);
			hexData = "03";         
	        dataBytes = hexStringToByteArray(hexData);
	        outputStreamForExitAVC.write(dataBytes);
	        After_IAVC=Integer.valueOf(d.getNumberOfTransaction());
	        Violation.randomViolation();
	        if(Before_IAVC==After_IAVC)
	        {
	        	Thread.sleep(2000);
	        }
	        Before_IAVC=After_IAVC;
	        
	        System.out.println("After "+i+" IAVC Transacvtion"+d.getNumberOfTransaction());
			System.out.println("IAVC" +i);
		}
	}
}
