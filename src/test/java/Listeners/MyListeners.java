package Listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import utilities.MyExtentReporter;

public class MyListeners implements ITestListener{
	
	ExtentReports extentReport;
	ExtentTest extentTest;
	
	@Override
	public void onStart(ITestContext context) {
		
	 try {
		extentReport = MyExtentReporter.generateExtentReport();
	} catch (IOException e) {
		
		e.printStackTrace();
	}
		
	}
	@Override
	public void onTestStart(ITestResult result) {
		
		String testName = result.getName();
	    extentTest =	extentReport.createTest(testName);
	    extentTest.log(Status.INFO, testName+"started executing");
		
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String testName = result.getName();
		extentTest.log(Status.PASS, testName+" got successfully executed ");
		
		
		
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		String testName = result.getName();
		
		// we need driver 
		
		WebDriver driver = null;
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			
			e.printStackTrace();
		}
		File srcScreenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		
		String destinationScreenshotPath = System.getProperty("user.dir")+"\\screenshots\\"+ testName +".png";
		
		try {
			FileHandler.copy(srcScreenshot, new File(destinationScreenshotPath));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		extentTest.addScreenCaptureFromBase64String(destinationScreenshotPath);
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.FAIL, testName+"got failed");

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
		String testName = result.getName();
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.SKIP, testName+" got skipped");
		
		
	}

	

	@Override
	public void onFinish(ITestContext context) {
		
		extentReport.flush();
		String pathofExtentReport = System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html\\";
		
		File FinalReport = new File(pathofExtentReport);
		
		try {
			Desktop.getDesktop().browse(FinalReport.toURI());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		
	}

	
	
	
}
