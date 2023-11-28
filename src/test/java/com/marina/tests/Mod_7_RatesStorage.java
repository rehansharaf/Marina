package com.marina.tests;

import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.marina.base.BrowserFactory;
import com.marina.base.TestBase;
import com.marina.pages.HomePage;
import com.marina.pages.LoginPage;
import com.marina.pages.RatesStorageAnnualPricePage;
import com.marina.pages.RatesStorageMonthlyPricePage;
import com.marina.pages.RatesStorageNightlyPricePage;
import com.marina.pages.RatesStorageNightlyPrice_add_updatePage;
import com.marina.pages.RatesStoragePage;
import com.marina.pages.RatesStorageflatPricePage;
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
	RatesStorageMonthlyPricePage rsmpp;
	RatesStorageAnnualPricePage rsapp;
	RatesStorageflatPricePage rsfpp;
	RatesStorageNightlyPrice_add_updatePage rsnpaup;

	String rate_new_group_name = "automation tc1102";

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
		
		stp = hp.space_types_click_Mod7_ratesStorage();
		statp = stp.click_btn_add_space_type();
		stp = statp.adding_rates_group(rate_new_group_name);
		hp = stp.breadcrumbs_homePage();
		rsp = hp.spaces_dropdown_rates();
		boolean name =  rsp.rate_group_list(rate_new_group_name);
		Assert.assertTrue(name);
		Log.endTestCase("Creating Rate Group from Space Type, check rate group should be available Tc_1101");

		
	}
	
	
	
	
//	dependsOnMethods = "newlyGroupCreated_RevisionHisotryEmpty_TC_1101"
			
	@Test(groups = { "regression",
			"smoke" }, priority = 2, description = "No Revision History should be available in newly created rate group")

	public void newlyGroupCreated_RevisionHisotryEmpty_TC_1102() throws InterruptedException {

		Log.startTestCase("No Revision History should be available in newly created rate group Tc_1102");

		stp = hp.space_types_click_Mod7_ratesStorage();
		statp = stp.click_btn_add_space_type();
		stp = statp.adding_rates_group(rate_new_group_name);
		hp = stp.breadcrumbs_homePage();
		rsp = hp.spaces_dropdown_rates();

		System.out.println("testing");
		rsp.search_specifig_rates_group(rate_new_group_name);
		boolean[] expected_nightly_data = new boolean[2];
		expected_nightly_data[0] = true;
		System.out.println("test");
		expected_nightly_data[1] = true;

		rsnpp = rsp.search_specifig_rates_group(rate_new_group_name);
		boolean[] recrod_nightly_data = rsnpp.newly_group_nightly_rivision_history();

		rsmpp = rsnpp.single_click_monthly_price();
		boolean record_monthly_data = rsmpp.newly_group_monthly_rivision_history();

		rsapp = rsmpp.single_click_annual_price();
		boolean record_annual_data = rsapp.newly_group_annual_rivision_history();

		rsfpp = rsapp.single_click_flat_price();
		boolean record_flat_data = rsfpp.newly_group_annual_rivision_history();

		Assert.assertEquals(recrod_nightly_data, expected_nightly_data);
		Assert.assertEquals(record_monthly_data, true);
		Assert.assertEquals(record_annual_data, true);
		Assert.assertEquals(record_flat_data, true);

		Log.endTestCase("No Revision History should be available in newly created rate group Tc_1102");

	}

	
	
	
//	dependsOnMethods = "newlyGroupCreated_RevisionHisotryEmpty_TC_1102"
	
	@Test(groups = { "regression",
			"smoke" }, priority = 3, description = "Add New buckets and verify in specific rate group")
	public void nighlty_addNew_Bucket_andVerifyBucket_TC_1103() throws InterruptedException {

		Log.startTestCase("Add New buckets and verify in specific rate group Tc_1103");
		rsnpp = rsp.search_specifig_rates_group(rate_new_group_name);

		rsnpaup = rsnpp.click_update_btn_return_edit_page();
		rsnpaup.click_bucket_btn();
		rsnpp = rsnpaup.add_more_buckets();
		boolean newly_bucket_result = rsnpp.fouth_buckets_check();

		Assert.assertEquals(newly_bucket_result, true);

		Log.endTestCase("Add New buckets and verify in specific rate group Tc_1103");

	}
	
	

	
//	 dependsOnMethods = "nighlty_addNew_Bucket_andVerifyBucket_TC_1103"
			 
	@Test(groups = { "regression",
			"smoke" }, priority = 4, description = "Delete newly added buckets and verify rate group")
	public void nightly_delete_newley_bucketAndVerify_TC_1104() throws InterruptedException {

		
		Log.startTestCase("Delete newly added buckets and verify rate group TC_1104");
		rsnpp = rsp.search_specifig_rates_group(rate_new_group_name);

		rsnpaup = rsnpp.click_update_btn_return_edit_page();
		rsnpaup.click_bucket_btn();
		rsnpp = rsnpaup.delete_newly_bucket();
		boolean newly_bucket_result = rsnpp.after_delete_fouthBucket_verify_delete();

		Assert.assertEquals(newly_bucket_result, true);

		Log.endTestCase("Delete newly added buckets and verify rate group TC_1104");

	}
	
	
	
	
//	dependsOnMethods = "newlyGroupCreated_RevisionHisotryEmpty_TC_1102"
	
//	@Test(groups = { "regression",
//			"smoke" }, priority = 5, description = "Create Nightly rate group with single row and without holiday")
//	public void nightly_add_record_without_holiday_TC_1105() throws InterruptedException {
//
//		Log.startTestCase("Create Nightly rate group with single row and without holiday TC_1105");
//		rsnpp = rsp.search_specifig_rates_group(rate_new_group_name);
//		rsnpaup = rsnpp.click_update_btn_return_edit_page();
//		rsnpp =rsnpaup.add_12_month_price_in_one_row();
//		
//		
//		/*
//		 * 
//		 * 
//		 * /
//		 */
//		
//		
//		
//		
//		
//		
//		rsnpaup.click_bucket_btn();
//		rsnpp = rsnpaup.delete_newly_bucket();
//		boolean newly_bucket_result = rsnpp.after_delete_fouthBucket_verify_delete();
//
//		Assert.assertEquals(newly_bucket_result, true);
//
//		Log.endTestCase("Create Nightly rate group with single row and without holiday TC_1105");
//		
//		
//		
//
//}
	



	@AfterMethod
	public void afterTest() {

		driver.get(prop.getProperty("logout_url"));
		BrowserFactory.getInstance().removeDriver();

	}

}
