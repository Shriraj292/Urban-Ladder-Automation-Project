package com.urbanladder.utils;

import java.time.Duration;

import org.openqa.selenium.support.ui.WebDriverWait;

import com.urbanladder.base.TestBase;

public class Timeouts extends TestBase{
	
	public static long PAGE_LOAD_TIMEOUT = 30;
	public static long IMPLICIT_WAIT = 30;
	public static long EXPLICIT_WAIT = 30;
	
	public static WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT));

}
