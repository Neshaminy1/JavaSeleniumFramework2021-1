package gov.acms.acmsproject.base;

import java.time.Duration;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.Random;
import gov.acms.acmsproject.utils.WaitForHelper;


public class BasePage{

    public WebDriver driver;

    public BasePage (WebDriver driver){
        this.driver = driver;
    }
    //open url method
    public void gotoURL(String url) {
        driver.get(url);
        driver.navigate().to(url);
    }

    //Navigate url method
    public void navigateURL(String url) {
        driver.navigate().to(url);
    }
    
    //wait method
    public void waitForElementToAppear(By elementLocation) {
        //Conditional wait for one of the elements on the search results page to be present
        new WaitForHelper(driver).presenceOfTheElement(elementLocation);
    }

    public void waitForTime() {
        //Conditional wait for one of the elements on the search results page to be present
        new WaitForHelper(driver).implicitwait( );
    }

    //Click Method
    public void click(By elementLocation) {
        driver.findElement(elementLocation).click( );
    }

    //Write Text
    public void writeText(By elementLocation, String text) {
        driver.findElement(elementLocation).clear( );
        driver.findElement(elementLocation).sendKeys(text);
    }


    //Read Text
    public String readText(By elementLocation) {
        return driver.findElement(elementLocation).getText( );
    }

    // Move to Element
    public void moveToElement(By elementLocation) {

        new Actions(driver).moveToElement(driver.findElement(elementLocation)).build( ).perform( );

    }
    
    
   //**********************************************************************************************************
    public void implicitwait()
    {
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    
    public WebDriverWait presenceOfTheElement(final By elementIdentifier) {
        WebDriverWait firstResult = new WebDriverWait(driver, Duration.ofSeconds(20));
        firstResult.until(ExpectedConditions.elementToBeClickable(elementIdentifier));
        return firstResult;
    }
    
   
   //**********************************************************************************************************
    public String FirstNameOptions()
    {
    	String[] generation = { "Alex", "Alexis", "Andy", "Angel", "Ash", "Ashley", "Bennie", "Bertie", "Beverly", "Blair", "Bobbie", "Brett", "Carol", "Casey", "Charlie", "Cis", "Chris", "Christian", "Clem", "Connie", "Cory" };
        Random rand = new Random();
        int index = rand.nextInt(generation.length);
        String generationType = generation[index];
        return generationType;
    }
    
    public static String RandomString(int stringLength)
    {
        Random ran = new Random();
        String b = "abcdefghijklmnopqrstuvwxyz";
        String random = "";

        for (int i = 0; i < stringLength; i++)
        {
            int a = ran.nextInt(26);
            random = random + b.charAt(a);
        }
        return random;

    }
    
    public static int generateRandomDigits(int n) {
        int m = (int) Math.pow(10, n - 1);
        return m + new Random().nextInt(9 * m);
    }
        
   
}