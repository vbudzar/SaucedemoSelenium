# 🧪 SauceDemo Selenium Test Suite
Automated UI tests for SauceDemo using Selenium WebDriver, Java, and TestNG. Designed following the Page Object Model (POM) pattern for clear structure and scalability.

## 📋 Features Tested
### ✅ Login Tests
- Standard user login (valid credentials)

- Invalid username/password

- Login with empty username/password

- Data-driven login with multiple users (DDT from Excel)

- Locked-out user validation

- Logout functionality

### 🛒 Cart Functionality
- Add/remove product from product and cart pages

- Cart badge number update

- Cart badge assertion

### 📦 Product Sorting
- Name (A-Z / Z-A)

- Price (low to high / high to low)

- Validated correctness of sorting via comparison

### 🧾 Checkout Process
- Fill in checkout form

- Finish checkout and validate success message

- Try to checkout without filling all fields

## 📁 Project Structure
- base/: Reusable code (e.g., ExcelReader, BaseTest)
  
- pages/: Page Object Model classes

- tests/: Test classes grouped by feature

## 🚀 How to Run
1. Clone the repo:

  ```bash
git clone https://github.com/yourusername/SaucedemoSelenium.git
cd SaucedemoSelenium

2. Import into IntelliJ or Eclipse as a Maven project.

3. Update dependencies with:

bash
mvn clean install

4. Run tests:

- From testng.xml

- Or directly from the test class

## ✅ Technologies Used
- Java

- Selenium WebDriver

- TestNG

- Apache POI (for Excel)

- Maven

- Page Object Model (POM)

## 📌 Lessons and QA Concepts Demonstrated
- Data-Driven Testing (DDT)

- Assertions (UI elements, sorting logic, cart status)

- DOM interaction via CSS selectors

- Clean separation of concerns (tests vs. locators)

- Reusability and maintainability with POM

## 🎨 Author

Vladimir Budzar
