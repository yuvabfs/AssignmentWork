package amazon.project;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
	WebDriver driver;
	By emailField=By.name("email");
	By continueButton=By.id("continue");
	By errorMessage=By.xpath("//div[@id='auth-error-message-box']");
	By passwordField=By.name("password");
	By confirmSignIn=By.id("signInSubmit"); 
	By successfulLoginId=By.xpath("//span[text()='Hello, TestAccount']");
	
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public void setUserName(String emailID)
	{
		driver.findElement(emailField).sendKeys(emailID);
	}
	
	public void clickContinue()
	{
		driver.findElement(continueButton).click();
	}
	public void setPassword(String password)
	{
		driver.findElement(passwordField).sendKeys(password);
	}
	
	public void confirmSignIn()
	{
		driver.findElement(confirmSignIn).click();
	}
	
	public void submitUserName(String emailId)
	{
		this.setUserName(emailId);
		this.clickContinue();
	}
	
	public boolean verifyErrorMessage()
	{
		return driver.findElement(errorMessage).isDisplayed();
	}
	
	public void loginToAmazon(String emailId,String password)
	{
		this.submitUserName(emailId);
		this.setPassword(password);
		this.confirmSignIn();
	}
	public boolean verifySuccessfulLogin()
	{
		return driver.findElement(successfulLoginId).isDisplayed();
	}
	

}
