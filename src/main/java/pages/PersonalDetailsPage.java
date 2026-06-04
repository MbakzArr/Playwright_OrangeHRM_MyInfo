package pages;

import base.BasePage;
import com.microsoft.playwright.Page;
import utils.LocatorReader;

public class PersonalDetailsPage extends BasePage {

    public PersonalDetailsPage(Page page) {
        super(page);
    }

    public void updateFirstName(String firstName) {
        fill(LocatorReader.get("myinfo.firstname"), firstName);
    }

    public void updateLastName(String lastName) {
        fill(LocatorReader.get("myinfo.lastname"), lastName);
    }

    public void clickSave() {
        click(LocatorReader.get("myinfo.savebutton"));
        waitForToast();
    }

    public String getFirstName() {
        return getInputValue(LocatorReader.get("myinfo.firstname"));
    }

    public String getLastName() {
        return getInputValue(LocatorReader.get("myinfo.lastname"));
    }
}