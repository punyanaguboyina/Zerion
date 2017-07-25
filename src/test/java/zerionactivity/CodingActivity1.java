package zerionactivity;

import java.io.IOException;
import java.util.Properties;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import excelExportAndFileIO.ReadTestDataFile;
import operation.ReadObject;
import pages.IFormCreateRecord;
import pages.IFormHomePage;
import pages.IFormLogin;

public class CodingActivity1 {

	 WebDriver driver;
	 
	 WebDriverWait wait;

	 IFormLogin iformLogin;

	 IFormHomePage iformHomePage;
	 
	 IFormCreateRecord iformCreateRecord;
	 
	 ReadObject object = new ReadObject();
	
    /**
     * This test is to launch a browsers(Firefox, Chrome, Edge etc) based on the parameter passed from the test suite in testng.xml file.
     * Following actions are performed in this test:
     * 1. Navigate to https://app.iformbuilder.com/exzact/dataViews.php
     * 2. Create a record
     * @param browser
     * @throws Exception
     */
	 @BeforeTest
	 @Parameters("browser")
     public void launchBrowser(String browser) throws Exception {
		 
		 Properties objRepo = object.getObjectRepository();
		 
         try{
        	 
		//Check if parameter passed from TestNG is 'firefox'
        	 
			if(browser.equalsIgnoreCase("Firefox")){
				
				//create firefox instance
				
				System.setProperty("webdriver.gecko.driver", objRepo.getProperty("FIRE_FOX_DRIVER_PATH"));
				
				driver = new FirefoxDriver();
				
			}
			
		//Check if parameter passed as 'chrome'
			
			else if(browser.equalsIgnoreCase("Chrome")){
				
				//set path to chromedriver.exe
				
				System.setProperty("webdriver.chrome.driver", objRepo.getProperty("CHROME_DRIVER_PATH"));
				
				//create chrome instance
				
				driver = new ChromeDriver();
				
			}
			
		//Check if parameter passed as 'Edge'
			
			else if(browser.equalsIgnoreCase("Edge")){
				
				//set path to Edge.exe
				
				System.setProperty("webdriver.edge.driver", objRepo.getProperty("EDGE_DRIVER_PATH"));
				
				//create Edge instance
				
				driver = new EdgeDriver();
				
			}
			else{
				
				//If no browser passed throw exception
				
				throw new Exception("Browser is not correct");
				
			}
			
         }catch(Exception e){
        	 
        	 System.err.println(e);
        	 
         }
         
         driver.get(objRepo.getProperty("BASE_URL"));  
         
  }

	 
	 /**
	  * This test is to confirm correct user is logged in.
	 * @throws IOException 
	  */
	 @Test(priority=0)
	public void confrimUser() throws IOException {
		 
		 Properties objRepo = object.getObjectRepository();
		 
		//Create Login Page object
		 
		  iformLogin = new IFormLogin(driver);
		 	
		//login to application
		 	
		  iformLogin.loginToIForm(objRepo.getProperty("IFORM_USERNAME"), objRepo.getProperty("IFORM_PASSWORD"));
		 	
		// go the next page
		 	
		  iformHomePage = new IFormHomePage(driver);
		 
		//Verify home page

		  Assert.assertTrue(iformHomePage.getHomePageDashboardUserName().toLowerCase().contains(objRepo.getProperty("IFORM_USERNAME")));
		 
	 }
	 
	 /**
	  * Create record.
	 * @throws IOException 
	 * @throws InterruptedException 
	  */
	 @Test(priority=1)
	 public void createRecord() throws IOException{
		 		 
		 ReadTestDataFile file = new ReadTestDataFile();
		 
		 iformHomePage = new IFormHomePage(driver);
		 
		 iformCreateRecord = new IFormCreateRecord(driver);		 
		 
		//click on form ID
		 
		 iformHomePage.clickOnForm();
			 
		 int oldRecordSize = iformCreateRecord.getRecordsCount();
		 
		 iformCreateRecord.clickOnCreateNewRecordButton();
		  
		//Read keyword sheet
			 
		 Sheet sheet = file.readExcel(System.getProperty("user.dir")+"\\","TestData.xlsx" , "Data");
		    
	    //access 2nd rows of excel file to read test data
		    
		 Row row = sheet.getRow(1);
		  
		 iformCreateRecord.createRecord(row.getCell(0).toString(),row.getCell(1).toString(), row.getCell(2).toString(), row.getCell(3).toString());
		  
		 int newRecordSize = iformCreateRecord.getRecordsCount();
		  
		 Integer serialNum = (int) Math.round(row.getCell(0).getNumericCellValue());
		  
		 Assert.assertTrue(newRecordSize-oldRecordSize>0 && serialNum.toString().equals(iformCreateRecord.getSerialNumberData()) && row.getCell(1).toString().equals(iformCreateRecord.getFnameData()) && row.getCell(2).toString().equals(iformCreateRecord.getLnameData()) && row.getCell(3).toString().equals(iformCreateRecord.getEmailData()));  		  
				      		
	 }
	
	/**
	 * This test is to kill the web browser after executing the test script.
	 */
	 @AfterTest
     public void terminateBrowser(){
         driver.close();
     }

}
