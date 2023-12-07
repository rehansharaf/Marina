package com.marina.pages;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
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

	By nightly_spaces_price = By.xpath("//a[text()='Nightly']");
	By monthly_spaces_price = By.xpath("//a[text()='Monthly']");
	By annual_spaces_price = By.xpath("//a[text()='Annual']");
	By flat_spaces_price = By.xpath("//a[text()='Flat']");

	By rates_storage_tittle = By.xpath("//h1[@class='mb-0 text-white page-nav fs_22 fw_6 d-flex align-items-center']");

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

	public boolean rate_group_list(String rateGroupName) {

		boolean name = false;
		int i;
		for (i = 1; i < 100; i++) {

		
			WebElement group_name_record_search = driver
					.findElement(By.xpath("//ul[@class='ulsdbr-rates menu-sub ']/li[" + i + "]"));
			
			
			try {

				Thread.sleep(2000);
				

				action.scrollByVisibilityOfElement(driver, group_name_record_search);
				
				
				Thread.sleep(2000);
				String rate_group_name_selected = group_name_record_search.getText();
				Thread.sleep(2000);
				System.out.println("count  " + i + "  = " + rate_group_name_selected);

				if (rate_group_name_selected.equals(rateGroupName)) {
					name = true;
					System.out.println("result found");
					break;
				}

			} catch (Exception e) {
				System.out.println("no other recrod found ");
				i = 100;

			}

		}

		return name;

	}


	public RatesStorageNightlyPricePage search_specifig_rates_group(String specific_rates_group_name)
			throws InterruptedException {

		Thread.sleep(1000);
		WebElement newly_group_name = driver.findElement(By.xpath("//div[text()='" + specific_rates_group_name + "']"));
		Thread.sleep(1000);
		action.scrollByVisibilityOfElement(driver, newly_group_name);
		Thread.sleep(1000);
		action.click1(newly_group_name, specific_rates_group_name);

		action.explicitWaitElementClickable(driver, driver.findElement(btn_update_price),
				Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		Thread.sleep(1000);
		action.explicitWaitElementClickable(driver, driver.findElement(btn_next_year),
				Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));

		System.out.println();

		return new RatesStorageNightlyPricePage(driver);

	}


}
