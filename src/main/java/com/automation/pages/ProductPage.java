package com.automation.pages;

import com.automation.utilities.LoggerUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

/**
 * Product Page Object Class
 */
public class ProductPage extends BasePage {
    // Locators
    private By productList = By.cssSelector(".product-item");
    private By productName = By.cssSelector(".product-title");
    private By productPrice = By.cssSelector(".price.actual-price");
    private By firstProduct = By.cssSelector(".product-title a");
    private By addToCartButton = By.xpath("//input[@value='Add to cart']");
    private By addToCompareButton = By.xpath("//input[@value='Add to compare list']");
    private By pageTitle = By.xpath("//h1");
    private By noResultsMessage = By.cssSelector(".search-results .warning, .no-result, .result");

    /**
     * Constructor
     */
    public ProductPage(WebDriver driver) {
        super(driver);
        LoggerUtil.info("Initializing ProductPage");
    }

    /**
     * Check if product page is loaded
     */
    public boolean isProductPageLoaded() {
        return isAnyElementDisplayed(pageTitle, productList, noResultsMessage);
    }

    public boolean hasSearchResults() {
        return getProductCount() > 0;
    }

    public boolean isNoResultsMessageDisplayed() {
        return isAnyElementDisplayed(noResultsMessage);
    }

    public boolean areProductCardsValid() {
        return hasSearchResults()
                && !driver.findElements(productName).isEmpty()
                && !driver.findElements(productPrice).isEmpty();
    }

    /**
     * Get product count
     */
    public int getProductCount() {
        List<WebElement> products = driver.findElements(productList);
        LoggerUtil.info("Product count: " + products.size());
        return products.size();
    }

    /**
     * Get all product names
     */
    public List<WebElement> getAllProductNames() {
        List<WebElement> products = driver.findElements(productName);
        LoggerUtil.info("Retrieved all product names");
        return products;
    }

    /**
     * Get all product prices
     */
    public List<WebElement> getAllProductPrices() {
        List<WebElement> prices = driver.findElements(productPrice);
        LoggerUtil.info("Retrieved all product prices");
        return prices;
    }

    /**
     * Click on first product
     */
    public ProductDetailsPage clickFirstProduct() {
        clickElement(firstProduct, By.xpath("(//h2[contains(@class,'product-title')]/a)[1]"));
        return new ProductDetailsPage(driver);
    }

    /**
     * Click on product by name
     */
    public ProductDetailsPage clickProductByName(String productName) {
        By productByName = By.xpath("//h2[contains(@class,'product-title')]/a[normalize-space()='" + productName + "']");
        clickElement(productByName);
        return new ProductDetailsPage(driver);
    }

    /**
     * Get product price by name
     */
    public String getProductPriceByName(String productName) {
        By priceLocator = By.xpath("//h2[@class='product-title']/a[text()='" + productName + 
                                   "']/../../following-sibling::div//span[@class='price actual-price']");
        String price = getText(priceLocator);
        LoggerUtil.info("Product price for " + productName + ": " + price);
        return price;
    }

    /**
     * Get page title
     */
    public String getProductPageTitle() {
        String title = getText(pageTitle);
        LoggerUtil.info("Product page title: " + title);
        return title;
    }
}
