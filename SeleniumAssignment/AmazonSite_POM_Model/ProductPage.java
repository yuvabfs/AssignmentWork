package amazon.project;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage {
	
	WebDriver driver;
	
	By addToCartButton=By.xpath("//div[@id='buybox']//input[@name='submit.add-to-cart']");
	By outOfStock=By.id("outOfStock");
	By addToWishListButton=By.id("add-to-wishlist-button-submit");
	By buyNowButton=By.id("buy-now-button");
	By productPrice=By.xpath("//span[@id='price']");
	By seeAllBuyingOptions=By.xpath("//a[@title='See All Buying Options']");
	
	
	
	public ProductPage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public void addProductToCart()
	{
		driver.findElement(addToCartButton).click();
		
	}
		
	public boolean isOutOfStock()
	{
		if(driver.findElement(outOfStock).isDisplayed())
			return true;
		else
			return false;
	}
	
	public void clickAddToCartButton()
	{
		driver.findElement(addToCartButton).click();
		
	}
	
	
	public void clickAddToWishListButton()
	{
		driver.findElement(addToWishListButton).click();
		
	}
	
	public void clickBuyNowButton()
	{
		driver.findElement(buyNowButton).click();
		
	}
	
	public float getProductPrice()
	{
		String priceString=driver.findElement(productPrice).getText();
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
