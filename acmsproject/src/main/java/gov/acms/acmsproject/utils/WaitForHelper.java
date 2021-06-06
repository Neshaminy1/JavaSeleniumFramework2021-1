package gov.acms.acmsproject.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class WaitForHelper {

    public WebDriver driver;

    public WaitForHelper(WebDriver driver) {
        this.driver = driver;
    }

    public void implicitwait()
    {
       // driver.manage().timeouts().implicitlyWait(Integer.parseInt(PropertyReader.readItem("elementLoadTimeout")),TimeUnit.SECONDS);
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }


  //  public WebElement presenceOfTheElement(final By elementIdentifier) {
  //      WebElement firstResult = new WebDriverWait(driver, 20)
  //              .until(ExpectedConditions.elementToBeClickable(elementIdentifier));
  //      return firstResult;
  //  }
    
    public WebDriverWait presenceOfTheElement(final By elementIdentifier) {
        WebDriverWait firstResult = new WebDriverWait(driver, Duration.ofSeconds(20));
        firstResult.until(ExpectedConditions.elementToBeClickable(elementIdentifier));
        return firstResult;
    }
    
    //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
}