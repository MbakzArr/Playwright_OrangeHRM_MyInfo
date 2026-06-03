package base;

import com.microsoft.playwright.Page;
import utils.LocatorReader;
import utils.WaitUtils;

public abstract class BasePage {

    protected Page page;

    public BasePage(Page page) {
        this.page = page;
    }

    protected void click(String locator) {
        WaitUtils.waitForVisible(page, locator);
        page.locator(locator).first().click();
    }

    protected void fill(String locator, String value) {
        WaitUtils.waitForVisible(page, locator);
        // Click to focus the field
        page.locator(locator).first().click();
        // Select all existing text and delete it
        page.locator(locator).first().fill("");
        // Fill the new value
        page.locator(locator).first().fill(value);
        // Fire the input event so Vue.js registers the change
        page.locator(locator).first().evaluate(
                "el => { el.dispatchEvent(new Event('input', { bubbles: true })); }");
    }

    protected boolean isVisible(String locator) {
        return page.locator(locator).isVisible();
    }

    protected String getText(String locator) {
        return page.locator(locator).innerText();
    }

    protected String getInputValue(String locator) {
        return page.locator(locator).first().inputValue().trim();
    }

    protected void waitForToast() {
        WaitUtils.waitForVisible(page,
                LocatorReader.get("toast.success"));
        WaitUtils.waitForHidden(page,
                LocatorReader.get("toast.success"));
    }

    public void logout() {
        click(LocatorReader.get("logout.dropdown"));
        click(LocatorReader.get("logout.link"));
        WaitUtils.waitForVisible(page,
                LocatorReader.get("login.button"));
    }
}