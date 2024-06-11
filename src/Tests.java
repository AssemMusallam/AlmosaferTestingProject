import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import dev.failsafe.internal.util.Assert;

public class Tests {
	WebDriver driver = new ChromeDriver();
	String url = "https://global.almosafer.com/en";
	String expectedLanguage = "en";
	String expectedCurrency = "SAR";
	String expectedContactNumber = "+966554400000";
	boolean expectedQitafLogoIsAvailable = true;

	@BeforeTest
	public void setup() {
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));

		WebElement KSAButton = driver
				.findElement(By.cssSelector(".sc-jTzLTM.hQpNle.cta__button.cta__saudi.btn.btn-primary"));
		KSAButton.click();
	}

	@Test(priority = 1)
	public void checkTheLanguage() {
		WebElement htmlTag = driver.findElement(By.tagName("html"));
		String actualLanguage = htmlTag.getAttribute("lang");

		org.testng.Assert.assertEquals(actualLanguage, expectedLanguage);
	}

	@Test(priority = 2)
	public void SARCurrency() {
		String actualCurrency = driver.findElement(By.xpath("//button[@data-testid = 'Header__CurrencySelector']"))
				.getText();

		org.testng.Assert.assertEquals(actualCurrency, expectedCurrency);
	}

	@Test(priority = 3)
	public void contactNumber() {
		String actualContactNumber = driver.findElement(By.tagName("strong")).getText();

		org.testng.Assert.assertEquals(actualContactNumber, expectedContactNumber);
	}

	@Test(priority = 4)
	public void checkQitafLogoAvailability() {
		WebElement footerTag =  driver.findElement(By.tagName("footer"));
		boolean actualQitafLogoIsAvailable = footerTag.findElement(By.xpath("//svg[@data-testid = 'Footer__QitafLogo']")).isDisplayed();


		org.testng.Assert.assertEquals(actualQitafLogoIsAvailable, expectedQitafLogoIsAvailable);
	}

}
