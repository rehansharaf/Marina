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

	ArrayList<ArrayList<String>> data;

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

	@FindBy(how = How.XPATH, using = "//*[@id='swal2-html-container']")
	WebElement new_poput;

	@FindBy(how = How.XPATH, using = "//button[@type='button' and @class='swal2-confirm swal2-styled']")
	WebElement btn_popup_missing_txt_ok;

	@FindBy(how = How.XPATH, using = "//div[text()=' The Rate Group field is required.']")
	WebElement popup_missing_rate_group;

	@FindBy(how = How.XPATH, using = "//div[text()=' The Rate Group field is required.']")
	WebElement popup_missing_pricingType;

	public SpaceTypesAddTypesPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String click_add_space_type() {

		action.explicitWait(driver, verify_popup_text, Duration.ofSeconds(10));
		return verify_popup_text.getText();

	}

	public Object[][] addingNewSpace(String filename) throws InterruptedException {

		System.out.println("data collection from excel sheet");

		space_for_add = multi_excel_read(filename);

		System.out.println("testing");

		for (int i = 0; i < actRows; i++) {

			String space_name = (String) space_for_add[i][0];
			String fixed_unit_period = (String) space_for_add[i][1];
			String capacity = (String) space_for_add[i][2];
			String pricing_type = (String) space_for_add[i][3];
			String p_plane = (String) space_for_add[i][4];
			String add_to_group = (String) space_for_add[i][5];
			String include_in_occupancy = (String) space_for_add[i][6];

			try {
				action.explicitWait(driver, nameText, Duration.ofSeconds(10));
			} catch (Exception e) {

				System.out.println("click on add to space button");
				action.explicitWaitElementClickable(driver, btn_reClick_add_space, Duration.ofSeconds(10));
				btn_reClick_add_space.click();
				Thread.sleep(1000);
				action.explicitWait(driver, nameText, Duration.ofSeconds(10));

			}
			Thread.sleep(1000);
			nameText.sendKeys(space_name);
			Thread.sleep(1000);

			if (fixed_unit_period.equals("Boat")) {

				fixedCheck.click();
			} else if (fixed_unit_period.equals("Car/Trailer")) {
				unit_period_check.click();
			}

			Thread.sleep(1000);

			if (fixed_unit_period.equals("Boat")) {
				
				try {
				action.explicitWait(driver, capacity_click, Duration.ofSeconds(10));
				}
				catch(Exception e) {
					System.out.println("capacity drop down not found");
					break;
				}
				capacity_click.click();
				Thread.sleep(1000);

				if (capacity.equals("Single")) {
					action.explicitWait(driver, single_boat, Duration.ofSeconds(10));
					single_boat.click();
				}

				else if (capacity.equals("Multiple")) {
					action.explicitWait(driver, multipleBoats, Duration.ofSeconds(10));
					multipleBoats.click();
				}

				Thread.sleep(1000);
				action.explicitWait(driver, pricing_type_click, Duration.ofSeconds(10));
				pricing_type_click.click();
				Thread.sleep(2000);

				if (pricing_type.equals("$/ft/period")) {

					action.explicitWait(driver, pt_ft_period, Duration.ofSeconds(10));
					pt_ft_period.click();
				}

				else if (pricing_type.equals("$/sq.ft/period")) {
					action.explicitWait(driver, pt_sq_ft_period, Duration.ofSeconds(10));
					pt_sq_ft_period.click();

				}

				else if (pricing_type.equals("$/period")) {
					action.explicitWait(driver, pt_period, Duration.ofSeconds(10));
					pt_period.click();

				}

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
				include_in_OCCUPANCY_yes.click();

			}

			else if (include_in_occupancy.equals("No")) {
				include_in_OCCUPANCY_no.click();

			}

			Thread.sleep(2000);

			action.explicitWait(driver, btn_save, Duration.ofSeconds(10));
			Thread.sleep(1000);
			btn_save.click();

			Thread.sleep(2000);
			try {
				action.explicitWait(driver, popup_group_successfully_add, Duration.ofSeconds(10));
				Thread.sleep(1000);
				popup_space_saved = popup_group_successfully_add.getText();
				System.out.println(popup_space_saved);
			} catch (Exception e) {
				System.out.println("space not added");
			}

			Thread.sleep(2000);

			try {
//				action.explicitWait(driver, btn_ok_group_added, Duration.ofSeconds(10));
				action.explicitWaitElementClickable(driver, btn_ok_group_added, Duration.ofSeconds(10));

				Thread.sleep(1000);
				btn_ok_group_added.click();
				Thread.sleep(3000);

			} catch (Exception e) {
				System.out.println("some error durrin adding space");
			}

			System.out.println(" Space Name Added  = " + space_name);

			after_add_space = new String[actRows][8];
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
		action.explicitWaitElementClickable(driver, btn_reClick_add_space, Duration.ofSeconds(10));
		btn_reClick_add_space.click();
		Thread.sleep(1000);
		action.explicitWait(driver, nameText, Duration.ofSeconds(10));

		btn_save.click();
		action.explicitWaitElementClickable(driver, new_poput, Duration.ofSeconds(10));
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

				action.explicitWaitElementClickable(driver, btn_save, Duration.ofSeconds(10));
				nameText.sendKeys("test_tbt");
				btn_save.click();
				Charct_popup_all = new_poput.getText();
				space_name_filed = false;

			}

			else if (rate_group_field == true) {

				action.explicitWaitElementClickable(driver, btn_save, Duration.ofSeconds(10));

				add_to_rate_group_selection.click();
				wet_Storage_group.click();
				btn_save.click();
				Charct_popup_all = new_poput.getText();
				rate_group_field = false;

			}

			else if (pricing_type_field == true) {

				action.explicitWaitElementClickable(driver, btn_save, Duration.ofSeconds(10));

				pricing_type_click.click();
				pt_sq_ft_period.click();
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
			action.explicitWaitElementClickable(driver, btn_save, Duration.ofSeconds(10));

		}

		System.out.println("testing");

		driver.navigate().refresh();

		action.explicitWaitElementClickable(driver, btn_reClick_add_space, Duration.ofSeconds(10));
		btn_reClick_add_space.click();
		Thread.sleep(1000);
		action.explicitWait(driver, nameText, Duration.ofSeconds(10));

		
		unit_period_check.click();

		btn_save.click();
		action.explicitWaitElementClickable(driver, new_poput, Duration.ofSeconds(10));
		Charct_popup_all = new_poput.getText();
		System.out.println("testing");

		count = 0;

		space_name_filed = false;
		rate_group_field = false;

		for (int j = 0; j < 2; j++) {

			if (space_name_filed == true) {

				action.explicitWaitElementClickable(driver, btn_save, Duration.ofSeconds(10));
				nameText.sendKeys("test_tbt");
				btn_save.click();
				Charct_popup_all = new_poput.getText();
				space_name_filed = false;

			}

			else if (rate_group_field == true) {

				action.explicitWaitElementClickable(driver, btn_save, Duration.ofSeconds(10));

				add_to_rate_group_selection.click();
				wet_Storage_group.click();
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
			action.explicitWaitElementClickable(driver, btn_save, Duration.ofSeconds(10));

		}

		return record_popup_error_field;

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

}
