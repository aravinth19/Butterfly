package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtentTestNGITestListener implements ITestListener {

    private static ExtentReports extent = ExtentManager.createInstance("extent.html");
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onStart(ITestContext context) {
        System.out.println("Start Of Execution(TEST)-> " + context.getName());
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test Started->" + result.getMethod().getMethodName());
        test.set(extent.createTest(result.getMethod().getMethodName()));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test Pass->" + result.getMethod().getMethodName());
        test.get().pass("Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test Failed->" + result.getMethod().getMethodName());
        test.get().fail(result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test Skipped->" + result.getMethod().getMethodName());
        test.get().skip("Test Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("END Of Execution(TEST)-> " + context.getName());
        extent.flush();
    }
}
