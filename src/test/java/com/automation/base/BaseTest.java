package com.automation.base;

import com.automation.utilities.*;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeMethod;
import java.lang.reflect.Method;

/**
 * Base Test Class with common setup and teardown methods
 */
public class BaseTest {
    protected WebDriver driver;
    protected ExtentTest extentTest;
    protected String testName;

    /**
     * Setup method - runs before each test
     */
    @BeforeMethod
    public void setup(ITestContext context, Method method) {
        try {
            testName = context.getCurrentXmlTest().getName() + " - " + method.getName();
            LoggerUtil.info("=========================== Test Execution Started: " + testName + " ===========================");
            
            // Initialize WebDriver
            String browser = PropertyManager.getBrowser();
            driver = DriverManager.initializeDriver(browser);
            
            // Initialize Extent Report
            extentTest = ExtentReportManager.createTest(testName, method.getAnnotation(org.testng.annotations.Test.class).description());
            extentTest.assignCategory(method.getAnnotation(org.testng.annotations.Test.class).groups());
            extentTest.info("Browser: " + browser);
            extentTest.info("Application URL: " + PropertyManager.getAppUrl());
            
            // Navigate to application
            String appUrl = PropertyManager.getAppUrl();
            DriverManager.navigateToUrl(appUrl);
            
            LoggerUtil.info("Test setup completed successfully");
        } catch (Exception e) {
            LoggerUtil.error("Error in test setup", e);
            throw e;
        }
    }

    /**
     * Teardown method - runs after each test
     */
    @AfterMethod
    public void tearDown(ITestResult result) {
        try {
            if (result.getStatus() == ITestResult.FAILURE) {
                // Capture screenshot on failure
                String screenshotPath = ScreenshotUtil.captureScreenshotOnFailure(driver, testName);
                if (screenshotPath != null) {
                    ExtentReportManager.addScreenshot(extentTest, screenshotPath);
                }
                
                // Log failure in report
                ExtentReportManager.logTestFail(extentTest, "Test failed: " + result.getThrowable().getMessage());
                LoggerUtil.error("Test failed: " + result.getThrowable());
            } else if (result.getStatus() == ITestResult.SUCCESS) {
                if (PropertyManager.captureSuccessScreenshots()) {
                    String screenshotPath = ScreenshotUtil.captureScreenshotOnSuccess(driver, testName);
                    if (screenshotPath != null) {
                        ExtentReportManager.addScreenshot(extentTest, screenshotPath);
                    }
                }
                
                ExtentReportManager.logTestPass(extentTest, "Test passed successfully");
                LoggerUtil.info("Test passed");
            } else if (result.getStatus() == ITestResult.SKIP) {
                ExtentReportManager.logTestWarning(extentTest, "Test skipped");
                LoggerUtil.warn("Test skipped");
            }
            
            LoggerUtil.info("=========================== Test Execution Completed: " + testName + " ===========================");
        } catch (Exception e) {
            LoggerUtil.error("Error in test teardown", e);
        } finally {
            // Close WebDriver
            try {
                if (driver != null) {
                    DriverManager.closeDriver();
                }
            } catch (Exception e) {
                LoggerUtil.error("Error closing driver", e);
            }
        }
    }

    /**
     * Setup method for test suite - runs once before all tests
     */
    @BeforeSuite(alwaysRun = true)
    public static void suiteSetup() {
        try {
            LoggerUtil.info("Test Suite Setup - Initializing Extent Report");
            ExtentReportManager.initializeReport();
        } catch (Exception e) {
            LoggerUtil.error("Error in suite setup", e);
        }
    }

    /**
     * Teardown method for test suite - runs once after all tests
     */
    @AfterSuite(alwaysRun = true)
    public static void suiteTearDown() {
        try {
            LoggerUtil.info("Test Suite Teardown - Flushing Extent Report");
            ExtentReportManager.flushReport();
        } catch (Exception e) {
            LoggerUtil.error("Error in suite teardown", e);
        }
    }

    /**
     * Utility wait method available to test classes
     */
    protected void waitForSeconds(long seconds) {
        try {
            WaitUtil waitUtil = new WaitUtil(driver);
            waitUtil.waitForSeconds(seconds);
        } catch (Exception e) {
            LoggerUtil.error("Error during wait", e);
        }
    }
}
