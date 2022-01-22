package baseClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import pageClasses.LandingPage;

public class PageBaseClass extends BaseClass {
	//open the website
	//validatation
	
	public PageBaseClass(WebDriver driver) {
		this.driver = driver;
	}

	/****************** OpenApplication ***********************/
	public LandingPage openSite() {
		driver.get("https://www.urbanladder.com/");
		LandingPage landingPage = new LandingPage(driver);
		PageFactory.initElements(driver, landingPage);
		return landingPage;
	}
	
	//repeating functions
	public static boolean isElementVisible(WebElement element) {
		return element.isDisplayed();
	}
	
	public static boolean isElementClickable(WebElement element) {
		return element.isDisplayed() && element.isEnabled();
	}
}
