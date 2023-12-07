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

	

	By tab_1_ft_size = By.xpath("//*[@id='buckets_tabs']/li[1]/button/span");
	@FindBy(how = How.XPATH, using = "//*[@id='buckets_tabs']/li[1]/button/span")
	WebElement tab_1_ft_size_webelement;

	By tab_2_ft_size = By.xpath("//*[@id='buckets_tabs']/li[2]/button/span");
	By tab_3_ft_size = By.xpath("//*[@id='buckets_tabs']/li[3]/button/span");
	By tab_4_ft_size = By.xpath("//*[@id='buckets_tabs']/li[4]/button/span");

	By btn_previous_year = By.xpath("//button[@ng-click='prev_year()']");
	By h_active_year = By.xpath("//h4[@ng-bind='active_year']");
	By btn_next_year = By.xpath("//button[@ng-click='next_year()']");

	
	String active_year ="";
	
	By tab_year_2023 = By.xpath("//button[@id='year-2023-tab']");
	By tab_year_2024 = By.xpath("//button[@id='year-2024-tab']");
	By tab_year_2025 = By.xpath("//button[@id='year-2025-tab']");
	By tab_year_2026 = By.xpath("//button[@id='year-2026-tab']");
	
	
	
	
	
	// 2023 ok and check
//	By input_monday = By.xpath("//*[@id='bucket-"+active_year+"-81']/div/div[1]/div/table/tbody/tr/td[2]/input");
//	By input_tuesday = By.xpath("//*[@id='bucket-"+active_year+"-81']/div/div[1]/div/table/tbody/tr/td[3]/input");
//	By input_wednesday = By.xpath("//*[@id='bucket-"+active_year+"-81']/div/div[1]/div/table/tbody/tr/td[4]/input");
//	By input_thursday = By.xpath("//*[@id='bucket-"+active_year+"-81']/div/div[1]/div/table/tbody/tr/td[5]/input");
//	By input_friday = By.xpath("//*[@id='bucket-"+active_year+"-81']/div/div[1]/div/table/tbody/tr/td[6]/input");
//	By input_saturday = By.xpath("//*[@id='bucket-"+active_year+"-81']/div/div[1]/div/table/tbody/tr/td[7]/input");
//	By input_sunday = By.xpath("//*[@id='bucket-"+active_year+"-81']/div/div[1]/div/table/tbody/tr/td[8]/input");

//	By input_monday_second_bucket = By.xpath("//*[@id='bucket-"+active_year+"-82']/div/div[1]/div/table/tbody/tr/td[2]/input");
//	By input_tuesday_second_bucket = By.xpath("//*[@id='bucket-"+active_year+"-82']/div/div[1]/div/table/tbody/tr/td[3]/input");
//	By input_wednesday_second_bucket = By.xpath("//*[@id='bucket-"+active_year+"-82']/div/div[1]/div/table/tbody/tr/td[4]/input");
//	By input_thursday_second_bucket = By.xpath("//*[@id='bucket-"+active_year+"-82']/div/div[1]/div/table/tbody/tr/td[5]/input");
//	By input_friday_second_bucket = By.xpath("//*[@id='bucket-"+active_year+"-82']/div/div[1]/div/table/tbody/tr/td[6]/input");
//	By input_saturday_second_bucket = By.xpath("//*[@id='bucket-"+active_year+"-82']/div/div[1]/div/table/tbody/tr/td[7]/input");
//	By input_sunday_second_bucket = By.xpath("//*[@id='bucket-"+active_year+"-82']/div/div[1]/div/table/tbody/tr/td[8]/input");

//	By input_monday_third_bucket = By.xpath("//*[@id='bucket-"+active_year+"-99']/div/div[1]/div/table/tbody/tr/td[2]/input");
//	By input_tuesday_third_bucket = By.xpath("//*[@id='bucket-"+active_year+"-99']/div/div[1]/div/table/tbody/tr/td[3]/input");
//	By input_wednesday_third_bucket = By.xpath("//*[@id='bucket-"+active_year+"-99']/div/div[1]/div/table/tbody/tr/td[4]/input");
//	By input_thursday_third_bucket = By.xpath("//*[@id='bucket-"+active_year+"-99']/div/div[1]/div/table/tbody/tr/td[5]/input");
//	By input_friday_third_bucket = By.xpath("//*[@id='bucket-"+active_year+"-99']/div/div[1]/div/table/tbody/tr/td[6]/input");
//	By input_saturday_third_bucket = By.xpath("//*[@id='bucket-"+active_year+"-99']/div/div[1]/div/table/tbody/tr/td[7]/input");
//	By input_sunday_third_bucket = By.xpath("//*[@id='bucket-"+active_year+"-99']/div/div[1]/div/table/tbody/tr/td[8]/input");

	// 2023
//	By btn_delete_nighlty_month = By
//			.xpath("//*[@id='bucket-"+active_year+"-81']/div/div[1]/div/table/tbody/tr/td[9]/div/button[1]");

//	By btn_delete_nighlty_month_second_bucket = By
//			.xpath("//*[@id='bucket-"+active_year+"-82']/div/div[1]/div/table/tbody/tr/td[9]/div/button[1]");

//	By btn_delete_nighlty_month_third_bucket = By
//			.xpath("//*[@id='bucket-"+active_year+"-99']/div/div[1]/div/table/tbody/tr/td[9]/div/button[1]");

	// By btn_add_nightly_month_when_row_enable =
	// By.xpath("//*[@id='bucket-"+active_year+"-81']/div/div[1]/div/table/tbody/tr/td[9]/div/button[2]");

//	@FindBy(how = How.XPATH, using = "//*[@id='bucket-"+active_year+"-81']/div/div[1]/div/table/thead/tr/th[9]/button")
//	WebElement btn_add_nightly_month_when_row_enable;

	
//	By by_btn_add_nightly_month_when_row_enable = By.xpath("//*[@id='bucket-"+active_year+"-81']/div/div[1]/div/table/thead/tr/th[9]/button");
	
	
	
//	By btn_add_nightly_month_when_row_disable = By.xpath("");

	// 2023
	By h2_yearly_holiday_tittle = By.xpath("//*[@id='bucket-"+active_year+"-81']/div/div[2]/h2");
	

	// use for holidays
	
	
//	By input_holiday_name = By.xpath("//*[@id='bucket-"+active_year+"-81']/div/div[2]/table/tbody/tr/td[1]/input");
//	By input_holiday_date = By.xpath("//*[@id='bucket-"+active_year+"-81']/div/div[2]/table/tbody/tr/td[2]/input");
//	By input_holiday_price = By.xpath("//*[@id='bucket-"+active_year+"-81']/div/div[2]/table/tbody/tr/td[3]/input");
//	By input_holiday_minimum_reservation = By
//			.xpath("//*[@id='bucket-"+active_year+"-81']/div/div[2]/table/tbody/tr/td[4]/div/input");

//	
//	By btn_delete_Holidays = By.xpath("//*[@id='bucket-"+active_year+"-81']/div/div[2]/table/tbody/tr/td[5]/div/button[1]");
//	By btn_holiday_month_add = By.xpath("//*[@id='bucket-"+active_year+"-81']/div/div[2]/table/tbody/tr/td[5]/div/button[2]");
//	By select_effective_holiday_date = By.xpath("//*[@id='year-2023']/div[3]/div/input");


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


	By calender_data_select = By.xpath("//html/body/div[14]/div[2]/div/div[2]/div/span[4]");

	By btn_save_nightly_pricing = By.xpath("//button[text()='Save Nightly Pricing']");

	@FindBy(how = How.XPATH, using = "//button[@class='swal2-confirm swal2-styled swal2-default-outline' and text()='Yes, overwrite']")
	WebElement btn_popup_yes_overwrite_nightly_pricing;
//	By btn_popup_yes_overwrite_nightly_pricing = By.xpath("//button[text()='Save Nightly Pricing']");

	@FindBy(how = How.XPATH, using = "//button[text()='Cancel']")
	WebElement btn_popup_not_overwrite_nightly_pricing;
//	By btn_popup_not_overwrite_nightly_pricing = By.xpath("//button[text()='Cancel']");

	@FindBy(how = How.XPATH, using = "//h2[text()='Are you sure?']")
	WebElement h2_popup_overwrite_nightly_pricing;

	By popup_nightly_price_succsfully_save_text = By.xpath("//h2[text()='Success!']");
	By btn_popup_ok_nightly_price_successfully_save = By.xpath("//button[@class='swal2-confirm swal2-styled']");



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

	public boolean add_12_month_price_in_one_week_row_first_bucket(String active_year2) throws InterruptedException {

		boolean nightly_price_add = false;
		active_year=active_year2;
		String bucket_1 ="81";

		try {
		action.explicitWaitElementClickable(driver, driver.findElement(By.xpath("//*[@id='bucket-"+active_year+"-"+bucket_1+"']/div/div[1]/div/table/thead/tr/th[9]/button")),
				Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
	
		action.click1(driver.findElement(By.xpath("//*[@id='bucket-"+active_year+"-"+bucket_1+"']/div/div[1]/div/table/thead/tr/th[9]/button")), "add month price when row is empty");
		
		
		}
		
		catch(Exception e) {
			System.out.println("in bucket already price define");
		}
		System.out.println("tes");

		WebElement select_month_to_scroll_down = driver.findElement(By.xpath("//*[@id='bucket-"+active_year+"-"+bucket_1+"']/div/div[1]/div/table/tbody/tr/td[1]/div/div[2]/select"));
		select_month_to_scroll_down.click();
		
		WebElement select_dec_month = driver.findElement(By.xpath("//*[@id='bucket-"+active_year+"-81']/div/div[1]/div/table/tbody/tr/td[1]/div/div[2]/select/option[@value='number:12']"));
		select_dec_month.click();
		
	
		
		Thread.sleep(1000);
		WebElement input_monday = driver.findElement(By.xpath("//*[@id='bucket-"+active_year+"-"+bucket_1+"']/div/div[1]/div/table/tbody/tr/td[2]/input"));
		input_monday.clear();
		input_monday.sendKeys("1");

		WebElement input_tuesday = driver.findElement(By.xpath("//*[@id='bucket-"+active_year+"-"+bucket_1+"']/div/div[1]/div/table/tbody/tr/td[3]/input"));
		input_tuesday.clear();
		input_tuesday.sendKeys("2");
		
		WebElement input_wednesday = driver.findElement(By.xpath("//*[@id='bucket-"+active_year+"-"+bucket_1+"']/div/div[1]/div/table/tbody/tr/td[4]/input"));
		input_wednesday.clear();
		input_wednesday.sendKeys("3");
		
		WebElement input_thursday = driver.findElement(By.xpath("//*[@id='bucket-"+active_year+"-"+bucket_1+"']/div/div[1]/div/table/tbody/tr/td[5]/input"));
		input_thursday.clear();
		input_thursday.sendKeys("4");
		
		
		WebElement input_friday = driver.findElement(By.xpath("//*[@id='bucket-"+active_year+"-"+bucket_1+"']/div/div[1]/div/table/tbody/tr/td[6]/input"));
		input_friday.clear();
		input_friday.sendKeys("5");

		
		WebElement input_saturday = driver.findElement(By.xpath("//*[@id='bucket-"+active_year+"-"+bucket_1+"']/div/div[1]/div/table/tbody/tr/td[7]/input"));
		input_saturday.clear();
		input_saturday.sendKeys("6");


		
		WebElement input_sunday = driver.findElement(By.xpath("//*[@id='bucket-"+active_year+"-"+bucket_1+"']/div/div[1]/div/table/tbody/tr/td[8]/input"));
		input_sunday.clear();
		input_sunday.sendKeys("7");


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
	
	

	public boolean add_12_month_price_in_one_week_row_second_bucket(String active_year2) throws InterruptedException {

		boolean nightly_price_add_second_bucket = false;
		active_year=active_year2;
		String bucket_2 ="82";
		
		
		WebElement btn_delete_nighlty_month_second_bucket = driver.findElement(By.xpath("//*[@id='bucket-"+active_year+"-"+bucket_2+"']/div/div[1]/div/table/tbody/tr/td[9]/div/button[1]"));
		
		action.explicitWaitElementClickable(driver, btn_delete_nighlty_month_second_bucket,
				Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
//		action.click1(btn_add_nightly_month_when_row_enable, "add month price when row is empty");
//		driver.findElement(dropdown_month_to_selection_second_bucket).click();

		
		WebElement select_month_to_scroll_down = driver.findElement(By.xpath("//*[@id='bucket-"+active_year+"-"+bucket_2+"']/div/div[1]/div/table/tbody/tr/td[1]/div/div[2]/select"));
		select_month_to_scroll_down.click();
		
		WebElement select_to_dec_second_bucket = driver.findElement(By.xpath("//*[@id='bucket-"+active_year+"-"+bucket_2+"']/div/div[1]/div/table/tbody/tr/td[1]/div/div[2]/select/option[@value='number:12']"));
		Thread.sleep(1000);
		select_to_dec_second_bucket.click();
		
		
		
		

		Thread.sleep(1000);
		WebElement input_monday = driver.findElement(By.xpath("//*[@id='bucket-"+active_year+"-"+bucket_2+"']/div/div[1]/div/table/tbody/tr/td[2]/input"));
		input_monday.clear();
		input_monday.sendKeys("8");

		WebElement input_tuesday = driver.findElement(By.xpath("//*[@id='bucket-"+active_year+"-"+bucket_2+"']/div/div[1]/div/table/tbody/tr/td[3]/input"));
		input_tuesday.clear();
		input_tuesday.sendKeys("9");
		
		WebElement input_wednesday = driver.findElement(By.xpath("//*[@id='bucket-"+active_year+"-"+bucket_2+"']/div/div[1]/div/table/tbody/tr/td[4]/input"));
		input_wednesday.clear();
		input_wednesday.sendKeys("10");
		
		WebElement input_thursday = driver.findElement(By.xpath("//*[@id='bucket-"+active_year+"-"+bucket_2+"']/div/div[1]/div/table/tbody/tr/td[5]/input"));
		input_thursday.clear();
		input_thursday.sendKeys("11");
		
		
		WebElement input_friday = driver.findElement(By.xpath("//*[@id='bucket-"+active_year+"-"+bucket_2+"']/div/div[1]/div/table/tbody/tr/td[6]/input"));
		input_friday.clear();
		input_friday.sendKeys("12");

		
		WebElement input_saturday = driver.findElement(By.xpath("//*[@id='bucket-"+active_year+"-"+bucket_2+"']/div/div[1]/div/table/tbody/tr/td[7]/input"));
		input_saturday.clear();
		input_saturday.sendKeys("13");


		
		WebElement input_sunday = driver.findElement(By.xpath("//*[@id='bucket-"+active_year+"-"+bucket_2+"']/div/div[1]/div/table/tbody/tr/td[8]/input"));
		input_sunday.clear();
		input_sunday.sendKeys("14");
		
		

		Thread.sleep(1000);
		action.scrollByVisibilityOfElement(driver, driver.findElement(btn_save_nightly_pricing));

		driver.findElement(btn_save_nightly_pricing).click();

		/// get text of popup if update the record..

		Thread.sleep(1000);

		action.explicitWaitElementClickable(driver, btn_popup_yes_overwrite_nightly_pricing,
				Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));

		btn_popup_yes_overwrite_nightly_pricing.click();

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

	public boolean add_12_month_price_in_one_week_row_third_bucket(String active_year2) throws InterruptedException {

		boolean nightly_price_add_third_bucket = false;
		active_year=active_year2;
		String bucket_3 ="99";

		
		WebElement btn_delete_nighlty_month_third_bucket = driver.findElement(By.xpath("//*[@id='bucket-"+active_year+"-"+bucket_3+"']/div/div[1]/div/table/tbody/tr/td[9]/div/button[1]"));
		
		
		
		action.explicitWaitElementClickable(driver, btn_delete_nighlty_month_third_bucket,
				Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
//		action.click1(btn_add_nightly_month_when_row_enable, "add month price when row is empty");
//		driver.findElement(dropdown_month_to_selection_third_bucket).click();

		

		WebElement select_month_to_scroll_down = driver.findElement(By.xpath("//*[@id='bucket-"+active_year+"-"+bucket_3+"']/div/div[1]/div/table/tbody/tr/td[1]/div/div[2]/select"));
		select_month_to_scroll_down.click();
		
		WebElement select_to_dec_third_bucket = driver.findElement(By.xpath("//*[@id='bucket-"+active_year+"-"+bucket_3+"']/div/div[1]/div/table/tbody/tr/td[1]/div/div[2]/select/option[@value='number:12']"));
		Thread.sleep(1000);
		select_to_dec_third_bucket.click();
		
		
		
		
		
		Thread.sleep(1000);
		WebElement input_monday = driver.findElement(By.xpath("//*[@id='bucket-"+active_year+"-"+bucket_3+"']/div/div[1]/div/table/tbody/tr/td[2]/input"));
		input_monday.clear();
		input_monday.sendKeys("15");

		WebElement input_tuesday = driver.findElement(By.xpath("//*[@id='bucket-"+active_year+"-"+bucket_3+"']/div/div[1]/div/table/tbody/tr/td[3]/input"));
		input_tuesday.clear();
		input_tuesday.sendKeys("16");
		
		WebElement input_wednesday = driver.findElement(By.xpath("//*[@id='bucket-"+active_year+"-"+bucket_3+"']/div/div[1]/div/table/tbody/tr/td[4]/input"));
		input_wednesday.clear();
		input_wednesday.sendKeys("17");
		
		WebElement input_thursday = driver.findElement(By.xpath("//*[@id='bucket-"+active_year+"-"+bucket_3+"']/div/div[1]/div/table/tbody/tr/td[5]/input"));
		input_thursday.clear();
		input_thursday.sendKeys("18");
		
		
		WebElement input_friday = driver.findElement(By.xpath("//*[@id='bucket-"+active_year+"-"+bucket_3+"']/div/div[1]/div/table/tbody/tr/td[6]/input"));
		input_friday.clear();
		input_friday.sendKeys("19");

		
		WebElement input_saturday = driver.findElement(By.xpath("//*[@id='bucket-"+active_year+"-"+bucket_3+"']/div/div[1]/div/table/tbody/tr/td[7]/input"));
		input_saturday.clear();
		input_saturday.sendKeys("20");


		
		WebElement input_sunday = driver.findElement(By.xpath("//*[@id='bucket-"+active_year+"-"+bucket_3+"']/div/div[1]/div/table/tbody/tr/td[8]/input"));
		input_sunday.clear();
		input_sunday.sendKeys("21");
		
		
		

		Thread.sleep(1000);
		action.scrollByVisibilityOfElement(driver, driver.findElement(btn_save_nightly_pricing));

		driver.findElement(btn_save_nightly_pricing).click();

		/// get text of popup if update the record..

		Thread.sleep(1000);

		action.explicitWaitElementClickable(driver, btn_popup_yes_overwrite_nightly_pricing,
				Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));

		btn_popup_yes_overwrite_nightly_pricing.click();

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

	public void single_click_first_bucket(String active_year) {

		
		
		action.explicitWaitElementClickable(driver, driver.findElement(btn_next_year),
				Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));

		driver.findElement(tab_1_ft_size).click();

		WebElement dropdown_month_selection_first_bucket = driver.findElement(By.xpath(
				"//*[@id='bucket-" + active_year + "-81']/div/div[1]/div/table/tbody/tr/td[1]/div/div[1]/select"));

		action.explicitWaitElementClickable(driver, dropdown_month_selection_first_bucket,
				Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));

	}

	public void single_click_second_bucket_update(String active_year) {
		
		
		WebElement dropdown_month_selection_first_bucket = driver.findElement(By.xpath(
				"//*[@id='bucket-"+active_year+"-81']/div/div[1]/div/table/tbody/tr/td[1]/div/div[1]/select"));

		action.explicitWaitElementClickable(driver, dropdown_month_selection_first_bucket,
				Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));

		driver.findElement(tab_2_ft_size).click();

		WebElement dropdown_from_month_selection_second_bucket = driver.findElement(By.xpath(
				"//*[@id='bucket-"+active_year+"-82']/div/div[1]/div/table/tbody/tr/td[1]/div/div[1]/select"));

		action.explicitWaitElementClickable(driver, dropdown_from_month_selection_second_bucket,
				Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));

	}

	public void single_click_third_bucket_update (String active_year) {

		WebElement dropdown_month_selection_first_bucket = driver.findElement(By.xpath(
				"//*[@id='bucket-" + active_year + "-81']/div/div[1]/div/table/tbody/tr/td[1]/div/div[1]/select"));

		action.explicitWaitElementClickable(driver, dropdown_month_selection_first_bucket,
				Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));

		driver.findElement(tab_3_ft_size).click();

		WebElement dropdown_month_selection_third_bucket = driver.findElement(By.xpath(
				"//*[@id='bucket-" + active_year + "-99']/div/div[1]/div/table/tbody/tr/td[1]/div/div[1]/select"));
		action.explicitWaitElementClickable(driver, dropdown_month_selection_third_bucket,
				Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));

		action.explicitWaitElementClickable(driver, dropdown_month_selection_third_bucket,
				Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));

	}
	
	
	
public void add_mutli_months_price_1st_bucket(String active_year2,String bucket, String [] price_list_first_row_month, String [] price_list_2nd_row_month, 
		String holiday_price_set, String holiday_minimum_reservation) throws InterruptedException {
	

	
	boolean nightly_price_add_third_bucket = false;
	active_year=active_year2;
	String bucket_all =bucket;
	String input_values;
	

	
	WebElement btn_delete_nighlty_month_third_bucket = driver.findElement(By.xpath("//*[@id='bucket-"+active_year+"-"+bucket_all+"']/div/div[1]/div/table/tbody/tr/td[9]/div/button[1]"));
		
	action.explicitWaitElementClickable(driver, btn_delete_nighlty_month_third_bucket,
			Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
//	action.click1(btn_add_nightly_month_when_row_enable, "add month price when row is empty");
//	driver.findElement(dropdown_month_to_selection_third_bucket).click();

	WebElement select_month_to_scroll_down = driver.findElement(By.xpath("//*[@id='bucket-"+active_year+"-"+bucket_all+"']/div/div[1]/div/table/tbody/tr[1]/td[1]/div/div[2]/select"));
	action.scrollByVisibilityOfElement(driver, select_month_to_scroll_down);
	select_month_to_scroll_down.click();
	
	WebElement select_to_aug = driver.findElement(By.xpath("//*[@id='bucket-"+active_year+"-"+bucket_all+"']/div/div[1]/div/table/tbody/tr[1]/td[1]/div/div[2]/select/option[@value='number:8']"));
	Thread.sleep(1000);
	select_to_aug.click();
	
	WebElement btn_add_more_month = driver.findElement(By.xpath("//*[@id='bucket-"+active_year+"-"+bucket_all+"']/div/div[1]/div/table/tbody/tr/td[9]/div/button[2]"));
	Thread.sleep(1000);
	
	
	
	WebElement select_month_2nd_row_to_scroll_down = driver.findElement(By.xpath("//*[@id='bucket-"+active_year+"-"+bucket_all+"']/div/div[1]/div/table/tbody/tr[2]/td[1]/div/div[2]/select"));
	action.scrollByVisibilityOfElement(driver, select_month_to_scroll_down);
	select_month_to_scroll_down.click();
	
	
	
	WebElement select_to_dec = driver.findElement(By.xpath("//*[@id='bucket-"+active_year+"-"+bucket_all+"']/div/div[1]/div/table/tbody/tr[2]/td[1]/div/div[2]/select/option[@value='number:12']"));
	action.scrollByVisibilityOfElement(driver, select_to_dec);
	select_to_dec.click();
	
	
	
	int i,j;
		
	for(i=1; i<3;i++) {
		for (j=1; j<8; j++) {
		Thread.sleep(1000);
			WebElement input_monday = driver.findElement(By.xpath("//*[@id='bucket-"+active_year+"-"+bucket_all+"']/div/div[1]/div/table/tbody/tr[i]/td[j]/input"));
			input_monday.clear();
			
			if(j==1) {
			input_monday.sendKeys(price_list_first_row_month[j]);
			}
			
			if(j==2) {
				input_monday.sendKeys(price_list_2nd_row_month);
				
			
			}
			
	
			
		}
			
			
	}
	

	
	String holidays_row = null;
	
	WebElement holiday_name_field = driver.findElement(By.xpath("//*[@id='bucket-"+active_year+"-"+bucket_all+"']/div/div[2]/table/tbody/tr/td[1]/input"));
	WebElement holiday_date_selection = driver.findElement(By.xpath("//*[@id='bucket-"+active_year+"-"+bucket_all+"']/div/div[2]/table/tbody/tr/td[2]/input"));
//	WebElement holiday_year_selection = driver.findElement(By.xpath("");
	WebElement holiday_date = driver.findElement(By.xpath("/html/body/div[16]/div[2]/div/div[2]/div/span[35]"));
	WebElement holiday_price = driver.findElement(By.xpath("//*[@id='bucket-"+active_year+"-"+bucket_all+"']/div/div[2]/table/tbody/tr/td[3]/input"));
	WebElement holiday_minimum_reservations  = driver.findElement(By.xpath("//*[@id='bucket-"+active_year+"-"+bucket_all+"']/div/div[2]/table/tbody/tr/td[4]/div/input"));

	
	
	
	try {
		
		driver.findElement(By.xpath("//*[@id='bucket-2023-81']/div/div[2]/table/thead/tr/th[5]/button[2]"));
	
	holidays_row= "found";

	}
	
	catch (Exception e) {

		System.out.println("holidays entries available");
		
		holidays_row = "not found";
		
	}
	
	
	
	if(holidays_row.equals("not found")) {
		
	WebElement btn_holiday_add_selection =	driver.findElement(By.xpath("//*[@id='bucket-"+active_year+"-"+bucket_all+"']/div/div[2]/table/thead/tr/th[5]/button"));	
	action.scrollByVisibilityOfElement(driver, btn_holiday_add_selection);
	btn_holiday_add_selection.click();
	String date_time = action.getCurrentTime();
	
	holiday_name_field.sendKeys("automation holiday "+date_time);
	
	action.explicitWaitElementClickable(driver, holiday_date_selection, Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
	holiday_date_selection.click();
	
	action.explicitWaitElementClickable(driver, holiday_date, Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
	holiday_date.click();
	Thread.sleep(1000);
	holiday_price.click();
	holiday_price.clear();
	holiday_price.sendKeys(holiday_price_set);
	Thread.sleep(1000);
	
	
	
	}
	
	else if(holidays_row.equals("found")) {
		
		holiday_price.click();
		holiday_price.clear();
		holiday_price.sendKeys(holiday_price_set);
		Thread.sleep(1000);
		
	}
	
	
	else if(holidays_row==null || holidays_row=="") {
		System.out.println("holiday not insert");
			
		return;
		
	}
	

	Thread.sleep(1000);
	action.scrollByVisibilityOfElement(driver, driver.findElement(btn_save_nightly_pricing));
	driver.findElement(btn_save_nightly_pricing).click();
	
	
	
	
	
	 
	
	
	
	


	/// get text of popup if update the record..

	Thread.sleep(1000);

	action.explicitWaitElementClickable(driver, btn_popup_yes_overwrite_nightly_pricing,
			Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));

	btn_popup_yes_overwrite_nightly_pricing.click();

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

//	return nightly_price_add_third_bucket;
	
	
	
	
	
	
	

	
	
	
	
	
}
	
	
	
	

}
