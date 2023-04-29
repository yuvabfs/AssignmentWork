package campaigns.functionality;

import java.util.ArrayList;
import java.util.Arrays;

import org.testng.annotations.DataProvider;

public class CampaignsDataProviderClass {
	

	  @DataProvider (name="customFieldCreation")
	  
	  public Object[][] testDataSet1()
	  {
		  
		return new Object[][] {
			{"Text", "Customer Name","10",null,null},
			{"Integer","Customer Age","9",null,null },
			{"Phone","Phone Number", "6",null,null},
			{"LongInteger","Landline Number","11",null,null},
			{"URL","Web Link","20",null,null},			
			{"Decimal","CreditScore","5","2",null},
			{"Email","emailId",null,null,null},
			{"Date","DOB",null,null,null},
			{"DateTime","CreatedOn",null,null,null},
			{"Percent","Score",null,null,null},
			{"textarea","FeedBack",null,null,null},
			{"Picklist","DropDown",null,null,new ArrayList<>(Arrays.asList("item 1","item 2","item 3"))},
			{"Multiselect","Multi Select DropDown",null,null,new ArrayList<>(Arrays.asList("item 4"))},
			{"RadioOption","Choose One",null,null,new ArrayList<>(Arrays.asList("item 7","item 8","item 1","item 5"))},
			{"MultiselectCheckbox","DropDownBox",null,null,new ArrayList<>(Arrays.asList("item 10","item 11","item 13","item 3","item 5"))}
		  
	  };
	}
	  
	  
	  @DataProvider (name="mergeTagVerification")
	  
	  public Object[][] testDataSet2()
	  {
		  return null;  
	  };
	  
	  
}