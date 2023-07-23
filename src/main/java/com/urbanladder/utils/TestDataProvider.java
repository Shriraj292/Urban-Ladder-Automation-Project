/*
 * Copyright Disclaimer
 *
 * This script is the intellectual property of Shriraj Ghorpade. All rights reserved.
 * You may use this script for personal use only. You may not reproduce, distribute, or modify this script
 * for any commercial purposes without explicit written permission from Shriraj Ghorpade.
 * Any unauthorized use, reproduction, or distribution of this script may result in legal action.
 *
 * @created 07/05/2023 - 9:12 PM
 * @project UrbanLadderAutomation
 * @author Shriraj Ghorpade
 */
package com.urbanladder.utils;

import com.urbanladder.base.TestBase;
import org.testng.annotations.DataProvider;

public class TestDataProvider extends TestBase {

    @DataProvider(name = "OrderData")
    public Object[][] getData(){
        testData = new ReadExcel(prop.getProperty("fileName"));
        int totalRows = testData.getRowCount();
        int totalCols = testData.getColCount();

        String[][] orderData = new String[totalRows][totalCols];
        for (int i = 1; i <= totalRows; i++) {
            for (int j = 0; j < totalCols; j++) {
                orderData[i-1][j] = testData.getCellData(i, j);
            }
        }

        return orderData;
    }
}
