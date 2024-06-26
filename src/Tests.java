import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import org.checkerframework.checker.units.qual.s;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import dev.failsafe.internal.util.Assert;

public class Tests {


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

		org.testng.Assert.assertEquals(actualLanguage, expectedEnLanguage);
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
		WebElement footerTag = driver.findElement(By.tagName("footer"));
		boolean actualQitafLogo = footerTag.findElement(By.cssSelector(".sc-fihHvN.eYrDjb"))
				.findElement(By.tagName("svg")).isDisplayed();

		org.testng.Assert.assertEquals(actualQitafLogo, expectedQitafLogo);
	}

	@Test(priority = 5)
	public void checkHotelIfNotSelected() {
		String ExpectedResult = "false";
		String ActualResult = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"))
				.getAttribute("aria-selected");
		org.testng.Assert.assertEquals(ActualResult, ExpectedResult);
	}

	@Test(priority = 6)
	public void checkDepartureAndReturnDates() {
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

	@Test(priority = 7)
	public void ChangeTheLanguageOfTheWebsiteRandomly() {
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

	@Test(priority = 8)
	public void HotelSelection() {
		WebElement HotelTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
		HotelTab.click();

		WebElement searchHotel = driver.findElement(By.cssSelector(".sc-phbroq-2.uQFRS.AutoComplete__Input"));

		if (driver.getCurrentUrl().contains("en")) {
			String[] CitiesInEnglish = { "dubai", "jeddah", "riyadh" };
			int randomIndex = rand.nextInt(CitiesInEnglish.length);
			searchHotel.sendKeys(CitiesInEnglish[randomIndex]);

		} else if (driver.getCurrentUrl().contains("ar")) {
			String[] CitiesInArabic = { "دبي", "جدة" };
			int randomIndex = rand.nextInt(CitiesInArabic.length);
			searchHotel.sendKeys(CitiesInArabic[randomIndex]);

		}
	}

	@Test(priority = 9)
	public void SelectNumberOfPeople() {
		WebElement SelectRooms = driver
				.findElement(By.xpath("//select[@data-testid = 'HotelSearchBox__ReservationSelect_Select']"));
		Select Selector = new Select(SelectRooms);

		int randomIndex = rand.nextInt(2);
		Selector.selectByIndex(randomIndex);

		WebElement searchButton = driver.findElement(By.xpath("//button[@data=testid='HotelSearchBox__SearchButton']"));
		searchButton.click();

	}
	
	@Test(priority = 10)
	public void checkPageIfFullyLoaded() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		
		WebElement resultsTab = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@data-testid='HotelSearchResult__resultsFoundCount']")));
		
		org.testng.Assert.assertEquals(resultsTab.getText().contains("found")||resultsTab.getText().contains("وجدنا"),true);)

	}
	
	
	@Test(priority = 12)
	public void sortItems() throws InterruptedException {
		WebElement LowestPriceButton = driver.findElement(By.id("//button[@data-testid='HotelSearchResult__sort__LOWEST_PRICE']"));
		LowestPriceButton.click();
		
		Thread.sleep(2000);
		
		WebElement pricesContainer = driver.findElement(By.cssSelector(".sc-htpNat.KtFsv.col-9"));
		
		List<WebElement> prices = pricesContainer.findElements(By.className("Price__Value"));
		
		int firstPrice = Integer.parseInt( prices.get(0).getText());
		
		int lastPrice = Integer.parseInt(prices.get(prices.size()-1).getText());
		
		org.testng.Assert.assertEquals(firstPrice<lastPrice, true);
		}

}
