## UMoneyTest: Automation Testing of YooMoney.ru
---
UMoneyTest is a system for a base test of some features in yooumoney.ru

That project were made just like an example of methods that i know how to use in selenium. The main goal is to get a little chance for a work in your company.

---
### 📝 Check-List 📝


| First Header  | Second Header |
| ------------- | ------------- |
| Enviroment | Windows 10 |
| Framework  | Eclipse  |
| Build  | 2022-09 (4.25.0) |
| Tool  | Selenium |
| Test date | 10.01.2023 |
| Tester | Alexandr |

| Type  | ID | Checkpoint | Yes/No | Comments |
| ------------- | ------------- | ------------- | ------------- |------------- |
|Browser | 0 | Google Chrome performance | Yes | Google is able to open cases |
| Home Page | 1 | Panel bar validation | Yes | Panel bar is displayed |
| Home Page  | 2 | Login button | Yes | Login button is displayed and able to use |
| Home Page | 3 | Title | Yes | Title is placed correct without grammar issues |
| Login Page  | 4 | Email stroke validation | Yes | Display is abale to write a text |
| Login Page | 5 | Cheking for Blocked/Unblocked users | Yes | Validation system is correct to aprove user account |
| Login Page | 6 | Button "Next" | Yes | Button is displayed and able to use |
---

### 🗂️ Project Structure 🗂️
Structure for the IMPORTANT files that are placed in an EndProject folder.


    ~/EndProject
    ├── settings
    │   ├── org.eclipse.core.resources.pref
    │   ├── org.eclipse.m2e.core.prefs
    │   └── org.eclipse.jdt.core.prefs
    ├── reports
    │   ├── index.html
    │   └── PageNavigationForTitle.png
    ├── src
    │   ├── main
    │   │   └── java
    │   │       ├── pageObj
    │   │       │   ├── BasePage
    │   │       │   └── LoginPage
    │   │       └── resources
    │   │           ├── Base
    │   │           ├── data.properties
    │   │           ├── ExtentReporterNG
    │   │           └── log4j2
    │   └── test
    │       └── java
    │           └── projectForTest
    │               ├── BasePanelBarTest
    │               ├── HomePageTest
    │               ├── Listeners
    │               └── TitleTest
    ├── testng.xml
    └── pom.xml

*Some files are unshown here

#### 📂 pageObj 📂

__BasePage__ is a class for a base page navigation where are we can do multiple testes:

__sign in__

Possibility sign in
```java
private By signin = By.id("signin");

	public LogginPage getLogin() {
		driver.findElement(signin).click();
		LogginPage lp = new LogginPage(driver);
		return lp;
	}
```
__title__

Is title placed on a base page

```java
private By title = By.xpath("//span[contains(text(),'ЮMoney: приложение, кошелёк и карты для шопинга')]");

	public WebElement getTitle() {
		return driver.findElement(title);
	}
```
__panel bar__

Is panel bar placed on a base page

```java
private By panel = By.xpath("//body/div[@id='root']/div[1]/div[1]/div[1]");

	public WebElement getPanel() {
		return driver.findElement(panel);
	}
```

__LoginPage__

is a class for a base page navigation where we could do sign in

```java
private By email = By.name("login");
private By logIn = By.xpath("//*[text()='Дальше']");

	public WebElement getEmail() {
		return driver.findElement(email);
	}

    	public WebElement getLogin() {
		return driver.findElement(logIn);
	}
```
#### 📂 resources 📂

We use __resources__ folder for information about browsers that we are going to use.

There are three browsers in Base class: Chrome, Firefox, Internet Explore.

As we are not going to use firefox and IE in our tests, we do not set property for this browsers.

```java
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
```

Also we have method __initializeDriver__ to set file to load and use it in our system in extended classes

```java
public WebDriver initializeDriver() throws IOException {
		// set file to load 
	    propForTest = new Properties();
		FileInputStream fileStream = new FileInputStream("C:\\Users\\Sasha\\eclipse-workspace\\EndProject\\src\\main\\java\\resources\\data.properties");
		propForTest.load(fileStream);
		String browserName = propForTest.getProperty("browser");
```

In  __Base__ we set information to create a file for reports creation and make a screenshot of failures
```java
String destinationFile = System.getProperty("user.dir")+"\\reports\\"+testCaseName+".png";
TakesScreenshot ts=(TakesScreenshot) driver;
```

__ExtentReporterNG__ make a report for our tests results
```java
String path = System.getProperty("user.dir")+"\\reports\\index.html";
ExtentSparkReporter reporter = new ExtentSparkReporter(path);
reporter.config().setReportName("Web Automation Results");
reporter.config().setDocumentTitle("Test Results");
```

__data.properties__ is a file for set the information about the browser that we are going to use and also to set the URL for our test case

__log4j.xml__ file for logs.
#### 📂 projectForTest 📂

__BasePanelBarTest__ is a class for testing usability of a panel bar.
```java
BasePage page = new BasePage(driver);
Assert.assertTrue(page.getPanel().isDisplayed());
log.info("Panel bar is displayed");
```
__HomePageTest__ we test the login page and system for restricted and non-restricted user

```java
LogginPage lp=page.getLogin();
lp.getEmail().sendKeys(Username);
System.out.println(text);
log.info("Display is abale to write a text");
log.info(text);
```
```java
// For blocked and unblocked users
Object[][] data = new Object[2][2];
data[0][0] = "IDoNotLoveUMoney@mail.com";
data[0][1] = "Blocked User";
// unblocked user
data[1][0]="ILoveUMoney@mail.com";
data[1][1]="Unblocked User";
```

__Listeners__ is a testNg listener for failures in our tests, for exapmle we use test failure to get a screenshot.
```java
public void onTestFailure(ITestResult result) {
	// TODO Auto-generated method stub
	extentTest.get().fail(result.getThrowable());
	WebDriver driver = null;
	String testMethodName = result.getMethod().getMethodName();
 
	try {
		driver =(WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
	} catch(Exception e) {}
	try {
		extentTest.get().addScreenCaptureFromPath(getSreenShotPath(testMethodName,driver),result.getMethod().getMethodName());
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
```

__TitleTest__ to get the information about the title.
```java
BasePage page = new BasePage(driver);
Assert.assertEquals(page.getTitle().getText(), "ЮMoney: приложение, кошелёк и карты для шопинга" );
log.info("Text message is correct");
```	


#### 📂 reports 📂
Folder __reports__ is used for logs

file __index.html__ gives the web atoumation results by class __ExtentReporterNG__

file __PageNavigationForTitle.png__ is a screenshot of a failure test. Name of this report is based on a class where the failure was find.

---

### ⚙️ Maven and TestNg ⚙️ 

We use maven to build and manage project by comand line.
 
 testNg instead of JUnit to optimazie our testes with full control over the test cases and the execution of the test cases.

 ---

 ### 🔗 Jenkins

 For the Continuous Integration i use Jenkins and Java version 17
