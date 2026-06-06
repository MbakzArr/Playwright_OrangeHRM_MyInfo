package pages;

import base.BasePage;
import com.microsoft.playwright.Page;
import utils.LocatorReader;
import utils.WaitUtils;

public class DependentsPage extends BasePage {

    public DependentsPage(Page page) {
        super(page);
    }

    public void openDependentsTab() {
        click(LocatorReader.get("dependents.tab"));
    }

    public void clickAdd() {
        page.locator(LocatorReader.get("dependents.add.button"))
                .first().click();
    }

    public void enterName(String name) {
        fill(LocatorReader.get("dependents.name"), name);
    }

    public void selectRelationship(String relationship) {
        click(LocatorReader.get(
                "dependents.relationship.dropdown"));

        String option = String.format(
                LocatorReader.get("dependents.relationship.option"),
                relationship);

        WaitUtils.waitForVisible(page, option);
        click(option);
    }

    public void clickSave() {
        click(LocatorReader.get("dependents.save.button"));
        waitForToast();
    }

    public boolean isDependentAdded(String name) {
        WaitUtils.waitForVisible(page,
                LocatorReader.get("dependents.table.body"));
        return page.locator(
                LocatorReader.get("dependents.table.body"))
                .getByText(name)
                .first()
                .isVisible();
    }
}
