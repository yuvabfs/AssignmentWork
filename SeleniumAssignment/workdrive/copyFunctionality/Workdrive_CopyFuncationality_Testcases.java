package workdrive.copyFunctionality;

import java.io.BufferedReader;
import java.time.Duration;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import workdrive.actionClasses.AccountAuthAction;
import workdrive.actionClasses.CopyPopUpAction;
import workdrive.actionClasses.FileKeyboardShortcuts;
import workdrive.actionClasses.FileListingAction;
import workdrive.actionClasses.FileTopMenuAction;
import workdrive.actionClasses.HomePageAction;
import workdrive.actionClasses.LeftPanelAction;
import workdrive.actionClasses.SignInAction;
import workdrive.actionClasses.TeamFolderSection;
import workdrive.actionClasses.TopMenuAction;
import workdrive.actionClasses.UploadDownloadPopUpAction;

@Listeners(WebDriverListenerClass.class)
public class Workdrive_CopyFuncationality_Testcases {
	WebDriver driver;
	
	HomePageAction homePageAction;
	SignInAction signInAction;
	AccountAuthAction accountAuthAction;
	TopMenuAction topMenuAction;
	LeftPanelAction leftPanelAction;
	FileListingAction fileListingAction;
	CopyPopUpAction copyPopUpAction;
	WorkDriveAutomationUtil workDriveAutomationUtil;
	UploadDownloadPopUpAction uploadDownloadPopUpAction;
	FileTopMenuAction fileTopMenuAction;
	CookieDataFunctions cookieDataFunctions;
	FileKeyboardShortcuts fileKeyboardShortcuts;
	TeamFolderSection teamFolderSection;
	
	
	
	@BeforeMethod
	public void setup()
	{
		//driver = new EdgeDriver();
		
		driver=new FirefoxDriver();
		
		//ChromeOptions options = new ChromeOptions();
		//options.addArguments("--remote-allow-origins=*");
		//driver = new ChromeDriver(options);
		
		homePageAction =new HomePageAction(driver);
		signInAction =new SignInAction(driver);
		accountAuthAction =new AccountAuthAction(driver);
		topMenuAction=new TopMenuAction(driver);
		leftPanelAction= new LeftPanelAction(driver);
		fileListingAction=new FileListingAction(driver);
		copyPopUpAction= new CopyPopUpAction(driver);
		workDriveAutomationUtil=new WorkDriveAutomationUtil(driver);
		uploadDownloadPopUpAction= new UploadDownloadPopUpAction(driver);
		fileTopMenuAction =new FileTopMenuAction(driver);
		cookieDataFunctions= new CookieDataFunctions(driver);
		fileKeyboardShortcuts=new FileKeyboardShortcuts(driver);
		teamFolderSection=new TeamFolderSection(driver);
		  
		//cookieDataFunctions.getStoredCookieData();
		driver.manage().window().maximize() ;
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
		
		
		driver.get("https://accounts.zoho.in/");
		
		cookieDataFunctions.manualCookieSetup();
		
		
		 driver.get("https://accounts.zoho.in/");
		 waitForLoading(5000);
	        driver.get("https://workdrive.zoho.in");
	       waitForLoading(10000);

	}

	
  



	@Test( dataProvider="FileRightClick", dataProviderClass =DataProviderClass.class )
  public void uc_01_CopyFileUsingRightClickOption(String fileName, String sourceMainFolder,String sourcePath, String destinationMainFolder, String destinationPath) {
	  
			 
	  //signInAction.loginToWorkdrive("yuvabfs@gmail.com", "TestAccount123");
   	  // cookieDataFunctions.writeCookieData();
	  
	  //String fileName="TestFile_CopyFunctionality";
	  
	  //String sourceMainFolder="My Folders";
	  
	  //String sourcePath="";
	  
	  //String destinationMainFolder="My Folders";
	  
	  //String destinationPath="";
	  
	  String fileOperation="Copy To...";
	  
	  boolean destinationHasSameFileName=false;
	  
	  String copiedFileName=(fileName.split("[.]"))[0];
	  System.out.println("copiedFileName1:"+copiedFileName);

//To make sure Logged-in with stored cookie data  
	  Assert.assertTrue(topMenuAction.isLoginSuccess(),"Facing Issue with Login");
	  
//Verify if there is any duplicate file present already at the destination folder
	  leftPanelAction.chooseLeftPaneOption(destinationMainFolder);
		
	  fileListingAction.navigateToFolder(destinationPath);
	  
	  if(fileListingAction.isFilePresent(fileName) || fileListingAction.isFilePresent(copiedFileName) )
	  {  destinationHasSameFileName=true; // if true--> the copied file name has to be concatenated with time stamp
	  System.out.println("destinationHasSameFileName"+destinationHasSameFileName);
	  
	  }
//Navigate to the Source File Location 
	  if(!leftPanelAction.isPageLoaded(sourceMainFolder))  // If source main folder is already landing page of the account(By default- My Fodlers is the Landing page)
	  leftPanelAction.chooseLeftPaneOption(sourceMainFolder);
	  
	  Assert.assertTrue(leftPanelAction.isPageLoaded(sourceMainFolder),"Facing Issue in loading Source Folder");;
	  
	  fileListingAction.navigateToFolder(sourcePath);
	  
	  
	  //Choose the desired File (Then Make sure that desired file is selected or rightCLicked)
	  //      and
	  //Perform Copy Operation
	  fileListingAction.rightClickFileAndChoose(fileName,fileOperation);
	  
	 
	  //workDriveAutomationUtil.takeScreenShot("D:\\ZOHO\\WorkDrive Automation Script\\ScreenShot\\uc_01_CopyFileUsingRightClickOption\\copyDialog.png");
	 
	  Assert.assertTrue(copyPopUpAction.isCopyPopUpDisplayed(),"Copy Pop-up is not getting displayed");
	  
	  
	  if(!(sourceMainFolder.equals(destinationMainFolder) && sourcePath.equals(destinationPath))) //Default destination location in copy pop-up dialog is Source file location
	  {
		  copyPopUpAction.selectDestination(destinationMainFolder);
		  
		  copyPopUpAction.findAndClickSubFolder(destinationPath);
	  }
	  //copyPopUpAction.selectDestination(destinationFolder);
	  
	  copyPopUpAction.confirmCopy();
	  
	 
	  uploadDownloadPopUpAction.waitingForCopyActionToBeCompleted();
	  
	  
	 
	  
	  if(sourceMainFolder.equals(destinationMainFolder) || destinationHasSameFileName)
	  { 
		  System.out.println("Duplicate found!");
		  copiedFileName=copiedFileName.concat(" "+workDriveAutomationUtil.getTimeStamp());
	  }
	  System.out.println("copiedFileName2:"+copiedFileName);
	  
	  //Verify if the file Copied successfully
	  Assert.assertTrue(uploadDownloadPopUpAction.verifyCopiedPopUp(copiedFileName),"File not listed in 'Item_Copied_Successfuly' pop-up");
	 

	  Assert.assertTrue(verifyCopiedFileAtDestination(copiedFileName,destinationMainFolder,destinationPath ),"Copied File not found at Destination");
 
	  //To verify fileLocator icon in 'Item_Copied_Successfuly' pop-up
	  String currentDestinationPageTitle=driver.getTitle();
	  
	  uploadDownloadPopUpAction.clickFileLocator();
	  
	  String fileLocatorNavigatedPageTitle=driver.getTitle();
	  
	  Assert.assertEquals(currentDestinationPageTitle,fileLocatorNavigatedPageTitle,"Mismatch between Copied file Destination and File Locator Navigated Page Title");
	  
	  

  }
  
    
  @Test (dataProvider="TopMenuMoreAction", dataProviderClass =DataProviderClass.class)
  public void uc_02_CopyFileUsingMoreActionsIcon(String fileName, String sourceMainFolder,String sourcePath, String destinationMainFolder, String destinationPath)
  {

	  //String fileName="Test File";
	  	 	  
 //Need to navigating through subFolders
	 // String sourcePath="Internal Share/Provide Access to Internal Team/TestFile";
	   sourceMainFolder="My Folders";
	  
	 // String
	 // String destinationMainFolder="A_Test Team Folder";
	  
	 // String destinationPath="A Team Folder's subFolder";
	  
	  String fileOperation="Copy To...";
	  
	  String topMenuButton="More actions";
	  
	  boolean destinationHasSameFileName=false;
	  
	  String copiedFileName=fileName.split("[.]")[0];
	  
 //Navigate to the Source File Location 
	 // signInAction.loginToWorkdrive("yuvabfs@gmail.com", "TestAccount123");
  			//writeCookieData();
//To make sure Logged-in with stored cookie data  
	  Assert.assertTrue(topMenuAction.isLoginSuccess(),"Facing Issue with Login");
	  
//Verify if there is any duplicate file present already at the destination folder	  
	  leftPanelAction.chooseLeftPaneOption(destinationMainFolder);
		
	  fileListingAction.navigateToFolder(destinationPath);
	  
	  if(fileListingAction.isFilePresent(fileName))
		  destinationHasSameFileName=true;
	  
//Navigate to the Source File Location 
	  if(!leftPanelAction.isPageLoaded(sourceMainFolder))  // If source main folder itself is landing page of the account(By default- My Fodlers is the Landing page)
		  leftPanelAction.chooseLeftPaneOption(sourceMainFolder);
	 	
	  Assert.assertTrue(leftPanelAction.isPageLoaded(sourceMainFolder),"Facing Issue in loading Source Folder");;
	  
	  fileListingAction.navigateToFolder(sourcePath);
	  
	  
 //Choose the desired File 	  
	  Assert.assertTrue(fileListingAction.isFilePresent(fileName),"Input File not found at Source destination");
	  
	  fileListingAction.findAndSelectFile(fileName);
	  
	  Assert.assertTrue(fileListingAction.isFileSelected(fileName),"File not selected yet");
	 

 //Perform Copy operation 
	  fileTopMenuAction.clickFileTopMenuButton(topMenuButton);
	  		// System.out.println( driver.getPageSource());
	  
	  fileListingAction.chooseFileOperation(fileOperation);

	  Assert.assertTrue(copyPopUpAction.isCopyPopUpDisplayed(),"Copy Pop-up is not getting displayed");
	  
	  
	  if(!(sourceMainFolder.equals(destinationMainFolder) && sourcePath.equals(destinationPath))) //Default destination location in copy pop-up dialog is Source file location
	  {
		  copyPopUpAction.selectDestination(destinationMainFolder);
		  
		  copyPopUpAction.findAndClickSubFolder(destinationPath);
	  }
	  copyPopUpAction.confirmCopy();

	  
	  waitForLoading(20000);		//Wait 20 seconds for copy operation to be completed
	  
	  if((sourceMainFolder+"/"+sourcePath).equals(destinationMainFolder+"/"+destinationPath) || destinationHasSameFileName)
	  {
	
		  copiedFileName.concat(" "+workDriveAutomationUtil.getTimeStamp());
	  }
	  
//Verify if the file Copied successfully
	  //Assert.assertTrue(uploadDownloadPopUpAction.copiedItemPopUp.isDisplayed(),"Item_Copied_Successfuly pop-up is getting d");
	 
	  
	  Assert.assertTrue(uploadDownloadPopUpAction.verifyCopiedPopUp(copiedFileName),"File not listed in 'Item_`Copied_Successfuly' pop-up");
		 
		  
	  Assert.assertTrue(verifyCopiedFileAtDestination(copiedFileName,destinationMainFolder,destinationPath ),"CopiednFile not found at Destination");

//To verify fileLocator icon in 'Item_Copied_Successfuly' pop-up
	  String currentDestinationPageTitle=driver.getTitle();
	  
	  uploadDownloadPopUpAction.clickFileLocator();
	  
	  String fileLocatorNavigatedPageTitle=driver.getTitle();
	  
	  Assert.assertEquals(currentDestinationPageTitle,fileLocatorNavigatedPageTitle,"Mismatch between Copied file Destination and File Locator Navigated Page Title");
	  
  }
  
  
  @Test
  public void uc_03_CopyFile_AtViewerTeamFolder_UsingKeyBoardShortCut()
  {

	  String fileName="Test File";
	  
	  String fileOperation="Copy To...";
	  
	  String topMenuButton="More actions";
	  
 //Need to navigate through subFolders
	  String sourceMainFolder="My Folders";
	  
	  String sourcePath="Internal Share/Provide Access to Internal Team/TestFile";
	  
	  String destinationMainFolder="Pvt Folder for LABEL DA";
	  
	 // String destinationPath="A Team Folder's subFolder";
	  
 //Navigate to the Source File Location 
	  //signInAction.loginToWorkdrive("yuvabfs@gmail.com", "TestAccount123");
  			//writeCookieData();
	  
	  Assert.assertTrue(topMenuAction.isLoginSuccess(),"Facing Issue with Login");
	  
	  leftPanelAction.chooseLeftPaneOption(sourceMainFolder);
		
	  fileListingAction.navigateToFolder(sourcePath);
	  
 //Choose the desired File 	  
	  
	  Assert.assertTrue(fileListingAction.isFilePresent(fileName));
	  
	  fileListingAction.findAndSelectFile(fileName);

 //Perform Copy operation 
	  
	  fileKeyboardShortcuts.pressShortCut(Keys.CONTROL,"c");
	  
	  
	  leftPanelAction.chooseLeftPaneOption(destinationMainFolder);
		
	  //fileListingAction.navigateToFolder(destinationPath);
	  
	  
	  fileKeyboardShortcuts.pressShortCut(Keys.CONTROL,"v");
	 
	  
	  Assert.assertTrue(fileListingAction.isCopyAccessDeniedAlertShown(),"Copy Access Denied Alert not getting Displayed");
	  


  }
 
  
  @Test( dataProvider="CreatingNewFolder", dataProviderClass =DataProviderClass.class )
  public void uc_04_CopyFileinNewFolderUsingRightClickOption(String fileName, String sourceMainFolder,String sourcePath, String destinationMainFolder, String destinationPath,String newFolderName) {
	  
			 
	  //signInAction.loginToWorkdrive("yuvabfs@gmail.com", "TestAccount123");
   	  // cookieDataFunctions.writeCookieData();
	  
	  //String fileName="TestFile_CopyFunctionality";
	  
	  //String sourceMainFolder="My Folders";
	  
	  //String sourcePath="";
	  
	  //String destinationMainFolder="My Folders";
	  
	  //String destinationPath="";
	  
	  String fileOperation="Copy To...";
	  
	  //boolean duplicateFileName=false;
	  
	  //String copiedFileName=(fileName.split("[.]"))[0];
	  
	  boolean duplicateNewFolderName=false;
	  
	  boolean noFolderName=false;
	 // System.out.println("copiedFileName1:"+copiedFileName);

//To make sure Logged-in with stored cookie data  
	  Assert.assertTrue(topMenuAction.isLoginSuccess(),"Facing Issue with Login");
	  
//Verify if there is any duplicate file present already at the destination folder
	  leftPanelAction.chooseLeftPaneOption(destinationMainFolder);
		
	  fileListingAction.navigateToFolder(destinationPath);
	  
	  if(newFolderName.length()>0 && newFolderName!=null)
	  {
		  fileListingAction.filterFileListing("Folders");
		  
		  if(fileListingAction.isFilePresent(newFolderName))
		  {
			  duplicateNewFolderName=true;
		  }	 
		  fileListingAction.filterFileListing("All");
		  
	  }
	  else if(newFolderName.length()==0 && newFolderName!=null)
	  {
		  noFolderName=true;
	  }
	 
//Navigate to the Source File Location 
	  if(!leftPanelAction.isPageLoaded(sourceMainFolder))  // If source main folder is already landing page of the account(By default- My Fodlers is the Landing page)
	  leftPanelAction.chooseLeftPaneOption(sourceMainFolder);
	  
	  Assert.assertTrue(leftPanelAction.isPageLoaded(sourceMainFolder),"Facing Issue in loading Source Folder");;
	  
	  fileListingAction.navigateToFolder(sourcePath);
	  
	  waitForLoading(2000);
	  
	  //Choose the desired File (Then Make sure that desired file is selected or rightCLicked)
	  //      and
	  //Perform Copy Operation
	  fileListingAction.rightClickFileAndChoose(fileName,fileOperation);
	  
	 
	  
	  Assert.assertTrue(copyPopUpAction.isCopyPopUpDisplayed(),"Copy Pop-up is not getting displayed");
	  
	  if(!(sourceMainFolder.equals(destinationMainFolder) && sourcePath.equals(destinationPath))) //Default destination location in copy pop-up dialog is Source file location
	  {
		  copyPopUpAction.selectDestination(destinationMainFolder);
		  
		  copyPopUpAction.findAndClickSubFolder(destinationPath);
	  }
	  
	  copyPopUpAction.clickCreateNewFolderButton();
	  
	  copyPopUpAction.enterNewFolderName(newFolderName);
	  
	  waitForLoading(3000);
	  

	  if(duplicateNewFolderName)
	  {
		  String expectedErrorMessage="This name already exists.\n"
		  		+ "Please try another name.";
		  String actualErrorMessage=copyPopUpAction.getDuplicateNewFolderErrorMessage();
		  Assert.assertEquals(expectedErrorMessage,actualErrorMessage);
		  System.out.println(copyPopUpAction.getDuplicateNewFolderErrorMessage());
	  }
	  else if(noFolderName)
	  {
		  String expectedErrorMessage="Please provide a name for the new folder.";
			  String actualErrorMessage=copyPopUpAction.getNoNameNewFolderErrorMessage();
			  Assert.assertEquals(expectedErrorMessage,actualErrorMessage);
			  System.out.println(copyPopUpAction.getNoNameNewFolderErrorMessage());
	  }
	  else 		//When NewFolder name is valid (positive scenario)
	  {
		  copyPopUpAction.findAndClickSubFolder(newFolderName);
		  destinationPath=destinationPath.concat("/"+newFolderName);

		  copyPopUpAction.confirmCopy();
	  
	 
	  waitForLoading(20000);
	 
	  System.out.println("destinationPath"+destinationPath);
	  
	  //Verify if the file Copied successfully
	  Assert.assertTrue(uploadDownloadPopUpAction.verifyCopiedPopUp(fileName),"File not listed in 'Item_Copied_Successfuly' pop-up");
	 

	  Assert.assertTrue(verifyCopiedFileAtDestination(fileName,destinationMainFolder,destinationPath ),"Copied File not found at Destination");
 
	  //To verify fileLocator icon in 'Item_Copied_Successfuly' pop-up
	  String currentDestinationPageTitle=driver.getTitle();
	  
	  uploadDownloadPopUpAction.clickFileLocator();
	  
	  String fileLocatorNavigatedPageTitle=driver.getTitle();
	  
	  Assert.assertEquals(currentDestinationPageTitle,fileLocatorNavigatedPageTitle,"Mismatch between Copied file Destination and File Locator Navigated Page Title");
	  
	  }

  }
  

  @Test(enabled=true)
  public void uc_05_Verify_SharedWithMeFolderList_AtCopyPopUpDestinationDropDown()
  {
	  String destinationMainFolder="Shared with Me";
	  
	  String sourceMainFolder="My Folders";
	  
	  String fileName="SWM Folder List";
	  
	  String fileOperation="Copy To...";
	  
	 //Make sure that account logged-in successfully
	  Assert.assertTrue(topMenuAction.isLoginSuccess(),"Facing Issue with Login");
	  
	 //Navigate to Shared with Me Folder
	  leftPanelAction.chooseLeftPaneOption(destinationMainFolder);
	  
	  fileListingAction.filterFileListing("Folders");
	  
	  
	  List<String> foldersWithAtleastEditorAccess=fileListingAction.getFoldersWithAtleastEditorAccess();
	  
	  Collections.sort(foldersWithAtleastEditorAccess);
	  
	  System.out.println(foldersWithAtleastEditorAccess);
	  
	  
	  //Navigate to SourceFolder
	  leftPanelAction.chooseLeftPaneOption(sourceMainFolder);
	  
	  Assert.assertTrue(leftPanelAction.isPageLoaded(sourceMainFolder),"Facing Issue in loading Source Folder");;
	  
	//Perform Copy Operation
	  fileListingAction.rightClickFileAndChoose(fileName,fileOperation);
	  
	 
	  
	  Assert.assertTrue(copyPopUpAction.isCopyPopUpDisplayed(),"Copy Pop-up is not getting displayed");
	  
	  
	  copyPopUpAction.selectDestination(destinationMainFolder);
		  
	  List<String> foldersListedAsCopyDestination=copyPopUpAction.getSubFolderList();
	  
	  Collections.sort(foldersListedAsCopyDestination);
	  
	  System.out.println(foldersListedAsCopyDestination);
	  
	  Assert.assertEquals(foldersWithAtleastEditorAccess,foldersListedAsCopyDestination,"Error found in Shared with Me Folders list");
	  
	  Assert.assertTrue(copyPopUpAction.isNewFolderButtonDisabled());//New Folder Button should be disabled when Shared with Me parent folder is chosen as copy destination
	  
	  Assert.assertTrue(copyPopUpAction.isconfirmCopyButtonDisabled());//Confirm Copy button should be disabled when Shared with Me parent folder is chosen as copy destination
	  
	  
  }
  
  
  @Test ( enabled=true)
  public void uc_06_Verify_TeamFolderList_AtCopyPopUpDestinationDropDown()
  {

	  String fileName="Test File";
	  	 	  

	  String sourceMainFolder="My Folders";
	  
	  String sourcePath="Internal Share/Provide Access to Internal Team/Test File";
	  

	  
	  String fileOperation="Copy To...";
	  
	  String topMenuButton="More actions";
	  
	  

	 // signInAction.loginToWorkdrive("yuvabfs@gmail.com", "TestAccount123");
  			//writeCookieData();
//To make sure Logged-in with stored cookie data  
	  Assert.assertTrue(topMenuAction.isLoginSuccess(),"Facing Issue with Login");
	  
	  
//Storing Team Folder names of which has Editor/Organizer/Admin access
	  List<String> teamFoldersWithAtleastEditorAccess=new ArrayList<>();
	  
	  
	  
	  for(String teamFolderName:leftPanelAction.getTeamfoldersWithAtleastEditorAccess())
	  {
		  waitForLoading(2000);
		  leftPanelAction.chooseLeftPaneOption(teamFolderName);
		  
		  String accountRole=teamFolderSection.getAccountUserRole();
		  
		  if(accountRole.equals("Admin") || accountRole.equals("Organizer")|| accountRole.equals("Editor"))
			  teamFoldersWithAtleastEditorAccess.add(teamFolderName);
		  driver.navigate().back();
			
	  }
	  Collections.sort(teamFoldersWithAtleastEditorAccess);
	  
	//Navigate to the Source File Location  
	  if(!leftPanelAction.isPageLoaded(sourceMainFolder))  // If source main folder itself is landing page of the account(By default- My Fodlers is the Landing page)
		  leftPanelAction.chooseLeftPaneOption(sourceMainFolder);
	 	
	  Assert.assertTrue(leftPanelAction.isPageLoaded(sourceMainFolder),"Facing Issue in loading Source Folder");;
	  
	  fileListingAction.navigateToFolder(sourcePath);
	  
	  
	  
 //Choose the desired File 	  
	  Assert.assertTrue(fileListingAction.isFilePresent(fileName),"Input File not found at Source destination");
	  
	  fileListingAction.findAndSelectFile(fileName);
	  
	  Assert.assertTrue(fileListingAction.isFileSelected(fileName),"File not selected yet");
	  
//Perform Copy operation 
	  fileTopMenuAction.clickFileTopMenuButton(topMenuButton);
	  		// System.out.println( driver.getPageSource());
	  
	  fileListingAction.chooseFileOperation(fileOperation);

	  Assert.assertTrue(copyPopUpAction.isCopyPopUpDisplayed(),"Copy Pop-up is not getting displayed");
	  
	  
	  copyPopUpAction.clickdestinationDropDownIcon();
	  
	  
	  List<String> dropDownTeamFolders=copyPopUpAction.getTeamFoldersDestinationDropDown();
	  

	  Collections.sort(dropDownTeamFolders);
	  Assert.assertEquals(dropDownTeamFolders,teamFoldersWithAtleastEditorAccess,"Team Folder List Shown in Destination drop down is not as expected");
	  
	  
	  
	  
	  for(String eachFolderFromDropDown:teamFoldersWithAtleastEditorAccess)
	  {
		  copyPopUpAction.selectDestination(eachFolderFromDropDown);
		  
		  waitForLoading(2000);
		  Assert.assertFalse(copyPopUpAction.isNewFolderButtonDisabled());//New Folder Button is enabled when Shared with Me parent folder is chosen as copy destination
		  
		  Assert.assertFalse(copyPopUpAction.isconfirmCopyButtonDisabled());//Confirm Copy button  is enabled when Shared with Me parent folder is chosen as copy destination
		  
		    
	  }
	  
  }

  
  @AfterMethod
  public void closeSetUp()
  {
	 // driver.close();
  }
  
  
 
  
  
  private void waitForLoading(int i) {
      try {
			Thread.sleep(i);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
}
  
  public boolean verifyCopiedFileAtDestination(String copiedFileName, String destination, String destinationPath)
	{
		
		boolean fileFound=false;
		
		leftPanelAction.chooseLeftPaneOption(destination);
		
		if(destinationPath.length()!=0)
		{
			fileListingAction.navigateToFolder(destinationPath);
		}
		if(fileListingAction.isFilePresent(copiedFileName))
				fileFound=true;
		
			
		
		return fileFound;
	}
	

  
  //enabled=false
  @Test( enabled=false)
public void tc_02_CopyFileWithinMyFolderSubFolder() {
  
  
  
  String fileName="Test File";
  
  String fileOperation="Copy To...";
  
  String topMenuButton="More actions";
  
  //Need to navigating through subFolders
  String sourceMainFolder="My Folders";
  
  String sourcePath="Internal Share/Provide Access to Internal Team/TestFile";
  
  String destinationMainFolder="My Folders";
  
  String destinationPath="Internal Share/Provide Access to Internal Team";
  
  boolean destinationHasSameFileName=false;
 //Navigate to the Source File Location 
  	//signInAction.loginToWorkdrive("yuvabfs@gmail.com", "TestAccount123");
// cookieDataFunctions.writeCookieData();
  
  Assert.assertTrue(topMenuAction.isLoginSuccess(),"Facing Issue with Login");
  
  leftPanelAction.chooseLeftPaneOption(destinationMainFolder);
	
  fileListingAction.navigateToFolder(destinationPath);
  
  if(fileListingAction.isFilePresent(fileName))
	  destinationHasSameFileName=true;
  
  
  leftPanelAction.chooseLeftPaneOption(sourceMainFolder);
	
  fileListingAction.navigateToFolder(sourcePath);
  
  
  fileListingAction.findAndSelectFile(fileName);
	
  fileTopMenuAction.clickFileTopMenuButton(topMenuButton);
 // System.out.println( driver.getPageSource());
  
  fileListingAction.chooseFileOperation(fileOperation);
  	  

  Assert.assertTrue(copyPopUpAction.isCopyPopUpDisplayed(),"Copy Pop-up is not getting displayed");
  
  copyPopUpAction.selectDestination(destinationMainFolder);
  
  copyPopUpAction.findAndClickSubFolder(destinationPath);
  
  copyPopUpAction.confirmCopy();
 
  
  try {
		Thread.sleep(20000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  
  //Verify if the file Copied successfully
  Assert.assertTrue(uploadDownloadPopUpAction.verifyCopiedPopUp(fileName),"Item Copied Success message pop up is not displayed");
 
  String copiedFileName=fileName;
  if((sourceMainFolder+"/"+sourcePath).equals(destinationMainFolder+"/"+destinationPath) || destinationHasSameFileName)
	  copiedFileName.concat(" "+workDriveAutomationUtil.getTimeStamp());
	  
  Assert.assertTrue(verifyCopiedFileAtDestination(copiedFileName,destinationMainFolder,destinationPath ),"CopiednFile not found at Destination");

  
}



}
