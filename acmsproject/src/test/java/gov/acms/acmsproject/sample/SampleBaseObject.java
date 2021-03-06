package gov.acms.acmsproject.sample;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import gov.acms.acmsproject.base.BasePage;
import gov.acms.acmsproject.base.TestBase;

public class SampleBaseObject extends TestBase {

	Logger log = Logger.getLogger(SampleBaseObject.class);
	
	@Test
	public void Setup() throws Exception {
				
		BasePage  basepage  = new BasePage (driver);
		basepage.navigateURL("https://ca2-portal-core-dev.powerappsportals.us/");
		test.log(Status.PASS, "Successfully Navigated to the portal");
		
		basepage.click(By.xpath("//*[@id='navbar']/div[1]/ul/li[3]/a"));
		test.log(Status.PASS, "Click Sign In");
		
		basepage.writeText(By.xpath("//*[@id='loginForm:loginName']"), "Test1");
		test.log(Status.PASS, "Enter UserName");
		
		basepage.writeText(By.xpath("//*[@id='loginForm:password']"), "Password1");
		test.log(Status.PASS, "Enter Password");
		
		//Best Option
		test.log(Status.INFO, "portal", MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot(driver, "portal")).build());

	}
	
	
}
