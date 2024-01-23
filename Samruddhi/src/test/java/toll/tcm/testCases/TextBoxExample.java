package toll.tcm.testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TextBoxExample {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "D:\\Eclipse\\Hydrabad\\driver\\chromedriver.exe");

        // Create a WebDriver instance (in this case, using Chrome)
        WebDriver driver = new ChromeDriver();

        // Navigate to the webpage containing the text box
        driver.get("https://google.com");

        // Find the text box element using its HTML attribute (e.g., id, name, etc.)
        WebElement textBox = driver.findElement(By.id("APjFqb"));
        driver.findElement(By.id("APjFqb")).sendKeys("123");
        // Get the default text from the text box
        String defaultText = textBox.getAttribute("value");

        // Print the default text
        System.out.println("Default Text: " + defaultText);


	}

}
