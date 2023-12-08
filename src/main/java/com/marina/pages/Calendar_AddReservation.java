package com.marina.pages;

import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.PageFactory;

import com.marina.actiondriver.Action;
import com.marina.utils.Database;
import com.marina.utils.Utilities;

public class Calendar_AddReservation {
	
	WebDriver driver;
	Action action = new Action();
	
	
	By nightlyPricingLabel = By.xpath("//label[text()='Nightly']");
	By monthlyPricingLabel = By.xpath("//label[text()='Monthly']");
	By annualPricingLabel = By.xpath("//label[text()='Annual']");
	By nightlyCheckbox = By.xpath("//label[text()='Nightly']/preceding-sibling::input");
	By reservArrivalDate = By.xpath("//input[@name='start']");
	By reservEndDate = By.xpath("//input[@name='end']");
	By returningCustomerField = By.xpath("//input[@name='customer_email']"); 
	By customerEmail = By.xpath("//input[@name='new_customer[email]']");
	By customerContact = By.xpath("//input[@name='new_customer[phone][0][number]']");
	By customerFirst = By.xpath("//input[@name='new_customer[firstName]']");
	By customerLast = By.xpath("//input[@name='new_customer[lastName]']");
	By customerBoat = By.xpath("//input[@name='new_boat[name]']");
	By customerLoa = By.xpath("//input[@name='new_boat[length]']");
	By customerBeam = By.xpath("//input[@name='new_boat[width]']");
	By customerDraft = By.xpath("//input[@name='new_boat[depth]']");
	By customerPower = By.xpath("//select[@name='new_boat[power_rating]']");
	By customerVessel = By.xpath("//select[@name='new_boat[type]']");
	By reservationCalc = By.xpath("//div[contains(@class,'calculation ')]/table/tbody/tr/td[2]");
	By reservationCalcT = By.xpath("//div[contains(@class,'calculation ')]/table/tbody/tr/td[3]");
	By reservationCashBtn = By.xpath("//div[@id='customer_card_container' and not(@style='display: none;')]/following-sibling::div[2]/button[@data-key='Cash']");
	By cashInput = By.xpath("//input[@class='swal2-input']");
	By cashTotal = By.id("swal2-title");
	By okBtn = By.xpath("//button[text()='OK']");
	By totalAmtSuccessReceipt = By.xpath("//td[text()='Amount Received']/parent::tr/td[2]");
	By totalOrderSuccessReceipt = By.xpath("//td[text()='Order Total']/parent::tr/td[2]");
	By totalReturnAmtSuccessReceipt = By.xpath("//td[text()='Return Amount']/parent::tr/td[2]");
	By calendarYearBeforeSelection = By.xpath("//div[@class='flatpickr-calendar animate static open arrowTop arrowLeft']//input[@aria-label='Year']");							
	By calendarYear = By.xpath("//div[@class='flatpickr-calendar animate static open arrowTop arrowLeft']//input[@aria-label='Year']");						
	By calendarMonth = By.xpath("//div[@class='flatpickr-calendar animate static open arrowTop arrowLeft']//select[@aria-label='Month']");
	
	
	
	
	public Calendar_AddReservation(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	public String addReservationAnnual(String spaceType, String rateGroup, String customerName, String slipName, String boatName, int noOfDays, 
			String pricing, int newBucket) throws Exception {
		
	
		boolean defaultPricingSelect, daysDiffCheck;
		action.explicitWaitVisibility(driver, driver.findElement(nightlyPricingLabel), nightlyPricingLabel, Duration.ofSeconds(10));
		action.explicitWaitElementClickable(driver, driver.findElement(nightlyCheckbox), Duration.ofSeconds(10));
		Thread.sleep(1000);
		String bgColor = "";
		if(pricing.equals("annual")) {
			
			verifyChangePricingNightlyToAnnual(noOfDays);
			bgColor = driver.findElement(annualPricingLabel).getCssValue("background-color");

		}
		
		
		String hex = Color.fromString(bgColor).asHex();
		if(!hex.equals("#eceef1"))
			defaultPricingSelect = true;
		else 
			defaultPricingSelect = false;
		
		
		String startDate = driver.findElement(reservArrivalDate).getAttribute("value");
		String endDate = driver.findElement(reservEndDate).getAttribute("value");
		long daysDiff = Utilities.calculateDaysDiff(startDate, endDate, "MM-dd-yyyy");
		if(noOfDays == 1) {
		
			if(daysDiff == 1)
				daysDiffCheck = true;
			else
				daysDiffCheck = false;
		
		}else {
			
			if(daysDiff == noOfDays)
				daysDiffCheck = true;
			else
				daysDiffCheck = false;
		}
		
		
		action.type(driver.findElement(returningCustomerField), customerName);
		try {
		
			action.explicitWaitVisibility(driver, driver.findElement(By.xpath("//ul[@style='display: block;']/li/div/div[text()='"+boatName+"']/parent::div/parent::li")), By.xpath("//ul[@style='display: block;']/li/div/div[text()='"+boatName+"']/parent::div/parent::li"), Duration.ofSeconds(20));
			//action.explicitWaitElementClickable(driver, driver.findElement(By.xpath("//ul[@style='display: block;']/li/div/div[text()='"+boatName+"']/parent::div/parent::li")), Duration.ofSeconds(10));
			
		}catch(StaleElementReferenceException ste) {
			
			Thread.sleep(1000);
			action.explicitWaitVisibility(driver, driver.findElement(
					By.xpath("//ul[@style='display: block;']/li/div/div[text()='Boat2']/parent::div/parent::li")), 
					By.xpath("//ul[@style='display: block;']/li/div/div[text()='Boat2']/parent::div/parent::li"), Duration.ofSeconds(10));

			action.explicitWaitElementClickable(driver, 
					driver.findElement(By.xpath("//ul[@style='display: block;']/li/div/div[text()='"+boatName+"']/parent::div/parent::li")), Duration.ofSeconds(10));

		}catch(org.openqa.selenium.TimeoutException ste) {
			
			action.explicitWaitVisibility(driver, driver.findElement(By.xpath("//ul[@style='display: block;']/li/div/div[text()='"+boatName+"']/parent::div/parent::li")), By.xpath("//ul[@style='display: block;']/li/div/div[text()='"+boatName+"']/parent::div/parent::li"), Duration.ofSeconds(20));

		}
		
		
		try {
	
			action.click1(driver.findElement(
					By.xpath("//ul[@style='display: block;']/li/div/div[text()='"+boatName+"']/parent::div/parent::li")), "Click Existing Boat 2");

		}catch(StaleElementReferenceException ste) {
			
			Thread.sleep(1000);
			action.click1(driver.findElement(
					By.xpath("//ul[@style='display: block;']/li/div/div[text()='"+boatName+"']/parent::div/parent::li")), "Click Existing Boat 2");
		}
	
		try {
		
			action.explicitWaitVisibility(driver, driver.findElement(By.xpath("//th[text()='"+spaceType+"']")), 
					By.xpath("//th[text()='"+spaceType+"']"), Duration.ofSeconds(10));
		
		}catch(StaleElementReferenceException ste) {
		
			Thread.sleep(1000);
			action.explicitWaitVisibility(driver, driver.findElement(By.xpath("//th[text()='"+spaceType+"']")), 
					By.xpath("//th[text()='"+spaceType+"']"), Duration.ofSeconds(10));
			
		}catch(NoSuchElementException nse) {
			
			int checkCond = 0;
			while(checkCond == 0) {
			
				try {
					
					action.click1(driver.findElement(
							By.xpath("//ul[@style='display: block;']/li/div/div[text()='"+boatName+"']/parent::div/parent::li")), "Click Existing Boat 2");

					action.explicitWaitVisibility(driver, driver.findElement(By.xpath("//th[text()='"+spaceType+"']")), 
							By.xpath("//th[text()='"+spaceType+"']"), Duration.ofSeconds(10));
					
					checkCond = 1;
					
				}catch(StaleElementReferenceException ste) {
					
					Thread.sleep(1000);
					action.click1(driver.findElement(
							By.xpath("//ul[@style='display: block;']/li/div/div[text()='"+boatName+"']/parent::div/parent::li")), "Click Existing Boat 2");
				
				}catch(Exception e) {}
				
			}
			
		}
		
		
		String spaceTypeName = driver.findElement(By.xpath("//th[text()='"+spaceType+"']/following-sibling::td")).getText();
		spaceTypeName = spaceTypeName.trim();
		
		String[] dateParts = startDate.split("-");
		int month = Integer.parseInt(dateParts[0]);
		int day = Integer.parseInt(dateParts[1]);
		int year = Integer.parseInt(dateParts[2]);
		
		dateParts = endDate.split("-");
		int endMonth = Integer.parseInt(dateParts[0]);
		int endYear = Integer.parseInt(dateParts[2]);
		int endDay = Integer.parseInt(dateParts[1]);
		
		String slipLength = "",actualRateFormated = "", totalAmt = "",calculationT = "";
		
		
		String calculation = driver.findElement(reservationCalc).getText();
		calculationT = driver.findElement(reservationCalcT).getText();
		String[] parts = calculation.split("@");
		slipLength =  parts[0];
		slipLength = slipLength.replace("ft", "");
		slipLength = slipLength.trim();
		
		
		LocalDate startDateDiff = LocalDate.of(year, month, day);    
		LocalDate endDateDiff = LocalDate.of(endYear, endMonth, endDay); 
		Period diff = Period.between(startDateDiff, endDateDiff); 
		//System.out.println("\nDifference between "+ userday +" and "+ today +": " 
		//+ diff.getYears() +" Years and "+ diff.getMonths() +" Months" + " and days "+diff.getDays());
		
		double yearRate;
		
		Database db = new Database();
		
		yearRate = db.getPricingYearly(rateGroup, slipLength, startDate, endDate, "yes");
		
		DecimalFormat df = new DecimalFormat("#.00");
	    actualRateFormated = df.format(yearRate);
    
		totalAmt = driver.findElement(By.xpath("//th[contains(text(),'"+spaceType+" Total')]/following-sibling::td")).getText();
		totalAmt = totalAmt.replace("$", "");
		totalAmt = totalAmt.trim();
		totalAmt = totalAmt.replaceAll(",", "");
		
		
		action.scrollByVisibilityOfElement(driver, driver.findElement(reservationCashBtn));
	    action.click1(driver.findElement(reservationCashBtn), "Click Cash btn");
	    action.explicitWaitVisibility(driver, driver.findElement(cashInput), cashInput, Duration.ofSeconds(10));
	    String totalAmountPay = driver.findElement(cashTotal).getText();
	    totalAmountPay = totalAmountPay.replace("Order Total: $", "");
	    totalAmountPay = totalAmountPay.replace("Enter Received Amount", "");
	    totalAmountPay = totalAmountPay.trim();
	    action.type(driver.findElement(cashInput), totalAmountPay);
	    action.click1(driver.findElement(okBtn), "click ok btn");
	    	
		action.explicitWaitVisibility(driver, driver.findElement(totalAmtSuccessReceipt), totalAmtSuccessReceipt, Duration.ofSeconds(20));
		
		String totalAmount = driver.findElement(totalAmtSuccessReceipt).getText();
		String totalOrder = driver.findElement(totalOrderSuccessReceipt).getText();
		String totalReturn = driver.findElement(totalReturnAmtSuccessReceipt).getText();
		action.click1(driver.findElement(okBtn), "click ok btn");
		
		totalAmount = totalAmount.replace("$", "");
	    totalOrder = totalOrder.replace("$", "");
	    totalReturn = totalReturn.replace("$", "");
	    totalReturn = totalReturn.replace(".00", "");
	    
	    
	    if(actualRateFormated.equals(totalAmt) && 
				defaultPricingSelect == true && daysDiffCheck == true && totalAmountPay.equals(totalAmount) && totalOrder.equals(totalAmountPay) &&
				totalReturn.equals("0"))
			return "true;"+totalAmount;
		else 
			return "false;"+totalAmount;
	    	
	
	}
	
	public boolean verifyChangePriceMonthlyToNightly(String spaceType, String rateGroup, String customerName, String slipName, String boatName, int noOfDays, 
			String pricing, int newBucket) throws Exception {
		
		
		boolean defaultPricingSelect, daysDiffCheck;
		action.explicitWaitVisibility(driver, driver.findElement(nightlyPricingLabel), nightlyPricingLabel, Duration.ofSeconds(10));
		action.explicitWaitElementClickable(driver, driver.findElement(nightlyCheckbox), Duration.ofSeconds(10));
		Thread.sleep(1000);
		String bgColor = "";
		if(pricing.equals("nightly")) 
			bgColor = driver.findElement(nightlyPricingLabel).getCssValue("background-color");
		else if(pricing.equals("monthly")) {

			verifyChangePricingNightlyToMonthly(noOfDays);
			bgColor = driver.findElement(monthlyPricingLabel).getCssValue("background-color");
			
		}else if(pricing.equals("annual"))
			bgColor = driver.findElement(annualPricingLabel).getCssValue("background-color");
		
		
		String hex = Color.fromString(bgColor).asHex();
		if(!hex.equals("#eceef1"))
			defaultPricingSelect = true;
		else 
			defaultPricingSelect = false;
		
		
		String startDate = driver.findElement(reservArrivalDate).getAttribute("value");
		String endDate = driver.findElement(reservEndDate).getAttribute("value");
		long daysDiff = Utilities.calculateDaysDiff(startDate, endDate, "MM-dd-yyyy");
		if(noOfDays == 1) {
		
			if(daysDiff == 1)
				daysDiffCheck = true;
			else
				daysDiffCheck = false;
		
		}else {
			
			if(daysDiff == noOfDays)
				daysDiffCheck = true;
			else
				daysDiffCheck = false;
		}
		
		
		action.type(driver.findElement(returningCustomerField), customerName);
		try {
		
			action.explicitWaitVisibility(driver, driver.findElement(By.xpath("//ul[@style='display: block;']/li/div/div[text()='"+boatName+"']/parent::div/parent::li")), By.xpath("//ul[@style='display: block;']/li/div/div[text()='"+boatName+"']/parent::div/parent::li"), Duration.ofSeconds(20));
			//action.explicitWaitElementClickable(driver, driver.findElement(By.xpath("//ul[@style='display: block;']/li/div/div[text()='"+boatName+"']/parent::div/parent::li")), Duration.ofSeconds(10));
			
		}catch(StaleElementReferenceException ste) {
			
			Thread.sleep(1000);
			action.explicitWaitVisibility(driver, driver.findElement(
					By.xpath("//ul[@style='display: block;']/li/div/div[text()='Boat2']/parent::div/parent::li")), 
					By.xpath("//ul[@style='display: block;']/li/div/div[text()='Boat2']/parent::div/parent::li"), Duration.ofSeconds(10));

			action.explicitWaitElementClickable(driver, 
					driver.findElement(By.xpath("//ul[@style='display: block;']/li/div/div[text()='"+boatName+"']/parent::div/parent::li")), Duration.ofSeconds(10));

		}catch(org.openqa.selenium.TimeoutException ste) {
			
			action.explicitWaitVisibility(driver, driver.findElement(By.xpath("//ul[@style='display: block;']/li/div/div[text()='"+boatName+"']/parent::div/parent::li")), By.xpath("//ul[@style='display: block;']/li/div/div[text()='"+boatName+"']/parent::div/parent::li"), Duration.ofSeconds(20));

		}
		
		
		try {
	
			action.click1(driver.findElement(
					By.xpath("//ul[@style='display: block;']/li/div/div[text()='"+boatName+"']/parent::div/parent::li")), "Click Existing Boat 2");

		}catch(StaleElementReferenceException ste) {
			
			Thread.sleep(1000);
			action.click1(driver.findElement(
					By.xpath("//ul[@style='display: block;']/li/div/div[text()='"+boatName+"']/parent::div/parent::li")), "Click Existing Boat 2");
		}
	
		try {
		
			action.explicitWaitVisibility(driver, driver.findElement(By.xpath("//th[text()='"+spaceType+"']")), 
					By.xpath("//th[text()='"+spaceType+"']"), Duration.ofSeconds(10));
		
		}catch(StaleElementReferenceException ste) {
		
			Thread.sleep(1000);
			action.explicitWaitVisibility(driver, driver.findElement(By.xpath("//th[text()='"+spaceType+"']")), 
					By.xpath("//th[text()='"+spaceType+"']"), Duration.ofSeconds(10));
			
		}catch(NoSuchElementException nse) {
			
			int checkCond = 0;
			while(checkCond == 0) {
			
				try {
					
					action.click1(driver.findElement(
							By.xpath("//ul[@style='display: block;']/li/div/div[text()='"+boatName+"']/parent::div/parent::li")), "Click Existing Boat 2");

					action.explicitWaitVisibility(driver, driver.findElement(By.xpath("//th[text()='"+spaceType+"']")), 
							By.xpath("//th[text()='"+spaceType+"']"), Duration.ofSeconds(10));
					
					checkCond = 1;
					
				}catch(StaleElementReferenceException ste) {
					
					Thread.sleep(1000);
					action.click1(driver.findElement(
							By.xpath("//ul[@style='display: block;']/li/div/div[text()='"+boatName+"']/parent::div/parent::li")), "Click Existing Boat 2");
				
				}catch(Exception e) {}
				
			}
			
		}
		
		
		String spaceTypeName = driver.findElement(By.xpath("//th[text()='"+spaceType+"']/following-sibling::td")).getText();
		spaceTypeName = spaceTypeName.trim();
		
		String[] dateParts = startDate.split("-");
		int month = Integer.parseInt(dateParts[0]);
		int day = Integer.parseInt(dateParts[1]);
		int year = Integer.parseInt(dateParts[2]);
		
		dateParts = endDate.split("-");
		int endMonth = Integer.parseInt(dateParts[0]);
		int endYear = Integer.parseInt(dateParts[2]);
		int endDay = Integer.parseInt(dateParts[1]);
		
		String slipLength = "",actualRateFormated = "", totalAmtMonthly = "",calculationT = "", totalAmtNightly = "";
		
		
		String calculation = driver.findElement(reservationCalc).getText();
		calculationT = driver.findElement(reservationCalcT).getText();
		String[] parts = calculation.split("@");
		slipLength =  parts[0];
		slipLength = slipLength.replace("ft", "");
		slipLength = slipLength.trim();
    
		totalAmtMonthly = driver.findElement(By.xpath("//th[contains(text(),'"+spaceType+" Total')]/following-sibling::td")).getText();
		totalAmtMonthly = totalAmtMonthly.replace("$", "");
		totalAmtMonthly = totalAmtMonthly.trim();
		totalAmtMonthly = totalAmtMonthly.replaceAll(",", "");
		
		
		action.click1(driver.findElement(nightlyCheckbox), "Click nightly checkbox");
		boolean pricingSelected = verifyColorChangeAfterPricingSelection("nightly");
		
		
		Database db = new Database();
		double actualRate_ind = db.getPricingDaily(rateGroup, slipLength, year, month, day, endYear, endMonth, endDay, newBucket);
		
		//float actualRate_ind = Float.valueOf(dayRate / 100f);
		double sum_ind = actualRate_ind * Double.valueOf(slipLength);
		DecimalFormat df = new DecimalFormat("#.00");
	    actualRateFormated = df.format(sum_ind);
    
		totalAmtNightly = driver.findElement(By.xpath("//th[contains(text(),'"+spaceType+" Total')]/following-sibling::td")).getText();
		totalAmtNightly = totalAmtNightly.replace("$", "");
		totalAmtNightly = totalAmtNightly.trim();
		totalAmtNightly = totalAmtNightly.replaceAll(",", "");
		
		if(!totalAmtMonthly.equals(totalAmtNightly) && actualRateFormated.equals(totalAmtNightly))
			return true;
		else 
			return false;
		
	}
	
	public boolean verifyColorChangeAfterPricingSelection(String toPrice) throws Exception {
		
		action.explicitWaitVisibility(driver, driver.findElement(nightlyPricingLabel), nightlyPricingLabel, Duration.ofSeconds(10));
		action.explicitWaitElementClickable(driver, driver.findElement(nightlyCheckbox), Duration.ofSeconds(10));
		
		int checkCond = 0,counter = 0;
		String hexSelected = "",bgColorSelected = "", hexNotSelected1 = "", hexNotSelected2 = "", bgNotSelected1 = "", bgNotSelected2 = "";
		while(checkCond == 0) {
			
			if(toPrice.equalsIgnoreCase("nightly")) 
				action.click1(driver.findElement(nightlyPricingLabel), "click nightly checkbox");
			else if(toPrice.equalsIgnoreCase("monthly"))
				action.click1(driver.findElement(monthlyPricingLabel), "click nightly checkbox");
			else if(toPrice.equalsIgnoreCase("annual"))
				action.click1(driver.findElement(annualPricingLabel), "click nightly checkbox");


			
			if(counter > 10)
				throw new Exception("Pricing is not getting change");
			
			if(toPrice.equalsIgnoreCase("monthly")) {
				
	 			bgColorSelected = driver.findElement(monthlyPricingLabel).getCssValue("background-color");
	 			bgNotSelected1 = driver.findElement(nightlyPricingLabel).getCssValue("background-color");
	 			bgNotSelected2 = driver.findElement(annualPricingLabel).getCssValue("background-color");
	 			hexNotSelected1 = Color.fromString(bgNotSelected1).asHex();
	 			hexNotSelected2 = Color.fromString(bgNotSelected2).asHex();

			}else if(toPrice.equalsIgnoreCase("nightly")) {

	 			bgColorSelected = driver.findElement(nightlyPricingLabel).getCssValue("background-color");
	 			bgNotSelected1 = driver.findElement(monthlyPricingLabel).getCssValue("background-color");
	 			bgNotSelected2 = driver.findElement(annualPricingLabel).getCssValue("background-color");
	 			hexNotSelected1 = Color.fromString(bgNotSelected1).asHex();
	 			hexNotSelected2 = Color.fromString(bgNotSelected2).asHex();

			}else if(toPrice.equalsIgnoreCase("annual")) {
				
	 			bgColorSelected = driver.findElement(annualPricingLabel).getCssValue("background-color");
	 			bgNotSelected1 = driver.findElement(monthlyPricingLabel).getCssValue("background-color");
	 			bgNotSelected2 = driver.findElement(nightlyPricingLabel).getCssValue("background-color");
	 			hexNotSelected1 = Color.fromString(bgNotSelected1).asHex();
	 			hexNotSelected2 = Color.fromString(bgNotSelected2).asHex();

			}
			
			hexSelected = Color.fromString(bgColorSelected).asHex();
			if(!hexSelected.equals("#eceef1"))
				checkCond = 1;
			else {
				Thread.sleep(1000);
				counter++;
			}
		}
		
		
		if(hexNotSelected1.equals("#eceef1") && !hexSelected.equals("#eceef1") && hexNotSelected2.equals("#eceef1"))
			return true;
		else 
			return false;
		
	}
	
	public boolean verifyChangePricingNightlyToAnnual(int daysToSkip) throws Exception {
	
		action.explicitWaitVisibility(driver, driver.findElement(nightlyPricingLabel), nightlyPricingLabel, Duration.ofSeconds(10));
		action.explicitWaitElementClickable(driver, driver.findElement(nightlyCheckbox), Duration.ofSeconds(10));
		String startDate = driver.findElement(reservArrivalDate).getAttribute("value");
		
		action.click1(driver.findElement(reservEndDate), "click end date field");
		String endDate = action.getCurrentDate(0, 1, daysToSkip, "yyyy-MM-dd");
		selectReservationCalendarDate(endDate);
		
		int checkCond = 0,counter = 0;
		String hex_annual = "";
		while(checkCond == 0) {
			
			if(counter > 10)
				throw new Exception("Pricing is not getting change");
			
			String annual_bgColor = driver.findElement(annualPricingLabel).getCssValue("background-color");
			hex_annual = Color.fromString(annual_bgColor).asHex();
			if(!hex_annual.equals("#eceef1"))
				checkCond = 1;
			else {
				Thread.sleep(1000);
				counter++;
			}
		}
		
		String nightly_bgColor = driver.findElement(nightlyPricingLabel).getCssValue("background-color");
		String hex_nightly = Color.fromString(nightly_bgColor).asHex();
		
		String monthly_bgColor = driver.findElement(monthlyPricingLabel).getCssValue("background-color");
		String hex_monthly = Color.fromString(monthly_bgColor).asHex();
		
		if(hex_nightly.equals("#eceef1") && hex_monthly.equals("#eceef1") && !hex_annual.equals("#eceef1"))
			return true;
		else 
			return false;

		
	
	}
	
	public boolean verifyChangePricingNightlyToMonthly(int daysToSkip) throws Exception {
		
		action.explicitWaitVisibility(driver, driver.findElement(nightlyPricingLabel), nightlyPricingLabel, Duration.ofSeconds(10));
		action.explicitWaitElementClickable(driver, driver.findElement(nightlyCheckbox), Duration.ofSeconds(10));
		String startDate = driver.findElement(reservArrivalDate).getAttribute("value");
		
		action.click1(driver.findElement(reservEndDate), "click end date field");
		String endDate = action.getCurrentDate(0, 1, daysToSkip, "yyyy-MM-dd");
		selectReservationCalendarDate(endDate);
		
		int checkCond = 0,counter = 0;
		String hex_monthly = "";
		while(checkCond == 0) {
			
			if(counter > 10)
				throw new Exception("Pricing is not getting change");
			
			String monthly_bgColor = driver.findElement(monthlyPricingLabel).getCssValue("background-color");
			hex_monthly = Color.fromString(monthly_bgColor).asHex();
			if(!hex_monthly.equals("#eceef1"))
				checkCond = 1;
			else {
				Thread.sleep(1000);
				counter++;
			}
		}
		
		String nightly_bgColor = driver.findElement(nightlyPricingLabel).getCssValue("background-color");
		String hex_nightly = Color.fromString(nightly_bgColor).asHex();
		
		String annual_bgColor = driver.findElement(annualPricingLabel).getCssValue("background-color");
		String hex_annual = Color.fromString(annual_bgColor).asHex();
		
		if(hex_nightly.equals("#eceef1") && !hex_monthly.equals("#eceef1") && hex_annual.equals("#eceef1"))
			return true;
		else 
			return false;

		
	}
	
	public String addReservation_monthly(String spaceType, String rateGroup, String customerName, String slipName, String boatName, int noOfDays, 
			String pricing, int newBucket) throws Exception {

		boolean defaultPricingSelect, daysDiffCheck;
		action.explicitWaitVisibility(driver, driver.findElement(nightlyPricingLabel), nightlyPricingLabel, Duration.ofSeconds(10));
		action.explicitWaitElementClickable(driver, driver.findElement(nightlyCheckbox), Duration.ofSeconds(10));
		Thread.sleep(1000);
		String bgColor = "";
		if(pricing.equals("nightly")) 
			bgColor = driver.findElement(nightlyPricingLabel).getCssValue("background-color");
		else if(pricing.equals("monthly")) {

			verifyChangePricingNightlyToMonthly(noOfDays);
			bgColor = driver.findElement(monthlyPricingLabel).getCssValue("background-color");
			
		}else if(pricing.equals("annual"))
			bgColor = driver.findElement(annualPricingLabel).getCssValue("background-color");
		
		
		String hex = Color.fromString(bgColor).asHex();
		if(!hex.equals("#eceef1"))
			defaultPricingSelect = true;
		else 
			defaultPricingSelect = false;
		
		
		String startDate = driver.findElement(reservArrivalDate).getAttribute("value");
		String endDate = driver.findElement(reservEndDate).getAttribute("value");
		long daysDiff = Utilities.calculateDaysDiff(startDate, endDate, "MM-dd-yyyy");
		if(noOfDays == 1) {
		
			if(daysDiff == 1)
				daysDiffCheck = true;
			else
				daysDiffCheck = false;
		
		}else {
			
			if(daysDiff == noOfDays)
				daysDiffCheck = true;
			else
				daysDiffCheck = false;
		}
		
		
		action.type(driver.findElement(returningCustomerField), customerName);
		try {
		
			action.explicitWaitVisibility(driver, driver.findElement(By.xpath("//ul[@style='display: block;']/li/div/div[text()='"+boatName+"']/parent::div/parent::li")), By.xpath("//ul[@style='display: block;']/li/div/div[text()='"+boatName+"']/parent::div/parent::li"), Duration.ofSeconds(20));
			//action.explicitWaitElementClickable(driver, driver.findElement(By.xpath("//ul[@style='display: block;']/li/div/div[text()='"+boatName+"']/parent::div/parent::li")), Duration.ofSeconds(10));
			
		}catch(StaleElementReferenceException ste) {
			
			Thread.sleep(1000);
			action.explicitWaitVisibility(driver, driver.findElement(
					By.xpath("//ul[@style='display: block;']/li/div/div[text()='Boat2']/parent::div/parent::li")), 
					By.xpath("//ul[@style='display: block;']/li/div/div[text()='Boat2']/parent::div/parent::li"), Duration.ofSeconds(10));

			action.explicitWaitElementClickable(driver, 
					driver.findElement(By.xpath("//ul[@style='display: block;']/li/div/div[text()='"+boatName+"']/parent::div/parent::li")), Duration.ofSeconds(10));

		}catch(org.openqa.selenium.TimeoutException ste) {
			
			action.explicitWaitVisibility(driver, driver.findElement(By.xpath("//ul[@style='display: block;']/li/div/div[text()='"+boatName+"']/parent::div/parent::li")), By.xpath("//ul[@style='display: block;']/li/div/div[text()='"+boatName+"']/parent::div/parent::li"), Duration.ofSeconds(20));

		}
		
		
		try {
	
			action.click1(driver.findElement(
					By.xpath("//ul[@style='display: block;']/li/div/div[text()='"+boatName+"']/parent::div/parent::li")), "Click Existing Boat 2");

		}catch(StaleElementReferenceException ste) {
			
			Thread.sleep(1000);
			action.click1(driver.findElement(
					By.xpath("//ul[@style='display: block;']/li/div/div[text()='"+boatName+"']/parent::div/parent::li")), "Click Existing Boat 2");
		}
	
		try {
		
			action.explicitWaitVisibility(driver, driver.findElement(By.xpath("//th[text()='"+spaceType+"']")), 
					By.xpath("//th[text()='"+spaceType+"']"), Duration.ofSeconds(10));
		
		}catch(StaleElementReferenceException ste) {
		
			Thread.sleep(1000);
			action.explicitWaitVisibility(driver, driver.findElement(By.xpath("//th[text()='"+spaceType+"']")), 
					By.xpath("//th[text()='"+spaceType+"']"), Duration.ofSeconds(10));
			
		}catch(NoSuchElementException nse) {
			
			int checkCond = 0;
			while(checkCond == 0) {
			
				try {
					
					action.click1(driver.findElement(
							By.xpath("//ul[@style='display: block;']/li/div/div[text()='"+boatName+"']/parent::div/parent::li")), "Click Existing Boat 2");

					action.explicitWaitVisibility(driver, driver.findElement(By.xpath("//th[text()='"+spaceType+"']")), 
							By.xpath("//th[text()='"+spaceType+"']"), Duration.ofSeconds(10));
					
					checkCond = 1;
					
				}catch(StaleElementReferenceException ste) {
					
					Thread.sleep(1000);
					action.click1(driver.findElement(
							By.xpath("//ul[@style='display: block;']/li/div/div[text()='"+boatName+"']/parent::div/parent::li")), "Click Existing Boat 2");
				
				}catch(Exception e) {}
				
			}
			
		}
		
		
		String spaceTypeName = driver.findElement(By.xpath("//th[text()='"+spaceType+"']/following-sibling::td")).getText();
		spaceTypeName = spaceTypeName.trim();
		
		String[] dateParts = startDate.split("-");
		int month = Integer.parseInt(dateParts[0]);
		int day = Integer.parseInt(dateParts[1]);
		int year = Integer.parseInt(dateParts[2]);
		
		dateParts = endDate.split("-");
		int endMonth = Integer.parseInt(dateParts[0]);
		int endYear = Integer.parseInt(dateParts[2]);
		int endDay = Integer.parseInt(dateParts[1]);
		
		String slipLength = "",actualRateFormated = "", totalAmt = "",calculationT = "";
		
		
		String calculation = driver.findElement(reservationCalc).getText();
		calculationT = driver.findElement(reservationCalcT).getText();
		String[] parts = calculation.split("@");
		slipLength =  parts[0];
		slipLength = slipLength.replace("ft", "");
		slipLength = slipLength.trim();
		
		
		LocalDate startDateDiff = LocalDate.of(year, month, day);    
		LocalDate endDateDiff = LocalDate.of(endYear, endMonth, endDay); 
		Period diff = Period.between(startDateDiff, endDateDiff); 
		//System.out.println("\nDifference between "+ userday +" and "+ today +": " 
		//+ diff.getYears() +" Years and "+ diff.getMonths() +" Months" + " and days "+diff.getDays());
		
		int noOfMonths = diff.getMonths();
		int noOfDaysCalc = diff.getDays();
		double  dayRate1 = 0, dayRate2 = 0, rate_ind1 = 0, rate_ind2 = 0;
		
		Database db = new Database();
		
		if(noOfDaysCalc == 0) {
			if(noOfMonths != 0) {
				dayRate1 = db.getPricingMonthly(rateGroup, slipLength, year, month, day, endYear, endMonth, endDay, "yes");
				rate_ind1 = dayRate1 * Float.valueOf(slipLength);
				rate_ind1 = Utilities.roundValue(rate_ind1, 2);


			}
		}else {
		
			LocalDate date = LocalDate.of(endYear,endMonth,endDay).minusDays(noOfDaysCalc);
			System.out.println(date);
			String[] parts1 = date.toString().split("-");
			
			dayRate1 = db.getPricingMonthly(rateGroup, slipLength, year, month, day, Integer.parseInt(parts1[0]), Integer.parseInt(parts1[1]),
					Integer.parseInt(parts1[2]),"yes");
			
			rate_ind1 = dayRate1 * Double.valueOf(slipLength);

			//date = LocalDate.of(Integer.parseInt(parts1[0]),Integer.parseInt(parts1[1]),Integer.parseInt(parts1[2])).plusDays(1);
			date = LocalDate.of(Integer.parseInt(parts1[0]),Integer.parseInt(parts1[1]),Integer.parseInt(parts1[2]));
			parts1 = date.toString().split("-");
			year = Integer.parseInt(parts1[0]);
			month = Integer.parseInt(parts1[1]);
			day = Integer.parseInt(parts1[2]);
			dayRate2 = db.getPricingMonthly_Day(rateGroup, slipLength, year, month, day, endYear, endMonth, endDay, "yes", "yes");
			
			rate_ind2 = dayRate2 * Double.valueOf(slipLength);
			rate_ind2 = Utilities.roundValue(rate_ind2, 2);


		}
		
		double sum_ind = rate_ind1 + rate_ind2;
		
		//float actualRate_ind = Float.valueOf(dayRate / 100f);
		//float sum_ind = actualRate_ind * Float.valueOf(slipLength);
		DecimalFormat df = new DecimalFormat("#.00");
	    actualRateFormated = df.format(sum_ind);
    
		totalAmt = driver.findElement(By.xpath("//th[contains(text(),'"+spaceType+" Total')]/following-sibling::td")).getText();
		totalAmt = totalAmt.replace("$", "");
		totalAmt = totalAmt.trim();
		totalAmt = totalAmt.replaceAll(",", "");
		
	    
	    action.scrollByVisibilityOfElement(driver, driver.findElement(reservationCashBtn));
	    action.click1(driver.findElement(reservationCashBtn), "Click Cash btn");
	    action.explicitWaitVisibility(driver, driver.findElement(cashInput), cashInput, Duration.ofSeconds(10));
	    String totalAmountPay = driver.findElement(cashTotal).getText();
	    totalAmountPay = totalAmountPay.replace("Order Total: $", "");
	    totalAmountPay = totalAmountPay.replace("Enter Received Amount", "");
	    totalAmountPay = totalAmountPay.trim();
	    action.type(driver.findElement(cashInput), totalAmountPay);
	    action.click1(driver.findElement(okBtn), "click ok btn");
	    	
		action.explicitWaitVisibility(driver, driver.findElement(totalAmtSuccessReceipt), totalAmtSuccessReceipt, Duration.ofSeconds(20));
		
		String totalAmount = driver.findElement(totalAmtSuccessReceipt).getText();
		String totalOrder = driver.findElement(totalOrderSuccessReceipt).getText();
		String totalReturn = driver.findElement(totalReturnAmtSuccessReceipt).getText();
		action.click1(driver.findElement(okBtn), "click ok btn");
		
		totalAmount = totalAmount.replace("$", "");
	    totalOrder = totalOrder.replace("$", "");
	    totalReturn = totalReturn.replace("$", "");
	    totalReturn = totalReturn.replace(".00", "");
	
	    
		if(actualRateFormated.equals(totalAmt) && 
				defaultPricingSelect == true && daysDiffCheck == true && totalAmountPay.equals(totalAmount) && totalOrder.equals(totalAmountPay) &&
				totalReturn.equals("0"))
			return "true;"+totalAmount;
		else 
			return "false;"+totalAmount;
			
		//System.out.println("Day Rate is "+dayRate);
	}	
	
	
	public String addReservation(String spaceType, String rateGroup, String customerName, String slipName, String boatName, int noOfDays, 
			String pricing, int newBucket, int daysToSkip) throws Exception {

		boolean defaultPricingSelect, daysDiffCheck;
		action.explicitWaitVisibility(driver, driver.findElement(nightlyPricingLabel), nightlyPricingLabel, Duration.ofSeconds(10));
		action.explicitWaitElementClickable(driver, driver.findElement(nightlyCheckbox), Duration.ofSeconds(10));
		Thread.sleep(1000);
		String bgColor = "";
		if(pricing.equals("nightly")) 
			bgColor = driver.findElement(nightlyPricingLabel).getCssValue("background-color");
		else if(pricing.equals("monthly")) {

			verifyChangePricingNightlyToMonthly(daysToSkip);
			bgColor = driver.findElement(monthlyPricingLabel).getCssValue("background-color");
			
		}else if(pricing.equals("annual"))
			bgColor = driver.findElement(annualPricingLabel).getCssValue("background-color");

		String hex = Color.fromString(bgColor).asHex();
		if(!hex.equals("#eceef1"))
			defaultPricingSelect = true;
		else 
			defaultPricingSelect = false;
		
		
		String startDate = driver.findElement(reservArrivalDate).getAttribute("value");
		String endDate = driver.findElement(reservEndDate).getAttribute("value");
		long daysDiff = Utilities.calculateDaysDiff(startDate, endDate, "MM-dd-yyyy");
		if(noOfDays == 1) {
		
			if(daysDiff == 1)
				daysDiffCheck = true;
			else
				daysDiffCheck = false;
		
		}else {
			
			if(daysDiff == noOfDays)
				daysDiffCheck = true;
			else
				daysDiffCheck = false;
		}
		
		
		action.type(driver.findElement(returningCustomerField), customerName);
		try {
		
			action.explicitWaitVisibility(driver, driver.findElement(By.xpath("//ul[@style='display: block;']/li/div/div[text()='"+boatName+"']/parent::div/parent::li")), By.xpath("//ul[@style='display: block;']/li/div/div[text()='"+boatName+"']/parent::div/parent::li"), Duration.ofSeconds(20));
			//action.explicitWaitElementClickable(driver, driver.findElement(By.xpath("//ul[@style='display: block;']/li/div/div[text()='"+boatName+"']/parent::div/parent::li")), Duration.ofSeconds(10));
			
		}catch(StaleElementReferenceException ste) {
			
			Thread.sleep(1000);
			action.explicitWaitVisibility(driver, driver.findElement(
					By.xpath("//ul[@style='display: block;']/li/div/div[text()='Boat2']/parent::div/parent::li")), 
					By.xpath("//ul[@style='display: block;']/li/div/div[text()='Boat2']/parent::div/parent::li"), Duration.ofSeconds(10));

			action.explicitWaitElementClickable(driver, 
					driver.findElement(By.xpath("//ul[@style='display: block;']/li/div/div[text()='"+boatName+"']/parent::div/parent::li")), Duration.ofSeconds(10));

		}catch(org.openqa.selenium.TimeoutException ste) {
			
			action.explicitWaitVisibility(driver, driver.findElement(By.xpath("//ul[@style='display: block;']/li/div/div[text()='"+boatName+"']/parent::div/parent::li")), By.xpath("//ul[@style='display: block;']/li/div/div[text()='"+boatName+"']/parent::div/parent::li"), Duration.ofSeconds(20));

		}
		
		
		try {
	
			action.click1(driver.findElement(
					By.xpath("//ul[@style='display: block;']/li/div/div[text()='"+boatName+"']/parent::div/parent::li")), "Click Existing Boat 2");

		}catch(StaleElementReferenceException ste) {
			
			Thread.sleep(1000);
			action.click1(driver.findElement(
					By.xpath("//ul[@style='display: block;']/li/div/div[text()='"+boatName+"']/parent::div/parent::li")), "Click Existing Boat 2");
		}
	
		try {
		
			action.explicitWaitVisibility(driver, driver.findElement(By.xpath("//th[text()='"+spaceType+"']")), 
					By.xpath("//th[text()='"+spaceType+"']"), Duration.ofSeconds(10));
		
		}catch(StaleElementReferenceException ste) {
		
			Thread.sleep(1000);
			action.explicitWaitVisibility(driver, driver.findElement(By.xpath("//th[text()='"+spaceType+"']")), 
					By.xpath("//th[text()='"+spaceType+"']"), Duration.ofSeconds(10));
			
		}catch(NoSuchElementException nse) {
			
			int checkCond = 0;
			while(checkCond == 0) {
			
				try {
					
					action.click1(driver.findElement(
							By.xpath("//ul[@style='display: block;']/li/div/div[text()='"+boatName+"']/parent::div/parent::li")), "Click Existing Boat 2");

					action.explicitWaitVisibility(driver, driver.findElement(By.xpath("//th[text()='"+spaceType+"']")), 
							By.xpath("//th[text()='"+spaceType+"']"), Duration.ofSeconds(10));
					
					checkCond = 1;
					
				}catch(StaleElementReferenceException ste) {
					
					Thread.sleep(1000);
					action.click1(driver.findElement(
							By.xpath("//ul[@style='display: block;']/li/div/div[text()='"+boatName+"']/parent::div/parent::li")), "Click Existing Boat 2");
				
				}catch(Exception e) {}
				
			}
			
		}
		
		
		String spaceTypeName = driver.findElement(By.xpath("//th[text()='"+spaceType+"']/following-sibling::td")).getText();
		spaceTypeName = spaceTypeName.trim();
		
		String[] dateParts = startDate.split("-");
		int month = Integer.parseInt(dateParts[0]);
		int day = Integer.parseInt(dateParts[1]);
		int year = Integer.parseInt(dateParts[2]);
		
		dateParts = endDate.split("-");
		int endMonth = Integer.parseInt(dateParts[0]);
		int endYear = Integer.parseInt(dateParts[2]);
		int endDay = Integer.parseInt(dateParts[1]);
		
		String slipLength = "",actualRateFormated = "", totalAmt = "",calculationT = "";
		//String noOfNights = "",pricingrateCalc = "",calculationT = "";
		//double totalsum = 0;
		
		/*if(month == endMonth && year == endYear) {
		
			String calculation = driver.findElement(reservationCalc).getText();
			calculationT = driver.findElement(reservationCalcT).getText();
			String[] parts = calculation.split("@");
			slipLength =  parts[0];
			slipLength = slipLength.replace("ft", "");
			slipLength = slipLength.trim();
			
			Database db = new Database();
			int dayRate = db.getPricingDaily(rateGroup, slipLength, year, month, day, endYear, endMonth, endDay, newBucket);
			
			float actualRate_ind = Float.valueOf(dayRate / 100f);
			float sum_ind = actualRate_ind * Float.valueOf(slipLength);
			DecimalFormat df = new DecimalFormat("#.00");
		    actualRateFormated = df.format(sum_ind);
	    
			totalAmt = driver.findElement(By.xpath("//th[contains(text(),'"+spaceType+" Total')]/following-sibling::td")).getText();
			totalAmt = totalAmt.replace("$", "");
			totalAmt = totalAmt.trim();
			
		}else {
			
			String calculation = driver.findElement(reservationCalc).getText();
			String[] parts = calculation.split("@");
			slipLength =  parts[0];
			slipLength = slipLength.replace("ft", "");
			slipLength = slipLength.trim();
			
			totalAmt = driver.findElement(By.xpath("//th[contains(text(),'"+spaceType+" Total')]/following-sibling::td")).getText();
			totalAmt = totalAmt.replace("$", "");
			totalAmt = totalAmt.trim();

			
			LocalDate start = LocalDate.of(year,month,day);
			LocalDate end = LocalDate.of(endYear,endMonth,endDay);
			Database db = new Database();
			while (!start.equals(end)) {
				//2023-12-03
				String startDateIndvidual = start.toString();
				String[] parts1 = startDateIndvidual.split("-");
				int year_ind = Integer.parseInt(parts1[0]);
				int month_ind = Integer.parseInt(parts1[1]);
				int day_ind = Integer.parseInt(parts1[2]);
				
				
				int dayRate = db.getPricingDaily(rateGroup, slipLength, year_ind, month_ind, day_ind, year_ind, month_ind, day_ind, newBucket);
				float actualRate_ind = Float.valueOf(dayRate / 100f);
				float sum_ind = actualRate_ind * Float.valueOf(slipLength);
				if(actualRateFormated.isEmpty()) {
				
					totalsum = sum_ind;
					DecimalFormat df = new DecimalFormat("#.00");
				    actualRateFormated = df.format(totalsum);
				    
					
				}else {
					
					totalsum = totalsum + sum_ind;
					DecimalFormat df = new DecimalFormat("#.00");
				    actualRateFormated = df.format(totalsum);
				}
				
			    
			    
			    start = start.plusDays(1);
			}
			
			
			
		}*/
		
		String calculation = driver.findElement(reservationCalc).getText();
		calculationT = driver.findElement(reservationCalcT).getText();
		String[] parts = calculation.split("@");
		slipLength =  parts[0];
		slipLength = slipLength.replace("ft", "");
		slipLength = slipLength.trim();
		
		Database db = new Database();
		double actualRate_ind = db.getPricingDaily(rateGroup, slipLength, year, month, day, endYear, endMonth, endDay, newBucket);
		
		//float actualRate_ind = Float.valueOf(dayRate / 100f);
		double sum_ind = actualRate_ind * Double.valueOf(slipLength);
		DecimalFormat df = new DecimalFormat("#.00");
	    actualRateFormated = df.format(sum_ind);
    
		totalAmt = driver.findElement(By.xpath("//th[contains(text(),'"+spaceType+" Total')]/following-sibling::td")).getText();
		totalAmt = totalAmt.replace("$", "");
		totalAmt = totalAmt.trim();
		totalAmt = totalAmt.replaceAll(",", "");
		
	    
	    action.scrollByVisibilityOfElement(driver, driver.findElement(reservationCashBtn));
	    action.click1(driver.findElement(reservationCashBtn), "Click Cash btn");
	    action.explicitWaitVisibility(driver, driver.findElement(cashInput), cashInput, Duration.ofSeconds(10));
	    String totalAmountPay = driver.findElement(cashTotal).getText();
	    totalAmountPay = totalAmountPay.replace("Order Total: $", "");
	    totalAmountPay = totalAmountPay.replace("Enter Received Amount", "");
	    totalAmountPay = totalAmountPay.trim();
	    action.type(driver.findElement(cashInput), totalAmountPay);
	    action.click1(driver.findElement(okBtn), "click ok btn");
	    	
		action.explicitWaitVisibility(driver, driver.findElement(totalAmtSuccessReceipt), totalAmtSuccessReceipt, Duration.ofSeconds(20));
		
		String totalAmount = driver.findElement(totalAmtSuccessReceipt).getText();
		String totalOrder = driver.findElement(totalOrderSuccessReceipt).getText();
		String totalReturn = driver.findElement(totalReturnAmtSuccessReceipt).getText();
		action.click1(driver.findElement(okBtn), "click ok btn");
		
		totalAmount = totalAmount.replace("$", "");
	    totalOrder = totalOrder.replace("$", "");
	    totalReturn = totalReturn.replace("$", "");
	    totalReturn = totalReturn.replace(".00", "");
	
	    
		if(actualRateFormated.equals(totalAmt) && 
				defaultPricingSelect == true && daysDiffCheck == true && totalAmountPay.equals(totalAmount) && totalOrder.equals(totalAmountPay) &&
				totalReturn.equals("0"))
			return "true;"+totalAmount;
		else 
			return "false;"+totalAmount;
			
		//System.out.println("Day Rate is "+dayRate);
	}	
	
	
public void selectReservationCalendarDate(String selectedDate) throws InterruptedException {
		
		String[] parts = selectedDate.split("-");
		String year = parts[0];
		String month = parts[1];
		String day = parts[2];

		String selectedYear = driver.findElement(calendarYearBeforeSelection).getAttribute("min");
		
		if(!selectedYear.equals(year)) {
			
			driver.findElement(calendarYearBeforeSelection).click();
			//driver.findElement(calendarYear).clear();
			Thread.sleep(500);
			action.type(driver.findElement(calendarYear), year);
			Thread.sleep(500);
		}
		
		String monthName = getMonth(Integer.parseInt(month));
		action.selectByVisibleText(monthName, driver.findElement(calendarMonth));
		Thread.sleep(500);
		day = day.replaceFirst("^0+(?!$)", "");
		String dateFormat = monthName+" "+day+", "+year; 
		action.click1(driver.findElement(By.xpath("//span[@class='flatpickr-day' and @aria-label='"+dateFormat+"']")), "end date selection");
		Thread.sleep(500);
	}
	
	public String getMonth(int month) {
	    return new DateFormatSymbols().getMonths()[month-1];
	}

}
