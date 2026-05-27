package com.automation.tests;

import com.automation.base.BaseTest;
import com.automation.pages.*;
import com.automation.utilities.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.WebElement;
import java.util.List;

/**
 * Product Search and Browsing Test Cases
 */
public class ProductSearchTest extends BaseTest {

    /**
     * Test Case 1: Search for product by keyword
     */
    @Test(description = "Verify user can search for product by keyword", priority = 1)
    public void testSearchProductByKeyword() {
        try {
            ExtentReportManager.logTestInfo(extentTest, "Starting product search by keyword test");
            
            HomePage homePage = new HomePage(driver);
            Assert.assertTrue(homePage.isHomePageLoaded(), "Home page not loaded");

            String productName = "laptop";
            ProductPage productPage = homePage.searchForProduct(productName);
            
            Assert.assertTrue(productPage.isProductPageLoaded(), "Product page not loaded");
            int productCount = productPage.getProductCount();
            Assert.assertTrue(productCount > 0, "No products found");
            
            ExtentReportManager.logTestPass(extentTest, "Search completed successfully. Found " + productCount + " products");

        } catch (Exception e) {
            ExtentReportManager.logTestFail(extentTest, "Test failed: " + e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    /**
     * Test Case 2: Verify product list is displayed
     */
    @Test(description = "Verify product list is displayed after search", priority = 2)
    public void testProductListDisplay() {
        try {
            ExtentReportManager.logTestInfo(extentTest, "Starting product list display test");
            
            HomePage homePage = new HomePage(driver);
            ProductPage productPage = homePage.searchForProduct("phone");
            
            List<WebElement> productNames = productPage.getAllProductNames();
            Assert.assertTrue(productNames.size() > 0, "No product names found");
            
            List<WebElement> productPrices = productPage.getAllProductPrices();
            Assert.assertTrue(productPrices.size() > 0, "No product prices found");
            
            ExtentReportManager.logTestPass(extentTest, "Product list displayed with " + productNames.size() + " products");

        } catch (Exception e) {
            ExtentReportManager.logTestFail(extentTest, "Test failed: " + e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    /**
     * Test Case 3: Click on product and view details
     */
    @Test(description = "Verify user can click on product and view details", priority = 3)
    public void testViewProductDetails() {
        try {
            ExtentReportManager.logTestInfo(extentTest, "Starting product details view test");
            
            HomePage homePage = new HomePage(driver);
            ProductPage productPage = homePage.searchForProduct("computer");
            
            Assert.assertTrue(productPage.getProductCount() > 0, "No products found");
            
            ProductDetailsPage productDetailsPage = productPage.clickFirstProduct();
            Assert.assertTrue(productDetailsPage.isProductDetailsPageLoaded(), "Product details page not loaded");
            
            String productTitle = productDetailsPage.getProductTitle();
            Assert.assertFalse(productTitle.isEmpty(), "Product title is empty");
            
            String productPrice = productDetailsPage.getProductPrice();
            Assert.assertFalse(productPrice.isEmpty(), "Product price is empty");
            
            ExtentReportManager.logTestPass(extentTest, "Product details displayed successfully: " + productTitle);

        } catch (Exception e) {
            ExtentReportManager.logTestFail(extentTest, "Test failed: " + e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    /**
     * Test Case 4: Search with empty keyword
     */
    @Test(description = "Verify search with empty keyword", priority = 4)
    public void testSearchWithEmptyKeyword() {
        try {
            ExtentReportManager.logTestInfo(extentTest, "Starting search with empty keyword test");
            
            HomePage homePage = new HomePage(driver);
            homePage.searchProduct("");
            homePage.clickSearchButton();
            waitForSeconds(2);

            try {
                String alertText = driver.switchTo().alert().getText();
                driver.switchTo().alert().accept();
                Assert.assertTrue(alertText.toLowerCase().contains("search keyword"), "Unexpected empty-search alert");
            } catch (Exception noAlert) {
                Assert.assertTrue(driver.getCurrentUrl().contains("search"), "Search page not loaded");
            }
            
            ExtentReportManager.logTestPass(extentTest, "Search page loaded for empty keyword");

        } catch (Exception e) {
            ExtentReportManager.logTestFail(extentTest, "Test failed: " + e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    /**
     * Test Case 5: Search with special characters
     */
    @Test(description = "Verify search with special characters", priority = 5)
    public void testSearchWithSpecialCharacters() {
        try {
            ExtentReportManager.logTestInfo(extentTest, "Starting search with special characters test");
            
            HomePage homePage = new HomePage(driver);
            String specialCharKeyword = "$@#$%";
            homePage.searchProduct(specialCharKeyword);
            homePage.clickSearchButton();
            waitForSeconds(2);
            
            // Should handle special characters gracefully
            Assert.assertTrue(driver.getCurrentUrl().contains("search"), "Search page not loaded");
            
            ExtentReportManager.logTestPass(extentTest, "Search handled special characters gracefully");

        } catch (Exception e) {
            ExtentReportManager.logTestFail(extentTest, "Test failed: " + e.getMessage());
            Assert.fail(e.getMessage());
        }
    }
}
