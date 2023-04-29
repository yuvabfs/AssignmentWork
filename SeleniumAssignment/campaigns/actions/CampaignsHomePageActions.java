package campaigns.actions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import campaigns.elements.LoginPageElements;
import campaigns.utils.CampaignsUtils;

public class CampaignsHomePageActions {
	
	
	WebDriver driver;
	
	LoginPageElements loginPage=new LoginPageElements();
	CampaignsUtils utilMethods;
	public CampaignsHomePageActions(WebDriver driver)
	{
		this.driver=driver;
		
	}
	
	public void clickSignIn()
	{
		WebElement signInButton=driver.findElement(loginPage.signInButton);
		signInButton.click();
	}
	
	public void loginWithCookies()
	{
		utilMethods = new CampaignsUtils(driver);
				
		driver.get("https://accounts.zoho.in/");
		utilMethods.manualCookieSetup();
		
		driver.get("https://accounts.zoho.in/");
		utilMethods.waitForLoading(5000);
		
	    driver.get("https://campaigns.zoho.in/campaigns/org60021078504/home.do#dashboard");
	    utilMethods.waitForLoading(10000);
		
	}

	public void loginWithCredential(String userName,String passWord) {

		
		WebElement userNameField=driver.findElement(loginPage.userNameField);
		
		userNameField.sendKeys(userName);
		
		WebElement userNameSubmitButton=driver.findElement(loginPage.userNameSubmitButton);
		
		userNameSubmitButton.click();
		
		
		WebElement passWordField=driver.findElement(loginPage.passWordField);
		
		passWordField.sendKeys(passWord);
		
		WebElement passWordSubmitButton=driver.findElement(loginPage.passWordSubmitButton);
		
		passWordSubmitButton.click();
		
	}
	

}
