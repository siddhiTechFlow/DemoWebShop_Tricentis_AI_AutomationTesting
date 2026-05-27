package com.automation.pages;

import com.automation.utilities.LoggerUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Order Confirmation Page Object Class
 */
public class OrderConfirmationPage extends BasePage {
    // Locators
    private By pageTitle = By.xpath("//h1[contains(., 'Thank you')]");
    private By successMessage = By.cssSelector(".section.order-completed .title, .section.account-page .title");
    private By orderNumber = By.xpath("//*[contains(normalize-space(), 'Order number')]");
    private By orderDetails = By.cssSelector(".section.order-completed, .details");
    private By orderSummary = By.xpath("//div[@class='section order-items']");
    private By continueButton = By.xpath("//input[@value='Continue']");
    private By downloadInvoiceButton = By.xpath("//input[@value='Download invoice (PDF)']");
    private By printInvoiceButton = By.xpath("//input[@value='Print invoice']");

    /**
     * Constructor
     */
    public OrderConfirmationPage(WebDriver driver) {
        super(driver);
        LoggerUtil.info("Initializing OrderConfirmationPage");
    }

    /**
     * Check if order confirmation page is loaded
     */
    public boolean isOrderConfirmationPageLoaded() {
        return isAnyElementDisplayed(pageTitle, successMessage, orderDetails);
    }

    /**
     * Get success message
     */
    public String getSuccessMessage() {
        String message = getText(successMessage);
        LoggerUtil.info("Success message: " + message);
        return message;
    }

    /**
     * Get order number
     */
    public String getOrderNumber() {
        String orderNum = getText(orderNumber);
        LoggerUtil.info("Order number: " + orderNum);
        return orderNum;
    }

    /**
     * Check if order details are displayed
     */
    public boolean isOrderDetailsDisplayed() {
        return isAnyElementDisplayed(orderDetails, orderNumber);
    }

    /**
     * Check if order summary is displayed
     */
    public boolean isOrderSummaryDisplayed() {
        return isElementDisplayed(orderSummary);
    }

    /**
     * Click continue button
     */
    public HomePage clickContinue() {
        clickElement(continueButton, By.cssSelector("input[value='Continue'], button[value='Continue']"));
        waitForSeconds(2);
        return new HomePage(driver);
    }

    /**
     * Click download invoice button
     */
    public void clickDownloadInvoice() {
        clickElement(downloadInvoiceButton);
        LoggerUtil.info("Download invoice button clicked");
    }

    /**
     * Click print invoice button
     */
    public void clickPrintInvoice() {
        clickElement(printInvoiceButton);
        LoggerUtil.info("Print invoice button clicked");
    }
}
