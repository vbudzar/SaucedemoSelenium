package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage extends BaseTest {

    public String expectedURL = "https://www.saucedemo.com/checkout-complete.html";

    public CheckoutPage() {
        PageFactory.initElements(driver, this);
    }
    @FindBy(id = "first-name")
    public WebElement firstNameField;

    @FindBy(id = "last-name")
    public WebElement lastNameField;

    @FindBy(id = "postal-code")
    public WebElement postalCodeField;

    @FindBy(id = "continue")
    public WebElement continueButton;

    @FindBy(id = "checkout_summary_container")
    public WebElement checkoutSummaryInfo;

    @FindBy(id = "finish")
    public WebElement finishButton;

    @FindBy(id = "checkout_complete_container")
    public WebElement successfulCheckoutMessage;

    @FindBy(id = "back-to-products")
    public WebElement backHomeButton;

    @FindBy(css = ".error-message-container.error")
    public WebElement errorMessage;

    public void insertFirstName(String firstName) {
        firstNameField.clear();
        firstNameField.sendKeys(firstName);
    }
    public void insertLastName(String lastName) {
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
    }
    public void insertPostalCode(String postalCode) {
        postalCodeField.clear();
        postalCodeField.sendKeys(postalCode);
    }
    public void clickContinueButton() {
        continueButton.click();
    }
    public void clickFinishButton() {
        finishButton.click();
    }
}
