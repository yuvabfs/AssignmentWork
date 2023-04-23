package workdrive.copyFunctionality;

import org.testng.annotations.DataProvider;

public class DataProviderClass {
	
	
	
	
	 
	  @DataProvider (name="FileRightClick")
	  
	  public Object[][] testDataSet1()
	  {
		  //1.Within My folders
		  //2.Within My Folders SubFolders
		  //3.From My folders to Team Folder
		  //4.From My Folders to Shared with Me Folder
		  //Combination of account role,File Type
		  
		  
		return new Object[][] {
			{"TestFile_CopyFunctionality", "My Folders","","My Folders",""},
			{"MS Power point.pptx", "My Folders","Internal Share","My Folders","TestFolder"},
			{"Test File1 PDF.pdf","My Folders","Internal Share/Provide Access to Internal Team/Test File","A_Test Team Folder","A Team Folder's subFolder"},
			{"Test Sheet","My Folders","TestFolder","Shared with Me","MyFolder share - Edit Access"}};
		  
	  }
	  @DataProvider (name="TopMenuMoreAction")
	  
	  public Object[][] testDataSet2()
	  {
		  
		  
		return new Object[][] {
			{"TestExcel.xlsx", "My Folders","","My Folders",""},
			{ "Sample Image.bmp", "My Folders","TestFolder 123/TestSubFolder2","My Folders","TestFolder"},
			{"Test File","My Folders","Internal Share/Provide Access to Internal Team","A_Test Team Folder","A Team Folder's subFolder"},
			{ "TestDocument.docx","My Folders","TestFolder","Shared with Me","MyFolder share - Edit Access"}};
		  
	  }
	  
@DataProvider (name="CreatingNewFolder")
	  
	  public Object[][] testDataSet3()
	  {
		  
		  
		return new Object[][] {
			{"TestExcel.xlsx", "My Folders","","My Folders","","CreatingNewFolder"},
			{ "Sample Image.bmp", "My Folders","TestFolder 123/TestSubFolder2","My Folders","TestFolder",""}, // Negative case-No name New Folder
			{"Test File","My Folders","Internal Share/Provide Access to Internal Team","A_Test Team Folder","A Team Folder's subFolder","CreatingNewFolder3"},
			{ "TestDocument.docx","My Folders","TestFolder","Shared with Me","MyFolder share - Edit Access","CreatingNewFolder4"}
			
			};
		  
	  }

}
