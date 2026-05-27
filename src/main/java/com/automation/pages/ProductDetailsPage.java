package com.automation.pages;

import com.automation.utilities.LoggerUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Product Details Page Object Class
 */
public class ProductDetailsPage extends BasePage {
    // Locators
    private By productTitle = By.xpath("//h1");
    private By productPrice = By.xpath("//span[@class='price actual-price']");
    private By productDescription = By.xpath("//div[@class='product-description']");
    private By quantityField = By.id("product_enteredQuantity");
    private By addToCartButtonDynamic = By.cssSelector("input[id^='add-to-cart-button'], input[value='Add to cart'], button[value='Add to cart']");
    private By addToWishlistButton = By.xpath("//input[@value='Add to wishlist']");
    private By addToCompareButton = By.xpath("//input[@value='Add to compare list']");
    private By notifyMeButton = By.xpath("//input[@value='Notify me when in stock']");
    private By successMessage = By.xpath("//div[@class='bar-notification success']");
    private By notificationCartLink = By.cssSelector(".bar-notification.success a[href='/cart']");
    private By topCartLink = By.cssSelector("a[href='/cart']");

    /**
     * Constructor
     */
    public ProductDetailsPage(WebDriver driver) {
        super(driver);
        LoggerUtil.info("Initializing ProductDetailsPage");
    }

    /**
     * Check if product details page is loaded
     */
    public boolean isProductDetailsPageLoaded() {
        return isElementDisplayed(productTitle);
    }

    /**
     * Check if core product detail UI elements are visible
     */
    public boolean isProductDetailSectionDisplayed() {
        return isElementDisplayed(productTitle)
                && isAnyElementDisplayed(productPrice, productDescription)
                && isElementDisplayed(addToCartButtonDynamic);
    }

    /**
     * Get product title
     */
    public String getProductTitle() {
        String title = getText(productTitle);
        LoggerUtil.info("Product title: " + title);
        return title;
    }

    /**
     * Get product price
     */
    public String getProductPrice() {
        String price = getText(productPrice);
        LoggerUtil.info("Product price: " + price);
        return price;
    }

    /**
     * Get product description
     */
    public String getProductDescription() {
        String description = getText(productDescription);
        LoggerUtil.info("Product description retrieved");
        return description;
    }

    /**
     * Enter quantity
     */
    public void enterQuantity(String quantity) {
        if (isAnyElementDisplayed(quantityField, By.cssSelector("input.qty-input, input[id*='Quantity']"))) {
            typeText(quantityField, quantity, By.cssSelector("input.qty-input, input[id*='Quantity']"));
            LoggerUtil.info("Quantity entered: " + quantity);
        } else {
            LoggerUtil.info("Quantity input is not displayed for this product; continuing with default quantity");
        }
    }

    /**
     * Click add to cart button
     */
    public void clickAddToCart() {
        clickElement(addToCartButtonDynamic, By.xpath("//input[contains(@value,'Add to cart') or contains(@class,'add-to-cart')]"));
        LoggerUtil.info("Add to cart button clicked");
    }

    /**
     * Add product to cart
     */
    public ShoppingCartPage addProductToCart(String quantity) {
        enterQuantity(quantity);
        clickAddToCart();
        if (isAnyElementDisplayed(notificationCartLink, successMessage)) {
            clickElement(notificationCartLink, topCartLink);
        } else {
            clickElement(topCartLink);
        }
        return new ShoppingCartPage(driver);
    }

    /**
     * Add product to cart with default quantity
     */
    public ShoppingCartPage addProductToCart() {
        enterQuantity("1");
        clickAddToCart();
        if (isAnyElementDisplayed(notificationCartLink, successMessage)) {
            clickElement(notificationCartLink, topCartLink);
        } else {
            clickElement(topCartLink);
        }
        return new ShoppingCartPage(driver);
    }

    /**
     * Click add to wishlist
     */
    public void clickAddToWishlist() {
        clickElement(addToWishlistButton);
        LoggerUtil.info("Add to wishlist button clicked");
    }

    /**
     * Click add to compare
     */
    public void clickAddToCompare() {
        clickElement(addToCompareButton);
        LoggerUtil.info("Add to compare button clicked");
    }

    /**
     * Check if success message is displayed
     */
    public boolean isSuccessMessageDisplayed() {
        return isElementDisplayed(successMessage);
    }

    /**
     * Get success message
     */
    public String getSuccessMessage() {
        String message = getText(successMessage);
        LoggerUtil.info("Success message: " + message);
        return message;
    }
}
