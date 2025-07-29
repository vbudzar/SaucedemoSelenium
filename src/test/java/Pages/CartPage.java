package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class CartPage extends BaseTest {

    public String expectedURL = "https://www.saucedemo.com/cart.html";
    boolean isPresent = false;

    public CartPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".btn.btn_secondary.btn_small.cart_button")
    public WebElement removeButtonCartPage;

    @FindBy(id = "checkout")
    public WebElement checkoutButton;

    public void removeProductFromCartPage() {
        removeButtonCartPage.click();
    }
    public void assertProductIsNotPresent() {
        try {
            isPresent = removeButtonCartPage.isDisplayed();
        } catch (Exception e) {

        }
        Assert.assertFalse(isPresent);
    }
    public void goToCheckout() {
        checkoutButton.click();
    }

}

