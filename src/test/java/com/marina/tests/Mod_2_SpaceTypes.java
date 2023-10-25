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
	int a = 2;

	@BeforeMethod
	public void beforeTest() {

		browserIntialization();
		lp = new LoginPage(driver);
		hp = lp.login(prop.getProperty("email"), prop.getProperty("password"));
		stp = hp.spaces_dropdown(a);

	}

	@Test(groups = "regression,sanity,smoke")
	public void ST1_001_Space_Type() {

		Log.startTestCase("Verify  space type page Page");
		Assert.assertEquals(stp.space_type_page_verify(), "Space Types");
		Log.endTestCase("Verify  space type page");

	}
	
	@Test 
	public void ST1_002_add_space() {
		
		Log.startTestCase(" add new space type window should appear");
		Assert.assertEquals(stp.add_space_type(), "Add New Space Type");
		Log.endTestCase(" add new space type window should appear");
		
		
		
	}
	
	
	
	
	

	@AfterMethod
	public void afterTest() {

		driver.get("https://staging.appedology.pk/marina/login");
		BrowserFactory.getInstance().removeDriver();
	}

}
