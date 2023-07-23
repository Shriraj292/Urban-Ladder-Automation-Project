/*
 * Copyright Disclaimer
 *
 * This script is the intellectual property of Shriraj Ghorpade. All rights reserved.
 * You may use this script for personal use only. You may not reproduce, distribute, or modify this script
 * for any commercial purposes without explicit written permission from Shriraj Ghorpade.
 * Any unauthorized use, reproduction, or distribution of this script may result in legal action.
 *
 * @created 01/05/2023 - 12:44 PM
 * @project UrbanLadderAutomation
 * @author Shriraj Ghorpade
 */

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
    }
}

