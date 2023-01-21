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


public class basePanelBarTest extends base{
	public WebDriver driver; // local driver for parel mod tests
	public static Logger log =LogManager.getLogger(basePanelBarTest.class.getName());
	
	@BeforeTest
	public void initialize() throws IOException {
		driver = initializeDriver();
		driver.get(propForTest.getProperty("url"));
	}

	@Test
	public void basePanelBarTest() throws IOException {
		
		BasePage page = new BasePage(driver);
		Assert.assertTrue(page.getPanel().isDisplayed());
		log.info("Panel bar is displayed");
		
	}
	
	@AfterTest
	public void closeDriver() {
		driver.close();
	}
	
}