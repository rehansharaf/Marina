package com.marina.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.marina.actiondriver.Action;
import com.marina.base.TestBase;

public class RatesStorageAnnualPricePage {

	WebDriver driver;
	Action action = new Action();

	public RatesStorageAnnualPricePage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	By tab_btn_nightly = By.xpath("//a[text()='Nightly']");
	By tab_btn_monthly = By.xpath("//a[text()='Monthly']");
	By tab_btn_annual = By.xpath("//a[text()='Annual']");
	By tab_btn_fixed = By.xpath("//a[text()='Flat']");

	By rates_annual_price_tittle = By
			.xpath("//h1[@class='mb-0 text-white page-nav fs_22 fw_6 d-flex align-items-center']");
	By btn_update_price = By.xpath("//a[@class='btn btn-success btn-md px-4']");
	By btn_add_price = By.xpath("//a[@class='btn btn-success btn-md px-4']");
	By tab_years_selections = By.xpath("//*[@id='year-2023-tab']");
	By h_revision_history = By.xpath("//*[@id='year-2023']/div[1]/div/label");
	By dropdown_revision_history = By.xpath("//*[@id='year-2023']/div[1]/div/select");

//	By first_bucket_loa_detail = By.xpath("//*[@id='year-2023']/div[2]/div/table/tbody/tr[1]/td[1]/div/div[1]");
	By ft_detail = By.xpath("//*[@id='year-2023']/div[2]/div/table/tbody/tr[1]/td[1]/div/div[2]");
	By monthlyPrice_detail = By.xpath("//*[@id='year-2023']/div[2]/div/table/tbody/tr[1]/td[2]");
	By premium_detail = By.xpath("//*[@id='year-2023']/div[2]/div/table/tbody/tr[1]/td[3]");
	By oneTimePrice_detail = By.xpath("//*[@id='year-2023']/div[2]/div/table/tbody/tr[1]/td[4]");

	public boolean newly_group_annual_rivision_history() {

		boolean revision_history_empty = false;

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

	public RatesStorageflatPricePage single_click_flat_price() {

		WebElement flat_price_btn = driver.findElement(tab_btn_fixed);
		action.explicitWaitElementClickable(driver, flat_price_btn,
				Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		flat_price_btn.click();

		return new RatesStorageflatPricePage(driver);

	}

}
