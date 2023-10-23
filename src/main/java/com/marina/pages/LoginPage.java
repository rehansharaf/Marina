package com.marina.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.marina.base.TestBase;

public class LoginPage {
	
	WebDriver driver;
	
	@FindBy(how = How.ID, using = "txtLogin")
	WebElement username;
	
	@FindBy(how = How.ID, using = "txtPassword")
	WebElement password;

	@FindBy(how = How.ID, using = "btnLogin")
	WebElement btnLogin;
	
	
	
	public LoginPage(WebDriver driver) {
	
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	
	public HomePage login(String user, String pass) {
		
		username.sendKeys(user);
		password.sendKeys(pass);
		btnLogin.click();
		return new HomePage(driver);
		
	}
	
	
	

	
}
