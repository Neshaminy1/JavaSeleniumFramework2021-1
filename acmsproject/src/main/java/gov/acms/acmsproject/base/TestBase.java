package gov.acms.acmsproject.base;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import gov.acms.acmsproject.utils.DriverManager;
import gov.acms.acmsproject.utils.PropertyReader;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TestBase extends DriverManager {
	

    public static WebDriver driver;
    PropertyReader pr = new PropertyReader( );
    
    public static ExtentReports extent;
    public static ExtentSparkReporter sparkReporter;
    public static ExtentTest test;

    public TestBase() {

    	TestBase.driver = super.getDriver( );

    }
    
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

	@BeforeMethod(alwaysRun = true)
    public void setUp() {
        try {

            if (PropertyReader.readItem("browser").equals("firefox") || PropertyReader.readItem("browser").equals("FIREFOX")) {
                
  		  		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+
  				"//src//main//java//gov//acms//acmsproject//browserdriver//geckodriver.exe");
  				driver = new FirefoxDriver(); 
  				driver.manage( ).window( ).maximize( );
  				

            } else if (PropertyReader.readItem("browser").equals("chrome") || PropertyReader.readItem("browser").equals("CHROME")){
            	
    		  	System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+
    					"//src//main//java//gov//acms//acmsproject//browserdriver//chromedriver.exe");
    					ChromeOptions options = new ChromeOptions();
    					options.setExperimentalOption("useAutomationExtension", false); 
    					driver = new ChromeDriver(options); 
    					driver.manage( ).window( ).maximize( );
            }
            
            else {
                try {
                    throw new Exception("Browser or Browser Driver is not supported yet.");
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.getMessage( );
                    e.printStackTrace( );
                }
            }
        } catch (Exception e) {

        }
    }


    @AfterMethod(alwaysRun = true)
    public void teardown() {
        driver.quit( );


    }
    
	@AfterClass
	public void endTest()
	{
		//extent.("test");
		extent.flush();
	}

}