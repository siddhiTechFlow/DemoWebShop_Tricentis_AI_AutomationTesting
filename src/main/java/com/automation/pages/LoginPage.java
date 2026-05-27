package com.automation.pages;

import com.automation.utilities.LoggerUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Login Page Object Class
 */
public class LoginPage extends BasePage {
    // Locators
    private By pageTitle = By.xpath("//h1[contains(.,'Welcome') or contains(.,'Returning Customer')]");
    private By emailField = By.id("Email");
    private By passwordField = By.id("Password");
    private By rememberMeCheckbox = By.id("RememberMe");
    private By loginButton = By.xpath("//input[@value='Log in']");
    private By loginButtonCss = By.cssSelector("input.login-button, button[type='submit']");
    private By logoutLink = By.xpath("//a[text()='Log out']");
    private By errorMessage = By.xpath("//div[@class='validation-summary-errors']");

    /**
     * Constructor
     */
    public LoginPage(WebDriver driver) {
        super(driver);
        LoggerUtil.info("Initializing LoginPage");
    }

    /**
     * Check if login page is loaded
     */
    public boolean isLoginPageLoaded() {
        return isAnyElementDisplayed(pageTitle, emailField, passwordField);
    }

    /**
     * Check if login form UI elements are visible
     */
    public boolean isLoginFormVisible() {
        return isElementDisplayed(emailField)
                && isElementDisplayed(passwordField)
                && isElementDisplayed(loginButton)
                && isElementDisplayed(rememberMeCheckbox);
    }

    /**
     * Enter email
     */
    public void enterEmail(String email) {
        typeText(emailField, email, By.name("Email"), By.cssSelector("input[type='email']"));
        LoggerUtil.info("Email entered: " + email);
    }

    /**
     * Enter password
     */
    public void enterPassword(String password) {
        typeText(passwordField, password, By.name("Password"), By.cssSelector("input[type='password']"));
        LoggerUtil.info("Password entered");
    }

    /**
     * Click remember me checkbox
     */
    public void clickRememberMeCheckbox() {
        clickElement(rememberMeCheckbox);
        LoggerUtil.info("Remember me checkbox clicked");
    }

    /**
     * Click login button
     */
    public void clickLoginButton() {
        clickElement(loginButton, loginButtonCss);
        LoggerUtil.info("Login button clicked");
    }

    /**
     * Perform login
     */
    public HomePage login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickRememberMeCheckbox();
        clickLoginButton();
        waitForSeconds(2);
        return new HomePage(driver);
    }

    /**
     * Perform login without remember me
     */
    public HomePage loginWithoutRememberMe(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLoginButton();
        waitForSeconds(2);
        return new HomePage(driver);
    }

    /**
     * Get error message
     */
    public String getErrorMessage() {
        String message = getText(errorMessage);
        LoggerUtil.info("Error message: " + message);
        return message;
    }

    /**
     * Check if error message is displayed
     */
    public boolean isErrorMessageDisplayed() {
        return isElementDisplayed(errorMessage);
    }

    /**
     * Logout
     */
    public HomePage logout() {
        if (!isAnyElementDisplayed(logoutLink, By.cssSelector("a[href='/logout']"))) {
            LoggerUtil.info("Logout link is not visible; user appears to already be logged out");
            return new HomePage(driver);
        }
        clickElement(logoutLink, By.cssSelector("a[href='/logout']"));
        waitForSeconds(2);
        return new HomePage(driver);
    }
}
