package workdrive.actionClasses;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator.RelativeBy;

import workdrive.elementClasses.FileTopMenuElements;

public class FileTopMenuAction {
	FileTopMenuElements fileTopMenuElements=new FileTopMenuElements();
	
	WebDriver driver;
	
	public FileTopMenuAction(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public void clickFileTopMenuButton(String buttonName)//Share or More actions or Copy link or Download
	{
		
		String xpath="//div[contains(@class,'zwd-selectionBar')]//div[contains(@class,'zwd-flex zwd-justify-center')]//button[@zd-title='"+buttonName+"']";
		By fileTopMenuButtons=By.xpath(xpath);
		
		WebElement desiredFileTopMenuButton=driver.findElement(fileTopMenuButtons);
		
		desiredFileTopMenuButton.click();
		
		
	}
	
	
	
	
	
}
