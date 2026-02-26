
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private final WebDriver driver;

    private final By username = By.name("uid");
    private final By password = By.name("password");
    private final By loginBtn = By.name("btnLogin");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage open(String baseUrl) {
        driver.get(baseUrl);
        return this;
    }

    public LoginPage enterUsername(String user) {
        driver.findElement(username).sendKeys(user);
        return this;
    }

    public LoginPage enterPassword(String pass) {
        driver.findElement(password).sendKeys(pass);
        return this;
    }

    public void clickLogin() {
        driver.findElement(loginBtn).click();
    }
}
