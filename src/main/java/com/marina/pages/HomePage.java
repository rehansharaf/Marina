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
	
	@FindBy(how = How.XPATH, using = "//span[text()='Denham Bay Marina']")
	WebElement dashboardText;
	
	@FindBy(how = How.XPATH, using = "//div[text()='Spaces']")
	WebElement spacesdropdown;
	
	@FindBy(how = How.XPATH, using = "//div[text()='Space Types']")
	WebElement Spacetypes;
	
	@FindBy(how = How.XPATH, using = "//div[text()='All Spaces']")
	WebElement allspaces;
	
	
	@FindBy(how = How.XPATH, using = "")
	WebElement calendar;
	
	@FindBy(how = How.XPATH, using = "//div[text()='Space Groups']")
	WebElement spacegroups;
	
	@FindBy(how = How.XPATH, using = "//div[text()='Boat Groups']")
	WebElement boatgroups;
	
	
	
	
	
	

	
	
	
	public HomePage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	
	
	public String verifyHomePageHeading() {

		action.explicitWait(driver, dashboardText, Duration.ofSeconds(10));
		// utils.explicitWait(driver, dashboardText, Duration.ofSeconds(10));
		return dashboardText.getText();
	}
	
	
	
	public SpaceTypesPage spaces_dropdown(int a) {
		
		action.click(driver, spacesdropdown);

		if (a == 2) {
			action.click(driver, Spacetypes);

		}

		if (a == 3) {
			action.click(driver, allspaces);

		}

		if (a == 4) {
			action.click(driver, calendar);

		}

		if (a == 5) {
			action.click(driver, spacegroups);

		}

		if (a == 6) {
			action.click(driver, boatgroups);

		}

		a = 0;
		return new SpaceTypesPage(driver);
				
	}
	
	

	
	
	
	
	
	
	
	

	
	
	
}
