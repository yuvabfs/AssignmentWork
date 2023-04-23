package workdrive.actionClasses;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import workdrive.elementClasses.AccountAuthenticationPage;
import workdrive.elementClasses.HomePage;
import workdrive.elementClasses.SignInPage;
import java.io.*;


public class SignInAction {
	WebDriver driver;
	
	HomePage homePage=new HomePage();
	SignInPage signInPage=new SignInPage();
	AccountAuthenticationPage accountAuthenticationPage=new AccountAuthenticationPage();
	
	public SignInAction(WebDriver driver)
	{
		this.driver=driver;
	}
	
	
	public void loginToWorkdrive(String username, String password) 
	{
		driver.findElement(homePage.signInButton).click();
		
		driver.findElement(signInPage.userNameField).sendKeys(username);
		driver.findElement(signInPage.submitUsername).click();
		
		driver.findElement(signInPage.passwordField).sendKeys(password);
		driver.findElement(signInPage.submitcredential).click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
	//In-case Zoho Authentication options is shown right after sign-in.
		if(driver.getTitle().equals("Zoho Accounts"))
		{
			driver.findElement(accountAuthenticationPage.remindMeLater).click();
		}
		
		
	}
	

}
