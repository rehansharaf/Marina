package com.marina.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.marina.actiondriver.Action;

public class RatesStorageNightlyPricePage {

	WebDriver driver;
	Action action = new Action();

	public RatesStorageNightlyPricePage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	By rates_nightly_price_tittle = By
			.xpath("//h1[@class='mb-0 text-white page-nav fs_22 fw_6 d-flex align-items-center']");

	By btn_update_price = By.xpath("//a[@class='btn btn-success btn-md px-4']");
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
	By dropdown_revision_history = By.xpath("//*[@id='bucket']/div/div/div/div[2]/div/div/label");

	By btn_previous_year = By.xpath("//button[@ng-click='prev_year()']");
	By h_active_year = By.xpath("//h4[@ng-bind='active_year']");
	By btn_next_year = By.xpath("//button[@ng-click='next_year()']");

	// calendar years tab , month text in camel case
	String month_text;
	By tab_calendar_month = By.xpath("//button[text()='" + month_text + "']");

//		By tab_year_February = By.xpath("//button[text()='February']");
//		By tab_year_March = By.xpath("//button[text()='March']");
//		By tab_year_April = By.xpath("//button[text()='April']");
//		By tab_year_May = By.xpath("//button[text()='May']");
//		By tab_year_June = By.xpath("//button[text()='June']");
//		By tab_year_July = By.xpath("//button[text()='July']");
//		By tab_year_August = By.xpath("//button[text()='August']");
//		By tab_year_September = By.xpath("//button[text()='September']");
//		By tab_year_October = By.xpath("//button[text()='October']");
//		By tab_year_November = By.xpath("//button[text()='November']");
//		By tab_year_December = By.xpath("//button[text()='December']");

	// day and days

	int month_number;
	int day_number;
	int date_column;
	By tab_days = By.xpath(
			"//*[@id='month-" + month_number + "']/div/div/table/tbody/tr[" + date_column + "]/td[" + day_number + "]");
	// *[@id='month-11']/div/div/table/tbody/tr[1]/td[1]

}
