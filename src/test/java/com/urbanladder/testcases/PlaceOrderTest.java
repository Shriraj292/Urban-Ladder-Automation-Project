package com.urbanladder.testcases;

import org.testng.annotations.Test;

import com.urbanladder.base.TestBase;
import com.urbanladder.commonmethods.HeaderValidationMethods;
import com.urbanladder.commonmethods.ProductCategoriesMethods;

public class PlaceOrderTest extends TestBase{
	
	@Test(description = "Order journey till placing order. The order will not be placed.")
	public void orderComputerTable() {
		HeaderValidationMethods.hoverOverTopNavItem("Study");
		HeaderValidationMethods.clickOnSubNavItem("Computer Tables");
		ProductCategoriesMethods.selectTheProduct("Truman Solid Wood Study Table in Teak Finish");
		productDetailsPg.clickOnAddToCart();
		shoppingCartPg.setQuantity("1");
		shoppingCartPg.clickOnCheckOut();
		shoppingCartPg.clickOnSaveAndContinue();
	}

}
