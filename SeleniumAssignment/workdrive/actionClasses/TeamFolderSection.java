package workdrive.actionClasses;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TeamFolderSection {
	
	
	public By accountUserRole=By.xpath("//div[contains(@class,'left menu')]//div[@zd-title='Your role in this Team Folder']");
	
	WebDriver driver;
	
	public TeamFolderSection(WebDriver driver)
	{
		this.driver=driver;
		
	}
	
	
	public String getAccountUserRole()
	{
		return driver.findElement(accountUserRole).getText();
	}
	
	
}
