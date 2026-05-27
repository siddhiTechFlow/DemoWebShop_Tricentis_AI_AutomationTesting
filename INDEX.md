# Framework Index & Navigation Guide

## 📚 Complete Documentation Index

### Getting Started Documents
1. **[PROJECT_SUMMARY.md](PROJECT_SUMMARY.md)** ⭐ START HERE
   - Overview of complete delivery
   - What has been created
   - Quick navigation
   - Next steps

2. **[QUICKSTART.md](QUICKSTART.md)** ⚡ 5-Minute Setup
   - Fast setup instructions
   - Common commands
   - Basic troubleshooting
   - Key concepts

3. **[SETUP_INSTRUCTIONS.md](SETUP_INSTRUCTIONS.md)** 🔧 Detailed Setup
   - Step-by-step installation
   - Environment configuration
   - IDE setup (IntelliJ/VS Code)
   - Verification checklist

4. **[README.md](README.md)** 📖 Complete Documentation
   - Full framework overview
   - Technology stack
   - Architecture details
   - Advanced usage
   - CI/CD integration

5. **[TEST_DATA_GUIDE.md](TEST_DATA_GUIDE.md)** 📊 Excel Format Guide
   - Test data structure
   - Sheet-by-sheet format
   - Data examples
   - Validation guidelines

---

## 🗂️ Project Structure

### Source Code Organization

**Utility Classes** (`src/main/java/com/automation/utilities/`)
- LoggerUtil - Logging wrapper
- ScreenshotUtil - Screenshot capture
- ExcelUtil - Excel operations
- DriverManager - WebDriver management
- PropertyManager - Configuration management
- WaitUtil - Explicit waits
- ExtentReportManager - Test reporting
- DataProviderUtil - Data provider

**Page Objects** (`src/main/java/com/automation/pages/`)
- BasePage - Base class with common methods
- HomePage - Home page operations
- LoginPage - Login operations
- RegisterPage - Registration operations
- ProductPage - Product search operations
- ProductDetailsPage - Product details operations
- ShoppingCartPage - Cart operations
- CheckoutPage - Checkout operations
- OrderConfirmationPage - Order confirmation operations

**Test Classes** (`src/test/java/com/automation/tests/`)
- RegistrationTest - 4 test cases
- LoginTest - 5 test cases
- ProductSearchTest - 5 test cases
- ShoppingCartTest - 6 test cases
- CheckoutTest - 4 test cases
- EndToEndPurchaseWorkflowTest - 3 test cases

**Base Test** (`src/test/java/com/automation/base/`)
- BaseTest - Setup/teardown for all tests

### Configuration & Resources
- **pom.xml** - Maven dependencies
- **src/main/resources/log4j2.xml** - Logging configuration
- **src/test/resources/config.properties** - Application configuration
- **src/test/resources/testng.xml** - TestNG suite configuration

### Test Data & Output
- **test-data/** - Excel test data location
- **screenshots/** - Failure screenshots
- **reports/** - Extent HTML reports
- **logs/** - Log files

---

## 📋 Document Navigation by Task

### For Initial Setup
```
1. Read: PROJECT_SUMMARY.md
   ↓
2. Read: QUICKSTART.md
   ↓
3. Follow: SETUP_INSTRUCTIONS.md
   ↓
4. Create: TestData.xlsx (see TEST_DATA_GUIDE.md)
   ↓
5. Run: mvn clean test
```

### For Running Tests
```
1. Quick Reference: QUICKSTART.md (Common Commands)
   ↓
2. Run Tests: mvn clean test
   ↓
3. View Results: reports/ExtentReport_*.html
   ↓
4. Review Logs: logs/automation.log
```

### For Writing New Tests
```
1. Review: README.md (Architecture section)
   ↓
2. Study: Existing test class (e.g., LoginTest.java)
   ↓
3. Review: Corresponding page object (e.g., LoginPage.java)
   ↓
4. Create: New test method in test class
   ↓
5. Use: Existing page objects and utilities
```

### For Customization
```
1. Update URLs: config.properties
   ↓
2. Update Locators: Page object classes
   ↓
3. Update Test Data: TestData.xlsx
   ↓
4. Add New Tests: Test classes
   ↓
5. Run: mvn clean test
```

### For CI/CD Integration
```
1. Read: README.md (CI/CD Integration section)
   ↓
2. Create: Jenkinsfile or CI config
   ↓
3. Set up: Automated test execution
   ↓
4. Configure: Report publishing
```

---

## 🎯 Test Cases Quick Reference

### Registration (4 Tests)
```
RegistrationTest.java
├── testValidUserRegistration
├── testRegistrationWithExistingEmail
├── testRegistrationWithMismatchedPasswords
└── testRegistrationPageElementsValidation
```

### Login (5 Tests)
```
LoginTest.java
├── testValidUserLogin
├── testLoginWithInvalidEmail
├── testLoginWithInvalidPassword
├── testLoginAndLogout
└── testLoginWithRememberMe
```

### Product Search (5 Tests)
```
ProductSearchTest.java
├── testSearchProductByKeyword
├── testProductListDisplay
├── testViewProductDetails
├── testSearchWithEmptyKeyword
└── testSearchWithSpecialCharacters
```

### Shopping Cart (6 Tests)
```
ShoppingCartTest.java
├── testAddSingleProductToCart
├── testAddMultipleProductsToCart
├── testUpdateProductQuantityInCart
├── testRemoveProductFromCart
├── testCartTotalCalculation
└── testEmptyCartValidation
```

### Checkout (4 Tests)
```
CheckoutTest.java
├── testProceedToCheckout
├── testEnterBillingAddress
├── testSelectShippingMethod
└── testSelectPaymentMethod
```

### End-to-End Workflows (3 Tests)
```
EndToEndPurchaseWorkflowTest.java
├── testCompleteNewUserPurchaseWorkflow
├── testCompleteExistingUserPurchaseWorkflow
└── testMultipleProductsPurchase
```

**Total: 27 Test Cases**

---

## 🔧 Common Commands Reference

### Setup & Build
```bash
mvn clean install              # Install dependencies
mvn clean build                # Clean and build
mvn compile                    # Compile code
```

### Run Tests
```bash
mvn clean test                 # Run all tests
mvn test -Dtest=LoginTest      # Run specific class
mvn test -Dtest=LoginTest#testValidUserLogin  # Run specific method
```

### Advanced Execution
```bash
mvn test -Dbrowser=firefox    # Run with Firefox
mvn test -DthreadCount=3      # Parallel execution
mvn test -X                    # Verbose output
mvn test -DskipTests          # Skip tests
```

### Generate Reports
```bash
mvn javadoc:javadoc           # Generate JavaDoc
mvn surefire-report:report    # Generate test report
```

---

## 📁 File Organization

```
DemoWebShopAutomation/
│
├── 📄 Documentation Files
│   ├── README.md                    (Complete documentation)
│   ├── QUICKSTART.md               (5-minute guide)
│   ├── SETUP_INSTRUCTIONS.md       (Detailed setup)
│   ├── TEST_DATA_GUIDE.md          (Excel format)
│   ├── PROJECT_SUMMARY.md          (Delivery summary)
│   ├── INDEX.md                    (This file)
│   └── .gitignore                  (Git ignore rules)
│
├── 🔧 Configuration Files
│   ├── pom.xml                     (Maven config)
│   ├── src/main/resources/log4j2.xml
│   ├── src/test/resources/config.properties
│   └── src/test/resources/testng.xml
│
├── 💻 Source Code
│   ├── src/main/java/com/automation/
│   │   ├── base/
│   │   ├── pages/
│   │   └── utilities/
│   └── src/test/java/com/automation/
│       ├── base/
│       └── tests/
│
├── 📊 Test Data & Output
│   ├── test-data/          (Create: TestData.xlsx)
│   ├── screenshots/        (Auto-created)
│   ├── reports/            (Auto-created)
│   └── logs/               (Auto-created)
│
└── 📦 Build Output (Auto-created)
    └── target/
```

---

## ✅ Quick Verification Checklist

### Before Running Tests
- [ ] Java 11+ installed and JAVA_HOME set
- [ ] Maven 3.8.9+ installed and MAVEN_HOME set
- [ ] Project extracted to `d:\Testing in AI\DemoWebShopAutomation`
- [ ] `mvn clean install` completed successfully
- [ ] TestData.xlsx created in test-data folder
- [ ] All 4 sheets added to TestData.xlsx
- [ ] config.properties verified
- [ ] Application URL accessible

### After Running Tests
- [ ] Test execution completed
- [ ] Report generated in reports/
- [ ] Screenshots in screenshots/ (if failures)
- [ ] Logs written to logs/automation.log
- [ ] No build errors in console

---

## 🆘 Troubleshooting Quick Links

**Setup Issues** → See SETUP_INSTRUCTIONS.md (Troubleshooting Section)
**Test Data Issues** → See TEST_DATA_GUIDE.md (Troubleshooting Section)
**General Issues** → See README.md (Troubleshooting Section)
**Command Issues** → See QUICKSTART.md (Common Commands)
**Execution Logs** → Check logs/automation.log

---

## 📞 Support Resources

### For Each Topic
| Topic | Resource | Location |
|-------|----------|----------|
| Getting Started | PROJECT_SUMMARY.md | Root |
| Quick Start | QUICKSTART.md | Root |
| Detailed Setup | SETUP_INSTRUCTIONS.md | Root |
| Framework Details | README.md | Root |
| Test Data Format | TEST_DATA_GUIDE.md | Root |
| Test Code | src/test/java/com/automation/tests/ | Source |
| Page Objects | src/main/java/com/automation/pages/ | Source |
| Utilities | src/main/java/com/automation/utilities/ | Source |

---

## 🚀 Getting Started in 3 Steps

### Step 1: Read Documentation (5 min)
```
Open: PROJECT_SUMMARY.md
Understand: What has been delivered
```

### Step 2: Setup Environment (10 min)
```
Follow: SETUP_INSTRUCTIONS.md
Result: mvn clean install succeeds
```

### Step 3: Run First Test (5 min)
```
Command: mvn test -Dtest=LoginTest#testValidUserLogin
Result: Test passes & report generated
```

**Total Time: ~20 minutes**

---

## 📝 Documentation Maintenance

### When to Update Documentation
- Framework changes made
- New test cases added
- Configuration modified
- Issues discovered and resolved
- Best practices updated

### Where to Document
- **Code Comments** - Complex logic in source code
- **README.md** - General framework information
- **Specific Guides** - Feature-specific documentation
- **Logs** - Execution details for debugging

---

## 🎓 Learning Path

### For Test Automation Beginners
1. Read QUICKSTART.md
2. Review README.md (Overview section)
3. Run sample tests
4. Review LoginTest.java
5. Review LoginPage.java
6. Create simple test

### For Intermediate Users
1. Read complete README.md
2. Study all page objects
3. Review utility classes
4. Modify page object locators
5. Add new test cases

### For Advanced Users
1. Extend framework with new features
2. Add parallel execution
3. Integrate with CI/CD
4. Create custom utilities
5. Optimize performance

---

## 📊 Framework Statistics

```
Total Java Files:        23
- Utility Classes:        8
- Page Objects:           9
- Test Classes:           6

Total Test Cases:        27
- Registration:           4
- Login:                  5
- Product Search:         5
- Shopping Cart:          6
- Checkout:               4
- End-to-End:             3

Lines of Code:        ~5,000+
Documentation Pages:      6
Dependencies:            10+
```

---

## 🎯 Next Actions

### Immediate (Today)
- [ ] Read PROJECT_SUMMARY.md
- [ ] Follow SETUP_INSTRUCTIONS.md
- [ ] Create TestData.xlsx

### Short Term (This Week)
- [ ] Run all 27 test cases
- [ ] Review Extent Reports
- [ ] Verify test coverage
- [ ] Customize for your environment

### Medium Term (This Month)
- [ ] Add new test cases
- [ ] Integrate with CI/CD
- [ ] Set up automated execution
- [ ] Fine-tune framework

### Long Term (Ongoing)
- [ ] Maintain test data
- [ ] Update locators if UI changes
- [ ] Optimize test execution
- [ ] Expand test coverage

---

## 📞 Quick Help

**Question: Where do I start?**
→ Answer: Read PROJECT_SUMMARY.md first

**Question: How do I run tests?**
→ Answer: See QUICKSTART.md (Common Commands section)

**Question: How do I create test data?**
→ Answer: See TEST_DATA_GUIDE.md

**Question: How do I add new tests?**
→ Answer: See README.md (Adding New Tests section)

**Question: Where are the results?**
→ Answer: reports/ folder → ExtentReport_*.html

**Question: Where are the logs?**
→ Answer: logs/ folder → automation.log

**Question: How do I debug a failure?**
→ Answer: Check screenshots/ folder + logs/automation.log

---

**Framework Ready for Use! 🎉**

Start with [PROJECT_SUMMARY.md](PROJECT_SUMMARY.md) →
Then follow [SETUP_INSTRUCTIONS.md](SETUP_INSTRUCTIONS.md) →
Finally execute [QUICKSTART.md](QUICKSTART.md) commands

Happy Testing! 🚀

---

**Last Updated:** May 26, 2026
**Status:** ✅ Complete & Ready for Use
