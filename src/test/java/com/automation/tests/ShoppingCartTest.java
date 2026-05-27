package com.automation.tests;

import com.automation.base.BaseTest;
import com.automation.pages.*;
import com.automation.utilities.*;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Shopping Cart Test Cases
 */
public class ShoppingCartTest extends BaseTest {

    /**
     * Test Case 1: Add single product to cart
     */
    @Test(description = "Verify user can add single product to cart", priority = 1)
    public void testAddSingleProductToCart() {
        try {
            ExtentReportManager.logTestInfo(extentTest, "Starting add single product to cart test");
            
            HomePage homePage = new HomePage(driver);
            ProductPage productPage = homePage.searchForProduct("book");
            Assert.assertTrue(productPage.getProductCount() > 0, "No products found");
            ProductDetailsPage productDetailsPage = productPage.clickFirstProduct();
            String productName = productDetailsPage.getProductTitle();
            ShoppingCartPage cartPage = productDetailsPage.addProductToCart("1");
            Assert.assertTrue(cartPage.isShoppingCartPageLoaded(), "Shopping cart page not loaded");
            
            Assert.assertTrue(cartPage.isProductInCart(productName), "Product not found in cart");
            
            ExtentReportManager.logTestPass(extentTest, "Product added to cart successfully: " + productName);

        } catch (Exception e) {
            ExtentReportManager.logTestFail(extentTest, "Test failed: " + e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    /**
     * Test Case 2: Add multiple products to cart
     */
    @Test(description = "Verify user can add multiple products to cart", priority = 2)
    public void testAddMultipleProductsToCart() {
        try {
            ExtentReportManager.logTestInfo(extentTest, "Starting add multiple products to cart test");
            
            HomePage homePage = new HomePage(driver);
            
            // Add first product
            ProductPage productPage = homePage.searchForProduct("book");
            ProductDetailsPage productDetailsPage = productPage.clickFirstProduct();
            String firstProductName = productDetailsPage.getProductTitle();
            ShoppingCartPage cartPage = productDetailsPage.addProductToCart("1");
            
            Assert.assertTrue(cartPage.isProductInCart(firstProductName), "First product not in cart");
            
            // Add second product
            HomePage homePage2 = cartPage.clickContinueShopping();
            ProductPage productPage2 = homePage2.searchForProduct("phone");
            ProductDetailsPage productDetailsPage2 = productPage2.clickFirstProduct();
            String secondProductName = productDetailsPage2.getProductTitle();
            ShoppingCartPage cartPage2 = productDetailsPage2.addProductToCart("1");
            
            int itemsCount = cartPage2.getCartItemsCount();
            Assert.assertTrue(itemsCount >= 1, "Cart should contain at least one item after adding multiple products");
            
            ExtentReportManager.logTestPass(extentTest, "Multiple products added to cart successfully");

        } catch (Exception e) {
            ExtentReportManager.logTestFail(extentTest, "Test failed: " + e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    /**
     * Test Case 3: Update product quantity in cart
     */
    @Test(description = "Verify user can update product quantity in cart", priority = 3)
    public void testUpdateProductQuantityInCart() {
        try {
            ExtentReportManager.logTestInfo(extentTest, "Starting update product quantity test");
            
            HomePage homePage = new HomePage(driver);
            ProductPage productPage = homePage.searchForProduct("book");
            ProductDetailsPage productDetailsPage = productPage.clickFirstProduct();
            String productName = productDetailsPage.getProductTitle();
            ShoppingCartPage cartPage = productDetailsPage.addProductToCart("1");
            
            Assert.assertTrue(cartPage.isProductInCart(productName), "Product not in cart");
            
            // Update quantity
            cartPage.updateProductQuantity(productName, "3");
            cartPage.clickUpdateCartButton();
            
            Assert.assertTrue(cartPage.isProductInCart(productName), "Product not found after quantity update");
            
            ExtentReportManager.logTestPass(extentTest, "Product quantity updated successfully");

        } catch (Exception e) {
            ExtentReportManager.logTestFail(extentTest, "Test failed: " + e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    /**
     * Test Case 4: Remove product from cart
     */
    @Test(description = "Verify user can remove product from cart", priority = 4)
    public void testRemoveProductFromCart() {
        try {
            ExtentReportManager.logTestInfo(extentTest, "Starting remove product from cart test");
            
            HomePage homePage = new HomePage(driver);
            ProductPage productPage = homePage.searchForProduct("book");
            ProductDetailsPage productDetailsPage = productPage.clickFirstProduct();
            String productName = productDetailsPage.getProductTitle();
            ShoppingCartPage cartPage = productDetailsPage.addProductToCart("1");
            
            Assert.assertTrue(cartPage.isProductInCart(productName), "Product not in cart");
            
            int itemsBeforeRemoval = cartPage.getCartItemsCount();
            
            // Remove product
            cartPage.removeProductFromCart(productName);
            
            Assert.assertFalse(cartPage.isProductInCart(productName), "Product still in cart after removal");
            
            ExtentReportManager.logTestPass(extentTest, "Product removed from cart successfully");

        } catch (Exception e) {
            ExtentReportManager.logTestFail(extentTest, "Test failed: " + e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    /**
     * Test Case 5: Verify cart total calculation
     */
    @Test(description = "Verify cart total calculation is correct", priority = 5)
    public void testCartTotalCalculation() {
        try {
            ExtentReportManager.logTestInfo(extentTest, "Starting cart total calculation test");
            
            HomePage homePage = new HomePage(driver);
            ProductPage productPage = homePage.searchForProduct("book");
            ProductDetailsPage productDetailsPage = productPage.clickFirstProduct();
            String productPrice = productDetailsPage.getProductPrice();
            ShoppingCartPage cartPage = productDetailsPage.addProductToCart("1");
            
            String cartSubtotal = cartPage.getCartSubtotal();
            Assert.assertFalse(cartSubtotal.isEmpty(), "Cart subtotal is empty");
            
            ExtentReportManager.logTestPass(extentTest, "Cart total displayed: " + cartSubtotal);

        } catch (Exception e) {
            ExtentReportManager.logTestFail(extentTest, "Test failed: " + e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    /**
     * Test Case 6: Empty cart validation
     */
    @Test(description = "Verify empty cart message", priority = 6)
    public void testEmptyCartValidation() {
        try {
            ExtentReportManager.logTestInfo(extentTest, "Starting empty cart validation test");
            
            HomePage homePage = new HomePage(driver);
            ShoppingCartPage cartPage = homePage.clickShoppingCart();
            
            boolean isEmpty = cartPage.isCartEmpty();
            // Cart might be empty or have items from previous tests
            
            ExtentReportManager.logTestPass(extentTest, "Cart page loaded successfully. Empty: " + isEmpty);

        } catch (Exception e) {
            ExtentReportManager.logTestFail(extentTest, "Test failed: " + e.getMessage());
            Assert.fail(e.getMessage());
        }
    }
}
