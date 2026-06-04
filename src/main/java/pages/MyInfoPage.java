package pages;

import base.BasePage;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import utils.LocatorReader;

public class MyInfoPage extends BasePage {

    public MyInfoPage(Page page) {
        super(page);
    }

    public void goToMyInfo() {
        click(LocatorReader.get("nav.myinfo"));

        // Wait for the firstName field to be fully loaded and visible
        // Using ATTACHED first then VISIBLE handles Vue.js lazy rendering
        page.waitForSelector(
                "input[name='firstName']",
                new Page.WaitForSelectorOptions()
                        .setState(WaitForSelectorState.ATTACHED)
                        .setTimeout(20000));

        page.waitForSelector(
                "input[name='firstName']",
                new Page.WaitForSelectorOptions()
                        .setState(WaitForSelectorState.VISIBLE)
                        .setTimeout(20000));

        System.out.println("Navigated to My Info");
    }

    public boolean isMyInfoPageVisible() {
        return isVisible(LocatorReader.get("myinfo.firstname"));
    }
}