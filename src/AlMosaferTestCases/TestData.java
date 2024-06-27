package AlMosaferTestCases;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class TestData {
	WebDriver driver = new ChromeDriver();

	Random rand = new Random();
	String url = "https://global.almosafer.com/en";
	String expectedEnLanguage = "en";
	String expectedArLanguage = "ar";
	String expectedCurrency = "SAR";
	boolean expectedQitafLogo = true;
	String expectedContactNumber = "+966554400000";
	boolean expectedQitafLogoIsAvailable = true;

	@BeforeSuite
	public void setUp() {
		if (driver == null) {
			WebDriver driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
		}

	}
	
	@AfterSuite
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

	public void TheDefultConfiguration() {
		driver.get(url);
		WebElement KSAButton = driver
				.findElement(By.cssSelector(".sc-jTzLTM.hQpNle.cta__button.cta__saudi.btn.btn-primary"));
		KSAButton.click();
	}

}
