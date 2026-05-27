package com.automation.pages;

import com.automation.utilities.LoggerUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

/**
 * Shopping Cart Page Object Class
 */
public class ShoppingCartPage extends BasePage {
    // Locators
    private By pageTitle = By.xpath("//h1[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'shopping cart')]");
    private By cartItems = By.cssSelector("tr.cart-item-row");
    private By productNameInCart = By.cssSelector("a.product-name");
    private By productPriceInCart = By.cssSelector(".product-price");
    private By quantityInput = By.cssSelector("input.qty-input");
    private By removeButton = By.xpath("//input[@value='Remove']");
    private By updateCartButton = By.name("updatecart");
    private By continueShoppingButton = By.xpath("//*[self::input or self::button or self::a][contains(translate(@value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'continue shopping') or contains(translate(normalize-space(.), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'continue shopping')]");
    private By homeLogoLink = By.cssSelector("a[href='/'], .header-logo a");
    private By checkoutButton = By.xpath("//*[self::input or self::button or self::a][contains(translate(@value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'checkout') or contains(translate(normalize-space(.), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'checkout')]");
    private By checkoutButtonCss = By.cssSelector("#checkout, input.checkout-button, button.checkout-button, input[name='checkout'], button[name='checkout'], .checkout-buttons input, .checkout-buttons button");
    private By cartSubtotal = By.xpath("//span[@class='product-price']");
    private By termsCheckbox = By.id("termsofservice");
    private By emptyCartMessage = By.xpath("//div[@class='no-data']");

    /**
     * Constructor
     */
    public ShoppingCartPage(WebDriver driver) {
        super(driver);
        LoggerUtil.info("Initializing ShoppingCartPage");
    }

    /**
     * Check if shopping cart page is loaded
     */
    public boolean isShoppingCartPageLoaded() {
        return isElementDisplayed(pageTitle);
    }

    /**
     * Verify key shopping cart UI elements are visible
     */
    public boolean isCartSummaryVisible() {
        return isElementDisplayed(cartItems)
                && isElementDisplayed(quantityInput)
                && isAnyElementDisplayed(termsCheckbox, checkoutButton, checkoutButtonCss);
    }

    /**
     * Get cart items count
     */
    public int getCartItemsCount() {
        List<WebElement> items = driver.findElements(cartItems);
        int count = items.size();
        LoggerUtil.info("Cart items count: " + count);
        return count;
    }

    /**
     * Get all product names in cart
     */
    public List<WebElement> getAllProductNamesInCart() {
        List<WebElement> products = driver.findElements(productNameInCart);
        LoggerUtil.info("Retrieved all product names from cart");
        return products;
    }

    /**
     * Check if product is in cart by name
     */
    public boolean isProductInCart(String productName) {
        boolean isPresent = getAllProductNamesInCart().stream()
                .anyMatch(product -> product.getText().trim().equalsIgnoreCase(productName.trim()));
        LoggerUtil.info("Product '" + productName + "' in cart: " + isPresent);
        return isPresent;
    }

    /**
     * Get product price in cart
     */
    public String getProductPriceInCart(String productName) {
        By priceLocator = By.xpath("//a[@class='product-name'][text()='" + productName + 
                                   "']/../../following-sibling::td//span[@class='product-price']");
        String price = getText(priceLocator);
        LoggerUtil.info("Product price in cart: " + price);
        return price;
    }

    /**
     * Update product quantity
     */
    public void updateProductQuantity(String productName, String newQuantity) {
        By quantityLocator = By.xpath("//a[@class='product-name'][text()='" + productName + 
                                      "']/../../following-sibling::td//input[@class='qty-input']");
        typeText(quantityLocator, newQuantity, quantityInput);
        LoggerUtil.info("Product quantity updated to: " + newQuantity);
    }

    /**
     * Click update cart button
     */
    public void clickUpdateCartButton() {
        clickElement(updateCartButton);
        waitForSeconds(2);
        LoggerUtil.info("Update cart button clicked");
    }

    /**
     * Remove product from cart
     */
    public void removeProductFromCart(String productName) {
        By removeButtonLocator = By.xpath("//a[@class='product-name'][text()='" + productName + 
                                          "']/../../following-sibling::td//input[@value='Remove']");
        clickElement(removeButtonLocator, By.cssSelector("input[name='removefromcart'], input[value='Remove']"));
        clickUpdateCartButton();
        waitForSeconds(2);
        LoggerUtil.info("Product removed from cart: " + productName);
    }

    /**
     * Click continue shopping
     */
    public HomePage clickContinueShopping() {
        if (isAnyElementDisplayed(continueShoppingButton)) {
            clickElement(continueShoppingButton);
        } else {
            clickElement(homeLogoLink);
        }
        return new HomePage(driver);
    }

    /**
     * Accept terms and checkout
     */
    public CheckoutPage acceptTermsAndCheckout() {
        if (isElementDisplayed(termsCheckbox)) {
            clickElement(termsCheckbox);
            LoggerUtil.info("Terms and conditions checkbox clicked");
        }
        clickElement(checkoutButton, checkoutButtonCss);
        waitForSeconds(2);
        return new CheckoutPage(driver);
    }

    /**
     * Click checkout button
     */
    public CheckoutPage clickCheckout() {
        if (isElementDisplayed(termsCheckbox) && !driver.findElement(termsCheckbox).isSelected()) {
            clickElement(termsCheckbox);
        }
        clickElement(checkoutButton, checkoutButtonCss);
        waitForSeconds(2);
        return new CheckoutPage(driver);
    }

    /**
     * Check if cart is empty
     */
    public boolean isCartEmpty() {
        boolean isEmpty = isElementDisplayed(emptyCartMessage);
        LoggerUtil.info("Cart is empty: " + isEmpty);
        return isEmpty;
    }

    /**
     * Get cart subtotal
     */
    public String getCartSubtotal() {
        String subtotal = getText(cartSubtotal);
        LoggerUtil.info("Cart subtotal: " + subtotal);
        return subtotal;
    }
}
