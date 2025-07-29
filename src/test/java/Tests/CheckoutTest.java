package Tests;

import Base.BaseTest;
import Base.ExcelReader;
import Pages.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class CheckoutTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp() throws IOException {

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--disable-password-manager-reauthentication");
        options.addArguments("--disable-save-password-bubble");
        options.addArguments("--disable-features=PasswordLeakDetectionEnabled");
        options.addArguments("--password-store=basic");
        options.addArguments("--incognito");
        driver = new ChromeDriver(options);

//        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://www.saucedemo.com/");
        loginPage = new LoginPage();
        productsPage = new ProductsPage();
        cartPage = new CartPage();
        checkoutPage = new CheckoutPage();
        productDetailsPage = new ProductDetailsPage();
        excelReader = new ExcelReader("C:\\Users\\Korisnik\\Downloads\\SwagLabs_users.xlsx");

        String username = excelReader.getStringData("Sheet1", 1, 0);
        String password = excelReader.getStringData("Sheet1", 1, 1);
        loginPage.insertUsername(username);
        loginPage.insertPassword(password);
        loginPage.clickLoginButton();
        productsPage.addProductToCart();
        productsPage.goToCart();
    }
    @Test(priority = 1)
    public void userCanCheckout() {
        cartPage.goToCheckout();
        checkoutPage.insertFirstName("Vladimir");
        checkoutPage.insertLastName("Budzar");
        checkoutPage.insertPostalCode("23000");
        checkoutPage.clickContinueButton();
        checkoutPage.clickFinishButton();
        Assert.assertEquals(driver.getCurrentUrl(), checkoutPage.expectedURL);
        Assert.assertTrue(checkoutPage.successfulCheckoutMessage.isDisplayed());
        Assert.assertTrue(checkoutPage.backHomeButton.isDisplayed());
    }
    @Test(priority = 2)
    public void userCannotCheckoutWithEmptyFirstNameField() {
        cartPage.goToCheckout();
        checkoutPage.insertFirstName("");
        checkoutPage.insertLastName("Budzar");
        checkoutPage.insertPostalCode("23000");
        checkoutPage.clickContinueButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-step-one.html");
        Assert.assertTrue(checkoutPage.errorMessage.getText().contains("First Name is required"));
    }
    @Test(priority = 3)
    public void userCannotCheckoutWithEmptyLastNameField() {
        cartPage.goToCheckout();
        checkoutPage.insertFirstName("Vladimir");
        checkoutPage.insertLastName("");
        checkoutPage.insertPostalCode("23000");
        checkoutPage.clickContinueButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-step-one.html");
        Assert.assertTrue(checkoutPage.errorMessage.getText().contains("Last Name is required"));
    }
    @Test(priority = 4)
    public void userCannotCheckoutWithEmptyPostalCodeField() {
        cartPage.goToCheckout();
        checkoutPage.insertFirstName("Vladimir");
        checkoutPage.insertLastName("Budzar");
        checkoutPage.insertPostalCode("");
        checkoutPage.clickContinueButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-step-one.html");
        Assert.assertTrue(checkoutPage.errorMessage.getText().contains("Postal Code is required"));
    }
    @Test(priority = 5)
    public void userCannotCheckoutWithAllEmptyFields() {
        cartPage.goToCheckout();
        checkoutPage.insertFirstName("");
        checkoutPage.insertLastName("");
        checkoutPage.insertPostalCode("");
        checkoutPage.clickContinueButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-step-one.html");
        Assert.assertTrue(checkoutPage.errorMessage.getText().contains("First Name is required"));
    }
}
