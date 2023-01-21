package ProjectForTest;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import resources.base;
import pageObj.BasePage;


public class TitleTest extends base{
	public WebDriver driver; // local driver for parel mod tests
	public static Logger log =LogManager.getLogger(TitleTest.class.getName());
	@BeforeTest
	public void initialize() throws IOException {
		driver = initializeDriver();
		log.info("Driver is initialized");
		driver.get(propForTest.getProperty("url"));
		log.info("Navigate to home page");
	}

	@Test
	public void PageNavigationForTitle() throws IOException {
		
		BasePage page = new BasePage(driver);
		Assert.assertEquals(page.getTitle().getText(), "ЮMoney: приложение, кошелёк и карты для шопинг2а" );
		log.info("Text message is correct");
		
	}
	
	@AfterTest
	public void closeDriver() {
		driver.close();
	}
	
	
}