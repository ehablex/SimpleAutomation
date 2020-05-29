package com.testautomation.PageObjects;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import junit.framework.Assert;

public class AdminDashBoardPage {
	WebDriver driver;
	static final String EMPLOYEE_TABLE_CELL_XPATH = "//*[@id=\"employee-table\"]/tbody/tr[%s]/td[%s]"; 
	static final int NUMBER_OF_PAY_PERIODS_PER_YEAR = 26;
	static final double BASE_PAY_PER_PERIOD = 2000.00;
	static final double ANNUAL_BONUS_ONE = 5000.00;
	static final String EXPECTED_HEADER_TEXT = "Admin Dashboard";

	public AdminDashBoardPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how=How.XPATH, using="//*[@id=\"header\"]/h1")
	private WebElement headerText;
	
	@FindBy(how=How.ID, using="btnAddEmployee")
	private WebElement addNewEmployeeButton;
	
	public void clickAddNewEmployee() {
		addNewEmployeeButton.click();
	}
	
	public String getActualHeaderText() {
		return headerText.getText();
	}
	public String getExpectedHeaderText() {
		return EXPECTED_HEADER_TEXT;
	}
	
	private String getTextFromCell(int row, int column) {
		String genericXpath = "//*[@id=\"employee-table\"]/tbody/tr[%s]/td[%s]";
		String actualXpath = String.format(genericXpath, row, column);
		
		return driver.findElement(By.xpath(actualXpath)).getText();
	}
	
	// get the index of record containing first and last name (1 based)
	// return -1 if no match found
	private int getIndexOfRecordContaining(String firstName, String lastName) {
		List<WebElement> rows = (List<WebElement>) driver.findElements(By.xpath("//*[@id=\"employee-table\"]/tbody/tr"));
		int numberOfRows = rows.size();
		for(int i=1; i<=numberOfRows; i++) {
			if(getTextFromCell(i, 2).equalsIgnoreCase(lastName) &&
					getTextFromCell(i, 3).equalsIgnoreCase(firstName)) {
				return i;
			}
		}
		return -1; //return -1 if no match
	}
	
	// will return all info in record in a hashMap
	public HashMap<String, String> getRecordInfoContaining(String firstName, String lastName) {
		int rowIndex = getIndexOfRecordContaining(firstName, lastName); // 1 based
		if(rowIndex<=0) {
			return null;
		}
		
		String row = String.valueOf(rowIndex); //1 based
		
		// -- getting text of varous fields for the record using customized xpath
		String lName = driver.findElement(By.xpath(String.format(EMPLOYEE_TABLE_CELL_XPATH, rowIndex, 2))).getText(); 
		String fName = driver.findElement(By.xpath(String.format(EMPLOYEE_TABLE_CELL_XPATH, rowIndex, 3))).getText();
		String baseAnnualSalary = driver.findElement(By.xpath(String.format(EMPLOYEE_TABLE_CELL_XPATH, rowIndex, 4))).getText();
		String program = driver.findElement(By.xpath(String.format(EMPLOYEE_TABLE_CELL_XPATH, rowIndex, 5))).getText();
		String baseBiweeklyPay = driver.findElement(By.xpath(String.format(EMPLOYEE_TABLE_CELL_XPATH, rowIndex, 6))).getText();
		String yearlyProgramBonus = driver.findElement(By.xpath(String.format(EMPLOYEE_TABLE_CELL_XPATH, rowIndex, 7))).getText();
		String totalBiweeklyPay = driver.findElement(By.xpath(String.format(EMPLOYEE_TABLE_CELL_XPATH, rowIndex, 8))).getText();
		
		HashMap<String, String> resultMap = new HashMap<>();
		resultMap.put("Last Name", lName);
		resultMap.put("First Name", fName);
		resultMap.put("Base Annual Salary", baseAnnualSalary);
		resultMap.put("Program", program);
		resultMap.put("Base Biweekly Pay", baseBiweeklyPay);
		resultMap.put("Yearly Program Bonus", yearlyProgramBonus);
		resultMap.put("Total Biweekly Pay", totalBiweeklyPay);
		
		System.out.print(resultMap);
		
		return resultMap;
	}
	
	// will return a hash map that contains Program bonus and adjusted pay amount
	public HashMap<String, String> getExpectedVAlues(String firstName, String program) {
		double annualBonus = Integer.parseInt(program)*ANNUAL_BONUS_ONE; // will calculate the annual bonus
		if(firstName.charAt(0)=='A' || firstName.charAt(0)=='a') {
			annualBonus = annualBonus * 0.9;  // after 10 % reduction (weird AC)
		}
		double payPeriodBonus = annualBonus/NUMBER_OF_PAY_PERIODS_PER_YEAR;
		double payPeriodTotalPay = payPeriodBonus + BASE_PAY_PER_PERIOD;
		double baseYearlySalary = BASE_PAY_PER_PERIOD * NUMBER_OF_PAY_PERIODS_PER_YEAR;
		
		
		final DecimalFormat df = new DecimalFormat("0.00");
		df.setRoundingMode(RoundingMode.UP);
		String yearlyBonus = df.format(annualBonus);
		String periodTotalPay = df.format(payPeriodTotalPay);
		String baseBiweeklyPay = df.format(BASE_PAY_PER_PERIOD);
		String baseAnnualSalary = df.format(baseYearlySalary);
		
		HashMap<String, String> resultMap = new HashMap<>();
		resultMap.put("Program Bonus", yearlyBonus);
		resultMap.put("Total Biweekly Pay", periodTotalPay);
		resultMap.put("Base Biweekly Pay", baseBiweeklyPay);
		resultMap.put("Base Annual Salary", baseAnnualSalary);
		
		return resultMap;
	}

}
