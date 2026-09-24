package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutInformationPage extends BasePage {
    private final By firstNameFieldSelector = By.id("first-name");
    private final By lastNameFieldSelector = By.id("last-name");
    private final By postalCodeSelector = By.id("postal-code");
    private final By continueButtonSelector = By.id("continue");
    private final By errorSelector = By.cssSelector("[data-test = 'error']");

    public CheckoutInformationPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutInformationPage fillTheForm(String firstName, String lastName, String postalCode) {
        enterText(firstNameFieldSelector, firstName);
        enterText(lastNameFieldSelector, lastName);
        enterText(postalCodeSelector, postalCode);
        return this;
    }

    public CheckoutOverviewPage proceed() {
        click(continueButtonSelector);
        return new CheckoutOverviewPage(driver).waitForPageLoad();
    }

    public String getErrorText() {
        return getText(errorSelector);
    }
}
