package com.automation.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

/**
 * Wait Utility Class for explicit waits
 */
public class WaitUtil {
    private WebDriver driver;
    private WebDriverWait wait;
    private final long timeout;

    /**
     * Constructor
     */
    public WaitUtil(WebDriver driver) {
        this.driver = driver;
        this.timeout = Math.max(1, PropertyManager.getExplicitWait());
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
    }

    public WebElement waitForAnyVisible(By... locators) {
        try {
            return wait.until(webDriver -> {
                for (By locator : locators) {
                    List<WebElement> elements = webDriver.findElements(locator);
                    for (WebElement element : elements) {
                        try {
                            if (element.isDisplayed()) {
                                LoggerUtil.debug("Visible element found with locator: " + locator);
                                return element;
                            }
                        } catch (StaleElementReferenceException ignored) {
                            LoggerUtil.debug("Stale element while checking visibility: " + locator);
                        }
                    }
                }
                return null;
            });
        } catch (Exception e) {
            LoggerUtil.debug("No visible element found for locators: " + Arrays.toString(locators));
            return null;
        }
    }

    public WebElement waitForAnyClickable(By... locators) {
        try {
            return wait.until(webDriver -> {
                for (By locator : locators) {
                    List<WebElement> elements = webDriver.findElements(locator);
                    for (WebElement element : elements) {
                        try {
                            if (element.isDisplayed() && element.isEnabled()) {
                                LoggerUtil.debug("Clickable element found with locator: " + locator);
                                return element;
                            }
                        } catch (StaleElementReferenceException ignored) {
                            LoggerUtil.debug("Stale element while checking clickability: " + locator);
                        }
                    }
                }
                return null;
            });
        } catch (Exception e) {
            LoggerUtil.debug("No clickable element found for locators: " + Arrays.toString(locators));
            return null;
        }
    }

    /**
     * Wait for element to be visible
     */
    public WebElement waitForElementToBeVisible(By locator) {
        try {
            LoggerUtil.debug("Waiting for element to be visible: " + locator);
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception e) {
            LoggerUtil.error("Element not visible: " + locator, e);
            return null;
        }
    }

    /**
     * Wait for element to be clickable
     */
    public WebElement waitForElementToBeClickable(By locator) {
        try {
            LoggerUtil.debug("Waiting for element to be clickable: " + locator);
            return wait.until(ExpectedConditions.elementToBeClickable(locator));
        } catch (Exception e) {
            LoggerUtil.error("Element not clickable: " + locator, e);
            return null;
        }
    }

    /**
     * Wait for element to be present
     */
    public WebElement waitForElementToBePresent(By locator) {
        try {
            LoggerUtil.debug("Waiting for element to be present: " + locator);
            return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (Exception e) {
            LoggerUtil.error("Element not present: " + locator, e);
            return null;
        }
    }

    /**
     * Wait for element to disappear
     */
    public boolean waitForElementToDisappear(By locator) {
        try {
            LoggerUtil.debug("Waiting for element to disappear: " + locator);
            return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        } catch (Exception e) {
            LoggerUtil.error("Element still visible: " + locator, e);
            return false;
        }
    }

    /**
     * Wait for text to be present in element
     */
    public boolean waitForTextInElement(By locator, String text) {
        try {
            LoggerUtil.debug("Waiting for text in element: " + text);
            return wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
        } catch (Exception e) {
            LoggerUtil.error("Text not found in element", e);
            return false;
        }
    }

    /**
     * Wait for page title
     */
    public boolean waitForPageTitle(String title) {
        try {
            LoggerUtil.debug("Waiting for page title: " + title);
            return wait.until(ExpectedConditions.titleContains(title));
        } catch (Exception e) {
            LoggerUtil.error("Page title not found: " + title, e);
            return false;
        }
    }

    /**
     * Custom wait time
     */
    public void waitForSeconds(long seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            LoggerUtil.error("Wait interrupted", e);
        }
    }
}
