package pages;

import base.BasePage;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import utils.LocatorReader;
import utils.WaitUtils;

public class EmergencyContactPage extends BasePage {

    public EmergencyContactPage(Page page) {
        super(page);
    }

    public void openEmergencyContacts() {
        click(LocatorReader.get("emergency.tab"));
    }

    public void clickAdd() {
        click(LocatorReader.get("emergency.add.button"));
    }

    public void enterName(String name) {
        fill(LocatorReader.get("emergency.name"), name);
    }

    public void enterRelationship(String relation) {
        fill(LocatorReader.get("emergency.relationship"), relation);
    }

    public void enterMobile(String mobile) {
        fill(LocatorReader.get("emergency.mobile"), mobile);
    }

    public void clickSave() {
        click(LocatorReader.get("emergency.save.button"));
        waitForToast();
    }

    public boolean isRecordAdded(String name) {
        WaitUtils.waitForVisible(page,
                LocatorReader.get("emergency.table.body"));
        return page.locator(
                LocatorReader.get("emergency.table.body"))
                .filter(new Locator.FilterOptions()
                        .setHasText(name))
                .count() > 0;
    }

    public void waitForLoaderToDisappear() {
        page.waitForSelector(
                "//div[contains(@class,'oxd-form-loader')]",
                new Page.WaitForSelectorOptions()
                        .setState(WaitForSelectorState.HIDDEN));
    }
}
