package com.marina.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.marina.actiondriver.Action;

public class RatesStorageNightlyPrice_add_updatePage {

	WebDriver driver;
	Action action = new Action();

	public RatesStorageNightlyPrice_add_updatePage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	By h_nightly_price_tittle = By
			.xpath("//h1[@class='mb-0 text-white page-nav fs_22 fw_6 d-flex align-items-center']");

	By btn_buckets = By.xpath("//button[@type='button' and @class='btn btn-secondary bg-white text-dark']");

	By btn_view_nightly_price = By.xpath("//a[@class='btn btn-success btn-md px-4']");

	By tab_space_pricing = By.xpath("//button[text()='Space Pricing']");
	By tab_power_pricing = By.xpath("//button[text()='Power Pricing']");

	By btn_add_new_year = By.xpath("//button[@ng-click='addYear()']");

	By tab_year_2023 = By.xpath("//button[@id='year-2023-tab']");
	By tab_year_2024 = By.xpath("//button[@id='year-2024-tab']");
	By tab_year_2025 = By.xpath("//button[@id='year-2025-tab']");

	By tab_1_ft_size = By.xpath("//*[@id='buckets_tabs']/li[1]/button/span");
	By tab_2_ft_size = By.xpath("//*[@id='buckets_tabs']/li[2]/button/span");
	By tab_3_ft_size = By.xpath("//*[@id='buckets_tabs']/li[3]/button/span");
	By tab_4_ft_size = By.xpath("//*[@id='buckets_tabs']/li[4]/button/span");

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

	By dropdown_month_to_selection = By
			.xpath("//*[@id='bucket-2023-81']/div/div[1]/div/table/tbody/tr/td[1]/div/div[2]/select");
	By select_to_jan = By
			.xpath("//*[@id='bucket-2023-81']/div/div[1]/div/table/tbody/tr/td[1]/div/div[2]/select/option[1]");
	By select_to_feb = By
			.xpath("//*[@id='bucket-2023-81']/div/div[1]/div/table/tbody/tr/td[1]/div/div[2]/select/option[2]");
	By select_to_mar = By
			.xpath("//*[@id='bucket-2023-81']/div/div[1]/div/table/tbody/tr/td[1]/div/div[2]/select/option[3]");
	By select_to_apr = By
			.xpath("//*[@id='bucket-2023-81']/div/div[1]/div/table/tbody/tr/td[1]/div/div[2]/select/option[4]");
	By select_to_may = By
			.xpath("//*[@id='bucket-2023-81']/div/div[1]/div/table/tbody/tr/td[1]/div/div[2]/select/option[5]");
	By select_to_jun = By
			.xpath("//*[@id='bucket-2023-81']/div/div[1]/div/table/tbody/tr/td[1]/div/div[2]/select/option[6]");
	By select_to_jul = By
			.xpath("//*[@id='bucket-2023-81']/div/div[1]/div/table/tbody/tr/td[1]/div/div[2]/select/option[7]");
	By select_to_aug = By
			.xpath("//*[@id='bucket-2023-81']/div/div[1]/div/table/tbody/tr/td[1]/div/div[2]/select/option[8]");
	By select_to_sep = By
			.xpath("//*[@id='bucket-2023-81']/div/div[1]/div/table/tbody/tr/td[1]/div/div[2]/select/option[9]");
	By select_to_oct = By
			.xpath("//*[@id='bucket-2023-81']/div/div[1]/div/table/tbody/tr/td[1]/div/div[2]/select/option[10]");
	By select_to_nov = By
			.xpath("//*[@id='bucket-2023-81']/div/div[1]/div/table/tbody/tr/td[1]/div/div[2]/select/option[11]");
	By select_to_dec = By
			.xpath("//*[@id='bucket-2023-81']/div/div[1]/div/table/tbody/tr/td[1]/div/div[2]/select/option[12]");

	// 2023
	By input_monday = By.xpath("//*[@id='bucket-2023-81']/div/div[1]/div/table/tbody/tr/td[2]/input");

	// 2023
	By input_tuesday = By.xpath("//*[@id='bucket-2023-81']/div/div[1]/div/table/tbody/tr/td[3]/input");
	By input_wednesday = By.xpath("//*[@id='bucket-2023-81']/div/div[1]/div/table/tbody/tr/td[3]/input");
	By input_thursday = By.xpath("//*[@id='bucket-2023-81']/div/div[1]/div/table/tbody/tr/td[3]/input");
	By input_friday = By.xpath("//*[@id='bucket-2023-81']/div/div[1]/div/table/tbody/tr/td[3]/input");
	By input_saturday = By.xpath("//*[@id='bucket-2023-81']/div/div[1]/div/table/tbody/tr/td[3]/input");
	By input_sunday = By.xpath("//*[@id='bucket-2023-81']/div/div[1]/div/table/tbody/tr/td[3]/input");

	// 2023
	By btn_delete_nighlty_month = By
			.xpath("//*[@id='bucket-2023-81']/div/div[1]/div/table/tbody/tr/td[9]/div/button[1]");
	By btn_add_nightly_month = By.xpath("//*[@id='bucket-2023-81']/div/div[1]/div/table/tbody/tr/td[9]/div/button[2]");

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
	By calender_data_select = By.xpath("//html/body/div[14]/div[2]/div/div[2]/div/span[4]");

	By btn_save_nightly_pricing = By.xpath("//button[text()='Save Nightly Pricing']");

	By btn_popup_yes_overwrite_nightly_pricing = By.xpath("//button[text()='Save Nightly Pricing']");
	By btn_popup_not_overwrite_nightly_pricing = By.xpath("//button[text()='Cancel']");

	By popup_nightly_price_succsfully_save_text = By.xpath("//h2[text()='Success!']");
	By btn_popup_ok_nightly_price_successfully_save = By.xpath("//button[@class='swal2-confirm swal2-styled']");
	By btn_view_nightly_pricing = By.xpath("//a[@class='btn btn-success btn-md px-4']");
	
	// *[@id="year-2023"]/div[3]/div/input

}
