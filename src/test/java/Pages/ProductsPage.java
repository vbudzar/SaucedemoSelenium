package Pages;

import Base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ProductsPage extends BaseTest {
    public String expectedURL = "https://www.saucedemo.com/inventory.html";
    boolean isPresent = false;
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));

    public ProductsPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "shopping_cart_container")
    public WebElement cartButton;

    @FindBy(id = "react-burger-menu-btn")
    public WebElement burgerMenuButton;

    @FindBy(css = ".btn.btn_primary.btn_small.btn_inventory ")
    public WebElement addToCartButton;

    @FindBy(id = "logout_sidebar_link")
    public WebElement logoutButton;

    @FindBy(className = "inventory_item")
    public List<WebElement> productList;

    @FindBy(className = "shopping_cart_badge")
    public WebElement cartCountBadge;

    @FindBy(css = ".btn.btn_secondary.btn_small.btn_inventory")
    public WebElement removeButtonProductsPage;

    @FindBy(className = "inventory_item_name")
    public WebElement inventoryProduct;

    @FindBy(className = "product_sort_container")
    public WebElement dropdownSortMenu;

    @FindBy(className = "inventory_item_name")
    public List<WebElement> productNames;

    @FindBy(className = "inventory_item_price")
    public List<WebElement> productPrices;

    public void addProductToCart() {
        addToCartButton.click();
    }

    public void goToCart() {
        cartButton.click();
    }

    public void logout() {
        burgerMenuButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton));
        logoutButton.click();
    }

    public void addMultipleProductsToCart(int index) {
        WebElement product = productList.get(index);
        product.findElement(By.cssSelector(".btn.btn_primary.btn_small.btn_inventory ")).click();
    }

    public int getCartBadgeNumber() {
        return Integer.parseInt(cartButton.getText());
    }

    public void removeProductFromProductsPage() {
        removeButtonProductsPage.click();
    }

    public void assertRemoveButtonIsNotPresent() {
        try {
            isPresent = removeButtonProductsPage.isDisplayed();
        } catch (Exception e) {

        }
        Assert.assertFalse(isPresent);
    }
    public void clickFirstInventoryProduct() {
        inventoryProduct.click();
    }
    public List<String> getProductNames() {
        List<String> names = new ArrayList<>();
        for (WebElement element : productNames) {
            names.add(element.getText());
        }
        return names;
    }
    public List<Double> getProductPrices() {
        List<Double> prices = new ArrayList<>();
        for (WebElement element : productPrices) {
            String priceText = element.getText().replace("$", "");
            prices.add(Double.parseDouble(priceText));
        }
        return prices;
    }
    public void sortBy(String text) {
        Select select = new Select(dropdownSortMenu);
        select.selectByVisibleText(text);
    }
}

