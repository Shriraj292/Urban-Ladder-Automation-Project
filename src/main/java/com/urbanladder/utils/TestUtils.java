package com.urbanladder.utils;

import java.util.Set;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.urbanladder.base.TestBase;

public class TestUtils extends TestBase{
	
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
	 * @param dropdown
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
}
