package com.automation.utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Screenshot Utility Class for capturing screenshots
 */
public class ScreenshotUtil {
    private static final String SCREENSHOT_PATH = "screenshots/";

    static {
        File screenshotDir = new File(SCREENSHOT_PATH);
        if (!screenshotDir.exists()) {
            screenshotDir.mkdirs();
        }
    }

    /**
     * Capture screenshot with timestamp
     */
    public static String captureScreenshot(WebDriver driver, String testName) {
        try {
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
            
            String timestamp = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss-SSS"));
            String fileName = testName + "_" + timestamp + ".png";
            String destinationPath = SCREENSHOT_PATH + fileName;
            
            Files.copy(srcFile.toPath(), Paths.get(destinationPath));
            LoggerUtil.info("Screenshot captured: " + destinationPath);
            return destinationPath;
        } catch (IOException e) {
            LoggerUtil.error("Failed to capture screenshot", e);
            return null;
        }
    }

    /**
     * Capture screenshot on test failure
     */
    public static String captureScreenshotOnFailure(WebDriver driver, String testName) {
        return captureScreenshot(driver, "FAIL_" + testName);
    }

    /**
     * Capture screenshot on test success
     */
    public static String captureScreenshotOnSuccess(WebDriver driver, String testName) {
        return captureScreenshot(driver, "PASS_" + testName);
    }
}
