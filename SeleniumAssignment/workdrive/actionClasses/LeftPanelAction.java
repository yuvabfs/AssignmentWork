package workdrive.actionClasses;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import workdrive.elementClasses.FileListingElements;
import workdrive.elementClasses.LeftPanelSection;

public class LeftPanelAction {
	
	WebDriver driver;
	LeftPanelSection leftPanelSection=new LeftPanelSection();
	
	public LeftPanelAction(WebDriver driver)
	{
		this.driver=driver;
	}
	
	
	public List<String> getTeamfoldersWithAtleastEditorAccess()
	{
		List<String> teamFolderNameList=new ArrayList<>();
		List<WebElement> teamFolders=driver.findElements(leftPanelSection.teamFolderList);
		
		for(WebElement teamFolder:teamFolders )
		{
			if(teamFolder.getText().trim().length()>0)
			teamFolderNameList.add(teamFolder.getText());
		}
		System.out.println(teamFolderNameList);
		return teamFolderNameList;
		
		
	}
	
	
	
	
	public void chooseLeftPaneOption(String leftPaneOption)
	{
		
		List<WebElement> leftPaneMenuList= driver.findElements(leftPanelSection.folderElement);
		
		for(WebElement leftPaneMenu:leftPaneMenuList)
		{
			
			if(leftPaneMenu.getText().equals(leftPaneOption))
			{
				
				leftPaneMenu.click();
				break;
			}
		}
	}

	
	
	public boolean isPageLoaded(String menuOption)
	{
		String expectedPageTitle=menuOption.concat(" - Zoho WorkDrive");
		String actualPageTitle=driver.getTitle();
		
		return expectedPageTitle.equals(actualPageTitle);
	}
	}