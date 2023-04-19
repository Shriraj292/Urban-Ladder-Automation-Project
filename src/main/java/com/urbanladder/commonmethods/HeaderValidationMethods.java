package com.urbanladder.commonmethods;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import com.urbanladder.base.TestBase;
import com.urbanladder.utils.Timeouts;

public class HeaderValidationMethods extends TestBase{
	
	public HeaderValidationMethods() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//a[@title='Track Order']")
	WebElement trackOrderIcon;
	
	@FindBy(xpath = "//a[@title='Wishlist']")
	WebElement wishListIcon;
	
	@FindBy(xpath = "//a[@title='Cart']")
	WebElement cartIcon;
	
	/************************ Header Topbar Navigation Validations*****************************/

	/**
	 * This method clicks on wishlist icon and verifies "My Wishlist" page is displayed.
	 */
	public void viewWishlist() {
		wishListIcon.click();
		myWishlistPg.verifyMyWishlistPage();
	}
	
	/**
	 * This method clicks on wishlist icon and verifies "My Wishlist" page is displayed.
	 */
	public void viewCart() {
		cartIcon.click();
		shoppingCartPg.verifyShoppingCartPage();
	}
	
	/*****************************Taxon Navigation Validations*********************************/
	
	/**
	 * This method hovers mouse over top navigation items.
	 * @param itemName
	 */
	public static void hoverOverTopNavItem(String itemName) {
		String topNavItemsXpath = "//span[@class='topnav_itemname' and normalize-space(text())='" + itemName + "']";
		try {
			WebElement topNavItem = driver.findElement(By.xpath(topNavItemsXpath));
			Actions actions = new Actions(driver);
			actions.moveToElement(topNavItem).build().perform();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			Assert.fail(itemName + " element not displayed.");
		}
	}
	
	/**
	 * This method clicks on sublist item passed as parameter and validates title to verify 
	 * whether appropriate page is displayed.
	 * @param sublistItemName
	 */
	public static void clickOnSublistItem(String sublistItemName) {
		String sublistItemXpath = "//li[@class='sublist_item']/descendant::a[normalize-space(text())='" + sublistItemName + "']";
		try {
			WebElement sublistItem = driver.findElement(By.xpath(sublistItemXpath));
			sublistItem.click();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			Assert.fail(sublistItemName + " element not displayed.");
		}
		
		try {
			WebElement pageTitle = driver.findElement(By.xpath("//div[@class='header-section']//h1[normalize-space(text())='Computer Tables']"));
			String title = pageTitle.getText();
			Assert.assertEquals(title, sublistItemName, "Title name not matching.");
			
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			Assert.fail("Title element not found.");
		}
	}
	
	/**
	 * This method clicks on subNav item passed as parameter.
	 * @param subNavItemName
	 */
	public static void clickOnSubNavItem(String subNavItemName) {
		String subNavItemNameXpath = "//ul[@class='taxonslist']/ancestor::li/span[@class='topnav_itemname active']/parent::li/div/descendant::a/span[normalize-space(text())='" + subNavItemName + "']";
		try {
			WebElement subNavItem = driver.findElement(By.xpath(subNavItemNameXpath));
			subNavItem.click();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			Assert.fail(subNavItemName + " element not displayed.");
		}
	}
}