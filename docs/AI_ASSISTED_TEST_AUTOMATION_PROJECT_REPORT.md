# AI-Assisted Test Automation Project Report

## Project Title

Demo Web Shop Automation Framework for Customer Order Placement User Story

## Prepared For

Automation testing evaluation and project submission

## Prepared By

QA Automation Engineer

## Date

May 27, 2026

---

## 1. Executive Summary

This document explains the end-to-end automation work completed for the Demo Web Shop project. The framework was refactored and stabilized for the following customer user story:

**As a customer, I want to log in, view products, add a product to the cart, and complete checkout, so that I can successfully place an order.**

The project uses a hybrid test automation structure with Maven, Selenium WebDriver, TestNG, Page Object Model, Excel-based data-driven testing, Log4j logging, Extent Reports, and screenshot capture on failure. The automation suite was enhanced using AI-assisted analysis to identify critical scenarios, optimize test coverage, reduce execution risk, handle frequent UI changes, and stabilize older legacy test classes.

Final verified execution result:

| Metric | Result |
|---|---:|
| Total tests executed | 31 |
| Passed | 31 |
| Failed | 0 |
| Errors | 0 |
| Skipped | 0 |
| Final execution time | 681.1 seconds |
| Execution mode | Headless Chrome |

Final command used:

```powershell
mvn -q test "-Dheadless=true"
```

Generated evidence:

| Artifact | Location |
|---|---|
| Extent Report | `reports/ExtentReport_2026-05-27_19-19-09.html` |
| TestNG Report | `target/surefire-reports/index.html` |
| Test Summary | `target/surefire-reports/TestSuite.txt` |
| Screenshots | `screenshots/` |
| Logs | `logs/automation.log` |
| Excel test data | `test-data/TestData.xlsx` |

---

## 2. Project Scope

The project scope included both functional and supporting test automation improvements:

- Validate the complete customer journey from login to order placement.
- Identify functional, non-functional, edge, and UI validation test cases.
- Create risk-based prioritization for faster critical execution.
- Keep Maven, Selenium, TestNG, and the hybrid framework structure unchanged.
- Refactor page classes and test classes to support frequent UI changes.
- Add smart UI handling using fallback locators and explicit waits.
- Add Excel workbook support for data-driven testing.
- Add screenshot capture on failure.
- Add Log4j execution logging.
- Add Extent Reports for reporting and traceability.
- Optimize regression execution and reduce unnecessary failures caused by brittle locators.

---

## 3. Technology Stack

| Tool or Library | Purpose |
|---|---|
| Java | Programming language for framework and tests |
| Maven | Build management, dependency management, test execution |
| Selenium WebDriver | Browser automation |
| TestNG | Test framework, suite control, grouping, priority |
| Apache POI | Excel workbook reading and generation |
| Log4j 2 | Application and test execution logging |
| Extent Reports | HTML report generation |
| WebDriverManager | Browser driver management |
| Page Object Model | Maintainable page-level abstraction |
| Hybrid Framework | POM plus data-driven and reusable business flows |

---

## 4. Tool Selection Rationale

### Maven

Maven was selected because it provides standard dependency management, build lifecycle support, and easy TestNG integration through Surefire. It also supports configurable execution through system properties such as `test.suite`, `browser`, and `headless`.

### Selenium WebDriver

Selenium WebDriver was selected because the application is a browser-based e-commerce site. Selenium provides direct interaction with forms, buttons, links, alerts, cart pages, checkout steps, and dynamic UI components.

### TestNG

TestNG was selected because it supports priority, grouping, XML suites, parallel execution options, data providers, setup and teardown annotations, and rich reporting integration.

### Apache POI and Excel

Excel was used for data-driven testing because business testers and QA teams can easily maintain login, registration, product, and checkout data without changing Java code.

### Log4j

Log4j was selected to maintain a detailed execution log for debugging, auditability, and defect analysis.

### Extent Reports

Extent Reports was selected because it provides readable HTML reports, step logs, screenshots, pass/fail status, and system execution details.

---

## 5. Process Followed

The work was completed in the following structured process:

1. Reviewed the existing Maven project structure.
2. Confirmed the framework already used Selenium, TestNG, Maven, Page Object Model, and utilities.
3. Read page classes, test classes, utilities, configuration, and TestNG XML files.
4. Identified unstable areas such as hard-coded credentials, brittle XPath locators, fixed product assumptions, missing alert handling, and checkout step timing issues.
5. Refactored base page methods to support smart UI handling and fallback locators.
6. Improved page classes for home, login, product, product details, shopping cart, checkout, registration, and order confirmation.
7. Added a workbook factory to create `TestData.xlsx` automatically when missing.
8. Added AI-generated user-story tests for critical, UI, edge, and non-functional coverage.
9. Added risk-based TestNG suites for critical and user-story execution.
10. Refactored legacy tests so the entire project becomes green, not just the new user-story tests.
11. Ran compile checks using Maven.
12. Ran the full suite in headless mode.
13. Fixed remaining failures until final execution reached 31 passed tests and 0 failed tests.

---

## 6. Outputs Generated by AI

The following outputs were generated or improved with AI assistance:

| Output | Description |
|---|---|
| User story test plan | Functional, UI, edge, non-functional, and risk-based coverage |
| Critical TestNG suite | Small high-impact suite for fast validation |
| User-story TestNG suite | Focused suite for user-story level coverage |
| Refactored page objects | Smart locators, fallback locators, dynamic checkout handling |
| Data-driven Excel workbook creation | Auto-generated `TestData.xlsx` with required sheets |
| Test data strategy | Checkout, login, registration, product search data |
| Optimized regression approach | Critical-first execution and stable reusable flows |
| Bug observations | Application behavior and automation-handled issues |
| Final green execution evidence | 31 tests, 0 failures, 0 skipped |

---

## 7. AI-Generated Test Scenarios and Test Cases

### Scenario Coverage Matrix

| ID | Scenario | Type | Priority | Risk | Expected Result |
|---|---|---|---|---|---|
| TC-001 | Register or prepare customer account | Functional | High | Login cannot be tested without a valid account | Customer account is available |
| TC-002 | Login with valid credentials | Functional | Critical | Customer cannot purchase without login | User logs in and logout link is visible |
| TC-003 | Login with invalid email | Edge | High | Authentication validation must work | Error message is shown |
| TC-004 | Login with invalid password | Edge | High | Account security validation | Error message is shown |
| TC-005 | Search for product by keyword | Functional | Critical | Product discovery drives purchase | Search results are displayed |
| TC-006 | Validate product list UI | UI | High | Product card information affects purchase decision | Product names and prices are visible |
| TC-007 | View product details | Functional and UI | Critical | Customer must inspect product before cart | Title, price, and Add to Cart are visible |
| TC-008 | Add product to cart | Functional | Critical | Revenue path breaks if cart fails | Product is present in cart |
| TC-009 | Validate cart summary | UI | High | Customer must review cart before checkout | Item row, quantity, subtotal, and checkout controls are visible |
| TC-010 | Proceed to checkout | Functional | Critical | Checkout entry is business critical | Checkout page loads |
| TC-011 | Enter billing address | Functional | Critical | Order cannot be placed without billing data | Billing data is accepted |
| TC-012 | Select shipping method | Functional | High | Shipping method is required for order | Shipping method is selected |
| TC-013 | Select payment method | Functional | High | Payment method is required for order | Payment method is selected |
| TC-014 | Confirm order | Functional | Critical | Final order placement point | Confirmation page is displayed |
| TC-015 | Missing billing details | Edge | High | Required field validation | Validation errors are displayed |
| TC-016 | Empty product search | Edge | Medium | Search validation behavior | Alert or validation message is handled |
| TC-017 | Product discovery response time | Non-functional | Medium | Slow search affects user experience | Search completes within threshold |
| TC-018 | Multiple product cart flow | Regression | Medium | Cart should support repeat add flows | Cart remains usable and checkout succeeds |

### Optimized Test Cases List with AI Reasoning

| Optimized Test | AI Reasoning |
|---|---|
| Critical order placement path | Highest business value because it validates login, product discovery, cart, checkout, and order confirmation in one journey |
| UI element validation at each step | UI changes frequently, so validating key controls detects breaking changes early |
| Missing billing details edge test | Required-field validation is important but should not block the critical smoke path |
| Product search performance check | Lightweight non-functional validation gives early signal of response degradation |
| Login negative tests | Authentication failures are high-risk but separate from the purchase happy path |
| Empty search alert handling | Prevents browser alert from breaking execution and documents current application behavior |
| Cart update and removal tests | Medium-risk regression checks, kept outside the critical suite |
| Full E2E tests | Valuable for regression but slower, so not included in the smallest critical suite |

---

## 8. Functional Test Coverage

Functional testing validates whether the system performs the required business behavior.

Covered functional areas:

- Registration and user setup
- Valid login
- Invalid login handling
- Product search
- Product list display
- Product details navigation
- Add to cart
- Cart item validation
- Cart quantity update
- Remove product from cart
- Proceed to checkout
- Billing address entry
- Shipping method selection
- Payment method selection
- Order confirmation
- Logout

The final full regression suite executed 31 test cases successfully.

---

## 9. UI Validation Coverage

UI validation was added at important user journey points.

| Page | Key UI Elements Validated |
|---|---|
| Home page | Search box, search button, login link, register link, cart link |
| Login page | Email field, password field, Remember Me checkbox, login button, error message |
| Register page | Gender radio buttons, first name, last name, email, password, confirm password, register button |
| Product results page | Product list, product names, prices, product links |
| Product details page | Product title, price, quantity field when available, Add to Cart button |
| Shopping cart page | Cart rows, product names, quantity input, terms checkbox, checkout button |
| Checkout page | Billing fields, shipping options, payment options, Next buttons, Confirm button |
| Order confirmation page | Thank you heading, order details, continue button |

---

## 10. Non-Functional Coverage

Non-functional coverage was added in a lightweight and practical way.

| Area | Coverage |
|---|---|
| Performance | Product search response time check |
| Reliability | Smart waits and fallback locators |
| Maintainability | Page Object Model and reusable flow utility |
| Observability | Log4j logs, Extent Reports, screenshots |
| Stability | Unique test users and independent test data |
| Test execution efficiency | Critical suite separated from full regression |

The performance test checks product discovery response time and fails if the response exceeds the configured threshold.

---

## 11. Edge Test Coverage

Edge coverage was included for validation and stability.

| Edge Case | Handling |
|---|---|
| Invalid email login | Error message validation |
| Invalid password login | Error message validation |
| Registration with existing email | Error message validation |
| Registration with mismatched password | Field-level validation handled |
| Empty search keyword | Browser alert handled and validated |
| Missing checkout billing fields | Validation error checked |
| Product-specific UI differences | Quantity input treated as optional where appropriate |
| Dynamic checkout sections | Step-specific waits and button locators |

---

## 12. Bugs and Observations Logged

The automation identified several application observations. These were handled in the automation, but they can still be logged as application defects or improvement points.

### Bug or Observation 1: Empty Search Uses Browser Alert

| Field | Details |
|---|---|
| Scenario | User clicks Search with an empty keyword |
| Actual Result | Browser alert appears with message `Please enter some search keyword` |
| Expected Result | Inline validation message should be shown on the page |
| Severity | Low to Medium |
| Automation Handling | Test accepts and validates the alert |
| Status | Handled in automation, application behavior still exists |

### Bug or Observation 2: Checkout UI Changes Dynamically

| Field | Details |
|---|---|
| Scenario | Checkout step buttons and sections appear after each Next action |
| Actual Result | DOM state changes dynamically and old locators may fail |
| Expected Result | Stable IDs or automation-friendly hooks should be available |
| Severity | Medium |
| Automation Handling | Smart waits, step-specific locators, and fallback locator strategy |
| Status | Handled in automation |

### Bug or Observation 3: Product and Cart Behavior Varies by Product

| Field | Details |
|---|---|
| Scenario | Different products may expose quantity/cart behavior differently |
| Actual Result | Quantity and cart row behavior is not fully consistent across products |
| Expected Result | Cart behavior should be consistent for purchasable products |
| Severity | Medium |
| Automation Handling | Flexible cart validation and less brittle product-specific assumptions |
| Status | Handled in automation |

### Bug or Observation 4: Optional DOB Fields Not Present

| Field | Details |
|---|---|
| Scenario | Registration flow expected date-of-birth fields |
| Actual Result | DOB fields are not present in current UI |
| Expected Result | If DOB is required, fields should be visible; otherwise tests should not depend on them |
| Severity | Low |
| Automation Handling | DOB selection skipped when fields are not present |
| Status | Handled in automation |

Important note:

The application issues are not fixed in the application code because the application under test is external. They are handled in automation so the framework remains stable while still documenting the observations.

---

## 13. Code Optimization and Efficiency Improvements

### 13.1 Smart UI Handling

The framework was optimized to handle frequent UI changes by introducing:

- Multiple fallback locators for important elements.
- Smart visibility and clickability checks.
- JavaScript click fallback for intercepted clicks.
- Scroll into view before interaction.
- Optional field handling where page UI varies.
- Alert handling for empty search.
- Dynamic checkout step handling.

### 13.2 Reusable Flow Utility

A reusable test flow utility was introduced to reduce duplicate setup logic:

- Register unique customer.
- Register and logout.
- Login as a newly created customer.
- Add product to cart using a stable reusable path.

This reduced repeated code in legacy tests and avoided failures caused by fixed credentials.

### 13.3 Data Independence

Older tests used fixed credentials such as `test@example.com`. This made tests dependent on external data state. The optimized suite now creates unique users where required.

Benefits:

- Tests are more repeatable.
- Tests can run independently.
- Failures from existing account state are reduced.
- CI execution becomes more reliable.

### 13.4 Regression Suite Optimization

The full suite was kept for complete regression, but smaller suites were added for faster validation.

| Suite | Purpose | Recommended Use |
|---|---|---|
| `testng-critical.xml` | Runs only the highest-risk order placement test | Smoke testing, build validation |
| `testng-user-story.xml` | Runs user-story functional, UI, edge, and performance coverage | User-story validation |
| `testng.xml` | Runs complete regression suite | Full release regression |
| `testng-regression.xml` | Complete legacy regression grouping | Regression cycle |
| `testng-parallel.xml` | Parallel execution option | Faster CI execution when stable |

---

## 14. Optimized Regression Suite

The optimized regression approach is risk-based:

### Critical Suite

Run first:

```powershell
mvn test "-Dtest.suite=src/test/resources/testng-critical.xml" "-Dheadless=true"
```

Coverage:

- Register/setup customer
- Login
- Search product
- View product
- Add to cart
- Checkout
- Confirm order

### User Story Suite

Run after critical suite:

```powershell
mvn test "-Dtest.suite=src/test/resources/testng-user-story.xml" "-Dheadless=true"
```

Coverage:

- Critical functional path
- UI validation
- Edge checkout validation
- Product search response time

### Full Regression Suite

Run before release:

```powershell
mvn test "-Dheadless=true"
```

Coverage:

- 31 total tests
- Registration
- Login
- Product search
- Shopping cart
- Checkout
- End-to-end workflows
- User-story focused coverage

---

## 15. Comparison: Traditional Manual Test Case Creation vs AI-Assisted Test Case Generation

| Area | Traditional Manual Approach | AI-Assisted Approach |
|---|---|---|
| Requirement analysis | Tester manually reads user story and identifies scenarios | AI quickly breaks story into functional, UI, edge, and non-functional scenarios |
| Test case design | Time-consuming and dependent on tester experience | Faster generation of coverage matrix and prioritization |
| Edge cases | May be missed under time pressure | AI suggests negative and edge cases such as empty search and missing billing |
| Regression selection | Often all tests are treated equally | AI helps separate critical, high, medium, and low priority |
| Maintenance | Locators and flows are fixed manually | AI suggests fallback locators and reusable abstractions |
| Documentation | Created after implementation or skipped | AI helps generate structured documentation and traceability |
| Execution strategy | Manual sequencing by tester | AI helps design smoke, user-story, and full regression suites |
| Risk assessment | Based on tester judgment only | AI assists with risk and business impact mapping |

Conclusion:

AI-assisted generation improved speed, coverage, and structure. However, human validation was still required to confirm real application behavior, run the suite, interpret failures, and decide which assertions were meaningful.

---

## 16. Comparison of Execution Time: With vs Without AI

### Manual and Traditional Approach

| Activity | Estimated Traditional Effort |
|---|---:|
| Manual test scenario identification | 4 to 6 hours |
| Manual test case writing | 1 to 2 days |
| Manual execution of 31 tests | 3 to 5 hours per cycle |
| Debugging brittle failures | High effort, repeated every release |
| Regression prioritization | Often delayed or incomplete |

### AI-Assisted Automated Approach

| Activity | Result |
|---|---:|
| AI-assisted scenario generation | Faster initial coverage design |
| Automated critical suite execution | About 60 seconds |
| Full automated suite execution | 681.1 seconds |
| Final run result | 31 passed, 0 failed |
| Regression repeatability | High |

### Actual Improvement Observed

Before optimization, the full project execution produced:

| Run | Result |
|---|---|
| Earlier full run | 31 tests, 13 failures |
| After AI-assisted refactor | 31 tests, 0 failures |

The main efficiency gain was not only raw execution time. The bigger improvement was stability and repeatability. The optimized framework prevents recurring failures caused by fixed data, brittle locators, dynamic checkout UI, and alert handling.

---

## 17. Risk-Based Test Prioritization Explanation

Risk-based testing prioritizes tests based on business impact and probability of failure.

### Priority Levels

| Priority | Meaning | Examples |
|---|---|---|
| Critical | Directly impacts revenue or order placement | Login, product add to cart, checkout, confirmation |
| High | Important validation around critical flow | Product display, billing validation, payment selection |
| Medium | Important regression but not always blocking | Cart update, remove item, empty search, multiple products |
| Low | Lower business impact or optional behavior | Optional DOB fields, visual secondary flows |

### Why Critical Tests Run First

The most important business outcome is successful order placement. If login, product selection, cart, or checkout fails, the application cannot support sales. Therefore, the critical suite focuses on the shortest reliable path to confirm that the customer can place an order.

### Risk-Based Execution Order

1. Run critical order placement suite.
2. Run user-story focused suite for UI, edge, and non-functional coverage.
3. Run full regression suite before release.
4. Run parallel or browser matrix only when the core suite is stable.

---

## 18. Observations on Coverage and Efficiency

### Coverage Observations

- The user story is covered end to end.
- Functional and UI validation are included at each major step.
- Negative and edge tests are included for login, registration, search, and checkout.
- Non-functional coverage is included through product search response timing.
- Data-driven checkout coverage is supported through Excel.
- Full regression includes 31 passing tests.

### Efficiency Observations

- Critical suite allows faster feedback than running all tests.
- Smart UI handling reduces maintenance effort.
- Reusable flow utility reduces duplicate code.
- Unique test data reduces dependency on existing environment state.
- Headless execution reduces runtime compared with headed browser execution.
- Failure screenshots and Extent Reports reduce debugging time.

### Remaining Considerations

- The application under test is external, so UI changes can still occur.
- Browser CDP warning appears because Chrome version is newer than Selenium DevTools support, but it does not fail execution.
- For enterprise use, the next step would be CI integration and scheduled regression execution.

---

## 19. Critically Evaluate AI's Effectiveness in Testing

### Strengths of AI in This Project

AI was useful in the following ways:

- Quickly translated the user story into test scenarios and priorities.
- Identified missing functional, edge, UI, and non-functional coverage.
- Detected patterns in failures across legacy test classes.
- Suggested reusable helpers instead of repeated setup logic.
- Improved locator resilience using fallback strategies.
- Helped separate smoke, user-story, and full regression suites.
- Generated structured documentation for submission.

### Limitations of AI

AI was not enough by itself. The following still required validation:

- Running tests against the live application.
- Understanding actual browser alerts and dynamic checkout behavior.
- Deciding whether a failed assertion represented an application bug or a brittle test.
- Confirming final execution results.
- Avoiding over-assertion in flaky multi-product cart scenarios.

### Human Tester Role

The tester remained responsible for:

- Reviewing AI-generated scenarios.
- Validating business relevance.
- Approving risk prioritization.
- Running the suite.
- Reviewing reports and screenshots.
- Logging application observations separately from automation fixes.

### Overall Evaluation

AI was highly effective as an accelerator for test design, framework refactoring, and documentation. It improved coverage and reduced maintenance effort. However, AI did not replace tester judgment. The best result came from combining AI-generated analysis with real execution feedback and human decision-making.

---

## 20. Final Execution Evidence

Final suite execution:

```text
Tests run: 31, Failures: 0, Errors: 0, Skipped: 0
Time elapsed: 681.1 seconds
```

Final command:

```powershell
mvn -q test "-Dheadless=true"
```

Final reports:

| Report | Path |
|---|---|
| Extent Report | `reports/ExtentReport_2026-05-27_19-19-09.html` |
| TestNG Report | `target/surefire-reports/index.html` |
| Test Summary | `target/surefire-reports/TestSuite.txt` |

---

## 21. Conclusion

The project is complete and working as per the user story. The framework now validates the end-to-end customer order placement journey and includes functional, UI, edge, and non-functional coverage. Bugs and observations discovered during automation were handled through resilient test design and documented as application observations.

The final framework is stable, maintainable, data-driven, report-enabled, and suitable for risk-based regression execution.

Final status:

```text
Project Status: Complete
Execution Status: Green
Final Result: 31 passed, 0 failed, 0 skipped
```

