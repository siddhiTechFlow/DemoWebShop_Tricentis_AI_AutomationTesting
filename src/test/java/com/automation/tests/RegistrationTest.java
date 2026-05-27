package com.automation.tests;

import com.automation.base.BaseTest;
import com.automation.pages.HomePage;
import com.automation.pages.LoginPage;
import com.automation.pages.RegisterPage;
import com.automation.utilities.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Map;

/**
 * User Registration Test Cases
 */
public class RegistrationTest extends BaseTest {

    /**
     * Test Case 1: Valid user registration
     */
    @Test(description = "Verify user can register with valid data", priority = 1)
    public void testValidUserRegistration() {
        try {
            ExtentReportManager.logTestInfo(extentTest, "Starting valid user registration test");
            
            // Navigate to home page
            HomePage homePage = new HomePage(driver);
            Assert.assertTrue(homePage.isHomePageLoaded(), "Home page not loaded");
            ExtentReportManager.logTestPass(extentTest, "Home page loaded successfully");

            // Click on register link
            RegisterPage registerPage = homePage.clickRegisterLink();
            Assert.assertTrue(registerPage.isRegisterPageLoaded(), "Register page not loaded");
            ExtentReportManager.logTestPass(extentTest, "Register page loaded successfully");

            // Register new user
            String gender = "Male";
            String firstName = "John";
            String lastName = "Doe";
            String email = "john.doe" + System.currentTimeMillis() + "@test.com";
            String password = "Password@123";
            String confirmPassword = "Password@123";
            String day = "15";
            String month = "5";
            String year = "1990";

            HomePage homePageAfterRegistration = registerPage.register(gender, firstName, lastName,
                    email, password, confirmPassword, day, month, year);

            Assert.assertTrue(registerPage.isSuccessMessageDisplayed(), "Success message not displayed");
            String successMessage = registerPage.getSuccessMessage();
            Assert.assertTrue(successMessage.contains("Your registration completed"), 
                    "Expected success message not displayed");
            ExtentReportManager.logTestPass(extentTest, "User registered successfully: " + email);

        } catch (Exception e) {
            ExtentReportManager.logTestFail(extentTest, "Test failed: " + e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    /**
     * Test Case 2: Registration with existing email
     */
    @Test(description = "Verify registration fails with existing email", priority = 2)
    public void testRegistrationWithExistingEmail() {
        try {
            ExtentReportManager.logTestInfo(extentTest, "Starting registration with existing email test");
            
            String email = "existing.user" + System.currentTimeMillis() + "@test.com";
            TestFlowUtil.registerAndLogout(driver, email, "Password@123");

            HomePage homePage = new HomePage(driver);
            RegisterPage registerPage = homePage.clickRegisterLink();

            String gender = "Female";
            String firstName = "Jane";
            String lastName = "Smith";
            String password = "Password@123";
            String confirmPassword = "Password@123";
            String day = "20";
            String month = "3";
            String year = "1995";

            registerPage.register(gender, firstName, lastName, email, password, 
                    confirmPassword, day, month, year);

            Assert.assertTrue(registerPage.isErrorMessageDisplayed(), "Error message not displayed");
            ExtentReportManager.logTestPass(extentTest, "Error message displayed for existing email");

        } catch (Exception e) {
            ExtentReportManager.logTestFail(extentTest, "Test failed: " + e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    /**
     * Test Case 3: Registration with mismatched passwords
     */
    @Test(description = "Verify registration fails with mismatched passwords", priority = 3)
    public void testRegistrationWithMismatchedPasswords() {
        try {
            ExtentReportManager.logTestInfo(extentTest, "Starting registration with mismatched passwords test");
            
            HomePage homePage = new HomePage(driver);
            RegisterPage registerPage = homePage.clickRegisterLink();

            String gender = "Male";
            String firstName = "Bob";
            String lastName = "Johnson";
            String email = "bob.johnson" + System.currentTimeMillis() + "@test.com";
            String password = "Password@123";
            String confirmPassword = "Password@456"; // Mismatched password

            registerPage.selectGender(gender);
            registerPage.enterFirstName(firstName);
            registerPage.enterLastName(lastName);
            registerPage.enterEmail(email);
            registerPage.enterPassword(password);
            registerPage.confirmPassword(confirmPassword);
            registerPage.selectDateOfBirth("10", "6", "1988");
            registerPage.clickRegisterButton();
            waitForSeconds(2);

            Assert.assertTrue(registerPage.isErrorMessageDisplayed(), "Error message not displayed");
            ExtentReportManager.logTestPass(extentTest, "Error message displayed for mismatched passwords");

        } catch (Exception e) {
            ExtentReportManager.logTestFail(extentTest, "Test failed: " + e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    /**
     * Test Case 4: Registration page elements validation
     */
    @Test(description = "Verify all registration page elements are displayed", priority = 4)
    public void testRegistrationPageElementsValidation() {
        try {
            ExtentReportManager.logTestInfo(extentTest, "Starting registration page elements validation test");
            
            HomePage homePage = new HomePage(driver);
            RegisterPage registerPage = homePage.clickRegisterLink();
            
            Assert.assertTrue(registerPage.isRegisterPageLoaded(), "Register page not loaded");
            ExtentReportManager.logTestPass(extentTest, "All registration page elements are displayed");

        } catch (Exception e) {
            ExtentReportManager.logTestFail(extentTest, "Test failed: " + e.getMessage());
            Assert.fail(e.getMessage());
        }
    }
}
