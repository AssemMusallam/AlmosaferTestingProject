package AlMosaferTestCases;
import java.time.LocalDate;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class HomePageTestCases extends TestData {

	public void checkTheLanguageTest() {
		WebElement htmlTag = driver.findElement(By.tagName("html"));
		String actualLanguage = htmlTag.getAttribute("lang");

		org.testng.Assert.assertEquals(actualLanguage, expectedEnLanguage);
	}

	public void SARCurrencyTest() {
		String actualCurrency = driver.findElement(By.xpath("//button[@data-testid = 'Header__CurrencySelector']"))
				.getText();

		org.testng.Assert.assertEquals(actualCurrency, expectedCurrency);
	}

	public void contactNumberTest() {
		String actualContactNumber = driver.findElement(By.tagName("strong")).getText();
		
		org.testng.Assert.assertEquals(actualContactNumber, expectedContactNumber);
	}
	
	public void checkQitafLogoAvailabilityTest() {
		WebElement footerTag 	= driver.findElement(By.tagName("footer"));
		boolean actualQitafLogo = footerTag.findElement(By.cssSelector(".sc-fihHvN.eYrDjb"))
				.findElement(By.tagName("svg")).isDisplayed();

		org.testng.Assert.assertEquals(actualQitafLogo, expectedQitafLogo);
	}
	
	public void checkHotelIfNotSelectedTest() {
		String ExpectedResult = "false";
		String ActualResult = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"))
				.getAttribute("aria-selected");
		org.testng.Assert.assertEquals(ActualResult, ExpectedResult);
	}
	
	public void checkDepartureAndReturnDatesTest() {
		LocalDate today = LocalDate.now();
		int ExpectedDepartureDate = today.plusDays(1).getDayOfMonth();
		int ExpectedReturnDate = today.plusDays(2).getDayOfMonth();

		int ActualDepartureDate = Integer.parseInt(driver
				.findElement(By.cssSelector("div[class='sc-iHhHRJ sc-kqlzXE blwiEW'] span[class='sc-cPuPxo LiroG']"))
				.getText());
		int ActualReturnDate = Integer.parseInt(driver
				.findElement(By.cssSelector("div[class='sc-iHhHRJ sc-OxbzP edzUwL'] span[class='sc-cPuPxo LiroG']"))
				.getText());

		org.testng.Assert.assertEquals(ActualDepartureDate, ExpectedDepartureDate);
		org.testng.Assert.assertEquals(ActualReturnDate, ExpectedReturnDate);

	}

	public void ChangeTheLanguageOfTheWebsiteRandomlyTest() {
		String[] websites = { "https://global.almosafer.com/en", "https://global.almosafer.com/ar" };
		int randomIndex = rand.nextInt(websites.length); // range 0 and 1

		if (driver.getCurrentUrl().contains("en")) {
			WebElement htmlTag = driver.findElement(By.tagName("html"));
			String actualLanguage = htmlTag.getAttribute("lang");

			org.testng.Assert.assertEquals(actualLanguage, expectedEnLanguage);
		} else if (driver.getCurrentUrl().contains("ar")) {
			WebElement htmlTag = driver.findElement(By.tagName("html"));
			String actualLanguage = htmlTag.getAttribute("lang");

			org.testng.Assert.assertEquals(actualLanguage, expectedArLanguage);
		}
	}
}
