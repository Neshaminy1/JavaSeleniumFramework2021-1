package gov.acms.acmsproject.sample;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SampleWebDriveManager {
	
	Logger log = Logger.getLogger(SampleWebDriveManager.class);
	
	public static WebDriver driver;
	static ExtentReports extent;
	ExtentSparkReporter sparkReporter;
	ExtentTest test;

		@BeforeClass
		public void BeforeClass() {
			
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
			String className = this.getClass().getName();
			
			extent = new ExtentReports();
			sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"/src/test/resources/reports/extentreports/"+ className + formater.format(calendar.getTime()) + ".html");
			extent.attachReporter(sparkReporter);
			test = extent.createTest(className);
			
		}
		
		public static String getScreenshot(WebDriver driver, String screenshotName)   throws Exception {
			  
			  Calendar calendar = Calendar.getInstance(); 
			  SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
			  
			  //below line is just to append the date format with the screenshot name to avoid duplicate names 
			  //String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date()); 
			  TakesScreenshot ts =   (TakesScreenshot) driver; 
			  File source = ts.getScreenshotAs(OutputType.FILE);
			  //after execution, you could see a folder "FailedTestsScreenshots" under src folder 
			  String destination =  System.getProperty("user.dir")+"/src/test/resources/reports/screenshots/" + screenshotName + formater.format(calendar.getTime()) + ".png"; 
			  File finalDestination = new File(destination); 
			  FileUtils.copyFile(source, finalDestination);
			  //Returns the captured file path return destination; }
			  return destination;
			  
			  //How to use
			  //test.log(Status.INFO, "details", MediaEntityBuilder.createScreenCaptureFromPath(this.getScreenshot(driver, "Hello")).build());
		}
		
		
		@Test
		public void SampleTest() throws Exception {
			
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.get("https://google.com");
			Thread.sleep(2000);
	    	log.info("Successfully Navigated to Google");
	    	test.log(Status.PASS, "Successfully Navigated to Google");
	    	
			driver.findElement(By.xpath("/html/body/div[1]/div[3]/form/div[1]/div[1]/div[1]/div/div[2]/input")).sendKeys("QTP");
			log.info("Successfully entered value in google search box");
			test.log(Status.PASS, "Successfully entered value in google search box");
			
			//String screenshotPath = Sample.getScreenshot(driver, "Hello");
			//test.info("Snapshot below: " + test.addScreenCaptureFromPath(screenshotPath));
			//test.info("Snapshot below: " + test.addScreenCaptureFromPath(Sample.getScreenshot(driver, "Hello")));
			
			//Best Option
			test.log(Status.INFO, "GoogleHomePage", MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot(driver, "GoogleHomePage")).build());

			
		}
		
	    @AfterMethod(alwaysRun = true)
	    public void teardown() {
	        driver.quit( );


	    }
		
		@AfterClass
		public void endTest()
		{
			extent.flush();
		}
		
	}