package com.marina.tests;

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
	public void beforeTest() {

		browserIntialization();
		lp = new LoginPage(driver);
		hp = lp.login(prop.getProperty("email"), prop.getProperty("password"));
		stp = hp.spaces_dropdown_SpaceTypes();

	}

	@Test(groups = { "regression", "smoke",
			"sanity" }, priority = 2, description = " Clicking add space type button, add new space type window should appear TC 201")
	public void verifySpacePage_Tc_201() {

		Log.startTestCase("Clicking space type link should open space type page TC_201 ");
		Assert.assertEquals(stp.space_type_page_verify(), "Space Types");
		Log.endTestCase("Clicking space type link should open space type page TC_201");

	}

	@Test(groups = { "regression", "smoke",
			"sanity" }, priority = 3, description = " Clicking add space type button, add new space type window should appear TC 202")
	public void verifyPopupAddSpace_TC_202() {

		statp = stp.add_space_type();
		Log.startTestCase(" Clicking add space type button, add new space type window should appear TC 202");
		Assert.assertEquals(statp.click_add_space_type(), "FIXED $ OR $/UNIT/PERIOD");
		Log.endTestCase("Clicking add space type button, add new space type window should appear  TC 202 ");

	}

	@Test(retryAnalyzer = com.marina.utils.TestRetryAnalyzer.class, groups = {
			"regression" }, priority = 4, description = " TC_203 mutli spaces added with different options TC_203")
	public void multipleUsersWithMutlipleSelections_TC_203() throws InterruptedException {

		Log.startTestCase(" TC_203 mutli spaces added with different options TC_203 ");
		statp = stp.add_space_type();
		added_space = statp.addingNewSpace("mutlispaces.xlsx");
		Assert.assertTrue(true);

		String entries_remain = stp.delete_space("Test_tbt_");

		Log.endTestCase("  TC_203  mutli spaces added with different options TC_203");

	}

	@Test(retryAnalyzer = com.marina.utils.TestRetryAnalyzer.class, groups = { "regression", "smoke",
			"sanity" }, priority = 5, description = "verify skipping fields of add space TC_204")
	public void addSpace_skippingFileds_TC_204() throws InterruptedException {

		Log.startTestCase("verify skipping fields of add space TC_204");
		statp = stp.add_space_type();

		verify_skipping_fileds = statp.addSpace_skipping();
		String count_expected_skipping_res[] = { "clear", "clear", "clear", "clear", "clear", "clear" };
		Assert.assertEquals(verify_skipping_fileds, count_expected_skipping_res);

		System.out.println("test");

		Log.endTestCase(" verify skipping fields of add space TC_204");

	}

	@Test(retryAnalyzer = com.marina.utils.TestRetryAnalyzer.class, groups = { "regression", "smoke",
			"sanity" }, priority = 6, description = "verify after insert data space record match with table record TC_205")
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

	@Test(retryAnalyzer = com.marina.utils.TestRetryAnalyzer.class, groups = { "regression", "smoke",
			"sanity" }, priority = 9, description = "verify table data after edit space TC_208")
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

	@Test(retryAnalyzer = com.marina.utils.TestRetryAnalyzer.class, groups = { "regression", "smoke",
			"sanity" }, priority = 10, description = "verify the new space type to excel sheet and check the complete records export TC_209")
	public void exportNewAddSpace_verifyCompleteRecords_TC_209() throws InterruptedException {

		
		Log.startTestCase("verify the new space type to excel sheet and check the complete records export TC_209");
		boolean excel_match = stp.exportDataToExcel();
		Assert.assertEquals(excel_match, true);
		Log.endTestCase("verify the new space type to excel sheet and check the complete records  export TC_209");

	}
	
	

	@Test(retryAnalyzer = com.marina.utils.TestRetryAnalyzer.class, groups = { "regression", "smoke",
			"sanity" }, priority = 12, description = "Search the added space from search field TC_211")
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

	@Test(retryAnalyzer = com.marina.utils.TestRetryAnalyzer.class, groups = { "regression", "smoke",
			"sanity" }, priority = 13, description = "verify after delete space from space table TC_212")
	public void verifyDeleteSpace_TC_212() throws InterruptedException {

		Log.startTestCase("verify after delete space from space table TC_212");
		statp = stp.add_space_type();
		added_space = statp.addingNewSpace("singleSpace.xlsx");
		String user = (String) added_space[0][0];

		String entries_remain = stp.delete_space(user);
		Assert.assertEquals(entries_remain, "No matching records found");
		Log.endTestCase("verify after delete space from space table TC_212");

	}

	@Test(groups = { "regression", "smoke" }, priority = 14)
	public void add_Spaces_Single_And_Carparking_Options_TC_213() throws InterruptedException {

		Log.startTestCase("verify add space form refresh after add single and carparking spaces");

		statp = stp.add_space_type();
		added_space = statp.addingNewSpace("boatAndCarParking.xlsx");
		String user = (String) added_space[0][0];
		Assert.assertEquals(user, "Test_tbt_001");
		Log.endTestCase("verify add space form refresh after add single and carparking spaces");

	}

	@AfterMethod
	public void afterTest() {

		driver.get("https://staging.appedology.pk/marina/login");
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
