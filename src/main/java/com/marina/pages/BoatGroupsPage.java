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

public class BoatGroupsPage {

	WebDriver driver;
	Action action = new Action();

	@FindBy(how = How.XPATH, using = "//h1[@class='mb-0 text-white page-nav fs_22 fw_6 d-flex align-items-center']")
	WebElement pageHeading;
	
	@FindBy(how = How.XPATH, using = "//button[text()='New Group']")
	WebElement addBoatGroupBtn;
	
	@FindBy(how = How.XPATH, using = "//input[@ng-model='group_search']")
	WebElement searchField;
	
	@FindBy(how = How.XPATH, using = "//button[text()='Add Boat']")
	WebElement addBoatMain;
	
	@FindBy(how = How.XPATH, using = "//a[text()='Edit Group']")
	WebElement editGroupMain;

	
	By addBoatGroupHeading = By.xpath("//h5[@id='modalTitleId' and text()='Add Group']");
	By boatGroupName = By.xpath("//input[@id='inputName']");
	By dateRange = By.xpath("//input[@id='inputDate']");
	By saveBtn = By.xpath("//button[text()='Save']");
	By successOk = By.xpath("//button[text()='OK']");
	By contractsDropDown = By.id("inputContracts");
	By documentsDropDown = By.id("inputDocuments");
	By spaceTypeDropDown = By.id("inputSpace");
	By paymentTypeDropDown = By.id("inputPType");
	By discountDropDown = By.id("inputDiscount");
	By notesField = By.id("inputNote");
	By boatGroupList = By.xpath("//div[@class='row']/div[@class='col-md-6 col-lg-4 card mb-3 col-group-item ng-scope']");
	By boatGroupViewBtn = By.xpath("//a[text()='View']");
	By boatGroupViewPage = By.xpath("//h4[text()='Date Range']");
	By boatGroupDateRange = By.xpath("//h4[text()='Date Range']/following-sibling::p");
	By boatGroupPricingType = By.xpath("//h4[text()='Pricing Type']/following-sibling::li");
	By boatGroupSpaces = By.xpath("//h4[text()='Spaces']/following-sibling::li");
	By boatGroupDiscounts = By.xpath("//h4[text()='Discounts']/following-sibling::li");
	By boatGroupDocuments = By.xpath("//h4[text()='Documents']/following-sibling::li/a");
	By boatGroupContract = By.xpath("//h4[text()='Contract']/following-sibling::li/a");
	By boatGroupNotes = By.xpath("//h4[text()='Notes']/following-sibling::p");
	By viewPageAddBoat = By.xpath("//button[text()='Add Boat']");
	By viewPageAddBoatHeading = By.xpath("//h5[text()='Add Boat(s)']");
	By viewPageAddBoatBtn = By.xpath("//table[@class='table']/parent::div/following-sibling::div/button[@type='submit']");
	By selectedBoatHeading = By.xpath("//h2[@class='ng-binding']");
	By viewPageSelectedBoatTable = By.xpath("//table[@class='table table-responsive']/tbody/tr/td[3]");
	By viewPageRemoveSelectedBoat = By.xpath("//a[text()='Remove']");
	By removeBoatConfBtn = By.xpath("//button[text()='Yes, remove it!']");
	By editPageHeading = By.xpath("//h1[@class='mb-0 text-white page-nav fs_22 fw_6 d-flex align-items-center']");
	By editPageName = By.xpath("//input[@id='inputName']");
	By editPageDocuments = By.xpath("//select[@id='inputDocuments']");
	By editPageSpaceType = By.xpath("//select[@id='inputSpace']");
	By editPagePaymentType = By.xpath("//select[@id='inputPType']");
	By editPageDiscount = By.xpath("//select[@id='inputDiscount']");
	By editPageNotes = By.id("inputNote");
	By editPageSaveBtn = By.xpath("//button[text()='Save']");
	By updatedDocsList = By.xpath("//h4[text()='Documents']/parent::ul/li");
	By updatedPriceTypeList = By.xpath("//h4[text()='Pricing Type']/parent::ul/li");
	By updatedSpaceType = By.xpath("//h4[text()='Spaces']/parent::ul/li");
	By updatedDiscountType = By.xpath("//h4[text()='Discounts']/parent::ul/li");
	By updatedNote = By.xpath("//h4[text()='Notes']/following-sibling::p");
	By copyBtnMain = By.xpath("//button[@ng-click='addGroupPopup(group)']");
	By deleteBtnList = By.xpath("//button[text()='Delete Group']");
	By deleteBoatGroupConf = By.xpath("//button[text()='Yes, delete it!']");
	
	public BoatGroupsPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean deleteBoatGroups() throws InterruptedException {
		
		action.explicitWaitElementClickable(driver, driver.findElement(deleteBtnList), Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		int checkCond = 0, counter = 0;
		while(checkCond == 0) {
			
			if(driver.findElements(deleteBtnList).size() > 0) {
				
				if(counter > 10)
					break; 
				
				action.click1(driver.findElement(deleteBtnList), "Click Delete Btn Boat Group Page");
				action.explicitWaitElementClickable(driver, driver.findElement(deleteBoatGroupConf), Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
				action.click1(driver.findElement(deleteBoatGroupConf), "Click Boat Group Delete Conf Btn");
				action.explicitWaitElementClickable(driver, driver.findElement(successOk), Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
				action.click1(driver.findElement(successOk), "Click Success Ok Btn");

				Thread.sleep(1500);
				counter++;
				
			}else {
				
				checkCond = 1;
			}
		}
		
		if(checkCond == 1)
			return true;
		else
			return false;
 	}
	
	public boolean createCopyBoatGroup(String groupName) throws InterruptedException {
		
		action.explicitWait(driver, pageHeading, Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		action.type(searchField, groupName);
		Thread.sleep(1000);
		
		List<WebElement> boatList = driver.findElements(boatGroupList);
		int checkCond = 0, counter = 0;
		while(checkCond == 0) {			
			if(counter > 3)
				break;
			
			if(boatList.size() == 1)
				checkCond = 1;
			
			else {
				Thread.sleep(500);
				boatList = driver.findElements(boatGroupList);
				
			}
			counter++;
		}
		
		action.click1(driver.findElement(copyBtnMain), "Click Copy Btn Main Page");
		action.explicitWait(driver, driver.findElement(editPageName), Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		action.type(driver.findElement(editPageName), groupName+"_copy");
		action.scrollByVisibilityOfElement(driver, driver.findElement(saveBtn));
		action.click1(driver.findElement(saveBtn), "Clicking Copy Page Save Btn");
		action.explicitWaitElementClickable(driver, driver.findElement(successOk), Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		action.click1(driver.findElement(successOk), "Click Success Ok Btn Copy Page");
		
		action.explicitWait(driver, driver.findElement(By.xpath("//h4[text()='"+groupName+"_copy"+"']")), Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		action.type(searchField, groupName+"_copy");
		Thread.sleep(1000);
		
		boatList = null;
		boatList = driver.findElements(boatGroupList);
		checkCond = 0; counter = 0;
		while(checkCond == 0) {			
			if(counter > 3)
				break;
			
			if(boatList.size() == 1)
				checkCond = 1;
			
			else {
				Thread.sleep(500);
				boatList = driver.findElements(boatGroupList);
				
			}
			counter++;
		}
		
		
		action.explicitWaitElementClickable(driver, driver.findElement(boatGroupViewBtn), Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		action.click1(driver.findElement(boatGroupViewBtn), "Click View Btn Group Page");
		action.explicitWait(driver, driver.findElement(boatGroupViewPage), Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		
		List<WebElement> docList = driver.findElements(updatedDocsList);
		List<WebElement> pricingList = driver.findElements(updatedPriceTypeList);
		List<WebElement> spaceList = driver.findElements(updatedSpaceType);
		List<WebElement> discountList = driver.findElements(updatedDiscountType);
		String updatedNoteText = driver.findElement(updatedNote).getText().trim();
		String noOfBoats = driver.findElement(selectedBoatHeading).getText().trim();

		if(docList.size() == 2 && pricingList.size() == 2 && spaceList.size() == 2 && discountList.size() == 2 &&
				updatedNoteText.equals("This is sample boatgroup note edited") && noOfBoats.equals("0 Boat"))
			return true;
		else
			return false;
		
		
		
	}
	
	public int checkNoOfGroupsCreated(String groupName) throws InterruptedException {
		
		action.explicitWait(driver, pageHeading, Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		action.type(searchField, groupName);
		Thread.sleep(1000);
		
		List<WebElement> boatList = driver.findElements(boatGroupList);
		int checkCond = 0, counter = 0;
		while(checkCond == 0) {			
			if(counter > 3)
				break;
			
			if(boatList.size() == 1)
				checkCond = 1;
			
			else {
				Thread.sleep(500);
				boatList = driver.findElements(boatGroupList);
				
			}
			counter++;
		}
		
		int noOfBoats = boatList.size();
		return noOfBoats;
		
	}
	
	public boolean generatingMultipleRequestsAddGroup(String groupName) throws InterruptedException {
		
		action.explicitWaitElementClickable(driver, addBoatGroupBtn, Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		action.click1(addBoatGroupBtn, "Clicking Add BoatGroup Btn");
		action.explicitWait(driver, driver.findElement(addBoatGroupHeading), Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		
		action.explicitWait(driver, driver.findElement(boatGroupName), Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		action.type(driver.findElement(boatGroupName), groupName);
		
		String todayDate = action.getCurrentDate(1,0,0,"MMMM d, yyyy");
		String afterDate = action.getCurrentDate(0,1,6,"MMMM d, yyyy");
		
		action.click1(driver.findElement(dateRange), "Clicking Date Range Field");
		driver.findElement(By.xpath("//span[@aria-label='"+todayDate+"']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[@aria-label='"+afterDate+"']")).click();
		Thread.sleep(500);

		action.scrollByVisibilityOfElement(driver, driver.findElement(saveBtn));
//		action.JSClick(driver, driver.findElement(saveBtn));
//		action.JSClick(driver, driver.findElement(saveBtn));
//		action.JSClick(driver, driver.findElement(saveBtn));
		action.click1(driver.findElement(saveBtn), "Clicking save btn");
		action.click1(driver.findElement(saveBtn), "Clicking save btn");
		action.click1(driver.findElement(saveBtn), "Clicking save btn");
		
		int checkCond = 0;
		while(checkCond == 0) {
		
			try {
				Thread.sleep(2000);
				action.explicitWaitElementClickable(driver, driver.findElement(successOk), Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
				action.click1(driver.findElement(successOk), "Clicking Success Ok Btn");
			}catch(Exception e) {
				checkCond = 1;
			}
		}
		
		
		try {
			action.explicitWait(driver, driver.findElement(By.xpath("//h4[@class='card-title ng-binding' and text()='"+groupName+"']")), Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
			return true;
		}catch(Exception e) {
			return false;
		}
	}
	
	public boolean checkBoatGroupExist(String groupName) throws InterruptedException {
		
		action.explicitWait(driver, pageHeading, Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		action.type(searchField, groupName);
		Thread.sleep(1000);
		
		List<WebElement> boatList = driver.findElements(boatGroupList);
		int checkCond = 0, counter = 0;
		while(checkCond == 0) {			
			if(counter > 3)
				break;
			
			if(boatList.size() == 1)
				checkCond = 1;
			
			else {
				Thread.sleep(500);
				boatList = driver.findElements(boatGroupList);
				
			}
			counter++;
		}
		
		if(checkCond == 1)
			return true;
		else
			return false;
		
	}
	
	public boolean editBoatGroup(String groupName, String editedGroupName, String docName, String spaceType, String payType,
			String discount, String note) throws InterruptedException {
		
		action.explicitWait(driver, pageHeading, Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		action.type(searchField, groupName);
		Thread.sleep(1000);
		
		List<WebElement> boatList = driver.findElements(boatGroupList);
		int checkCond = 0, counter = 0;
		while(checkCond == 0) {			
			if(counter > 3)
				break;
			
			if(boatList.size() == 1)
				break;
			else {
				Thread.sleep(500);
				boatList = driver.findElements(boatGroupList);
				
			}
			counter++;
		}
		
		action.click1(editGroupMain, "Clicking Edit Group Btn");
		action.explicitWaitElementClickable(driver, driver.findElement(editPageName), Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		driver.findElement(editPageName).clear();
		action.type(driver.findElement(editPageName), editedGroupName);
		action.selectByVisibleText(docName, driver.findElement(editPageDocuments));
		Thread.sleep(500);
		action.selectByVisibleText(spaceType, driver.findElement(editPageSpaceType));
		Thread.sleep(500);
		action.selectByVisibleText(payType, driver.findElement(editPagePaymentType));
		Thread.sleep(500);
		action.selectByVisibleText(discount, driver.findElement(editPageDiscount));
		Thread.sleep(500);
		driver.findElement(editPageNotes).clear();
		action.type(driver.findElement(editPageNotes), note);
		
		action.scrollByVisibilityOfElement(driver, driver.findElement(editPageSaveBtn));
		action.click1(driver.findElement(editPageSaveBtn), "Clicking edit page save btn");
		action.explicitWaitElementClickable(driver, driver.findElement(successOk), Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		action.click1(driver.findElement(successOk), "Click success btn");
		action.explicitWait(driver, driver.findElement(boatGroupViewPage), Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		
		List<WebElement> docList = driver.findElements(updatedDocsList);
		List<WebElement> pricingList = driver.findElements(updatedPriceTypeList);
		List<WebElement> spaceList = driver.findElements(updatedSpaceType);
		List<WebElement> discountList = driver.findElements(updatedDiscountType);
		String updatedNoteText = driver.findElement(updatedNote).getText().trim();
	

		if(docList.size() == 2 && pricingList.size() == 2 && spaceList.size() == 2 && discountList.size() == 2 &&
				updatedNoteText.equals(note))
			return true;
		else
			return false;

		
		
		
		
	}
	
	public boolean addBoatMainPage(String groupName, String boatName) throws InterruptedException {
		
		action.explicitWait(driver, pageHeading, Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		action.type(searchField, groupName);
		Thread.sleep(1000);
		
		List<WebElement> boatList = driver.findElements(boatGroupList);
		int checkCond = 0, counter = 0;
		while(checkCond == 0) {			
			if(counter > 3)
				break;
			
			if(boatList.size() == 1)
				break;
			else {
				Thread.sleep(500);
				boatList = driver.findElements(boatGroupList);
				
			}
			counter++;
		}
		
		
		action.click1(addBoatMain, "Clicking Add Boat Btn Main Page");
		action.explicitWait(driver, driver.findElement(viewPageAddBoatHeading), Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		
		action.explicitWaitElementClickable(driver, 
				driver.findElement(By.xpath("//label[text()='"+boatName+"']/parent::td/preceding-sibling::td/input[@type='checkbox']")),
				Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		
		
		action.click1(driver.findElement(By.xpath("//label[text()='"+boatName+"']/parent::td/preceding-sibling::td/input[@type='checkbox']")), "Selecting Boat checkboz");
		
		checkCond = 0; counter = 0;
		while(checkCond == 0) {
		
			if(counter > 3)
				break;
			
			String boatText = driver.findElement(viewPageAddBoatBtn).getText().trim();
			if(boatText.equals("Add 1 Boat"))
				checkCond = 1;
			else {
				
				Thread.sleep(1000);
				
			}
		}
		
		action.click1(driver.findElement(viewPageAddBoatBtn), "Click view page add boat btn");
		action.explicitWaitElementClickable(driver, driver.findElement(successOk), Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		action.click1(driver.findElement(successOk), "Click success btn");
		
		action.explicitWait(driver, pageHeading, Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		action.type(searchField, groupName);
		Thread.sleep(1000);
		
		boatList = null;
		boatList = driver.findElements(boatGroupList);
		checkCond = 0; counter = 0;
		while(checkCond == 0) {			
			if(counter > 3)
				break;
			
			if(boatList.size() == 1)
				break;
			else {
				Thread.sleep(500);
				boatList = driver.findElements(boatGroupList);
				
			}
			counter++;
		}
		
		String boatCount = driver.findElement(By.xpath("//h4[text()='"+groupName+"']/parent::div/div/div[1]/span")).getText().trim();
		if(boatCount.equals("1")) {
			
			/*action.click1(driver.findElement(boatGroupViewBtn), "Click boat group view btn");
			action.explicitWaitElementClickable(driver, driver.findElement(viewPageAddBoat), Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
			action.scrollByVisibilityOfElement(driver, driver.findElement(viewPageRemoveSelectedBoat));
			action.click1(driver.findElement(viewPageRemoveSelectedBoat), "Click View Page Remove Boat Btn");
			action.explicitWaitElementClickable(driver, driver.findElement(removeBoatConfBtn), Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
			action.click1(driver.findElement(removeBoatConfBtn), "Click remove boat conf btn");
			action.explicitWaitElementClickable(driver, driver.findElement(successOk), Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
			action.click1(driver.findElement(successOk), "Click success btn");
			Thread.sleep(1000);*/
			
			return true;
		}else
			return false;
		
	}
	
	public boolean addBoatViewPage(String groupName, String boatName) throws InterruptedException {
		
		action.explicitWait(driver, pageHeading, Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		action.type(searchField, groupName);
		Thread.sleep(1000);
		
		List<WebElement> boatList = driver.findElements(boatGroupList);
		int checkCond = 0, counter = 0;
		while(checkCond == 0) {			
			if(counter > 3)
				break;
			
			if(boatList.size() == 1)
				break;
			else {
				Thread.sleep(500);
				boatList = driver.findElements(boatGroupList);
				
			}
			counter++;
		}
		
		action.click1(driver.findElement(boatGroupViewBtn), "Click boat group view btn");
		action.explicitWaitElementClickable(driver, driver.findElement(viewPageAddBoat), Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		action.click1(driver.findElement(viewPageAddBoat), "Click View Page Add Boat Btn");
		action.explicitWait(driver, driver.findElement(viewPageAddBoatHeading), Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		
		action.explicitWaitElementClickable(driver, 
				driver.findElement(By.xpath("//label[text()='"+boatName+"']/parent::td/preceding-sibling::td/input[@type='checkbox']")),
				Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		
		
		action.click1(driver.findElement(By.xpath("//label[text()='"+boatName+"']/parent::td/preceding-sibling::td/input[@type='checkbox']")), "Selecting Boat checkboz");
		
		checkCond = 0; counter = 0;
		while(checkCond == 0) {
		
			if(counter > 3)
				break;
			
			String boatText = driver.findElement(viewPageAddBoatBtn).getText().trim();
			if(boatText.equals("Add 1 Boat"))
				checkCond = 1;
			else {
				
				Thread.sleep(1000);
				
			}
		}
		
		action.click1(driver.findElement(viewPageAddBoatBtn), "Click view page add boat btn");
		action.explicitWaitElementClickable(driver, driver.findElement(successOk), Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		action.click1(driver.findElement(successOk), "Click success btn");
		action.explicitWait(driver, driver.findElement(selectedBoatHeading), Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		
		checkCond = 0; counter = 0;
		while(checkCond == 0) {
		
			if(counter > 3)
				break;
			
			String selectedBoatText = driver.findElement(selectedBoatHeading).getText().trim();
			if(selectedBoatText.equals("1 Boat"))
				checkCond = 1;
			else {
				
				Thread.sleep(1000);
				
			}
		}
		
		boolean selectedBoatHeading = false;
		boolean selectedBoatTable = false;
		
		if(checkCond == 1)
			selectedBoatHeading = true;
		
		
		action.scrollByVisibilityOfElement(driver, driver.findElement(viewPageSelectedBoatTable));
		String selectedBoat =  driver.findElement(viewPageSelectedBoatTable).getText().trim();
		if(selectedBoat.equals(boatName))
			selectedBoatTable = true;
		
		
		if(selectedBoatHeading == true && selectedBoatTable == true) {
			
			action.scrollByVisibilityOfElement(driver, driver.findElement(viewPageRemoveSelectedBoat));
			action.click1(driver.findElement(viewPageRemoveSelectedBoat), "Click View Page Remove Boat Btn");
			action.explicitWaitElementClickable(driver, driver.findElement(removeBoatConfBtn), Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
			action.click1(driver.findElement(removeBoatConfBtn), "Click remove boat conf btn");
			action.explicitWaitElementClickable(driver, driver.findElement(successOk), Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
			action.click1(driver.findElement(successOk), "Click success btn");
			Thread.sleep(1000);
			return true;
		}else
			return false;
		
		
		
	}
	
	public String[] verifySavedBoatGroupInfo(String groupName) throws InterruptedException {
		
		String[] Data = new String[7];
		action.explicitWait(driver, pageHeading, Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		action.type(searchField, groupName);
		Thread.sleep(1000);
		
		List<WebElement> boatList = driver.findElements(boatGroupList);
		int checkCond = 0, counter = 0;
		while(checkCond == 0) {			
			if(counter > 3)
				break;
			
			if(boatList.size() == 1)
				break;
			else {
				Thread.sleep(500);
				boatList = driver.findElements(boatGroupList);
				
			}
			counter++;
		}
		
		action.click1(driver.findElement(boatGroupViewBtn), "Click boat group view btn");
		action.explicitWait(driver, driver.findElement(boatGroupViewPage), Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		Data[0] = driver.findElement(boatGroupDateRange).getText().trim();
		Data[1] = driver.findElement(boatGroupPricingType).getText().trim();
		Data[2] = driver.findElement(boatGroupSpaces).getText().trim();
		Data[3] = driver.findElement(boatGroupDiscounts).getText().trim();
		Data[4] = driver.findElement(boatGroupDocuments).getText().trim();
		Data[5] = driver.findElement(boatGroupContract).getText().trim();
		Data[6] = driver.findElement(boatGroupNotes).getText().trim();
		
		return Data;

	}
	
	public int searchBoatGroup(String groupName) throws InterruptedException {
		
		action.explicitWait(driver, pageHeading, Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		action.type(searchField, groupName);
		Thread.sleep(1000);
		
		List<WebElement> boatList = driver.findElements(boatGroupList);
		return boatList.size();
	}
	
	public boolean addBoatGroupAllFields(String groupName, String contract, String document, String spaceType, String paymentType, String discount,
			String notes) throws InterruptedException {
		
		action.explicitWaitElementClickable(driver, addBoatGroupBtn, Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		action.click1(addBoatGroupBtn, "Clicking Add BoatGroup Btn");
		action.explicitWait(driver, driver.findElement(addBoatGroupHeading), Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		
		action.explicitWait(driver, driver.findElement(boatGroupName), Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		action.type(driver.findElement(boatGroupName), groupName);
		action.selectByVisibleText(contract, driver.findElement(contractsDropDown));
		action.selectByVisibleText(document, driver.findElement(documentsDropDown));
		
		String todayDate = action.getCurrentDate(1,0,0,"MMMM d, yyyy");
		String afterDate = action.getCurrentDate(0,1,6,"MMMM d, yyyy");
		
		action.click1(driver.findElement(dateRange), "Clicking Date Range Field");
		driver.findElement(By.xpath("//span[@aria-label='"+todayDate+"']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[@aria-label='"+afterDate+"']")).click();
		Thread.sleep(500);
		
		action.selectByVisibleText(spaceType,driver.findElement(spaceTypeDropDown));
		action.selectByVisibleText(paymentType, driver.findElement(paymentTypeDropDown));
		action.selectByVisibleText(discount, driver.findElement(discountDropDown));
		action.type(driver.findElement(notesField), notes);
		
		action.scrollByVisibilityOfElement(driver, driver.findElement(saveBtn));
		action.JSClick(driver, driver.findElement(saveBtn));
		action.explicitWaitElementClickable(driver, driver.findElement(successOk), Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		action.click1(driver.findElement(successOk), "Clicking Success Ok Btn");
		
		try {
			action.explicitWait(driver, driver.findElement(By.xpath("//h4[text()='"+groupName+"']")), Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
			return true;
		}catch(Exception e) {
			
			return false;
		}
		
		
	}
	
	public boolean addBoatGroupMandatoryFields(String groupName) throws InterruptedException {
		
		action.explicitWaitElementClickable(driver, addBoatGroupBtn, Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		action.click1(addBoatGroupBtn, "Clicking Add BoatGroup Btn");
		action.explicitWait(driver, driver.findElement(addBoatGroupHeading), Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		
		action.explicitWait(driver, driver.findElement(boatGroupName), Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		action.type(driver.findElement(boatGroupName), groupName);
		
		String todayDate = action.getCurrentDate(1,0,0,"MMMM d, yyyy");
		String afterDate = action.getCurrentDate(0,1,6,"MMMM d, yyyy");
		
		action.click1(driver.findElement(dateRange), "Clicking Date Range Field");
		driver.findElement(By.xpath("//span[@aria-label='"+todayDate+"']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[@aria-label='"+afterDate+"']")).click();
		Thread.sleep(500);

		action.scrollByVisibilityOfElement(driver, driver.findElement(saveBtn));
		action.JSClick(driver, driver.findElement(saveBtn));
		//action.click1(driver.findElement(saveBtn), "Clicking save btn");
		action.explicitWaitElementClickable(driver, driver.findElement(successOk), Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		action.click1(driver.findElement(successOk), "Clicking Success Ok Btn");
		
		try {
			action.explicitWait(driver, driver.findElement(By.xpath("//h4[@class='card-title ng-binding' and text()='"+groupName+"']")), Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
			return true;
		}catch(Exception e) {
			return false;
		}
	}
	
	public String verifyBoatGroupPageHeading() {
	
		action.explicitWait(driver, pageHeading, Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		return pageHeading.getText().trim();
		
	}

}
