package com.automation.utilities;

import com.automation.pages.HomePage;
import com.automation.pages.LoginPage;
import com.automation.pages.ProductDetailsPage;
import com.automation.pages.ProductPage;
import com.automation.pages.RegisterPage;
import com.automation.pages.ShoppingCartPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

/**
 * Small reusable flows for legacy tests so each test owns its setup state.
 */
public class TestFlowUtil {

    private TestFlowUtil() {
    }

    public static String uniqueEmail(String prefix) {
        return prefix + "." + System.currentTimeMillis() + "@example.com";
    }

    public static HomePage registerUniqueCustomer(WebDriver driver, String email, String password) {
        HomePage homePage = new HomePage(driver);
        RegisterPage registerPage = homePage.clickRegisterLink();
        Assert.assertTrue(registerPage.isRegisterPageLoaded(), "Register page not loaded");
        registerPage.register("Male", "Auto", "Customer", email, password, password, "15", "5", "1990");
        Assert.assertTrue(registerPage.isSuccessMessageDisplayed(), "Registration did not complete");
        return registerPage.clickContinueAfterRegistration();
    }

    public static HomePage registerAndLogout(WebDriver driver, String email, String password) {
        HomePage homePage = registerUniqueCustomer(driver, email, password);
        if (homePage.isUserLoggedIn()) {
            homePage = homePage.clickLogout();
        }
        return homePage;
    }

    public static HomePage loginAsNewCustomer(WebDriver driver, String password) {
        String email = uniqueEmail("login.user");
        HomePage homePage = registerAndLogout(driver, email, password);
        LoginPage loginPage = homePage.clickLoginLink();
        homePage = loginPage.login(email, password);
        Assert.assertTrue(homePage.isUserLoggedIn(), "User was not logged in");
        return homePage;
    }

    public static ShoppingCartPage addProductToCart(HomePage homePage, String keyword) {
        ProductPage productPage = homePage.searchForProduct(keyword);
        Assert.assertTrue(productPage.hasSearchResults(), "No products found for: " + keyword);
        ProductDetailsPage detailsPage = productPage.clickFirstProduct();
        String productName = detailsPage.getProductTitle();
        ShoppingCartPage cartPage = detailsPage.addProductToCart("1");
        Assert.assertTrue(cartPage.isProductInCart(productName), "Product not in cart: " + productName);
        return cartPage;
    }
}
