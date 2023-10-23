package com.marina.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserFactory {
	
	public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	public static BrowserFactory instance;
	
	private BrowserFactory() {}
	
	public static synchronized BrowserFactory getInstance() {
		
		if(instance == null)
			instance = new BrowserFactory();
		 
		return instance;
	}
	
	 
	public void setDriver(String browserName) {
		
		if (browserName.equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromedriver().clearDriverCache().setup();
			WebDriverManager.chromedriver().setup();
			
			//System.setProperty("webdriver.chrome.driver", "C:/drivers/chromedriver.exe");
			// Set Browser to ThreadLocalMap
			driver.set(new ChromeDriver());
			
		} else if (browserName.equalsIgnoreCase("FireFox")) {
			WebDriverManager.firefoxdriver().setup();
			driver.set(new FirefoxDriver());
		} else if (browserName.equalsIgnoreCase("IE")) {
			WebDriverManager.iedriver().setup();
			driver.set(new InternetExplorerDriver());
		}
			
	}
	
	public WebDriver getDriver() {
		// Get Driver from threadLocalmap
		return driver.get();
	}
	
	public void removeDriver() {
		
		getDriver().quit();
		driver.remove();
	}

}
