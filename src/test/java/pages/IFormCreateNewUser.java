package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IFormCreateNewUser {
	WebDriver driver;
	WebDriverWait wait;
	By menuHolderUser = By.cssSelector("div.menuHolder ul:nth-child(3) li:nth-child(1)>a");
	By menuHolderUserItem = By.cssSelector("div.menuHolder ul:nth-child(3) li:nth-child(1)>ul li:nth-child(2)>a");
	By newUserButtonID = By.cssSelector("div#fbutton_New>div>span.new>div");
	By userNameID = By.cssSelector("input[name='newUsername']");
	By firstNameID = By.cssSelector("input[name='newFirstname']");
	By lastNameID = By.cssSelector("input[name='newLastname']");
	By emailID = By.cssSelector("input[name='newEmail']");
	By newPasswordID = By.cssSelector("input[name='newPassword']");
	By newPasswordAgainID = By.cssSelector("input[name='newPasswordAgain']");
	By createRightID = By.cssSelector("input[name='newCreateRight']");
	By newAdminRightID = By.cssSelector("input[name='newAdminRight']");
	By newSyncRightID = By.cssSelector("input[name='newSyncRight']");
	By createUserButton = By.cssSelector("div#custombtn_list>ul li:nth-child(1)>div>a");
	By userCount = By.cssSelector("table[summary='iFormBuilder Data Collection User List']>tbody>tr");
	By errorID = By.cssSelector("div#error");
	By logoutID = By.cssSelector("div.padRight>ul li:nth-child(3)>a");
	
	public IFormCreateNewUser(WebDriver driver){
		this.driver = driver;
		wait=new WebDriverWait(this.driver, 5);
	}
	
	public void clickAddUserFromMenu(){
		new Actions(driver).moveToElement(driver.findElement(menuHolderUser)).release(driver.findElement(menuHolderUser)).build().perform();
		driver.findElement(menuHolderUserItem).click();
	}
	
	public void clickNewUserButton(){
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(newUserButtonID));
		System.out.println("clickNewUserButton=="+element.getText());
		element.click();
	}
	
	//get records count
    public int getUsersCount(){
    	
    	List<WebElement> elements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(userCount));
    	
    	return elements.size();
    	
    }
	
	//Set user name in textbox
    public void setUserName(String strUserName){
    	WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(userNameID));
    	element.sendKeys(strUserName);

    }
    
  //Set first name in textbox
    public void setFirstName(String strFirstName){
    	driver.findElement(firstNameID).sendKeys(strFirstName);

    }
    
  //Set last name in textbox
    public void setLastName(String strLastName){
    	driver.findElement(lastNameID).sendKeys(strLastName);

    }
    
  //Set email in textbox
    public void setEmail(String strEmail){
    	driver.findElement(emailID).sendKeys(strEmail);
    }
    
  //Set new password in textbox
    public void setNewPassword(String strNewPassword){
    	driver.findElement(newPasswordID).sendKeys(strNewPassword);
    }
    
  //Set new password confirm in textbox
    public void setNewPasswordConfirm(String strNewPasswordConfirm){
    	driver.findElement(newPasswordAgainID).sendKeys(strNewPasswordConfirm);
    }
    
  //Set right to create form checkbox 
    public void setCreateForm(){
    	driver.findElement(createRightID).click();
    }
    
  //Set company admin checkbox 
    public void setCompanyAdmin(){
    	driver.findElement(newAdminRightID).click();
    }
    
  //Set Sync checkbox 
    public void setSync(){
    	driver.findElement(newSyncRightID).click();
    }
    
 public boolean createUser(String strUserName, String strFirstName,String strLastName,String strEmailAddress,String strNewPassword,String strNewPasswordConfirm,String strCreateForm,String strCompanyAdmin,String strSync){
	 
	    //Fill User Name
	 
	 	this.setUserName(strUserName);
    	
        //Fill first name

        this.setFirstName(strFirstName);

        //Fill Last name

        this.setLastName(strLastName);
        
        //Fill Email address
        
        this.setEmail(strEmailAddress);
        
        //Fill password
        
        this.setNewPassword(strNewPassword);
        
        //Fill password confirm
        
        this.setNewPasswordConfirm(strNewPasswordConfirm);
        
        //Fill create form
        if("yes".equals(strCreateForm)){
        this.setCreateForm();
        }
        
        //Fill company admin
        if("yes".equals(strCompanyAdmin)){
        this.setCompanyAdmin();
        }
        //Fill sync
        if("yes".equals(strSync)){
        this.setSync();
        }
        //Click save record button

        return this.clickOnCreateUserButton();  
        
    }
 
 	public boolean clickOnCreateUserButton(){
 		driver.findElement(createUserButton).click();
 		boolean isElementPresent = false;
 		if( isElementPresent(errorID)){
 			isElementPresent = true;
 			}
 		return isElementPresent;
 	}
 	
 	private boolean isElementPresent(By by) {
 	    try {
 	    	wait.until(ExpectedConditions.visibilityOfElementLocated(by));
 	        return true;
 	    } catch (Exception e) {
 	        return false;
 	    }
 	}
 	
 	public void clickOnLogOut(){
 		driver.findElement(logoutID).click();
 	}
}
