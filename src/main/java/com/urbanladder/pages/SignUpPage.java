package com.urbanladder.pages;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.urbanladder.base.TestBase;

public class SignUpPage extends TestBase{
	
	public SignUpPage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//div[@id='signup-module']/div[@class='form-heading']")
	WebElement signUpMessage;
	
	@FindBy(xpath = "//div[@id='signup-module']/form/descendant::input[@id='spree_user_email']")
	WebElement emailField;
	
	@FindBy(xpath = "//div[@id='signup-module']/form/descendant::input[@id='spree_user_password' and @type='password']")
	WebElement passwordField;
	
	@FindBy(xpath = "//div[@id='signup-module']/form/descendant::input[@class='button primary signup']")
	WebElement signUpButton;
	
	/**
	 * This method verifies whether sign up page is displayed.
	 */
	public void verifySignUpPage() {
		String expectedHeading = "BE THE FIRST TO KNOW.";
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	    wait.until(ExpectedConditions.visibilityOf(signUpMessage));
		String actualHeading = signUpMessage.getText();
		Assert.assertEquals(actualHeading, expectedHeading);
	}

	/**
	 * This method enters sign up credentials and clicks on Sign Up button.
	 * @param email
	 * @param password
	 */
	public void enterDetailsAndClickSignUp(String email, String password) {
		emailField.sendKeys(email);
		passwordField.sendKeys(password);
		signUpButton.click();
	}
	
}
