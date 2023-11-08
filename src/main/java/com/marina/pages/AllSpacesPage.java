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

import com.marina.actiondriver.Action;
import com.marina.utils.ExcelLibrary;

public class AllSpacesPage {
	
	WebDriver driver;
	Action action = new Action();
	
	@FindBy(how = How.XPATH, using = "//h1[@class='mb-0 text-white page-nav fs_22 fw_6 d-flex align-items-center']")
	WebElement spacesPageHeading;
	
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
	
	@FindBy(how = How.XPATH, using = "//button[text()='OK']")
	WebElement successOK;
	
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
	
	By noSearchRecords = By.xpath("//td[text()='No matching records found']");
	By firstDataTableElement = By.xpath("//*[@id='slip']/tbody/tr");
	By viewSpaceSection = By.xpath("//tr[@class='responsive']/th[text()='Name']");
	By singleNoteDeleteButton = By.xpath("//table[@class='table table-striped fs_14']/tbody/tr[10]/td[1]/div[1]/div[1]/div/a");
	By listOfNotes = By.xpath("//table[@class='table table-striped fs_14']/tbody/tr[10]/td[1]/div/div");
	By noteTextLocator = By.xpath("//table[@class='table table-striped fs_14']/tbody/tr[10]/td[1]/div/div/div");
	By totalRecordCount = By.id("slip_info");
	By openGoogleSheetLink = By.xpath("//a[text()='Open Google Sheet']");
	By googleSheetTitle = By.xpath("//span[@id='docs-title-input-label-inner']");
	By googleSheetFileMenu = By.id("docs-file-menu");
	By googleDownloadMenuItem = By.xpath("//span[@aria-label='Download d']/parent::div");
	By googleDownloadExcelOption = By.xpath("//span[@aria-label='Microsoft Excel (.xlsx) x']/parent::div");
	
	
	public AllSpacesPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean searchSpace(String spaceName) {
		
		boolean spaceNotAvailable;
		
		action.explicitWait(driver, searchField, Duration.ofSeconds(10));
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
		action.explicitWait(driver, googleSheetBtn, Duration.ofSeconds(10));
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
		action.click1(successOK, "Success Ok Btn");
		
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
		action.explicitWait(driver, excelSheetBtn, Duration.ofSeconds(10));
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
		
		action.explicitWait(driver, driver.findElement(viewSpaceSection), Duration.ofSeconds(10));
		List<WebElement> li = driver.findElements(listOfNotes);
		int initialSize = li.size();
		action.explicitWait(driver, driver.findElement(singleNoteDeleteButton), Duration.ofSeconds(10));
		action.click1(driver.findElement(singleNoteDeleteButton), "Delete Note Btn");
		action.explicitWait(driver, successOK, Duration.ofSeconds(10));
		action.click1(successOK, "Success OK Btn");
		
		action.explicitWait(driver, driver.findElement(listOfNotes), 
				Duration.ofSeconds(10));
		
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
	
	public void clickAddNoteWithoutData() {
		
		action.explicitWait(driver, driver.findElement(viewSpaceSection), Duration.ofSeconds(10));
		List<WebElement> li = driver.findElements(listOfNotes);
		int initialSize = li.size();
		action.click1(addNote, "Add Note Btn");
		action.click1(noteSaveBtn, "Note Save Btn");
		action.explicitWait(driver, successOK, Duration.ofSeconds(10));
	}
	
	public String[] addNewNoteViewSection(String noteText, int mustView) throws InterruptedException {
		
		action.explicitWait(driver, driver.findElement(viewSpaceSection), Duration.ofSeconds(10));
		List<WebElement> li = driver.findElements(listOfNotes);
		int initialSize = li.size();
		
		action.click1(addNote, "Add Note Btn");
		action.type(noteFieldViewSection, noteText);
		if(mustView == 1)
			action.click1(noteMustView, "Must View checkbox");
		
		action.click1(noteSaveBtn, "Note Save Btn");
		action.explicitWait(driver, successOK, Duration.ofSeconds(10));
		action.click1(successOK, "Success OK Btn");
		action.explicitWait(driver, driver.findElement(listOfNotes), 
				Duration.ofSeconds(10));
		
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
		
		action.explicitWait(driver, driver.findElement(viewSpaceSection), Duration.ofSeconds(10));
		action.click1(editBtn, "EditBtn");
		return new UpdateSpaceItemPage(driver); 
		
	}
	
	
	public AddNewSpaceItemPage clickAddSpaceBtn() {
		
		action.explicitWait(driver, spacesPageHeading, Duration.ofSeconds(10));
		//action.click(driver, addSpaceBtn);
		action.JSClick(driver, addSpaceBtn);
		//action.click1(addSpaceBtn, "Click Add Space Btn");
		return new AddNewSpaceItemPage(driver);
	}
	
	public String getAllSpacePageHeading() {
		
		String pageHeadingText =  spacesPageHeading.getText();
		pageHeadingText.trim();
		return pageHeadingText;
	}
	
	public String[] readFirstRecordDataTable(String spaceName) {
		
		action.explicitWait(driver, spacesPageHeading, Duration.ofSeconds(10));
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
		
		action.explicitWait(driver, spacesPageHeading, Duration.ofSeconds(10));
		//action.click(driver, driver.findElement(firstDataTableElement));
		action.click1(driver.findElement(firstDataTableElement), "First DataTableElement");
		action.explicitWait(driver, driver.findElement(viewSpaceSection), Duration.ofSeconds(10));
		
	}
	
	public String[] verifySpaceDataViewSection(String type) {
		
		String[] Data = new String[15];
		action.explicitWait(driver, spacesPageHeading, Duration.ofSeconds(10));
		//action.click(driver, driver.findElement(firstDataTableElement));
		action.click1(driver.findElement(firstDataTableElement), "First DataTableElement");
		action.explicitWait(driver, driver.findElement(viewSpaceSection), Duration.ofSeconds(10));
		//Space Name
		Data[0] = driver.findElement(By.xpath("//table[@class='table table-striped fs_14']/tbody/tr/td[1]")).getText();
		//Availability
		Data[1] = driver.findElement(By.xpath("//table[@class='table table-striped fs_14']/tbody/tr/td[2]")).getText();
		
		if(Data[1].equalsIgnoreCase("No")) {
			
			//Reason of Unavailability
			Data[2] = driver.findElement(By.xpath("//table[@class='table table-striped fs_14']/tbody/tr[2]/td")).getText();
			//Unavailability Date
			Data[3] = driver.findElement(By.xpath("//table[@class='table table-striped fs_14']/tbody/tr[3]/td")).getText();
		
		}else {
			Data[2] = "";
			Data[3] = "";
		}
		
		//SpaceType
		Data[4] = driver.findElement(By.xpath("//table[@class='table table-striped fs_14']/tbody/tr[4]/td[1]")).getText();
		//DocBuffer
		Data[5] = driver.findElement(By.xpath("//table[@class='table table-striped fs_14']/tbody/tr[4]/td[2]")).getText().trim();
		//Max LOA
		Data[6] = driver.findElement(By.xpath("//table[@class='table table-striped fs_14']/tbody/tr[5]/td[1]")).getText();
		//Max Beam
		Data[7] = driver.findElement(By.xpath("//table[@class='table table-striped fs_14']/tbody/tr[5]/td[2]")).getText();
		//Max Draft
		Data[8] = driver.findElement(By.xpath("//table[@class='table table-striped fs_14']/tbody/tr[6]/td[1]")).getText();
		//Nearest Slip
		Data[9] = driver.findElement(By.xpath("//table[@class='table table-striped fs_14']/tbody/tr[6]/td[2]")).getText();
		//Water
		Data[10] = driver.findElement(By.xpath("//table[@class='table table-striped fs_14']/tbody/tr[7]/td[1]")).getText();
		//Rafting
		Data[11] = driver.findElement(By.xpath("//table[@class='table table-striped fs_14']/tbody/tr[7]/td[2]")).getText();
		//Power
		Data[12] = driver.findElement(By.xpath("//table[@class='table table-striped fs_14']/tbody/tr[8]/td[1]")).getText();
		//HydroMeter
		Data[13] = driver.findElement(By.xpath("//table[@class='table table-striped fs_14']/tbody/tr[8]/td[2]")).getText();
		
		if(type.equals("all"))
			Data[14] = driver.findElement(noteTextLocator).getText().trim();
		else
			Data[14] = "";
		driver.findElement(By.xpath("//h5[text()='"+Data[0]+"']/following-sibling::button[@type='button' and @class='btn-close']")).click();
		return Data;

	}
	
	

}
