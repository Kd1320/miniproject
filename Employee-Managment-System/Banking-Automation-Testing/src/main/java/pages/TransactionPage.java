
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TransactionPage {

    private final WebDriver driver;

    private final By miniStatement = By.linkText("Mini Statement");
    private final By accountNo     = By.name("accountno");
    private final By submitBtn     = By.name("AccSubmit");

    // Optional success heading if a valid account is used
    private final By miniStatementHeading = By.xpath("//p[text()='Last Five Transaction Details']");

    public TransactionPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openMiniStatement() {
        driver.findElement(miniStatement).click();
    }

    public boolean isMiniStatementFormLoaded() {
        return driver.findElement(accountNo).isDisplayed();
    }

    public void enterAccount(String acc) {
        driver.findElement(accountNo).clear();
        driver.findElement(accountNo).sendKeys(acc);
    }

    public void submit() {
        driver.findElement(submitBtn).click();
    }

    public boolean isMiniStatementShown() {
        return !driver.findElements(miniStatementHeading).isEmpty()
                && driver.findElement(miniStatementHeading).isDisplayed();
    }
}
