package com.marina.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.marina.actiondriver.Action;

public class LoginPage {
	
	WebDriver driver;
	Action action = new Action();
	
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
		
		action.type(Email, email);
		action.type(password, pass);
		action.click1(btnLogin, "Login btn");
		return new HomePage(driver);
		
	}
	
	
	

	
}
