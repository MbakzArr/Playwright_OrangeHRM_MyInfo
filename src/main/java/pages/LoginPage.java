package pages;

import base.BasePage;
import com.microsoft.playwright.Page;
import utils.LocatorReader;

public class LoginPage extends BasePage {

    public LoginPage(Page page) {
        super(page);
    }

    public void login(String username, String password) {
        fill(LocatorReader.get("login.username"), username);
        fill(LocatorReader.get("login.password"), password);
        click(LocatorReader.get("login.button"));
    }

    public boolean isDashboardVisible() {
        return isVisible(LocatorReader.get("login.dashboard"));
    }
}
