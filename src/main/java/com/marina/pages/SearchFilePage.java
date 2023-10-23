package com.marina.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.marina.base.TestBase;

public class SearchFilePage {

	WebDriver driver;
	
	@FindBy(how = How.XPATH, using = "//td[text()='Search File']")
	WebElement searchFilePageHeading;

	
	public SearchFilePage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	
	public String verifySearchFilePage() {
		
		return searchFilePageHeading.getText();
	}
	
	
	
}
