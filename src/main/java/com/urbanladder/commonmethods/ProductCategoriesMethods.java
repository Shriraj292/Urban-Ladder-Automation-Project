/*
 * Copyright Disclaimer
 *
 * This script is the intellectual property of Shriraj Ghorpade. All rights reserved.
 * You may use this script for personal use only. You may not reproduce, distribute, or modify this script
 * for any commercial purposes without explicit written permission from Shriraj Ghorpade.
 * Any unauthorized use, reproduction, or distribution of this script may result in legal action.
 *
 * @created 01/05/2023 - 12:36 PM
 * @project UrbanLadderAutomation
 * @author Shriraj Ghorpade
 */

package com.urbanladder.commonmethods;

import java.util.Arrays;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.urbanladder.base.TestBase;
import com.urbanladder.utils.TestUtils;

public class ProductCategoriesMethods extends TestBase{

    static String productBoxXpath = "//div[@class='clearfix']/descendant::div[@class='categories row']/descendant::div[@class='productbox']";
    static String productInfoBlockXpath = productBoxXpath + "/div[@class='product-info-block']";
    static String productTitleBlockXpath = productInfoBlockXpath + "/a[@class='product-title-block']";

    /**
     * This method clicks on "View Product" option of the required product
     * and validates whether appropriate page is displayed.
     * @param productName
     */
    public static void selectTheProduct(String productName) {
        String productTitleXpath = productTitleBlockXpath + "/descendant::span[normalize-space(text())='" + productName + "']";
        String viewProductXpath = productTitleXpath + "/ancestor::a/following-sibling::div[@class='otherinfo']/a[contains(text(),'View ')]";

        try {
            WebElement productTitle = driver.findElement(By.xpath(productTitleXpath));
            TestUtils.hoverOver(productTitle);

            try {
                if(productTitle.isDisplayed()) {
                    WebElement viewProduct = driver.findElement(By.xpath(viewProductXpath));
                    viewProduct.click();
                }
            } catch (NoSuchElementException e) {
                e.printStackTrace();
                Assert.fail(" View Product for required product " + productName + " not found.");
            }

        } catch (NoSuchElementException e) {
            e.printStackTrace();
            Assert.fail("Required product " + productName + " not found.");
        }

        String actualTitle = driver.getTitle();
        String expectedTitle = productName + " - Urban Ladder";
        Assert.assertEquals(actualTitle, expectedTitle, "Title not matched.");
    }

    /**
     * This method clicks on "Add to Wishlist" option of the required product
     * and validates whether product is added to wishlist.
     * @param productName
     */
    public static void addToWishlist(String productName) {
        String productTitleXpath = productTitleBlockXpath + "/descendant::span[normalize-space(text())='" + productName + "']";

        String addToWishlistXpath = productTitleXpath + "/ancestor::div[@class='product-info-block']"
                + "/following-sibling::div[@class='wishproduct wishblock']";
        //+ "/span[text()='Add to Wishlist']";

        try {
            WebElement productTitle = driver.findElement(By.xpath(productTitleXpath));
            TestUtils.hoverOver(productTitle);

            try {
                WebElement addToWishlistElement = driver.findElement(By.xpath(addToWishlistXpath));
                addToWishlistElement.click();
            } catch (NoSuchElementException e) {
                e.printStackTrace();
                Assert.fail("Add to Wishlist for required product " + productName + " not found.");
            }

        } catch (NoSuchElementException e) {
            e.printStackTrace();
            Assert.fail("Required product " + productName + " not found.");
        }
    }

    /**
     * This method applies filters.
     * @param filterName
     * @param options
     */
    public static void applyFilter(String filterName, String ...options){
        String filterNameXpath = "//form[@id='filters-form']/descendant::div[@class='filters-list-wrapper']" +
                "/ul[@class='grouplist clearfix']/li/div[normalize-space(text())='" + filterName + "']";

        String[] filterOptions = (String[]) Arrays.stream(options).toArray();

        try {
            WebElement filter = driver.findElement(By.xpath(filterNameXpath));
            TestUtils.hoverOver(filter);

            for (String optionName:
                    filterOptions) {
                String filterOptionXpath = filterNameXpath + "/following-sibling::div/descendant::ul//label[normalize-space(text())='" + optionName + "']";

                try {
                    WebElement option = driver.findElement(By.xpath(filterOptionXpath));
                    option.click();
                }catch (NoSuchElementException e){
                    e.printStackTrace();
                    Assert.fail("Required filter option " + "\"" + optionName + "\"" + " not found.");
                }catch (ElementNotInteractableException e){
                    e.printStackTrace();
                    Assert.fail("Required filter option " + "\"" + optionName + "\"" + " will not receive the click.");
                }
            }
        }catch (NoSuchElementException e){
            e.printStackTrace();
            Assert.fail("Required filter " + "\"" + filterName + "\"" + " not found.");
        }
        verifySelectedFilterOptions(options);
    }

    /**
     * This method verifies whether filters are applied or not.
     * @param options
     */
    public static void verifySelectedFilterOptions(String ...options){
        String[] selectedOptions = (String[]) Arrays.stream(options).toArray();

        for (String option:
                selectedOptions) {
            String selectedOptionXpath = "//div[@class='filter-section']" +
                    "/div[@class='selected-options']/descendant::li/span[normalize-space(text())='" + option + "']";

            try {
                WebElement selectedOption = driver.findElement(By.xpath(selectedOptionXpath));
                Assert.assertTrue(selectedOption.isDisplayed(), "Required filter " + "\"" + option + "\"" + " not applied.");
            } catch (NoSuchElementException e){
                e.printStackTrace();
                Assert.fail("Required filter " + "\"" + option + "\"" + " not applied.");
            }
        }
    }
}

