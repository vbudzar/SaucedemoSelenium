package Base;

import Pages.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;

public class BaseTest {

    public static WebDriver driver;
    public static LoginPage loginPage;
    public static ProductsPage productsPage;
    public static ExcelReader excelReader;
    public static CartPage cartPage;
    public static CheckoutPage checkoutPage;
    public static ProductDetailsPage productDetailsPage;


    public void setUp() {

        WebDriverManager.chromedriver().setup();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
