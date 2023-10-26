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
	String added_space;
	String formDetails[];
	SpaceTypesPage stp2;
	
//	String userFName= prop.getProperty("space_name");
	String parking;
	
	
	
	
	@BeforeMethod
	public void beforeTest() {

		browserIntialization();
		lp = new LoginPage(driver);
		hp = lp.login(prop.getProperty("email"), prop.getProperty("password"));
		stp = hp.spaces_dropdown_SpaceTypes();

	}

	
	
	
	
	
//	@Test(groups = "regression,sanity,smoke", priority = 1 )
//	public void verifySpacePage_Tc_201() {
//
//		Log.startTestCase("Verify  space type page Page");
//		Assert.assertEquals(stp.space_type_page_verify(), "Space Types");
//		Log.endTestCase("Verify  space type page");
//
//	}
//	
//
//	
//	@Test (groups ="regression,smoke", priority = 2)
//	public void verifyPopupAddSpace_TC_202() {
//		
//		
//		statp = stp.add_space_type();
//		Log.startTestCase(" add new space type window should appear");
//		Assert.assertEquals(statp.click_add_space_type(), "FIXED $ OR $/UNIT/PERIOD");
//		Log.endTestCase(" add new space type window should appear");
//	
//		
//	}
	
	
	
	
	
	
	
//	@Test(groups="regression,smoke", priority=3)
//	public void verifySpaceAfterAdd_TC_203() throws InterruptedException {
//		
//		statp = stp.add_space_type();
//		added_space = statp.addingNewSpace(prop.getProperty("space_name"));
//		
//		System.out.println("test");
//		
//		
//		
//	}
	
	

	public String[] formArray(String user, String parking, String capacity, String pricingType, String pricePlane, String rate_group, String occupancy) {
		
		
		formDetails = new String[] {user,parking,capacity,pricingType,pricePlane,rate_group,occupancy} ;
		return formDetails;
		
//		user="";
//		parking="";
//		capacity="";
//		pricingType="";
//		pricePlane="";
//		rate_group="";
//		occupancy="";
		
	
		
		
	}
	
	
	
	@Test(groups="regression,smoke", priority=3)
	public void verifySpaceAfterAdd_TC_203() throws InterruptedException {
		
		System.out.println("testing");
		
		formArray(
				prop.getProperty("space_name"),
				prop.getProperty("parking_fixed"),
				prop.getProperty("capacity_single_boat"),
				prop.getProperty("pricing_t1"),
				prop.getProperty("p_plane_nightly"),
				prop.getProperty("rate_group_New_rate_group"),
				prop.getProperty("Include_in_occupancy_no")
				);
		
		
//				String[] alpha = formDetails;
				statp = stp.add_space_type();
				added_space = statp.addingNewSpace(formDetails);
				stp.search_space_data(formDetails[0]);
				
				
				
				
				System.out.println("test");
		
		
	}
	
	
	
	
	
	
	
	
//	@Test(groups ="regression,smoke", priority=4)
//	public void skippingFileds_TC_204() {
//		
//		
//		
//	}
//	
//	@Test(groups="regression,smoke", priority=5)
//	public void checkNewSpaceShowing_TC_205() {
//		
//	}
//	
	
	
	
	
	
	
	
	

	@AfterMethod
	public void afterTest() {

		driver.get("https://staging.appedology.pk/marina/login");
		BrowserFactory.getInstance().removeDriver();
	}

}
