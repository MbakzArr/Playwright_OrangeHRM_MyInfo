package utils;

import com.microsoft.playwright.Page;
import java.nio.file.Paths;

public class ScreenshotUtil {

    public static String capture(Page page, String testName) {
        String folderPath = ConfigReader.get("screenshot.path");
        new java.io.File(folderPath).mkdirs();

        String filePath = folderPath + testName + "_"
                + System.currentTimeMillis() + ".png";

        page.screenshot(
                new Page.ScreenshotOptions()
                        .setPath(Paths.get(filePath))
                        .setFullPage(true));

        System.out.println("Screenshot saved: " + filePath);
        return filePath;
    }
}
