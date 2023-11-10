package com.marina.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.marina.actiondriver.Action;
import com.marina.base.TestBase;

public class ReservationsPage {
	
	WebDriver driver;
	Action action = new Action();
	
	@FindBy(how = How.ID, using = "reservations-tab")
	WebElement reservationTab;
	
	@FindBy(how = How.XPATH, using = "//div[@id='orders-table_filter']/label/input[@type='search']")
	WebElement searchField;
	
	By dataTableReservationSearchRec = By.xpath("//table[@id='orders-table']/tbody/tr/td[4]");
	
	public ReservationsPage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public boolean searchSlipsAvailableReservation(String[] slipList) {
		
		boolean allExist = true;
		action.explicitWait(driver, reservationTab, Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		action.explicitWait(driver, searchField, Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		
		for(String slip: slipList) {
		
			searchField.clear();
			action.type(searchField, slip);
			try {
				action.explicitWait(driver, driver.findElement(dataTableReservationSearchRec), Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
				if(!driver.findElement(dataTableReservationSearchRec).getText().trim().equals(slip)) {
					
					allExist = false;
					return allExist;
				}
					
				
			}catch(Exception e) {
				allExist = false;
				return allExist;
			}
		}
			
		return allExist;
		
	}
	
	
	
	
}
