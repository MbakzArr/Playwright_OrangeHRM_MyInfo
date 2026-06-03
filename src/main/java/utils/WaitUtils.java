package utils;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public class WaitUtils {

    public static void waitForVisible(Page page, String selector) {
        page.waitForSelector(selector,
                new Page.WaitForSelectorOptions()
                        .setState(WaitForSelectorState.VISIBLE));
    }

    public static void waitForHidden(Page page, String selector) {
        page.waitForSelector(selector,
                new Page.WaitForSelectorOptions()
                        .setState(WaitForSelectorState.HIDDEN));
    }

    public static void waitForAttached(Page page, String selector) {
        page.waitForSelector(selector,
                new Page.WaitForSelectorOptions()
                        .setState(WaitForSelectorState.ATTACHED));
    }

    public static void waitForLoad(Page page) {
        page.waitForLoadState();
    }

    public static void waitForUrl(Page page, String urlPart) {
        page.waitForURL("**" + urlPart + "**");
    }
}
