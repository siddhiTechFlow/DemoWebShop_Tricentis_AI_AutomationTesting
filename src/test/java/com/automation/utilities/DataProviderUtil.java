package com.automation.utilities;

import org.testng.annotations.DataProvider;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Data Provider Utility Class for data-driven testing
 */
public class DataProviderUtil {
    private static final String TEST_DATA_PATH = PropertyManager.getTestDataPath() + PropertyManager.getTestDataFilename();

    static {
        TestDataWorkbookFactory.ensureDefaultWorkbook(TEST_DATA_PATH);
    }

    /**
     * Provide registration test data
     */
    @DataProvider(name = "registrationData")
    public static Object[][] getRegistrationData() {
        ExcelUtil excelUtil = new ExcelUtil(TEST_DATA_PATH, "Registration");
        int rowCount = excelUtil.getRowCount();
        Object[][] data = new Object[rowCount - 1][1];
        
        for (int i = 1; i < rowCount; i++) {
            data[i - 1][0] = excelUtil.getRowData(i);
        }
        
        excelUtil.closeWorkbook();
        return data;
    }

    /**
     * Provide login test data
     */
    @DataProvider(name = "loginData")
    public static Object[][] getLoginData() {
        ExcelUtil excelUtil = new ExcelUtil(TEST_DATA_PATH, "Login");
        int rowCount = excelUtil.getRowCount();
        Object[][] data = new Object[rowCount - 1][1];
        
        for (int i = 1; i < rowCount; i++) {
            data[i - 1][0] = excelUtil.getRowData(i);
        }
        
        excelUtil.closeWorkbook();
        return data;
    }

    /**
     * Provide product search test data
     */
    @DataProvider(name = "productSearchData")
    public static Object[][] getProductSearchData() {
        ExcelUtil excelUtil = new ExcelUtil(TEST_DATA_PATH, "ProductSearch");
        int rowCount = excelUtil.getRowCount();
        Object[][] data = new Object[rowCount - 1][1];
        
        for (int i = 1; i < rowCount; i++) {
            data[i - 1][0] = excelUtil.getRowData(i);
        }
        
        excelUtil.closeWorkbook();
        return data;
    }

    /**
     * Provide checkout test data
     */
    @DataProvider(name = "checkoutData")
    public static Object[][] getCheckoutData() {
        return getFilteredSheetData("Checkout", null, null);
    }

    @DataProvider(name = "criticalCheckoutData")
    public static Object[][] getCriticalCheckoutData() {
        return getFilteredSheetData("Checkout", "ExpectedResult", "OrderPlaced");
    }

    @DataProvider(name = "edgeCheckoutData")
    public static Object[][] getEdgeCheckoutData() {
        return getFilteredSheetData("Checkout", "ExpectedResult", "ValidationError");
    }

    private static Object[][] getFilteredSheetData(String sheetName, String filterColumn, String filterValue) {
        ExcelUtil excelUtil = new ExcelUtil(TEST_DATA_PATH, sheetName);
        List<Map<String, String>> rows = new ArrayList<>();
        int rowCount = excelUtil.getRowCount();
        for (int i = 1; i < rowCount; i++) {
            Map<String, String> row = excelUtil.getRowData(i);
            if (filterColumn == null || filterValue.equalsIgnoreCase(row.getOrDefault(filterColumn, ""))) {
                rows.add(row);
            }
        }
        excelUtil.closeWorkbook();
        Object[][] data = new Object[rows.size()][1];
        for (int i = 0; i < rows.size(); i++) {
            data[i][0] = rows.get(i);
        }
        return data;
    }

    /**
     * Get data list from Excel
     */
    public static List<Map<String, String>> getDataList(String sheetName) {
        ExcelUtil excelUtil = new ExcelUtil(TEST_DATA_PATH, sheetName);
        List<Map<String, String>> dataList = new ArrayList<>();
        int rowCount = excelUtil.getRowCount();
        
        for (int i = 1; i < rowCount; i++) {
            dataList.add(excelUtil.getRowData(i));
        }
        
        excelUtil.closeWorkbook();
        return dataList;
    }
}
