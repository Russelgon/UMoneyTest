package pageObj;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BasePage {
	
	public WebDriver driver;
	private By signin = By.id("signin");
	private By title = By.xpath("//span[contains(text(),'ЮMoney: приложение, кошелёк и карты для шопинга')]");
	private By panel = By.xpath("//body/div[@id='root']/div[1]/div[1]/div[1]");
	
	public BasePage(WebDriver driver2) {
		// TODO Auto-generated constructor stub
		this.driver=driver2;   
	}
	
	public LogginPage getLogin() {
		driver.findElement(signin).click();
		LogginPage lp = new LogginPage(driver);
		return lp;
	}
	
	public WebElement getTitle() {
		return driver.findElement(title);
	}
	
	public WebElement getPanel() {
		return driver.findElement(panel);
	}
}
