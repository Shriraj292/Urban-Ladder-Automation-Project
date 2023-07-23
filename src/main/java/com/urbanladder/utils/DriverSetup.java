/*
 * Copyright Disclaimer
 *
 * This script is the intellectual property of Shriraj Ghorpade. All rights reserved.
 * You may use this script for personal use only. You may not reproduce, distribute, or modify this script
 * for any commercial purposes without explicit written permission from Shriraj Ghorpade.
 * Any unauthorized use, reproduction, or distribution of this script may result in legal action.
 *
 * @created 01/05/2023 - 12:46 PM
 * @project UrbanLadderAutomation
 * @author Shriraj Ghorpade
 */

package com.urbanladder.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class DriverSetup {

    //Declaring WebDriver instance
    private static WebDriver driver;
    static ChromeOptions options = new ChromeOptions();

    /**
     * Method name: getChromeDriver
     * Description: This method setup web driver manager to launch Chrome browser, initializes web driver instance and returns driver instance.
     * @return
     */
    public static WebDriver getChromeDriver() {
        //Setup WebDriver Manager for Chrome
        WebDriverManager.chromedriver().setup();

        options.addArguments("--remote-allow-origins=*");

        //Initializing WebDriver instance
        driver = new ChromeDriver(options);

        return driver;
    }

    /**
     * Method name: getFireDriver
     * Description: This method setup web driver manager to launch FireFox browser, initializes web driver instance and returns driver instance.
     * @return
     */
    public static WebDriver getFireDriver() {
        //Setup WebDriver Manager for FireFox
        WebDriverManager.firefoxdriver().setup();

        //Initializing WebDriver instance
        driver = new FirefoxDriver();

        return driver;
    }

    /**
     * Method name: getEdgeDriver
     * Description: This method setup web driver manager to launch MS Edge browser, initializes web driver instance and returns driver instance.
     * @return
     */
    public static WebDriver getEdgeDriver() {
        //Setup WebDriver Manager for MS Edge
        WebDriverManager.edgedriver().setup();

        //Initializing WebDriver instance
        driver = new EdgeDriver();

        return driver;
    }

}
