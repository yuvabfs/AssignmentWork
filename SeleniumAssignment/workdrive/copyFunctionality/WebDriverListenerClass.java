package workdrive.copyFunctionality;



import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class WebDriverListenerClass implements ITestListener  {

	@Override
	public void onTestSuccess(ITestResult result) {
		
		System.out.println("ScreenShot Method");
		
		try {
			//WorkDriveAutomationUtil workDriveAutomationUtil;
			//workDriveAutomationUtil = new WorkDriveAutomationUtil((WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance()));
			
			//workDriveAutomationUtil.takeScreenShot("D:\\ZOHO\\WorkDrive Automation Script\\ScreenShot\\"+result.getName()+".png");
			 
			 //Convert web driver object to TakeScreenshot
		      TakesScreenshot screenShot =((TakesScreenshot)(WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance()));

			
			//Call getScreenshotAs method to create image file
		      File screenshotFile = screenShot.getScreenshotAs(OutputType.FILE);

		      //Move image file to new destination
		      File DestFile=new File("D:\\ZOHO\\WorkDrive Automation Script\\ScreenShot\\"+result.getName()+".png");
		      
		      
		      //Copy file at destination
		      FileUtils.copyFile(screenshotFile, DestFile);

		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	      
	      
	    catch (IOException e) {
	          e.printStackTrace();
	      }
	      
	      System.out.println(" ScreenShot Taken!!!");
		
		
		
	}
	
	@Override

public void onTestFailure(ITestResult result) {
		
	
	
	try {
		WorkDriveAutomationUtil workDriveAutomationUtil;
		workDriveAutomationUtil = new WorkDriveAutomationUtil((WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance()));
	
		workDriveAutomationUtil.takeScreenShot("D:\\ZOHO\\WorkDrive Automation Script\\ScreenShot\\"+result.getName()+".png");

	} catch (IllegalArgumentException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IllegalAccessException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (NoSuchFieldException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SecurityException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	
	}
	
	
}
