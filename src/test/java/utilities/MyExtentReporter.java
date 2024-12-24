package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class MyExtentReporter {
	public static Properties configProp ;
	
	public static  ExtentReports generateExtentReport() throws IOException {
		
		ExtentReports extentReport = new ExtentReports();
		File extetReportFile = new File(System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html");
		
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extetReportFile);
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setReportName("OpenCart Test Reports");
		sparkReporter.config().setDocumentTitle("OpenCart Automation Report");
		sparkReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");
		
		extentReport.attachReporter(sparkReporter);
		
		 configProp = new Properties();
		
		File conffigPropFile = new File(System.getProperty("user.dir")+"\\src\\test\\resources\\conig.properties");
		
		try {
			FileInputStream fisConfigProp = new FileInputStream(conffigPropFile);
			configProp.load(fisConfigProp);
		}catch(Throwable e) {
			e.printStackTrace();
		}
		
		extentReport.setSystemInfo("Application URL","opencart");
		extentReport.setSystemInfo("Browser Name ", configProp.getProperty("appURLl"));
		extentReport.setSystemInfo("Email", configProp.getProperty("email"));
		extentReport.setSystemInfo("Password", configProp.getProperty("password"));
		extentReport.setSystemInfo("Operating system", System.getProperty("os.name"));
		extentReport.setSystemInfo("Username", System.getProperty("user.name"));
		extentReport.setSystemInfo("Java version", System.getProperty("java.version"));
		
		return extentReport;
		
	}
	
}
