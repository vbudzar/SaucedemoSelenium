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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductSortingTest extends BaseTest {

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
    public void userCanSortByPriceLowestToHighest() {
        productsPage.sortBy("Price (low to high)");
        List<Double> sorted = productsPage.getProductPrices();
        List<Double> sortExpected = new ArrayList<>(sorted);
        Collections.sort(sortExpected);
        Assert.assertEquals(sorted, sortExpected);
    }
    @Test(priority = 2)
    public void userCanSortByPriceHighestToLowest() {
        productsPage.sortBy("Price (high to low)");
        List<Double> sorted = productsPage.getProductPrices();
        List<Double> sortExpected = new ArrayList<>(sorted);
        sortExpected.sort(Collections.reverseOrder());
        Assert.assertEquals(sorted, sortExpected);
    }
    @Test(priority = 3)
    public void userCanSortByNameAZ() {
        productsPage.sortBy("Name (A to Z)");
        List<String> sorted = productsPage.getProductNames();
        List<String> sortExpected = new ArrayList<>(sorted);
        Collections.sort(sortExpected);
        Assert.assertEquals(sorted, sortExpected);
    }
    @Test(priority = 4)
    public void userCanSortByNameZA() {
        productsPage.sortBy("Name (Z to A)");
        List<String> sorted = productsPage.getProductNames();
        List<String> sortExpected = new ArrayList<>(sorted);
        sortExpected.sort(Collections.reverseOrder());
        Assert.assertEquals(sorted, sortExpected);
    }
}

