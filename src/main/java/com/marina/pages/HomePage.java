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
	
	@FindBy(how = How.XPATH, using = "//span[text()='DockSpace Test']")
	WebElement dashboardText;

	@FindBy(how = How.XPATH, using = "//div[text()='Spaces']")
	WebElement spacesdropdown;

	@FindBy(how = How.XPATH, using = "//div[text()='Space Types']")
	WebElement Spacetypes;

	@FindBy(how = How.XPATH, using = "//div[text()='All Spaces']/parent::a")
	WebElement allspaces;

	@FindBy(how = How.XPATH, using = "//div[@class='sdbr-calendar']/parent::a")
	WebElement calendar;
	
	@FindBy(how = How.XPATH, using = "//div[@class='sdbr-reservations']/parent::a")
	WebElement reservation;

	@FindBy(how = How.XPATH, using = "//div[text()='Space Groups']")
	WebElement spacegroups;

	@FindBy(how = How.XPATH, using = "//div[@class='sdbr-boat-groups']/parent::a")
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
	
	
	public AllSpacesPage spaces_dropdown_AllSpaces() throws InterruptedException {

		action.scrollByVisibilityOfElement(driver, spacesdropdown);
		action.explicitWait(driver, spacesdropdown,Duration.ofSeconds(10));
		action.click1(spacesdropdown, "Spaces DropDown");
		action.click1(allspaces, "All Space Link");
		return new AllSpacesPage(driver);
			
	}
	
	
	public SpaceGroupsPage spaces_dropdown_SpaceGroups() throws InterruptedException {

		action.scrollByVisibilityOfElement(driver, spacesdropdown);
		action.explicitWait(driver, spacesdropdown,Duration.ofSeconds(10));
		action.click1(spacesdropdown, "Spaces DropDown");
		action.click1(spacegroups, "Space Groups Link");
		return new SpaceGroupsPage(driver);
		
			
	}
	
	public BoatGroupsPage spaces_dropdown_BoatGroups() throws InterruptedException {

		action.scrollByVisibilityOfElement(driver, spacesdropdown);
		action.explicitWait(driver, spacesdropdown,Duration.ofSeconds(10));
		action.click1(spacesdropdown, "Spaces DropDown");
		action.click1(boatgroups, "Boat Group Link");
		return new BoatGroupsPage(driver);
			
	}
	
	public CalendarPage calendarLink() {

		action.explicitWait(driver, spacesdropdown,Duration.ofSeconds(10));
		action.click1(calendar, "Calendar link");
		return new CalendarPage(driver);
			
	}
	
	public ReservationsPage reservationLink() throws InterruptedException {
		
		action.scrollByVisibilityOfElement(driver, spacesdropdown);
		action.explicitWait(driver, spacesdropdown,Duration.ofSeconds(10));
		action.click1(reservation, "ReservationsPage link");
		return new ReservationsPage(driver);

	}

}
