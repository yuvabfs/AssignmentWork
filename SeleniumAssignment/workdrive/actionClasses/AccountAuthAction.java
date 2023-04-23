package workdrive.actionClasses;

import org.openqa.selenium.WebDriver;

import workdrive.elementClasses.AccountAuthenticationPage;


public class AccountAuthAction {
	
	WebDriver driver;
	
	AccountAuthenticationPage accountAuthenticationPage=new AccountAuthenticationPage();
	
	public AccountAuthAction(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public void clickRemindMeLater()
	{
		driver.findElement(accountAuthenticationPage.remindMeLater).click();
	}
	

}
