package com.automation.utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Excel Utility Class for reading and writing test data
 */
public class ExcelUtil {
    private Workbook workbook;
    private Sheet sheet;
    private String filePath;

    /**
     * Initialize Excel file
     */
    public ExcelUtil(String filePath, String sheetName) {
        this.filePath = filePath;
        try {
            File file = new File(filePath);
            if (file.exists()) {
                FileInputStream fis = new FileInputStream(file);
                workbook = new XSSFWorkbook(fis);
            } else {
                workbook = new XSSFWorkbook();
            }
            sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                sheet = workbook.createSheet(sheetName);
            }
        } catch (IOException e) {
            LoggerUtil.error("Error initializing Excel file: " + filePath, e);
        }
    }

    /**
     * Read data from specific cell
     */
    public String getCellValue(int rowNum, int cellNum) {
        try {
            Row row = sheet.getRow(rowNum);
            if (row != null) {
                Cell cell = row.getCell(cellNum);
                if (cell != null) {
                    return getCellValueAsString(cell);
                }
            }
            return "";
        } catch (Exception e) {
            LoggerUtil.error("Error reading cell data", e);
            return "";
        }
    }

    /**
     * Get cell value as string
     */
    private String getCellValueAsString(Cell cell) {
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf((long) cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            default:
                return "";
        }
    }

    /**
     * Get row data as Map with headers
     */
    public Map<String, String> getRowData(int rowNum) {
        Map<String, String> rowData = new HashMap<>();
        try {
            Row headerRow = sheet.getRow(0);
            Row dataRow = sheet.getRow(rowNum);

            if (headerRow != null && dataRow != null) {
                for (int i = 0; i < headerRow.getLastCellNum(); i++) {
                    Cell headerCell = headerRow.getCell(i);
                    Cell dataCell = dataRow.getCell(i);
                    if (headerCell != null && dataCell != null) {
                        rowData.put(getCellValueAsString(headerCell), 
                                   getCellValueAsString(dataCell));
                    }
                }
            }
        } catch (Exception e) {
            LoggerUtil.error("Error reading row data", e);
        }
        return rowData;
    }

    /**
     * Write data to cell
     */
    public void setCellValue(int rowNum, int cellNum, String value) {
        try {
            Row row = sheet.getRow(rowNum);
            if (row == null) {
                row = sheet.createRow(rowNum);
            }
            Cell cell = row.getCell(cellNum);
            if (cell == null) {
                cell = row.createCell(cellNum);
            }
            cell.setCellValue(value);
        } catch (Exception e) {
            LoggerUtil.error("Error writing cell data", e);
        }
    }

    /**
     * Get total rows in sheet
     */
    public int getRowCount() {
        return sheet.getLastRowNum() + 1;
    }

    /**
     * Save changes to Excel
     */
    public void saveChanges() {
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            workbook.write(fos);
            LoggerUtil.info("Excel file saved successfully");
        } catch (IOException e) {
            LoggerUtil.error("Error saving Excel file", e);
        }
    }

    /**
     * Close workbook
     */
    public void closeWorkbook() {
        try {
            if (workbook != null) {
                workbook.close();
            }
        } catch (IOException e) {
            LoggerUtil.error("Error closing workbook", e);
        }
    }
}
