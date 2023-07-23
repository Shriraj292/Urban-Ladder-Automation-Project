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
import org.testng.Assert;
import org.testng.annotations.Test;

import com.urbanladder.base.TestBase;

public class SignUpAndLoginTest extends TestBase{
    @Test(priority = 1, enabled = false, description = "Validation of Sign Up Functionality")
    public void signUpTest() {
        homePg.clickOnSignUp();
        signUpPg.verifySignUpPage();
        signUpPg.enterDetailsAndClickSignUp("anonymousxyz@gmail.com", "TestPass@123");
        boolean isSuccessful = homePg.verifySignUpOrLoginIsSuccessful();
        Assert.assertTrue(isSuccessful);
    }
}