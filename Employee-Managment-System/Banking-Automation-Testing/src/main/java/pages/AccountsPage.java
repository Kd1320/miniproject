
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountsPage {

    private final WebDriver driver;

    private final By balanceLink = By.linkText("Balance Enquiry");
    private final By accountNo   = By.name("accountno");
    private final By submitBtn   = By.name("AccSubmit");

    // Optional success heading if a valid account is used
    private final By balanceDetailsHeading = By.xpath("//p[text()='Balance Details']");

    public AccountsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openBalance() {
        driver.findElement(balanceLink).click();
    }

    public boolean isBalanceFormLoaded() {
        return driver.findElement(accountNo).isDisplayed();
    }

    public void enterAccount(String acc) {
        driver.findElement(accountNo).clear();
        driver.findElement(accountNo).sendKeys(acc);
    }

    public void submit() {
        driver.findElement(submitBtn).click();
    }

    public boolean isBalanceDetailsShown() {
        return !driver.findElements(balanceDetailsHeading).isEmpty()
                && driver.findElement(balanceDetailsHeading).isDisplayed();
    }
}
