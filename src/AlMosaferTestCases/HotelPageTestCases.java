package AlMosaferTestCases;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HotelPageTestCases extends TestData{

	public void HotelSelectionTest() {
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

	public void SelectNumberOfPeopleTest() {
		WebElement SelectRooms = driver
				.findElement(By.xpath("//select[@data-testid = 'HotelSearchBox__ReservationSelect_Select']"));
		Select Selector = new Select(SelectRooms);

		int randomIndex = rand.nextInt(2);
		Selector.selectByIndex(randomIndex);

		WebElement searchButton = driver.findElement(By.xpath("//button[@data=testid='HotelSearchBox__SearchButton']"));
		searchButton.click();

	}

	public void checkPageIfFullyLoadedTest() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		
		WebElement resultsTab = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@data-testid='HotelSearchResult__resultsFoundCount']")));
		
		org.testng.Assert.assertEquals(resultsTab.getText().contains("found")||resultsTab.getText().contains("وجدنا"),true);

	}

	public void sortItemsTest() throws InterruptedException {
		WebElement LowestPriceButton = driver
				.findElement(By.id("//button[@data-testid='HotelSearchResult__sort__LOWEST_PRICE']"));
		LowestPriceButton.click();

		Thread.sleep(2000);

		WebElement pricesContainer = driver.findElement(By.cssSelector(".sc-htpNat.KtFsv.col-9"));

		List<WebElement> prices = pricesContainer.findElements(By.className("Price__Value"));

		int firstPrice = Integer.parseInt(prices.get(0).getText());

		int lastPrice = Integer.parseInt(prices.get(prices.size() - 1).getText());

		org.testng.Assert.assertEquals(firstPrice < lastPrice, true);
	}

	



}
