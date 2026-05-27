package com.automation.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Extent Reports Utility Class
 */
public class ExtentReportManager {
    private static ExtentReports extentReports;
    private static ExtentSparkReporter sparkReporter;
    private static final String REPORT_PATH = PropertyManager.getExtentReportPath();

    /**
     * Initialize Extent Reports
     */
    public static void initializeReport() {
        try {
            File reportDir = new File(REPORT_PATH);
            if (!reportDir.exists()) {
                reportDir.mkdirs();
            }

            String timestamp = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
            String reportName = "ExtentReport_" + timestamp + ".html";
            String reportPath = REPORT_PATH + reportName;

            sparkReporter = new ExtentSparkReporter(reportPath);
            sparkReporter.config().setTheme(Theme.DARK);
            sparkReporter.config().setDocumentTitle("Test Automation Report");
            sparkReporter.config().setReportName("Demo Web Shop Automation Report");

            extentReports = new ExtentReports();
            extentReports.attachReporter(sparkReporter);
            extentReports.setSystemInfo("OS", System.getProperty("os.name"));
            extentReports.setSystemInfo("Java Version", System.getProperty("java.version"));
            extentReports.setSystemInfo("Browser", PropertyManager.getBrowser());
            extentReports.setSystemInfo("Application URL", PropertyManager.getAppUrl());

            LoggerUtil.info("Extent Report initialized: " + reportPath);
        } catch (Exception e) {
            LoggerUtil.error("Error initializing Extent Report", e);
        }
    }

    /**
     * Get Extent Reports instance
     */
    public static ExtentReports getExtentReports() {
        if (extentReports == null) {
            initializeReport();
        }
        return extentReports;
    }

    /**
     * Create test case in report
     */
    public static ExtentTest createTest(String testName, String description) {
        ExtentTest test = getExtentReports().createTest(testName, description);
        LoggerUtil.info("Test created in report: " + testName);
        return test;
    }

    /**
     * Log test step as pass
     */
    public static void logTestPass(ExtentTest test, String message) {
        test.log(Status.PASS, message);
        LoggerUtil.info("[PASS] " + message);
    }

    /**
     * Log test step as fail
     */
    public static void logTestFail(ExtentTest test, String message) {
        test.log(Status.FAIL, message);
        LoggerUtil.error("[FAIL] " + message);
    }

    /**
     * Log test step as info
     */
    public static void logTestInfo(ExtentTest test, String message) {
        test.log(Status.INFO, message);
        LoggerUtil.info("[INFO] " + message);
    }

    /**
     * Log test step as warning
     */
    public static void logTestWarning(ExtentTest test, String message) {
        test.log(Status.WARNING, message);
        LoggerUtil.warn("[WARNING] " + message);
    }

    /**
     * Add screenshot to report
     */
    public static void addScreenshot(ExtentTest test, String screenshotPath) {
        try {
            String imagePath = new File(screenshotPath).getAbsolutePath();
            test.addScreenCaptureFromPath(imagePath);
            LoggerUtil.info("Screenshot added to report: " + screenshotPath);
        } catch (Exception e) {
            LoggerUtil.error("Error adding screenshot to report", e);
        }
    }

    /**
     * Flush Extent Reports
     */
    public static void flushReport() {
        try {
            if (extentReports != null) {
                extentReports.flush();
                LoggerUtil.info("Extent Report flushed successfully");
            }
        } catch (Exception e) {
            LoggerUtil.error("Error flushing Extent Report", e);
        }
    }
}
