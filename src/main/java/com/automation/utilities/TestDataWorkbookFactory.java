package com.automation.utilities;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Creates the default Excel workbook used by data-driven tests when it is missing.
 */
public class TestDataWorkbookFactory {

    private TestDataWorkbookFactory() {
    }

    public static synchronized void ensureDefaultWorkbook(String filePath) {
        try {
            File file = new File(filePath);
            File parent = file.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }

            Workbook workbook = file.exists() && file.length() > 0
                    ? new XSSFWorkbook(new FileInputStream(file))
                    : new XSSFWorkbook();

            boolean changed = false;
            changed |= ensureSheet(workbook, "Registration",
                    new String[]{"Gender", "FirstName", "LastName", "Email", "Password", "ConfirmPassword", "Day", "Month", "Year", "Priority", "Risk", "Type"},
                    new String[][]{
                            {"Male", "Auto", "Customer", "auto.customer@example.com", "Password@123", "Password@123", "15", "5", "1990", "High", "Account creation", "Functional"}
                    });

            changed |= ensureSheet(workbook, "Login",
                    new String[]{"Email", "Password", "RememberMe", "ExpectedResult", "Priority", "Risk", "Type"},
                    new String[][]{
                            {"auto.customer@example.com", "Password@123", "true", "Success", "Critical", "Customer cannot buy without login", "Functional"},
                            {"invalid.customer@example.com", "WrongPassword", "false", "Error", "High", "Authentication errors must be visible", "Edge"}
                    });

            changed |= ensureSheet(workbook, "ProductSearch",
                    new String[]{"ProductName", "Quantity", "ExpectedResult", "Priority", "Risk", "Type"},
                    new String[][]{
                            {"book", "1", "Results", "Critical", "Product discovery blocks revenue", "Functional"},
                            {"zz-no-product-123", "1", "NoResults", "Medium", "Search empty state", "Edge"}
                    });

            changed |= ensureSheet(workbook, "Checkout",
                    new String[]{"TestCaseId", "Email", "Password", "FirstName", "LastName", "Company", "Country", "State", "City", "Address", "ZipCode", "Phone", "ProductName", "Quantity", "ShippingMethod", "PaymentMethod", "ExpectedResult", "Priority", "Risk", "Type", "GenerateUniqueUser"},
                    new String[][]{
                            {"CRITICAL_ORDER", "auto.customer@example.com", "Password@123", "Auto", "Customer", "Automation Inc", "1", "1", "New York", "123 Automation St", "10001", "5551234567", "book", "1", "Ground", "Payments.CashOnDelivery", "OrderPlaced", "Critical", "Revenue checkout path", "Functional", "true"},
                            {"EDGE_MISSING_BILLING", "auto.customer@example.com", "Password@123", "", "", "Automation Inc", "1", "1", "", "", "", "", "book", "1", "Ground", "Payments.CashOnDelivery", "ValidationError", "High", "Checkout validation", "Edge", "true"}
                    });

            if (changed || !file.exists()) {
                try (FileOutputStream outputStream = new FileOutputStream(file)) {
                    workbook.write(outputStream);
                }
                LoggerUtil.info("Default Excel test data workbook created/updated at: " + filePath);
            }
            workbook.close();
        } catch (Exception e) {
            LoggerUtil.error("Unable to create default Excel test data workbook: " + filePath, e);
        }
    }

    private static boolean ensureSheet(Workbook workbook, String sheetName, String[] headers, String[][] rows) {
        Sheet sheet = workbook.getSheet(sheetName);
        boolean changed = false;
        if (sheet == null) {
            sheet = workbook.createSheet(sheetName);
            changed = true;
        }
        if (sheet.getRow(0) == null) {
            writeRow(sheet.createRow(0), headers);
            changed = true;
        }
        if (sheet.getLastRowNum() < rows.length) {
            for (int i = 0; i < rows.length; i++) {
                Row row = sheet.getRow(i + 1);
                if (row == null) {
                    row = sheet.createRow(i + 1);
                    writeRow(row, rows[i]);
                    changed = true;
                }
            }
        }
        return changed;
    }

    private static void writeRow(Row row, String[] values) {
        for (int i = 0; i < values.length; i++) {
            row.createCell(i).setCellValue(values[i]);
        }
    }
}
