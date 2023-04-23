package workdrive.actionClasses;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import workdrive.elementClasses.UploadDownloadPopUp;

public class UploadDownloadPopUpAction {
	
	UploadDownloadPopUp uploadDownloadPopUp=new UploadDownloadPopUp();
	
	WebDriver driver;
	

	
	public UploadDownloadPopUpAction (WebDriver driver)
	{
		this.driver=driver;
	}
	//public WebElement copiedItemPopUp=driver.findElement(uploadDownloadPopUp.copiedItemPopUpTitle); 	
	public boolean verifyCopiedPopUp(String fileName)
	{
		//WebDriverWait wait=new WebDriverWait(driver, 20);
		WebElement copiedItemPopUp=driver.findElement(uploadDownloadPopUp.copiedItemPopUpTitle);
		boolean fileNameFound=false;
		
		if(copiedItemPopUp.isDisplayed())
		{	
		
		List<WebElement> fileList=driver.findElements(uploadDownloadPopUp.copiedFileList);
		
		for( WebElement fileItem:fileList)
		{
			if(fileItem.getText().contains(fileName))
			{
				fileNameFound=true;
			}
			break;
		}
		}
		else
		{
			System.out.println("Wait time exceed");
		}
		return fileNameFound;
	}
	
	
	public void clickFileLocator()
	{
		driver.findElement(uploadDownloadPopUp.fileLocator).click();
	}
	
	public void waitingForCopyActionToBeCompleted()
	{
	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
		wait.until(ExpectedConditions.visibilityOfElementLocated(uploadDownloadPopUp.copiedItemPopUpTitle));
	}

}
