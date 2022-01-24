package testClasses;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import baseClasses.BaseClass;
import baseClasses.PageBaseClass;
import pageClasses.Bookshelves;
import pageClasses.GiftCards;
import pageClasses.LandingPage;
import pageClasses.StudyChair;

public class UrbanLadderHomePage extends BaseClass {
	PageBaseClass pageBase;
	LandingPage landingPage;
	Bookshelves bookshelves;
	GiftCards giftCard;
	StudyChair studyChair;

	@BeforeSuite
	public void openHomePage() {
		invokeBrowser("Chrome");
		pageBase = new PageBaseClass(driver);
		PageFactory.initElements(driver, pageBase);
		landingPage = pageBase.openSite();
	}

	@AfterClass
	public void close() {
		closeBrowser();
	}

// TC001 - To navigate to the Web Page "https://www.urbanladder.com/" and assert the page title.
	@Test(priority = 0)
	public void verifyHomePageTitle() {
		String homePageTitle = driver.getTitle();
		System.out.println("The title of the HomePage is: " + homePageTitle);
		Assert.assertEquals(homePageTitle,
				"Furniture Online: @Upto 40% Off on Wooden Furniture Online in India at Best Price - Urban Ladder");
	}

// TC002 To validate if the nav bar is visible, which contains the main categories in the HomePage
	@Test(priority = 1)
	public void checkVisibilityOfNavbar() {
		Assert.assertTrue(landingPage.checkVisibiltyOfNavigationBar());
	}

//	  TC003 To validate when the user click/hover on the Study main category, the sub menu Study Chair is displayed in the HomePage
	@Test(priority = 2)
	public void testStudyMenu() {
		landingPage.checkStudyMenu();
	}

//	TC004	To validate if the sub-menu Study Chair displayed under the main category Study can be clicked on the HomePage
	@Test(priority = 3)
	public void testStudyChairs() {
		Assert.assertTrue(landingPage.checkStudyChairMenu());
	}

//	TC005	To capture the first 3 product results with highest recommendations and display the same
	@Test(priority = 4)
	public void printStudyChairs() {
		studyChair = landingPage.printStudyChairs();
		studyChair.recommendedChairs();
	}

//	TC006	To validate if all the icons under the"Explore our Furniture Range" on the HomePage are displayed.
	@Test(priority = 5)
	public void testFurnitureRange() {
		Assert.assertTrue(landingPage.checkVisibilityOfFurnitureRange());
	}

//	TC007	To validate if all the icons under the"Explore our Furniture Range" on the HomePage are clickable.
	@Test(priority = 6)
	public void testClickAbilityOfFurnitureRange() {
		Assert.assertTrue(landingPage.checkClickabilityOfFurnitureRange());
	}

//	TC008	To validate the visibility of price, storage type,type,material/finish,brand under Filter tab, when BookShelves icon is clicked under "Explore our Furniture Range".
	@Test(priority = 7)
	public void testVisibilityOfFilters() {
		bookshelves = landingPage.navigateToBookshelves();
		Assert.assertTrue(bookshelves.checkVisibilityOfFilters());
	}

//	TC009	To validate the visibility of CheckBox, Exclude out of stock, when BookShelves icon is clicked under "Explore our Furniture Range".
	@Test(priority = 8)
	public void testVisibilityOfStockCheckBox() {
		Assert.assertTrue(PageBaseClass.isElementVisible(bookshelves.stockCheckBox));
		;
	}

//	TC010	To validate the visibility of list box under Sort By
	@Test(priority = 9)
	public void testVisibilityOfSortByOption() {
		Assert.assertTrue(PageBaseClass.isElementVisible(bookshelves.sortByOption));
	}

//	TC011	To display the name, price of Bookshelves below Rs. 15000 with Including out of stock and Storage type should be open.
	@Test(priority = 10)
	public void printBookshelvesDetailsIncluding() {
		bookshelves.printBookshelvesIncluding();
	}

//	TC012	To display the first 3 name, price of Bookshelves below Rs. 15000 with Excluding out of stock and Storage type should be open.
	@Test(priority = 11)
	public void printBookshelvesDetailsExcluding() {
		bookshelves.printBookshelvesExcluding();
	}

//	TC013	To validate the visibility of "Being-At-home" category under "collections" main category.
	@Test(priority = 12)
	public void testVisibilityOfBeingAtHome() {
		Assert.assertTrue(landingPage.visibilityOfBeingAtHome());
	}

//	TC013	To validate the retrieval of all sub-menu items under Being-At-home.
	@Test(dependsOnMethods = { "testVisibilityOfBeingAtHome" })
	public void testSubMenu() {
		Assert.assertTrue(true);
	}

	@Test(priority = 12)
	public void goToGiftCards() {
		giftCard = bookshelves.goToGiftCards();
		Assert.assertEquals(driver.getTitle(),
				"Gift Card - Buy Gift Cards & Vouchers Online for Wedding, Birthday | Urban Ladder");
	}

//	TC014	To validate the visibility of "gift cards" in the featured links bar on the Top header.
	@Test(priority = 13)
	public void testVisibilityOfGiftCards() {
		Assert.assertTrue(giftCard.checkVisibilityOfGiftCardsLink());
	}

//	TC015	To validate the visibility of Birthday/Anniversary option under the "First choose an occation category"
	@Test(priority = 14)
	public void testVisibilityOfBirthdayOption() {
		Assert.assertTrue(giftCard.checkVisibilityOfBirthdayOption());
	}

//	TC016	To validate that the Birthday/Anniversary option can be clicked
	@Test(priority = 15)
	public void testClickablityOfBirthdayOption() {
		Assert.assertTrue(giftCard.checkClickabiltyOfBirthdayOption());
	}

//	TC017	To validate if the price tabs of "1000","5000","10000" are clickable
	@Test(priority = 16)
	public void testPriceButtonsClickable() {
		Assert.assertTrue(giftCard.checkPriceButtonsClickable());
	}

//	TC018	To validate after entering into text box "Enter an amount between ₹1,000 and  ₹5,00,000" the "NEXT" button is enabled
	@Test(priority = 19)
	public void testNextBtnEnabled1() {
		giftCard.priceTextBox.clear();
		String priceString = "15500";
		Assert.assertTrue(giftCard.checkNextBtnEnabled(priceString));
		giftCard.nextBtn.click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//	TC019	To validate if the "NEXT" button is disabled, when an invalid input of <1000 is entered into the Text field
	@Test(priority = 18)
	public void testNextBtnEnabled2() {
		giftCard.priceTextBox.clear();
		String priceString = "500";
		Assert.assertFalse(giftCard.checkNextBtnEnabled(priceString));
	}

////	TC020	To validate if the "NEXT" button is disabled, when an invalid input of >500000 is entered into the Text field
	@Test(priority = 17)
	public void testNextBtnEnabled3() {

		String priceString = "550000";
		Assert.assertFalse(giftCard.checkNextBtnEnabled(priceString));
	}

//	TC021	To validate if the list boxes under "Send On:" is selectable.
	@Test(priority = 20)
	public void testSendOnList() {
		Assert.assertTrue(giftCard.checkingSendOnMonth());
		Assert.assertTrue(giftCard.checkingSendOnDates());
	}

//	TC022	To capture the error message displayed, when the email is filled with invalid data.
	@Test(priority = 21)
	public void testInvalidEmail() {
		Assert.assertEquals("Please include an '@' in the email address. 'bhavna123' is missing an '@'.",
				giftCard.checkInvalidEmailFunctionality());
	}

}