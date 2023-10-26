package com.marina.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.marina.base.BrowserFactory;
import com.marina.base.TestBase;
import com.marina.pages.HomePage;
import com.marina.pages.LoginPage;
import com.marina.pages.SpaceTypesAddTypesPage;
import com.marina.pages.SpaceTypesPage;
import com.marina.utils.Log;

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

	@BeforeMethod
	public void beforeTest() {

		browserIntialization();
		lp = new LoginPage(driver);
		hp = lp.login(prop.getProperty("email"), prop.getProperty("password"));
		stp = hp.spaces_dropdown_SpaceTypes();

	}

	
	
	@Test(groups = "regression,sanity,smoke", priority = 1 )
	public void verifySpacePage_Tc_201() {

		Log.startTestCase("Verify  space type page Page");
		Assert.assertEquals(stp.space_type_page_verify(), "Space Types");
		Log.endTestCase("Verify  space type page");

	}
	

	
	@Test (groups ="regression", priority = 2)
	public void verifyPopupAddSpace_TC_202() {
		
		
		statp = stp.add_space_type();
		Log.startTestCase(" add new space type window should appear");
		Assert.assertEquals(statp.add_space_type(), "FIXED $ OR $/UNIT/PERIOD");
		Log.endTestCase(" add new space type window should appear");
		
		
		
	}
	
	
	
	
	

	@AfterMethod
	public void afterTest() {

		driver.get("https://staging.appedology.pk/marina/login");
		BrowserFactory.getInstance().removeDriver();
	}

}
