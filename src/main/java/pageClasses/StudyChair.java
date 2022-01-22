package pageClasses;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import baseClasses.PageBaseClass;

public class StudyChair extends PageBaseClass {

	public StudyChair(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public void openStudy() {
		// click study main category
		driver.findElement(By.xpath("//*[@id='topnav_wrapper']/ul/li[6]/span")).click();
		// click study chair sub menu.
		driver.findElement(By.xpath("//*[@id='topnav_wrapper']/ul/li[6]/div/div/ul/li[2]/ul/li[1]/a/span")).click();
	}

	// Take first 3 study chair details with highest recommendation If more than one
	// item with same price displayed, include that details as well.
	public void Recommendedchairs() {

		List<WebElement> chairnames = driver.findElements(By.xpath("//span[@itemprop='name' and @class='name']"));
		List<WebElement> smallnames = driver
				.findElements(By.xpath("//*[@id='content']/div[3]/div/ul/li[1]/div/div[5]/a/div[1]/div[1]"));
		List<WebElement> chaircosts = driver.findElements(By.xpath("//div[@class='price-number']/span"));

		// myList contains all the web elements
		// if you want to get all elements text into array list
		List<String> all_chairs_text = new ArrayList<>();
		List<String> all_small_text = new ArrayList<>();
		List<String> all_costs_text = new ArrayList<>();

		for (int i = 0; i < 3; i++) {

			// loading text of each element in to array all_elements_text
			all_chairs_text.add(chairnames.get(i).getText());
			all_costs_text.add(chaircosts.get(i).getText());
			all_small_text.add(smallnames.get(i).getText());

			// to print directly
			System.out.println(
					chairnames.get(i).getText() + smallnames.get(i).getText() + ", " + chaircosts.get(i).getText());

		}

	}
}