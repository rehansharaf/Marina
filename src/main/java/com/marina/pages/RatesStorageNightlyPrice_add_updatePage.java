package com.marina.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.marina.actiondriver.Action;
import com.marina.base.TestBase;

public class RatesStorageNightlyPrice_add_updatePage {

	WebDriver driver;
	Action action = new Action();

	public RatesStorageNightlyPrice_add_updatePage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// breadcrumb home /space/pricing/group name/nightprice/update

	@FindBy(how = How.XPATH, using = "//button[@data-bs-target='#modalBuckets' and @type='button']")
	WebElement btn_buckets_web_element;

	@FindBy(how = How.XPATH, using = "//button[@type='button' and text()='Add More']")
	WebElement btn_popup_add_more_buckets;

	@FindBy(how = How.XPATH, using = "//button[@type='button' and text()='Save Buckets']")
	WebElement btn_popup_save_new_buckets;

	By bucket_table_4_row = By.xpath("//div[@class='table-responsive']/table[@class='table']/tbody/tr[5]");

	@FindBy(how = How.XPATH, using = "//h2[@id='swal2-title' and text()='Success!']")
	WebElement popup_bucket_add_succssfully_text;

	@FindBy(how = How.XPATH, using = "//button[@class='swal2-confirm swal2-styled' and @type='button' and text()='OK']")
	WebElement popup_btn_ok_successfully_add_bucket;

	
	By btn_add_price = By.xpath("//a[@class='btn btn-success btn-md px-4']");
	
	By h_nightly_price_tittle = By
			.xpath("//h1[@class='mb-0 text-white page-nav fs_22 fw_6 d-flex align-items-center']");

	By btn_buckets_by_xpath = By.xpath("//button[@type='button' and @class='btn btn-secondary bg-white text-dark']");

	By btn_view_nightly_price = By.xpath("//a[@class='btn btn-success btn-md px-4']");

	By tab_space_pricing = By.xpath("//button[text()='Space Pricing']");
	By tab_power_pricing = By.xpath("//button[text()='Power Pricing']");

	By btn_add_new_year = By.xpath("//button[@ng-click='addYear()']");

	By tab_year_2023 = By.xpath("//button[@id='year-2023-tab']");
	By tab_year_2024 = By.xpath("//button[@id='year-2024-tab']");
	By tab_year_2025 = By.xpath("//button[@id='year-2025-tab']");

	By tab_1_ft_size = By.xpath("//*[@id='buckets_tabs']/li[1]/button/span");
	@FindBy(how = How.XPATH, using = "//*[@id='buckets_tabs']/li[1]/button/span")
	WebElement tab_1_ft_size_webelement;

	By tab_2_ft_size = By.xpath("//*[@id='buckets_tabs']/li[2]/button/span");
	By tab_3_ft_size = By.xpath("//*[@id='buckets_tabs']/li[3]/button/span");
	By tab_4_ft_size = By.xpath("//*[@id='buckets_tabs']/li[4]/button/span");

	By btn_previous_year = By.xpath("//button[@ng-click='prev_year()']");
	By h_active_year = By.xpath("//h4[@ng-bind='active_year']");
	By btn_next_year = By.xpath("//button[@ng-click='next_year()']");
	
	
	
	
	
	/*
	 * 
	 * ok check upside
	 * 
	 * 
	 * 
	 * /
	 */

	By dropdown_month_from_selection = By
			.xpath("//*[@id='bucket-2023-81']/div/div[1]/div/table/tbody/tr/td[1]/div/div[1]/select/option[1]");
	By select_from_jan = By
			.xpath("//*[@id='bucket-2023-81']/div/div[1]/div/table/tbody/tr/td[1]/div/div[1]/select/option[1]");
	By select_from_feb = By
			.xpath("//*[@id='bucket-2023-81']/div/div[1]/div/table/tbody/tr/td[1]/div/div[1]/select/option[2]");
	By select_from_mar = By
			.xpath("//*[@id='bucket-2023-81']/div/div[1]/div/table/tbody/tr/td[1]/div/div[1]/select/option[3]");
	By select_from_apr = By
			.xpath("//*[@id='bucket-2023-81']/div/div[1]/div/table/tbody/tr/td[1]/div/div[1]/select/option[4]");
	By select_from_may = By
			.xpath("//*[@id='bucket-2023-81']/div/div[1]/div/table/tbody/tr/td[1]/div/div[1]/select/option[5]");
	By select_from_jun = By
			.xpath("//*[@id='bucket-2023-81']/div/div[1]/div/table/tbody/tr/td[1]/div/div[1]/select/option[6]");
	By select_from_jul = By
			.xpath("//*[@id='bucket-2023-81']/div/div[1]/div/table/tbody/tr/td[1]/div/div[1]/select/option[7]");
	By select_from_aug = By
			.xpath("//*[@id='bucket-2023-81']/div/div[1]/div/table/tbody/tr/td[1]/div/div[1]/select/option[8]");
	By select_from_sep = By
			.xpath("//*[@id='bucket-2023-81']/div/div[1]/div/table/tbody/tr/td[1]/div/div[1]/select/option[9]");
	By select_from_oct = By
			.xpath("//*[@id='bucket-2023-81']/div/div[1]/div/table/tbody/tr/td[1]/div/div[1]/select/option[10]");
	By select_from_nov = By
			.xpath("//*[@id='bucket-2023-81']/div/div[1]/div/table/tbody/tr/td[1]/div/div[1]/select/option[11]");
	By select_from_dec = By
			.xpath("//*[@id='bucket-2023-81']/div/div[1]/div/table/tbody/tr/td[1]/div/div[1]/select/option[12]");

	
	//*[@id="bucket-2023-82"]/div/div[1]/div/table/tbody/tr/td[1]/div/div[2]/select
	//*[@id='bucket-2023-81']/div/div[1]/div/table/tbody/tr/td[1]/div/div[2]/select
	
	
	
//// ok check apply 
	By dropdown_month_to_selection = By
			.xpath("//*[@id='bucket-2023-81']/div/div[1]/div/table/tbody/tr/td[1]/div/div[2]/select");

	
	By dropdown_month_to_selection_second_bucket = By
			.xpath("//*[@id='bucket-2023-82']/div/div[1]/div/table/tbody/tr/td[1]/div/div[2]/select");
	
	By select_to_dec_second_bucket = By.xpath(
			"//*[@id='bucket-2023-82']/div/div[1]/div/table/tbody/tr/td[1]/div/div[2]/select/option[@value='number:12']");
	
	
	
	
	By dropdown_month_to_selection_third_bucket = By
			.xpath("//*[@id='bucket-2023-99']/div/div[1]/div/table/tbody/tr/td[1]/div/div[2]/select");
	
	By select_to_dec_third_bucket = By.xpath(
			"//*[@id='bucket-2023-99']/div/div[1]/div/table/tbody/tr/td[1]/div/div[2]/select/option[@value='number:12']");
	
	
	/*
	 * 
	 * 
	 * 
	 * 
	 */
	
	
	
	By select_to_jan = By.xpath(
			"//*[@id='bucket-2023-81']/div/div[1]/div/table/tbody/tr/td[1]/div/div[2]/select/option[@value='number:1']");
	By select_to_feb = By.xpath(
			"//*[@id='bucket-2023-81']/div/div[1]/div/table/tbody/tr/td[1]/div/div[2]/select/option[@value='number:2']");
	By select_to_mar = By.xpath(
			"//*[@id='bucket-2023-81']/div/div[1]/div/table/tbody/tr/td[1]/div/div[2]/select/option[@value='number:3']");
	By select_to_apr = By.xpath(
			"//*[@id='bucket-2023-81']/div/div[1]/div/table/tbody/tr/td[1]/div/div[2]/select/option[@value='number:4']");
	By select_to_may = By.xpath(
			"//*[@id='bucket-2023-81']/div/div[1]/div/table/tbody/tr/td[1]/div/div[2]/select/option[@value='number:5']");
	By select_to_jun = By.xpath(
			"//*[@id='bucket-2023-81']/div/div[1]/div/table/tbody/tr/td[1]/div/div[2]/select/option[@value='number:6']");
	By select_to_jul = By.xpath(
			"//*[@id='bucket-2023-81']/div/div[1]/div/table/tbody/tr/td[1]/div/div[2]/select/option[@value='number:7']");
	By select_to_aug = By.xpath(
			"//*[@id='bucket-2023-81']/div/div[1]/div/table/tbody/tr/td[1]/div/div[2]/select/option[@value='number:8']");
	By select_to_sep = By.xpath(
			"//*[@id='bucket-2023-81']/div/div[1]/div/table/tbody/tr/td[1]/div/div[2]/select/option[@value='number:9']");
	By select_to_oct = By.xpath(
			"//*[@id='bucket-2023-81']/div/div[1]/div/table/tbody/tr/td[1]/div/div[2]/select/option[@value='number:10']");
	By select_to_nov = By.xpath(
			"//*[@id='bucket-2023-81']/div/div[1]/div/table/tbody/tr/td[1]/div/div[2]/select/option[@value='number:11']");
	By select_to_dec = By.xpath(
			"//*[@id='bucket-2023-81']/div/div[1]/div/table/tbody/tr/td[1]/div/div[2]/select/option[@value='number:12']");

	// 2023 ok and check
	By input_monday = By.xpath("//*[@id='bucket-2023-81']/div/div[1]/div/table/tbody/tr/td[2]/input");
	By input_tuesday = By.xpath("//*[@id='bucket-2023-81']/div/div[1]/div/table/tbody/tr/td[3]/input");
	By input_wednesday = By.xpath("//*[@id='bucket-2023-81']/div/div[1]/div/table/tbody/tr/td[4]/input");
	By input_thursday = By.xpath("//*[@id='bucket-2023-81']/div/div[1]/div/table/tbody/tr/td[5]/input");
	By input_friday = By.xpath("//*[@id='bucket-2023-81']/div/div[1]/div/table/tbody/tr/td[6]/input");
	By input_saturday = By.xpath("//*[@id='bucket-2023-81']/div/div[1]/div/table/tbody/tr/td[7]/input");
	By input_sunday = By.xpath("//*[@id='bucket-2023-81']/div/div[1]/div/table/tbody/tr/td[8]/input");

	
	By input_monday_second_bucket = By.xpath("//*[@id='bucket-2023-82']/div/div[1]/div/table/tbody/tr/td[2]/input");
	By input_tuesday_second_bucket = By.xpath("//*[@id='bucket-2023-82']/div/div[1]/div/table/tbody/tr/td[3]/input");
	By input_wednesday_second_bucket = By.xpath("//*[@id='bucket-2023-82']/div/div[1]/div/table/tbody/tr/td[4]/input");
	By input_thursday_second_bucket = By.xpath("//*[@id='bucket-2023-82']/div/div[1]/div/table/tbody/tr/td[5]/input");
	By input_friday_second_bucket = By.xpath("//*[@id='bucket-2023-82']/div/div[1]/div/table/tbody/tr/td[6]/input");
	By input_saturday_second_bucket = By.xpath("//*[@id='bucket-2023-82']/div/div[1]/div/table/tbody/tr/td[7]/input");
	By input_sunday_second_bucket = By.xpath("//*[@id='bucket-2023-82']/div/div[1]/div/table/tbody/tr/td[8]/input");
	
	
	By input_monday_third_bucket = By.xpath("//*[@id='bucket-2023-99']/div/div[1]/div/table/tbody/tr/td[2]/input");
	By input_tuesday_third_bucket = By.xpath("//*[@id='bucket-2023-99']/div/div[1]/div/table/tbody/tr/td[3]/input");
	By input_wednesday_third_bucket = By.xpath("//*[@id='bucket-2023-99']/div/div[1]/div/table/tbody/tr/td[4]/input");
	By input_thursday_third_bucket = By.xpath("//*[@id='bucket-2023-99']/div/div[1]/div/table/tbody/tr/td[5]/input");
	By input_friday_third_bucket = By.xpath("//*[@id='bucket-2023-99']/div/div[1]/div/table/tbody/tr/td[6]/input");
	By input_saturday_third_bucket = By.xpath("//*[@id='bucket-2023-99']/div/div[1]/div/table/tbody/tr/td[7]/input");
	By input_sunday_third_bucket = By.xpath("//*[@id='bucket-2023-99']/div/div[1]/div/table/tbody/tr/td[8]/input");
	
	
	
	
	
	
	
	
	// 2023
	By btn_delete_nighlty_month = By
			.xpath("//*[@id='bucket-2023-81']/div/div[1]/div/table/tbody/tr/td[9]/div/button[1]");

	
	By btn_delete_nighlty_month_second_bucket = By
			.xpath("//*[@id='bucket-2023-82']/div/div[1]/div/table/tbody/tr/td[9]/div/button[1]");
	
	
	
	By btn_delete_nighlty_month_third_bucket = By
			.xpath("//*[@id='bucket-2023-99']/div/div[1]/div/table/tbody/tr/td[9]/div/button[1]");
	
	
	
	
	//	By btn_add_nightly_month_when_row_enable = By.xpath("//*[@id='bucket-2023-81']/div/div[1]/div/table/tbody/tr/td[9]/div/button[2]");

	@FindBy(how = How.XPATH, using = "//*[@id='bucket-2023-81']/div/div[1]/div/table/thead/tr/th[9]/button")
	WebElement btn_add_nightly_month_when_row_enable;

	By btn_add_nightly_month_when_row_disable = By.xpath("");

	// 2023
	By h2_yearly_holiday_tittle = By.xpath("//*[@id='bucket-2023-81']/div/div[2]/h2");

	// 2023
	By input_holiday_name = By.xpath("//*[@id='bucket-2023-81']/div/div[2]/table/tbody/tr/td[1]/input");
	By input_holiday_date = By.xpath("//*[@id='bucket-2023-81']/div/div[2]/table/tbody/tr/td[2]/input");
	By input_holiday_price = By.xpath("//*[@id='bucket-2023-81']/div/div[2]/table/tbody/tr/td[3]/input");
	By input_holiday_minimum_reservation = By
			.xpath("//*[@id='bucket-2023-81']/div/div[2]/table/tbody/tr/td[4]/div/input");

	By btn_delete_Holidays = By.xpath("//*[@id='bucket-2023-81']/div/div[2]/table/tbody/tr/td[5]/div/button[1]");
	By btn_holiday_month_add = By.xpath("//*[@id='bucket-2023-81']/div/div[2]/table/tbody/tr/td[5]/div/button[2]");

	By select_effective_holiday_date = By.xpath("//*[@id='year-2023']/div[3]/div/input");

	// 14 = 2023
	By dropdown_calendar_month = By.xpath("//html/body/div[14]/div[1]/div/div/select");
	By calender_holidy_month_jan = By.xpath("//html/body/div[14]/div[1]/div/div/select/option[1]");
	By calender_holidy_month_feb = By.xpath("//html/body/div[14]/div[1]/div/div/select/option[2]");
	By calender_holidy_month_mar = By.xpath("//html/body/div[14]/div[1]/div/div/select/option[3]");
	By calender_holidy_month_apr = By.xpath("//html/body/div[14]/div[1]/div/div/select/option[4]");
	By calender_holidy_month_may = By.xpath("//html/body/div[14]/div[1]/div/div/select/option[5]");
	By calender_holidy_month_jun = By.xpath("//html/body/div[14]/div[1]/div/div/select/option[6]");
	By calender_holidy_month_jul = By.xpath("//html/body/div[14]/div[1]/div/div/select/option[7]");
	By calender_holidy_month_aug = By.xpath("//html/body/div[14]/div[1]/div/div/select/option[8]");
	By calender_holidy_month_sep = By.xpath("//html/body/div[14]/div[1]/div/div/select/option[9]");
	By calender_holidy_month_oct = By.xpath("//html/body/div[14]/div[1]/div/div/select/option[10]");
	By calender_holidy_month_nov = By.xpath("//html/body/div[14]/div[1]/div/div/select/option[11]");
	By calender_holidy_month_dec = By.xpath("//html/body/div[14]/div[1]/div/div/select/option[12]");

	By calender_year_title = By.xpath("//html/body/div[14]/div[1]/div/div/div");

	/*
	 * 
	 * click on selected date //span[@aria-label='November 4, 2023'] /
	 */

	By calender_data_select = By.xpath("//html/body/div[14]/div[2]/div/div[2]/div/span[4]");

	By btn_save_nightly_pricing = By.xpath("//button[text()='Save Nightly Pricing']");

	By btn_popup_yes_overwrite_nightly_pricing = By.xpath("//button[text()='Save Nightly Pricing']");
	By btn_popup_not_overwrite_nightly_pricing = By.xpath("//button[text()='Cancel']");

	By popup_nightly_price_succsfully_save_text = By.xpath("//h2[text()='Success!']");
	By btn_popup_ok_nightly_price_successfully_save = By.xpath("//button[@class='swal2-confirm swal2-styled']");

//	By btn_view_nightly_pricing = By.xpath("//a[@class='btn btn-success btn-md px-4']");

	// *[@id="year-2023"]/div[3]/div/input

	public void click_bucket_btn() {

		action.explicitWaitElementClickable(driver, btn_buckets_web_element,
				Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		btn_buckets_web_element.click();

	}

	public RatesStorageNightlyPricePage add_more_buckets() {

		action.explicitWaitElementClickable(driver, btn_popup_add_more_buckets,
				Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));

		btn_popup_add_more_buckets.click();

		action.explicitWaitPresenceOfElement(driver, bucket_table_4_row,
				Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));

		btn_popup_save_new_buckets.click();

		action.explicitWaitElementClickable(driver, popup_btn_ok_successfully_add_bucket,
				Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		popup_btn_ok_successfully_add_bucket.click();

		return new RatesStorageNightlyPricePage(driver);

	}

	public RatesStorageNightlyPricePage delete_newly_bucket() throws InterruptedException {

		action.explicitWaitElementClickable(driver, btn_popup_add_more_buckets,
				Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));

		action.click1(
				driver.findElement(
						By.xpath("//div[@class='table-responsive']/table[@class='table']/tbody/tr[4]/td[2]/button/i")),
				"bucket 4 delete button");
		Thread.sleep(1000);
		btn_popup_save_new_buckets.click();
		action.explicitWaitElementClickable(driver, popup_btn_ok_successfully_add_bucket,
				Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		popup_btn_ok_successfully_add_bucket.click();

		return new RatesStorageNightlyPricePage(driver);

	}

	public boolean add_12_month_price_in_one_week_row() throws InterruptedException {

		boolean nightly_price_add = false;

		action.explicitWaitElementClickable(driver, btn_add_nightly_month_when_row_enable,
				Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		action.click1(btn_add_nightly_month_when_row_enable, "add month price when row is empty");
		driver.findElement(dropdown_month_to_selection).click();

		Thread.sleep(1000);
		driver.findElement(select_to_dec).click();

		Thread.sleep(1000);
		driver.findElement(input_monday).sendKeys("1");

		Thread.sleep(1000);
		driver.findElement(input_tuesday).sendKeys("2");

		Thread.sleep(1000);
		driver.findElement(input_wednesday).sendKeys("3");

		Thread.sleep(1000);
		driver.findElement(input_thursday).sendKeys("4");

		Thread.sleep(1000);
		driver.findElement(input_friday).sendKeys("5");

		Thread.sleep(1000);
		driver.findElement(input_saturday).sendKeys("6");

		Thread.sleep(1000);
		driver.findElement(input_sunday).sendKeys("7");

		Thread.sleep(1000);
		action.scrollByVisibilityOfElement(driver, driver.findElement(btn_save_nightly_pricing));

		driver.findElement(btn_save_nightly_pricing).click();

		/// get text of popup if update the record..
		Thread.sleep(1000);
		action.explicitWaitElementClickable(driver, popup_btn_ok_successfully_add_bucket,
				Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		String group_adding_message = popup_bucket_add_succssfully_text.getText();
		Thread.sleep(1000);
		popup_btn_ok_successfully_add_bucket.click();

		action.explicitWaitElementClickable(driver, driver.findElement(btn_view_nightly_price),
				Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		Thread.sleep(1000);
		action.scrollByVisibilityOfElement(driver, driver.findElement(btn_view_nightly_price));
		Thread.sleep(1000);
		driver.findElement(btn_view_nightly_price).click();

		if (group_adding_message.equals("Success!")) {

			nightly_price_add = true;

		}

		return nightly_price_add;

	}
	
	
	
	
	
	public boolean add_12_month_price_in_one_week_row_second_bucket() throws InterruptedException {

		boolean nightly_price_add_second_bucket = false;

		action.explicitWaitElementClickable(driver, driver.findElement(btn_delete_nighlty_month_second_bucket),
				Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
//		action.click1(btn_add_nightly_month_when_row_enable, "add month price when row is empty");
//		driver.findElement(dropdown_month_to_selection_second_bucket).click();

		Thread.sleep(1000);
		driver.findElement(select_to_dec_second_bucket).click();

		Thread.sleep(1000);
		driver.findElement(input_monday_second_bucket).sendKeys("8");

		Thread.sleep(1000);
		driver.findElement(input_tuesday_second_bucket).sendKeys("9");

		Thread.sleep(1000);
		driver.findElement(input_wednesday_second_bucket).sendKeys("10");

		Thread.sleep(1000);
		driver.findElement(input_thursday_second_bucket).sendKeys("11");

		Thread.sleep(1000);
		driver.findElement(input_friday_second_bucket).sendKeys("12");

		Thread.sleep(1000);
		driver.findElement(input_saturday_second_bucket).sendKeys("13");

		Thread.sleep(1000);
		driver.findElement(input_sunday_second_bucket).sendKeys("14");

		Thread.sleep(1000);
		action.scrollByVisibilityOfElement(driver, driver.findElement(btn_save_nightly_pricing));

		driver.findElement(btn_save_nightly_pricing).click();

		/// get text of popup if update the record..
		Thread.sleep(1000);
		action.explicitWaitElementClickable(driver, popup_btn_ok_successfully_add_bucket,
				Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		String group_adding_message = popup_bucket_add_succssfully_text.getText();
		Thread.sleep(1000);
		popup_btn_ok_successfully_add_bucket.click();

		action.explicitWaitElementClickable(driver, driver.findElement(btn_view_nightly_price),
				Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		Thread.sleep(1000);
		action.scrollByVisibilityOfElement(driver, driver.findElement(btn_view_nightly_price));
		Thread.sleep(1000);
		
		driver.findElement(btn_view_nightly_price).click();
		
		

		if (group_adding_message.equals("Success!")) {

			nightly_price_add_second_bucket = true;

		}

		return nightly_price_add_second_bucket;

	}
	
	
	
	
	
	public boolean add_12_month_price_in_one_week_row_third_bucket() throws InterruptedException {

		boolean nightly_price_add_third_bucket = false;

		action.explicitWaitElementClickable(driver, driver.findElement(btn_delete_nighlty_month_second_bucket),
				Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
//		action.click1(btn_add_nightly_month_when_row_enable, "add month price when row is empty");
//		driver.findElement(dropdown_month_to_selection_third_bucket).click();

		Thread.sleep(1000);
		driver.findElement(select_to_dec_third_bucket).click();

		Thread.sleep(1000);
		driver.findElement(input_monday_third_bucket).sendKeys("15");

		Thread.sleep(1000);
		driver.findElement(input_tuesday_third_bucket).sendKeys("16");

		Thread.sleep(1000);
		driver.findElement(input_wednesday_third_bucket).sendKeys("17");

		Thread.sleep(1000);
		driver.findElement(input_thursday_third_bucket).sendKeys("18");

		Thread.sleep(1000);
		driver.findElement(input_friday_third_bucket).sendKeys("19");

		Thread.sleep(1000);
		driver.findElement(input_saturday_third_bucket).sendKeys("20");

		Thread.sleep(1000);
		driver.findElement(input_sunday_third_bucket).sendKeys("21");

		Thread.sleep(1000);
		action.scrollByVisibilityOfElement(driver, driver.findElement(btn_save_nightly_pricing));

		driver.findElement(btn_save_nightly_pricing).click();

		/// get text of popup if update the record..
		Thread.sleep(1000);
		action.explicitWaitElementClickable(driver, popup_btn_ok_successfully_add_bucket,
				Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		String group_adding_message = popup_bucket_add_succssfully_text.getText();
		Thread.sleep(1000);
		popup_btn_ok_successfully_add_bucket.click();

		action.explicitWaitElementClickable(driver, driver.findElement(btn_view_nightly_price),
				Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		Thread.sleep(1000);
		action.scrollByVisibilityOfElement(driver, driver.findElement(btn_view_nightly_price));
		Thread.sleep(1000);
		
		driver.findElement(btn_view_nightly_price).click();
		
		

		if (group_adding_message.equals("Success!")) {

			nightly_price_add_third_bucket = true;

		}

		return nightly_price_add_third_bucket;

	}
	
	
	
	
	
	
	public void single_click_first_bucket() {

		action.explicitWaitElementClickable(driver, driver.findElement(btn_add_price),
				Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		
		driver.findElement(tab_1_ft_size).click();
		
		action.explicitWaitElementClickable(driver, driver.findElement(btn_add_price),
				Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
	
		}
	
	
	
	public void single_click_second_bucket() {

		action.explicitWaitElementClickable(driver, driver.findElement(btn_next_year),
				Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		
		driver.findElement(tab_2_ft_size).click();
		
		action.explicitWaitElementClickable(driver, driver.findElement(btn_next_year),
				Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
	
		}
	
	
	
	
	public void single_click_third_bucket() {

		action.explicitWaitElementClickable(driver, driver.findElement(btn_next_year),
				Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		
		driver.findElement(tab_3_ft_size).click();
		
		action.explicitWaitElementClickable(driver, driver.findElement(btn_next_year),
				Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
	
		}
	
	
	
	
		
	
	
	
	

}
