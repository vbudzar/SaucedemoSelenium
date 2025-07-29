package Tests;

import Base.BaseTest;
import Base.ExcelReader;
import Pages.CartPage;
import Pages.LoginPage;
import Pages.ProductDetailsPage;
import Pages.ProductsPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class CartTest extends BaseTest {

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
        //driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://www.saucedemo.com/");
        loginPage = new LoginPage();
        productsPage = new ProductsPage();
        cartPage = new CartPage();
        productDetailsPage = new ProductDetailsPage();
        excelReader = new ExcelReader("C:\\Users\\Korisnik\\Downloads\\SwagLabs_users.xlsx");

        String username = excelReader.getStringData("Sheet1", 1, 0);
        String password = excelReader.getStringData("Sheet1", 1, 1);
        loginPage.insertUsername(username);
        loginPage.insertPassword(password);
        loginPage.clickLoginButton();
    }
    @Test(priority = 1)
    public void userCanAddProductToCart() {
        productsPage.addProductToCart();
        productsPage.goToCart();
        Assert.assertTrue(cartPage.removeButtonCartPage.isDisplayed());
        Assert.assertEquals(productsPage.cartCountBadge.getText(), "1");
        Assert.assertEquals(driver.getCurrentUrl(), cartPage.expectedURL);

    }
    @Test(priority = 2)
    public void userCanAddProductFromProductDetailsPage() {
        productsPage.clickFirstInventoryProduct();
        productDetailsPage.addProductToCart();
        Assert.assertTrue(productDetailsPage.removeButtonProductDetailsPage.isDisplayed());
        Assert.assertEquals(productsPage.cartCountBadge.getText(),"1");
    }
    @Test(priority = 3)
    public void userCanRemoveProductFromCart() {
        productsPage.addProductToCart();
        productsPage.goToCart();
        Assert.assertTrue(cartPage.removeButtonCartPage.isDisplayed());
        Assert.assertEquals(productsPage.cartCountBadge.getText(), "1");
        cartPage.removeProductFromCartPage();
        cartPage.assertProductIsNotPresent();
    }
    @Test(priority = 4)
    public void userCanRemoveProductFromProductsPage() {
        productsPage.addProductToCart();
        Assert.assertEquals(productsPage.cartCountBadge.getText(), "1");
        productsPage.removeProductFromProductsPage();
        productsPage.assertRemoveButtonIsNotPresent();
    }
    @Test(priority = 5)
    public void cartBadgeDisplaysCorrectNumber() {
        int itemsToAdd = 3;
        for (int i = 0; i < itemsToAdd; i++) {
            productsPage.addMultipleProductsToCart(i);
            int expectedCount = i + 1;
            int actualCount = productsPage.getCartBadgeNumber();
            Assert.assertEquals(actualCount, expectedCount);
        }
    }
}
