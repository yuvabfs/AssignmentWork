package workdrive.copyFunctionality;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


public class WorkDriveAutomationUtil {
	
	
	public WebDriver driver;
	
	public WorkDriveAutomationUtil(WebDriver driver)
	{
		this.driver=driver;
	}
	
	// To verify newly copied file with its file name
	
	public String getTimeStamp()
	{
		System.out.println("getTimeStamp!!!");
	    
		return new SimpleDateFormat("dd-MM-yyyy HH:mm").format(new Date());
	}
	
	
	public void takeScreenShot(String filePath)
	{

		  //Convert web driver object to TakeScreenshot
	      TakesScreenshot screenShot =((TakesScreenshot)driver);

	      //Call getScreenshotAs method to create image file
	      File screenshotFile = screenShot.getScreenshotAs(OutputType.FILE);

	      //Move image file to new destination
	      File DestFile=new File(filePath);
	      
	      //Copy file at destination
	      
	      
	      try {
	      FileUtils.copyFile(screenshotFile, DestFile);
	      }catch (IOException e) {
	          e.printStackTrace();
	      }
	      
	      System.out.println(" ScreenShot Taken!!!");
		    
	}
	
	
	
	
}
