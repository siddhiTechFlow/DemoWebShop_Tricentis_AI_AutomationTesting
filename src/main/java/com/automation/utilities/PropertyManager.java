package com.automation.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Property Manager Utility for reading configuration properties
 */
public class PropertyManager {
    private static Properties properties;
    private static final String CONFIG_PATH = "src/test/resources/config.properties";

    static {
        properties = new Properties();
        try (FileInputStream fis = new FileInputStream(CONFIG_PATH)) {
            properties.load(fis);
            LoggerUtil.info("Configuration properties loaded successfully");
        } catch (IOException e) {
            LoggerUtil.error("Error loading configuration properties", e);
        }
    }

    /**
     * Get property value by key
     */
    public static String getProperty(String key) {
        String systemValue = System.getProperty(key);
        if (systemValue != null && !systemValue.trim().isEmpty()) {
            return systemValue;
        }
        return properties.getProperty(key, "");
    }

    /**
     * Get application URL
     */
    public static String getAppUrl() {
        return getProperty("app.url");
    }

    /**
     * Get browser type
     */
    public static String getBrowser() {
        String browser = System.getProperty("browser");
        if (browser != null && !browser.trim().isEmpty()) {
            return browser;
        }
        return getProperty("app.browser");
    }

    public static boolean isHeadless() {
        String headless = System.getProperty("headless", getProperty("app.headless"));
        return Boolean.parseBoolean(headless);
    }

    /**
     * Get implicit wait timeout
     */
    public static long getImplicitWait() {
        return Long.parseLong(getProperty("implicit.wait"));
    }

    /**
     * Get explicit wait timeout
     */
    public static long getExplicitWait() {
        return Long.parseLong(getProperty("explicit.wait"));
    }

    /**
     * Get test data path
     */
    public static String getTestDataPath() {
        return getProperty("testdata.path");
    }

    /**
     * Get test data filename
     */
    public static String getTestDataFilename() {
        return getProperty("testdata.filename");
    }

    /**
     * Get screenshot path
     */
    public static String getScreenshotPath() {
        return getProperty("screenshot.path");
    }

    /**
     * Get extent report path
     */
    public static String getExtentReportPath() {
        return getProperty("extent.report.path");
    }

    /**
     * Get extent report name
     */
    public static String getExtentReportName() {
        return getProperty("extent.report.name");
    }

    public static boolean captureSuccessScreenshots() {
        return Boolean.parseBoolean(getProperty("screenshot.on.success"));
    }
}
