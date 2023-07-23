/*
 * Copyright Disclaimer
 *
 * This script is the intellectual property of Shriraj Ghorpade. All rights reserved.
 * You may use this script for personal use only. You may not reproduce, distribute, or modify this script
 * for any commercial purposes without explicit written permission from Shriraj Ghorpade.
 * Any unauthorized use, reproduction, or distribution of this script may result in legal action.
 *
 * @created 01/05/2023 - 12:50 PM
 * @project UrbanLadderAutomation
 * @author Shriraj Ghorpade
 */

package com.urbanladder.testcases;

import com.urbanladder.utils.ReadExcel;
import com.urbanladder.utils.TestDataProvider;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.urbanladder.base.TestBase;
import com.urbanladder.commonmethods.HeaderValidationMethods;
import com.urbanladder.commonmethods.ProductCategoriesMethods;

@Listeners(com.urbanladder.utils.ListenerTest.class)
public class PlaceOrderTest extends TestBase{
    @Test(dataProvider = "OrderData", dataProviderClass = TestDataProvider.class, priority = 1, description = "Computer Table Order journey till placing order. The order will not be placed.")
    public void orderComputerTable(String topNavItem, String subnavItem, String productName, String quantity) {
        HeaderValidationMethods.hoverOverTopNavItem(topNavItem);
        HeaderValidationMethods.clickOnSubNavItem(subnavItem);
        ProductCategoriesMethods.selectTheProduct(productName);
        productDetailsPg.clickOnAddToCart();
        shoppingCartPg.verifyShoppingCartPage();
        shoppingCartPg.setQuantity(quantity);
        shoppingCartPg.clickOnCheckOut();
        shoppingCartPg.clickOnSaveAndContinue();
    }

    @Test(priority = 2, description = "Gaming Chair Order journey till placing order. The order will not be placed.")
    public void orderGamingChair() {
        HeaderValidationMethods.hoverOverTopNavItem("Living");
        HeaderValidationMethods.clickOnSubNavItem("Gaming Chairs");
        ProductCategoriesMethods.selectTheProduct("Ultron Swivel Leatherette Gaming Chair in Red Colour");
        productDetailsPg.clickOnAddToCart();
        shoppingCartPg.verifyShoppingCartPage();
        shoppingCartPg.setQuantity("1");
        shoppingCartPg.clickOnCheckOut();
        shoppingCartPg.clickOnSaveAndContinue();
    }

    @Test(priority = 3, description = "Bean bag Order journey till placing order. The order will not be placed.")
    public void orderBeanBag() {
        HeaderValidationMethods.hoverOverTopNavItem("Sofas & Recliners");
        HeaderValidationMethods.clickOnSubNavItem("Bean Bags");
        ProductCategoriesMethods.selectTheProduct("Jon Filled Bean Bag");
        productDetailsPg.clickOnAddToCart();
        shoppingCartPg.verifyShoppingCartPage();
        shoppingCartPg.setQuantity("1");
        shoppingCartPg.clickOnCheckOut();
        shoppingCartPg.clickOnSaveAndContinue();
    }
}