import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Tests {
	WebDriver driver = new ChromeDriver();
	String url = "https://global.almosafer.com/en?gad_source=1&gclid=CjwKCAjwyJqzBhBaEiwAWDRJVHtycgk1OmINoNrMPPh6nxQv03A-9ZiZQmtsQbPYEXf316jjEL8OjRoCOLoQAvD_BwE";
	
	@BeforeTest
	public void setup() {
		
	}
	
	@Test
	public void testOne() {
		
	}

}
