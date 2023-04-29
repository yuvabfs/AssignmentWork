package campaigns.actions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import campaigns.elements.SettingsPageElements;

public class SettingsAction {
	
	WebDriver driver;
	
	SettingsPageElements settingsPageElements=new SettingsPageElements();
	
	
	public SettingsAction(WebDriver driver)
	{
		this.driver=driver;
		
	} 
	
	public String getPageHeaderText()
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(2));
		
		WebElement pageHeaderText=wait.until(ExpectedConditions.visibilityOf(driver.findElement(settingsPageElements.pageHeaderText)));
				
		return pageHeaderText.getText();
	}
	
	
	public void clickCustomFields()
	{
		JavascriptExecutor execute=(JavascriptExecutor)driver;
		
		WebElement customFieldsOption=driver.findElement(settingsPageElements.customFields);
		
		execute.executeScript("arguments[0].scrollIntoView(false)", customFieldsOption);
		customFieldsOption.click();
		
	}

	public void clickSettingsOption(String settingsPageOption) {
		
		
		
		WebElement settingsOption=driver.findElement(By.xpath("//div[@id='SettingsResponse']//a[text()='"+settingsPageOption+"']"));
		
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(false)", settingsOption);
		
		settingsOption.click();
		
		
	}
	

}
