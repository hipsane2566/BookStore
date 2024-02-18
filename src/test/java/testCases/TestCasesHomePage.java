package testCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import com.aventstack.extentreports.Status;
import pageObjects.HomePage;
import testBases.BaseClass;

public class TestCasesHomePage extends BaseClass{
	WebDriver driver;
	HomePage hp;
	ITestResult result;
	@Test(priority =0,groups = {"functional"},description ="Verified Home page slider are displayed")
	public void testCase01() {
	/*1) Open the browser
	2) Enter the URL “http://practice.automationtesting.in/”
	3) Click on Shop Menu
	4) Now click on Home menu button
	5) Test whether the Home page has Three Sliders only
	6) The Home page must contains only three sliders
	}*/
		try {
			getLog(result).log(Status.INFO, "*****Test case 01 execution is started successfully*****");
			hp = new HomePage(BaseClass.getDriver());
			hp.clickOnShopMenu();
			getLog(result).log(Status.INFO, "*****Click on Shop menu*****");
			hp.clickOnHomeMenu();
			getLog(result).log(Status.INFO, "*****Click on Home menu*****");
			int slider = hp.getSliderCount();
			getLog(result).log(Status.INFO, "*****Slider count is match as expected*****");
			Assert.assertEquals(slider, 3, "Total count 3 is match");
			getLog(result).log(Status.PASS, "*****Test case 01 execution is completed successfully*****");
		}catch(Exception e) {
			getLog(result).log(Status.FAIL, "*****Test case 01 is failed");
			Assert.fail();
			System.out.println(e.getMessage());
		}
	}
	
	@Test(priority =1,groups = {"functional"})
	/*
	1) Open the browser
	2) Enter the URL “http://practice.automationtesting.in/”
	3) Click on Shop Menu
	4) Now click on Home menu button
	5) Test whether the Home page has Three Arrivals only
	6) The Home page must contains only three Arrivals
	*/
	public void testCase02() {
		getLog(result).log(Status.INFO, "*****Test case 02 is execution is started successfully*****");
		boolean status = false;
		try{
			hp = new HomePage(BaseClass.getDriver());
			hp.clickOnShopMenu();
			getLog(result).log(Status.INFO, "*****Click on Shop menu*****");
			hp.clickOnHomeMenu();
			getLog(result).log(Status.INFO, "*****Click on Home menu*****");
			status = hp.getBannerCount();
			getLog(result).log(Status.INFO, "*****Verified that 3 new arrivals are displayed*****");
			Assert.assertTrue(status, "The homepage contains 3 arrivals only");
			getLog(result).log(Status.PASS, "*****Test case 02 execution is completed successfully*****");
		}catch(Exception e ) {
			getLog(result).log(Status.FAIL, "*****Test case 02 is failed");
			Assert.fail();
			System.out.println(e.getMessage().toString());
		}
	}
	
}
