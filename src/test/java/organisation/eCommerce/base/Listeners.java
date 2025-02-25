package organisation.eCommerce.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listeners extends BaseTest implements ITestListener {
    ExtentReports extentReportObj = extentReportConfiguration();
    ExtentTest extentTest;
    ThreadLocal<ExtentTest> threadLocal = new ThreadLocal<>(); // thread safe

    @Override
    public void onTestStart(ITestResult iTestResult) {
        extentTest = extentReportObj.createTest(iTestResult.getMethod().getMethodName());
        threadLocal.set(extentTest); // created a unique thread id and assigns it to test
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        //extentTest.log(Status.PASS, "Testcase Passed");
        threadLocal.get().log(Status.PASS, "Testcase Passed");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        //extentTest.fail(iTestResult.getThrowable()); // logs exception into report
        threadLocal.get().fail(iTestResult.getThrowable());
        String testCaseName = iTestResult.getMethod().getMethodName(); //get testcase name
        String path = System.getProperty("user.dir") + "/extentReports/" + testCaseName + ".png"; // define screenshot location

        //Take screenshot
        try {
            driver = (WebDriver) iTestResult.getTestClass().getRealClass().getField("driver").get(iTestResult.getInstance());
            takeScreenshot(driver, testCaseName);
            threadLocal.get().addScreenCaptureFromPath(path, testCaseName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        threadLocal.get().log(Status.FAIL, "Testcase Failed");
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        extentReportObj.flush();
    }
}
