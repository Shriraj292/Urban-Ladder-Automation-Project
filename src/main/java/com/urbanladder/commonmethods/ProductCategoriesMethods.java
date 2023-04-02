package com.urbanladder.commonmethods;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.urbanladder.base.TestBase;
import com.urbanladder.utils.TestUtils;

public class ProductCategoriesMethods extends TestBase{

	static String productBoxXpath = "//div[@class='clearfix']/descendant::div[@class='categories row']/descendant::div[@class='productbox']";
	static String productInfoBlockXpath = productBoxXpath + "/div[@class='product-info-block']";
	static String productTitleBlockXpath = productInfoBlockXpath + "/a[@class='product-title-block']";
	
	/**
	 * This method clicks on "View Product" option of the required product 
	 * and validates whether appropriate page is displayed.
	 * @param productName
	 */
	public static void selectTheProduct(String productName) {
		String productTitleXpath = productTitleBlockXpath + "/descendant::span[normalize-space(text())='" + productName + "']";
		String viewProductXpath = productTitleXpath + "/ancestor::a/following-sibling::div[@class='otherinfo']/a[contains(text(),'View ')]";
		
		try {
			WebElement productTitle = driver.findElement(By.xpath(productTitleXpath));
			TestUtils.hoverOver(productTitle);
			
			try {
				if(productTitle.isDisplayed()) {
					WebElement viewProduct = driver.findElement(By.xpath(viewProductXpath));
					viewProduct.click();
				}
			} catch (NoSuchElementException e) {
				e.printStackTrace();
				Assert.fail(" View Product for required product " + productName + " not found.");
			}
			
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			Assert.fail("Required product " + productName + " not found.");
		}
		
		String actualTitle = driver.getTitle();
		String expectedTitle = productName + " - Urban Ladder";
		Assert.assertEquals(actualTitle, expectedTitle, "Title not matched.");
	}
	
}
