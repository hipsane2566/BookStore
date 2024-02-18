package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener{

	public static ExtentSparkReporter spark;
	public static ExtentReports extent;
	public static ExtentTest test;
	static String repName;
	
	public static ExtentReports getReports() {
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

		//get details of environment executing Operating System
		
		}
		return extent;
	}
	
	public void onStart(ITestContext context) {
		String os = context.getCurrentXmlTest().getParameter("OS");
		getReports().setSystemInfo("Operating System", os);
		
		//get details of environment executing browser
		String browser = context.getCurrentXmlTest().getParameter("browser");
		getReports().setSystemInfo("Browser", browser);

		List<String> includedGroups = context.getCurrentXmlTest().getIncludedGroups();
		if(!includedGroups.isEmpty()) {
			getReports().setSystemInfo("Groups", includedGroups.toString());
		}
	}
	public void onTestSuccess(ITestResult result) {
		test = getReports().createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS, result.getName()+" got successfully executed");
	}
	
	public void onTestFailure(ITestResult result) {
		test = getReports().createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL, result.getName()+" got failed");
	}
	
	public void onTestSkip(ITestResult result) {
		test = getReports().createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP,result.getName()+ "got skipped");
		test.log(Status.FAIL, result.getThrowable().getMessage());
	}
	
	public void onFinish(ITestContext context) {
		getReports().flush();
		String pathOfExtentReport = System.getProperty("user.dir")+"\\reports\\"+repName;
		File extentReport = new File(pathOfExtentReport);
		
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
