package com.marina.pages;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v115.network.model.LoadingFinished;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.marina.actiondriver.Action;
import com.marina.base.TestBase;

public class SpaceTypesPage {

	WebDriver driver;
	Action action = new Action();
	String get_recrod_from_serach_tab[];
	int table_array_column=7;
	WebDriverWait wait;
	
	

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
	WebElement col1_space_name_detail;
	
	@FindBy(how = How.XPATH, using = "//*[@id='space_type_table']/tbody/tr/td[2]")
	WebElement col2_space_name_detail;
	
	@FindBy(how = How.XPATH, using = "//*[@id='space_type_table']/tbody/tr/td[3]")
	WebElement col3_space_name_detail;
	
	@FindBy(how = How.XPATH, using = "//*[@id='space_type_table']/tbody/tr/td[4]")
	WebElement col4_space_name_detail;
	
	@FindBy(how = How.XPATH, using = "//*[@id='space_type_table']/tbody/tr/td[5]")
	WebElement col5_space_name_detail;
	
	@FindBy(how = How.XPATH, using = "//*[@id='space_type_table']/tbody/tr/td[6]")
	WebElement col6_space_name_detail;
	
	@FindBy(how = How.XPATH, using = "//*[@id='space_type_table']/tbody/tr/td[7]")
	WebElement col7_space_name_detail;
	
	

	public SpaceTypesPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String space_type_page_verify() {

		action.explicitWait(driver, stp_title, Duration.ofSeconds(10));
		return stp_title.getText();

	}
	
	
public SpaceTypesAddTypesPage add_space_type() {
		
	action.explicitWait(driver, btn_addspacetype, Duration.ofSeconds(10));
		
		btn_addspacetype.click();
		return new SpaceTypesAddTypesPage(driver);
		
	}



public String[] verify_spaceAdd__with_sheet(String name) throws InterruptedException {
	
	
	
//	String total_entries =showing_entries.getText();
	action.explicitWait(driver, search_name, Duration.ofSeconds(10));
	search_name.sendKeys(name);
	
	get_recrod_from_serach_tab= new String[table_array_column];
	
	System.out.println("test");
	
	Thread.sleep(6000);		
	
	get_recrod_from_serach_tab[0] = col1_space_name_detail.getText();
	get_recrod_from_serach_tab[1] = col2_space_name_detail.getText();
	get_recrod_from_serach_tab[2] = col3_space_name_detail.getText();
	get_recrod_from_serach_tab[3] = col4_space_name_detail.getText();	
	get_recrod_from_serach_tab[4] = col5_space_name_detail.getText();
	get_recrod_from_serach_tab[5] = col6_space_name_detail.getText();
	get_recrod_from_serach_tab[6] = col7_space_name_detail.getText();
	
	
	

	
	return get_recrod_from_serach_tab;
	
	
}





	
	
	

	

//	public String verifySearchFilePage() {

//		return searchFilePageHeading.getText();
//	}

}
