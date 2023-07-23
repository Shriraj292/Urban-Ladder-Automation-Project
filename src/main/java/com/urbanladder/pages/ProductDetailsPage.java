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

import com.urbanladder.base.TestBase;

public class ProductDetailsPage extends TestBase {

    public ProductDetailsPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@id='product-show']/descendant::div[@id='product-right-part']/descendant::div[@id='cart-form']/descendant::div[@data-hook='product_price']/descendant::div[contains(@class,'buy_details clearfix')]/descendant::button[@id='add-to-cart-button']")
    WebElement addToCartButton;

    /**
     * This method clicks on "Add To Cart" button and verifies whether
     * shopping cart page is displayed.
     */
    public void clickOnAddToCart() {
        addToCartButton.click();
    }
}
