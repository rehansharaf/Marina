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
	
	
	By searchField = By.xpath("//div[@id='orders-table_filter']/label/input[@type='search']");
	By paginationNext = By.id("orders-table_next");
	By dataTableReservationSearchRec = By.xpath("//table[@id='orders-table']/tbody/tr/td[4]");
	By dataTableSearchedRow = By.xpath("//table[@id='orders-table']/tbody/tr");
	By reservationViewTab = By.id("customer-details-pop-customer-tab");
	By reservViewActionLink = By.xpath("//button[contains(@id,'action')]");
	By reservReceiptOpt = By.xpath("//a[@class='dropdown-item btn-print-receipt']");
	By iframeReceipt = By.xpath("//iframe[contains(@src,'https://sandbox.dev.clover.com/r/')]");
	By receiptTotalAmt = By.xpath("//span[@class='dollar-amount']");
	By receiptTotalAmtCent = By.xpath("//span[@class='cents']");

	
	public ReservationsPage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public String verifyReservationReceipt(String slipName) throws InterruptedException {
		
		action.explicitWaitVisibility(driver, driver.findElement(paginationNext), paginationNext, Duration.ofSeconds(10));
		action.type(driver.findElement(searchField), slipName);
		action.explicitWaitVisibility(driver, driver.findElement(dataTableReservationSearchRec), dataTableReservationSearchRec, Duration.ofSeconds(10));
		int checkCond = 0;
		while(checkCond == 0) {
			
			if(driver.findElement(dataTableReservationSearchRec).getText().equals(slipName))
					break;
			else
				Thread.sleep(500);
		}
		
			
		action.click1(driver.findElement(dataTableSearchedRow), "click searched row");
		action.explicitWaitVisibility(driver, driver.findElement(reservationViewTab), reservationViewTab, Duration.ofSeconds(10));
		action.scrollByVisibilityOfElement(driver, driver.findElement(reservViewActionLink));
		action.click1(driver.findElement(reservViewActionLink), "Click Action link");
		try {
			action.explicitWaitVisibility(driver, driver.findElement(reservReceiptOpt), reservReceiptOpt, Duration.ofSeconds(10));
			action.click1(driver.findElement(reservReceiptOpt), "Click receipt link");
		}catch(Exception e) {
			
			action.JSClick(driver, driver.findElement(reservViewActionLink));
			Thread.sleep(1000);
			action.JSClick(driver, driver.findElement(reservReceiptOpt));
		}
		
		Thread.sleep(1000);
		boolean iframeFlag = action.switchToFrameByLocator(driver, iframeReceipt);
		
		System.out.println("Frame flag "+iframeFlag);
		action.explicitWaitVisibility(driver, driver.findElement(receiptTotalAmt), receiptTotalAmt, Duration.ofSeconds(10));
		action.scrollByVisibilityOfElement(driver, driver.findElement(receiptTotalAmt));
		String receiptAmt = driver.findElement(receiptTotalAmt).getText();
		receiptAmt = receiptAmt.replaceAll(",", "");
		String receiptAmt_cent = driver.findElement(receiptTotalAmtCent).getText();
		receiptAmt = receiptAmt + "." + receiptAmt_cent;
		return receiptAmt;
		
		
	}

	public void verifyReservationPage() {
	
		action.explicitWaitVisibility(driver, driver.findElement(paginationNext), paginationNext, Duration.ofSeconds(10));
		
	}
	
	
	public boolean searchSlipsAvailableReservation(String[] slipList) {
		
		boolean allExist = true;
		action.explicitWait(driver, reservationTab, Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		action.explicitWait(driver, driver.findElement(searchField), Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		
		for(String slip: slipList) {
		
			driver.findElement(searchField).clear();
			action.type(driver.findElement(searchField), slip);
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
