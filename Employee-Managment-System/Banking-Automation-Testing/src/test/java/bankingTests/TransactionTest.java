
package bankingTests;

import base.BaseClass;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.TransactionPage;
import utils.ConfigReader;

public class TransactionTest extends BaseClass {

    @Test
    public void openMiniStatementForm_afterValidLogin() {
        String baseUrl = ConfigReader.get("baseUrl");
        String user    = ConfigReader.get("username");
        String pass    = ConfigReader.get("password");

        new LoginPage(driver).open(baseUrl).enterUsername(user).enterPassword(pass).clickLogin();

        TransactionPage tp = new TransactionPage(driver);
        tp.openMiniStatement();
        Assert.assertTrue(tp.isMiniStatementFormLoaded(), "Mini Statement form not loaded");
        System.out.println("✅ Mini Statement form loaded.");
    }

    @Test
    public void miniStatement_invalidAccount_shouldShowAlert() {
        String baseUrl = ConfigReader.get("baseUrl");
        String user    = ConfigReader.get("username");
        String pass    = ConfigReader.get("password");

        new LoginPage(driver).open(baseUrl).enterUsername(user).enterPassword(pass).clickLogin();

        TransactionPage tp = new TransactionPage(driver);
        tp.openMiniStatement();
        Assert.assertTrue(tp.isMiniStatementFormLoaded(), "Mini Statement form not loaded");

        tp.enterAccount("999999"); // invalid
        tp.submit();

        String alert = acceptAlertIfPresent();
        Assert.assertNotNull(alert, "Expected alert for invalid account but none appeared");
        System.out.println("⚠️ Mini Statement invalid alert: " + alert);
    }
}
