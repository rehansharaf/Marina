package com.marina.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.marina.actiondriver.Action;

public class AllSpacesPage {
	
	WebDriver driver;
	Action action = new Action();
	
	@FindBy(how = How.XPATH, using = "//h1[@class='mb-0 text-white page-nav fs_22 fw_6 d-flex align-items-center']")
	WebElement spacesPageHeading;
	
	@FindBy(how = How.XPATH, using = "//input[@class='form-control form-control-sm']")
	WebElement searchField;
	
	@FindBy(how = How.XPATH, using = "//a[@href = 'https://staging.appedology.pk/marina/spaces/create']")
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
	
	By firstDataTableElement = By.xpath("//*[@id='slip']/tbody/tr");
	By viewSpaceSection = By.xpath("//tr[@class='responsive']/th[text()='Name']");
	By singleNoteDeleteButton = By.xpath("//table[@class='table table-striped fs_14']/tbody/tr[10]/td[1]/div[1]/div[1]/div/a");
	By listOfNotes = By.xpath("//table[@class='table table-striped fs_14']/tbody/tr[10]/td[1]/div/div");
	By noteTextLocator = By.xpath("//table[@class='table table-striped fs_14']/tbody/tr[10]/td[1]/div/div/div");
	
	public AllSpacesPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
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
		Data[0] = driver.findElement(By.xpath("//table[@class='table table-striped fs_14']/tbody/tr/td[1]")).getText();
		Data[1] = driver.findElement(By.xpath("//table[@class='table table-striped fs_14']/tbody/tr/td[2]")).getText();
		
		if(Data[1].equalsIgnoreCase("No")) {
			
			Data[2] = driver.findElement(By.xpath("//table[@class='table table-striped fs_14']/tbody/tr[2]/td")).getText();
			Data[3] = driver.findElement(By.xpath("//table[@class='table table-striped fs_14']/tbody/tr[3]/td")).getText();
		
		}else {
			Data[2] = "";
			Data[3] = "";
		}
		
		Data[4] = driver.findElement(By.xpath("//table[@class='table table-striped fs_14']/tbody/tr[4]/td[1]")).getText();
		Data[5] = driver.findElement(By.xpath("//table[@class='table table-striped fs_14']/tbody/tr[4]/td[2]")).getText().trim();
		Data[6] = driver.findElement(By.xpath("//table[@class='table table-striped fs_14']/tbody/tr[5]/td[1]")).getText();
		Data[7] = driver.findElement(By.xpath("//table[@class='table table-striped fs_14']/tbody/tr[5]/td[2]")).getText();
		Data[8] = driver.findElement(By.xpath("//table[@class='table table-striped fs_14']/tbody/tr[6]/td[1]")).getText();
		Data[9] = driver.findElement(By.xpath("//table[@class='table table-striped fs_14']/tbody/tr[6]/td[2]")).getText();
		Data[10] = driver.findElement(By.xpath("//table[@class='table table-striped fs_14']/tbody/tr[7]/td[1]")).getText();
		Data[11] = driver.findElement(By.xpath("//table[@class='table table-striped fs_14']/tbody/tr[7]/td[2]")).getText();
		Data[12] = driver.findElement(By.xpath("//table[@class='table table-striped fs_14']/tbody/tr[8]/td[1]")).getText();
		Data[13] = driver.findElement(By.xpath("//table[@class='table table-striped fs_14']/tbody/tr[8]/td[2]")).getText();
		
		if(type.equals("all"))
			Data[14] = driver.findElement(noteTextLocator).getText().trim();
		else
			Data[14] = "";
		driver.findElement(By.xpath("//h5[text()='"+Data[0]+"']/following-sibling::button[@type='button' and @class='btn-close']")).click();
		return Data;

	}
	
	

}
