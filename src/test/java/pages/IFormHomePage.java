package pages;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IFormHomePage {
	WebDriver driver;
	Properties objRepo;
    By homePageUserName = By.cssSelector("ul#header_rt li:nth-child(1)");
    By formID = By.cssSelector("table#flex1>tbody>tr#row3638850>td#row3638850_ID>div>a"); 
    WebDriverWait wait;
    
    public IFormHomePage(WebDriver driver){

        this.driver = driver;
        wait=new WebDriverWait(this.driver, 20);

    }
    
  //Get the User name from Home Page

    public String getHomePageDashboardUserName(){
    	
     WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(homePageUserName));

     return element.getText();

    }
    
  //Click on form
    
    public void clickOnForm(){
    	
    	WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(formID));

    	element.click();
    	

    }

}
