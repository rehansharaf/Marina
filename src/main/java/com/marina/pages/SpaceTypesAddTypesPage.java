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

	public SpaceTypesAddTypesPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String click_add_space_type() {

		action.explicitWait(driver, verify_popup_text, Duration.ofSeconds(10));
		return verify_popup_text.getText();

	}

	public Object[][] addingNewSpace() throws InterruptedException {

		System.out.println("data collection from excel sheet");

		space_for_add = multi_excel_read();

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

				btn_reClick_add_space.click();
				action.explicitWait(driver, nameText, Duration.ofSeconds(10));

			}

			nameText.sendKeys(space_name);
			Thread.sleep(1000);

			if (fixed_unit_period.equals("Boat")) {

				fixedCheck.click();
			} else if (fixed_unit_period.equals("Car/Trailer")) {
				unit_period_check.click();
			}

			
			Thread.sleep(1000);
			
			if (fixed_unit_period.equals("Boat")) {
			capacity_click.click();
			Thread.sleep(1000);

			if (capacity.equals("Single")) {
				single_boat.click();
			}

			else if (capacity.equals("Multiple")) {
				multipleBoats.click();
			}

			Thread.sleep(1000);
			pricing_type_click.click();

			if (pricing_type.equals("$/ft/period")) {
				pt_ft_period.click();
			}

			else if (pricing_type.equals("$/sq.ft/period")) {
				pt_sq_ft_period.click();

			}

			else if (pricing_type.equals("$/period")) {

				pt_period.click();

			}
			
			
			
			}

			Thread.sleep(1000);

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
				check_Yearly.click();

			}

			Thread.sleep(1000);
			add_to_rate_group_selection.click();

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

			

			btn_save.click();
			Thread.sleep(1000);
			try {
				popup_space_saved = popup_group_successfully_add.getText();
			} catch (Exception e) {
				System.out.println("space not added");
			}

			Thread.sleep(1000);

			try {
				btn_ok_group_added.click();
				Thread.sleep(1000);

			} catch (Exception e) {
				System.out.println("some error durrin space adding");
			}
			
			
			System.out.println("user add" + i);
			Thread.sleep(1000);

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

		return after_add_space;

//		return popup_space_saved;

	}

	public Object[][] singleAddingSpace() throws InterruptedException {

		System.out.println("data collection from excel sheet");

		space_for_add = single_Space_Exce_Read();

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

				btn_reClick_add_space.click();
				action.explicitWait(driver, nameText, Duration.ofSeconds(10));

			}

			nameText.sendKeys(space_name);
			Thread.sleep(1000);

			if (fixed_unit_period.equals("Boat")) {

				fixedCheck.click();
			} else if (fixed_unit_period.equals("Car/Trailer")) {
				unit_period_check.click();
			}

			Thread.sleep(1000);
			capacity_click.click();
			Thread.sleep(1000);

			if (capacity.equals("Single")) {
				single_boat.click();
			}

			else if (capacity.equals("Multiple")) {
				multipleBoats.click();
			}

			Thread.sleep(1000);
			pricing_type_click.click();

			if (pricing_type.equals("$/ft/period")) {
				pt_ft_period.click();
			}

			else if (pricing_type.equals("$/sq.ft/period")) {
				pt_sq_ft_period.click();

			}

			else if (pricing_type.equals("$/period")) {

				pt_period.click();

			}

			Thread.sleep(1000);

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
				Thread.sleep(1000);
				check_Yearly.click();

			}

			else if (p_plane.equals("Nightly,Yearly")) {

				check_night.click();
				Thread.sleep(1000);
				check_Yearly.click();

			}

			else if (p_plane.equals("Nightly,Monthly")) {

				check_night.click();
				Thread.sleep(1000);
				check_monthly.click();

			}

			else if (p_plane.equals("Nightly,Monthly,Yearly")) {

				check_night.click();
				Thread.sleep(1000);
				check_monthly.click();
				Thread.sleep(1000);
				check_Yearly.click();

			}

			Thread.sleep(1000);
			add_to_rate_group_selection.click();

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

			System.out.println("user add" + i);
			Thread.sleep(1000);

			btn_save.click();
			Thread.sleep(1000);
			try {
				popup_space_saved = popup_group_successfully_add.getText();
			} catch (Exception e) {
				System.out.println("space not added");
			}

			Thread.sleep(1000);

			try {
				btn_ok_group_added.click();
				Thread.sleep(1000);

			} catch (Exception e) {
				System.out.println("some error durrin space adding");
			}

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

		return after_add_space;

//		return popup_space_saved;

	}

	public Object[][] multi_excel_read() {

		String path = System.getProperty("user.dir") + "\\src\\test\\resources\\testdata\\multiSpaces.xlsx";
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

	public Object[][] single_Space_Exce_Read() {

		String path = System.getProperty("user.dir") + "\\src\\test\\resources\\testdata\\singleSpace.xlsx";
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
