package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;

public class ExtentManager {
    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null)
            createInstance("extent.html");
        return extent;
    }

    public static ExtentReports createInstance(String fileName) {
        // Create the test-output directory
        File reportDir = new File("test-output");
        if (!reportDir.exists()) {
            boolean created = reportDir.mkdirs(); // Create the directory if it doesn't exist
            System.out.println("Directory created: " + created + " at " + reportDir.getAbsolutePath());
        }

        // Construct the full file path using the directory and fileName
        File reportFile = new File(reportDir, fileName);

        // Initialize the ExtentSparkReporter with the full file path
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportFile.getPath());
        sparkReporter.config().setTheme(com.aventstack.extentreports.reporter.configuration.Theme.STANDARD);
        sparkReporter.config().setDocumentTitle("Extent Reports");
        sparkReporter.config().setEncoding("utf-8");
        sparkReporter.config().setReportName("Extent Test Report");
        sparkReporter.config().setTimelineEnabled(true); // Enabling timeline for better traceability

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Organization", "QA Scientist Organization");
        extent.setSystemInfo("Browser", "Chrome");

        return extent;
    }

}

