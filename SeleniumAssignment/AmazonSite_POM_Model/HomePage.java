package amazon.project;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

	WebDriver driver;
	
	By signInButton=By.id("nav-link-accountList");
	By searchBox=By.id("twotabsearchtextbox");
	
	public HomePage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public void clickLogin()
	{
		driver.findElement(signInButton).click();
	}
	
	public void amazonSearch(String productName)
	{
		driver.findElement(searchBox).sendKeys(productName);
		driver.findElement(searchBox).submit();
		
	}

}
