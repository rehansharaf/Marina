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
	
	@FindBy(how = How.XPATH, using = "//input[@id='email']")
	WebElement Email;
	
	@FindBy(how = How.XPATH, using = "//input[@id='password']")
	WebElement password;
	
	@FindBy(how = How.XPATH, using = "//small[text()='Forgot your password?']")
	WebElement forgotpass;
	
	
	@FindBy(how = How.XPATH, using = "//input[@id='remember_me']")
	WebElement rememberpass;
	
	
	@FindBy(how = How.XPATH, using = "//a[text()='Self Registration']")
	WebElement selfregistration;
	

	@FindBy(how = How.XPATH, using = "//button[@type='submit']")
	WebElement btnLogin;
	
	
	
	
	
	public LoginPage(WebDriver driver) {
	
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	
	public HomePage login(String email, String pass) {
		
		Email.sendKeys(email);
		password.sendKeys(pass);
		btnLogin.click();
		return new HomePage(driver);
		
	}
	
	
	

	
}
