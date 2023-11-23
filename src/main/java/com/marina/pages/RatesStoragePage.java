package com.marina.pages;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v115.network.model.LoadingFinished;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.openqa.selenium.By;

import com.marina.actiondriver.Action;
import com.marina.base.TestBase;
import com.marina.utils.ExcelLibrary;

public class RatesStoragePage {

	WebDriver driver;
	Action action = new Action();

	public RatesStoragePage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	
	
	
	
	
	
	
	@FindBy(how = How.XPATH, using = "//div[@class='sdbr-dry-storage']")
	WebElement dry_storage;
	
	
	@FindBy(how = How.XPATH, using = "//div[@class='sdbr-wet-storage']")
	WebElement wet_storage;
	
	@FindBy(how = How.XPATH, using = "//div[@class='sdbr-dummy-name-storage']")
	WebElement dummy_storage;

	
	By btn_edit_storage_name = By.xpath("//*[@id='layout-menu']/ul/li[4]/ul/li[5]/ul/li[1]/a[2]/i");
	
	int i;
	
//	By rate_group = By.xpath("//*[@id='layout-menu']/ul/li[4]/ul/li[5]/ul/li["+i+"]");
	
	By rate_group = By.xpath("//ul[@class='ulsdbr-rates menu-sub']/li["  +i+  "]/a[1]");

	ArrayList<String> rate_list_record;
	
	
	
	
	
	
	

	
	
	
	
	
	
	public void rate_group_list() {
		
		
		//a[@data-defaultvalue='automation test user']
		
		System.out.println("testing ");
		
		
		
		rate_list_record = new ArrayList<String>();
		
		for (i=1; i<100; i++) {
			
					
			try {
			rate_list_record.add(driver.findElement(rate_group).getText());
			
			}
			catch(Exception e) {
				System.out.println("record not found closing loop");
				i=100;
				
				
			}
			System.out.println(rate_list_record);
			
			
			
			
			
		}
		
	
		
		
		
		
		
		
		
		
	}
	
	
	
	
	By rates_storage_tittle = By.xpath("//h1[@class='mb-0 text-white page-nav fs_22 fw_6 d-flex align-items-center']");

	By nightly_spaces_price = By.xpath("//a[text()='Nightly']");
	By monthly_spaces_price = By.xpath("//a[text()='Monthly']");
	By annual_spaces_price = By.xpath("//a[text()='Annual']");
	By flat_spaces_price = By.xpath("//a[text()='Flat']");

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	By btn_update_price = By.xpath("//a[@class='btn btn-success btn-md px-4']");
	By btn_add_price = By.xpath("//a[@class='btn btn-success btn-md px-4']");

	By tab_space_pricing = By.xpath("//button[text()='Space Pricing']");
	By tab_power_pricing = By.xpath("//button[text()='Power Pricing']");

	By tab_1_ft_size = By.xpath("//*[@id='buckets_tabs']/li[1]/button/span");
	By tab_2_ft_size = By.xpath("//*[@id='buckets_tabs']/li[2]/button/span");
	By tab_3_ft_size = By.xpath("//*[@id='buckets_tabs']/li[3]/button/span");

	By revision_history = By.xpath("//*[@id='bucket']/div/div/div/div[2]/div/div/label");
	By dropdown_revision_history = By.xpath("//*[@id='bucket']/div/div/div/div[2]/div/div/label");

	By btn_previous_year = By.xpath("//button[@ng-click='prev_year()']");
	By h_active_year = By.xpath("//h4[@ng-bind='active_year']");
	By btn_next_year = By.xpath("//button[@ng-click='next_year()']");
	
	
	
	
//	action.click1(spacesdropdown, "Spaces DropDown");
	
	
	public void test() {
		
		
	
		
		
	}
	
	
	
	
	
	
}
