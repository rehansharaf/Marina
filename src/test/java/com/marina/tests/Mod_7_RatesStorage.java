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

	String rate_new_group_name = "Mod 7 All test";
	String[] zero_to_50_ft_calendar_table_record_dec_2024;
	String[] fifty_to_100_ft_calendar_table_record_dec_2023;
	String[] hundread_100_ft_calendar_table_record_dec_2023;
	
	
	
	
	@BeforeMethod
	public void beforeTest() throws InterruptedException {

		browserIntialization();
		lp = new LoginPage(driver);
		hp = lp.login(prop.getProperty("email"), prop.getProperty("password"));
		rsp = hp.spaces_dropdown_rates();

	}

	@Test(groups = { "regression", "smoke", "sanity" }, priority = 1, description = "Creating Rate Group from Space Type,"
			+ " check rate group should be available")


	public void createGroup_verifyGroup_TC_1101() throws InterruptedException {

		Log.startTestCase("Creating Rate Group from Space Type, check rate group should be available Tc_1101");
		
		stp = hp.space_types_click_Mod7_ratesStorage();
		statp = stp.click_btn_add_space_type();
		stp = statp.adding_rates_group(rate_new_group_name);
		Thread.sleep(1000);
		hp = stp.breadcrumbs_homePage();
		Thread.sleep(1000);
		rsp = hp.spaces_dropdown_rates();
		Thread.sleep(1000);
		boolean name =  rsp.rate_group_list(rate_new_group_name);
		Assert.assertTrue(name);
		Log.endTestCase("Creating Rate Group from Space Type, check rate group should be available Tc_1101");

		
	}

//	dependsOnMethods = "newlyGroupCreated_RevisionHisotryEmpty_TC_1101"

	@Test(groups = { "regression",
			"smoke" }, priority = 2, description = "No Revision History should be available in newly created rate group")

	public void newlyGroupCreated_RevisionHisotryEmpty_TC_1102() throws InterruptedException {

		Log.startTestCase("No Revision History should be available in newly created rate group Tc_1102");
	
		rsp.search_specifig_rates_group(rate_new_group_name);
		boolean[] expected_nightly_data = new boolean[2];
		expected_nightly_data[0] = true;
		
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

//	dependsOnMethods = "newlyGroupCreated_RevisionHisotryEmpty_TC_1101"

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
	
	

//	dependsOnMethods = "newlyGroupCreated_RevisionHisotryEmpty_TC_1101"

	
	@Test(groups = { "regression",
			"smoke" }, priority = 5, description = "Create Nightly rate group with single row and without holiday")
	public void nightly_add_record_without_holiday_TC_1105() throws InterruptedException {

		Log.startTestCase("Create Nightly rate group with single row and without holiday TC_1105");

		
		rsnpp = rsp.search_specifig_rates_group(rate_new_group_name);
		Thread.sleep(1000);
		String activeYear_currentTime [] = rsnpp.active_year();
		Thread.sleep(1000);
		String active_year = activeYear_currentTime[0];
		Thread.sleep(1000);
		
				
		rsnpaup = rsnpp.click_update_btn_return_edit_page();
		boolean succesfuuly_add_nightly_price = rsnpaup.add_12_month_price_in_one_week_row_first_bucket(active_year);

		Thread.sleep(1000);

		
		
		rsnpaup = rsnpp.click_update_btn_return_edit_page();
		rsnpaup.single_click_second_bucket_update(active_year);
		boolean second_bucket_priced_updated = rsnpaup.add_12_month_price_in_one_week_row_second_bucket(active_year);

		Thread.sleep(1000);

		rsnpaup = rsnpp.click_update_btn_return_edit_page();
		rsnpaup.single_click_third_bucket_update(active_year);
		boolean third_bucket_priced_updated = rsnpaup.add_12_month_price_in_one_week_row_third_bucket(active_year);

		
		Assert.assertEquals(succesfuuly_add_nightly_price, true);
		Assert.assertEquals(second_bucket_priced_updated, true);
		Assert.assertEquals(third_bucket_priced_updated, true);

		Log.endTestCase("Create Nightly rate group with single row and without holiday TC_1105");

	}

	
	
	
	
	
//	dependsOnMethods = "nightly_add_record_without_holiday_TC_1105"

	@Test(groups = { "regression",
			"smoke" }, priority = 6, description = "Verify all the added rates for all the different sizes")
	public void verify_allBucketsMonths_prices_TC_1106() throws InterruptedException {

		Log.startTestCase("Verify all the added rates for all the different sizes TC_1106");
		rsnpp = rsp.search_specifig_rates_group(rate_new_group_name);
		String activeYear_currentTime [] = rsnpp.active_year();
		Thread.sleep(1000);
		String active_year = activeYear_currentTime[0];

		Thread.sleep(1000);
	
		zero_to_50_ft_calendar_table_record_dec_2024 = rsnpp.nightly_2024_december_calendar_selection();
		Thread.sleep(1000);
		String[] expected_result_0to50ft = { null, null, null, null, "$5.00", "$6.00", "$7.00", "$1.00", "$2.00",
				"$3.00", "$4.00", "$5.00", "$6.00", "$7.00", "$1.00", "$2.00", "$3.00", "$4.00", "$5.00", "$6.00",
				"$7.00", "$1.00", "$2.00", "$3.00", "$4.00", "$5.00", "$6.00", "$7.00", "$1.00", "$2.00", "$3.00",
				"$4.00", "$5.00", "$6.00", "$7.00" };

		Thread.sleep(0);
		rsnpp.single_click_second_bucket();

		fifty_to_100_ft_calendar_table_record_dec_2023 = rsnpp.nightly_2024_december_calendar_selection();
		Thread.sleep(1000);
		String[] expected_result_50to100ft = { null, null, null, null, "$12.00", "$13.00", "$14.00", "$8.00", "$9.00",
				"$10.00", "$11.00", "$12.00", "$13.00", "$14.00", "$8.00", "$9.00", "$10.00", "$11.00", "$12.00",
				"$13.00", "$14.00", "$8.00", "$9.00", "$10.00", "$11.00", "$12.00", "$13.00", "$14.00", "$8.00",
				"$9.00", "$10.00", "$11.00", "$12.00", "$13.00", "$14.00" };

		rsnpp.single_click_third_bucket();

		hundread_100_ft_calendar_table_record_dec_2023 = rsnpp.nightly_2024_december_calendar_selection();
		Thread.sleep(1000);
		String[] expected_result_hundreadft = { null, null, null, null, "$19.00", "$20.00", "$21.00", "$15.00",
				"$16.00", "$17.00", "$18.00", "$19.00", "$20.00", "$21.00", "$15.00", "$16.00", "$17.00", "$18.00",
				"$19.00", "$20.00", "$21.00", "$15.00", "$16.00", "$17.00", "$18.00", "$19.00", "$20.00", "$21.00",
				"$15.00", "$16.00", "$17.00", "$18.00", "$19.00", "$20.00", "$21.00" };

	
		Assert.assertEquals(zero_to_50_ft_calendar_table_record_dec_2024, expected_result_0to50ft);
		Assert.assertEquals(fifty_to_100_ft_calendar_table_record_dec_2023, expected_result_50to100ft);
		Assert.assertEquals(hundread_100_ft_calendar_table_record_dec_2023, expected_result_hundreadft);

		Log.endTestCase("Verify all the added rates for all the different sizes TC_1106");
		

		
		
	}
	
	
	
	
	
//	
//	@Test(groups = { "regression",
//	"smoke" }, priority = 7, description = "Verify all the added rates for all the different sizes TC_1107")
public void Update_Nightly_Record_TC_1107() throws InterruptedException {
		
		
		Log.startTestCase("Update nightly rate group with multiple row and with holiday");
		
		rsnpp = rsp.search_specifig_rates_group(rate_new_group_name);
		String activeYear_currentTime[] = rsnpp.active_year();
		Thread.sleep(1000);
		String active_year = activeYear_currentTime[0];

		Thread.sleep(1000);
		zero_to_50_ft_calendar_table_record_dec_2024 = rsnpp.nightly_2024_december_calendar_selection();
		Thread.sleep(1000);
		rsnpp.single_click_second_bucket();
		fifty_to_100_ft_calendar_table_record_dec_2023 = rsnpp.nightly_2024_december_calendar_selection();
		Thread.sleep(1000);
		rsnpp.single_click_third_bucket();
		hundread_100_ft_calendar_table_record_dec_2023 = rsnpp.nightly_2024_december_calendar_selection();
		Thread.sleep(1000);
		rsnpp = rsp.search_specifig_rates_group(rate_new_group_name);
		Thread.sleep(1000);
	
	// bucket 1 =  81 , bucket = 82, bucket 3= 99

		rsnpaup = rsnpp.click_update_btn_return_edit_page();
		String [] price_list_first_row_month= {"0","4","5","6","7","8","9","10"};
		String [] price_list_2nd_row_month= {"0","104","105","106","107","108","109","110"};
		String holiday_price_set ="2";
		String holiday_minimum_reservation ="5";
		rsnpaup.add_mutli_months_price_1st_bucket(active_year, "81",price_list_first_row_month, price_list_2nd_row_month, holiday_price_set, holiday_minimum_reservation);
		
		
		
		
		
		
		
		
		
		
				
		rsnpaup = rsnpp.click_update_btn_return_edit_page();
		boolean succesfuuly_add_nightly_price = rsnpaup.add_12_month_price_in_one_week_row_first_bucket(active_year);

		Thread.sleep(1000);
	
		rsnpaup = rsnpp.click_update_btn_return_edit_page();
		rsnpaup.single_click_second_bucket_update(active_year);
		boolean second_bucket_priced_updated = rsnpaup.add_12_month_price_in_one_week_row_second_bucket(active_year);

		Thread.sleep(1000);

		rsnpaup = rsnpp.click_update_btn_return_edit_page();
		rsnpaup.single_click_third_bucket_update(active_year);
		boolean third_bucket_priced_updated = rsnpaup.add_12_month_price_in_one_week_row_third_bucket(active_year);
		
		
		
		
		Log.endTestCase("Update nightly rate group with multiple row and with holiday");
		
	
	}
	
		
	
	

	@AfterMethod
	public void afterTest() {

		driver.get(prop.getProperty("logout_url"));
		BrowserFactory.getInstance().removeDriver();

	}

}
