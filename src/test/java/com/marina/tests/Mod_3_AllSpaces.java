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
import com.marina.pages.SpaceTypesAddTypesPage;
import com.marina.pages.SpaceTypesPage;
import com.marina.pages.UpdateSpaceItemPage;
import com.marina.utils.Log;
import com.marina.base.BrowserFactory;

public class Mod_3_AllSpaces extends TestBase {
	
	LoginPage lp;
	HomePage hp;
	SpaceTypesPage spacetype;
	SpaceTypesAddTypesPage spaceTypeAdd;
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


	@Test(groups = "regression,sanity,smoke", priority = 1, description = "Verify All Space Page")
	public void verifyAllSpacePage_TC_401() {
		
		Log.startTestCase("Verify All Space Page");
		Assert.assertEquals(allspace.getAllSpacePageHeading(), "Spaces");
		Log.endTestCase("Verify All Space Page");

		
	}
	
	@Test(groups = "regression,sanity,smoke", priority = 2, dependsOnMethods = "verifyAllSpacePage_TC_401",
			description = "Create New Space With Mandatory Fields")
	public void createNewSpaceMandatoryFields_TC_402() throws InterruptedException {

		Log.startTestCase("Create New Space With Mandatory Fields");
		add_new_space = allspace.clickAddSpaceBtn();
		allspace = add_new_space.createNewSpaceMandatoryFields("Automation Type", "SL-TestSpace", "Yes", "10", "20", 
				"30", "5", "Yes", "Yes", "M-100", "DS-03","1","10");
		
		String[] actualData = allspace.readFirstRecordDataTable("");
		String[] expectedData = {"SL-TestSpace","Automation Type","Yes","M-100","M-100: -"};
		Assert.assertEquals(actualData, expectedData);
		Log.endTestCase("Create New Space With Mandatory Fields");

		
	}
	
	@Test(groups = "regression,sanity,smoke", priority = 3,dependsOnMethods = "createNewSpaceMandatoryFields_TC_402",
			description = "Verify Additional Form Fields Appears For Unavailibility Of Space")
	public void verifyAdditionalFieldsForUnAvailibility_TC_403() {
		
		Log.startTestCase("Verify Additional Form Fields Appears For Unavailibility Of Space");
		add_new_space = allspace.clickAddSpaceBtn();
		boolean flag = add_new_space.checkNotAvailibilityStatusAdditionalFields();
		Assert.assertTrue(flag);
		Log.endTestCase("Verify Additional Form Fields Appears For Unavailibility Of Space");

	}
	
	
	@Test(groups = "regression,sanity,smoke", priority = 4, dependsOnMethods = "verifyAdditionalFieldsForUnAvailibility_TC_403", 
			description = "Create New Space With All Fields")
	public void createNewSpaceAllFields_TC_404() throws InterruptedException {

		Log.startTestCase("Create New Space With All Fields");	
		String imagepath = System.getProperty("user.dir") + "\\src\\test\\resources\\testimages\\space_image.png";
		add_new_space = allspace.clickAddSpaceBtn();
		allspace = add_new_space.createSpaceWithAllFields("Automation Type", "SL-Test2Space", "No", "10", "20", 
				"30", "5", "Yes", "Yes", "M-101", "DS-03","1",imagepath,"This is test note");

		String[] actualData = allspace.readFirstRecordDataTable("SL-Test2Space");
		String[] expectedData = {"SL-Test2Space","Automation Type","No","M-101","M-101: -"};
		Assert.assertEquals(actualData, expectedData);
		Log.endTestCase("Create New Space With All Fields");

	}
	
	@Test(groups = "regression,sanity,smoke", priority = 5, dependsOnMethods = {"createNewSpaceMandatoryFields_TC_402","createNewSpaceAllFields_TC_404"},
			description = "Verify Data Of Created Spaces In Data Table Appeaing Under All Spaces Page")
	public void verifySearchFeatureInDataTable_TC_405() {
		
		Log.startTestCase("Verify Data Of Created Spaces In Data Table Appeaing Under All Spaces Page");
		
		String[] allActualData = allspace.readFirstRecordDataTable("SL-Test2Space");
		String[] allExpectedData = {"SL-Test2Space","Automation Type","No","M-101","M-101: -"};
		
		boolean allFlag = Arrays.equals(allActualData, allExpectedData);
		
		if(allFlag)
			Assert.assertTrue(true);
		else 
			Assert.assertTrue(false);
		Log.endTestCase("Verify Data Of Created Spaces In Data Table Appeaing Under All Spaces Page");

	}
	
	@Test(groups = "regression,sanity,smoke", priority = 6, dependsOnMethods = {"createNewSpaceMandatoryFields_TC_402","createNewSpaceAllFields_TC_404"},
		description = "Verify Data Of Specific Space From View Section")
	
	public void verifySpecificSpaceViewSection_TC_406() throws InterruptedException {
		
		Log.startTestCase("Verify Data Of Specific Space From View Section");
		
		allspace.readFirstRecordDataTable("SL-Test2Space");
		String[] allactualData = allspace.verifySpaceDataViewSection("all");
		String[] allexpectedData = {"SL-Test2Space","No","Due to some issue",allactualData[3],"Automation Type","1 ft","10","20","30",
				"DS-03","Yes","Yes","5 Amps","M-101","This is test note"};
		
		boolean allFlag = Arrays.equals(allactualData, allexpectedData);
		
		if(allFlag)
			Assert.assertTrue(true);
		else 
			Assert.assertTrue(false);
		
		Log.endTestCase("Verify Data Of Specific Space From View Section");

	}
	
	@Test(groups = "regression,sanity,smoke", priority = 7, dependsOnMethods = "createNewSpaceAllFields_TC_404",
			description = "Edit The Existing Space Data & Verify Data On DataTable & View Section")

	public void editRecordVerifyData_TC_407() throws InterruptedException {
		
		Log.startTestCase("Edit The Existing Space Data & Verify Data On DataTable & View Section");
		String imagepath = System.getProperty("user.dir") + "\\src\\test\\resources\\testimages\\updated_space.png";
		allspace.readFirstRecordDataTable("SL-Test2Space");
		allspace.openSpaceViewPage();
		updateSpaceItem = allspace.clickEditButtonViewSpace();
		

		allspace = updateSpaceItem.updateSpaceWithAllFields("Linear Dockage", "SL-Test21", "No", "20", "30", "40", "10", "No", "No", "M-102",
				"SL-TestSpace",imagepath, "This is second test note");
		
		String[] actualData = allspace.readFirstRecordDataTable("SL-Test21");
		String[] expectedData = {"SL-Test21","Linear Dockage","No","M-102","M-102: -"};
		boolean verifyDataTable = Arrays.equals(actualData, expectedData);
		
		String[] editedActualData = allspace.verifySpaceDataViewSection("all");
		String[] editedExpectedData = {"SL-Test21","No","Due to some issue",editedActualData[3],"Linear Dockage","1 ft","20","30","40",
				"SL-TestSpace","No","No","10 Amps","M-102","This is second test note"};
		
		
		boolean verifyViewSection = Arrays.equals(editedActualData, editedExpectedData);

		if(verifyDataTable == true && verifyViewSection == true)
			Assert.assertTrue(true);
		else
			Assert.assertTrue(false);
		
		Log.endTestCase("Edit The Existing Space Data & Verify Data On DataTable & View Section");

	}
	
	
	@Test(groups = "regression,sanity,smoke", priority = 8, dependsOnMethods = "createNewSpaceAllFields_TC_404",
			description = "Add New Note From View Section Of Space")
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
	
	@Test(groups = "regression,sanity,smoke", priority = 9, dependsOnMethods = "createNewSpaceAllFields_TC_404",
			description = "Add Must Read New Note From View Section Of Space")
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
	
	
	@Test(groups = "regression,sanity,smoke", priority = 10, dependsOnMethods = "createNewSpaceAllFields_TC_404",
			description = "Click Add Note Button Under View Space Page Without Entering Data")
	public void clickAddNoteBtnWithoutNotes_TC_410() throws InterruptedException {
		
		Log.startTestCase("Click Add Note Button Under View Space Page Without Entering Data");
		allspace.readFirstRecordDataTable("SL-Test21");
		allspace.openSpaceViewPage();
		boolean flag = allspace.clickAddNoteWithoutData();
		if(flag)
			Assert.assertTrue(flag);
		else
			Assert.assertTrue(flag,"Javascript network error alert is appearing");
		
		Log.endTestCase("Click Add Note Button Under View Space Page Without Entering Data");
	}
	
	@Test(groups = "regression,sanity,smoke", priority = 11, dependsOnMethods = {"addNoteFromViewSection_TC_408","addNoteFromViewSectionMustRead_TC_409"},
			description = "Verify Note Is Getting Deleted From View Space Page")
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
	
	@Test(groups = "regression,sanity,smoke", priority = 12, dependsOnMethods = "editRecordVerifyData_TC_407",
			description = "Verify Note Is Getting Deleted From Edit Space Page")
	public void deleteNoteFromEditSpace_TC_412() throws InterruptedException {
		
		Log.startTestCase("Verify Note Is Getting Deleted From Edit Space Page");
		
		allspace.readFirstRecordDataTable("SL-Test21");
		allspace.openSpaceViewPage();
		updateSpaceItem = allspace.clickEditButtonViewSpace();
		boolean flag = updateSpaceItem.deleteNote("This is second test note");
		
		Assert.assertTrue(flag);
		Log.endTestCase("Verify Note Is Getting Deleted From Edit Space Page");
		
	}
	
	
	
	@Test(groups = "regression,sanity,smoke", priority = 13,dependsOnMethods = "deleteNoteFromEditSpace_TC_412",
			description = "Import Spaces In Bulk")
	public void importSpacesBulk_TC_413() throws InterruptedException {

		//========================== Need to Add the Space Type here =====================================================
		Log.startTestCase("Import Spaces In Bulk");
		
		spacetype = hp.spaces_dropdown_SpaceTypes();
		spaceTypeAdd = spacetype.add_space_type();
		spaceTypeAdd.addSingleSpaceMandatoryFields("TestSpaceType_01", "Multiple Boats", "$/period", "Nightly", "Wet Storage", "No");
		String[] addedSpaceType = spacetype.get_space_data_from_table("TestSpaceType_01");
		
		if(addedSpaceType[1].equals("TestSpaceType_01")) {
			
			
			allspace = hp.spaces_dropdown_AllSpaces();
			importSpaces = allspace.openImportSpace();
			allspace = importSpaces.importSpacesData("space_bulk_import");
			allspace.getAllSpacePageHeading();
			
			  //Space Name, Status, Unavailability Reason, Unavailability Date, SpaceType, LinearBuffer, MaxLOA, MaxBeam, MaxDraft, 
			  //NearestSlip,Water,Rafting, Power,Hydrometer,Note 
			 
			
			boolean firstRecord, secondRecord;
			String[] actualData1 = allspace.readFirstRecordDataTable("TestSpace3");
			String[] expectedData1 = {"TestSpace3","TestSpaceType_01","Yes","M-103","M-103: -"};
			boolean verifyDataTable = Arrays.equals(actualData1, expectedData1);
			String[] actualDataView1 = allspace.verifySpaceDataViewSection("all");
			String[] expectedDataView1 = {"TestSpace3","Yes","","","TestSpaceType_01","ft","11","31","21",
					"","No","Yes","5 Amps","M-103","This is first bulk space"};
			
			boolean verifyViewSection = Arrays.equals(actualDataView1, expectedDataView1);

			if(verifyDataTable == true && verifyViewSection == true)
				firstRecord = true;
			else
				firstRecord = false;
			
			
			driver.navigate().refresh();
			
			String[] actualData2 = allspace.readFirstRecordDataTable("TestSpace4");
			String[] expectedData2 = {"TestSpace4","TestSpaceType_01","Yes","M-104","M-104: -"};
			verifyDataTable = Arrays.equals(actualData2, expectedData2);
		
			String[] actualDataView2 = allspace.verifySpaceDataViewSection("all");
			String[] expectedDataView2 = {"TestSpace4","Yes","","","TestSpaceType_01","ft","12","32","22",
					"","Yes","No","10 Amps","M-104","This is second bulk space"};
			
			
			verifyViewSection = Arrays.equals(actualDataView2, expectedDataView2);

			if(verifyDataTable == true && verifyViewSection == true)
				secondRecord = true;
			else
				secondRecord = false;
			
			
			if(firstRecord == true && secondRecord == true)
				Assert.assertTrue(true);
			else
				Assert.assertTrue(false);
		}else 
			Assert.assertTrue(false, "Space Type Is Not Getting Created");
		
		
		
		Log.endTestCase("Import Spaces In Bulk");

	} 
	
	@Test(groups = "regression,sanity,smoke", priority = 14, dependsOnMethods = "importSpacesBulk_TC_413", 
			description = "Export Space Data To Excel Sheet")
	public void exportSpaceExcel_TC_414() {
		
		Log.startTestCase("Export Space Data To Excel Sheet");
		boolean flag = allspace.exportDataToExcel();
		Assert.assertTrue(flag);
		Log.endTestCase("Export Space Data To Excel Sheet");
	}
	
	@Test(groups = "regression,sanity,smoke", priority = 15, dependsOnMethods = "exportSpaceExcel_TC_414", 
			description = "Export Space Data To Google Sheet")
	public void exportSpaceGoogleSheet_TC_415() throws GeneralSecurityException, IOException, InterruptedException {
		
		Log.startTestCase("Export Space Data To Google Sheet");
		boolean flag = allspace.exportDataToGoogleSheet();
		Assert.assertTrue(flag);
		Log.endTestCase("Export Space Data To Google Sheet");

	}
	
	@Test(groups = "regression,sanity,smoke", priority = 16, dependsOnMethods = "importSpacesBulk_TC_413",
			description = "Deleting Space Type, Related Spaces Should Also Be Deleted")
	public void deleteSpaceTypeSpacesDeleted_TC_416() throws InterruptedException {
		
		Log.startTestCase("Deleting Space Type, Related Spaces Should Also Be Deleted");
		
		// ================ Delete SpaceType =========================
		spacetype = hp.spaces_dropdown_SpaceTypes();
		spacetype.delete_space("TestSpaceType_01");
		allspace = hp.spaces_dropdown_AllSpaces();
		
		boolean flag1 = allspace.deleteSpace("TestSpace 3");
		boolean flag2 = allspace.deleteSpace("TestSpace 4");
		allspace.deleteSpace("SL-TestSpace");
		allspace.deleteSpace("SL-Test21");
		
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
