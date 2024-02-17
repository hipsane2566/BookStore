package testBases;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseClass {
	public static WebDriver driver;
	static Properties p;
	@BeforeClass
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
	public static WebDriver getDriver() {
		return driver;
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	
}
