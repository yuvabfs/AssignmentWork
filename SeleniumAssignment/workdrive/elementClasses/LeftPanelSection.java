package workdrive.elementClasses;

import org.openqa.selenium.By;

public class LeftPanelSection {
	
	
	public By folderElement=By.xpath("//div[contains(@class,'left fixed vertical menu')]//span[@title]");
	public By teamFolder;
	public By sharedWithMeFolder;
	
	public By teamFolderList=By.xpath("//div[@class='zwd-workspace-container-alone ']//child::div/a/span");
	

}
