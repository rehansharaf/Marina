package com.marina.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.marina.base.BrowserFactory;
import com.marina.base.TestBase;
import com.marina.pages.HomePage;
import com.marina.pages.LoginPage;
import com.marina.pages.ReservationsPage;
import com.marina.pages.SpaceGroupsPage;
import com.marina.utils.Log;

public class Mod_5_SpaceGroup extends TestBase {
	
	LoginPage lp;
	HomePage hp;
	SpaceGroupsPage sg;
	ReservationsPage res;
	
	@BeforeMethod
	public void beforeMethod() throws InterruptedException {
		
		browserIntialization();
		lp = new LoginPage(driver);
		hp = lp.login(prop.getProperty("email"), prop.getProperty("password"));
		sg = hp.spaces_dropdown_SpaceGroups();
	}
	
	
	@Test(groups = "regression,sanity,smoke", priority = 1)
	public void verifySpaceGroupPage_TC_801() {
		
		Log.startTestCase("Verify Space Group Page Gets Open");
		String pageHeading = sg.verifyPageHeading();
		Assert.assertEquals(pageHeading, "Space Groups");
		Log.endTestCase("Verify Space Group Page Gets Open");
	}

	
	@Test(groups = "regression,sanity,smoke", priority = 2, dependsOnMethods = "verifySpaceGroupPage_TC_801")
	public void checkReservSpacesAvail_TC_802() throws InterruptedException {
		
		Log.startTestCase("Check Only Those Spaces Should Be Available In List That Have Reservation Available");
		
		String[] Data = sg.getSpacesList();
		res = hp.reservationLink();
		boolean flag = res.searchSlipsAvailableReservation(Data);
		
		Assert.assertTrue(flag);
		Log.endTestCase("Check Only Those Spaces Should Be Available In List That Have Reservation Available");

	}
	
	
	@Test(groups = "regression,sanity,smoke", priority = 3, dependsOnMethods = "verifySpaceGroupPage_TC_801")
	public void addSpaceGroupSingleSpace_TC_803() throws InterruptedException {
		
		Log.startTestCase("Verify Creating Space Group By Adding Single Space");
		boolean flag = sg.addSpaceGroup(1, "TestGroup1");  //No Of Slip need to add, Space Group Name
		
		Assert.assertTrue(flag);
		Log.endTestCase("Verify Creating Space Group By Adding Single Space");
		
		
	}
	
	@Test(groups = "regression,sanity,smoke", priority = 4, dependsOnMethods = "addSpaceGroupSingleSpace_TC_803")
	public void addSpaceGroupMultipleSpace_TC_804() throws InterruptedException {
		
		Log.startTestCase("Verify Creating Space Group By Adding Multiple Spaces");
		boolean flag = sg.addSpaceGroup(2, "TestGroup2");  //No Of Slip need to add, Space Group Name
		
		Assert.assertTrue(flag);
		Log.endTestCase("Verify Creating Space Group By Adding Multiple Spaces");
		
	}
	
	
	@Test(groups = "regression,sanity,smoke", priority = 5)
	public void searchSlipByNameType_TC_805() throws InterruptedException {
		
		Log.startTestCase("Search Slip By Name Or Type Through Add Space Group Section");
		
		boolean flag = sg.searchSpaceByNameType(4);
		Assert.assertTrue(flag);
		Log.endTestCase("Search Slip By Name Or Type Through Add Space Group Section");

	}
	
	
	@Test(groups = "regression,sanity,smoke", priority = 6, dependsOnMethods = {"addSpaceGroupSingleSpace_TC_803","addSpaceGroupMultipleSpace_TC_804"})
	public void editAlreadyExistingGroup_TC_806() throws InterruptedException {
		
		Log.startTestCase("Edit Already Existing Space Group & Verify Data");
		boolean flag1 = sg.editSpaceGroup("TestGroup1", "select");
		boolean flag2 = sg.editSpaceGroup("TestGroup2", "deselect");
		
		if(flag1 == true && flag2 == true)
			Assert.assertTrue(true);
		else
			Assert.assertTrue(false);
		
		Log.endTestCase("Edit Already Existing Space Group & Verify Data");

	}
	
	
	@Test(groups = "regression,sanity,smoke", priority = 7, dependsOnMethods = {"addSpaceGroupSingleSpace_TC_803"})
	public void createGroupSameName_TC_807() throws InterruptedException {
		
		Log.startTestCase("Create Space Group With Same Name");		
		boolean flag = sg.addingGroupSameName(0, "TestGroup1");
		Assert.assertTrue(flag);
		Log.endTestCase("Create Space Group With Same Name");
	}
	
	
	@Test(groups = "regression,sanity,smoke", priority = 8, dependsOnMethods = "addSpaceGroupSingleSpace_TC_803")
	public void deleteExistingSpaceGroup_TC_808() throws InterruptedException {
	
		Log.startTestCase("Deleting Existing Space Groups");	
		boolean flag = sg.deletingSpaceGroup("TestGroup1");
		Assert.assertTrue(flag);
		Log.endTestCase("Deleting Existing Space Groups");
		
	}

	
	@AfterMethod
	public void afterTest() {
		
		driver.get(prop.getProperty("logout_url"));
		BrowserFactory.getInstance().removeDriver();
		
	}
}
