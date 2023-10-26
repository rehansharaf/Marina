package com.marina.pages;


import java.lang.reflect.Array;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v115.domsnapshot.model.ArrayOfStrings;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.marina.actiondriver.Action;
import com.marina.base.TestBase;

public class SpaceTypesAddTypesPage {

	

	WebDriver driver;
	Action action = new Action();
	String popup_space_saved;
	String rateGroup;
	String [] Space_detail;
	
	
	
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
	WebElement pt_ft_period;
	
	
	@FindBy(how = How.XPATH, using = "//option[@value='per-sq-unit']")
	WebElement pt_sq_ft_period;
	
	@FindBy(how = How.XPATH, using = "//option[@value='per-night']")
	WebElement pt_period;

	@FindBy(how = How.XPATH, using = "//input[@value='nightly']")
	WebElement check_night;
	
	@FindBy(how = How.XPATH, using = "//input[@value='monthly']")
	WebElement check_monthly;
	
	@FindBy(how = How.XPATH, using = "//input[@value='Yearly']")
	WebElement check_Yearly;
	
	@FindBy(how = How.XPATH, using = "//select[@name='price_group']")
	WebElement add_to_rate_group_selection;
	
	
	@FindBy(how = How.XPATH, using = "//option[@value='new']")
	WebElement create_new_group;
	
	@FindBy(how = How.XPATH, using = "//option[@value='1']")
	WebElement wet_Storage_group;
	
	@FindBy(how = How.XPATH, using = "//option[@value='2']")
	WebElement dry_Storage_group;
	
	@FindBy(how = How.XPATH, using = "//input[@value='no']")
	WebElement include_in_OCCUPANCY_yes;
	
	@FindBy(how = How.XPATH, using = "//input[@value='yes']")
	WebElement include_in_OCCUPANCY_no;
	
	
	@FindBy(how = How.XPATH, using = "//*[@id='modalAddSpaceType']/div/form/div/div[3]/button[1]")
	WebElement btn_close;
	
	@FindBy(how = How.XPATH, using = "//*[@id='modalAddSpaceType']/div/form/div/div[3]/button[2]")
	WebElement btn_save;
	
	
	
	@FindBy(how = How.XPATH, using = "//*[@id='modalAddSpaceType']/div/form/div/div[1]/button")
	WebElement btn_cross;
	
	
	@FindBy(how = How.XPATH, using = "//div[text()='Space type saved successfully']")
	WebElement popup_group_successfully_add;
	
	@FindBy(how = How.XPATH, using = "//button[text()='OK']")
	WebElement btn_ok_group_added;
	
	
	
	
	
	public SpaceTypesAddTypesPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	
	public String click_add_space_type() {
		
		
		action.explicitWait(driver, verify_popup_text, Duration.ofSeconds(10));
		return verify_popup_text.getText();
		
	}
	
	
	
	public String addingNewSpace(String[] formDetails) throws InterruptedException {
		
		
		String alpha =formDetails[0];
		String bravo =formDetails[0];
		
		
		action.explicitWait(driver, nameText, Duration.ofSeconds(10));
		nameText.sendKeys(formDetails[0]);
		Thread.sleep(1000);
		
			
//		Thread.sleep(1000);
		String parking =formDetails[1];
		
		if(parking.equals("FIXED")) {
		
		fixedCheck.click();
	} 
		else if(parking.equals("UNIT PERIOD")) {
		unit_period_check.click();
	}

	Thread.sleep(1000);
	capacity_click.click();
	Thread.sleep(1000);

	if (formDetails[2].equals("SINGLE BOAT")) {
		single_boat.click();
	}

	else  if (formDetails[2].equals("MULTIPLE BOAT")){
		multipleBoats.click();
	}

	Thread.sleep(1000);
	pricing_type_click.click();
	
	
	
	if(formDetails[3].equals("$/ft/period")) {
		pt_ft_period.click();
	}
	
	else if (formDetails[3].equals("$/sq.ft/period")) {
		pt_sq_ft_period.click();
		
	}
	
	else if (formDetails[3].equals("$/period")) {
		
	 pt_period.click();
	 
	}
	
	Thread.sleep(1000);
	
	
	if(formDetails[4].equals("Nightly")) {
		
		check_night.click();
		
		
	}
	
	else if(formDetails[4].equals("Monthly")) {
		
		check_monthly.click();
		
	}
	
	else if(formDetails[4].equals("Yearly")) {
		
		check_Yearly.click();
		
	}
	
	else if(formDetails[4].equals("Monthly,Yearly")) {
		
		check_monthly.click();
		check_Yearly.click();
		
	}
	
	Thread.sleep(1000);
	add_to_rate_group_selection.click();
	
	
	if(formDetails[5].equals("Create New Rate Group")) {
		create_new_group.click();
		
	}
	
	else if(formDetails[5].equals("Wet Storage")) {
		wet_Storage_group.click();
		
	}
	
	else if(formDetails[5].equals("Dry Storage")) {
		
		
		dry_Storage_group.click();
	
}

	
	Thread.sleep(1000);
	
	if (formDetails[6].equals("Yes")) {
		include_in_OCCUPANCY_yes.click();
		
	}
	
	else if (formDetails[6].equals("No")) {
		include_in_OCCUPANCY_no.click();
		
	}
	
	Thread.sleep(1000);
	
	

		btn_save.click();
		
		Thread.sleep(1000);
		popup_space_saved = popup_group_successfully_add.getText();
		
		Thread.sleep(1000);
		btn_ok_group_added.click();
		
		
//		Thread.sleep(4000);
		
//		Space_detail = new String [] {space_name,"Wet Storage", "Boat", "Single", "$/sq.ft/period","Nightly" };
		
		
		
		
		return popup_space_saved;
		
		
		
	}
	
	
	
	
	
	
	
	
	
}
