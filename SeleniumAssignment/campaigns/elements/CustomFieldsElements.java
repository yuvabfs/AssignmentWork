package campaigns.elements;

import org.openqa.selenium.By;

public class CustomFieldsElements {
	
	public By createCustomFieldButton=By.xpath("//div[@id='customfldId']//button[@id='createField']");
	
	public By addNewDropDownButton=By.xpath("//div[contains(@class,'settingshdrbnd')]//button/span[text()='Add New']");
	
	public By addCustomFieldPopUp=By.xpath("//div[@id='settingsAddCustomFieldForm']");
	
	public By firstFieldTypeDropDownOption=By.xpath("//div[@id='settingsAddCustomFieldForm']//div[@name='customFieldForm']//ul[@id='fieldTypes']/li[@purpose='Text']");
	
	public By displayLabelField=By.xpath("//div[@id='settingsAddCustomFieldForm']//div[@name='customFieldForm']//div/span[text()='Display Label']/../following-sibling::div/input[@type='text']");
	
	public By fieldLengthField=By.xpath("//div[@id='settingsAddCustomFieldForm']//div[@name='customFieldForm']//div/div[text()='Field Length:']/../div/input[@type='text']");

	public By decimalPlaces=By.xpath("//div[@id='settingsAddCustomFieldForm']//div[@name='customFieldForm']//div[@action='decimalRow']//input[@type='text']");
		
	public By itemsTextArea=By.xpath("//div[@id='settingsAddCustomFieldForm']//div[@name='customFieldForm']//div[@action='picklistRow']//textarea");
	
	public By addButtonPopup=By.xpath("//div[@id='settingsAddCustomFieldForm']//div[@name='customFieldForm']//div/button[text()='Add']");
	
	public By addedCustomFields=By.xpath("//div[text()='Contact Custom Fields ']/..//div/div[@onmouseover]");
	
	public By latestCustomFieldEntryName=By.xpath("//div[text()='Contact Custom Fields ']/..//div/div[@onmouseover][last()]/div[text()]");
	
	public By latestCustomFieldEntryType=By.xpath("//div[text()='Contact Custom Fields ']/..//div/div[@onmouseover][last()]/div[text()][2]");
	
	public By contactCustomFieldsList=By.xpath("//div[text()='Contact Custom Fields ']/..//div/div[@onmouseover]/div[text()]//input/parent::div");
	
	public By listOfCustomFieldName=By.xpath("//div[text()='Contact Custom Fields ']/..//div/div[@onmouseover]/div[text()][1]");
	
	public By activeDeleteIcon=By.xpath("//div[text()='Contact Custom Fields ']/..//div/div[@onmouseover]/div[last()]/i[contains(@id,'Delete') and contains(@style,'display: inline')]");

	public By deleteAlertPopUp=By.xpath("//div[@id='deleteAlertWithEntities']");
	
	public By confirmDeleteOnPopup=By.xpath("//div[@id='deleteAlertWithEntities']//button[text()='Delete']");
}
