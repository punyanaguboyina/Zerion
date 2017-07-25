package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IFormLogin {
	WebDriver driver;
	By iFormUserName = By.cssSelector("input[name='USERNAME']");
	By iFormPassword = By.cssSelector("input[name='PASSWORD']");
	By login = By.cssSelector("input[type='image']");
	WebDriverWait wait;

	public IFormLogin(WebDriver driver){
		this.driver = driver;
		wait=new WebDriverWait(this.driver, 20);
	}
	
	//Set user name in textbox
    public void setUserName(String strUserName){
    	
    	WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(iFormUserName));

    	element.sendKeys(strUserName);

    }

    //Set password in password textbox
    public void setPassword(String strPassword){

         driver.findElement(iFormPassword).sendKeys(strPassword);

    }
    
  //Click on login button
    public void clickLogin(){

            driver.findElement(login).click();

    }
    
    public void loginToIForm(String strUserName,String strPasword){
    	
        //Fill user name

        this.setUserName(strUserName);

        //Fill password

        this.setPassword(strPasword);

        //Click Login button

        this.clickLogin();  
        
    }
}
