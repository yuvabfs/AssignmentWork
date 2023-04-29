package campaigns.actions;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import campaigns.elements.LeftMenuBarElements;

public class LeftMenuBarSelection {

	WebDriver driver;
	
	LeftMenuBarElements leftMenuBar=new LeftMenuBarElements();
	
	public LeftMenuBarSelection (WebDriver driver)
	{
		this.driver=driver;
	}
	
	
	public void clickSettings()
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		
		WebElement settingOption=wait.until(ExpectedConditions.visibilityOf(driver.findElement(leftMenuBar.settingsOption)));
		
		settingOption.click();
	}
	
}
