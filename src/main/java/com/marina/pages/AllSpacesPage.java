package com.marina.pages;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.marina.actiondriver.Action;
import com.marina.base.TestBase;
import com.marina.utils.ExcelLibrary;

public class AllSpacesPage {
	
	WebDriver driver;
	Action action = new Action();
	
	//@FindBy(how = How.XPATH, using = "//h1[@class='mb-0 text-white page-nav fs_22 fw_6 d-flex align-items-center']")
	//WebElement spacesPageHeading;
	
	@FindBy(how = How.XPATH, using = "//input[@class='form-control form-control-sm']")
	WebElement searchField;
	
	@FindBy(how = How.XPATH, using = "//a[text()[contains(., 'Add Space')]]")
	WebElement addSpaceBtn;
	
	@FindBy(how = How.XPATH, using = "//a[@class='btn btn-primary btn-key edit-btn']")
	WebElement editBtn;
	
	@FindBy(how = How.XPATH, using = "//button[text()='Add note']")
	WebElement addNote;
	
	@FindBy(how = How.ID, using = "note")
	WebElement noteFieldViewSection;
	
	@FindBy(how = How.XPATH, using = "//button[text()='Save']")
	WebElement noteSaveBtn;
	
	@FindBy(how = How.ID, using = "must-view")
	WebElement noteMustView;
	
	@FindBy(how = How.XPATH, using = "//i[@title='Must view note']")
	WebElement mustViewIndicator;
	
	@FindBy(how = How.XPATH, using = "//a[text()[contains(., 'Import Spaces')]]")
	WebElement import_space;
	
	@FindBy(how = How.XPATH, using = "//button[@class='btn btn-success export']")
	WebElement exportBtn;
	
	@FindBy(how = How.XPATH, using = "//button[text()='Excel Sheet']")
	WebElement excelSheetBtn;
	
	@FindBy(how = How.XPATH, using = "//button[text()='Google Sheet']")
	WebElement googleSheetBtn;
	
	By spacesPageHeading = By.xpath("//h1[@class='mb-0 text-white page-nav fs_22 fw_6 d-flex align-items-center']");
	By successOk = By.xpath("//button[text()='OK']");
	By noSearchRecords = By.xpath("//td[text()='No matching records found']");
	By firstDataTableElement = By.xpath("//*[@id='slip']/tbody/tr");
	By viewSpaceSection = By.xpath("//tr[@class='responsive']/th[text()='Name']");
	By viewSpaceSectionDeleteBtn = By.xpath("//a[@class='btn btn-danger btn-delete-slip']");
	By deleteConfBtn = By.xpath("//button[text()='Yes, delete it!']");
	By singleImpNoteDeleteBtn = By.xpath("//table[@class='table table-striped fs_14']//th[text()='Note']/parent::tr/following-sibling::tr[1]/td/div/div/div/i/parent::div/following-sibling::div[@class='col-md-6']/a");
	By singleNonImpNoteDeleteBtn = By.xpath("(//table[@class='table table-striped fs_14']//th[text()='Note']/parent::tr/following-sibling::tr[1]/td/div/div/div/following-sibling::div[@class='col-md-6']/a)[1]");
	By listOfNotes = By.xpath("//table[@class='table table-striped fs_14']//th[text()='Note']/parent::tr/following-sibling::tr[1]/td/div/div/div[@class='col-12 mb-2']");
	By noteTextLocator = By.xpath("(//table[@class='table table-striped fs_14']//th[text()='Note']/parent::tr/following-sibling::tr[1]/td/div/div/div[@class='col-12 mb-2'])[1]");
	By totalRecordCount = By.id("slip_info");
	By openGoogleSheetLink = By.xpath("//a[text()='Open Google Sheet']");
	By googleSheetTitle = By.xpath("//span[@id='docs-title-input-label-inner']");
	By googleSheetFileMenu = By.id("docs-file-menu");
	By googleDownloadMenuItem = By.xpath("//span[@aria-label='Download d']/parent::div");
	By googleDownloadExcelOption = By.xpath("//span[@aria-label='Microsoft Excel (.xlsx) x']/parent::div");
	By dataTable = By.xpath("//table[@id='slip']/tbody/tr");
	By dataTableFirstRow = By.xpath("//table[@id='slip']/tbody/tr");
	By dataTableFirstRowFirstCol = By.xpath("//table[@id='slip']/tbody/tr/td");

	
	
	public AllSpacesPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean deleteSpace(String spaceName) {
		
		boolean spaceNotAvailable;
		
		action.explicitWait(driver, searchField, Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		action.type(searchField,spaceName);
		try {
			action.explicitWait(driver, driver.findElement(firstDataTableElement), Duration.ofSeconds(4));
			spaceNotAvailable = true;
			openSpaceViewPage();
			action.explicitWaitElementClickable(driver, driver.findElement(viewSpaceSectionDeleteBtn), Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
			action.click1(driver.findElement(viewSpaceSectionDeleteBtn), "Click Space Delete Btn From View Section");
			action.explicitWaitElementClickable(driver, driver.findElement(deleteConfBtn), Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
			action.click1(driver.findElement(deleteConfBtn), "Click Conf Delete Btn");
			action.explicitWaitElementClickable(driver, driver.findElement(successOk), Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
			action.click1(driver.findElement(successOk), "Click success Ok Btn");
			action.explicitWait(driver, driver.findElement(spacesPageHeading), Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
			driver.navigate().refresh();
			return true;
		}catch(NoSuchElementException nse) {
			
			spaceNotAvailable = false;
			return spaceNotAvailable;
		}
		
	}
	
	
	public boolean searchSpace(String spaceName) {
		
		boolean spaceNotAvailable;
		
		action.explicitWait(driver, searchField, Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		action.type(searchField,spaceName);
		try {
			action.explicitWait(driver, driver.findElement(firstDataTableElement), Duration.ofSeconds(4));
			spaceNotAvailable = true;
			return spaceNotAvailable;
		}catch(NoSuchElementException nse) {
			
			spaceNotAvailable = false;
			return spaceNotAvailable;
		}
		
	}
	
	public boolean exportDataToGoogleSheet() throws GeneralSecurityException, IOException, InterruptedException {
		
		action.click1(exportBtn, "Export Space Btn");
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
		String filename = "Spaces"+"_"+currentDate;
		String filepath = action.isFileDownloaded(filename, ".xlsx", 30);
		
		driver.close();
		action.switchToOldWindow(driver);
		action.click1(driver.findElement(successOk), "Success Ok Btn");
		
		ExcelLibrary excel = new ExcelLibrary(filepath);
		int excelRows = excel.getRowCount("Sheet1");
		String dataTableRowsS = driver.findElement(totalRecordCount).getText().trim();
		String[] splitData = dataTableRowsS.split("of");
		dataTableRowsS = splitData[1];
		dataTableRowsS = dataTableRowsS.replace("entries", "");
		dataTableRowsS = dataTableRowsS.trim();
		int dataTableRows = Integer.parseInt(dataTableRowsS);
		
		if(excelRows-1 == dataTableRows) {
			File spaceExcelFile = new File(filepath);
			spaceExcelFile.delete();
			return true;
		}else 
			return false;

	}
	
	public boolean exportDataToExcel() {
		
		action.click1(exportBtn, "Export Space Btn");
		action.explicitWait(driver, excelSheetBtn, Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		action.click1(excelSheetBtn, "Excel Sheet Btn");
		String filepath = action.isFileDownloaded("Spaces Report", ".xlsx", 30);

		ExcelLibrary excel = new ExcelLibrary(filepath);
		int excelRows = excel.getRowCount("Worksheet");
		String dataTableRowsS = driver.findElement(totalRecordCount).getText().trim();
		String[] splitData = dataTableRowsS.split("of");
		dataTableRowsS = splitData[1];
		dataTableRowsS = dataTableRowsS.replace("entries", "");
		dataTableRowsS = dataTableRowsS.trim();
		int dataTableRows = Integer.parseInt(dataTableRowsS);
		
		if(excelRows-1 == dataTableRows) {
			File spaceExcelFile = new File(filepath);
			spaceExcelFile.delete();
			return true;
		}else 
			return false;
	}
	
	public ImportSpacesPage openImportSpace() {
		
		action.click1(import_space, "import space btn");
		return new ImportSpacesPage(driver);
	}
	
	public boolean deleteNoteFromViewSection(int impNote) throws InterruptedException {
		
		action.explicitWait(driver, driver.findElement(viewSpaceSection), Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		List<WebElement> li = driver.findElements(listOfNotes);
		int initialSize = li.size();
		if(impNote == 1) {
			
			action.explicitWait(driver, driver.findElement(singleImpNoteDeleteBtn), Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
			action.scrollByVisibilityOfElement(driver, driver.findElement(singleImpNoteDeleteBtn));
			action.click1(driver.findElement(singleImpNoteDeleteBtn), "Delete Note Btn");
		}else {
			
			action.explicitWait(driver, driver.findElement(singleNonImpNoteDeleteBtn), Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
			action.scrollByVisibilityOfElement(driver, driver.findElement(singleNonImpNoteDeleteBtn));
			action.click1(driver.findElement(singleNonImpNoteDeleteBtn), "Delete Note Btn");
		}
		
		action.explicitWait(driver, driver.findElement(successOk), Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		action.click1(driver.findElement(successOk), "Success OK Btn");
		
		action.explicitWait(driver, driver.findElement(listOfNotes), 
				Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		
		int counter = 0, retry = 0;
		while(counter == 0) {
		
			li = driver.findElements(listOfNotes);
			if(li.size() < initialSize)
				counter = 1;
			else {
				counter = 0;
				if(retry > 5)
					break;
				retry++;
				Thread.sleep(1000);
			}
		}
		
		boolean verifyImpNote;
		if(impNote == 1) {
			verifyImpNote = action.isDisplayed(driver, mustViewIndicator);
			li = driver.findElements(listOfNotes);
			int newSize = li.size();
			if(verifyImpNote == false && newSize != initialSize)
				return true;
			else 
				return false;
		}else {
			
			li = driver.findElements(listOfNotes);
			int newSize = li.size();
			if(newSize != initialSize)
				return true;
			else 
				return false;
			
		}
			
		
	}
	
	public boolean clickAddNoteWithoutData() throws InterruptedException {
		
		action.explicitWait(driver, driver.findElement(viewSpaceSection), Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		List<WebElement> li = driver.findElements(listOfNotes);
		int initialSize = li.size();
		action.click1(addNote, "Add Note Btn");
		action.click1(noteSaveBtn, "Note Save Btn");
		Thread.sleep(1000);
		boolean checkAlert = action.isAlertPresent(driver);
		if(checkAlert) {
			
			String text = action.getAlertText(driver);
			if(text.contains("Network error, please check your internet"))
				return false;
		}
		
		return true;
		
	}
	
	public String[] addNewNoteViewSection(String noteText, int mustView) throws InterruptedException {
		
		action.explicitWait(driver, driver.findElement(viewSpaceSection), Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		List<WebElement> li = driver.findElements(listOfNotes);
		int initialSize = li.size();
		
		action.click1(addNote, "Add Note Btn");
		action.type(noteFieldViewSection, noteText);
		if(mustView == 1)
			action.click1(noteMustView, "Must View checkbox");
		
		action.click1(noteSaveBtn, "Note Save Btn");
		action.explicitWait(driver, driver.findElement(successOk), Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		action.click1(driver.findElement(successOk), "Success OK Btn");
		action.explicitWait(driver, driver.findElement(listOfNotes), 
				Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		
		int counter = 0, retry = 0;
		while(counter == 0) {
		
			li = driver.findElements(listOfNotes);
			if(li.size() > initialSize)
				counter = 1;
			else {
				counter = 0;
				if(retry > 5)
					break;
				retry++;
				Thread.sleep(1000);
			}
		}
		
		boolean flag;
		String indicator;
		if(mustView == 1) {
			flag = action.isDisplayed(driver, mustViewIndicator);
			if(flag)
				indicator = "1";
			else 
				indicator = "0";
		}else { 
			flag = false;
			indicator = "0";
		}
		String noteTextDisplayed = driver.findElement(noteTextLocator).getText().trim();
		return new String[]{noteTextDisplayed,indicator};

		
			
	}
	
	
	public UpdateSpaceItemPage clickEditButtonViewSpace() {
		
		action.explicitWait(driver, driver.findElement(viewSpaceSection), Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		action.click1(editBtn, "EditBtn");
		return new UpdateSpaceItemPage(driver); 
		
	}
	
	
	public AddNewSpaceItemPage clickAddSpaceBtn() {
		
		action.explicitWait(driver, driver.findElement(spacesPageHeading), Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		//action.click(driver, addSpaceBtn);
		action.JSClick(driver, addSpaceBtn);
		//action.click1(addSpaceBtn, "Click Add Space Btn");
		return new AddNewSpaceItemPage(driver);
	}
	
	public String getAllSpacePageHeading() {
		
		action.explicitWait(driver, driver.findElement(spacesPageHeading), Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		String pageHeadingText =  driver.findElement(spacesPageHeading).getText();
		pageHeadingText.trim();
		return pageHeadingText;
	}
	
	public String[] readFirstRecordDataTable(String spaceName) {
		
		action.explicitWait(driver, driver.findElement(spacesPageHeading), Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		action.type(searchField, spaceName);
		
		String[] Data = new String[5];
		Data[0] = driver.findElement(By.xpath("//*[@id='slip']/tbody/tr[1]/td[2]")).getText().trim();
		Data[1] = driver.findElement(By.xpath("//*[@id='slip']/tbody/tr[1]/td[3]")).getText().trim();
		Data[2] = driver.findElement(By.xpath("//*[@id='slip']/tbody/tr[1]/td[4]")).getText().trim();
		Data[3] = driver.findElement(By.xpath("//*[@id='slip']/tbody/tr[1]/td[5]")).getText().trim();
		Data[4] = driver.findElement(By.xpath("//*[@id='slip']/tbody/tr[1]/td[6]")).getText().trim();
		return Data;
		

	}
	
	public void openSpaceViewPage() {
		
		action.explicitWait(driver, driver.findElement(spacesPageHeading), Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		//action.click(driver, driver.findElement(firstDataTableElement));
		action.click1(driver.findElement(firstDataTableElement), "First DataTableElement");
		action.explicitWait(driver, driver.findElement(viewSpaceSection), Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		
	}
	
	public String[] verifySpaceDataViewSection(String type) throws InterruptedException {
		
		String[] Data = new String[15];
		action.explicitWait(driver, driver.findElement(spacesPageHeading), Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		action.click1(driver.findElement(firstDataTableElement), "First DataTableElement");
		action.explicitWaitPresenceOfElement(driver, viewSpaceSection, Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		action.explicitWait(driver, driver.findElement(viewSpaceSection), Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		
		//Space Name
		Data[0] = driver.findElement(By.xpath("//table[@class='table table-striped fs_14']//th[text()='Name']/following-sibling::td[1]")).getText();
		//Availability
		Data[1] = driver.findElement(By.xpath("//table[@class='table table-striped fs_14']//th[text()='Availability']/following-sibling::td[1]")).getText();
		
		if(Data[1].equalsIgnoreCase("No")) {
			
			//Reason of Unavailability
			Data[2] = driver.findElement(By.xpath("//table[@class='table table-striped fs_14']//th[text()='Reason of Unavailability']/following-sibling::td[1]")).getText();
			//Unavailability Date
			Data[3] = driver.findElement(By.xpath("//table[@class='table table-striped fs_14']//th[text()='Unavailable From - Till']/following-sibling::td[1]")).getText();
		
		}else {
			Data[2] = "";
			Data[3] = "";
		}
		
		//SpaceType
		Data[4] = driver.findElement(By.xpath("//table[@class='table table-striped fs_14']//th[text()='Type']/following-sibling::td[1]")).getText();
		//DocBuffer
		try {
			Data[5] = driver.findElement(By.xpath("//table[@class='table table-striped fs_14']//th[text()='Dock Buffer']/following-sibling::td[1]")).getText().trim();
		}catch(Exception e) {
			Data[5] = "ft";
		}
		//Max LOA
		Data[6] = driver.findElement(By.xpath("//table[@class='table table-striped fs_14']//th[text()='Max LOA']/following-sibling::td[1]")).getText();
		//Max Beam
		Data[7] = driver.findElement(By.xpath("//table[@class='table table-striped fs_14']//th[text()='Max Beam']/following-sibling::td[1]")).getText();
		//Max Draft
		Data[8] = driver.findElement(By.xpath("//table[@class='table table-striped fs_14']//th[text()='Max Draft']/following-sibling::td[1]")).getText();
		//Nearest Slip
		Data[9] = driver.findElement(By.xpath("//table[@class='table table-striped fs_14']//th[text()='Nearest Slip']/following-sibling::td[1]")).getText();
		//Water
		Data[10] = driver.findElement(By.xpath("//table[@class='table table-striped fs_14']//th[text()='Water']/following-sibling::td[1]")).getText();
		//Rafting
		Data[11] = driver.findElement(By.xpath("//table[@class='table table-striped fs_14']//th[text()='Rafting']/following-sibling::td[1]")).getText();
		//Power
		Data[12] = driver.findElement(By.xpath("//table[@class='table table-striped fs_14']//th[text()='Power']/following-sibling::td[1]")).getText();
		//HydroMeter
		Data[13] = driver.findElement(By.xpath("//table[@class='table table-striped fs_14']//th[text()='Hydro Meter']/following-sibling::td[1]")).getText();
		
		if(type.equals("all"))
			Data[14] = driver.findElement(noteTextLocator).getText().trim();
		else
			Data[14] = "";
		action.scrollByVisibilityOfElement(driver, driver.findElement(By.xpath("//h5[text()='"+Data[0]+"']/following-sibling::button[@type='button' and @class='btn-close']")));
		action.click1(driver.findElement(By.xpath("//h5[text()='"+Data[0]+"']/following-sibling::button[@type='button' and @class='btn-close']")),
				"Click close btn of view section");
		return Data;

	}
	
	
	public boolean verifySlipAndDelete(String slipName) throws InterruptedException {
		
		action.explicitWait(driver, driver.findElement(spacesPageHeading), Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		action.type(searchField, slipName);
		
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
					driver.findElement(dataTableFirstRow).click();
					action.explicitWaitVisibility(driver, driver.findElement(viewSpaceSectionDeleteBtn), viewSpaceSectionDeleteBtn, Duration.ofSeconds(10));
					action.explicitWaitElementClickable(driver, driver.findElement(viewSpaceSectionDeleteBtn), Duration.ofSeconds(10));
					action.click1(driver.findElement(viewSpaceSectionDeleteBtn), "click delete btn");
					action.explicitWaitElementClickable(driver, driver.findElement(deleteConfBtn), Duration.ofSeconds(20));
					action.click1(driver.findElement(deleteConfBtn), "click ok btn");
					action.explicitWaitVisibility(driver, driver.findElement(successOk), successOk, Duration.ofSeconds(20));
					action.explicitWaitElementClickable(driver, driver.findElement(successOk), Duration.ofSeconds(20));
					action.click1(driver.findElement(successOk), "click success btn");
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
	

}
