# ZerionCodingActivity
Coding Activity
# Prerequisites

Softwares to install on your local machine:
1. apache-maven-3.5.0

Download Link: https://maven.apache.org/download.cgi

# Installation Instructions:
Copy the bin folder path of apache maven from your local machine (i.e:C:\apache-maven-3.5.0-bin\apache-maven-3.5.0\bin) and set path in Environment variables (Right click on Mycomputer > go to properties > go to advanced system settings > click on "Environment Variables" > set path.).

# Web Driver Versions Used and Download Links

Firefox Webdriver Version: 0.16.1

Download Link: https://github.com/mozilla/geckodriver/releases

Chrome: 2.26

Download Link: https://chromedriver.storage.googleapis.com/index.html

Microsoft Edge: Release 15063 Version: 4.15063 | Edge version supported: 15.15063 

Download Link: https://developer.microsoft.com/en-us/microsoft-edge/tools/webdriver/

After downloading the above webdrivers it is required to change the folder path in constants.java file located under src/test/java/util folder.

# Browser Versions Used:
Firefox web browser version: 53.0

Chrome web browser version: 58.0.3029.81

Microsoft Edge browser version: 38.14393.1066.0

# Running the tests
1. Create a folder and copy code into that folder.
2. Open Command Prompt
3. Change directory to where pom.xml file is located on your local machine.
4. mvn clean install

By default tests will run on firefox browser. please uncomment the code in testng.xml file to run tests on various browsers.

Tests are designed to perform following actions:
Test Case1:
1. Confirm correct user is logged in. You can compare username with login credentials to
what is shown in the top right corner.
2. After new record is create, confirm that the record is displayed in the list view and that
the data is correct.
Test Case2:
Confirm correct user is logged in. You can compare username with login credentials to
what is shown in the top right corner.
2. After creating a new user, confirm that user is now shown in the main Add/Edit user
page.
3. Confirm correct user is logged in.
