package workdrive.actionClasses;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import workdrive.elementClasses.CopyPopUpElements;

public class CopyPopUpAction {
	

	CopyPopUpElements copyPopUpElements=new CopyPopUpElements();
	WebDriver driver;
	public CopyPopUpAction(WebDriver driver)
	{
		this.driver= driver;
		
	}
	
	
	public void clickdestinationDropDownIcon()
	{
		driver.findElement(copyPopUpElements.destinationDropDownIcon).click();
	}
	
	public boolean isCopyPopUpDisplayed()
	{
		return driver.findElement(copyPopUpElements.copyPopUp).isDisplayed();
	}
	
	public void selectDestination(String destinationMainFolderName)
	{
		
		clickdestinationDropDownIcon();
		
		//WebElement desriredFileElement=driver.findElement(By.xpath("//div[contains(@id,'zd-dropdown' )and contains(@class,'zwd-menu')]//div[@tabindex]//span[text()='"+destinationMainFolderName+"']"));
		List<WebElement> allFolderElements=driver.findElements(By.xpath("//div[contains(@id,'zd-dropdown' )and contains(@class,'zwd-menu')]//div[@tabindex]//span[@class='zwd-text ']"));
		
		for(WebElement folderItem:allFolderElements)
		{
			if(folderItem.getText().equals(destinationMainFolderName))
			{
				Actions builder=new Actions(driver);
				builder.sendKeys(Keys.ENTER).perform();
				break;
			
			}
			else
			{
				Actions builder=new Actions(driver);
				builder.sendKeys(Keys.ARROW_DOWN).perform();
			
			}
			
		}

		
	}
	
	public List<String> getTeamFoldersDestinationDropDown()
	{
		
		List<String> destinationDropDownTeamFolders=new ArrayList<>();
		List<WebElement> dropDownTeamFolders= driver.findElements(copyPopUpElements.dropDownTeamFolderList);
		
		for(WebElement dropDownTeamFolderElement: dropDownTeamFolders)
		{
			destinationDropDownTeamFolders.add(dropDownTeamFolderElement.getText());
		}
		
		return destinationDropDownTeamFolders;
		
	}
	
	public void confirmCopy()
	{
		driver.findElement(copyPopUpElements.confirmCopyButton).click();
		
	}
	
	public void clickCreateNewFolderButton()
	{
		driver.findElement(copyPopUpElements.newFolderButton).click();
	}
	
	public void enterNewFolderName(String newFolderName)
	{
		driver.findElement(copyPopUpElements.newFolderInputField).sendKeys(newFolderName);
		
		Actions action=new Actions(driver);
		
		action.sendKeys(Keys.ENTER).perform();

	}
	public String getDuplicateNewFolderErrorMessage()
	{
		return driver.findElement(copyPopUpElements.duplicateNewFolderErrorMessage).getText();
	}
	
	public String getNoNameNewFolderErrorMessage()
	{
		return driver.findElement(copyPopUpElements.noNameErrorMessage).getText();
	}
	
	public void findAndClickSubFolder(String subFolderPath)
	{
		String[] subFolderPathArray=subFolderPath.split("/");
		
		System.out.println(subFolderPath);
		System.out.println(subFolderPathArray[0]);
		
		for(int i=0;i<subFolderPathArray.length;i++)
		{
			List<WebElement> subFolderList=driver.findElements(copyPopUpElements.subFolderListing);
			
			System.out.println(subFolderList);
			
			for(WebElement subFolder:subFolderList )
			{
				System.out.println(subFolder.getText());

				if(subFolder.getText().equals(subFolderPathArray[i]))
				{	
					System.out.println(subFolder.getText());
					subFolder.click();
					break;		
				}			
			}
		}	
	}
	
	public List<String> getSubFolderList()
	{
		List<WebElement> subFolderElementList=driver.findElements(copyPopUpElements.subFolderListing);
		
		List<String> subFolderList=new ArrayList<>();
		for(WebElement subFolder:subFolderElementList )
		{
			subFolderList.add(subFolder.getText());
						
		}
		
		return subFolderList;
	}
	
	public boolean isNewFolderButtonDisabled()
	{
		
		
		return driver.findElement(copyPopUpElements.newFolderButton).getAttribute("class").contains("disabled");
	}
	
	public boolean isconfirmCopyButtonDisabled()
	{
		
		
		return driver.findElement(copyPopUpElements.confirmCopyButton).getAttribute("class").contains("disabled");
	}
	
}
