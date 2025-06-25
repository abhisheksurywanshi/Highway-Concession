package toll.tcm.APIs;

import java.awt.AWTException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

//import io.github.bonigarcia.wdm.WebDriverManager;

public class DashBoard {

	public static WebDriver driver;
	@Test
	public void	 Launch() throws InterruptedException, AWTException
	{
		ChromeOptions option = new ChromeOptions();
//		 option.addArguments("--headless=new");
//		 WebDriverManager.chromedriver().setup();

	        // Initialize a new Chrome browser instance
	         driver = new ChromeDriver(option);

		driver.manage().window().maximize();
	    driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	    driver.get("https://www.fitpeo.com"); //
	}

}
