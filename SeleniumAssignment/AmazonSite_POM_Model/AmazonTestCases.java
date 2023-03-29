package amazon.project;

import org.testng.annotations.Test;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;


public class AmazonTestCases {
	
	public WebDriver driver;
	LoginPage loginPage;
	HomePage homePage;
	SearchResultPage searchResultPage;
	ProductPage productPage;
	CartSummaryPage cartSummaryPage;
	ShoppingCartPage shoppingCartPage;
	MenuBarElements menuBarElements;
	
	  @BeforeMethod (groups="current")
	  public void beforeMethod() {
		  
			driver = new FirefoxDriver();
			driver.get("https://www.amazon.in/");
			
			loginPage=new LoginPage(driver);
			homePage=new HomePage(driver);
			searchResultPage=new SearchResultPage(driver);
			productPage=new ProductPage(driver);
			cartSummaryPage=new CartSummaryPage(driver);
			menuBarElements=new MenuBarElements(driver);
			shoppingCartPage=new ShoppingCartPage(driver);
			  
	  }
	//UC01 - Login Functionality
	//TC01 - login With Invalid UserName
		
		@Test 
		public void uc01_tc01_LoginWithInvalidUserName() {

			waitForLoading();
			homePage.clickLogin();
			loginPage.submitUserName("yuavbfs@gmail.com");
			Assert.assertTrue(loginPage.verifyErrorMessage());
					
			
		}
		
	//TC02 - login With Invalid Password
		@Test
		public void uc01_tc02_LoginWithInvalidPassWord() {

			waitForLoading();
			homePage.clickLogin();
			loginPage.loginToAmazon("yuvabfs@gmail.com","password");
			Assert.assertTrue(loginPage.verifyErrorMessage());
					
		}
		
	//TC03 - login With Valid Credential
		@Test
		public void uc01_tc03_LoginWithValidCredential() {

			waitForLoading();
			homePage.clickLogin();
			loginPage.loginToAmazon("+919629534930","Test@1993");
			Assert.assertTrue(loginPage.verifySuccessfulLogin());
					
		}
		
	//UC02- Product Search Functionality 
	//TC04- Search Iphone_Verify If Search Result Is Relevant
		 @Test 
		  public void uc02_tc04_SearchIphone_VerifyIfSearchResultIsRelevant() {

			  
			  String productName="iPhone";
			  waitForLoading();
			  homePage.amazonSearch(productName);
			  waitForLoading();
			  	  
			  Assert.assertTrue(checkIfRelevant(searchResultPage.getProductList(),productName));
			  
		  }
	
	//TC05- Search Tablet_Verify If Search Result Is Relevant  
		  @Test 
		  public void uc02_tc05_SearchTablet_VerifyIfSearchResultIsRelevant() {

			  
			  String productName="Java";
			  homePage.amazonSearch(productName);
			  waitForLoading();
			  
			Assert.assertTrue(checkIfRelevant(searchResultPage.getProductList(),productName));
		  }

		  
		  	//Validation method: To check if the product Title has searchText
			public boolean checkIfRelevant(List<String> productList,String productName)
			{
				boolean relevantResult=true;
				
				for(int i =0;i<5;i++)
				{
					if(!(productList.get(i).contains(productName)))
					{
						relevantResult=false;
					}
						
				}
				
				
				return relevantResult;
				
			}
		
		
	//UC03- Cart Functionality	
	//TC06- verify Add To Cart Success Message
	@Test 
	  public void uc03_tc06_VerifyAddToCartSuccessMessage() {

		  homePage.amazonSearch("Chicken Soup for Soul");
		  waitForLoading();
			
		  searchResultPage.chooseFirstProduct();
		  waitForLoading();
		  
		  productPage.clickAddToCartButton();
		  String expectedSuccessMessage="Added to Cart";
		  String actualSuccessMessage=cartSummaryPage.successMessage();
		    
		  Assert.assertEquals(actualSuccessMessage,expectedSuccessMessage);
	  }
	  
	
	//TC07-  verify If Cart Count Is Updated
	@Test 
	  public void uc03_tc07_VerifyIfCartCountIsUpdated()
	  {
		  int noOfItemsAlreadyInCart=menuBarElements.getCartCount();
		  homePage.amazonSearch("How to Talk so Kids will listen");
		  waitForLoading();
			
		  searchResultPage.chooseFirstProduct();
		  waitForLoading();
		  
		  productPage.clickAddToCartButton();
		  int noOfItemsInCartNow=menuBarElements.getCartCount();
		    
		  Assert.assertTrue(noOfItemsInCartNow==(noOfItemsAlreadyInCart+1),"Cart Count is not updated");
	  }
	  
	//TC08-  verify Product Price Match Across Pages
	@Test 
	  public void uc03_tc08_VerifyProductPriceMatchAcrossPages()
	  {
		  homePage.amazonSearch("Java Book");
		  waitForLoading();
			
		  //Price at Search Result Page
		  float priceAtSearchResult=searchResultPage.getProductPriceAtSearchResult();
		  waitForLoading();
		  
		  searchResultPage.chooseFirstProduct();
		  waitForLoading();
		  
		  //Price at Product Page
		  float priceAtProductPage=productPage.getProductPrice();
		  System.out.println("Price at Search Result: "+priceAtProductPage);
		  
		  
		  Assert.assertEquals(priceAtSearchResult,priceAtProductPage,"Price Mismatch at Search_Result_Page & Product_Page");
		  productPage.clickAddToCartButton();
		  menuBarElements.clickCartIcon();
		  
		  float priceOfProductLastlyAddedToCart=shoppingCartPage.getlatestCartItemPrice();
		  
		  System.out.println("Price at Search Result: "+priceOfProductLastlyAddedToCart);
		  
		  
		  Assert.assertEquals(priceAtProductPage,priceOfProductLastlyAddedToCart,"Price Mismatch at Shopping_Cart & Product_Page");
	  }
	
	  
	//TC09- check If Cart Total Is Correct
	  @Test 
	  public void uc03_tc09_CheckIfCartTotalIsCorrect()
	  {
		  //PRODUCT-1: Search a product_name|&|select the product listed first|&|Add the product to Cart
		  homePage.amazonSearch("Java Book");
		  waitForLoading();
		  searchResultPage.chooseFirstProduct();
		  waitForLoading();
		  productPage.clickAddToCartButton();
		  waitForLoading();
		  
		  //PRODUCT-2: Search a product_name|&|select the product listed first|&|Add the product to Cart
		  homePage.amazonSearch("Python Book");
		  waitForLoading();
		  searchResultPage.chooseFirstProduct();
		  waitForLoading();
		  productPage.clickAddToCartButton();
		  waitForLoading();
		  
		  //PRODUCT-3: Search a product_name|&|select the product listed first|&|Add the product to Cart
		  homePage.amazonSearch("JavaScript Book");
		  waitForLoading();
		  searchResultPage.chooseFirstProduct();
		  waitForLoading();
		  productPage.clickAddToCartButton();
		  waitForLoading();
		  
		 //Verify Cart Total Value 
		  menuBarElements.clickCartIcon();
		  
		  
		  Float totalCartItemPrice=calculateSum(shoppingCartPage.getPriceListOfCartItems());
		  
		  Float subTotalValueLeftPane= shoppingCartPage.getSubTotalValueLeftPane();
		  
		  Assert.assertEquals(totalCartItemPrice,subTotalValueLeftPane,"Total Mismatch at ActiveCart");

		  Float subTotalValueActiveCart= shoppingCartPage.getSubTotalValueActiveCart();
		  
		  Assert.assertEquals(totalCartItemPrice,subTotalValueActiveCart,"Total Mismatch at Left Panel");
				  
		  
	  }
	  
	//TC10- Verify By Update Item Quantity In Cart uc03_tc10_
	  
	  @Test(groups="current")
	  public void VerifyByUpdateItemQuantityInCart()
	  {
		  //Search a product_name|&|select the product listed first|&|Add the product to Cart
		  homePage.amazonSearch("Java Book");
		  waitForLoading();
		  searchResultPage.chooseFirstProduct();
		  waitForLoading();
		  productPage.clickAddToCartButton();
		  waitForLoading();
		//Verify Cart Total Value 
		  menuBarElements.clickCartIcon();
		  shoppingCartPage.clickQuantity();
		  shoppingCartPage.updateQuantity("4");
		  
	  }
	  

	  
		public float calculateSum(List<Float> priceList)
		{
			float sum=0.0f;
			for(Float element:priceList)
			{
				sum =element+sum;
			}
			
			
			return sum;
			
		}
		
		public void waitForLoading()
		  {
			  try 
			  {Thread.sleep(2000);} 
			  catch (InterruptedException e) 
			  {e.printStackTrace();}
			  
		  }
 



	  

}
