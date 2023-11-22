package com.marina.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.marina.actiondriver.Action;

public class RatesStorageMonthlyPricePage {

	
	WebDriver driver;
	Action action = new Action();

	public RatesStorageMonthlyPricePage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	
	By h_rates_monthly_price_tittle = By.xpath("//h1[@class='mb-0 text-white page-nav fs_22 fw_6 d-flex align-items-center']");
	
	
	By btn_update_price = By.xpath("//a[@class='btn btn-success btn-md px-4']");
	By btn_add_price = By.xpath("//a[@class='btn btn-success btn-md px-4']");
	By tab_years_selections = By.xpath("//*[@id='year-2023-tab']");
	By h_revision_history = By.xpath("//*[@id=\"year-2023\"]/div[1]/div/label");
	By dropdown_revision_history = By.xpath("//*[@id='year-2023']/div[1]/div/select");
	
	
		
	By tab_bucket_options = By.xpath("//*[@id='bucket6_2023-tab']");
	// bucket tab
	//*[@id='bucket6_2023-tab']
	//*[@id='bucket7_2023-tab']


	By h2_months= By.xpath("//*[@id='bucket6_2023']/div/div/table/thead/tr/th[1]/text()");
	By h2_monthly_price= By.xpath("//*[@id='bucket6_2023']/div/div/table/thead/tr/th[2]/text()");
	By h2_premium= By.xpath("//*[@id='bucket6_2023']/div/div/table/thead/tr/th[3]/text()");
	By h2_one_time_price= By.xpath("//*[@id='bucket6_2023']/div/div/table/thead/tr/th[4]/text()");
	
	
	
	
	//*[@id='bucket6_2023']/div/div/table/thead/tr/th[1]/text()
	
	//*[@id='year-2023']/div[1]/div/label
	
	
	
	
	
	
	
}
