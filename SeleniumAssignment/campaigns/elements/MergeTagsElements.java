package campaigns.elements;

import org.openqa.selenium.By;

public class MergeTagsElements {
	
	public By createDropDownButton=By.xpath("//div[@class='middle_container']//button/span[text()='Create']");
	
	public By createContactCustomTagOption=By.xpath("//div[@class='middle_container']//a[text()='Contact Custom Tag']");
	
	public By contactCustomFieldDropDown=By.xpath("//div[@class='drpdnmnu']/p[text()='Select Field']/../span/i");
	
	public By contactCustomFieldDropDownOption=By.xpath("//div[contains(@class,'drpdwnmnulst') and @isactive='yes']//ul/li/a[text()='Contact Custom Fields']/../following-sibling::li/a");

	public By categorySelected=By.xpath("//div[@id='categories']/a[@class='sel']");

	public By showMoreCategories=By.xpath("//div[@id='campaignInnerContents']//div[@id='IntegDiv']//a[@id='showmore']");

	public By allCategories= By.xpath("//div[@id='campaignInnerContents']//div[@id='IntegDiv']//div[@id='categories']/a");

	
	public By mergeTagPopupCancelButton=By.xpath("//div[@id='addCfPanel']//div[contains(@class,'popupbtnbg')]/input[@value='Cancel']");
	
	public By mergeTagPopupSaveButton=By.xpath("//div[@id='addCfPanel']//div[contains(@class,'popupbtnbg')]/input[@value='Save']");

	public By mergeTagInputFieldOnPopUp=By.xpath("//div[@id='addCfPanel']//input[@id='mergeName']");
	
	public By lastCustomFieldTagValue=By.xpath("//div[@id='tagetElementforMT']//table/tbody/tr[@onmouseover]/td[2]/input");
	
	public By lastCustomFieldDefaultValue=By.xpath("//div[@id='tagetElementforMT']//table/tbody/tr[@onmouseover]/td[3]/input");
	
	public By contactFieldNameWithMergeTag= By.xpath("//div[@id='tagetElementforMT']//table/tbody/tr[@onmouseover]/td[4]");

	public By errorMessageElement=By.xpath("//div[@id='addCfPanel']//div[@id='userFieldErrMsg']");
	
	
}
