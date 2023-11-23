package com.marina.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.marina.actiondriver.Action;

public class RatesStorageFlatePricePage {

	WebDriver driver;
	Action action = new Action();

	public RatesStorageFlatePricePage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	By rates_flate_price_tittle = By
			.xpath("//h1[@class='mb-0 text-white page-nav fs_22 fw_6 d-flex align-items-center']");
	By btn_update_price = By.xpath("//a[@class='btn btn-success btn-md px-4']");
	By btn_add_price = By.xpath("//a[@class='btn btn-success btn-md px-4']");
	By dropdown_revision_history = By.xpath("//select[@ng-change='fetchData()']");

	By text_space = By.xpath(
			"//html/body/div[3]/div[2]/div/div[2]/div[1]/div[3]/div/div/div/div/div/div[2]/div/table/tbody/tr[1]/td[1]");
	By text_nightlyPrice = By.xpath(
			"//html/body/div[3]/div[2]/div/div[2]/div[1]/div[3]/div/div/div/div/div/div[2]/div/table/tbody/tr[1]/td[2]/span[2]");
	By text_monthlyPrice = By.xpath(
			"//html/body/div[3]/div[2]/div/div[2]/div[1]/div[3]/div/div/div/div/div/div[2]/div/table/tbody/tr[1]/td[3]/span[2]");
	By text_yearlyPrice = By.xpath(
			"//html/body/div[3]/div[2]/div/div[2]/div[1]/div[3]/div/div/div/div/div/div[2]/div/table/tbody/tr[1]/td[4]/span[2]");

	
	
	
	
	
	
}
