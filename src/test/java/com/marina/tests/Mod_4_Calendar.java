package com.marina.tests;

import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.marina.actiondriver.Action;
import com.marina.base.BrowserFactory;
import com.marina.base.TestBase;
import com.marina.pages.AddNewSpaceItemPage;
import com.marina.pages.AllSpacesPage;
import com.marina.pages.CalendarPage;
import com.marina.pages.HomePage;
import com.marina.pages.LoginPage;
import com.marina.utils.Log;

public class Mod_4_Calendar extends TestBase {

	LoginPage lp;
	HomePage hp;
	AllSpacesPage allspace;
	AddNewSpaceItemPage add_new_space;
	CalendarPage cp;
	
	@BeforeMethod
	public void beforeTest() {
		
		browserIntialization();
		lp = new LoginPage(driver);
		hp = lp.login(prop.getProperty("email"), prop.getProperty("password"));
		cp = hp.calendarLink();

	}
	
	@Test(groups = {"regression,sanity,smoke"}, priority = 1, description = "Check Newly Added Space Should Be Available In Calendar")
	public void addedSpaceInCalendar_TC_601() throws InterruptedException {
		
		Log.startTestCase("Check Newly Added Space Should Be Available In Calendar");
		
		allspace = hp.spaces_dropdown_AllSpaces();
		add_new_space = allspace.clickAddSpaceBtn();
		allspace = add_new_space.createNewSpaceMandatoryFields("Automation Type", "SL-Test", "Yes", "10", "20", 
				"30", "5", "Yes", "Yes", "M-105", "DS-03","1");
 
 		String imagepath = System.getProperty("user.dir") + "\\src\\test\\resources\\testimages\\space_image.png";
		add_new_space = allspace.clickAddSpaceBtn();
		allspace = add_new_space.createSpaceWithAllFields("Automation Type", "SL-Test2", "No", "10", "20", 
				"30", "5", "Yes", "Yes", "M-106", "DS-03","1",imagepath,"This is test note");
		cp = hp.calendarLink();
		
		boolean flag1 = cp.verifySpaceOnCalendar("SL-Test");
		boolean flag2 = cp.verifySpaceOnCalendar("SL-Test2");
		if(flag1 == true && flag2 == true)
			Assert.assertTrue(flag1);
		else
			Assert.assertTrue(flag2);	
		Log.endTestCase("Check Newly Added Space Should Be Available In Calendar");
		
	}
	
	
	@Test(groups = {"regression,sanity,smoke"}, priority = 2, dependsOnMethods = "addedSpaceInCalendar_TC_601",
			description = "Verify Space Data When Hovering To Specific Space On Calendar Page")
	public void verifySpaceDataOnHover_TC_602() throws InterruptedException {
		
		Log.startTestCase("Verify Space Data When Hovering To Specific Space On Calendar Page");
		
		String[] actualData1 = cp.verifyDataHoverSpace("SL-Test");
		String[] expectedData1 = {"SL-Test","Automation Type","Available","10","20","30","yes","5","M-105"};
		boolean flag1 = Arrays.equals(actualData1, expectedData1);
		
		Action action = new Action();
		String currentDate = action.getCurrentDate(1, 0, 0, "MM-dd-yyyy");
		String afterDate = action.getCurrentDate(0,1,6,"MM-dd-yyyy");
		String unavailableDate = currentDate + " / "+afterDate;
		
		String[] actualData2 = cp.verifyDataHoverSpace("SL-Test2"); 
		String[] expectedData2 = {"SL-Test2","Automation Type",unavailableDate,"10","20","30","yes","5","M-106"};
		boolean flag2 = Arrays.equals(actualData2, expectedData2);

		if(flag1 == true && flag2 == true)
			Assert.assertTrue(true);
		else 
			Assert.assertTrue(false);
		
		Log.endTestCase("Verify Space Data When Hovering To Specific Space On Calendar Page");

	}
	
	
//	@Test(retryAnalyzer = com.marina.utils.TestRetryAnalyzer.class)
	@Test(groups = {"regression,sanity,smoke"}, priority = 3, dependsOnMethods = "addedSpaceInCalendar_TC_601",
			description = "Verify All the Slip Details On Slip Detail Section")
	public void verifyDetailsSlipDetailPage_TC_603() throws InterruptedException {
		
		Log.startTestCase("Verify All the Slip Details On Slip Detail Section");
		
		String[] actualData = cp.verifyDetailsOnSlipDetail("SL-Test","Mandatory");
		String[] expectedData = {"SL-Test","Yes","","",
				"Automation Type","1 ft","10","20","30",
				"DS-03","Yes","Yes","5 Amps","M-105","","1. M-105: (Reading: )"};
		
		boolean flag = Arrays.equals(actualData, expectedData);
		
		Action action = new Action();
		String currentDate = action.getCurrentDate(1, 0, 0, "MM-dd-yyyy");
		String afterDate = action.getCurrentDate(0,1,6,"MM-dd-yyyy");
		String unavailableDate = currentDate + " to "+afterDate;
		
		String[] actualData2 = cp.verifyDetailsOnSlipDetail("SL-Test2","All");
		String[] expectedData2 = {"SL-Test2","No","Due to some issue",unavailableDate,
				"Automation Type","1 ft","10","20","30",
				"DS-03","Yes","Yes","5 Amps","M-106","This is test note","1. M-106: (Reading: )"};
		
		boolean flag2 = Arrays.equals(actualData2, expectedData2);
		
		if(flag == true && flag2 == true)
			Assert.assertTrue(true);
		else
			Assert.assertTrue(false);
		
		Log.endTestCase("Verify All the Slip Details On Slip Detail Section");
		
	} 
	
	@Test(groups = {"regression,sanity,smoke"}, priority = 4, dependsOnMethods = "addedSpaceInCalendar_TC_601",
			description = "Verify ReservationsPage Should Not Be Set For Unavailable Dates")
	public void reservNotUnavailable_TC_604() throws InterruptedException {
		
		Log.startTestCase("Verify ReservationsPage Should Not Be Set For Unavailable Dates");
	
		Action action = new Action();
		String afterDate = action.getCurrentDate(0,1,6,"yyyy-MM-dd");		
		boolean flag = cp.reservNotSetForUnavailDates("SL-Test2", afterDate);
		
		//Deletion of Slips
		driver.navigate().refresh();
		allspace = hp.spaces_dropdown_AllSpaces();
		allspace.deleteSpace("SL-Test2");
		driver.navigate().refresh();
		allspace.deleteSpace("SL-Test");
		
		Assert.assertTrue(flag, "Unavailable Error Pop Not Appearing");
		Log.endTestCase("Verify ReservationsPage Should Not Be Set For Unavailable Dates");
	
		
	}

	
	
	
	@AfterMethod
	public void afterTest() {
		
		driver.get(prop.getProperty("logout_url"));
		BrowserFactory.getInstance().removeDriver();
	}
}
