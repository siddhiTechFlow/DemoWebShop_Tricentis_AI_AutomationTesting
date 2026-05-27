package com.automation.pages;

import com.automation.utilities.LoggerUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Register Page Object Class
 */
public class RegisterPage extends BasePage {
    // Locators
    private By pageTitle = By.xpath("//h1[text()='Register']");
    private By genderMaleRadio = By.id("gender-male");
    private By genderFemaleRadio = By.id("gender-female");
    private By firstNameField = By.id("FirstName");
    private By lastNameField = By.id("LastName");
    private By emailField = By.id("Email");
    private By passwordField = By.id("Password");
    private By confirmPasswordField = By.id("ConfirmPassword");
    private By registerButton = By.id("register-button");
    private By successMessage = By.xpath("//div[@class='result']");
    private By errorMessage = By.cssSelector(".validation-summary-errors, .field-validation-error, span[id$='-error']");
    private By continueButton = By.cssSelector("input.register-continue-button, input[value='Continue']");
    private By dateOfBirthDay = By.name("DateOfBirthDay");
    private By dateOfBirthMonth = By.name("DateOfBirthMonth");
    private By dateOfBirthYear = By.name("DateOfBirthYear");

    /**
     * Constructor
     */
    public RegisterPage(WebDriver driver) {
        super(driver);
        LoggerUtil.info("Initializing RegisterPage");
    }

    /**
     * Check if register page is loaded
     */
    public boolean isRegisterPageLoaded() {
        return isElementDisplayed(pageTitle);
    }

    /**
     * Select gender
     */
    public void selectGender(String gender) {
        if (gender.equalsIgnoreCase("male")) {
            clickElement(genderMaleRadio);
        } else if (gender.equalsIgnoreCase("female")) {
            clickElement(genderFemaleRadio);
        }
        LoggerUtil.info("Gender selected: " + gender);
    }

    /**
     * Enter first name
     */
    public void enterFirstName(String firstName) {
        typeText(firstNameField, firstName);
        LoggerUtil.info("First name entered: " + firstName);
    }

    /**
     * Enter last name
     */
    public void enterLastName(String lastName) {
        typeText(lastNameField, lastName);
        LoggerUtil.info("Last name entered: " + lastName);
    }

    /**
     * Enter email
     */
    public void enterEmail(String email) {
        typeText(emailField, email);
        LoggerUtil.info("Email entered: " + email);
    }

    /**
     * Enter password
     */
    public void enterPassword(String password) {
        typeText(passwordField, password);
        LoggerUtil.info("Password entered");
    }

    /**
     * Confirm password
     */
    public void confirmPassword(String password) {
        typeText(confirmPasswordField, password);
        LoggerUtil.info("Password confirmed");
    }

    /**
     * Select date of birth
     */
    public void selectDateOfBirth(String day, String month, String year) {
        if (driver.findElements(dateOfBirthDay).isEmpty()) {
            LoggerUtil.info("Date of birth fields are not present; skipping optional DOB selection");
            return;
        }
        selectDropdownByValue(dateOfBirthDay, day);
        selectDropdownByValue(dateOfBirthMonth, month);
        selectDropdownByValue(dateOfBirthYear, year);
        LoggerUtil.info("Date of birth selected: " + day + "/" + month + "/" + year);
    }

    /**
     * Click register button
     */
    public void clickRegisterButton() {
        clickElement(registerButton);
        LoggerUtil.info("Register button clicked");
    }

    /**
     * Perform registration
     */
    public HomePage register(String gender, String firstName, String lastName,
                             String email, String password, String confirmPassword,
                             String day, String month, String year) {
        selectGender(gender);
        enterFirstName(firstName);
        enterLastName(lastName);
        enterEmail(email);
        enterPassword(password);
        confirmPassword(confirmPassword);
        selectDateOfBirth(day, month, year);
        clickRegisterButton();
        waitForSeconds(2);
        return new HomePage(driver);
    }

    /**
     * Get success message
     */
    public String getSuccessMessage() {
        String message = getText(successMessage);
        LoggerUtil.info("Success message: " + message);
        return message;
    }

    /**
     * Check if success message is displayed
     */
    public boolean isSuccessMessageDisplayed() {
        return isElementDisplayed(successMessage);
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
        return isAnyElementDisplayed(errorMessage);
    }

    public HomePage clickContinueAfterRegistration() {
        clickElement(continueButton);
        return new HomePage(driver);
    }
}
