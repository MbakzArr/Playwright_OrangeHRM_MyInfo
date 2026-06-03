package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewName;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            String reportPath = ConfigReader.get("report.path");
            new java.io.File("target").mkdirs();

            ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);

            // Report appearance
            spark.config().setReportName("Team 6 - My Info Module Automation");
            spark.config().setDocumentTitle("OrangeHRM Automation Report");
            spark.config().setTheme(Theme.STANDARD);
            spark.config().setEncoding("UTF-8");
            spark.config().setTimelineEnabled(true);

            // Set view order - Dashboard first then Test
            spark.viewConfigurer()
                    .viewOrder()
                    .as(new ViewName[] {
                            ViewName.DASHBOARD,
                            ViewName.TEST,
                            ViewName.CATEGORY,
                            ViewName.AUTHOR,
                            ViewName.DEVICE,
                            ViewName.EXCEPTION,
                            ViewName.LOG
                    })
                    .apply();

            extent = new ExtentReports();
            extent.attachReporter(spark);

            // System info shown in environment section
            extent.setSystemInfo("Team", "Team 6");
            extent.setSystemInfo("Module", "My Info");
            extent.setSystemInfo("Environment", "OrangeHRM Demo");
            extent.setSystemInfo("Base URL",
                    ConfigReader.get("base.url"));
            extent.setSystemInfo("Browser",
                    ConfigReader.get("browser"));
            extent.setSystemInfo("OS",
                    System.getProperty("os.name") + " "
                            + System.getProperty("os.version"));
            extent.setSystemInfo("Java Version",
                    System.getProperty("java.version"));
            extent.setSystemInfo("User",
                    System.getProperty("user.name"));
        }
        return extent;
    }

    public static void flush() {
        if (extent != null) {
            extent.flush();
        }
    }
}