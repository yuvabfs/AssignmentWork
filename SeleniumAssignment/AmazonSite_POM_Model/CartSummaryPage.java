package amazon.project;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartSummaryPage {
	WebDriver driver;
	
	By addedToCartMessage=By.xpath("//div[starts-with(@id,'NATC_SMART')]//span");
	By proceedToBuyButton=By.name("proceedToRetailCheckout");
	
	public CartSummaryPage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public String successMessage()
	{
		return driver.findElement(addedToCartMessage).getText();
	}

}
