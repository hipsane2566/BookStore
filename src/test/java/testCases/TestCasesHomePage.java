package testCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
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
			super.addLog("*****Test case 01 execution is started successfully*****");
			hp = new HomePage(BaseClass.getDriver());
			hp.clickOnShopMenu();
			super.addLog("*****Click on Shop menu*****");
			hp.clickOnHomeMenu();
			super.addLog("*****Click on Home menu*****");
			int slider = hp.getSliderCount();
			super.addLog("*****Slider count is match as expected*****");
			Assert.assertEquals(slider, 3, "Total count 3 is match");
			super.addLog("*****Test case 01 execution is completed successfully*****");
		}catch(Exception e) {
			super.addLog("*****Test case 01 is Failed");
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
		addLog("*****Test case 02 is execution is started successfully*****");
		boolean status = false;
		try{
			hp = new HomePage(BaseClass.getDriver());
			hp.clickOnShopMenu();
			addLog("*****Click on Shop menu*****");
			hp.clickOnHomeMenu();
			addLog("*****Click on Home menu*****");
			status = hp.getBannerCount();
			addLog("*****Verified that 3 new arrivals are displayed*****");
			Assert.assertTrue(status, "The homepage contains 3 arrivals only");
			addLog("*****Test case 02 execution is completed successfully*****");
		}catch(Exception e ) {
			addLog("*****Test case 02 is Failed");
			Assert.fail();
			System.out.println(e.getMessage().toString());
		}
	}
	/*
	1) Open the browser
	2) Enter the URL “http://practice.automationtesting.in/”
	3) Click on Shop Menu
	4) Now click on Home menu button
	5) Test whether the Home page has Three Arrivals only
	6) The Home page must contains only three Arrivals
	7) Now click the image in the Arrivals
	8) Test whether it is navigating to next page where the user can add that book into his basket.
	9) Image should be clickable and should navigate to next page where user can add that book to his basket
	*/
	@Test(priority = 2, dependsOnMethods = {"testCase02"}, groups={"functional"}, description = "Image should be clickable and shoul navigate to next page where user can add that book to his basket")
	public void testCase03() { 
		try {
			addLog("*****Test case 03 execution is started successfully*****");
			String imageText = hp.getImageText();
			addLog("*****Captured the image text of arrival*****");
			hp.clickOnProductImage();
			addLog("*****Click on product image*****");
			String productText = hp.getProductText();
			addLog("*****Captured the product image text*****");
			Assert.assertEquals(productText, imageText);
			addLog("*****Test Case 03 is Passed*****");
		}catch(Exception e) {
			addLog("*****Test case 03 is Failed");
			Assert.fail();
		}
		
	}
}
