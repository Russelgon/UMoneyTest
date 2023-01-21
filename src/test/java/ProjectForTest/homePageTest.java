package ProjectForTest;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import resources.base;
import pageObj.BasePage;
import pageObj.LogginPage;

public class homePageTest extends base{
	public WebDriver driver; // local driver for parel mod tests
	public static Logger log =LogManager.getLogger(homePageTest.class.getName());

	@BeforeTest
	public void initialize() throws IOException {
		driver = initializeDriver();
	}
	
	@Test(dataProvider = "getData")
	public void PageNavigation(String Username, String text) throws IOException {
		
		driver.get(propForTest.getProperty("url"));
		BasePage page = new BasePage(driver);
		
		LogginPage lp=page.getLogin();
		lp.getEmail().sendKeys(Username);
		System.out.println(text);
		log.info("Display is abale to write a text");
		log.info(text);
		log.info(Username);
		
		lp.getLogin().click();
	}
	
	@DataProvider
	public Object[][] getData() {
		// For blocked and unblocked users
		Object[][] data = new Object[2][2];
		data[0][0] = "IDoNotLoveUMoney@mail.com";
		data[0][1] = "Blocked User";
		
		data[1][0]="ILoveUMoney@mail.com";
		data[1][1]="Unblocked User";
		
		return data;
		
	}
	
	@AfterTest
	public void closeDriver() {
		driver.close();
	}
}
