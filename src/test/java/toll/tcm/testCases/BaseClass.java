package toll.tcm.testCases;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.net.Priority;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.IHookCallBack;
import org.testng.IHookable;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;
import io.appium.java_client.functions.ExpectedCondition;
import io.appium.java_client.windows.WindowsDriver;
import io.appium.java_client.windows.WindowsElement;
import toll.tcm.APIs.GetBlacklistTag;
import toll.tcm.Database.DatabaseConnectivity;
import toll.tcm.Database.GetExemptType;
import toll.tcm.Database.GetWimSubClass;
import toll.tcm.Hardware.COM_Setup;
import toll.tcm.utilities.ExetentReportPass;
import toll.tcm.utilities.ReadConfig;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
//import toll.snc.tcm.utilities.ReadConfig;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.fazecast.jSerialComm.*;


public class BaseClass extends StaticVariables
{	
	static Logger logger=LogManager.getLogger(BaseClass.class);
//	@Parameters("browser")
	@BeforeSuite
	public void getdataBaseData() throws Exception
	{
		
		
		 
		 
//		ExtentTest DatabaseConnectivity=extent.createTest("DatabaseConnectivity");
		
		capabilities = new DesiredCapabilities();
	
		connection = DriverManager.getConnection(DataBaseurl, DatBaseusername, DatBasepassword);
//		 DatabaseConnectivity.pass("Database Connected Successfully");
		 GetBlacklistTag g= new GetBlacklistTag();
		

	    DatabaseConnectivity d= new DatabaseConnectivity();
	    
			d.getTollInfo();
			
			
			spark.config().setTheme(Theme.DARK);
			 spark.config().setDocumentTitle(Toll_Name+" Automation Report");
			 spark.config().setReportName("TCM");
			 extent.attachReporter(spark);
			 
			 
			logger.info("Is_LSDU:"+Is_LSDU);
//			DatabaseConnectivity.pass("Is_LSDU:"+Is_LSDU);
//			if(Is_LSDU.contains("L"))
//			{
//				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/WebDrivers/chromedriver.exe");
//				webdriver= new ChromeDriver();
//				webdriver.get("http://10.10.1.185/LSDU");
//				webdriver.manage().window().maximize();
//				 JavascriptExecutor jsExecutor = (JavascriptExecutor) webdriver;
//				 
//					 double zoomFactor = 0.6; // Adjust this value as needed
//				        String script = String.format("document.body.style.zoom = '%.2f'", zoomFactor);
////				      WebElement  elementToClick =webdriver.findElement(By.xpath("//a[contains(text(),' Sign in')]"));
////				        jsExecutor.executeScript(script);
//				        
//				        Actions actions = new Actions(webdriver);
//				        actions.sendKeys(Keys.TAB).perform();
//				        actions.sendKeys(Keys.TAB).perform();
//				        actions.sendKeys(Keys.ENTER).perform();
////				        actions.moveToElement(elementToClick).click().perform();
//				        webdriver.findElement(By.id("UserID")).sendKeys("ADMIN");
//				        webdriver.findElement(By.id("Password")).sendKeys("121212"); 
//				        webdriver.findElement(By.id("signin")).click();
//				         script = String.format("document.body.style.zoom = '%.2f'", 0.3);
////				        WebDriverWait wait = new WebDriverWait(webdriver, 10);
////				        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.id("//a[contains(text(),' Sign in')]")));
////
////				        		elementToClick.click();
//				 
//			}
			
//			SchedualClassType=KeyMapping.getSchedualVehicleKeys().size();
//			NonSchedualClassType=KeyMapping.getNonSchedualVehicleKeys().size();
			
			
			
			
			
			
			ExemptType=new String[ExemptTypes];
			for(int i=0;i<ExemptTypes;i++)
			{
				ExemptType[i]=(String) GetExemptType.getExemptType().get(i);
			}
			logger.info("Exempt Types and Wim Sub Type Noted....");
//			DatabaseConnectivity.pass("Exempt Types and Wim Sub Type Noted....");
			//avc com setup
			
	        logger.info("lane ip:"+LaneIPAddress+" Port "+Port);		
//	        DatabaseConnectivity.info("lane ip:"+LaneIPAddress+" Port "+Port);	
			capabilities.setCapability("app", url);
//			capabilities.setCapability("appTopLevelWindow", "Enter  Remark Here...");
//			 logger.info("capabilities set");	
//			Thread.sleep(5000);
			try 
			{
				driver=new WindowsDriver(new URL("http://127.0.0.1:4723"),capabilities);
				logger.info("driver is initialize");
			}
			catch(org.openqa.selenium.SessionNotCreatedException e)
			{
				driver=new WindowsDriver(new URL("http://127.0.0.1:4723"),capabilities);
				logger.info("driver is initialize with SessionNotCreatedExcweption");
			}
			   
			

	}
	
	
	@BeforeTest
	public void login() throws Exception
	{
		ExtentTest login=extent.createTest("login");
		boolean HardwareChangeFound=false;
		boolean CheckingRemarkFound=false;
		boolean UserIDFound=false;
		String parent=driver.getWindowHandle();
		if(!IsAutoLane)
		{

			
			do 
			{
				try
				{
					
					ExplicitWait(By.xpath("//Window[@Name='Login'][starts-with(@AutomationId,'frmLogin')]//Edit[@AutomationId='txtUserID']"));
					logger.info("first try block block");
					login.info("first try block block");
					visible=true;
				}
				catch(Exception e)
				{
					capabilities.setCapability("app", url);
					driver=new WindowsDriver(new URL("http://127.0.0.1:4723"),capabilities);
					try
					{
						ExplicitWait(By.xpath("//Window[@Name='Login'][starts-with(@AutomationId,'frmLogin')]//Edit[@AutomationId='txtUserID']"));
						UserIDFound=true;
						visible=true;
						if(UserIDFound)
						{
							try 
							{
								visible=driver.findElementByXPath("//Window[@Name='Login'][starts-with(@AutomationId,'frmLogin')]//Edit[@AutomationId='txtUserID']").isDisplayed();
							}
							catch(org.openqa.selenium.NoSuchElementException r)
							{
								
							}
						}
					}catch(org.openqa.selenium.NoSuchElementException r)
					{
						
					}
					
					
					
					
				}
			}while(!visible);
//			ExplicitWait(By.name("OK")); 
			parent=driver.getWindowHandle();
//			WindowsDriver<WindowsElement> driver = new WindowsDriver<WindowsElement>(new URL("http://127.0.0.1:4723"),capabilities);
//			driver.switchTo().window(parent);
			logger.info("Application Started....");
//			//login.pass("Application Started....");
			 ExetentReportPass test;
			 test= new ExetentReportPass(login,"Login Window","application started ","This stage Login ID and Password is null");
			try 
			{
				if(!HardwareChangeFound)
				{
					driver.findElementByName("OK").click();
				}
			}
			catch(org.openqa.selenium.NoSuchElementException e)
			{
				try 
				{
					if(!CheckingRemarkFound)
					{
						driver.findElementByName("Enter  Remark Here...").sendKeys("Testt");
						driver.getKeyboard().sendKeys(Keys.ENTER);
					}
				}
				catch(org.openqa.selenium.NoSuchElementException e2)
				{
					
				}
			}
			
				
				
		    
			
			

			

//			driver.getKeyboard().sendKeys(Keys.ENTER);
//			Thread.sleep(2000);
//			IAVC i=new IAVC();
//			i.getIAVC(5);			
			driver.findElementByAccessibilityId("txtUserID").sendKeys(username);
			driver.findElementByAccessibilityId("txtPassword").sendKeys(password);
			
			 test= new ExetentReportPass(login,"Login Window","login credentials  ","This stage Login ID is "+username+" and Password is "+password);
			driver.getKeyboard().sendKeys(Keys.ENTER);
			if(Is_LSDU=="L")
			{
				webdriver= new ChromeDriver();
				webdriver.findElement(By.xpath("//h2[contains(text(),'Login/Logout')]/../..//button[@id='authorisey']")).click();
//				
				
			}
			WebDriverWait wait=new WebDriverWait(driver, 5);
			logger.info("after click login");
//			login.info("after click login");
			try
			{
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.name("Cancel")));
				
			}
			catch(org.openqa.selenium.TimeoutException r)
			{
				logger.warn("Cancel button invisiblity is skipped");
				login.warning("Cancel button invisiblity is skipped");
			}	
			
//			wait.until(ExpectedConditions.presenceOfElementLocated(By.name("Vehicle Number/Barcode")) );

//			Thread.sleep(20000);
		}
		    parent=driver.getWindowHandle();
			mainWindowToCurrentWindow(driver, parent);
			try 
			{
				if(driver==null)
				{
					capabilities.setCapability("app", url);
					driver=new WindowsDriver(new URL("http://127.0.0.1:4723"),capabilities);
					logger.error("driver was null");
				}
//				driver.switchTo().window(driver.getWindowHandle());

				mainWindowToCurrentWindow(driver, parent);  
//				ExplicitWait(By.name("Vehicle Number/Barcode"));
				boolean visible =false;
				
					ExplicitWait(By.name("Waiting for New transaction"));
					
				
				
				
				Home_Page_Window=driver.getWindowHandle();
//				logger.info("ETC IP port connecting");
				login.info("ETC IP port connecting");
				etcConnect(login);
				
			}
			catch(Exception e)
			{
				
				mainWindowToCurrentWindow(driver, parent);
//				driver.switchTo().window(driver.getWindowHandle());
				
				try 
				{
					ExplicitWait(By.name("Vehicle Number/Barcode"));
					ExplicitWait(By.name("Waiting for New transaction"));
					logger.info("try block for waiting for trasaction element");
				}
				catch(org.openqa.selenium.TimeoutException p)
				{
					
						logger.error("AVC Connection failed");
						
					
				}
				logger.info("ETC IP port connecting");
				login.info("ETC IP port connecting");
				etcConnect(login);
			}
		try 
		{
			mainWindowToCurrentWindow(driver, parent);
			this.parent=parent;
			if(driver.findElementByName("Vehicle Number/Barcode").isDisplayed())  //&&driver.findElementByAccessibilityId("txtVehicleNo").isSelected()
				logger.info("Login Successfully....");
//				login.info("Login Successfully....");
				
		}	
		catch(Exception e)
		{
				e.printStackTrace();
				logger.warn("Login unSuccessfully!! or unable to find element");
				login.warning("Login unSuccessfully!! or unable to find element");
		}
		
			  
		
		
	}
	public static void mainWindowToCurrentWindow(WindowsDriver driver,String parent,ExtentTest mainwindowToCurrent) throws InterruptedException
	{
		
		for (String winHandle : driver.getWindowHandles())
		{
			
			if(!winHandle.equals(parent))
			{
		    driver.switchTo().window(winHandle); 
//		    Thread.sleep(1000);
		    logger.info("Move to the current window....");
//		    mainwindowToCurrent.info("Move to the current window....");
		    break;
		   
			}
		}
		}
		public static void mainWindowToCurrentWindow(WindowsDriver driver,String parent) throws InterruptedException
		{
			
			for (String winHandle : driver.getWindowHandles())
			{
				logger.info("window-"+winHandle);
				if(!winHandle.equals(parent))
				{
			    driver.switchTo().window(winHandle); 
//			    Thread.sleep(1000);
			    logger.info("Move to the current window....");
			   
			    break;
			   
				}
			}
	}
	
	
	@AfterClass
	public void close() throws Exception
	{
		ExtentTest Close=extent.createTest("Close");
		ExetentReportPass test;
		String parent =driver.getWindowHandle();
		driver.getKeyboard().sendKeys(Keys.ESCAPE);
		Robot robot = new Robot();
		if(!IsAutoLane)
		{
			robot.keyPress(36);
			robot.keyRelease(36);
		}
		else
		{
			driver.getKeyboard().sendKeys(Keys.chord(Keys.CONTROL,Keys.HOME));
			driver.getKeyboard().sendKeys(Keys.chord(Keys.CONTROL,Keys.HOME));

		}
		try  
		{
			if(driver.findElementByName("Vehicle pending in queue.").isDisplayed())
			{
				logger.info("Vehicle pending in queue pop up display try if condition apply ");
				driver.getKeyboard().sendKeys(Keys.ENTER);
				boolean v;
				do
				{
					logger.info("do---------");
					driver.findElementByAccessibilityId("Vehicle Number/Barcode").click();
//					Robot robot = new Robot();
//					robot.keyPress(KeyEvent.VK_CONTROL);
//					robot.keyPress(KeyEvent.VK_END);
//					Thread.sleep(1000);
//					robot.keyRelease(KeyEvent.VK_CONTROL);
//		            robot.keyRelease(KeyEvent.VK_END);
					driver.getKeyboard().sendKeys(Keys.chord(Keys.CONTROL,Keys.END));
					driver.getKeyboard().sendKeys(Keys.chord(Keys.CONTROL,Keys.END));
					
					if(!IsAutoLane)
					{
						robot.keyPress(36);
						robot.keyRelease(36);
					}
					else
					{
						driver.getKeyboard().sendKeys(Keys.chord(Keys.CONTROL,Keys.HOME));
						driver.getKeyboard().sendKeys(Keys.chord(Keys.CONTROL,Keys.HOME));
					}
					v=driver.findElementByName("Vehicle pending in queue - 0").isDisplayed();
				}while(v);
				
//				Actions actions = new Actions(driver);
//				actions.keyDown(Keys.CONTROL).sendKeys(Keys.CONTROL).keyUp(Keys.END).perform();
//				actions.keyDown(Keys.CONTROL).sendKeys(Keys.CONTROL).keyUp(Keys.END).perform();
//				driver.getKeyboard().sendKeys(Keys.chord(Keys.CONTROL,Keys.END));
//				driver.getKeyboard().sendKeys(Keys.chord(Keys.CONTROL,Keys.END));
			
			}
			logger.info("try block");
		}
		catch(org.openqa.selenium.NoSuchElementException r)
		{
			logger.info("catch block for queue pending pop up not found");
		}
		
		WebDriverWait wait=new WebDriverWait(driver, 5);
		
		do 
		{   
			try
			{
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.name("Waiting for New transaction")));
				visible=true;
				logger.info("first try block invisibility of waiting for new transaction  ");
				
				mainWindowToCurrentWindow(driver, parent,Close);
			}
			catch(TimeoutException e)
			{
				try 
				{
					mainWindowToCurrentWindow(driver, parent,Close);
					visible=driver.findElementByName("Cancel").isDisplayed();
					logger.info("Second try block");
					
				}
				catch(org.openqa.selenium.NoSuchElementException r)
				{
					logger.info("last catch block");
					
				}
				
				logger.info(visible);
			}
		}while(!visible);
//		logger.info(visible+" ok");
//		driver.getKeyboard().sendKeys(Keys.NUMPAD1);
//		String default_value=driver.findElement(By.xpath("//Edit[@AutomationId='txtUserID'")).getAttribute("value");
//		logger.info(default_value);
//		if(default_value.contains("1"))
//				{
//					logger.info("default selection is correct");
//				}
//		else
//		{
//			logger.info("default selection is incorrect");
//		}
//		logger.info("ooo");
		
		mainWindowToCurrentWindow(driver, parent,Close);
		
		if(!driver.findElementByAccessibilityId("txtUserID").isSelected())
		{
			test= new ExetentReportPass(Close,"After Home Key Press Window","Login Window is opened","In This stage Logout is done by Operator");

			ExplicitWait(By.xpath("//Button[@AutomationId='btnCancel']"));
			driver.getKeyboard().sendKeys(Keys.NUMPAD1);
//			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//Edit[@AutomationId='txtUserID'")));
//			logger.info("ooooo"+wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//Edit[@AutomationId='txtUserID'"))).toString());
//			mainWindowToCurrentWindow(driver, parent);  
			
//			List windows = new ArrayList(driver.getWindowHandles());
			
			driver.findElementByXPath("//Button[@AutomationId='btnCancel']").click();
//			test= new ExetentReportPass(Close,"After Cancel Key Press Window","Maintain logout Window is opened","In This stage Logout is done by Maintaince operator");
			
//			driver.getKeyboard().sendKeys(Keys.chord(Keys.ALT,Keys.TAB));
			mainWindowToCurrentWindow(driver, parent);  
//			driver.findElement(By.xpath("//Window[@AutomationId='frmCloseApplication'][@Name='Close Application']//Pane[@AutomationId='pnlPassword']//Edit[@AutomationId='txtUserID']")).click();
//			driver.getKeyboard().sendKeys(Keys.ENTER);
	//		\"][@Name=\"Close Application\"]/Pane[@AutomationId=\"pnlPassword\"]/Edit[@AutomationId=\"txtUserID\"]"
			driver.findElement(By.xpath("//Window[@AutomationId='frmCloseApplication'][@Name='Close Application']//Pane[@AutomationId='pnlPassword']//Edit[@AutomationId='txtUserID']")).sendKeys(Maintanance_ID);
//			driver.getKeyboard().sendKeys(Keys.ENTER);
			System.out.println(Maintanance_ID);
			driver.findElement(By.xpath("//Window[@AutomationId='frmCloseApplication'][@Name='Close Application']//Pane[@AutomationId='pnlPassword']//Edit[@AutomationId='txtPassword']")).sendKeys("121212");
			driver.findElement(By.xpath("//Window[@AutomationId='frmCloseApplication'][@Name='Close Application']//Pane[@AutomationId='pnlPassword']//Edit[@AutomationId='txtPassword']")).click();
			
			test= new ExetentReportPass(Close,"After enter credential for Maintaince  Window","Maintain logout Window is Done","In This stage Logout is done by Maintaince operator");
			driver.findElement(By.xpath("//Window[@AutomationId='frmCloseApplication'][@Name='Close Application']//Pane[@AutomationId='pnlPassword']//Edit[@AutomationId='txtPassword']")).click();
			driver.getKeyboard().sendKeys(Keys.ENTER);
			driver.getKeyboard().sendKeys(Keys.ENTER);
//			driver.findElementByAccessibilityId("txtUserID").sendKeys("99999");
//			driver.findElementByAccessibilityId("txtPassword").sendKeys("121212");
//			driver.findElementByName("Ok").click();
//			driver.findElementByName("Enter  Remark Here...").sendKeys("Testing logout");
//			driver.findElementByName("Enter  Remark Here...").isSelected();
//			driver.getKeyboard().sendKeys(Keys.ENTER);
		}
		else
		{
	
			parent=driver.getWindowHandle();
			mainWindowToCurrentWindow(driver, parent);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("Cancel")));
//			logger.info(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//Edit[@AutomationId='txtUserID'"))).toString());
			driver.getKeyboard().sendKeys(Keys.NUMPAD1);
			
			logger.info("default selection is wrong...");
			driver.getKeyboard().sendKeys(Keys.TAB);
			driver.getKeyboard().sendKeys(Keys.TAB);
			driver.getKeyboard().sendKeys(Keys.TAB);
//			driver.getKeyboard().sendKeys(Keys.TAB);
//			driver.getKeyboard().sendKeys(Keys.TAB);
			driver.getKeyboard().sendKeys(Keys.ENTER);
			
//			driver.findElementByName("Cancel").click();
//			driver.getKeyboard().sendKeys(Keys.ENTER);
//			driver.findElement(By.xpath("//Window[@Name='Close Application'][@AutomationId='frmCloseApplication']//Pane[starts-with(@AutomationId,'panel')]//Edit[@AutomationId='txtUserID']")).click();
//			driver.findElement(By.xpath("//Window[@Name='Close Application'][@AutomationId='frmCloseApplication']//Pane[starts-with(@AutomationId,'panel')]//Edit[@AutomationId='txtUserID']")).sendKeys("99999");
//			driver.getKeyboard().sendKeys(Keys.ENTER);
//			driver.findElement(By.xpath("//Window[@Name='Close Application'][@AutomationId='frmCloseApplication']//Pane[starts-with(@AutomationId,'panel')]//Edit[@AutomationId='txtPassword']")).sendKeys("121212");
//			driver.getKeyboard().sendKeys(Keys.ENTER);
			mainWindowToCurrentWindow(driver, parent,Close);
			driver.findElementByName("Application Exit Authorization").click();
			driver.getKeyboard().sendKeys(Maintanance_ID);
			System.out.println(Maintanance_ID);
			driver.getKeyboard().sendKeys(Keys.TAB);
			driver.getKeyboard().sendKeys("121212");
			driver.getKeyboard().sendKeys(Keys.ENTER);
			driver.getKeyboard().sendKeys(Keys.ENTER);
			mainWindowToCurrentWindow(driver, parent,Close);
//			driver.getKeyboard().sendKeys("Testing logout");
//			driver.getKeyboard().sendKeys(Keys.ENTER);
//			driver.findElementByAccessibilityId("txtUserID").sendKeys("99999");
//			driver.findElementByAccessibilityId("txtPassword").sendKeys("121212");
//			mainWindowToCurrentWindow(driver, parent);
//			driver.findElementByName("Ok").click();
//			mainWindowToCurrentWindow(driver, driver.getWindowHandle());
//			driver.findElementByName("Enter  Remark Here...").sendKeys("Testing logout");
//			logger.info(driver.findElementByName("Enter  Remark Here...").isSelected());
//			driver.getKeyboard().sendKeys(Keys.ENTER);
			
		}

	}
	@AfterSuite
	public void otherClose() throws IOException, SQLException
	{
		extent.flush();
		if(serialPortForAVC!=null)
			serialPortForAVC.close();
		if(serialPortForExitAVC!=null)
			serialPortForExitAVC.close();
		if(serialPortForWeight!=null)
			serialPortForWeight.close();
		if(outputStreamForTag!=null)
			outputStreamForTag.close();
		if(inputStreamForTag!=null)
			inputStreamForTag.close();
		if(connection!=null)
			connection.close();
	}
	public  static int randomGenerator(int start,int end)
	{
		random=new Random();
        int randomnumber = random.nextInt(end - start + 1) + start;
		return randomnumber;
	}
	
	public static void ExplicitWait(By WinElement)
	{
		WebDriverWait wait= new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(WinElement));
	}
	public static void ExplicitWaitForTag(By WinElement)
	{
		WebDriverWait wait= new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(WinElement));
	}
	public static void ExplicitQuickWait(By WinElement)
	{
		WebDriverWait wait= new WebDriverWait(driver, (int) (1 / 1000), 1);
		wait.until(ExpectedConditions.visibilityOfElementLocated(WinElement));
	}
	public static void captureScreen(WebDriver driver,String tname) throws Exception
	{
		TakesScreenshot scrShot=((TakesScreenshot)driver);
		File scrFile=scrShot.getScreenshotAs(OutputType.FILE);
		String timeStamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());		
		File DestFile=new File(System.getProperty("user.dir")+"/Screenshots/"+tname+timeStamp+".png");
		FileUtils.copyFile(scrFile, DestFile);
		logger.info("screenshot taken");
	}
	public static byte[] hexStringToByteArray(String hexString) {
        int len = hexString.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4)
                    + Character.digit(hexString.charAt(i + 1), 16));
        }
        return data;
    }
	
	public static void fetchAllExcel(XSSFWorkbook xw ,XSSFSheet sheet) throws Exception
	  {    
		 int rowcount=sheet.getLastRowNum();
		 int count=0;
		  for(int i=0;i<=rowcount;i++)
		  {
			  
			  int colcount=sheet.getRow(i).getLastCellNum();
			  XSSFRow currentrow=sheet.getRow(i);
			  for(int j=0;j<colcount;j++)
			  {
				  
				  XSSFCell value=currentrow.getCell(j);
				  if(value==null)
				  {
					  
					  System.out.print("NULL ");
				  }  
				  else
				  {
					  switch(value.getCellType())
					  {
					  case STRING:  System.out.print(value.getStringCellValue().replace(".", "")+" ");count++;break;
					  case NUMERIC: System.out.print(value.getNumericCellValue()+" "); break;
					  case BOOLEAN: System.out.print(value.getBooleanCellValue()+" "); break;
					  
					  }
					  System.out.print("|");
				  }				  
			  }
			  
		  }
		  logger.info("----------------------------"+rowcount+" "+count);
	  }
	
	public static String getShiftDate(Cell cell) {
        if (cell == null) {
            return null;
        }

        if (cell.getCellType() == CellType.NUMERIC) {
            if (DateUtil.isCellDateFormatted(cell)) {
                // If the cell contains a date value, convert it to a Date object
            	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy");
            	
                Date date = cell.getDateCellValue();
                return dateFormat.format(date);
            } else {
                double numericValue = cell.getNumericCellValue();
                long longValue = (long) numericValue;
                if (numericValue == longValue) {
                    // If the numeric value is an integer (no decimal part), return it as a String.
                    return null;
                }
            }
        }

        return null;
    }
    public static String formatDateToString(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy hh:mm:ss"); // You can choose any desired date format
        return dateFormat.format(date);
    }
    
    
    public static ArrayList transactionRange(int FromTrNo, int ToTrNo) throws ParseException, IOException
	{
    	FileInputStream fls=new FileInputStream(System.getProperty("user.dir")+"/src/test/java/toll/snc/tcm/testData/KTTRL DATA.xlsx");
		   xw= new XSSFWorkbook(fls);
		  sheet=xw.getSheet("TOTAL");
		int count=0;
		int rowcount=sheet.getLastRowNum();
		ArrayList<String> n=new ArrayList<String>();
		
		 for(int i=FromTrNo ;i<=ToTrNo;FromTrNo++)
			{
//			  if(!timeCompaire(getCellValueAsString(sheet.getRow(FromTrNo).getCell(9)),getCellValueAsString(sheet.getRow(ToTrNo).getCell(9))))
			  if(ToTrNo!=0)
			  {
				  count++;
				  String vehicle_cd=sheet.getRow(i).getCell(3).toString().replace(" ", "").replace(".0", "");
				  logger.info(sheet.getRow(i).getCell(3).toString().replace(" ", "").replace(".0", ""));
				  n.add(vehicle_cd);
					
			  }
			  
			  
			}
		 if(ToTrNo!=0)
		 {
			 return n;
		 }
		  else
		  {
//			  logger.info("limit Reach");
			  return null;
		  }
	}
	
	
	
	
	
	
	
	

	public static String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return null;
        }

        if (cell.getCellType() == CellType.NUMERIC) {
            if (DateUtil.isCellDateFormatted(cell)) {
                // If the cell contains a date value, convert it to a Date object
//            	LocalTime time = LocalTime.parse(cell.toString(), DateTimeFormatter.ofPattern("HH:mm:ss"));
//            	String formattedTime = time.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
            	
            	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy HH.mm.ss");
            	
                Date date = cell.getDateCellValue();
                return dateFormat.format(date);
            } else {
                double numericValue = cell.getNumericCellValue();
                long longValue = (long) numericValue;
                if (numericValue == longValue) {
                    // If the numeric value is an integer (no decimal part), return it as a String.
                    return null;
                }
            }
        }

        return null;
    }
	
	public static void limitDate(String date,XSSFWorkbook xw,XSSFSheet sheet)
	{
		
		int rowcount=sheet.getLastRowNum();
		ArrayList<String> n=new ArrayList<String>();
		for(int i=1;i<=rowcount;i++)
		 {
			
			
				  XSSFCell value=sheet.getRow(i).getCell(3);
				  
				  if(value!=null)
				  {					  
					  int Vehicle_CD=Integer.valueOf(value.toString().replace(" ", "").replace(".0", ""));
					  logger.info(Vehicle_CD);
					  logger.info(getCellValueAsString(sheet.getRow(i).getCell(9)).toString()+" ------ "+date);
					  n.add(String.valueOf(Vehicle_CD));
				  }  
				  else
				  {
					  System.out.print("Null ");
				  }
				 				
			 if(getCellValueAsString(sheet.getRow(i).getCell(9)).toString().equals(date))
			 {
				 logger.info("limit reach");
				 break;
			 }
			 
		 }
	}
	public static boolean  timeCompaire(String startDate ,String LimitDate ) throws ParseException
	{
	        // Compare the current date with the start and end dates using the <= operator
	        if (startDate.toString().equals((LimitDate))) {
	            logger.info("Current date is within the range.");
	            logger.info(startDate+" -------"+LimitDate);
	            
	            	return true;
	        } else {
	            logger.info("Current date is outside the range.");
	            logger.info(startDate+"------ "+LimitDate);
	            return false;
	        }	        
	        
	}
	 public static String generateRandomString(int length) {
	        Random random = new Random();
	        StringBuilder stringBuilder = new StringBuilder();

	        String characters = "0123456789ABCDEF";
	        int charactersLength = characters.length();

	        for (int i = 0; i < length; i++) {
	            int randomIndex = random.nextInt(charactersLength);
	            char randomChar = characters.charAt(randomIndex);
	            stringBuilder.append(randomChar);
	        }

	        return stringBuilder.toString();
	    }
	 public static WebElement Fluentwait(WebElement locator,int timeOut,int pollingTime)
	 {
		 Wait <WebDriver> wait= new FluentWait<WebDriver>(driver)
				 .withTimeout(Duration.ofSeconds(timeOut))
				 .pollingEvery(Duration.ofMillis(pollingTime))
				 .ignoring(NoSuchElementException.class)
				 .ignoring(StaleElementReferenceException.class)
				 .withMessage("TimeOut is Done ... Element not found..."+locator);
		  return wait.until(ExpectedConditions.visibilityOf(locator));
	 }
	 public static void etcConnect(ExtentTest login) throws IOException 
	 {
		 AVC_socket_Connect(login);
		 int port=Integer.valueOf(4001);
		 try {
			Tag_socket = new Socket(tagReaderIPaddress, port);
		} catch (UnknownHostException e) {
			logger.warn("socket error not able to connnect HHD");
			login.warning("socket error not able to connnect HHD");
			e.printStackTrace();
		} catch (IOException e) {
			logger.warn("socket error not able to connnect HHD");
			login.warning("socket error not able to connnect HHD");
			e.printStackTrace();
		}
		 outputStreamForTag = Tag_socket.getOutputStream();
		 inputStreamForTag = Tag_socket.getInputStream();
		

	 }
	 public static void AVC_socket_Connect(ExtentTest login) throws IOException
	 {
		 if(ProfilerPortNo!=null)
		 {
			 int port=Integer.valueOf(4001);
			 try {
				AVC_Socket = new Socket(tagReaderIPaddress, port);
			} catch (UnknownHostException e) {
				logger.warn("socket error not able to connnect AVC");
				login.warning("socket error not able to connnect AVC");
				e.printStackTrace();
			} catch (IOException e) {
				logger.warn("socket error not able to connnect AVC");
				login.warning("socket error not able to connnect AVC");
				e.printStackTrace();
			}
			 outputStreamForAVC = AVC_Socket.getOutputStream();
			 inputStreamForAVC = AVC_Socket.getInputStream();
		 }
	 }
	 public static void arrowDownOneTime() {
			driver.getKeyboard().sendKeys(Keys.ARROW_DOWN);
		}

		public static void arrowDownTwoTime() {
			driver.getKeyboard().sendKeys(Keys.ARROW_DOWN);
			driver.getKeyboard().sendKeys(Keys.ARROW_DOWN);
		}

//		public static void arrowDownZeroTime() {
//			driver.getKeyboard().sendKeys(Keys.ARROW_UP);
//		}
		
		
		
		
}
