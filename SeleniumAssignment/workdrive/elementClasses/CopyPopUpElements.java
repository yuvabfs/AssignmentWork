package workdrive.elementClasses;

import org.openqa.selenium.By;

public class CopyPopUpElements {
	
	public By copyPopUp=By.xpath("//div[contains(@class,'active flex')]");
	public By destinationDropDownIcon=By.xpath("//div[contains(@class,'active flex')]//a[@zd-title='Switch view']");
	public By dropDownTeamFolderList=By.xpath("//div[contains(@class,'zwd-js-scrollable-dd-list')]//div[text()='Team Folders']//following-sibling::div/span/span[@class='zwd-text ']");
	public By confirmCopyButton=By.xpath("//div[contains(@class,'active flex')]//button[text()='Copy']");
	public By destination_DropDown_MyFolders=By.xpath("//div[contains(@id,'zd-dropdown' )and contains(@class,'zwd-menu')]//div[@tabindex]//span[text()='My Folders']");
	public By destination_DropDown_SharedWithMeFolders=By.xpath("//div[contains(@id,'zd-dropdown' )and contains(@class,'zwd-menu')]//div[@tabindex]//span[text()='Shared with Me']");
	public By destination_DropDown_TeamFolders=By.xpath("//div[contains(@id,'zd-dropdown' )and contains(@class,'zwd-menu')]//div[@tabindex]//span[text()='My Folders']");
	public By subFolderListing=By.xpath("//div[@id='choose-folder-listing']//div[@draggable]//span[contains(@class,'zwd-js-item-name')]");
	public By newFolderButton=By.xpath("//div[contains(@class,'active flex')]//button[text()='New Folder']");
	public By duplicateNewFolderErrorMessage=By.xpath("//div[contains(@class,'error message')]//p//span");
	public By newFolderInputField=By.xpath("//div[contains(@class,'zwd-input')]//input[@id='folder-name']");
	public By noNameErrorMessage=By.xpath("//div[contains(@class,'warning message')]//span[contains(@class,'status-message')]");
	
	
}
