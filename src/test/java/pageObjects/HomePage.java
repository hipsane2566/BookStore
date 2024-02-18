package pageObjects;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class HomePage extends BasePage{
	
	public HomePage(WebDriver driver){
		super(driver);
	}
	
	private @FindBy(xpath = "//a[text()='Shop']")
	WebElement shopLink;
	
	private @FindBy(xpath = "//a[text()='Home']")
	WebElement homeLink;
	
	private @FindBy(xpath = "//div[@class='n2-ss-slider-3']/descendant::img")
	List <WebElement> homePageSliders;
	
	private static @FindBy(xpath = "//div[@class='n2-ss-slider-3']/following::div[2]")
	WebElement rightArrow;
	
	private static @FindBy(how = How.TAG_NAME, using = "h2")
	WebElement arrivalText;
	
	private static @FindBy(how = How.XPATH, using = "//h2[.='new arrivals']/following::div[2]/div")
	List <WebElement> bannerThreeArrivals;
	
	//click on shop menu option
	public void clickOnShopMenu() {
		shopLink.click();
	}
	
	//click on home menu option
	public void clickOnHomeMenu() {
		homeLink.click();
	}
	
	public static void clickOnArrow() {
		try {
			rightArrow.click();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int getSliderCount() throws InterruptedException {
		int counter = 0;
		System.out.println(homePageSliders.size());
		try{
			for(WebElement slider: homePageSliders) {
				if(slider.isDisplayed()) {
					clickOnArrow();
					counter= counter+1;
					Thread.sleep(3000);
				}
			}
			return counter;
		}catch(NoSuchElementException e) {
			System.out.println("Element not found......" + e.getMessage());
		}
		return counter;
	}	
	
	public boolean getBannerCount() {
		boolean status = false;
		if(arrivalText.isDisplayed()) {
			if(bannerThreeArrivals.size()==3) {
				status = true;
			}else {
				status = false;
			}
		}return status;
	}
	
}
