package resources;


import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import resources.Base;

public class Listeners implements ITestListener{
	
	Base b = new Base();
	ExtentReports extent = ExtentReporterNG.getReportObject();
	ExtentTest test;
	ThreadLocal <ExtentTest> extentTest = new ThreadLocal <ExtentTest>();
	
	
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
	}

	public void onFinish(ITestContext arg0) {
		extent.flush();
	}
	
	public void onTestSuccess(ITestResult result) {
		extentTest.get().log(Status.PASS, "Test Passed");
		WebDriver driver = null;
		String testMethodName = result.getMethod().getMethodName();
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		}catch(Exception e) {
			
		}
		try {
			extentTest.get().addScreenCaptureFromPath(b.getScreenShot(testMethodName,driver), result.getMethod().getMethodName());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void onTestFailure(ITestResult result) {
		extentTest.get().fail(result.getThrowable());
		WebDriver driver = null;
		String testMethodName = result.getMethod().getMethodName();
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (Exception e) {
			
		}
		try {
			extentTest.get().addScreenCaptureFromPath(b.getScreenShot(testMethodName,driver), result.getMethod().getMethodName());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void onTestSkipped(ITestResult arg0) {
		extentTest.get().log(Status.SKIP, "Test Skipped");
	}
	
	public void onStart(ITestContext arg0) {
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}
}
