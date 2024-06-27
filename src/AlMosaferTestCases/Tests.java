package AlMosaferTestCases;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Tests extends TestData {
	HomePageTestCases TC = new HomePageTestCases();
	HotelPageTestCases HTC = new HotelPageTestCases();

	@BeforeTest
	public void setup() {
		TheDefultConfiguration();
 }

	@Test(description = "MainPageTestCases", priority = 1)
	public void checkTheLanguage() {
		TC.checkTheLanguageTest();
	}

	@Test(description = "MainPageTestCases", priority = 2)
	public void SARCurrency() {
		TC.SARCurrencyTest();
	}

	@Test(description = "MainPageTestCases", priority = 3)
	public void contactNumber() {
		TC.contactNumberTest();
	}

	@Test(description = "MainPageTestCases", priority = 4)
	public void checkQitafLogoAvailability() {
		TC.checkQitafLogoAvailabilityTest();
	}

	@Test(description = "MainPageTestCases", priority = 5)
	public void checkHotelIfNotSelected() {
		TC.checkHotelIfNotSelectedTest();
	}

	@Test(description = "MainPageTestCases", priority = 6)
	public void checkDepartureAndReturnDates() {
		TC.checkDepartureAndReturnDatesTest();

	}

	@Test(description = "MainPageTestCases", priority = 7)
	public void ChangeTheLanguageOfTheWebsiteRandomly() {
		TC.ChangeTheLanguageOfTheWebsiteRandomlyTest();
	}

	@Test(description = "HotelPageTestCases", priority = 8)
	public void HotelSelection() {
		HTC.HotelSelectionTest();
	}

	@Test(description = "HotelPageTestCases", priority = 9)
	public void SelectNumberOfPeople() {
		HTC.SelectNumberOfPeopleTest();
	}

	@Test(description = "HotelPageTestCases", priority = 10)
	public void checkPageIfFullyLoaded() {
		HTC.checkPageIfFullyLoadedTest();
	}

	@Test(description = "HotelPageTestCases", priority = 11)
	public void sortItems() throws InterruptedException {
		HTC.sortItemsTest();
	}

}
