package com.urbanladder.pages;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.urbanladder.base.TestBase;

public class LoginPage extends TestBase{

	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//div[@id='login-module']/div[@class='form-heading']")
	WebElement loginMessage;
	
	@FindBy(xpath = "//div[@id='login-module']/form/div/descendant::input[@id='spree_user_email']")
	WebElement emailField;
	
	@FindBy(xpath = "//div[@id='login-module']/form/div/descendant::input[@id='spree_user_password']")
	WebElement passwordField;
	
	@FindBy(xpath = "//div[@id='login-module']/form/input[@value='Log In']")
	WebElement loginButton;
	
	/**
	 * This method verifies whether login up page is displayed.
	 */
	public void verifyLoginPage() {
		String expectedHeading = "LOGIN WITH YOUR EMAIL ID";
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	    wait.until(ExpectedConditions.visibilityOf(loginMessage));
		String actualHeading = loginMessage.getText();
		Assert.assertEquals(actualHeading, expectedHeading);
	}

	/**
	 * This method enters login up credentials and clicks on Login button.
	 * @param email
	 * @param password
	 */
	public void enterDetailsAndClickLogin() {
		String email = prop.getProperty("email");
		String password = prop.getProperty("password");
		emailField.sendKeys(email);
		passwordField.sendKeys(password);
		loginButton.click();
	}

}
