package com.marina.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.marina.actiondriver.Action;
import com.marina.base.TestBase;

public class ImportSpacesPage {

	WebDriver driver;
	Action action = new Action();
	
	
	@FindBy(how = How.XPATH, using = "//h1[@class='mb-0 text-white page-nav fs_22 fw_6 d-flex align-items-center']")
	WebElement pageHeading;
	
	@FindBy(how = How.XPATH, using = "//input[@id='file']")
	WebElement fileField;
	
	@FindBy(how = How.XPATH, using = "//button[@class='btn btn-primary mt-sm-1']")
	WebElement submitBtn;
	
	@FindBy(how = How.XPATH, using = "//div[@class='alert alert-success alert-dismissible fade show']")
	WebElement importSuccessMsg;
	
	@FindBy(how = How.XPATH, using = "//a[text()='Spaces']")
	WebElement spaceBreadcrum;
	
	
	public ImportSpacesPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	
	public AllSpacesPage importSpacesData(String filename) {
		
		String filepath = System.getProperty("user.dir")+"/src/test/resources/testdata/"+filename+".xlsx";
		action.explicitWait(driver, pageHeading, Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		action.type(fileField, filepath);
		action.click(driver, submitBtn);
		action.explicitWait(driver, importSuccessMsg, Duration.ofSeconds(Integer.parseInt(TestBase.prop.getProperty("timeout"))));
		action.click1(spaceBreadcrum, "space breadcrum");
		return new AllSpacesPage(driver);

	}
	
	
	
	
}
