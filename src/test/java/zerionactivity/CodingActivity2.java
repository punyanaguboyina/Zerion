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
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import excelExportAndFileIO.ReadTestDataFile;
import excelExportAndFileIO.WriteTestDataFile;
import operation.ReadObject;
import pages.IFormCreateNewUser;
import pages.IFormHomePage;
import pages.IFormLogin;

public class CodingActivity2 {


	 WebDriver driver;
	 
	 WebDriverWait wait;

	 IFormLogin iformLogin;

	 IFormHomePage iformHomePage;
	 
	 IFormCreateNewUser iformCreateNewUser;
	 
	 ReadObject object = new ReadObject();
	 
	 boolean isUserNotCreated = false;
	 
	
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
	  * Create new user.
	 * @throws IOException 
	 * @throws InterruptedException 
	  */
	 @Test(priority=1)
	 public void createNewUser() throws IOException{
		 
		 ReadTestDataFile file = new ReadTestDataFile();
		 
		 iformCreateNewUser = new IFormCreateNewUser(driver);
		   
		  iformCreateNewUser.clickAddUserFromMenu();
		  
		  int oldUsersCount = iformCreateNewUser.getUsersCount();
		 	
		  iformCreateNewUser.clickNewUserButton();
		  
		//Read keyword sheet
			 
		  Sheet sheet = file.readExcel(System.getProperty("user.dir")+"\\","TestData.xlsx" , "NewUserData");
		    
	    //access 2nd rows of excel file to read test data
		    
		  Row row = sheet.getRow(1);
		  
		  isUserNotCreated = iformCreateNewUser.createUser(row.getCell(0).toString(), row.getCell(1).toString(), row.getCell(2).toString(), row.getCell(3).toString(), row.getCell(4).toString(), row.getCell(5).toString(), row.getCell(6).toString(), row.getCell(7).toString(), row.getCell(8).toString());
		  
		 //Create an array with the data in the same order in which you expect to be filled in excel file

	        String[] valueToWrite = {row.getCell(0).toString(),row.getCell(4).toString()};

	        //Create an object of current class

	        WriteTestDataFile objExcelFile = new WriteTestDataFile();

	        //Write the file using file name, sheet name and the data to be filled

	        objExcelFile.writeExcel(System.getProperty("user.dir")+"\\","TestData.xlsx","NewUserCredentials",valueToWrite);
		  
		  int newUsersCount = 0;
		  
		  if(isUserNotCreated){
			  
			  newUsersCount = oldUsersCount;
			  
		  }else{
			  newUsersCount = iformCreateNewUser.getUsersCount();
		  }
			  
		  Assert.assertTrue(newUsersCount-oldUsersCount>0);
 		   
	 }
	 
	 /**
	  * Verify new user.
	 * @throws IOException 
	  */
	 @Test(priority=2)
	 public void verifyNewUser() throws IOException{
		 
		 ReadTestDataFile file = new ReadTestDataFile();
		 
		 if(isUserNotCreated){
			 throw new SkipException("Skip this test.");
		 }else{
		 
		 Properties objRepo = object.getObjectRepository();
		 
			//Create Login Page object
			 
			  iformLogin = new IFormLogin(driver);
			  
			  iformCreateNewUser.clickOnLogOut();
			  
			//Read keyword sheet
				 
			  Sheet sheet = file.readExcel(System.getProperty("user.dir")+"\\","TestData.xlsx" , "NewUserCredentials");
			  
			  Row lastRow = sheet.getRow(sheet.getLastRowNum());
			 	
			//login to application
			  
			  iformLogin.loginToIForm(lastRow.getCell(0).toString(), lastRow.getCell(1).toString());
			 	
			// go the next page
			 	
			  iformHomePage = new IFormHomePage(driver);
			 
			//Verify home page

			  Assert.assertTrue(iformHomePage.getHomePageDashboardUserName().toLowerCase().contains(lastRow.getCell(0).toString()));
			  
		 }
		 
	 }
	
	/**
	 * This test is to kill the web browser after executing the test script.
	 */
	 @AfterTest
    public void terminateBrowser(){
        driver.close();
    }


}
