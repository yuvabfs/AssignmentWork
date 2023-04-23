package workdrive.copyFunctionality;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Date;
import java.util.StringTokenizer;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

public class CookieDataFunctions {
	
	WebDriver driver;
	
	public CookieDataFunctions(WebDriver driver)
	{
		this.driver=driver;
	}
	
		public void manualCookieSetup()
		{
			
			
		  System.out.println("Fetching cookie!");
		  driver.manage().addCookie(new Cookie("_iamadt", "be92b4dfabc6a7859ccdcf68990ba61a0f4ed83f4198039a05fc17005ad4f7ba9b052b1043c16a653f8b37e3c1a35ec778d388bad1aa6f841e1fc79662cb70ce", ".zoho.in", "/", null, true));
		  driver.manage().addCookie(new Cookie("_iambdt", "31caa93d4fcfc475080a5607d90f165db444d5e784a8d831847870dab50b3cb1421cc5cc31d5f6b578102a70664bf30dc79205e5f8cc4c489ca3da70994b5e05", ".zoho.in", "/", null, true));
		  driver.manage().addCookie(new Cookie("iamcsr", "d030ab518f21d3ad91c9df0b1f0c4c9da6a1613aad1067c208ed12e52743b87da9d4dbc362acb0a170c17e4651d93552c102eafb4440b882114b587f868eda75", "accounts.zoho.in", "/", null, true));
		  try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		    }
		
	  
	  
	  public void writeCookieData()
	  {


			File file = new File("Cookies.data");
			 try		
		        {	  
		            // Delete old file if exists
					file.delete();		
		            file.createNewFile();			
		            FileWriter fileWrite = new FileWriter(file);							
		            BufferedWriter Bwrite = new BufferedWriter(fileWrite);							
		            // loop for getting the cookie information 		
		            	
		            // loop for getting the cookie information 		
		         //   for(Cookie ck : driver.manage().getCookies())							
		            //{			
		           //     Bwrite.write((ck.getName()+";"+ck.getValue()+";"+ck.getDomain()+";"+ck.getPath()+";"+ck.getExpiry()+";"+ck.isSecure()));																									
		           //     Bwrite.newLine();             
		            ////}			
		            Bwrite.close();			
		            fileWrite.close();	
		            
		        }
		        catch(Exception ex)					
		        {		
		            ex.printStackTrace();			
		        }


	  }

		public void getStoredCookieData()
		{

	try{			
	   
	      File file = new File("Cookies.data");							
	      FileReader fileReader = new FileReader(file);							
	      BufferedReader Buffreader = new BufferedReader(fileReader);							
	      String strline;			
	      while((strline=Buffreader.readLine())!=null){									
	      StringTokenizer token = new StringTokenizer(strline,";");									
	      while(token.hasMoreTokens()){					
	      String name = token.nextToken();					
	      String value = token.nextToken();					
	      String domain = token.nextToken();					
	      String path = token.nextToken();					
	      Date expiry = null;					
	      		
	      String val;			
	      if(!(val=token.nextToken()).equals("null"))
			{		
	      	expiry = new Date(val);					
	      }		
	      Boolean isSecure = new Boolean(token.nextToken()).								
	      booleanValue();		
	      Cookie ck = new Cookie(name,value,domain,path,expiry,isSecure);			
	      System.out.println(ck);
	  ///    driver.manage().addCookie(ck); // This will add the stored cookie to your current session					
	      }		
	      }	
	      Buffreader.close();
	      }catch(Exception ex){					
	      ex.printStackTrace();			
	      }
		
		}


}
