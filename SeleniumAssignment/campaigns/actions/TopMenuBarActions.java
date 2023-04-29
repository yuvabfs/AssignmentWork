package campaigns.actions;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import campaigns.elements.TopMenuBarElements;

public class TopMenuBarActions {
	
	WebDriver driver;
	
	TopMenuBarElements topMenuBarElements=new TopMenuBarElements() ;
	
	public TopMenuBarActions(WebDriver driver)
	{
		this.driver=driver;
		
	}
	
	public boolean isLoginSuccess()
	{
		boolean loginSuccess=false;		
		
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(3));
		
		
		WebElement accountProfileIcon=wait.until(ExpectedConditions.visibilityOf(driver.findElement(topMenuBarElements.accountProfileIcon)));
			
		String currentUrl=driver.getCurrentUrl();
		
		if(currentUrl.contains("org") & accountProfileIcon.isDisplayed())
			loginSuccess=true;
	
		return loginSuccess;
	}
}
