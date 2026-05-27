# Demo Web Shop - Comprehensive Test Strategy

## User Story
**As a customer, I want to log in, view products, add a product to the cart, and complete checkout, so that I can successfully place an order.**

---

## 1. Test Case Categories

### 1.1 Functional Tests

| Test ID | Test Description | Priority | Category | UI Elements |
|---------|-----------------|----------|----------|-------------|
| TC_LOGIN_001 | Valid login with correct credentials | P0 | Critical | Email input, Password input, Login button |
| TC_LOGIN_002 | Invalid login with wrong password | P1 | High | Email input, Password input, Error message |
| TC_LOGIN_003 | Login with empty fields | P2 | Medium | Submit button, Validation messages |
| TC_LOGIN_004 | Login with unregistered email | P2 | Medium | Email input, Error message |
| TC_SEARCH_001 | Search product by exact name | P0 | Critical | Search box, Search button, Results grid |
| TC_SEARCH_002 | Search product by partial name | P1 | High | Search results, Product cards |
| TC_SEARCH_003 | Search with no results | P2 | Medium | Empty results message |
| TC_SEARCH_004 | Search special characters | P3 | Low | Error handling, Sanitization |
| TC_CART_001 | Add product to cart | P0 | Critical | Add to cart button, Cart counter, Success message |
| TC_CART_002 | Update product quantity in cart | P1 | High | Quantity input, Update button, Total price |
| TC_CART_003 | Remove product from cart | P1 | High | Remove button, Cart table |
| TC_CART_004 | Add multiple products to cart | P1 | High | Multiple add buttons, Cart summary |
| TC_CART_005 | Add out-of-stock product | P2 | Medium | Stock status, Disabled button |
| TC_CHECKOUT_001 | Complete checkout flow | P0 | Critical | Checkout button, Address form, Payment options, Confirm button |
| TC_CHECKOUT_002 | Checkout with billing address | P1 | High | Address form fields, Validation |
| TC_CHECKOUT_003 | Checkout with shipping address | P1 | High | Shipping options, Address selection |
| TC_CHECKOUT_004 | Apply discount/coupon code | P2 | Medium | Coupon input, Apply button, Discount display |
| TC_CHECKOUT_005 | Checkout without items | P3 | Low | Empty cart message, Disabled checkout |

### 1.2 Non-Functional Tests

| Test ID | Test Description | Priority | Category | Metrics |
|---------|-----------------|----------|----------|---------|
| NF_PERF_001 | Page load time - Home page | P1 | Performance | < 3 seconds |
| NF_PERF_002 | Page load time - Search results | P1 | Performance | < 5 seconds |
| NF_PERF_003 | Page load time - Checkout | P2 | Performance | < 5 seconds |
| NF_USAB_001 | UI responsiveness on different browsers | P2 | Usability | Chrome, Firefox, Edge |
| NF_USAB_002 | Accessibility - Screen reader compatibility | P3 | Accessibility | WCAG 2.1 compliance |
| NF_SEC_001 | Session timeout handling | P2 | Security | Auto-logout after inactivity |
| NF_SEC_002 | SQL injection prevention | P2 | Security | Input sanitization |

### 1.3 Edge Case Tests

| Test ID | Test Description | Priority | Scenario |
|---------|-----------------|----------|----------|
| EC_SESSION_001 | Session expiry during checkout | P1 | User idle for extended period |
| EC_SESSION_002 | Concurrent login from multiple devices | P2 | Same user logged in twice |
| EC_CART_001 | Add product while another tab modifies cart | P2 | Cart synchronization |
| EC_CART_002 | Price change during cart review | P2 | Price update notification |
| EC_CHECKOUT_001 | Payment failure during checkout | P1 | Error handling, retry option |
| EC_CHECKOUT_002 | Address validation edge cases | P2 | Special characters, Long addresses |
| EC_DATA_001 | Excel data provider with missing fields | P2 | Null/empty handling |
| EC_DATA_002 | Unicode characters in user data | P2 | Internationalization |
| EC_UI_001 | UI element relocation after update | P1 | Fallback locators |
| EC_UI_002 | Modal/popup blocking actions | P1 | Smart modal handling |

---

## 2. Test Prioritization Matrix

### Priority Classification

| Priority | Description | Execution | Retry Policy |
|----------|-------------|-----------|--------------|
| **P0 - Critical** | Core business flow (Login → Search → Cart → Checkout) | Always run | 3 retries |
| **P1 - High** | Important features supporting core flow | Always run | 2 retries |
| **P2 - Medium** | Secondary features, edge cases | Run in regression | 1 retry |
| **P3 - Low** | Edge cases, non-critical UI | Run on-demand | No retry |

### Risk Assessment

| Risk Level | Criteria | Test Coverage |
|------------|----------|---------------|
| **High** | Direct impact on order placement | 100% - P0 tests |
| **Medium** | Indirect impact, recoverable | 80% - P0 + P1 tests |
| **Low** | Cosmetic issues, rare scenarios | 50% - All tests |

---

## 3. Test Suite Configuration

### 3.1 Suite Definitions

| Suite | Description | Test Count | Est. Time |
|-------|-------------|------------|------------|
| `testng-critical.xml` | P0 tests only | 5 | ~2 min |
| `testng-smoke.xml` | P0 + P1 tests | 15 | ~8 min |
| `testng-regression.xml` | All tests | 30+ | ~20 min |
| `testng-parallel.xml` | Parallel execution | Varies | ~5 min |

### 3.2 Parallel Execution Strategy

```
Browser Instances: 3 (Chrome, Firefox, Edge)
Tests per Suite: Parallel by test class
Thread Count: Configurable via Maven property
```

---

## 4. UI Change Handling Strategy

### 4.1 Smart Locator Strategy

| Locator Type | Reliability | Change Frequency | Fallback |
|--------------|-------------|------------------|----------|
| ID | Highest | Low | CSS, XPath |
| Name | High | Low | ID, XPath |
| CSS | Medium | Medium | XPath with text |
| XPath (absolute) | Low | High | XPath (relative) |
| XPath (relative) | Medium | Medium | CSS |

### 4.2 Locator Fallback Mechanism

```java
// Primary: Data-TestID → ID → CSS → XPath (text-based)
By[] fallbackLocators = {
    By.id("product-name"),
    By.cssSelector("[data-testid='product-name']"),
    By.xpath("//div[contains(@class,'product')]//span[text()='Product Name']")
};
```

### 4.3 UI Update Detection

- Screenshot comparison for UI regression
- Element existence checks before interaction
- Graceful degradation with informative logging

---

## 5. Data-Driven Testing

### 5.1 Excel Workbook Structure

| Sheet Name | Purpose | Columns |
|------------|---------|---------|
| `LoginData` | Login test data | email, password, expectedResult, testType |
| `ProductSearch` | Search test data | searchTerm, expectedResults, category |
| `CartData` | Cart operations | productName, quantity, expectedTotal |
| `CheckoutData` | Checkout flow | address, paymentMethod, couponCode |
| `NegativeData` | Edge cases | scenario, input, expectedError |

### 5.2 Data Execution Matrix

| Scenario | Data Source | Row Count | Coverage |
|----------|-------------|-----------|----------|
| Happy Path | Excel (Row 1) | 1 | Primary flow |
| Multiple Users | Excel | 10 | Data variety |
| Boundary Values | Excel | 5 | Edge data |
| Negative Cases | Excel | 5 | Error handling |

---

## 6. Key UI Elements Reference

### 6.1 Login Page Elements

| Element | Type | Locator Strategy | UI Change Risk |
|---------|------|------------------|----------------|
| Email Input | Text Field | name="Email", fallback: id/placeholder | Medium |
| Password Input | Password Field | name="Password", fallback: label | Medium |
| Login Button | Submit | type="submit", fallback: button text | Low |
| Remember Me | Checkbox | name="RememberMe" | Low |
| Forgot Password | Link | linkText="Forgot password?" | Low |

### 6.2 Product Page Elements

| Element | Type | Locator Strategy | UI Change Risk |
|---------|------|------------------|----------------|
| Product Grid | Container | class="product-grid" | High |
| Product Card | Container | class="product-item" | High |
| Product Name | Link/Text | class="product-title" | High |
| Add to Cart | Button | class="add-to-cart-button" | Medium |
| Price | Text | class="price" | Low |
| Rating | Stars | class="rating" | Medium |

### 6.3 Cart Page Elements

| Element | Type | Locator Strategy | UI Change Risk |
|---------|------|------------------|----------------|
| Cart Table | Table | id="shopping-cart-form" | Low |
| Quantity Input | Number | name="itemquantity" | Medium |
| Update Button | Button | value="Update" | Low |
| Remove Link | Link | class="remove-btn" | Medium |
| Total Price | Text | class="cart-total" | Low |
| Checkout Button | Button | class="checkout-button" | Low |

### 6.4 Checkout Page Elements

| Element | Type | Locator Strategy | UI Change Risk |
|---------|------|------------------|----------------|
| Address Form | Form | id="billingaddress-form" | Medium |
| First Name | Text | name="BillingNewAddress.FirstName" | Medium |
| Last Name | Text | name="BillingNewAddress.LastName" | Medium |
| Email | Email | name="BillingNewAddress.Email" | Medium |
| Address | Textarea | name="BillingNewAddress.Address1" | Medium |
| City | Text | name="BillingNewAddress.City" | Medium |
| ZIP | Text | name="BillingNewAddress.ZipPostalCode" | Medium |
| Phone | Text | name="BillingNewAddress.PhoneNumber" | Medium |
| Continue Button | Button | name="save" | Low |
| Payment Method | Radio | name="paymentmethod" | Medium |
| Confirm Button | Button | class="confirm-order" | Low |

---

## 7. Test Execution Timeline

```
Phase 1 (CI/CD - Every Commit): ~2 min
├── TC_LOGIN_001 (P0)
├── TC_SEARCH_001 (P0)
├── TC_CART_001 (P0)
└── TC_CHECKOUT_001 (P0)

Phase 2 (Nightly Build): ~8 min
├── All P0 + P1 tests
└── Parallel execution enabled

Phase 3 (Weekly Regression): ~20 min
├── All tests
├── Multiple browsers
└── Full reporting
```

---

## 8. Reporting & Monitoring

### 8.1 Extent Reports Configuration

- **Screenshot on Failure**: Automatic capture
- **Step Logging**: Detailed action logs
- **Test Duration**: Track execution time
- **Retry Tracking**: Count retry attempts

### 8.2 Log4j2 Configuration

- **Log Levels**: INFO, WARN, ERROR, DEBUG
- **Appenders**: Console, File, HTML Report
- **Log Location**: `logs/automation.log`
- **Retention**: Last 7 days

---

## 9. Success Criteria

| Metric | Target | Measurement |
|--------|--------|-------------|
| Test Coverage | > 95% for P0 | Covered requirements / Total |
| Flakiness Rate | < 5% | Failed runs / Total runs |
| Execution Time | < 30 min regression | Time measurement |
| Defect Leakage | < 2% | Production bugs / Total bugs |

---

*Document Version: 1.0*
*Last Updated: 2024*
*Author: Test Automation Team*
