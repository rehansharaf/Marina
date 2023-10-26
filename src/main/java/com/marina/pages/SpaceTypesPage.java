package com.marina.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.marina.actiondriver.Action;
import com.marina.base.TestBase;

public class SpaceTypesPage {

	WebDriver driver;
	Action action = new Action();

	@FindBy(how = How.XPATH, using = "//div[text()='Space Types']")
	WebElement stp_title;

	@FindBy(how = How.XPATH, using = "//span[text()=' Refresh']")
	WebElement btn_refresh;

	@FindBy(how = How.XPATH, using = "//input[@type='search' and @class='form-control form-control-sm']")
	WebElement search_name;

	@FindBy(how = How.XPATH, using = "//button[@class='btn btn-success export']")
	WebElement btn_export;

	@FindBy(how = How.XPATH, using = "//div[@class='dataTables_info']")
	WebElement showing_entries;

	@FindBy(how = How.XPATH, using = "//button[text()='Add Space Type']")
	WebElement btn_addspacetype;
	
	@FindBy(how = How.XPATH, using = "//*[@id='space_type_table']/tbody/tr/td[1]")
	WebElement space_name_detail_fetch;
	
	
	

	public SpaceTypesPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String space_type_page_verify() {

		action.explicitWait(driver, stp_title, Duration.ofSeconds(10));
		return stp_title.getText();

	}
	
	
public SpaceTypesAddTypesPage add_space_type() {
		

		btn_addspacetype.click();
		return new SpaceTypesAddTypesPage(driver);
		
	}



public String search_space_data(String name) {
	
	
	search_name.sendKeys(name);
	
	//*[@id="space_type_table"]/tbody/tr/td[1]
	//*[@id="space_type_table"]/tbody/tr/td[2]
	
	
	
	return name;
	
	
}
	
	
	

	

//	public String verifySearchFilePage() {

//		return searchFilePageHeading.getText();
//	}

}
