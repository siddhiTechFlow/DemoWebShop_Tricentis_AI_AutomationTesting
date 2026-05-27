package com.automation.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * WebDriver Manager Utility for managing WebDriver instances
 */
public class DriverManager {
    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

    /**
     * Initialize WebDriver based on browser type
     */
    public static WebDriver initializeDriver(String browser) {
        if (browser == null || browser.isEmpty()) {
            browser = "chrome";
        }

        switch (browser.toLowerCase()) {
            case "chrome":
                DRIVER.set(initializeChromeDriver());
                break;
            case "firefox":
                DRIVER.set(initializeFirefoxDriver());
                break;
            default:
                LoggerUtil.warn("Unknown browser: " + browser + ". Using Chrome as default.");
                DRIVER.set(initializeChromeDriver());
        }

        configureDriver(getDriver());
        return getDriver();
    }

    /**
     * Initialize Chrome Driver
     */
    private static WebDriver initializeChromeDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("enable-automation");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        if (PropertyManager.isHeadless()) {
            options.addArguments("--headless=new");
            options.addArguments("--window-size=1920,1080");
        }
        
        LoggerUtil.info("Initializing Chrome WebDriver");
        return new ChromeDriver(options);
    }

    /**
     * Initialize Firefox Driver
     */
    private static WebDriver initializeFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        if (PropertyManager.isHeadless()) {
            options.addArguments("-headless");
        }
        
        LoggerUtil.info("Initializing Firefox WebDriver");
        return new FirefoxDriver(options);
    }

    /**
     * Configure Driver settings
     */
    private static void configureDriver(WebDriver driver) {
        driver.manage().timeouts()
        .implicitlyWait(Duration.ofSeconds(PropertyManager.getImplicitWait()))
        .pageLoadTimeout(Duration.ofSeconds(PropertyManager.getExplicitWait()));
        
        driver.manage().window().maximize();
        LoggerUtil.info("WebDriver configured successfully");
    }

    /**
     * Get current driver instance
     */
    public static WebDriver getDriver() {
        if (DRIVER.get() == null) {
            initializeDriver("chrome");
        }
        return DRIVER.get();
    }

    /**
     * Close WebDriver
     */
    public static void closeDriver() {
        WebDriver driver = DRIVER.get();
        if (driver != null) {
            driver.quit();
            DRIVER.remove();
            LoggerUtil.info("WebDriver closed successfully");
        }
    }

    /**
     * Navigate to URL
     */
    public static void navigateToUrl(String url) {
        try {
            getDriver().navigate().to(url);
            LoggerUtil.info("Navigated to: " + url);
        } catch (Exception e) {
            LoggerUtil.error("Error navigating to URL: " + url, e);
        }
    }
}
