package com.marina.pages;

import java.lang.reflect.Array;
import java.time.Duration;
import java.time.Year;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.marina.actiondriver.Action;
import com.marina.base.TestBase;

public class RatesStorageNightlyPricePage {

	WebDriver driver;
	Action action = new Action();

	public RatesStorageNightlyPricePage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	By tab_btn_nightly = By.xpath("//a[text()='Nightly']");

	By tab_btn_monthly = By.xpath("//a[text()='Monthly']");

	@FindBy(how = How.XPATH, using = "//a[text()='Monthly']")
	WebElement month_btn;

	By tab_btn_annual = By.xpath("//a[text()='Annual']");
	By tab_btn_fixed = By.xpath("//a[text()='Flat']");

	By rates_nightly_price_tittle = By
			.xpath("//h1[@class='mb-0 text-white page-nav fs_22 fw_6 d-flex align-items-center']");

	By btn_update_price = By.xpath("//a[@class='btn btn-success btn-md px-4']");

	@FindBy(how = How.XPATH, using = "//a[@class='btn btn-success btn-md px-4']")
	WebElement btn_update_price_web;

	By btn_add_price = By.xpath("//a[@class='btn btn-success btn-md px-4']");

	By tab_space_pricing = By.xpath("//button[text()='Space Pricing']");
	By tab_power_pricing = By.xpath("//button[text()='Power Pricing']");

	By tab_year_2023 = By.xpath("//button[@id='year-2023-tab']");
	By tab_year_2024 = By.xpath("//button[@id='year-2024-tab']");
	By tab_year_2025 = By.xpath("//button[@id='year-2025-tab']");

	By tab_1_ft_size = By.xpath("//*[@id='buckets_tabs']/li[1]/button/span");
	By tab_2_ft_size = By.xpath("//*[@id='buckets_tabs']/li[2]/button/span");
	By tab_3_ft_size = By.xpath("//*[@id='buckets_tabs']/li[3]/button/span");
	By tab_4_ft_size = By.xpath("//*[@id='buckets_tabs']/li[4]/button/span");

	By revision_history = By.xpath("//*[@id='bucket']/div/div/div/div[2]/div/div/label");
	By dropdown_revision_history = By.xpath("//select[@ng-change='bucket_change(null, true, effective_date)']");
	By dropdown_power_pricing_revision_history = By.xpath("//select[@ng-change='date_change()']");

	By btn_previous_year = By.xpath("//button[@ng-click='prev_year()']");
	By h_active_year = By.xpath("//h4[@ng-bind='active_year']");
	By btn_next_year = By.xpath("//button[@ng-click='next_year()']");

	// calendar years tab , month text in camel case

	By tab_calendar_month_december = By.xpath("//button[text()='December']");

	int month_number;
	int day_number;
	int date_column;
	By tab_days = By.xpath(
			"//*[@id='month-" + month_number + "']/div/div/table/tbody/tr[" + date_column + "]/td[" + day_number + "]");

	ArrayList<String> history_recrod_array;

	String[] calender_table_record;

	public boolean[] newly_group_nightly_rivision_history() {

		boolean[] booleans = new boolean[2];
		booleans[0] = false;
		booleans[1] = false;

		action.explicitWaitPresenceOfElement(driver, dropdown_revision_history,
				Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));

		action.click1(driver.findElement(dropdown_revision_history), "testing");

		history_recrod_array = new ArrayList<String>();

		System.out.println();

		int i = 2;

		By revision_history = By
				.xpath("//select[@ng-change='bucket_change(null, true, effective_date)']/option[" + i + "]");

		String list_revision_record;

		try {

			list_revision_record = driver.findElement(revision_history).getText();

		}

		catch (Exception e) {
			System.out.println("no text found");

			booleans[0] = true;

		}

		driver.findElement(tab_power_pricing).click();
		action.explicitWaitPresenceOfElement(driver, dropdown_power_pricing_revision_history,
				Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));

		action.click1(driver.findElement(dropdown_power_pricing_revision_history), "testing");

		try {

			list_revision_record = driver.findElement(revision_history).getText();

		}

		catch (Exception e) {
			System.out.println("no text found");
			booleans[1] = true;

		}

		System.out.println("testing");

		return booleans;

	}

	public RatesStorageMonthlyPricePage single_click_monthly_price() {

		action.explicitWaitElementClickable(driver, month_btn,
				Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		month_btn.click();

		return new RatesStorageMonthlyPricePage(driver);

	}

	public void single_click_first_bucket() {

		action.explicitWaitElementClickable(driver, driver.findElement(btn_next_year),
				Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));

		driver.findElement(tab_1_ft_size).click();

		action.explicitWaitElementClickable(driver, driver.findElement(btn_next_year),
				Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));

	}

	public void single_click_second_bucket() throws InterruptedException {

		action.explicitWaitElementClickable(driver, driver.findElement(btn_next_year),
				Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));

		String testing_locatotrs = driver.findElement(tab_2_ft_size).getAccessibleName();
		
		action.scrollByVisibilityOfElement(driver, driver.findElement(btn_add_price));
		action.scrollByVisibilityOfElement(driver, driver.findElement(tab_2_ft_size));
		
		driver.findElement(tab_2_ft_size).click();

		action.explicitWaitElementClickable(driver, driver.findElement(btn_next_year),
				Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));

	}

	public void single_click_third_bucket() throws InterruptedException {

		action.explicitWaitElementClickable(driver, driver.findElement(btn_next_year),
				Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));

	
		action.scrollByVisibilityOfElement(driver, driver.findElement(tab_3_ft_size));
		
		driver.findElement(tab_3_ft_size).click();

		action.explicitWaitElementClickable(driver, driver.findElement(btn_next_year),
				Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));

	}

	public RatesStorageNightlyPrice_add_updatePage click_update_btn_return_edit_page() throws InterruptedException {
		
		action.scrollByVisibilityOfElement(driver, driver.findElement(btn_next_year));
		action.explicitWaitElementClickable(driver, driver.findElement(btn_next_year),
				Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		action.scrollByVisibilityOfElement(driver, btn_update_price_web);

		btn_update_price_web.click();

		return new RatesStorageNightlyPrice_add_updatePage(driver);

	}

	public boolean fouth_buckets_check() {

		boolean fourth_bucket = false;

		action.explicitWaitElementClickable(driver, driver.findElement(tab_4_ft_size),
				Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		String newly_bucket_fts = driver.findElement(tab_4_ft_size).getText();

		if (newly_bucket_fts.equals(newly_bucket_fts)) {

			fourth_bucket = true;
		}

		return fourth_bucket;

	}

	public boolean after_delete_fouthBucket_verify_delete() {

		boolean fourth_bucket = false;
		String newly_bucket_fts;

		try {
			action.explicitWaitElementClickable(driver, driver.findElement(tab_4_ft_size),
					Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
			newly_bucket_fts = driver.findElement(tab_4_ft_size).getText();

		} catch (Exception e) {
			System.out.println("4th bucket option not found");
			fourth_bucket = true;

		}

		return fourth_bucket;

	}

	public String[] active_year() {

		action.explicitWaitPresenceOfElement(driver, h_active_year,
				Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		String active_year_calendar = driver.findElement(h_active_year).getText();
		String currentTime = action.getCurrentTime();

		String[] activeYear_currentTime = { active_year_calendar, currentTime };
		return activeYear_currentTime;

	}

	public String[] nightly_2024_december_calendar_selection() throws InterruptedException {

		action.explicitWaitPresenceOfElement(driver, h_active_year,
				Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));

		String active_year_calendar = driver.findElement(h_active_year).getText();

		boolean compete_recod_confirm = false;

		if (active_year_calendar.contentEquals("2023")) {

			action.explicitWaitElementClickable(driver, driver.findElement(btn_previous_year),
					Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
			driver.findElement(btn_next_year).click();
			action.explicitWaitElementClickable(driver, driver.findElement(btn_previous_year),
					Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
			active_year_calendar = driver.findElement(h_active_year).getText();

		}
	
		else if (active_year_calendar.contentEquals("2025")) {

			action.explicitWaitElementClickable(driver, driver.findElement(btn_previous_year),
					Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
			String[] error = { "need to update scipt" };
			return error;

		}
		
		System.out.println("data");

		if (active_year_calendar.equals("2024")) {

			action.click(driver, driver.findElement(tab_calendar_month_december));
			action.explicitWaitElementClickable(driver, driver.findElement(btn_add_price),
					Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
			Thread.sleep(6000);

			calender_table_record = new String[35];
			int i;
			int j;
			int k = 0;
			for (i = 2; i < 7; i++) {

				for (j = 1; j < 8; j++) {

					if (k > 3) {

						WebElement days_date_price = driver.findElement(By
								.xpath("//*[@id='month-12']/div/div/table/tbody/tr[" + i + "]/td[" + j + "]/span[2]"));

						action.explicitWaitVisibility(driver, days_date_price,
								(By.xpath(
										"//*[@id='month-12']/div/div/table/tbody/tr[" + i + "]/td[" + j + "]/span[2]")),
								Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));

						calender_table_record[k] = days_date_price.getText();

						System.out.println(k + " -  " + calender_table_record[k]);

					}

					k++;
					if (k == 34) {
						compete_recod_confirm = true;

					}

				}

			}

		}

		if (compete_recod_confirm == false) {
			System.out.println("check calendar record,");

		}

		return calender_table_record;

	}

	public String[][][] calendar_create(int request_year_user, String request_month_user) throws InterruptedException {

		/*
		 * 
		 * request month user formate month number "10" /request year formate 2023
		 */

		
		
		
		String[][] calendar_month_dates = new String[6][7];

		int request_year = request_year_user;
		String request_month = request_month_user;
		String calendar_array[][][] = null;

		String days_text = "empty";

		String current_year_active = driver.findElement(h_active_year).getText();
		current_year_active = current_year_active.trim();
		int int_current_year_active = 0;

		try {
			int_current_year_active = Integer.parseInt(current_year_active);

		} catch (NumberFormatException n) {

			System.out.println("calendar active year not found");
		}

		if (request_year != int_current_year_active) {

			if (request_year < int_current_year_active) {

				for (int i = 1; i < 100; i++) {

					Thread.sleep(1000);
					action.explicitWaitElementClickable(driver, driver.findElement(btn_previous_year),
							Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
					Thread.sleep(1000);
					driver.findElement(btn_previous_year).click();
					Thread.sleep(1000);

					action.explicitWaitElementClickable(driver, driver.findElement(btn_previous_year),
							Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
					Thread.sleep(1000);

					current_year_active = driver.findElement(h_active_year).getText();
					current_year_active = current_year_active.trim();
					int_current_year_active = Integer.parseInt(current_year_active);

					if (request_year == int_current_year_active) {
						i=100;
					}

				}

			}

			else if (request_year > int_current_year_active) {

				for (int i = 1; i < 100; i++) {

					Thread.sleep(1000);
					action.explicitWaitElementClickable(driver, driver.findElement(btn_next_year),
							Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
					Thread.sleep(1000);
					driver.findElement(btn_next_year).click();
					Thread.sleep(1000);

					action.explicitWaitElementClickable(driver, driver.findElement(btn_previous_year),
							Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
					Thread.sleep(1000);

					current_year_active = driver.findElement(h_active_year).getText();
					current_year_active = current_year_active.trim();
					int_current_year_active = Integer.parseInt(current_year_active);

					if (request_year == int_current_year_active) {
						i=100;
					}

				}

			}

		}

		
		 System.out.println("calendar result fetching");

			WebElement required_month_click = driver
					.findElement(By.xpath("//*[@id='month-" + request_month + "-tab']"));
			action.explicitWaitElementClickable(driver, driver.findElement(btn_next_year),
					Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
			action.scrollByVisibilityOfElement(driver, required_month_click);
			required_month_click.click();

			int z = 0;

			for (int i = 2; i < 8; i++) {

				String text_record_dates;

				try {

					WebElement record_dates = driver.findElement(
							By.xpath("//*[@id='month-" + request_month + "']/div/div/table/tbody/tr[" + i + "]/td[1]"));
					text_record_dates = record_dates.getText();
					z++;

				} catch (Exception e) {
					System.out.println("6 row of calendar not found");
					break;

				}

			}

			

			calendar_array = new String[z][7][3];

			action.explicitWaitElementClickable(driver, driver.findElement(btn_next_year),
					Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
			Thread.sleep(2000);

			for (int i = 2; i < z + 2; i++) {

				for (int j = 1; j < 8; j++) {

					String text_record_dates = null;

					try {
						WebElement record_dates = driver.findElement(By.xpath("//*[@id='month-" + request_month
								+ "']/div/div/table/tbody/tr[" + i + "]/td[" + j + "]"));

						text_record_dates = record_dates.getText();
					} catch (Exception e) {
						System.out.println("calendar record not found");
						break;
					}

					String[] text_array = text_record_dates.split("\\n");

					int k = i - 2;
					int l = j - 1;

					if (text_array[0].equals("")) {

						calendar_month_dates[k][l] = "No_date";

						if (j == 1) {

							days_text = "Monday";
						}

						else if (j == 2) {

							days_text = "Tuesday";
						} else if (j == 3) {

							days_text = "Wednesday";
						} else if (j == 4) {

							days_text = "Thursday";
						} else if (j == 5) {

							days_text = "Friday";
						} else if (j == 6) {

							days_text = "Saturday";
						} else if (j == 7) {

							days_text = "Sunday";
						}

						try {
							calendar_array[k][l][0] = days_text;
						} catch (Exception e) {

							System.out.println("Month Days text error!!");

						}

					}

					else if (!text_array[0].equals("")) {

						calendar_month_dates[k][l] = text_array[0];

						if (j == 1) {

							days_text = "Monday";
						}

						else if (j == 2) {

							days_text = "Tuesday";
						} else if (j == 3) {

							days_text = "Wednesday";
						} else if (j == 4) {

							days_text = "Thursday";
						} else if (j == 5) {

							days_text = "Friday";
						} else if (j == 6) {

							days_text = "Saturday";
						} else if (j == 7) {

							days_text = "Sunday";
						}

						try {
							calendar_array[k][l][0] = days_text;
						} catch (Exception e) {

							System.out.println("Month Days text error!!");

						}

						calendar_array[k][l][1] = text_array[0];
						calendar_array[k][l][2] = text_array[1];

						days_text = "empty";

					}

					text_array = null;

				}


			}

 System.out.println("result collect successfully");

		return calendar_array;

	}

	
	public String[] complete_calendar_repalce(String[][][] calendar_recrod_saved) {
		
		String[][][] convert_calendar = calendar_recrod_saved;
		int row_count_of_array = convert_calendar.length;

		int array_row_create = row_count_of_array * 7;

		String[] recrod_converted_successfully = new String[array_row_create];

		int i = 0;

		for (int k = 0; k < row_count_of_array; k++) {

			for (int j = 0; j < 7; j++) {

				recrod_converted_successfully[i] = convert_calendar[k][j][2];
				i++;

			}

		}

		return recrod_converted_successfully;

	}
	
	
	
	
	
	
	
	
	
	

}
