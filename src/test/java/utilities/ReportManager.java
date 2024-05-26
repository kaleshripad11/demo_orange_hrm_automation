package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import testbase.Test_Base;
import java.io.File;

import java.util.Date;
import java.text.SimpleDateFormat;

public class ReportManager implements ITestListener {
    public ExtentSparkReporter sparkReporter;
    public ExtentReports reports;
    public ExtentTest tests;

    @Override
    public void onStart(ITestContext context) {
        String timestamp = new SimpleDateFormat("yyyy.MM.dd-HH.mm.ss").format(new Date());
        sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"/reports/OrangeHRM_Automation_Report-"+timestamp+".html");  //Set the location for report file

        sparkReporter.config().setDocumentTitle("OrangeHRM Automation Report");					  //Set title for the report
        sparkReporter.config().setReportName("BasicSanity");										  //Name of the report
        sparkReporter.config().setTheme(Theme.STANDARD);											  //Report theme

        reports = new ExtentReports();
        reports.attachReporter(sparkReporter);

        reports.setSystemInfo("Computer Name", "MacPC");
        reports.setSystemInfo("Computer OS", System.getProperty("os.name"));
        reports.setSystemInfo("User Name", System.getProperty("user.name"));
        reports.setSystemInfo("Environment Name", "QA");
        reports.setSystemInfo("Tester Name", "SK");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // TODO Auto-generated method stub
        tests = reports.createTest(result.getName());
        tests.log(Status.PASS, "Passed Test : " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        tests = reports.createTest(result.getName());
        tests.assignCategory(result.getMethod().getGroups());
        tests.log(Status.FAIL, "Failed Test : " + result.getName());
        tests.log(Status.INFO, "Failed Test Exception : " + result.getThrowable().getMessage());
        try{
            //String failedCaseScreen = Test_Base.captureScreenshots(result.getName());
            String failedCaseScreen = new Test_Base().captureScreenshots(result.getName());
            tests.addScreenCaptureFromPath(failedCaseScreen);
        }catch(Exception ex){
            ex.getMessage();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        tests = reports.createTest(result.getName());
        tests.log(Status.SKIP, "Skipped Test : " + result.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        reports.flush();
    }
}
