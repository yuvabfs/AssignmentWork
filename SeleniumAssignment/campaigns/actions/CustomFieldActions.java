package campaigns.actions;


import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import campaigns.elements.CustomFieldsElements;
import campaigns.utils.CampaignsUtils;

public class CustomFieldActions {
	
	WebDriver driver;
	
	CustomFieldsElements customFieldsElements=new CustomFieldsElements();
	CampaignsUtils utilMethods;
	
	public boolean isCreateCustomFieldButtonDisplayed()
	{
		try {
		WebElement customFieldButton=driver.findElement(By.xpath("//div[@id='customfldId']//button[@id='createField']"));
		}
		catch (RuntimeException e)
		{
			System.out.println("EXCEPTION CAUGHT");
			return false;
		}
		
		return true;
	}
	
	public CustomFieldActions(WebDriver driver)
	{
		this.driver=driver;
	}
	
	
	public void clickCustomFieldButton()
	{
		WebElement customFieldButton=driver.findElement(customFieldsElements.createCustomFieldButton);
		
		customFieldButton.click();
		
	}
	
	public void clickAddNewDropDownButton()
	{
		WebElement addNewButton=driver.findElement(customFieldsElements.addNewDropDownButton);
		
		addNewButton.click();
	}
	
	public boolean isAddCustomFieldPopUpDisplayed()
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(2));
		
		WebElement addCustomFieldPopUp=wait.until(ExpectedConditions.visibilityOf(driver.findElement(customFieldsElements.addCustomFieldPopUp)));
		
		return addCustomFieldPopUp.isDisplayed();
		
	}
	
	public void chooseFieldType(String fieldType)
	{
		utilMethods = new CampaignsUtils(driver);
		//To make a random selection in the FieldType dropdown box

		utilMethods.waitForLoading(3000);
		//To scroll until the desired Field type is visible
		WebElement desiredFieldType=driver.findElement(By.xpath("//div[@id='settingsAddCustomFieldForm']//div[@name='customFieldForm']//ul[@id='fieldTypes']/li[@purpose='"+fieldType+"']"));
		JavascriptExecutor js=(JavascriptExecutor)driver;		
		js.executeScript("arguments[0].scrollIntoView(false)",desiredFieldType );
		
		utilMethods.waitForLoading(5000);
		//To choose the desired option as Field Type
		Actions builder2=new Actions(driver);		
		Action action2=builder2.moveToElement(desiredFieldType).click().build();
		action2.perform();
		
	}
	
	public void enterDisplayLabel(String displayLabel)
	{
		WebElement displayLabelField=driver.findElement(customFieldsElements.displayLabelField);
		
		displayLabelField.sendKeys(displayLabel);
	}
	//fieldLengthField
	public void enterFieldLength(String fieldLength)
	{
		WebElement fieldLengthField=driver.findElement(customFieldsElements.fieldLengthField);
		
		fieldLengthField.sendKeys(fieldLength);
	}
	
	public void enterDecimalPlaces(String decimalPlaces)
	{
		WebElement decimalPlace=driver.findElement(customFieldsElements.decimalPlaces);
		
		decimalPlace.sendKeys(decimalPlaces);
	}
	
	public void enterItems(List<String> itemsList)
	{
		WebElement itemsTextArea=driver.findElement(customFieldsElements.itemsTextArea);
		
		
		for(String item:itemsList)
		{
			itemsTextArea.sendKeys(item);
			
			Actions builder=new Actions(driver);
			Action action=builder.keyDown(Keys.SHIFT).keyDown(Keys.ENTER).keyUp(Keys.SHIFT).keyUp(Keys.ENTER).build();
			action.perform();
			
		}
		
	}

	public void clickAddButton()
	{
		WebElement addButtonPopup=driver.findElement(customFieldsElements.addButtonPopup);
		
		addButtonPopup.click();
	}


	public boolean verifyAddedField(String displayLabelInput, String fieldTypeInput) {
		utilMethods = new CampaignsUtils(driver);
		
		utilMethods.waitForLoading(3000);

		List<WebElement> addedCustomFieldEntries=driver.findElements(customFieldsElements.addedCustomFields);
		
		boolean foundMatch=false;
		
		
		if(fieldTypeInput.equals("LongInteger")) fieldTypeInput="Long Integer";
		if(fieldTypeInput.equals("textarea")) fieldTypeInput="Text Area";
		if(fieldTypeInput.equals("Picklist")) fieldTypeInput="Pick List";
		if(fieldTypeInput.equals("RadioOption")) fieldTypeInput="Radio Buttons";
		if(fieldTypeInput.equals("BirthDay")) fieldTypeInput="Anniversary";
		
		System.out.println(addedCustomFieldEntries.size());
		if(addedCustomFieldEntries.size()<1)
		return foundMatch;
		
		else
		{
			utilMethods.waitForLoading(2000);

			WebElement latestCustomFieldEntryName=driver.findElement(customFieldsElements.latestCustomFieldEntryName);
			System.out.println(latestCustomFieldEntryName.getText());
			if(latestCustomFieldEntryName.getText().contains(displayLabelInput))
				foundMatch=true;
		
			WebElement latestCustomFieldEntryType=driver.findElement(customFieldsElements.latestCustomFieldEntryType);
			System.out.println(latestCustomFieldEntryType.getText());
			if(latestCustomFieldEntryType.getText().contains(fieldTypeInput))
				foundMatch=true;
			else
				foundMatch=false;
			
			 
		}
		
		return foundMatch;
	}

	public List<String> getContactCustomField()
	{
		utilMethods = new CampaignsUtils(driver);
		
		utilMethods.waitForLoading(6000);
		List<WebElement> contactCustomFieldElementList=driver.findElements(customFieldsElements.contactCustomFieldsList);
	
		List<String> contactCustomFieldList=new ArrayList<>();
		for(WebElement customContactField: contactCustomFieldElementList)
		{
			contactCustomFieldList.add(customContactField.getText());
		}
	
		return contactCustomFieldList;
	
	}
	public void chooseAddNewOption(String addNewOption) {
		
		WebElement addNewButtonOption=driver.findElement(By.xpath("//div[contains(@class,'settingshdrbnd')]//div[contains(@class,'drpdwnlst')]//ul/li/a[text()='"+addNewOption+"']"));
		
		addNewButtonOption.click();
	}

	public void clickCustomFieldDeleteIcon(String firstCustomFieldFromList) {
		utilMethods = new CampaignsUtils(driver);
		
		
		List<WebElement> listOfAllCustomFields=driver.findElements(customFieldsElements.listOfCustomFieldName);
		System.out.println("listOfAllCustomFields : "+listOfAllCustomFields);
		utilMethods.waitForLoading(3000);
		for(WebElement customFieldNameElement: listOfAllCustomFields)
		{
			System.out.println(customFieldNameElement.getText()+" and "+firstCustomFieldFromList);
			if(customFieldNameElement.getText().equals(firstCustomFieldFromList))
			{
				
				JavascriptExecutor js=(JavascriptExecutor)driver;
				js.executeScript("arguments[0].scrollIntoView(true)",customFieldNameElement );
				
				utilMethods.waitForLoading(2000);
				
				Actions builder=new Actions(driver);
				builder.moveToElement(customFieldNameElement).build().perform();

				String uniqueId=customFieldNameElement.getAttribute("id");
				uniqueId=uniqueId.substring(12);
				utilMethods.waitForLoading(2000);
				//
				WebElement activeDeleteIcon=driver.findElement(By.xpath("//div[text()='Contact Custom Fields ']/..//div/div[@onmouseover]/div[last()]/i[contains(@id,'Delete') and contains(@id,'"+uniqueId+"')]"));

				builder.moveToElement(activeDeleteIcon).click().build().perform();
				
				utilMethods.waitForLoading(3000);
				break;
				
			}

		}

		
	}

	public boolean deleteAlertForMergeTagAssociatedField() {

		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(4));

		WebElement deleteAlertPopUp=wait.until(ExpectedConditions.visibilityOf(driver.findElement(customFieldsElements.deleteAlertPopUp)));
		return deleteAlertPopUp.isDisplayed();
	}
	
	public void clickConfirmDeleteButton()
	{
		
		WebElement confirmDeleteButton=driver.findElement(customFieldsElements.confirmDeleteOnPopup);
		
		confirmDeleteButton.click();
		
		
	}
}
