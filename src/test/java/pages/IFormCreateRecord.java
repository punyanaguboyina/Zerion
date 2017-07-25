package pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import util.Constants;

public class IFormCreateRecord {
	
	WebDriver driver;	
	WebDriverWait wait;
	By createRecordID = By.cssSelector("div#fbutton_Create_New_Record");
	By formLable = By.cssSelector("div.ftitle");
	By serialNum = By.cssSelector("input#p3638850_rec0_serial_number");
	By firstName = By.cssSelector("input#p3638850_rec0_my_element_1");
	By lastName = By.cssSelector("input#p3638850_rec0_my_element_2");
	By emailAddress = By.cssSelector("input#p3638850_rec0_my_element_3");
	By saveButton = By.cssSelector("div#custombtn_list>ul li:nth-of-type(1)>div>a");
	By recordSize = By.cssSelector("table[summary='iFormBuilder Data Collection Data List']>tbody>tr");
	By serialNumCellData = By.cssSelector("table#flex1>tbody tr:nth-child(1) td:nth-child(3)>div");
	By fnameCellData = By.cssSelector("table#flex1>tbody tr:nth-child(1) td:nth-child(4)>div");
	By lnameCellData = By.cssSelector("table#flex1>tbody tr:nth-child(1) td:nth-child(5)>div");
	By emailCellData = By.cssSelector("table#flex1>tbody tr:nth-child(1) td:nth-child(6)>div");
	
	public IFormCreateRecord(WebDriver driver){
		
		this.driver = driver;
		
		 wait=new WebDriverWait(this.driver, 20);
		
	}
    
  
    //Checks form validity
	public boolean verifyForm(){
		
		boolean formValidity = false;
		
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(recordSize));
		
		String text = driver.findElement(formLable).getText();
		
		if(Constants.FORM_LABLE.equals(text)){
			
			formValidity = true;
			
		}
		
		return formValidity;
		
	}
   
    
    //get records count
    public int getRecordsCount(){
    	
    	List<WebElement> elements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(recordSize));
    	
    	return elements.size();
    	
    }
    
    //Click on create new button
    public void clickOnCreateNewRecordButton(){
    	
    	WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(createRecordID));
    	
    	element.click();
    	
    }
    
  //Set serial number
    public void setSerialNumber(String strSerialNumber){
    	
    	WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(serialNum));

    	element.sendKeys(strSerialNumber);
    	
    }
    
  //Set first name in textbox
    public void setFirstName(String strFirstName){

    	WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(firstName));

    	element.sendKeys(strFirstName);

    }
    
  //Set last name in textbox
    public void setLastName(String strLastName){

        driver.findElement(lastName).sendKeys(strLastName);

    }
    
  //Set email address in textbox
    public void setEmailAddress(String strEmailAddress){

        driver.findElement(emailAddress).sendKeys(strEmailAddress);

    }
    
  //Click on save button
    public void clickOnSaveRecordButton(){
    	
    	driver.findElement(saveButton).click();
    	
    }
    
    public void createRecord(String strSerialNumber,String strFirstName,String strLastName,String strEmailAddress){
    	
    	//Fill serial number
    	
    	this.setSerialNumber(strSerialNumber);
    	
        //Fill first name

        this.setFirstName(strFirstName);

        //Fill Last name

        this.setLastName(strLastName);
        
        //Fill Email address
        
        this.setEmailAddress(strEmailAddress);

        //Click save record button

        this.clickOnSaveRecordButton();  
        
    }
    
  //Gets serial number
    public String getSerialNumberData(){
    	return driver.findElement(serialNumCellData).getText();
    }
    
    //Gets fname text
    public String getFnameData(){
    	return driver.findElement(fnameCellData).getText();
    }
    
    //Gets lname text
    public String getLnameData(){
    	return driver.findElement(lnameCellData).getText();
    }
    
    //Gets email text
    public String getEmailData(){
    	return driver.findElement(emailCellData).getText();
    }

}
