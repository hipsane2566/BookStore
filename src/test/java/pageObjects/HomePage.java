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
	//shop link
	private @FindBy(xpath = "//a[text()='Shop']")
	WebElement shopLink;
	
	//home link
	private @FindBy(xpath = "//a[text()='Home']")
	WebElement homeLink;
	
	//home page slider
	private @FindBy(xpath = "//div[@class='n2-ss-slider-3']/descendant::img")
	List <WebElement> homePageSliders;
	
	//home page slider right arrow
	private static @FindBy(xpath = "//div[@class='n2-ss-slider-3']/following::div[2]")
	WebElement rightArrow;
	
	//home page new arrivals head text
	private static @FindBy(how = How.TAG_NAME, using = "h2")
	WebElement arrivalText;
	
	//home page new arrivals elements list
	private static @FindBy(how = How.XPATH, using = "//h2[.='new arrivals']/following::div[2]/div")
	List <WebElement> bannerThreeArrivals;
	
	//home page arrival image link
	private static @FindBy(xpath = "//h2[.='new arrivals']/following::div[2]/div[1]/descendant::h3")
	WebElement imageArrival;
	
	//product page, product title text
	private static @FindBy(tagName = "h1" )
	WebElement textProductPage;
	
	//add to basket button
	private static @FindBy(tagName = "button")
	WebElement btnAddToBasket;
	
	//product price
	private static @FindBy(xpath = "//p[@class='price']/span[1]")
	WebElement productPrice;
	
	//item count
	private static @FindBy(className = "cartcontents")
	WebElement itemCount;
	
	//item price
	private static @FindBy(className = "amount")
	WebElement itemPrice;
	
	//click on shop menu option
	public void clickOnShopMenu() {
		shopLink.click();
	}
	
	//click on home menu option
	public void clickOnHomeMenu() {
		homeLink.click();
	}
	
	//click on home page banner right arrow
	public static void clickOnArrow() {
		try {
			rightArrow.click();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//get home page slider count and return count of slider
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
	
	/*get new arrivals products count 
	 *return the status true if count is match 3
	 *return the status false if count is less than 3 
	 */
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
	
	//click on image from home page
	public void clickOnProductImage() {
		imageArrival.click();
	}
	
	//get click on image text
	public String getImageText() {
		return imageArrival.getText();
	}
	
	//getProdcut text
	public String getProductText() {
		return textProductPage.getText();
	}
	
	//getProduct pricing
	public static int getProductPrice() {
		return stringToInt(productPrice.getText());
	}
	
	//get menu items count and price
	public static int [] checkMenuItemDetails() {
		String [] items = itemCount.getText().split(" ");
		int count = Integer.parseInt(items[0]);
		int price = stringToInt(itemPrice.getText());
		int itemDetails [] = {count, price};
		return itemDetails;
	}
	
	public boolean getMenuItemWithPrice() {
		boolean status = false;
		try {
			int productPrice = getProductPrice();
			int beforeAddtoCart [] = checkMenuItemDetails();
			btnAddToBasket.click();
			int afterAddToCart [] = checkMenuItemDetails();
			for(int i = 0; i<1; i++) {
				if(beforeAddtoCart[i]<afterAddToCart[i]) {
					if(productPrice==afterAddToCart[i+1]) {
						status = true;
					}
				}
			}return status;	
		}catch(NoSuchElementException e) {
			System.out.println(e.getMessage());
			return status;
		}
	}
	
	public static int stringToInt(String text) {
		String splitText []=text.split("\\₹");
		double convert = Double.parseDouble(splitText[1]);
		System.out.println(convert);
		int integer = (int) convert;
		return integer;
	}
}
