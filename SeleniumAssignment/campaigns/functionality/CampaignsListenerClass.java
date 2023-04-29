package campaigns.functionality;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class CampaignsListenerClass implements ITestListener{
	
	//To take Screenshot once a text method passed
	@Override 
	public void onTestSuccess(ITestResult result) {
	
		try {
			 
			 //Convert web driver object to TakesScreenshot
		      TakesScreenshot screenShot =((TakesScreenshot)(WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance()));

			//Call getScreenshotAs method to create image file
		      File screenshotFile = screenShot.getScreenshotAs(OutputType.FILE);
		      
		      String firstParameter="";
		      if(result.getParameters().length!=0)
		      {
		      firstParameter=result.getParameters()[0].toString();
		      }		      
		      String fileName=result.getName()+firstParameter;

		      //Move image file to new destination
		      File DestFile=new File("D:\\CampaignsScreenShot\\ScreenShot\\"+fileName+".png");
		      
		      
		      //Copy file at destination
		      FileUtils.copyFile(screenshotFile, DestFile);

		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
	        e.printStackTrace();
	    }
	      
	      System.out.println(" ScreenShot Taken!!!");
		

		
	}

}
