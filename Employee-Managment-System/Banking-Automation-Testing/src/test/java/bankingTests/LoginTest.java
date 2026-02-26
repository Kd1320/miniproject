
package bankingTests;

import base.BaseClass;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import utils.ConfigReader;

public class LoginTest extends BaseClass {

    @Test
    public void validLogin_shouldShowDashboard() {
        String baseUrl = ConfigReader.get("baseUrl");
        String user    = ConfigReader.get("username");
        String pass    = ConfigReader.get("password");

        new LoginPage(driver).open(baseUrl).enterUsername(user).enterPassword(pass).clickLogin();

        // If any alert pops up, fail (valid creds should not trigger alert)
        String alert = acceptAlertIfPresent(3);
        Assert.assertNull(alert, "Unexpected alert on valid login: " + alert);

        Assert.assertTrue(new DashboardPage(driver).isManagerIdVisible(),
                "Dashboard (Manger Id) not visible after valid login");
        System.out.println("✅ Login valid flow passed.");
    }


    @DataProvider
    public Object[][] invalidCreds() {
        return new Object[][] {
                {"wrongUser", "wrongPass", "invalid combo"},
                {"", "somepass", "blank user"},
                {"", "", "blank both"}   // keep this
        };
    }

    @Test(dataProvider = "invalidCreds")
    public void invalidLogin_shouldShowAlert(String user, String pass, String note) {
        String baseUrl = ConfigReader.get("baseUrl");

        new LoginPage(driver).open(baseUrl).enterUsername(user).enterPassword(pass).clickLogin();

        String alert = acceptAlertIfPresent(); // default 3s
        Assert.assertNotNull(alert, "Expected alert for invalid login (" + note + ") but none appeared");
        System.out.println("⚠️ Invalid login (" + note + ") alert: " + alert);
    }
}
