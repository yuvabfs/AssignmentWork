package amazon.project;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import org.testng.Assert;

public class TestLoginFunctionality {
	
	public WebDriver driver;
	
	LoginPage loginPage;
	HomePage homePage;
	
	@BeforeMethod
	public void setup()
	{
		driver = new FirefoxDriver();
		driver.get("https://www.amazon.in/");
	}
	
	//TC01 - login With Invalid UserName
	
	@Test(priority=1)
	public void loginWithInvalidUserName() {
		loginPage=new LoginPage(driver);
		homePage= new HomePage(driver);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		homePage.clickLogin();
		loginPage.submitUserName("yuavbfs@gmail.com");
		Assert.assertTrue(loginPage.verifyErrorMessage());
				
	}
	
	//TC02 - login With Invalid Password
	@Test(priority=2)
	public void loginWithInvalidPassWord() {
		loginPage=new LoginPage(driver);
		homePage= new HomePage(driver);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		homePage.clickLogin();
		loginPage.loginToAmazon("yuvabfs@gmail.com","password");
		Assert.assertTrue(loginPage.verifyErrorMessage());
				
	}
	
	//TC03 - login With Valid Credential
	@Test(priority=3)
	public void loginWithValidCredential() {
		loginPage=new LoginPage(driver);
		homePage= new HomePage(driver);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		homePage.clickLogin();
		loginPage.loginToAmazon("+919629534930","Test@1993");
		Assert.assertTrue(loginPage.verifySuccessfulLogin());
				
	}
	
}
