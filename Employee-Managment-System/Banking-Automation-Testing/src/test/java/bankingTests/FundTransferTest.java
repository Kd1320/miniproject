
package bankingTests;

import base.BaseClass;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.FundTransferPage;
import pages.LoginPage;
import utils.ConfigReader;

public class FundTransferTest extends BaseClass {

    @Test
    public void openFundTransferForm_afterValidLogin() {
        String baseUrl = ConfigReader.get("baseUrl");
        String user    = ConfigReader.get("username");
        String pass    = ConfigReader.get("password");

        new LoginPage(driver).open(baseUrl).enterUsername(user).enterPassword(pass).clickLogin();

        FundTransferPage ft = new FundTransferPage(driver);
        ft.openFundTransfer();
        Assert.assertTrue(ft.isFormLoaded(), "Fund Transfer form not loaded");
        System.out.println("✅ Fund Transfer form loaded.");
    }

    @Test
    public void transferWithInvalidAccounts_shouldShowAlert() {
        String baseUrl = ConfigReader.get("baseUrl");
        String user    = ConfigReader.get("username");
        String pass    = ConfigReader.get("password");

        new LoginPage(driver).open(baseUrl).enterUsername(user).enterPassword(pass).clickLogin();

        FundTransferPage ft = new FundTransferPage(driver);
        ft.openFundTransfer();
        Assert.assertTrue(ft.isFormLoaded(), "Fund Transfer form not loaded");

        ft.transfer("111111", "222222", "5000", "invalid accounts");

        String alert = acceptAlertIfPresent();
        Assert.assertNotNull(alert, "Expected alert for invalid accounts, but none appeared");
        System.out.println("⚠️ Fund transfer invalid alert: " + alert);
    }
}
