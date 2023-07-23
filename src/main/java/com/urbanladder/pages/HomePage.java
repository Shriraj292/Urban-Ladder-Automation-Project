/*
 * Copyright Disclaimer
 *
 * This script is the intellectual property of Shriraj Ghorpade. All rights reserved.
 * You may use this script for personal use only. You may not reproduce, distribute, or modify this script
 * for any commercial purposes without explicit written permission from Shriraj Ghorpade.
 * Any unauthorized use, reproduction, or distribution of this script may result in legal action.
 *
 * @created 01/05/2023 - 12:37 PM
 * @project UrbanLadderAutomation
 * @author Shriraj Ghorpade
 */

package com.urbanladder.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.urbanladder.base.TestBase;
import com.urbanladder.utils.TestUtils;

public class HomePage extends TestBase{

    public HomePage() {
        PageFactory.initElements(driver, this);
    }

    //PageFactory
    @FindBy(className = "header__topBarIconList_profile-icon")
    public WebElement profileIcon;

    @FindBy(xpath = "//a[contains(@class,'signup-link authentication_popup')]")
    WebElement signUp;

    @FindBy(xpath = "//a[contains(@class,'login-link authentication_popup')]")
    WebElement logIn;

    @FindBy(id = "logout")
    WebElement logoutButton;

    /**
     * This method hovers the mouse over profile icon and clicks on sign up option.
     */
    public void clickOnSignUp() {
        TestUtils.hoverOver(profileIcon);
        signUp.click();
    }

    /**
     * This method hovers the mouse over profile icon and clicks on login option.
     */
    public void clickOnLogIn() {
        TestUtils.hoverOver(profileIcon);
        logIn.click();
    }

    /**
     * This method hovers the mouse over profile icon and clicks on logout option.
     */
    public void clickOnLogout() {
        TestUtils.hoverOver(profileIcon);
        logoutButton.click();
    }

    /**
     * This method verifies whether sign up / login is successful.
     */
    public boolean verifySignUpOrLoginIsSuccessful() {
        boolean isSuccessful = false;
        TestUtils.hoverOver(profileIcon);
        if (logoutButton.isDisplayed()) {
            isSuccessful = true;
        }
        return isSuccessful;
    }

}

