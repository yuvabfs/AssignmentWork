package campaigns.actions;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import campaigns.elements.MergeTagsElements;
import campaigns.utils.CampaignsUtils;

public class MergeTagsActions {
	
	WebDriver driver;
	
	MergeTagsElements mergeTagsElements=new MergeTagsElements();
	CampaignsUtils utilMethods;
	
	public MergeTagsActions(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public void clickCreateButton()
	{
		WebElement createButton=driver.findElement(mergeTagsElements.createDropDownButton);
		
		createButton.click();
	}
	
	public void chooseContactCustomTagOption()
	{
		WebElement createContactCustomTag=driver.findElement(mergeTagsElements.createContactCustomTagOption);
		
		createContactCustomTag.click();
	}
	
	public void clickContactCustomFieldDropDownOnPopUp()
	{
		
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(3));
		
		
		WebElement contactCustomFieldDropDown=wait.until(ExpectedConditions.visibilityOf(driver.findElement(mergeTagsElements.contactCustomFieldDropDown)));
		
		
		Actions builder=new Actions(driver);
		Action action=builder.moveToElement(contactCustomFieldDropDown).click().build();
		action.perform();
	}

	public List<String> getContactCustomFieldDropDownOption()
	{		
		List<WebElement> customFieldDropDownOption=driver.findElements(mergeTagsElements.contactCustomFieldDropDownOption);
		utilMethods = new CampaignsUtils(driver);
		utilMethods.waitForLoading(5000);
		List<String> customFieldDropDownOptions=new ArrayList<>();

		for(WebElement customContactField:customFieldDropDownOption)
		{
		customFieldDropDownOptions.add(customContactField.getText());
		}
	
		return customFieldDropDownOptions;
	}

	public String getCategorySelection() {
		
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(2));
		
		WebElement categoryChosen=wait.until(ExpectedConditions.visibilityOf(driver.findElement(mergeTagsElements.categorySelected)));
		
		
		

		return categoryChosen.getText();
	}

	public void selectCategory(String categoryOption) {

		WebElement showMore=driver.findElement(mergeTagsElements.showMoreCategories);
		
		showMore.click();
		
		List<WebElement> categoriesListed=driver.findElements(mergeTagsElements.allCategories);
		
		for(WebElement category:categoriesListed)
		{
			if(category.getText().contains(categoryOption))
			{
				category.click();
				break;
			}
			
		}	
		
	}

	public List<String> getCustomContactFieldListWithMergeTag() {
		
		List<WebElement> contactFieldListWithMergeTagElements=driver.findElements(mergeTagsElements.contactFieldNameWithMergeTag);
		
		
		List<String> customContactFieldNameList=new ArrayList<>();
		
		for (WebElement fieldNameElement:contactFieldListWithMergeTagElements)
		{
			
			WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(2));
			wait.until(ExpectedConditions.visibilityOf(fieldNameElement));
			
			customContactFieldNameList.add(fieldNameElement.getText());
		}
		return customContactFieldNameList;
	}
	
	public void chooseCustomFieldOptionOnMergeTagPopUp(String fieldName) {
		

		List<WebElement> contactFieldListWithMergeTagElements=driver.findElements(mergeTagsElements.contactCustomFieldDropDownOption);
		utilMethods = new CampaignsUtils(driver);
		utilMethods.waitForLoading(3000);
		System.out.println("FIELDELEMENT: "+contactFieldListWithMergeTagElements);
		for (WebElement fieldNameElement:contactFieldListWithMergeTagElements)
		{
			System.out.println(fieldNameElement.getText());
			if(fieldNameElement.getText().equals(fieldName))
			{
				JavascriptExecutor js=(JavascriptExecutor)driver;
				js.executeScript("arguments[0].scrollIntoView(false)", fieldNameElement);
				WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(2));
				wait.until(ExpectedConditions.visibilityOf(fieldNameElement));
				
				fieldNameElement.click();
				break;
				
			}
			
		}		

		
	}
	
	
	public void clickMergePopupCancelButton()
	{
		WebElement cancelButton=driver.findElement(mergeTagsElements.mergeTagPopupCancelButton);
		cancelButton.click();
	}
	public void clickMergePopupSaveButton()
	{
		WebElement saveButton=driver.findElement(mergeTagsElements.mergeTagPopupSaveButton);
		
		saveButton.click();
	}

	public void enterMergeTag(String mergeTagValue) {
		
		WebElement mergeTagField=driver.findElement(mergeTagsElements.mergeTagInputFieldOnPopUp);
		
		mergeTagField.sendKeys(mergeTagValue);
	}

	public boolean verifyLatestCustomFieldMergeTagEntry(String fieldName, String string) {
		utilMethods = new CampaignsUtils(driver);
		utilMethods.waitForLoading(3000);
		boolean matchFound=false;
		WebElement lastCustomFieldTagValueElement=driver.findElement(mergeTagsElements.lastCustomFieldTagValue);
		
		WebElement lastCustomFieldDefaultValueElement=driver.findElement(mergeTagsElements.lastCustomFieldDefaultValue);
		
		WebElement contactFieldNameWithMergeTagElement=driver.findElement(mergeTagsElements.contactFieldNameWithMergeTag);

		if(contactFieldNameWithMergeTagElement.getText().equals(fieldName)
				&& lastCustomFieldTagValueElement.getAttribute("value").equals("$[UD:"+string.toUpperCase()+"]$")
				&& lastCustomFieldDefaultValueElement.getAttribute("value").equals("$[UD:"+string.toUpperCase()+"||]$"))
			matchFound=true;

		return matchFound;
	}

	public boolean isCustomFieldErrorDisplayed(String errorMessage) {
		
		boolean errorMessageShown=false;
		
		WebElement errorMessageElement=driver.findElement(mergeTagsElements.errorMessageElement);
		
		if(errorMessageElement.getText().equals(errorMessage))
				errorMessageShown=true;

		return errorMessageShown;
	}


	
	
	
	
}
