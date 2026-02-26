
package bankingTests;

import base.BaseClass;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AccountsPage;
import pages.LoginPage;
import utils.ConfigReader;

public class AccountsTest extends BaseClass {

    @Test
    public void openBalanceEnquiryForm_afterValidLogin() {
        String baseUrl = ConfigReader.get("baseUrl");
        String user    = ConfigReader.get("username");
        String pass    = ConfigReader.get("password");

        new LoginPage(driver).open(baseUrl).enterUsername(user).enterPassword(pass).clickLogin();

        AccountsPage ap = new AccountsPage(driver);
        ap.openBalance();
        Assert.assertTrue(ap.isBalanceFormLoaded(), "Balance Enquiry form not loaded");
        System.out.println("✅ Balance Enquiry form loaded.");
    }

    @Test
    public void balanceEnquiry_invalidAccount_shouldShowAlert() {
        String baseUrl = ConfigReader.get("baseUrl");
        String user    = ConfigReader.get("username");
        String pass    = ConfigReader.get("password");

        new LoginPage(driver).open(baseUrl).enterUsername(user).enterPassword(pass).clickLogin();

        AccountsPage ap = new AccountsPage(driver);
        ap.openBalance();
        Assert.assertTrue(ap.isBalanceFormLoaded(), "Balance Enquiry form not loaded");

        ap.enterAccount("999999"); // invalid
        ap.submit();

        String alert = acceptAlertIfPresent();
        Assert.assertNotNull(alert, "Expected alert for invalid account but none appeared");
        System.out.println("⚠️ Balance Enquiry invalid alert: " + alert);
    }
}
