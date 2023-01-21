package pageObj;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LogginPage {
	
	public WebDriver driver;
	
	private By email = By.name("login");
	private By logIn = By.xpath("//*[text()='Дальше']");
	
	public LogginPage(WebDriver driver2) {
		// TODO Auto-generated constructor stub
		this.driver=driver2;
	}

	public WebElement getEmail() {
		return driver.findElement(email);
	}
	
	public WebElement getLogin() {
		return driver.findElement(logIn);
	}
}
