/*
 * Copyright Disclaimer
 *
 * This script is the intellectual property of Shriraj Ghorpade. All rights reserved.
 * You may use this script for personal use only. You may not reproduce, distribute, or modify this script
 * for any commercial purposes without explicit written permission from Shriraj Ghorpade.
 * Any unauthorized use, reproduction, or distribution of this script may result in legal action.
 *
 * @created 01/05/2023 - 12:42 PM
 * @project UrbanLadderAutomation
 * @author Shriraj Ghorpade
 */

package com.urbanladder.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.urbanladder.base.TestBase;
import com.urbanladder.utils.Timeouts;

public class PaymentPage extends TestBase{

    public PaymentPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[text()='Pay Now']")
    WebElement payNow;

    @FindBy(xpath = "//button[contains(@class,'modal-close')]")
    WebElement closePayment;

    /**
     * This method clicks on "Pay Now" button on payment page.
     */
    public void clickOnPayNow() {
        payNow.click();
    }

    /**
     * ***Need Attention
     * This method clicks on close button on payment form.
     */
    public void closePaymentForm() {
//		closePayment.click();
        WebElement closePaymentOption = Timeouts.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(@class,'modal-close')]/*/*")));
        closePaymentOption.click();

    }
}

