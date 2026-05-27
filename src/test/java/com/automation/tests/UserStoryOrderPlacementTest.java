package com.automation.tests;

import com.automation.base.BaseTest;
import com.automation.pages.*;
import com.automation.utilities.DataProviderUtil;
import com.automation.utilities.ExtentReportManager;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

/**
 * Risk-based tests for the customer order placement user story.
 */
public class UserStoryOrderPlacementTest extends BaseTest {

    @Test(description = "P1 critical functional flow: login, view products, add to cart, checkout, and place order",
            dataProvider = "criticalCheckoutData",
            dataProviderClass = DataProviderUtil.class,
            groups = {"critical", "functional", "ui"},
            priority = 1)
    public void testCriticalCustomerCanPlaceOrder(Map<String, String> testData) {
        ExtentReportManager.logTestInfo(extentTest, "Business risk: revenue is blocked if this flow fails");

        HomePage homePage = new HomePage(driver);
        String email = ensureCustomerCanLogin(homePage, testData);
        String password = valueOrDefault(testData, "Password", "Password@123");

        LoginPage loginPage = homePage.clickLoginLink();
        Assert.assertTrue(loginPage.isLoginPageLoaded(), "Login page did not load");
        Assert.assertTrue(loginPage.isLoginFormVisible(), "Login form UI elements are missing");
        homePage = loginPage.login(email, password);
        Assert.assertTrue(homePage.isHomePageLoaded(), "Home page did not load after login");
        Assert.assertTrue(homePage.isUserLoggedIn(), "Logout link was not visible after login");
        ExtentReportManager.logTestPass(extentTest, "Functional and UI validation passed for login");

        String productName = valueOrDefault(testData, "ProductName", "book");
        String quantity = valueOrDefault(testData, "Quantity", "1");
        ProductPage productPage = homePage.searchForProduct(productName);
        Assert.assertTrue(productPage.isProductPageLoaded(), "Product page did not load");
        Assert.assertTrue(productPage.hasSearchResults(), "No products were returned for: " + productName);
        Assert.assertTrue(productPage.areProductCardsValid(), "Product list UI is missing names or prices");
        ExtentReportManager.logTestPass(extentTest, "Functional and UI validation passed for product results");

        ProductDetailsPage detailsPage = productPage.clickFirstProduct();
        Assert.assertTrue(detailsPage.isProductDetailsPageLoaded(), "Product details page did not load");
        Assert.assertTrue(detailsPage.isProductDetailSectionDisplayed(), "Product details UI elements are missing");
        String selectedProductName = detailsPage.getProductTitle();
        ShoppingCartPage cartPage = detailsPage.addProductToCart(quantity);
        Assert.assertTrue(cartPage.isShoppingCartPageLoaded(), "Shopping cart page did not load");
        Assert.assertTrue(cartPage.isProductInCart(selectedProductName), "Selected product is not in cart");
        Assert.assertTrue(cartPage.isCartSummaryVisible(), "Cart summary UI elements are missing");
        ExtentReportManager.logTestPass(extentTest, "Functional and UI validation passed for cart");

        CheckoutPage checkoutPage = cartPage.acceptTermsAndCheckout();
        Assert.assertTrue(checkoutPage.isCheckoutPageLoaded(), "Checkout page did not load");
        Assert.assertTrue(checkoutPage.isBillingFormVisible(), "Billing section is missing");
        ExtentReportManager.logTestPass(extentTest, "Checkout entry UI validated");

        OrderConfirmationPage confirmationPage = checkoutPage.completeCheckout(
                valueOrDefault(testData, "FirstName", "Auto"),
                valueOrDefault(testData, "LastName", "Customer"),
                email,
                valueOrDefault(testData, "Company", "Automation Inc"),
                valueOrDefault(testData, "Country", "1"),
                valueOrDefault(testData, "State", "1"),
                valueOrDefault(testData, "City", "New York"),
                valueOrDefault(testData, "Address", "123 Automation St"),
                valueOrDefault(testData, "ZipCode", "10001"),
                valueOrDefault(testData, "Phone", "5551234567"),
                valueOrDefault(testData, "ShippingMethod", "Ground"),
                valueOrDefault(testData, "PaymentMethod", "Payments.CashOnDelivery")
        );

        Assert.assertTrue(confirmationPage.isOrderConfirmationPageLoaded(), "Order confirmation page did not load");
        Assert.assertTrue(confirmationPage.isOrderDetailsDisplayed(), "Order details are not visible");
        confirmationPage.clickContinue();
        ExtentReportManager.logTestPass(extentTest, "Order was placed successfully");
    }

    @Test(description = "P2 UI validation: key forms, buttons, links, inputs, and labels are present along the story",
            dataProvider = "criticalCheckoutData",
            dataProviderClass = DataProviderUtil.class,
            groups = {"functional", "ui"},
            priority = 2)
    public void testUserStoryKeyUiElementsArePresent(Map<String, String> testData) {
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isHomePageLoaded(), "Home page key UI is missing");

        LoginPage loginPage = homePage.clickLoginLink();
        Assert.assertTrue(loginPage.isLoginFormVisible(), "Login form controls are missing");

        homePage = new HomePage(driver);
        ProductPage productPage = homePage.searchForProduct(valueOrDefault(testData, "ProductName", "book"));
        Assert.assertTrue(productPage.areProductCardsValid(), "Product names/prices are missing");

        ProductDetailsPage detailsPage = productPage.clickFirstProduct();
        Assert.assertTrue(detailsPage.isProductDetailSectionDisplayed(), "Product detail controls are missing");
        ExtentReportManager.logTestPass(extentTest, "Key UI elements were visible for forms, buttons, links, inputs, and labels");
    }

    @Test(description = "P2 edge flow: checkout blocks missing required billing details with validation",
            dataProvider = "edgeCheckoutData",
            dataProviderClass = DataProviderUtil.class,
            groups = {"edge", "functional", "ui"},
            priority = 3)
    public void testCheckoutRequiresBillingDetails(Map<String, String> testData) {
        HomePage homePage = new HomePage(driver);
        String email = ensureCustomerCanLogin(homePage, testData);
        homePage = homePage.clickLoginLink().login(email, valueOrDefault(testData, "Password", "Password@123"));

        ProductPage productPage = homePage.searchForProduct(valueOrDefault(testData, "ProductName", "book"));
        ProductDetailsPage detailsPage = productPage.clickFirstProduct();
        ShoppingCartPage cartPage = detailsPage.addProductToCart(valueOrDefault(testData, "Quantity", "1"));
        CheckoutPage checkoutPage = cartPage.acceptTermsAndCheckout();

        checkoutPage.enterBillingAddressDetails("", "", email, "", "1", "1", "", "", "", "");
        checkoutPage.clickBillingNext();
        Assert.assertTrue(checkoutPage.isValidationErrorDisplayed(), "Checkout did not show validation errors for missing billing details");
        ExtentReportManager.logTestPass(extentTest, "Edge validation passed for missing billing details");
    }

    @Test(description = "P3 non-functional check: product discovery page responds within threshold",
            dataProvider = "criticalCheckoutData",
            dataProviderClass = DataProviderUtil.class,
            groups = {"nonFunctional", "performance"},
            priority = 4)
    public void testProductDiscoveryResponseTime(Map<String, String> testData) {
        HomePage homePage = new HomePage(driver);
        long start = System.currentTimeMillis();
        ProductPage productPage = homePage.searchForProduct(valueOrDefault(testData, "ProductName", "book"));
        long durationMs = System.currentTimeMillis() - start;

        Assert.assertTrue(productPage.isProductPageLoaded(), "Product search page did not load");
        Assert.assertTrue(durationMs < 7000, "Product search exceeded response threshold. Actual ms: " + durationMs);
        ExtentReportManager.logTestPass(extentTest, "Product discovery response time was " + durationMs + " ms");
    }

    private String ensureCustomerCanLogin(HomePage homePage, Map<String, String> testData) {
        String email = valueOrDefault(testData, "Email", "auto.customer@example.com");
        if (Boolean.parseBoolean(valueOrDefault(testData, "GenerateUniqueUser", "false"))) {
            email = "auto.customer." + System.currentTimeMillis() + "@example.com";
        }

        RegisterPage registerPage = homePage.clickRegisterLink();
        Assert.assertTrue(registerPage.isRegisterPageLoaded(), "Register page did not load");
        registerPage.register(
                "Male",
                valueOrDefault(testData, "SetupFirstName", "Auto"),
                valueOrDefault(testData, "SetupLastName", "Customer"),
                email,
                valueOrDefault(testData, "Password", "Password@123"),
                valueOrDefault(testData, "Password", "Password@123"),
                "15",
                "5",
                "1990"
        );
        Assert.assertTrue(registerPage.isSuccessMessageDisplayed(), "Customer registration failed");
        HomePage registeredHomePage = registerPage.clickContinueAfterRegistration();
        if (registeredHomePage.isUserLoggedIn()) {
            registeredHomePage.clickLogout();
        }
        return email;
    }

    private String valueOrDefault(Map<String, String> data, String key, String defaultValue) {
        String value = data.get(key);
        return value == null || value.trim().isEmpty() ? defaultValue : value;
    }
}
