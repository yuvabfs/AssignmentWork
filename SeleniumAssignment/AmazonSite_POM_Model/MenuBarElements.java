package amazon.project;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MenuBarElements {
	WebDriver driver;
	
	By cartIcon=By.id("nav-cart");
	By noOfItemsInCart=By.id("nav-cart-count");
	
	public MenuBarElements(WebDriver driver)
	{
		this.driver=driver;
	}
	
	
	public void clickCartIcon()
	{
		driver.findElement(cartIcon).click();
	}
	public int getCartCount()
	{
		String noOfCartItems= driver.findElement(noOfItemsInCart).getText();
		
		return Integer.parseInt(noOfCartItems);
	}
	
	
	
	
	
	
	
	
	

}
