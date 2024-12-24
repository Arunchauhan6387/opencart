package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import utilities.ExtentReportManager;

public class BaseClass {

public static WebDriver driver;
public Logger logger; //log4j2
public Properties p ;

public static ExtentReports extent;
public static ExtentTest test;


	
	@BeforeClass(groups= {"Sanity","Regression","Master"})
	@Parameters({"os","browser"})
	public void setUp(String os, String browser) throws IOException {
		
		
		
		//loading config.properties file
		p = new Properties();
		FileReader file = new FileReader(".//src//test//resources//config.properties");
		
		p.load(file);
		
		
		logger = LogManager.getLogger(this.getClass()); // this will load the particuler test class logs dynamically
		
		//Setup Grid env code 
		
		if(p.getProperty("execution_env").equalsIgnoreCase("remote")) {
			
			DesiredCapabilities capabiiities = new DesiredCapabilities();
			
			//os
			if(os.equalsIgnoreCase("windows")) {

				capabiiities.setPlatform(Platform.WIN11);
			}
			else if(os.equalsIgnoreCase("Linux")) {
				capabiiities.setPlatform(Platform.LINUX);
			}
			else if(os.equalsIgnoreCase("mac")) {
				capabiiities.setPlatform(Platform.MAC);
			}
			else {
				System.out.println("No matching os ");
			}
			
			//browser
			switch(browser.toLowerCase())
			{
			case "chrome" : capabiiities.setBrowserName("chrome"); break;
			case "edge" : capabiiities.setBrowserName("MicrosoftEdge"); break;
			case "firefox" : capabiiities.setBrowserName("firefox"); break;
			default : System.out.println("No matching browser"); return;
			
			}
			
			driver = new RemoteWebDriver(new URL("http://192.168.1.169:5555/wd/hub"),capabiiities);
			
		}
		
		
		
		if(p.getProperty("execution_env").equalsIgnoreCase("local"))
		{
			switch(browser.toLowerCase())
			{
			case "chrome"  :driver = new ChromeDriver(); break;
			case "edge"	   :driver = new EdgeDriver();break;
			case "firefox" :driver = new FirefoxDriver(); break;
			default       :System.out.println(" Invalid broswer name ");return;				
			}	
		}
		
		
		
		
		
		
		
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get(p.getProperty("appURLl")); //reading from properties file
		
	}
	


	@AfterClass(groups= {"Sanity","Regression","Master"})
	public void tearDown() {
		driver.close();
	}
	

	public String randomString() {
		
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}
	public String randomNumber() {
		
		String generatedNumber = RandomStringUtils.randomNumeric(10);
		return generatedNumber;
	}
	
	
	//this is for strong password 
	public String randomAlphaNumeric() {
		
		String generatedString = RandomStringUtils.randomAlphabetic(3);
		String generatedNumber = RandomStringUtils.randomNumeric(3);
		return (generatedString+"@"+generatedNumber);
		
	}
	
public String captureScreen(String tname) throws IOException{
		
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
		TakesScreenshot takeScreenshot = (TakesScreenshot) driver;
		File sourceFile = takeScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath = System.getProperty("user.dir")+"\\screenshots\\"+ tname + "_" + timeStamp + ".png";
		File targetFile = new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);		
		return targetFilePath;
		
	}
	
	
	
	
}
