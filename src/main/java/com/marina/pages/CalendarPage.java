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

	
	public CalendarPage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public Calendar_AddReservation selectDates(String slipName, int numberOfDays, int today, int dayAfterTodayDate) throws InterruptedException {
		
		int counter = 1, fromIndex = 1, toIndex = 1, dayAfterTodayCount = 0;
		String fromDate, toDate = "";
		action.explicitWait(driver, driver.findElement(By.xpath("//a[text()='"+slipName+"' and @class='slip_details_popup']")), Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		
		if(numberOfDays > 1) {
	
			for(WebElement el: calendarDates) {
				
				String fromAttribute = el.getAttribute("class");
				String dateAttribute = el.getAttribute("data-date");
				
				if(today == 1) {
				
					if(fromAttribute.contains("fc-today")) {
						fromIndex = counter;
						fromDate = dateAttribute;
						toDate = action.getCurrentDate(0, 1, numberOfDays-1, "yyyy-MM-dd");
					}
					
				}else {
					
					if(fromAttribute.contains("fc-future")) {
						dayAfterTodayCount++;
						if(dayAfterTodayCount == dayAfterTodayDate) {
						
							fromIndex = counter;
							fromDate = dateAttribute;
							toDate = action.getCurrentDate(0, 1, numberOfDays-1 +(dayAfterTodayDate), "yyyy-MM-dd");
						}
						
					}
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
						dayAfterTodayCount++;
						if(dayAfterTodayCount == dayAfterTodayDate) {
							fromIndex = counter;
							fromDate = dateAttribute;
							break;
						}
						
					}
				}
				
					
				counter++;
			}
			
			action.scrollByVisibilityOfElement(driver, driver.findElement(By.xpath("//a[text()='"+slipName+"' and @class='slip_details_popup']")));
			String slip_reference = driver.findElement(By.xpath("//a[text()='"+slipName+"' and @class='slip_details_popup']")).getAttribute("data-id");
			driver.findElement(By.xpath("//div[@class='fc-scroller-canvas']//tr[@data-resource-id='"+slip_reference+"']/td/div/div/span["+fromIndex+"]")).click();
			action.explicitWait(driver, driver.findElement(reservationPopUp), Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));

			
		}
		

		return new Calendar_AddReservation(driver);
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
