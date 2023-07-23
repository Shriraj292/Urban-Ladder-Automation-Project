/*
 * Copyright Disclaimer
 *
 * This script is the intellectual property of Shriraj Ghorpade. All rights reserved.
 * You may use this script for personal use only. You may not reproduce, distribute, or modify this script
 * for any commercial purposes without explicit written permission from Shriraj Ghorpade.
 * Any unauthorized use, reproduction, or distribution of this script may result in legal action.
 *
 * @created 06/05/2023 - 1:15 PM
 * @project UrbanLadderAutomation
 * @author Shriraj Ghorpade
 */
package com.urbanladder.utils;

import com.urbanladder.base.TestBase;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class WriteExcel extends TestBase {

    public String path;
    public FileOutputStream fos = null;
    private XSSFWorkbook workbook = null;
    private XSSFSheet sheet = null;
    private XSSFRow row = null;
    private XSSFCell cell = null;
    XSSFCellStyle style = null;

    public WriteExcel(String fileName, String sheetName) throws IOException {
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet(sheetName);
        style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        path = "src/test/resources/testdata/" + fileName + ".xlsx";
        try {
            fos = new FileOutputStream(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeExcel() throws IOException {
        Object empData[][] = {
                {"Employee ID", "Employee Name", "Age", "Role"},
                {935830, "Shriraj", 23, "Test Automation Engineer"},
                {654321, "Shubham", 24, "Java Backend Developer"},
                {10, "Devraj", 10, "Standard 5"}
        };

        int rowCount = empData.length;
        int colCount = empData[0].length;

        for (int i = 0; i < rowCount; i++) {
            row = sheet.createRow(i);
            for (int j = 0; j < colCount; j++) {

                cell = row.createCell(j);
                if (i==0) {
                    cell.setCellStyle(style);
                }
                Object value = empData[i][j];

                if (value instanceof String){
                    cell.setCellValue((String) value);
                } else if (value instanceof Integer) {
                    cell.setCellValue((Integer) value);
                }

            }
        }
        workbook.write(fos);
        fos.close();
    }

    public static void main(String[] args) throws IOException {
        WriteExcel excel = new WriteExcel("Output", "EmpDetails");
        excel.writeExcel();
    }
}
