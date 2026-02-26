
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage {

    private final WebDriver driver;

    // Note: Page shows "Manger Id" (typo on site), not "Manager Id"
    private final By managerID = By.xpath("//td[contains(text(),'Manger Id')]");
    private final By fundTransferLink = By.linkText("Fund Transfer");
    private final By logoutLink = By.linkText("Log out");

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isManagerIDDisplayed() {
        return driver.findElement(managerID).isDisplayed();
    }

    // Alias for convenience (used by some tests)
    public boolean isManagerIdVisible() {
        return isManagerIDDisplayed();
    }

    public void clickFundTransfer() {
        driver.findElement(fundTransferLink).click();
    }

    public void clickLogout() {
        driver.findElement(logoutLink).click();
    }
}
