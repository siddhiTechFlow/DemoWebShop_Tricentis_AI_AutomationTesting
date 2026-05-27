package com.automation.pages;

import com.automation.utilities.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import java.util.Arrays;
import java.util.List;

/**
 * Base Page Class with common methods for all page objects
 */
public class BasePage {
    protected WebDriver driver;
    protected WaitUtil waitUtil;

    /**
     * Constructor
     */
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.waitUtil = new WaitUtil(driver);
    }

    /**
     * Click on element
     */
    public void clickElement(By locator) {
        clickElement(locator, new By[0]);
    }

    /**
     * Type text in element
     */
    public void typeText(By locator, String text) {
        typeText(locator, text, new By[0]);
    }

    /**
     * Get text from element
     */
    public String getText(By locator) {
        return getText(locator, new By[0]);
    }

    /**
     * Get text from first available element using primary and fallback locators
     */
    public String getText(By locator, By... fallbackLocators) {
        WebElement element = findFirstVisibleElement(joinLocators(locator, fallbackLocators));
        if (element != null) {
            return element.getText();
        }
        return "";
    }

    /**
     * Click on element using primary and fallback locators
     */
    public void clickElement(By locator, By... fallbackLocators) {
        By[] locators = joinLocators(locator, fallbackLocators);
        try {
            WebElement element = findFirstClickableElement(locators);
            if (element == null) {
                throw new NoSuchElementException("No clickable element found for: " + Arrays.toString(locators));
            }
            scrollIntoView(element);
            try {
                element.click();
            } catch (ElementClickInterceptedException intercepted) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
            }
            LoggerUtil.info("Clicked on element: " + Arrays.toString(locators));
        } catch (Exception e) {
            LoggerUtil.error("Error clicking element: " + locator, e);
            throw e;
        }
    }

    /**
     * Type text in element using primary and fallback locators
     */
    public void typeText(By locator, String text, By... fallbackLocators) {
        By[] locators = joinLocators(locator, fallbackLocators);
        try {
            WebElement element = findFirstVisibleElement(locators);
            if (element == null) {
                throw new NoSuchElementException("No visible input found for: " + Arrays.toString(locators));
            }
            scrollIntoView(element);
            element.clear();
            element.sendKeys(text);
            LoggerUtil.info("Typed text in element: " + Arrays.toString(locators));
        } catch (Exception e) {
            LoggerUtil.error("Error typing text in element: " + locator, e);
            throw e;
        }
    }

    protected WebElement findFirstVisibleElement(By... locators) {
        return waitUtil.waitForAnyVisible(locators);
    }

    protected WebElement findFirstClickableElement(By... locators) {
        return waitUtil.waitForAnyClickable(locators);
    }

    private By[] joinLocators(By locator, By... fallbackLocators) {
        By[] locators = new By[fallbackLocators.length + 1];
        locators[0] = locator;
        System.arraycopy(fallbackLocators, 0, locators, 1, fallbackLocators.length);
        return locators;
    }

    protected void scrollIntoView(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", element);
    }

    /**
     * Get attribute value
     */
    public String getAttribute(By locator, String attributeName) {
        try {
            WebElement element = waitUtil.waitForElementToBePresent(locator);
            if (element != null) {
                return element.getAttribute(attributeName);
            }
        } catch (Exception e) {
            LoggerUtil.error("Error getting attribute from element: " + locator, e);
        }
        return "";
    }

    /**
     * Check if element is displayed
     */
    public boolean isElementDisplayed(By locator) {
        return isAnyElementDisplayed(locator);
    }

    public boolean isAnyElementDisplayed(By... locators) {
        try {
            WebElement element = findFirstVisibleElement(locators);
            return element != null && element.isDisplayed();
        } catch (Exception e) {
            LoggerUtil.debug("Element not displayed: " + Arrays.toString(locators));
            return false;
        }
    }

    /**
     * Check if element is enabled
     */
    public boolean isElementEnabled(By locator) {
        try {
            WebElement element = driver.findElement(locator);
            return element.isEnabled();
        } catch (Exception e) {
            LoggerUtil.debug("Element not enabled: " + locator);
            return false;
        }
    }

    /**
     * Select dropdown by visible text
     */
    public void selectDropdownByVisibleText(By locator, String visibleText) {
        try {
            WebElement element = findFirstVisibleElement(locator);
            if (element != null) {
                Select select = new Select(element);
                select.selectByVisibleText(visibleText);
                LoggerUtil.info("Selected dropdown option: " + visibleText);
            }
        } catch (Exception e) {
            LoggerUtil.error("Error selecting dropdown option: " + visibleText, e);
        }
    }

    /**
     * Select dropdown by value
     */
    public void selectDropdownByValue(By locator, String value) {
        try {
            WebElement element = findFirstVisibleElement(locator);
            if (element != null) {
                Select select = new Select(element);
                select.selectByValue(value);
                LoggerUtil.info("Selected dropdown option by value: " + value);
            }
        } catch (Exception e) {
            LoggerUtil.error("Error selecting dropdown option by value: " + value, e);
        }
    }

    /**
     * Select dropdown by index
     */
    public void selectDropdownByIndex(By locator, int index) {
        try {
            WebElement element = waitUtil.waitForElementToBePresent(locator);
            if (element != null) {
                Select select = new Select(element);
                select.selectByIndex(index);
                LoggerUtil.info("Selected dropdown option by index: " + index);
            }
        } catch (Exception e) {
            LoggerUtil.error("Error selecting dropdown option by index: " + index, e);
        }
    }

    /**
     * Get all dropdown options
     */
    public List<WebElement> getDropdownOptions(By locator) {
        try {
            WebElement element = waitUtil.waitForElementToBePresent(locator);
            if (element != null) {
                Select select = new Select(element);
                return select.getOptions();
            }
        } catch (Exception e) {
            LoggerUtil.error("Error getting dropdown options", e);
        }
        return null;
    }

    /**
     * Move to element
     */
    public void moveToElement(By locator) {
        try {
            WebElement element = driver.findElement(locator);
            Actions actions = new Actions(driver);
            actions.moveToElement(element).perform();
            LoggerUtil.info("Moved to element: " + locator);
        } catch (Exception e) {
            LoggerUtil.error("Error moving to element: " + locator, e);
        }
    }

    /**
     * Double click element
     */
    public void doubleClickElement(By locator) {
        try {
            WebElement element = driver.findElement(locator);
            Actions actions = new Actions(driver);
            actions.doubleClick(element).perform();
            LoggerUtil.info("Double clicked element: " + locator);
        } catch (Exception e) {
            LoggerUtil.error("Error double clicking element: " + locator, e);
        }
    }

    /**
     * Right click element
     */
    public void rightClickElement(By locator) {
        try {
            WebElement element = driver.findElement(locator);
            Actions actions = new Actions(driver);
            actions.contextClick(element).perform();
            LoggerUtil.info("Right clicked element: " + locator);
        } catch (Exception e) {
            LoggerUtil.error("Error right clicking element: " + locator, e);
        }
    }

    /**
     * Wait for page title
     */
    public boolean waitForPageTitle(String title) {
        return waitUtil.waitForPageTitle(title);
    }

    /**
     * Get current URL
     */
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    /**
     * Get page title
     */
    public String getPageTitle() {
        return driver.getTitle();
    }

    /**
     * Refresh page
     */
    public void refreshPage() {
        driver.navigate().refresh();
        LoggerUtil.info("Page refreshed");
    }

    /**
     * Switch to alert
     */
    public void acceptAlert() {
        try {
            driver.switchTo().alert().accept();
            LoggerUtil.info("Alert accepted");
        } catch (Exception e) {
            LoggerUtil.error("Error accepting alert", e);
        }
    }

    /**
     * Wait for custom seconds
     */
    public void waitForSeconds(long seconds) {
        waitUtil.waitForSeconds(seconds);
    }
}
