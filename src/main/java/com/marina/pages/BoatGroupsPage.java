package com.marina.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.marina.actiondriver.Action;

public class BoatGroupsPage {

	WebDriver driver;
	Action action = new Action();

	@FindBy(how = How.XPATH, using = "//h1[@class='mb-0 text-white page-nav fs_22 fw_6 d-flex align-items-center']")
	WebElement pageHeading;
	
	
	public BoatGroupsPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public String verifyBoatGroupPageHeading() {
	
		action.explicitWait(driver, pageHeading, Duration.ofSeconds(10));
		return pageHeading.getText().trim();
		
	}
}
