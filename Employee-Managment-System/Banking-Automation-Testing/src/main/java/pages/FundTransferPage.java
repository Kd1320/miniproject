
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FundTransferPage {

    private final WebDriver driver;

    private final By fundTransferLink = By.linkText("Fund Transfer");
    private final By payer = By.name("payersaccount");
    private final By payee = By.name("payeeaccount");
    private final By amount = By.name("ammount");
    private final By desc = By.name("desc");
    private final By submit = By.name("AccSubmit");

    // Optional: success block often shows this heading when real accounts are used
    private final By transferDetailsHeading = By.xpath("//p[text()='Fund Transfer Details']");

    public FundTransferPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openFundTransfer() {
        driver.findElement(fundTransferLink).click();
    }

    public boolean isFormLoaded() {
        return driver.findElement(payer).isDisplayed()
                && driver.findElement(payee).isDisplayed()
                && driver.findElement(amount).isDisplayed()
                && driver.findElement(desc).isDisplayed();
    }

    public void transfer(String payerAcc, String payeeAcc, String amt, String description) {
        driver.findElement(payer).clear();  driver.findElement(payer).sendKeys(payerAcc);
        driver.findElement(payee).clear();  driver.findElement(payee).sendKeys(payeeAcc);
        driver.findElement(amount).clear(); driver.findElement(amount).sendKeys(amt);
        driver.findElement(desc).clear();   driver.findElement(desc).sendKeys(description);
        driver.findElement(submit).click();
    }

    public boolean isTransferDetailsShown() {
        return !driver.findElements(transferDetailsHeading).isEmpty()
                && driver.findElement(transferDetailsHeading).isDisplayed();
    }
}
