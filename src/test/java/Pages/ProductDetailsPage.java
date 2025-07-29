package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductDetailsPage extends BaseTest {

    public ProductDetailsPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "shopping_cart_container")
    public WebElement cartButton;

    @FindBy(id = "react-burger-menu-btn")
    public WebElement burgerMenuButton;

    @FindBy(css = ".btn.btn_primary.btn_small.btn_inventory ")
    public WebElement addToCartButton;


    @FindBy(className = "shopping_cart_badge")
    public WebElement cartCountBadge;

    @FindBy(css = ".btn.btn_secondary.btn_small.btn_inventory")
    public WebElement removeButtonProductDetailsPage;

    @FindBy(id = "back-to-products")
    public WebElement backToProductsButton;

    public void addProductToCart() {
        addToCartButton.click();
    }
}
