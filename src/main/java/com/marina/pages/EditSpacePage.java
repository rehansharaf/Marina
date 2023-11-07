package com.marina.pages;

import java.lang.reflect.Array;
import java.time.Duration;
import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v115.domsnapshot.model.ArrayOfStrings;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.marina.actiondriver.Action;
import com.marina.base.TestBase;
import com.marina.utils.ExcelLibrary;

public class EditSpacePage {

	WebDriver driver;
	Action action = new Action();
	String after_edit_space_data[];

	@FindBy(how = How.XPATH, using = "//label[text()='FIXED $ OR $/UNIT/PERIOD']")
	WebElement verify_popup_text;

	@FindBy(how = How.XPATH, using = "//input[@name='name']")
	WebElement nameText;

	@FindBy(how = How.XPATH, using = "//*[@id='modalEditSpaceType']/div/form/div/div[2]/div/div[1]/input")
	WebElement edit_nameText;

	@FindBy(how = How.XPATH, using = "//label[@for='parkingb']")
	WebElement fixedCheck;

	@FindBy(how = How.XPATH, using = "//label[@for='parkingc']")
	WebElement unit_period_check;

	@FindBy(how = How.XPATH, using = "//select[@name='boats']")
	WebElement capacity_click;

	@FindBy(how = How.XPATH, using = "//option[. = 'Single Boat']")
	WebElement single_boat;

	@FindBy(how = How.XPATH, using = "//option[. = 'Multiple Boats']")
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

	@FindBy(how = How.XPATH, using = "//input[@value='yearly']")
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

	@FindBy(how = How.XPATH, using = "//button[text()='Add Space Type']")
	WebElement btn_reClick_add_space;

	@FindBy(how = How.XPATH, using = "/html/body/div[13]/div/form/div/div[3]/button[2]")
	WebElement btn_edit_save;

	@FindBy(how = How.XPATH, using = "//*[@id='modalEditSpaceType']/div/form/div/div[3]/button[2]")
	WebElement btn_edit_save2;

	
	
	
	
	@FindBy(how = How.XPATH, using = "/html/body/div[13]/div/form/div/div[2]/div/div[3]/select")
	WebElement edit_capacity;
	
	
	

	@FindBy(how = How.XPATH, using = "/html/body/div[13]/div/form/div/div[2]/div/div[3]/select/option[2]")
	WebElement edit_multipleBoats;

	@FindBy(how = How.XPATH, using = "/html/body/div[13]/div/form/div/div[2]/div/div[4]/select")
	WebElement edit_pricing_type_click;

	@FindBy(how = How.XPATH, using = "/html/body/div[13]/div/form/div/div[2]/div/div[4]/select/option[2]")
	WebElement edit_pt_sq_ft_period;

	@FindBy(how = How.XPATH, using = "/html/body/div[13]/div/form/div/div[2]/div/div[5]/div[2]/div/input")
	WebElement edit_check_monthly;

	@FindBy(how = How.XPATH, using = "//*[@id='modalEditSpaceType']/div/form/div/div[2]/div/div[6]/select")
	WebElement edit_add_to_rate_group_selection;

	@FindBy(how = How.XPATH, using = "/html/body/div[13]/div/form/div/div[2]/div/div[6]/select/option[4]")
	WebElement edit_dry_Storage_group;

	@FindBy(how = How.XPATH, using = "/html/body/div[13]/div/form/div/div[2]/div/div[7]/div[1]/input")
	WebElement edit_include_in_OCCUPANCY_yes;

	public EditSpacePage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public SpaceTypesPage edit_space_data(String userChange) throws InterruptedException {
		// TODO Auto-generated method stub

		after_edit_space_data = new String[6];
		action.explicitWaitElementClickable(driver, btn_edit_save, Duration.ofSeconds(10));
		edit_nameText.clear();
		edit_nameText.sendKeys(userChange);

		edit_capacity.click();
		edit_multipleBoats.click();
		edit_pricing_type_click.click();
		edit_pt_sq_ft_period.click();
		edit_check_monthly.click();
		edit_add_to_rate_group_selection.click();
		edit_dry_Storage_group.click();
		edit_include_in_OCCUPANCY_yes.click();
		action.JSClick(driver, btn_edit_save2);
		action.explicitWaitElementClickable(driver, btn_ok_group_added, Duration.ofSeconds(10));
		btn_ok_group_added.click();
		driver.navigate().refresh();
		
		
		return new SpaceTypesPage(driver);
	

	}

}
