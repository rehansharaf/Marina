package com.marina.tests;

import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.marina.actiondriver.Action;
import com.marina.base.BrowserFactory;
import com.marina.base.TestBase;
import com.marina.pages.BoatGroupsPage;
import com.marina.pages.HomePage;
import com.marina.pages.LoginPage;
import com.marina.utils.Log;

public class Mod_6_BoatGroup extends TestBase {

	LoginPage lp;
	HomePage hp;
	BoatGroupsPage bg;
	
	@BeforeMethod
	public void beforeMethod() throws InterruptedException {
		
		browserIntialization();
		lp = new LoginPage(driver);
		hp = lp.login(prop.getProperty("email"), prop.getProperty("password"));
		bg = hp.spaces_dropdown_BoatGroups();
		
	}
	
	
	@Test(groups = "regression,sanity,smoke", priority = 1)
	public void verifyBoatGroupPageOpens_TC_1001() {
		
		Log.startTestCase("Check Boat Group Page Gets Open");
		String pageHeading = bg.verifyBoatGroupPageHeading();
		Assert.assertEquals(pageHeading, "Boat Groups");
		Log.endTestCase("Check Boat Group Page Gets Open");
	}
	
	@Test(groups = "regression,sanity,smoke", priority = 2, dependsOnMethods = "verifyBoatGroupPageOpens_TC_1001")
	public void addBoatGroupMandatoryFields_TC_1002() throws InterruptedException {
		
		Log.startTestCase("Add Boat Group With Mandatory Fields");
		boolean flag = bg.addBoatGroupMandatoryFields("TestBoatGroup1");
		Assert.assertTrue(flag);
		Log.endTestCase("Add Boat Group With Mandatory Fields");
	}
	
	
	@Test(groups = "regression,sanity,smoke", priority = 3, dependsOnMethods = "addBoatGroupMandatoryFields_TC_1002")
	public void addBoatGroupAllFields_TC_1003() throws InterruptedException {
		
		Log.startTestCase("Add Boat Group With All Fields");
		
		
		//  addBoatGroupAllFields(String groupName, String contract, String document, String spaceType, String paymentType, String discount,
		//	String notes)
		 
		boolean flag = bg.addBoatGroupAllFields("TestBoatGroup2", "Moorage Contract Template (One Time Payment)", "Terms and Conditions", "Automation Type", 
				"Flat Rate", "Regular Discount", "This is sample boatgroup note");
		
		Assert.assertTrue(flag);
		Log.endTestCase("Add Boat Group With All Fields");
		
	}
	
	
	
	@Test(groups = "regression,sanity,smoke", priority = 4, dependsOnMethods = {"addBoatGroupAllFields_TC_1003"})
	public void searchBoatGroup_TC_1004() throws InterruptedException {
		
		Log.startTestCase("Search Specific Boat Group");
		int recordCount = bg.searchBoatGroup("TestBoatGroup2");
		Assert.assertEquals(recordCount, 1);
		Log.endTestCase("Search Specific Boat Group");
	}
	
	
	@Test(groups = "regression,sanity,smoke", priority = 5, dependsOnMethods = {"addBoatGroupAllFields_TC_1003"})
	public void viewSavedDataBoatGroup_TC_1005() throws InterruptedException {

		
		Log.startTestCase("Verify All The Saved Information Of Boat Group On View Page");
		
		Action action = new Action();
		String todayDate = action.getCurrentDate(1,0,0,"MM-dd-yyyy");
		String afterDate = action.getCurrentDate(0,1,6,"MM-dd-yyyy");
		
		String dateFormated = todayDate+" to "+afterDate;
		
		String[] actualData = bg.verifySavedBoatGroupInfo("TestBoatGroup2");
		String[] expectedData = {"Equal "+dateFormated,"Flat Rate","Automation Type","Regular Discount",
					"Terms and Conditions","Moorage Contract Template (One Time Payment)","This is sample boatgroup note"};
		
		boolean flag = Arrays.equals(actualData, expectedData);
		Assert.assertTrue(flag);
		Log.endTestCase("Verify All The Saved Information Of Boat Group On View Page");
		
	}
	
	
	@Test(groups = "regression,sanity,smoke", priority = 6, dependsOnMethods = {"addBoatGroupAllFields_TC_1003"})
	public void viewPageNewBoatAdd_TC_1006() throws InterruptedException {
		
		Log.startTestCase("Add New Boat From View Page Of Boat Group");
		boolean flag = bg.addBoatViewPage("TestBoatGroup2", "Boat1");	
		Assert.assertTrue(flag);
		Log.endTestCase("Add New Boat From View Page Of Boat Group");

	}
	
	
	
	@Test(groups = "regression,sanity,smoke", priority = 7, dependsOnMethods = {"addBoatGroupAllFields_TC_1003"})
	public void addBoatMainPage_TC_1007() throws InterruptedException {
		
		Log.startTestCase("Add New Boat From Main Boat Group Page");
		bg.addBoatMainPage("TestBoatGroup2", "Boat1");
		Log.endTestCase("Add New Boat From Main Boat Group Page");
	}
	
	
	
	@Test(groups = "regression,sanity,smoke", priority = 8, dependsOnMethods = {"addBoatGroupAllFields_TC_1003"})
	public void editBoatGroupVerifyData_TC_1008() throws InterruptedException {
		
		Log.startTestCase("Edit Specific Boat Group & Verify Its Data");
		
		boolean updatedDataFlag = bg.editBoatGroup("TestBoatGroup2", "TestBoatGroup2_Edited", "Test document", "Dry Sheltered", "Monthly", "Special discount (5% off)", 
				"This is sample boatgroup note edited");	
		
		bg = hp.spaces_dropdown_BoatGroups();
		boolean updatedBoatGroupExist = bg.checkBoatGroupExist("TestBoatGroup2_Edited");
		
		if(updatedDataFlag == true && updatedBoatGroupExist == true)
			Assert.assertTrue(true);
		else
			Assert.assertTrue(false);
		
		Log.endTestCase("Edit Specific Boat Group & Verify Its Data");
		
	}

	
	@Test(groups = "regression,sanity,smoke", priority = 9, dependsOnMethods = {"addBoatGroupAllFields_TC_1003"})
	public void generatingMultipleAddRequest_TC_1009() throws InterruptedException {
		
		Log.startTestCase("Check If Multiple Groups Generating While Clicking Add Button Multiple Times");
		boolean recordCreatedFlag = bg.generatingMultipleRequestsAddGroup("Test1");
		
		if(recordCreatedFlag == true) {
			
			bg = hp.spaces_dropdown_BoatGroups();
			Assert.assertEquals(bg.checkNoOfGroupsCreated("Test1"), 1);
		
		}else
			Assert.assertTrue(recordCreatedFlag);	
		Log.endTestCase("Check If Multiple Groups Generating While Clicking Add Button Multiple Times");
	
	}
	
	
	@Test(groups = "regression,sanity,smoke", priority = 10, dependsOnMethods = {"editBoatGroupVerifyData_TC_1008"})
	public void verifyCopyFunctionBoatGroup_TC_1010() throws InterruptedException {
		
		Log.startTestCase("Check & Verify The Copy Functionality Of Boat Group");
		boolean flag = bg.createCopyBoatGroup("TestBoatGroup2_Edited");
		Assert.assertTrue(flag);
		Log.endTestCase("Check & Verify The Copy Functionality Of Boat Group");
		
	}
	
	
	
	@Test(groups = "regression,sanity,smoke", priority = 11, dependsOnMethods = "verifyCopyFunctionBoatGroup_TC_1010")
	public void deleteBoatGroups_TC_1011() throws InterruptedException {
		
		Log.startTestCase("Check Delete Boat Group Functionality");
		boolean flag = bg.deleteBoatGroups();
		Assert.assertTrue(flag);
		Log.endTestCase("Check Delete Boat Group Functionality");
	}
	
	@AfterMethod
	public void afterMethod() {
		
		driver.get(prop.getProperty("logout_url"));
		BrowserFactory.getInstance().removeDriver();
	}
	
}
