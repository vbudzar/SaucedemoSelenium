package Tests;

import Base.BaseTest;
import Base.ExcelReader;
import Pages.LoginPage;
import Pages.ProductsPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTest extends BaseTest {

    private static final Logger log = LoggerFactory.getLogger(LoginTest.class);

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
        excelReader = new ExcelReader("C:\\Users\\Korisnik\\Downloads\\SwagLabs_users.xlsx");
    }

    @Test(priority = 1)
    public void standardUserCanLogIn() {
        loginPage.insertUsername("standard_user");
        loginPage.insertPassword("secret_sauce");
        loginPage.clickLoginButton();
        Assert.assertEquals(driver.getCurrentUrl(), productsPage.expectedURL);
        Assert.assertTrue(productsPage.cartButton.isDisplayed());
        Assert.assertTrue(productsPage.burgerMenuButton.isDisplayed());
    }

    @Test(priority = 2)
    public void standardUserCanLogInDDT() {
        String username = excelReader.getStringData("Sheet1", 1, 0);
        String password = excelReader.getStringData("Sheet1", 1, 1);
        loginPage.insertUsername(username);
        loginPage.insertPassword(password);
        loginPage.clickLoginButton();
        Assert.assertEquals(driver.getCurrentUrl(), productsPage.expectedURL);
        Assert.assertTrue(productsPage.cartButton.isDisplayed());
        Assert.assertTrue(productsPage.burgerMenuButton.isDisplayed());
    }

    @Test(priority = 3)
    public void usersCanLogInAndLogOutDDT() {
        int lastRow = excelReader.getLastRow("Sheet1");

        for (int i = 1; i <= lastRow; i++) {
            String username = excelReader.getStringData("Sheet1", i, 0);
            String password = excelReader.getStringData("Sheet1", i, 1);
            loginPage.insertUsername(username);
            loginPage.insertPassword(password);
            loginPage.clickLoginButton();
            Assert.assertTrue(productsPage.cartButton.isDisplayed());
            Assert.assertTrue(productsPage.burgerMenuButton.isDisplayed());
            productsPage.logout();

        }
    }
    @Test(priority = 4)
    public void lockedOutUserCannotLogIn() {
        loginPage.insertUsername("locked_out_user");
        loginPage.insertPassword("secret_sauce");
        loginPage.clickLoginButton();
        Assert.assertTrue(loginPage.errorMessage.getText().contains("Sorry, this user has been locked out."));
        Assert.assertTrue(loginPage.loginButton.isDisplayed());
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");

    }
    @Test(priority = 5)
    public void userCannotLogInWithInvalidUsername() {
        loginPage.insertUsername("invalid_username");
        loginPage.insertPassword("secret_sauce");
        loginPage.clickLoginButton();
        Assert.assertTrue(loginPage.errorMessage.getText().contains("Username and password do not match any user in this service"));
        Assert.assertTrue(loginPage.loginButton.isDisplayed());
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");

    }
    @Test(priority = 6)
    public void userCannotLogInWithInvalidPassword() {
        loginPage.insertUsername("standard_user");
        loginPage.insertPassword("invalid_password");
        loginPage.clickLoginButton();
        Assert.assertTrue(loginPage.errorMessage.getText().contains("Username and password do not match any user in this service"));
        Assert.assertTrue(loginPage.loginButton.isDisplayed());
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
    }
    @Test(priority = 7)
    public void userCannotLogInWithInvalidUsernameAndPassword() {
        loginPage.insertUsername("invalid_username");
        loginPage.insertPassword("invalid_password");
        loginPage.clickLoginButton();
        Assert.assertTrue(loginPage.errorMessage.getText().contains("Username and password do not match any user in this service"));
        Assert.assertTrue(loginPage.loginButton.isDisplayed());
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
    }
    @Test(priority = 8)
    public void userCannotLogInWithoutUsernameInput() {
        loginPage.insertUsername("");
        loginPage.insertPassword("secret_sauce");
        loginPage.clickLoginButton();
        Assert.assertTrue(loginPage.errorMessage.getText().contains("Username is required"));
        Assert.assertTrue(loginPage.loginButton.isDisplayed());
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
    }
    @Test(priority = 9)
    public void userCannotLogInWithoutPasswordInput() {
        loginPage.insertUsername("standard_user");
        loginPage.insertPassword("");
        loginPage.clickLoginButton();
        Assert.assertTrue(loginPage.errorMessage.getText().contains("Password is required"));
        Assert.assertTrue(loginPage.loginButton.isDisplayed());
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
    }
    @Test(priority = 10)
    public void userCannotLogInWithBothEmptyFields() {
        loginPage.insertUsername("");
        loginPage.insertPassword("");
        loginPage.clickLoginButton();
        Assert.assertTrue(loginPage.errorMessage.getText().contains("Username is required"));
        Assert.assertTrue(loginPage.loginButton.isDisplayed());
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
    }
}
