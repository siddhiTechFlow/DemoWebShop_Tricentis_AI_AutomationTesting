package com.automation.pages;

import com.automation.utilities.LoggerUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Home Page Object Class
 */
public class HomePage extends BasePage {
    // Locators
    private By welcomeText = By.xpath("//h1[contains(text(), 'Welcome')]");
    private By loginLink = By.xpath("//a[text()='Log in']");
    private By loginLinkByHref = By.cssSelector("a[href='/login']");
    private By logoutLink = By.cssSelector("a[href='/logout']");
    private By registerLink = By.xpath("//a[text()='Register']");
    private By searchBox = By.id("small-searchterms");
    private By searchButton = By.xpath("//input[@value='Search']");
    private By searchButtonCss = By.cssSelector("input.button-1.search-box-button, button[type='submit']");
    private By cartLink = By.xpath("//a[text()='Shopping cart']");
    private By cartLinkByHref = By.cssSelector("a[href='/cart']");
    private By shoppingCartCounter = By.xpath("//span[@class='cart-qty']");

    /**
     * Constructor
     */
    public HomePage(WebDriver driver) {
        super(driver);
        LoggerUtil.info("Initializing HomePage");
    }

    /**
     * Check if home page is loaded
     */
    public boolean isHomePageLoaded() {
        return isAnyElementDisplayed(welcomeText, searchBox);
    }

    /**
     * Click on Login Link
     */
    public LoginPage clickLoginLink() {
        clickElement(loginLink, loginLinkByHref);
        return new LoginPage(driver);
    }

    public boolean isUserLoggedIn() {
        return isElementDisplayed(logoutLink);
    }

    public HomePage clickLogout() {
        clickElement(logoutLink, By.xpath("//a[text()='Log out']"));
        return new HomePage(driver);
    }

    /**
     * Click on Register Link
     */
    public RegisterPage clickRegisterLink() {
        clickElement(registerLink, By.cssSelector("a[href='/register']"));
        return new RegisterPage(driver);
    }

    /**
     * Search for product
     */
    public void searchProduct(String productName) {
        typeText(searchBox, productName, By.name("q"));
        LoggerUtil.info("Searched for product: " + productName);
    }

    /**
     * Click search button
     */
    public void clickSearchButton() {
        clickElement(searchButton, searchButtonCss);
        LoggerUtil.info("Search button clicked");
    }

    /**
     * Perform search
     */
    public ProductPage searchForProduct(String productName) {
        searchProduct(productName);
        clickSearchButton();
        return new ProductPage(driver);
    }

    /**
     * Click on Shopping Cart
     */
    public ShoppingCartPage clickShoppingCart() {
        clickElement(cartLink, cartLinkByHref);
        return new ShoppingCartPage(driver);
    }

    /**
     * Get cart items count
     */
    public String getCartItemsCount() {
        String count = getText(shoppingCartCounter);
        LoggerUtil.info("Cart items count: " + count);
        return count;
    }
}
