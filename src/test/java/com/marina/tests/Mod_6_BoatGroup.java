package com.marina.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.marina.base.TestBase;
import com.marina.pages.BoatGroupsPage;
import com.marina.pages.HomePage;
import com.marina.pages.LoginPage;
import com.marina.utils.Log;

public class Mod_6_BoatGroup extends TestBase {

	LoginPage lp;
	HomePage hp;
	BoatGroupsPage bg;
	
	@BeforeMethod
	public void beforeMethod() throws InterruptedException {
		
		browserIntialization();
		browserIntialization();
		lp = new LoginPage(driver);
		hp = lp.login(prop.getProperty("email"), prop.getProperty("password"));
		bg = hp.spaces_dropdown_BoatGroups();
		
	}
	
	
	@Test
	public void verifyBoatGroupPageOpens_TC_1001() {
		
		Log.startTestCase("Check Boat Group Page Gets Open");
		String pageHeading = bg.verifyBoatGroupPageHeading();
		Assert.assertEquals(pageHeading, "Boat Groups");
		Log.endTestCase("Check Boat Group Page Gets Open");
	}
}
