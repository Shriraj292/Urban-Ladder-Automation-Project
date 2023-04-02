package com.urbanladder.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.urbanladder.pages.HomePage;
import com.urbanladder.pages.LoginPage;
import com.urbanladder.pages.PaymentPage;
import com.urbanladder.pages.ProductDetailsPage;
import com.urbanladder.pages.ShoppingCartPage;
import com.urbanladder.pages.SignUpPage;
import com.urbanladder.utils.DriverSetup;
import com.urbanladder.utils.TestUtils;
import com.urbanladder.utils.Timeouts;

public class TestBase {
	
	protected static WebDriver driver = null;
	public static FileInputStream input;
	public static Properties prop = new Properties();
	
//	Page Objects
	public HomePage homePg;
	public SignUpPage signUpPg;
	public LoginPage loginPg;
	public ProductDetailsPage productDetailsPg;
	public ShoppingCartPage shoppingCartPg;
	public PaymentPage paymentPg;
		
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
	 * Description: This methods launches the browser based on environment properties in config.properties
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
			}else {
				homePg.clickOnLogout();
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			Assert.fail("My Account option not found.");
		}
	}

}
