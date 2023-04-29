package campaigns.utils;


import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

public class CampaignsUtils {

		
		WebDriver driver;
		
		public CampaignsUtils(WebDriver driver)
		{
			this.driver=driver;
		}
		
			public void manualCookieSetup()
			{
				
			  System.out.println("Fetching cookie!");
			  driver.manage().addCookie(new Cookie("_iamadt", "be92b4dfabc6a7859ccdcf68990ba61a0196c08714adead9c57bcaef5ce1410a3b594acfbdf7e1eb98476e4270d2244904058fa0f3675a4e1911a5f21b27b519", ".zoho.in", "/", null, true));
			  driver.manage().addCookie(new Cookie("_iambdt", "9336763f4b178a8540a55e8473b4b08bbff2b9ee35071cdcb7c0fa3f69301355e17bb60ea2b06f88312788c33c145e39161e34f2e88f7b947ffd32a0dfb4806e", ".zoho.in", "/", null, true));
			  driver.manage().addCookie(new Cookie("iamcsr", "5426558cec00ab4ef39ab8a964ab26c1c62024d2f17078cc94a55d70389737db877b9376798343ced366a3ba757588ecb7ac8226908e674e253a5196b45448d8", "accounts.zoho.in", "/", null, true));

			}
			
			  
			  public void waitForLoading(int i) {
			      try {
						Thread.sleep(i);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
			}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
