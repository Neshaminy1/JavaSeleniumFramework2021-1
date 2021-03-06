package gov.acms.acmsproject.pagelibrary.portal;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import gov.acms.acmsproject.base.BasePage;

public class PortalPage extends BasePage{


	WebDriver driver;

    public PortalPage (WebDriver driver){
        super(driver);
        this.driver = driver;
    }
    
  //******************** Elements Locator for Portal ********************
    
  	//Click signIn
      By SignIn = By.xpath("//*[@id='navbar']/div[1]/ul/li[3]/a");
  	
  	//Enter username	
      By userName = By.xpath("//*[@id='loginForm:loginName']");
    
  	//Enter password
      By passWord = By.xpath("//*[@id='loginForm:password']");

  	//Click Login
      By clickSignIn = By.xpath("(//*[text() ='Login'])[2]");
    // Google Searxh
      By googleSearch = By.xpath("/html/body/div[1]/div[3]/form/div[1]/div[1]/div[1]/div/div[2]/input");
  	
  	
  //************************************************ Page Actions **************************************************

  	public void SignInHomePage() throws InterruptedException{
  		waitForElementToAppear(SignIn);
  		click(SignIn);
  		//waitForElementToAppear(userName);
  	    presenceOfTheElement(userName);
  	}

  	
  	public void LoginToPortal(String UserName, String Password) throws InterruptedException{			
  		writeText(userName, UserName);
  		writeText(passWord, Password);
  		click(clickSignIn);
  	}

  	
  	
  	
 
  	
  	

  }

	
