package toll.tcm.MOPs;

import java.awt.AWTException;
import java.awt.Robot;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;

import toll.tcm.Database.Is_OverWeight_Applicable;
import toll.tcm.Hardware.GetAVCData;
import toll.tcm.Hardware.ImageVerification;
import toll.tcm.Hardware.PC_Send;
import toll.tcm.MOPs.CashTransaction;
import toll.tcm.MOPs.RandomOtherCashKeySelector;
import toll.tcm.testCases.BaseClass;
import toll.tcm.utilities.RandomClassSelector;
import toll.tcm.utilities.SelectRandomSubClass;

public class ManualWeight extends BaseClass
{
	static Logger logger=LogManager.getLogger(ManualWeight.class);

	public static void manualWeightTransaction(int Weight) throws Exception
	{
		manualInsertWeight(Weight);
		try
		{
			GetAVCData getdata=new GetAVCData();
//			random = new Random();
//			int VRNStart = 1;
//	        int VRNEnd = 9999;
//	        int randomVRN = random.nextInt(VRNEnd - VRNStart + 1) + VRNStart;
	        int randomVRN = BaseClass.randomGenerator(1, 9999);
				    DecimalFormat fourdigits = new DecimalFormat("0000");
				    System.out.println(fourdigits.format(randomVRN));
				    String b=fourdigits.format(randomVRN);
//				    Keys Class=RandomClassSelector.getRandomKeys();
//				    driver.getKeyboard().sendKeys(Class);
				    RandomClassSelector.getSchedualVehicleKey();
				    RandomOtherCashKeySelector c= new RandomOtherCashKeySelector();
				    logger.info("Class selection TIme");
//				    if(Class==Keys.F6||Class==Keys.F7) //tasawade
//				    {
//				    	RandomClassSelector.subClassSelector(Class);
//				    	driver.getKeyboard().sendKeys(Keys.ENTER);
//				    }
//				    try	
//				    {
//				    	if(driver.findElementByName("Select sub Vehicle Class For WIM").isDisplayed())
//				    	{ 
				    		Thread.sleep(500);
				    		if(IsWimAvailable.contains("Y"))
				    		{
				    			SelectRandomSubClass.getRandomSelection();
				    		}
				    		
//				    	}
//				    }
//				    catch(org.openqa.selenium.NoSuchElementException r)
//				    {
				    	
//				    }
				    
				    
				    String Vclass=driver.findElementByAccessibilityId("lblClass").getText().toString();
				    String MOP=driver.findElementByAccessibilityId("lblMOP").getText().toString();
				    System.out.println(Is_OverWeight_Applicable.isOverWeightApplicableCheck(Vclass, MOP));
				    logger.info(Vclass.toString()+" Key is selected...");
				    if(Vclass.equals("Vehicle Class"))
				    {
				    	
				    	driver.findElementByAccessibilityId("txtVehicleNo").clear();
				    	logger.info("Vehicle number cleared..");
				    }
				    else
				    {
				    	driver.findElementByAccessibilityId("txtVehicleNo").sendKeys("MH01"+"AA"+b);	
				    	logger.info("Random VRN is Generated :"+b);
				    	OverWeightLogic(Vclass, Weight);
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
				        	i= new ImageVerification();
				            break;
				        case 1: //Card
//				        	CashTransaction.arrowDownZeroTime();
				        	CashTransaction.arrowDownOneTime();
				        	driver.getKeyboard().sendKeys(Keys.ENTER);
				        	logger.info("Card transaction is selected....");
				        	for (int i = 0; i < keysArraygetPaymentTypeIsReference.length; i++)
				        	{
				   			 String key = keysArraygetPaymentTypeIsReference[i];
				   			 if(key.contains("CARD"))
				   			 {
				   				ExplicitQuickWait(By.name("Card Details"));
					        	driver.getKeyboard().sendKeys(b);
					        	driver.getKeyboard().sendKeys(Keys.ENTER);
					        	if(Is_Card_Ref.contains("Y"))
					        	{
					        		driver.getKeyboard().sendKeys(b);
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
				        	i= new ImageVerification();
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
//				        			                          Enter Refernce Number
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
							    	i= new ImageVerification();
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
							    	i= new ImageVerification();
						            break;
				        		case 2:
				        			CashTransaction.arrowDownZeroTime();
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
							    	i= new ImageVerification();
						            break;
				        		default:
						             System.out.println("Invalid random number generated. inside");
				        			
				        		}
				        		break;
				        default:
				             System.out.println("Invalid random number generated. outside");
				    	
				    	
				    	
				    	
				    		
				    	
				    	
				    	}
																															
						
						
						
						
						
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
