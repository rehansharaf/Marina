package com.marina.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.marina.actiondriver.Action;

public class RatesStorageAnnualPricePage {

	WebDriver driver;
	Action action = new Action();

	public RatesStorageAnnualPricePage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

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

}
