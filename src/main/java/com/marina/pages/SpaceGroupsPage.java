package com.marina.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.marina.actiondriver.Action;

public class SpaceGroupsPage {

	WebDriver driver;
	Action action = new Action();

	public SpaceGroupsPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
}