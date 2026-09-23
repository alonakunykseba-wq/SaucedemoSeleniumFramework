package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.*;
import java.util.stream.Collectors;

public class ProductsOverviewPage extends BaseProductPage {

    private final By titleSelector = By.cssSelector(".title");
    private final By sortingDropdownSelector = By.className("product_sort_container");
    private final By shoppingCartSelector = By.cssSelector("a[data-test='shopping-cart-link']");
    private final By shoppingCartBadgeSelector = By.cssSelector(".shopping_cart_badge");
    private final By removeButtonSelector = By.xpath("//button[text()='Remove']");
    private final By addButtonSelector = By.xpath("//button[text()='Add to cart']");
    private final By logoutButtonSelector = By.xpath("//a[text()='Logout']");
    private final By burgerButtonSelector = By.xpath("//button[text()='Open Menu']");

    public ProductsOverviewPage(WebDriver driver) {
        super(driver);
    }

    public String getPageTitle() {
        return getText(titleSelector);
    }

    public List<String> getProductNames() {
        return getTexts(productNameSelector);
    }

    public String getRandomProductName() {
        List<String> products = getProductNames();
        int randomIndex = new Random().nextInt(products.size());
        return products.get(randomIndex);
    }

    public ProductDetailsPage clickRandomProductLink(String randomProduct) {
        String randomProductSelector = String.format("//div[@data-test='inventory-item-name' and text()='%s']", randomProduct);
        click(By.xpath(randomProductSelector));
        return new ProductDetailsPage(driver).waitForPageLoad();
    }

    public  Double getProductPriceByName(String productName) {
        List<WebElement> productElementsList = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productDescriptionSelector));
        Map<String, Double>  productsMap=  productElementsList.stream()
                .collect(Collectors.toMap(
                        product -> product.findElement(productNameSelector).getText(),
                        product -> Double.parseDouble(
                                product.findElement(productPriceSelector).getText().replace("$", "")))
                );
        if (!productsMap.containsKey(productName)) {
            throw new NoSuchElementException("Product not found: " + productName);
        }
        return productsMap.get(productName);
    }

    public ProductsOverviewPage applySortingFilter(String sortingMethodName) {
        WebElement sortingDropdownElement = wait.until(ExpectedConditions.visibilityOfElementLocated(sortingDropdownSelector));
        Select dropdown = new Select(sortingDropdownElement);
        dropdown.selectByVisibleText(sortingMethodName);
        return this;
    }

    public ProductsOverviewPage addProductToTheCartByPrice(double price) {
        By addToCartLocator = By.xpath(
                String.format(Locale.US, "//div[@class='inventory_item_price' and contains(.,'$%.2f')]/following-sibling::button", price));
        click(addToCartLocator);
        return this;
    }

    public ProductsOverviewPage addProductsToTheCart(int amount) {
        for (int count = 0; count < amount; count++) {
            click(addButtonSelector);
        }
        return this;
    }

    public boolean areRemoveButtonsDisplayed() {
        return !driver.findElements(removeButtonSelector).isEmpty();
    }

    public ProductsOverviewPage removeProduct() {
        click(removeButtonSelector);
        return this;
    }

    public boolean isShoppingCartBadgeDisplayed() {
        return !driver.findElements(shoppingCartBadgeSelector).isEmpty();
    }

    public int getProductsAmountInTheCart() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(shoppingCartBadgeSelector));
        return Integer.parseInt(getText(shoppingCartBadgeSelector));
    }

    public ShoppingCartPage navigateToTheCart() {
        click(shoppingCartSelector);
        return new ShoppingCartPage(driver).waitForPageLoad();
    }

    public LoginPage submitLogout() {
        click(burgerButtonSelector);
        click(logoutButtonSelector);
        return new LoginPage(driver);
    }
}
