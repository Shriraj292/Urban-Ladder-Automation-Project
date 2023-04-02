package com.urbanladder.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.urbanladder.base.TestBase;

public class ProductDetailsPage extends TestBase {
	
	public ProductDetailsPage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@id='product-show']/descendant::div[@id='product-right-part']/descendant::div[@id='cart-form']/descendant::div[@data-hook='product_price']/descendant::div[contains(@class,'buy_details clearfix  current')]/descendant::button[@id='add-to-cart-button']")
	WebElement addToCartButton;
		
	/**
	 * This method clicks on "Add To Cart" button and verifies whether
	 * shopping cart page is displayed.
	 */
	public void clickOnAddToCart() {
		addToCartButton.click();
		String actualTitle = driver.getTitle();
		String expectedTitle = "Shopping Cart - Urban Ladder";
		Assert.assertEquals(actualTitle, expectedTitle, "Title mismatched. Required page not displayed.");
	}
	
}
