# Demo Web Shop Test Automation Framework

## Overview
This is a comprehensive End-to-End Test Automation Framework built using **Selenium WebDriver**, **TestNG**, **Maven**, and a **Hybrid Testing Framework** approach. The framework is designed to test the complete customer purchase workflow on the Demo Web Shop application (https://demowebshop.tricentis.com/).

## Features

### ✅ Comprehensive Testing Coverage
- **User Registration** - Valid registration, duplicate email, password mismatch
- **User Login** - Valid login, invalid email, invalid password, remember me, logout
- **Product Search** - Keyword search, product list, product details, empty search, special characters
- **Shopping Cart** - Add single/multiple products, update quantity, remove items, validate totals
- **Checkout Process** - Billing address, shipping method, payment method
- **Order Confirmation** - Order success, order details, order summary
- **End-to-End Workflows** - Complete purchase process for new and existing users, multiple products

### 🛠️ Technology Stack
- **Language:** Java 11
- **Build Tool:** Maven
- **WebDriver:** Selenium 4.15.0
- **Test Framework:** TestNG 7.8.1
- **Logging:** Log4j 2.21.1
- **Data-Driven Testing:** Apache POI (Excel)
- **Reporting:** Extent Reports 5.1.1
- **WebDriver Management:** WebDriver Manager 5.6.3
- **Design Pattern:** Page Object Model (POM) + Hybrid Framework

### 📁 Project Structure

```
DemoWebShopAutomation/
├── src/
│   ├── main/
│   │   ├── java/com/automation/
│   │   │   ├── base/
│   │   │   │   └── BaseTest.java          # Base test class with setup/teardown
│   │   │   ├── pages/
│   │   │   │   ├── BasePage.java          # Base page with common methods
│   │   │   │   ├── HomePage.java          # Home page object
│   │   │   │   ├── LoginPage.java         # Login page object
│   │   │   │   ├── RegisterPage.java      # Registration page object
│   │   │   │   ├── ProductPage.java       # Product search page object
│   │   │   │   ├── ProductDetailsPage.java # Product details page object
│   │   │   │   ├── ShoppingCartPage.java  # Shopping cart page object
│   │   │   │   ├── CheckoutPage.java      # Checkout page object
│   │   │   │   └── OrderConfirmationPage.java # Order confirmation page object
│   │   │   └── utilities/
│   │   │       ├── LoggerUtil.java        # Logging utility
│   │   │       ├── ScreenshotUtil.java    # Screenshot capture utility
│   │   │       ├── ExcelUtil.java         # Excel read/write utility
│   │   │       ├── DriverManager.java     # WebDriver management
│   │   │       ├── PropertyManager.java   # Configuration property manager
│   │   │       ├── WaitUtil.java          # Explicit wait utility
│   │   │       ├── ExtentReportManager.java # Extent reports utility
│   │   │       └── DataProviderUtil.java  # Data provider utility
│   │   └── resources/
│   │       └── log4j2.xml                 # Log4j configuration
│   └── test/
│       ├── java/com/automation/
│       │   ├── base/
│       │   │   └── BaseTest.java          # Base test class
│       │   └── tests/
│       │       ├── RegistrationTest.java        # Registration test cases
│       │       ├── LoginTest.java              # Login test cases
│       │       ├── ProductSearchTest.java      # Product search test cases
│       │       ├── ShoppingCartTest.java       # Shopping cart test cases
│       │       ├── CheckoutTest.java           # Checkout test cases
│       │       └── EndToEndPurchaseWorkflowTest.java # E2E workflow tests
│       └── resources/
│           ├── config.properties         # Configuration file
│           └── testng.xml               # TestNG configuration
├── test-data/
│   └── TestData.xlsx                   # Excel test data
├── screenshots/                        # Screenshot storage
├── reports/                            # Extent reports
├── logs/                               # Log files
├── pom.xml                             # Maven configuration
└── README.md                           # Documentation

```

## Installation & Setup

### Prerequisites
- **Java 11 or higher** - Download from [Oracle Java](https://www.oracle.com/java/technologies/javase-downloads.html)
- **Maven 3.8.9 or higher** - Download from [Apache Maven](https://maven.apache.org/download.cgi)
- **Git** - For version control

### Step 1: Clone/Download the Project
```bash
cd d:\Testing in AI
# Project is in DemoWebShopAutomation folder
```

### Step 2: Set Environment Variables
**Windows:**
```bash
# Set JAVA_HOME
setx JAVA_HOME "C:\Program Files\Java\jdk-11"

# Set MAVEN_HOME
setx MAVEN_HOME "C:\apache-maven-3.8.9"

# Add to PATH
setx PATH "%PATH%;%MAVEN_HOME%\bin"
```

**Mac/Linux:**
```bash
export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk-11.jdk/Contents/Home
export MAVEN_HOME=/opt/maven
export PATH=$PATH:$MAVEN_HOME/bin
```

### Step 3: Verify Installation
```bash
java -version
mvn -version
```

### Step 4: Install Dependencies
```bash
cd d:\Testing\ in\ AI\DemoWebShopAutomation
mvn clean install
```

## Configuration

### Update Configuration File
Edit `src/test/resources/config.properties`:
```properties
# Application URL
app.url=https://demowebshop.tricentis.com/

# Browser Type (chrome/firefox)
app.browser=chrome

# Timeouts (in seconds)
implicit.wait=10
explicit.wait=15
page.load.timeout=30

# Test Data Path
testdata.path=test-data/
testdata.filename=TestData.xlsx

# Screenshot and Report Paths
screenshot.path=screenshots/
extent.report.path=reports/
```

### Create Excel Test Data
Create `test-data/TestData.xlsx` with sheets:
- **Registration** - Headers: Gender, FirstName, LastName, Email, Password, Day, Month, Year
- **Login** - Headers: Email, Password, RememberMe
- **ProductSearch** - Headers: ProductName, Quantity
- **Checkout** - Headers: FirstName, LastName, Email, Company, Country, State, City, Address, ZipCode, Phone

## Running Tests

### Run All Tests
```bash
mvn clean test
```

### Run Specific Test Class
```bash
mvn test -Dtest=RegistrationTest
mvn test -Dtest=LoginTest
mvn test -Dtest=ProductSearchTest
mvn test -Dtest=ShoppingCartTest
mvn test -Dtest=CheckoutTest
mvn test -Dtest=EndToEndPurchaseWorkflowTest
```

### Run Specific Test Method
```bash
mvn test -Dtest=RegistrationTest#testValidUserRegistration
```

### Run with Custom Configuration
```bash
mvn test -Dbrowser=firefox
```

### Run the Critical User Story Suite
```bash
mvn test -Dtest.suite=src/test/resources/testng-critical.xml
```

### Run Tests in Parallel
```bash
mvn test -DthreadCount=3
```

## Test Cases

### Registration Tests (4 test cases)
1. ✅ Valid user registration
2. ✅ Registration with existing email
3. ✅ Registration with mismatched passwords
4. ✅ Registration page elements validation

### Login Tests (5 test cases)
1. ✅ Valid user login
2. ✅ Login with invalid email
3. ✅ Login with invalid password
4. ✅ Login and logout
5. ✅ Login with remember me

### Product Search Tests (5 test cases)
1. ✅ Search product by keyword
2. ✅ Verify product list display
3. ✅ Click on product and view details
4. ✅ Search with empty keyword
5. ✅ Search with special characters

### Shopping Cart Tests (6 test cases)
1. ✅ Add single product to cart
2. ✅ Add multiple products to cart
3. ✅ Update product quantity in cart
4. ✅ Remove product from cart
5. ✅ Verify cart total calculation
6. ✅ Empty cart validation

### Checkout Tests (4 test cases)
1. ✅ Proceed to checkout from cart
2. ✅ Enter billing address
3. ✅ Select shipping method
4. ✅ Select payment method

### End-to-End Workflow Tests (3 test cases)
1. ✅ Complete purchase workflow for new user
2. ✅ Complete purchase workflow for existing user
3. ✅ Purchase multiple products

**Total: 27 comprehensive test cases**

## Test Reports

### Extent Reports
After test execution, open the HTML report:
```
reports/ExtentReport_YYYY-MM-DD_HH-mm-ss.html
```

The report includes:
- Test execution summary
- Pass/Fail statistics
- Screenshots on failure
- Detailed test logs
- System information

### Console Logs
Logs are generated in:
```
logs/automation.log
```

### Screenshots
Failed test screenshots:
```
screenshots/FAIL_*.png
```

Passed test screenshots:
```
screenshots/PASS_*.png
```

## Framework Architecture

### Page Object Model (POM)
Each page has:
- Locators (XPath, ID, CSS Selector)
- Page-specific methods
- Interaction methods

### Hybrid Framework Approach
- **POM** - Page encapsulation
- **Data-Driven** - External test data (Excel)
- **Keyword-Driven** - Business logic in methods
- **Modular** - Reusable components

### Best Practices Implemented
✅ Separation of concerns
✅ Reusable page objects
✅ Proper wait strategies (Implicit + Explicit)
✅ Comprehensive logging
✅ Screenshot on failure
✅ Data-driven testing with Excel
✅ Extent Reports integration
✅ Centralized configuration
✅ Exception handling
✅ Base classes for inheritance

## Logging

### Log Levels
- **INFO** - General information about test flow
- **DEBUG** - Detailed information for debugging
- **WARN** - Warning messages
- **ERROR** - Error messages with exception details
- **FATAL** - Critical errors

### Log Configuration
Edit `src/main/resources/log4j2.xml` to customize:
- Log file location
- Log format
- Log level
- Rolling policy

## Utilities

### LoggerUtil
```java
LoggerUtil.info("Test message");
LoggerUtil.debug("Debug message");
LoggerUtil.error("Error message", exception);
```

### ScreenshotUtil
```java
String screenshotPath = ScreenshotUtil.captureScreenshot(driver, "testName");
```

### ExcelUtil
```java
ExcelUtil excel = new ExcelUtil("filePath", "sheetName");
String cellValue = excel.getCellValue(0, 0);
Map<String, String> rowData = excel.getRowData(1);
```

### WaitUtil
```java
WaitUtil wait = new WaitUtil(driver);
wait.waitForElementToBeClickable(locator);
wait.waitForElementToBeVisible(locator);
wait.waitForPageTitle("Expected Title");
```

### ExtentReportManager
```java
ExtentReportManager.logTestPass(extentTest, "Test passed");
ExtentReportManager.logTestFail(extentTest, "Test failed");
ExtentReportManager.addScreenshot(extentTest, screenshotPath);
```

## Troubleshooting

### Common Issues

**Issue 1: Tests fail due to element not found**
- Solution: Verify XPath selectors are correct
- Update locators in page objects if UI changes

**Issue 2: WebDriver not initialized**
- Solution: Ensure WebDriver Manager dependency is installed
- Check browser driver is available

**Issue 3: Excel file not found**
- Solution: Create TestData.xlsx in test-data folder
- Verify file path in config.properties

**Issue 4: Screenshots not captured**
- Solution: Verify screenshots folder exists
- Check write permissions

**Issue 5: Extent Report not generated**
- Solution: Verify reports folder exists
- Check disk space

## Performance Tips

1. **Parallel Execution** - Run tests in parallel (modify testng.xml)
2. **Headless Mode** - Add headless option for faster execution
3. **Reduce Wait Times** - Adjust timeouts for faster execution
4. **Selective Screenshots** - Disable screenshots for passed tests

## CI/CD Integration

### Jenkins Pipeline Example
```groovy
pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
        stage('Report') {
            steps {
                publishHTML([
                    reportDir: 'reports',
                    reportFiles: 'ExtentReport.html',
                    reportName: 'Test Report'
                ])
            }
        }
    }
}
```

## Maintenance

### Update Locators
If UI elements change, update in corresponding page class:
```java
private By newLocator = By.xpath("//new/xpath");
```

### Add New Test Cases
1. Create new test method in appropriate test class
2. Use existing page objects and utilities
3. Follow naming convention: test*()
4. Add @Test annotation with description

### Add New Page Objects
1. Create new class extending BasePage
2. Define locators as private By variables
3. Add page-specific methods
4. Add validation method

## Documentation

### Code Comments
- Class level: Purpose of the class
- Method level: What the method does
- Complex logic: Why it's implemented this way

### JavaDoc
Generate JavaDoc:
```bash
mvn javadoc:javadoc
```

## Support & Contribution

For issues, enhancements, or contributions:
1. Report issues in detail with logs
2. Provide screenshots if applicable
3. Suggest improvements with rationale

## License
This framework is provided as-is for automation testing purposes.

## Version History

**v1.0.0** (Current)
- Initial framework release
- 27 comprehensive test cases
- Full E2E workflow coverage
- Extent Reports integration
- Log4j logging
- Data-driven testing
- Screenshot capture
- Cross-browser support

---

**Happy Testing! 🚀**

For more details, refer to specific utility classes and page object implementations.
