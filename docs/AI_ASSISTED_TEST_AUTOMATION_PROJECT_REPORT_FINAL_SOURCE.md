# AI-Assisted Test Automation Project Report

## Introduction

This document presents the complete end-to-end automation work completed for the Demo Web Shop project. The automation framework was enhanced for the customer order placement user story: as a customer, the user should be able to log in, view products, add a product to the cart, complete checkout, and successfully place an order.

The project was improved using an AI-assisted testing approach. AI was used to generate test scenarios, identify edge cases, prioritize risk-based coverage, improve automation stability, optimize regression execution, and prepare structured documentation. Human validation was still used throughout the process to review failures, verify the application behavior, and confirm the final test results.

The final automation suite is fully green. The full regression execution completed with 31 tests passed, 0 failures, 0 errors, and 0 skipped tests.

## Project Overview

The project follows a hybrid Selenium automation framework using Maven, TestNG, Java, Page Object Model, Excel-based data-driven testing, Log4j logging, Extent Reports, and screenshots on failure. The existing project structure was preserved, while unstable areas were refactored to support frequent UI changes and reliable execution.

The final framework validates the following areas:

- Customer registration and login.
- Product search and product details.
- Add to cart and cart validation.
- Checkout with billing, shipping, and payment steps.
- Order confirmation.
- UI validation at each major page.
- Edge cases such as invalid login, empty search, duplicate registration, mismatched password, and missing billing details.
- Basic non-functional validation through product search response time.

## User Story and Objective

The main user story covered by this automation framework is:

**As a customer, I want to log in, view products, add a product to the cart, and complete checkout, so that I can successfully place an order.**

The objective was not only to automate this happy path, but also to make the framework stable enough for regression execution. Since the website UI can change frequently, the automation was designed with smart waits, fallback locators, dynamic checkout handling, optional field checks, and reusable setup flows.

## Technology Stack and Tool Selection

| Tool | Purpose and Rationale |
|---|---|
| Maven | Used for dependency management, build lifecycle, and TestNG suite execution. |
| Selenium WebDriver | Used for browser automation and UI interaction. |
| TestNG | Used for test organization, priority, suites, assertions, and reporting integration. |
| Java | Main programming language for the framework. |
| Page Object Model | Used to separate page logic from test logic and improve maintainability. |
| Apache POI | Used for Excel-based data-driven testing. |
| Log4j 2 | Used for structured execution logging. |
| Extent Reports | Used for HTML reporting with test steps and screenshots. |
| WebDriverManager | Used to simplify browser driver setup. |

## Process Followed

The work started with a review of the existing Maven project. The framework already included Selenium, TestNG, Page Object Model classes, utility classes, configuration files, and TestNG XML suites. After reviewing the code, several issues were identified: hard-coded credentials, brittle locators, product-specific cart assumptions, dynamic checkout behavior, missing alert handling, and repeated setup code.

The base page and page object classes were then refactored. Smart UI handling was added through fallback locators, explicit waits, scroll handling, JavaScript click fallback, and optional field handling. The checkout flow was improved with step-specific methods for billing, shipping, payment, payment information, and confirmation.

AI-generated test scenarios were added for functional, UI, edge, and non-functional coverage. The old legacy tests were also updated so the entire project could run green, not just the new user-story tests. Finally, the full suite was executed repeatedly until the final execution showed 31 passed tests and 0 failed tests.

## AI-Generated Test Scenarios and Test Cases

AI was used to expand the user story into practical automation scenarios. The generated scenarios covered the critical business flow as well as negative, UI, and performance-related validations.

| ID | Scenario | Type | Priority | Expected Result |
|---|---|---|---|---|
| TC-001 | Prepare or register a customer account | Functional | High | Customer account is available. |
| TC-002 | Login with valid credentials | Functional | Critical | User logs in successfully. |
| TC-003 | Login with invalid email | Edge | High | Error message is displayed. |
| TC-004 | Login with invalid password | Edge | High | Error message is displayed. |
| TC-005 | Search product by keyword | Functional | Critical | Product results are displayed. |
| TC-006 | Validate product cards | UI | High | Product names and prices are visible. |
| TC-007 | Open product details | Functional/UI | Critical | Product title, price, and Add to Cart are visible. |
| TC-008 | Add product to cart | Functional | Critical | Product appears in cart. |
| TC-009 | Validate cart summary | UI | High | Cart rows, quantity, terms, and checkout controls are visible. |
| TC-010 | Complete checkout | Functional | Critical | Order confirmation is displayed. |
| TC-011 | Missing billing details | Edge | High | Validation error is displayed. |
| TC-012 | Empty product search | Edge | Medium | Alert or validation is handled correctly. |
| TC-013 | Product search response time | Non-functional | Medium | Search completes within the threshold. |

## Coverage Summary

The final suite provides strong coverage for the given user story. Functional coverage validates whether the application performs the required business actions. UI coverage validates whether important forms, buttons, links, fields, labels, and checkout controls are visible. Edge coverage verifies invalid and boundary situations. Non-functional coverage provides a lightweight response-time check for product discovery.

The most important coverage areas are:

- Login and logout validation.
- Registration with valid and invalid data.
- Product search and product details.
- Product add-to-cart validation.
- Cart update, removal, subtotal, and empty cart checks.
- Checkout entry, billing details, shipping method, payment method, and order confirmation.
- UI validation at home, login, register, product, cart, checkout, and confirmation pages.

## Bugs and Observations Logged

During automation, some application observations were identified. Since the Demo Web Shop is an external application, these issues were not fixed in the application code. Instead, the automation framework was updated to handle them gracefully so that tests remain stable.

| ID | Bug / Observation | Steps to Reproduce | Actual Result | Expected Result | Severity | Automation Handling |
|---|---|---|---|---|---|---|
| BUG-001 | Empty search uses browser alert | 1. Open home page. 2. Keep search field empty. 3. Click Search. | Browser alert appears with message: `Please enter some search keyword`. | Inline validation message should appear on the page. | Low/Medium | Alert is accepted and validated in automation. |
| BUG-002 | Checkout sections change dynamically | 1. Add product to cart. 2. Start checkout. 3. Click Next through checkout steps. | Buttons and sections appear dynamically after each step. | Stable IDs or automation-friendly hooks should be available. | Medium | Step-specific waits and fallback locators were added. |
| BUG-003 | Product/cart behavior varies by product | 1. Search different products. 2. Add them to cart. 3. Inspect quantity and cart rows. | Quantity and cart behavior can vary by product. | Purchasable products should behave consistently in cart. | Medium | Flexible cart validation and optional quantity handling were added. |
| BUG-004 | Optional DOB fields are not present | 1. Open registration page. 2. Inspect available form fields. | DOB fields expected by older test design are not present. | If DOB is required, fields should be visible; otherwise tests should not depend on them. | Low | DOB selection is skipped when fields are absent. |

These observations can be reported as application improvement points. In the automation framework, they are already handled.

## Code Optimization and Efficiency Improvements

The framework was optimized to reduce false failures and improve maintainability. The most important improvement was smart UI handling. Instead of depending on one locator for each element, important actions now support fallback locators and explicit waits. This helps the automation survive minor UI changes.

The checkout process was also optimized. Earlier, tests used generic Next button clicks, which could become unstable because checkout sections load dynamically. The framework now has dedicated methods for billing, shipping address, shipping method, payment method, payment information, and final confirmation.

Reusable flow utilities were added for common setup activities such as registering a unique customer, logging in as a new customer, and adding a product to the cart. This reduced duplicate code and removed dependency on fixed credentials such as `test@example.com`.

Other improvements include:

- Automatic Excel workbook creation when test data is missing.
- Failure screenshots attached to reports.
- Log4j execution logs for debugging.
- Success screenshots disabled to reduce unnecessary output.
- Headless execution support for faster regression runs.
- Optional field handling for fields that may or may not appear.

## Optimized Regression Suite

The regression suite was organized using a risk-based approach. Instead of running all tests for every small change, the framework supports different levels of execution.

| Suite | Purpose | Recommended Use |
|---|---|---|
| `testng-critical.xml` | Runs the highest-risk order placement path. | Smoke testing and build validation. |
| `testng-user-story.xml` | Runs user-story functional, UI, edge, and performance coverage. | User-story acceptance validation. |
| `testng.xml` | Runs the complete 31-test regression suite. | Full release regression. |

Recommended execution commands:

```powershell
mvn test "-Dtest.suite=src/test/resources/testng-critical.xml" "-Dheadless=true"
mvn test "-Dtest.suite=src/test/resources/testng-user-story.xml" "-Dheadless=true"
mvn test "-Dheadless=true"
```

## Traditional Manual Testing vs AI-Assisted Testing

Traditional manual test case creation usually requires the tester to manually read the user story, identify scenarios, write test cases, prioritize them, execute them, and update them when the UI changes. This is effective but time-consuming, especially when the application changes frequently.

AI-assisted test generation helped speed up this process by quickly identifying functional, UI, edge, and non-functional scenarios. It also helped identify risk-based priorities, common failure patterns, and opportunities for code reuse.

| Area | Traditional Manual Approach | AI-Assisted Approach |
|---|---|---|
| Scenario design | Created manually from requirements. | AI quickly generated functional, UI, edge, and non-functional scenarios. |
| Edge case discovery | Depends heavily on tester experience. | AI suggested empty search, missing billing, invalid login, and dynamic UI checks. |
| Regression planning | Often all tests are treated equally. | AI helped separate critical, user-story, and full regression suites. |
| Maintenance | Locators are fixed after failures occur. | Fallback locators and smart UI handling were added proactively. |
| Documentation | Often written after implementation. | AI helped generate structured documentation and traceability. |

AI did not replace the tester. The final decisions, execution review, bug interpretation, and assertion choices still required human judgment.

## Execution Time and Efficiency Comparison

Before optimization, the full suite had several failures caused by brittle test design and changing UI behavior. After AI-assisted refactoring, the same full suite became stable.

| Execution Area | Result |
|---|---|
| Earlier full execution | 31 tests executed, 13 failed. |
| Final optimized execution | 31 tests executed, 0 failed. |
| Final full suite time | 681.1 seconds. |
| Critical suite feedback | Around 60 seconds for the main order placement path. |
| Main efficiency gain | Stability, repeatability, and reduced debugging effort. |

The biggest benefit was not only runtime improvement. The key improvement was that the suite became repeatable and reliable. Tests now create their own users, handle alerts, use smart locators, and manage dynamic checkout steps.

## Risk-Based Test Prioritization

Risk-based testing was used to decide which tests should run first. The most business-critical flow is order placement, because it directly affects revenue. Therefore, login, product selection, cart, checkout, and confirmation were treated as critical.

Priority levels used:

- **Critical:** Login, add to cart, checkout, and order confirmation.
- **High:** Product display, billing validation, shipping method, and payment method.
- **Medium:** Cart update, remove item, empty search, and multiple product checks.
- **Low:** Optional UI behavior such as DOB fields.

Recommended order:

1. Run the critical suite first.
2. Run the user-story suite for UI, edge, and performance validation.
3. Run the full regression suite before release.

## AI Effectiveness in Testing

AI was effective as an accelerator for test design, analysis, and documentation. It helped convert the user story into structured test scenarios, identify coverage gaps, suggest risk-based prioritization, and improve framework stability.

However, AI was not enough by itself. The tests still had to be executed against the real application. Some failures only became clear during live execution, such as dynamic checkout behavior, browser alerts, and product-specific cart behavior. Human review was required to decide whether a failure was an application bug, an automation issue, or an overly strict assertion.

Overall, AI was useful for improving speed, structure, and coverage, while human QA judgment remained essential for validation and final approval.

## Final Execution Evidence

Final execution command:

```powershell
mvn -q test "-Dheadless=true"
```

Final result:

```text
Tests run: 31, Failures: 0, Errors: 0, Skipped: 0
Time elapsed: 681.1 seconds
```

Generated outputs:

- Extent Report: `reports/ExtentReport_2026-05-27_19-19-09.html`
- TestNG Report: `target/surefire-reports/index.html`
- Test Summary: `target/surefire-reports/TestSuite.txt`
- Logs: `logs/automation.log`
- Screenshots: `screenshots/`
- Excel Test Data: `test-data/TestData.xlsx`

## Conclusion

The project is complete and working according to the user story. It validates the customer journey from login to order confirmation and also includes UI validation, edge cases, data-driven testing, logging, reporting, screenshots, and optimized regression execution.

The final framework is stable, maintainable, and suitable for release regression. The final verified result is 31 tests passed, 0 failed, 0 errors, and 0 skipped.
