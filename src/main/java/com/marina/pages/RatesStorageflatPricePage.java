package com.marina.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.marina.actiondriver.Action;
import com.marina.base.TestBase;

public class RatesStorageflatPricePage {

	WebDriver driver;
	Action action = new Action();

	public RatesStorageflatPricePage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	
	By tab_btn_nightly = By.xpath("//a[text()='Nightly']");
	By tab_btn_monthly = By.xpath("//a[text()='Monthly']");
	By tab_btn_annual = By.xpath("//a[text()='Annual']");
	By tab_btn_flat = By.xpath("//a[text()='Flat']");
	
	
	
	

	By rates_flat_price_tittle = By
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

	
	
public boolean newly_group_annual_rivision_history() {
		
		
		boolean revision_history_empty = false;
		
			
//		WebElement popu_error_ok_button = driver.findElement(tab_btn_flat);
//		action.explicitWaitElementClickable(driver, popu_error_ok_button,
//				Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
//		popu_error_ok_button.click();
		
		
		
		action.click1(driver.findElement(dropdown_revision_history), "dropdown revision history");
		
		By revision_history = By
				.xpath("//select[@ng-change='fetchData()']/option[1]");
		
		
		
		String list_revision_record;
		
		
		try {
		
		list_revision_record = driver.findElement(revision_history).getText();
		
		}
		catch (Exception e) {
			System.out.println("some error monthly revision price issued.");
			return revision_history_empty;
			
			
			
		}
		
		if(list_revision_record==null || list_revision_record.isEmpty()) {
			
			revision_history_empty= true;
			
			
		}
		
		
		return revision_history_empty;
		
		
			
					
		}




	
	
	
	
	
}
