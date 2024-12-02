package api.utilities;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.markuputils.ExtentColor;

public class ListenerClass implements ITestListener {

    private ExtentReports extent = ExtentManager.getInstance();
    private ExtentTest test;

    @Override
    public void onStart(ITestContext context) {
        // Called before any test starts
        System.out.println("Test Started: " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        // Called after all tests are finished
        System.out.println("Test Finished: " + context.getName());
        extent.flush();  // Write to the report
    }

    @Override
    public void onTestStart(ITestResult result) {
        // Create a test in the report
        test = extent.createTest(result.getName());
        System.out.println("Test Started: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // Log the success of the test
        test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " PASSED", ExtentColor.GREEN));
    }

    @Override
    public void onTestFailure(ITestResult result) {
        // Log the failure of the test and attach the screenshot (if any)
        test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " FAILED", ExtentColor.RED));
        String screenshotPath = "path/to/screenshot.png";  // Add your screenshot logic
        test.fail("Screenshot: " + test.addScreenCaptureFromPath(screenshotPath));
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // Log the skipped test
        test.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " SKIPPED", ExtentColor.YELLOW));
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // Optional: Handle partial successes, usually for retries
    }
}
