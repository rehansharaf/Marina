package com.marina.pages;

import java.time.Duration;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.marina.actiondriver.Action;

public class SpaceGroupsPage {

	WebDriver driver;
	Action action = new Action();
	
	@FindBy(how = How.XPATH, using = "//h1[@class='mb-0 text-white page-nav fs_22 fw_6 d-flex align-items-center']")
	WebElement pageHeading;
	
	@FindBy(how = How.XPATH, using = "//button[@class='btn btn-success btn-md px-4']")
	WebElement addGroup;
	
	By addGroupPageHeading = By.id("modalAddSpaceGroupTitleId");
	By listOfSlipsName = By.xpath("//label[@class='list-group-item ng-scope']/div/div[1]");
	By listOfSpaceName = By.xpath("//label[@class='list-group-item ng-scope']/div/div[2]");
	By closeAddGroupBtn = By.xpath("//h5[@id='modalAddSpaceGroupTitleId']/following-sibling::button[@class='btn-close']");
	By saveBtn = By.xpath("//button[text()='Save']");
	By successOk = By.xpath("//button[text()='OK']");
	By nameInputField = By.id("txtName");
	By searchBySlipName = By.xpath("//input[@ng-model='filterSlipId']");
	By searchBySlipType = By.xpath("//input[@ng-model='filterSpace']");
	By groupList = By.xpath("//table[@class='table']/tbody/tr");
	By deleteConfirmBtn = By.xpath("//button[text()='Yes, delete it!']");
	

	public SpaceGroupsPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	public boolean deletingSpaceGroup(String groupName) throws InterruptedException {
		
		int checkCount = 0;
		action.explicitWait(driver, pageHeading, Duration.ofSeconds(10));
		action.explicitWaitElementClickable(driver, addGroup, Duration.ofSeconds(10));		
		List<WebElement> deleteGroupBtns = driver.findElements(By.xpath("//table[@class='table']/tbody/tr/td[text()='"+groupName+"']/following-sibling::td[2]/div/button[2]"));

		while(deleteGroupBtns.size() == 0) {
		
			if(checkCount > 5)
				break;
			
			Thread.sleep(500);
			deleteGroupBtns = driver.findElements(By.xpath("//table[@class='table']/tbody/tr/td[text()='"+groupName+"']/following-sibling::td[2]/div/button[2]"));
			checkCount++;
		}
		

			checkCount = 0;
			action.click1(driver.findElement(By.xpath("//table[@class='table']/tbody/tr/td[text()='"+groupName+"']/following-sibling::td[2]/div/button[2]")), "Click Group Delete Btn");
			action.explicitWait(driver, driver.findElement(deleteConfirmBtn), Duration.ofSeconds(10));
			action.click1(driver.findElement(deleteConfirmBtn), "Clicking delete Conf Btn");
			action.explicitWaitElementClickable(driver,driver.findElement(successOk), Duration.ofSeconds(10));
			action.click1(driver.findElement(successOk), "Clicking success OK btn");
			Thread.sleep(1000);
			List<WebElement> deleteGroupBtnsUpdate = driver.findElements(By.xpath("//table[@class='table']/tbody/tr/td[text()='"+groupName+"']/following-sibling::td[2]/div/button[2]"));
			while(deleteGroupBtns.size() == deleteGroupBtnsUpdate.size()) {
				
				if(checkCount > 5)
					break;
				
				Thread.sleep(500);
				deleteGroupBtnsUpdate = driver.findElements(By.xpath("//table[@class='table']/tbody/tr/td[text()='"+groupName+"']/following-sibling::td[2]/div/button[2]"));
			}
			
			if(deleteGroupBtns.size() != deleteGroupBtnsUpdate.size())
				return true;
			else
				return false;
		
			
			
	
		
	}
	
	
	public boolean addingGroupSameName(int noOfSlips, String groupName) throws InterruptedException {
		
		String[] Data = new String[noOfSlips];
		int index = 0;
		
		action.explicitWait(driver, pageHeading, Duration.ofSeconds(10));
		action.explicitWaitElementClickable(driver, addGroup, Duration.ofSeconds(10));
		action.click1(addGroup, "Click Add Group Btn");
		action.explicitWait(driver, driver.findElement(listOfSlipsName), Duration.ofSeconds(10));
			
		for(int counter = 1; counter <=noOfSlips; counter++) {
				
			action.click1(driver.findElement(By.xpath("(//label[@class='list-group-item ng-scope']/div/input)["+counter+"]")),
						"Clicking Slips in the list "+counter);
			
			Data[index] = driver.findElement(By.xpath("(//label[@class='list-group-item ng-scope']/div/div[1])["+counter+"]")).getText().trim();
			index++;
		}
		
		action.type(driver.findElement(nameInputField), groupName);
		action.click1(driver.findElement(saveBtn), "Clicking Save Btn");
		action.explicitWaitElementClickable(driver, driver.findElement(successOk), Duration.ofSeconds(10));
		action.click1(driver.findElement(successOk), "Clicking Success Ok Btn");
		action.explicitWait(driver, driver.findElement(By.xpath("//table[@class='table']/tbody/tr/td[text()='"+groupName+"']")), Duration.ofSeconds(10));
		
		List<WebElement> noOfGroups = driver.findElements(groupList);
		List<WebElement> noOfGroupsUpdatedCount = driver.findElements(groupList);
		int checkCond = 0;
		while(noOfGroups.size() == noOfGroupsUpdatedCount.size()) {
			
			if(checkCond > 4)
				break;
			Thread.sleep(500);
			noOfGroupsUpdatedCount = driver.findElements(groupList);
			checkCond++;
			
		}
		
		List<WebElement> createdSlip = driver.findElements(By.xpath("//table[@class='table']/tbody/tr/td[text()='"+groupName+"']/following-sibling::td[1]"));
		if(createdSlip.size() == 2)
			return false;
		else
			return true;
		
	}
	
	public boolean editSpaceGroup(String groupName, String actionType) throws InterruptedException {
		
		int index;
		action.explicitWait(driver, driver.findElement(By.xpath("//table[@class='table']/tbody/tr/td[text()='"+groupName+"']/following-sibling::td[2]/div/button[1]")), 
				Duration.ofSeconds(10));
		
		String noOfSlipsSaved = driver.findElement(By.xpath("//table[@class='table']/tbody/tr/td[text()='"+groupName+"']/following-sibling::td[1]")).getText().trim();
		
		action.click1(driver.findElement(By.xpath("//table[@class='table']/tbody/tr/td[text()='"+groupName+"']/following-sibling::td[2]/div/button[1]")),
				"Clicking Edit Btn Of Specific Space Group");

		action.explicitWait(driver, driver.findElement(listOfSlipsName), Duration.ofSeconds(10));
		List<WebElement> listSlips = driver.findElements(listOfSlipsName);
		for(int i = 0 ; i < listSlips.size(); i++) {
			
			index = i + 1;
			boolean flag = action.isAttribtuePresent(driver.findElement(By.xpath("(//label[@class='list-group-item ng-scope']/div/input)["+index+"]")), "checked");
			if(actionType.equals("deselect")) {
				if(flag) {
				
					action.click1(driver.findElement(By.xpath("(//label[@class='list-group-item ng-scope']/div/input)["+index+"]")), "Click the checked Slip");
					break;
				}
			}else {
				if(flag == false) {
					
					action.click1(driver.findElement(By.xpath("(//label[@class='list-group-item ng-scope']/div/input)["+index+"]")), "Click the checked Slip");
					break;
				}
			}
		}
		
		action.click1(driver.findElement(saveBtn), "Clicking Save Btn");
		action.explicitWait(driver, driver.findElement(successOk), Duration.ofSeconds(10));
		action.click1(driver.findElement(successOk), "Clicking Success Ok");
		action.explicitWait(driver, driver.findElement(By.xpath("//table[@class='table']/tbody/tr/td[text()='"+groupName+"']/following-sibling::td[1]")), Duration.ofSeconds(10));
		
		String noOfSlipsSavedEdited = "";
		int checkCond = 0;
		while(checkCond < 10) {
			
			Thread.sleep(500);
			noOfSlipsSavedEdited = driver.findElement(By.xpath("//table[@class='table']/tbody/tr/td[text()='"+groupName+"']/following-sibling::td[1]")).getText().trim();
			if(!noOfSlipsSaved.equals(noOfSlipsSavedEdited))
				break;
			else
				checkCond++;
		}

		if(!noOfSlipsSaved.equals(noOfSlipsSavedEdited))
			return true;
		else 
			return false;
	}
	
	public boolean searchSpaceByNameType(int checkNoOfRecords) throws InterruptedException {

		boolean checkSearchBySlipName = true, checkSearchBySpace = true;
		action.explicitWait(driver, pageHeading, Duration.ofSeconds(10));
		action.explicitWaitElementClickable(driver, addGroup, Duration.ofSeconds(10));
		action.click1(addGroup, "Click Add Group Btn");
		action.explicitWait(driver, driver.findElement(listOfSlipsName), Duration.ofSeconds(10));
		
		List<WebElement> listOfSlipsExist = driver.findElements(listOfSlipsName);
		if(checkNoOfRecords > listOfSlipsExist.size()) {
			
			if(listOfSlipsExist.size() > 0)
				checkNoOfRecords = 1; 
		}
		
		for(int i = 0; i < checkNoOfRecords; i++) {
			
			int ele = i+1;
			//String selectedSlip = slipList.get(i).getText().trim();
			String selectedSlip = driver.findElement(By.xpath("(//label[@class='list-group-item ng-scope']/div/div[1])["+ele+"]")).getText().trim();
			String selectedSpace = driver.findElement(By.xpath("(//label[@class='list-group-item ng-scope']/div/div[2])["+ele+"]")).getText().trim();			
			
			action.type(driver.findElement(searchBySlipName), selectedSlip);
			Thread.sleep(500);
			List<WebElement> listElements = driver.findElements(listOfSlipsName);
			for(WebElement el : listElements) {
				String elementName = el.getText().trim();
				if(!elementName.equals(selectedSlip)) {
					
					checkSearchBySlipName = false;
					break;
				}
					
			}
			
			driver.findElement(searchBySlipName).clear();
			Thread.sleep(500);
			action.type(driver.findElement(searchBySlipType), selectedSpace);
			Thread.sleep(500);

			listElements = driver.findElements(listOfSpaceName);
			for(WebElement el : listElements) {
				String elementName = el.getText().trim();
				if(!elementName.equals(selectedSpace)) {
					
					checkSearchBySpace = false;
					break;
				}
					
			}
				
			driver.findElement(searchBySlipType).clear();

			if(checkSearchBySlipName == false || checkSearchBySpace == false)
				break;
			
			
		}
		
		
		if(checkSearchBySlipName == true && checkSearchBySpace == true)
			return true;
		else 
			return false;
		
	}
	
	public boolean addSpaceGroup(int noOfSlips, String groupName) throws InterruptedException {
		
		String[] Data = new String[noOfSlips];
		int index = 0, editIndex = 1;
		boolean groupRecordCreated = true;
		boolean editPageSlipSelection = true;
		
		action.explicitWait(driver, pageHeading, Duration.ofSeconds(10));
		action.explicitWaitElementClickable(driver, addGroup, Duration.ofSeconds(10));
		action.click1(addGroup, "Click Add Group Btn");
		action.explicitWait(driver, driver.findElement(listOfSlipsName), Duration.ofSeconds(10));
			
		for(int counter = 1; counter <=noOfSlips; counter++) {
				
			action.click1(driver.findElement(By.xpath("(//label[@class='list-group-item ng-scope']/div/input)["+counter+"]")),
						"Clicking Slips in the list "+counter);
			
			Data[index] = driver.findElement(By.xpath("(//label[@class='list-group-item ng-scope']/div/div[1])["+counter+"]")).getText().trim();
			index++;
		}
		
		action.type(driver.findElement(nameInputField), groupName);
		action.click1(driver.findElement(saveBtn), "Clicking Save Btn");
		action.explicitWaitElementClickable(driver, driver.findElement(successOk), Duration.ofSeconds(10));
		action.click1(driver.findElement(successOk), "Clicking Success Ok Btn");
		action.explicitWait(driver, driver.findElement(By.xpath("//table[@class='table']/tbody/tr/td[text()='"+groupName+"']")), Duration.ofSeconds(10));
		
		List<WebElement> noOfGroups = driver.findElements(groupList);
		List<WebElement> noOfGroupsUpdatedCount = driver.findElements(groupList);
		int checkCond = 0;
		while(noOfGroups.size() == noOfGroupsUpdatedCount.size()) {
			
			if(checkCond > 4)
				break;
			Thread.sleep(500);
			noOfGroupsUpdatedCount = driver.findElements(groupList);
			checkCond++;
			
		}
		
		List<WebElement> createdSlip = driver.findElements(By.xpath("//table[@class='table']/tbody/tr/td[text()='"+groupName+"']/following-sibling::td[1]"));
		String noOfSlipsSaved = "";
		if(createdSlip.size() == 1)
			noOfSlipsSaved = driver.findElement(By.xpath("//table[@class='table']/tbody/tr/td[text()='"+groupName+"']/following-sibling::td[1]")).getText().trim();
		else 
			noOfSlipsSaved = driver.findElement(By.xpath("(//table[@class='table']/tbody/tr/td[text()='"+groupName+"']/following-sibling::td[1])["+createdSlip.size()+"]")).getText().trim();
		
		if(noOfSlips == Integer.parseInt(noOfSlipsSaved))
			groupRecordCreated = true;
		else
			groupRecordCreated = false;
		
		//Clicking Edit Button Of Specific SpaceGroup
		if(createdSlip.size() == 1)
			action.click1(driver.findElement(By.xpath("//table[@class='table']/tbody/tr/td[text()='"+groupName+"']/following-sibling::td[2]/div/button[1]")), 
				"Clicking Space Group Edit Btn");
		else
			action.click1(driver.findElement(By.xpath("(//table[@class='table']/tbody/tr/td[text()='"+groupName+"']/following-sibling::td[2]/div/button[1])["+createdSlip.size()+"]")), 
					"Clicking Space Group Edit Btn");
		
		action.explicitWait(driver, driver.findElement(listOfSlipsName), Duration.ofSeconds(10));
		
		List<WebElement> listSlips = driver.findElements(listOfSlipsName);
		for(WebElement el : listSlips) {

			boolean flag = action.isAttribtuePresent(driver.findElement(By.xpath("(//label[@class='list-group-item ng-scope']/div/input)["+editIndex+"]")), "checked");
			String pickedSlipName = el.getText().trim();
			
			if(flag == true) {
				
				if (!ArrayUtils.contains(Data, pickedSlipName)) {
					editPageSlipSelection = false;
					break;
				}
			}else {
				
				if (ArrayUtils.contains(Data, pickedSlipName)) {
					editPageSlipSelection = false;
					break;
				}
				
			}
			
			editIndex++;
		}
		
		if(groupRecordCreated == true && editPageSlipSelection == true)
			return true;
		else 
			return false;
		
	}
	
	public String[] getSpacesList() {
		
		String[] Data;
		int index = 0;
		
		action.explicitWait(driver, pageHeading, Duration.ofSeconds(10));
		action.explicitWaitElementClickable(driver, addGroup, Duration.ofSeconds(10));
		action.click1(addGroup, "Click Add Group Btn");
		action.explicitWait(driver, driver.findElement(addGroupPageHeading), Duration.ofSeconds(10));
		action.explicitWait(driver, driver.findElement(listOfSlipsName), Duration.ofSeconds(10));
		
		List<WebElement> slips = driver.findElements(listOfSlipsName);
		Data = new String[slips.size()];
		
		for(WebElement el : slips) {
			
			Data[index] = el.getText().trim();
			index++;
		}
	
		action.click1(driver.findElement(closeAddGroupBtn), "Click close btn Add Group page");
		return Data;
		
	}
	
	public String verifyPageHeading() {
		
		action.explicitWait(driver, pageHeading, Duration.ofSeconds(10));
		return pageHeading.getText().trim();
	}
}
