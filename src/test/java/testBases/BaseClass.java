package testBases;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import utilities.ExtentReportManager;

public class BaseClass {
	public static WebDriver driver;
	public static Properties p;
	public static Logger log;
	public static ExtentReports extent;
	public static ExtentTest test;
	@BeforeClass(groups= {"functional"})
	public static WebDriver intitializeBrowser() throws IOException {
		if(getProperties().getProperty("execution_env").equalsIgnoreCase("remote")){
			DesiredCapabilities capabilities = new DesiredCapabilities();
			switch (getProperties().getProperty("browser").toLowerCase()) {
			case "chrome": capabilities.setBrowserName("chrome"); break;
			case "edge": capabilities.setBrowserName("MicrosoftEdge"); break;
			default: System.out.println("No matching browsers");
			}
			driver = new RemoteWebDriver(new URL(""), capabilities);
		}else if(getProperties().getProperty("execution_env").equalsIgnoreCase("local")) {
			
			switch(getProperties().getProperty("browser").toLowerCase()){
			case "chrome":
			    driver=new ChromeDriver();
			    break;
			case "edge":
				driver=new EdgeDriver();
			    break;
			default:
			    System.out.println("No matching browser");
			    driver=null;
			}
			
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get(p.getProperty("AppUrl"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		return driver;
	}
	
	private static Properties getProperties() throws IOException {
		FileReader file = new FileReader(".//src/test/resources/config.properties");
		p = new Properties();
		p.load(file);
		return p;
	}
	
	//log4j logger
	/**
     * Returns a Logger with the name of the calling class.
     *
     * @return The Logger for the calling class.
     * @throws UnsupportedOperationException if the calling class cannot be determined.
     */
	@BeforeTest
	public Logger logger() {
		log = LogManager.getLogger(this.getClass());
		return log;
	}
	
	
	//extent report logger
	 /**
     * Returns a ExtentTest using the fully qualified name of the Class as the Logger name.
     *
     * @param ITestResult object reference to get The test class used this object is a result for. 
     * @return The ExtentTest .
     * @throws UnsupportedOperationException if {@code clazz} is {@code null} and the calling class cannot be
     *             determined.
     */
	@BeforeMethod
	public ExtentTest getLog(ITestResult result) {
		extent = ExtentReportManager.getReports();
		test = extent.createTest(result.getTestClass().getName());
		return test;
	}
	public static WebDriver getDriver() {
		return driver;
	}

	@AfterClass(groups= {"functional"})
	public void tearDown() {
		driver.quit();
	}
	
}
