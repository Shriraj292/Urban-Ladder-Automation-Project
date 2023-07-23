/*
 * Copyright Disclaimer
 *
 * This script is the intellectual property of Shriraj Ghorpade. All rights reserved.
 * You may use this script for personal use only. You may not reproduce, distribute, or modify this script
 * for any commercial purposes without explicit written permission from Shriraj Ghorpade.
 * Any unauthorized use, reproduction, or distribution of this script may result in legal action.
 *
 * @created 01/05/2023 - 12:33 PM
 * @project UrbanLadderAutomation
 * @author Shriraj Ghorpade
 */

package com.urbanladder.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.Properties;

import com.urbanladder.utils.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

import com.urbanladder.commonmethods.HeaderValidationMethods;
import com.urbanladder.pages.HomePage;
import com.urbanladder.pages.LoginPage;
import com.urbanladder.pages.MyWishlistPage;
import com.urbanladder.pages.PaymentPage;
import com.urbanladder.pages.ProductDetailsPage;
import com.urbanladder.pages.ShoppingCartPage;
import com.urbanladder.pages.SignUpPage;

public class TestBase {

    protected static WebDriver driver = null;
    public static FileInputStream input;
    public static Properties prop = new Properties();
    public static ReadExcel testData = null;

    //	Page Objects
    public HomePage homePg;
    public SignUpPage signUpPg;
    public LoginPage loginPg;
    public ShoppingCartPage shoppingCartPg;
    public ProductDetailsPage productDetailsPg;
    public PaymentPage paymentPg;
    public MyWishlistPage myWishlistPg;
    public static HeaderValidationMethods hdrValidationMtds;
    public TestDataProvider testDataProvider;

    public TestBase() {
        try {
            input = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\configFiles\\config.properties");
            prop.load(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method name: invokeBrowser
     * Description: This method launches the browser based on environment properties in config.properties
     */
    private void invokeBrowser() {
        String browser = prop.getProperty("browser").toUpperCase();
        switch (browser) {
            case "CHROME":
                driver = DriverSetup.getChromeDriver();
                break;

            case "FIREFOX":
                driver = DriverSetup.getFireDriver();
                break;

            case "EDGE":
                driver = DriverSetup.getEdgeDriver();
                break;

            default:
                System.out.println("Invalid browser. Check config.properties file for investigation.");
        }
    }

    /**
     * Method name: getUrl
     * Description: This method hits the URL based on environment properties in config.properties
     */
    private void getUrl() {
        String url = prop.getProperty("url").toUpperCase();
        driver.get(url);
    }

    /**
     * Method name: closeTheBrowser
     * Description: This method quits the browser instance.
     */
    private void closeTheBrowser() {
        driver.quit();
    }

    int suiteCounter = 1;

    @BeforeSuite
    public void suiteStart(){
        System.out.println("Suite " + suiteCounter + " execution started.");
    }

    @AfterSuite
    public void suiteEnd(){
        System.out.println("Suite " + suiteCounter++ + " execution stopped.");
    }

    @BeforeTest
    public void setUp(){
        invokeBrowser();

//		Driver settings
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Timeouts.PAGE_LOAD_TIMEOUT));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Timeouts.IMPLICIT_WAIT));

//		Launching the URL
        getUrl();

//		Initializing page objects
        homePg = new HomePage();
        signUpPg = new SignUpPage();
        loginPg = new LoginPage();
        productDetailsPg = new ProductDetailsPage();
        shoppingCartPg = new ShoppingCartPage();
        paymentPg = new PaymentPage();
        myWishlistPg = new MyWishlistPage();
        hdrValidationMtds = new HeaderValidationMethods();
        testDataProvider = new TestDataProvider();
    }

    @AfterTest
    public void tearDown() {
        //Quit the browser instance
        closeTheBrowser();
    }

    @BeforeMethod
    public void loginTest() {
        homePg.clickOnLogIn();
        loginPg.verifyLoginPage();
        loginPg.enterDetailsAndClickLogin();
        boolean isSuccessful = homePg.verifySignUpOrLoginIsSuccessful();
        Assert.assertTrue(isSuccessful);
    }

    @AfterMethod
    public void logout() {
        String actualURL = driver.getCurrentUrl();
        String paymentPageURL = "https://www.urbanladder.com/checkout/payment";

        if (actualURL.equals(paymentPageURL)){
            String myAccountXpath = "//li[normalize-space(text())='My Account']";
            try {
                WebElement myAccount = driver.findElement(By.xpath(myAccountXpath));
                if (myAccount.isDisplayed()) {
                    TestUtils.hoverOver(myAccount);
                    String logoutXpath = "//div[@id='user_links']/ul/li[@id='logout']";
                    try {
                        WebElement logout = driver.findElement(By.xpath(logoutXpath));
                        logout.click();
                    } catch (NoSuchElementException e) {
                        e.printStackTrace();
                        Assert.fail("Logout option not found.");
                    }
                }

            } catch (NoSuchElementException e) {
                e.printStackTrace();
                Assert.fail("My Account option not found.");
            }
        }else {
            homePg.clickOnLogout();
        }
    }
}

