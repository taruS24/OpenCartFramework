package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("EPIC - 100: Design of the login page for opencart application")
@Story("US200: Implement Login page features for opencart app")
public class AccountsPageTest extends BaseTest {

	@BeforeClass
	public void accountPageSetup() {
		accountsPage = loginPage.doLogin(prop.getProperty("userName"), prop.getProperty("password"));
	}
	
	@Description("Login page title test")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void accPageTitleTest() {
		String accTitle = accountsPage.verifyPageTitle();
		Assert.assertEquals(accTitle, AppConstants.ACCOUNTS_PAGE_TITLE);
	}

	
	@Description("Login page URL test")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void isLogoutLinkExistTest() {
		Assert.assertTrue(accountsPage.ifLogoutLinkExist());
	}

	@Description("Count headers")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void accountsPageHeadersCountTest() {
		int headerCount = accountsPage.verifyAccountsPageHeadersCount();
		Assert.assertEquals(headerCount, AppConstants.ACCOUNTS_PAGE_HEADER_COUNT);
	}

	@Test
	public void accountsPageHeadersTest() {
		List<String> acctualAccountsPageHeaderList = accountsPage.verifyAccountsPageHeaderNames();
		Assert.assertEquals(acctualAccountsPageHeaderList, AppConstants.ACCOUNTS_PAGE_EXPECTED_HEADERS_LIST);
	}

	// The return type of data providers are always two dimensional Object
	// arrays as it can store data of any type
	@DataProvider
	public Object[][] searchOptions() {
		Object[][] searchOptions = { { "macbook", 3 }, { "imac", 1 }, { "samsung", 2 } };
		return searchOptions;
	}

	@Test(dataProvider = "searchOptions")
	public void searchTest(String searchableProduct, int expectedResultCount) {
		searchResultsPage = accountsPage.doSearch(searchableProduct);
		int actualResultCount = searchResultsPage.getSearchResultCount();		
		System.out.println("Expectd Result count for " + searchableProduct + " is: " + expectedResultCount);
		System.out.println("Actual Result count for " + searchableProduct + " is: " + actualResultCount);

		Assert.assertEquals(actualResultCount, expectedResultCount);
	}
}
