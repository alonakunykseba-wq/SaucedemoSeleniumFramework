package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    private final By usernameField = By.cssSelector("[data-test='username']");
    private final By passwordField = By.cssSelector("[data-test='password']");
    private final By loginButton = By.id("login-button");
    private final By errorMessage = By.cssSelector("[data-test='error']");

    public ProductsOverviewPage logInSuccessfully(String username, String password) {
        enterText(usernameField, username);
        enterText(passwordField, password);
        click(loginButton);
        return new ProductsOverviewPage(driver).waitForPageLoad();
    }

    public LoginPage loginWithFailure(String username, String password) {
        enterText(usernameField, username);
        enterText(passwordField, password);
        click(loginButton);
        return this;
    }

    public String getErrorText() {
        return getText(errorMessage);
    }

}
