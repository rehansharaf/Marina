package com.marina.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.marina.actiondriver.Action;

public class RatesStorageflatPrice_add_updatePage {

	

	WebDriver driver;
	Action action = new Action();

	public RatesStorageflatPrice_add_updatePage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	
	By text_space_name = By.xpath("//html/body/div[3]/div[2]/div/div[2]/div[1]/div[2]/div/div/div/div/div[1]/div/table/tbody/tr[1]/td[1]");
	By text_nightlyPrice = By.xpath("//html/body/div[3]/div[2]/div/div[2]/div[1]/div[2]/div/div/div/div/div[1]/div/table/tbody/tr[1]/td[2]");
	By text_monthlyPrice = By.xpath("//html/body/div[3]/div[2]/div/div[2]/div[1]/div[2]/div/div/div/div/div[1]/div/table/tbody/tr[1]/td[3]");
	By text_yearlyPrice = By.xpath("//html/body/div[3]/div[2]/div/div[2]/div[1]/div[2]/div/div/div/div/div[1]/div/table/tbody/tr[1]/td[4]");
	
	
	By dropdown_effective_from_date = By.xpath("//input[@ng-model='effective_date']");
	
	
	By dropdown_calendar_month = By.xpath("//html/body/div[13]/div[1]/div/div/select");

	By calender_flatPrice_month_jan = By.xpath("//html/body/div[13]/div[1]/div/div/select/option[1]");
	By calender_flatPrice_month_feb = By.xpath("//html/body/div[13]/div[1]/div/div/select/option[2]");
	By calender_flatPrice_month_mar = By.xpath("//html/body/div[13]/div[1]/div/div/select/option[3]");
	By calender_flatPrice_month_apr = By.xpath("//html/body/div[13]/div[1]/div/div/select/option[4]");
	By calender_flatPrice_month_may = By.xpath("//html/body/div[13]/div[1]/div/div/select/option[5]");
	By calender_flatPrice_month_jun = By.xpath("//html/body/div[13]/div[1]/div/div/select/option[6]");
	By calender_flatPrice_month_jul = By.xpath("//html/body/div[13]/div[1]/div/div/select/option[7]");
	By calender_flatPrice_month_aug = By.xpath("//html/body/div[13]/div[1]/div/div/select/option[8]");
	By calender_flatPrice_month_sep = By.xpath("//html/body/div[13]/div[1]/div/div/select/option[9]");
	By calender_flatPrice_month_oct = By.xpath("//html/body/div[13]/div[1]/div/div/select/option[10]");
	By calender_flatPrice_month_nov = By.xpath("//html/body/div[13]/div[1]/div/div/select/option[11]");
	By calender_flatPrice_month_dec = By.xpath("//html/body/div[13]/div[1]/div/div/select/option[12]");

	By calender_year_title = By.xpath("//html/body/div[13]/div[1]/div/div/div");
	By calender_data_select = By.xpath("//html/body/div[13]/div[2]/div/div[2]/div/span[7]");
	
	
	/*
	 * 
	 * click on selected date
	 * //span[@aria-label='November 4, 2023']
	 * /
	 */
	
	
	By btn_save_flat_price = By.xpath("//button[@ng-click='save()']");
	By popup_text_empty_field_error = By.xpath("//div[@class='swal2-html-container']");
	
	By btn_ok_empty_field_error = By.xpath("//button[text()='OK']");
	
	
	
	By btn_popup_yes_overwrite = By.xpath("//button[text()='Yes, overwrite']");
	By btn_popup_cancel = By.xpath("//button[text()='Cancel']");
	By popup_text_areYouSure = By.xpath("//h2[text()='Are you sure?']");
	By popup_text_success_saved = By.xpath("//h2[text()='Success!']");
	By btn_popup_successfully_saved_ok = By.xpath("//button[text()='OK']");
	
	
	
}
