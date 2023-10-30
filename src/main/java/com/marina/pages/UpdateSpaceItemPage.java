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

public class UpdateSpaceItemPage {

	WebDriver driver;
	Action action = new Action();
	
	@FindBy(how = How.XPATH, using = "//h1[@class='mb-0 text-white page-nav fs_22 fw_6 d-flex align-items-center']")
	WebElement addNewSpaceItemHeading;
	
	@FindBy(how = How.ID, using = "select2-space_id-container")
	WebElement type;
	
	@FindBy(how = How.ID, using = "name")
	WebElement name;
	
	@FindBy(how = How.XPATH, using = "//label[@for='availability_checkbox']")
	WebElement status;
	
	@FindBy(how = How.NAME, using = "unavailable_till")
	WebElement unavailableFormTill;
	
	@FindBy(how = How.XPATH, using = "//textarea[@name='reason']")
	WebElement reasonOfUnavailablility;
	
	@FindBy(how = How.ID, using = "max_length")
	WebElement max_loa;
	
	@FindBy(how = How.ID, using = "max_width")
	WebElement max_beam;
	
	@FindBy(how = How.ID, using = "max_draft")
	WebElement max_draft;
	
	@FindBy(how = How.ID, using = "power_rating")
	WebElement power;
	
	@FindBy(how = How.ID, using = "water")
	WebElement water;
	
	@FindBy(how = How.ID, using = "rafting_capable")
	WebElement rafting_capable;
	
	@FindBy(how = How.ID, using = "hydro_meter_id")
	WebElement hydro_meter;
	
	@FindBy(how = How.ID, using = "nearest_slip")
	WebElement nearest_slip;
	
	@FindBy(how = How.ID, using = "picture")
	WebElement upload_pictures;
	
	@FindBy(how = How.NAME, using = "note")
	WebElement note;
	
	@FindBy(how = How.XPATH, using = "//button[@class='btn btn-primary' and text()='Save']")
	WebElement btnSave;
	
	@FindBy(how = How.XPATH, using = "//button[text()='OK']")
	WebElement successOK;
	
	@FindBy(how = How.XPATH, using = "//button[text()='No Task is Required']")
	WebElement btnNoTaskReq;
	
	@FindBy(how = How.XPATH, using = "//button[@class='select2-selection__choice__remove']")
	WebElement removeSelectedHyrdoMeter;
	
	@FindBy(how = How.XPATH, using = "//a[@class='btn btn-danger btn-delete text-white']")
	WebElement removeSelectedPic;
	
	@FindBy(how = How.XPATH, using = "//label[text()='Upload pictures']")
	WebElement pictureText;
	
	
	By linear_buffer = By.id("linear_buffer");
	By first_note = By.xpath("(//a[@class='btn btn-danger btn-delete'])[1]");
	By listOfNotes = By.xpath("//a[@class='btn btn-danger btn-delete']");
	By notesTable = By.xpath("//table[@class='table border d-block rounded mx-w-700']/tbody/tr/td[2]");
	
	
	public UpdateSpaceItemPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	public boolean deleteNote(String note_inputText) throws InterruptedException {
		
		//	This is second test note
		action.explicitWait(driver, addNewSpaceItemHeading, Duration.ofSeconds(10));
		action.scrollByVisibilityOfElement(driver, driver.findElement(first_note));
		action.explicitWait(driver, driver.findElement(first_note), Duration.ofSeconds(10));
		List<WebElement> li = driver.findElements(listOfNotes);
		int initialSize = li.size();
		action.click(driver, driver.findElement(first_note));
		action.explicitWait(driver, successOK, Duration.ofSeconds(10));
		action.click1(successOK, "Success Ok button Note Removal");
		
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
		
		
		boolean noteNotExist = true;
		List<WebElement> notesText = driver.findElements(notesTable);
		for(int i = 0; i < notesText.size(); i++) {
			
			String notes = notesText.get(i).getText().trim();
			if(notes.equals(note_inputText))
				noteNotExist = false;
		
		}
		
		return noteNotExist;
		
	}
	
	public AllSpacesPage updateSpaceWithAllFields(String selectType, String nameText, String availability,
			String maxLOA, String maxBeam, String maxDraft, String selectPower, String selectWater, String raftingCapable,
			String selectHydroMeter, String nearestSlip, String picture, String noteText) throws InterruptedException {
		
		
		action.explicitWait(driver, addNewSpaceItemHeading, Duration.ofSeconds(10));
		
		action.JSClick(driver, removeSelectedPic);
		action.explicitWait(driver, successOK, Duration.ofSeconds(10));
		action.click1(successOK, "Success Ok button Image Removal");
		action.explicitWait(driver, pictureText, Duration.ofSeconds(10));
		action.type(upload_pictures, picture);
		
		List<WebElement> options = driver.findElements(By.xpath("//select[@id='space_id']/option"));

		for (WebElement option : options) {
		    if (option.getText().contains(selectType)) {
		        option.click();
		        break;
		    }
		}
		
		action.type(name, nameText);
		if(availability.equals("No")) {
		
			//action.click1(status, "status");
			String todayDate = action.getCurrentDate(1,0,0,"MMMM d, yyyy");
			String afterDate = action.getCurrentDate(0,1,2,"MMMM d, yyyy");
			
			action.click1(unavailableFormTill, "unavailableFromTill");
			driver.findElement(By.xpath("//span[@aria-label='"+todayDate+"']")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//span[@aria-label='"+afterDate+"']")).click();
			Thread.sleep(500);
			action.type(reasonOfUnavailablility, "Due to some issue");
			
		}
			action.type(max_loa, maxLOA);
			action.type(max_beam, maxBeam);
			action.type(max_draft, maxDraft);
			action.selectByValue(power,selectPower);
			action.selectByVisibleText(selectWater, water);
			action.selectByVisibleText(raftingCapable, rafting_capable);
			//action.click1(removeSelectedHyrdoMeter, "Remove Selected Hydro Meter");
			action.selectByVisibleText(selectHydroMeter, hydro_meter);
			action.selectByVisibleText(nearestSlip, nearest_slip);
			action.type(note, noteText);
			action.JSClick(driver, btnSave);
			action.explicitWait(driver, btnNoTaskReq, Duration.ofSeconds(10));
			action.click1(btnNoTaskReq, "btn no task req");
			return new AllSpacesPage(driver);
		
	}

		
	
	
	
	
}
