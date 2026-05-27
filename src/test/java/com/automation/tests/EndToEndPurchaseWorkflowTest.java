package com.automation.tests;

import com.automation.base.BaseTest;
import com.automation.pages.CheckoutPage;
import com.automation.pages.HomePage;
import com.automation.pages.OrderConfirmationPage;
import com.automation.pages.ProductDetailsPage;
import com.automation.pages.ProductPage;
import com.automation.pages.RegisterPage;
import com.automation.pages.ShoppingCartPage;
import com.automation.utilities.ExtentReportManager;
import com.automation.utilities.TestFlowUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * End-to-end purchase workflow tests using isolated customer data.
 */
public class EndToEndPurchaseWorkflowTest extends BaseTest {

    @Test(description = "Complete end-to-end purchase workflow for new user", priority = 1)
    public void testCompleteNewUserPurchaseWorkflow() {
        try {
            String password = "Password@123";
            String email = TestFlowUtil.uniqueEmail("e2e.new");

            HomePage homePage = new HomePage(driver);
            RegisterPage registerPage = homePage.clickRegisterLink();
            registerPage.register("Male", "TestUser", "EndToEnd", email, password, password, "15", "5", "1990");
            Assert.assertTrue(registerPage.isSuccessMessageDisplayed(), "Registration not successful");
            homePage = registerPage.clickContinueAfterRegistration();
            Assert.assertTrue(homePage.isUserLoggedIn(), "New user was not logged in after registration");

            ShoppingCartPage cartPage = TestFlowUtil.addProductToCart(homePage, "book");
            OrderConfirmationPage confirmationPage = completeOrder(cartPage, email);

            Assert.assertTrue(confirmationPage.isOrderConfirmationPageLoaded(), "Order confirmation page not loaded");
            confirmationPage.clickContinue();
            ExtentReportManager.logTestPass(extentTest, "Complete new user purchase workflow passed");
        } catch (Exception e) {
            ExtentReportManager.logTestFail(extentTest, "Test failed: " + e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    @Test(description = "Complete purchase workflow for existing user", priority = 2)
    public void testCompleteExistingUserPurchaseWorkflow() {
        try {
            HomePage homePage = TestFlowUtil.loginAsNewCustomer(driver, "Password@123");
            ShoppingCartPage cartPage = TestFlowUtil.addProductToCart(homePage, "book");
            OrderConfirmationPage confirmationPage = completeOrder(cartPage, TestFlowUtil.uniqueEmail("billing"));

            Assert.assertTrue(confirmationPage.isOrderConfirmationPageLoaded(), "Order confirmation page not loaded");
            ExtentReportManager.logTestPass(extentTest, "Existing user purchase workflow passed");
        } catch (Exception e) {
            ExtentReportManager.logTestFail(extentTest, "Test failed: " + e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    @Test(description = "Purchase multiple products in single order", priority = 3)
    public void testMultipleProductsPurchase() {
        try {
            HomePage homePage = TestFlowUtil.loginAsNewCustomer(driver, "Password@123");

            ProductPage firstSearch = homePage.searchForProduct("book");
            ProductDetailsPage firstProduct = firstSearch.clickFirstProduct();
            String firstProductName = firstProduct.getProductTitle();
            ShoppingCartPage cartPage = firstProduct.addProductToCart("1");

            HomePage homeAgain = cartPage.clickContinueShopping();
            ProductPage secondSearch = homeAgain.searchForProduct("phone");
            ProductDetailsPage secondProduct = secondSearch.clickFirstProduct();
            String secondProductName = secondProduct.getProductTitle();
            ShoppingCartPage finalCart = secondProduct.addProductToCart("1");

            Assert.assertTrue(finalCart.getCartItemsCount() >= 1, "Cart should contain products before checkout");

            OrderConfirmationPage confirmationPage = completeOrder(finalCart, TestFlowUtil.uniqueEmail("multi.billing"));
            Assert.assertTrue(confirmationPage.isOrderConfirmationPageLoaded(), "Order confirmation page not loaded");
            ExtentReportManager.logTestPass(extentTest, "Multiple products purchase workflow passed");
        } catch (Exception e) {
            ExtentReportManager.logTestFail(extentTest, "Test failed: " + e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    private OrderConfirmationPage completeOrder(ShoppingCartPage cartPage, String email) {
        CheckoutPage checkoutPage = cartPage.clickCheckout();
        Assert.assertTrue(checkoutPage.isCheckoutPageLoaded(), "Checkout page not loaded");
        return checkoutPage.completeCheckout(
                "John",
                "Doe",
                email,
                "Automation Company",
                "1",
                "1",
                "New York",
                "123 Main Street",
                "10001",
                "5551234567",
                "Ground",
                "Payments.CashOnDelivery"
        );
    }
}
