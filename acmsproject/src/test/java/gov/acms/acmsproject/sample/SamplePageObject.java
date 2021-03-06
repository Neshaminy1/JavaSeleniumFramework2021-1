package gov.acms.acmsproject.sample;

import org.testng.annotations.Test;

import gov.acms.acmsproject.base.TestBase;
import gov.acms.acmsproject.pagelibrary.portal.PortalPage;
import gov.acms.acmsproject.utils.PropertyReader;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

public class SamplePageObject extends TestBase{
	
	@Test
	public void PageObject() throws Exception {
		
		PortalPage  portalpage  = new PortalPage (driver);
    	//ExtentReportNG extend = new ExtentReportNG();
    	
    	portalpage.navigateURL("https://ca2-portal-core-dev.powerappsportals.us/");
    	test.log(Status.PASS, "Successfully Navigated to the portal");
    	
    	portalpage.SignInHomePage();
    	test.log(Status.PASS, "Click Sign In");
    	
    	portalpage.LoginToPortal(PropertyReader.readItem("portalusername"), PropertyReader.readItem("portalpassword"));
    	test.log(Status.PASS, "Click Login");
    	
    	//Best Option
		test.log(Status.INFO, "SamplePageObject", MediaEntityBuilder.createScreenCaptureFromPath(SamplePageObject.getScreenshot(driver, "SamplePageObject")).build());
		
	}

}
