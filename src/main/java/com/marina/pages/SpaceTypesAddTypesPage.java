package com.marina.pages;

import java.lang.reflect.Array;
import java.time.Duration;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v115.domsnapshot.model.ArrayOfStrings;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.marina.actiondriver.Action;
import com.marina.base.TestBase;
import com.marina.utils.ExcelLibrary;

public class SpaceTypesAddTypesPage {

	WebDriver driver;
	Action action = new Action();
	String popup_space_saved;
	String rateGroup;
	int rows, column, actRows;
	SpaceTypesPage stp;
	Object[][] space_for_add;
	Object[][] after_add_space;
	String filename;
	
	String []price_type_options_count;
	

	ArrayList<ArrayList<String>> data;

	@FindBy(how = How.XPATH, using = "//label[text()='FIXED $ OR $/UNIT/PERIOD']")
	WebElement verify_popup_text;


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
	
	By h_enter_rate_group_name_title = By.xpath("//h2[text()='Enter Rate Group Name']");
	
	By input_field_group_name_enter = By.xpath("//input[@id='swal2-input']");
	
	By btn_ok_group_name_save = By.xpath("//button[text()='OK']");
	
	By btn_cancel_group_name_save = By.xpath("//button[text()='Cancel']");
	
	
	
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

	@FindBy(how = How.XPATH, using = "//button[text()='OK']")
	WebElement btn_ok_group_added;

	@FindBy(how = How.XPATH, using = "//button[text()='Add Space Type']")
	WebElement btn_reClick_add_space;

	@FindBy(how = How.XPATH, using = "//*[@id='swal2-html-container']")
	WebElement new_poput;

	@FindBy(how = How.XPATH, using = "//button[@type='button' and @class='swal2-confirm swal2-styled']")
	WebElement btn_popup_missing_txt_ok;

	@FindBy(how = How.XPATH, using = "//div[text()=' The Rate Group field is required.']")
	WebElement popup_missing_rate_group;

	@FindBy(how = How.XPATH, using = "//div[text()=' The Rate Group field is required.']")
	WebElement popup_missing_pricingType;
	
	By nameText = By.xpath("//input[@name='name']");
	By editSpaceBtn = By.xpath("//a[@title='Edit Space Type']");
	By successOk = By.xpath("//button[text()='OK']");
	By popup_group_successfully_add = By.xpath("//div[text()='Space type saved successfully']");
	By rateGroupSelectField = By.xpath("//select[@name='price_group']");
	By btn_saveAddingRate = By.xpath("//div[@id='modalAddSpaceType']//button[@type='submit' and @class='btn btn-primary' and text()='Save']");
	By capacitySelect = By.xpath("//label[text()='Capacity']/following-sibling::select");
	
	public SpaceTypesAddTypesPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void addSingleSpaceMandatoryFields(String spaceTypeName, String capacity, String pricingType, String duration, String rateGroup, String newRateGroup) throws InterruptedException {
		
		action.explicitWait(driver, verify_popup_text, Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		action.type(driver.findElement(nameText), spaceTypeName);
		action.selectByVisibleText(capacity, driver.findElement(capacitySelect));
		action.selectByVisibleText(pricingType, pricing_type_click); /* $/ft/period , $/sq.ft/period, $/period  */
		if(duration.equalsIgnoreCase("nightly"))
			action.click1(check_night, "click nightly option");
		else if(duration.equalsIgnoreCase("check_monthly"))
			action.click1(check_monthly, "click monthly option");
		else if(duration.equalsIgnoreCase("check_yearly"))
			action.click1(check_Yearly, "click yearly option");
		else if(duration.equalsIgnoreCase("check_all")) {
			action.click1(check_night, "click all options");
			action.click1(check_monthly, "click all options");
			action.click1(check_Yearly, "click all options");
		}else if(duration.contains("nightly") && duration.contains("monthly")) {
			action.click1(check_night, "check nightly and monthly");
			action.click1(check_monthly, "check nightly and monthly");
		}else if(duration.contains("nightly") && duration.contains("yearly")) {
			action.click1(check_night, "click nightly and yearly");
			action.click1(check_Yearly, "click nightly and yearly");
		}else {
			action.click1(check_monthly, "click monthly and yearly");
			action.click1(check_Yearly, "click monthly and yearly");
		}
			
		if(newRateGroup.equalsIgnoreCase("no")) {
		
			action.selectByVisibleText(rateGroup, add_to_rate_group_selection);
			
		}else {
			
			action.selectByVisibleText("Create New Rate Group", driver.findElement(rateGroupSelectField));
			action.explicitWaitVisibility(driver, driver.findElement(input_field_group_name_enter), input_field_group_name_enter, 
											Duration.ofSeconds(10));
			
			action.type(driver.findElement(input_field_group_name_enter), rateGroup);
			action.click1(btn_popup_missing_txt_ok, "click ok btn rategroup");
			action.explicitWaitVisibility(driver, driver.findElement(btn_saveAddingRate), btn_saveAddingRate, Duration.ofSeconds(20));
			action.explicitWaitElementClickable(driver, driver.findElement(btn_saveAddingRate), Duration.ofSeconds(20));
			
			
		}
		
		action.scrollByVisibilityOfElement(driver, btn_save);
		action.explicitWaitElementClickable(driver, btn_save, Duration.ofSeconds(20));
		Thread.sleep(2000);
		action.click1(btn_save, "click save btn");
		action.explicitWaitElementClickable(driver,driver.findElement(successOk) , Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		action.click1(driver.findElement(successOk), "click success ok btn");
		
		
		
		
	}
	
	public String click_add_space_type() {

		action.explicitWait(driver, verify_popup_text, Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		return verify_popup_text.getText();
	}

	public Object[][] addingNewSpace(String filename) throws InterruptedException {

		System.out.println("data collection from excel sheet");

		space_for_add = multi_excel_read(filename);

		System.out.println("testing");
		after_add_space = new String[actRows][8];
		
		for (int i = 0; i < actRows; i++) {

			String space_name = (String) space_for_add[i][0];
			String fixed_unit_period = (String) space_for_add[i][1];
			String capacity = (String) space_for_add[i][2];
			String pricing_type = (String) space_for_add[i][3];
			String p_plane = (String) space_for_add[i][4];
			String add_to_group = (String) space_for_add[i][5];
			String include_in_occupancy = (String) space_for_add[i][6];

			//try {
			if(i > 0) {
				
				action.scrollByVisibilityOfElement(driver, btn_reClick_add_space);
				action.explicitWaitElementClickable(driver, btn_reClick_add_space, Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
				action.click1(btn_reClick_add_space, "Clickung add space btn");
				Thread.sleep(1000);
			}
				
				action.explicitWait(driver, driver.findElement(nameText), Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
			/*} catch (Exception e) {

				System.out.println("click on add to space button");
				action.scrollByVisibilityOfElement(driver, btn_reClick_add_space);
				action.explicitWaitElementClickable(driver, btn_reClick_add_space, Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
				action.click1(btn_reClick_add_space, "Clickung add space btn");
				Thread.sleep(1000);
				action.explicitWaitPresenceOfElement(driver, nameText, Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));

			}*/
			Thread.sleep(1000);
			driver.findElement(nameText).sendKeys(space_name);
			Thread.sleep(1000);

			if (fixed_unit_period.equals("Boat")) {

				fixedCheck.click();
			} else if (fixed_unit_period.equals("Car/Trailer")) {
				unit_period_check.click();
			}

			Thread.sleep(1000);

			if (fixed_unit_period.equals("Boat")) {
				
				try {
				action.explicitWait(driver, capacity_click, Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
				}
				catch(Exception e) {
					System.out.println("capacity drop down not found");
					break;
				}
				capacity_click.click();
				Thread.sleep(1000);

				if (capacity.equals("Single")) {
					action.explicitWait(driver, single_boat, Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
					single_boat.click();
				}

				else if (capacity.equals("Multiple")) {
					action.explicitWait(driver, multipleBoats, Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
					multipleBoats.click();
				}

				Thread.sleep(1000);
				action.explicitWait(driver, pricing_type_click, Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
				pricing_type_click.click();
				Thread.sleep(2000);

				if (pricing_type.equals("$/ft/period")) {

					action.explicitWait(driver, pt_ft_period, Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
					pt_ft_period.click();
				}

				else if (pricing_type.equals("$/sq.ft/period")) {
					action.explicitWait(driver, pt_sq_ft_period, Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
					pt_sq_ft_period.click();

				}

				
				
//				else if (pricing_type.equals("$/period")) {
//					action.explicitWait(driver, pt_period, Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
//					pt_period.click();
//
//				}

	
				
				
				
			}

			Thread.sleep(2000);

			if (p_plane.equals("Nightly")) {

				check_night.click();

			}

			else if (p_plane.equals("Monthly")) {

				check_monthly.click();

			}

			else if (p_plane.equals("Yearly")) {

				check_Yearly.click();

			}

			else if (p_plane.equals("Monthly,Yearly")) {

				check_monthly.click();
				Thread.sleep(2000);
				check_Yearly.click();

			}

			else if (p_plane.equals("Nightly,Yearly")) {

				check_night.click();
				Thread.sleep(2000);
				check_Yearly.click();

			}

			else if (p_plane.equals("Nightly,Monthly")) {

				check_night.click();
				Thread.sleep(2000);
				check_monthly.click();

			}

			else if (p_plane.equals("Nightly,Monthly,Yearly")) {

				check_night.click();
				Thread.sleep(2000);
				check_monthly.click();
				Thread.sleep(2000);
				check_Yearly.click();

			}

			Thread.sleep(2000);
			add_to_rate_group_selection.click();
			Thread.sleep(1000);

			if (add_to_group.equals("Create New Rate Group")) {
				create_new_group.click();

			}

			else if (add_to_group.equals("Wet Storage")) {
				wet_Storage_group.click();

			}

			else if (add_to_group.equals("Dry Storage")) {

				dry_Storage_group.click();

			}

			Thread.sleep(1000);

			if (include_in_occupancy.equals("Yes")) {
				action.scrollByVisibilityOfElement(driver, include_in_OCCUPANCY_yes);
				include_in_OCCUPANCY_yes.click();

			}

			else if (include_in_occupancy.equals("No")) {
				action.scrollByVisibilityOfElement(driver, include_in_OCCUPANCY_no);
				include_in_OCCUPANCY_no.click();

			}

			Thread.sleep(2000);

			action.explicitWait(driver, btn_save, Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
			Thread.sleep(1000);
			action.scrollByVisibilityOfElement(driver, btn_save);
			btn_save.click();

			//Thread.sleep(2000);
			try {
				action.explicitWaitPresenceOfElement(driver, popup_group_successfully_add, Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
				Thread.sleep(1000);
				popup_space_saved = driver.findElement(popup_group_successfully_add).getText();
				System.out.println(popup_space_saved);
			} catch (Exception e) {
				System.out.println("space not added");
			}

			Thread.sleep(2000);

			try {
//				action.explicitWait(driver, btn_ok_group_added, Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
				action.explicitWaitElementClickable(driver, btn_ok_group_added, Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));

				Thread.sleep(1000);
				btn_ok_group_added.click();
				Thread.sleep(3000);

			} catch (Exception e) {
				System.out.println("some error durrin adding space");
			}

			System.out.println(" Space Name Added  = " + space_name);

				
			//after_add_space = new String[actRows][8];
			after_add_space[i][0] = space_for_add[i][0];
			after_add_space[i][1] = space_for_add[i][1];
			after_add_space[i][2] = space_for_add[i][2];
			after_add_space[i][3] = space_for_add[i][3];
			after_add_space[i][4] = space_for_add[i][4];
			after_add_space[i][5] = space_for_add[i][5];
			after_add_space[i][6] = space_for_add[i][6];
			after_add_space[i][7] = popup_space_saved;

		}

		System.out.println("test");

		return after_add_space;

	}


	

	public String[] addSpace_skipping() throws InterruptedException {

		driver.navigate().refresh();

		String record_popup_error_field[] = new String[6];

		System.out.println("click on add to space button");
		action.explicitWaitElementClickable(driver, btn_reClick_add_space, Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		btn_reClick_add_space.click();
		Thread.sleep(1000);
		action.explicitWaitPresenceOfElement(driver, nameText, Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		
		action.scrollByVisibilityOfElement(driver, btn_save);
		btn_save.click();
		action.explicitWaitElementClickable(driver, new_poput, Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		String Charct_popup_all = new_poput.getText();
		System.out.println("testing");

		int count = 0;

		boolean space_name_filed = false;
		boolean rate_group_field = false;
		boolean pricing_type_field = false;
		boolean prcing_plane_filed = false;
		System.out.println("teseting");

		for (int j = 0; j < 4; j++) {

			if (space_name_filed == true) {

				action.explicitWaitElementClickable(driver, btn_save, Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
				driver.findElement(nameText).sendKeys("test_tbt");
				action.scrollByVisibilityOfElement(driver, btn_save);
				btn_save.click();
				Charct_popup_all = new_poput.getText();
				space_name_filed = false;

			}

			else if (rate_group_field == true) {

				action.explicitWaitElementClickable(driver, btn_save, Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));

				add_to_rate_group_selection.click();
				wet_Storage_group.click();
				action.scrollByVisibilityOfElement(driver, btn_save);
				btn_save.click();
				Charct_popup_all = new_poput.getText();
				rate_group_field = false;

			}

			else if (pricing_type_field == true) {

				action.explicitWaitElementClickable(driver, btn_save, Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));

				pricing_type_click.click();
				pt_sq_ft_period.click();
				action.scrollByVisibilityOfElement(driver, btn_save);
				btn_save.click();
				Charct_popup_all = new_poput.getText();
				space_name_filed = true;
				rate_group_field = true;
				pricing_type_field = true;
				prcing_plane_filed = true;
				System.out.println("loop closed");

			}

			for (int i = 0; i < Charct_popup_all.length(); i++) {
				if (Charct_popup_all.charAt(i) != ' ')
					count++;
			}

			System.out.println("teseting");

			if (count == 123) {
				space_name_filed = true;
				record_popup_error_field[0] = "clear";
			}

			else if(count == 90) {
				rate_group_field = true;
				record_popup_error_field[1] = "clear";

			}

			else if (count == 61) {
				pricing_type_field = true;
				record_popup_error_field[2] = "clear";
			}

			else if (count == 30) {
				prcing_plane_filed = true;
				record_popup_error_field[3] = "clear";
			}

			count = 0;

			btn_popup_missing_txt_ok.click();
			action.explicitWaitElementClickable(driver, btn_save, Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));

		}

		System.out.println("testing");

		driver.navigate().refresh();

		action.explicitWaitElementClickable(driver, btn_reClick_add_space, Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		btn_reClick_add_space.click();
		Thread.sleep(1000);
		action.explicitWaitPresenceOfElement(driver, nameText, Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));

		
		unit_period_check.click();
		action.scrollByVisibilityOfElement(driver, btn_save);
		btn_save.click();
		action.explicitWaitElementClickable(driver, new_poput, Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		Charct_popup_all = new_poput.getText();
		System.out.println("testing");

		count = 0;

		space_name_filed = false;
		rate_group_field = false;

		for (int j = 0; j < 2; j++) {

			if (space_name_filed == true) {

				action.explicitWaitElementClickable(driver, btn_save, Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
				driver.findElement(nameText).sendKeys("test_tbt");
				action.scrollByVisibilityOfElement(driver, btn_save);
				btn_save.click();
				Charct_popup_all = new_poput.getText();
				space_name_filed = false;

			}

			else if (rate_group_field == true) {

				action.explicitWaitElementClickable(driver, btn_save, Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));

				add_to_rate_group_selection.click();
				wet_Storage_group.click();
				action.scrollByVisibilityOfElement(driver, btn_save);
				btn_save.click();
				Charct_popup_all = new_poput.getText();
				rate_group_field = false;

			}

			for (int i = 0; i < Charct_popup_all.length(); i++) {
				if (Charct_popup_all.charAt(i) != ' ')
					count++;
			}

			if (count == 61) {
				space_name_filed = true;
				record_popup_error_field[4] = "clear";
			}

			else if (count == 28) {
				rate_group_field = true;
				record_popup_error_field[5] = "clear";

			}

			count = 0;

			btn_popup_missing_txt_ok.click();
			action.explicitWaitElementClickable(driver, btn_save, Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));

		}

		return record_popup_error_field;

	}
	
	
	public String[] select_unit_period_verify_pricing_type() {

		action.explicitWaitElementClickable(driver, btn_reClick_add_space,
				Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));

		fixedCheck.click();
		pricing_type_click.click();

		price_type_options_count = new String[3];

		price_type_options_count[0] = pt_ft_period.getText();
		price_type_options_count[1] = pt_sq_ft_period.getText();

		try {

			price_type_options_count[2] = pt_period.getText();

		} catch (Exception e) {

			System.out.println("period value not found ");
			price_type_options_count[2] = "no text";

		}

		return price_type_options_count;

	}
	


	public Object[][] multi_excel_read(String filename) {

		String path = System.getProperty("user.dir") + "\\src\\test\\resources\\testdata\\" + filename;
		ExcelLibrary obj = new ExcelLibrary(path);

		// Totals rows count
		rows = obj.getRowCount("loginData");
		// Total Columns
		column = obj.getColumnCount("loginData");
		actRows = rows - 1;

		Object[][] data2 = new Object[actRows][column];

		for (int i = 0; i < actRows; i++) {
			for (int j = 0; j < column; j++) {
				data2[i][j] = obj.getCellData("loginData", j, i + 2);
			}

		}
		return data2;
	}
	
	
	
	
	public SpaceTypesPage  adding_rates_group(String name) throws InterruptedException{


		System.out.println("click on add to space button");
		add_to_rate_group_selection.click();
		Thread.sleep(1000);
		action.explicitWaitElementClickable(driver, create_new_group,
				Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		create_new_group.click();
		Thread.sleep(1000);
		action.explicitWait(driver, driver.findElement(h_enter_rate_group_name_title),
				Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		driver.findElement(input_field_group_name_enter).sendKeys(name);
		Thread.sleep(2000);
		driver.findElement(btn_ok_group_name_save).click();
		Thread.sleep(2000);
		btn_close.click();
		return new SpaceTypesPage(driver);

	}
	
	
	

	
	
	
	
		
	
	
	
	
	
	

}
