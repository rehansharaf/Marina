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
import com.marina.base.TestBase;

public class AddNewSpaceItemPage {

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
	
	@FindBy(how = How.XPATH, using = "//span[@id='select2-hydro_meter_id-container']")
	WebElement hydro_meter_field;
	
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
	
	By linear_buffer = By.id("linear_buffer");

	public AddNewSpaceItemPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	public AllSpacesPage createSpaceWithAllFields(String selectType, String nameText, String availability,
			String maxLOA, String maxBeam, String maxDraft, String selectPower, String selectWater, String raftingCapable,
			String selectHydroMeter, String nearestSlip, String linearDockerBufferText, String picture, String noteText) throws InterruptedException {
		
		
		action.explicitWait(driver, addNewSpaceItemHeading, Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		
		List<WebElement> options = driver.findElements(By.xpath("//select[@id='space_id']/option"));

		for (WebElement option : options) {
		    if (option.getText().contains(selectType)) {
		        option.click();
		        break;
		    }
		}
		
		action.type(name, nameText);
		if(availability.equals("No")) {
		
			action.click1(status, "status");
			String todayDate = action.getCurrentDate(1,0,0,"MMMM d, yyyy");
			String afterDate = action.getCurrentDate(0,1,6,"MMMM d, yyyy");
			
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
			action.selectByVisibleText(selectHydroMeter, hydro_meter);
			action.selectByVisibleText(nearestSlip, nearest_slip);
			action.type(driver.findElement(linear_buffer), linearDockerBufferText);
			action.type(upload_pictures, picture);
			action.type(note, noteText);
			
			boolean isAvailable = action.isDisplayed(driver, hydro_meter_field);
			if(isAvailable)
				action.selectByVisibleText(selectHydroMeter, hydro_meter);
			
			action.explicitWaitElementClickable(driver, btnSave, Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
			action.JSClick(driver, btnSave);
			action.explicitWait(driver, btnNoTaskReq, Duration.ofSeconds(40));
			action.click1(btnNoTaskReq, "btn no task req");
			return new AllSpacesPage(driver);
		
	}
	
	public boolean checkNotAvailibilityStatusAdditionalFields() {
		
		action.explicitWait(driver, addNewSpaceItemHeading, Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		action.click1(status, "status");
		boolean unavailableTillField = action.isDisplayed(driver, unavailableFormTill);
		boolean reasonField = action.isDisplayed(driver, reasonOfUnavailablility);
		driver.navigate().back();
		
		if(unavailableTillField == true && reasonField == true)
			return true;
		else 
			return false;
	}
	
	public AllSpacesPage createNewSpaceMandatoryFields(String selectType, String nameText, String availability,
			String maxLOA, String maxBeam, String maxDraft, String selectPower, String selectWater, String raftingCapable,
			String selectHydroMeter, String nearestSlip, String linearDockerBufferText) throws InterruptedException {
		
		action.explicitWait(driver, addNewSpaceItemHeading, Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		//action.selectByVisibleText(selectType, type);
		
		List<WebElement> options = driver.findElements(By.xpath("//select[@id='space_id']/option"));

		for (WebElement option : options) {
		    if (option.getText().contains(selectType)) {
		        option.click();
		        break;
		    }
		}
		
		
		action.type(name, nameText);
		if(availability.equals("No")) {
		
			action.click1(status, "status");
			String todayDate = action.getCurrentDate(1,0,0,"MMM dd, yyyy");
			String afterDate = action.getCurrentDate(0,1,2,"MMM dd, yyyy");
			
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
			action.selectByVisibleText(selectHydroMeter, hydro_meter);
			action.selectByVisibleText(nearestSlip, nearest_slip);
			action.type(driver.findElement(linear_buffer), linearDockerBufferText);
			Thread.sleep(1000);
			action.JSClick(driver, btnSave);
			action.explicitWait(driver, successOK, Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
			action.click1(successOK, "Success Button");
			return new AllSpacesPage(driver);

			
		}
		
	
	
	
	
}
