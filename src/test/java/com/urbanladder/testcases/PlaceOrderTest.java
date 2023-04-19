package com.urbanladder.testcases;

import org.testng.annotations.Test;

import com.urbanladder.base.TestBase;
import com.urbanladder.commonmethods.HeaderValidationMethods;
import com.urbanladder.commonmethods.ProductCategoriesMethods;

public class PlaceOrderTest extends TestBase{
	
	@Test(priority = 1, description = "Computer Table Order journey till placing order. The order will not be placed.")
	public void orderComputerTable() {
		HeaderValidationMethods.hoverOverTopNavItem("Study");
		HeaderValidationMethods.clickOnSubNavItem("Computer Tables");
		ProductCategoriesMethods.selectTheProduct("Truman Solid Wood Study Table in Teak Finish");
		productDetailsPg.clickOnAddToCart();
		shoppingCartPg.setQuantity("1");
		shoppingCartPg.clickOnCheckOut();
		shoppingCartPg.clickOnSaveAndContinue();
	}
	
	@Test(priority = 2, description = "Gaming Chair Order journey till placing order. The order will not be placed.")
	public void orderGamingChair() {
		HeaderValidationMethods.hoverOverTopNavItem("Living");
		HeaderValidationMethods.clickOnSubNavItem("Gaming Chairs");
		ProductCategoriesMethods.selectTheProduct("Ultron Swivel Leatherette Gaming Chair in Red Colour");
		productDetailsPg.clickOnAddToCart();
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
		shoppingCartPg.setQuantity("1");
		shoppingCartPg.clickOnCheckOut();
		shoppingCartPg.clickOnSaveAndContinue();
	}

}
