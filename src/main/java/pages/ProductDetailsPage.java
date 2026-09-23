package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductDetailsPage extends BaseProductPage {

    public ProductDetailsPage(WebDriver driver) {
        super(driver);
    }

    public String getProductName() {
        return getText(productNameSelector);
    }

    public double getProductPrice() {
        return Double.parseDouble(getText(productPriceSelector).replace("$", ""));
    }
}
