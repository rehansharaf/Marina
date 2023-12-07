package com.marina.pages;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.Duration;
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


public class SpaceTypesPage {

	WebDriver driver;
	Action action = new Action();
	String get_recrod_from_serach_tab[];
	int table_array_column = 7;
	WebDriverWait wait;
	String no_entries_found;
//String totalRecordCount ="//div[@class='dataTables_info']";
//	String alpha= totalRecordCount;
	
	
	

	@FindBy(how = How.XPATH, using = "//div[text()='Space Types']")
	WebElement stp_title;

	@FindBy(how = How.XPATH, using = "//span[text()=' Refresh']")
	WebElement btn_refresh;

	@FindBy(how = How.XPATH, using = "//input[@type='search' and @class='form-control form-control-sm']")
	WebElement search_name;

	@FindBy(how = How.XPATH, using = "//button[@class='btn btn-success export']")
	WebElement btn_export;
	
	@FindBy(how = How.XPATH, using = "//button[text()='Excel Sheet']")
	WebElement btn_excel_export;
	
	
	@FindBy(how = How.XPATH, using = "//button[text()='Google Sheet']")
	WebElement btn_google_sheet_export;
	
	

	@FindBy(how = How.XPATH, using = "//div[@class='dataTables_info']")
	WebElement showing_entries;


	@FindBy(how = How.XPATH, using = "//*[@id='space_type_table']/tbody/tr[1]/td[1]")
	WebElement col1_space_name_detail;

	@FindBy(how = How.XPATH, using = "//*[@id='space_type_table']/tbody/tr[1]/td[2]")
	WebElement col2_space_name_detail;

	@FindBy(how = How.XPATH, using = "//*[@id='space_type_table']/tbody/tr[1]/td[3]")
	WebElement col3_space_name_detail;

	@FindBy(how = How.XPATH, using = "//*[@id='space_type_table']/tbody/tr[1]/td[4]")
	WebElement col4_space_name_detail;

	@FindBy(how = How.XPATH, using = "//*[@id='space_type_table']/tbody/tr[1]/td[5]")
	WebElement col5_space_name_detail;

	@FindBy(how = How.XPATH, using = "//*[@id='space_type_table']/tbody/tr[1]/td[6]")
	WebElement col6_space_name_detail;

	@FindBy(how = How.XPATH, using = "//*[@id='space_type_table']/tbody/tr[1]/td[7]")
	WebElement col7_space_name_detail;


	@FindBy(how = How.XPATH, using = "//h2[text()='Are you sure?']")
	WebElement delte_confirmation_text;

	@FindBy(how = How.XPATH, using = "//button[text()='Yes, delete it!']")
	WebElement confirm_delete_btn;

	@FindBy(how = How.XPATH, using = "//h2[text()='Success!']")
	WebElement succes_delete_message;

	@FindBy(how = How.XPATH, using = "//button[text()='OK']")
	WebElement after_delte_success_ok_btn;


	@FindBy(how = How.XPATH, using = "//*[@id='space_type_table']/tbody/tr[1]/td[8]/div/a[1]")
	WebElement edit_option_spaceTable;

	@FindBy(how = How.XPATH, using = "//button[text()='Cancel']")
	WebElement cancle_delete_btn;
	
	@FindBy(how = How.XPATH, using = "//button[text()='Google Sheet']")
	WebElement googleSheetBtn;
	
	By openGoogleSheetLink = By.xpath("//a[text()='Open Google Sheet']");
	By googleSheetTitle = By.xpath("//span[@id='docs-title-input-label-inner']");
	By googleSheetFileMenu = By.id("docs-file-menu");
	By googleDownloadMenuItem = By.xpath("//span[@aria-label='Download d']/parent::div");
	By googleDownloadExcelOption = By.xpath("//span[@aria-label='Microsoft Excel (.xlsx) x']/parent::div");
	By btn_addspacetype = By.xpath("//button[text()='Add Space Type']");
	By delete_option_spaceTable = By.xpath("//*[@id='space_type_table']/tbody/tr[1]/td[8]/div/a[2]");
	By no_match_record_found_text = By.xpath("//td[text()='No matching records found']");
	By dataTable = By.xpath("//table[@id='space_type_table']/tbody/tr");
	By dataTableFirstRowFirstCol = By.xpath("//table[@id='space_type_table']/tbody/tr/td");

	
	@FindBy(how = How.XPATH, using = "//button[text()='Add Space Type']")
	WebElement btn_reClick_add_space;
	
	@FindBy(how = How.XPATH, using = "//*[@id='modalAddSpaceType']/div/form/div/div[3]/button[2]")
	WebElement btn_save_add_space_type_form;
	
	
	
	By breadcrumbs_home_page = By.xpath("//a[text()='Home']");
	
	

	public SpaceTypesPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	

	public String space_type_page_verify() {

		action.explicitWait(driver, stp_title, Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		return stp_title.getText();

	}

	public SpaceTypesAddTypesPage add_space_type() throws InterruptedException {

		action.scrollByVisibilityOfElement(driver, driver.findElement(btn_addspacetype));
		action.explicitWaitPresenceOfElement(driver, btn_addspacetype, Duration.ofSeconds(10));
		action.explicitWaitElementClickable(driver, driver.findElement(btn_addspacetype), Duration.ofSeconds(10));
		//action.explicitWaitPresenceOfElement(driver, delete_option_spaceTable, Duration.ofSeconds(10));
		//try {
			//Thread.sleep(2000);
			//driver.findElement(btn_addspacetype).click();
			action.click1(driver.findElement(btn_addspacetype), "Clicking add space btn");
		//}

		/*catch (Exception e) {

			System.out.println("add space button not clickable");
			String url = action.getCurrentURL(driver);
			System.out.println(url);

		}*/

		return new SpaceTypesAddTypesPage(driver);

	}

	public String[] get_space_data_from_table(String name) throws InterruptedException {

//	String total_entries =showing_entries.getText();

		Thread.sleep(1000);
		try {
			action.explicitWaitElementClickable(driver, driver.findElement(delete_option_spaceTable), Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		} catch (Exception e) {
			System.out.println("no sapce record found in table");
		}
		String result_count = showing_entries.getText();

		search_name.click();

		action.explicitWait(driver, search_name, Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		search_name.sendKeys(name);

		get_recrod_from_serach_tab = new String[table_array_column];

		Thread.sleep(6000);
		try {
			action.explicitWaitElementClickable(driver, driver.findElement(delete_option_spaceTable), Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));

		} catch (Exception e) {
			System.out.println("delete option not found");

		}

		get_recrod_from_serach_tab[0] = col1_space_name_detail.getText();
		get_recrod_from_serach_tab[1] = col2_space_name_detail.getText();
		get_recrod_from_serach_tab[2] = col3_space_name_detail.getText();
		get_recrod_from_serach_tab[3] = col4_space_name_detail.getText();
		get_recrod_from_serach_tab[4] = col5_space_name_detail.getText();
		get_recrod_from_serach_tab[5] = col6_space_name_detail.getText();
		get_recrod_from_serach_tab[6] = col7_space_name_detail.getText();

		Thread.sleep(2000);
		search_name.clear();
		Thread.sleep(1000);

		search_name.click();
		Thread.sleep(2000);
		try {
			action.explicitWaitElementClickable(driver, driver.findElement(delete_option_spaceTable), Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));

		} catch (Exception e) {
			System.out.println("delete option not found ");
		}

		Thread.sleep(3000);

		return get_recrod_from_serach_tab;

	}

	public String delete_space(String user) throws InterruptedException {

		Thread.sleep(2000);
		try {
			action.explicitWaitPresenceOfElement(driver, btn_addspacetype, Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
			action.explicitWait(driver, search_name, Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		} catch (Exception e) {

			System.out.println("delete or add space button not showing -194");

		}

		String entries = showing_entries.getText();

		Thread.sleep(1000);
		search_name.clear();
		Thread.sleep(1000);
		search_name.click();
		Thread.sleep(1000);
		search_name.sendKeys(user);
		Thread.sleep(1000);
		String entries_remain = "a";

		for (int i = 0; i < 2000; i++) {

			
			try {
				//Thread.sleep(3000);
				action.explicitWaitPresenceOfElement(driver, delete_option_spaceTable, Duration.ofSeconds(3));

			} catch (Exception e) {
				System.out.println("No Space found on table");
				action.explicitWaitPresenceOfElement(driver, no_match_record_found_text, Duration.ofSeconds(3));
				Thread.sleep(2000);
				no_entries_found = driver.findElement(no_match_record_found_text).getText();

				break;
			}

			Thread.sleep(3000);
			String reSet_entries = showing_entries.getText();
			Thread.sleep(1000);

			if (!entries.equals(reSet_entries)) {

				try {

					Thread.sleep(1000);
//					action.explicitWait(driver, delete_option_spaceTable, Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
					action.explicitWaitPresenceOfElement(driver, delete_option_spaceTable, Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
					Thread.sleep(1000);
					String space_id = col1_space_name_detail.getText();
					String space_name = col2_space_name_detail.getText();

					int int_space_id = Integer.valueOf(space_id);

					Thread.sleep(1000);
					if (int_space_id > 10) {

						action.click1(driver.findElement(delete_option_spaceTable), "Click delete option spacetable");
						Thread.sleep(2000);
						action.explicitWaitElementClickable(driver, delte_confirmation_text, Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
						Thread.sleep(1000);
						confirm_delete_btn.click();

						System.out.println("SPACE ID Deleted = " + int_space_id + " ,  SPACE NAME =  " + space_name);

						Thread.sleep(2000);
						action.explicitWaitElementClickable(driver, succes_delete_message, Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
						Thread.sleep(2000);
						after_delte_success_ok_btn.click();
						Thread.sleep(3000);

					}

					else {
						System.out.println("space id is less then 9");
						break;
					}

					i++;

				} catch (Exception e) {

					action.explicitWaitPresenceOfElement(driver, no_match_record_found_text, Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
					Thread.sleep(2000);
					no_entries_found = driver.findElement(no_match_record_found_text).getText();
					entries_remain = showing_entries.getText();
					System.out.println(entries_remain);
					System.out.println("Table Empty");

//				action.explicitWait(driver, no_match_record_found_text, Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
//				no_entries_found = no_match_record_found_text.getText();

					if (no_entries_found.equals("No matching records found")) {
						i = 2000;
					}

				}

			} else {
				i++;

				System.out.println(i);
				entries_remain = showing_entries.getText();
				try {
					Thread.sleep(2000);
					action.explicitWaitPresenceOfElement(driver, no_match_record_found_text, Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
					no_entries_found = driver.findElement(no_match_record_found_text).getText();
					System.out.println(no_entries_found);
					if (no_entries_found.equals("No matching records found")) {
						i = 2000;
					}
				} catch (Exception e) {
					System.out.println(" no data text not found");
					break;
					
					// TODO: handle exception
				}

			}

		}

		return no_entries_found;

	}
	
	
	
	public EditSpacePage search_space_click_edit_btn(String space_id) throws InterruptedException {
		
		driver.navigate().refresh();
		action.explicitWaitElementClickable(driver, driver.findElement(btn_addspacetype), Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		String result_count = showing_entries.getText();

		search_name.click();
		action.explicitWait(driver, search_name, Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		search_name.sendKeys(space_id);
		Thread.sleep(3000);
		action.explicitWaitElementClickable(driver, edit_option_spaceTable, Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		String result_count_two = showing_entries.getText();
		
		
		for(int i=0; i<20; i++) {
			
			if (!result_count.equals(result_count_two)) {
				break;
			}
			
			Thread.sleep(2000);
		}
		
		
		edit_option_spaceTable.click();
	
		return new EditSpacePage(driver);
		
		
	}
	
	
	public boolean exportDataToExcel() {

		action.explicitWait(driver, btn_excel_export, Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		action.click1(btn_export, "Export Space Btn");
		action.explicitWait(driver, btn_excel_export, Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		action.click1(btn_excel_export, "Excel Sheet Btn");
		String filepath = action.isFileDownloaded("Spaces Type Report", ".xlsx", 30);

		ExcelLibrary excel = new ExcelLibrary(filepath);
		int excelRows = excel.getRowCount("Worksheet");

		String dataTableRowsS = showing_entries.getText().trim();
		String[] splitData = dataTableRowsS.split("of");
		dataTableRowsS = splitData[1];
		dataTableRowsS = dataTableRowsS.replace("entries", "");
		dataTableRowsS = dataTableRowsS.trim();
		int dataTableRows = Integer.parseInt(dataTableRowsS);

		if (excelRows - 1 == dataTableRows) {
			File spaceExcelFile = new File(filepath);
			spaceExcelFile.delete();
			return true;
		} else
			return false;
	}

	public boolean exportDataToGoogleSheet() throws GeneralSecurityException, IOException, InterruptedException {

		action.click1(btn_export, "Export Space Btn");
		action.explicitWait(driver, googleSheetBtn, Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		action.click1(googleSheetBtn, "Google Sheet Btn");
		action.explicitWait(driver, driver.findElement(openGoogleSheetLink), Duration.ofSeconds(30));
		Thread.sleep(1000);

		action.click1(driver.findElement(openGoogleSheetLink), "Open Google Sheet Link");
		action.switchToNewWindow(driver);
		action.explicitWait(driver, driver.findElement(googleSheetTitle), Duration.ofSeconds(30));
		action.click1(driver.findElement(googleSheetFileMenu), "Google Sheet File Menu");
		action.click1(driver.findElement(googleDownloadMenuItem), "Google Download Menu Item");
		action.click1(driver.findElement(googleDownloadExcelOption), "Google Download Excel Sheet Option");
		String currentDate = action.getCurrentDate(1, 0, 0, "dd_MM_yyyy");
		String filename = "Spaces" + "_" + currentDate;
		String filepath = action.isFileDownloaded(filename, ".xlsx", 30);

		driver.close();
		action.switchToOldWindow(driver);
		action.click1(after_delte_success_ok_btn, "Success Ok Btn");

		ExcelLibrary excel = new ExcelLibrary(filepath);
		int excelRows = excel.getRowCount("Sheet1");
		String dataTableRowsS = showing_entries.getText().trim();
		String[] splitData = dataTableRowsS.split("of");
		dataTableRowsS = splitData[1];
		dataTableRowsS = dataTableRowsS.replace("entries", "");
		dataTableRowsS = dataTableRowsS.trim();
		int dataTableRows = Integer.parseInt(dataTableRowsS);

		if (excelRows - 1 == dataTableRows) {
			File spaceExcelFile = new File(filepath);
			spaceExcelFile.delete();
			return true;
		} else
			return false;

	}
	
	
	
	
	public SpaceTypesAddTypesPage click_btn_add_space_type() throws InterruptedException {
		
		action.explicitWaitElementClickable(driver, btn_reClick_add_space, Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		action.scrollByVisibilityOfElement(driver, btn_reClick_add_space);
		btn_reClick_add_space.click();
		action.explicitWaitElementClickable(driver, btn_save_add_space_type_form, Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		
		
		return new SpaceTypesAddTypesPage(driver);
		
		
				
	}
	
	public boolean searchAndDeleteSpaceType(String spaceName) throws InterruptedException {
		
		action.scrollByVisibilityOfElement(driver, driver.findElement(btn_addspacetype));
		action.explicitWaitPresenceOfElement(driver, btn_addspacetype, Duration.ofSeconds(10));
		action.explicitWait(driver, search_name, Duration.ofSeconds(10));
		action.type(search_name, spaceName);
		int checkCond = 0;
		while(checkCond == 0) {
			
			int rowCount = driver.findElements(dataTable).size();
			if(rowCount == 1) {
				
				String rowText = driver.findElement(dataTableFirstRowFirstCol).getText();
				if(rowText.equals("No matching records found")) {
				
					checkCond = 0;
					break;
					
				}else {
					
					Thread.sleep(1000);
					driver.findElement(By.xpath("//a[@data-name='"+spaceName+"']")).click();
					action.explicitWaitElementClickable(driver, confirm_delete_btn, Duration.ofSeconds(10));
					action.click1(confirm_delete_btn, "click delete btn");
					action.explicitWaitElementClickable(driver, after_delte_success_ok_btn, Duration.ofSeconds(10));
					action.click1(after_delte_success_ok_btn, "click ok btn");
					action.explicitWaitElementClickable(driver, search_name, Duration.ofSeconds(10));
					checkCond = 1;
					break;
				}
					
			}else 
				Thread.sleep(1000);
		}
		if(checkCond == 0)
			return false;
		else 
			return true;
		
		
	}
	
	public HomePage breadcrumbs_homePage() throws InterruptedException {
		
		action.explicitWaitElementClickable(driver, driver.findElement(breadcrumbs_home_page), Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		action.click1(driver.findElement(breadcrumbs_home_page), "home page click");
		driver.navigate().refresh();
		Thread.sleep(1000);
		return new HomePage(driver);
		
		
		
	}
	
	
	
	

	

//	public String verifySearchFilePage() {

//		return searchFilePageHeading.getText();
//	}

}
