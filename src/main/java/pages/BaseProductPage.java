package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class BaseProductPage extends BasePage {
    public BaseProductPage(WebDriver driver) {
        super(driver);
    }

    protected final By productPriceSelector = By.cssSelector("div[data-test='inventory-item-price']");
    protected final By productNameSelector = By.cssSelector("div[data-test='inventory-item-name']");
    protected final By productDescriptionSelector = By.cssSelector("div[data-test='inventory-item-description']");


    public List<String> getProductPricesWithCurrency() {
        return getTexts(productPriceSelector);
    }

    public List<Double> getProductPrices() {
        return getProductPricesWithCurrency().stream()
                .map(price -> Double.parseDouble(price.replace("$", "")))
                .toList();
    }
}
