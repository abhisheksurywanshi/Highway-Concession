package toll.tcm.Hardware;

import java.io.IOException;

import toll.tcm.testCases.*;

public class PC_Send extends BaseClass{

	public PC_Send() throws IOException, InterruptedException 
	{
		hexData = "02";         
        dataBytes = hexStringToByteArray(hexData);
        outputStreamForExitAVC.write(dataBytes);
//        System.out.println("02: "+dataBytes);
//        Thread.sleep(200);
		outputStreamForExitAVC.write("P".getBytes());
//		Thread.sleep(200);
		hexData = "03";         
        dataBytes = hexStringToByteArray(hexData);
        outputStreamForExitAVC.write(dataBytes);
//        System.out.println("03: "+dataBytes);
        Thread.sleep(150);
        hexData = "02";         
        dataBytes = hexStringToByteArray(hexData);
        outputStreamForExitAVC.write(dataBytes);
//        Thread.sleep(200);
		outputStreamForExitAVC.write("C".getBytes());
//		Thread.sleep(200);
		hexData = "03";         
        dataBytes = hexStringToByteArray(hexData);
        outputStreamForExitAVC.write(dataBytes);
        Thread.sleep(150);
	}

}
