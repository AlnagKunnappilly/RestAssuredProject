package api.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExtentManager {

    private static ExtentReports extent;
    private static ExtentTest test;
    private static final String reportDir = System.getProperty("user.dir") + File.separator + "test-output"
            + File.separator + "ExtentReports";
    private static LocalDateTime now = LocalDateTime.now();
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
    private static String timestamp = now.format(formatter);
    private static final String reportPath = reportDir + File.separator + "Report-" + timestamp + ".html";

    // Create the ExtentReports instance
    public static ExtentReports getInstance() {
        if (extent == null) {
            File reportDirectory = new File(reportDir);
            if (!reportDirectory.exists()) {
                reportDirectory.mkdirs();
            }

            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
            sparkReporter.config().setTheme(Theme.DARK);
            sparkReporter.config().setDocumentTitle("Test Report");
            sparkReporter.config().setReportName("API Automation Tests");

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);

            // Add system information (optional)
            extent.setSystemInfo("Machine", "TestPC");
            extent.setSystemInfo("OS", "Windows 10");
            extent.setSystemInfo("User", "YourName");
        }
        return extent;
    }

    // Start a test and return the ExtentTest instance
    public static void startTest(String testName) {
        test = getInstance().createTest(testName);
    }

    // End the test and flush the report
    public static void endTest() {
        getInstance().flush();
    }

    // Log an info message
    public static void logInfo(String message) {
        test.info(message);
    }

    // Log a failure message
    public static void logFailure(String message) {
        test.fail(message);
    }

    // Log a skip message
    public static void logSkip(String message) {
        test.skip(message);
    }

    // Add screenshots (if needed)
    public static void addScreenshot(String screenshotPath) {
        test.addScreenCaptureFromPath(screenshotPath);
    }
}
