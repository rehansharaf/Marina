package com.marina.tests;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.marina.base.BrowserFactory;
import com.marina.base.TestBase;
import com.marina.pages.EditSpacePage;
import com.marina.pages.HomePage;
import com.marina.pages.LoginPage;
import com.marina.pages.SpaceTypesAddTypesPage;
import com.marina.pages.SpaceTypesPage;
import com.marina.utils.ExcelLibrary;
import com.marina.utils.Log;
import com.marina.dataproviders.DataProviders;

/*
 * OOPS Major Concepts (major pillar)
 * 1. Inheritance 
 * 2. Abstraction 
 * 3. Polymorphism
 * 4. Encapsulation
 */
public class Mod_2_SpaceTypes extends TestBase {

	LoginPage lp;
	HomePage hp;
	SpaceTypesPage stp;
	SpaceTypesAddTypesPage statp;
	EditSpacePage editSP;
	Object[][] added_space;
	String[] added_space_s;
	String[] verify_skipping_fileds;

	String form_data_create[];
	String[] tableResult;

	String[] reset_formdata;
	String[] reset_tableresult;
	String[] edit_table_result;
	String parking;

	@BeforeMethod
	public void beforeTest() throws InterruptedException {

		browserIntialization();
		lp = new LoginPage(driver);
		hp = lp.login(prop.getProperty("email"), prop.getProperty("password"));
		stp = hp.spaces_dropdown_SpaceTypes();

	}
	
	
	@Test(groups = { "regression", "smoke",
			"sanity" }, priority = 2, description = "Clicking space type link should open space type page TC_201")
	public void verifySpacePage_Tc_201() {

		Log.startTestCase("Clicking space type link should open space type page TC_201");
		Assert.assertEquals(stp.space_type_page_verify(), "Space Types");
		Log.endTestCase("Clicking space type link should open space type page TC_201");

	}

	@Test(groups = { "regression", "smoke",
			"sanity" }, priority = 3, dependsOnMethods = "verifySpacePage_Tc_201",
			description = " Clicking add space type button, add new space type window should appear TC 202")
	public void verifyPopupAddSpace_TC_202() throws InterruptedException {

		statp = stp.add_space_type();
		Log.startTestCase(" Clicking add space type button, add new space type window should appear TC 202");
		Assert.assertEquals(statp.click_add_space_type(), "FIXED $ OR $/UNIT/PERIOD");
		Log.endTestCase("Clicking add space type button, add new space type window should appear  TC 202 ");

	}

	
	@Test(groups = {"regression" }, priority = 4, 
			dependsOnMethods = "verifyPopupAddSpace_TC_202", description = "Multi spaces added with different options TC_203")
	public void multipleUsersWithMutlipleSelections_TC_203() throws InterruptedException {

		Log.startTestCase("TC_203 multi spaces added with different options TC_203 ");
		statp = stp.add_space_type();
		added_space = statp.addingNewSpace("mutlispaces.xlsx");
		Assert.assertTrue(true);

		String entries_remain = stp.delete_space("Test_tbt_");

		Log.endTestCase("  TC_203  mutli spaces added with different options TC_203");

	}

	@Test(groups = { "regression", "smoke",
			"sanity" }, priority = 5, dependsOnMethods = "multipleUsersWithMutlipleSelections_TC_203",
			description = "Verify skipping fields of add space TC_204")
	public void addSpace_skippingFileds_TC_204() throws InterruptedException {

		Log.startTestCase("verify skipping fields of add space TC_204");
		statp = stp.add_space_type();

		verify_skipping_fileds = statp.addSpace_skipping();
		String count_expected_skipping_res[] = { "clear", "clear", "clear", "clear", "clear", "clear" };
		Assert.assertEquals(verify_skipping_fileds, count_expected_skipping_res);

		System.out.println("test");

		Log.endTestCase(" verify skipping fields of add space TC_204");

	}

	@Test(groups = { "regression", "smoke",
			"sanity" }, priority = 6, dependsOnMethods = "addSpace_skippingFileds_TC_204", 
			description = "Verify after insert data space record match with table record TC_205")
	public void verifySpaceData_fromTableData_TC_205() throws InterruptedException {

		Log.startTestCase("verify after insert data space record match with table record TC_205");
		statp = stp.add_space_type();
		added_space = statp.addingNewSpace("singleSpace.xlsx");
		String user = (String) added_space[0][0];
		tableResult = stp.get_space_data_from_table(user);
		formData_and_TableData_reset();

		String entries_remain = stp.delete_space(user);
		System.out.println("test");
		Assert.assertEquals(reset_formdata, reset_tableresult);

		Log.endTestCase("verify after insert data space record match with table record TC_205");

	}

	@Test( groups = { "regression", "smoke",
			"sanity" }, priority = 9, dependsOnMethods = "verifySpaceData_fromTableData_TC_205", 
			description = "Verify table data after edit space TC_208")
	public void verifySpaceData_afterEdit_TC_208() throws InterruptedException {

		Log.startTestCase("verify table data after edit space TC_208");
		statp = stp.add_space_type();
		added_space = statp.addingNewSpace("singleSpace.xlsx");
		String user = (String) added_space[0][0];
		tableResult = stp.get_space_data_from_table(user);
		String space_id = tableResult[0];
		editSP = stp.search_space_click_edit_btn(space_id);
		String user_name_change = "Test_tbt_spacechange";
		editSP.edit_space_data(user_name_change);
		tableResult = stp.get_space_data_from_table(space_id);
		String edit_result_data[] = { space_id, user_name_change, "Dry Storage", "Boat", "Multiple", "$/sq.ft/period",
				"Nightly, Monthly" };
		Assert.assertEquals(tableResult, edit_result_data);

		stp.delete_space("Test_tbt");
		Log.endTestCase("verify table data after edit space TC_208");

	}

	@Test( groups = { "regression", "smoke",
			"sanity" }, priority = 10, dependsOnMethods = "verifySpaceData_afterEdit_TC_208",  
			description = "Verify the new space type to excel sheet and check the complete records export TC_209")
	public void exportNewAddSpace_verifyCompleteRecords_TC_209() throws InterruptedException {

		
		Log.startTestCase("verify the new space type to excel sheet and check the complete records export TC_209");
		boolean excel_match = stp.exportDataToExcel();
		Assert.assertEquals(excel_match, true);
		Log.endTestCase("verify the new space type to excel sheet and check the complete records  export TC_209");

	}
	
	@Test(groups = { "regression", "smoke",
			"sanity" }, priority = 11,dependsOnMethods = "exportNewAddSpace_verifyCompleteRecords_TC_209", 
			description = "Export Space Types Into Google Sheet & Verify Records TC_210")
	public void exportSpacesGoogleSheet_TC_210() throws GeneralSecurityException, IOException, InterruptedException {
	
		Log.startTestCase("Export Space Types Into Google Sheet & Verify Records TC_210");
		boolean flag = stp.exportDataToGoogleSheet();
		Assert.assertTrue(flag);
		Log.endTestCase("Export Space Types Into Google Sheet & Verify Records TC_210");
		
	}

	@Test( groups = { "regression", "smoke",
			"sanity" }, priority = 12, dependsOnMethods = "verifySpaceData_afterEdit_TC_208",
			description = "Search the added space from search field TC_211")
	public void verify_serachField_withSpaceName_TC_211() throws InterruptedException {

		Log.startTestCase("Search the added space from search field TC_211");
		statp = stp.add_space_type();
		added_space = statp.addingNewSpace("singleSpace.xlsx");
		String user = (String) added_space[0][0];
		tableResult = stp.get_space_data_from_table(user);
		String space_Search_name = tableResult[1];
		Assert.assertEquals(user, space_Search_name);
		String entries_remain = stp.delete_space(user);
		Log.endTestCase("Search the added space from search field TC_211");

	}

	@Test( groups = { "regression", "smoke",
			"sanity" }, priority = 13, dependsOnMethods = "verify_serachField_withSpaceName_TC_211", 
			description = "Verify after delete space from space table TC_212")
	public void verifyDeleteSpace_TC_212() throws InterruptedException {

		Log.startTestCase("verify after delete space from space table TC_212");
		statp = stp.add_space_type();
		added_space = statp.addingNewSpace("singleSpace.xlsx");
		String user = (String) added_space[0][0];

		String entries_remain = stp.delete_space(user);
		Assert.assertEquals(entries_remain, "No matching records found");
		Log.endTestCase("verify after delete space from space table TC_212");

	}

	@Test(groups = { "regression", "smoke" }, priority = 14, dependsOnMethods = "verifyDeleteSpace_TC_212", 
			description = "Verify add space form refresh after add single and carparking spaces")
	public void add_Spaces_Single_And_Carparking_Options_TC_213() throws InterruptedException {

		Log.startTestCase("Verify add space form refresh after add single and carparking spaces");

		statp = stp.add_space_type();
		added_space = statp.addingNewSpace("boatAndCarParking.xlsx");
		String user = (String) added_space[0][0];
		String user1 = (String) added_space[1][0];
		String user2 = (String) added_space[2][0];
		
		driver.navigate().refresh();
		if(user != null)
			stp.delete_space("Test_tbt_001");
		if(user1 != null)
			stp.delete_space("Test_tbt_002");
		if(user2 != null)
			stp.delete_space("Test_tbt_003");
		
		if(user != null && user1 != null && user2 != null)
			Assert.assertTrue(true);
		else
			Assert.assertTrue(false,"Space Types are not getting created when adding single and then carparking spaces");
		
		
		Log.endTestCase("verify add space form refresh after add single and carparking spaces");

	}
	
	
	
	@Test(groups = { "regression",
			"smoke" }, priority = 15, description = "after selection of unit/period, two values should show, "
					+ "1. ft/period and 2. sq.ft/period and 3 . value should be hide or disable TC_214 ")
	public void SelectionUnitPeriod_and_verify_two_option_TC_214() throws InterruptedException {

		Log.startTestCase("after selection of unit/period, two values should show, 1. ft/period and "
				+ "2. sq.ft/period and 3 . value should be hide or disable TC_214 ");

		statp = stp.add_space_type();

		String[] pricing_type_options = statp.select_unit_period_verify_pricing_type();
		String[] options_compare = { "$/ft/period", "$/sq.ft/period", "no text" };

		Assert.assertEquals(pricing_type_options, options_compare);

		Log.endTestCase("after selection of unit/period, two values should show, 1. ft/period and 2. sq.ft/period "
				+ "and 3 . value should be hide or disable TC_214 ");

	}
	
	


	@AfterMethod
	public void afterTest() {

		driver.get(prop.getProperty("logout_url"));
		BrowserFactory.getInstance().removeDriver();
	}

	public void formData_and_TableData_reset() {

		reset_tableresult = new String[6];
		reset_formdata = new String[6];

		reset_tableresult[0] = tableResult[1];
		reset_tableresult[1] = tableResult[2];
		reset_tableresult[2] = tableResult[3];
		reset_tableresult[3] = tableResult[4];
		reset_tableresult[4] = tableResult[5];
		reset_tableresult[5] = tableResult[6];

		String space_name = (String) added_space[0][0];
		String add_to_group = (String) added_space[0][5];
		String fixed = (String) added_space[0][1];
		String capacity = (String) added_space[0][2];
		String pricing_type = (String) added_space[0][3];
		String p_plane = (String) added_space[0][4];

		reset_formdata[0] = space_name;
		reset_formdata[1] = add_to_group;
		reset_formdata[2] = fixed;
		reset_formdata[3] = capacity;
		reset_formdata[4] = pricing_type;
		reset_formdata[5] = p_plane;

	}

}
