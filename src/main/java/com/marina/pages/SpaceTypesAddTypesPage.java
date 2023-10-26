package com.marina.pages;


import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.marina.actiondriver.Action;
import com.marina.base.TestBase;

public class SpaceTypesAddTypesPage {

	

	WebDriver driver;
	Action action = new Action();
	
	@FindBy(how = How.XPATH, using = "//label[text()='FIXED $ OR $/UNIT/PERIOD']")
	WebElement verify_popup_text;
	
	
	@FindBy(how = How.XPATH, using = "//input[@name='name']")
	WebElement nameText;
	
	
	@FindBy(how = How.XPATH, using = "//label[@for='parkingb']")
	WebElement fixedCheck;
	
	@FindBy(how = How.XPATH, using = "//label[@for='parkingc']")
	WebElement unit_period_check;
	
	@FindBy(how = How.XPATH, using = "//select[@name='boats']")
	WebElement capacity_click;
	
	@FindBy(how = How.XPATH, using = "//option[text()='Single Boat']")
	WebElement single_boat;
	
	@FindBy(how = How.XPATH, using = "//option[text()='Multiple Boats']")
	WebElement multipleBoats;
	
	
	@FindBy(how = How.XPATH, using = "//select[@name='pricing']")
	WebElement pricing_type_click;
	
	
	@FindBy(how = How.XPATH, using = "//option[@value='per-unit']")
	WebElement pt_per_unit;
	
	
	@FindBy(how = How.XPATH, using = "//option[@value='per-sq-unit']")
	WebElement pt_per_sq_unit;
	
	@FindBy(how = How.XPATH, using = "//option[@value='per-night']")
	WebElement pt_per_night;

	@FindBy(how = How.XPATH, using = "//input[@value='nightly']")
	WebElement check_night;
	
	@FindBy(how = How.XPATH, using = "//input[@value='monthly']")
	WebElement check_monthly;
	
	@FindBy(how = How.XPATH, using = "//input[@value='Yearly']")
	WebElement check_Yearly;
	
	@FindBy(how = How.XPATH, using = "//select[@name='price_group']")
	WebElement add_to_rate_group;
	
	
	@FindBy(how = How.XPATH, using = "//option[@value='new']")
	WebElement group_create;
	
	@FindBy(how = How.XPATH, using = "//option[@value='1']")
	WebElement group_Wet_Storage;
	
	@FindBy(how = How.XPATH, using = "//option[@value='2']")
	WebElement group_Dry_Storage;
	
	@FindBy(how = How.XPATH, using = "//input[@value='no']")
	WebElement include_in_occ_yes;
	
	@FindBy(how = How.XPATH, using = "//input[@value='yes']")
	WebElement include_in_occ_no;
	
	
	@FindBy(how = How.XPATH, using = "//*[@id='modalAddSpaceType']/div/form/div/div[3]/button[1]")
	WebElement btn_close;
	
	@FindBy(how = How.XPATH, using = "//*[@id='modalAddSpaceType']/div/form/div/div[3]/button[2]")
	WebElement btn_save;
	
	
	
	@FindBy(how = How.XPATH, using = "//*[@id='modalAddSpaceType']/div/form/div/div[1]/button")
	WebElement btn_cross;
	
	
	
	
	
	
	public SpaceTypesAddTypesPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	
	public String add_space_type() {
		
//		btn_addspacetype.click();
		action.explicitWait(driver, verify_popup_text, Duration.ofSeconds(10));
		return verify_popup_text.getText();
		
	}
	
	
	
	
	
}
