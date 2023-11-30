package com.marina.tests;

import java.text.ParseException;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.marina.base.TestBase;
import com.marina.pages.AddNewSpaceItemPage;
import com.marina.pages.AllSpacesPage;
import com.marina.pages.CalendarPage;
import com.marina.pages.HomePage;
import com.marina.pages.LoginPage;
import com.marina.pages.ReservationsPage;
import com.marina.pages.SpaceTypesAddTypesPage;
import com.marina.pages.SpaceTypesPage;
import com.marina.utils.Database;
import com.marina.utils.Log;

public class Mod_8_Reservation extends TestBase {
	
	LoginPage lp;
	HomePage hp;
	CalendarPage cp;
	AllSpacesPage allSpaces;
	AddNewSpaceItemPage addSpace;
	ReservationsPage rp;
	
	@BeforeMethod
	public void setUp() {
		
		browserIntialization();
		lp = new LoginPage(driver);
		hp = lp.login(prop.getProperty("email"), prop.getProperty("password"));
		cp = hp.calendarLink();
		
	}
	
	/*
	@Test(groups = "regression,sanity,smoke", priority = 1,
			description = "Add Reservation For Single Day, Verify Reservation Created With Verification Of Data")
	public void addReservationSingleDay_ExistingUser_TC_1201() throws InterruptedException, ParseException {
	
		Log.startTestCase("Add Reservation For Single Day, Verify Reservation Created With Verification Of Data");
		
		Database db = new Database();
		db.deleteReservation("SL-Resv");
		
//		allSpaces = hp.spaces_dropdown_AllSpaces();
//		addSpace = allSpaces.clickAddSpaceBtn();
//		allSpaces = addSpace.createNewSpaceMandatoryFields("Automation Type", "SL-Resv", "Yes", "20", "30", 
//				"40", "5", "Yes", "Yes", "M-100", "DS-03","1","10");
//		cp = hp.calendarLink();
		cp.selectDates("SL-Resv", 1, 1);
		String output = cp.addReservation("Automation Type","Wet Storage", "Test User", "SL-Resv", "Boat2", 1, "nightly");
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
		
		Log.endTestCase("Add Reservation For Single Day, Verify Reservation Created With Verification Of Data");
	}
	
	
	
	@Test(groups = "regression,sanity,smoke", priority = 2,
			description = "Add Reservation For Multiple Days, Verify Reservation Created With Verification Of Data")
	public void addReservationMultipleDay_ExistingUser_TC_1202() throws InterruptedException, ParseException {
		
		Log.startTestCase("Add Reservation For Multiple Days, Verify Reservation Created With Verification Of Data");

		Database db = new Database();
		db.deleteReservation("SL-Resv");
		
		driver.navigate().refresh();
		
		cp.selectDates("SL-Resv", 4, 1);
		String output = cp.addReservation("Automation Type","Wet Storage", "Test User", "SL-Resv", "Boat2", 4, "nightly");
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
		
		cp.selectDates("SL-Resv", 1, 1);
		boolean flag = cp.verifyChangePricingNightlyToMonthly(33);
		if(flag)
			Assert.assertTrue(flag,"Pricing is getting changed automatically from nightly to monthly");
		else
			Assert.assertTrue(flag,"Pricing not getting changed from nightly to monthly automatically");
		
		Log.endTestCase("Change the days to more than 1 month, check if pricing category move from nightly to monthly and calculate the pricing");

	}*/
	

}
