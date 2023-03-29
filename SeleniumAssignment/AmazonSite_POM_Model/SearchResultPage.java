package amazon.project;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchResultPage {
	
	WebDriver driver;
	
	By searchResult=By.xpath("//h2[starts-with(@class,'a-size-mini a-spacing-none')]//a//span");
	
	By firstProduct=By.xpath("//div[@data-component-type='s-search-result']//h2//a//span");
	By priceOfFirstProduct=By.xpath("//div[@data-component-type='s-search-result']//span[@class='a-price-whole']");
	
	
	
	SearchResultPage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	
	public List<String> getProductList()
	{
		List<WebElement> productList= driver.findElements(searchResult);
		List<String> productNameList=new ArrayList<>();
		for(int i=0;i<productList.size();i++)
		{
			System.out.println(productList.get(i).getText());
			productNameList.add(productList.get(i).getText());
		}
		return productNameList;
	}
	
	public void chooseFirstProduct()
	{
		String mainWindowHandle = driver.getWindowHandle();
		driver.findElement(firstProduct).click();
		
		//To get driver control to the product page(newly opened tab)
		Set<String> allWindowHandles=driver.getWindowHandles();
		
		Iterator<String> iterator = allWindowHandles.iterator();
		String ChildWindow="";
       // Check if child window has other child windows and will fetch the heading of the child window
        while (iterator.hasNext()) {
            ChildWindow = iterator.next();
                if (!mainWindowHandle.equalsIgnoreCase(ChildWindow)) {
                driver.switchTo().window(ChildWindow);
    
                }
		
	}
	}
	
	
	public float getProductPriceAtSearchResult()
	{
		String priceString=driver.findElement(priceOfFirstProduct).getText();
		String newPrice="";
		for (char digit : priceString.toCharArray())
        {
            
            if (Character.isDigit(digit) || digit=='.') 
            {
               
                newPrice=newPrice.concat(Character.toString(digit));
            }
        }
		Float price=Float.parseFloat(priceString);
		
		
		return price;
		
		
	}
	
}
	

	

