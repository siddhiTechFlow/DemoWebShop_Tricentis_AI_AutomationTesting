package com.automation.pages;

import com.automation.utilities.LoggerUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Checkout Page Object Class
 */
public class CheckoutPage extends BasePage {
    // Locators - Billing Address
    private By pageTitle = By.xpath("//h1[contains(text(), 'Checkout')]");
    private By billingAddressSelector = By.id("billing-address-select");
    private By firstNameBilling = By.id("BillingNewAddress_FirstName");
    private By lastNameBilling = By.id("BillingNewAddress_LastName");
    private By emailBilling = By.id("BillingNewAddress_Email");
    private By companyBilling = By.id("BillingNewAddress_Company");
    private By countryBilling = By.id("BillingNewAddress_CountryId");
    private By stateBilling = By.id("BillingNewAddress_StateProvinceId");
    private By cityBilling = By.id("BillingNewAddress_City");
    private By addressBilling = By.id("BillingNewAddress_Address1");
    private By zipCodeBilling = By.id("BillingNewAddress_ZipPostalCode");
    private By phoneBilling = By.id("BillingNewAddress_PhoneNumber");

    // Locators - Shipping Address
    private By shippingAddressSelector = By.id("shipping-address-select");
    private By shippingMethodRadio = By.name("shippingoption");
    private By nextButton = By.xpath("//input[@value='Next']");
    private By confirmButton = By.xpath("//input[@value='Confirm']");
    private By paymentMethodRadio = By.name("paymentmethod");
    private By billingNextButton = By.cssSelector("#billing-buttons-container input.button-1, #billing-buttons-container button");
    private By shippingAddressNextButton = By.cssSelector("#shipping-buttons-container input.button-1, #shipping-buttons-container button");
    private By shippingMethodNextButton = By.cssSelector("#shipping-method-buttons-container input.button-1, #shipping-method-buttons-container button");
    private By paymentMethodNextButton = By.cssSelector("#payment-method-buttons-container input.button-1, #payment-method-buttons-container button");
    private By paymentInfoNextButton = By.cssSelector("#payment-info-buttons-container input.button-1, #payment-info-buttons-container button");
    private By confirmOrderButton = By.cssSelector("#confirm-order-buttons-container input.button-1, #confirm-order-buttons-container button");
    private By validationErrors = By.cssSelector(".field-validation-error, .validation-summary-errors");

    /**
     * Constructor
     */
    public CheckoutPage(WebDriver driver) {
        super(driver);
        LoggerUtil.info("Initializing CheckoutPage");
    }

    /**
     * Check if checkout page is loaded
     */
    public boolean isCheckoutPageLoaded() {
        return isAnyElementDisplayed(pageTitle, billingAddressSelector, billingNextButton);
    }

    /**
     * Enter billing address details
     */
    public void enterBillingAddressDetails(String firstName, String lastName, String email,
                                          String company, String country, String state,
                                          String city, String address, String zipCode, String phone) {
        selectNewBillingAddressIfAvailable();
        typeText(firstNameBilling, firstName);
        typeText(lastNameBilling, lastName);
        typeText(emailBilling, email);
        typeText(companyBilling, company);
        selectDropdownByValue(countryBilling, country);
        waitForSeconds(1);
        selectDropdownByValue(stateBilling, state);
        typeText(cityBilling, city);
        typeText(addressBilling, address);
        typeText(zipCodeBilling, zipCode);
        typeText(phoneBilling, phone);
        LoggerUtil.info("Billing address details entered");
    }

    public void selectNewBillingAddressIfAvailable() {
        if (isElementDisplayed(billingAddressSelector)) {
            try {
                selectDropdownByValue(billingAddressSelector, "");
            } catch (Exception e) {
                LoggerUtil.info("Continuing with selected billing address because New Address option was not available");
            }
        }
    }

    /**
     * Select shipping method
     */
    public void selectShippingMethod(String methodValue) {
        By shippingMethod = By.xpath("//input[@name='shippingoption'][@value='" + methodValue
                + "' or contains(@value,'" + methodValue + "') or contains(../label, '" + methodValue + "')]");
        clickElement(shippingMethod, shippingMethodRadio);
        LoggerUtil.info("Shipping method selected: " + methodValue);
    }

    /**
     * Select payment method
     */
    public void selectPaymentMethod(String methodValue) {
        By paymentMethod = By.xpath("//input[@name='paymentmethod'][@value='" + methodValue
                + "' or contains(@value,'" + methodValue + "') or contains(../label, '" + methodValue + "')]");
        clickElement(paymentMethod, paymentMethodRadio);
        LoggerUtil.info("Payment method selected: " + methodValue);
    }

    /**
     * Select first available payment method when exact value is not found
     */
    public void selectFirstAvailablePaymentMethod() {
        try {
            if (!driver.findElements(paymentMethodRadio).isEmpty()) {
                driver.findElements(paymentMethodRadio).get(0).click();
                LoggerUtil.info("Selected first available payment method");
            }
        } catch (Exception e) {
            LoggerUtil.error("Error selecting first available payment method", e);
        }
    }

    /**
     * Check if checkout billing form is visible
     */
    public boolean isBillingFormVisible() {
        if (isElementDisplayed(billingAddressSelector) && !isElementDisplayed(firstNameBilling)) {
            return true;
        }
        return isElementDisplayed(firstNameBilling)
                && isElementDisplayed(lastNameBilling)
                && isElementDisplayed(emailBilling)
                && isElementDisplayed(countryBilling)
                && isElementDisplayed(addressBilling)
                && isElementDisplayed(phoneBilling);
    }

    /**
     * Check if shipping options section is visible
     */
    public boolean isShippingSectionVisible() {
        return isAnyElementDisplayed(shippingMethodRadio, shippingMethodNextButton);
    }

    /**
     * Check if payment options section is visible
     */
    public boolean isPaymentSectionVisible() {
        return isAnyElementDisplayed(paymentMethodRadio, paymentMethodNextButton);
    }

    /**
     * Select shipping method or fallback to first available option
     */
    public void selectShippingMethodOrFallback(String methodValue) {
        try {
            if (!driver.findElements(shippingMethodRadio).isEmpty()) {
                selectShippingMethod(methodValue);
            }
        } catch (Exception e) {
            LoggerUtil.warn("Shipping method not found with value: " + methodValue + ". Selecting first available option.");
            if (!driver.findElements(shippingMethodRadio).isEmpty()) {
                driver.findElements(shippingMethodRadio).get(0).click();
            }
        }
    }

    /**
     * Select payment method or fallback to first available option
     */
    public void selectPaymentMethodOrFallback(String methodValue) {
        try {
            selectPaymentMethod(methodValue);
        } catch (Exception e) {
            LoggerUtil.warn("Payment method not found with value: " + methodValue + ". Selecting first available option.");
            selectFirstAvailablePaymentMethod();
        }
    }

    /**
     * Click next button
     */
    public void clickNext() {
        clickElement(nextButton);
        waitForSeconds(2);
        LoggerUtil.info("Next button clicked");
    }

    public void clickBillingNext() {
        clickElement(billingNextButton, nextButton);
        waitForSeconds(1);
        LoggerUtil.info("Billing next button clicked");
    }

    public void clickShippingAddressNextIfAvailable() {
        if (isElementDisplayed(shippingAddressNextButton)) {
            clickElement(shippingAddressNextButton);
            waitForSeconds(1);
            LoggerUtil.info("Shipping address next button clicked");
        }
    }

    public void clickShippingMethodNextIfAvailable() {
        if (isElementDisplayed(shippingMethodNextButton)) {
            clickElement(shippingMethodNextButton, nextButton);
            waitForSeconds(1);
            LoggerUtil.info("Shipping method next button clicked");
        }
    }

    public void clickPaymentMethodNext() {
        clickElement(paymentMethodNextButton, nextButton);
        waitForSeconds(1);
        LoggerUtil.info("Payment method next button clicked");
    }

    public void clickPaymentInfoNext() {
        clickElement(paymentInfoNextButton, nextButton);
        waitForSeconds(1);
        LoggerUtil.info("Payment information next button clicked");
    }

    /**
     * Click confirm button
     */
    public OrderConfirmationPage clickConfirm() {
        clickElement(confirmOrderButton, confirmButton);
        waitForSeconds(3);
        return new OrderConfirmationPage(driver);
    }

    /**
     * Complete checkout process
     */
    public OrderConfirmationPage completeCheckout(String firstName, String lastName, String email,
                                                  String company, String country, String state,
                                                  String city, String address, String zipCode, String phone,
                                                  String shippingMethod, String paymentMethod) {
        enterBillingAddressDetails(firstName, lastName, email, company, country, state, city, address, zipCode, phone);
        clickBillingNext();
        clickShippingAddressNextIfAvailable();

        selectShippingMethodOrFallback(shippingMethod);
        clickShippingMethodNextIfAvailable();

        selectPaymentMethodOrFallback(paymentMethod);
        clickPaymentMethodNext();
        clickPaymentInfoNext();

        return clickConfirm();
    }

    /**
     * Check if billing address selector is displayed
     */
    public boolean isBillingAddressSelectorDisplayed() {
        return isElementDisplayed(billingAddressSelector);
    }

    public boolean isValidationErrorDisplayed() {
        return isElementDisplayed(validationErrors);
    }
}
