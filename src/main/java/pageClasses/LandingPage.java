package pageClasses;

import java.time.Duration;
import java.util.ArrayList;
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

public class LandingPage extends PageBaseClass {
	public LandingPage(WebDriver driver) {
		super(driver);
	}

	/* Top Navigation Bar */
	@FindBy(xpath = "//ul[@class='topnav bodytext']")
	WebElement topNavigationBar;

	public boolean checkVisibiltyOfNavigationBar() {
		return topNavigationBar.isDisplayed();
	}

//	To validate the visibility of "Being-At-home" category under "collections" main category.
	@FindBy(xpath = "//*[@id=\"topnav_wrapper\"]/ul/li[10]/div/div/ul/li/ul/li/a/span")
	List<WebElement> collectionsList;

	public boolean visibilityOfBeingAtHome() {
		Actions action = new Actions(driver);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

		WebElement collectionLink = driver.findElement(By.xpath("//*[@id='topnav_wrapper']/ul/li[10]"));

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView()", collectionLink);

		wait.until(ExpectedConditions.visibilityOf(collectionLink));
		action.moveToElement(collectionLink).build().perform();

		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		wait.until(ExpectedConditions.visibilityOfAllElements(collectionsList));
		
		List<String> menusInCollections = new ArrayList<String>();
		//for storing in a list
		System.out.println("\nItems present in Collections are - \n\n");
		for (WebElement webElement : collectionsList) {
			menusInCollections.add(webElement.getText());
			System.out.println(webElement.getText());
		}
		
		//for checking if 'Being-At-Home' is present or not
		for (String string : menusInCollections) {
			if(string.equals("Being-At-Home")) {
				System.out.println("Being-At-Home menu is present");
				return true;
			}
			
		}
		return false;		
	}

	/* Study Menu in Top Navigation Bar */
	public void checkStudyMenu() {
		WebElement studyMenu;
		Actions builder = new Actions(driver);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		studyMenu = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"topnav_wrapper\"]/ul/li[6]")));

		builder.moveToElement(studyMenu).build().perform();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/* Study Chair Menu in Study Menu sub-navigation list */
	public boolean checkStudyChairMenu() {
		WebElement studyChairMenu;
		Actions builder = new Actions(driver);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		studyChairMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Study Chairs")));

		builder.moveToElement(studyChairMenu).build().perform();
		boolean bool = studyChairMenu.isDisplayed();

		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return bool;
	}

	/* Checking whether all items are present in Explore Furniture Range section */
	public boolean checkVisibilityOfFurnitureRange() {
		String[] expectedFurnitureItems = { "Sofas", "Beds", "Dining", "TV Units", "Mattresses", "Seating",
				"Coffee Tables", "Cupboards", "Recliners", "Storage", "Study", "Bookshelves", "Shoe Racks", "Decor",
				"All Furniture" };

		List<WebElement> furnitureRangeItems = driver.findElements(By.xpath("//*[@id='content']/div[3]/div/div/a/h4"));
		List<String> furnitureRangeItemsList = new ArrayList<String>();

		for (WebElement webElement : furnitureRangeItems) {
			String itemTitle = webElement.getText();
			furnitureRangeItemsList.add(itemTitle);
		}

		String[] furnitureRangeItemsNames = furnitureRangeItemsList.toArray(new String[furnitureRangeItemsList.size()]);
		for (int i = 0; i < furnitureRangeItemsNames.length; i++) {
//			System.out.println(furnitureRangeItemsNames[i]+"------------------"+expectedFurnitureItems[i]);
			if (!(furnitureRangeItemsNames[i].equals(expectedFurnitureItems[i]))) {
				return false;
			}
		}

		return true;
	}

	/*
	 * Checking whether all the elements in Explore Furniture Range are clickable or
	 * not
	 */
	public boolean checkClickabilityOfFurnitureRange() {
		boolean bool = true;
		List<WebElement> furnitureRangeItems = driver.findElements(By.xpath("//*[@id='content']/div[3]/div/div/a"));
		for (WebElement webElement : furnitureRangeItems) {
			if (!(webElement.isDisplayed() && webElement.isEnabled())) {
				bool = false;
			}
		}
		return bool;
	}

	/* Bookshelves Icon */
	@FindBy(xpath = "//a/h4[text()='Bookshelves']")
	WebElement bookshelvesIcon;

	public Bookshelves navigateToBookshelves() {
		bookshelvesIcon.click();
		WebElement closeButton = driver.findElement(By.linkText("Close"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(closeButton));
		closeButton.click();

		return PageFactory.initElements(driver, Bookshelves.class);
	}
}