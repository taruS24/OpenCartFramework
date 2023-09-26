package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ExcelUtil;

public class RegisterAccountPageTest extends BaseTest {

	@BeforeClass
	public void RegisterAccPageSetup() {
		regAccPage = loginPage.goToReistrationPage();
		// accountsPage = loginPage.doLogin(prop.getProperty("userName"),
		// prop.getProperty("password"));
	}

	@Test
	public void regPageTitleTest() {
		String pageTitle = regAccPage.getRegisterPageTitle();
		System.out.println("Actual registration page title is: " + pageTitle);
		Assert.assertEquals(pageTitle, AppConstants.REGISTER_PAGE_TITLE);
	}

	@Test
	public void regPageURLTest() {
		String regPageURL = regAccPage.getRegisterPageURL();
		System.out.println("Actual registration page URL is: " + regPageURL);
		Assert.assertTrue(regPageURL.contains(AppConstants.REGISTER_PAGE_URL_FRACTION));
	}

	@Test
	public void regPageFormHeaderTest() {
		String formHeader = regAccPage.getRegisterFormHeader();
		System.out.println("Actual Register form header is: " + formHeader);
		Assert.assertEquals(formHeader, AppConstants.REGISTER_PAGE_FORM_HEADER);
	}

	@DataProvider
	public Object[][] registrationData() {
		return new Object[][] {
				{ "test2", "last2", "test2@" + System.currentTimeMillis() + "123.com", "2340646544", "test@123" },
				{ "test3", "last3", "test@" + System.currentTimeMillis() + "123.com", "2340646544", "test@123" } };
	}
	
	
	@DataProvider
	public Object[][] registrationDataFromExcel() {
		return ExcelUtil.getSheetData(AppConstants.REGISTER_SHEET_NAME);
	}

	// Always the Tests without priority are run first (based on the
	// alphabetical order) followed by priority ones
	@Test(dataProvider = "registrationDataFromExcel", priority = 1)
	public void fillRegistrationFormTest(String firstNameTxt, String lastNameTxt, String emailTxt, String phoneNum,
			String pwd) {
//		accSuccessPage = regAccPage.doFillRegistrationForm(firstNameTxt, lastNameTxt, emailTxt, phoneNum, pwd);
//		String actualSuccessMsg = accSuccessPage.getSuccessMsg();
//		Assert.assertEquals(actualSuccessMsg, AppConstants.REGISTER_SUCCESS_PAGE_MSG);

		Assert.assertTrue(regAccPage.doFillRegistrationForm(firstNameTxt, lastNameTxt, emailTxt, phoneNum, pwd));
	}

}
