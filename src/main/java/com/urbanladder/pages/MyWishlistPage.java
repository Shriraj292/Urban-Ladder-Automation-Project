//new class added
package com.urbanladder.pages;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.urbanladder.base.TestBase;
import com.urbanladder.utils.TestUtils;

public class MyWishlistPage extends TestBase{

	public MyWishlistPage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//h1[text()='My wishlist']")
	WebElement myWishlistTitle;
	
	@FindBy(xpath = "//div[@class='message' and normalize-space(text())='Oops! There are no items in your wishlist. Continue browsing and add some!']")
	WebElement emptyWishlistMessage;
	
	/**
	 * This method verifies whether "My wishlist" page is displayed or not.
	 */
	public void verifyMyWishlistPage() {
		String actualTitle = myWishlistTitle.getText();
		String expectedTitle = "My wishlist";
		Assert.assertEquals(actualTitle, expectedTitle, "Heading mismatched. My wishlist page page not displayed.");
	}
	
	/**
	 * This method clicks on "Added to Wishlist" option of the required product from "My Wishlist" 
	 * page and validates whether product is removed from wishlist.
	 * @param productName
	 */
	public void removeProductFromWishlist(String productName) {
		String wishlistProductXpath = "//div[@class='productbox']/div[@class='product-info-block']/a[@class='product-title-block']"
				+ "/descendant::span[normalize-space(text())='" + productName + "']";
		
		String addedToWishlistXpath = "/ancestor::div[@class='product-info-block']"
				+ "/following-sibling::div[@class='wishproduct wishblock']/span[text()='Added to Wishlist']";
		
		try {
			WebElement wishListProduct = driver.findElement(By.xpath(wishlistProductXpath));
			TestUtils.hoverOver(wishListProduct);
			
			try {
				WebElement addedToWishlist = driver.findElement(By.xpath(addedToWishlistXpath));
				addedToWishlist.click();
			} catch (NoSuchElementException e) {
				e.printStackTrace();
				Assert.fail("Added to Wishlist for required product " + productName + " not found.");
			}
			
			driver.navigate().refresh();
			Assert.assertTrue(wishListProduct.isDisplayed(), "Product " + productName + "still in wishlist");
			
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			Assert.fail("Required product " + productName + " not found.");
		}
	}
	
	/**
	 * This method clicks on "Add to Cart" option of the required product from "My Wishlist" 
	 * page and validates whether shopping cart page is displayed.
	 * @param productName
	 */
	public void addToCart(String productName) {
		String wishlistProductXpath = "//div[@class='productbox']/div[@class='product-info-block']/a[@class='product-title-block']"
				+ "/descendant::span[normalize-space(text())='" + productName + "']";
		
		String addToCartXpath = wishlistProductXpath + "/ancestor::*[@class='product-info-block']"
				+ "/following-sibling::*/descendant::button[@id='add-to-cart-button']";
		
		try {
			WebElement wishListProduct = driver.findElement(By.xpath(wishlistProductXpath));
			TestUtils.hoverOver(wishListProduct);
			
			try {
				WebElement addToCart = driver.findElement(By.xpath(addToCartXpath));
				addToCart.click();
				shoppingCartPg.verifyShoppingCartPage();
				
			} catch (NoSuchElementException e) {
				e.printStackTrace();
				Assert.fail("Add to Cart for required product " + productName + " not found.");
			}
			
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			Assert.fail("Required product " + productName + " not found.");
		}
	}

	/**
	 * This method verifies whether wishlist is empty or not
	 * @return boolean value
	 */
	private boolean verifyEmptyWishlist() {
		boolean isEmpty = false;
		driver.navigate().refresh();
		if (emptyWishlistMessage.isDisplayed()) {
			isEmpty = true;
		}
		return isEmpty;
	}
}
