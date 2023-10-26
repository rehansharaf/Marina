package com.marina.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.marina.base.TestBase;
import com.marina.pages.HomePage;
import com.marina.pages.LoginPage;

public class Mod_3_AllSpaces extends TestBase {
	
	LoginPage lp;
	HomePage hp;
	String pageType = "AllSpaces";

	
	
	@BeforeMethod
	public void beforeTest() {
		
		browserIntialization();

		
	}
	
	
	@Test
	public void verifyAllSpacePage_TC_401() {
		
		
	}
	
	
	@AfterMethod
	public void afterTest() {
		
		
	}

}
