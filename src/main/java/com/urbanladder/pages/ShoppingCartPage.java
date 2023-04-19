package com.urbanladder.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.urbanladder.base.TestBase;
import com.urbanladder.utils.TestUtils;

public class ShoppingCartPage extends TestBase{
	
	public ShoppingCartPage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//div[@id='line_items']/descendant::select")
	WebElement quantity;
	
	@FindBy(xpath = "//div[@id='subtotal']/descendant::div[contains(@class,'action-links')]/button")
	WebElement checkOut;
	
	@FindBy(xpath = "//input[@value='Save and Continue']")
	WebElement saveNContinueButton;
	
	@FindBy(xpath = "//input[@value='Place Order']")
	WebElement placeOrder;
	
	/**
	 * This method verifies whether "Shopping Cart" page is displayed or not.
	 */
	public void verifyShoppingCartPage() {
		String actualTitle = driver.getTitle();
		String expectedTitle = "Shopping Cart - Urban Ladder";
		Assert.assertEquals(actualTitle, expectedTitle, "Title mismatched. Required page not displayed.");
	}

	/**
	 * This method selects quantity on Cart page.
	 */
	public void setQuantity(String quantityRequired) {
		TestUtils.selectFromDropdown(quantity, quantityRequired);
	}
	
	/**
	 * This method clicks on "Checkout" button on Cart page.
	 */
	public void clickOnCheckOut() {
		checkOut.click();
	}
	
	/**
	 * This method clicks on "Save And Continue" button on Address page.
	 */
	public void clickOnSaveAndContinue() {
		saveNContinueButton.click();
	}
	
	/**
	 * ***Need Attention
	 * This method clicks on "Place Order" on Payment page.
	 */
	public void clickOnPlaceOrder() {
		placeOrder.click();
//		String paymentFormVerification = "//h3[@class='title' and text()='Cards, UPI & More']";
//		
//		try {
//			WebElement paymentOptions = Timeouts.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(paymentFormVerification)));
//			assertTrue(paymentOptions.isDisplayed());
//		} catch (NoSuchElementException e) {
//			e.printStackTrace();
//			Assert.fail("Payment form not displayed.");
//		}
		
	}
}
