package toll.tcm.MOPs;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;

import toll.tcm.Database.Is_OverWeight_Applicable;
import toll.tcm.Hardware.GetAVCData;
import toll.tcm.Hardware.ImageVerification;
import toll.tcm.Hardware.PC_Send;
import toll.tcm.testCases.BaseClass;
import toll.tcm.utilities.ExetentReportPass;
import toll.tcm.utilities.ManualWeight;
import toll.tcm.utilities.RandomVehicleNumberGenerator;
import toll.tcm.utilities.SelectRandomSubClass;

public class SequentialWeightTransaction extends BaseClass
{
	static Logger logger=LogManager.getLogger(SequentialWeightTransaction.class);

	public static void SequentialWeightTransaction() throws Exception
	{
		Robot robot=new Robot();
		ExtentTest SequentialWeightTransaction = extent.createTest("SequentialWeightTransaction");
		ExetentReportPass test;
		String[] keysArray = OtherCashKeys.keySet().toArray(new String[0]);
		try
		{
			GetAVCData getdata=new GetAVCData();		
		 for (int i = 0; i <=keysArrayForSchedualVehicle.length; i++)
		 {
			    int FromWeight =FromWeightArrayList.get(i);
	    		int toWeight=ToWeightArrayList.get(i);
	    		String key = keysArrayForSchedualVehicle[i];
				 robot.keyPress(Integer.valueOf(key));
				 robot.keyRelease(Integer.valueOf(key));
			for(int k=0;k<=OtherCashKey+1;k++)
			{
			try 
			{
				String OtherCashkey = keysArray[k];
				robot.keyPress(Integer.parseInt(OtherCashkey));
		        robot.keyRelease(Integer.parseInt(OtherCashkey));
			}	
		    catch(java.lang.ArrayIndexOutOfBoundsException w)
		    {
					System.out.println("698754163----------------------------------------------------------------------------------");
		    }
			 for(int j=1;j<=4;j++)
			 {
				 
				 
				 Thread.sleep(500);
		    		if(IsWimAvailable.contains("Y"))
		    		{
		    			SelectRandomSubClass.getRandomSelection();
		    		}
		    		 String Vclass=driver.findElementByAccessibilityId("lblClass").getText().toString();
					    String MOP=driver.findElementByAccessibilityId("lblMOP").getText().toString();
					    System.out.println(Vclass+"  "+MOP);
					    System.out.println(Is_OverWeight_Applicable.isOverWeightApplicableCheck(Vclass, MOP));
					    logger.info(Vclass.toString()+" Key is selected...");
					    
					    if(Vclass.equals("Vehicle Class"))
					    {
					    	
					    	driver.findElementByAccessibilityId("txtVehicleNo").clear();
					    	logger.info("Vehicle number cleared..");
					    }
					    else
					    {
					    	String VRN=RandomVehicleNumberGenerator.generateRandomVehicleNumber();
					    	driver.findElementByAccessibilityId("txtVehicleNo").sendKeys(VRN);	
					    	logger.info("Random VRN is Generated :"+VRN);
					    	if(j==1)
					    	{
					    		 ManualWeight.OverWeightLogic(Vclass, 0);
					    		  ManualWeight.insert0Weight();
					    	}
					    	if(j==2)
					    	{

					    		  int Weight=BaseClass.randomGenerator(FromWeight, toWeight);
					    		  ManualWeight.OverWeightLogic(Vclass, Weight);
					    		  ManualWeight.manualInsertWeight(Weight);
					    	}if(j==3)
					    	{
					    		 ManualWeight.OverWeightLogic(Vclass, toWeight);
					    		  ManualWeight.manualInsertWeight(toWeight);
					    	}if(j==4)
					    	{

					    		  ManualWeight.OverWeightLogic(Vclass, toWeight+1);
					    		  ManualWeight.manualInsertWeight(toWeight+1);
					    	}	 
					    		 
					    	 
					    		 
					    		 
					    		
					    		  
					    			  
					    	
					    		
					    	
					    	
					    	driver.getKeyboard().sendKeys(Keys.ENTER);
					    	
					    	random =new Random();
					    	int randomNumber = random.nextInt(PaymentTypes-1);
					    				    	
					    	int randomNumber1 = random.nextInt(3);
					    	
					    	switch (randomNumber) {
					        case 0: //cash-cash
					        	
					        	driver.getKeyboard().sendKeys(Keys.ENTER);
					        	logger.info("Cash transaction is selected....");
					        	
					        	if(Is_OverWeight_Applicable.isOverWeightApplicableCheck(Vclass, MOP))
					        	ExplicitWait(By.xpath("//Button[@ClassName='Button'][@Name='Yes']"));
					        	
					        	driver.getKeyboard().sendKeys(Keys.ENTER);
					        	ExplicitWait(By.name("Vehicle pending in queue - 1"));
					        	logger.info("Barrier up time");
					        	System.out.println(getdata.getAVCData(Vclass));
					        	PC_Send obj1=new PC_Send();
					        	logger.info("PC Send Time");
						    	outputStreamForAVC.write(getdata.getAVCData(Vclass).getBytes());
					        	logger.info(Vclass+" AVC is send");
					        	logger.info(Vclass+" Barrier Down Time");
					        	ExplicitWait(By.name("Vehicle pending in queue - 0"));
					        	ImageVerification m=new ImageVerification(SequentialWeightTransaction);
					            break;
					        case 1: //Card
//					        	CashTransaction.arrowDownZeroTime();
					        	CashTransaction.arrowDownOneTime();
					        	driver.getKeyboard().sendKeys(Keys.ENTER);
					        	logger.info("Card transaction is selected....");
					        	for (int l = 0; l < keysArraygetPaymentTypeIsReference.length; l++)
					        	{
					   			  key = keysArraygetPaymentTypeIsReference[l];
					   			 if(key.contains("CARD"))
					   			 {
					   				ExplicitQuickWait(By.name("Card Details"));
						        	driver.getKeyboard().sendKeys(VRN);
						        	driver.getKeyboard().sendKeys(Keys.ENTER);
						        	if(Is_Card_Ref.contains("Y"))
						        	{
						        		driver.getKeyboard().sendKeys(VRN);
						        		driver.getKeyboard().sendKeys(Keys.ENTER);
						        	}
						        	
						        	
					   			 }
					        	}
					        	
					        	
					        	if(Is_OverWeight_Applicable.isOverWeightApplicableCheck(Vclass, MOP))
					        	ExplicitWait(By.xpath("//Button[@ClassName='Button'][@Name='Yes']"));
					        	
					        	driver.getKeyboard().sendKeys(Keys.ENTER);
					        	ExplicitWait(By.name("Vehicle pending in queue - 1"));
					        	logger.info("Barrier up time");
					        	System.out.println(getdata.getAVCData(Vclass));
					        	PC_Send obj2=new PC_Send();
					        	logger.info("PC Send Time");
						    	outputStreamForAVC.write(getdata.getAVCData(Vclass).getBytes());
						    	logger.info(getdata.getAVCData(Vclass)+" :outputstream ");
					        	logger.info(Vclass+" AVC is send");
					        	logger.info(Vclass+" Barrier Down Time");
					        	ExplicitWait(By.name("Vehicle pending in queue - 0"));
					        	m=new ImageVerification(SequentialWeightTransaction);
					            break;
					        	
					        case 2: //wallet
//					        	
					            CashTransaction.arrowDownTwoTime();
					        	driver.getKeyboard().sendKeys(Keys.ARROW_RIGHT);
					        		switch(randomNumber1)
					        		{
					        		case 0:
					        			CashTransaction.arrowDownOneTime();
					        			driver.getKeyboard().sendKeys(Keys.ENTER);
					        			logger.info("Paytm Wallet transaction is selected....");
//					        			                          Enter Refernce Number
					        			driver.findElementByName("Enter Refernce Number").sendKeys(String.valueOf(randomVRN));
					        			logger.info("Reference number is entered");
					        			driver.getKeyboard().sendKeys(Keys.ENTER);
								    	System.out.println("wallet Paytm");
							        	
							        	if(Is_OverWeight_Applicable.isOverWeightApplicableCheck(Vclass, MOP))
							        	ExplicitWait(By.xpath("//Button[@ClassName='Button'][@Name='Yes']"));
							        	
							        	driver.getKeyboard().sendKeys(Keys.ENTER);
								    	ExplicitWait(By.name("Vehicle pending in queue - 1"));
								    	logger.info("Barrier up time");
								    	System.out.println(getdata.getAVCData(Vclass));
								    	PC_Send obj3=new PC_Send();
							        	logger.info("PC Send Time");
								    	outputStreamForAVC.write(getdata.getAVCData(Vclass).getBytes());
							        	logger.info(Vclass+" AVC is send");
							        	logger.info(Vclass+" Barrier Down Time");

								    	ExplicitWait(By.name("Vehicle pending in queue - 0"));
								    	m=new ImageVerification(SequentialWeightTransaction);
							            break;
					        		case 1:
					        			CashTransaction.arrowDownTwoTime();
					        			driver.getKeyboard().sendKeys(Keys.ENTER);
					        			logger.info("Paytm Wallet transaction is selected....");
					        			driver.findElementByName("Enter Refernce Number").sendKeys(String.valueOf(randomVRN));
					        			logger.info("Reference number is entered");
					        			driver.getKeyboard().sendKeys(Keys.ENTER);
								        System.out.println("Wallet");
							        	
							        	if(Is_OverWeight_Applicable.isOverWeightApplicableCheck(Vclass, MOP))
							        	ExplicitWait(By.xpath("//Button[@ClassName='Button'][@Name='Yes']"));
							        	
							        	driver.getKeyboard().sendKeys(Keys.ENTER);
								    	ExplicitWait(By.name("Vehicle pending in queue - 1"));
								    	logger.info("Barrier up time");
								    	System.out.println(getdata.getAVCData(Vclass));
								    	PC_Send obj4=new PC_Send();
							        	logger.info("PC Send Time");
								    	outputStreamForAVC.write(getdata.getAVCData(Vclass).getBytes());
							        	logger.info(Vclass+" AVC is send");
							        	logger.info(Vclass+" Barrier Down Time");

								    	ExplicitWait(By.name("Vehicle pending in queue - 0"));
								    	m=new ImageVerification(SequentialWeightTransaction);
							            break;
					        		case 2:
//					        			CashTransaction.arrowDownZeroTime();
					        			driver.getKeyboard().sendKeys(Keys.ENTER);
					        			logger.info("Paytm Wallet transaction is selected....");
					        			driver.findElementByName("Enter Refernce Number").sendKeys(String.valueOf(randomVRN));
					        			logger.info("Reference number is entered");
					        			driver.getKeyboard().sendKeys(Keys.ENTER);
								    	System.out.println("Wallet");
							        	
							        	if(Is_OverWeight_Applicable.isOverWeightApplicableCheck(Vclass, MOP))
							        	ExplicitWait(By.xpath("//Button[@ClassName='Button'][@Name='Yes']"));
							        	
							        	driver.getKeyboard().sendKeys(Keys.ENTER);
								    	ExplicitWait(By.name("Vehicle pending in queue - 1"));
								    	logger.info("Barrier up time");
								    	System.out.println(getdata.getAVCData(Vclass));
								    	PC_Send obj5=new PC_Send();
							        	logger.info("PC Send Time");
								    	outputStreamForAVC.write(getdata.getAVCData(Vclass).getBytes());
							        	logger.info(Vclass+" AVC is send");
							        	logger.info(Vclass+" Barrier Down Time");
								    	ExplicitWait(By.name("Vehicle pending in queue - 0"));
								    	m=new ImageVerification(SequentialWeightTransaction);
							            break;
					        		default:
							             System.out.println("Invalid random number generated. inside");
					        			
					        		}
					        		break;
					        default:
					             System.out.println("Invalid random number generated. outside");
					    	
					    	
					    	
					    	
					    		
					    	
					    	
					    	}
																																
							
							
							
							
							
					    }			
			 }}
			 }
		 }
			catch(org.openqa.selenium.TimeoutException c)
			{
//				
				logger.warn("AVC is unable to send or somthing went wrong...");
				driver.getKeyboard().sendKeys(Keys.ESCAPE);
				driver.getKeyboard().sendKeys(Keys.ESCAPE);
				driver.getKeyboard().sendKeys(Keys.chord(Keys.CONTROL,Keys.END));
				driver.getKeyboard().sendKeys(Keys.chord(Keys.CONTROL,Keys.END));
			}
			    			
		}
		
		public static void arrowDownOneTime()
		{
			driver.getKeyboard().sendKeys(Keys.ARROW_DOWN);
		}
		public static void arrowDownTwoTime()
		{
			driver.getKeyboard().sendKeys(Keys.ARROW_DOWN);
			driver.getKeyboard().sendKeys(Keys.ARROW_DOWN);
		}
		public static void arrowDownZeroTime()
		{
			driver.getKeyboard().sendKeys(Keys.ARROW_UP);			
		}
		

		public static void manualInsertWeight(int Weight) throws AWTException
		{
			if(Is_ManualInsertWeight.contains("Y"))
			{
				Robot robot=new Robot();
				robot.keyPress(111);
				ExplicitWait(By.name("Wim Weight"));
				driver.findElementsByAccessibilityId("txtWeight").clear();
				driver.findElementByAccessibilityId("txtWeight").sendKeys(String.valueOf(Weight));
				driver.getKeyboard().sendKeys(Keys.ENTER);
			}
			else
			{
				Assert.assertTrue(!Is_0Weight_Insert.contains("Y"));
			}
			
		}
		public static void insert0Weight() throws AWTException
		{
			if(Is_0Weight_Insert.contains("Y"))
			{
				driver.getKeyboard().sendKeys(Keys.chord(Keys.CONTROL,Keys.INSERT));
				driver.getKeyboard().sendKeys(Keys.ENTER);
			}
			else
			{
				Assert.assertTrue(!Is_0Weight_Insert.contains("Y"));
			}
		}
		public static void deleteWeight() throws AWTException
		{
			if(Is_Weight_Delete.contains("Y"))
			{
				driver.getKeyboard().sendKeys(Keys.chord(Keys.CONTROL,Keys.DELETE));
				driver.getKeyboard().sendKeys(Keys.ENTER);
			}
			else
			{
				Assert.assertTrue(!Is_Weight_Delete.contains("Y"));
				
			}
			
		}
		public static void OverWeightLogic(String VClass,int Weight)
		{
			 for(int i=0;i<ClassArrayForTollWimClassDetail.size();i++)
		       {
		    	   String Class=ClassArrayForTollWimClassDetail.get(i);
		    	   System.out.println("Loop Class"+Class);
		    	   int FromWeight =FromWeightArrayList.get(i)  ;
	    		   int toWeight=ToWeightArrayList.get(i);
	    		   System.out.println("From Weight is:"+FromWeight+"To Weight is"+toWeight);
		    	   if(VClass.contains(Class))
		    	   {
		    		   
		    		   if(Weight>FromWeight &&Weight<toWeight)
		    		   {
		    			   FromWeight =FromWeightArrayList.get(i)   ;
		    			   toWeight=ToWeightArrayList.get(i);
		    			   logger.info("is in between, fine is of that perticular class");	    			   
		    			   Assert.assertTrue(true);
		    			   break;
		    		   }
		    		   else if(Weight>toWeight)
		    		   {
		    			   FromWeight =FromWeightArrayList.get(i)   ;
		    			   toWeight=ToWeightArrayList.get(i);
		    			   logger.info("more than that perticular class fine is of class:"+FromWeight +"to "+toWeight);	    			  
		    			   Assert.assertTrue(true);
		    			   break;
		    		   }
		    		   else if(Weight<FromWeight)
		    		   {
		    			   FromWeight =FromWeightArrayList.get(i)   ;
		    			   toWeight=ToWeightArrayList.get(i);
		    			   logger.info("more than that  perticular class fine is of class:"+FromWeight +"to "+toWeight);	    			   
		    			   Assert.assertTrue(true);
		    			   break;
		    		   }
		    	   }
		    	   System.out.println("Set class:::"+VClass+"\n");
		    	   
		       }
				
			
		}

			 
}


