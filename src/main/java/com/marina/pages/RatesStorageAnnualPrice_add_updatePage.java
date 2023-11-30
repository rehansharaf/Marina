package com.marina.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.marina.actiondriver.Action;

public class RatesStorageAnnualPrice_add_updatePage {

	
	
	WebDriver driver;
	Action action = new Action();

	public RatesStorageAnnualPrice_add_updatePage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	By h_annual_price_tittle = By
			.xpath("//h1[@class='mb-0 text-white page-nav fs_22 fw_6 d-flex align-items-center']");
	
	By btn_view_monthly_price = By.xpath("//a[@class='btn btn-success btn-md px-4']");
	
	By btn_add_new_year = By.xpath("//button[@ng-click='addYear()']");
	
	By tab_year_2023 = By.xpath("//button[@id='year-2023-tab']");
	By tab_year_2024 = By.xpath("//button[@id='year-2024-tab']");
	By tab_year_2025 = By.xpath("//button[@id='year-2025-tab']");
	
	
	By input_field_monthly_payment_premium = By.xpath("//*[@id='year-2023']/div/div[1]/div/input");
	
	By h_firstbucket_tittle = By.xpath("//*[@id='year-2023']/div/div[2]/div[1]/div/table/tbody/tr[1]/td[1]/div/div[1]");
	By h_secondbucket_tittle = By.xpath("//*[@id='year-2023']/div/div[2]/div[1]/div/table/tbody/tr[2]/td[1]/div/div[1]");
	
	By input_field_min_ft = By.xpath("//*[@id='year-2023']/div/div[2]/div[1]/div/table/tbody/tr[1]/td[1]/div/div[2]/div/span");
	By input_field_max_ft = By.xpath("//*[@id='year-2023']/div/div[2]/div[1]/div/table/tbody/tr[1]/td[1]/div/div[2]/div/input");
	
	By montlhy_price_ft_month = By.xpath("//*[@id='year-2023']/div/div[2]/div[1]/div/table/tbody/tr[1]/td[2]");
	By premium_percent = By.xpath("//*[@id='year-2023']/div/div[2]/div[1]/div/table/tbody/tr[1]/td[3]");
	
	By input_field_oneTimePrice = By.xpath("//*[@id='year-2023']/div/div[2]/div[1]/div/table/tbody/tr[1]/td[4]/input");
	By btn_delete_1_bucket = By.xpath("//*[@id='year-2023']/div/div[2]/div[1]/div/table/tbody/tr[1]/td[5]/button");
	By btn_add_more_bucket = By.xpath("//*[@id='year-2025']/div/div[2]/div[2]/div/button");
	
	
	By select_effective_from_date = By.xpath("//*[@id='year-2023']/div/div[2]/div[3]/div[1]/div[1]/input");

	/*
	 * 
	 * 
	 * 
	 * 
	 * /calendar are not clickable.. bug reported.
	 */
	By dropdown_calendar_month = By.xpath("//html/body/div[13]/div[1]/div/div/select");

	By calender_holidy_month_jan = By.xpath("//html/body/div[13]/div[1]/div/div/select/option[1]");
	By calender_holidy_month_feb = By.xpath("//html/body/div[13]/div[1]/div/div/select/option[2]");
	By calender_holidy_month_mar = By.xpath("//html/body/div[13]/div[1]/div/div/select/option[3]");
	By calender_holidy_month_apr = By.xpath("//html/body/div[13]/div[1]/div/div/select/option[4]");
	By calender_holidy_month_may = By.xpath("//html/body/div[13]/div[1]/div/div/select/option[5]");
	By calender_holidy_month_jun = By.xpath("//html/body/div[13]/div[1]/div/div/select/option[6]");
	By calender_holidy_month_jul = By.xpath("//html/body/div[13]/div[1]/div/div/select/option[7]");
	By calender_holidy_month_aug = By.xpath("//html/body/div[13]/div[1]/div/div/select/option[8]");
	By calender_holidy_month_sep = By.xpath("//html/body/div[13]/div[1]/div/div/select/option[9]");
	By calender_holidy_month_oct = By.xpath("//html/body/div[13]/div[1]/div/div/select/option[10]");
	By calender_holidy_month_nov = By.xpath("//html/body/div[13]/div[1]/div/div/select/option[11]");
	By calender_holidy_month_dec = By.xpath("//html/body/div[13]/div[1]/div/div/select/option[12]");

	
	
	By calender_year_title = By.xpath("//html/body/div[13]/div[1]/div/div/div");
	By calender_data_select = By.xpath("//html/body/div[13]/div[2]/div/div[2]/div/span[4]");
	
	
	
	/*
	 * 
	 * click on selected date
	 * //span[@aria-label='November 4, 2023']
	 * /
	 */
	
	
	By btn_popup_yes_overwrite = By.xpath("//button[text()='Yes, overwrite']");
	By btn_popup_cancel = By.xpath("//button[text()='Cancel']");
	By popup_text_areYouSure = By.xpath("//h2[text()='Are you sure?']");
	By popup_text_success_saved = By.xpath("//h2[text()='Success!']");
	By btn_popup_successfully_saved_ok = By.xpath("//button[text()='OK']");
	
	
	
	
	
	
	
	
	
}
