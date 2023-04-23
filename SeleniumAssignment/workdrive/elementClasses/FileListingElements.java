package workdrive.elementClasses;

import org.openqa.selenium.By;

public class FileListingElements {
	
	public By fileList=By.xpath("//div[@id='file_listing']//div[starts-with(@id,'ember')]//span[contains(@class,'zwd-atom-filename')]");
	
	public By selectedFile=By.xpath("//div[@id='file_listing']//div[starts-with(@id,'ember') and contains(@class,'zwd-active')]//span[contains(@class,'zwd-atom-filename') ]");
	
	public By fileView=By.xpath("//div[@id='file_listing']//div[contains(@class,'zwd-ui zwd-divided zwd-items zwd-scroll')]");
	
	public By folderList=By.xpath("//div[@id='file_listing']//div[starts-with(@id,'ember') and contains(@class,'zwd-item zwd-atom-item') or contains (@class,'zwd-isfolder') ]//span[contains(@class,'zwd-atom-filename')]");
	
	public By fileMenuList=By.xpath("//div[contains(@class,'ui dropdown docs-menu-list')]//span");
	
	public By firstFileCheckBox=By.xpath("//div[@id='file_listing']//div[@id='listview' or 'gridview']//i");
	
	public By copyTo=By.xpath("//div[contains(@class,'ui dropdown docs-menu-list')]//span[text()='Copy To...']");
	 
	public By copyAccessDeniedAlert=By.xpath("//div//p//span[contains(@class,'floated fleft pt-2 zwd-atom-status-message')]");
	
	public By filterButton=By.xpath("//div[@class='zwd-ui zwd-container zwd-fluid']//button[@id='key_filter']");
	
	public By filterBy=By.xpath("//div[contains(@class,'zwd-ui zwd-dropdown')]//div[@id='zd-dropdown']//div[@tabindex]//span");
	 
	 
	
	public By emptySpace=By.xpath("//div[contains(@class,'zwd-none-value')]"); // To undo the right click
	 
	 


}
