package com.marina.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.marina.actiondriver.Action;

public class AllSpacesPage {
	
	WebDriver driver;
	Action action = new Action();
	
	@FindBy(how = How.XPATH, using = "//span[text()='Denham Bay Marina']")
	WebElement dashboardText;
	
	public AllSpacesPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	

}
