package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.CreateAccountSuccessPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductDescriptionPage;
import com.qa.opencart.pages.RegisterAccountPage;
import com.qa.opencart.pages.SearchResultsPage;

public class BaseTest {

	// Without access modifier, cannot access LoginPage reference variable in
	// the Loin test class because -
	// by default AM is 'default' - can access only by classes within same
	// package
	// We should not make the AM -
	// a. public - can be accessed by only class from any package (not best
	// practice anyone will be able to modify it)
	// b. private - will not be able to be accessed by any other class (same or
	// different package)

	WebDriver driver;
	protected Properties prop;
	DriverFactory df;
	protected LoginPage loginPage;
	protected AccountsPage accountsPage;
	protected SearchResultsPage searchResultsPage;
	protected ProductDescriptionPage productDescPage;
	protected RegisterAccountPage regAccPage;
	protected CreateAccountSuccessPage accSuccessPage;
	protected SoftAssert softAssert;

	@BeforeTest
	public void setUp() {
		df = new DriverFactory();
		prop = df.initProp();
		driver = df.initDriver(prop);
		loginPage = new LoginPage(driver);
		softAssert = new SoftAssert();
		
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
