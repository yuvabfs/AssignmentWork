package campaigns.functionality;

import java.time.Duration;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import campaigns.actions.*;
import campaigns.utils.*;

@Listeners(CampaignsListenerClass.class)
public class CampaignsTestCases {
	WebDriver driver;
	
	CampaignsUtils utilMethods;
	CampaignsHomePageActions campaignsHomePageActions;
	LeftMenuBarSelection leftMenuBarSelection;
	TopMenuBarActions topMenuBarActions;
	SettingsAction settingsAction;
	CustomFieldActions customFieldActions;
	MergeTagsActions mergeTagsActions;
	
	@BeforeTest
	public void setup()
	{
		driver =new FirefoxDriver();
		
		
		//cookieDataFunctions.getStoredCookieData();
		driver.manage().window().maximize() ;
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		
		utilMethods = new CampaignsUtils(driver);
		campaignsHomePageActions=new CampaignsHomePageActions(driver);
		leftMenuBarSelection=new LeftMenuBarSelection(driver);
		topMenuBarActions = new TopMenuBarActions(driver);
		settingsAction= new SettingsAction(driver);
		customFieldActions=new CustomFieldActions(driver);
		mergeTagsActions=new MergeTagsActions(driver);
		campaignsHomePageActions.loginWithCookies();
		

		//To verify if Account Landing Page is getting displayed or not
		Boolean loginStatus=topMenuBarActions.isLoginSuccess();
		Assert.assertTrue(loginStatus,"Issue with Login");
		Reporter.log("Logged-in Successfully");
	
				
		
	}
	
	@Test( enabled=false, priority=1)
	public void tc01_verifyLoginFunctionality()
	{
		//To login Campaigns using Test account Credentials
		
		driver.get("https://campaigns.zoho.in/campaigns/org60021078504/home.do#dashboard");
		
		campaignsHomePageActions.clickSignIn();
		campaignsHomePageActions.loginWithCredential("testyuvapriya@gmail.com", "TestAccount123");
		
		
		//To Verify whether login success
		Boolean loginStatus=topMenuBarActions.isLoginSuccess();		
		Assert.assertTrue(loginStatus, "Issue with Login");
	}
	
	
	
	@Test(priority=1,  dataProvider="customFieldCreation", dataProviderClass =CampaignsDataProviderClass.class )
	public void tc02_verifyCustomFieldCreation_ofAllValidFieldTypes(String fieldType,String displayLabel,String fieldLength,String decimalPlaces,List<String> itemsList)
	{
		

		//To Navigate to Settings Page
		leftMenuBarSelection.clickSettings();
		
		//Verify if Settings Page is getting Displayed as expected
		Assert.assertEquals(settingsAction.getPageHeaderText(), "Settings","Page header text is incorrect");
		Reporter.log("- Navigated to Settings page Successfully");
		
		
		//To navigate to custom Field Section
		settingsAction.clickCustomFields();
		
		//Verify if custom field section is getting displayed
		String currentUrl=driver.getCurrentUrl();
		Assert.assertTrue(currentUrl.contains("customfields"), "Url mismatch at Custom Fields section");
		Reporter.log("- Navigated to Custom Field Successfully");
		
		
		//To open add custom field pop-up form
		if(customFieldActions.isCreateCustomFieldButtonDisplayed())
			customFieldActions.clickCustomFieldButton();
		
		else
		{
			customFieldActions.clickAddNewDropDownButton();
			customFieldActions.chooseAddNewOption("Contact Field");
		}	

		Assert.assertTrue(customFieldActions.isAddCustomFieldPopUpDisplayed(),"Add Custom Pop-up not getting Dsiplayed");
		Reporter.log("Contact Custom Field pop-up is getting displayed successfully");
		
		//Entering Custom Field Detail in pop-up dialog and Submit it
		customFieldActions.chooseFieldType(fieldType);
		customFieldActions.enterDisplayLabel(displayLabel);
		
		switch(fieldType)
		{
			case "Text":
			case "Integer":
			case "Phone":
			case "LongInteger":
			case "URL":
				customFieldActions.enterFieldLength(fieldLength);
				break;
			case "Email":
			case "Date":
			case "DateTime":
			case "Percent":
			case "textarea":
			case "Checkbox":
				break;
			case "Picklist":
			case "Multiselect":
			case "RadioOption":
			case "MultiselectCheckbox":
				customFieldActions.enterItems(itemsList);
				break;
			case "Decimal":

				customFieldActions.enterFieldLength(fieldLength);
				customFieldActions.enterDecimalPlaces(decimalPlaces);	
				break;
			case "BirthDay":
				
				break;
		}
		customFieldActions.clickAddButton();
		
		
		//Verify if the newly added contact custom field is added
		boolean newFieldAdded=customFieldActions.verifyAddedField(displayLabel,fieldType);		
		Assert.assertTrue(newFieldAdded,"Newly added Entry not listed correctly");		
		Reporter.log("- New Contact custom field added successfully");		
				
	}
	
	//Prerequisite: At least one custom field has to be created already(thus added dependsOnMethods)
	@Test(priority=1, dependsOnMethods="tc02_verifyCustomFieldCreation_ofAllValidFieldTypes")
	public void tc03_VerifyIfAllCustomFieldsAreGettingListedForMergeTagOperation()
	{
		

		//To Navigate to Settings Page
		leftMenuBarSelection.clickSettings();
		
		//Verify if Settings Page is getting Displayed as expected
		Assert.assertEquals(settingsAction.getPageHeaderText(), "Settings","Page header text is incorrect");
		
		//To navigate to custom Field Section
		settingsAction.clickCustomFields();
				
		//Verify if custom field section is getting displayed
		String currentUrl=driver.getCurrentUrl();			
		Assert.assertTrue(currentUrl.contains("customfields"), "Url mismatch at Custom Fields section");
		
		//To fetch a list of the entire Custom Contact Fields Created so far
		List<String> createdContactCustomFields=customFieldActions.getContactCustomField();
		
		Collections.sort(createdContactCustomFields);
		
		Reporter.log("- Fetched all Custom Contact Fields created so far");
		
		//To Navigate back to Settings Page--->and Select Merge Tags
		leftMenuBarSelection.clickSettings();
		Assert.assertEquals(settingsAction.getPageHeaderText(), "Settings","Page header text is incorrect");
		settingsAction.clickSettingsOption("Merge Tags");
		
		//To fetch a list of Merge Tags Listed
		mergeTagsActions.clickCreateButton();
		mergeTagsActions.chooseContactCustomTagOption();	
		mergeTagsActions.clickContactCustomFieldDropDownOnPopUp();
		List<String> contactCustomFieldsListedForMergeTag=mergeTagsActions.getContactCustomFieldDropDownOption();
		Collections.sort(contactCustomFieldsListedForMergeTag);
		
		Reporter.log("- Fetched all Custom contact fields listed in Merge Tag operation");
		
		//Verify custom contact Field list with those listed for Merge Tag operation
		Assert.assertEquals(createdContactCustomFields, contactCustomFieldsListedForMergeTag);
		
		Reporter.log("- Added Custom Field is getting listed under Merge Tag as Expected");
		
		mergeTagsActions.clickMergePopupCancelButton();
		
				
		

	}
	
	//Prerequisite: At least one custom field has to be created already(thus added dependsOnMethods)
	@Test(priority=1,dependsOnMethods="tc02_verifyCustomFieldCreation_ofAllValidFieldTypes")
	public void tc04_verifyMergeTagOperationForAllValidContactCustomFields()
	{		
		//Navigate to Settings Page & Make sure it is properly loaded
		leftMenuBarSelection.clickSettings();
		Assert.assertEquals(settingsAction.getPageHeaderText(), "Settings", "Page header text is incorrect");		
		Reporter.log("- Navigated to Settings page Successfully");

		//To navigate to custom Field Section & Make sure it is loaded properly
		settingsAction.clickCustomFields();
		String currentUrl = driver.getCurrentUrl();
		Assert.assertTrue(currentUrl.contains("customfields"), "Url mismatch at Custom Fields section");
		Reporter.log("- Navigated to Custom Contact Field Section Successfully");

		// To fetch a list of the entire Custom Contact Fields Created so far
		List<String> createdContactCustomFields = customFieldActions.getContactCustomField();
		
		Reporter.log("- Fetched all the names of Custom Contact Fields created so far");
		
		
		//Navigate back to Settings Page & Make sure it is loaded
		leftMenuBarSelection.clickSettings();
		Assert.assertEquals(settingsAction.getPageHeaderText(), "Settings","Page header text is incorrect");		
		Reporter.log("- Navigated to Settings page Successfully -");
		
		//Select Merge Tags & Make sure if categories 'All' is selected by default
		settingsAction.clickSettingsOption("Merge Tags");
		Assert.assertEquals(mergeTagsActions.getCategorySelection(),"All", "Default Selection Category mismatch error");
		Reporter.log("- Navigated to Merge Tag Section Successfully");

		//Navigate to Contact custom Field Category
		mergeTagsActions.selectCategory("Contact Custom Tags");
		Assert.assertEquals(mergeTagsActions.getCategorySelection(),"Contact Custom Tags", "Not able to select Contact Custom Tags category");
		
		//Fetch the list of contact custom field with Merge Tag done
		List<String> customContactFieldListWithMergeTag=mergeTagsActions.getCustomContactFieldListWithMergeTag();
		
		Reporter.log("- Fetched all the names of Custom Contact Fields for which Merge Tag is done");
		
		
		//Remove All duplicate contact custom fields to perform Merge Tag operation with valid fields
		createdContactCustomFields.removeAll(customContactFieldListWithMergeTag);
		
		Reporter.log("- List of Custom Field Names to perform Merge Tag operation"+createdContactCustomFields);
		
		int countMergeTagCreation=0;
		
		//Perform Merge Tag operation for non-duplicate Contact Custom Fields
		for(String fieldName:createdContactCustomFields)
		{
			countMergeTagCreation++;
			
			mergeTagsActions.clickCreateButton();
			mergeTagsActions.chooseContactCustomTagOption();	
			mergeTagsActions.clickContactCustomFieldDropDownOnPopUp();
			
			String mergeTagValue=fieldName.replaceAll(" ","").concat("test");
			mergeTagsActions.chooseCustomFieldOptionOnMergeTagPopUp(fieldName);
			mergeTagsActions.enterMergeTag(mergeTagValue);
			mergeTagsActions.clickMergePopupSaveButton();
			

			utilMethods.waitForLoading(2000);
			boolean isMergeTagCreated=mergeTagsActions.verifyLatestCustomFieldMergeTagEntry(fieldName,mergeTagValue);

			Assert.assertTrue(isMergeTagCreated);
			
			Reporter.log("Merge Tag Entry Created for the custom Field: "+fieldName);

			utilMethods.waitForLoading(2000);
			if(countMergeTagCreation>=5)
				break;
		}		
		
	}
	
	//Prerequisite: At least one custom field merge tag has to be done already(thus added dependsOnMethods)
	@Test(priority=2)
	public void tc05_verifyByCreatingDuplicateMergeTagForContactCustomField()
	{
		//To Fetch the list of contact custom field with Merge Tag done

		//Navigate back to Settings Page & Make sure it is loaded
		leftMenuBarSelection.clickSettings();
		Assert.assertEquals(settingsAction.getPageHeaderText(), "Settings","Page header text is incorrect");		

		//Select Merge Tags & Make sure if categories 'All' is selected by default
		settingsAction.clickSettingsOption("Merge Tags");
		Assert.assertEquals(mergeTagsActions.getCategorySelection(),"All", "Default Selection Category mismatch error");
		
		//Navigate to Contact custom Field Category
		mergeTagsActions.selectCategory("Contact Custom Tags");
		Assert.assertEquals(mergeTagsActions.getCategorySelection(),"Contact Custom Tags", "Not able to select Contact Custom Tags category");
		
		//Fetch the list of contact custom field with Merge Tag done
		List<String> customContactFieldListWithMergeTag=mergeTagsActions.getCustomContactFieldListWithMergeTag();
		
		Reporter.log("- Fetched all the names of Custom Contact Fields for which Merge Tag is done");

		Assert.assertTrue(customContactFieldListWithMergeTag.size()>0, "No Custom Contact Field Merge Tag has been done already[Prerequisite not met]");
		
		
		//Perform Merge Tag operation for a duplicate custom field & Validate the error message

		mergeTagsActions.clickCreateButton();
		mergeTagsActions.chooseContactCustomTagOption();	
		mergeTagsActions.clickContactCustomFieldDropDownOnPopUp();
		
		String firstCustomFieldFromList=customContactFieldListWithMergeTag.get(0); //Randomly pick the first custom field from list
		mergeTagsActions.chooseCustomFieldOptionOnMergeTagPopUp(firstCustomFieldFromList);
		
		String mergeTagValue=firstCustomFieldFromList.replaceAll(" ","").concat("test");
		mergeTagsActions.enterMergeTag(mergeTagValue);
		mergeTagsActions.clickMergePopupSaveButton();
		
		//Validate error message for custom field duplication in Merge Tag
		String expectedErrorMessage="Tag duplication is not allowed."; //As per Requirement given in UI content
		Assert.assertTrue(mergeTagsActions.isCustomFieldErrorDisplayed(expectedErrorMessage));
		mergeTagsActions.clickMergePopupCancelButton();
		

		utilMethods.waitForLoading(2000);

	}
	
	@Test(priority=3)
	public void tc06_validateErrorMessageWhenDeletingMergeTagAssociatedCustomField()
	{
		// To Fetch the list of contact custom field with Merge Tag done

		// Navigate back to Settings Page & Make sure it is loaded
		leftMenuBarSelection.clickSettings();
		
		settingsAction.clickSettingsOption("Merge Tags");
		
		mergeTagsActions.selectCategory("Contact Custom Tags");
		
		// Fetch the list of contact custom field with Merge Tag done
		List<String> customContactFieldListWithMergeTag = mergeTagsActions.getCustomContactFieldListWithMergeTag();

		Reporter.log("- Fetched all the names of Custom Contact Fields for which Merge Tag is done");

		Assert.assertTrue(customContactFieldListWithMergeTag.size() > 0,"No Custom Contact Field Merge Tag has been done already[Prerequisite not met]");
		
		System.out.println(customContactFieldListWithMergeTag);
		String firstCustomFieldFromList=customContactFieldListWithMergeTag.get(0); //Randomly pick the first custom field from list
		
		//Deleting Merge Tag Associated Custom Field
		//Navigate back to Settings Page --->Custom Field Section
		leftMenuBarSelection.clickSettings();
		settingsAction.clickCustomFields();
		String currentUrl = driver.getCurrentUrl();
		Assert.assertTrue(currentUrl.contains("customfields"), "Url mismatch at Custom Fields section");
		
		System.out.println("firstCustomFieldFromList: "+firstCustomFieldFromList);
		customFieldActions.clickCustomFieldDeleteIcon(firstCustomFieldFromList);
		
		Assert.assertTrue(customFieldActions.deleteAlertForMergeTagAssociatedField());
		
		Reporter.log("Verified Delete Alert Pop up for Merge Tag Associated custom field");
	
		
	}
	
	@Test(priority=0)
	public void tc07_verifyByDeletingCustomFieldNotAssociatedToMergeTag()
	{

		//First, Create a fresh contact custom tag for testing delete operation on it
		//To Navigate to Settings Page
		leftMenuBarSelection.clickSettings();
		
		//To navigate to custom Field Section
		settingsAction.clickCustomFields();
		
				
		//To open add custom field pop-up form
		if(customFieldActions.isCreateCustomFieldButtonDisplayed())
			customFieldActions.clickCustomFieldButton();
		
		else
		{
			customFieldActions.clickAddNewDropDownButton();
			customFieldActions.chooseAddNewOption("Contact Field");
		}	

		Assert.assertTrue(customFieldActions.isAddCustomFieldPopUpDisplayed(),"Add Custom Pop-up not getting Dsiplayed");
		Reporter.log("Contact Custom Field pop-up is getting displayed successfully");
		
		//Entering Custom Field Detail in pop-up dialog and Submit it
		customFieldActions.chooseFieldType("Text");
		customFieldActions.enterDisplayLabel("FreshTag");
		customFieldActions.enterFieldLength("9");
		customFieldActions.clickAddButton();
		
		//Verify if the newly added contact custom field is added
		boolean newFieldAdded=customFieldActions.verifyAddedField("FreshTag","Text");		
		Assert.assertTrue(newFieldAdded,"Newly added Entry not listed correctly");		
		Reporter.log("- New Contact custom field added successfully");		
		
		customFieldActions.clickCustomFieldDeleteIcon("FreshTag");
		
		customFieldActions.clickConfirmDeleteButton();

		Reporter.log("- Custom Field not associated to Merge Tag has been deleted successfully!");
		
		
		//Perform deletion operation on the newly Created Tag
		

	}
	

}
