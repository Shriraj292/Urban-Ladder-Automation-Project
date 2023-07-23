/*
 * Copyright Disclaimer
 *
 * This script is the intellectual property of Shriraj Ghorpade. All rights reserved.
 * You may use this script for personal use only. You may not reproduce, distribute, or modify this script
 * for any commercial purposes without explicit written permission from Shriraj Ghorpade.
 * Any unauthorized use, reproduction, or distribution of this script may result in legal action.
 *
 * @created 01/05/2023 - 1:04 PM
 * @project UrbanLadderAutomation
 * @author Shriraj Ghorpade
 */

package com.urbanladder.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import com.urbanladder.base.TestBase;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel extends TestBase {
    public String path;
    public FileInputStream fis = null;
    private XSSFWorkbook workbook = null;
    private XSSFSheet sheet = null;
    private XSSFRow row = null;
    private XSSFCell cell = null;

    /******************************************* Reading Excel **********************************************/
    public ReadExcel(String fileName) {
        String path = "\\src\\test\\resources\\testdata\\" + fileName + ".xlsx";

        try {
            fis = new FileInputStream(System.getProperty("user.dir") + path);
            workbook = new XSSFWorkbook(fis);
            String sheetName = prop.getProperty("sheetName");
            sheet = workbook.getSheet(sheetName);
            fis.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public int getRowCount() {
        return sheet.getLastRowNum();
    }

    public int getColCount(){
        return sheet.getRow(0).getLastCellNum();
    }

    public String getCellData(int rowNum, int colNum){
        row = sheet.getRow(rowNum);
        cell = row.getCell(colNum);

        DataFormatter formatter = new DataFormatter();
        String data;
        try {
            data = formatter.formatCellValue(cell);
        }catch (Exception e){
            data = "";
        }
        return data;
    }

    public void readTestData(String sheetName){
        int rowCount = getRowCount();
        int colCount = getColCount();
        for (int i = 1; i < rowCount; i++) {
            row = sheet.getRow(i);
            for (int j = 0; j < colCount; j++) {
                cell = row.getCell(j);
                switch (cell.getCellType()){
                    case STRING -> {
                        System.out.println(sheet.getRow(0).getCell(j).getStringCellValue().toString().trim() + ": "
                                + cell.getStringCellValue().toString().trim());
                    }

                    case NUMERIC -> {
                        System.out.println(sheet.getRow(0).getCell(j).getStringCellValue().toString().trim() + ": "
                                + cell.getNumericCellValue());
                    }

                }
            }
            System.out.println();
        }
    }
}
