package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;

import testBases.BaseClass;

public class TestListener implements ITestListener{
	String repName;
	public void onStart(ITestContext context) {
		System.out.println("Test case Started " + context.getName());
	}
	
	public void onTestStart(ITestResult result) {
		String desc = result.getMethod().getDescription();
		if(desc != null)
			ReportTestManager.startTest(result.getMethod().getMethodName() + "(" +desc+")",
					result.getInstance().getClass().getCanonicalName());
		else if(result.getTestName() != null) {
			ReportTestManager.startTest(result.getTestName(),
					result.getInstance().getClass().getCanonicalName());
		}else {
			ReportTestManager.startTest(result.getMethod().getMethodName(),
					result.getInstance().getClass().getCanonicalName());
		}
	}
	
	public void onTestSuccess(ITestResult result) {
		ReportTestManager.getTest().log(Status.PASS, "Test Passed");
	}
	
	public void onTestFailure(ITestResult result) {
		//TakeScreenshot
		try {
			String imgPath = new BaseClass().captureScreen(result.getName());
			ReportTestManager.getTest().addScreenCaptureFromPath(imgPath);
			ReportTestManager.getTest().log(Status.FAIL, result.getThrowable());
			ReportTestManager.getTest().fail("deatils");
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public void onTestSkip(ITestResult result) {
		ReportTestManager.getTest().log(Status.SKIP, "Test Skipped");
	}
	
	public void onFinish(ITestContext context) {
		repName = ExtentReportManager.repName;
		ExtentReportManager.getInstance().flush();
		String pathOfExtentReport = System.getProperty("user.dir")+"\\reports\\"+repName;
		File extentReport = new File(pathOfExtentReport);
		
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
