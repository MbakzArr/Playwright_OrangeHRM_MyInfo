package tests;

import base.BaseTest;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    @Test(groups = {"smoke"},
          description = "Verify login works and dashboard is visible")
    public void testValidLogin() {
        extentTest.get().log(Status.INFO,
                "Verifying dashboard is visible after login");

        LoginPage loginPage = new LoginPage(page);

        Assert.assertTrue(
                loginPage.isDashboardVisible(),
                "Dashboard should be visible after login");

        extentTest.get().log(Status.PASS,
                "Login verified - dashboard is visible");
        System.out.println("Login test passed");
    }
}
