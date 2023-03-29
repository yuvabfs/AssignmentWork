package amazon.project;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ShoppingCartPage {
	WebDriver driver;
	
	By priceListOfAllProductsInCart=By.xpath("//span[contains(@class,'sc-price sc-white-space-nowrap sc-prod')]");
	By latestCartItemPrice=By.xpath("//span[contains(@class,'sc-price sc-white-space-nowrap sc-prod')][1]");
	By subTotalValueLeftPane = By.xpath("//span[@id='sc-subtotal-amount-buybox']//span[contains(@class,'sc-price sc-white-space')]");
	By subTotalValueActiveCart=By.xpath("//span[@id='sc-subtotal-amount-activecart']//span[contains(@class,'sc-price sc-white-space')]");
	By quanityDropDown=By.xpath("//select[@name='quantity']");
	By quantityDropDownMask=By.xpath("//span[@class='a-dropdown-container']");
	
	ShoppingCartPage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public void clickQuantity()
	{
		driver.findElement(quantityDropDownMask).click();
	}
	public float getlatestCartItemPrice()
	{
		String priceString=driver.findElement(latestCartItemPrice).getText();

		return convertStringToFloat(priceString);

	}
	
	public float getSubTotalValueLeftPane()
	{
		String priceString=driver.findElement(subTotalValueLeftPane).getText();

		return convertStringToFloat(priceString);

	}
	
	public float getSubTotalValueActiveCart()
	{
		String priceString=driver.findElement(subTotalValueActiveCart).getText();

		return convertStringToFloat(priceString);

	}
	
	
	public List<Float> getPriceListOfCartItems()
	{
		List<WebElement> priceLocators=driver.findElements(priceListOfAllProductsInCart);
		List<Float> priceList=new ArrayList<>();
		for(WebElement priceDetail:priceLocators)
		{
			priceList.add(convertStringToFloat(priceDetail.getText()));
		}
		return priceList;
		
	}
	
	public void updateQuantity(String newQuantity)
	{
		Select quantityDropDown=new Select(driver.findElement(quanityDropDown));
		quantityDropDown.selectByValue(newQuantity);
		
	}
	
	public float convertStringToFloat(String priceString)
	{
		
		String newPrice="";
		for (char digit : priceString.toCharArray())
        {
            
            if (Character.isDigit(digit) || digit=='.') 
            {
               
                newPrice=newPrice.concat(Character.toString(digit));
            }
        }
		Float price=Float.parseFloat(newPrice);
		
		
		return price;
		
		
	}

}
