package workdrive.elementClasses;

import org.openqa.selenium.By;

public class UploadDownloadPopUp {
	
	public By movedItemPopUpTitle=By.xpath("//div[contains(@class,'upload-download')]//h5[contains(@zd-title,'item moved') or contains(@zd-title,'items moved') ]");
	
	public By copiedItemPopUpTitle=By.xpath("//div[contains(@class,'upload-download')]//h5[contains(@zd-title,'item copied') or contains(@zd-title,'items copied') ]");
	
	//this returns file list of latest operation-Copy/Move/Delete
	public By copiedFileList=By.xpath("//div[contains(@class,'upload-download')]//div[contains(@class,'zwd-items')]//div[contains(@class,'zwd-items')]");
	
	public By fileLocator=By.xpath("//div[contains(@class,'upload-download')]//div[contains(@class,'zwd-items')]//div[contains(@class,'zwd-items')]//i//following-sibling::i");
	
	

}
