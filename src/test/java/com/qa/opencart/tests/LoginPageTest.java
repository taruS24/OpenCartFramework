package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class LoginPageTest extends BaseTest {

	@Test(priority = 1)
	public void loginPageTitleTest() {
		String acTualLoginPageTitle = loginPage.getLoginPageTitle();
		Assert.assertEquals(acTualLoginPageTitle, AppConstants.LOGIN_PAGE_TITLE);
	}

	@Test(priority = 2)
	public void loginPageURLTest() {
		String pageURL = loginPage.getLoginPageURL();
		Assert.assertTrue(pageURL.contains(AppConstants.LOGIN_PAGE_URL_FRACTION));
	}

	@Test(priority = 3)
	public void isForgotPwdLinkAvaiableTest() {
		Assert.assertTrue(loginPage.isForgotPasswordLinkExist());
	}

	@Test(priority = 4)
	public void loginTest() {
		accountsPage = loginPage.doLogin(prop.getProperty("userName"), prop.getProperty("password"));
		Assert.assertEquals(accountsPage.verifyPageTitle(), AppConstants.ACCOUNTS_PAGE_TITLE);
	}

}
