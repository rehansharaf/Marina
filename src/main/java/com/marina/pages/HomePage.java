package com.marina.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.marina.actiondriver.Action;
import com.marina.base.TestBase;

public class HomePage {
	
	WebDriver driver;
	Action action = new Action();
	
	@FindBy(how = How.XPATH, using = "//td[@class='dft_page_header']")
	WebElement dashboardText;
	
	@FindBy(how = How.ID, using = "services")
	WebElement navPatientSection;
	
	@FindBy(how = How.XPATH, using = "//a[text()='Search File']")
	WebElement searchFileLink;
	
	
	
	public HomePage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	
	
	public String verifyHomePageHeading() {
	
		action.explicitWait(driver, dashboardText, Duration.ofSeconds(10));
		//utils.explicitWait(driver, dashboardText, Duration.ofSeconds(10));
		return dashboardText.getText();
	}
	
	public SearchFilePage gotoSearchFile() {
		
		action.explicitWait(driver, dashboardText, Duration.ofSeconds(10));
		action.mouseOverElement(driver, navPatientSection);
		//utils.mouseOverElement(driver, navPatientSection);
		searchFileLink.click();
		return new SearchFilePage(driver);
	}
}
