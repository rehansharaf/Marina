package com.marina.pages;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.marina.actiondriver.Action;
import com.marina.base.TestBase;
import com.marina.utils.Database;

public class CalendarPage {
	
	WebDriver driver;
	Action action = new Action();
	
	@FindBy(how = How.XPATH, using = "//h1[@class='mb-0 text-white page-nav fs_22 fw_6 d-flex align-items-center']")
	WebElement pageHeading;
	
	@FindBy(how = How.XPATH, using = "//td[@class='fc-time-area fc-widget-header']//table/tbody/tr[3]/th")
	List<WebElement> calendarDates;
	
	By hoverSpaceName = By.xpath("//span[@id='tool_space_name']");
	By hoverSpaceType = By.xpath("//span[@id='tool_space_type']/span");
	By hoverSpaceStatus = By.xpath("//span[@id='tool_space_status']/span");
	By hoverSpaceMaxLOA = By.xpath("//span[@id='tool_space_maxLOA']/span");
	By hoverSpaceMaxBeam = By.xpath("//span[@id='tool_space_maxBeam']/span");
	By hoverSpaceMaxDraft = By.xpath("//span[@id='tool_space_maxDraft']/span");
	By hoverSpaceWater = By.xpath("//span[@id='tool_space_water']/span");
	By hoverSpacePower = By.xpath("//span[@id='tool_space_power']/span");
	By hoverSpaceMeter = By.xpath("//span[@id='tool_space_meter']/span");
	By slipDetail = By.xpath("//h5[text()='Slip Details']");
	By slipDetailData = By.xpath("//table[@class='table table-striped fs_14']/tbody/tr[not(@style='display:none;')]/td");
	By closeSlipDetailBtn = By.xpath("//h5[text()='Slip Details']/following-sibling::button[@class='btn-close']");
	By reservationErrorPopup = By.xpath("//h2[text()='Error']");
	By successOk = By.xpath("//button[text()='OK']");
	
	By reservationPopUp = By.xpath("//h5[text()='New Reservation']");
	By nightlyPricingLabel = By.xpath("//label[text()='Nightly']");
	By monthlyPricingLabel = By.xpath("//label[text()='Monthly']");
	By annuallyPricingLabel = By.xpath("//label[text()='Annual']");
	
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
	
	
	public CalendarPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
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
		
		String annual_bgColor = driver.findElement(annuallyPricingLabel).getCssValue("background-color");
		String hex_annual = Color.fromString(annual_bgColor).asHex();
		
		if(hex_nightly.equals("#eceef1") && !hex_monthly.equals("#eceef1") && hex_annual.equals("#eceef1"))
			return true;
		else 
			return false;

		
	}
	

	
	public String addReservation(String spaceType, String rateGroup, String customerName, String slipName, String boatName, int noOfDays, 
			String pricing) throws InterruptedException, ParseException {
		
		boolean defaultPricingSelect, daysDiffCheck;
		action.explicitWaitVisibility(driver, driver.findElement(nightlyPricingLabel), nightlyPricingLabel, Duration.ofSeconds(10));
		action.explicitWaitElementClickable(driver, driver.findElement(nightlyCheckbox), Duration.ofSeconds(10));
		Thread.sleep(1000);
		String bgColor = "";
		if(pricing.equals("nightly")) 
			bgColor = driver.findElement(nightlyPricingLabel).getCssValue("background-color");
		String hex = Color.fromString(bgColor).asHex();
		if(!hex.equals("#eceef1"))
			defaultPricingSelect = true;
		else 
			defaultPricingSelect = false;
		
		
		String startDate = driver.findElement(reservArrivalDate).getAttribute("value");
		String endDate = driver.findElement(reservEndDate).getAttribute("value");
		long daysDiff = action.calculateDaysDiff(startDate, endDate, "MM-dd-yyyy");
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
		
			action.explicitWaitVisibility(driver, driver.findElement(
					By.xpath("//ul[@style='display: block;']/li/div/div[text()='"+boatName+"']/parent::div/parent::li")), 
					By.xpath("//ul[@style='display: block;']/li/div/div[text()='"+boatName+"']/parent::div/parent::li"), Duration.ofSeconds(20));
			action.explicitWaitElementClickable(driver, 
					driver.findElement(By.xpath("//ul[@style='display: block;']/li/div/div[text()='"+boatName+"']/parent::div/parent::li")), Duration.ofSeconds(10));
			
		}catch(StaleElementReferenceException ste) {
			
			Thread.sleep(1000);
			action.explicitWaitVisibility(driver, driver.findElement(
					By.xpath("//ul[@style='display: block;']/li/div/div[text()='Boat2']/parent::div/parent::li")), 
					By.xpath("//ul[@style='display: block;']/li/div/div[text()='Boat2']/parent::div/parent::li"), Duration.ofSeconds(10));

			action.explicitWaitElementClickable(driver, 
					driver.findElement(By.xpath("//ul[@style='display: block;']/li/div/div[text()='"+boatName+"']/parent::div/parent::li")), Duration.ofSeconds(10));

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
		double totalsum = 0;
		
		if(month == endMonth && year == endYear) {
		
			String calculation = driver.findElement(reservationCalc).getText();
			calculationT = driver.findElement(reservationCalcT).getText();
			String[] parts = calculation.split("@");
			slipLength =  parts[0];
			slipLength = slipLength.replace("ft", "");
			slipLength = slipLength.trim();
			
			Database db = new Database();
			int dayRate = db.getPricingDaily(rateGroup, year, month, day);
			
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
				
				int dayRate = db.getPricingDaily(rateGroup, year_ind, month_ind, day_ind);
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
			
			
			
		}
		
	    
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
	
	public void selectDates(String slipName, int numberOfDays, int today) throws InterruptedException {
		
		int counter = 1, fromIndex = 1, toIndex = 1;
		String fromDate, toDate = "";
		action.explicitWait(driver, driver.findElement(By.xpath("//a[text()='"+slipName+"' and @class='slip_details_popup']")), Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		
		if(numberOfDays > 1) {
	
			for(WebElement el: calendarDates) {
				
				String fromAttribute = el.getAttribute("class");
				String dateAttribute = el.getAttribute("data-date");
				if(fromAttribute.contains("fc-today")) {
					fromIndex = counter;
					fromDate = dateAttribute;
					toDate = action.getCurrentDate(0, 1, numberOfDays-1, "yyyy-MM-dd");
				}
					
					if(dateAttribute.equals(toDate)) {
						
						toIndex = counter;
						break;
					}

				counter++;
			}
			
			
			action.scrollByVisibilityOfElement(driver, driver.findElement(By.xpath("//a[text()='"+slipName+"' and @class='slip_details_popup']")));
			String slip_reference = driver.findElement(By.xpath("//a[text()='"+slipName+"' and @class='slip_details_popup']")).getAttribute("data-id");
			
			action.draganddrop(driver, 
					driver.findElement(By.xpath("//div[@class='fc-scroller-canvas']//tr[@data-resource-id='"+slip_reference+"']/td/div/div/span["+fromIndex+"]")),
					driver.findElement(By.xpath("//div[@class='fc-scroller-canvas']//tr[@data-resource-id='"+slip_reference+"']/td/div/div/span["+toIndex+"]")));
			
			action.explicitWait(driver, driver.findElement(reservationPopUp), Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
					
			
		}else {
			
			for(WebElement el: calendarDates) {
				
				String fromAttribute = el.getAttribute("class");
				String dateAttribute = el.getAttribute("data-date");
				if(today == 1) {
				
					if(fromAttribute.contains("fc-today")) {
						fromIndex = counter;
						fromDate = dateAttribute;
						break;
					}
					
				}else {
					
					if(fromAttribute.contains("fc-future")) {
						fromIndex = counter;
						fromDate = dateAttribute;
						break;
					}
				}
				
					
				counter++;
			}
			
			action.scrollByVisibilityOfElement(driver, driver.findElement(By.xpath("//a[text()='"+slipName+"' and @class='slip_details_popup']")));
			String slip_reference = driver.findElement(By.xpath("//a[text()='"+slipName+"' and @class='slip_details_popup']")).getAttribute("data-id");
			driver.findElement(By.xpath("//div[@class='fc-scroller-canvas']//tr[@data-resource-id='"+slip_reference+"']/td/div/div/span["+fromIndex+"]")).click();
			action.explicitWait(driver, driver.findElement(reservationPopUp), Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));

			
		}
		

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
		action.click1(driver.findElement(By.xpath("//span[@aria-label='"+dateFormat+"']")), "end date selection");
		Thread.sleep(500);
	}
	
	public String getMonth(int month) {
	    return new DateFormatSymbols().getMonths()[month-1];
	}
	
	public boolean reservNotSetForUnavailDates(String slipName, String toDate) throws InterruptedException {
		
		int counter = 1, fromIndex = 1, toIndex = 1;
		action.explicitWait(driver, driver.findElement(By.xpath("//a[text()='"+slipName+"' and @class='slip_details_popup']")), Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		for(WebElement el: calendarDates) {
			
			String fromAttribute = el.getAttribute("class");
			String dateAttribute = el.getAttribute("data-date");
			if(fromAttribute.contains("fc-today")) 
				fromIndex = counter;
			
			if(dateAttribute.equals(toDate)) {
				
				toIndex = counter;
				break;
			}
			
			counter++;
		}
		
		action.scrollByVisibilityOfElement(driver, driver.findElement(By.xpath("//a[text()='"+slipName+"' and @class='slip_details_popup']")));
		String slip_reference = driver.findElement(By.xpath("//a[text()='"+slipName+"' and @class='slip_details_popup']")).getAttribute("data-id");
		int exception = 0;
		
			for(int i = fromIndex; i <= toIndex; i++) {
			
				driver.findElement(By.xpath("//div[@class='fc-scroller-canvas']//tr[@data-resource-id='"+slip_reference+"']/td/div/div/span["+i+"]")).click();
				try {
					action.explicitWait(driver, driver.findElement(reservationErrorPopup), Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
					action.click1(driver.findElement(successOk), "Click Success OK");
				}catch(Exception e) {
					exception = 1;
					break;
				}
			}
		
			if(exception == 1)
				return false;
			else
				return true;
		
	}
	
	public String[] verifyDetailsOnSlipDetail(String slipName, String slipType) throws InterruptedException {
		
		String[] Data = new String[16];
		action.explicitWait(driver, driver.findElement(By.xpath("//a[text()='"+slipName+"' and @class='slip_details_popup']")), Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		action.scrollByVisibilityOfElement(driver, driver.findElement(By.xpath("//a[text()='"+slipName+"' and @class='slip_details_popup']")));
		Thread.sleep(1000);
		action.JSClick(driver, driver.findElement(By.xpath("//a[text()='"+slipName+"' and @class='slip_details_popup']")));
		action.explicitWait(driver, driver.findElement(slipDetail), Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		
		//List<WebElement> li = driver.findElements(slipDetailData);
		
		//name
		Data[0] = driver.findElement(By.xpath("//table[@class='table table-striped fs_14']//th[text()='Name']/following-sibling::td[1]")).getText().trim();
		//availability
		Data[1] = driver.findElement(By.xpath("//table[@class='table table-striped fs_14']//th[text()='Availability']/following-sibling::td[1]")).getText().trim();
		
		if(slipType.equals("All")) {
		
			//Reason of unavailability
			Data[2] = driver.findElement(By.xpath("//table[@class='table table-striped fs_14']//th[text()='Reason of Unavailability']/following-sibling::td[1]")).getText().trim();
			//Unavailable till
			Data[3] = driver.findElement(By.xpath("//table[@class='table table-striped fs_14']//th[text()='Unavailable From - Till']/following-sibling::td[1]")).getText().trim();

		}else {
			
			Data[2] = "";
			Data[3] = "";
		}
		//type
		Data[4] = driver.findElement(By.xpath("//table[@class='table table-striped fs_14']//th[text()='Type']/following-sibling::td[1]")).getText().trim();
		//dock buffer
		Data[5] = driver.findElement(By.xpath("//table[@class='table table-striped fs_14']//th[text()='Dock Buffer']/following-sibling::td[1]")).getText().trim();
		//max loa
		Data[6] = driver.findElement(By.xpath("//table[@class='table table-striped fs_14']//th[text()='Max LOA']/following-sibling::td[1]")).getText().trim();
		// max beam
		Data[7] = driver.findElement(By.xpath("//table[@class='table table-striped fs_14']//th[text()='Max Beam']/following-sibling::td[1]")).getText().trim();
		//max draft
		Data[8] = driver.findElement(By.xpath("//table[@class='table table-striped fs_14']//th[text()='Max Draft']/following-sibling::td[1]")).getText().trim();
		//nearest slip
		Data[9] = driver.findElement(By.xpath("//table[@class='table table-striped fs_14']//th[text()='Nearest Slip']/following-sibling::td[1]")).getText().trim();
		//water
		Data[10] = driver.findElement(By.xpath("//table[@class='table table-striped fs_14']//th[text()='Water']/following-sibling::td[1]")).getText().trim();
		//rafting
		Data[11] = driver.findElement(By.xpath("//table[@class='table table-striped fs_14']//th[text()='Rafting']/following-sibling::td[1]")).getText().trim();
		//power
		Data[12] = driver.findElement(By.xpath("//table[@class='table table-striped fs_14']//th[text()='Power']/following-sibling::td[1]")).getText().trim();
		//hydro meter
		Data[13] = driver.findElement(By.xpath("//table[@class='table table-striped fs_14']//th[text()='Hydro Meter']/following-sibling::td[1]")).getText().trim();
		
		if(slipType.equals("All")) {
		
			//note
			Data[14] = driver.findElement(By.xpath("//table[@class='table table-striped fs_14']//th[text()='Note']/parent::tr/following-sibling::tr[1]/td/div/div/div[@class='col-12 mb-2']")).getText().trim();
		
		}else
			Data[14] = "";
		
		//meter reading
		Data[15] = driver.findElement(By.xpath("//table[@class='table table-striped fs_14']//th[text()='Meters']/following-sibling::td[1]")).getText().trim();

		action.click1(driver.findElement(closeSlipDetailBtn), "Close Slip Btn");
		return Data;

	}
	
	public String[] verifyDataHoverSpace(String slipName) throws InterruptedException {
		
		String[] Data = new String[9];
		action.explicitWait(driver, driver.findElement(By.xpath("//a[text()='"+slipName+"' and @class='slip_details_popup']")), Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		action.scrollByVisibilityOfElement(driver, driver.findElement(By.xpath("//a[text()='"+slipName+"' and @class='slip_details_popup']")));
		action.mouseOverElement(driver, driver.findElement(By.xpath("//a[text()='"+slipName+"' and @class='slip_details_popup']")));
		action.explicitWait(driver, driver.findElement(hoverSpaceName), Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		action.isDisplayed(driver, driver.findElement(hoverSpaceName));
		
		Data[0] = driver.findElement(hoverSpaceName).getText().trim();
		Data[1] = driver.findElement(hoverSpaceType).getText().trim();
		Data[2] = driver.findElement(hoverSpaceStatus).getText().trim();
		Data[3] = driver.findElement(hoverSpaceMaxLOA).getText().trim();
		Data[4] = driver.findElement(hoverSpaceMaxBeam).getText().trim();
		Data[5] = driver.findElement(hoverSpaceMaxDraft).getText().trim();
		Data[6] = driver.findElement(hoverSpaceWater).getText().trim();
		Data[7] = driver.findElement(hoverSpacePower).getText().trim();
		Data[8] = driver.findElement(hoverSpaceMeter).getText().trim();
		return Data;
		
	}
	
	public boolean verifySpaceOnCalendar(String slipName) {
		
		int elementFound = 0;
		action.explicitWait(driver, pageHeading, Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		try {
			action.explicitWait(driver, driver.findElement(By.xpath("//a[text()='"+slipName+"' and @class='slip_details_popup']")), Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
			elementFound = 1;
		}catch(Exception e) {
			elementFound = 0;
		}	
		if(elementFound == 1)
			return true;
		else 
			return false;
	}
}
