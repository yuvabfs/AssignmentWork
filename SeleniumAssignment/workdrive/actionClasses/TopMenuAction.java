package workdrive.actionClasses;

import org.openqa.selenium.WebDriver;

import workdrive.elementClasses.TopMenuElements;

public class TopMenuAction {
	
	WebDriver driver;	
	TopMenuElements topMenuElements=new TopMenuElements();
	public TopMenuAction(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public boolean  isLoginSuccess()
	{
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return driver.findElement(topMenuElements.accountImage).isDisplayed();
	}
	
	
	
}
