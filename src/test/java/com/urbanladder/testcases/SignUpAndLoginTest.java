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
