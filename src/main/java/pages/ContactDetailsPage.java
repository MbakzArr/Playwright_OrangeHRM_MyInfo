package pages;

import base.BasePage;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import utils.LocatorReader;
import utils.WaitUtils;

public class ContactDetailsPage extends BasePage {

    public ContactDetailsPage(Page page) {
        super(page);
    }

    public void clickContactDetails() {
        click(LocatorReader.get("contact.details.link"));

        // Wait for spinner to disappear before interacting
        page.waitForSelector(
                ".oxd-form-loader",
                new Page.WaitForSelectorOptions()
                        .setState(WaitForSelectorState.HIDDEN)
                        .setTimeout(15000));

        WaitUtils.waitForVisible(page,
                LocatorReader.get("contact.city"));
    }

    public void fillContactDetails(String street1, String street2,
            String city, String state, String zip,
            String home, String mobile, String work,
            String otherEmail) {

        fill(LocatorReader.get("contact.street1"), street1);
        fill(LocatorReader.get("contact.street2"), street2);
        fill(LocatorReader.get("contact.city"), city);

        click(LocatorReader.get("contact.country.dropdown"));
        WaitUtils.waitForVisible(page,
                LocatorReader.get("contact.country.option"));
        click(LocatorReader.get("contact.country.option"));

        fill(LocatorReader.get("contact.state"), state);
        fill(LocatorReader.get("contact.zip"), zip);
        fill(LocatorReader.get("contact.home"), home);
        fill(LocatorReader.get("contact.mobile"), mobile);
        fill(LocatorReader.get("contact.work"), work);
        fill(LocatorReader.get("contact.other.email"), otherEmail);
    }

    public void clickSave() {
        click(LocatorReader.get("contact.save.button"));
        waitForToast();
    }

    public String getCity() {
        return getInputValue(LocatorReader.get("contact.city"));
    }

    public String getState() {
        return getInputValue(LocatorReader.get("contact.state"));
    }

    public String getZip() {
        return getInputValue(LocatorReader.get("contact.zip"));
    }

    public String getMobile() {
        return getInputValue(LocatorReader.get("contact.mobile"));
    }

    public String getOtherEmail() {
        return getInputValue(
                LocatorReader.get("contact.other.email"));
    }
}