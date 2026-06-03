package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.microsoft.playwright.*;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.LoginPage;
import utils.ConfigReader;
import utils.ExtentManager;
import utils.ScreenshotUtil;
import utils.WaitUtils;

public class BaseTest {

        protected static Playwright playwright;
        protected static Browser browser;
        protected static BrowserContext context;
        protected static Page page;

        protected static ExtentReports extent;
        protected static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

        private static boolean isLoggedIn = false;

        @BeforeSuite(alwaysRun = true)
        public void setupSuite() {
                playwright = Playwright.create();

                browser = playwright.chromium().launch(
                                new BrowserType.LaunchOptions()
                                                .setChannel("chrome")
                                                .setHeadless(false));

                context = browser.newContext();
                page = context.newPage();
                page.setDefaultTimeout(30000);

                extent = ExtentManager.getInstance();
                System.out.println("Browser and report initialised");
        }

        @BeforeMethod(alwaysRun = true)
        public void setup(java.lang.reflect.Method method) {

                // Get test annotation description if available
                Test annotation = method.getAnnotation(Test.class);
                String description = (annotation != null
                                && !annotation.description().isEmpty())
                                                ? annotation.description()
                                                : method.getName();

                ExtentTest test = extent.createTest(
                                method.getName(), description);

                // Assign author, device, category from annotation groups
                test.assignAuthor("Team 6");
                test.assignDevice("Chrome - " + System.getProperty("os.name"));

                if (annotation != null && annotation.groups().length > 0) {
                        test.assignCategory(annotation.groups());
                }

                extentTest.set(test);

                try {
                        if (!isLoggedIn) {
                                page.navigate(ConfigReader.get("base.url"),
                                                new Page.NavigateOptions()
                                                                .setTimeout(15000));

                                WaitUtils.waitForVisible(page,
                                                "input[name='username']");

                                LoginPage loginPage = new LoginPage(page);
                                loginPage.login(
                                                ConfigReader.get("admin.username"),
                                                ConfigReader.get("admin.password"));

                                WaitUtils.waitForVisible(page,
                                                "//span[text()='My Info']");

                                isLoggedIn = true;
                                extentTest.get().log(Status.INFO,
                                                "Logged in successfully as: "
                                                                + ConfigReader.get("admin.username"));
                                System.out.println("Logged in once for suite");

                        } else {
                                extentTest.get().log(Status.INFO,
                                                "Reusing existing session");
                        }

                } catch (Exception e) {
                        extentTest.get().log(Status.WARNING,
                                        "App not reachable: " + e.getMessage());
                        System.out.println("App not reachable - skipping test");
                        throw new org.testng.SkipException(
                                        "App not reachable");
                }
        }

        @AfterMethod(alwaysRun = true)
        public void afterMethod(ITestResult result) {
                if (result.getStatus() == ITestResult.FAILURE) {
                        try {
                                String screenshotPath = ScreenshotUtil.capture(
                                                page, result.getName());
                                extentTest.get().log(Status.FAIL,
                                                "Test failed: "
                                                                + result.getThrowable().getMessage());
                                extentTest.get().fail(
                                                MediaEntityBuilder
                                                                .createScreenCaptureFromPath(
                                                                                screenshotPath,
                                                                                "Failure Screenshot")
                                                                .build());
                        } catch (Exception e) {
                                extentTest.get().log(Status.FAIL,
                                                "Test failed: "
                                                                + result.getThrowable().getMessage());
                        }
                        System.out.println("Test FAILED: " + result.getName());

                } else if (result.getStatus() == ITestResult.SKIP) {
                        extentTest.get().log(Status.SKIP,
                                        "Test skipped: " + result.getName());

                } else {
                        extentTest.get().log(Status.PASS,
                                        "Test passed successfully");
                        System.out.println("Test PASSED: " + result.getName());
                }
        }

        @AfterSuite(alwaysRun = true)
        public void tearDown() {
                ExtentManager.flush();

                if (context != null)
                        context.close();
                if (browser != null)
                        browser.close();
                if (playwright != null)
                        playwright.close();

                System.out.println("Browser closed and report saved at: "
                                + ConfigReader.get("report.path"));
        }

        public static void reLogin() {
                isLoggedIn = false;

                page.navigate(ConfigReader.get("base.url"),
                                new Page.NavigateOptions().setTimeout(15000));

                WaitUtils.waitForVisible(page, "input[name='username']");

                LoginPage loginPage = new LoginPage(page);
                loginPage.login(
                                ConfigReader.get("admin.username"),
                                ConfigReader.get("admin.password"));

                WaitUtils.waitForVisible(page, "//span[text()='My Info']");

                isLoggedIn = true;
                System.out.println("Re-logged in for persistence validation");
        }
}