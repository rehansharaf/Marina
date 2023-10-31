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
		return dashboardText.getText();
	}

	public SpaceTypesPage spaces_dropdown_SpaceTypes() {

		try {
		action.explicitWait(driver, spacesdropdown,Duration.ofSeconds(10));
		action.click(driver, spacesdropdown);
		action.explicitWait(driver, Spacetypes, Duration.ofSeconds(10));
		}
		catch (Exception e) {
			
			action.explicitWait(driver, spacesdropdown,Duration.ofSeconds(10));
			action.click(driver, spacesdropdown);
			
		}
		
		
		action.click(driver, Spacetypes);
	
		return new SpaceTypesPage(driver);

	}
	
	
	public AllSpacesPage spaces_dropdown_AllSpaces() {

		action.explicitWait(driver, spacesdropdown,Duration.ofSeconds(10));
		action.click(driver, spacesdropdown);
		action.click(driver, allspaces);
		return new AllSpacesPage(driver);
			
	}
	
	public CalendarPage spaces_dropdown_Calendar() {
//////// its out side the dropedown
		action.explicitWait(driver, spacesdropdown,Duration.ofSeconds(10));
		action.click(driver, spacesdropdown);
		action.click(driver, calendar);
		return new CalendarPage(driver);
			
	}
	
	public SpaceGroupsPage spaces_dropdown_SpaceGroups() {

		action.explicitWait(driver, spacesdropdown,Duration.ofSeconds(10));
		action.click(driver, spacesdropdown);
		action.click(driver, spacegroups);
		return new SpaceGroupsPage(driver);
			
	}
	
	public BoatGroupsPage spaces_dropdown_BoatGroups() {

		action.explicitWait(driver, spacesdropdown,Duration.ofSeconds(10));
		action.click(driver, spacesdropdown);
		action.click(driver, boatgroups);
		return new BoatGroupsPage(driver);
			
	}

}
