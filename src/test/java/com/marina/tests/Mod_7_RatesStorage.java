package com.marina.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.marina.base.BrowserFactory;
import com.marina.base.TestBase;
import com.marina.pages.HomePage;
import com.marina.pages.LoginPage;
import com.marina.pages.RatesStorageNightlyPricePage;
import com.marina.pages.RatesStoragePage;
import com.marina.pages.SpaceTypesAddTypesPage;
import com.marina.pages.SpaceTypesPage;
import com.marina.utils.Log;

public class Mod_7_RatesStorage extends TestBase {

	LoginPage lp;
	HomePage hp;
	SpaceTypesPage stp;
	SpaceTypesAddTypesPage statp;
	RatesStoragePage rsp;
	RatesStorageNightlyPricePage rsnpp;

	@BeforeMethod
	public void beforeTest() throws InterruptedException {

		browserIntialization();
		lp = new LoginPage(driver);
		hp = lp.login(prop.getProperty("email"), prop.getProperty("password"));
		rsp = hp.spaces_dropdown_rates();

	}

	@Test(groups = { "regression", "smoke" }, priority = 1, description = "Creating Rate Group from Space Type,"
			+ " check rate group should be available")
	public void createGroup_verifyGroup_TC_1101() throws InterruptedException {

		Log.startTestCase("Creating Rate Group from Space Type, check rate group should be available Tc_1101");
		String rate_new_group_name = "automation test user test4";
		stp = hp.space_types_click_Mod7_ratesStorage();
		statp = stp.click_btn_add_space_type();
		stp = statp.adding_rates_group(rate_new_group_name);
		hp = stp.breadcrumbs_homePage();
		rsp = hp.spaces_dropdown_rates();
		boolean name =  rsp.rate_group_list(rate_new_group_name);
		Assert.assertTrue(name);
		Log.endTestCase("Creating Rate Group from Space Type, check rate group should be available Tc_1101");
//done
		
	}

	
	
	
	@Test(groups = { "regression",
			"smoke" }, priority = 1, description = "No Revision History should be available in newly created rate group")
	public void newlyGroupCreated_RevisionHisotryEmpty_TC_1102() throws InterruptedException {

		Log.startTestCase("No Revision History should be available in newly created rate group Tc_1102");
		String rate_new_group_name = "automation test revision hisotry";
		stp = hp.space_types_click_Mod7_ratesStorage();
		statp = stp.click_btn_add_space_type();
		stp = statp.adding_rates_group(rate_new_group_name);
		hp = stp.breadcrumbs_homePage();
		rsp = hp.spaces_dropdown_rates();
		rsp.search_specifig_rates_group(rate_new_group_name);
		rsnpp = rsp.search_specifig_rates_group("testing manual");
		boolean[] recrod_data = rsnpp.newly_group_rivision_history();

		/*
		 * 
		 * 
		 * in progress
		 * 
		 * /
		 */
		
		
		
		boolean[] expected_value = new boolean[2];
		expected_value[0] = true;
		expected_value[1] = true;

		Assert.assertEquals(recrod_data, expected_value);

		
		Log.endTestCase("No Revision History should be available in newly created rate group Tc_1102");

	}
	
	

//	public void verifySp() {
//
//		Log.startTestCase("Clicking space type link should open space type page TC_201");
//		Assert.assertEquals(stp.space_type_page_verify(), "Space Types");
//		Log.endTestCase("Clicking space type link should open space type page TC_201");
//
//	}

	@AfterMethod
	public void afterTest() {

		driver.get(prop.getProperty("logout_url"));
		BrowserFactory.getInstance().removeDriver();

	}

}
