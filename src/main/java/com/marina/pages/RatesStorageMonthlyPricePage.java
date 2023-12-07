package com.marina.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.marina.actiondriver.Action;
import com.marina.base.TestBase;

public class RatesStorageMonthlyPricePage {

	WebDriver driver;
	Action action = new Action();

	public RatesStorageMonthlyPricePage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	By tab_btn_nightly = By.xpath("//a[text()='Nightly']");
	By tab_btn_monthly = By.xpath("//a[text()='Monthly']");
	By tab_btn_annual = By.xpath("//a[text()='Annual']");
	By tab_btn_fixed = By.xpath("//a[text()='Flat']");

	By popup_error_no_pricing = By
			.xpath("//button[@type='button' and text()='OK' and @class='swal2-confirm swal2-styled']");

	By h_rates_monthly_price_tittle = By
			.xpath("//h1[@class='mb-0 text-white page-nav fs_22 fw_6 d-flex align-items-center']");

	By btn_update_price = By.xpath("//a[@class='btn btn-success btn-md px-4']");
	By btn_add_price = By.xpath("//a[@class='btn btn-success btn-md px-4']");
	By tab_years_selections = By.xpath("//*[@id='year-2023-tab']");
	By h_revision_history = By.xpath("//*[@id=\"year-2023\"]/div[1]/div/label");
	By dropdown_revision_history = By.xpath("//*[@id='year-2023']/div[1]/div/select");

	By tab_bucket_options = By.xpath("//*[@id='bucket6_2023-tab']");

	By h2_months = By.xpath("//*[@id='bucket6_2023']/div/div/table/thead/tr/th[1]/text()");
	By h2_monthly_price = By.xpath("//*[@id='bucket6_2023']/div/div/table/thead/tr/th[2]/text()");
	By h2_premium = By.xpath("//*[@id='bucket6_2023']/div/div/table/thead/tr/th[3]/text()");
	By h2_one_time_price = By.xpath("//*[@id='bucket6_2023']/div/div/table/thead/tr/th[4]/text()");

	public boolean newly_group_monthly_rivision_history() {

		boolean revision_history_empty = false;

		try {

			WebElement popu_error_ok_button = driver.findElement(popup_error_no_pricing);
			action.explicitWaitElementClickable(driver, popu_error_ok_button,
					Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
			popu_error_ok_button.click();
		} catch (Exception e) {
			System.out.println("pricing already saved");
		}

		action.click1(driver.findElement(dropdown_revision_history), "dropdown revision history");

		By revision_history = By.xpath("//select[@ng-change='loadYear(year, 1)']/option[1]");

		String list_revision_record;

		try {

			list_revision_record = driver.findElement(revision_history).getText();

		} catch (Exception e) {
			System.out.println("some error monthly revision price issued.");
			return revision_history_empty;

		}

		if (list_revision_record == null || list_revision_record.isEmpty()) {

			revision_history_empty = true;

		}

		return revision_history_empty;

	}

	public RatesStorageAnnualPricePage single_click_annual_price() {

		WebElement annual_btn = driver.findElement(tab_btn_annual);
		action.explicitWaitElementClickable(driver, annual_btn,
				Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		annual_btn.click();

		return new RatesStorageAnnualPricePage(driver);

	}

}
