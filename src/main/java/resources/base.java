package resources;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

	
public class base {
    	
	public WebDriver driver;
	public Properties propForTest;
	
	@SuppressWarnings("deprecation")
	public WebDriver initializeDriver() throws IOException {
		// set file to load 
	    propForTest = new Properties();
		FileInputStream fileStream = new FileInputStream("user.dir"+"\\src\\main\\java\\resources\\data.properties");
		propForTest.load(fileStream);
		String browserName = propForTest.getProperty("browser");
	
		if(browserName.equals("chrome")) {
			//set property for chrome
			System.setProperty("webdriver.chrome.driver", "C://chromedriver.exe");
			driver = new ChromeDriver();		
		}
		else if (browserName.equals("firefox")) {
			// set property for firefox
			driver = new FirefoxDriver();	
		}
		else if(browserName.equals("IE")) {
			// set property for IE
			driver = new InternetExplorerDriver();
		}
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;	
	}
	
	public String getSreenShotPath(String testCaseName, WebDriver driver) throws IOException {
		// file for reports creation
		String destinationFile = System.getProperty("user.dir")+"\\reports\\"+testCaseName+".png";
		TakesScreenshot ts=(TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(source,new File(destinationFile));
		return destinationFile;
	}
}
