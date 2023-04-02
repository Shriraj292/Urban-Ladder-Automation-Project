package com.urbanladder.commonmethods;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import com.urbanladder.base.TestBase;

public class HeaderValidationMethods extends TestBase{
	
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
			String subNavItemNameXpath = "//ul[@class='taxonslist']/descendant::a/span[normalize-space(text())='" + subNavItemName + "']";
			try {
				WebElement subNavItem = driver.findElement(By.xpath(subNavItemNameXpath));
				subNavItem.click();
			} catch (NoSuchElementException e) {
				e.printStackTrace();
				Assert.fail(subNavItemName + " element not displayed.");
			}
		}
	}