package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class BaseProductPage extends BasePage {
    public BaseProductPage(WebDriver driver) {
        super(driver);
    }

    protected final By productPriceSelector = By.cssSelector(".inventory_item_price");
    protected final By productNameSelector = By.cssSelector(".inventory_item_name");
    protected final By productDescriptionSelector = By.cssSelector(".inventory_item_description");

    public List<String> getProductPricesWithCurrency() {
        return getTexts(productPriceSelector);
    }

    public List<Double> getProductPrices() {
        return getProductPricesWithCurrency().stream()
                .map(price -> Double.parseDouble(price.replace("$", "")))
                .toList();
    }
}
