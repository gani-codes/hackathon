package pageClasses;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import baseClasses.PageBaseClass;

public class Bookshelves extends PageBaseClass {

	public Bookshelves(WebDriver driver) {
		super(driver);
	}

//	To validate the visibility of price, storage type,type,material/finish,brand under Filter tab, when BookShelves icon is clicked under "Explore our Furniture Range".
	@FindBy(xpath = "// *[@id='filters-form']/div[1]/div/div/ul/li/div[1]")
	List<WebElement> filters;

	public boolean checkVisibilityOfFilters() {
		for (int i = 0; i < filters.size(); i++) {
			if (!isElementVisible(filters.get(i))) {
				return false;
			}
		}
		return true;
	}

//	To validate the visibility of CheckBox, Exclude out of stock, when BookShelves icon is clicked under "Explore our Furniture Range".
	@FindBy(id = "filters_availability_In_Stock_Only")
	public WebElement stockCheckBox;

	public boolean checkBoxVisibility() {
		return isElementClickable(stockCheckBox);
	}

//	To validate the visibility of list box under Sort By
	@FindBy(xpath = "//*[@id='content']/div[2]/div[1]/div/div/div/div/div[2]")
	public WebElement sortByOption;

	public boolean checkVisibilityOfSortByOption() {
		return sortByOption.isDisplayed();
	}

//	To display the name, price of Bookshelves below Rs. 15000 with Including out of stock and Storage type should be open.
	@FindBy(xpath = "//*[@id='filters-form']/div[1]/div/div/ul/li[1]/div[2]/div/div/ul/li[1]/div/div[2]/div[2]/div/div[2]/div")
	WebElement slider;

	@FindBy(id = "filters_storage_type_Open")
	WebElement openStorageType;

	@FindBy(id = "filters_availability_In_Stock_Only")
	WebElement stock;

	@FindBy(xpath = "//span[@itemprop='name' and @class='name']")
	List<WebElement> bookShelvesNames;
	@FindBy(xpath = "//div[@class='price-number']/span")
	List<WebElement> bookShelvesCost;

	public void printBookshelvesIncluding() {
		WebElement priceBtn = filters.get(0);
		priceBtn.click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(slider));

		Actions action = new Actions(driver);
		action.dragAndDropBy(slider, (int) -176, 0).perform();
		try {
			Thread.sleep(2500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		WebElement storageType = filters.get(1);
		wait.until(ExpectedConditions.visibilityOf(storageType));
		action.moveToElement(storageType).build().perform();

		wait.until(ExpectedConditions.visibilityOf(openStorageType));
		openStorageType.click();

		try {
			Thread.sleep(2500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("\n\nThe details of bookshelves under 15000 and INCLUDING out of stocks are:\n\n");
		for (int i = 0; i < bookShelvesNames.size(); i++) {
			// to print directly
			System.out
					.println(bookShelvesNames.get(i).getText() + " - " + bookShelvesCost.get(i).getText().substring(1));

		}
	}

	// excluding out of stock
	public void printBookshelvesExcluding() {
		if (!stock.isSelected())
			stock.click();

		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("\n\nThe details of bookshelves under 15000 and EXCLUDING out of stocks are:\n\n");
		for (int i = 0; i < bookShelvesNames.size(); i++) {
			// to print directly
			System.out
					.println(bookShelvesNames.get(i).getText() + " - " + bookShelvesCost.get(i).getText().substring(1));

		}

	}

	@FindBy(linkText = "Gift Cards")
	WebElement giftCards;

	public GiftCards goToGiftCards() {
		giftCards.click();
		return PageFactory.initElements(driver, GiftCards.class);
	}
}
