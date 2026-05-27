package com.automation.tests;

import com.automation.base.BaseTest;
import com.automation.pages.HomePage;
import com.automation.pages.LoginPage;
import com.automation.utilities.*;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * User Login Test Cases
 */
public class LoginTest extends BaseTest {

    /**
     * Test Case 1: Valid user login
     */
    @Test(description = "Verify user can login with valid credentials", priority = 1)
    public void testValidUserLogin() {
        try {
            ExtentReportManager.logTestInfo(extentTest, "Starting valid user login test");
            
            HomePage homePage = new HomePage(driver);
            Assert.assertTrue(homePage.isHomePageLoaded(), "Home page not loaded");
            ExtentReportManager.logTestPass(extentTest, "Home page loaded successfully");

            LoginPage loginPage = homePage.clickLoginLink();
            Assert.assertTrue(loginPage.isLoginPageLoaded(), "Login page not loaded");
            ExtentReportManager.logTestPass(extentTest, "Login page loaded successfully");

            String password = "Password@123";
            String email = TestFlowUtil.uniqueEmail("valid.login");
            homePage = TestFlowUtil.registerAndLogout(driver, email, password);
            loginPage = homePage.clickLoginLink();
            
            HomePage homePageAfterLogin = loginPage.login(email, password);
            Assert.assertTrue(homePageAfterLogin.isHomePageLoaded(), "Home page not loaded after login");
            ExtentReportManager.logTestPass(extentTest, "User logged in successfully with email: " + email);

        } catch (Exception e) {
            ExtentReportManager.logTestFail(extentTest, "Test failed: " + e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    /**
     * Test Case 2: Login with invalid email
     */
    @Test(description = "Verify login fails with invalid email", priority = 2)
    public void testLoginWithInvalidEmail() {
        try {
            ExtentReportManager.logTestInfo(extentTest, "Starting login with invalid email test");
            
            HomePage homePage = new HomePage(driver);
            LoginPage loginPage = homePage.clickLoginLink();

            String invalidEmail = TestFlowUtil.uniqueEmail("invalid.login");
            String password = "Password@123";
            
            loginPage.enterEmail(invalidEmail);
            loginPage.enterPassword(password);
            loginPage.clickLoginButton();
            waitForSeconds(2);

            Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message not displayed");
            ExtentReportManager.logTestPass(extentTest, "Error message displayed for invalid email");

        } catch (Exception e) {
            ExtentReportManager.logTestFail(extentTest, "Test failed: " + e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    /**
     * Test Case 3: Login with invalid password
     */
    @Test(description = "Verify login fails with invalid password", priority = 3)
    public void testLoginWithInvalidPassword() {
        try {
            ExtentReportManager.logTestInfo(extentTest, "Starting login with invalid password test");
            
            HomePage homePage = new HomePage(driver);
            LoginPage loginPage = homePage.clickLoginLink();

            String email = TestFlowUtil.uniqueEmail("wrong.password");
            TestFlowUtil.registerAndLogout(driver, email, "Password@123");
            homePage = new HomePage(driver);
            loginPage = homePage.clickLoginLink();
            String invalidPassword = "wrongpassword";
            
            loginPage.enterEmail(email);
            loginPage.enterPassword(invalidPassword);
            loginPage.clickLoginButton();
            waitForSeconds(2);

            Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message not displayed");
            ExtentReportManager.logTestPass(extentTest, "Error message displayed for invalid password");

        } catch (Exception e) {
            ExtentReportManager.logTestFail(extentTest, "Test failed: " + e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    /**
     * Test Case 4: Login and logout
     */
    @Test(description = "Verify user can login and logout successfully", priority = 4)
    public void testLoginAndLogout() {
        try {
            ExtentReportManager.logTestInfo(extentTest, "Starting login and logout test");
            
            HomePage homePage = new HomePage(driver);
            LoginPage loginPage = homePage.clickLoginLink();

            String password = "Password@123";
            String email = TestFlowUtil.uniqueEmail("logout.login");
            homePage = TestFlowUtil.registerAndLogout(driver, email, password);
            loginPage = homePage.clickLoginLink();
            
            HomePage homePageAfterLogin = loginPage.login(email, password);
            Assert.assertTrue(homePageAfterLogin.isHomePageLoaded(), "Home page not loaded after login");
            ExtentReportManager.logTestPass(extentTest, "User logged in successfully");

            HomePage homePageAfterLogout = homePageAfterLogin.clickLogout();
            Assert.assertTrue(homePageAfterLogout.isHomePageLoaded(), "Home page not loaded after logout");
            ExtentReportManager.logTestPass(extentTest, "User logged out successfully");

        } catch (Exception e) {
            ExtentReportManager.logTestFail(extentTest, "Test failed: " + e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    /**
     * Test Case 5: Login with remember me
     */
    @Test(description = "Verify user can login with remember me option", priority = 5)
    public void testLoginWithRememberMe() {
        try {
            ExtentReportManager.logTestInfo(extentTest, "Starting login with remember me test");
            
            HomePage homePage = new HomePage(driver);
            LoginPage loginPage = homePage.clickLoginLink();

            String password = "Password@123";
            String email = TestFlowUtil.uniqueEmail("remember.login");
            homePage = TestFlowUtil.registerAndLogout(driver, email, password);
            loginPage = homePage.clickLoginLink();
            
            loginPage.clickRememberMeCheckbox();
            loginPage.enterEmail(email);
            loginPage.enterPassword(password);
            loginPage.clickLoginButton();
            waitForSeconds(2);

            HomePage homePageAfterLogin = new HomePage(driver);
            Assert.assertTrue(homePageAfterLogin.isHomePageLoaded(), "Home page not loaded after login");
            ExtentReportManager.logTestPass(extentTest, "User logged in successfully with remember me option");

        } catch (Exception e) {
            ExtentReportManager.logTestFail(extentTest, "Test failed: " + e.getMessage());
            Assert.fail(e.getMessage());
        }
    }
}
