package com.testautomation.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how=How.NAME, using="form-username")
	private WebElement userNameTextBox;
	
	@FindBy(how=How.NAME, using="form-password")
	private WebElement passWordTextField;
	
	@FindBy(how=How.ID, using="btnLogin")
	private WebElement loginButton;
	
	public AdminDashBoardPage loginAsAdmin(String userName, String passWord) {
		userNameTextBox.sendKeys(userName);
		passWordTextField.sendKeys(passWord);
		loginButton.click();
		return new AdminDashBoardPage(driver);
	}

}