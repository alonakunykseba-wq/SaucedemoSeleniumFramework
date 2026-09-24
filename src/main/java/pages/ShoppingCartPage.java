package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class ShoppingCartPage extends BaseProductPage {

    private final By checkoutSelector = By.id("checkout");
    private final By productNameSelector = By.cssSelector("[data-test ='inventory-item-name']");

    public ShoppingCartPage(WebDriver driver) {
        super(driver);
    }

    public List<String> getProductNames() {
        return getTexts(productNameSelector);
    }

    public CheckoutInformationPage checkout() {
        click(checkoutSelector);
        return new CheckoutInformationPage(driver).waitForPageLoad();
    }
}

