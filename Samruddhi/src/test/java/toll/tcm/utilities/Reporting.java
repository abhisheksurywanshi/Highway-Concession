package toll.tcm.utilities;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
//import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
//import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

import toll.tcm.testCases.*;


public class Reporting extends TestListenerAdapter
{
		public ExtentReporter htmlReporter;
		public ExtentReports extent;
		public ExtentTest logger;
		public void onStart(ITestContext testContext)
		{
			String timeStamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
			String repName="Test-Report-"+timeStamp+".html";
			htmlReporter=new ExtentSparkReporter(System.getProperty("user.dir")+"\\test-output\\extent_report\\"+repName);
//			htmlReporter.loadConfig(System.getProperty("user.dir")+"/extent-config.xml");
			
			extent=new ExtentReports();
//			extent.attachReporter(htmlReporter);
			extent.setSystemInfo("HostName", "LocalHost");
			extent.setSystemInfo("Environment", "QA");
			extent.setSystemInfo("Execute By", "Abhishek");
			
//			htmlReporter.config().setDocumentTitle("POS Test Report");
//			htmlReporter.config().setReportName("Functional Automation Test Report");
//			htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
//			htmlReporter.config().setTheme(Theme.DARK);
			
		}
		public void onTestSuccess(ITestResult tr)
		{
			logger=extent.createTest(tr.getName());
			logger.log(Status.PASS,MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));
		}
		public void onTestFailure(ITestResult tr)
		{
			logger=extent.createTest(tr.getName());
			
			logger.log(Status.FAIL,MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));
			String timeStamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
			String screenshotPath=System.getProperty("user.dir")+"\\Screenshots\\"+tr.getName()+timeStamp+".png";
			File f = new File(screenshotPath);
			if(f.exists())
			{
//				try 
//				{
//					logger.fail("Screenshot is below:"+ logger.addScreenCaptureFromPath(screenshotPath));
//				}
//				catch(IOException e)
//				{
//					e.printStackTrace();
//				}
			}
		}
		public void onTestSkipped(ITestResult tr)
		{
			logger=extent.createTest(tr.getName());
			logger.log(Status.SKIP,MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));
		}
		public void onFinish(ITestContext testContext)
		{
			extent.flush();
		}
}
