package com.marina.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.marina.base.BrowserFactory;
import com.marina.base.TestBase;
import com.marina.pages.HomePage;
import com.marina.pages.LoginPage;
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
		statp = hp.space_types_click_Mod7_ratesStorage();
		String rate_group_name="automation test user";
		statp.adding_rates_group(rate_group_name);
		rsp = hp.spaces_dropdown_rates_();
		rsp.rate_group_list();
		
/*
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * /
 */
		
		
		
		
		
		System.out.println("test");
		
	
		Log.endTestCase("Creating Rate Group from Space Type, check rate group should be available Tc_1101");

	}

//	public void verifySpacePage_Tc_201() {
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
