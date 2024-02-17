package testCases;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import testBases.BaseClass;

public class TestCasesHomePage extends BaseClass{
	
	@Test
	public void testCase01() {
	/*1) Open the browser
	2) Enter the URL “http://practice.automationtesting.in/”
	3) Click on Shop Menu
	4) Now click on Home menu button
	5) Test whether the Home page has Three Sliders only
	6) The Home page must contains only three sliders
	}*/
		try {
			HomePage hp = new HomePage(driver);
			hp.clickOnShopMenu();
			hp.clickOnHomeMenu();
			int slider = hp.getSliderCount();
			Assert.assertEquals(slider, 3, "Total count 3 is match");
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	
	
	}
}
