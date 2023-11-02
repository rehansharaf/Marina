package com.marina.tests;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.marina.base.TestBase;
import com.marina.pages.AddNewSpaceItemPage;
import com.marina.pages.AllSpacesPage;
import com.marina.pages.HomePage;
import com.marina.pages.ImportSpacesPage;
import com.marina.pages.LoginPage;
import com.marina.pages.SpaceTypesPage;
import com.marina.pages.UpdateSpaceItemPage;
import com.marina.utils.ExcelLibrary;
import com.marina.utils.Log;
import com.marina.dataproviders.DataProviders;
import com.marina.base.BrowserFactory;

public class Mod_3_AllSpaces extends TestBase {
	
	LoginPage lp;
	HomePage hp;
	SpaceTypesPage spacetype;
	AllSpacesPage allspace;
	AddNewSpaceItemPage add_new_space;
	UpdateSpaceItemPage updateSpaceItem;
	ImportSpacesPage importSpaces;

	
	
	@BeforeMethod
	public void beforeTest() throws InterruptedException {
		
		browserIntialization();
		lp = new LoginPage(driver);
		hp = lp.login(prop.getProperty("email"), prop.getProperty("password"));
		allspace = hp.spaces_dropdown_AllSpaces();

		
	}
	
	
	@Test(groups = "regression,sanity,smoke", priority = 1)
	public void verifyAllSpacePage_TC_401() {
		
		Log.startTestCase("Verify All Space Page");
		Assert.assertEquals(allspace.getAllSpacePageHeading(), "Spaces");
		Log.endTestCase("Verify All Space Page");

		
	}
	
	@Test(groups = "regression,sanity,smoke", priority = 2, dependsOnMethods = "verifyAllSpacePage_TC_401")
	public void createNewSpaceMandatoryFields_TC_402() throws InterruptedException {

		Log.startTestCase("Create New Space With Mandatory Fields");
		add_new_space = allspace.clickAddSpaceBtn();
		allspace = add_new_space.createNewSpaceMandatoryFields("Automation Type", "SL-Test", "Yes", "10", "20", 
				"30", "5", "Yes", "Yes", "M-0051", "DS-03","1");
		
		String[] actualData = allspace.readFirstRecordDataTable("");
		String[] expectedData = {"SL-Test","Automation Type","Yes","M-0051","M-0051: -"};
		Assert.assertEquals(actualData, expectedData);
		Log.endTestCase("Create New Space With Mandatory Fields");

		
	}
	
	@Test(groups = "regression,sanity,smoke", priority = 3)
	public void verifyAdditionalFieldsForUnAvailibility_TC_403() {
		
		Log.startTestCase("Verify Additional Form Fields Appears For Unavailibility Of Space");
		add_new_space = allspace.clickAddSpaceBtn();
		boolean flag = add_new_space.checkNotAvailibilityStatusAdditionalFields();
		Assert.assertTrue(flag);
		Log.endTestCase("Verify Additional Form Fields Appears For Unavailibility Of Space");

	}
	
	
	@Test(groups = "regression,sanity,smoke", priority = 4)
	public void createNewSpaceAllFields_TC_404() throws InterruptedException {

		Log.startTestCase("Create New Space With All Fields");	
		String imagepath = System.getProperty("user.dir") + "\\src\\test\\resources\\testimages\\space_image.png";
		add_new_space = allspace.clickAddSpaceBtn();
		allspace = add_new_space.createSpaceWithAllFields("Automation Type", "SL-Test2", "No", "10", "20", 
				"30", "5", "Yes", "Yes", "M-027", "DS-03","1",imagepath,"This is test note");

		String[] actualData = allspace.readFirstRecordDataTable("");
		String[] expectedData = {"SL-Test2","Automation Type","No","M-027","M-027: -"};
		Assert.assertEquals(actualData, expectedData);
		Log.endTestCase("Create New Space With All Fields");

	}
	
	@Test(groups = "regression,sanity,smoke", priority = 5, dependsOnMethods = {"createNewSpaceMandatoryFields_TC_402","createNewSpaceAllFields_TC_404"})
	public void verifySearchFeatureInDataTable_TC_405() {
		
		Log.startTestCase("Verify Data Of Created Spaces In Data Table Appeaing Under All Spaces Page");
		
		String[] allActualData = allspace.readFirstRecordDataTable("SL-Test2");
		String[] allExpectedData = {"SL-Test2","Automation Type","No","M-027","M-027: -"};
		
		boolean allFlag = Arrays.equals(allActualData, allExpectedData);
		
		if(allFlag)
			Assert.assertTrue(true);
		else 
			Assert.assertTrue(false);
		Log.endTestCase("Verify Data Of Created Spaces In Data Table Appeaing Under All Spaces Page");

	}
	
	@Test(groups = "regression,sanity,smoke", priority = 6, dependsOnMethods = {"createNewSpaceMandatoryFields_TC_402","createNewSpaceAllFields_TC_404"})
	public void verifySpecificSpaceViewSection_TC_406() {
		
		Log.startTestCase("Verify Data Of Specific Space From View Section");
		
		allspace.readFirstRecordDataTable("SL-Test2");
		String[] allactualData = allspace.verifySpaceDataViewSection("all");
		String[] allexpectedData = {"SL-Test2","No","Due to some issue",allactualData[3],"Automation Type","1 ft","10","20","30",
				"DS-03","Yes","Yes","5 Amps","M-027","This is test note"};
		
		boolean allFlag = Arrays.equals(allactualData, allexpectedData);
		
		if(allFlag)
			Assert.assertTrue(true);
		else 
			Assert.assertTrue(false);
		
		Log.endTestCase("Verify Data Of Specific Space From View Section");

	}
	
	@Test(groups = "regression,sanity,smoke", priority = 7, dependsOnMethods = "createNewSpaceAllFields_TC_404")
	public void editRecordVerifyData_TC_407() throws InterruptedException {
		
		Log.startTestCase("Edit The Existing Space Data & Verify Data On DataTable & View Section");
		String imagepath = System.getProperty("user.dir") + "\\src\\test\\resources\\testimages\\updated_space.png";
		allspace.readFirstRecordDataTable("SL-Test2");
		allspace.openSpaceViewPage();
		updateSpaceItem = allspace.clickEditButtonViewSpace();
		

		allspace = updateSpaceItem.updateSpaceWithAllFields("Dry Sheltered", "SL-Test21", "No", "20", "30", "40", "10", "No", "No", "M-032",
				"SL-053",imagepath, "This is second test note");
		
		String[] actualData = allspace.readFirstRecordDataTable("SL-Test21");
		String[] expectedData = {"SL-Test21","Dry Sheltered","No","M-032","M-032: -"};
		boolean verifyDataTable = Arrays.equals(actualData, expectedData);
		
		String[] editedActualData = allspace.verifySpaceDataViewSection("all");
		String[] editedExpectedData = {"SL-Test21","No","Due to some issue",editedActualData[3],"Dry Sheltered","","20","30","40",
				"SL-053","No","No","10 Amps","M-032","This is second test note"};
		
		
		boolean verifyViewSection = Arrays.equals(editedActualData, editedExpectedData);

		if(verifyDataTable == true && verifyViewSection == true)
			Assert.assertTrue(true);
		else
			Assert.assertTrue(false);
		
		Log.endTestCase("Edit The Existing Space Data & Verify Data On DataTable & View Section");

	}
	
	
	@Test(groups = "regression,sanity,smoke", priority = 8, dependsOnMethods = "createNewSpaceAllFields_TC_404")
	public void addNoteFromViewSection_TC_408() throws InterruptedException {
		
		Log.startTestCase("Add New Note From View Section Of Space");
		allspace.readFirstRecordDataTable("SL-Test21");
		allspace.openSpaceViewPage();
		String[] Data = allspace.addNewNoteViewSection("This is test note without imp",0);
		if(Data[0].equals("This is test note without imp") && Data[1].equals("0"))
			Assert.assertTrue(true);
		else
			Assert.assertTrue(false);
		Log.endTestCase("Add New Note From View Section Of Space");
	}
	
	@Test(groups = "regression,sanity,smoke", priority = 9, dependsOnMethods = "createNewSpaceAllFields_TC_404")
	public void addNoteFromViewSectionMustRead_TC_409() throws InterruptedException {
		
		Log.startTestCase("Add Must Read New Note From View Section Of Space");
		allspace.readFirstRecordDataTable("SL-Test21");
		allspace.openSpaceViewPage();
		String[] Data = allspace.addNewNoteViewSection("This is test note imp",1);
		if(Data[0].equals("This is test note imp") && Data[1].equals("1"))
			Assert.assertTrue(true);
		else
			Assert.assertTrue(false);
		
		Log.endTestCase("Add Must Read New Note From View Section Of Space");
	}
	
	
	@Test(groups = "regression,sanity,smoke", priority = 10, dependsOnMethods = "createNewSpaceAllFields_TC_404")
	public void clickAddNoteBtnWithoutNotes_TC_410() {
		
		Log.startTestCase("Click Add Note Button Under View Space Page Without Entering Data");
		allspace.readFirstRecordDataTable("SL-Test21");
		allspace.openSpaceViewPage();
		allspace.clickAddNoteWithoutData();
		Log.endTestCase("Click Add Note Button Under View Space Page Without Entering Data");
	}
	
	@Test(groups = "regression,sanity,smoke", priority = 11, dependsOnMethods = {"addNoteFromViewSection_TC_408","addNoteFromViewSectionMustRead_TC_409"})
	public void deleteNoteFromViewSpacePage_TC_411() throws InterruptedException {
		
		Log.startTestCase("Verify Note Is Getting Deleted From View Space Page");
		
		allspace.readFirstRecordDataTable("SL-Test21");
		allspace.openSpaceViewPage();
		boolean impNoteDeleted = allspace.deleteNoteFromViewSection(1);
		driver.navigate().refresh();
		allspace.readFirstRecordDataTable("SL-Test21");
		allspace.openSpaceViewPage();
		boolean nonImpNoteDeleted = allspace.deleteNoteFromViewSection(0);
		
		if(impNoteDeleted == true && nonImpNoteDeleted == true)
			Assert.assertTrue(true);
		else
			Assert.assertTrue(false);
		
		Log.endTestCase("Verify Note Is Getting Deleted From View Space Page");


	}
	
	@Test(groups = "regression,sanity,smoke", priority = 12, dependsOnMethods = "editRecordVerifyData_TC_407")
	public void deleteNoteFromEditSpace_TC_412() throws InterruptedException {
		
		Log.startTestCase("Verify Note Is Getting Deleted From Edit Space Page");
		
		allspace.readFirstRecordDataTable("SL-Test21");
		allspace.openSpaceViewPage();
		updateSpaceItem = allspace.clickEditButtonViewSpace();
		boolean flag = updateSpaceItem.deleteNote("This is second test note");
		
		Assert.assertTrue(flag);
		Log.endTestCase("Verify Note Is Getting Deleted From Edit Space Page");
		
	}
	
	
	
	@Test(groups = "regression,sanity,smoke", priority = 13)
	public void importSpacesBulk_TC_413() {

		//========================== Need to Add the Space Type here =====================================================
		Log.startTestCase("Import Spaces In Bulk");
		importSpaces = allspace.openImportSpace();
		allspace = importSpaces.importSpacesData();
		
		
		  //Space Name, Status, Unavailability Reason, Unavailability Date, SpaceType, LinearBuffer, MaxLOA, MaxBeam, MaxDraft, 
		  //NearestSlip,Water,Rafting, Power,Hydrometer,Note 
		 
		
		boolean firstRecord, secondRecord;
		String[] actualData1 = allspace.readFirstRecordDataTable("TestSpace3");
		String[] expectedData1 = {"TestSpace3","TestSpaceType_01","Yes","M-027","M-027: -"};
		boolean verifyDataTable = Arrays.equals(actualData1, expectedData1);
		String[] actualDataView1 = allspace.verifySpaceDataViewSection("all");
		String[] expectedDataView1 = {"TestSpace3","Yes","","","TestSpaceType_01","ft","11","31","21",
				"","No","Yes","5 Amps","M-027","This is first bulk space"};
		
		boolean verifyViewSection = Arrays.equals(actualDataView1, expectedDataView1);

		if(verifyDataTable == true && verifyViewSection == true)
			firstRecord = true;
		else
			firstRecord = false;
		
		
		driver.navigate().refresh();
		
		String[] actualData2 = allspace.readFirstRecordDataTable("TestSpace4");
		String[] expectedData2 = {"TestSpace4","TestSpaceType_01","Yes","M-049","M-049: -"};
		verifyDataTable = Arrays.equals(actualData2, expectedData2);
	
		String[] actualDataView2 = allspace.verifySpaceDataViewSection("all");
		String[] expectedDataView2 = {"TestSpace4","Yes","","","TestSpaceType_01","ft","12","32","22",
				"","Yes","No","10 Amps","M-049","This is second bulk space"};
		
		
		verifyViewSection = Arrays.equals(actualDataView2, expectedDataView2);

		if(verifyDataTable == true && verifyViewSection == true)
			secondRecord = true;
		else
			secondRecord = false;
		
		
		if(firstRecord == true && secondRecord == true)
			Assert.assertTrue(true);
		else
			Assert.assertTrue(false);
		
		Log.endTestCase("Import Spaces In Bulk");

	} 
	
	@Test(groups = "regression,sanity,smoke", priority = 14)
	public void exportSpaceExcel_TC_414() {
		
		Log.startTestCase("Export Space Data To Excel Sheet");
		boolean flag = allspace.exportDataToExcel();
		Assert.assertTrue(flag);
		Log.endTestCase("Export Space Data To Excel Sheet");
	}
	
	@Test(groups = "regression,sanity,smoke", priority = 15)
	public void exportSpaceGoogleSheet_TC_415() throws GeneralSecurityException, IOException, InterruptedException {
		
		Log.startTestCase("Export Space Data To Google Sheet");
		boolean flag = allspace.exportDataToGoogleSheet();
		Assert.assertTrue(flag);
		Log.endTestCase("Export Space Data To Google Sheet");

	}
	
	@Test(groups = "regression,sanity,smoke", priority = 16, dependsOnMethods = "importSpacesBulk_TC_413")
	public void deleteSpaceTypeSpacesDeleted_TC_416() {
		
		Log.startTestCase("Deleting Space Type, Related Spaces Should Also Be Deleted");
		
		// ================ Delete SpaceType =========================
		
		boolean flag1 = allspace.searchSpace("TestSpace 3");
		boolean flag2 = allspace.searchSpace("TestSpace 4");
		
		if(flag1 == true && flag2 == true)
			Assert.assertTrue(true);
		else 
			Assert.assertTrue(false);
		
		Log.endTestCase("Deleting Space Type, Related Spaces Should Also Be Deleted");

	}
	
	@AfterMethod
	public void afterTest() {
		
		driver.get(prop.getProperty("logout_url"));
		BrowserFactory.getInstance().removeDriver();
	}

}
