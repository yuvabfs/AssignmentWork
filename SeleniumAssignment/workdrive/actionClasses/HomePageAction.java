package workdrive.actionClasses;

import org.openqa.selenium.WebDriver;

import workdrive.elementClasses.HomePage;

public class HomePageAction {
	
	WebDriver driver;
	HomePage homePage=new HomePage();
	
	public HomePageAction(WebDriver driver)
	{
		this.driver=driver;
	}
	


}
