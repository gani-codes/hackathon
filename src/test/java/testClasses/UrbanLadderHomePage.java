package testClasses;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import baseClasses.BaseClass;
import baseClasses.PageBaseClass;
import pageClasses.Bookshelves;
import pageClasses.LandingPage;

public class UrbanLadderHomePage extends BaseClass {
	LandingPage landingPage;
	Bookshelves bookshelves;

// TC001 - To navigate to the Webpage "https://www.urbanladder.com/" and assert
// the page title.
	@Test
	public void openHomePage() {
		invokeBrowser("Chrome");
		PageBaseClass pageBase = new PageBaseClass(driver);
		PageFactory.initElements(driver, pageBase);
		landingPage = pageBase.openSite();

		String homePageTitle = driver.getTitle();
		System.out.println("The title of the HomePage is: " + homePageTitle);
		Assert.assertEquals(homePageTitle,
				"Furniture Online: @Upto 40% Off on Wooden Furniture Online in India at Best Price - Urban Ladder");
	}

// TC002 To validate if the nav bar is visible, which contains the main
// categories in the HomePage
	@Test(priority = 1)
	public void checkVisibilityOfNavbar() {
		Assert.assertTrue(landingPage.checkVisibiltyOfNavigationBar());
	}

	
//	  TC003 To validate when the user click/hover on the Study main category, the
//	  sub menu Study Chair is displayed in the HomePage
	@Test(priority = 2)
	public void testStudyMenu() {
		landingPage.checkStudyMenu();
		// capture a screenshot here
	}
	
	
//	TC004	To validate if the sub-menu Study Chair displayed under the main category Study can be clicked on the HomePage
	@Test(priority = 3)
	public void testStudyChairs() {
		Assert.assertTrue(landingPage.checkStudyChairMenu());
	}
	
//	TC005	To validate if all the icons under the"Explore our Furniture Range" on the HomePage are displayed.
	@Test(priority=4)
	public void testFurnitureRange() {
		Assert.assertTrue(landingPage.checkVisibilityOfFurnitureRange());
	}
	
	
//	TC006	To validate if all the icons under the"Explore our Furniture Range" on the HomePage are clickable.
	@Test(priority=5)
	public void testClickAbilityOfFurnitureRange() {
		Assert.assertTrue(landingPage.checkClickabilityOfFurnitureRange());
	}
	
	
	@Test(priority=6)
	public void goToBookshelves() {
		bookshelves = landingPage.navigateToBookshelves();
		Assert.assertEquals(driver.getTitle(),
				"Bookshelf @ Upto 25% Off: Buy Bookshelves Online [Latest Bookshelf Designs] - Urban Ladder");
	}
}