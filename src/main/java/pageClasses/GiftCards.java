package pageClasses;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import baseClasses.PageBaseClass;

public class GiftCards extends PageBaseClass {

	public GiftCards(WebDriver driver) {
		super(driver);
	}

//	Checking the visibility of Gift cards link
	@FindBy(linkText = "Gift Cards")
	WebElement giftCards;

	public boolean checkVisibilityOfGiftCardsLink() {
		return giftCards.isDisplayed();
	}

	// Checking the visibility of Birthday/Anniversary option
	@FindBy(xpath = "//*//*[@id=\"app-container\"]//ul/li[3]/div/h3[text()=\"Birthday/Anniversary\"]")
	WebElement birthdayOption;

	public boolean checkVisibilityOfBirthdayOption() {
		return birthdayOption.isDisplayed();
	}

	// Checking Birthday/Anniversary option can be clicked or not
	public boolean checkClickabiltyOfBirthdayOption() {
		birthdayOption.click();
		return birthdayOption.isDisplayed() && birthdayOption.isEnabled();
	}

	// Checking "1000","5000","10000" are click able
	@FindBy(className = "HuPJS")
	List<WebElement> priceBtn;

	public boolean checkPriceButtonsClickable() {
		boolean bool = true;
		for (WebElement webElement : priceBtn) {
			if (!(webElement.isDisplayed() && webElement.isEnabled())) {
				System.out.println(webElement.getText());
				bool = false;
			}
		}

		return bool;
	}

	// Checking NEXT button is enabled or not when price is given in text box
	@FindBy(name = "amount_select")
	public WebElement priceTextBox;

	@FindBy(xpath = "//button[text()=\"Next\"]")
	public WebElement nextBtn;

	public boolean checkNextBtnEnabled(String priceString) {
		priceTextBox.sendKeys(priceString);
		int price = Integer.parseInt(priceTextBox.getAttribute("value"));
		if (price >= 1000 && price <= 500000) {
			return true;
		} else {
			return false;
		}
	}

//	Checking the Send On list boxes functionality
	@FindBy(xpath = "//*[@id=\"app-container\"]/div/main/section/section[2]/div/section[2]/div[4]/select[1]")
	List<WebElement> months;

	@FindBy(xpath = "//*[@id=\"app-container\"]/div/main/section/section[2]/div/section[2]/div[4]/select[2]")
	List<WebElement> dates;

	public boolean checkingSendOnMonth() {
		for (int i = 1; i < months.size(); i++) {
			if (!isElementClickable(months.get(i))) {
				return false;
			}
		}
		return true;
	}
	
	public boolean checkingSendOnDates() {
		for (int i = 1; i < dates.size(); i++) {
			if (!isElementClickable(dates.get(i))) {
				return false;
			}
		}
		return true;
	}
	
	
	//To capture the error message displayed, when the email is filled with invalid data
	public String checkInvalidEmailFunctionality() {
		WebElement email = driver.findElement(By.name("recipient_email"));
		
		driver.findElement(By.name("recipient_name")).sendKeys("Bhavna");
		email.sendKeys("bhavna123");
		driver.findElement(By.name("customer_name")).sendKeys("harry");
		driver.findElement(By.name("customer_email")).sendKeys("harry@email.com");
		driver.findElement(By.name("customer_mobile_number")).sendKeys("1234567890");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		JavascriptExecutor js = (JavascriptExecutor)driver;

		
		Boolean is_valid = (Boolean)js.executeScript("return arguments[0].checkValidity();", email);
		String message = (String)js.executeScript("return arguments[0].validationMessage;", email);
		return message;
	}
}
