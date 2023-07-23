/*
 * Copyright Disclaimer
 *
 * This script is the intellectual property of Shriraj Ghorpade. All rights reserved.
 * You may use this script for personal use only. You may not reproduce, distribute, or modify this script
 * for any commercial purposes without explicit written permission from Shriraj Ghorpade.
 * Any unauthorized use, reproduction, or distribution of this script may result in legal action.
 *
 * @created 01/05/2023 - 12:48 PM
 * @project UrbanLadderAutomation
 * @author Shriraj Ghorpade
 */

package com.urbanladder.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.urbanladder.base.TestBase;
import org.testng.ITestResult;

public class TestUtils extends TestBase{

    public static JavascriptExecutor js = (JavascriptExecutor) driver;

    /**
     * This method scrolls to the top of the page.
     */
    public static void scrollToTop(){
        js.executeScript("window.scrollTo(0, 0);");
    }

    /**
     * Hovers the mouse on the element that is passed as parameter.
     * @param element
     */
    public static void hoverOver(WebElement element) {
        Actions action = new Actions(driver);
        action.moveToElement(element).build().perform();
    }

    /**
     * Selects the required option from dropdown.
     * @param dropDown
     * @param optionToBeSelected
     */
    public static void selectFromDropdown(WebElement dropDown, String optionToBeSelected) {
        Select select = new Select(dropDown);
        select.selectByVisibleText(optionToBeSelected);
    }

    /**
     * Switches between windows.
     */
    public static void switchWindow() {
        String parentWindowHandle = driver.getWindowHandle();
        Set<String> windowHandles = driver.getWindowHandles();

        for (String win : windowHandles) {
            if (!win.equals(parentWindowHandle)) {
                driver.switchTo().window(win);
            }
        }
    }

    /**
     * This method takes screenshot.
     * @param driver
     * @param result
     */
    public static void takeScreenshot(WebDriver driver, ITestResult result) {
        try {
            // Generate a unique name for the screenshot based on the test method name and current date and time
            String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
            String screenshotName = System.getProperty("user.dir") + "/src/test/resources/screenshots/" + result.getName() + "_" + timestamp + ".png";

            // Take the screenshot and save it with the generated name
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File(screenshotName));
            System.out.println("Screenshot saved as " + screenshotName);
        } catch (Exception e) {
            System.out.println("Error taking screenshot: " + e.getMessage());
        }
    }
}
