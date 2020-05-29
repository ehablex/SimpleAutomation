package com.testautomation.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class AddEmployeePage {
	WebDriver driver;

	public AddEmployeePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(how=How.XPATH, using="//*[@id=\"employees-form\"]/div[1]/div/input")
	private WebElement firstNameTextField;
	
	@FindBy(how=How.XPATH, using="//*[@id=\"employees-form\"]/div[2]/div/input")
	private WebElement lastNameTextField;
	
	@FindBy(how=How.XPATH, using="//*[@id=\"employees-form\"]/div[3]/div/input")
	private WebElement programTextField;
	
	@FindBy(how=How.XPATH, using="//*[@id=\"employees-form\"]/div[4]/div/button[1]")
	private WebElement submitButton;
	
	@FindBy(how=How.CLASS_NAME, using="btn btn-default")
	private WebElement closeButton;
	
	public void fillEmployeeInfo(String firstName, String lastName, String program) {
		firstNameTextField.sendKeys(firstName);
		lastNameTextField.sendKeys(lastName);
		programTextField.sendKeys(program);
	}
	
	public void clickSubmit() throws InterruptedException {
		submitButton.click();
		Thread.sleep(3000);
	}
	
	
	

}
