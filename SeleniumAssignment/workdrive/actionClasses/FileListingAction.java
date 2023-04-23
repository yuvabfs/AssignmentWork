package workdrive.actionClasses;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import workdrive.elementClasses.FileListingElements;

public class FileListingAction {
	WebDriver driver;
	
	FileListingElements fileListingElements=new FileListingElements();
	public FileListingAction(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public boolean isFilePresent(String fileName)
	{
		boolean fileFound=false;
		List<WebElement> fileList=driver.findElements(fileListingElements.fileList);
		
		for(WebElement fileItem:fileList)
		{
			if(fileItem.getText().contains(fileName))
			{
				fileFound=true;
				break;
			}
		}
		
		
		return fileFound;
		
	}
	
	public String getFileView()   //listview or gridview 
	{
		
		return driver.findElement(fileListingElements.fileView).getAttribute("id");
	}
	
	
	
	public boolean isCopyAccessDeniedAlertShown()
	{
		String expectedErrorMessage="You don't have permission to add items to this folder.";
		
		String actualErrorMessage=driver.findElement(fileListingElements.copyAccessDeniedAlert).getText();
		
		if(expectedErrorMessage.equals(actualErrorMessage))
			return true;
		else
			return false;
		
		
	}
	
	public boolean isFileSelected(String fileName)
	{
		boolean fileSelected =false;
		
		String actualSelectedFileName=driver.findElement(fileListingElements.selectedFile).getText();
		
		System.out.println("#############actualSelectedFileName: "+actualSelectedFileName);
		if(actualSelectedFileName.equals(fileName))
			fileSelected=true;
		
		return fileSelected;
	}
	
	public void rightClickFileAndChoose(String filename,String rightClickOption)
	{
		List<WebElement> fileList=driver.findElements(fileListingElements.fileList);
		System.out.println("Seaarching File : "+filename);
		for(WebElement fileItem:fileList)
		{
			System.out.println("ITERATING  : "+fileItem.getText());

			if(fileItem.getText().equals(filename))
			{
				System.out.println("FOUND File : "+fileItem.getText());

				Actions builder=new Actions(driver);
				Action fileRightClick=builder.moveToElement(fileItem).contextClick().build();
				
				fileRightClick.perform();
				
				 Assert.assertTrue(isFileSelected(filename),"File not selected yet");
				 
				 waitForLoading(3000);
				chooseFileOperation(rightClickOption);
				waitForLoading(3000);
				break;
			}
		}
	}
	
	public void chooseFileOperation(String fileMenuOption)
	{
		List<WebElement> fileOperations=driver.findElements(fileListingElements.fileMenuList);
		
		for(WebElement fileMenu:fileOperations)
		{
			if(fileMenu.getText().equals(fileMenuOption))
			{
				Actions builder=new Actions(driver);
				Action chooseMenu=builder.moveToElement(fileMenu).click().build();
				
				chooseMenu.perform();
				waitForLoading(3000);
				break;
			}
			
		}
		
	}
	
	
	public void navigateToFolder(String sourceFolderPath)
	{
		String[] sourceFolderPathArray=sourceFolderPath.split("/");
		
		
		for(int i=0;i<sourceFolderPathArray.length;i++)
		{

			clickFirstListingCheckBox();
			findAndClickFolder(sourceFolderPathArray[i]);

		}
		
		
	}
	
	public void findAndClickFolder(String folderName)
	{
		List<WebElement> folderList=driver.findElements(fileListingElements.folderList);
		//folderList.get(0).click();
		for(WebElement folder:folderList)
		{
			if(!folder.getText().equals(folderName))
			{
				Actions action=new Actions(driver);
				
				if(getFileView().equals("listview"))
				action.sendKeys(Keys.ARROW_DOWN);
				else if(getFileView().equals("gridview"))
					action.sendKeys(Keys.ARROW_RIGHT);
					
				
			}
			else
			{
				folder.click();
				break;
			}
				
			
		}
		
		
	}
	
	public void clickFirstListingCheckBox()
	{
		
		Actions action=new Actions(driver);
		action.moveToElement(driver.findElement(fileListingElements.firstFileCheckBox)).click().build().perform();
		
	}
	
	public void filterFileListing(String filterByOption)
	{
		driver.findElement(fileListingElements.filterButton).click();
		
		List<WebElement> filerByOptions=driver.findElements(fileListingElements.filterBy);
		
		for(WebElement filterBy:filerByOptions)
		{
			System.out.println(filterBy.getText());
			
			if(filterBy.getText().equals(filterByOption))
			{
				filterBy.click();
				break;
			}
		}
		
		//System.out.println(driver.getPageSource());
		


	}

	public void findAndSelectFile(String fileName) {
		
		List<WebElement> fileList=driver.findElements(fileListingElements.fileList);
		
		clickFirstListingCheckBox();
		System.out.println("####Check box clicked");
		
		for(WebElement fileItem:fileList)
		{
			System.out.println("Iterating through file list" +fileItem.getText());
			if(fileItem.getText().equals(fileName))
			{
				System.out.println("Found***" +fileItem.getText());

				break;
			}
			else
			{
				Actions action=new Actions(driver);
				
				action.sendKeys(Keys.ARROW_DOWN).perform();
				
				
			}
		}
	}
	
	
	public List<String> getFoldersWithAtleastEditorAccess()   //Shared with Me Folder Listing
	{
		List<String> foldersWithAtleastEditorAccess=new ArrayList<>();// To fetch Folders name with atleast editor access 
		
		
		List<WebElement> fileList=driver.findElements(fileListingElements.fileList);
		
		for(WebElement fileItem:fileList)
		{
			Actions builder=new Actions(driver);
			Action fileRightClick=builder.sendKeys(Keys.ESCAPE).moveToElement(fileItem).contextClick().build();
			fileRightClick.perform();
				 
			waitForLoading(5000);
				List<WebElement> fileOperations=driver.findElements(fileListingElements.fileMenuList);
				
				for(WebElement fileMenu:fileOperations)
				{
					if(fileMenu.getText().equals("Rename")) //Editor has rename access hence it denotes the folder access must be editor or above
					{
						foldersWithAtleastEditorAccess.add(fileItem.getText());
						
						
						//waitForLoading(3000);
						break;
					}
					
					
					
				}
				waitForLoading(3000);
		}
	return foldersWithAtleastEditorAccess;
		
	}
	  private void waitForLoading(int i) {
	      try {
				Thread.sleep(i);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

}
