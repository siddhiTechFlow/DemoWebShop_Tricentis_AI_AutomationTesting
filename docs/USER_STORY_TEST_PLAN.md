# User Story Test Plan

## User Story
As a customer I want to log in, view products, add a product to the cart, and complete checkout, so that I can successfully place an order.

## Scope
- Critical path: Login, search/view product, add to cart, complete checkout, view order confirmation
- Functional coverage: UI element validation, page navigation, cart totals, checkout form submission
- Non-functional coverage: page load responsiveness, error handling, retry/fallback behavior for UI changes
- Edge coverage: invalid login, empty product search, missing billing data, dynamic UI changes, cart update/removal

## Functional Test Cases
1. Login with valid credentials and verify home page landing
2. Search for a product, validate search results, and open product details
3. Add a product to the cart and verify cart contents
4. Proceed to checkout, validate billing/shipping/payment UI elements
5. Complete order and verify confirmation message and order summary

## UI Validation Points
- Home Page: search box, search button, login link, cart link, welcome heading
- Login Page: email field, password field, login button, remember me checkbox
- Product Page: product list, product title links, product price labels, result count
- Product Details Page: title header, price label, quantity field, add to cart button
- Shopping Cart Page: cart item rows, product name links, quantity input, checkout button
- Checkout Page: billing fields, shipping options, payment options, next/confirm buttons
- Order Confirmation Page: thank you heading, order details section, continue button

## Key UI Elements and Locators
- Forms: login form, billing address form, shipping options section
- Buttons: `Log in`, `Search`, `Add to cart`, `Checkout`, `Next`, `Confirm`, `Continue`
- Links: `Shopping cart`, `Log in`, `Register`, product title links
- Inputs: search text, email, password, quantity, billing address fields
- Labels: product price, order total, confirmation message

## Edge Cases
- Invalid credentials should display a login error
- Empty search should show no results or a validation message
- Incorrect billing address or missing required fields should trigger form validation
- Cart update behavior should handle increased quantity and removal
- Dynamic UI changes should fall back to alternate selectors and keep test flow stable

## Risk-Based Prioritization
1. Critical: login, add item to cart, checkout, order confirmation
2. High: product search/display, cart totals, billing + payment form availability
3. Medium: remember me, multiple product purchase, cart update removal
4. Low: registration workflow, wishlist/compare functionality, invoice download

## Execution Strategy to Reduce Time
- Use `testng-critical.xml` for the smallest high-value path
- Keep critical tests lean and stable, avoiding slow secondary flows
- Reuse page object actions for common navigation steps
- Capture only essential screenshots on failure to preserve execution performance
- Run critical tests first, then expanded regression suites as needed

## Test Data Strategy
- Data-driven tests read from `test-data/TestData.xlsx`
- Keep a focused sheet for checkout scenarios with valid customer and product data
- Support multiple user and product permutations without code changes

## Reporting and Test Diagnostics
- Log4j captures execution details to `logs/automation.log`
- Extent Reports create HTML reports in `reports/`
- Screenshots are captured on failure and added to reports for fast triage

## Recommended Execution Commands
```bash
# Run the full suite
mvn clean test

# Run the critical user-story suite
mvn test -Dtest.suite=src/test/resources/testng-critical.xml
```
