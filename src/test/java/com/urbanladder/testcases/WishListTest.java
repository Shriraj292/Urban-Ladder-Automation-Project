/*
 * Copyright Disclaimer
 *
 * This script is the intellectual property of Shriraj Ghorpade. All rights reserved.
 * You may use this script for personal use only. You may not reproduce, distribute, or modify this script
 * for any commercial purposes without explicit written permission from Shriraj Ghorpade.
 * Any unauthorized use, reproduction, or distribution of this script may result in legal action.
 *
 * @created 01/05/2023 - 12:51 PM
 * @project UrbanLadderAutomation
 * @author Shriraj Ghorpade
 */

package com.urbanladder.testcases;

import com.urbanladder.base.TestBase;
import com.urbanladder.commonmethods.HeaderValidationMethods;
import com.urbanladder.commonmethods.ProductCategoriesMethods;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(com.urbanladder.utils.ListenerTest.class)
public class WishListTest extends TestBase {

    @Test(priority = 1, description = "Adding product to wishlist and removing again. Verify that product is removed from wishlist.")
    public void addToWishlistAndRemove(){
        HeaderValidationMethods.hoverOverTopNavItem("Outdoor");
        HeaderValidationMethods.clickOnSubNavItem("Balcony Sets");
        ProductCategoriesMethods.addToWishlist("Lana Square Metal Outdoor Table in Grey Colour with set of 3 Chairs");
        hdrValidationMtds.viewWishlist();
        myWishlistPg.verifyMyWishlistPage();
        myWishlistPg.removeProductFromWishlist("Lana Square Metal Outdoor Table in Grey Colour with set of 3 Chairs");
    }

    @Test(priority = 2, description = "Adding product to wishlist. Add to cart and proceed with order placement.")
    public void addToWishlistAndAddToBasket(){
        HeaderValidationMethods.hoverOverTopNavItem("Living");
        HeaderValidationMethods.clickOnSubNavItem("Recliners");
        ProductCategoriesMethods.addToWishlist("Griffin Leatherette Two Seater Manual Recliner in Burgundy Leatherette C...");
        hdrValidationMtds.viewWishlist();
        myWishlistPg.verifyMyWishlistPage();
        myWishlistPg.addToCart("Griffin Leatherette Two Seater Manual Recliner in Burgundy Leatherette C...");
        shoppingCartPg.verifyShoppingCartPage();
        shoppingCartPg.setQuantity("1");
        shoppingCartPg.clickOnCheckOut();
        shoppingCartPg.clickOnSaveAndContinue();
    }

}
