package amazon.project;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestProductSearchFunctionality {
	
	public WebDriver driver;
	
	HomePage homePage;
	SearchResultPage searchResultPage;
	ProductPage productPage;
	
	
	@BeforeMethod
	public void setup() {
		driver = new FirefoxDriver();
		driver.get("https://www.amazon.in/");
		
	}
  @Test (priority=1)
  public void searchIphone_VerifyProductList() {
	  homePage=new HomePage(driver);
	  searchResultPage=new SearchResultPage(driver);
	  productPage=new ProductPage(driver);
	  
	  String productName="Iphone";
	  homePage.amazonSearch(productName);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("exception occured");
		}
	  	  
	  Assert.assertTrue(checkIfRelevant(searchResultPage.getProductList(),productName));
	  
  }
  
  @Test (priority=2)
  public void searchTablet_VerifyProductList() {
	  homePage=new HomePage(driver);
	  searchResultPage=new SearchResultPage(driver);
	  productPage=new ProductPage(driver);
	  
	  String productName="Tablet";
	  homePage.amazonSearch(productName);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("exception occured");
		}

	Assert.assertTrue(checkIfRelevant(searchResultPage.getProductList(),productName));
  }

  
  //Validation method: To check if the product Title has searchText
	public boolean checkIfRelevant(List<String> productList,String productName)
	{
		boolean relevantResult=true;
		
		for(String productTitle: productList)
		{
			if(!(productName.contains(productName)))
			{
				relevantResult=false;
			}
				
		}
		
		
		return relevantResult;
		
	}
  
  
}
