@EhabTesting
Feature: Add Employee Feature	
	
	Scenario Outline: Add New employee without 10% deduction. (Automated)
	Given an Admin
	And I am on the Admin Dashboard page
	When I select Add New Employee
	And I enter <first_name>, <last_name>, and <program> info
	And I submit
	Then Employee <first_name>, <last_name>, <program>, <program_bonus>, <total_biweekly_pay> info should display correctly
	Examples:
	|first_name	|last_name|program|program_bonus	|total_biweekly_pay	|
	|"ehab"			|"William"  |"1"      |"5000.00"	|"2192.31"					|
	|"Krishna"	|"amar"			|"2"			|"10000.00"	|"2384.62"					| 
	|"Dan"			|"Garig"		|"3"			|"15000.00"	|"2576.92"					|
	
	
	Scenario Outline: Add New employee with 10% deductions. (Automated)
	Given an Admin
	And I am on the Admin Dashboard page
	When I select Add New Employee
	And I enter <first_name>, <last_name>, and <program> info
	And I submit
	Then Employee <first_name>, <last_name>, <program>, <program_bonus>, <total_biweekly_pay> info should display correctly
	Examples:
	|first_name	|last_name|program|program_bonus	|total_biweekly_pay	|
	|"ajay"			|"singh"		|"1"			|"4500.00"	|"2173.08"					|
	|"Anna"			|"Sarkisian"|"2"			|"9000.00"	|"2346.15"					|
	|"aA"				|"Shearer"	|"3"			|"13500.00"	|"2519.23"					|
	
	
	Scenario: new added employees should exist all the time
	Given an Admin
	And I am on the Admin Dashboard page
	When I select Add New Employee
	And I enter "Tim", "Scott", and "3" info
	And I submit
	Then Employee "Tim", "Scott", "3" shows in the table in Admin Dashboard page
	And the number of table records increased by one.
	When I sign out and in or refresh the page 
	Then Employee "Tim", "Scott", "3" shows in the table in Admin Dashboard page
	
	
	
	