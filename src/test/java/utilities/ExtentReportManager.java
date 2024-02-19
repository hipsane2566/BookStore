package utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestListener;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener{

	public static ExtentSparkReporter spark;
	public static ExtentReports extent;
	public static ExtentTest test;
	static String repName;
	
	public static ExtentReports getInstance() {
		if(extent == null)
			createInstance();
		return extent;
	}
	public static ExtentReports createInstance() {
		if(extent ==null) {
			extent = new ExtentReports();
		
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		repName = "Test-Report-" + timeStamp + ".html";
		
		spark = new ExtentSparkReporter(".\\reports\\" + repName);
		spark.config().setDocumentTitle("Shop Selenium Automation Report");
		spark.config().setReportName("Shop Selenium Functional Testing");
		spark.config().setTheme(Theme.DARK);
		
		extent.attachReporter(spark);	// attach spark report to extentreport 
		extent.setSystemInfo("Application", "Book Store");
		extent.setSystemInfo("Module", "Shop");
		extent.setSystemInfo("Sub Module", "All Module");
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environemnt", "QA");
		}
		return extent;
	}
	
	
	

}
