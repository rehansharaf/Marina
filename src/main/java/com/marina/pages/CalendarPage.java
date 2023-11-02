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

public class CalendarPage {
	
	WebDriver driver;
	Action action = new Action();
	
	@FindBy(how = How.XPATH, using = "//h1[@class='mb-0 text-white page-nav fs_22 fw_6 d-flex align-items-center']")
	WebElement pageHeading;
	
	@FindBy(how = How.XPATH, using = "//div[@class='fc-scroller-canvas fc-gutter-right']/div[@class='fc-content']/table/tbody/tr[3]/th")
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
	
	public CalendarPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	

	public void reservNotSetForUnavailDates(String slipName, String toDate) throws InterruptedException {
		
		int counter = 1, fromIndex = 1, toIndex = 1;
		action.explicitWait(driver, driver.findElement(By.xpath("//a[text()='"+slipName+"' and @class='slip_details_popup']")), Duration.ofSeconds(10));
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
			
			for(int i = fromIndex; i <= toIndex; i++) {
			
				driver.findElement(By.xpath("//div[@class='fc-scroller-canvas']//tr[@data-resource-id='"+slip_reference+"']/td/div/div/span["+i+"]")).click();
				action.explicitWait(driver, driver.findElement(reservationErrorPopup), Duration.ofSeconds(10));
				action.click1(driver.findElement(successOk), "Click Success OK");

			}
		
	
		
	}
	
	public String[] verifyDetailsOnSlipDetail(String slipName) throws InterruptedException {
		
		int index = 0;
		String[] Data;
		action.explicitWait(driver, driver.findElement(By.xpath("//a[text()='"+slipName+"' and @class='slip_details_popup']")), Duration.ofSeconds(10));
		action.scrollByVisibilityOfElement(driver, driver.findElement(By.xpath("//a[text()='"+slipName+"' and @class='slip_details_popup']")));
		Thread.sleep(1000);
		action.JSClick(driver, driver.findElement(By.xpath("//a[text()='"+slipName+"' and @class='slip_details_popup']")));
		action.explicitWait(driver, driver.findElement(slipDetail), Duration.ofSeconds(10));
		
		List<WebElement> li = driver.findElements(slipDetailData);
		
		if(li.size() > 13)
			Data = new String[16];
		else
			Data = new String[li.size()];
		
		for(WebElement el: li) {
			if(index == 14)
				Data[index] = driver.findElement(By.xpath("(//table[@class='table table-striped fs_14']/tbody/tr[not(@style='display:none;')]/td)[15]/div/div/div")).getText().trim();
			else
				Data[index] = el.getText().trim();
			
			index++;
			if(index == 16)
				break;
		}
		
		action.click1(driver.findElement(closeSlipDetailBtn), "Close Slip Btn");
		return Data;

	}
	
	public String[] verifyDataHoverSpace(String slipName) throws InterruptedException {
		
		String[] Data = new String[9];
		action.explicitWait(driver, driver.findElement(By.xpath("//a[text()='"+slipName+"' and @class='slip_details_popup']")), Duration.ofSeconds(10));
		action.scrollByVisibilityOfElement(driver, driver.findElement(By.xpath("//a[text()='"+slipName+"' and @class='slip_details_popup']")));
		action.mouseOverElement(driver, driver.findElement(By.xpath("//a[text()='"+slipName+"' and @class='slip_details_popup']")));
		action.explicitWait(driver, driver.findElement(hoverSpaceName), Duration.ofSeconds(10));
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
		action.explicitWait(driver, pageHeading, Duration.ofSeconds(10));
		try {
			action.explicitWait(driver, driver.findElement(By.xpath("//a[text()='"+slipName+"' and @class='slip_details_popup']")), Duration.ofSeconds(10));
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
