package com.marina.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.marina.base.BrowserFactory;
import com.marina.base.TestBase;
import com.marina.pages.AddNewSpaceItemPage;
import com.marina.pages.AllSpacesPage;
import com.marina.pages.CalendarPage;
import com.marina.pages.Calendar_AddReservation;
import com.marina.pages.HomePage;
import com.marina.pages.LoginPage;
import com.marina.pages.RatesStorageNightlyPricePage;
import com.marina.pages.RatesStorageNightlyPrice_add_updatePage;
import com.marina.pages.RatesStoragePage;
import com.marina.pages.ReservationsPage;
import com.marina.pages.SpaceTypesAddTypesPage;
import com.marina.pages.SpaceTypesPage;
import com.marina.utils.Database;
import com.marina.utils.Log;

public class Mod_8_Reservation extends TestBase {
	
	LoginPage lp;
	HomePage hp;
	SpaceTypesPage spaceType;
	SpaceTypesAddTypesPage addSpaceType;
	CalendarPage cp;
	AllSpacesPage allSpaces;
	AddNewSpaceItemPage addSpace;
	ReservationsPage rp;
	Calendar_AddReservation cAddRev;
	RatesStoragePage rsp;
	RatesStorageNightlyPricePage rateNightPriceP;
	RatesStorageNightlyPrice_add_updatePage rateUpdateNight;
	String holidayDates = "2023-12-8 2023-12-6", holidayRates = "100";
	
	/*
	@BeforeClass
	public void beforeClass() throws InterruptedException {
		
		Database db = new Database();
		//Delete reservation and rate group from db
		db.deleteReservation("SL-Resv");
		db.deleteRateGroup("testresv_group");
		
		browserIntialization();
		lp = new LoginPage(driver);
		hp = lp.login(prop.getProperty("email"), prop.getProperty("password"));
		
		//Delete slips from front-end
		allSpaces = hp.spaces_dropdown_AllSpaces();
		allSpaces.verifySlipAndDelete("SL-Resv");
		
		
		// Check space exist, if yes delete it and add new space type with rate group
		spaceType = hp.spaces_dropdown_SpaceTypes();
		spaceType.searchAndDeleteSpaceType("SpaceReservation");
		addSpaceType = spaceType.add_space_type();
		addSpaceType.addSingleSpaceMandatoryFields("SpaceReservation", "Multiple Boats", "$/ft/period", "check_all", "testresv_group", "Yes");
		

		//Create new slip
		allSpaces = hp.spaces_dropdown_AllSpaces();
		addSpace = allSpaces.clickAddSpaceBtn();
		allSpaces = addSpace.createNewSpaceMandatoryFields("SpaceReservation", "SL-Resv", "Yes", "140", "30", 
				"40", "5", "Yes", "Yes", "M-100", "DS-03","1","10");
		
		
		
	//Create Rates in RateGroup
		//rsp = hp.spaces_dropdown_rates();
		//rateNightPriceP = rsp.search_specifig_rates_group("testresv_group");
		//rateUpdateNight = rateNightPriceP.click_update_btn_return_edit_page();
		//rateUpdateNight.single_click_first_bucket();
		//rateUpdateNight.add_12_month_price_in_one_week_row();
		
		//rateUpdateNight = rateNightPriceP.click_update_btn_return_edit_page();
		//rateUpdateNight.single_click_second_bucket();
		//rateUpdateNight.add_12_month_price_in_one_week_row_second_bucket();
		
		//rateUpdateNight = rateNightPriceP.click_update_btn_return_edit_page();
		//rateUpdateNight.single_click_third_bucket();
		//rateUpdateNight.add_12_month_price_in_one_week_row_third_bucket();	
		
		
		
	}*/
	

	@BeforeMethod
	public void setUp() {
		
		browserIntialization();
		lp = new LoginPage(driver);
		hp = lp.login(prop.getProperty("email"), prop.getProperty("password"));
		cp = hp.calendarLink();
		
	}
	
	
	
	@Test(groups = "regression,sanity,smoke", priority = 1,
			description = "Add Reservation For Single Day, Verify Reservation Created With Verification Of Data")
	public void addReservationSingleDay_ExistingUser_TC_1201() throws Exception {
	
		Log.startTestCase("Add Reservation For Single Day, Verify Reservation Created With Verification Of Data");
		
		Database db = new Database();
		db.deleteReservation("SL-Resv");
		
		cAddRev = cp.selectDates("SL-Resv", 1, 1, 0);
		String output = cAddRev.addReservation("SpaceReservation","testresv_group", "Test User", "SL-Resv", "Boat2", 1, "nightly", 0, 0);
		String[] parts = output.split(";");
		boolean flag = Boolean.parseBoolean(parts[0]);
		String totalAmt = parts[1];
		
		rp = hp.reservationLink();
		String totalAmount = rp.verifyReservationReceipt("SL-Resv");
		
		if(totalAmount.equals(totalAmt) && flag == true)
			Assert.assertTrue(true);
		else 
			Assert.assertTrue(false);
		
		
		Log.endTestCase("Add Reservation For Single Day, Verify Reservation Created With Verification Of Data");
	}
	
	
	
	@Test(groups = "regression,sanity,smoke", priority = 2,
			description = "Add Reservation For Multiple Days, Verify Reservation Created With Verification Of Data")
	public void addReservationMultipleDay_ExistingUser_TC_1202() throws Exception {
		
		Log.startTestCase("Add Reservation For Multiple Days, Verify Reservation Created With Verification Of Data");

		Database db = new Database();
		db.deleteReservation("SL-Resv");
		
		driver.navigate().refresh();
		
		cAddRev = cp.selectDates("SL-Resv", 4, 1, 0);
		String output = cAddRev.addReservation("SpaceReservation","testresv_group", "Test User", "SL-Resv", "Boat2", 4, "nightly", 0, 0);
		String[] parts = output.split(";");
		boolean flag = Boolean.parseBoolean(parts[0]);
		String totalAmt = parts[1];
		
		rp = hp.reservationLink();
		String totalAmount = rp.verifyReservationReceipt("SL-Resv");
		
		if(totalAmount.equals(totalAmt) && flag == true)
			Assert.assertTrue(true);
		else 
			Assert.assertTrue(false);
		
		db.deleteReservation("SL-Resv");
		
		Log.endTestCase("Add Reservation For Multiple Days, Verify Reservation Created With Verification Of Data");

	}

	
	@Test(groups = "regression,sanity,smoke", priority = 3,
			description = "Change the days to more than 1 month, check if pricing category move from nightly to monthly,"
					+ " and calculate all the pricing")
	public void categoryChangeNightlyToMonthly_TC_1203() throws Exception {
		
		Log.startTestCase("Change the days to more than 1 month, check if pricing category move from nightly to monthly and calculate the pricing");
		
		Database db = new Database();
		db.deleteReservation("SL-Resv");
		
		driver.navigate().refresh();
		
		cAddRev = cp.selectDates("SL-Resv", 1, 1, 0);
		boolean flag = cAddRev.verifyChangePricingNightlyToMonthly(33);
		if(flag)
			Assert.assertTrue(flag,"Pricing is getting changed automatically from nightly to monthly");
		else
			Assert.assertTrue(flag,"Pricing not getting changed from nightly to monthly automatically");
		
		Log.endTestCase("Change the days to more than 1 month, check if pricing category move from nightly to monthly and calculate the pricing");

	}
	
	
	@Test(groups = "regression,sanity,smoke", priority = 4,
			description = "Add New Reservation For Nightly Pricing With Special Holiday Rates Check Charges Calculation")
	public void addReservationNightly_SpecialRate_TC_1204() throws Exception {
		
		Log.startTestCase("Add New Reservation For Nightly Pricing With Special Holiday Rates Check Charges Calculation");
		
		//String[] parts = holidayRates.split(" ");
		//String holidayStart = parts[0];
		
		Database db = new Database();
		db.deleteReservation("SL-Resv");
		driver.navigate().refresh();
		cAddRev = cp.selectDates("SL-Resv", 5, 0, 1);
		
		String output = cAddRev.addReservation("SpaceReservation", "testresv_group", "Test User", "SL-Resv", "Boat2", 5, "nightly", 0, 0);
		String[] parts1 = output.split(";");
		boolean flag = Boolean.parseBoolean(parts1[0]);
		String totalAmt = parts1[1];
		
		rp = hp.reservationLink();
		String totalAmount = rp.verifyReservationReceipt("SL-Resv");
		totalAmount = totalAmount.replaceAll(",", "");
		
		if(totalAmount.equals(totalAmt) && flag == true)
			Assert.assertTrue(true);
		else 
			Assert.assertTrue(false);
		
		db.deleteReservation("SL-Resv");
		
		Log.endTestCase("Add New Reservation For Nightly Pricing With Special Holiday Rates Check Charges Calculation");
		
	}
	
	@Test(groups = "regression,sanity,smoke", priority = 5,
			description = "Add New Reservation For Nightly Pricing With Different Boat Sizes & Check Charges Calculation")
	public void addReservationDifferentBoatSize_TC_1205() throws Exception {
		

		Log.startTestCase("Add New Reservation For Nightly Pricing With Different Boat Sizes & Check Charges Calculation");

	// For Boat Size 50ft
		Database db = new Database();
		db.deleteReservation("SL-Resv");
		driver.navigate().refresh();
		cAddRev = cp.selectDates("SL-Resv", 5, 0, 1);
		String output = cAddRev.addReservation("SpaceReservation","testresv_group", "Test User", "SL-Resv", "Boat3", 5, "nightly", 0, 0);
		String[] parts = output.split(";");
		boolean flag = Boolean.parseBoolean(parts[0]);
		String totalAmt = parts[1];
		
		rp = hp.reservationLink();
		String totalAmount = rp.verifyReservationReceipt("SL-Resv");
		totalAmount = totalAmount.replaceAll(",", "");
		
		
	// For Boat Size 100+ft
		driver.navigate().refresh();
		db.deleteReservation("SL-Resv");
		cp = hp.calendarLink();
		
		cAddRev = cp.selectDates("SL-Resv", 5, 0, 1);
		String output1 = cAddRev.addReservation("SpaceReservation","testresv_group", "Test User", "SL-Resv", "Boat4", 5, "nightly", 0, 0);
		String[] parts1 = output1.split(";");
		boolean flag1 = Boolean.parseBoolean(parts1[0]);
		String totalAmt1 = parts1[1];
		
		rp = hp.reservationLink();
		String totalAmount1 = rp.verifyReservationReceipt("SL-Resv");
		totalAmount1 = totalAmount1.replaceAll(",", "");
		
		if(totalAmount.equals(totalAmt) && flag == true && totalAmount1.equals(totalAmt1) && flag1 == true)
			Assert.assertTrue(true);
		else 
			Assert.assertTrue(false);
		
		db.deleteReservation("SL-Resv");
		
		Log.endTestCase("Add New Reservation For Nightly Pricing With Different Boat Sizes & Check Charges Calculation");
		
		
		
	}

	
	
	@Test(groups = "regression,sanity,smoke", priority = 6,
			description = "Add Reservation For Monthly & Verify All the Calculations")
	public void addReservationMonthly_TC_1206() throws Exception {
		
		Log.startTestCase("Add Reservation For Monthly & Verify All the Calculations");
		
		Database db = new Database();
		db.deleteReservation("SL-Resv");
		driver.navigate().refresh();
		
		cAddRev = cp.selectDates("SL-Resv", 1, 1, 0);
		String output = cAddRev.addReservation_monthly("SpaceReservation","testresv_group", "Test User", "SL-Resv", "Boat2", 60, "monthly", 0);
		String[] parts = output.split(";");
		boolean flag = Boolean.parseBoolean(parts[0]);
		String totalAmt = parts[1];
		
		rp = hp.reservationLink();
		String totalAmount = rp.verifyReservationReceipt("SL-Resv");
		
		if(totalAmount.equals(totalAmt) && flag == true)
			Assert.assertTrue(true);
		else 
			Assert.assertTrue(false);
		
		db.deleteReservation("SL-Resv");
		
		Log.endTestCase("Add Reservation For Monthly & Verify All the Calculations");
	}
	
	
	@Test(groups = "regression,sanity,smoke", priority = 7,
			description = "Update Pricing From Monthly To Nightly")
	public void updatePricingFromMonthlyToNightly_1207() throws Exception {

		Log.startTestCase("Update Pricing From Monthly To Nightly");
		
		Database db = new Database();
		db.deleteReservation("SL-Resv");
		driver.navigate().refresh();
		
		cAddRev = cp.selectDates("SL-Resv", 1, 1, 0);
		boolean output = cAddRev.verifyChangePriceMonthlyToNightly("SpaceReservation","testresv_group", "Test User", "SL-Resv", "Boat2", 60, "monthly", 0);
		if(output)
			Assert.assertTrue(true, "Pricing Is Validated When Changed From Monthly To Nightly");
		else 
			Assert.assertTrue(false, "Pricing Is Not Correct When Changed From Monthly To Nightly");
				
		
		Log.endTestCase("Update Pricing From Monthly To Nightly");
		
	}
	
	@Test(groups = "regression,sanity,smoke", priority = 8,
			description = "Add Reservation For An Year & Verify The Charges")
	public void addReservationAnnual_TC_1208() throws Exception {
		
		Log.startTestCase("Add Reservation For An Year & Verify The Charges");
		
		Database db = new Database();
		db.deleteReservation("SL-Resv");
		driver.navigate().refresh();
		cAddRev = cp.selectDates("SL-Resv", 1, 1, 0);
		
		String output = cAddRev.addReservationAnnual("SpaceReservation","testresv_group", "Test User", "SL-Resv", "Boat2", 370, "annual", 0);
		String[] parts = output.split(";");
		boolean flag = Boolean.parseBoolean(parts[0]);
		String totalAmt = parts[1];
		
		rp = hp.reservationLink();
		String totalAmount = rp.verifyReservationReceipt("SL-Resv");
		
		if(totalAmount.equals(totalAmt) && flag == true)
			Assert.assertTrue(true);
		else 
			Assert.assertTrue(false);
		
		db.deleteReservation("SL-Resv");
		
		
		Log.endTestCase("Add Reservation For An Year & Verify The Charges");
	}
	
	
	
	
	
	@AfterMethod
	public void tearDown() {
		
		driver.get(prop.getProperty("logout_url"));
		BrowserFactory.getInstance().removeDriver();
		
	}
}
