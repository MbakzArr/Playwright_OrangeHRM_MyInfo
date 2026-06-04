package pages;

import base.BasePage;
import com.microsoft.playwright.Page;
import utils.LocatorReader;
import utils.WaitUtils;
import java.nio.file.Paths;

public class ProfilePicPage extends BasePage {

    public ProfilePicPage(Page page) {
        super(page);
    }

    public void clickProfileImage() {
        click(LocatorReader.get("profile.image"));
    }

    public void uploadImage(String filePath) {
        // The file input is intentionally hidden on OrangeHRM.
        // Playwright can set files on hidden inputs directly
        // without needing the element to be visible.
        page.locator(LocatorReader.get("profile.file.input"))
                .setInputFiles(Paths.get(filePath));
    }

    public void clickSave() {
        click(LocatorReader.get("profile.save.button"));
        WaitUtils.waitForVisible(page,
                LocatorReader.get("toast.success"));
        WaitUtils.waitForHidden(page,
                LocatorReader.get("toast.success"));
    }

    public boolean isSaveButtonVisible() {
        return isVisible(LocatorReader.get("profile.save.button"));
    }
}